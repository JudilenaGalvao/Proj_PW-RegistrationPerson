package com.example.ProjetoPw;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = "localhost";
        String dbPort = "5432";
        String dbName = "ProjPW_CadastroPessoas";

        String username = "postgres";
        String password = "123";
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
