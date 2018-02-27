package com.maerskdigital.task.domain;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	String username;
	
	String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}