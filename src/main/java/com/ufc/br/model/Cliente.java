package com.ufc.br.model;

import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
