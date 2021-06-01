package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AjaxResponseBody;
import com.example.model.SearchCriteria;
import com.example.model.User;
import com.example.services.UserServices;

@RestController
public class SearchController {
	
	UserServices userService;

	@Autowired
	public void setUserService(UserServices userService) {
		this.userService = userService;
	}
	
	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax( @Valid @RequestBody SearchCriteria search, Errors errors){
		AjaxResponseBody result = new AjaxResponseBody();
		if (errors.hasErrors()) {
			result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);
		}
		List<User> users = userService.findByUserNameOrEmail(search.getUserName());
		if (users.isEmpty()) {
			result.setMsg("no user found");
		} else {
			result.setMsg("success");
		}
		result.setResult(users);
		
		return ResponseEntity.ok(result);
	}
	
	

}
