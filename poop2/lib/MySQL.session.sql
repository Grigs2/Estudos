CREATE DATABASE supermercado2;
USE supermercado2;

-- Criação da Tabela Pagamento
CREATE TABLE pagamento (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10,2),
    categoria VARCHAR(20),
    descricao VARCHAR(30)
);
DROP TABLE IF EXISTS pagamento;
CREATE TABLE pagamento (
    idPagamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL,
    metodoPagamento VARCHAR(50) NOT NULL
);
ALTER TABLE pedido DROP FOREIGN KEY pedido_ibfk_2;
ALTER TABLE notafiscal DROP FOREIGN KEY notafiscal_ibfk_1;
ALTER TABLE historicovendas DROP FOREIGN KEY historicovendas_ibfk_3;
ALTER TABLE historicocompras DROP FOREIGN KEY historicocompras_ibfk_3;
DROP TABLE IF EXISTS pagamento;
CREATE TABLE pagamento (
    idPagamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL,
    metodoPagamento VARCHAR(50) NOT NULL
);
ALTER TABLE historicocompras ADD CONSTRAINT historicocompras_ibfk_3 FOREIGN KEY (idPagamento) REFERENCES pagamento(idPagamento);




-- Criação da Tabela Fornecedor
CREATE TABLE fornecedor (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30),
    cnpj VARCHAR(15),
    endereco VARCHAR(40),
    infoContato VARCHAR(30)
);

-- Criação da Tabela Produto
CREATE TABLE produto (
    idProduto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    precoVenda DECIMAL(10,2),
    precoCompra DECIMAL(10,2),
    categoria VARCHAR(30),
    quantidade INT,
    idFornecedor BIGINT,
    FOREIGN KEY (idFornecedor) REFERENCES fornecedor(codigo)
);
ALTER TABLE produto ADD quantidade INT;

-- Criação da Tabela Estoque
CREATE TABLE estoque (
    qntdProdutoEstoque INT,
    status ENUM('Em estoque', 'Sem estoque', 'Nível baixo') DEFAULT 'Em estoque', 
    idProduto BIGINT,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);

-- Criação da Tabela ListaProdutos
CREATE TABLE ListaProdutos (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    idProduto BIGINT,
    valorProdutos DECIMAL (10,2),
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto)
);

-- Criação da Tabela Pedido
CREATE TABLE pedido (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME DEFAULT CURRENT_TIMESTAMP, 
    valorTotal DECIMAL(10,2),
    idPagamento BIGINT,
    idProduto BIGINT,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idPagamento) REFERENCES pagamento(codigo)
);

-- Criação da Tabela NotaFiscal
CREATE TABLE notaFiscal (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    qntdProdutoComprado INT,
    valorPedido DECIMAL(10,2),
    idPagamento BIGINT,
    idPedido BIGINT,
    FOREIGN KEY (idPagamento) REFERENCES pagamento(codigo),
    FOREIGN KEY (idPedido) REFERENCES pedido(codigo)
);

-- Criação da Tabela Cliente
CREATE TABLE cliente (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    cpf CHAR(11)
);

-- Criação da Tabela Venda
CREATE TABLE venda (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPedido BIGINT,
    idCliente BIGINT,
    FOREIGN KEY (idPedido) REFERENCES pedido(codigo),
    FOREIGN KEY (idCliente) REFERENCES cliente(codigo)
);

-- Criação da Tabela Compra
CREATE TABLE compra (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    idPedido BIGINT,
    idFornecedor BIGINT,
    FOREIGN KEY (idPedido) REFERENCES pedido(codigo),
    FOREIGN KEY (idFornecedor) REFERENCES fornecedor(codigo)
);
-- Adicionar a coluna idProduto
ALTER TABLE compra ADD COLUMN idProduto BIGINT;

-- Adicionar a coluna quantidade
ALTER TABLE compra ADD COLUMN quantidade INT;

-- Adicionar a coluna categoria
ALTER TABLE compra ADD COLUMN categoria VARCHAR(255);

-- Adicionar a coluna data
ALTER TABLE compra ADD COLUMN data TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Adicionar chave estrangeira para idProduto
ALTER TABLE compra ADD CONSTRAINT fk_compra_produto FOREIGN KEY (idProduto) REFERENCES produto(idProduto);
-- Adicionar a coluna idPagamento
ALTER TABLE compra ADD COLUMN idPagamento BIGINT;

