/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.EGEN;


@SuppressWarnings("serial")

public class ApiClientAmountFormBean  {//implements Serializable {
	
	private int corporateAgentId;
	
	private double totalCash;
	private double usedCash;
	private double totalBalanceAmount;
	private double availableBalanceAmount;
	private double cutOffAmount;
	private double lastAmount;
	
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
	
	public void setTotalBalanceAmount(double totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}
	public double getTotalBalanceAmount() {
		return totalBalanceAmount;
	}
	public void setAvailableBalanceAmount(double availableBalanceAmount) {
		this.availableBalanceAmount = availableBalanceAmount;
	}
	public double getAvailableBalanceAmount() {
		return availableBalanceAmount;
	}
	public void setCorporateAgentId(int corporateAgentId) {
		this.corporateAgentId = corporateAgentId;
	}
	public int getCorporateAgentId() {
		return corporateAgentId;
	}

	
	
	

	
}
