package com.sportico.DTO;

import java.time.LocalDate;

public class PostUserdto extends Basedto{

		private String fname;
		
		private String lname;
		
		private String email;
		
		private String password;
		
		private String mobNo;
				
		private LocalDate dob;


		public PostUserdto() {
			super();
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




		public String getMobNo() {
			return mobNo;
		}



		public void setMobNo(String mobNo) {
			this.mobNo = mobNo;
		}



		public LocalDate getDob() {
			return dob;
		}



		public void setDob(LocalDate dob) {
			this.dob = dob;
		}



		@Override
		public String toString() {
			return "PostUserdto [fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password
					+ ", mobNo=" + mobNo + ", dob=" + dob + "]";
		}

		
}