-- Adicionar chave estrangeira para idPagamento
ALTER TABLE compra ADD CONSTRAINT fk_compra_pagamento FOREIGN KEY (idPagamento) REFERENCES pagamento(idPagamento);


ALTER TABLE compra ADD COLUMN dataCompra TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE compra DROP COLUMN dataCompra;


-- Criação da Tabela ItensCompra
CREATE TABLE ItensCompra (
   idItem INT AUTO_INCREMENT PRIMARY KEY,
   quantidade INT,
   idProduto BIGINT,
   idCompra BIGINT,
   FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
   FOREIGN KEY (idCompra) REFERENCES compra(codigo)
);

-- Criação da Tabela ItensVenda
CREATE TABLE ItensVenda (
   idItem INT AUTO_INCREMENT PRIMARY KEY,
   quantidade INT,
   idProduto BIGINT,
   idVenda BIGINT,
   FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
   FOREIGN KEY (idVenda) REFERENCES venda(codigo)
);

-- Criação da Tabela HistoricoVendas
CREATE TABLE HistoricoVendas (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    valor_total DECIMAL(10, 2),
    idProduto BIGINT NOT NULL,
    idVenda BIGINT NOT NULL,
    idPagamento BIGINT NOT NULL,
    idCliente BIGINT NOT NULL,
    idPedido BIGINT NOT NULL,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idVenda) REFERENCES venda(codigo),
    FOREIGN KEY (idPagamento) REFERENCES pagamento(codigo),
    FOREIGN KEY (idCliente) REFERENCES cliente(codigo),
    FOREIGN KEY (idPedido) REFERENCES pedido(codigo)
);

-- Criação da Tabela HistoricoCompras
CREATE TABLE HistoricoCompras (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    valor_total DECIMAL(10, 2),
    idProduto BIGINT NOT NULL,
    idCompra BIGINT NOT NULL,
    idPagamento BIGINT NOT NULL,
    idFornecedor BIGINT NOT NULL,
    idPedido BIGINT NOT NULL,
    FOREIGN KEY (idProduto) REFERENCES produto(idProduto),
    FOREIGN KEY (idCompra) REFERENCES compra(codigo),
    FOREIGN KEY (idPagamento) REFERENCES pagamento(codigo),
    FOREIGN KEY (idFornecedor) REFERENCES fornecedor(codigo),
    FOREIGN KEY (idPedido) REFERENCES pedido(codigo)
);
ALTER TABLE HistoricoCompras ADD COLUMN nome_produto VARCHAR(255);

-- Inserção de Dados na Tabela Fornecedor
INSERT INTO fornecedor (nome, cnpj, endereco, infoContato)
VALUES 
    ('Camil Alimentos', '64904295000456', 'Av. Dra. Ruth Cardoso, 8501', 'Fornecedor arroz'),
    ('COCA COLA INDUSTRIAS LTDA', '45997418001800', 'Rua Geraldo Flausino Gomes, Conjunto 82', '0800 727 1100'),
    ('SANCHEZ CANO LTDA', '03594123000196', 'Avenida José Benassi, 1003', 'rivacidade@finicompany.com');

-- Procedures
CREATE PROCEDURE PopularHistoricoVenda(
    IN p_idProduto BIGINT,
    IN p_idVenda BIGINT,
    IN p_idPagamento BIGINT,
    IN p_idCliente BIGINT,
    IN p_idPedido BIGINT,
    IN p_quantidade INT
)
BEGIN
    DECLARE variavel_nomeProduto VARCHAR(255);
    DECLARE variavel_valorTotal DECIMAL(10, 2);

    SELECT nome, precoVenda INTO variavel_nomeProduto, variavel_valorTotal
    FROM produto
    WHERE idProduto = p_idProduto;

    SET variavel_valorTotal = variavel_valorTotal * p_quantidade;

    INSERT INTO HistoricoVendas (
        nome_produto, quantidade, valor_total, idProduto, idVenda, idPagamento, idCliente, idPedido
    )
    VALUES (
        variavel_nomeProduto, p_quantidade, variavel_valorTotal, p_idProduto, p_idVenda, p_idPagamento, p_idCliente, p_idPedido
    );
END;

