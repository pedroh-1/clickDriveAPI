package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDateTime;

public record DicaRequestDTO(
        Long id,
        String titulo,
        String conteudo,
        LocalDateTime dataPublicacao,
        Long categoriaDicaId
) {
}
