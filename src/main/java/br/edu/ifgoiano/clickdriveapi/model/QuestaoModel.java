package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_questao") // acho melhor colocar esses nomes nas tabelas
public class QuestaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @Column(nullable = false, length = 100)
    private String categoria;  //

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpcaoModel> opcoes = new ArrayList<>();

    public QuestaoModel() {}

    public QuestaoModel(Long id, String enunciado, String categoria) {
        this.id = id;
        this.enunciado = enunciado;
        this.categoria = categoria;
    }

    public void addOpcao(OpcaoModel opcao) {
        opcoes.add(opcao);
        opcao.setQuestao(this);
    }

    public void removeOpcao(OpcaoModel opcao) {
        opcoes.remove(opcao);
        opcao.setQuestao(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<OpcaoModel> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<OpcaoModel> opcoes) {
        this.opcoes = opcoes;
    }
}
