package com.lcwd.hotel.repository1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.hotel.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
