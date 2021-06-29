package br.hepta.dbescola.teste;


import java.sql.Connection;
import java.sql.SQLException;

import br.hepta.dbescola.connector.ConexaoBD;

public class TesteConexao {

    public static void main(String[] args) throws SQLException {
        Connection connection = new ConexaoBD().conectar();
        connection.close();
    }

}