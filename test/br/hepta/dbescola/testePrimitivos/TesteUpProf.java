package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.ProfessorDAO;
import br.hepta.dbescola.entity.Professor;

public class TesteUpProf {

    public static void main(String[] args) {
        
        Professor prof = new Professor();
        prof.setId(6);
        prof.setNome("teste3");
        prof.setEmail("teste3@email.com");
        prof.setMateria("Matematica");
        
        ProfessorDAO dao = new ProfessorDAO();
        dao.atualizarProf(prof);
        System.out.println(prof);
    }
    
}
