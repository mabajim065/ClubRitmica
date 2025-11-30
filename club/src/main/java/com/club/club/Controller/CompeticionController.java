package com.club.club.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.club.club.model.Competicion;
import com.club.club.repository.CompeticionRepository;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {

    private final CompeticionRepository competicionRepository;

    public CompeticionController(CompeticionRepository competicionRepository) {
        this.competicionRepository = competicionRepository;
    }

    // Listar competiciones
    @GetMapping
    public String listarCompeticiones(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competicion-list";
    }

    // Formulario nueva competición
    @GetMapping("/nuevo")
    public String nuevaCompeticionForm(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion-form";
    }

    // Guardar competición
    @PostMapping("/guardar")
    public String guardarCompeticion(@ModelAttribute Competicion competicion) {
        competicionRepository.save(competicion);
        return "redirect:/competiciones";
    }

    // Editar competición
    @GetMapping("/editar/{id}")
    public String editarCompeticion(@PathVariable Long id, Model model) {
        Competicion competicion = competicionRepository.findById(id).orElseThrow();
        model.addAttribute("competicion", competicion);
        return "competicion-form";
    }

    // Borrar competición
    @GetMapping("/borrar/{id}")
    public String borrarCompeticion(@PathVariable Long id) {
        competicionRepository.deleteById(id);
        return "redirect:/competiciones";
    }
}
