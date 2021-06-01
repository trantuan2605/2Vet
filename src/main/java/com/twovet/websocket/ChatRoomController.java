package com.twovet.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demoSk")
public class ChatRoomController {

	@GetMapping(value = {"", "/"})
	public String init() {
		return "pages/chat";
	}
}
