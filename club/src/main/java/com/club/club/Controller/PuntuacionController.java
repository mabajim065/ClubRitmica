package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.club.club.model.Puntuacion;
import com.club.club.repository.PuntuacionRepository;

@Controller
@RequestMapping("/puntuacion")
public class PuntuacionController {

    @Autowired
    private PuntuacionRepository puntuacionRepo;

    @GetMapping("")
    public String index() {
        return "redirect:/puntuacion/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("puntuaciones", puntuacionRepo.findAll());
        return "puntuacion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("puntuacion", new Puntuacion());
        return "puntuacion/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Puntuacion puntuacion = puntuacionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("puntuacion", puntuacion);
        return "puntuacion/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Puntuacion puntuacion) {
        puntuacionRepo.save(puntuacion);
        return "redirect:/puntuacion/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        puntuacionRepo.deleteById(id);
        return "redirect:/puntuacion/list";
    }
}