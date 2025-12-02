package com.club.club.repository;

import com.club.club.model.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ConjuntoRepository extends JpaRepository<Conjunto, Long> {

}
