package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Conjunto")
public class Conjunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConjunto")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Categoria")
    private String categoria;
}