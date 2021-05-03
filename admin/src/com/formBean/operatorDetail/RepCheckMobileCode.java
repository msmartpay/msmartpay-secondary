package com.formBean.operatorDetail;

import java.util.Date;

public class RepCheckMobileCode {

	private String operator;
	private String Service;
	private String Status;
	private String RinStatus;
	private String cyberPlatStatus;
	private String GoalStatus;
	private String updatedUser;
	private String message;
	private String internalMessage;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getInternalMessage() {
		return internalMessage;
	}
	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
	}
	private Date updateDate;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getService() {
		return Service;
	}
	public void setService(String service) {
		Service = service;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRinStatus() {
		return RinStatus;
	}
	public void setRinStatus(String rinStatus) {
		RinStatus = rinStatus;
	}
	public String getCyberPlatStatus() {
		return cyberPlatStatus;
	}
	public void setCyberPlatStatus(String cyberPlatStatus) {
		this.cyberPlatStatus = cyberPlatStatus;
	}
	public String getGoalStatus() {
		return GoalStatus;
	}
	public void setGoalStatus(String goalStatus) {
		GoalStatus = goalStatus;
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
