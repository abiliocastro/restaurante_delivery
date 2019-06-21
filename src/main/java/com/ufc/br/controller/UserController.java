package com.ufc.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@RequestMapping("/logar")
	public ModelAndView login() {
	    ModelAndView mv = new ModelAndView("loginPagina");
	    return mv;
    }	
	
}
