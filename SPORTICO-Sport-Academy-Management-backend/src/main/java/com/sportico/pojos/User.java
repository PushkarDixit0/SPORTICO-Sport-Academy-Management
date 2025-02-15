package com.sportico.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User extends BasicEntity {
	
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	@Column(name = "email")
	private String email;
	@Column(name = "mobNo")
	private String mobNo;
	@Column(name = "password")
	private String password;
	@Column(name = "dob")
	private LocalDate dob;
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Roles role;

	
	public User() {
	
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public String getMobNo() {
		return mobNo;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password + ", dob="
				+ dob + ", Role=" + role + "]";
	}

	
}
