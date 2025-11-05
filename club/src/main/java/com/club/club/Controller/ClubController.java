package com.club.club.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Club;
import com.club.club.repository.ClubRepository;

@Controller
@RequestMapping("/clubs")
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // Listar todos los clubs
    @GetMapping
    public String listarClubs(Model model) {
        model.addAttribute("clubs", clubRepository.findAll());
        return "club-list"; // nombre del HTML
    }

    // Mostrar formulario nuevo club
    @GetMapping("/nuevo")
    public String nuevoClubForm(Model model) {
        model.addAttribute("club", new Club());
        return "club-form";
    }

    // Guardar club (nuevo o editado)
    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        clubRepository.save(club);
        return "redirect:/clubs";
    }

    // Editar club
    @GetMapping("/editar/{id}")
    public String editarClub(@PathVariable Integer id, Model model) {
        Club club = clubRepository.findById(id).orElseThrow();
        model.addAttribute("club", club);
        return "club-form";
    }

    // Borrar club
    @GetMapping("/borrar/{id}")
    public String borrarClub(@PathVariable Integer id) {
        clubRepository.deleteById(id);
        return "redirect:/clubs";
    }

    // Ver detalles
    @GetMapping("/detalle/{id}")
    public String detalleClub(@PathVariable Integer id, Model model) {
        Club club = clubRepository.findById(id).orElseThrow();
        model.addAttribute("club", club);
        return "club-detalle";
    }
}
