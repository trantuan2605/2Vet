package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.model.AppUser;
import com.example.model.ParamForm;
import com.example.model.UserRole;

public interface IUserDao {
	public AppUser findByUsername(Integer id);
	AppUser findByEmail(String username);
	List<AppUser> getAllUser();
	int deleteUser(List<AppUser> users) throws Exception;
	int updateUser(EntityManager em, ParamForm param) throws Exception;
	List<UserRole> getUserRolesByUserId(EntityManager em, int id);
}
