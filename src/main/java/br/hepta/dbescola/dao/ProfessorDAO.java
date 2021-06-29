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
import br.hepta.dbescola.entity.Professor;

public class ProfessorDAO {

    private Connection conexao;

    public ProfessorDAO() {
        this.conexao = new ConexaoBD().conectar();
    }

    public Integer cadastrarProf(Professor prof) {

        Integer id = 0;

        String sql = "INSERT INTO professor" // Numeros indicados para facilitar entendimento no PreparedStatement
                /* 1 */ + "(nome, "//
                /* 2 */ + "email, "//
                /* 3 */ + "telefone, "//
                /* 4 */ + "dataInscricao, "//
                /* 5 */ + "dataNascimento, "//
                /* 6 */ + "foto, "//
                /* 7 */ + "area, "//
                /* 8 */ + "estagiario) "//
                + "VALUE (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, prof.getNome());
            stmt.setString(2, prof.getEmail());
            stmt.setInt(3, prof.getTelefone());
            stmt.setDate(4, Date.valueOf(prof.getDataId()));
            stmt.setDate(5, Date.valueOf(prof.getDataNascimento()));
            stmt.setBlob(6, prof.getFoto());
            stmt.setString(7, prof.getMateria());
            stmt.setBoolean(8, prof.isEstagiario());

            stmt.executeUpdate();
            try (ResultSet res = stmt.getGeneratedKeys()) {

                while (res.next()) {
                    id = res.getInt(1);
                    System.out.println(id > 0 ? "Professor Cadastrado com sucesso!" //
                            : "Professor NÃO Cadastrado!");
                }

            }
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;

    }

    public Boolean removerProf(int id) {

        Boolean check = false;

        String sql = "DELETE FROM professor WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) {
                check = true;
                System.out.println("Professor Excluido com sucesso!");
            } else {
                System.out.println("Professor NÃO Excluido!");
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;

    }

    public List<Professor> buscarProfsNome(String nome) {

        List<Professor> profs = new ArrayList<Professor>();

        String sql = "SELECT * FROM professor WHERE nome LIKE ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setString(1, nome + "%");

            ResultSet res = ps.executeQuery();

            System.out.print("Professores Encontrados:  ");
            while (res.next()) {

                Professor profBuscado = new Professor();

                profBuscado.setId(res.getInt(1));
                profBuscado.setNome(res.getString(2));
                profBuscado.setDataNascimento(res.getDate(3).toLocalDate());
                profBuscado.setDataId(res.getDate(4).toLocalDate());
                profBuscado.setFoto(res.getBlob(5));
                profBuscado.setMateria(res.getString(6));
                profBuscado.setEstagiario(res.getBoolean(7));
                profBuscado.setTelefone(res.getInt(8));
                profBuscado.setEmail(res.getString(9));

                System.out.print(profBuscado.getNome() + "  ");
                profs.add(profBuscado);
            }
            System.out.println("");
            
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro na lista");
            e.printStackTrace();
        }

        return profs;
    }

    public Professor selecionarProfId(int id) {

        Professor profBuscado = new Professor();

        String sql = "SELECT * FROM professor WHERE id = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);          

            ResultSet res = ps.executeQuery();

            System.out.print("Professor Encontrado:  ");
            while (res.next()) {

                profBuscado.setId(res.getInt(1));
                profBuscado.setNome(res.getString(2));
                profBuscado.setDataNascimento(res.getDate(3).toLocalDate());
                profBuscado.setDataId(res.getDate(4).toLocalDate());
                profBuscado.setFoto(res.getBlob(5));
                profBuscado.setMateria(res.getString(6));
                profBuscado.setEstagiario(res.getBoolean(7));
                profBuscado.setTelefone(res.getInt(8));
                profBuscado.setEmail(res.getString(9));

                System.out.print(profBuscado.getNome() + "  ");
                
            }
            System.out.println("");
            
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro ao encontrar professor");
            e.printStackTrace();
        }

        return profBuscado;
    }

    public Boolean atualizarProf(Professor prof) {

        Boolean check = false;

        String sqlSelect = "SELECT * FROM professor WHERE id = ?";

        Professor profBuscado = new Professor();

        try {
            PreparedStatement pst = conexao.prepareStatement(sqlSelect);

            pst.setInt(1, prof.getId());

            ResultSet res = pst.executeQuery();

            while (res.next()) {

                profBuscado.setId(res.getInt(1));
                profBuscado.setNome(res.getString(2));
                profBuscado.setDataNascimento(res.getDate(3).toLocalDate());
                profBuscado.setDataId(res.getDate(4).toLocalDate());
                profBuscado.setFoto(res.getBlob(5));
                profBuscado.setMateria(res.getString(6));
                profBuscado.setEstagiario(res.getBoolean(7));
                profBuscado.setTelefone(res.getInt(8));
                profBuscado.setEmail(res.getString(9));
            }

            String sqlUp = "UPDATE professor SET " // Numeros indicados para facilitar entendimento no PreparedStatement
                    /* 1 */ + "nome = ?, "
                    /* 2 */ + "dataNascimento = ?, "
                    /* 3 */ + "dataInscricao = ?,"
                    /* 4 */ + "foto = ?, "
                    /* 5 */ + "area = ?, "
                    /* 6 */ + "estagiario = ?, "
                    /* 7 */ + "telefone = ?, "
                    /* 8 */ + "email = ? "
                    /* 9 */ + "WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sqlUp);

            stmt.setString(1, prof.getNome() != null ? prof.getNome() : profBuscado.getNome());

            stmt.setDate(2, prof.getDataNascimento() != null ? Date.valueOf(prof.getDataNascimento()) //
                    : Date.valueOf(profBuscado.getDataNascimento()));

            stmt.setDate(3, prof.getDataId() != null ? Date.valueOf(prof.getDataId()) //
                    : Date.valueOf(profBuscado.getDataId()));

            stmt.setBlob(4, prof.getFoto() != null ? prof.getFoto() : profBuscado.getFoto());

            stmt.setString(5, prof.getMateria() != null ? prof.getMateria() : profBuscado.getMateria());

            stmt.setBoolean(6, prof.isEstagiario() != null ? prof.isEstagiario() : profBuscado.isEstagiario());

            stmt.setInt(7, prof.getTelefone() != null ? prof.getTelefone() : profBuscado.getTelefone());

            stmt.setString(8, prof.getEmail() != null ? prof.getEmail() : profBuscado.getEmail());

            stmt.setInt(9, prof.getId());

            int adicionado = stmt.executeUpdate();
            if (adicionado > 0) {
                System.out.println("Professor Atualizado com sucesso!");
                check = true;
            }

            pst.close();
            stmt.close();

        } catch (

        SQLException e) {
            throw new RuntimeException(e);
        }

        return check;

    }

}
