package com.sportico.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportico.DTO.APIResponse;
import com.sportico.DTO.CoachingSessiondtoPost;
import com.sportico.Service.CoachingSessionService;

@RestController
@RequestMapping("/session")
public class CoachingSessionController {

	@Autowired
	public CoachingSessionService coachingSessionService;


	
	public CoachingSessionController() {
		super();
		System.out.println("coaching session running");
	}
	
	@GetMapping("/allsession")
	public ResponseEntity<?> getMethodGetAllSession() {
		return ResponseEntity.ok(coachingSessionService.getallSession());
	}
	
	@GetMapping("/allsessionCID/{cid}")
	public ResponseEntity<?> getMethodGetAllSessionByCoachId(@PathVariable Long cid ) {
		return ResponseEntity.ok(coachingSessionService.getallSessionCID(cid));
	}
//	
	@GetMapping("/allsessionSID/{sid}")
	public ResponseEntity<?> getMethodGetAllSessionBySportId(@PathVariable Long sid ) {
		return ResponseEntity.ok(coachingSessionService.getallSessionSID(sid));
	}
//	
	

	@PostMapping("/savesession")
	public ResponseEntity<?> postMethodInsertSession(@RequestBody CoachingSessiondtoPost entity) {
		
		 if (entity.getSportID() == null || entity.getCoachID() == null) {
		        throw new IllegalArgumentException("SportID and CoachID must not be null");
		    }
		
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(coachingSessionService.savesession(entity));
	}
	
	
	
	
	
	
	
	
	
}
