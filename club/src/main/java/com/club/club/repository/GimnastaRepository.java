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

    // Método para la relación Club (ID de Club es Long)
    List<Gimnasta> findByClubId(Long idClub);

    // --- MÉTODOS REQUERIDOS ---

    // 1. Dos métodos de búsqueda usando nomenclatura Spring Data (findBy...)
    // Método 1: Búsqueda por Nombre Y Apellidos (Containing)
    List<Gimnasta> findByNombreContainingAndApellidosContaining(String nombre, String apellidos);

    // Método 2: Búsqueda por Nivel O Especialidad (Or)
    List<Gimnasta> findByNivelOrEspecialidad(String nivel, String especialidad);

    // 2. Un método con ordenación (OrderBy...)
    // Ordenar gimnastas por Nivel de forma descendente y por Apellidos de forma
    // ascendente
    List<Gimnasta> findAllByOrderByNivelDescApellidosAsc();

    // 3. Un método de conteo y 1 con agregación (countBy... o findTop...)
    // Método de conteo: Cuenta cuántos gimnastas hay en un club específico
    long countByClubId(Long idClub);

    // Método de agregación: Encuentra el gimnasta más joven (o sea, el que tiene la
    // fecha más reciente)
    Gimnasta findTopByOrderByFechaNacimientoDesc();

    // 4. Una consulta personalizada con @Query (JPQL) que reciba al menos 1
    // parámetro.
    // Consulta existente:
    @Query("SELECT g FROM Gimnasta g WHERE g.fechaNacimiento < :fecha")
    List<Gimnasta> buscarMayoresDe(@Param("fecha") LocalDate fecha);

    // --- MÉTODOS DE ELIMINACIÓN TRANSACCIONALES ---

    @Transactional
    void deleteByApellidos(String apellidos);

    @Transactional
    void deleteByClubId(Long idClub);

    // Método para la búsqueda original por nombre (mantengo la tuya)
    List<Gimnasta> findByNombreContaining(String texto);
}