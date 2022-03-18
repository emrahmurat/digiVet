package com.digivetApp.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digivetApp.ws.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);
	Users findByFirstName(String email);
	Users findByPassword(String password);
}
