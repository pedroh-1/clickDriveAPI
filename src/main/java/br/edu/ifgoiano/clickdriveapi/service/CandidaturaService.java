package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import br.edu.ifgoiano.clickdriveapi.repository.CandidaturaRepository;
import br.edu.ifgoiano.clickdriveapi.repository.InstrutorRepository;
import br.edu.ifgoiano.clickdriveapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final InstrutorRepository instrutorRepository;
    private final UsuarioRepository usuarioRepository;

    public CandidaturaService(CandidaturaRepository candidaturaRepository, InstrutorRepository instrutorRepository, UsuarioRepository usuarioRepository) {
        this.candidaturaRepository = candidaturaRepository;
        this.instrutorRepository = instrutorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CandidaturaModel salvar(CandidaturaModel candidatura) {
        // Busca o instrutor no banco para garantir que o vínculo não seja nulo na resposta
        if (candidatura.getInstrutor() != null && candidatura.getInstrutor().getId() != null) {
            InstrutorModel instrutorExistente = instrutorRepository.findById(candidatura.getInstrutor().getId())
                    .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));
            candidatura.setInstrutor(instrutorExistente);
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

        // 1. Atualiza o status da candidatura para APROVADA
        candidatura.setStatus(StatusCandidatura.APROVADA);

        // 2. Atualiza o instrutor vinculado para aprovado = true
        InstrutorModel instrutor = candidatura.getInstrutor();
        if (instrutor != null) {
            instrutor.setAprovado(true);
            instrutorRepository.save(instrutor);
            
            // 3. Sincronização transacional: Garante que o perfil no banco seja "INSTRUTOR"
            usuarioRepository.atualizarPerfil(instrutor.getId(), "INSTRUTOR");
        }

        return candidaturaRepository.save(candidatura);
    }

    public void deletar(Long id) {
        candidaturaRepository.deleteById(id);
    }

    // O retorno deve ser List<CandidaturaModel> e não InstrutorModel
    public List<CandidaturaModel> findByStatus(StatusCandidatura status) {
        // Você deve usar o repositório de Candidatura para filtrar
        return candidaturaRepository.findByStatus(status);
    }


   
}