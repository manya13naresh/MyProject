package com.example.Hotel_Booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Hotel_Booking.Model.AuthenticationRequest;
import com.example.Hotel_Booking.Model.AuthenticationResponse;
import com.example.Hotel_Booking.Model.HotelBookingModel;
import com.example.Hotel_Booking.Model.HotelBookingModelJwt;
import com.example.Hotel_Booking.Repository.HotelBookingRepo;
import com.example.Hotel_Booking.SecurityConfigurer.JwtTokenUtil;
import com.example.Hotel_Booking.Service.MyUserDetailsService;

@Controller
public class HotelBookingController {
	@Autowired
	HotelBookingRepo repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@RequestMapping("/")
	public String index()
	{
		return "index.jsp";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	// ResponseEntity is HTTP

	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(authenticationRequest.getName(), authenticationRequest.getPassword()));
			} 
		catch (BadCredentialsException e) {

			throw new Exception("Incorrect Username or Password");

		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}
	@RequestMapping(value = "/signin", method = RequestMethod.POST)

	public ResponseEntity<?> saveUser(@RequestBody HotelBookingModelJwt user) throws Exception {

		return ResponseEntity.ok(userDetailsService.save(user));

	}
}
//	@RequestMapping("/signin")
//	public String signIn()
//	{
//		return "signIn.jsp";
//	}
//
//	@RequestMapping("/adduser")
//	public String addUser(@RequestParam("name") String name,@RequestParam("email") String email ,@RequestParam("password") String password,Model model)
//	{
//		HotelBookingModel userDetail = new HotelBookingModel();
//		userDetail.setName(name);
//		userDetail.setEmail(email);
//		userDetail.setPassword(password);
//		repo.save(userDetail);
//		model.addAttribute("Registration Success","Continue to login page");
//		return "login.jsp";
//	}
//	@RequestMapping("/login")
//	public String login()
//	{
//		return "login.jsp";
//	}
//	@RequestMapping("/check")
//
//	public String check(@RequestParam("email") String email, @RequestParam("password") String password ,Model model )
//	{
//		HotelBookingModel userDetail=null;
//		HotelBookingModel userDetail1=null;
//		try 
//		{
//			userDetail=repo.findByEmail(email);
//			userDetail1=repo.findByPassword(password);
//		}
//		catch(Exception E)
//		{
//			System.out.println("User Not Found");
//		}
//		if(userDetail!=null&&userDetail1!=null)
//		{
//			model.addAttribute("email", email);
//
//			return "home.jsp";
//		}
//		else
//		{
//		model.addAttribute("error","User Not Found, Kindly register !!!");
//
//		return "signIn.jsp";
//		}
//
//	}
//
//}
