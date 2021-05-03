package com.disttributordetails;

import java.sql.Time;
import java.util.Date;

public class DistributorTransactionDetailForm 
{

private String distributorId;	
private String TransactionNo;
private String ReferenceId;
private String MdID;
private String UserType;
private Date DateOfTransaction;
private Time TimeOfTransaction;
private String Service;
private double BankCharge;
private double NetTransactionAmount;
private String ActionOnBalanceAmount;
private double PreviousBalanceAmount;
private double UpdatedBalanceAmount;
private double FinalBalanceAmount;
private String IpAddress;
private Date UpdatedDate;
private Time UpdatedTime;
private String UpdatedUser;
private String updatedIpAddress;
private String Remarks;
private double TransactionAmount;
private String TransactionStatus;	
private double Commission;
private double Servicecharge;
private long IDNO;

public long getIDNO() {
	return IDNO;
}
public void setIDNO(long iDNO) {
	IDNO = iDNO;
}
public double getServicecharge() {
	return Servicecharge;
}
public void setServicecharge(double servicecharge) {
	Servicecharge = servicecharge;
}
public double getCommission() {
	return Commission;
}
public void setCommission(double commission) {
	Commission = commission;
}
public String getDistributorId() {
	return distributorId;
}
public void setDistributorId(String distributorId) {
	this.distributorId = distributorId;
}
public String getTransactionNo() {
	return TransactionNo;
}
public void setTransactionNo(String transactionNo) {
	TransactionNo = transactionNo;
}
public String getReferenceId() {
	return ReferenceId;
}
public void setReferenceId(String referenceId) {
	ReferenceId = referenceId;
}
public String getMdID() {
	return MdID;
}
public void setMdID(String mdID) {
	MdID = mdID;
}
public String getUserType() {
	return UserType;
}
public void setUserType(String userType) {
	UserType = userType;
}
public Date getDateOfTransaction() {
	return DateOfTransaction;
}
public void setDateOfTransaction(Date dateOfTransaction) {
	DateOfTransaction = dateOfTransaction;
}
public Time getTimeOfTransaction() {
	return TimeOfTransaction;
}
public void setTimeOfTransaction(Time timeOfTransaction) {
	TimeOfTransaction = timeOfTransaction;
}
public String getService() {
	return Service;
}
public void setService(String service) {
	Service = service;
}
public double getBankCharge() {
	return BankCharge;
}
public void setBankCharge(double bankCharge) {
	BankCharge = bankCharge;
}
public double getNetTransactionAmount() {
	return NetTransactionAmount;
}
public void setNetTransactionAmount(double netTransactionAmount) {
	NetTransactionAmount = netTransactionAmount;
}
public String getActionOnBalanceAmount() {
	return ActionOnBalanceAmount;
}
public void setActionOnBalanceAmount(String actionOnBalanceAmount) {
	ActionOnBalanceAmount = actionOnBalanceAmount;
}
public double getPreviousBalanceAmount() {
	return PreviousBalanceAmount;
}
public void setPreviousBalanceAmount(double previousBalanceAmount) {
	PreviousBalanceAmount = previousBalanceAmount;
}
public double getUpdatedBalanceAmount() {
	return UpdatedBalanceAmount;
}
public void setUpdatedBalanceAmount(double updatedBalanceAmount) {
	UpdatedBalanceAmount = updatedBalanceAmount;
}
public double getFinalBalanceAmount() {
	return FinalBalanceAmount;
}
public void setFinalBalanceAmount(double finalBalanceAmount) {
	FinalBalanceAmount = finalBalanceAmount;
}
public String getIpAddress() {
	return IpAddress;
}
public void setIpAddress(String ipAddress) {
	IpAddress = ipAddress;
}
public Date getUpdatedDate() {
	return UpdatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	UpdatedDate = updatedDate;
}
public Time getUpdatedTime() {
	return UpdatedTime;
}
public void setUpdatedTime(Time updatedTime) {
	UpdatedTime = updatedTime;
}
public String getUpdatedUser() {
	return UpdatedUser;
}
public void setUpdatedUser(String updatedUser) {
	UpdatedUser = updatedUser;
}
public String getUpdatedIpAddress() {
	return updatedIpAddress;
}
public void setUpdatedIpAddress(String updatedIpAddress) {
	this.updatedIpAddress = updatedIpAddress;
}
public String getRemarks() {
	return Remarks;
}
public void setRemarks(String remarks) {
	Remarks = remarks;
}
public double getTransactionAmount() {
	return TransactionAmount;
}
public void setTransactionAmount(double transactionAmount) {
	TransactionAmount = transactionAmount;
}
public String getTransactionStatus() {
	return TransactionStatus;
}
public void setTransactionStatus(String transactionStatus) {
	TransactionStatus = transactionStatus;
}



}
