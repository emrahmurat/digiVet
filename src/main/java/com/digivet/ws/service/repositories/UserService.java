package com.digivet.ws.service.repositories;

import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;

public interface UserService {

	public UserDto createUser(UserDto user) throws Exception;
	public Boolean findUser(UserLoginDto user);
	

	
}
