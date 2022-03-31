package com.example.Hotel_Booking.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hotel_Booking.Model.HotelBookingModel;

public interface HotelBookingRepo extends CrudRepository<HotelBookingModel,Integer> {
	
 HotelBookingModel findByEmail(String email);
 HotelBookingModel findByPassword(String password);
}
