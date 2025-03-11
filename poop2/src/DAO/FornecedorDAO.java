package DAO;

import java.sql.*;
import java.util.ArrayList;
import DTO.Fornecedor;

public class FornecedorDAO {
    private ConexaoMysql conexao = new ConexaoMysql();

    public FornecedorDAO() {
        String sql = "CREATE TABLE IF NOT EXISTS fornecedor("
                + "codigo BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(30),"
                + "cnpj VARCHAR(15),"
                + "endereco VARCHAR(40),"
                + "infoContato VARCHAR(30));";

        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela Fornecedor Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public int inserir(Fornecedor obj) {
        conexao.conectar();
        String sql = "INSERT INTO fornecedor(nome, cnpj, endereco, infoContato) VALUES (?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getInfoContato());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }

    // Método para buscar todos os fornecedores
    public ArrayList<Fornecedor> buscarTodos() {
        ArrayList<Fornecedor> listaFornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor";
        try {
            if (this.conexao.conectar()) {
                Statement stmt = this.conexao.criarStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Fornecedor fornecedor = new Fornecedor(
                            rs.getLong("codigo"),
                            rs.getString("nome"),
                            rs.getString("cnpj"),
                            rs.getString("endereco"),
                            rs.getString("infoContato")
                    );
                    listaFornecedores.add(fornecedor);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
        return listaFornecedores;
    }

    // Método para buscar fornecedor por ID
    public Fornecedor obterFornecedorPopulado(long codigo) {
        Fornecedor fornecedor = null;
        String sql = "SELECT * FROM fornecedor WHERE codigo = ?";
        try {
            if (this.conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setLong(1, codigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    fornecedor = new Fornecedor();
                    fornecedor.setCodigo(rs.getLong("codigo"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEndereco(rs.getString("endereco"));
                    fornecedor.setInfoContato(rs.getString("infoContato"));
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
        return fornecedor;
    }

    // Método para buscar fornecedor por ID (novo método simplificado)
    public Fornecedor buscarPorId(Long idFornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE codigo = ?";
        Fornecedor fornecedor = null;

        try {
            if (conexao.conectar()) {
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setLong(1, idFornecedor);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    fornecedor = new Fornecedor();
                    fornecedor.setCodigo(rs.getLong("codigo"));
                    fornecedor.setNome(rs.getString("nome"));
                    fornecedor.setCnpj(rs.getString("cnpj"));
                    fornecedor.setEndereco(rs.getString("endereco"));
                    fornecedor.setInfoContato(rs.getString("infoContato"));
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar fornecedor por ID: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }

        return fornecedor;
    }
}
