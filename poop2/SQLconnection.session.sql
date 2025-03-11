INSERT INTO fornecedor (nome, cnpj, endereco, infoContato)
VALUES 
	('Camil Alimentos', '64904295000456', 'Av. Dra. Ruth Cardoso, 8501', 'Fornecedor arroz'),
	('COCA COLA INDUSTRIAS LTDA', '45997418001800', 'Rua Geraldo Flausino Gomes, Conjunto 82', '0800 727 1100'),
	('SANCHEZ CANO LTDA', '03594123000196', 'Avenida José Benassi, 1003', 'rivacidade@finicompany.com');

ALTER TABLE produto ADD COLUMN quantidade INT;
