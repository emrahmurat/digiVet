package com.digivetApp.ws.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digivetApp.ws.SpringApplicationContext;
import com.digivetApp.ws.Shared.dto.UserDto;
import com.digivetApp.ws.Shared.dto.VetDto;
import com.digivetApp.ws.entities.Vet;
import com.digivetApp.ws.model.request.UserDetailsLoginRequestModel;
import com.digivetApp.ws.service.impl.UserService;
import com.digivetApp.ws.service.impl.VetServices;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException
	{
		try
		{
		UserDetailsLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), UserDetailsLoginRequestModel.class);
				//.readValue(req.getInputStream(), UserLoginRequestModel.class);
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword())
				);

		}catch(IOException exception)
		{
			throw new RuntimeException(exception);
		}
	
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain, Authentication auth) throws IOException,ServletException
	{
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,SecurityConstants.TOKEN_SECRET)
				.compact();
		UserService userService = (UserService)SpringApplicationContext.getBean("UserServiceImpl");
		
		UserDto userDto = userService.getUserByUserName(userName);
		 
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);

		res.addHeader("UserId", userDto.getPassword());
		

	}

	
}