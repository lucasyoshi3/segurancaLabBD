package com.fatec.segurancaLabBd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
public class Projeto {

	@Id
	@Column(name = "id", columnDefinition = "INT", nullable = false)
	private int id;
	
	@Column(name = "nome", columnDefinition = "VARCHAR(100)", nullable = false)
	private String nome;
	
	@Column(name = "dataInicio", columnDefinition = "DATE", nullable = false)
	private LocalDate dataInicio;
	
	@Column(name = "qtdDiasEstimado", columnDefinition = "INT", nullable = false)
	private int qtdDiasEstimado;
	
	@Column(name = "orcamento", columnDefinition = "DECIMAL(9,2)", nullable = false)
	private Double orcamento;
	
	@ManyToOne
    @JoinColumn(name = "linguagemid", nullable = false)
    private Linguagem linguagem;
}
