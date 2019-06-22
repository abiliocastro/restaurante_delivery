package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.model.Pedido;
import com.ufc.br.services.ClienteService;
import com.ufc.br.services.PedidoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping("/adicionar/{id}")
	public ModelAndView adicionarItem(@PathVariable Long id) {
	
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;		
		Cliente cliente = clienteService.obterCliente(user.getUsername());
		
		pedidoService.cadastrar(id, cliente);
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/atual")
	public ModelAndView pedidoAtual() {
	
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;		
		Cliente cliente = clienteService.obterCliente(user.getUsername());
		
		Pedido pedido = pedidoService.obterPedidoAtual(cliente);
		
		ModelAndView mv = new ModelAndView("pedidoAtual");
		mv.addObject("pedido", pedido);
		return mv;
	}
}
