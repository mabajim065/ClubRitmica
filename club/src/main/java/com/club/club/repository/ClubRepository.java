package com.club.club.repository;

import com.club.club.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // <-- Añadido para los borrados

import java.util.Date;
import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    // --- MÉTODOS DE BORRADO (REQUISITO NUEVO) ---

    // 1. Borrar todos los registros que cumplan cierta condición (por Ciudad)
    @Transactional
    void deleteByCiudad(String ciudad);

    // 2. Borrar todos los registros que cumplan cierta condición (por Número de
    // Socios menor a)
    @Transactional
    void deleteByNumSociosLessThan(int numSocios);

    // --- MÉTODOS DE BÚSQUEDA Y FILTROS (REQUISITO NUEVO Y EXISTENTE) ---

    // 3. Un método de búsqueda con filtros (Combinación de varios campos)
    // Busca clubs de una Ciudad concreta y con un mínimo de Socios
    List<Club> findByCiudadAndNumSociosGreaterThan(String ciudad, int minSocios);

    // 4. Una consulta personalizada con @Query (existente, pero sigue siendo
    // requisito)
    // Busca clubs de una ciudad con un mínimo de socios
    @Query("SELECT c FROM Club c WHERE c.ciudad = :ciudad AND c.numSocios > :minSocios")
    List<Club> buscarPorCiudadYSocios(@Param("ciudad") String ciudad, @Param("minSocios") int minSocios);

    // --- MÉTODOS ADICIONALES EXISTENTES ---
    List<Club> findByNombreContainingAndCiudad(String nombre, String ciudad);

    List<Club> findByFundacionBeforeAndCiudad(Date fundacion, String ciudad);

    List<Club> findAllByOrderByNombreAsc();

    long countByCiudad(String ciudad);

    Club findTopByOrderByFundacionAsc();
}