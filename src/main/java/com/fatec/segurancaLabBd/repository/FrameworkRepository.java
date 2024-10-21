package com.fatec.segurancaLabBd.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatec.segurancaLabBd.model.Framework;

import jakarta.transaction.Transactional;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, Integer> {

	@Query(value = "SELECT f FROM Framework f "
		      + "INNER JOIN f.linguagem l "
		      + "WHERE l.nome LIKE CONCAT('%', :nome, '%')", nativeQuery = true)
	List<Framework> findByLinguagemNome(@Param("nome") String nome);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Framework f SET f.versao = :versao WHERE f.id = :id", nativeQuery = true)
	void updateVersaoById(@Param("versao")int versao, @Param("id")int id);
}
