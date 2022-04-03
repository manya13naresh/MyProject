package com.example.Hotel_Booking.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hotel_Booking.Model.HotelBookingModelJwt;

public interface HotelBookingRepo extends CrudRepository<HotelBookingModelJwt,Integer> {
	

 HotelBookingModelJwt findByName(String name);


}
