package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.twovet.base.constant.ViewNameConstants;

@Controller
public class IndexController {
//	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		String message = "Hello Spring Boot integrate JSP";
		model.addAttribute("message", message);
		return "ajax";
	}
	
	@RequestMapping (value = "/customer/list", method = RequestMethod.GET)
	public String Customer() {
		return new ViewNameConstants().LIST_CUSTOMER;
	}
	
	
//	@RequestMapping (value = "/login", method = RequestMethod.GET)
//	public String Login() {
//		return new ViewNameConstants().LOGIN;
//	}
}
