package com.club.club.repository;

import com.club.club.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//                                                     VVVVVVV  <-- AQUÍ estaba el problema (antes Long)
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}