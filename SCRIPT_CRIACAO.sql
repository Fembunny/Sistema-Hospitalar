DROP DATABASE hospitalPI;
CREATE DATABASE HospitalPI;
USE HospitalPI;

/*CREATE TABLE IF NOT EXISTS Especialidade(
    id INT NOT NULL AUTO_INCREMENT,
    nome ENUM('PEDIATRIA', 'CLINICA GERAL', 'GASTROENTEROLOGIA', 'DERMATOLOGIA') NOT NULL UNIQUE,
	PRIMARY KEY (id))
ENGINE = InnoDB;*/

CREATE TABLE IF NOT EXISTS Medico(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
	data_nasc DATE NOT NULL,
	telefone VARCHAR(15) NOT NULL,
    endereco VARCHAR(90) NOT NULL,
    crm CHAR(5) NOT NULL UNIQUE,
    especialidade VARCHAR(50) NOT NULL,
    PRIMARY KEY (id))
ENGINE = InnoDB;

/*CREATE TABLE IF NOT EXISTS Medico_Especialidade(
    idMedico INT NOT NULL,
    idEspecialidade INT NOT NULL,
    PRIMARY KEY (idMedico, idEspecialidade),
    FOREIGN KEY (idMedico) REFERENCES Medico(id),
    FOREIGN KEY (idEspecialidade) REFERENCES Especialidade(id))
ENGINE = InnoDB;*/

