package DAO;

import DTO.HistoricoVendas;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoricoVendasDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public HistoricoVendasDAO() {
        String sql = "CREATE TABLE IF NOT EXISTS HistoricoVendas ("
                + "codigo INT AUTO_INCREMENT PRIMARY KEY,"
                + "nome_produto VARCHAR(255),"
                + "quantidade INT,"
                + "valor_total DECIMAL(10, 2),"
                + "idProduto INT,"
                + "idVenda BIGINT,"
                + "idPagamento BIGINT,"
                + "idCliente BIGINT,"
                + "idPedido BIGINT,"
                + "FOREIGN KEY (idProduto) REFERENCES produto(idProduto),"
                + "FOREIGN KEY (idVenda) REFERENCES venda(codigo),"
                + "FOREIGN KEY (idPagamento) REFERENCES pagamento(codigo),"
                + "FOREIGN KEY (idCliente) REFERENCES cliente(codigo),"
                + "FOREIGN KEY (idPedido) REFERENCES pedido(codigo)"
                + ");";

        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela HistoricoVendas Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void inserirHistoricoVendas(HistoricoVendas historico) {
        String sql = "INSERT INTO HistoricoVendas (nome_produto, quantidade, valor_total, idProduto, idVenda, idPagamento, idCliente, idPedido) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, historico.getNomeProduto());
                stmt.setInt(2, historico.getQuantidade());
                stmt.setBigDecimal(3, historico.getValorTotal());
                stmt.setLong(4, historico.getIdProduto());
                stmt.setLong(5, historico.getIdVenda());
                stmt.setLong(6, historico.getIdPagamento());
                stmt.setLong(7, historico.getIdCliente());
                stmt.setLong(8, historico.getIdPedido());
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
