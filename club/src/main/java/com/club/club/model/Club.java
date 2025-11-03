package com.club.club.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClub;

    private String nombre;
    private String direccion;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Gimnasta> gimnastas;

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Gimnasta> getGimnastas() {
        return gimnastas;
    }

    public void setGimnastas(List<Gimnasta> gimnastas) {
        this.gimnastas = gimnastas;
    }
}
