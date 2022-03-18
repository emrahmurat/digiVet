package com.digivetApp.ws.model.request;

import java.util.Date;

public class CommentDetailsRequestModel {

	private String userFirstName;
	private String userLastName;
	private String vetFirstName;
	private String vetLastName;
	private String Comment;
	private Date date;
	
	public CommentDetailsRequestModel() {
		super();
	}
	public CommentDetailsRequestModel(String userFirstName, String userLastName, String vetFirstName,
			String vetLastName, String comment, Date date) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.vetFirstName = vetFirstName;
		this.vetLastName = vetLastName;
		Comment = comment;
		this.date = date;
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
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
