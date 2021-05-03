package com.dsamount;

public class DistributorAmountForm {

	private String distributorId;
	private double totalCash;
	private double usedCash;
	private double cutoffAamount;
    private double lastAmount;

	
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
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
	public double getCutoffAamount() {
		return cutoffAamount;
	}
	public void setCutoffAamount(double cutoffAamount) {
		this.cutoffAamount = cutoffAamount;
	}
	public double getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(double lastAmount) {
		this.lastAmount = lastAmount;
	}

    
}
