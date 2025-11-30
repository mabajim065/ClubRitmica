package com.club.club.Controller;

import com.club.club.model.Aparato;
import com.club.club.repository.AparatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aparato")
public class AparatoController {

    @Autowired
    private AparatoRepository aparatoRepo;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("aparatos", aparatoRepo.findAll());
        return "aparato/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("aparato", new Aparato());
        return "aparato/nuevo";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Aparato aparato) {
        aparatoRepo.save(aparato);
        return "redirect:/aparato/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        aparatoRepo.deleteById(id);
        return "redirect:/aparato/list";
    }
}
