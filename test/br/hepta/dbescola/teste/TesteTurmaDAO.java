package br.hepta.dbescola.teste;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.hepta.dbescola.dao.TurmaDAO;
import br.hepta.dbescola.entity.Turma;

@TestMethodOrder(OrderAnnotation.class)

public class TesteTurmaDAO {

    private static Integer pk;

    @Test
    @Order(1)
    public void testAddTurma() {

        Turma turmaTeste = new Turma();

        turmaTeste.setNome("M");
        turmaTeste.setSala(113);
        turmaTeste.setSerie(5);
       
        TurmaDAO dao = new TurmaDAO();
        pk = dao.cadastrarTurma(turmaTeste);

        assert (pk > 0);

    }

    @Test
    @Order(2)
    public void testBuscarTurmaID() {

        Turma check = null;

        TurmaDAO dao = new TurmaDAO();

        check = dao.selecionarTurmaId(pk);

        assert (check != null);
        
    }
    
    @Test
    @Order(3)
    public void testBuscarTurmasSerie() {

        List<Turma> check = null;

        TurmaDAO dao = new TurmaDAO();

        check = dao.buscarTurmasSerie(5);

        assert (check.size()>0);
        
    }

    @Test
    @Order(4)
    public void testUpTurma() {

        Boolean check = false;

        Turma turmaTeste = new Turma();
        turmaTeste.setId(pk);
        turmaTeste.setNome("K");
        turmaTeste.setSala(111);
        TurmaDAO dao = new TurmaDAO();

        check = dao.atualizarTurma(turmaTeste);

        assert (check);

    }
    
    @Test
    @Order(5)
    public void testBuscarTurmaUPID() {

        Turma check = null;

        TurmaDAO dao = new TurmaDAO();

        check = dao.selecionarTurmaId(pk);

        assert (check != null);
    }

    @Test
    @Order(6)
    public void testDelTurma() {

        Boolean check = false;

        TurmaDAO dao = new TurmaDAO();

        check = dao.removerTurma(pk);

        assert (check);

    }

}
