package com.sportico.pojos;

public enum SessionBatch {
MORNING("8:00 AM to 11:00 AM"),AFTERNOON("1:00 PM to 4:00 PM"),EVENING("6:00 PM to 9:00 PM");

	private final String timeSlot;
	
	
SessionBatch(String timeSlot) {
	 this.timeSlot = timeSlot;
}


public String getTimeSlotByBranch() {
	return timeSlot;
}


}
