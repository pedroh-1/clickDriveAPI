package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record DisponibResponseDTO(
        Long id,
        DayOfWeek diasSemana,
        LocalTime horario,
        LocalTime horaFim,
        Boolean ativo,
        Long instrutorId
) {
}