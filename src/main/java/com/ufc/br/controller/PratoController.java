package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.services.PratoService;

@Controller
@RequestMapping("/prato")
public class PratoController {
	@Autowired
	PratoService pratoService;
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView("cadastrarPratos");
		mv.addObject("prato", new Prato());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(@Validated Prato prato, BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		ModelAndView mv = new ModelAndView("cadastrarPratos");
		if(result.hasErrors()) {
			return mv;
		}
		pratoService.cadastrar(prato, imagem);
		mv.addObject("mensagem", "Prato cadastrado com sucesso");
		return mv;
	}
	
	@RequestMapping("/listar")
	public ModelAndView listar() {
		List<Prato> pratos = pratoService.retornarPratos();
		ModelAndView mv = new ModelAndView("listarPratos");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/deletar/{id}")
	public ModelAndView delete(@PathVariable Long id) {
		pratoService.deletar(id);
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");
		return mv;
	}
	
	
	
}
