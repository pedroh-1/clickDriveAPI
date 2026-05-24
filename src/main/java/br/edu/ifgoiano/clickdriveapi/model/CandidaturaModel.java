package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_candidaturas")
public class CandidaturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // Cada candidatura pertence a um usuário base do sistema
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusCandidatura status = StatusCandidatura.PENDENTE;

    private String credenciamentoPretendido;//
    private String urlDocumentoPdf;// link da documentação

    public String getCredenciamentoPretendido() {
        return credenciamentoPretendido;
    }

    public void setCredenciamentoPretendido(String credenciamentoPretendido) {
        this.credenciamentoPretendido = credenciamentoPretendido;
    }

    public String getUrlDocumentoPdf() {
        return urlDocumentoPdf;
    }

    public void setUrlDocumentoPdf(String urlDocumentoPdf) {
        this.urlDocumentoPdf = urlDocumentoPdf;
    }

    //get and sets


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }
}