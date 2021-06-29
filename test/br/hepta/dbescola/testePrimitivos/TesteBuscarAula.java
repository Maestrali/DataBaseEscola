package br.hepta.dbescola.testePrimitivos;

import java.util.List;

import br.hepta.dbescola.dao.AulaDAO;
import br.hepta.dbescola.entity.Aula;

public class TesteBuscarAula {

    public static void main(String[] args) {

        Aula aula = new Aula();
        aula.setId(0);
        aula.setIdProf(0);
        aula.setIdTurma(0);
        aula.setHorario("");
        aula.setDiaSemana("6");

        AulaDAO dao = new AulaDAO();
        List<Aula> lista = dao.buscarAulasProfTurma(1, 1);
        System.out.println(lista);

    }
}
