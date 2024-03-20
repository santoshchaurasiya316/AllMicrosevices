package com.lcwd.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	//create
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		User savUser = service.savUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savUser);
	}
	//get single user
	
	int retrycount=1;
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleuser(@PathVariable String userId){
		//above return type and fallback method(created below)retun type should be same
	    logger.info("Retry count{}:",retrycount);
		retrycount++;
		User userbyId = service.getUser(userId);
		return ResponseEntity.ok(userbyId);
	}
	
	//***Creating fallback method for circuit breaker
	
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		
		logger.info("fallback is executed because service is down:",ex.getMessage());
		
		User user = User.builder()
		.email("dummy@gmail.com")
		.name("dummy")
		.about("This user is created bcoz some service is down")
		.userId("12344")
		.build();
		return new ResponseEntity<>(user,HttpStatus.OK);	
	}
	
	
	
	//getAllUser
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
	  
		List<User> allUser = service.getAllUser();
		return ResponseEntity.ok(allUser);
	}	
}
