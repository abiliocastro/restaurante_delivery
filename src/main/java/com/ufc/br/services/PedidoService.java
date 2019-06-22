package com.ufc.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Item;
import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;
import com.ufc.br.repository.IPedidoRepository;

@Service
public class PedidoService {
	@Autowired
	IPedidoRepository iPedidoRepository;
	
	@Autowired
	PratoService pratoService;
	
	@Autowired 
	ItemService itemService;
	
	public void cadastrar(Pedido pedido) {
		iPedidoRepository.save(pedido);
	}

	public void cadastrar(Long idPrato, Cliente cliente) {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus("aberto");
		iPedidoRepository.save(pedido);
		
		Prato prato = pratoService.obterPrato(idPrato);
		Item item = new Item(prato);
		item.setPedido(pedido);
		itemService.cadastrar(item);	
	}
	
	@Transactional
	public Pedido obterPedidoAtual(Cliente cliente) {
		return iPedidoRepository.findByStatusAndCliente("aberto", cliente);
	}
	
}
