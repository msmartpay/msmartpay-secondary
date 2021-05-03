package com.formBean.API;

public class APICustomerLoginDetails {

	private long ApiId;
	private String loginUserName;
	private String loginPassword;
	private String status;
	private String loginStatus;
	private String oldLoginPassword;
	private String emailVerificationCode;
	private String emailverifyStatus;
	private String mobileVerificationOtp;
	private String mobileVerifyStatus;
	private String sessionId;
	
	public String getLoginUserName() {
		return loginUserName;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public String getStatus() {
		return status;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public String getOldLoginPassword() {
		return oldLoginPassword;
	}
	public String getEmailVerificationCode() {
		return emailVerificationCode;
	}
	public String getEmailverifyStatus() {
		return emailverifyStatus;
	}
	public String getMobileVerificationOtp() {
		return mobileVerificationOtp;
	}
	public String getMobileVerifyStatus() {
		return mobileVerifyStatus;
	}
	public String getSessionId() {
		return sessionId;
	}
	public long getApiId() {
		return ApiId;
	}
	public void setApiId(long apiId) {
		ApiId = apiId;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public void setOldLoginPassword(String oldLoginPassword) {
		this.oldLoginPassword = oldLoginPassword;
	}
	public void setEmailVerificationCode(String emailVerificationCode) {
		this.emailVerificationCode = emailVerificationCode;
	}
	public void setEmailverifyStatus(String emailverifyStatus) {
		this.emailverifyStatus = emailverifyStatus;
	}
	public void setMobileVerificationOtp(String mobileVerificationOtp) {
		this.mobileVerificationOtp = mobileVerificationOtp;
	}
	public void setMobileVerifyStatus(String mobileVerifyStatus) {
		this.mobileVerifyStatus = mobileVerifyStatus;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
