package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.twovet.base.constant.ViewNameConstants;

@Controller
public class MainController {
	@GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin") 
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }
    @GetMapping("/user") 
    public String user() {
        return new ViewNameConstants().USER;
    }
    
//    @GetMapping("/pages") 
//    public String home() {
//        return new ViewNameConstants().HOME;
//    }
}
