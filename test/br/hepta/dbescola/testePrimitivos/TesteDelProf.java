package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.ProfessorDAO;

public class TesteDelProf {

    public static void main(String[] args) {
        
        ProfessorDAO dao = new ProfessorDAO();
        
        dao.removerProf(3);
        System.out.println("Foi");
        
        
    }
    
}
