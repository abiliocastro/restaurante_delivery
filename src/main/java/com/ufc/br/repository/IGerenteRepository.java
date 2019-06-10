package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Gerente;

public interface IGerenteRepository extends JpaRepository<Gerente, Long> {

}
