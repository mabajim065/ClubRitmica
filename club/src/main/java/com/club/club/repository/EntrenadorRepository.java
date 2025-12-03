package com.club.club.repository;

import com.club.club.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer> {

}
