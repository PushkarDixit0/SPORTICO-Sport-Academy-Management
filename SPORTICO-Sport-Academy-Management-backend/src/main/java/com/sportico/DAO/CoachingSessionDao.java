package com.sportico.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportico.pojos.CoachingSession;
import com.sportico.pojos.SessionBatch;
import com.sportico.pojos.Sport;
import com.sportico.pojos.User;

public interface CoachingSessionDao extends JpaRepository<CoachingSession, Long> {
	
// 	List<CoachingSession> findByCoachID(User coachID);;
	  @Query("SELECT cs FROM CoachingSession cs WHERE cs.CoachID = :coach")
	    List<CoachingSession> findSessionsByCoachId(@Param("coach") User coachId);

	  @Query("SELECT cs FROM CoachingSession cs WHERE cs.SportID = :sport")
	    List<CoachingSession> findSessionsBySportId(@Param("sport") Sport SportID);

	  
	  @Query("SELECT c FROM CoachingSession c WHERE c.Session_Branch = :sessionBatch AND c.SportID = :sportId")
	  CoachingSession findBySessionBatchAndSportId(@Param("sessionBatch") SessionBatch sessionBatch, @Param("sportId") Sport sportId);

}
