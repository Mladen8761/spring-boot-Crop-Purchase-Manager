package com.Mladen.otkupproizvoda.controller;

import com.Mladen.otkupproizvoda.entity.Operant;
import com.Mladen.otkupproizvoda.service.OperantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/operant")
public class OperantController {
    OperantService operantService;

    @Autowired
    public OperantController(OperantService operantService) {
        this.operantService = operantService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder)
    {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("")
    public String home()
    {
        return "home";
    }
    @GetMapping("/form")
    public String operantForm(Model model)
    {
        model.addAttribute("operant",new Operant());
        return "operant-form";
    }
    @GetMapping("/zaduzivanjeForm")
    public String zaduzivanjeForm(Model model)
    {
        model.addAttribute("operant",new Operant());
        List<Operant> operants=operantService.findAll();
        model.addAttribute("operants",operants);
        return "zaduzivanje-form";
    }
    @GetMapping("/razduzivanjeForm")
    public String razduzivanjeForm(Model model)
    {
        model.addAttribute("operant",new Operant());
        List<Operant> operants=operantService.findAll();
        model.addAttribute("operants",operants);
        return "razduzivanje-form";
    }

    @PostMapping("/updateZaduzivanje")
    public String zaduziOperant(@Valid @ModelAttribute("operant") Operant operant,BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("operants",operantService.findAll());
            return "zaduzivanje-form";
        }
        Operant tempOperant = operantService.findById(operant.getId());
            operant.setBrojZaduzenihKutija(tempOperant.getBrojZaduzenihKutija()+operant.getBrojZaduzenihKutija());
            operant.setBrojZaduzenihPosuda(tempOperant.getBrojZaduzenihPosuda()+operant.getBrojZaduzenihPosuda());
            operant.setFullName(tempOperant.getFullName());

        operantService.save(operant);
        return "redirect:/operant";
    }
    @PostMapping("/updateRazduzivanje")
    public String RazduziOperant(@Valid @ModelAttribute("operant") Operant operant, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("operants",operantService.findAll());
            return "razduzivanje-form";
        }
        Operant tempOperant = operantService.findById(operant.getId());
        operant.setBrojZaduzenihKutija(tempOperant.getBrojZaduzenihKutija()-operant.getBrojZaduzenihKutija());
        operant.setBrojZaduzenihPosuda(tempOperant.getBrojZaduzenihPosuda()-operant.getBrojZaduzenihPosuda());
        operant.setFullName(tempOperant.getFullName());
        operantService.save(operant);
        return "redirect:/operant";
    }
    @PostMapping("/save")
    public String saveOperant(@Valid @ModelAttribute("operant") Operant operant, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "operant-form";
        }
        operantService.save(operant);
        return "redirect:/operant";
    }
}
