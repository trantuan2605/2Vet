package com.twovet.base.common;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {
	public static int start = 0;
	public static int size = 10;
	public abstract void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession);
	@GetMapping(value = {"", "/"})
	public abstract String initScreen(Model model, HttpSession httpSession);
	
}
