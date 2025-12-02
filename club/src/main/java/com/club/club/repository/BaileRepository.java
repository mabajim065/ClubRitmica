package com.club.club.repository;

import com.club.club.model.Baile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BaileRepository extends JpaRepository<Baile, Long> {

}
