package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.club.club.model.Conjunto;
import com.club.club.repository.ConjuntoRepository;

@Controller
@RequestMapping("/conjunto")
public class ConjuntoController {

    @Autowired
    private ConjuntoRepository conjuntoRepo;

    // Redirección para evitar error 404 en /conjunto
    @GetMapping("")
    public String index() {
        return "redirect:/conjunto/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("conjuntos", conjuntoRepo.findAll());
        return "conjunto/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("conjunto", new Conjunto());
        return "conjunto/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Conjunto conjunto = conjuntoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("conjunto", conjunto);
        return "conjunto/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Conjunto conjunto) {
        conjuntoRepo.save(conjunto);
        return "redirect:/conjunto/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        conjuntoRepo.deleteById(id);
        return "redirect:/conjunto/list";
    }
}