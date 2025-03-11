package DTO;

import DAO.CompraDAO;
import DAO.ProdutoDAO;
import DAO.FornecedorDAO;
import DTO.Compra;
import DTO.Produto;
import DTO.Fornecedor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompraPainel extends JPanel {
    private JComboBox<Produto> comboBoxProduto;
    private JTextField campoQuantidade;
    private JComboBox<Fornecedor> comboBoxFornecedor;
    private JButton botaoRegistrarCompra;
    private JButton botaoAtualizarLista;
    private JTable tabelaCompras;
    private DefaultTableModel model;

    public CompraPainel() {
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel painelFormulario = new JPanel(new GridLayout(5, 2));
        painelFormulario.add(new JLabel("Produto:"));
        comboBoxProduto = new JComboBox<>();
        carregarProdutosNaComboBox();  // Carregar produtos na comboBox
        painelFormulario.add(comboBoxProduto);

        painelFormulario.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        painelFormulario.add(campoQuantidade);

        painelFormulario.add(new JLabel("Fornecedor:"));
        comboBoxFornecedor = new JComboBox<>();
        carregarFornecedoresNaComboBox();  // Carregar fornecedores na comboBox
        painelFormulario.add(comboBoxFornecedor);

        botaoRegistrarCompra = new JButton("Registrar Compra");
        painelFormulario.add(botaoRegistrarCompra);
        painelFormulario.add(new JLabel());

        // Botão de Atualização da Lista de Compras
        botaoAtualizarLista = new JButton("Atualizar Lista de Compras");
        painelFormulario.add(botaoAtualizarLista);

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela de Compras
        String[] colunas = {"Produto", "Quantidade", "Fornecedor", "Data"};
        model = new DefaultTableModel(new Object[][]{}, colunas);
        tabelaCompras = new JTable(model);
        add(new JScrollPane(tabelaCompras), BorderLayout.CENTER);

        // Ação do Botão Registrar Compra
        botaoRegistrarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Produto produto = (Produto) comboBoxProduto.getSelectedItem();
                    Long idProduto = produto.getIdProduto();
                    int quantidade = Integer.parseInt(campoQuantidade.getText());
                    Fornecedor fornecedor = (Fornecedor) comboBoxFornecedor.getSelectedItem();
                    Long idFornecedor = fornecedor.getCodigo();

                    // Criar instâncias dos DTOs
                    Compra compra = new Compra();
                    compra.setIdProduto(idProduto);
                    compra.setQuantidade(quantidade);
                    compra.setIdFornecedor(idFornecedor);

                    // Registrar compra no banco de dados
                    CompraDAO compraDAO = new CompraDAO();
                    compraDAO.inserir(compra);

                    // Atualizar a lista de compras automaticamente
                    atualizarListaDeCompras();

                    JOptionPane.showMessageDialog(null, "Compra registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para os campos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar compra: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão Atualizar Lista de Compras
        botaoAtualizarLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaDeCompras();
            }
        });

        // Carregar lista inicial de compras
        atualizarListaDeCompras();
    }

    private void carregarProdutosNaComboBox() {
        comboBoxProduto.removeAllItems();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = produtoDAO.buscarTodos();
        for (Produto produto : produtos) {
            comboBoxProduto.addItem(produto);
        }
    }

    private void carregarFornecedoresNaComboBox() {
        comboBoxFornecedor.removeAllItems();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ArrayList<Fornecedor> fornecedores = fornecedorDAO.buscarTodos();
        for (Fornecedor fornecedor : fornecedores) {
            comboBoxFornecedor.addItem(fornecedor);
        }
    }

    private void atualizarListaDeCompras() {
        model.setRowCount(0);  // Limpar tabela
        CompraDAO compraDAO = new CompraDAO();
        ArrayList<Compra> compras = compraDAO.buscarTodas();
        for (Compra compra : compras) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.buscarPorId(compra.getIdProduto());
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = fornecedorDAO.buscarPorId(compra.getIdFornecedor());
            model.addRow(new Object[]{produto.getNome(), compra.getQuantidade(), fornecedor.getNome(), compra.getData()});
        }
        carregarProdutosNaComboBox();  // Atualizar a ComboBox de produtos
    }
}
