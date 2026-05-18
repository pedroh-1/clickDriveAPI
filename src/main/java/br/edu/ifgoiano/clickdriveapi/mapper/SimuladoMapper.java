package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.SimuladoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.SimuladoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimuladoMapper {

    private final QuestaoMapper questaoMapper;

    public SimuladoMapper(QuestaoMapper questaoMapper) {
        this.questaoMapper = questaoMapper;
    }

    public SimuladoModel toEntity(SimuladoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        SimuladoModel simulado = new SimuladoModel();
        simulado.setId(dto.id());
        simulado.setDataRealizacao(dto.dataRealizacao());
        simulado.setPontuacao(dto.pontuacao());
        simulado.setAprovado(dto.aprovado());
        simulado.setAlunoId(dto.alunoId());

        if (dto.questaoIds() != null) {
            List<QuestaoModel> questoes = dto.questaoIds().stream()
                    .map(this::questaoReference)
                    .toList();
            simulado.setQuestoes(questoes);
        }

        return simulado;
    }

    public SimuladoResponseDTO toResponseDTO(SimuladoModel entity) {
        if (entity == null) {
            return null;
        }

        return new SimuladoResponseDTO(
                entity.getId(),
                entity.getDataRealizacao(),
                entity.getPontuacao(),
                entity.getAprovado(),
                entity.getAlunoId(),
                entity.getQuestoes() == null ? List.of() : entity.getQuestoes().stream()
                        .map(questaoMapper::toResponseDTO)
                        .toList()
        );
    }

    private QuestaoModel questaoReference(Long id) {
        QuestaoModel questao = new QuestaoModel();
        questao.setId(id);
        return questao;
    }
}
