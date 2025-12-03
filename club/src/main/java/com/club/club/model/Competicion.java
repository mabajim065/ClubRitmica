package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Competicion")
public class Competicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompeticion")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Fecha")
    private LocalDate fecha;

    @Column(name = "Lugar")
    private String lugar;
}