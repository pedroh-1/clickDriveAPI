package br.edu.ifgoiano.clickdriveapi.dto;

import java.time.LocalDate;

public record RegistroUsuarioDTO(
    String nome,
    String email,
    String senha,
    String telefone,
    String cpf,
    LocalDate dataNascimento,
    String cidade,
    String categoriaPretendida
) {}
