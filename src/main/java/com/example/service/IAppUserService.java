package com.example.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.model.AppUser;
import com.example.model.ParamForm;
import com.example.model.UserRole;

public interface IAppUserService {

	public AppUser loadUserByUsername(Integer id);
	
	public List<AppUser> getAllUser();
	
	public int deleteUser(List<AppUser> users) throws Exception;
	
	public int updateUser(EntityManager em, ParamForm param) throws Exception;
	
	public List<UserRole> getUserRolesByUserId(EntityManager em, int id);
	
}
