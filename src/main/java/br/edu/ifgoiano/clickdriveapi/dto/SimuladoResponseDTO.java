package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SimuladoResponseDTO(
        Long id,
        LocalDateTime dataRealizacao,
        Integer pontuacao,
        Boolean aprovado,
        Long alunoId,
        List<QuestaoResponseDTO> questoes
) {
}
