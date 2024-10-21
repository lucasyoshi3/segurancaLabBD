package com.fatec.segurancaLabBd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.segurancaLabBd.model.Especialidade;
import com.fatec.segurancaLabBd.model.EspecialidadeId;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, EspecialidadeId> {
    
}

