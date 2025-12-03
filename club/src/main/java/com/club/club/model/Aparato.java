package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "Aparato")
public class Aparato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(name = "Nombre")
    private String nombre;
    
}