CREATE PROCEDURE PopularHistoricoCompra (
    IN p_idProduto BIGINT,
    IN p_idCompra BIGINT,
    IN p_idPagamento BIGINT,
    IN p_idFornecedor BIGINT,
    IN p_idPedido BIGINT,
    IN p_quantidade INT
)
BEGIN
    DECLARE variavel_nomeProduto VARCHAR(255);
    DECLARE variavel_valorTotal DECIMAL(10, 2);

    SELECT nome, precoCompra INTO variavel_nomeProduto, variavel_valorTotal
    FROM produto
    WHERE idProduto = p_idProduto;

    SET variavel_valorTotal = variavel_valorTotal * p_quantidade;

    INSERT INTO HistoricoCompras (
        nome_produto, quantidade, valor_total, idProduto, idCompra, idPagamento, idFornecedor, idPedido
    )
    VALUES (
        variavel_nomeProduto, p_quantidade, variavel_valorTotal, p_idProduto, p_idCompra, p_idPagamento, p_idFornecedor, p_idPedido
    );
END;

CREATE TRIGGER tg_after_insert_itensvenda
AFTER INSERT ON ItensVenda
FOR EACH ROW
BEGIN
    CALL PopularHistoricoVenda(
        NEW.idProduto,   
        NEW.idVenda,     
        (SELECT idPagamento FROM pedido WHERE codigo = (SELECT idPedido FROM venda WHERE codigo = NEW.idVenda)), 
        (SELECT idCliente FROM venda WHERE codigo = NEW.idVenda), 
        (SELECT idPedido FROM venda WHERE codigo = NEW.idVenda), 
        NEW.quantidade    
    );
END;

CREATE TRIGGER tg_after_insert_itenscompra
AFTER INSERT ON ItensCompra
FOR EACH ROW
BEGIN
    DECLARE nomeProduto VARCHAR(255);
    DECLARE precoCompra DOUBLE;
    DECLARE valorTotal DOUBLE;

    -- Obter o nome do produto e o preço de compra
    SELECT nome, precoCompra INTO nomeProduto, precoCompra
    FROM produto
    WHERE idProduto = NEW.idProduto;

    -- Calcular o valor total da compra
    SET valorTotal = precoCompra * NEW.quantidade;

    -- Inserir os dados na tabela HistoricoCompras
    INSERT INTO HistoricoCompras (
        nome_produto, quantidade, valor_total, idProduto, idCompra, idPagamento, idFornecedor, idPedido
    ) VALUES (
        nomeProduto, NEW.quantidade, valorTotal, NEW.idProduto, NEW.idCompra,
        (SELECT idPagamento FROM pedido WHERE codigo = (SELECT idPedido FROM compra WHERE codigo = NEW.idCompra)),
        (SELECT idFornecedor FROM compra WHERE codigo = NEW.idCompra),
        (SELECT idPedido FROM compra WHERE codigo = NEW.idCompra)
    );
END;

DROP TRIGGER IF EXISTS tg_after_insert_itenscompra;

CREATE TRIGGER tg_after_insert_itenscompra
AFTER INSERT ON ItensCompra
FOR EACH ROW
BEGIN
    DECLARE nomeProduto VARCHAR(255);
    DECLARE precoCompra DOUBLE;
    DECLARE valorTotal DOUBLE;
    DECLARE idPagamento BIGINT;
    DECLARE idFornecedor BIGINT;
    DECLARE idPedido BIGINT;

    -- Obter o nome do produto e o preço de compra
    SELECT nome, precoCompra INTO nomeProduto, precoCompra
    FROM produto
    WHERE idProduto = NEW.idProduto;

    -- Calcular o valor total da compra
    SET valorTotal = precoCompra * NEW.quantidade;

    -- Obter o idPagamento, idFornecedor e idPedido relacionados
    SELECT idPagamento, idFornecedor, idPedido INTO idPagamento, idFornecedor, idPedido
    FROM compra
    WHERE codigo = NEW.idCompra;

    -- Inserir os dados na tabela HistoricoCompras
    INSERT INTO HistoricoCompras (
        nome_produto, quantidade, valor_total, idProduto, idCompra, idPagamento, idFornecedor, idPedido
    ) VALUES (
        nomeProduto, NEW.quantidade, valorTotal, NEW.idProduto, NEW.idCompra,
        idPagamento,
        idFornecedor,
        idPedido
    );
END;




