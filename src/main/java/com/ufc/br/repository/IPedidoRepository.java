package com.ufc.br.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
	Pedido findByStatusAndCliente(String status, Cliente cliente);
	List<Pedido> findAllByStatusAndCliente(String status, Cliente cliente);
}
