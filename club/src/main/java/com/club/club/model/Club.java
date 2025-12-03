package com.club.club.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString; // IMPORTANTE
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "Club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String ciudad;    
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fundacion;
    
    private int numSocios;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    @ToString.Exclude // <--- ESTO EVITA EL ERROR 500 (BUCLE INFINITO)
    private List<Gimnasta> gimnastas;
}