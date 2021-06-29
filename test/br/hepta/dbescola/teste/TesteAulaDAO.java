package br.hepta.dbescola.teste;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.hepta.dbescola.dao.AulaDAO;
import br.hepta.dbescola.entity.Aula;

@TestMethodOrder(OrderAnnotation.class)

public class TesteAulaDAO {

    private static Integer pk;

    @Test
    @Order(1)
    public void testAddAula() {

        Aula aulaTeste = new Aula();

        aulaTeste.setHorario("2º");
        aulaTeste.setDiaSemana("2ª");
        aulaTeste.setIdProf(4);
        aulaTeste.setIdTurma(1);
        
       
        AulaDAO dao = new AulaDAO();
        pk = dao.cadastrarAula(aulaTeste);

        assert (pk > 0);

    }

    @Test
    @Order(2)
    public void testBuscarAulaID() {

        Aula check = null;

        AulaDAO dao = new AulaDAO();

        check = dao.selecionarAulaId(pk);

        assert (check != null);
        
    }
    
    @Test
    @Order(3)
    public void testBuscarAulaProf() {

        List<Aula> check = null;

        AulaDAO dao = new AulaDAO();

        check = dao.buscarAulasProfTurma(4, 0);

        assert (check.size()>0);
        
    }
    
    @Test
    @Order(4)
    public void testBuscarAulaTurma() {

        List<Aula> check = null;

        AulaDAO dao = new AulaDAO();

        check = dao.buscarAulasProfTurma(0, 1);

        assert (check.size()>0);
        
    }

    @Test
    @Order(5)
    public void testUpAula() {

        Boolean check = false;

        Aula aulaTeste = new Aula();
        aulaTeste.setId(pk);
        aulaTeste.setIdProf(1);
        AulaDAO dao = new AulaDAO();

        check = dao.atualizarAula(aulaTeste);

        assert (check);

    }
    
    @Test
    @Order(6)
    public void testBuscarAulaIDUP() {

        Aula check = null;

        AulaDAO dao = new AulaDAO();

        check = dao.selecionarAulaId(pk);

        assert (check != null);
    }

    @Test
    @Order(7)
    public void testDelAula() {

        Boolean check = false;

        AulaDAO dao = new AulaDAO();

        check = dao.removerAula(pk);

        assert (check);

    }

}
