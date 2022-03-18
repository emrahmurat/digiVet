package com.digivetApp.ws.service.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.digivetApp.ws.Shared.dto.VetDto;
import com.digivetApp.ws.entities.Vet;
import com.digivetApp.ws.repositories.VetRepository;
import com.digivetApp.ws.service.impl.VetServices;


@Service
public class VetServiceImpl implements VetServices {

	@Autowired
	private VetRepository vetRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public VetDto createVet(VetDto vet) {
		if (this.vetRepository.findByEmail(vet.getEmail()) != null)
			throw new RuntimeException("the record is already exist");

		Vet storedVetsEntity = new Vet();
		Vet vets = new Vet();

		BeanUtils.copyProperties(vet, vets);
		vets.setPassword(bCryptPasswordEncoder.encode(vets.getPassword()));
		storedVetsEntity = this.vetRepository.save(vets);
		VetDto returnValue = new VetDto();
		BeanUtils.copyProperties(storedVetsEntity, returnValue);
		return returnValue;

	}

	@Override
	public List<Vet> getAllVet() {
		List<Vet> storedVetEntity = this.vetRepository.findAll();

		return storedVetEntity;
	}

	@Override
	public VetDto vetLogin(VetDto vet) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
