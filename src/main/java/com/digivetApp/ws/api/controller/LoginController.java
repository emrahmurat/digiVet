package com.digivetApp.ws.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivetApp.ws.Shared.dto.UserDto;
import com.digivetApp.ws.model.request.UserDetailsRequestModel;
import com.digivetApp.ws.model.response.UserRest;
import com.digivetApp.ws.service.impl.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class LoginController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		
		UserRest returnValue = new UserRest(); //model.response cevap
		
		UserDto userDto = new UserDto(); //package between tranfer object
		
		BeanUtils.copyProperties(userDetails,userDto); //copy source from target
		
		UserDto createUser = userService.createUser(userDto);
		
 		BeanUtils.copyProperties(createUser, returnValue);
		return returnValue;
	}
	
	
	

}
