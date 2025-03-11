package DTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.ProdutoDAO;
import DTO.Produto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VendaPainel extends JPanel {
    private JComboBox<Produto> comboBoxProduto;
    private JTextField campoQuantidade;
    private JButton botaoRegistrarVenda;
    private JTable tabelaProdutos;
    private DefaultTableModel model;
    private JButton botaoAtualizarTabela;

    public VendaPainel() {
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2));
        painelFormulario.add(new JLabel("Produto:"));
        comboBoxProduto = new JComboBox<>();
        painelFormulario.add(comboBoxProduto);

        painelFormulario.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        painelFormulario.add(campoQuantidade);

        botaoRegistrarVenda = new JButton("Registrar Venda");
        painelFormulario.add(botaoRegistrarVenda);
        painelFormulario.add(new JLabel());

        // Botão para atualizar a tabela
        botaoAtualizarTabela = new JButton("Atualizar Tabela");
        painelFormulario.add(botaoAtualizarTabela);
        painelFormulario.add(new JLabel());

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela de Produtos
        String[] colunas = {"Nome", "Código", "Preço de Venda", "Quantidade em Estoque"};
        model = new DefaultTableModel(new Object[][]{}, colunas);
        tabelaProdutos = new JTable(model);
        add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

        // Carregar produtos na tabela e na comboBox
        carregarProdutosNaTabela();
        carregarProdutosNaComboBox();

        // Ação do Botão Registrar Venda
        botaoRegistrarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Produto produto = (Produto) comboBoxProduto.getSelectedItem();
                    int quantidadeVendida = Integer.parseInt(campoQuantidade.getText());

                    if (produto != null && quantidadeVendida > 0) {
                        int novaQuantidade = produto.getQuantidade() - quantidadeVendida;

                        if (novaQuantidade < 0) {
                            JOptionPane.showMessageDialog(null, 
                                "Erro: Não há estoque suficiente para realizar a venda.", 
                                "Erro de Estoque", 
                                JOptionPane.ERROR_MESSAGE);
                        } else {
                            registrarVenda(produto, novaQuantidade);
                            atualizarTabela(produto, quantidadeVendida);
                            atualizarTabelaProdutos();

                            // Verificar estoque baixo e exibir aviso
                            if (novaQuantidade < 4) {
                                JOptionPane.showMessageDialog(null, 
                                    "Atenção: O produto '" + produto.getNome() + "' está com estoque baixo (" + novaQuantidade + " unidades restantes).", 
                                    "Estoque Baixo", 
                                    JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, selecione um produto e insira uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido para a quantidade.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão Atualizar Tabela
        botaoAtualizarTabela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabelaProdutos();
                carregarProdutosNaComboBox(); // Adicionado para atualizar a comboBox
                verificarEstoqueBaixo(); // Verificar estoque baixo ao atualizar a tabela
            }
        });
    }

    public void carregarProdutosNaComboBox() {
        comboBoxProduto.removeAllItems();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = produtoDAO.buscarTodos();
        for (Produto produto : produtos) {
            comboBoxProduto.addItem(produto);
        }
    }

    private void carregarProdutosNaTabela() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = produtoDAO.buscarTodos();
        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getNome(), produto.getIdProduto(), produto.getPrecoVenda(), produto.getQuantidade()});
        }
    }

    private void registrarVenda(Produto produto, int novaQuantidade) {
        try {
            // Atualizar quantidade no banco de dados
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.atualizarQuantidade(produto.getIdProduto(), novaQuantidade);

            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela(Produto produto, int quantidadeVendida) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(produto.getIdProduto())) {
                int quantidadeAtual = (int) model.getValueAt(i, 3);
                model.setValueAt(quantidadeAtual - quantidadeVendida, i, 3);
                break;
            }
        }
    }

    public void atualizarTabelaProdutos() {
        model.setRowCount(0); // Limpar a tabela
        carregarProdutosNaTabela(); // Recarregar produtos
    }

    public void verificarEstoqueBaixo() {
        for (int i = 0; i < model.getRowCount(); i++) {
            int quantidade = (int) model.getValueAt(i, 3);
            if (quantidade < 4) {
                String nomeProduto = (String) model.getValueAt(i, 0);
                JOptionPane.showMessageDialog(this, 
                    "Atenção: O produto '" + nomeProduto + "' está com estoque baixo (" + quantidade + " unidades restantes).", 
                    "Estoque Baixo", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
