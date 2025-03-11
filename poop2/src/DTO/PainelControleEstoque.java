package DTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.ProdutoDAO;
import DTO.Fornecedor;
import DTO.Produto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelControleEstoque extends JPanel {
    private JTextField campoNome;
    private JTextField campoPrecoVenda;
    private JTextField campoPrecoCompra;
    private JTextField campoCategoria;
    private JComboBox<Fornecedor> comboBoxFornecedor;
    private JTextField campoQuantidade;
    private JButton botaoAdicionarProduto;
    private JTable tabelaEstoque;
    private JTextField campoNovaQuantidade; // Campo para a nova quantidade
    private JButton botaoAtualizarQuantidade; // Botão para atualizar a quantidade
    private DefaultTableModel model;
    private JButton botaoAtualizarTabela;
    private VendaPainel vendaPainel; // Referência ao painel de vendas

    public PainelControleEstoque(VendaPainel vendaPainel, ArrayList<Fornecedor> fornecedores) {
        this.vendaPainel = vendaPainel;
        setLayout(new BorderLayout());

        // Painel de Formulário
        JPanel painelFormulario = new JPanel(new GridLayout(10, 2)); // Ajustado para 8 linhas
        painelFormulario.add(new JLabel("Nome do Produto:"));
        campoNome = new JTextField();
        painelFormulario.add(campoNome);

        painelFormulario.add(new JLabel("Preço de Venda:"));
        campoPrecoVenda = new JTextField();
        painelFormulario.add(campoPrecoVenda);

        painelFormulario.add(new JLabel("Preço de Compra:"));
        campoPrecoCompra = new JTextField();
        painelFormulario.add(campoPrecoCompra);

        painelFormulario.add(new JLabel("Categoria:"));
        campoCategoria = new JTextField();
        painelFormulario.add(campoCategoria);

        painelFormulario.add(new JLabel("Fornecedor:"));
        comboBoxFornecedor = new JComboBox<>(new DefaultComboBoxModel<>(fornecedores.toArray(new Fornecedor[0])));
        painelFormulario.add(comboBoxFornecedor);

        painelFormulario.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        painelFormulario.add(campoQuantidade);

        botaoAdicionarProduto = new JButton("Adicionar Produto");
        painelFormulario.add(botaoAdicionarProduto);
        painelFormulario.add(new JLabel());

        painelFormulario.add(new JLabel("Nova Quantidade:"));
        campoNovaQuantidade = new JTextField();
        painelFormulario.add(campoNovaQuantidade);

        botaoAtualizarQuantidade = new JButton("Atualizar Quantidade");
        painelFormulario.add(botaoAtualizarQuantidade);
        
        botaoAtualizarTabela = new JButton("Atualizar Tabela");
        painelFormulario.add(botaoAtualizarTabela);
        painelFormulario.add(new JLabel()); // Espaço vazio

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela de Produtos em Estoque
        String[] colunas = {"Nome", "Código", "Preço de Venda", "Preço de Compra", "Categoria", "Fornecedor", "Quantidade"};
        model = new DefaultTableModel(new Object[][]{}, colunas);
        tabelaEstoque = new JTable(model);
        add(new JScrollPane(tabelaEstoque), BorderLayout.CENTER);

        // Carregar produtos do banco de dados
        carregarProdutos(model);

        // Ação do Botão para Adicionar Produto
        botaoAdicionarProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = campoNome.getText();
                    double precoVenda = Double.parseDouble(campoPrecoVenda.getText());
                    double precoCompra = Double.parseDouble(campoPrecoCompra.getText());
                    String categoria = campoCategoria.getText();
                    Fornecedor fornecedor = (Fornecedor) comboBoxFornecedor.getSelectedItem();
                    int quantidade = Integer.parseInt(campoQuantidade.getText());

                    Produto produto = new Produto(nome, precoVenda, precoCompra, categoria, fornecedor, quantidade);
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    int resultado = produtoDAO.inserir(produto);

                    if (resultado > 0) {
                        model.addRow(new Object[]{nome, produto.getIdProduto(), precoVenda, precoCompra, categoria, fornecedor.getNome(), quantidade});
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao adicionar produto ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos para preço e quantidade.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão para Atualizar Quantidade
        botaoAtualizarQuantidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabelaEstoque.getSelectedRow();
                if (linhaSelecionada != -1) {
                    try {
                        int novaQuantidade = Integer.parseInt(campoNovaQuantidade.getText());
                        Long codigoProduto = (Long) tabelaEstoque.getValueAt(linhaSelecionada, 1);

                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        produtoDAO.corrigirQuantidade(codigoProduto, novaQuantidade);

                        tabelaEstoque.setValueAt(novaQuantidade, linhaSelecionada, 6); // Atualiza a quantidade na tabela
                        atualizarTabelaProdutos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um valor válido para a quantidade.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para atualizar a quantidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do Botão Atualizar Tabela
        botaoAtualizarTabela.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabelaProdutos(); // Chamar o método para atualizar a tabela
                vendaPainel.carregarProdutosNaComboBox(); // Atualizar a comboBox na tela de vendas
                verificarEstoqueBaixo(); // Verificar e notificar estoque baixo
            }
        });
    }

    public void atualizarTabelaProdutos() {
        model.setRowCount(0); // Limpar a tabela
        carregarProdutos(model); // Recarregar produtos
        verificarEstoqueBaixo(); // Verificar estoque baixo e notificar usuário
    }

    public void verificarEstoqueBaixo() {
        for (int i = 0; i < model.getRowCount(); i++) {
            int quantidade = (int) model.getValueAt(i, 6);
            if (quantidade < 4) {
                String nomeProduto = (String) model.getValueAt(i, 0);
                JOptionPane.showMessageDialog(this, "Atenção: O produto '" + nomeProduto + "' está com estoque baixo (" + quantidade + " unidades restantes).", "Estoque Baixo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void carregarProdutos(DefaultTableModel model) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<Produto> produtos = produtoDAO.buscarTodos();
        for (Produto produto : produtos) {
            model.addRow(new Object[]{
                produto.getNome(),
                produto.getIdProduto(),
                produto.getPrecoVenda(),
                produto.getPrecoCompra(),
                produto.getCategoria(),
                produto.getFornecedor().getNome(),
                produto.getQuantidade()
            });
        }
    }
}
