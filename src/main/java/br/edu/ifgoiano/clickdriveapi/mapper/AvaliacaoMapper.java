package br.edu.ifgoiano.clickdriveapi.mapper;

import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoRequestDTO;
import br.edu.ifgoiano.clickdriveapi.dto.AvaliacaoResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.AvaliacaoModel;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    public AvaliacaoModel toEntity(AvaliacaoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        AvaliacaoModel avaliacao = new AvaliacaoModel();
        avaliacao.setId(dto.id());
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());
        avaliacao.setAulaId(dto.aulaId());
        return avaliacao;
    }

    public AvaliacaoResponseDTO toResponseDTO(AvaliacaoModel entity) {
        if (entity == null) {
            return null;
        }

        return new AvaliacaoResponseDTO(
                entity.getId(),
                entity.getNota(),
                entity.getComentario(),
                entity.getAulaId()
        );
    }
}
