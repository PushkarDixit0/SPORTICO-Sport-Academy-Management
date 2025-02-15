package com.sportico.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.TournamentEnrollment;

public interface TournamentEnrollmentDao extends JpaRepository<TournamentEnrollment, Long> {
    boolean existsByUserIdAndTournamentId(Long userId, Long tournamentId);
    List<TournamentEnrollment> findByUserId(Long userId);
    
    List<TournamentEnrollment> findByTournamentId(Long tournamentId);
}
