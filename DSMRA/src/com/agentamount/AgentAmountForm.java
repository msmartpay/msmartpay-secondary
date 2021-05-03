
/*
 Class Property :  This class (AgentAmountForm) is created for is created to define setter getter method of agent details.

  Created Date   : 9-Dec-2011 at 11:55 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 9-Dec-2011.
  Update By      : Bharatveer Singh

*/

package com.agentamount;

public class AgentAmountForm {
	
public AgentAmountForm(){}

private String agentId;
private double totalcash;
private double usedcash;
private double cutoffAmt;
private double lastUpdAmt;


public String getAgentId() {
	return agentId;
}
public void setAgentId(String agentId) {
	this.agentId = agentId;
}
public double getTotalcash() {
	return totalcash;
}
public void setTotalcash(double totalcash) {
	this.totalcash = totalcash;
}
public double getUsedcash() {
	return usedcash;
}
public void setUsedcash(double usedcash) {
	this.usedcash = usedcash;
}
public double getCutoffAmt() {
	return cutoffAmt;
}
public void setCutoffAmt(double cutoffAmt) {
	this.cutoffAmt = cutoffAmt;
}
public double getLastUpdAmt() {
	return lastUpdAmt;
}
public void setLastUpdAmt(double lastUpdAmt) {
	this.lastUpdAmt = lastUpdAmt;
}



}
