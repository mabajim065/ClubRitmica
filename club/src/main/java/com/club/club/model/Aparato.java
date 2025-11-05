package com.club.club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Aparato")
public class Aparato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAparato")
    private Integer idAparato;

    @Column(name = "Nombre")
    private String nombre;

    // Getters y setters
    public Integer getIdAparato() {
        return idAparato;
    }

    public void setIdAparato(Integer idAparato) {
        this.idAparato = idAparato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
