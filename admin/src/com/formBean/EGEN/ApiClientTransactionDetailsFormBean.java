/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.EGEN;

import java.util.Date;

@SuppressWarnings("serial")
public class ApiClientTransactionDetailsFormBean  {//implements Serializable {


private String transactionId;
private int corporateAgentId;
private String transactionNo;
private int subAgentId;
private Date dateOfTransaction;
private Date timeOfTransaction;
private String service;
private double requestedAmount;
private double commission;
private double serviceCharge;
private double bankCharge;
private double deductedAmount;
private String actionOnBalanceAmount;
private double agentBalanceBeforeDeduction;
private double agentBalanceAfterDeduction;
private String transactionStatus;
private double agentFinalBalanceAmount;
private String ipAddress;
private String updatedDate;
private String updatedTime;
private String updatedUser;
private String updatedIpAddress;
private String remarks;
private int idNo;
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
public int getCorporateAgentId() {
	return corporateAgentId;
}
public void setCorporateAgentId(int corporateAgentId) {
	this.corporateAgentId = corporateAgentId;
}
public String getTransactionNo() {
	return transactionNo;
}
public void setTransactionNo(String transactionNo) {
	this.transactionNo = transactionNo;
}
public int getSubAgentId() {
	return subAgentId;
}
public void setSubAgentId(int subAgentId) {
	this.subAgentId = subAgentId;
}
public Date getDateOfTransaction() {
	return dateOfTransaction;
}
public void setDateOfTransaction(Date dateOfTransaction) {
	this.dateOfTransaction = dateOfTransaction;
}
public Date getTimeOfTransaction() {
	return timeOfTransaction;
}
public void setTimeOfTransaction(Date timeOfTransaction) {
	this.timeOfTransaction = timeOfTransaction;
}
public String getService() {
	return service;
}
public void setService(String service) {
	this.service = service;
}
public double getRequestedAmount() {
	return requestedAmount;
}
public void setRequestedAmount(double requestedAmount) {
	this.requestedAmount = requestedAmount;
}
public double getCommission() {
	return commission;
}
public void setCommission(double commission) {
	this.commission = commission;
}
public double getServiceCharge() {
	return serviceCharge;
}
public void setServiceCharge(double serviceCharge) {
	this.serviceCharge = serviceCharge;
}
public double getBankCharge() {
	return bankCharge;
}
public void setBankCharge(double bankCharge) {
	this.bankCharge = bankCharge;
}
public double getDeductedAmount() {
	return deductedAmount;
}
public void setDeductedAmount(double deductedAmount) {
	this.deductedAmount = deductedAmount;
}
public String getActionOnBalanceAmount() {
	return actionOnBalanceAmount;
}
public void setActionOnBalanceAmount(String actionOnBalanceAmount) {
	this.actionOnBalanceAmount = actionOnBalanceAmount;
}
public double getAgentBalanceBeforeDeduction() {
	return agentBalanceBeforeDeduction;
}
public void setAgentBalanceBeforeDeduction(double agentBalanceBeforeDeduction) {
	this.agentBalanceBeforeDeduction = agentBalanceBeforeDeduction;
}
public double getAgentBalanceAfterDeduction() {
	return agentBalanceAfterDeduction;
}
public void setAgentBalanceAfterDeduction(double agentBalanceAfterDeduction) {
	this.agentBalanceAfterDeduction = agentBalanceAfterDeduction;
}
public String getTransactionStatus() {
	return transactionStatus;
}
public void setTransactionStatus(String transactionStatus) {
	this.transactionStatus = transactionStatus;
}
public double getAgentFinalBalanceAmount() {
	return agentFinalBalanceAmount;
}
public void setAgentFinalBalanceAmount(double agentFinalBalanceAmount) {
	this.agentFinalBalanceAmount = agentFinalBalanceAmount;
}
public String getIpAddress() {
	return ipAddress;
}
public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}
public String getUpdatedDate() {
	return updatedDate;
}
public void setUpdatedDate(String updatedDate) {
	this.updatedDate = updatedDate;
}
public String getUpdatedTime() {
	return updatedTime;
}
public void setUpdatedTime(String updatedTime) {
	this.updatedTime = updatedTime;
}
public String getUpdatedUser() {
	return updatedUser;
}
public void setUpdatedUser(String updatedUser) {
	this.updatedUser = updatedUser;
}
public String getUpdatedIpAddress() {
	return updatedIpAddress;
}
public void setUpdatedIpAddress(String updatedIpAddress) {
	this.updatedIpAddress = updatedIpAddress;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public int getIdNo() {
	return idNo;
}
public void setIdNo(int idNo) {
	this.idNo = idNo;
}

}
