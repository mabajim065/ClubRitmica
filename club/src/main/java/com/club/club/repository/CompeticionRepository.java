package com.club.club.repository;

import com.club.club.model.Competicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CompeticionRepository extends JpaRepository<Competicion, Long> {

}
