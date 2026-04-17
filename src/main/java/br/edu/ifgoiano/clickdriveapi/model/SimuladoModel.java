package br.edu.ifgoiano.clickdriveapi.model;

import java.time.LocalDateTime;

public class SimuladoModel {

    private Long id;
    private LocalDateTime dataRealisacao;
    private Integer pontuacao;
    private Boolean aprovado;

    public SimuladoModel() {}

    public SimuladoModel(Long id, LocalDateTime dataRealisacao, Integer pontuacao,  Boolean aprovado) {
        this.id = id;
        this.dataRealisacao = dataRealisacao;
        this.pontuacao = pontuacao;
        this.aprovado = aprovado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRealisacao() {
        return dataRealisacao;
    }

    public void setDataRealisacao(LocalDateTime dataRealisacao) {
        this.dataRealisacao = dataRealisacao;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }
}
