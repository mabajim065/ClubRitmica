package com.club.club.Controller;

import com.club.club.model.Aparato;
import com.club.club.repository.AparatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
// Unificamos el mapeo base a /aparatos (plural) para que coincida con las rutas HTML
@RequestMapping("/aparatos") 
public class AparatoController {

    @Autowired
    private AparatoRepository aparatoRepo;
    // Eliminamos la inyección de BaileRepository

    @GetMapping({"", "/lista"}) // Maneja /aparatos y /aparatos/lista
    public String list(Model model) {
        model.addAttribute("aparatos", aparatoRepo.findAll());
        return "aparato/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("aparato", new Aparato());
        // Eliminamos model.addAttribute("bailes", ...)
        return "aparato/nuevo";
    }

    // Usamos /guardar para Crear y Editar (como lo piden las vistas)
    @PostMapping("/guardar") 
    public String guardar(@ModelAttribute Aparato aparato) {
        aparatoRepo.save(aparato);
        return "redirect:/aparatos/lista";
    }

 
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Aparato> aparatoOptional = aparatoRepo.findById(id);
        
        if (aparatoOptional.isPresent()) {
            model.addAttribute("aparato", aparatoOptional.get());
            // Eliminamos model.addAttribute("bailes", ...)
            return "aparato/editar";
        }
        
        // Manejo de error si el ID no existe
        return "redirect:/aparatos/lista";
    }
    
    // Mapeo para Borrar
    @GetMapping("/borrar/{id}") 
    public String borrar(@PathVariable Long id) {
        aparatoRepo.deleteById(id);
        return "redirect:/aparatos/lista";
    }
}