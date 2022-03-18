package com.digivetApp.ws.model.response;

import java.util.Date;

public class MeetRest {

	private String userFirstName;
	private String userLastName;
	private String vetFirstName;
	private String vetLastName;
	private String meetingType;
	private Date meetingDate;
	public MeetRest(String userFirstName, String userLastName, String vetFirstName,
			String vetLastName, String meetingType, Date meetingDate) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.vetFirstName = vetFirstName;
		this.vetLastName = vetLastName;
		this.meetingType = meetingType;
		this.meetingDate = meetingDate;
	}
	public MeetRest() {
		super();
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getVetFirstName() {
		return vetFirstName;
	}
	public void setVetFirstName(String vetFirstName) {
		this.vetFirstName = vetFirstName;
	}
	public String getVetLastName() {
		return vetLastName;
	}
	public void setVetLastName(String vetLastName) {
		this.vetLastName = vetLastName;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}
	
}
