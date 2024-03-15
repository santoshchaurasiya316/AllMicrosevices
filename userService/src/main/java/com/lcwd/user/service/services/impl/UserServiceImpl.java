package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.payload.ApiResponse;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User savUser(User user) {

		String randomuserid = UUID.randomUUID().toString();
		user.setUserId(randomuserid);
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

    @Override
    public User getUser(String userId) {
	   
	User user = repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id is not found on server:!!"+userId));
		                                                     //localhost:8083
	Rating[] ratingsofuser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
	  
	List<Rating> ratings = Arrays.stream(ratingsofuser).toList();
	
	logger.info("{} ",ratingsofuser); 
	 
	List<Rating> ratinglist = ratings.stream().map(rating -> {
	    	   
	   //api call to hotel service to get the hotel info by hotelId from rating
	  //IMPOTANT**---we'll create our own Hotel class in userservice instead of using Hotel of hotelservice
		//for making our code indepedent(loosely coupled)// 	           //localhost:8082
//    ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class); 
	    
	    Hotel hotel =hotelService.getHotel(rating.getHotelId());                                     // forEntity.getBody();
//	    logger.info("response status code: {}", forEntity.getStatusCode());
	    	
	    //set the hotel to rating
	      rating.setHotel(hotel);  
	    	  
	    //return  the rating
	      return rating;  
	    	  
	    }).collect(Collectors.toList());
	   
	   user.setRatings(ratinglist);
	   
	   return user;
	}

}
