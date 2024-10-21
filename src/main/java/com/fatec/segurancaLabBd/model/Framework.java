package com.fatec.segurancaLabBd.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "framework")
@Getter
@Setter
@NoArgsConstructor
public class Framework {

	@Id
	@Column(name = "id", columnDefinition = "INT", nullable = false)
	private int id;
	
	@Column(name = "nome", columnDefinition = "VARCHAR(100)", nullable = false)
	private String nome;
	
	@Column(name = "versao", columnDefinition = "INT", nullable = false)
	private int versao;
	
	@ManyToOne(targetEntity = Linguagem.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "linguagemid")
	private Linguagem linguagem;
}
