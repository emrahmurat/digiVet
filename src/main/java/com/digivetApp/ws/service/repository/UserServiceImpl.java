package com.digivetApp.ws.service.repository;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digivetApp.ws.Shared.dto.UserDto;
import com.digivetApp.ws.entities.Users;
import com.digivetApp.ws.repositories.UserRepository;
import com.digivetApp.ws.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) { //yeni user oluşturur
		if(this.userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("the record is already exist");
			
		Users storedUsersEntity = new Users();
		Users users = new Users();

		BeanUtils.copyProperties(user, users);
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		storedUsersEntity = this.userRepository.save(users);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUsersEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getAllUser() { //tüm user Datasını getirir
		List<Users> storedUsersEntity;
		UserDto returnValue = new UserDto();
		storedUsersEntity = this.userRepository.findAll();
		BeanUtils.copyProperties(storedUsersEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserName(String email) { //sadece 1 user getirir
		
		Users storedUserEntity = new Users();
		storedUserEntity = this.userRepository.findByFirstName(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto userLogin(UserDto user) { //userLogin işlemleri
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
