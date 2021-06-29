package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.entity.Aluno;
import br.hepta.dbescola.dao.AlunoDAO;

public class TesteUpAluno {
    
    public static void main(String[] args) {
        
        Aluno aluno = new Aluno();
        aluno.setMatricula(24);
        aluno.setNome("teste");
        
        AlunoDAO dao = new AlunoDAO();
        dao.atualizarAluno(aluno);
        
    }
}
