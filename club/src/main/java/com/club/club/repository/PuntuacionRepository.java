package com.club.club.repository;

import com.club.club.model.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

}
