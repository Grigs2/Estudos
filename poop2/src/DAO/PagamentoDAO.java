package DAO;

import DTO.Pagamento;
import java.sql.*;

public class PagamentoDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public Pagamento registrarPagamento(Pagamento pagamento) throws SQLException {
        String sqlPagamento = "INSERT INTO pagamento (valor, metodoPagamento) VALUES (?, ?)";

        if (!conexao.conectar()) {
            throw new SQLException("Não foi possível estabelecer conexão com o banco de dados.");
        }

        Connection conn = null;
        PreparedStatement stmtPagamento = null;

        try {
            conn = conexao.getConexao();
            stmtPagamento = conn.prepareStatement(sqlPagamento, Statement.RETURN_GENERATED_KEYS);
            stmtPagamento.setDouble(1, pagamento.getValor());
            stmtPagamento.setString(2, pagamento.getMetodoPagamento());
            stmtPagamento.executeUpdate();

            // Obter o ID gerado do pagamento e atualizar o DTO
            ResultSet rs = stmtPagamento.getGeneratedKeys();
            if (rs.next()) {
                pagamento.setIdPagamento(rs.getLong(1));
            }
            rs.close();
            stmtPagamento.close();
        } catch (SQLException e) {
            throw new SQLException("Erro ao registrar pagamento: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return pagamento;  // Retornar o DTO atualizado com o ID gerado
    }
}
