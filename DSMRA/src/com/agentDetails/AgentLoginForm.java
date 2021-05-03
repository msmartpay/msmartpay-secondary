
/*
  Class Property :  This class (AgentLoginForm) is created for is created to define setter getter method of agent details.

  Created Date   : 16-Dec-2011 at 2:45 PM.
  Created By     : Amit Pathak.

  Updated Date   : 9-Dec-2011.
  Update By      : Amit Pathak

*/


package com.agentDetails;

public class AgentLoginForm implements java.io.Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private String AgentUserId;
	private String AgentLoginStatus;
	private String userName;
	private String password;

	

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

	public String getAgentUserId() {
		return AgentUserId;
	}

	public void setAgentUserId(String agentUserId) {
		AgentUserId = agentUserId;
	}
	public String getAgentLoginStatus() {
		return AgentLoginStatus;
	}

	public void setAgentLoginStatus(String agentLoginStatus) {
		AgentLoginStatus = agentLoginStatus;
	}
	
}
