package com.digivet.ws.service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digivet.ws.Exceptions.VetServiceException;
import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;
import com.digivet.ws.entities.Vet;
import com.digivet.ws.repositories.VetRepository;
import com.digivet.ws.service.repositories.VetServices;

@Service
public class VetServicesImp implements VetServices {

	Logger logger = LogManager.getLogger(VetServicesImp.class);

	@Autowired
	private VetRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public VetDto createVet(VetDto vet) {
		VetDto returnValue = new VetDto();
		Vet vetEntity = new Vet();
		Vet storedByVet = new Vet();
		try {
			logger.info("createVet service is invoke");

			if (repository.findByEmail(vet.getEmail()) != null)
				throw new VetServiceException("this record already exist");

			String jwt = bCryptPasswordEncoder.encode(vet.getPassword());
			vet.setPassword(jwt);

			BeanUtils.copyProperties(vet, vetEntity);
			storedByVet = this.repository.save(vetEntity);
			BeanUtils.copyProperties(storedByVet, returnValue);
		} catch (Exception ex) {
			logger.error("createVet service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

	@Override
	public Boolean findVet(VetLoginDto loginDto) {
		Boolean returnValue = true;
		Vet vetEntity = new Vet();
		try {
			logger.info("findVet service is invoke");

			String jwt = bCryptPasswordEncoder.encode(loginDto.getPassword());
			loginDto.setPassword(jwt);
			BeanUtils.copyProperties(loginDto, vetEntity);
			String email = vetEntity.getEmail();
			String password = vetEntity.getPassword();
			if (this.repository.findByEmail(email).toString() == email
					&& this.repository.findByPassword(password).toString() == password)
			{
				returnValue = true;
			}
			
		} catch (Exception ex) {
			logger.error("findVet service is not invoke error message:" + ex.getMessage());
			returnValue = false;
		}
		return returnValue;
	}

}
