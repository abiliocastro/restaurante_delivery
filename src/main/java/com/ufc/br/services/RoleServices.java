package com.ufc.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Role;
import com.ufc.br.repository.IRoleRepository;

@Service
public class RoleServices {
	@Autowired
	IRoleRepository iRoleRepository;
	
	public void cadastrar(Role role) {
		iRoleRepository.save(role);
	}
		
	public Role obterRole(String roleID) {
		return iRoleRepository.getOne(roleID);
	}

}
