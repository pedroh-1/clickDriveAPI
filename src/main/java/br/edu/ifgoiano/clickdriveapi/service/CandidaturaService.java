package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import br.edu.ifgoiano.clickdriveapi.repository.CandidaturaRepository;
import br.edu.ifgoiano.clickdriveapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final UsuarioRepository usuarioRepository;

    public CandidaturaService(CandidaturaRepository candidaturaRepository, UsuarioRepository usuarioRepository){
        this.candidaturaRepository = candidaturaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CandidaturaModel salvar(CandidaturaModel candidatura) {
        if (candidatura.getUsuario() != null && candidatura.getUsuario().getId() != null) {
            usuarioRepository.findById(candidatura.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário Candidato não encontrado"));
        }

        candidatura.setStatus(StatusCandidatura.PENDENTE);
        return candidaturaRepository.save(candidatura);
    }

    public List<CandidaturaModel> listarTodas() {
        return candidaturaRepository.findAll();
    }

    @Transactional
    public CandidaturaModel aprovarInstrutor(Long id) {
        CandidaturaModel candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        candidatura.setStatus(StatusCandidatura.APROVADA);

        if (candidatura.getUsuario() != null) {
            Long usuarioId = candidatura.getUsuario().getId();

            // Agora passamos dinamicamente os dados reais enviados pelo candidato!
            usuarioRepository.promoverParaInstrutor(
                    usuarioId,
                    candidatura.getCredenciamentoPretendido(),
                    "A" // Você pode adicionar categoriaPretendida na candidatura também!
            );
        }

        return candidaturaRepository.save(candidatura);
    } // Chave corrigida aqui!

    public void deletar(Long id) {
        candidaturaRepository.deleteById(id);
    }

    public List<CandidaturaModel> findByStatus(StatusCandidatura status) {
        return candidaturaRepository.findByStatus(status);
    }
}