package br.edu.ifgoiano.clickdriveapi.dto;

import java.util.List;

public record QuestaoRequestDTO(
        Long id,
        String enunciado,
        String categoria,
        List<OpcaoRequestDTO> opcoes
) {
}
