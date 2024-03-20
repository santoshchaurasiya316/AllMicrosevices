package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lcwd.user.service.entities.Hotel;

@FeignClient("HOTEL-SERVICE")  //we use Feign client to make Http request(By creating interface,anotate with @FiegnClient) 
                                        //like-we were doing in RestTmplte
public interface HotelService {

	@GetMapping("hotel/{hotelId}")
   public Hotel getHotel(@PathVariable String hotelId );
}
