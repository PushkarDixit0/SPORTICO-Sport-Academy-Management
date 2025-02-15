package com.sportico.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TournamentDTO {

	private Long tournamentId;
    private String tournamentName;
    private String tournamentDescription;
    private String tournamentVenue;
    private String sportName; 
    private LocalDate tournamentDate;
    private LocalTime tournamentTime;
    private int slots;
    
    private double price;  


    public TournamentDTO() {
    }
    
    

    public Long getTournamentId() {
		return tournamentId;
	}



	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentDescription() {
        return tournamentDescription;
    }

    public void setTournamentDescription(String tournamentDescription) {
        this.tournamentDescription = tournamentDescription;
    }

    public String getTournamentVenue() {
        return tournamentVenue;
    }

    public void setTournamentVenue(String tournamentVenue) {
        this.tournamentVenue = tournamentVenue;
    }
    
    public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public LocalDate getTournamentDate() {
        return tournamentDate;
    }

    public void setTournamentDate(LocalDate tournamentDate) {
        this.tournamentDate = tournamentDate;
    }

    public LocalTime getTournamentTime() {
        return tournamentTime;
    }

    public void setTournamentTime(LocalTime tournamentTime) {
        this.tournamentTime = tournamentTime;
    }

    
    public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	@Override
    public String toString() {
        return "TournamentDTO [tournamentName=" + tournamentName + ", tournamentDescription=" + tournamentDescription
                + ", tournamentVenue=" + tournamentVenue + ", sportName=" + sportName + ", tournamentDate="
                + tournamentDate + ", tournamentTime=" + tournamentTime + "]";
    }
}
