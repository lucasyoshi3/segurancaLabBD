package com.fatec.segurancaLabBd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.segurancaLabBd.model.Framework;
import com.fatec.segurancaLabBd.repository.FrameworkRepository;

@Controller
public class FrameworkController {

    @Autowired
    FrameworkRepository frameworkRepository;

    List<Framework> lista = new ArrayList<>();

    @RequestMapping(value = "/framework", method = RequestMethod.GET)
    public ModelAndView frameworkGet() {
        ModelAndView mv = new ModelAndView("Framework_form");
        mv.addObject("framework", new Framework());
        mv.addObject("lista", lista); // Adicionando a lista ao GET, se necessário
        return mv;
    }

    @RequestMapping(value = "/framework", method = RequestMethod.POST)
    public ModelAndView frameworkPost(@ModelAttribute("framework") Framework framework, 
                                       @RequestParam("acao") String acao) {
        ModelAndView mv = new ModelAndView("Framework_form"); // Definindo a view de retorno
        String resposta = "";

        if (acao.equals("manter")) {
            frameworkRepository.save(framework);
        }

        if (acao.equals("buscarTodos")) {
            lista = (List<Framework>) frameworkRepository.findAll();
        }

//        if (acao.equals("atualizarVersao")) {
//            try {
//                frameworkRepository.updateVersaoById(framework.getVersao(), framework.getId());
//            } catch (DataAccessException ex) {
//                resposta = "Erro ao atualizar versão: " + ex.getMessage();
//            }
//        }

        mv.addObject("framework", new Framework()); // Resetar o formulário
        mv.addObject("lista", lista); // Atualizar a lista na view
        mv.addObject("resposta", resposta); // Passar a mensagem de resposta para a view

        return mv;
    }
}