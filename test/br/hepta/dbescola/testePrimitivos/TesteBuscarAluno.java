package br.hepta.dbescola.testePrimitivos;

import java.util.List;

import br.hepta.dbescola.dao.AlunoDAO;
import br.hepta.dbescola.entity.Aluno;

public class TesteBuscarAluno {

    public static void main(String[] args) {
        

        AlunoDAO dao = new AlunoDAO();
        
        List<Aluno> res = dao.buscarAlunosNome(""); 
        System.out.println(res);
        
    }

}
