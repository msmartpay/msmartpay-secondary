package com.smartkinda.model;

import java.io.File;

public class KYCForm {

	private File aadhaarzip;
	private String aadhaarzipFileName;
	private String zipPass;
	private String senderId;
	
	public File getAadhaarzip() {
		return aadhaarzip;
	}
	public void setAadhaarzip(File aadhaarzip) {
		this.aadhaarzip = aadhaarzip;
	}
	public String getAadhaarzipFileName() {
		return aadhaarzipFileName;
	}
	public void setAadhaarzipFileName(String aadhaarzipFileName) {
		this.aadhaarzipFileName = aadhaarzipFileName;
	}
	public String getZipPass() {
		return zipPass;
	}
	public void setZipPass(String zipPass) {
		this.zipPass = zipPass;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
}
