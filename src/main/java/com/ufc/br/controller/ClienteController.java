package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Cliente;
import com.ufc.br.services.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("cadastrarCliente");
		mv.addObject("cliente", new Cliente());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(@Validated Cliente cliente, BindingResult result) {
		ModelAndView mv = new ModelAndView("cadastrarCliente");
		if(result.hasErrors()) {
			return mv;
		}
		clienteService.cadastrar(cliente);
		mv.addObject("mensagem", "Cadastro realizado com sucesso");
		return mv;
	}

}
