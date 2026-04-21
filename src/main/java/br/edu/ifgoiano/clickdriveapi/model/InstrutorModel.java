package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.Entity;

@Entity
public class InstrutorModel extends UsuarioModel {

    private String credenciamentoDetran;
    private String categoria;
    private Double valorHora;
    private Boolean aprovado;

    public InstrutorModel(Long id, String nome, String email, String senha, String telefone, String credenciamentoDetran, Double valorHora, String categoria, Boolean aprovado) {
        super(id, nome, email, senha, telefone);
        this.credenciamentoDetran = credenciamentoDetran;
        this.valorHora = valorHora;
        this.categoria = categoria;
        this.aprovado = aprovado;
    }

    public InstrutorModel() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCredenciamentoDetran() {
        return credenciamentoDetran;
    }

    public void setCredenciamentoDetran(String credenciamentoDetran) {
        this.credenciamentoDetran = credenciamentoDetran;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }
}