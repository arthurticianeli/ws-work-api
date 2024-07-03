-- Inserindo marcas mais diversificadas
INSERT INTO brands (nome_marca) VALUES ('Toyota');
INSERT INTO brands (nome_marca) VALUES ('Volkswagen');
INSERT INTO brands (nome_marca) VALUES ('Ford');
INSERT INTO brands (nome_marca) VALUES ('Chevrolet');
INSERT INTO brands (nome_marca) VALUES ('Fiat');
INSERT INTO brands (nome_marca) VALUES ('Honda');
INSERT INTO brands (nome_marca) VALUES ('BMW');
INSERT INTO brands (nome_marca) VALUES ('Mercedes-Benz');

-- Inserindo modelos para cada marca com valores FIPE variados
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (1, 'Corolla', 110000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (2, 'Gol', 50000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (3, 'Fiesta', 55000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (4, 'Onix', 60000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (5, 'Toro', 120000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (6, 'Civic', 95000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (7, 'Série 3', 250000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (8, 'Classe C', 270000);

-- Inserindo carros com diferentes características
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 1, 2022, 'Flex', 4, 'Branco');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 2, 2018, 'Gasolina', 2, 'Vermelho');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 3, 2019, 'Diesel', 4, 'Preto');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 4, 2021, 'Flex', 4, 'Azul');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 5, 2020, 'Gasolina', 4, 'Cinza');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 6, 2023, 'Flex', 4, 'Preto');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 7, 2017, 'Gasolina', 2, 'Branco');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, 8, 2016, 'Diesel', 4, 'Prata');

-- Adicionando mais modelos para Toyota
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (1, 'Yaris', 80000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (1, 'RAV4', 180000);

-- Adicionando mais modelos para Volkswagen
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (2, 'Polo', 70000);
INSERT INTO models (marca_id, nome, valor_fipe) VALUES (2, 'Tiguan', 150000);

-- Adicionando carros para os novos modelos de Toyota
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, (SELECT id FROM models WHERE nome = 'Yaris'), 2021, 'Flex', 4, 'Prata');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, (SELECT id FROM models WHERE nome = 'RAV4'), 2022, 'Gasolina', 4, 'Preto');

-- Adicionando carros para os novos modelos de Volkswagen
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, (SELECT id FROM models WHERE nome = 'Polo'), 2020, 'Flex', 4, 'Vermelho');
INSERT INTO cars (timestamp_cadastro, modelo_id, ano, combustivel, num_portas, cor) VALUES (CURRENT_TIMESTAMP, (SELECT id FROM models WHERE nome = 'Tiguan'), 2023, 'Diesel', 4, 'Azul');

INSERT INTO users (login, password) VALUES ('wswork', '$2a$12$9HMQW2IOlnfPmJYU6L7/muq2H/jIhmEKuPDtPowec6iR0ga6lR0vq')
