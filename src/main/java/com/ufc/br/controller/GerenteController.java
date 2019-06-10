package com.ufc.br.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Gerente;
import com.ufc.br.service.GerenteService;

@Controller
@RequestMapping("/gerente")
public class GerenteController {
	@Autowired
	GerenteService gerenteService;
	
	@RequestMapping("/cadastrar")
	public ModelAndView cadastrar() {
		Gerente abilio = new Gerente();
		abilio.setCpf("088.888.888-47");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = format.parse("01/06/1993");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		abilio.setDataNascimento(date);
		abilio.setEmail("a@a.com");
		abilio.setNome("Abilio");
		abilio.setSenha("calango123");
		gerenteService.cadastrar(abilio);
		ModelAndView mv = new ModelAndView("cadastrarGerente");
		return mv;
	}

}
