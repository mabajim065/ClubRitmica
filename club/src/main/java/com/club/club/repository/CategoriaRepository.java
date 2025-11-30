package com.club.club.repository;

import com.club.club.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Métodos secundarios: 3
    List<Categoria> findByNombre(String nombre);

    // Borrar por nombre
    void deleteByNombre(String nombre);

    // Consulta JPQL personalizada
    @Query("SELECT c FROM Categoria c WHERE c.nombre LIKE %:nombre%")
    List<Categoria> buscarPorNombre(String nombre);
}
