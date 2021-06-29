package br.hepta.dbescola.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.dbescola.connector.ConexaoBD;
import br.hepta.dbescola.entity.Turma;

public class TurmaDAO {

    private Connection conexao;

    public TurmaDAO() {
        this.conexao = new ConexaoBD().conectar();
    }

    public Integer cadastrarTurma(Turma turma) {

        Integer id = 0;

        String sql = "INSERT INTO turma " // Numeros indicados para facilitar entendimento no PreparedStatement
                /* 1 */ + "(serie, "
                /* 2 */ + "nome, "
                /* 3 */ + "sala) "//
                + "VALUE (?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, turma.getSerie());
            stmt.setString(2, turma.getNome());
            stmt.setInt(3, turma.getSala());

            stmt.executeUpdate();
            try (ResultSet rst = stmt.getGeneratedKeys()) {

                while (rst.next()) {
                    id = rst.getInt(1);
                    System.out.println(id > 0 ? "Turma Cadastrada com sucesso!" //
                            : "Turma NÃO Cadastrada!");
                }

            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Turma");
            throw new RuntimeException(e);
        }

        return id;
    }

    public Boolean removerTurma(int id) {

        Boolean check = false;

        String sql = "DELETE FROM turma WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) {
                check = true;
                System.out.println("Turma Excluida com sucesso!");
            } else {
                System.out.println("Turma NÃO Excluida!");
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;
    }

    public Turma selecionarTurmaId(int id) {

        Turma turmaBuscada = new Turma();

        String sql = "SELECT * FROM turma WHERE id = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet res = ps.executeQuery();

            System.out.print("Turma Encontrada:  ");
            while (res.next()) {

                turmaBuscada.setId(res.getInt(1));
                turmaBuscada.setSerie(res.getInt(2));
                turmaBuscada.setNome(res.getString(3));
                turmaBuscada.setSala(res.getInt(4));

                System.out.println(turmaBuscada.getSerie() + turmaBuscada.getNome());
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro na busca");
            e.printStackTrace();
        }

        return turmaBuscada;
    }

    public List<Turma> buscarTurmasSerie(Integer serie) {

        List<Turma> turmas = new ArrayList<Turma>();

        String sql = "SELECT * FROM turma WHERE serie LIKE ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, (serie != null ? serie : "") + "%");

            ResultSet res = ps.executeQuery();

            System.out.print("Turmas Encontradas:  ");
            while (res.next()) {

                Turma turmaBuscada = new Turma();

                turmaBuscada.setId(res.getInt(1));
                turmaBuscada.setSerie(res.getInt(2));
                turmaBuscada.setNome(res.getString(3));
                turmaBuscada.setSala(res.getInt(4));

                System.out.print(turmaBuscada.getSerie() + turmaBuscada.getNome() + "  ");
                turmas.add(turmaBuscada);
            }
            System.out.println(" ");

            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro na lista");
            e.printStackTrace();
        }

        return turmas;
    }

    public Boolean atualizarTurma(Turma turma) {

        Boolean check = false;

        String sqlSelect = "SELECT * FROM turma WHERE id = ?";

        Turma turmaBuscada = new Turma();

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlSelect);

            ps.setInt(1, turma.getId());

            ResultSet res = ps.executeQuery();

            while (res.next()) {

                turmaBuscada.setId(res.getInt(1));
                turmaBuscada.setSerie(res.getInt(2));
                turmaBuscada.setNome(res.getString(3));
                turmaBuscada.setSala(res.getInt(4));
            }

            String sqlUp = "UPDATE turma SET "// Numeros indicados para facilitar entendimento no PreparedStatement
                    /* 1 */ + "serie = ?, "
                    /* 2 */ + "nome = ?, "
                    /* 3 */ + "sala = ? "
                    /* 4 */ + "WHERE id = ?";

            PreparedStatement stmt = conexao.prepareStatement(sqlUp);

            stmt.setInt(1, turma.getSerie() != null ? turma.getSerie() : turmaBuscada.getSerie());

            stmt.setString(2, turma.getNome() != null ? turma.getNome() : turmaBuscada.getNome());

            stmt.setInt(3, turma.getSala() != null ? turma.getSala() : turmaBuscada.getSala());

            stmt.setInt(4, turma.getId());

            int atualizado = stmt.executeUpdate();
            if (atualizado > 0) {
                System.out.println("Turma Atualizada com sucesso!");
                check = true;
            }

            ps.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

}
