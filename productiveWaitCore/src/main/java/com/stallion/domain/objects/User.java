package com.stallion.domain.objects;

import java.sql.Timestamp;

public class User {
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Timestamp create_dt;

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Timestamp create_dt) {
		this.create_dt = create_dt;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", email=" + email + ", create_dt=" + create_dt + "]";
	}

}
