
package com.club.club.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Categoria;
import com.club.club.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepo;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "categoria/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/nuevo";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Categoria categoria) {
        categoriaRepo.save(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaRepo.deleteById(id);
        return "redirect:/categoria/list";
    }
}
