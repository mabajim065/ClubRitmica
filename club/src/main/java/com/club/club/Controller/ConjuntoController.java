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

    @PostMapping("/crear")
    public String crear(@ModelAttribute Conjunto conjunto) {
        conjuntoRepo.save(conjunto);
        return "redirect:/conjunto/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        conjuntoRepo.deleteById(id);
        return "redirect:/conjunto/list";
    }
}
