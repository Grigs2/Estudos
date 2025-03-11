package DAO;

import DTO.ItensVenda;
//mport java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ItensVendaDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public ItensVendaDAO() {
        String sql = "CREATE TABLE IF NOT EXISTS ItensVenda ("
                + "idItem INT AUTO_INCREMENT PRIMARY KEY,"
                + "quantidade INT,"
                + "idProduto BIGINT,"
                + "idVenda BIGINT,"
                + "FOREIGN KEY (idProduto) REFERENCES produto(idProduto),"
                + "FOREIGN KEY (idVenda) REFERENCES venda(codigo)"
                + ");";

        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela ItensVenda Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void inserirItemVenda(ItensVenda item) {
        String sql = "INSERT INTO ItensVenda (quantidade, idProduto, idVenda) VALUES (?, ?, ?)";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, item.getQuantidade());
                stmt.setLong(2, item.getIdProduto());
                stmt.setLong(3, item.getIdVenda());
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
