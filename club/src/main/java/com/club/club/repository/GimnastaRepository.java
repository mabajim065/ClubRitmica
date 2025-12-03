package com.club.club.repository;

import com.club.club.model.Gimnasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GimnastaRepository extends JpaRepository<Gimnasta, Integer> {

    // ESTE ES EL MÉTODO QUE FALTABA Y CAUSA ERROR EN CLUB CONTROLLER
    // Ojo: Recibe Long porque el ID de Club es Long
    List<Gimnasta> findByClubId(Long idClub); 

    // --- REQUISITOS DEL EJERCICIO ---
    
    @Transactional
    void deleteByApellidos(String apellidos);

    @Transactional
    void deleteByClubId(Long idClub);

    List<Gimnasta> findByNombreContaining(String texto);

    @Query("SELECT g FROM Gimnasta g WHERE g.fechaNacimiento < :fecha")
    List<Gimnasta> buscarMayoresDe(@Param("fecha") LocalDate fecha);
}