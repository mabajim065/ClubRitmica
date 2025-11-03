package com.club.club.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Gimnasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGimnasta;

    private String nombre;
    private LocalDate fechaNacimiento;
    private String nivel;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Long getIdGimnasta() {
        return idGimnasta;
    }

    public void setIdGimnasta(Long idGimnasta) {
        this.idGimnasta = idGimnasta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}

