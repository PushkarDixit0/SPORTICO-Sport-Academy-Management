package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.EnrolledUsersDTO;
import com.sportico.DTO.TournamentDTO;
import com.sportico.DTO.TournamentEnrollmentDTO;

public interface TournamentService{

	List<TournamentDTO> getAllTournaments();

	TournamentDTO addTournament(TournamentDTO t);

	String deleteTournament(Long id);

	TournamentDTO updateTournament(Long id, TournamentDTO t);

	TournamentDTO getTournamentById(Long id);

	List<EnrolledUsersDTO> getEnrolledUsersByTournament(Long tournamentId);

}
