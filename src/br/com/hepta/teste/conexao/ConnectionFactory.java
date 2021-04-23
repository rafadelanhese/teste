package br.com.hepta.teste.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection criarConexao(){
        String url = "jdbc:mysql://localhost:3306/funcionarios_prova";
        String usuario = "func_prova";
        String senha = "func_prova";
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(url, usuario, senha);
            return connection;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }            
    }
}
