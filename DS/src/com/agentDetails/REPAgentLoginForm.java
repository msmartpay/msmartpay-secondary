package com.agentDetails;

/*
Class Property :  This class (REPAgentLoginForm) is created for is created to define setter getter method of agent details.

Created Date   : 16-Dec-2011 at 2:45 PM.
Created By     : Amit Pathak.

Updated Date   : 9-Dec-2011.
Update By      : Amit Pathak

*/

public class REPAgentLoginForm implements java.io.Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private String REPAgentUserId;
	private String REPAgentLoginStatus;
	private String RepuserName;
	private String Reppassword;
	
	public String getRepuserName() {
		return RepuserName;
	}
	public void setRepuserName(String repuserName) {
		RepuserName = repuserName;
	}
	public String getReppassword() {
		return Reppassword;
	}
	public void setReppassword(String reppassword) {
		Reppassword = reppassword;
	}
	public String getREPAgentLoginStatus() {
		return REPAgentLoginStatus;
	}
	public void setREPAgentLoginStatus(String REPAgentLoginStatus) {
		this.REPAgentLoginStatus = REPAgentLoginStatus;
	}
	public String getREPAgentUserId() {
		return REPAgentUserId;
	}
	public void setREPAgentUserId(String REPAgentUserId) {
		this.REPAgentUserId = REPAgentUserId;
	}
	

	

	
	
}
