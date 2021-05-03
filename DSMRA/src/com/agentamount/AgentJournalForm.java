

/*
 Class Property :  This class (AgentJournalForm) is created for is created to define setter getter method of agent journal.

  Created Date   : 14-Dec-2011 at 11:55 PM.
  Created By     : Amit Pathak.

  Updated Date   : 14-Dec-2011.
  Update By      : Amit Pathak

*/


package com.agentamount;

import java.sql.Time;
import java.util.Date;

public class AgentJournalForm
{
private String agentId;
private String JournalId;
private String RequestDate;
private Time    RequestTime;
private String ModeOfPayment;
private String AmounToCredit;
private String ChequeNumber;
private String Status;
private String distributorId;
private String remarks;
private String ApprovalDate;
private String BankName;
private String Bank_Charges;
private String DepositerName;
private String DepositorBankName;
private String DepositDate;
private String account_no;
private String Transaction_id;
private Date journalDate;



public Date getJournalDate() {
	return journalDate;
}

public void setJournalDate(Date journalDate) {
	this.journalDate = journalDate;
}

public String getTransaction_id() {
	return Transaction_id;
}

public void setTransaction_id(String transactionId) {
	Transaction_id = transactionId;
}

public String getAccount_no() {
	return account_no;
}

public void setAccount_no(String accountNo) {
	account_no = accountNo;
}

public String getDepositDate() {
	return DepositDate;
}

public void setDepositDate(String depositDate) {
	DepositDate = depositDate;
}

public String getDepositorBankName() {
	return DepositorBankName;
}

public void setDepositorBankName(String depositorBankName) {
	DepositorBankName = depositorBankName;
}

public String getDepositerName() {
	return DepositerName;
}

public void setDepositerName(String depositerName) {
	DepositerName = depositerName;
}

public String getBank_Charges() {
	return Bank_Charges;
}

public void setBank_Charges(String bankCharges) {
	Bank_Charges = bankCharges;
}

public String getBankName() {
	return BankName;
}

public void setBankName(String bankName) {
	BankName = bankName;
}

private String AcceptedAmount;

public String getAgentId() {
	return agentId;
}

public void setAgentId(String agentId) {
	this.agentId = agentId;
}

public String getJournalId() {
	return JournalId;
}

public void setJournalId(String journalId) {
	JournalId = journalId;
}

public String getRequestDate() {
	return RequestDate;
}

public void setRequestDate(String requestDate) {
	RequestDate = requestDate;
}

public Time getRequestTime() {
	return RequestTime;
}

public void setRequestTime(Time requestTime) {
	RequestTime = requestTime;
}

public String getModeOfPayment() {
	return ModeOfPayment;
}

public void setModeOfPayment(String modeOfPayment) {
	ModeOfPayment = modeOfPayment;
}

public String getAmounToCredit() {
	return AmounToCredit;
}

public void setAmounToCredit(String amounToCredit) {
	AmounToCredit = amounToCredit;
}

public String getChequeNumber() {
	return ChequeNumber;
}

public void setChequeNumber(String chequeNumber) {
	ChequeNumber = chequeNumber;
}

public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}

public String getDistributorId() {
	return distributorId;
}

public void setDistributorId(String distributorId) {
	this.distributorId = distributorId;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public String getApprovalDate() {
	return ApprovalDate;
}

public void setApprovalDate(String approvalDate) {
	ApprovalDate = approvalDate;
}

public String getAcceptedAmount() {
	return AcceptedAmount;
}

public void setAcceptedAmount(String acceptedAmount) {
	AcceptedAmount = acceptedAmount;
}

}
