package com.example.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_role")
public class AppRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8852123721635337168L;

	@Id
	@Column(name = "role_id", nullable = false)
	private int roleId;
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(Set<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	@Column(name = "role_name", length = 30)
	private String roleName;
	
	@ManyToMany(mappedBy = "appRoles", fetch = FetchType.LAZY)
	private Set<AppUser> appUsers;
	
}
