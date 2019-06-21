package com.ufc.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Role;
import com.ufc.br.repository.IClienteRepository;

@Service
public class ClienteService {
	@Autowired
	IClienteRepository iClienteRepository;
	
	@Autowired
	RoleServices roleService;
	
	public void cadastrar(Cliente cliente) {
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		Role roleCliente = roleService.obterRole("ROLE_CLIENTE");
		cliente.addRole(roleCliente);
		iClienteRepository.save(cliente);
	}
}
