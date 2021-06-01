package com.example.service.Implement;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.Implement.UserDao;
import com.example.model.AppUser;
import com.example.model.ParamForm;
import com.example.model.UserRole;
import com.example.service.IAppUserService;

@Service
public class AppUserService implements IAppUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public AppUser loadUserByUsername(Integer id) {
		return userDao.findByUsername(id);
	}

	@Override
	public List<AppUser> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public int deleteUser(List<AppUser> users) throws Exception{
		// TODO Auto-generated method stub
		return userDao.deleteUser(users);
	}

	@Override
	public int updateUser(EntityManager em, ParamForm param) throws Exception {
		// TODO Auto-generated method stub
		return userDao.updateUser(em, param);
	}

	@Override
	public List<UserRole> getUserRolesByUserId(EntityManager em, int id) {
		// TODO Auto-generated method stub
		return userDao.getUserRolesByUserId(em, id);
	}

}
