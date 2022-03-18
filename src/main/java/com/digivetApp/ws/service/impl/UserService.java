package com.digivetApp.ws.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.digivetApp.ws.Shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user); 
	UserDto getAllUser();
	UserDto getUserByUserName(String email);
	UserDto userLogin(UserDto user);
}
