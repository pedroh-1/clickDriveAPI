package br.edu.ifgoiano.clickdriveapi.model;

public class InstrutorModel extends UsuarioModel{

    String credenciamentoDetran;
    String categoria;
    Double valorHora;
    Boolean aprovado;

    public InstrutorModel(Long id, String nome, String email, String senha, String telefone, Double valorHora, String credenciamentoDetran, String categoria, Boolean aprovado) {
        super(id, nome, email, senha, telefone);
        this.valorHora = valorHora;
        this.credenciamentoDetran = credenciamentoDetran;
        this.categoria = categoria;
        this.aprovado = aprovado;
    }
}
