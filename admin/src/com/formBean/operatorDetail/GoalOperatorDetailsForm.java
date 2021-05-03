package com.formBean.operatorDetail;

import java.util.Date;

public class GoalOperatorDetailsForm {
	private String SubService;
	private String operator;
	private String Vendor;
	private String updatedUser;
	private Date updateDate;
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
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
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
