package com.example.Hotel_Booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Hotel_Booking.Model.HotelBookingModel;
import com.example.Hotel_Booking.Repository.HotelBookingRepo;

@Controller
public class HotelBookingController {
	@Autowired
	HotelBookingRepo repo;
	@RequestMapping("/")
	public String index()
	{
		return "index.jsp";
	}
	@RequestMapping("/signin")
	public String signIn()
	{
		return "signIn.jsp";
	}
	@RequestMapping("/adduser")
	public String addUser(@RequestParam("name") String name,@RequestParam("email") String email ,@RequestParam("password") String password,Model model)
	{
		HotelBookingModel hm= new HotelBookingModel();
		hm.setName(name);
		hm.setEmail(email);
		hm.setPassword(password);
		repo.save(hm);
		model.addAttribute("Registration Success","Continue to login page");
		return "login.jsp";
	}
	@RequestMapping("/login")
	public String login()
	{
		return "login.jsp";
	}
	@RequestMapping("/check")

	public String check(@RequestParam("email") String email, @RequestParam("password") String password ,Model model )
	{
		HotelBookingModel hm=null;
		HotelBookingModel hm1=null;
		try 
		{
			hm=repo.findByEmail(email);
			hm1=repo.findByPassword(password);
		}
		catch(Exception E)
		{
			System.out.println("User Not Found");
		}
		if(hm!=null&&hm1!=null)
		{
			model.addAttribute("email", email);

			return "home.jsp";
		}
		else
		{
		model.addAttribute("error","User Not Found, Kindly register !!!");

		return "signIn.jsp";
		}

	}

}
