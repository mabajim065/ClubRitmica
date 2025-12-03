package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.club.club.model.Competicion;
import com.club.club.repository.CompeticionRepository;

@Controller
@RequestMapping("/competicion")
public class CompeticionController {

    @Autowired
    private CompeticionRepository competicionRepo;

    @GetMapping("")
    public String index() {
        return "redirect:/competicion/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "competicion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Competicion competicion = competicionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("competicion", competicion);
        return "competicion/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Competicion competicion) {
        competicionRepo.save(competicion);
        return "redirect:/competicion/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        competicionRepo.deleteById(id);
        return "redirect:/competicion/list";
    }
}