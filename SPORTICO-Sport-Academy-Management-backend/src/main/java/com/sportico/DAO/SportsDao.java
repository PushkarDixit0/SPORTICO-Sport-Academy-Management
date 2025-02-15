package com.sportico.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.Sport;

public interface SportsDao extends JpaRepository<Sport, Long> {
		Optional<Sport> findByName(String name);
}
