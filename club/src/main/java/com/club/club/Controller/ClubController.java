package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Club;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.GimnastaRepository;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepo;

    @Autowired
    private GimnastaRepository gimnastaRepo;

    // LISTADO PAGINADO (CORREGIDO para usar /list y coincidir con Gimnasta)
    @GetMapping("/list") 
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<Club> clubes = clubRepo.findAll(PageRequest.of(page, size));
        model.addAttribute("clubes", clubes);
        model.addAttribute("currentPage", page);

        return "club/lista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        return "club/nuevo";
    }

    // CREAR (POST)
    @PostMapping("/crear") // Ruta usada por nuevo.html
    public String crear(@ModelAttribute Club club) {
        clubRepo.save(club);
        return "redirect:/club/list";
    }

    // EDITAR (GET)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("club", clubRepo.findById(id).orElse(null));
        return "club/editar";
    }

    // MODIFICAR (POST)
    @PostMapping("/modificar") // Ruta usada por editar.html
    public String modificar(@ModelAttribute Club club) {
        clubRepo.save(club);
        return "redirect:/club/list";
    }

    // ELIMINAR (GET)
    @GetMapping("/eliminar/{id}") // Ruta usada por lista.html
    public String eliminar(@PathVariable Long id) {
        clubRepo.deleteById(id);
        return "redirect:/club/list";
    }

    // DETALLE ESPECIAL
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id).orElse(null);
        model.addAttribute("club", club);
        // Además, cargamos las gimnastas de ese club
        model.addAttribute("gimnastas", gimnastaRepo.findByClubId(id));

        return "club/detalle";
    }
}