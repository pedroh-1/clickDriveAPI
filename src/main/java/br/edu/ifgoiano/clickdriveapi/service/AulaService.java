package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.AulaModel;
import br.edu.ifgoiano.clickdriveapi.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;

    public AulaService(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    public AulaModel salvar(AulaModel aula) {
        // Aqui você pode adicionar validações futuras, como verificar conflitos de horário
        return aulaRepository.save(aula);
    }

    public List<AulaModel> listarTodas() {
        return aulaRepository.findAll();
    }

    public Optional<AulaModel> buscarPorId(Long id) {
        return aulaRepository.findById(id);
    }

    public void deletar(Long id) {
        aulaRepository.deleteById(id);
    }

    public AulaModel atualizar(Long id, AulaModel aulaAtualizada) {
        AulaModel existente = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));

        // Atualiza os campos conforme solicitado
        existente.setData(aulaAtualizada.getData());
        existente.setHorario(aulaAtualizada.getHorario());
        existente.setCategoriaVeiculo(aulaAtualizada.getCategoriaVeiculo());

        // Se houver relacionamentos (Aluno/Instrutor), eles também podem ser atualizados aqui
        if (aulaAtualizada.getAluno() != null) {
            existente.setAluno(aulaAtualizada.getAluno());
        }
        if (aulaAtualizada.getInstrutor() != null) {
            existente.setInstrutor(aulaAtualizada.getInstrutor());
        }

        return aulaRepository.save(existente);
    }
}