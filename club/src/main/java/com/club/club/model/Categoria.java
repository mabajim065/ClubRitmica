package com.club.club.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Integer idCategoria;

    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "Nombre")
    private String nombre;

    // Relación 1:N con Gimnasta
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Gimnasta> gimnastas;

    // Getters y setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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

    public List<Gimnasta> getGimnastas() {
        return gimnastas;
    }

    public void setGimnastas(List<Gimnasta> gimnastas) {
        this.gimnastas = gimnastas;
    }
}
