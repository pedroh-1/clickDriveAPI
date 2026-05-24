package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.CandidaturaModel;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
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
        // Valida se o Usuário base realmente existe antes de criar a candidatura
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

        // 1. Atualiza o status da candidatura para APROVADA
        candidatura.setStatus(StatusCandidatura.APROVADA);

        // 2. Transforma o usuário em Instrutor e salva na tabela 'instrutores'
        UsuarioModel usuarioBase = candidatura.getUsuario();
        if (usuarioBase != null) {
            // Buscamos ou instanciamos o Instrutor polimorficamente
            InstrutorModel instrutor = new InstrutorModel();
            instrutor.setId(usuarioBase.getId());
            instrutor.setNome(usuarioBase.getNome());
            instrutor.setEmail(usuarioBase.getEmail());
            instrutor.setSenha(usuarioBase.getSenha());
            instrutor.setTelefone(usuarioBase.getTelefone());
            instrutor.setAprovado(true); // Agora ele está oficialmente aprovado!

            // Salva na tabela de instrutores
            usuarioRepository.save(instrutor);

            // 3. Sincronização transacional do perfil lágico
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