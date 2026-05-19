package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.PagamentoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.PagamentoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.PagamentoModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoModel toEntity(PagamentoRequestDTO dto);

    PagamentoResponseDTO toResponseDTO(PagamentoModel entity);

    List<PagamentoResponseDTO> toResponseDTOList(List<PagamentoModel> entities);
}
