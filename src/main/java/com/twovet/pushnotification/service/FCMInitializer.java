package com.twovet.pushnotification.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FCMInitializer {

	@Value("${app.firebase-configuration-file}")
	private String firebaseConfigPath;
	
	@PostConstruct
	public void initialize() {
		System.out.println("Start init");
		try {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
					.build();
			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
			System.out.println("Started init");
		} catch (Exception e) {
			System.out.println("Init Failed");
		}
	}
}
