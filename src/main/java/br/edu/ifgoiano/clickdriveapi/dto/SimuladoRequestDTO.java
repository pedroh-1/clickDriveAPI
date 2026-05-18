package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SimuladoRequestDTO(
        Long id,
        LocalDateTime dataRealizacao,
        Integer pontuacao,
        Boolean aprovado,
        Long alunoId,
        List<Long> questaoIds
) {
}
