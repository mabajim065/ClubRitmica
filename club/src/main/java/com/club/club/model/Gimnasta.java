package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Gimnasta")
public class Gimnasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGimnasta")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "FechaNacimiento")
    private LocalDate fechaNacimiento;

    // --- CAMPOS NUEVOS NECESARIOS ---
    @Column(name = "Edad")
    private Integer edad;

    @Column(name = "Nivel")
    private String nivel;

    @Column(name = "Especialidad")
    private String especialidad;

    // --- RELACIÓN SOLO CON CLUB ---
    @ManyToOne
    @JoinColumn(name = "idClub")
    @ToString.Exclude
    private Club club;
}