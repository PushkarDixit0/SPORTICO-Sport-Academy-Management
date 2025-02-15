package com.sportico.DTO;

import com.sportico.pojos.Roles;

public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Roles role;

    public LoginResponseDTO(Long id, String name, String email, Roles roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = roles;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

    
}
