package com.example.model;

import java.util.List;

public class UserResultBean {
	private String username;
	private List<String> rolename;
	private String status;
	private int userId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRolename() {
		return rolename;
	}

	public void setRolename(List<String> rolename) {
		this.rolename = rolename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
