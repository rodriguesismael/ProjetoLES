CREATE DATABASE IF NOT EXISTS estacionamento;
USE estacionamento;
CREATE TABLE Marca(
	codMarca INT NOT NULL auto_increment,
    descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codMarca)
);

/*CREATE GENERATOR gnMarca;

SET GENERATOR gnMarca TO 0;

SET TERM %;
CREATE TRIGGER Marca_AI FOR Marca ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codMarca = GEN_ID(gnMarca, 1);
		END %
SET TERM ;%*/

CREATE TABLE Modelo(
	codModelo INT NOT NULL auto_increment,
	codMarca INT NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codModelo),
	FOREIGN KEY (codMarca) REFERENCES Marca(codMarca)
);

/*CREATE GENERATOR gnModelo;

SET GENERATOR gnModelo TO 0;

SET TERM %;
CREATE TRIGGER Modelo_AI FOR Modelo ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codModelo = GEN_ID(gnModelo, 1);
		END %
SET TERM ;%*/

CREATE TABLE Veiculo(
	placa VARCHAR(8) NOT NULL,
	tipo VARCHAR(1) NOT NULL,
	codMarca INT NOT NULL,
	codModelo INT NOT NULL,
	PRIMARY KEY (placa),
	FOREIGN KEY (codMarca) REFERENCES Marca(codMarca),
	FOREIGN KEY (codModelo) REFERENCES Modelo(codModelo)
);

CREATE TABLE Estado(
	codEstado INT NOT NULL auto_increment,
	uf VARCHAR(2) NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codEstado)
);

/*CREATE GENERATOR gnEstado;

SET GENERATOR gnEstado TO 0;

SET TERM %;
CREATE TRIGGER Estado_AI FOR Estado ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codEstado = GEN_ID(gnEstado, 1);
		END %
SET TERM ;%*/

CREATE TABLE Cidade(
	codCidade INT NOT NULL auto_increment,
	codEstado INT NOT NULL,
	descricao VARCHAR(200) NOT NULL,
	PRIMARY KEY (codCidade),
	FOREIGN KEY (codEstado) REFERENCES Estado(codEstado)
);

/*CREATE GENERATOR gnCidade;

SET GENERATOR gnCidade TO 0;

SET TERM %;
CREATE TRIGGER Cidade_AI FOR Cidade ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codCidade = GEN_ID(gnCidade, 1);
		END %
SET TERM ;%*/

CREATE TABLE Cliente(
	codCliente VARCHAR(11) NOT NULL,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	codEstado INT NOT NULL,
	codCidade INT NOT NULL,
	telefone VARCHAR(13) NOT NULL,
	celular VARCHAR(14) DEFAULT NULL,
	periodo INT NOT NULL,
	PRIMARY KEY (codCliente),
	FOREIGN KEY (codEstado) REFERENCES Estado(codEstado),
	FOREIGN KEY (codCidade) REFERENCES Cidade(codCidade)
);

/*CREATE GENERATOR gnCliente;

SET GENERATOR gnCliente TO 0;

SET TERM %;
CREATE TRIGGER Cliente_AI FOR Cliente ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codCliente = GEN_ID(gnCliente, 1);
		END %
SET TERM ;%*/

CREATE TABLE ClienteXVeiculo(
	codCliente varchar(11) NOT NULL,
	placa VARCHAR(8) NOT NULL,
	FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa)
);

CREATE TABLE Movimento (
	codMovimento INT NOT NULL auto_increment,
	codCliente INT DEFAULT NULL,
	placa VARCHAR(8) NOT NULL,
	dataInicio DATE NOT NULL,
	dataTermino DATE DEFAULT NULL,
	PRIMARY KEY (codMovimento),
	FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente),
	FOREIGN KEY (placa) REFERENCES Veiculo(placa)
);

/*CREATE GENERATOR gnMovimento;

SET GENERATOR gnMovimento TO 0;

SET TERM %;
CREATE TRIGGER Movimento_AI FOR Movimento ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codMovimento = GEN_ID(gnMovimento, 1);
		END %
SET TERM ;%*/

CREATE TABLE Fatura (
	codFatura INT NOT NULL auto_increment,
	codMovimento INT NOT NULL,
	dataVencimento DATE,
	dataPagamento DATE DEFAULT NULL,
	status INT NOT NULL,
	PRIMARY KEY (codFatura),
	FOREIGN KEY (codMovimento) REFERENCES Movimento(codMovimento)
);

/*CREATE GENERATOR gnFatura;

SET GENERATOR gnFatura TO 0;

SET TERM %;
CREATE TRIGGER Fatura_AI FOR Fatura ACTIVE
	BEFORE INSERT POSITION 0
	AS
		BEGIN
			NEW.codFatura = GEN_ID(gnFatura, 1);
		END %
SET TERM ;%*/
