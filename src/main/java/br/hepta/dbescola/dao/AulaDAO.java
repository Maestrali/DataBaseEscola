package br.hepta.dbescola.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.hepta.dbescola.connector.ConexaoBD;
import br.hepta.dbescola.entity.Aula;

public class AulaDAO {

    private Connection conexao;

    public AulaDAO() {
        this.conexao = new ConexaoBD().conectar();
    }

    public Integer cadastrarAula(Aula aula) {

        Integer id = 0;

        String sql = "INSERT INTO aula " // Numeros indicados para facilitar entendimento no PreparedStatement
                /* 1 */ + "(fk_idProf, "
                /* 2 */ + "fK_idTurma, "
                /* 3 */ + "horario, "
                /* 4 */ + "diaSemana)" //
                + "VALUE (?,?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, aula.getIdProf());
            stmt.setInt(2, aula.getIdTurma());
            stmt.setString(3, aula.getHorario());
            stmt.setString(4, aula.getDiaSemana());

            stmt.executeUpdate();
            try (ResultSet rst = stmt.getGeneratedKeys()) {

                while (rst.next()) {
                    id = rst.getInt(1);
                    System.out.println(id > 0 ? "Aula Cadastrada com sucesso!" //
                            : "Aula NÃO Cadastrada!");
                }

            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println("Erro ao Cadastrar Aula");
            throw new RuntimeException(e);
        }

        return id;
    }

    public Boolean removerAula(int id) {

        Boolean check = false;

        String sql = "DELETE FROM aula WHERE id = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            if (stmt.executeUpdate() > 0) {
                check = true;
                System.out.println("Aula Excluida com sucesso!");
            } else {
                System.out.println("Aula NÃO Excluida!");
            }

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;

    }

    public Aula selecionarAulaId(int id) {

        Aula aulaBuscada = new Aula();

        String sql = "SELECT "//
                /* 1 */ + "a.id, "
                /* 2 */ + "a.fk_idProf, "
                /* 3 */ + "a.fk_idTurma, "
                /* 4 */ + "a.horario, "
                /* 5 */ + "a.diaSemana, "
                /* 6 */ + "p.area, "
                /* 7 */ + "t.serie, "
                /* 8 */ + "t.nome "//
                + "FROM aula a INNER JOIN professor p ON a.fk_idProf = p.id "//
                + "INNER JOIN turma t ON a.fk_idTurma = t.id WHERE a.id = ?";

        try {
            PreparedStatement st = conexao.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet res = st.executeQuery();

            System.out.print("Aula Encontrada:  ");
            while (res.next()) {

                aulaBuscada.setId(res.getInt(1));
                aulaBuscada.setIdProf(res.getInt(2));
                aulaBuscada.setIdTurma(res.getInt(3));
                aulaBuscada.setHorario(res.getString(4));
                aulaBuscada.setDiaSemana(res.getString(5));

                System.out.println(res.getString(6) + " na Turma " + res.getInt(7) + res.getString(8) //
                        + ", " + res.getString(5) + " no " + res.getString(4) + " horario");
            }

            st.close();

        } catch (SQLException e) {
            System.out.println("Erro na Query");
            e.printStackTrace();
        }

        return aulaBuscada;

    }

    public List<Aula> buscarAulasProfTurma(int idP, int idT) {

        List<Aula> aulas = new ArrayList<Aula>();

        String sql = "SELECT " //
                /* 1 */ + "a.id, "
                /* 2 */ + "a.fk_idProf, "// numeros de referencia
                /* 3 */ + "a.fk_idTurma, " // para informação ser
                /* 4 */ + "a.horario, " // armazenada em aulaBuscada
                /* 5 */ + "a.diaSemana,"// pelo getXXX
                + " "//
                /* 6 */ + "p.area, " // numeros de referencia
                /* 7 */ + "t.serie, " // exclusivamente para imprimir
                /* 8 */ + "t.nome " // o resultado da busca
                + "FROM aula a INNER JOIN professor p ON a.fk_idProf = p.id " //
                + "INNER JOIN turma t ON a.fk_idTurma = t.id WHERE "
                /* 1 */ + "fk_idProf = ? OR "
                /* 2 */ + "fk_idTurma = ?"; // numeros para o PreparedStatement

        String sql2 = "SELECT * FROM aula";

        try {

            PreparedStatement st = null;
            Boolean buscarTudo = (idT == 0 && idP == 0);

            if (buscarTudo) {
                st = conexao.prepareStatement(sql2);
            } else {
                st = conexao.prepareStatement(sql);
                st.setInt(1, idP);
                st.setInt(2, idT);
            }

            ResultSet res = st.executeQuery();

            System.out.print("Aulas Encontradas");
            if (!(idT == 0 && idP == 0)) {
                System.out.println(":");
            } else {
                System.out.println("");
            }
            while (res.next()) {

                Aula aulaBuscada = new Aula();

                aulaBuscada.setId(res.getInt(1));
                aulaBuscada.setIdProf(res.getInt(2));
                aulaBuscada.setIdTurma(res.getInt(3));
                aulaBuscada.setHorario(res.getString(4));
                aulaBuscada.setDiaSemana(res.getString(5));
                aulas.add(aulaBuscada);

                if (!(idT == 0 && idP == 0)) {
                    System.out.println("  " + res.getString(6) + " na Turma " + res.getInt(7) + res.getString(8) //
                            + ", " + res.getString(5) + " no " + res.getString(4) + " horario");
                }

            }

            st.close();

        } catch (SQLException e) {
            System.out.println("Erro na lista");
            e.printStackTrace();
        }

        return aulas;

    }

    public Boolean atualizarAula(Aula aula) {

        Boolean check = false;

        String sqlSelect = "SELECT * FROM aula WHERE id = ?";

        Aula aulaBuscada = new Aula();

        try {
            PreparedStatement ps = conexao.prepareStatement(sqlSelect);

            ps.setInt(1, aula.getId());

            ResultSet res = ps.executeQuery();

            while (res.next()) {

                aulaBuscada.setId(res.getInt(1));
                aulaBuscada.setIdProf(res.getInt(2));
                aulaBuscada.setIdTurma(res.getInt(3));
                aulaBuscada.setHorario(res.getString(4));
                aulaBuscada.setDiaSemana(res.getString(5));

            }

            String sqlUp = "UPDATE aula SET "//
                    /* 1 */ + "fk_idProf = ?, "
                    /* 2 */ + "fk_idTurma = ?, "
                    /* 3 */ + "horario = ?, "
                    /* 4 */ + "diaSemana = ? "
                    /* 5 */ + "WHERE id = ?";

            PreparedStatement pst = conexao.prepareStatement(sqlUp);

            pst.setInt(1, aula.getIdProf() != null ? aula.getIdProf() : aulaBuscada.getIdProf());

            pst.setInt(2, aula.getIdTurma() != null ? aula.getIdTurma() : aulaBuscada.getIdTurma());

            pst.setString(3, aula.getHorario() != null ? aula.getHorario() : aulaBuscada.getHorario());

            pst.setString(4, aula.getDiaSemana() != null ? aula.getDiaSemana() : aulaBuscada.getDiaSemana());

            pst.setInt(5, aula.getId());

            int atualizada = pst.executeUpdate();
            if (atualizada > 0) {
                System.out.println("Aula Atualizada com sucesso!");
                check = true;
            }

            ps.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;

    }

}
