package com.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int quantidade;
	
	@ManyToOne(targetEntity = Pedido.class)
	private Pedido pedido;
	
	@OneToOne
	private Prato prato;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
