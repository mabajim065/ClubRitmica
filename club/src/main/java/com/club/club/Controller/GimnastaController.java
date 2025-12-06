package com.club.club.Controller;

import com.club.club.model.Gimnasta;
import com.club.club.repository.GimnastaRepository;
import com.club.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/gimnasta")
public class GimnastaController {

    @Autowired
    private GimnastaRepository gimnastaRepo;

    @Autowired
    private ClubRepository clubRepo;

    // Método principal: Lista todos los gimnastas
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("gimnastas", gimnastaRepo.findAll());
        // Añadimos el conteo total para mostrarlo en la vista
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // Método para formularios: Nuevo Gimnasta
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("gimnasta", new Gimnasta());
        model.addAttribute("clubs", clubRepo.findAll());
        return "gimnasta/nuevo";
    }

    // Método para formularios: Editar Gimnasta
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Gimnasta gimnasta = gimnastaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        model.addAttribute("gimnasta", gimnasta);
        model.addAttribute("clubs", clubRepo.findAll());
        return "gimnasta/editar";
    }

    // Método de acción: Guardar (Crear)
    @PostMapping("/crear")
    public String crear(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepo.save(gimnasta);
        return "redirect:/gimnasta/list";
    }

// Método para ver Detalles
@GetMapping("/detalle/{id}")
public String verDetalle(@PathVariable Integer id, Model model) {
    Gimnasta gimnasta = gimnastaRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
    
    model.addAttribute("gimnasta", gimnasta);
    return "gimnasta/detalle"; 
}

    // Método de acción: Modificar
    @PostMapping("/modificar")
    public String modificar(@ModelAttribute Gimnasta gimnasta) {
        gimnastaRepo.save(gimnasta);
        return "redirect:/gimnasta/list";
    }

    // Método de acción: Eliminar (por ID)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        gimnastaRepo.deleteById(id);
        return "redirect:/gimnasta/list";
    }

    // --- MÉTODOS DE CONSULTA AMPLIADOS (Para el formulario en lista.html) ---

    // 1. findByNombreContainingAndApellidosContaining
    @GetMapping("/buscar-nombre-apellido")
    public String buscarPorNombreYApellido(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            Model model) {
        model.addAttribute("gimnastas",
                gimnastaRepo.findByNombreContainingAndApellidosContaining(nombre, apellido));
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // 2. findByNivelOrEspecialidad
    @GetMapping("/buscar-nivel-o-esp")
    public String buscarPorNivelOEspecialidad(@RequestParam("nivel") String nivel,
            @RequestParam("especialidad") String especialidad,
            Model model) {
        model.addAttribute("gimnastas",
                gimnastaRepo.findByNivelOrEspecialidad(nivel, especialidad));
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // 3. findAllByOrderByNivelDescApellidosAsc
    @GetMapping("/ordenar")
    public String ordenarGimnastas(Model model) {
        model.addAttribute("gimnastas", gimnastaRepo.findAllByOrderByNivelDescApellidosAsc());
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // 4. buscarMayoresDe (Consulta personalizada @Query)
    @GetMapping("/buscar-mayores")
    public String buscarMayoresDe(@RequestParam("fecha") LocalDate fecha, Model model) {
        model.addAttribute("gimnastas", gimnastaRepo.buscarMayoresDe(fecha));
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // 5. findTopByOrderByFechaNacimientoDesc (El más joven)
    @GetMapping("/buscar-mas-joven")
    public String buscarMasJoven(Model model) {
        Gimnasta gimnastaJoven = gimnastaRepo.findTopByOrderByFechaNacimientoDesc();
        // Creamos una lista de un solo elemento para reutilizar la vista
        model.addAttribute("gimnastas", java.util.Collections.singletonList(gimnastaJoven));
        model.addAttribute("conteoTotal", gimnastaRepo.count());
        return "gimnasta/lista";
    }

    // 6. deleteByApellidos (Acción de borrado especial)
    @PostMapping("/eliminar-apellido")
    public String eliminarPorApellido(@RequestParam("apellido") String apellido) {
        gimnastaRepo.deleteByApellidos(apellido);
        return "redirect:/gimnasta/list";
    }
}