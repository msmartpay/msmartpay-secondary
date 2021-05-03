/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.mds;

public class MdsLoginInfoFormBean  {	

	
    private int userId;
	private String userName;
	private String password;	
	
	private String loginStatus;
	
	private String userType;
	private String clientType;
	private String blockStatus;
	private String validUpto;
	private String otp;
	private String otpDate;
	private String otpValidityTime;
	
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getValidUpto() {
		return validUpto;
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
