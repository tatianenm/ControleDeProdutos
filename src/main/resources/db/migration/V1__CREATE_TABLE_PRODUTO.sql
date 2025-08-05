CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nomeempresa VARCHAR(255) NOT NULL,
    origem VARCHAR(255) ,
    tipo VARCHAR(255) NOT NULL,
    nomeproduto VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT,
    UNIQUE (nomeproduto,tipo)
);

