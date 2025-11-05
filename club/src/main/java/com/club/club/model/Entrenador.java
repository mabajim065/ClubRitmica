package com.club.club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEntrenador")
    private Integer idEntrenador;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefono")
    private Integer telefono;

    // Getters y setters
    public Integer getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Integer idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
}
