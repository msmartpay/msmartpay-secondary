package com.reports.ccModule.noticeAlert;

import java.sql.Time;
import java.util.Date;

public class TickerFormBean {

	
	private int userID;
	private String userType;
	
	
	
	private Date uploadedDate;
	private String uploadedFrom;
	private String uploadedTo;
	public String getUploadedFrom() {
		return uploadedFrom;
	}
	public void setUploadedFrom(String uploadedFrom) {
		this.uploadedFrom = uploadedFrom;
	}
	public String getUploadedTo() {
		return uploadedTo;
	}
	public void setUploadedTo(String uploadedTo) {
		this.uploadedTo = uploadedTo;
	}
	private Time uploadedTime;
	private String uploadedUser;
	private String DefaultMessage;
	private String NewMessage;
	
	
	public String getDefaultMessage() {
		return DefaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		DefaultMessage = defaultMessage;
	}
	public String getNewMessage() {
		return NewMessage;
	}
	public void setNewMessage(String newMessage) {
		NewMessage = newMessage;
	}
	
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public Time getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Time uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	public String getUploadedUser() {
		return uploadedUser;
	}
	public void setUploadedUser(String uploadedUser) {
		this.uploadedUser = uploadedUser;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
	
}
