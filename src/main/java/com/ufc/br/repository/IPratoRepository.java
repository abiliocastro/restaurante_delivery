package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Prato;

public interface IPratoRepository extends JpaRepository<Prato, Long> {

}
