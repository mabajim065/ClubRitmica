package com.club.club.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Conjunto")
public class Conjunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConjunto")
    private Integer idConjunto;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Categoria")
    private String categoria;

    // Relación 1:N con Gimnasta
    @OneToMany(mappedBy = "conjunto", cascade = CascadeType.ALL)
    private List<Gimnasta> gimnastas;

    // Getters y setters
    public Integer getIdConjunto() {
        return idConjunto;
    }

    public void setIdConjunto(Integer idConjunto) {
        this.idConjunto = idConjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Gimnasta> getGimnastas() {
        return gimnastas;
    }

    public void setGimnastas(List<Gimnasta> gimnastas) {
        this.gimnastas = gimnastas;
    }
}
