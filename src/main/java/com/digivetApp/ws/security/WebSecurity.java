package com.digivetApp.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.digivetApp.ws.service.impl.UserService;
import com.digivetApp.ws.service.impl.VetServices;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserService userService;
	private final VetServices vetServices;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,VetServices vetServices)
	{
		this.vetServices = vetServices;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception
	{
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST.GET,SecurityConstants.SIGN_UP_URL,SecurityConstants.SIGN_UP_URL2,SecurityConstants.SIGN_IN_URL) //yetkilendirme yaptık
		
		.permitAll().anyRequest().authenticated().and()
		.addFilter(new AuthenticationFilter(authenticationManager()))
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		
		
		
	}
	
	
	
}
