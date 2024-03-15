package com.lcwd.hotel.services;

import java.util.List;
import com.lcwd.hotel.entities.Hotel;
      

 //we use interface to provide implementation in my own way and can also at diff. place with diff. logic
//but in case of class we can only use implemented logic
public interface HotelService {
	
	//create 
	Hotel create(Hotel hotel);
	
	//get All
	List<Hotel> getAll();
	
	//get single
	Hotel get(String id);
}
