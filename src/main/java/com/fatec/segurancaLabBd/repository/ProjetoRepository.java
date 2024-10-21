package com.fatec.segurancaLabBd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.fatec.segurancaLabBd.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{
	
	@Query(value = "SELECT p FROM Projeto p WHERE p.nome LIKE %:nome%", nativeQuery = true)
	List<Projeto> findByNome(@Param("nome") String nome);
	
	@Query(value = "SELECT f_qtd_projetos_atrasados()", nativeQuery = true)
	public int qtdProjetosAtrasados();
}
