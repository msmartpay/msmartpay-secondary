package com.formBean.API;

import java.sql.Date;

public class APICustomerAuthDetails {

	private long ApiId;
	private String customerAuthId;
	private String customerAuthPass;
	private String status;
	private String ipAddress1;
	private String ipAddress2;
	private Date updatedDate;
	
	public String getCustomerAuthId() {
		return customerAuthId;
	}
	public String getCustomerAuthPass() {
		return customerAuthPass;
	}
	public String getStatus() {
		return status;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setCustomerAuthId(String customerAuthId) {
		this.customerAuthId = customerAuthId;
	}
	public void setCustomerAuthPass(String customerAuthPass) {
		this.customerAuthPass = customerAuthPass;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getIpAddress1() {
		return ipAddress1;
	}
	public String getIpAddress2() {
		return ipAddress2;
	}
	public void setIpAddress1(String ipAddress1) {
		this.ipAddress1 = ipAddress1;
	}
	public void setIpAddress2(String ipAddress2) {
		this.ipAddress2 = ipAddress2;
	}
	public long getApiId() {
		return ApiId;
	}
	public void setApiId(long apiId) {
		ApiId = apiId;
	}

	
}