CREATE TABLE IF NOT EXISTS Paciente(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    data_nasc DATE NOT NULL,
    endereco VARCHAR(90) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Consulta(
	id INT NOT NULL AUTO_INCREMENT,
    idMedico INT NOT NULL,
    medicoEspecialidade VARCHAR(50) NOT NULL,
    idPaciente INT NOT NULL,
    data_hora DATE NOT NULL,
    valor DOUBLE NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (idMedico) REFERENCES Medico(id),
    FOREIGN KEY (idPaciente) REFERENCES Paciente(id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Enfermeiro(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(90) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    data_nasc DATE NOT NULL,
    telefone VARCHAR(15) NOT NULL,
	endereco VARCHAR(90) NOT NULL,
    cre VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Remedio(
	id INT NOT NULL AUTO_INCREMENT,
    classe VARCHAR(90) NOT NULL,
    nome VARCHAR(90) NOT NULL UNIQUE,
    quantidade INT NOT NULL,
    valor DOUBLE NOT NULL,
    PRIMARY KEY (id))
ENGINE = InnoDB;

INSERT INTO Medico (nome, data_nasc, cpf, crm, telefone, endereco, especialidade) VALUES
	('Betina Sabrina Aline Pereira','1985-2-9', '887.690.466-20','09616', '(31) 98481-5703', 'Rua Di Cavalcante, 781', 'GASTROENTEROLOGIA'),
	('Lúcia Kamilly Vera Caldeira','1997-3-10','031.104.342-96','36688','(68) 98618-1988', 'Rua Gabrielly, 348', 'DERMATOLOGIA'),
	('Marcos Vinicius Joaquim Vicente Souza','1988-7-24', '540.378.937-54','04735', '(84) 99755-7733', 'Rua Cileno Silva, 182', 'PEDIATRIA'),
	('Renato Danilo Ryan Oliveira','1983-4-1', '930.343.045-01', '81863','(65) 98330-6429', 'Rua dos Peixes, 475', 'PEDIATRIA'),
	('Beatriz Jéssica Isabela da Silva','1992-4-26', '743.224.485-50', '52155','(13) 98185-5867', 'Rua Carijós, 238', 'CLINICA GERAL'),
	('Anderson Thales Lorenzo Campos','1977-10-4', '005.584.699-81', '63475', '(82) 98457-6186', 'Rua Vandeci dos Santos Figueiredo, 532', 'DERMATOLOGIA'),
	('Bianca Cecília da Luz','1997-4-17', '049.870.396-72', '75531', '(65) 99177-0360', 'Rua Dois, 983', 'CLINICA GERAL'),
	('Isaac Iago Nunes','1973-3-24', '398.649.511-81', '45218','(67) 98297-8350', 'Rua Uberlândia, 365', 'DERMATOLOGIA'),
	('João Marcos Antonio Barbosa','1977-7-16', '573.700.932-71', '99243','(96) 98267-2096','Rua Rio Calçoene, 878', 'GASTROENTEROLOGIA'),
	('Antonella Clarice Camila Almeida','1984-9-10', '959.195.986-97', '61505','(34) 99950-7666', 'Rua do Forró, 854', 'CLINICA GERAL');
	
INSERT INTO Paciente (nome, cpf, data_nasc, endereco, telefone) VALUES 
	('Benjamin Noah Cauã Gomes','869.251.930-84','1997-04-11','Rua José Reolon, 425','(54) 99122-2552'),
	('Milena Sebastiana Catarina Moura','501.752.520-33','1977-03-08','Rua Coronel Madeira, 629','(92) 99840-4737'),
	('Caio Mateus Fernandes','982.194.500-70','2001-02-19','Avenida São Jorge, 568','(71) 99505-4332'),
	('Raimundo Marcos Aragão','895.912.620-94','2017-07-08','Servidão João Manoel Fernandes, 372','(51) 98490-6960'),
	('Tatiane Tereza Lopes','249.042.840-38','1957-12-01','Rua Cumarú, 573','(21) 99730-4768'),
	('Yuri Luiz Marcos Oliveira','177.587.270-00','1983-07-16','Avenida das Flores, 635','(81) 98486-6719'),
	('Andrea Lívia da Mota','825.069.090-79','1983-09-04','Rua João Manoel Teixeira, 653','(45) 98170-0970'),
	('Pedro Henrique Augusto Bruno Campos','968.718.190-70','2021-08-28','Rua Doutor João Bezerra, 728','(88) 98297-5546'),
	('Jaqueline Antônia Dias','636.399.679-17','1978-11-29','Rua Viegas, 446','(63) 99897-6278'),
	('Luiz Cauê Silva','467.537.480-83','2018-03-14','1ª Travessa Lourival Braga, 900','(71) 99519-0478');
    
INSERT INTO Consulta (idMedico,medicoEspecialidade,idPaciente,data_hora, valor) VALUES
	(1,'GASTROENTEROLOGIA',10,'2021-12-13',100.00),
	(2,'DERMATOLOGIA',5,'2021-10-03',120.00),
	(3,'PEDIATRIA',8,'2021-07-13',150.00),
	(9,'GASTROENTEROLOGIA',4,'2021-03-27',130.000),
	(8,'DERMATOLOGIA',2,'2020-09-10',130.00),
	(1,'GASTROENTEROLOGIA',10,'2020-03-14',120.00),
	(4,'PEDIATRIA',6,'2019-12-03',120.00),
	(9,'GASTROENTEROLOGIA',1,'2019-09-06',150.00),
	(3,'PEDIATRIA',10,'2019-08-07',150.00),
	(8,'DERMATOLOGIA',8,'2019-06-09',120.00);
    
INSERT INTO Remedio (classe, nome, quantidade, valor) VALUES 
	('RELAXANTE MUSCULAR','DORFLEX', 250, 6.50),
	('ANALGESICO','NOVALGINA', 150, 23.00),
	('ANALGESICO, RELAXANTE MUSCULAR','TORSILAX', 350, 17.50),
	('ANTIARRITMICO','AMIODARONA', 80, 25.00),
	('ANTIINFLAMATORIO','DIPIRONA', 90, 14.50),
	('ANTIARRITMICO, ANALGESICO','LIDOCAINA', 120, 9.00),
	('ANTIDIABETICO','INSULINA', 1000, 200.00),
	('DIURETICO','HIDROCLOROTIAZIDA', 402, 3.00),
	('ANTI-INFLAMATORIO','CIMEGRIPE', 100, 10.00),
	('ANTIANEMICO','DEXFER', 120, 50.00);

INSERT INTO Enfermeiro (nome, cpf, data_nasc, endereco, telefone, cre) VALUES 
	('Guilherme Pierre Silva Corsino','75396087145','2004-08-22', 'Rua Santa Luzia, 299','(82) 3539-1539','14803'),
	('Gustavo Marcos Vinicius Drumond','74163588680','2006-06-09' ,'Quadra EQNM 17/19 Bloco E','(61) 3822-4751','73782'),
	('Eduarda Sônia Lívia Peixoto','39814112267','1998-07-21' ,'Rua Santos Dumont, 185','(24) 3903-1910','85467'),
	('Benício Gabriel Mário Fogaça','16840652327','1989-01-04','Rua Monteiro Lobato, 673','(83) 3814-1592','01854'),
	('Calebe Leonardo Silva','13785996241','1993-09-10','Rua Florianópolis, 724','(92) 3791-7611','18927'),
	('Nathan Marcos Vinicius Heitor Baptista','16974093004','1995-10-20' ,'Rua Doutor Abelardo Gomes Calheiros, 826','(82) 3944-6607','23212'),
	('Roberto Thomas da Cruz','40744484758','2001-12-25' ,'Rua Félix de Albuquerque, 236','(67) 2774-7185','86701'),
	('Cauê Isaac Bernardes','28157135273','2004-01-01','Rua Marechal Rondon, 139','(94) 2757-2013','17828'),
	('Julio Eduardo Monteiro','28854194310','2000-10-05','Rua Kuweit, 451','(77) 3648-6881','88731'),
	('Kaique Pietro Anthony Assis','73618370261','1994-03-15','Rua Afonso Dias Guimarães, 786','(88) 2907-0638','49344');


-- SELECT * FROM Especialidade;
SELECT * FROM Medico;
SELECT * FROM Paciente;
SELECT * FROM Consulta;
SELECT * FROM Enfermeiro;
SELECT * FROM Remedio;

