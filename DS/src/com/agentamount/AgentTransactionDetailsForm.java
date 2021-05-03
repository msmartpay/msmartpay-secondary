
/*
 Class Property :  This class (AgentTransactionDetailsForm) is created for is created to define setter getter method of agent transaction details.

  Created Date   : 13-Dec-2011 at 11:55 PM.
  Created By     : Amit Pathak.

  Updated Date   : 13-Dec-2011.
  Update By      : Amit Pathak

*/

package com.agentamount;

import java.sql.Time;
import java.util.Date;

public class AgentTransactionDetailsForm 
{

	private String Agentid;
	private String TransactionID;
	private String TransactionNo;
	private String UserType;
	private String distributorId;
	private Date DateOfTransaction;
	private Time TimeOfTransaction;
	private String Service;
	private double AgentBalanceBeforeDeduction;
	private double AgentBalanceAfterDeduction;
	private double RequestedAmount;
	private double serviceCharge2;

	private double DeductedAmont;
	private String ActionOnBalance;
	private String TransactionStatus;
	private Date   UpdatedDate;
	private Time   UpdatedTime;
	private double AgentFinalBalance;
	private String IpAddress;
	private String UpdatedIpAddress;
	private String remarks;
	private double serviceCharge1;
	
	
	
	public String getAgentid() {
		return Agentid;
	}
	public void setAgentid(String agentid) {
		Agentid = agentid;
	}
	public String getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}
	public String getTransactionNo() {
		return TransactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		TransactionNo = transactionNo;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
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
	public double getAgentBalanceBeforeDeduction() {
		return AgentBalanceBeforeDeduction;
	}
	public void setAgentBalanceBeforeDeduction(double agentBalanceBeforeDeduction) {
		AgentBalanceBeforeDeduction = agentBalanceBeforeDeduction;
	}
	public double getAgentBalanceAfterDeduction() {
		return AgentBalanceAfterDeduction;
	}
	public void setAgentBalanceAfterDeduction(double agentBalanceAfterDeduction) {
		AgentBalanceAfterDeduction = agentBalanceAfterDeduction;
	}
	public double getRequestedAmount() {
		return RequestedAmount;
	}
	public void setRequestedAmount(double requestedAmount) {
		RequestedAmount = requestedAmount;
	}
	
	public double getDeductedAmont() {
		return DeductedAmont;
	}
	public void setDeductedAmont(double deductedAmont) {
		DeductedAmont = deductedAmont;
	}
	public String getActionOnBalance() {
		return ActionOnBalance;
	}
	public void setActionOnBalance(String actionOnBalance) {
		ActionOnBalance = actionOnBalance;
	}
	public String getTransactionStatus() {
		return TransactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		TransactionStatus = transactionStatus;
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
	public double getAgentFinalBalance() {
		return AgentFinalBalance;
	}
	public void setAgentFinalBalance(double agentFinalBalance) {
		AgentFinalBalance = agentFinalBalance;
	}
	public String getIpAddress() {
		return IpAddress;
	}
	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public String getUpdatedIpAddress() {
		return UpdatedIpAddress;
	}
	public void setUpdatedIpAddress(String updatedIpAddress) {
		UpdatedIpAddress = updatedIpAddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public double getServiceCharge1() {
		return serviceCharge1;
	}
	public void setServiceCharge1(double serviceCharge1) {
		this.serviceCharge1 = serviceCharge1;
	}
	public double getServiceCharge2() {
		return serviceCharge2;
	}
	public void setServiceCharge2(double serviceCharge2) {
		this.serviceCharge2 = serviceCharge2;
	}
	
	
}
