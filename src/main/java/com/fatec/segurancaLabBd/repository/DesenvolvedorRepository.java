package com.fatec.segurancaLabBd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatec.segurancaLabBd.model.Desenvolvedor;

@Repository
public interface DesenvolvedorRepository extends CrudRepository<Desenvolvedor, Integer> {
	
	@Query(value = "SELECT d FROM Desenvolvedor d WHERE d.serionidade = :serionidade", nativeQuery = true)
	List<Desenvolvedor> findBySerionidade(@Param("serionidade") String serionidade);
	
	@Query(value = "SELECT d FROM Desenvolvedor d WHERE d.formacao = :formacao", nativeQuery = true)
	List<Desenvolvedor> findByFormacao(@Param("formacao") String formacao);
}
