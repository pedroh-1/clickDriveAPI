package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_aula")
public class AulaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private String categoriaVeiculo;

    // Relacionamentos sugeridos para integrar com seu sistema
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private InstrutorModel instrutor;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAulaModel status;

    // Getter e Setter
    public StatusAulaModel getStatus() {
        return status;
    }

    public void setStatus(StatusAulaModel status) {
        this.status = status;
    }




    public AulaModel() {
    }

    public AulaModel(Long id, LocalDate data, LocalTime horario, String categoriaVeiculo) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.categoriaVeiculo = categoriaVeiculo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getCategoriaVeiculo() {
        return categoriaVeiculo;
    }

    public void setCategoriaVeiculo(String categoriaVeiculo) {
        this.categoriaVeiculo = categoriaVeiculo;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public InstrutorModel getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(InstrutorModel instrutor) {
        this.instrutor = instrutor;
    }
}