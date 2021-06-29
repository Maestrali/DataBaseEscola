package br.hepta.dbescola.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.dbescola.connector.ConexaoBD;
import br.hepta.dbescola.entity.Aluno;

public class AlunoDAO {

    private Connection conexao;

    public AlunoDAO() {
        this.conexao = new ConexaoBD().conectar();
    }

    private Aluno parse(ResultSet res) throws SQLException {

        Aluno aluno = new Aluno();

        aluno.setMatricula(res.getInt(1));
        aluno.setNome(res.getString(2));
        aluno.setDataNascimento(res.getDate(3).toLocalDate());
        aluno.setDataMatricula(res.getDate(4).toLocalDate());
        aluno.setFoto(res.getBlob(5));
        aluno.setIdTurma(res.getInt(6));
        aluno.setPcd(res.getBoolean(7));
        aluno.setTelefone(res.getInt(8));
        aluno.setEmail(res.getString(9));

        return aluno;
    }

    public Integer cadastrarAluno(Aluno aluno) {

        Integer mat = 0;

        String sql = "INSERT INTO aluno " // Numeros indicados para facilitar entendimento no PreparedStatement
                /* 1 */ + "(nome, "
                /* 2 */ + "dataNascimento, "
                /* 3 */ + "dataMatricula, "
                /* 4 */ + "foto, "
                /* 5 */ + "fk_idTurma, "
                /* 6 */ + "pcd, "
                /* 7 */ + "telefone, "
                /* 8 */ + "email) " //
                + "VALUE (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, Date.valueOf(aluno.getDataNascimento()));
            stmt.setDate(3, Date.valueOf(aluno.getDataMatricula()));
            stmt.setBlob(4, aluno.getFoto());
            stmt.setInt(5, aluno.getIdTurma());
            stmt.setBoolean(6, aluno.isPcd());
            stmt.setInt(7, aluno.getTelefone());
            stmt.setString(8, aluno.getEmail());

            stmt.executeUpdate();
            try (ResultSet rst = stmt.getGeneratedKeys()) {

                while (rst.next()) {
                    mat = rst.getInt(1);
                    System.out.println(mat > 0 ? "Aluno Cadastrado com sucesso!" //
                            : "Aluno NÃO Cadastrado!");
                }

            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mat;
    }

    public Aluno selecionarAlunoMatricula(int matricula) {

        
        Aluno alunoBuscado = new Aluno();

        String sql = "SELECT * FROM aluno a WHERE matricula = ?";    

        PreparedStatement pst;
        
        try {
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, matricula);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                alunoBuscado = this.parse(res);
                System.out.println("Aluno Buscado: " + alunoBuscado.getNome() + "  " + alunoBuscado.getMatricula());
            }

        } catch (SQLException e) {
           
            e.printStackTrace();
        }

        return alunoBuscado;

    }

    public List<Aluno> buscarAlunosNome(String nome) {

        List<Aluno> alunos = new ArrayList<Aluno>();

        String sql = "SELECT * FROM aluno a WHERE nome LIKE ?";

        try {
            PreparedStatement pst = conexao.prepareStatement(sql);

            pst.setString(1, nome + "%");

            ResultSet res = pst.executeQuery();

            System.out.print("Alunos Encontrados:  ");
            while (res.next()) {
                Aluno alunoBuscado = this.parse(res);
                System.out.print(alunoBuscado.getNome() + "  ");
                alunos.add(alunoBuscado);
            }
            System.out.println("");

            pst.close();

        } catch (SQLException e) {
            System.out.println("Erro na lista");
            e.printStackTrace();
        }

        return alunos;
    }

    public Boolean removerAluno(int id) {

        Boolean check = false;
        String sql = "DELETE FROM aluno WHERE matricula = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) {
                check = true;
                System.out.println("Aluno Excluido com sucesso!");
            } else {
                System.out.println("Aluno NÃO Excluido!");
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    public Boolean atualizarAluno(Aluno aluno) {

        boolean check = false;

        String sqlSelect = "SELECT * FROM aluno WHERE matricula = ?";

        Aluno alunoBuscado = new Aluno();

        try {
            PreparedStatement pst = conexao.prepareStatement(sqlSelect);

            pst.setInt(1, aluno.getMatricula());

            ResultSet res = pst.executeQuery();

            while (res.next()) {

                alunoBuscado = this.parse(res);

            }

            String sqlUp = "UPDATE aluno SET " // Numeros indicados para facilitar entendimento no PreparedStatement
                    /* 1 */ + "nome = ?, "
                    /* 2 */ + "dataNascimento = ?, "
                    /* 3 */ + "dataMatricula = ?,"
                    /* 4 */ + "foto = ?, "
                    /* 5 */ + "fk_IdTurma = ?, "
                    /* 6 */ + "pcd = ?, "
                    /* 7 */ + "telefone = ?, "
                    /* 8 */ + "email = ? "
                    /* 9 */ + "WHERE matricula = ?";

            PreparedStatement stmt = conexao.prepareStatement(sqlUp);

            stmt.setString(1, aluno.getNome() != null ? aluno.getNome() : alunoBuscado.getNome());

            stmt.setDate(2, alunoBuscado.getDataNascimento() != null ? Date.valueOf(alunoBuscado.getDataNascimento()) //
                    : Date.valueOf(aluno.getDataNascimento()));

            stmt.setDate(3, aluno.getDataMatricula() != null ? Date.valueOf(aluno.getDataMatricula()) //
                    : Date.valueOf(alunoBuscado.getDataMatricula()));

            stmt.setBlob(4, aluno.getFoto() != null ? aluno.getFoto() : alunoBuscado.getFoto());

            stmt.setInt(5, aluno.getIdTurma() != null ? aluno.getIdTurma() : alunoBuscado.getIdTurma());

            stmt.setBoolean(6, aluno.isPcd() != null ? aluno.isPcd() : alunoBuscado.isPcd());

            stmt.setInt(7, aluno.getTelefone() != null ? aluno.getTelefone() : alunoBuscado.getTelefone());

            stmt.setString(8, aluno.getEmail() != null ? aluno.getEmail() : alunoBuscado.getEmail());

            stmt.setInt(9, aluno.getMatricula());

            int adicionado = stmt.executeUpdate();
            if (adicionado > 0) {
                System.out.println("Aluno Atualizado com sucesso!");
                check = true;
            }

            pst.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;

    }

}
