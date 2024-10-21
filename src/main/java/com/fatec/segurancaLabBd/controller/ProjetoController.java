package com.fatec.segurancaLabBd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fatec.segurancaLabBd.model.Projeto;
import com.fatec.segurancaLabBd.repository.ProjetoRepository;

@Controller
public class ProjetoController {

	@Autowired
	ProjetoRepository projetoRepository;
	
	List<Projeto> lista = new ArrayList<>();
	
	@RequestMapping(value = "/projeto", method = RequestMethod.GET)
	public ModelAndView projetoGet() {
		ModelAndView mv = new ModelAndView("Projeto_form");
		mv.addObject("projeto", new Projeto());
		
		return mv;
	}
	
	@RequestMapping(value = "/projeto", method = RequestMethod.POST)
	public ModelAndView projetoPost(@ModelAttribute("projeto")Projeto projeto, @RequestParam("acao")String acao) {
		ModelAndView mv = new ModelAndView("Projeto_form");
		
		if(acao.equals("manter")) {
			projetoRepository.save(projeto);
		}
		
		if(acao.equals("buscarTodos")) {
			lista = projetoRepository.findAll();
		}
		
		if(acao.equals("buscarNome")) {
			lista = projetoRepository.findByNome(projeto.getNome());
		}
		
		if(acao.equals("qtdAtrasados")) {
			int qtdProjetosAtrasados = projetoRepository.qtdProjetosAtrasados();
			mv.addObject("atrasado", qtdProjetosAtrasados);
	        }
		
		mv.addObject("projeto", new Projeto());
		mv.addObject("lista", lista);
		
		return mv;
	}
}
