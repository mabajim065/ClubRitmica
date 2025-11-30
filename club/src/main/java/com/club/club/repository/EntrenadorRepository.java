package com.club.club.repository;

import com.club.club.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    List<Entrenador> findByNombre(String nombre);

    void deleteByNombre(String nombre);

    @Query("SELECT e FROM Entrenador e WHERE e.nombre LIKE %:nombre%")
    List<Entrenador> buscarPorNombre(String nombre);
}
