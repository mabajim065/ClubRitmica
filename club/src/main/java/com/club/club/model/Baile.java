package com.club.club.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Baile")
public class Baile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBaile")
    private Integer idBaile;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Duración")
    private LocalTime duracion;

    @Column(name = "TipoAparato")
    private String tipoAparato;

    // Getters y setters
    public Integer getIdBaile() {
        return idBaile;
    }

    public void setIdBaile(Integer idBaile) {
        this.idBaile = idBaile;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getTipoAparato() {
        return tipoAparato;
    }

    public void setTipoAparato(String tipoAparato) {
        this.tipoAparato = tipoAparato;
    }
}
