package br.edu.ifgoiano.clickdriveapi.dto;

import java.util.List;

public record QuestaoResponseDTO(
        Long id,
        String enunciado,
        String categoria,
        List<OpcaoResponseDTO> opcoes
) {
}
