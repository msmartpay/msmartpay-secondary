/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.login;

public class LoginInfoFormBean  
{	
	private String userName;
	private String password;
	private String param;	
	private int userId;
	private String loginStatus;
	private String passwordStatus;
	private String oldPassword;
	private String verifiedStatus;
	private String verificationCode;
	private String mobileVerifiedStatus;
	private String mobileVerificationCode;
	private String userType;
	private String blockStatus;
	private String validUpto;

	private String otp;
	private String otpDate;
	private String otpValidityTime;
	
	
	public String getMobileVerifiedStatus() {
		return mobileVerifiedStatus;
	}
	public void setMobileVerifiedStatus(String mobileVerifiedStatus) {
		this.mobileVerifiedStatus = mobileVerifiedStatus;
	}
	public String getMobileVerificationCode() {
		return mobileVerificationCode;
	}
	public void setMobileVerificationCode(String mobileVerificationCode) {
		this.mobileVerificationCode = mobileVerificationCode;
	}

	public String getPasswordStatus() {
		return passwordStatus;
	}
	public void setPasswordStatus(String passwordStatus) {
		this.passwordStatus = passwordStatus;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getVerifiedStatus() {
		return verifiedStatus;
	}
	public void setVerifiedStatus(String verifiedStatus) {
		this.verifiedStatus = verifiedStatus;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getValidUpto() {
		return validUpto;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setOtpValidityTime(String otpValidityTime) {
		this.otpValidityTime = otpValidityTime;
	}
	public String getOtpValidityTime() {
		return otpValidityTime;
	}
	public void setOtpDate(String otpDate) {
		this.otpDate = otpDate;
	}
	public String getOtpDate() {
		return otpDate;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtp() {
		return otp;
	}

}
