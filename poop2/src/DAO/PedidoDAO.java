package DAO;

import java.sql.*;

public class PedidoDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public Long gerarNovoPedido(Double valorTotal, Long idPagamento, Long idProduto) throws SQLException {
        String sql = "INSERT INTO pedido (data, valorTotal, idPagamento, idProduto) VALUES (NOW(), ?, ?, ?)";

        if (!conexao.conectar()) {
            throw new SQLException("Não foi possível estabelecer conexão com o banco de dados.");
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        Long idPedido = null;

        try {
            conn = conexao.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, valorTotal);
            stmt.setLong(2, idPagamento);
            stmt.setLong(3, idProduto);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idPedido = rs.getLong(1);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao gerar novo pedido: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            conexao.desconectar();
        }

        return idPedido;
    }
}
