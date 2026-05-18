package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDateTime;

public record DicaResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        LocalDateTime dataPublicacao,
        Long categoriaDicaId,
        String categoriaDicaNome
) {
}
