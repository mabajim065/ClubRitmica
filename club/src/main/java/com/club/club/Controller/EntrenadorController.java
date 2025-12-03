package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.club.club.model.Entrenador;
import com.club.club.repository.EntrenadorRepository;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepo;

    // Redirección automática
    @GetMapping("")
    public String index() {
        return "redirect:/entrenador/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "entrenador/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Entrenador entrenador = entrenadorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("entrenador", entrenador);
        return "entrenador/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Entrenador entrenador) {
        entrenadorRepo.save(entrenador);
        return "redirect:/entrenador/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        entrenadorRepo.deleteById(id);
        return "redirect:/entrenador/list";
    }
}