package com.formBean.operatorDetail;

import java.util.Date;

public class AgentServiceDetailsForm {

	
	private String Service;
	private String SubService;
	private String operator;
	private String Active;
	private String Vendor;
	private String message;
	private String updatedUser;
	private Date updateDate;
	private String internalMessage;
	public String getInternalMessage() {
		return internalMessage;
	}
	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
	}
	public String getService() {
		return Service;
	}
	public void setService(String service) {
		Service = service;
	}
	public String getSubService() {
		return SubService;
	}
	public void setSubService(String subService) {
		SubService = subService;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getActive() {
		return Active;
	}
	public void setActive(String active) {
		Active = active;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
