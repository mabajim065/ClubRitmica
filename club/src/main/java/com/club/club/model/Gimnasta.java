package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Gimnasta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private int edad;
    private LocalDate fechaNacimiento;
    private String nivel;
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "categoria_id", nullable = false)
     * private Categoria categoria;
     */

}
