package com.example.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AppUser;
import com.example.model.UserResultBean;
import com.example.service.Implement.AppUserService;
import com.twovet.base.common.ResultBean;

@RestController
@RequestMapping("api/user")
public class UserRestController {
	@Autowired
	private AppUserService userService;
	
//	@GetMapping("/")
//	public String initial() {
//		return new ViewNameConstants().USER;
//	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listUser(){
		List<AppUser> user = userService.getAllUser();
		List<UserResultBean> results = new ArrayList<>();
		for (AppUser appUser : user) {
			UserResultBean resultObj = new UserResultBean();
//			resultObj.setRolename(rolename);
			resultObj.setStatus(appUser.getEnabled() == 1 ? "Active" : "Off");
			resultObj.setUsername(appUser.getUsername());
			results.add(resultObj);
		}
		ResultBean<UserResultBean> data = new ResultBean<>();
		data.setMessage("OK");
		data.setResults(results);
		return ResponseEntity.ok(results);
	}

}
