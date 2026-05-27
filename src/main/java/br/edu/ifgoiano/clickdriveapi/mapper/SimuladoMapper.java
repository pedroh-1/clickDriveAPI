package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.SimuladoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.SimuladoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import br.edu.ifgoiano.clickdriveapi.model.SimuladoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = QuestaoMapper.class)
public interface SimuladoMapper {

    @Mapping(target = "questoes", source = "questaoIds")
    SimuladoModel toEntity(SimuladoRequestDTO dto);

    SimuladoResponseDTO toResponseDTO(SimuladoModel entity);

    default QuestaoModel mapQuestao(Long questaoId) {
        if (questaoId == null) {
            return null;
        }
        QuestaoModel questao = new QuestaoModel();
        questao.setId(questaoId);
        return questao;
    }

    List<SimuladoResponseDTO> toResponseDTOList(List<SimuladoModel> entities);
}
