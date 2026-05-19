package br.edu.ifgoiano.clickdriveapi.service;

import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.mapper.QuestaoMapper;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import br.edu.ifgoiano.clickdriveapi.repository.QuestaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoService {

    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private QuestaoMapper questaoMapper;

    public QuestaoResponseDTO salvarQuestao(QuestaoRequestDTO questaoDTO) {
        QuestaoModel questao = questaoMapper.toEntity(questaoDTO);
        QuestaoModel questaoSalva = salvarQuestao(questao);
        return questaoMapper.toResponseDTO(questaoSalva);
    }

    public QuestaoModel salvarQuestao(QuestaoModel questao) {
        if (questao.getOpcoes() != null) {
            questao.getOpcoes().forEach(opcao -> opcao.setQuestao(questao));

            long opcoesCorretas = questao.getOpcoes().stream()
                    .filter(opcao -> opcao.getCorreta() != null && opcao.getCorreta())
                    .count();

            if (opcoesCorretas != 1) {
                throw new IllegalArgumentException("A questão deve ter exatamente UMA opção correta.");
            }
        } else {
             throw new IllegalArgumentException("A questão deve ter pelo menos uma opção, e exatamente uma delas deve ser correta.");
        }

        return questaoRepository.save(questao);
    }

    public List<QuestaoModel> listarTodas(){
        return questaoRepository.findAll();
    }

    public List<QuestaoResponseDTO> listarTodasDTO() {
        return questaoMapper.toResponseDTOList(listarTodas());
    }

    public Optional<QuestaoModel> buscarPorId(Long id){
        return questaoRepository.findById(id);
    }

    public Optional<QuestaoResponseDTO> buscarPorIdDTO(Long id) {
        return buscarPorId(id).map(questaoMapper::toResponseDTO);
    }

    public List<QuestaoModel> findByCategoria(String categoria) {
        return questaoRepository.findByCategoria(categoria);
    }

    @Transactional
    public void deletarQuestao(Long id) {
        questaoRepository.deleteById(id);
    }

}
