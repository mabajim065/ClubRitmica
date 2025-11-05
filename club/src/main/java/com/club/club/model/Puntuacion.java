package com.club.club.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPuntuación")
    private Integer idPuntuacion;

    @Column(name = "Nota")
    private Double nota;

    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "Fecha")
    private LocalDate fecha;

    // Getters y setters
    public Integer getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(Integer idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
