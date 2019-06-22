package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByEmail(String email);
}
