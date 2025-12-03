package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // IMPORTANTE
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

    @ManyToOne
    @JoinColumn(name = "idClub") 
    @ToString.Exclude // <--- ESTO EVITA QUE LA APLICACIÓN EXPLOTE
    private Club club;
}