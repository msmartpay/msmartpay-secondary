package com.dsamount;

import java.io.File;

public class DistributorJournalForm {

	private Integer MdId;
	private String DistributorId;
	private Integer JournalId;
    private String Initial;
	private String RequestDate;
	private String RequestTime;
	private String ModeOfPayment;
	private double AmountToCredit;
	private double BankCharges;
	private double AcceptedAmount;
	private String DepositorAccountNumber;
	private String DepositorBankName;
	private String DepositorName;
	private String ReceiptNumber;
	private String BankTransactionId;
	private String BankName;
	private String BranchName;
	private String BranchCity;
	private String DepositDate;
	private String ChequeDDDate;
	private String Status;
	private String remarks;
	private String AccountNumber;
	private String DepositLocation;
	private String ChequeNumber;
	private String ChequeBankName;
	private String ReferenceNo;
	private String ReceiverName;
	private String Paymentdate;//---only for jsp use
	private String ReceiverOfficeLocation;
	private String param;
	private String depositMode; //---only for jsp use
	private String TransactionJournalId;
	private String PaymentCommitmentDate;//---only for jsp use
	private String CreditDays;
	private File   AttachCashFile;
	private String AttachCashFileFileName;
	private String agentid;
	private String ipAddress;
	
	


	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getAttachCashFileFileName() {
		return AttachCashFileFileName;
	}
	public void setAttachCashFileFileName(String attachCashFileFileName) {
		AttachCashFileFileName = attachCashFileFileName;
	}
	public File getAttachCashFile() {
		return AttachCashFile;
	}
	public void setAttachCashFile(File attachCashFile) {
		AttachCashFile = attachCashFile;
	}
	public String getCreditDays() {
		return CreditDays;
	}
	public void setCreditDays(String creditDays) {
		CreditDays = creditDays;
	}
	public String getPaymentCommitmentDate() {
		return PaymentCommitmentDate;
	}
	public void setPaymentCommitmentDate(String paymentCommitmentDate) {
		PaymentCommitmentDate = paymentCommitmentDate;
	}
	public String getTransactionJournalId() {
		return TransactionJournalId;
	}
	public void setTransactionJournalId(String transactionJournalId) {
		TransactionJournalId = transactionJournalId;
	}
	public String getDepositMode() {
		return depositMode;
	}
	public void setDepositMode(String depositMode) {
		this.depositMode = depositMode;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	public String getDistributorId() {
		return DistributorId;
	}
	public void setDistributorId(String distributorId) {
		DistributorId = distributorId;
	}

	public String getInitial() {
		return Initial;
	}
	public void setInitial(String initial) {
		Initial = initial;
	}
	public String getRequestDate() {
		return RequestDate;
	}
	public void setRequestDate(String requestDate) {
		RequestDate = requestDate;
	}
	public String getRequestTime() {
		return RequestTime;
	}
	public void setRequestTime(String requestTime) {
		RequestTime = requestTime;
	}
	public String getModeOfPayment() {
		return ModeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		ModeOfPayment = modeOfPayment;
	}
	public double getAmountToCredit() {
		return AmountToCredit;
	}
	public void setAmountToCredit(double amountToCredit) {
		AmountToCredit = amountToCredit;
	}
	public double getAcceptedAmount() {
		return AcceptedAmount;
	}
	public void setAcceptedAmount(double acceptedAmount) {
		AcceptedAmount = acceptedAmount;
	}
	public String getReceiptNumber() {
		return ReceiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		ReceiptNumber = receiptNumber;
	}
	public String getDepositDate() {
		return DepositDate;
	}
	public void setDepositDate(String payTime) {
		DepositDate = payTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	
	public double getBankCharges() {
		return BankCharges;
	}
	public void setBankCharges(double bankCharges) {
		BankCharges = bankCharges;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getDepositLocation() {
		return DepositLocation;
	}
	public void setDepositLocation(String depositLocation) {
		DepositLocation = depositLocation;
	}
	public String getChequeNumber() {
		return ChequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		ChequeNumber = chequeNumber;
	}
	public String getChequeDDDate() {
		return ChequeDDDate;
	}
	public void setChequeDDDate(String checLocation) {
		ChequeDDDate = checLocation;
	}
	public String getChequeBankName() {
		return ChequeBankName;
	}
	public void setChequeBankName(String chequeBankName) {
		ChequeBankName = chequeBankName;
	}
	public String getDepositorName() {
		return DepositorName;
	}
	public void setDepositorName(String depositorName) {
		DepositorName = depositorName;
	}
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getBankTransactionId() {
		return BankTransactionId;
	}
	public void setBankTransactionId(String bankTransactionId) {
		BankTransactionId = bankTransactionId;
	}
	

	public String getReferenceNo() {
		return ReferenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		ReferenceNo = referenceNo;
	}

	public String getDepositorBankName() {
		return DepositorBankName;
	}
	public void setDepositorBankName(String depositorBankName) {
		DepositorBankName = depositorBankName;
	}
	

	public String getDepositorAccountNumber() {
		return DepositorAccountNumber;
	}
	public void setDepositorAccountNumber(String depositorAccountNumber) {
		DepositorAccountNumber = depositorAccountNumber;
	}
	
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}
	public String getReceiverOfficeLocation() {
		return ReceiverOfficeLocation;
	}
	public void setReceiverOfficeLocation(String receiverOfficeLocation) {
		ReceiverOfficeLocation = receiverOfficeLocation;
	}
	
	public String getPaymentdate() {
		return Paymentdate;
	}
	public void setPaymentdate(String paymentdate) {
		Paymentdate = paymentdate;
	}
	public Integer getMdId() {
		return MdId;
	}
	public void setMdId(Integer mdId) {
		MdId = mdId;
	}
	public String getBranchCity() {
		return BranchCity;
	}
	public void setBranchCity(String branchCity) {
		BranchCity = branchCity;
	}
	public Integer getJournalId() {
		return JournalId;
	}
	public void setJournalId(Integer journalId) {
		JournalId = journalId;
	}
	
}


