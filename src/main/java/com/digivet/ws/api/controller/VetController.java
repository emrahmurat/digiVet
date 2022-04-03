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

import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;
import com.digivet.ws.model.request.VetDetailsLoginModel;
import com.digivet.ws.model.request.VetDetailsRequestModel;
import com.digivet.ws.model.response.VetRest;
import com.digivet.ws.service.repositories.VetServices;

@RestController
@CrossOrigin
@RequestMapping("/vet")
public class VetController {

	Logger logger = LogManager.getLogger(VetController.class);

	@Autowired
	private VetServices vetServices;

	@PostMapping("/create")
	public VetRest createVet(@RequestBody VetDetailsRequestModel model) {
		VetRest returnValue = new VetRest();
		VetDto dto = new VetDto();
		VetDto storedByServices = new VetDto();
		try {
			BeanUtils.copyProperties(model, dto);
			storedByServices = this.vetServices.createVet(dto);
			BeanUtils.copyProperties(storedByServices, returnValue);
			logger.debug("createVet controller is invoke");
		} catch (Exception ex) {
			logger.error("createVet controller is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

	@PostMapping("/login")
	public Boolean findByVet(@RequestBody VetDetailsLoginModel model) {
		Boolean returnValue = false;
		
		VetLoginDto loginDto = new VetLoginDto();
		try {
			logger.debug("findByVet controller is invoke");

			BeanUtils.copyProperties(model, loginDto);
			returnValue = this.vetServices.findVet(loginDto);
			
		} catch (Exception ex) {
			logger.error("findByVet controller is not error message :" +ex.getMessage());

		}
		return returnValue;
	}
}
