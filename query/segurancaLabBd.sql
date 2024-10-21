CREATE DATABASE segurancaLabBD
use segurancaLabBD

CREATE TABLE linguagem(
	id				INT,
	nome			VARCHAR(100)	NOT NULL,
	tipo			VARCHAR(20)		NOT NULL,
	ide				VARCHAR(100)	NOT NULL
	PRIMARY KEY(id)
)

CREATE TABLE framework(
	id				INT,
	nome			VARCHAR(100)	NOT NULL,
	versao			INT				NOT NULL,
	linguagemid		INT				NOT NULL
	PRIMARY KEY(id)
	FOREIGN KEY(linguagemid) REFERENCES linguagem(id)
)

CREATE TABLE projeto(
	id				INT,
	nome			VARCHAR(100)	NOT NULL,
	dataInicio		DATE			NOT NULL,
	qtdDiasEstimado	INT				NOT NULL,
	orcamento		DECIMAL(9,2)	NOT NULL,
	linguagemid		INT				NOT NULL
	PRIMARY KEY(id)
	FOREIGN KEY(linguagemid) REFERENCES linguagem(id)
)

CREATE TABLE projetoframework(
	frameworkid		INT				NOT NULL,
	projetoid		INT				NOT NULL
	PRIMARY KEY(frameworkid, projetoid)
	FOREIGN KEY(frameworkid) REFERENCES framework(id),
	FOREIGN KEY(projetoid) REFERENCES projeto(id)
)

CREATE TABLE desenvolvedor(
	id				INT,
	nome			VARCHAR(100)	NOT NULL,
	formacao		VARCHAR(100)	NOT NULL,
	senioridade		VARCHAR(6)		NOT NULL,
	projetoid		INT				NOT NULL,
	valorHora		DECIMAL(7,2)	NOT NULL
	PRIMARY KEY(id)
	FOREIGN KEY(projetoid) REFERENCES projeto(id)
)

CREATE TABLE especialidade(
	desenvolvedorid	INT				NOT NULL,
	linguagemid		INT				NOT NULL
	PRIMARY KEY(desenvolvedorid, linguagemid)
	FOREIGN KEY(desenvolvedorid) REFERENCES desenvolvedor(id),
	FOREIGN KEY(linguagemid) REFERENCES linguagem(id)
)

CREATE TRIGGER t_verifica_formacao_senioridade_desenvolvedor
ON desenvolvedor
INSTEAD OF INSERT, UPDATE
AS
BEGIN
   
    IF EXISTS (
        SELECT 1
        FROM INSERTED
        WHERE formacao NOT IN ('tecnico', 'superior')
    )
    BEGIN
        RAISERROR ('Formação inválida! A formação deve ser técnico ou superior.', 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END

    
    IF EXISTS (
        SELECT 1
        FROM INSERTED
        WHERE senioridade NOT IN ('junior', 'pleno', 'senior')
    )
    BEGIN
        RAISERROR ('Senioridade inválida! A senioridade deve ser júnior, pleno ou sênior.', 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END
    
    
    INSERT INTO desenvolvedor (id, nome, formacao, senioridade, projetoid, valorHora)
    SELECT id, nome, formacao, senioridade, projetoid, valorHora
    FROM INSERTED
END;


CREATE TRIGGER t_verifica_tipo_linguagem
ON linguagem
INSTEAD OF INSERT, UPDATE
AS
BEGIN
    IF EXISTS (
        SELECT 1
        FROM INSERTED
        WHERE tipo NOT IN ('Modular', 'OO', 'Funcional')
    )
    BEGIN
        RAISERROR ('Tipo de linguagem inválido! O tipo deve ser Modular, OO ou Funcional.', 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END

    INSERT INTO Linguagem (id, nome, tipo, ide)
    SELECT id, nome, tipo, ide
    FROM INSERTED
END

CREATE TRIGGER t_verifica_versao_framework
ON framework
INSTEAD OF UPDATE
AS
BEGIN
    IF EXISTS (
        SELECT 1
        FROM INSERTED i
        JOIN DELETED d ON i.id = d.id
        WHERE i.versao < d.versao
    )
    BEGIN
        RAISERROR ('Não é permitido downgrade de versão! Versão atual não pode ser menor que a anterior.', 16, 1)
        ROLLBACK TRANSACTION
        RETURN
    END
    
    UPDATE framework
    SET nome = i.nome, versao = i.versao, linguagemid = i.linguagemid
    FROM INSERTED i
    WHERE framework.id = i.id
END

CREATE FUNCTION f_qtd_projetos_atrasados()
RETURNS INT
AS
BEGIN
    DECLARE @qtd_atrasados INT;

    SELECT @qtd_atrasados = COUNT(*)
    FROM projeto
    WHERE DATEADD(DAY, qtdDiasEstimado, dataInicio) < GETDATE();

    RETURN @qtd_atrasados;
END;

INSERT INTO linguagem
VALUES 
(1, 'Python', 'Funcional', 'PyCharm'),
(2, 'Java', 'OO', 'IntelliJ IDEA'),
(3, 'JavaScript', 'Modular', 'VS Code');

INSERT INTO framework (id, nome, versao, linguagemid)
VALUES 
(1, 'Django', 4, 1),   
(2, 'Spring', 5, 2),    
(3, 'React', 17, 3);    

INSERT INTO projeto (id, nome, dataInicio, qtdDiasEstimado, orcamento, linguagemid)
VALUES 
(1, 'Sistema Web', '2024-01-10', 90, 50000.00, 1),  
(2, 'App Corporativo', '2024-02-01', 120, 120000.00, 2),  
(3, 'E-commerce', '2024-03-15', 60, 75000.00, 3);  

INSERT INTO projeto (id, nome, dataInicio, qtdDiasEstimado, orcamento, linguagemid)
VALUES 
(4, 'Portal Educacional', '2023-01-01', 180, 60000.00, 1), 
(5, 'Sistema de Saúde', '2023-06-01', 120, 90000.00, 2),  
(6, 'Plataforma de Vendas', '2023-05-01', 150, 80000.00, 3),  
(7, 'Aplicativo de Finanças', '2024-08-01', 90, 150000.00, 1); 

INSERT INTO projetoframework (frameworkid, projetoid)
VALUES 
(1, 1), 
(2, 2),  
(3, 3);  

INSERT INTO desenvolvedor (id, nome, formacao, senioridade, projetoid, valorHora)
VALUES 
(1, 'João Silva', 'superior', 'senior', 1, 150.00),  
(2, 'Maria Oliveira', 'tecnico', 'pleno', 2, 120.00),  
(3, 'Carlos Lima', 'superior', 'junior', 3, 100.00); 

INSERT INTO especialidade (desenvolvedorid, linguagemid)
VALUES 
(1, 1),  
(2, 2),  
(3, 3);  

SELECT dbo.f_qtd_projetos_atrasados()

UPDATE framework 
SET versao = 18
WHERE id=3