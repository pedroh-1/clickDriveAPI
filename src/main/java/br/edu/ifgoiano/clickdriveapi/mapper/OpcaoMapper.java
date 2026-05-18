package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.OpcaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.OpcaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.OpcaoModel;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.springframework.stereotype.Component;

@Component
public class OpcaoMapper {

    public OpcaoModel toEntity(OpcaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        OpcaoModel opcao = new OpcaoModel();
        opcao.setId(dto.id());
        opcao.setTexto(dto.texto());
        opcao.setCorreta(dto.correta());

        if (dto.questaoId() != null) {
            QuestaoModel questao = new QuestaoModel();
            questao.setId(dto.questaoId());
            opcao.setQuestao(questao);
        }

        return opcao;
    }

    public OpcaoResponseDTO toResponseDTO(OpcaoModel entity) {
        if (entity == null) {
            return null;
        }

        Long questaoId = entity.getQuestao() != null ? entity.getQuestao().getId() : null;
        return new OpcaoResponseDTO(
                entity.getId(),
                entity.getTexto(),
                entity.getCorreta(),
                questaoId
        );
    }
}
