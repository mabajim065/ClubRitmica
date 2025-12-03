package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Baile")
public class Baile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    // IMPORTANTE: Quitamos la tilde ("Duracion") para evitar errores SQL
    @Column(name = "Duracion")
    private LocalTime duracion;
}