package com.sportico.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportico.DTO.EnrolledUsersDTO;
import com.sportico.DTO.TournamentDTO;
import com.sportico.DTO.TournamentEnrollmentDTO;
import com.sportico.Service.TournamentService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/tournament")
public class TournamentController {
	@Autowired
	public TournamentService tournamentService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(tournamentService.getAllTournaments());
	}
	
	@PostMapping("")
	public ResponseEntity<?> addTournament(@RequestBody  TournamentDTO tournament) {
		
		return ResponseEntity.ok(tournamentService.addTournament(tournament));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTournament(@PathVariable Long id, @RequestBody TournamentDTO t) {	
		
		return ResponseEntity.ok(tournamentService.updateTournament(id , t));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTournament(@PathVariable Long id){
		return ResponseEntity.ok(tournamentService.deleteTournament(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSingleTournament(@PathVariable Long id) {
		return  ResponseEntity.ok(tournamentService.getTournamentById(id));
	}

	@GetMapping("/{tournamentId}/enrolled-users")
    public ResponseEntity<List<EnrolledUsersDTO>> getEnrolledUsers(@PathVariable Long tournamentId) {
        List<EnrolledUsersDTO> enrolledUsers = tournamentService.getEnrolledUsersByTournament(tournamentId);
        return ResponseEntity.ok(enrolledUsers);
    }
	
}

