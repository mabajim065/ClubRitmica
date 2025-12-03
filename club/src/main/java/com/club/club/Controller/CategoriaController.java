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

    // --- NUEVO: ESTO ARREGLA EL ERROR 404 AL PULSAR EN EL MENÚ ---
    // Si entran a "/categoria", los redirige a "/categoria/list"
    @GetMapping("")
    public String index() {
        return "redirect:/categoria/list";
    }
    // -------------------------------------------------------------

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        return "categoria/lista"; 
        // ¡OJO! Asegúrate de que el archivo lista.html está dentro de 
        // la carpeta src/main/resources/templates/categoria/
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("categoria", categoria);
        return "categoria/editar";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Categoria categoria) {
        categoriaRepo.save(categoria);
        return "redirect:/categoria/list";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categoriaRepo.deleteById(id);
        return "redirect:/categoria/list";
    }
}