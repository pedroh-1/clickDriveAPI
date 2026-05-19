package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.QuestaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.QuestaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = OpcaoMapper.class)
public interface QuestaoMapper {

    QuestaoModel toEntity(QuestaoRequestDTO dto);

    QuestaoResponseDTO toResponseDTO(QuestaoModel entity);

    List<QuestaoResponseDTO> toResponseDTOList(List<QuestaoModel> entities);
}
