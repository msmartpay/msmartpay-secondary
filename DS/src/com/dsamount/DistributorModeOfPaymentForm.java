package com.dsamount;

public class DistributorModeOfPaymentForm {

	private long SNO;
	private String ModeOfPayment;
	private String BankName;
	private double LimitAmount;
	private double charges;
	private String chargeType;
	private String AccountNumber;
	private String BranchName;

	
	
	
	public String getModeOfPayment() {
		return ModeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		ModeOfPayment = modeOfPayment;
	}
	public long getSNO() {
		return SNO;
	}
	public void setSNO(long sNO) {
		SNO = sNO;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public double getLimitAmount() {
		return LimitAmount;
	}
	public void setLimitAmount(double limitAmount) {
		LimitAmount = limitAmount;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	
}
