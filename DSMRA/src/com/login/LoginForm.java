/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 11-Nov-2011 at 9:42 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 03-Dec-2011.
  Update By      : Bharatveer Singh

*/
package com.login;


@SuppressWarnings("serial")
public class LoginForm implements java.io.Serializable {
	
	private String loginId;
	private String userId;
	private String password;
	private String type;
	private String status;
	private String oldPass;
	private String emailVerified;
	private String mobileVerified;
	private String profileCompleted;
	private String emailVerifiedCode;
	private String mobileVerifiedCode;
	private String param;
	private  String clientType="";
	private  String blockStatus="";

	
	

	
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	public String getMobileVerifiedCode() {
		return mobileVerifiedCode;
	}
	public void setMobileVerifiedCode(String mobileVerifiedCode) {
		this.mobileVerifiedCode = mobileVerifiedCode;
	}
	public String getEmailVerifiedCode() {
		return emailVerifiedCode;
	}
	public void setEmailVerifiedCode(String emailVerifiedCode) {
		this.emailVerifiedCode = emailVerifiedCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}
	public String getMobileVerified() {
		return mobileVerified;
	}
	public void setMobileVerified(String mobileVerified) {
		this.mobileVerified = mobileVerified;
	}
	public String getProfileCompleted() {
		return profileCompleted;
	}
	public void setProfileCompleted(String profileCompleted) {
		this.profileCompleted = profileCompleted;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	
}
