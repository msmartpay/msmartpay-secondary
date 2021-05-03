package com.formBean.operatorDetail;

import java.util.Date;

public class TepAgentMobileRechargeActiveOperatorDetailsForm {

	private int userID;
	private String Service;
	private String Operator;
	private String ActiveStatus;
	private String RemarkMessage;
	private String InternalMessage;
	private String updatedUser;
	private Date updateDate;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getService() {
		return Service;
	}
	public void setService(String service) {
		Service = service;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getActiveStatus() {
		return ActiveStatus;
	}
	public void setActiveStatus(String activeStatus) {
		ActiveStatus = activeStatus;
	}
	public String getRemarkMessage() {
		return RemarkMessage;
	}
	public void setRemarkMessage(String remarkMessage) {
		RemarkMessage = remarkMessage;
	}
	public String getInternalMessage() {
		return InternalMessage;
	}
	public void setInternalMessage(String internalMessage) {
		InternalMessage = internalMessage;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
