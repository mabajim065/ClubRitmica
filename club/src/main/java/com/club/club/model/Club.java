package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String direccion;
    private String ciudad;
    private Date fundacion;
    private int numSocios;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Gimnasta> gimnastas;

}
