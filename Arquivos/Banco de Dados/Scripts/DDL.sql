--
-- Criando base de dados caso nao exista
--
CREATE DATABASE IF NOT EXISTS basanelapark;
USE basanelapark;
/*--------------------------------------------------------------------------*/

--
-- Criacao da tabela Operador
--
CREATE TABLE Operador (
	codOperador INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(200) NOT NULL,
	login VARCHAR(20) NOT NULL,
	senha VARCHAR(32) NOT NULL,
	administrador BIT NOT NULL,
	status BIT NOT NULL,
	PRIMARY KEY (codOperador)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Estado
--
CREATE TABLE Estado (
	codEstado INT NOT NULL AUTO_INCREMENT,
	uf VARCHAR(2) NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codEstado)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Cidade
--
CREATE TABLE Cidade (
	codCidade INT NOT NULL AUTO_INCREMENT,
	codEstado INT NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codCidade),
	FOREIGN KEY (codEstado) REFERENCES Estado(codEstado)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Cliente
--
CREATE TABLE Cliente(
	cpf VARCHAR(14) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	codEstado INT NOT NULL,
	codCidade INT NOT NULL,
	telefone VARCHAR(13) NOT NULL,
	celular VARCHAR(14) DEFAULT NULL,
	periodo INT NOT NULL,
	status BIT NOT NULL,
	PRIMARY KEY (cpf),
	FOREIGN KEY (codEstado) REFERENCES Estado(codEstado),
	FOREIGN KEY (codCidade) REFERENCES Cidade(codCidade)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Marca
--
CREATE TABLE Marca (
	codMarca INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codMarca)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Modelo
--
CREATE TABLE Modelo (
	codModelo INT NOT NULL AUTO_INCREMENT,
	codMarca INT NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codModelo),
	FOREIGN KEY (codMarca) REFERENCES Marca(codMarca)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Veiculo
--
CREATE TABLE Veiculo (
	placa VARCHAR(8) NOT NULL,
	tipoVeiculo BIT NOT NULL,
	codMarca INT NOT NULL,
	codModelo INT NOT NULL,
	PRIMARY KEY (placa),
	FOREIGN KEY (codMarca) REFERENCES Marca(codMarca),
	FOREIGN KEY (codModelo) REFERENCES Modelo(codModelo)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela ClienteXVeiculo
--
CREATE TABLE ClienteXVeiculo(
	cpf varchar(14) NOT NULL,
	placa VARCHAR(8) NOT NULL,
	FOREIGN KEY (cpf) REFERENCES Cliente(cpf),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa)
);
-- ------------------------------------------------------------------------

--
-- Criacao da tabela Movimento
--
CREATE TABLE Movimento (
	codMovimento INT NOT NULL AUTO_INCREMENT,
	cpf VARCHAR(14) DEFAULT NULL,
	placa VARCHAR(8) NOT NULL,
	dataInicio DATETIME NOT NULL,
	dataTermino DATETIME DEFAULT NULL,
	PRIMARY KEY (codMovimento),
	FOREIGN KEY (cpf) REFERENCES Cliente(cpf),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa)
);
-- ------------------------------------------------------------------------

/*CREATE TABLE Fatura (
	codFatura INT NOT NULL auto_increment,
	codMovimento INT NOT NULL,
	dataVencimento DATE,
	dataPagamento DATE DEFAULT NULL,
	status INT NOT NULL,
	PRIMARY KEY (codFatura),
	FOREIGN KEY (codMovimento) REFERENCES Movimento(codMovimento)
);*/