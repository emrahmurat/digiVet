package com.digivetApp.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digivetApp.ws.entities.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
