package br.hepta.dbescola.dto;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.hepta.dbescola.entity.Aluno;

public class AlunoDTO {

    private Integer matricula;
    private String nome;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataMatricula;
    private byte[] foto; // lembrar de mudar o tipo futuramente
    private String nomeFoto;
    private String tamanhoFoto;
    private String tipoFoto;
    private Integer idTurma;
    private Boolean pcd;
    private Integer telefone;
    private String email;

    public AlunoDTO() {
    }

    public Aluno toAluno() {

        Aluno aluno = new Aluno();

        aluno.setMatricula(this.matricula);
        aluno.setNome(this.nome);
        aluno.setDataNascimento(this.dataNascimento);
        aluno.setDataMatricula(this.dataMatricula);
        aluno.setFoto(this.foto);
        aluno.setNomeFoto(this.nomeFoto);
        aluno.setTipoFoto(this.tipoFoto);
        aluno.setTamanhoFoto(this.tamanhoFoto);
        aluno.setIdTurma(this.idTurma);
        aluno.setPcd(this.pcd);
        aluno.setTelefone(this.telefone);
        aluno.setEmail(this.email);

        return aluno;

    }

    public AlunoDTO(Aluno aluno) {
        this.setMatricula(aluno.getMatricula());
        this.setNome(aluno.getNome());
        this.setDataNascimento(aluno.getDataNascimento());
        this.setDataMatricula(aluno.getDataMatricula());
        this.setFoto(aluno.getFoto());
        this.setNomeFoto(aluno.getNomeFoto());
        this.setTipoFoto(aluno.getTipoFoto());
        this.setTamanhoFoto(aluno.getTamanhoFoto());
        this.setIdTurma(aluno.getIdTurma());
        this.setPcd(aluno.getPcd());
        this.setTelefone(aluno.getTelefone());
        this.setEmail(aluno.getEmail());

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataMatricula == null) ? 0 : dataMatricula.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + Arrays.hashCode(foto);
        result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((nomeFoto == null) ? 0 : nomeFoto.hashCode());
        result = prime * result + ((pcd == null) ? 0 : pcd.hashCode());
        result = prime * result + ((tamanhoFoto == null) ? 0 : tamanhoFoto.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((tipoFoto == null) ? 0 : tipoFoto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlunoDTO other = (AlunoDTO) obj;
        if (dataMatricula == null) {
            if (other.dataMatricula != null)
                return false;
        } else if (!dataMatricula.equals(other.dataMatricula))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (!Arrays.equals(foto, other.foto))
            return false;
        if (idTurma == null) {
            if (other.idTurma != null)
                return false;
        } else if (!idTurma.equals(other.idTurma))
            return false;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (nomeFoto == null) {
            if (other.nomeFoto != null)
                return false;
        } else if (!nomeFoto.equals(other.nomeFoto))
            return false;
        if (pcd == null) {
            if (other.pcd != null)
                return false;
        } else if (!pcd.equals(other.pcd))
            return false;
        if (tamanhoFoto == null) {
            if (other.tamanhoFoto != null)
                return false;
        } else if (!tamanhoFoto.equals(other.tamanhoFoto))
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        if (tipoFoto == null) {
            if (other.tipoFoto != null)
                return false;
        } else if (!tipoFoto.equals(other.tipoFoto))
            return false;
        return true;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }

    public String getTamanhoFoto() {
        return tamanhoFoto;
    }

    public void setTamanhoFoto(String tamanhoFoto) {
        this.tamanhoFoto = tamanhoFoto;
    }

    public String getTipoFoto() {
        return tipoFoto;
    }

    public void setTipoFoto(String tipoFoto) {
        this.tipoFoto = tipoFoto;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Boolean getPcd() {
        return pcd;
    }

    public void setPcd(Boolean pcd) {
        this.pcd = pcd;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AlunoDTO [matricula=" + matricula + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", dataMatricula=" + dataMatricula + ", nomeFoto="
                + nomeFoto + ", idTurma=" + idTurma + ", pcd=" + pcd + ", telefone=" + telefone + ", email=" + email + "]";
    }

}
