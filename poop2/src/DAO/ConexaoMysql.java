package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ConexaoMysql {

    private Connection conexao;

    public boolean conectar(){
        try {
            String url = "jdbc:mysql://localhost:3306/supermercado2";
            String senha = "Evolution 123";
            String login = "root";
            this.conexao = DriverManager.getConnection(url, login, senha);
            System.out.println("Conexão estabelecida!");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean desconectar(){
        try {
            if(this.conexao != null && !this.conexao.isClosed()){
                this.conexao.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Connection getConexao() {
        return conexao;
    }
    
    public Statement criarStatement(){
        try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public PreparedStatement prepareStatement(String sql){
        try {
            return this.conexao.prepareStatement(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}
