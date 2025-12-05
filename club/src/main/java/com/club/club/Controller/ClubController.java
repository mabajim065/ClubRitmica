package com.club.club.Controller;

import com.club.club.model.Club;
import com.club.club.repository.ClubRepository;
import com.club.club.repository.GimnastaRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    // 1. LOGGER PARA GESTIÓN DE CAMBIOS
    private static final Logger logger = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    private ClubRepository clubRepo;

    @Autowired
    private GimnastaRepository gimnastaRepo;

    // LISTADO PAGINADO
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Club> clubes = clubRepo.findAll(PageRequest.of(page, 5));
        model.addAttribute("clubes", clubes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalClubs", clubRepo.count());
        model.addAttribute("modoBusqueda", false);
        return "club/lista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        return "club/nuevo";
    }

    // CREAR (Con Log)
    @PostMapping("/crear")
    public String crear(@ModelAttribute Club club) {
        clubRepo.save(club);
        // GESTIÓN: Dejamos registro en consola
        logger.info("GESTIÓN: Se ha CREADO un nuevo club -> " + club.getNombre());
        return "redirect:/club/list";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("club", clubRepo.findById(id).orElse(null));
        return "club/editar";
    }

    // MODIFICAR (Con Log)
    @PostMapping("/modificar")
    public String modificar(@ModelAttribute Club club) {
        clubRepo.save(club);
        logger.info("GESTIÓN: Se ha MODIFICADO el club -> " + club.getNombre());
        return "redirect:/club/list";
    }

    // ELIMINAR (Con Log)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        // Recuperamos el nombre antes de borrar para el log
        String nombre = clubRepo.findById(id).map(Club::getNombre).orElse("Desconocido");
        clubRepo.deleteById(id);
        logger.warn("GESTIÓN: Se ha ELIMINADO el club -> " + nombre);
        return "redirect:/club/list";
    }

    // DETALLE
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Club club = clubRepo.findById(id).orElse(null);
        model.addAttribute("club", club);
        model.addAttribute("gimnastas", gimnastaRepo.findByClubId(id));
        return "club/detalle";
    }

    // BUSCAR
    @GetMapping("/buscar")
    public String buscar(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "ciudad", required = false) String ciudad,
            @RequestParam(value = "minSocios", required = false) Integer minSocios,
            Model model) {
        
        String n = (nombre != null) ? nombre : "";
        String c = (ciudad != null) ? ciudad : "";
        int s = (minSocios != null) ? minSocios : 0;
        
        List<Club> res = clubRepo.findByNombreContainingAndCiudadContainingAndNumSociosGreaterThanEqual(n, c, s);
        
        model.addAttribute("clubes", new PageImpl<>(res, PageRequest.of(0, 100), res.size()));
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalClubs", res.size());
        model.addAttribute("modoBusqueda", true);
        return "club/lista";
    }
    
    // ESTADISTICAS
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("totalClubes", clubRepo.count());
        model.addAttribute("totalGimnastas", gimnastaRepo.count());
        model.addAttribute("clubesMadrid", clubRepo.countByCiudad("Madrid"));
        model.addAttribute("gimnastaMasJoven", gimnastaRepo.findTopByOrderByFechaNacimientoDesc());
        model.addAttribute("clubMasAntiguo", clubRepo.findTopByOrderByFundacionAsc());
        return "club/estadisticas";
    }

    // --- NUEVO: EXPORTAR A PDF ---
    @GetMapping("/exportar-pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=clubes_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Club> listaClubes = clubRepo.findAll();

        // Crear documento
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        
        // Título
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        Paragraph paragraph = new Paragraph("Listado de Clubes", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        document.add(Chunk.NEWLINE);

        // Tabla
        PdfPTable table = new PdfPTable(5); // 5 columnas
        table.setWidthPercentage(100);
        
        // Cabeceras
        String[] cabeceras = {"ID", "Nombre", "Ciudad", "Fundación", "Socios"};
        for (String cabecera : cabeceras) {
            PdfPCell cell = new PdfPCell(new Phrase(cabecera));
            cell.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
            cell.setPadding(5);
            table.addCell(cell);
        }

        // Datos
        for (Club club : listaClubes) {
            table.addCell(String.valueOf(club.getId()));
            table.addCell(club.getNombre());
            table.addCell(club.getCiudad());
            table.addCell(club.getFundacion().toString()); // Ajusta formato si quieres
            table.addCell(String.valueOf(club.getNumSocios()));
        }

        document.add(table);
        document.close();
    }
}