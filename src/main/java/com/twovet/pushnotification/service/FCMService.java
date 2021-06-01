package com.twovet.pushnotification.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.twovet.pushnotification.dto.PnsRequest;

@Service
public class FCMService {

	public String pushNotification(PnsRequest pnsRequest) {
        ApnsConfig apnsConfig = getApnsConfig(pnsRequest.getTopic());
		Message message = Message.builder()
				.putData("content", pnsRequest.getContent())
				.setToken(pnsRequest.getFcmToken())
				.setApnsConfig(apnsConfig)
				.setNotification( new Notification(pnsRequest.getTitle(), pnsRequest.getContent()))
				.build();
		String response = null;
		try {
			response = FirebaseMessaging.getInstance().sendAsync(message).get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }
}
