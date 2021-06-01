package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class UserServices {
	private List<User> users;
	public List<User> findByUserNameOrEmail(String userName){
				
		List<User> result = users.stream()
				.filter(x -> x.getUserName().equalsIgnoreCase(userName))
				.collect(Collectors.toList());
		return result;
	}
	
	@PostConstruct
	private void initDataForTesting() {
		users = new ArrayList<User>();
		
		User user1 = new User("namnv", "123456", "namnv@rikkeisoft");
		User user2 = new User("namnv2", "123456", "namnv2@rikkeisoft");
		User user3 = new User("namnv3", "123456", "namnv3@rikkeisoft");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}
}
