package br.edu.ifgoiano.clickdriveapi.model;

public class UsuarioModel {

    Long id;
    String nome;
    String email;
    String senha;
    String telefone;

    public UsuarioModel(Long id, String nome, String email, String senha, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
}
