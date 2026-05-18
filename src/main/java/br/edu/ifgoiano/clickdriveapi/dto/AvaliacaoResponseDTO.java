package br.edu.ifgoiano.clickdriveapi.dto;

public record AvaliacaoResponseDTO(
        Long id,
        Integer nota,
        String comentario,
        Long aulaId
) {
}
