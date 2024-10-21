package com.fatec.segurancaLabBd.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "projetoframework")
@Data
@IdClass(ProjetoFrameworkId.class)
public class ProjetoFramework {

	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Framework.class,
				fetch = FetchType.LAZY)
	@JoinColumn(name = "frameworkid", nullable = false)
	Framework framework;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Projeto.class,
				fetch = FetchType.LAZY)
	@JoinColumn(name = "projetoid", nullable = false)
	Projeto projeto;
}
