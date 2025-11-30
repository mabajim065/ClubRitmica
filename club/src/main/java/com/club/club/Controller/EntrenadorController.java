package com.club.club.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Entrenador;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.EntrenadorRepository;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorRepository entrenadorRepository;
    private final ClubRepository clubRepository;

    public EntrenadorController(EntrenadorRepository entrenadorRepository, ClubRepository clubRepository) {
        this.entrenadorRepository = entrenadorRepository;
        this.clubRepository = clubRepository;
    }

    // Listar entrenadores
    @GetMapping
    public String listarEntrenadores(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenador-list";
    }

    // Formulario nuevo entrenador
    @GetMapping("/nuevo")
    public String nuevoEntrenadorForm(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("clubs", clubRepository.findAll());
        return "entrenador-form";
    }

    // Guardar entrenador
    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores";
    }

    // Editar entrenador
    @GetMapping("/editar/{id}")
    public String editarEntrenador(@PathVariable Long id, Model model) {
        Entrenador entrenador = entrenadorRepository.findById(id).orElseThrow();
        model.addAttribute("entrenador", entrenador);
        model.addAttribute("clubs", clubRepository.findAll());
        return "entrenador-form";
    }

    // Borrar entrenador
    @GetMapping("/borrar/{id}")
    public String borrarEntrenador(@PathVariable Long id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores";
    }
}
