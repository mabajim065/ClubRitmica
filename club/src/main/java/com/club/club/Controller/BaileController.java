package com.club.club.Controller;

import com.club.club.model.Baile;
import com.club.club.repository.BaileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/baile")
public class BaileController {

    @Autowired
    private BaileRepository baileRepo;

    // Redirección automática si entran a /baile
    @GetMapping("")
    public String index() {
        return "redirect:/baile/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("bailes", baileRepo.findAll());
        return "baile/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("baile", new Baile());
        return "baile/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Baile baile = baileRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("baile", baile);
        return "baile/editar";
    }

    // Estandarizado a "crear" para coincidir con tus otros controladores
    @PostMapping("/crear")
    public String crear(@ModelAttribute Baile baile) {
        baileRepo.save(baile);
        return "redirect:/baile/list";
    }

    // Estandarizado a "eliminar" para coincidir con tus otros controladores
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        baileRepo.deleteById(id);
        return "redirect:/baile/list";
    }
}