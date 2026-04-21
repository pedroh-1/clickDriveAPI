package br.edu.ifgoiano.clickdriveapi.model;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "tb_disponibilidade")
public class DisponibModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek diasSemana;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private LocalTime horaFim;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private InstrutorModel instrutor;

    public DisponibModel() {
    }

    public DisponibModel(Long id, DayOfWeek diasSemana, LocalTime horario, LocalTime horaFim, Boolean ativo, InstrutorModel instrutor) {
        this.id = id;
        this.diasSemana = diasSemana;
        this.horario = horario;
        this.horaFim = horaFim;
        this.ativo = ativo;
        this.instrutor = instrutor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(DayOfWeek diasSemana) {
        this.diasSemana = diasSemana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public InstrutorModel getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(InstrutorModel instrutor) {
        this.instrutor = instrutor;
    }
}