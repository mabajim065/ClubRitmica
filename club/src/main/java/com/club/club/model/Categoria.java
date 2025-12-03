package com.club.club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Puedes dejar name="idCategoria" si en la base de datos se llama así,
    // o cambiarlo a "id" si quieres que coincida todo. Aquí lo dejo mapeado para seguridad.
    @Column(name = "idCategoria") 
    private Integer id;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "Nombre")
    private String nombre;

    public Categoria() {
    }

    // Getters y setters actualizados a "id"

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}