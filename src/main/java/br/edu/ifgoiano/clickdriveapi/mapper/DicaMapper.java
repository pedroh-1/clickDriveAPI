package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.DicaRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.DicaResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.CategoriaDicaModel;
import br.edu.ifgoiano.clickdriveapi.model.DicaModel;
import org.springframework.stereotype.Component;

@Component
public class DicaMapper {

    public DicaModel toEntity(DicaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        DicaModel dica = new DicaModel();
        dica.setId(dto.id());
        dica.setTitulo(dto.titulo());
        dica.setConteudo(dto.conteudo());
        dica.setDataPublicacao(dto.dataPublicacao());

        if (dto.categoriaDicaId() != null) {
            CategoriaDicaModel categoria = new CategoriaDicaModel();
            categoria.setId(dto.categoriaDicaId());
            dica.setCategoriaDica(categoria);
        }

        return dica;
    }

    public DicaResponseDTO toResponseDTO(DicaModel entity) {
        if (entity == null) {
            return null;
        }

        CategoriaDicaModel categoria = entity.getCategoriaDica();
        return new DicaResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getConteudo(),
                entity.getDataPublicacao(),
                categoria != null ? categoria.getId() : null,
                categoria != null ? categoria.getNome() : null
        );
    }
}
