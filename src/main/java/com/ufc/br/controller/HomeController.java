package com.ufc.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;
import com.ufc.br.services.PratoService;

@Controller
public class HomeController {
	@Autowired
	PratoService pratoService;
	
	
	@RequestMapping("/")
	public ModelAndView showHome() {
		List<Prato> pratos = pratoService.retornarPratos();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/pratos")
	public ModelAndView showPratos() {
		List<Prato> pratos = pratoService.retornarPratos();
		ModelAndView mv = new ModelAndView("pratos");
		mv.addObject("listaPratos", pratos);
		return mv;
	}
	
	@RequestMapping("/contato")
	public ModelAndView showContato() {
		ModelAndView mv = new ModelAndView("contato");
		return mv;
	}
	
	@RequestMapping("/sobre")
	public ModelAndView showSobre() {
		ModelAndView mv = new ModelAndView("sobre");
		return mv;
	}
}
