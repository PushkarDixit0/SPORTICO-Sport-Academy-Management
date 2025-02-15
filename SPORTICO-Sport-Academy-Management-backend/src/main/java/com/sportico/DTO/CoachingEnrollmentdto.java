package com.sportico.DTO;

import com.sportico.pojos.PaymentStatus;
import com.sportico.pojos.SportType;

public class CoachingEnrollmentdto  extends Basedto {
	
private Long userId;
	
private Long coachingSessionId;

private Long sportId;
	
private PaymentStatus paymentType;

private SportType sportType;

public CoachingEnrollmentdto() {

	
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Long getCoachingSessionId() {
	return coachingSessionId;
}

public void setCoachingSessionId(Long coachingSessionId) {
	this.coachingSessionId = coachingSessionId;
}

public Long getSportId() {
	return sportId;
}

public void setSportId(Long sportId) {
	this.sportId = sportId;
}

public PaymentStatus getPaymentType() {
	return paymentType;
}

public void setPaymentType(PaymentStatus paymentType) {
	this.paymentType = paymentType;
}

public SportType getSportType() {
	return sportType;
}

public void setSportType(SportType sportType) {
	this.sportType = sportType;
}

@Override
public String toString() {
	return "CoachingEnrollmentdto [userId=" + userId + ", coachingSessionId=" + coachingSessionId + ", sportId="
			+ sportId + ", paymentType=" + paymentType + ", sportType=" + sportType + "]";
}



}
