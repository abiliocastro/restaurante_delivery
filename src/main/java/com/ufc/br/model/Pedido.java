package com.ufc.br.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String status;
	private String enderecoEntrega;
	private float totalPedido;
	@ManyToOne
	private Cliente cliente;
	@ManyToMany(targetEntity = Prato.class)
	private Set<Prato> pratos;
	@OneToMany(targetEntity = Item.class)
	private Set<Item> itens;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}
	
	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public float getTotalPedido() {
		return totalPedido;
	}
	
	public void setTotalPedido(float totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Set<Prato> getPratos() {
		return pratos;
	}

	public void setPratos(Set<Prato> pratos) {
		this.pratos = pratos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
	
	
	
}
