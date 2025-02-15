package com.sportico.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sportico.DTO.TournamentDTO;
import com.sportico.Service.TournamentEnrollmentService;
import com.sportico.Service.TournamentService;

@RestController
@RequestMapping("/enrollment")
public class TournamentEnrollmentController {

    @Autowired
    private TournamentEnrollmentService tournamentEnrollmentService;

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/{tournamentId}/{userId}")
    public ResponseEntity<?> enrollInTournament(@PathVariable Long tournamentId, @PathVariable Long userId) {
        try {
            TournamentDTO tdto = tournamentService.getTournamentById(tournamentId);
            if (tdto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
            }

            String enrolled = tournamentEnrollmentService.enrollUserInTournament(userId, tournamentId);
            return ResponseEntity.ok(enrolled);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling user in tournament.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTournaments() {
        try {
            List<TournamentDTO> tournaments = tournamentService.getAllTournaments();
            return ResponseEntity.ok(tournaments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve tournaments.");
        }
    }

    @GetMapping("/enrollments/{userId}")
    public ResponseEntity<?> getEnrolledTournaments(@PathVariable Long userId) {
        try {
            List<TournamentDTO> enrolled = tournamentEnrollmentService.getEnrolledTournament(userId);
            return ResponseEntity.ok(enrolled);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve enrolled tournaments.");
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> mockPayment(@RequestBody Map<String, Object> requestData) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (!requestData.containsKey("tournamentId") || !requestData.containsKey("userId")) {
                response.put("success", false);
                response.put("message", "Invalid request data.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Long tournamentId = Long.parseLong(requestData.get("tournamentId").toString());
            Long userId = Long.parseLong(requestData.get("userId").toString());

            boolean isSuccess = Math.random() > 0.2; // 80% success rate
            response.put("success", isSuccess);
            response.put("message", isSuccess ? "Payment successful!" : "Payment failed!");

            if (isSuccess) {
                String enrollmentStatus = tournamentEnrollmentService.enrollUserInTournament(userId, tournamentId);
                response.put("enrollmentStatus", enrollmentStatus);
            }

            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Invalid tournamentId or userId format.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred during payment.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
