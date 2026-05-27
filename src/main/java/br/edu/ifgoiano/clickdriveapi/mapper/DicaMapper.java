package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.DicaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DicaResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.CategoriaDicaModel;
import br.edu.ifgoiano.clickdriveapi.model.DicaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DicaMapper {

    @Mapping(target = "categoriaDica", source = "categoriaDicaId")
    DicaModel toEntity(DicaRequestDTO dto);

    @Mapping(target = "categoriaDicaId", source = "categoriaDica.id")
    @Mapping(target = "categoriaDicaNome", source = "categoriaDica.nome")
    DicaResponseDTO toResponseDTO(DicaModel entity);

    List<DicaResponseDTO> toResponseDTOList(List<DicaModel> entities);

    default CategoriaDicaModel mapCategoria(Long categoriaDicaId) {
        if (categoriaDicaId == null) {
            return null;
        }
        CategoriaDicaModel categoria = new CategoriaDicaModel();
        categoria.setId(categoriaDicaId);
        return categoria;
    }
}
