package com.example.Hotel_Booking.SecurityConfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Hotel_Booking.Service.MyUserDetailsService;

@Configuration

@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelBookingSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired

		private HotelBookingJwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



		@Autowired

		private MyUserDetailsService myUserDetailsService;



		@Autowired

		private JwtRequestFilter jwtRequestFilter;



//		@Autowired
//
//		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//			// configure AuthenticationManager so that it knows from where to load
//
//			// user for matching credentials
//
//			// Use BCryptPasswordEncoder
//
//			auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordencoder());
//
//		}
//		



		@Override

		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordencoder());

		}



		@Override

		@Bean

		public AuthenticationManager authenticationManagerBean() throws Exception {

			// TODO Auto-generated method stub

			return super.authenticationManagerBean();

		}



		@Bean(name = "passwordEncoder")

		public  PasswordEncoder passwordencoder() {

			return new BCryptPasswordEncoder();

		}



		@Override

		protected void configure(HttpSecurity http) throws Exception {

			// http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().

			// anyRequest().authenticated()

			// .and().sessionManagement()

			// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			// http.addFilterBefore(jwtRequestFilter,

			// UsernamePasswordAuthenticationFilter.class);

			http.csrf().disable()

					// dont authenticate this particular request

					.authorizeRequests().antMatchers("/","/login", "/signin").permitAll().

					// all other requests need to be authenticated

					anyRequest().authenticated().and().

					// make sure we use stateless session; session won't be used to

					// store user's state.

					exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()

					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);



			// Add a filter to validate the tokens with every request

			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);



		}



	}