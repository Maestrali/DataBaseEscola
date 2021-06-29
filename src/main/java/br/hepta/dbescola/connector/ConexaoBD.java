package br.hepta.dbescola.connector;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoBD {

    private String username = "root";

    private String password = "maestrali03";

    private String mysql = "jdbc:mysql://localhost:3306/dbescola";

    private Connection conexaoMySQL;

    public Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexaoMySQL = DriverManager.getConnection(mysql, username, password);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conexaoMySQL;

    }
}
