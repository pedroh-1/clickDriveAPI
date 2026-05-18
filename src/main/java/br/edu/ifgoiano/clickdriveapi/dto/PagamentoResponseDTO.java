package br.edu.ifgoiano.clickdriveapi.dto;

import br.edu.ifgoiano.clickdriveapi.model.StatusPagamento;

import java.time.LocalDateTime;

public record PagamentoResponseDTO(
        Long id,
        Double valor,
        StatusPagamento status,
        LocalDateTime dataPagamento,
        Long aulaId
) {
}
