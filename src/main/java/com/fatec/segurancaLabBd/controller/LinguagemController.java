package com.fatec.segurancaLabBd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.segurancaLabBd.model.Linguagem;
import com.fatec.segurancaLabBd.repository.LinguagemRepository;

@Controller
public class LinguagemController {

	@Autowired
	private LinguagemRepository linguagemRepository;
	
	private List<Linguagem> lista;
	
	@RequestMapping(value = "/linguagem", method = RequestMethod.GET)
	public ModelAndView linguagemManterGet() {
		ModelAndView mv = new ModelAndView("Linguagem_form");
		mv.addObject("linguagem", new Linguagem());
		mv.addObject("resposta", null);
		
		return new ModelAndView("Linguagem_form");
	}
	
	@RequestMapping(value = "/linguagem", method = RequestMethod.POST)
	public ModelAndView linguagemManterPost(@ModelAttribute("linguagem") Linguagem linguagem, @RequestParam("acao") String acao) {
		ModelAndView mv = new ModelAndView("Linguagem_form");
		String resposta = "";
		
		if(acao.equals("manter")) {
			try {
				linguagemRepository.save(linguagem);
				resposta = "Mantido com sucesso";
			} catch (DataAccessException ex) {
				// TODO: handle exception
				resposta = ex.getMessage();
			}
		}
		
		if(acao.equals("buscarTipo")) {
			lista = linguagemRepository.findByTipo(linguagem.getTipo());
		}
		
		if(acao.equals("buscarIde")) {
			lista = linguagemRepository.findByIde(linguagem.getIde());
		}
		
		mv.addObject("linguagem", new Linguagem());
		mv.addObject("resposta",resposta);
		return mv;
	}
}
