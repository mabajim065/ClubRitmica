package com.club.club.repository;

import com.club.club.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {

    List<Competicion> findByNombre(String nombre);

    void deleteByNombre(String nombre);

    @Query("SELECT c FROM Competicion c WHERE c.nombre LIKE %:nombre%")
    List<Competicion> buscarPorNombre(String nombre);
}
