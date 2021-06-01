package com.twovet.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.Navigation.service.Implement.MenuService;
import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.constant.ViewNameConstants;

@Controller
public class HomeController extends BaseController{
	
	@Autowired
	MenuService menuService;
//	@GetMapping("/home")
//	public String demo(Model model, HttpSession session) {
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		new FunctionCommon(attr, session);
//		model.addAttribute("name", "Cấu hình tài khoản");
//		return ViewNameConstants.HOME;
//	}
	
	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
		
	}
	@Override
	@GetMapping("/home")
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, httpSession);
		model.addAttribute("name", "Cấu hình tài khoản");
		return ViewNameConstants.HOME;
	}
}
