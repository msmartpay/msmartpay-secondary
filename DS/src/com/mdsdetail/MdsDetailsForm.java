package com.mdsdetail;

public class MdsDetailsForm {

	private Integer mdId;
	private String mdInitial;
	private String clientId;
	private String Email_id;
	
	
	public String getEmail_id() {
		return Email_id;
	}
	public void setEmail_id(String emailId) {
		Email_id = emailId;
	}
	public Integer getMdId() {
		return mdId;
	}
	public void setMdId(Integer mdId) {
		this.mdId = mdId;
	}
	public String getMdInitial() {
		return mdInitial;
	}
	public void setMdInitial(String mdInitial) {
		this.mdInitial = mdInitial;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
}
