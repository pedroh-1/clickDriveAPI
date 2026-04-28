package br.edu.ifgoiano.clickdriveapi.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_dica")
public class DicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;


    private LocalDateTime dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaDicaModel categoriaDica;

    public DicaModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public CategoriaDicaModel getCategoriaDica() {
        return categoriaDica;
    }

    public void setCategoriaDica(CategoriaDicaModel categoriaDica) {
        this.categoriaDica = categoriaDica;
    }
}
