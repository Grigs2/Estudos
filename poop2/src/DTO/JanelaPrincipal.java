package DTO;

import javax.swing.*;
import DAO.FornecedorDAO;
import java.awt.*;
import java.util.ArrayList;

public class JanelaPrincipal extends JFrame {
    public int WIDTH = 800;
    public int HEIGHT = 600;
    private JTabbedPane abas;
    private ArrayList<Fornecedor> listaFornecedores;

    public JanelaPrincipal() {
        setTitle("Sistema de Supermercado");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        listaFornecedores = fornecedorDAO.buscarTodos();

        // Criar Painéis
        VendaPainel vendaPainel = new VendaPainel();
        PainelControleEstoque painelControleEstoque = new PainelControleEstoque(vendaPainel, listaFornecedores);
       

        // Abas
        abas = new JTabbedPane();
        abas.addTab("Controle de Estoque", painelControleEstoque);
        abas.addTab("Registrar Venda", vendaPainel);
        abas.addTab("Registrar Compra", new CompraPainel());

        // Colocando abas
        add(abas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal frame = new JanelaPrincipal();
            frame.setVisible(true);
        });
    }
}
