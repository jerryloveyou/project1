package com.peng.entity;

import java.io.Serializable;

public class User implements Serializable{
	    private int uid;
		
		private String userName;
		
		private String userPass;
		
		private int sex;
		
		private String email;
		
		private int education;
		
		private String hobbys;
		
		private String description;
		
		

		public User() {}



		public User(int uid, String userName, String userPass, int sex,
				String email, int education, String hobbys, String description) {
			super();
			this.uid = uid;
			this.userName = userName;
			this.userPass = userPass;
			this.sex = sex;
			this.email = email;
			this.education = education;
			this.hobbys = hobbys;
			this.description = description;
		}



		public int getUid() {
			return uid;
		}



		public void setUid(int uid) {
			this.uid = uid;
		}



		public String getUserName() {
			return userName;
		}



		public void setUserName(String userName) {
			this.userName = userName;
		}



		public String getUserPass() {
			return userPass;
		}



		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}



		public int getSex() {
			return sex;
		}



		public void setSex(int sex) {
			this.sex = sex;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public int getEducation() {
			return education;
		}



		public void setEducation(int education) {
			this.education = education;
		}



		public String getHobbys() {
			return hobbys;
		}



		public void setHobbys(String hobbys) {
			this.hobbys = hobbys;
		}



		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		


		

}
