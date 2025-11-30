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

    @PostMapping("/crear")
    public String crear(@ModelAttribute Baile baile) {
        baileRepo.save(baile);
        return "redirect:/baile/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        baileRepo.deleteById(id);
        return "redirect:/baile/list";
    }
}
