package com.club.club.repository;

import com.club.club.model.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {

    List<Puntuacion> findByValor(Double valor);

    void deleteByValor(Double valor);

    @Query("SELECT p FROM Puntuacion p WHERE p.valor >= :valor")
    List<Puntuacion> buscarPorValorMayorIgual(Double valor);
}
