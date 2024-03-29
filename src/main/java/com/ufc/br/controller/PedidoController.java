package com.ufc.br.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired 
	JavaMailSender mailSender;
	
	@RequestMapping("/adicionar/{id}")
	public ModelAndView adicionarItem(@PathVariable Long id) {
		Cliente cliente = this.obterClienteAtual();
		
		Pedido pedidoAtual = pedidoService.obterPedidoAtual(cliente);
		
		if(pedidoAtual != null) {
			pedidoService.atualizar(id, cliente);
		} else {
			pedidoService.cadastrar(id, cliente);
		}
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/atual")
	public ModelAndView pedidoAtual() {
		Cliente cliente = this.obterClienteAtual();
		
		Pedido pedido = pedidoService.obterPedidoAtual(cliente);
		
		ModelAndView mv = new ModelAndView("pedidoAtual");
		mv.addObject("pedido", pedido);
		return mv;
	}
	
	@RequestMapping("/deletar/{id}")
	public ModelAndView deletarItem(@PathVariable Long id) {
		Cliente cliente = this.obterClienteAtual();
		
		pedidoService.deletarItem(id, cliente);
		ModelAndView mv = new ModelAndView("redirect:/pedido/atual");
		return mv;
	}
	
	@RequestMapping("/finalizar")
	public ModelAndView finalizarPedido(@RequestParam(value = "enderecoEntrega") String enderecoEntrega) {
		Cliente cliente = this.obterClienteAtual();
		Pedido pedido = pedidoService.obterPedidoAtual(cliente);
		pedido.setEnderecoEntrega(enderecoEntrega);
		pedidoService.finalizar(pedido);
		this.enviarEmail();
		ModelAndView mv = new ModelAndView("redirect:/pedido/atual");
		return mv;
	}
	
	@RequestMapping("/finalizados")
	public ModelAndView finalizarPedido() {
		Cliente cliente = this.obterClienteAtual();
		List<Pedido> pedidosFinalizados = pedidoService.obterFinalizados(cliente);
		ModelAndView mv = new ModelAndView("pedidosFinalizados");
		mv.addObject("listaFinalizados", pedidosFinalizados);
		return mv;
	}
	
	private void enviarEmail() {
		try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo("castro.abilio@gmail.com");
            helper.setSubject("Confirmação de Pedido Finalizado");
            helper.setText("<p>Olá seu pedido foi confirmado e será enviado</p>", true);
            mailSender.send(mail);

            System.out.println("EMAIL: Enviado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EMAIL: Erro ao enviar e-mail");
        }
	}
	
	private Cliente obterClienteAtual() {
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;		
		Cliente cliente = clienteService.obterCliente(user.getUsername());
		return cliente;
	}
}
