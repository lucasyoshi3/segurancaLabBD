package com.fatec.segurancaLabBd.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "linguagem")
@Getter
@Setter
@NoArgsConstructor
public class Linguagem {
	
	@Id
	@Column(name = "id", columnDefinition = "INT", nullable = false)
	private int id;
	
	@Column(name = "nome", columnDefinition = "VARCHAR(100)", nullable = false)
	private String nome;
	
	@Column(name = "tipo", columnDefinition = "VARCHAR(20)", nullable = false)
	private String tipo;
	
	@Column(name = "ide", columnDefinition = "VARCHAR(100)", nullable = false)
	private String ide;

}
