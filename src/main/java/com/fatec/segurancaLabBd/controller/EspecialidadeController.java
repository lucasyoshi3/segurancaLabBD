package com.fatec.segurancaLabBd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.segurancaLabBd.model.Especialidade;
import com.fatec.segurancaLabBd.repository.DesenvolvedorRepository;
import com.fatec.segurancaLabBd.repository.EspecialidadeRepository;
import com.fatec.segurancaLabBd.repository.LinguagemRepository;

@Controller
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private DesenvolvedorRepository desenvolvedorRepository;

    @Autowired
    private LinguagemRepository linguagemRepository;

    @RequestMapping(value = "/especialidade", method = RequestMethod.GET)
    public ModelAndView especialidadeGet() {
        ModelAndView mv = new ModelAndView("Especialidade_form");
        mv.addObject("especialidade", new Especialidade());

        return mv;
    }

    @RequestMapping(value = "/especialidade", method = RequestMethod.POST)
    public ModelAndView especialidadePost(@ModelAttribute("especialidade") Especialidade especialidade, 
                                          @RequestParam("acao") String acao) {
        ModelAndView mv = new ModelAndView("Especialidade_form");
        mv.addObject("especialidade", new Especialidade());

        if (acao.equals("manter")) {
            especialidadeRepository.save(especialidade);
        }

        return mv;
    }
}
