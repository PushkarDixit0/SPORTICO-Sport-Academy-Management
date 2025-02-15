package com.sportico.DTO;

import java.time.LocalDate;

public class APIResponse {

	private String Message;
	private LocalDate date;
	
	public APIResponse(String message) {
	
		Message = message;
		this.date = LocalDate.now();
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "APIResponse [Message=" + Message + ", date=" + date + "]";
	}
	
	
	
}
