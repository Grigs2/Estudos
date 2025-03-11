package DAO;

import DTO.ItensCompra;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ItensCompraDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public ItensCompraDAO() {
        String sql = "CREATE TABLE IF NOT EXISTS ItensCompra ("
                + "idItem INT AUTO_INCREMENT PRIMARY KEY,"
                + "quantidade INT,"
                + "idProduto BIGINT,"
                + "idCompra BIGINT,"
                + "FOREIGN KEY (idProduto) REFERENCES produto(idProduto),"
                + "FOREIGN KEY (idCompra) REFERENCES compra(codigo)"
                + ");";

        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela ItensCompra Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void inserirItemCompra(ItensCompra item) {
        String sql = "INSERT INTO ItensCompra (quantidade, idProduto, idCompra) VALUES (?, ?, ?)";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, item.getQuantidade());
                stmt.setLong(2, item.getIdProduto());
                stmt.setLong(3, item.getIdCompra());
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }
}
