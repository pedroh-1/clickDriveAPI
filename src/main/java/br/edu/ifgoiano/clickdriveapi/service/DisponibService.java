package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.model.DisponibModel;
import br.edu.ifgoiano.clickdriveapi.repository.DisponibRepository;
import br.edu.ifgoiano.clickdriveapi.repository.DisponibRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibService {

    private final DisponibRepository DisponibRepository;

    public DisponibService(DisponibRepository DisponibRepository) {
        this.DisponibRepository = DisponibRepository;
    }

    public DisponibModel salvar(DisponibModel disponibilidade) {
        return DisponibRepository.save(disponibilidade);
    }

    public List<DisponibModel> listarTodas() {
        return DisponibRepository.findAll();
    }

    public Optional<DisponibModel> buscarPorId(Long id) {
        return DisponibRepository.findById(id);
    }

    public void deletar(Long id) {
        DisponibRepository.deleteById(id);
    }

    public DisponibModel atualizar(Long id, DisponibModel disponibilidadeAtualizada) {
        DisponibModel existente = DisponibRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));

        // Atualiza os campos solicitados
        existente.setDiasSemana(disponibilidadeAtualizada.getDiasSemana());
        existente.setHorario(disponibilidadeAtualizada.getHorario());
        existente.setHoraFim(disponibilidadeAtualizada.getHoraFim());
        existente.setAtivo(disponibilidadeAtualizada.getAtivo());

        // Atualiza o instrutor vinculado, se necessário
        if (disponibilidadeAtualizada.getInstrutor() != null) {
            existente.setInstrutor(disponibilidadeAtualizada.getInstrutor());
        }

        return DisponibRepository.save(existente);
    }
}