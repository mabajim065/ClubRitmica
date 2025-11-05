package com.club.club.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscripcion")
    private Integer idInscripcion;

    @Column(name = "Fecha")
    private LocalDate fecha;

    // Getters y setters
    public Integer getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Integer idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
