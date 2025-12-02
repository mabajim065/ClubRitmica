package com.club.club.repository;

import com.club.club.model.Aparato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AparatoRepository extends JpaRepository<Aparato, Long> {

}
