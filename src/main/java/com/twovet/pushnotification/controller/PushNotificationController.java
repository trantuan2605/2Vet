package com.twovet.pushnotification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twovet.pushnotification.dto.PnsRequest;
import com.twovet.pushnotification.service.FCMService;

@RestController
public class PushNotificationController {

	@Autowired
	private FCMService fcmService;
	
	@PostMapping("/notification")
	public String sendSammpleNotification(@RequestBody PnsRequest pnsRequest) {
		return fcmService.pushNotification(pnsRequest);
	}
}
