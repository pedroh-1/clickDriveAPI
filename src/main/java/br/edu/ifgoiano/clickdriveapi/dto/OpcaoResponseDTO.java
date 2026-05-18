package br.edu.ifgoiano.clickdriveapi.dto;

public record OpcaoResponseDTO(
        Long id,
        String texto,
        Boolean correta,
        Long questaoId
) {
}
