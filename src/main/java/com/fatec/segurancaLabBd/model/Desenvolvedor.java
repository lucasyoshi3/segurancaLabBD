package com.fatec.segurancaLabBd.model;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desenvolvedor")
@Getter
@Setter
@NoArgsConstructor
public class Desenvolvedor {

	@Id
	@Column(name = "id", columnDefinition = "INT", nullable=false)
	private int id;
	
	@Column(name = "nome", columnDefinition = "VARCHAR(100)", nullable = false)
	private String nome;
	
	@Column(name = "formacao", columnDefinition = "VARCHAR(100)", nullable = false)
	private String formacao;
	
	@Column(name = "serionidade", columnDefinition = "VARCHAR(6)", nullable = false)
	private String serionidade;
	
	@ManyToOne(targetEntity = Projeto.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "projetoid")
	private Projeto projeto;
}
