package com.sportico.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.Tournament;

public interface TournamentDao extends JpaRepository<Tournament, Long> {
    List<Tournament> findByTournamentDateBefore(LocalDate date);
}
