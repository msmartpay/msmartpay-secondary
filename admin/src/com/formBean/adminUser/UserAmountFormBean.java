/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.adminUser;

public class UserAmountFormBean  {
	
	private int userId;
	
	private double totalCash;
	private double usedCash;
	private double totalBalanceAmount;
	private double availableBalanceAmount;
	private double cutOffAmount;
	private double lastUpdateAmount;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public double getTotalBalanceAmount() {
		return totalBalanceAmount;
	}
	public void setTotalBalanceAmount(double totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}
	public double getAvailableBalanceAmount() {
		return availableBalanceAmount;
	}
	public void setAvailableBalanceAmount(double availableBalanceAmount) {
		this.availableBalanceAmount = availableBalanceAmount;
	}
	public void setCutOffAmount(double cutOffAmount) {
		this.cutOffAmount = cutOffAmount;
	}
	public double getCutOffAmount() {
		return cutOffAmount;
	}
	public double getLastUpdateAmount() {
		return lastUpdateAmount;
	}
	public void setLastUpdateAmount(double lastUpdateAmount) {
		this.lastUpdateAmount = lastUpdateAmount;
	}
	
	
	

	
}
