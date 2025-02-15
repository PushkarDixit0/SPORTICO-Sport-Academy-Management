package com.sportico.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coachfeedback")
public class CoachFeedback extends BasicEntity {

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User UserID;
	@ManyToOne
	@JoinColumn(name = "coachId", referencedColumnName = "id")
	private User CoachID;
	
	@Column(name = "message",length = 1000)
	private String Message;
	@Column(name = "date")
	private LocalDate Date;

	public CoachFeedback() {
	}




	public User getUserID() {
		return UserID;
	}




	public void setUserID(User userID) {
		UserID = userID;
	}




	public User getCoachID() {
		return CoachID;
	}




	public void setCoachID(User coachID) {
		CoachID = coachID;
	}





	public String getMessage() {
		return Message;
	}




	public void setMessage(String message) {
		Message = message;
	}




	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}




	@Override
	public String toString() {
		return "CoachFeedback [UserID=" + UserID + ", CoachID=" + CoachID + ", Message=" + Message + ", Date=" + Date
				+ "]";
	}

	
}
