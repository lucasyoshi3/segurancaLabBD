package com.fatec.segurancaLabBd.model;

import org.hibernate.query.sqm.FetchClauseType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "especialidade")
@Data
@IdClass(EspecialidadeId.class)
public class Especialidade {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Desenvolvedor.class,
				fetch = FetchType.LAZY)
	@JoinColumn(name = "desenvolvedorid", referencedColumnName = "id", nullable = false)
	Desenvolvedor desenvolvedor;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Linguagem.class,
				fetch = FetchType.LAZY)
	@JoinColumn(name = "linguagemid", referencedColumnName = "id", nullable = false)
	Linguagem linguagem;
}
