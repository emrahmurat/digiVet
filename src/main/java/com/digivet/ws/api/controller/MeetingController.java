package com.digivet.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.model.request.MeetingDetailsRequestModel;
import com.digivet.ws.model.response.MeetRest;
import com.digivet.ws.service.repositories.MeetService;

@RestController
@CrossOrigin
@RequestMapping(value = "/digivet/meeting")
public class MeetingController {

	Logger logger = LogManager.getLogger(MeetingController.class);
	@Autowired
	private MeetService meetService;

	public MeetingController(MeetService meetService) {
		this.meetService = meetService;
	}

	@PostMapping(value = "/createMeeting")
	public MeetRest createMeetController(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto storedByMeeting = new MeetingDto();
		MeetingDto meetingDto = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.createMeet(meetingDto);
			BeanUtils.copyProperties(storedByMeeting, returnValue);
			logger.debug("createMeetController is invoke");
		} catch (Exception ex) {
			logger.error("createMeetController is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

	@PostMapping(value = "/getAllVetMeet")
	public List<MeetRest> getAllVetMeet(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto meetingDto = new MeetingDto();
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		List<MeetRest> returnValue = new ArrayList<MeetRest>();
		ModelMapper mapper = new ModelMapper();

		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.getAllVetMeet(meetingDto);
			for (MeetingDto meet : storedByMeeting) {
				returnValue.add(mapper.map(meet, MeetRest.class));
			}
			logger.debug("getAllVetMeet Controller is invoke");

		} catch (Exception ex) {
			logger.error("getAllVetMeet Controller is not invoked error message :" +ex.getMessage());
		}
		return returnValue;
	}

	@PostMapping(value = "/getAllUserMeet")
	public List<MeetRest> getAllUserMeet(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto meetingDto = new MeetingDto();
		ModelMapper mapper = new ModelMapper();
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		List<MeetRest> returnValue = new ArrayList<MeetRest>();

		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.getAllUserMeet(meetingDto);
			for (MeetingDto meet : storedByMeeting) {
				returnValue.add(mapper.map(meet, MeetRest.class));
			}
			logger.debug("getAllUserMeet is invoke");

		} catch (Exception ex) {
			logger.error("getAllUserMeet is not invoked error message :" +ex.getMessage());
		}
		return returnValue;
	}

	@DeleteMapping(value = "/deleteMeet{id}")
	public MeetRest deleteMeet(@Param(value = "id") int id) {
		try {
			logger.debug("deleteMeet is invoke");

			this.meetService.deleteMeeting(id);

		} catch (Exception ex) {
			logger.error("deleteMeet is not invoked error message :" +ex.getMessage());
		}
		return null;
	}

	@PutMapping(value = "/updateMeet")
	public MeetRest updateMeeting(MeetingDetailsRequestModel meetingDetailsRequestModel) {
		MeetingDto storedByMeetUpdate = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		MeetingDto dto = new MeetingDto();
		try {
			BeanUtils.copyProperties(meetingDetailsRequestModel, dto);
			storedByMeetUpdate = this.meetService.updateMeeting(dto);
			BeanUtils.copyProperties(storedByMeetUpdate, returnValue);
			logger.debug("updateMeeting is invoke");

		} catch (Exception ex) {
			logger.error("updateMeeting is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

}
