package com.twovet.config.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.AppRole;
import com.example.model.AppUser;
import com.example.model.UserResultBean;
import com.example.service.Implement.AppUserService;
import com.twovet.base.constant.ViewNameConstants;

@Controller
@RequestMapping("/config")
public class ConfigUserController {

	@Autowired
	private AppUserService userService;
	
	@GetMapping("/user")
	public String demo(Model model) {
		model.addAttribute("name", "Cấu hình tài khoản");
		return ViewNameConstants.CONFIG_USER;
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
}
