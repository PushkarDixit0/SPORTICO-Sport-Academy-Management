package com.sportico.DTO;

import com.sportico.pojos.SportType;

public class SportDTO  {
	
	private Long id;
    private String name; 
    private SportType type;
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SportType getType() {
		return type;
	}
	public void setType(SportType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SportDTO [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
    
    
}
