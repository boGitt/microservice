package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.feign.UserClient;

@RestController
public class MovieController {
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id){
		System.out.println("feign");
		return userClient.findById(id);
	}
	
}
