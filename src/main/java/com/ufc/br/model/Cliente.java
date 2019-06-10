package com.ufc.br.model;

import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario {
	private String endereco;

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
