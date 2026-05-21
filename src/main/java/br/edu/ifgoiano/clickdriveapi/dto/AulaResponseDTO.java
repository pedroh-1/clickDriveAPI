package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AulaResponseDTO(
        Long id,
        LocalDate data,
        LocalTime horario,
        String categoriaVeiculo,
        Long alunoId,
        Long instrutorId,
        String status
) {
}