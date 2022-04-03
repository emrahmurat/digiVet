package com.digivet.ws.service.repositories;

import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;

public interface VetServices {

	VetDto createVet(VetDto vet);
	Boolean findVet(VetLoginDto loginDto);
}
