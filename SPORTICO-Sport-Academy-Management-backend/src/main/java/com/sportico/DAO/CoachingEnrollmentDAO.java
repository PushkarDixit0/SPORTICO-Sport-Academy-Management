package com.sportico.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportico.pojos.CoachingEnrollment;

public interface CoachingEnrollmentDAO extends JpaRepository<CoachingEnrollment, Long> {

}
