package com.digivetApp.ws.service.impl;

import java.util.List;

import com.digivetApp.ws.Shared.dto.UserDto;
import com.digivetApp.ws.Shared.dto.VetDto;
import com.digivetApp.ws.entities.Vet;

public interface VetServices {

	VetDto createVet(VetDto vet);
	List<Vet> getAllVet();
	VetDto vetLogin(VetDto vet);
	
}
