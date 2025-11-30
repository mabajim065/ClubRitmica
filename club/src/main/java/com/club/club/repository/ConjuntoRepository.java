package com.club.club.repository;

import com.club.club.model.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ConjuntoRepository extends JpaRepository<Conjunto, Long> {

    // Métodos secundarios: 3
    List<Conjunto> findByNombre(String nombre);

    // Borrar por nombre
    void deleteByNombre(String nombre);

    // Consulta JPQL personalizada
    @Query("SELECT c FROM Conjunto c WHERE c.nombre LIKE %:nombre%")
    List<Conjunto> buscarPorNombre(String nombre);
}
