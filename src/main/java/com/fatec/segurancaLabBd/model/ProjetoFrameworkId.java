package com.fatec.segurancaLabBd.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProjetoFrameworkId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Framework framework;
	private Projeto projeto;
}
