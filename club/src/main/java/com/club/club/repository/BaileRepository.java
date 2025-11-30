package com.club.club.repository;

import com.club.club.model.Baile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BaileRepository extends JpaRepository<Baile, Long> {

    List<Baile> findByNombre(String nombre);

    void deleteByNombre(String nombre);

    @Query("SELECT b FROM Baile b WHERE b.nombre LIKE %:nombre%")
    List<Baile> buscarPorNombre(String nombre);
}
