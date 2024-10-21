package com.fatec.segurancaLabBd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.segurancaLabBd.model.Desenvolvedor;
import com.fatec.segurancaLabBd.repository.DesenvolvedorRepository;

@Controller
public class DesenvolvedorController {
	
	private List<Desenvolvedor> lista = new ArrayList<>();
	private DesenvolvedorRepository desenvolvedorRepository;
	
	@GetMapping("/desenvolvedor/manter")
	public ModelAndView desenvolvedor(){
		ModelAndView mv = new ModelAndView("desenvolvedor_form");
		mv.addObject("desenvolvedor", new Desenvolvedor());
		
		return mv;
	}
	
	@PostMapping("/desenvolvedor/manter")
	public ModelAndView desenvolvedorCreate(@ModelAttribute("desenvolvedor") Desenvolvedor desenvolvedor, @RequestParam("acao") String acao) {
		ModelAndView mv = new ModelAndView("desenvolvedor_form");
		String resposta = "Mantido com sucesso!";
		if(acao.equals("manter")) {
			try {
				desenvolvedorRepository.save(desenvolvedor);
			} catch (DataAccessException ex) {
				// TODO: handle exception
				resposta = ex.getMessage();
			}
		}
		
		mv.addObject("desenvolvedor", new Desenvolvedor());
		mv.addObject("resposta", resposta);
		return mv;
	}
	
	@PostMapping("/desenvolvedor/buscar")
	public ModelAndView desenvolvedorRead(@ModelAttribute("desenvolvedor") Desenvolvedor desenvolvedor, @RequestParam("acao") String acao) {
		ModelAndView mv = new ModelAndView("desenvolvedorBusca_form");
		
		if(acao.equals("buscaSenioridade")) {
			lista = desenvolvedorRepository.findBySerionidade(desenvolvedor.getSerionidade());
		}
		
		if(acao.equals("buscaFormacao")) {
			lista = desenvolvedorRepository.findByFormacao(desenvolvedor.getFormacao());
		}
		
		mv.addObject("desenvolvedor", new Desenvolvedor());
		mv.addObject("lista",lista);
		
		return mv;
	}
}
