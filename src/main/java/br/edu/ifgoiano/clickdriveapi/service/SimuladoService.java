package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.dto.SimuladoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.SimuladoMapper;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import br.edu.ifgoiano.clickdriveapi.repository.QuestaoRepository;
import br.edu.ifgoiano.clickdriveapi.repository.SimuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimuladoService {

    @Autowired
    private SimuladoRepository simuladoRepository;

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private SimuladoMapper simuladoMapper;

    public SimuladoResponseDTO gerarSimuladoDTO(Long alunoId) {
        return simuladoMapper.toResponseDTO(gerarSimulado(alunoId));
    }

    public SimuladoModel gerarSimulado(Long alunoId) {

        // 1. Busca 5 questões aleatórias
        List<QuestaoModel> questoesSorteadas = questaoRepository.buscarQuestoesAleatorias(5);

        if (questoesSorteadas.isEmpty()) {
            throw new RuntimeException("Não há questões cadastradas no banco para gerar o simulado!");
        }

        // 2. Monta a prova do aluno
        SimuladoModel novoSimulado = new SimuladoModel();
        novoSimulado.setAlunoId(alunoId);
        novoSimulado.setDataRealizacao(LocalDateTime.now());
        novoSimulado.setQuestoes(questoesSorteadas);

        novoSimulado.setPontuacao(0);
        novoSimulado.setAprovado(false);

        return simuladoRepository.save(novoSimulado);
    }

    public SimuladoModel buscarPorId(Long id) {
        return simuladoRepository.findById(id).orElse(null);
    }

    public SimuladoResponseDTO buscarPorIdDTO(Long id) {
        SimuladoModel simulado = buscarPorId(id);
        return simulado != null ? simuladoMapper.toResponseDTO(simulado) : null;
    }

    public List<SimuladoResponseDTO> listarTodosDTO() {
        return simuladoMapper.toResponseDTOList(simuladoRepository.findAll());
    }
}
