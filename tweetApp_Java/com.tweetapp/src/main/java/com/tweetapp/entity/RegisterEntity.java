package com.tweetapp.entity;

import java.time.LocalDate;

public class RegisterEntity {

	private String first_name;
	private String last_name;
	private String gender;
	private LocalDate date_of_birth;
	private String email;
	private String password;
	public RegisterEntity() {
		super();
	}
	public RegisterEntity(String first_name, String last_name, String gender, LocalDate date_of_birth, String email,
			String password) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.date_of_birth = date_of_birth;
		this.email = email;
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
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
	@Override
	public String toString() {
		return "RegisterEntity [first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender
				+ ", date_of_birth=" + date_of_birth + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
