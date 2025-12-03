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
@Table(name = "Puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPuntuacion")
    private Integer id;

    @Column(name = "Nota")
    private Double nota;

    @Column(name = "Comentario")
    private String comentario;

    @Column(name = "Fecha")
    private LocalDate fecha;
}