/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.agent;

public class AgentAmountFormBean {
	
	private int agentId;
	
	private double totalCash;
	private double usedCash;
	private double cutOffAmount;
	private double lastAmount;
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public double getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}
	public double getUsedCash() {
		return usedCash;
	}
	public void setUsedCash(double usedCash) {
		this.usedCash = usedCash;
	}
	public double getCutOffAmount() {
		return cutOffAmount;
	}
	public void setCutOffAmount(double cutOffAmount) {
		this.cutOffAmount = cutOffAmount;
	}
	public double getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(double lastAmount) {
		this.lastAmount = lastAmount;
	}
	
	
	

	
}
