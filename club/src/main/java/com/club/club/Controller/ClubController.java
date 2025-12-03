package com.club.club.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.club.model.Club;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.GimnastaRepository;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepo;

    @Autowired
    private GimnastaRepository gimnastaRepo;

    // LISTADO PAGINADO PRINCIPAL
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<Club> clubes = clubRepo.findAll(PageRequest.of(page, size));
        model.addAttribute("clubes", clubes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalClubs", clubRepo.count()); // Conteo total
        model.addAttribute("modoBusqueda", false); // Bandera para indicar si es búsqueda o lista completa

        return "club/lista";
    }

    // [ ... MÉTODOS nuevo, crear, editar, modificar, eliminar, detalle ya
    // existentes ... ]

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        return "club/nuevo";
    }

    // CREAR (POST)
    @PostMapping("/crear")
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
    @PostMapping("/modificar")
    public String modificar(@ModelAttribute Club club) {
        clubRepo.save(club);
        return "redirect:/club/list";
    }

    // ELIMINAR (GET)
    @GetMapping("/eliminar/{id}")
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

    // --- MÉTODOS DE BÚSQUEDA Y FILTRO ---

    // 1. findByCiudadAndNumSociosGreaterThan (Filtro)
    @GetMapping("/filtrar-socios")
    public String filtrarPorSocios(@RequestParam("ciudad") String ciudad,
            @RequestParam("minSocios") int minSocios,
            Model model) {
        List<Club> resultados = clubRepo.findByCiudadAndNumSociosGreaterThan(ciudad, minSocios);

        // Creamos una Page simulada para reutilizar la vista lista.html
        model.addAttribute("clubes", new PageImpl<>(resultados, PageRequest.of(0, 50), resultados.size()));
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalClubs", clubRepo.count());
        model.addAttribute("modoBusqueda", true);

        return "club/lista";
    }

    // 2. buscarPorCiudadYSocios (@Query)
    @GetMapping("/consulta-personalizada")
    public String consultaPersonalizada(@RequestParam("ciudad") String ciudad,
            @RequestParam("minSocios") int minSocios,
            Model model) {
        List<Club> resultados = clubRepo.buscarPorCiudadYSocios(ciudad, minSocios);

        // Creamos una Page simulada
        model.addAttribute("clubes", new PageImpl<>(resultados, PageRequest.of(0, 50), resultados.size()));
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalClubs", clubRepo.count());
        model.addAttribute("modoBusqueda", true);

        return "club/lista";
    }

    // --- MÉTODOS DE ACCIÓN DE BORRADO ---

    // 3. deleteByCiudad
    @PostMapping("/eliminar-ciudad")
    public String eliminarPorCiudad(@RequestParam("ciudad") String ciudad) {
        clubRepo.deleteByCiudad(ciudad);
        return "redirect:/club/list";
    }

    // 4. deleteByNumSociosLessThan
    @PostMapping("/eliminar-socios-menor")
    public String eliminarSociosMenor(@RequestParam("maxSocios") int maxSocios) {
        clubRepo.deleteByNumSociosLessThan(maxSocios);
        return "redirect:/club/list";
    }
}