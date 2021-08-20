package br.hepta.dbescola.testePrimitivos;

import java.util.List;

import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

public class TesteBuscarProf {

    public static void main(String[] args) {
        
        ProfessorDAO dao = new ProfessorDAO();
        List<Professor> lista = dao.buscarTodosProfs();
        System.out.println(lista);
        
    }
    
}
