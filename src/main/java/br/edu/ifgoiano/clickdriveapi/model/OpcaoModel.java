package br.edu.ifgoiano.clickdriveapi.model;
import br.edu.ifgoiano.clickdriveapi.model.QuestaoModel;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_opcao")
public class OpcaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    @Column(nullable = false)
    private Boolean correta;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questao_id", nullable = false)
    private QuestaoModel questao;

    public OpcaoModel() {}

    public OpcaoModel(Long id, String texto, Boolean correta) {
        this.id = id;
        this.texto = texto;
        this.correta = correta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }

    public QuestaoModel getQuestao() {
        return questao;
    }

    public void setQuestao(QuestaoModel questao) {
        this.questao = questao;
    }
}
