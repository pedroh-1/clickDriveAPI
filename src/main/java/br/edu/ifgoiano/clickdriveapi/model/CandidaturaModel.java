package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import br.edu.ifgoiano.clickdriveapi.model.StatusCandidatura;
@Entity
@Table(name = "tb_candidatura")
public class CandidaturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // Cada candidatura pertence a um instrutor específico
    @JoinColumn(name = "instrutor_id")
    private InstrutorModel instrutor;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusCandidatura status = StatusCandidatura.PENDENTE;

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstrutorModel getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(InstrutorModel instrutor) {
        this.instrutor = instrutor;
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

