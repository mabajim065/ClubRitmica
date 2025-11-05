package com.club.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.club.club.model.Club;

public interface ClubRepository extends JpaRepository<Club, Integer> {
}
