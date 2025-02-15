package com.sportico.Service;

import java.util.List;

import com.sportico.DTO.CoachingEnrollmentdto;
import com.sportico.pojos.SessionBatch;

public interface CoachingEnrollmentService {

	List<CoachingEnrollmentdto> getallEnrollment();

	String saveCoachinEnroll(CoachingEnrollmentdto coachingEnrollmentdto);

	String deleteEnrollment(Long enrollId);

	String updateEnrollment(Long enrollId,SessionBatch Session);

	String updateEnrollmentPayment(Long enrollId);

	List<CoachingEnrollmentdto> getallEnrollmentById(Long userId);

	
	
	
	
}
