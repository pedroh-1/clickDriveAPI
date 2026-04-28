package br.edu.ifgoiano.clickdriveapi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tb_categoriaDica")
public class CategoriaDicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    public CategoriaDicaModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
