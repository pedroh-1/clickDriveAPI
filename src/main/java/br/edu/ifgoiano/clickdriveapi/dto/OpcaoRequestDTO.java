package br.edu.ifgoiano.clickdriveapi.dto;

public record OpcaoRequestDTO(
        Long id,
        String texto,
        Boolean correta,
        Long questaoId
) {
}
