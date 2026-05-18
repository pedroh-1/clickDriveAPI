package br.edu.ifgoiano.clickdriveapi.dto;

public record AvaliacaoRequestDTO(
        Long id,
        Integer nota,
        String comentario,
        Long aulaId
) {
}
