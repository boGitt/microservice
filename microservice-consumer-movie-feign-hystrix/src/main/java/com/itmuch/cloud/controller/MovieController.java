package com.itmuch.cloud.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.feign.UserClient;

@RestController
public class MovieController {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private UserClient userClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id){
		
		logger.info("movie feign hystrix movieController findById was called.id was : {}",id);
		tracer.addTag("movieTag", "id:"+id );
		return userClient.findById(id);
	}
	
}
