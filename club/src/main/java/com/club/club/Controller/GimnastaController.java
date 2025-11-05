package com.club.club.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Gimnasta;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.GimnastaRepository;

@Controller
@RequestMapping("/gimnastas")
public class GimnastaController {

    private final GimnastaRepository gimnastaRepository;
    private final ClubRepository clubRepository;

    public GimnastaController(GimnastaRepository gimnastaRepository, ClubRepository clubRepository) {
        this.gimnastaRepository = gimnastaRepository;
        this.clubRepository = clubRepository;
    }

    // Listar todos los gimnastas
    @GetMapping
    public String listarGimnastas(Model model) {
        model.addAttribute("gimnastas", gimnastaRepository.findAll());
        return "gimnasta-list";
    }

    // Formulario nuevo gimnasta
    @GetMapping("/nuevo")
    public String nuevoGimnastaForm(Model model) {
        model.addAttribute("gimnasta", new Gimnasta());
        model.addAttribute("clubs", clubRepository.findAll()); // para seleccionar club
        return "gimnasta-form";
    }

    // Guardar gimnasta
    @PostMapping("/guardar")
    public String guardarGimnasta(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepository.save(gimnasta);
        return "redirect:/gimnastas";
    }

    // Editar gimnasta
    @GetMapping("/editar/{id}")
    public String editarGimnasta(@PathVariable Integer id, Model model) {
        Gimnasta gimnasta = gimnastaRepository.findById(id).orElseThrow();
        model.addAttribute("gimnasta", gimnasta);
        model.addAttribute("clubs", clubRepository.findAll());
        return "gimnasta-form";
    }

    // Borrar gimnasta
    @GetMapping("/borrar/{id}")
    public String borrarGimnasta(@PathVariable Integer id) {
        gimnastaRepository.deleteById(id);
        return "redirect:/gimnastas";
    }

    // Ver detalles
    @GetMapping("/detalle/{id}")
    public String detalleGimnasta(@PathVariable Integer id, Model model) {
        Gimnasta gimnasta = gimnastaRepository.findById(id).orElseThrow();
        model.addAttribute("gimnasta", gimnasta);
        return "gimnasta-detalle";
    }
}
