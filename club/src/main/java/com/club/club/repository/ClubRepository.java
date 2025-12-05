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

    // --- MÉTODOS DE BORRADO (REQUISITO) ---

    // 1. Borrar todos los registros que cumplan cierta condición (por Ciudad)
    @Transactional
    void deleteByCiudad(String ciudad); // Referenciado en ClubController.eliminarPorCiudad

    // 2. Borrar todos los registros que cumplan cierta condición (por Número de
    // Socios menor a)
    @Transactional
    void deleteByNumSociosLessThan(int numSocios); // Referenciado en ClubController.eliminarSociosMenor

    // --- MÉTODOS DE BÚSQUEDA Y FILTROS ---

    // 3. Busca clubs de una Ciudad concreta y con un mínimo de Socios
    List<Club> findByCiudadAndNumSociosGreaterThan(String ciudad, int minSocios); // Referenciado en ClubController.filtrarPorSocios

    // 4. Una consulta personalizada con @Query
    @Query("SELECT c FROM Club c WHERE c.ciudad = :ciudad AND c.numSocios > :minSocios")
    List<Club> buscarPorCiudadYSocios(@Param("ciudad") String ciudad, @Param("minSocios") int minSocios); // Referenciado en ClubController.consultaPersonalizada
    
    // ** 5. MÉTODO AÑADIDO: Búsqueda Avanzada Combinada (CRÍTICO para ClubController.buscar) **
    // Busca por Nombre (parcial) Y Ciudad (parcial) Y Socios (mayor o igual)
  List<Club> findByNombreContainingAndCiudadContainingAndNumSociosGreaterThanEqual(
    String nombre, 
    String ciudad, 
    int numSocios
);


    // --- MÉTODOS ADICIONALES/AGREGACIÓN ---
    
    // NOTA: El método findByNombreContainingAndCiudad se ha reemplazado por el combinado arriba.
    
    List<Club> findByFundacionBeforeAndCiudad(Date fundacion, String ciudad);

    List<Club> findAllByOrderByNombreAsc();

    long countByCiudad(String ciudad); // Referenciado en ClubController.estadisticas

    Club findTopByOrderByFundacionAsc(); // Referenciado en ClubController.estadisticas
}