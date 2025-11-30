package com.club.club.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Puntuacion;
import com.club.club.repository.CompeticionRepository;
import com.club.club.repository.GimnastaRepository;
import com.club.club.repository.PuntuacionRepository;

@Controller
@RequestMapping("/puntuaciones")
public class PuntuacionController {

    private final PuntuacionRepository puntuacionRepository;
    private final GimnastaRepository gimnastaRepository;
    private final CompeticionRepository competicionRepository;

    public PuntuacionController(
            PuntuacionRepository puntuacionRepository,
            GimnastaRepository gimnastaRepository,
            CompeticionRepository competicionRepository) {
        this.puntuacionRepository = puntuacionRepository;
        this.gimnastaRepository = gimnastaRepository;
        this.competicionRepository = competicionRepository;
    }

    // Listar puntuaciones
    @GetMapping
    public String listarPuntuaciones(Model model) {
        model.addAttribute("puntuaciones", puntuacionRepository.findAll());
        return "puntuacion-list";
    }

    // Formulario nueva puntuación
    @GetMapping("/nuevo")
    public String nuevaPuntuacionForm(Model model) {
        model.addAttribute("puntuacion", new Puntuacion());
        model.addAttribute("gimnastas", gimnastaRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "puntuacion-form";
    }

    // Guardar puntuación
    @PostMapping("/guardar")
    public String guardarPuntuacion(@ModelAttribute Puntuacion puntuacion) {
        puntuacionRepository.save(puntuacion);
        return "redirect:/puntuaciones";
    }

    // Editar puntuación
    @GetMapping("/editar/{id}")
    public String editarPuntuacion(@PathVariable Long id, Model model) {
        Puntuacion puntuacion = puntuacionRepository.findById(id).orElseThrow();
        model.addAttribute("puntuacion", puntuacion);
        model.addAttribute("gimnastas", gimnastaRepository.findAll());
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "puntuacion-form";
    }

    // Borrar puntuación
    @GetMapping("/borrar/{id}")
    public String borrarPuntuacion(@PathVariable Long id) {
        puntuacionRepository.deleteById(id);
        return "redirect:/puntuaciones";
    }
}
