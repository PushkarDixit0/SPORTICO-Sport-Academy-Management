package com.sportico.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coachingSession")

public class CoachingSession extends BasicEntity {

	@Column(name = "description")
	private String Description;
	@Column(name = "venue")
	private String Venue;
	@Enumerated(EnumType.STRING)
	@Column(name = "sessionBatch")
	private SessionBatch Session_Branch;
	@Column(name = "sessionTimming")
	private String Session_TimeSlot;
	
	@ManyToOne
	@JoinColumn(name = "sportsId" , referencedColumnName = "id")
	private Sport SportID;
	
	@ManyToOne
	@JoinColumn(name = "coachId", referencedColumnName = "id")
	private User CoachID;

	public CoachingSession() {
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

	public String getSession_TimeSlot() {
		return Session_TimeSlot;
	}

	public void setSession_TimeSlot(String session_TimeSlot) {
		Session_TimeSlot = session_TimeSlot;
	}



	public Sport getSportID() {
		return SportID;
	}

	public void setSportID(Sport sportID) {
		SportID = sportID;
	}

	public User getCoachID() {
		return CoachID;
	}

	public void setCoachID(User coachID) {
		CoachID = coachID;
	}

	@Override
	public String toString() {
		return "CoachingSession [Description=" + Description + ", Venue=" + Venue + ", Session_Branch=" + Session_Branch
				+ ", Session_TimeSlot=" + Session_TimeSlot + ", SportID=" + SportID + ", Coach_ID=" + CoachID + "]";
	}

	

	

}
