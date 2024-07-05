CREATE TABLE IF NOT EXISTS brands (
    id BIGSERIAL PRIMARY KEY,
    nome_marca VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS models (
    id BIGSERIAL PRIMARY KEY,
    marca_id BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    valor_fipe BIGINT NOT NULL,
    FOREIGN KEY (marca_id) REFERENCES brands(id)
);

CREATE TABLE IF NOT EXISTS cars (
    id BIGSERIAL PRIMARY KEY,
    timestamp_cadastro TIMESTAMP NOT NULL,
    modelo_id BIGINT NOT NULL,
    ano BIGINT NOT NULL,
    combustivel VARCHAR(50) NOT NULL,
    num_portas BIGINT NOT NULL,
    cor VARCHAR(50) NOT NULL,
    FOREIGN KEY (modelo_id) REFERENCES models(id)
);