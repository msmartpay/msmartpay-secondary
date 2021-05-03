package com.smartkinda.model;

import java.io.File;

public class AEPSForm {

	private String mobile;
	private String devicenumber;
	private File aadhaarFront;
	private File aadhaarBack;
	private File voterIdFront;
	private File voterIdBack;
	private File panCard;
	private String aadhaarFrontFileName;
	private String aadhaarBackFileName;
	private String voterIdFrontFileName;
	private String voterIdBackFileName;
	private String panCardFileName;
	private String userCode;
	private String modelName;
	private String state;
	private String district;
	private String address;
	private String pincode;
	
	public File getAadhaarFront() {
		return aadhaarFront;
	}
	public void setAadhaarFront(File aadhaarFront) {
		this.aadhaarFront = aadhaarFront;
	}
	public File getAadhaarBack() {
		return aadhaarBack;
	}
	public void setAadhaarBack(File aadhaarBack) {
		this.aadhaarBack = aadhaarBack;
	}
	public File getVoterIdFront() {
		return voterIdFront;
	}
	public void setVoterIdFront(File voterIdFront) {
		this.voterIdFront = voterIdFront;
	}
	public File getVoterIdBack() {
		return voterIdBack;
	}
	public void setVoterIdBack(File voterIdBack) {
		this.voterIdBack = voterIdBack;
	}
	public File getPanCard() {
		return panCard;
	}
	public void setPanCard(File panCard) {
		this.panCard = panCard;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public String getAadhaarFrontFileName() {
		return aadhaarFrontFileName;
	}
	public void setAadhaarFrontFileName(String aadhaarFrontFileName) {
		this.aadhaarFrontFileName = aadhaarFrontFileName;
	}
	public String getAadhaarBackFileName() {
		return aadhaarBackFileName;
	}
	public void setAadhaarBackFileName(String aadhaarBackFileName) {
		this.aadhaarBackFileName = aadhaarBackFileName;
	}
	public String getVoterIdFrontFileName() {
		return voterIdFrontFileName;
	}
	public void setVoterIdFrontFileName(String voterIdFrontFileName) {
		this.voterIdFrontFileName = voterIdFrontFileName;
	}
	public String getVoterIdBackFileName() {
		return voterIdBackFileName;
	}
	public void setVoterIdBackFileName(String voterIdBackFileName) {
		this.voterIdBackFileName = voterIdBackFileName;
	}
	public String getPanCardFileName() {
		return panCardFileName;
	}
	public void setPanCardFileName(String panCardFileName) {
		this.panCardFileName = panCardFileName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDevicenumber() {
		return devicenumber;
	}
	public void setDevicenumber(String devicenumber) {
		this.devicenumber = devicenumber;
	}
	
	
}
