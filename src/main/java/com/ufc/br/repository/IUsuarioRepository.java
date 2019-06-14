package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
