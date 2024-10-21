package com.fatec.segurancaLabBd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatec.segurancaLabBd.model.Linguagem;

@Repository
public interface LinguagemRepository extends CrudRepository<Linguagem, Integer>{

	@Query(value = "SELECT l FROM Linguagem l WHERE l.tipo = :tipo", nativeQuery = true)
	List<Linguagem> findByTipo(@Param("tipo") String tipo);
	
	@Query(value = "SELECT l FROM Linguagem l WHERE l.ide = :ide", nativeQuery = true)
	List<Linguagem> findByIde(@Param("ide") String ide);
}
