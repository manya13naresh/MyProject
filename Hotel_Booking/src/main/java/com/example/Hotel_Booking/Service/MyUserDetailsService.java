package com.example.Hotel_Booking.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Hotel_Booking.Model.HotelBookingModel;
import com.example.Hotel_Booking.Model.HotelBookingModelJwt;
import com.example.Hotel_Booking.Repository.HotelBookingRepo;

@Service

public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private  HotelBookingRepo  repo;
	@Autowired
	@Lazy
	private PasswordEncoder bcryptEncoder;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
	HotelBookingModelJwt user = repo.findByName(name);// jpa

		if (user == null) {

			System.out.println("");

			throw new UsernameNotFoundException("User not found with username: " + name);

		}

		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),

				new ArrayList<>());

	}



	public HotelBookingModelJwt save(HotelBookingModelJwt user) {

		HotelBookingModelJwt newUser = new HotelBookingModelJwt();

		newUser.setName(user.getName());

		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

		newUser.setEmail(user.getEmail());

		return repo.save(newUser);

	}



}