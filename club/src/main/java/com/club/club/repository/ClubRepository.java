package com.club.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.club.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Integer> {
}
