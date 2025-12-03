package com.club.club.repository;

import com.club.club.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    // --- REQUISITO 1: 2 métodos de búsqueda usando nomenclatura Spring Data ---
    
    // 1. Buscar por nombre (que contenga texto) y ciudad exacta
    List<Club> findByNombreContainingAndCiudad(String nombre, String ciudad);

    // 2. Buscar clubs fundados antes de una fecha y de una ciudad concreta
    List<Club> findByFundacionBeforeAndCiudad(Date fundacion, String ciudad);


    // --- REQUISITO 2: 1 método con ordenación ---
    
    // Lista todos los clubs ordenados alfabéticamente
    List<Club> findAllByOrderByNombreAsc();


    // --- REQUISITO 3: 1 método de conteo y 1 de agregación ---
    
    // Cuenta cuántos clubs hay en una ciudad
    long countByCiudad(String ciudad);

    // Encuentra el club más antiguo (el primero ordenado por fundación ascendente)
    Club findTopByOrderByFundacionAsc();


    // --- REQUISITO 4: 1 consulta personalizada con @Query ---
    
    // Busca clubs de una ciudad con un mínimo de socios
    @Query("SELECT c FROM Club c WHERE c.ciudad = :ciudad AND c.numSocios > :minSocios")
    List<Club> buscarPorCiudadYSocios(@Param("ciudad") String ciudad, @Param("minSocios") int minSocios);
}