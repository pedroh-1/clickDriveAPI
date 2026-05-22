package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
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
        // Valida se o Usuário base existe antes de criar o processo de análise
        if (candidatura.getInstrutor() != null && candidatura.getInstrutor().getId() != null) {
            usuarioRepository.findById(candidatura.getInstrutor().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario Candidato não encontrado"));
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

            // CORREÇÃO: Usamos o usuarioRepository que está injetado no topo da classe!
            usuarioRepository.save(instrutor);

            // 3. Sincronização transacional: Garante que o perfil no banco mude para "INSTRUTOR"
            usuarioRepository.atualizarPerfil(instrutor.getId(), "INSTRUTOR");
        }

        return candidaturaRepository.save(candidatura);
    }

    public void deletar(Long id) {
        candidaturaRepository.deleteById(id);
    }

    public List<CandidaturaModel> findByStatus(StatusCandidatura status) {
        return candidaturaRepository.findByStatus(status);
    }
}