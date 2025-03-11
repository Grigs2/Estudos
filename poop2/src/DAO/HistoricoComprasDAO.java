package DAO;

import DTO.HistoricoCompras;
import java.sql.*;
import java.util.ArrayList;

public class HistoricoComprasDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public ArrayList<HistoricoCompras> buscarTodos() {
        String sql = "SELECT * FROM HistoricoCompras";
        ArrayList<HistoricoCompras> historicoCompras = new ArrayList<>();

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    HistoricoCompras compra = new HistoricoCompras();
                    compra.setCodigo(rs.getInt("codigo"));
                    compra.setNomeProduto(rs.getString("nome_produto"));
                    compra.setQuantidade(rs.getInt("quantidade"));
                    compra.setValorTotal(rs.getDouble("valor_total"));
                    compra.setIdProduto(rs.getLong("idProduto"));
                    compra.setIdCompra(rs.getLong("idCompra"));
                    compra.setIdPagamento(rs.getLong("idPagamento"));
                    compra.setIdFornecedor(rs.getLong("idFornecedor"));
                    compra.setIdPedido(rs.getLong("idPedido"));

                    historicoCompras.add(compra);
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar histórico de compras: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return historicoCompras;
    }
}
