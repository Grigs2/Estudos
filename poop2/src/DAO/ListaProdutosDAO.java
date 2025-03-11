package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.ListaProdutos;

public class ListaProdutosDAO {

    private ConexaoMysql conexao = new ConexaoMysql();

    public ListaProdutosDAO(){
        String sql = "CREATE TABLE IF NOT EXISTS listProdutos("
                +   "codigo BIGINT AUTO_INCREMENT PRIMARY KEY,"
                +   "idProduto BIGINT,"
                +   "qtndProduto INT;"
                +   "valorTotalProduto DECIMAL(10,2));";

        try {
            if(this.conexao.conectar()){
                Statement stmt = this.conexao.criarStatement();
                stmt.execute(sql);
                System.out.println("Tabela Cliente Criada ou já existente");
                stmt.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            conexao.desconectar();
        }
    }

    public int inserir(ListaProdutos obj) {
        int tamanho = obj.produtos.size();
        conexao.conectar();
        int linhasInseridas = 0;
    
        try {
            for (int i = 0; i < tamanho; i++) {
                String sql = "insert into listaProdutos(idProduto, qntdProduto, valorTotalProduto) values (?,?,?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setLong(1, obj.produtos.get(i).getCodigo());
                stmt.setInt(2, obj.qntdProduto.get(i));
                stmt.setDouble(3, obj.valorTotalProduto.get(i)); 
                linhasInseridas += stmt.executeUpdate();
            }
            return linhasInseridas;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        } finally {
            conexao.desconectar();
        }
    }
    
    }

