package br.edu.ifgoiano.clickdriveapi.dto.Response;

import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;

public record InstrutorResponseDTO(
        Long id,
        String perfil,
        String nome,
        String email,
        String telefone,
        String credenciamentoDetran,
        String categoria,
        Double valorHora,
        Boolean aprovado
) {
    public static InstrutorResponseDTO deEntity(InstrutorModel model) {
        return new InstrutorResponseDTO(
                model.getId(),
                "INSTRUTOR", // Força o perfil correto na resposta
                model.getNome(),
                model.getEmail(),
                model.getTelefone(),
                model.getCredenciamentoDetran(),
                model.getCategoria(),
                model.getValorHora(),
                model.getAprovado()
        );
    }
}