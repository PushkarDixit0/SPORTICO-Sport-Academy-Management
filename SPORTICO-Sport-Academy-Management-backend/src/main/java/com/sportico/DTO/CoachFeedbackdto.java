package com.sportico.DTO;

import java.time.LocalDate;

public class CoachFeedbackdto extends Basedto {

private Long User_ID;

private Long Coach_ID;

private String Message;

private LocalDate Date;



public CoachFeedbackdto() {

}


public Long getUser_ID() {
	return User_ID;
}

public void setUser_ID(Long user_ID) {
	User_ID = user_ID;
}

public Long getCoach_ID() {
	return Coach_ID;
}

public void setCoach_ID(Long coach_ID) {
	Coach_ID = coach_ID;
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
	return "CoachFeedbackdto [User_ID=" + User_ID + ", Coach_ID=" + Coach_ID + ", Message=" + Message + ", Date=" + Date
			+ "]";
}




}
