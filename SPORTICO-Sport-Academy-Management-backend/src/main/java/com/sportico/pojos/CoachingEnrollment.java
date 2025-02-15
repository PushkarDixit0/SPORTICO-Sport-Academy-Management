package com.sportico.pojos;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "coachingEnrollment")
public class CoachingEnrollment extends BasicEntity {
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
private User UserID;
	
	@ManyToOne
	@JoinColumn(name = "coachingSessionId", referencedColumnName = "id")
private CoachingSession CoachingSessionID;
	
	@ManyToOne
	@JoinColumn(name = "sportsId" , referencedColumnName = "id")
private Sport SportID;
	
	@CreationTimestamp
	@Column(name = "enrollmentDate")
private LocalDate EnrollmentDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "paymentType")
private PaymentStatus PaymentType;
	@Enumerated(EnumType.STRING)
	@Column(name = "sportType")
private SportType SportType;
	
	
	
	public CoachingEnrollment() {
		
	}
	public User getUserID() {
		return UserID;
	}
	public void setUserID(User userID) {
		UserID = userID;
	}
	public CoachingSession getCoachingSessionID() {
		return CoachingSessionID;
	}
	public void setCoachingSessionID(CoachingSession coachingSessionID) {
		CoachingSessionID = coachingSessionID;
	}
	public Sport getSportID() {
		return SportID;
	}
	public void setSportID(Sport sportID) {
		SportID = sportID;
	}
	public LocalDate getEnrollmentDate() {
		return EnrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		EnrollmentDate = enrollmentDate;
	}
	public PaymentStatus getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(PaymentStatus paymentType) {
		PaymentType = paymentType;
	}
	public SportType getSportType() {
		return SportType;
	}
	public void setSportType(SportType sportType) {
		SportType = sportType;
	}
	@Override
	public String toString() {
		return "CoachingEnrollment [UserID=" + UserID + ", CoachingSessionID=" + CoachingSessionID + ", SportID="
				+ SportID + ", EnrollmentDate=" + EnrollmentDate + ", PaymentType=" + PaymentType + ", SportType="
				+ SportType + "]";
	}
	
}
