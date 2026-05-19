package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.OpcaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.OpcaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.OpcaoModel;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OpcaoMapper {

    @Mapping(target = "questao", source = "questaoId")
    OpcaoModel toEntity(OpcaoRequestDTO dto);

    @Mapping(target = "questaoId", source = "questao.id")
    OpcaoResponseDTO toResponseDTO(OpcaoModel entity);

    default QuestaoModel mapQuestao(Long questaoId) {
        if (questaoId == null) {
            return null;
        }
        QuestaoModel questao = new QuestaoModel();
        questao.setId(questaoId);
        return questao;
    }
}
