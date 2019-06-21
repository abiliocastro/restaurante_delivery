package com.ufc.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Gerente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.IGerenteRepository;

@Service
public class GerenteService {
	@Autowired
	IGerenteRepository iGerenteRepository;
	
	@Autowired
	RoleServices roleServices;
	
	public void cadastrar(Gerente gerente) {
		gerente.setSenha(new BCryptPasswordEncoder().encode(gerente.getSenha()));
		Role roleGerente = roleServices.obterRole("ROLE_GERENTE");
		gerente.addRole(roleGerente);
		iGerenteRepository.save(gerente);
	}
	
	public List<Gerente> retornarGerentes(){
		return iGerenteRepository.findAll();
	}

	public void excluir(Long id) {
		iGerenteRepository.deleteById(id);
	}
	
	public Gerente buscarPorId(Long id) {
		return iGerenteRepository.getOne(id);
	}
}
