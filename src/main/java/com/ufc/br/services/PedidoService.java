package com.ufc.br.services;

import java.util.Set;

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
	
	// Quando um pedido aberto já existe
	public void atualizar(Long idPrato, Cliente cliente) {
		Pedido pedido = iPedidoRepository.findByStatusAndCliente("aberto", cliente);
		Set<Item> itens =  pedido.getItens();
		boolean existe = false;
		for (Item item : itens) {
			if(item.getPrato().getId() == idPrato) {
				int qtdIncrementada = item.getQuantidade() + 1;
				item.setQuantidade(qtdIncrementada);
				itemService.cadastrar(item);
				existe = true;
				break;
			}
		}
		if(!existe) { // Quando o prato pedido ainda não está no pedido
			Prato prato = pratoService.obterPrato(idPrato);
			Item item = new Item(prato);
			item.setPedido(pedido);
			itemService.cadastrar(item);
		}
		
	}
	
	public void deletarItem(Long id, Cliente cliente) {
		Pedido pedido = iPedidoRepository.findByStatusAndCliente("aberto", cliente);
		Set<Item> itens =  pedido.getItens();
		for (Item item : itens) {
			if(item.getId() == id) {
				itens.remove(item);
				break;
			}
		}
		pedido.setItens(itens);
		itemService.deletar(id);
		iPedidoRepository.save(pedido);
	}

	public void finalizar(Pedido pedido) {
		pedido.setStatus("finalizado");
		pedido.atualizaTotal();
		iPedidoRepository.save(pedido);		
	}
	
}
