package com.fatec.segurancaLabBd.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class EspecialidadeId implements Serializable{

	private static final long serialVersionUID = 1L;
	private Desenvolvedor desenvolvedor;
	private Linguagem linguagem;
}
