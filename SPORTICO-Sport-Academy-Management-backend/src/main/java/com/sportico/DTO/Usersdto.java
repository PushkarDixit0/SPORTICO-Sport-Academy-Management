package com.sportico.DTO;

import java.sql.Date;

import com.sportico.pojos.Roles;

public class Usersdto {

		
		private String fname;
		
		private String lname;
		
		private String email;
		
		private String mobNo;
		
		private String password;
		
		private Date dob;
		
		private Roles role;

		public Usersdto() {
		
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

		public String getMobNo() {
			return mobNo;
		}

		public void setMobNo(String mobNo) {
			this.mobNo = mobNo;
		}

		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Date getDob() {
			return dob;
		}


		public void setDob(Date dob) {
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
			return "Usersdto [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password
					+ ", dob=" + dob + ", role=" + role + "]";
		}
		
}
