package com.sportico.DTO;

import java.time.LocalDate;

import com.sportico.pojos.Roles;

public class GetUserdto  extends Basedto{

		
		private String fname;
		
		private String lname;
		
		private String email;
				
		private LocalDate dob;
		
		private Roles role;

		
		public GetUserdto() {
		
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


		public LocalDate getDob() {
			return dob;
		}


		public void setDob(LocalDate dob) {
			this.dob = dob;
		}


		public Roles getRole() {
			return role;
		}


		public void setRole(Roles role) {
			this.role = role;
		}


		@Override
		public String toString() {
			return "GetUserdto [fname=" + fname + ", lname=" + lname + ", email=" + email + ", dob=" + dob + ", role="
					+ role + "]";
		}



}

