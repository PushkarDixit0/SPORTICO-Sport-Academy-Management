package com.sportico.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportico.pojos.CoachFeedback;
import com.sportico.pojos.User;

public interface CoachFeedbackDao extends JpaRepository<CoachFeedback, Long>{

	@Query("SELECT fed FROM CoachFeedback fed WHERE fed.UserID= :userId")
	List<CoachFeedback> findByUserID(@Param("userId") User userID);
	
	
	@Query("SELECT fed FROM CoachFeedback fed WHERE fed.CoachID= :coachId")
	List<CoachFeedback> findByCoachFeedback(@Param("coachId") User coach);
	
	
}
