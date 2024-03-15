package com.lcwd.rating.service;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {

	
	//create
	
	Rating create(Rating rating);
	
	//getAll ratings
	
	List<Rating> getAll();
	
	//getAll by userId
	
	List<Rating> getRatingByUserId(String userId);
	
	//getAll by hotel
	
	List<Rating> getRatingByHotelId(String hotelId);
}
