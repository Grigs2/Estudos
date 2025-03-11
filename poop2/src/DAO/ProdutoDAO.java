package DAO;

import DTO.Produto;
import DTO.Fornecedor;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public ProdutoDAO() {
        String sql = "CREATE TABLE IF NOT EXISTS produto("
                + "idProduto BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(50),"
                + "precoVenda DOUBLE,"
                + "precoCompra DOUBLE,"
                + "categoria VARCHAR(30),"
                + "quantidade INT,"
                + "idFornecedor BIGINT,"
                + "FOREIGN KEY (idFornecedor) REFERENCES fornecedor(codigo));";

        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela Produto Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public int inserir(Produto produto) {
        String sql = "INSERT INTO produto(nome, precoVenda, precoCompra, categoria, idFornecedor, quantidade) VALUES (?,?,?,?,?,?)";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPrecoVenda());
                stmt.setDouble(3, produto.getPrecoCompra());
                stmt.setString(4, produto.getCategoria());
                stmt.setLong(5, produto.getFornecedor().getCodigo());
                stmt.setInt(6, produto.getQuantidade());
                int resultado = stmt.executeUpdate();
                stmt.close();
                return resultado;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        return 0;
    }

    public ArrayList<Produto> buscarTodos() {
        String sql = "SELECT p.idProduto, p.nome, p.precoVenda, p.precoCompra, p.categoria, p.quantidade, "
                   + "f.codigo AS idFornecedor, f.nome AS nomeFornecedor, f.cnpj, f.endereco, f.infoContato "
                   + "FROM produto p "
                   + "JOIN fornecedor f ON p.idFornecedor = f.codigo";
        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto produto = new Produto();
                    Fornecedor fornecedor = new Fornecedor();

                    produto.setIdProduto(rs.getLong("idProduto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPrecoVenda(rs.getDouble("precoVenda"));
                    produto.setPrecoCompra(rs.getDouble("precoCompra"));
                    produto.setCategoria(rs.getString("categoria"));
                    produto.setQuantidade(rs.getInt("quantidade"));

                    fornecedor.setCodigo(rs.getLong("idFornecedor"));
                    fornecedor.setNome(rs.getString("nomeFornecedor"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEndereco(rs.getString("endereco"));
                    fornecedor.setInfoContato(rs.getString("infoContato"));
                    produto.setFornecedor(fornecedor);

                    produtos.add(produto);
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return produtos;
    }

    public Long buscarFornecedorPorProduto(Long idProduto) throws SQLException {
        String sql = "SELECT idFornecedor FROM produto WHERE idProduto = ?";
        Long idFornecedor = null;

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setLong(1, idProduto);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    idFornecedor = rs.getLong("idFornecedor");
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar fornecedor por produto: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return idFornecedor;
    }

    public void atualizarQuantidade(Long idProduto, int novaQuantidade) {
        String sql = "UPDATE produto SET quantidade = ? WHERE idProduto = ?";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, novaQuantidade);
                stmt.setLong(2, idProduto);
                stmt.executeUpdate();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar quantidade do produto: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public void corrigirQuantidade(Long idProduto, int novaQuantidade) {
        String sql = "UPDATE produto SET quantidade = ? WHERE idProduto = ?";
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, novaQuantidade);
                stmt.setLong(2, idProduto);
                stmt.executeUpdate();
                stmt.close();

                JOptionPane.showMessageDialog(null, 
                    "Quantidade do produto com ID " + idProduto + " corrigida para " + novaQuantidade, 
                    "Correção de Quantidade", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao corrigir quantidade do produto: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public Produto buscarPorId(Long idProduto) {
        String sql = "SELECT p.idProduto, p.nome, p.precoVenda, p.precoCompra, p.categoria, p.quantidade, "
                   + "f.codigo AS idFornecedor, f.nome AS nomeFornecedor, f.cnpj, f.endereco, f.infoContato "
                   + "FROM produto p "
                   + "JOIN fornecedor f ON p.idFornecedor = f.codigo "
                   + "WHERE p.idProduto = ?";
        Produto produto = null;

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setLong(1, idProduto);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    produto = new Produto();
                    Fornecedor fornecedor = new Fornecedor();

                    produto.setIdProduto(rs.getLong("idProduto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPrecoVenda(rs.getDouble("precoVenda"));
                    produto.setPrecoCompra(rs.getDouble("precoCompra"));
                    produto.setCategoria(rs.getString("categoria"));
                    produto.setQuantidade(rs.getInt("quantidade"));

                    fornecedor.setCodigo(rs.getLong("idFornecedor"));
                    fornecedor.setNome(rs.getString("nomeFornecedor"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEndereco(rs.getString("endereco"));
                    fornecedor.setInfoContato(rs.getString("infoContato"));
                    produto.setFornecedor(fornecedor);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return produto;
    }

    public long buscarCodigoPorNome(Produto produto) {
        long codigo = -1; // Valor padrão para indicar que o produto não foi encontrado
        String sql = "SELECT idProduto FROM produto WHERE nome = ?";

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, produto.getNome());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    codigo = rs.getLong("idProduto");
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
        return codigo;
    }

    public void verificarEstoqueBaixo() {
        String sql = "SELECT nome, quantidade FROM produto WHERE quantidade < 10"; // Supondo 10 como nível mínimo
        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String nomeProduto = rs.getString("nome");
                    int quantidade = rs.getInt("quantidade");
                    System.out.println("Alerta: O produto " + nomeProduto + " está com estoque baixo: " + quantidade + " unidades.");
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar estoque baixo: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }
}
