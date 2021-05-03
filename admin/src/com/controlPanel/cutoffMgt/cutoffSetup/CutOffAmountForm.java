package com.controlPanel.cutoffMgt.cutoffSetup;

public class CutOffAmountForm {

	private String param;
	private String userType;
	private int userId;
	private double cutoffAmount;
	private String Updateduser;
	private String ipAddress;
	private String remark;
	private String CutOffFor;
	private  String cutOffBy;
	private String EnterUserId;
	private String checkBoxCutOff;
	private double SecondcutoffAmount;
	private String RemarkAdmin;
	private String RemarkOther;

	

	public String getRemarkAdmin() {
		return RemarkAdmin;
	}

	public void setRemarkAdmin(String remarkAdmin) {
		RemarkAdmin = remarkAdmin;
	}

	public String getRemarkOther() {
		return RemarkOther;
	}

	public void setRemarkOther(String remarkOther) {
		RemarkOther = remarkOther;
	}

	public double getSecondcutoffAmount() {		
		return SecondcutoffAmount;
	}

	public void setSecondcutoffAmount(double secondcutoffAmount) {
		SecondcutoffAmount = secondcutoffAmount;
	}

	public String getCheckBoxCutOff() {
		return checkBoxCutOff;
	}

	public void setCheckBoxCutOff(String checkBoxCutOff) {
		this.checkBoxCutOff = checkBoxCutOff;
	}

	public String getCutOffFor() {
		return CutOffFor;
	}

	public void setCutOffFor(String cutOffFor) {
		CutOffFor = cutOffFor;
	}

	public String getCutOffBy() {
		return cutOffBy;
	}

	public void setCutOffBy(String cutOffBy) {
		this.cutOffBy = cutOffBy;
	}

	public String getEnterUserId() {
		return EnterUserId;
	}

	public void setEnterUserId(String enterUserId) {
		EnterUserId = enterUserId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getCutoffAmount() {
		return cutoffAmount;
	}

	public void setCutoffAmount(double cutoffAmount) {
		this.cutoffAmount = cutoffAmount;
	}

	public String getUpdateduser() {
		return Updateduser;
	}

	public void setUpdateduser(String updateduser) {
		Updateduser = updateduser;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
