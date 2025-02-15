package com.sportico.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.CoachingEnrollment;

public interface CoachingEnrollmentDto extends JpaRepository<CoachingEnrollment, Long> {

}
