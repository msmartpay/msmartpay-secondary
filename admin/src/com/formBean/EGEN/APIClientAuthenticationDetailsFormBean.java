/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.EGEN;



@SuppressWarnings("serial")
public class APIClientAuthenticationDetailsFormBean  
{
	private int corporateAgentId;	
	private String agentHeaderId;	
	private String agentHeaderPassword;	
	private String agentLoginId;
	private String agentLoginPassword;	
	private String agentMdId;
    private String authorizedIP1;
	private String authorizedIP2;	
	private String status;
	private String loginStatus;
	private String verifyCode;
	private String verifyStatus;
	private String blockStatus;

	private String otp;
	private String otpDate;
	private String otpValidityTime;

	public int getCorporateAgentId() {
		return corporateAgentId;
	}
	public void setCorporateAgentId(int corporateAgentId) {
		this.corporateAgentId = corporateAgentId;
	}
	public String getAgentHeaderId() {
		return agentHeaderId;
	}
	public void setAgentHeaderId(String agentHeaderId) {
		this.agentHeaderId = agentHeaderId;
	}
	public String getAgentHeaderPassword() {
		return agentHeaderPassword;
	}
	public void setAgentHeaderPassword(String agentHeaderPassword) {
		this.agentHeaderPassword = agentHeaderPassword;
	}
	public String getAgentLoginId() {
		return agentLoginId;
	}
	public void setAgentLoginId(String agentLoginId) {
		this.agentLoginId = agentLoginId;
	}
	public String getAgentLoginPassword() {
		return agentLoginPassword;
	}
	public void setAgentLoginPassword(String agentLoginPassword) {
		this.agentLoginPassword = agentLoginPassword;
	}
	public String getAgentMdId() {
		return agentMdId;
	}
	public void setAgentMdId(String agentMdId) {
		this.agentMdId = agentMdId;
	}
	public String getAuthorizedIP1() {
		return authorizedIP1;
	}
	public void setAuthorizedIP1(String authorizedIP1) {
		this.authorizedIP1 = authorizedIP1;
	}
	public String getAuthorizedIP2() {
		return authorizedIP2;
	}
	public void setAuthorizedIP2(String authorizedIP2) {
		this.authorizedIP2 = authorizedIP2;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
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
