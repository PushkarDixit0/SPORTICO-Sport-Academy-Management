package com.sportico.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportico.DTO.APIResponse;
import com.sportico.DTO.CoachingEnrollmentdto;
import com.sportico.Service.CoachingEnrollmentService;
import com.sportico.pojos.SessionBatch;




@RestController
@RequestMapping("/CoachingEnroll")
public class CoachingEnrollmentController {

	@Autowired
	public CoachingEnrollmentService coachingEnrollmentService;

	public CoachingEnrollmentController() {
		super();
		System.out.println("Coaching Enrollment running");
	}
	
	
	@GetMapping("/GetAllEnroll")
	public ResponseEntity<?> getMethodAllEnroll() {
		return ResponseEntity.ok(coachingEnrollmentService.getallEnrollment());
	}
	
	@GetMapping("/GetAllEnrollById/{userId}")
	public ResponseEntity<?> getMethodAllEnrollById(@PathVariable Long userId) {
		return ResponseEntity.ok(coachingEnrollmentService.getallEnrollmentById(userId));
	}
	
	@PostMapping("/SaveEnroll")
	public ResponseEntity<?> PostMethodEnroll(@RequestBody CoachingEnrollmentdto coachingEnrollmentdto) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(coachingEnrollmentService.saveCoachinEnroll(coachingEnrollmentdto)));
	}
	
	
	@DeleteMapping("/DeleteEnroll/{EnrollId}")
	public ResponseEntity<?> deleteMethodEnroll(@PathVariable Long EnrollId) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(coachingEnrollmentService.deleteEnrollment(EnrollId)));
	}
	
	
	@PutMapping("updateEnrollSession/{EnrollId}/{Session}")
	public ResponseEntity<?> putMethodUpdateEnroll(@PathVariable Long EnrollId,@PathVariable SessionBatch Session) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(coachingEnrollmentService.updateEnrollment(EnrollId,Session)));
	}
	
	@PatchMapping("updateEnrollPayment/{EnrollId}")
	public ResponseEntity<?> patchMethodUpdateEnrollPayment(@PathVariable Long EnrollId) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(coachingEnrollmentService.updateEnrollmentPayment(EnrollId)));
	}
	
	
}
