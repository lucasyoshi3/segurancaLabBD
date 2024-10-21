package com.fatec.segurancaLabBd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.segurancaLabBd.model.ProjetoFramework;
import com.fatec.segurancaLabBd.repository.ProjetoFrameworkRepository;

@Controller
public class ProjetoFrameworkController {
	
	@Autowired
	ProjetoFrameworkRepository pfRepository;
	
	@RequestMapping(value = "/projetoframework", method = RequestMethod.GET)
	public ModelAndView pfGet() {
		ModelAndView mv = new ModelAndView("ProjetoFramework_form");
		mv.addObject("projetoFramework", new ProjetoFramework());
		
		return mv;
	}
	
	@RequestMapping(value = "/projetoframework", method = RequestMethod.POST)
	public ModelAndView pfPost(@ModelAttribute("projetoFramework")ProjetoFramework pf, @RequestParam("acao")String acao) {
		ModelAndView mv = new ModelAndView("ProjetoFramework_form");
		mv.addObject("projetoFramework", new ProjetoFramework());
		
		if(acao.equals("manter")) {
			pfRepository.save(pf);
		}
		
		return mv;
	}
}
