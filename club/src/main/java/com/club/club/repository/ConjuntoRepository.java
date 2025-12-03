package com.club.club.repository;

import com.club.club.model.Conjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConjuntoRepository extends JpaRepository<Conjunto, Integer> {
}