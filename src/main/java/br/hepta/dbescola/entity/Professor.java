package br.hepta.dbescola.entity;

import java.time.LocalDate;

public class Professor {

    Integer id;
    String nome;
    LocalDate dataNascimento;
    LocalDate dataId;
    Boolean estagiario;
    String materia;
    java.sql.Blob foto;
    Integer telefone;
    String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataId() {
        return dataId;
    }

    public void setDataId(LocalDate dataId) {
        this.dataId = dataId;
    }

    public Boolean isEstagiario() {
        return estagiario;
    }

    public void setEstagiario(Boolean estagiario) {
        this.estagiario = estagiario;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public java.sql.Blob getFoto() {
        return foto;
    }

    public void setFoto(java.sql.Blob foto) {
        this.foto = foto;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;

    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
