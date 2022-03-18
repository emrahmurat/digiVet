package com.digivetApp.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digivetApp.ws.entities.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
	

}
