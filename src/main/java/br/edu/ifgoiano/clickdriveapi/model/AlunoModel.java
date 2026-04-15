package br.edu.ifgoiano.clickdriveapi.model;

import java.time.LocalDate;

public class AlunoModel extends UsuarioModel {

    String cpf;
    LocalDate dataNascimento;
    String cidade;

    public AlunoModel(Long id, String nome, String email, String senha, String telefone, String cpf, String cidade, LocalDate dataNascimento) {
        super(id, nome, email, senha, telefone);
        this.cpf = cpf;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
    }
}
