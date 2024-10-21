package com.fatec.segurancaLabBd.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatec.segurancaLabBd.model.Framework;
import com.fatec.segurancaLabBd.model.Projeto;
import com.fatec.segurancaLabBd.model.ProjetoFramework;
import com.fatec.segurancaLabBd.model.ProjetoFrameworkId;

@Repository
public interface ProjetoFrameworkRepository extends JpaRepository<ProjetoFramework, ProjetoFrameworkId> {

//    @Query("SELECT pf.framework FROM ProjetoFramework pf WHERE pf.projeto.id = :projetoId")
//    List<Framework> findFrameworksByProjetoId(@Param("projetoId") int projetoId);
//
//    @Query("SELECT pf.projeto FROM ProjetoFramework pf WHERE pf.framework.id = :frameworkId")
//    List<Projeto> findProjetosByFrameworkId(@Param("frameworkId") int frameworkId);
}
