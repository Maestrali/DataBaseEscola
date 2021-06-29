package br.hepta.dbescola.testePrimitivos;

import org.junit.jupiter.api.Test;

import br.hepta.dbescola.dao.AlunoDAO;

public class TesteDelAluno {

    @Test
    public void removerAluno() {

        AlunoDAO dao = new AlunoDAO();
        dao.removerAluno(25);

    }
}
