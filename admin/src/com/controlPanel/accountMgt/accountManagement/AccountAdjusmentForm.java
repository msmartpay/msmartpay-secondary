package com.controlPanel.accountMgt.accountManagement;

public class AccountAdjusmentForm {

	
	private String param;
	private String UserType;
	private String RequestUserId;
	private String actionOn;
	private String actionType;
	private String internalRemark;
	private String externalRemark;
	private double amount;
	private int userOnlyId;
	private String ipAddress;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getUserOnlyId() {
		return userOnlyId;
	}
	public void setUserOnlyId(int userOnlyId) {
		this.userOnlyId = userOnlyId;
	}
	public String getActionOn() {
		return actionOn;
	}
	public void setActionOn(String actionOn) {
		this.actionOn = actionOn;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getInternalRemark() {
		return internalRemark;
	}
	public void setInternalRemark(String internalRemark) {
		this.internalRemark = internalRemark;
	}
	public String getExternalRemark() {
		return externalRemark;
	}
	public void setExternalRemark(String externalRemark) {
		this.externalRemark = externalRemark;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getRequestUserId() {
		return RequestUserId;
	}
	public void setRequestUserId(String requestUserId) {
		RequestUserId = requestUserId;
	}
}
