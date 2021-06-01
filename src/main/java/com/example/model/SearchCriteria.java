package com.example.model;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("deprecation")
public class SearchCriteria {
	@NotBlank(message = "username can't empty!")
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
