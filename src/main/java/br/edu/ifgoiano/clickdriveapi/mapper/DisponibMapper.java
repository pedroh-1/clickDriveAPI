package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.DisponibRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DisponibResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.DisponibModel;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisponibMapper {

    @Mapping(target = "instrutor", source = "instrutorId")
    DisponibModel toEntity(DisponibRequestDTO dto);

    @Mapping(target = "instrutorId", source = "instrutor.id")
    DisponibResponseDTO toResponseDTO(DisponibModel entity);

    List<DisponibResponseDTO> toResponseDTOList(List<DisponibModel> entities);

    default InstrutorModel mapInstrutor(Long instrutorId) {
        if (instrutorId == null) return null;
        InstrutorModel instrutor = new InstrutorModel();
        instrutor.setId(instrutorId);
        return instrutor;
    }
}