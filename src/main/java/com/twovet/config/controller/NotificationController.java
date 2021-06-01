package com.twovet.config.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.twovet.base.constant.ViewNameConstants;

@Controller
@RequestMapping("/config")
public class NotificationController {

	@GetMapping("/notif")
	public String demo(Model model) {
		model.addAttribute("name", "Cấu hình tài khoản");
		return ViewNameConstants.CONFIG_NOTIF;
	}
	
	 @PostMapping("/notif/sendMessage")
	 public ResponseEntity<?> getAllUser() throws IOException{
		 
//		 FileInputStream refreshToken = new FileInputStream("E:/FireBase/namnv-firebase-firebase-adminsdk-f2585-c1d44507aa.json.json");
//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setCredentials(GoogleCredentials.fromStream(refreshToken))
//				.setDatabaseUrl("https://namnv-firebase.firebaseio.com/")
//				.build();
//		if (FirebaseApp.getApps() == null) {
//			FirebaseApp.initializeApp(options);
//		}
		 
		 String registrationToken = "AAAATuCv4z4:APA91bH327xxkPAgvb5-1gWGMKs9j5wvT5LXfC-2DzPQCGpYL2Irv-7o7RGIffeuSKqezT9gTjXr49DOsO5rHoCBLls0P7ZUSAimJZyK7CwD-EDsuCw-7YQMHW94t7CGURJJm43yqzOf";
		 Message message  = Message.builder()
				 .putData("FirstName", "Nam")
				 .putData("LastName", "Nguyen")
				 .setToken(registrationToken)
//				 .setTopic("namnv")
				 .build();
		 String reponse ="";
		try {
			reponse = FirebaseMessaging.getInstance().send(message);
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return ResponseEntity.ok(reponse);
	 }
}
