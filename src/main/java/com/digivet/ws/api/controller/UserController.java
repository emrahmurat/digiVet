package com.digivet.ws.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;
import com.digivet.ws.model.request.UserDetailsLoginRequestModel;
import com.digivet.ws.model.request.UserDetailsRequestModel;
import com.digivet.ws.model.response.UserRest;
import com.digivet.ws.service.repositories.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "/createUsers")
	public UserRest createUsers(@RequestBody UserDetailsRequestModel model) {
		UserRest returnValue = new UserRest();
		UserDto data = new UserDto();
		UserDto storedByUserService = new UserDto();
		try {
			logger.debug("createUser controller is invoked");
			BeanUtils.copyProperties(model, data);
			storedByUserService = this.userService.createUser(data);
			BeanUtils.copyProperties(storedByUserService, returnValue);
		} catch (Exception ex) {
			logger.error("createUser controller is not invoked error message :" +ex.getMessage());
			ex.printStackTrace();
		}

		return returnValue;
	}

	@PostMapping(value = "/login")
	public Boolean findUser(@RequestBody UserDetailsLoginRequestModel loginModel) {
		boolean returnValue = true;
		UserLoginDto data = new UserLoginDto();
		
		try {
			logger.debug("findUser controller is invoked");
			BeanUtils.copyProperties(loginModel, data);
			returnValue = this.userService.findUser(data);
		} catch (Exception ex) {
			logger.error("findUser controller is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

}
