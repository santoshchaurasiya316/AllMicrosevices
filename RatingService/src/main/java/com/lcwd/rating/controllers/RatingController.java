package com.lcwd.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	
	@Autowired
	private RatingService ratingService;

	//create rating
	@PostMapping
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
	
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}
	
	//getAll
	@GetMapping
	public ResponseEntity<List<Rating>> getAll(){
		return ResponseEntity.ok(ratingService.getAll());
	}
	
	//getByUSerID
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getByuserid(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}
	
	//getByHotelIsd
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getByhotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
}
