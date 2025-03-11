package DAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.Estoque;

public class EstoqueDAO {

    private ConexaoMysql conexao = new ConexaoMysql();

    public EstoqueDAO(){
        String sql = "CREATE TABLE IF NOT EXISTS estoque("
                +   "qntdProdutoEstoque INT,"
                +   "status ENUM('Em estoque', 'Sem estoque', 'Nível baixo') DEFAULT 'Em estoque',"
                +   "idProduto BIGINT,"
                +   "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto));" ;

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

    public int inserir(Estoque obj){
        conexao.conectar();
        String sql = "insert into estoque(qntdProdutoEstoque, status, idProduto) values (?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        try {
            stmt.setInt(1, obj.getQtndProdutoEstoque());
            stmt.setString(2, obj.getStatus());
            stmt.setLong(3, obj.produto.getCodigo());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }finally{
            conexao.desconectar();
        }
    }


}
