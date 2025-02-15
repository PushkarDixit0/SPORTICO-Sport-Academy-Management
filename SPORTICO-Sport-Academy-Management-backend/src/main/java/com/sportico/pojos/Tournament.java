package com.sportico.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament extends BasicEntity {

	@Column(name="name ")
	private String tournamentName;
	
	@Column(name="description")
	private String tournamentDescription;
	
	@Column(name="venue")
	private String tournamentVenue;
	
	@Column(name = "price", nullable = false)
	private double price;  // Added price field
	
    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false) 
    private Sport sport;
	
	@Column(name="date")
	private LocalDate tournamentDate;
	
	private LocalTime tournamentTime;
	
	@Column(name = "slots")
	private int slots;
	
	

	public Tournament() {
		super();
		// TODO Auto-generated constructor stub
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

	public Sport getSport() {
		return sport;
	}


	public void setSport(Sport sport) {
		this.sport = sport;
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



	public int getSlots() {
		return slots;
	}



	public void setSlots(int slots) {
		this.slots = slots;
	}



	public void setTournamentTime(LocalTime tournamentTime) {
		this.tournamentTime = tournamentTime;
	}

	public double getPrice() {
	    return price;
	}

	public void setPrice(double price) {
	    this.price = price;
	}



	@Override
	public String toString() {
	    return "Tournament [tournamentName=" + tournamentName + ", tournamentDescription=" + tournamentDescription
	            + ", tournamentVenue=" + tournamentVenue + ", sport=" + (sport != null ? sport.getId() : null) 
	            + ", tournamentDate=" + tournamentDate + ", tournamentTime=" + tournamentTime + ", price=" + price + "]";
	}



	
}
