package com.sportico.DTO;

import com.sportico.pojos.SessionBatch;

public class CoachingSessiondto extends Basedto{
	
	private String Description;
	
	private String Venue;
	
	private SessionBatch Session_Branch;
		
	private Long SportID;
	
	private Long CoachID;

	
	
	public CoachingSessiondto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getDescription() {
		return Description;
	}



	public void setDescription(String description) {
		Description = description;
	}



	public String getVenue() {
		return Venue;
	}



	public void setVenue(String venue) {
		Venue = venue;
	}



	public SessionBatch getSession_Branch() {
		return Session_Branch;
	}



	public void setSession_Branch(SessionBatch session_Branch) {
		Session_Branch = session_Branch;
	}




	public Long getSportID() {
		return SportID;
	}



	public void setSportID(Long sportID) {
		SportID = sportID;
	}



	public Long getCoachID() {
		return CoachID;
	}



	public void setCoachID(Long coachID) {
		CoachID = coachID;
	}



	@Override
	public String toString() {
		return "CoachingSessiondto [Description=" + Description + ", Venue=" + Venue + ", Session_Branch="
				+ Session_Branch + ", SportID=" + SportID + ", CoachID=" + CoachID + "]";
	}



	
	
	
	
	
}
