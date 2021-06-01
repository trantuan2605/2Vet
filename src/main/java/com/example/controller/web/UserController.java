package com.example.controller.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.AppRole;
import com.example.model.AppUser;
import com.example.model.ParamForm;
import com.example.model.UserResultBean;
import com.example.service.Implement.AppUserService;
import com.twovet.base.common.ResultBean;
import com.twovet.base.constant.ViewNameConstants;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private AppUserService userService;
	
	@Autowired
	private EntityManagerFactory emf;
	
	 @GetMapping("/") 
	    public String initScreen() {
	        return new ViewNameConstants().USER;
	    }
	 
	 @PostMapping("/list")
	 public ResponseEntity<?> getAllUser(){
		 List<UserResultBean> results = new ArrayList<>();
		 List<AppUser> users = userService.getAllUser();
		 for (AppUser appUser : users) {
			UserResultBean user = new UserResultBean();
//			user.setRolename(appUser.getAppRoles());
			user.setUsername(appUser.getUsername());
			user.setStatus(appUser.getEnabled() ==1 ? "Active" : "Off");
			user.setUserId(appUser.getId());
			Set<AppRole> roles = new HashSet<>();
			roles = appUser.getAppRoles();
			List<String> roleNames = new ArrayList<>();
			for (AppRole role : roles) {
				roleNames.add(role.getRoleName());
			}
			user.setRolename(roleNames);
			results.add(user);
		}
		 return ResponseEntity.ok(results);
	 }
	 
	 @PostMapping("/delete")
	 public ResponseEntity<?> delUser(@RequestBody ParamForm params) throws Exception{
		 ResultBean<UserResultBean> result = new ResultBean<>();
		 List<AppUser> users = params.getAppUser();
		 userService.deleteUser(users);
		 result.setMessage("OK");
		 return ResponseEntity.ok(result);
	 }
	 
	 @PostMapping("/edit")
	 public ResponseEntity<?> editUser(@RequestBody ParamForm param) throws Exception{
		 ResultBean<UserResultBean> result = new ResultBean<>();
		 AppUser user = new AppUser();
		 user = userService.loadUserByUsername(param.getUserId());
		 result.setMessage("OK");
		 UserResultBean userResult = new UserResultBean();
		 List<String> rolenames = new ArrayList<>();
		 Set<AppRole> roles =  new HashSet<>();
		 roles = user.getAppRoles();
		 for (AppRole appRole : roles) {
			rolenames.add(appRole.getRoleName());
		}
//		 userResult.setRolename(us);
		 userResult.setRolename(rolenames);
		 userResult.setStatus(user.getEnabled() != 1 ? "Active" :"Off");
		 userResult.setUsername(user.getUsername());
		 userResult.setUserId(user.getId());
		 result.setResult(userResult);
		 return ResponseEntity.ok(result);
	 }
	 
	 @PostMapping("/save")
	 public ResponseEntity<?> saveUser(@RequestBody ParamForm param) throws Exception{
		 ResultBean<UserResultBean> result = new ResultBean<>();
		 EntityManager em = emf.createEntityManager();
		 userService.updateUser(em, param);
		 result.setMessage("OK");
		 return ResponseEntity.ok(result);
	 }
}
