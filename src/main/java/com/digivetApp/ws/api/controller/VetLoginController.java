package com.digivetApp.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digivetApp.ws.Shared.dto.VetDto;
import com.digivetApp.ws.entities.Vet;
import com.digivetApp.ws.model.request.VetDetailsLoginModel;
import com.digivetApp.ws.model.request.VetDetailsRequestModel;
import com.digivetApp.ws.model.response.VetRest;
import com.digivetApp.ws.service.impl.VetServices;

@RestController
@CrossOrigin
@RequestMapping("/vets")
public class VetLoginController {

	@Autowired
	private VetServices vetServices;

	@PostMapping
	public VetRest vetCreateLogin(@RequestBody VetDetailsRequestModel vetDetailsRequestModel)
	{
		VetRest returnValue = new VetRest(); //model.response cevap
		
		VetDto vetDto = new VetDto(); //package between tranfer object
		
		BeanUtils.copyProperties(vetDetailsRequestModel,vetDto); //copy source from target
		
		VetDto createVet = vetServices.createVet(vetDto);
		
 		BeanUtils.copyProperties(createVet, returnValue);
		return returnValue;
	}
	
	@GetMapping
	public List<Vet> vetGetAll()
	{
		 
		 List<Vet> retunValue = this.vetServices.getAllVet();
		 
		 return retunValue;
		 
	}
}
