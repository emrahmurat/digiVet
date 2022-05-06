package com.digivet.ws.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digivet.ws.Exceptions.UserServiceException;
import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;
import com.digivet.ws.entities.Users;
import com.digivet.ws.repositories.UserRepository;
import com.digivet.ws.service.repositories.UserService;

@Service
public class UserServiceImp implements UserService {

	Logger logger = LogManager.getLogger(UserServiceImp.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) throws Exception {

		UserDto returnValue = new UserDto();
		Users userEntity = new Users();
		Users storedByData = new Users();
		try {
			logger.info("createUser service is invoke");

			String jwt = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(jwt);
			if (userRepository.findByEmail(user.getEmail()) != null)
				throw new UserServiceException("Record already exists");
			BeanUtils.copyProperties(user, userEntity);
			storedByData = this.userRepository.save(userEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
		} catch (Exception ex) {
			logger.error("createUser service is not invoke error message:" + ex.getMessage());
		}

		return returnValue;
	}

	@Override
	public Boolean findUser(UserLoginDto user) {
		boolean returnValue = true;
		Users userEntity = new Users();
		try {
			logger.info("findUser service is invoke");

			BeanUtils.copyProperties(user, userEntity);
			String email = userEntity.getEmail();
			String password = userEntity.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
						
			if (encoder.matches(password,this.userRepository.findByPassword(email)) == true)
			{
				
				returnValue = true;
			}
			
			else {
				returnValue = false;
			}
		} catch (Exception ex) {
			logger.error("findUser service is not invoke error message:" + ex.getMessage());
			returnValue = false;

		}
		return returnValue;
	}

}
