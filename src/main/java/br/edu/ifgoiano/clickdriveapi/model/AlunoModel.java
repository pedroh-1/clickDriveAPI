package br.edu.ifgoiano.clickdriveapi.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("ALUNO")
public class AlunoModel extends UsuarioModel {

    private String cpf;
    private LocalDate dataNascimento;
    private String cidade;
    private String CategoriaPretendida;


    public AlunoModel(Long id, String nome, String email, String senha, String telefone, String cpf, String categoriaPretendida, String cidade, LocalDate dataNascimento) {
        super(id, nome, email, senha, telefone);
        this.cpf = cpf;
        CategoriaPretendida = categoriaPretendida;
        this.cidade = cidade;
        this.dataNascimento = dataNascimento;
    }

    // Construtor padrão (sem argumentos) - OBRIGATÓRIO
    public AlunoModel() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCategoriaPretendida() {
        return CategoriaPretendida;
    }

    public void setCategoriaPretendida(String categoriaPretendida) {
        CategoriaPretendida = categoriaPretendida;
    }
}
