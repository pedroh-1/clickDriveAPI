package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.AvaliacaoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    AvaliacaoModel toEntity(AvaliacaoRequestDTO dto);

    AvaliacaoResponseDTO toResponseDTO(AvaliacaoModel entity);

    List<AvaliacaoResponseDTO> toResponseDTOList(List<AvaliacaoModel> entities);
}
