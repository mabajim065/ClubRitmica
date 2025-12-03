package com.club.club.repository;

import com.club.club.model.Baile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaileRepository extends JpaRepository<Baile, Integer> { 
    
}