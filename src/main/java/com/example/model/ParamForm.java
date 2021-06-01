package com.example.model;

import java.io.Serializable;
import java.util.List;

public class ParamForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String userName;
	private List<AppUser> appUser;
	private List<String> rolename;
	private String status;
	private int userId;
	private int role;
	private String password;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<AppUser> getAppUser() {
		return appUser;
	}

	public void setAppUser(List<AppUser> appUser) {
		this.appUser = appUser;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
