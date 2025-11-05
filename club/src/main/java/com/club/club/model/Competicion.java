package com.club.club.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Competicion")
public class Competicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompeticion")
    private Integer idCompeticion;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Lugar")
    private String lugar;

    // Getters y setters
    public Integer getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(Integer idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
