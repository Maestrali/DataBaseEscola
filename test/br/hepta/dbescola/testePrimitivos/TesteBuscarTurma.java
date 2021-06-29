package br.hepta.dbescola.testePrimitivos;

import java.util.List;

import br.hepta.dbescola.dao.TurmaDAO;
import br.hepta.dbescola.entity.Turma;

public class TesteBuscarTurma {

    public static void main(String[] args) {
        
        TurmaDAO dao = new TurmaDAO();
        List<Turma> lista = dao.buscarTurmasSerie(5);
        System.out.println(lista);

    }
}
