package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String nomeRole;
	
	@ManyToMany
	private List<Usuario> usuarios;
	
	public Role() {
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public Role(String nomeRole) {
		this.nomeRole = nomeRole;
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String getAuthority() {
		return this.nomeRole;
	}

}
