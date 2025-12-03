package com.club.club.Controller;

import com.club.club.model.Gimnasta;
import com.club.club.repository.GimnastaRepository;
import com.club.club.repository.ClubRepository; // Asegúrate de tener este repo creado
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/gimnasta")
public class GimnastaController {

    @Autowired
    private GimnastaRepository gimnastaRepo;

    @Autowired
    private ClubRepository clubRepo; // Necesario para el desplegable

    @GetMapping("")
    public String index() {
        return "redirect:/gimnasta/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("gimnastas", gimnastaRepo.findAll());
        return "gimnasta/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("gimnasta", new Gimnasta());
        // Pasamos la lista de clubs a la vista
        model.addAttribute("clubs", clubRepo.findAll());
        return "gimnasta/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Gimnasta gimnasta = gimnastaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("gimnasta", gimnasta);
        // Pasamos la lista de clubs a la vista también al editar
        model.addAttribute("clubs", clubRepo.findAll());
        return "gimnasta/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepo.save(gimnasta);
        return "redirect:/gimnasta/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        gimnastaRepo.deleteById(id);
        return "redirect:/gimnasta/list";
    }
}