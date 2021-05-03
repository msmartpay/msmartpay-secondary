package com.formBean.API;

import java.sql.Date;
import java.sql.Time;

public class APICustomerTransactionDetails {
	
	private long ApiId;
	private String aesTransactionId;
	private String customerTransactionId;
	private String vendorTransactionId;
	private String accountNoMobileNo;
	private Date dateOfTransaction;
	private Time timeOfTransaction;
	private String service;
	private double aPIBalBeforeTran;
	private double requestAmount;
	private double commission;
	private double serviceCharge;
	private double deductedAmount;
	private String actionOnBalanceAmount;
	private double aPIBalAfterTran;
	private String tranStatus;
	private Date updateDate;
	private Time updatedTime;
	private double aPIFinalBalance;
	private String iPAddress;
	private double balAmtBefRefund;
	private double refundedAmount;
	private double balAmtAfterRefund;
	private String remarks;
	public String getAesTransactionId() {
		return aesTransactionId;
	}
	public String getCustomerTransactionId() {
		return customerTransactionId;
	}
	public String getVendorTransactionId() {
		return vendorTransactionId;
	}
	public String getAccountNoMobileNo() {
		return accountNoMobileNo;
	}
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public Time getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public String getService() {
		return service;
	}
	public double getaPIBalBeforeTran() {
		return aPIBalBeforeTran;
	}
	public double getRequestAmount() {
		return requestAmount;
	}
	public double getCommission() {
		return commission;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public double getDeductedAmount() {
		return deductedAmount;
	}
	public String getActionOnBalanceAmount() {
		return actionOnBalanceAmount;
	}
	public double getaPIBalAfterTran() {
		return aPIBalAfterTran;
	}
	public String getTranStatus() {
		return tranStatus;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public Time getUpdatedTime() {
		return updatedTime;
	}
	public double getaPIFinalBalance() {
		return aPIFinalBalance;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public double getBalAmtBefRefund() {
		return balAmtBefRefund;
	}
	public double getRefundedAmount() {
		return refundedAmount;
	}
	public double getBalAmtAfterRefund() {
		return balAmtAfterRefund;
	}
	public String getRemarks() {
		return remarks;
	}
	public long getApiId() {
		return ApiId;
	}
	public void setApiId(long apiId) {
		ApiId = apiId;
	}
	public void setAesTransactionId(String aesTransactionId) {
		this.aesTransactionId = aesTransactionId;
	}
	public void setCustomerTransactionId(String customerTransactionId) {
		this.customerTransactionId = customerTransactionId;
	}
	public void setVendorTransactionId(String vendorTransactionId) {
		this.vendorTransactionId = vendorTransactionId;
	}
	public void setAccountNoMobileNo(String accountNoMobileNo) {
		this.accountNoMobileNo = accountNoMobileNo;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public void setTimeOfTransaction(Time timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setaPIBalBeforeTran(double aPIBalBeforeTran) {
		this.aPIBalBeforeTran = aPIBalBeforeTran;
	}
	public void setRequestAmount(double requestAmount) {
		this.requestAmount = requestAmount;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public void setDeductedAmount(double deductedAmount) {
		this.deductedAmount = deductedAmount;
	}
	public void setActionOnBalanceAmount(String actionOnBalanceAmount) {
		this.actionOnBalanceAmount = actionOnBalanceAmount;
	}
	public void setaPIBalAfterTran(double aPIBalAfterTran) {
		this.aPIBalAfterTran = aPIBalAfterTran;
	}
	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public void setUpdatedTime(Time updatedTime) {
		this.updatedTime = updatedTime;
	}
	public void setaPIFinalBalance(double aPIFinalBalance) {
		this.aPIFinalBalance = aPIFinalBalance;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public void setBalAmtBefRefund(double balAmtBefRefund) {
		this.balAmtBefRefund = balAmtBefRefund;
	}
	public void setRefundedAmount(double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	public void setBalAmtAfterRefund(double balAmtAfterRefund) {
		this.balAmtAfterRefund = balAmtAfterRefund;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
