package com.club.club.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Club;
import com.club.club.model.Gimnasta;
import com.club.club.repository.CategoriaRepository;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.GimnastaRepository;

@Controller
@RequestMapping("/gimnasta")
public class GimnastaController {

    @Autowired private GimnastaRepository gimnastaRepo;
    @Autowired private ClubRepository clubRepo;
    @Autowired private CategoriaRepository categoriaRepo;

    // LISTA PAGINADA
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {

        Page<Gimnasta> gimnastas = gimnastaRepo.findAll(PageRequest.of(page, size));
        model.addAttribute("gimnastas", gimnastas);
        model.addAttribute("currentPage", page);

        return "gimnasta/lista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("gimnasta", new Gimnasta());
        model.addAttribute("clubes", clubRepo.findAll());
        model.addAttribute("categorias", categoriaRepo.findAll());

        return "gimnasta/nuevo";
    }

    // CREAR
    @PostMapping("/crear")
    public String crear(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepo.save(gimnasta);
        return "redirect:/gimnasta/list";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("gimnasta", gimnastaRepo.findById(id).orElse(null));
        model.addAttribute("clubes", clubRepo.findAll());
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "gimnasta/editar";
    }

    // MODIFICAR
    @PostMapping("/modificar")
    public String modificar(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepo.save(gimnasta);
        return "redirect:/gimnasta/list";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        gimnastaRepo.deleteById(id);
        return "redirect:/gimnasta/list";
    }

    // DETALLE
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {

        Club g = gimnastaRepo.findById(id).orElse(null);
        model.addAttribute("gimnasta", g);

        return "gimnasta/detalle";
    }
}
