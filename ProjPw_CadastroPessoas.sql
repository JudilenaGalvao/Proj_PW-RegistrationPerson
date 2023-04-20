--Cadastro de pessoas para emprego de secretario(a).


CREATE TABLE vagas_pessoas(
	id SERIAL,
	nome TEXT,
	idade INT2,
	telefone TEXT,
	email TEXT,
	arqv_curriculo TEXT
);

SELECT * FROM vagas_pessoas;

DROP TABLE vagas_pessoas;

