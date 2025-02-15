package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.TournamentDTO;
import com.sportico.DTO.TournamentEnrollmentDTO;

public interface TournamentEnrollmentService {

	List<TournamentEnrollmentDTO> getAllEnrollments();

	String enrollUserInTournament(Long userId, Long tournamentId);

	List<TournamentDTO> getEnrolledTournament(Long userId) ;
}
