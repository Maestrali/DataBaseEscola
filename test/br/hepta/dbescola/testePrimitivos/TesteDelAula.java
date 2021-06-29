package br.hepta.dbescola.testePrimitivos;

import br.hepta.dbescola.dao.AulaDAO;

public class TesteDelAula {

    public static void main(String[] args) {

        AulaDAO dao = new AulaDAO();

        dao.removerAula(6);
        System.out.println("Aula excuilda!");

    }

}
