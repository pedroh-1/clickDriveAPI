package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_simulado")
public class SimuladoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime dataRealizacao;
    private Integer pontuacao;
    private Boolean aprovado;

    @Column(name = "aluno_id")
    private Long alunoId;

    @ManyToMany
    @JoinTable(
            name = "tb_simulado_questao", // Nome da tabela que vai nascer no banco
            joinColumns = @JoinColumn(name = "simulado_id"),
            inverseJoinColumns = @JoinColumn(name = "questao_id")
    )
    private List<QuestaoModel> questoes = new ArrayList<>();




    public SimuladoModel() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDateTime dataRealisacao) {
        this.dataRealizacao = dataRealisacao;
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

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public List<QuestaoModel> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoModel> questoes) {
        this.questoes = questoes;
    }
}
