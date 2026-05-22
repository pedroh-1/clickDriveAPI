package br.edu.ifgoiano.clickdriveapi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "perfil", discriminatorType = DiscriminatorType.STRING)
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "perfil", insertable = false, updatable = false)
    private String perfil;

    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public UsuarioModel(Long id, String nome, String email, String senha, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public UsuarioModel(){

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
