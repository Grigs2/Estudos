package DAO;

import DTO.Compra;
import java.sql.*;
import java.util.ArrayList;

public class CompraDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    // Método para inserir uma nova compra no banco de dados
    public int inserir(Compra compra) throws SQLException {
        String sqlCompra = "INSERT INTO compra(idProduto, quantidade, idFornecedor, data) VALUES (?,?,?, NOW())";
        String sqlAtualizarEstoque = "UPDATE produto SET quantidade = quantidade + ? WHERE idProduto = ?";
        if (conexao.conectar()) {
            try {
                conexao.getConexao().setAutoCommit(false); // Iniciar transação

                PreparedStatement stmtCompra = conexao.prepareStatement(sqlCompra);
                stmtCompra.setLong(1, compra.getIdProduto());
                stmtCompra.setInt(2, compra.getQuantidade());
                stmtCompra.setLong(3, compra.getIdFornecedor());
                stmtCompra.executeUpdate();

                PreparedStatement stmtAtualizarEstoque = conexao.prepareStatement(sqlAtualizarEstoque);
                stmtAtualizarEstoque.setInt(1, compra.getQuantidade());
                stmtAtualizarEstoque.setLong(2, compra.getIdProduto());
                stmtAtualizarEstoque.executeUpdate();

                conexao.getConexao().commit(); // Confirmar transação
                stmtCompra.close();
                stmtAtualizarEstoque.close();
                conexao.desconectar();
                return 1;
            } catch (SQLException e) {
                conexao.getConexao().rollback(); // Reverter transação em caso de erro
                throw e;
            }
        }
        return 0;
    }

    // Método para buscar todas as compras do banco de dados
    public ArrayList<Compra> buscarTodas() {
        String sql = "SELECT * FROM compra";
        ArrayList<Compra> compras = new ArrayList<>();

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Compra compra = new Compra();
                    compra.setIdProduto(rs.getLong("idProduto"));
                    compra.setQuantidade(rs.getInt("quantidade"));
                    compra.setIdFornecedor(rs.getLong("idFornecedor"));
                    compra.setData(rs.getTimestamp("data")); // Recupera a data da compra
                    compras.add(compra);
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar compras: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return compras;
    }
}
