package com.tradebalanceactivity.self;

import java.io.File;
/**
 *Created Date=03/App/2012
 *Created BY=Vibhor Kumar
 *Purpose=To Provide Deposit Information by Portal User
 *Updated Date=""
 *Updated By=""
 */
public class TradeBlanceDepositFormBean {
	private int userid;
	private String TransactionId;
	private String RequestDate;
	private String RequestTime;
	private String ModeofPayment;
	private double AmountToCredit;
	private double Bankcharges;
	private double AcceptedAmount;
	private String RecieptNo;
	private String RecievingBankName;
	private String RecievingBranchName;
	private String RecievingBranchCity;
	private String RecievingBankAccNo;
	private String BankTranId;
	private String RefrenceId;
	private String DepositBankName;
	private String DepositLocation;
	private String DepositDate;
	private String ChequeDDNo;
	private String Chequedate;
	private String Chequebankname;
	private String ApprovedBy;
	private String ApprovalDate;
	private String Status;
	private String Creditdate;
	private String Creditdays;
	private String commitmentdate;
	private String remark;
	private String remarkAdmin;
	private String DepositorAccountNumber;
	private String Refereceno;
	private String OrderId;
	private String cardholdername;
	private String ReceiverName;
	private String ReceiverOfficeLocation;		
	private String DepositerName;
	private File RecieptFile;
	private String recieptFileFileName;
	private String DepositTime;
	
	public TradeBlanceDepositFormBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TradeBlanceDepositFormBean(int userid, String transactionId,
			String requestDate, String requestTime, String modeofPayment,
			double amountToCredit, double bankcharges, double acceptedAmount,
			String recieptNo, String recievingBankName,
			String recievingBranchName, String recievingBranchCity,
			String recievingBankAccNo, String bankTranId, String refrenceId,
			String depositBankName, String depositLocation, String depositDate,
			String chequeDDNo, String chequedate, String chequebankname,
			String approvedBy, String approvalDate, String status,
			String creditdate, String creditdays, String commitmentdate,
			String remark, String remarkAdmin, String depositorAccountNumber,
			String refereceno, String orderId, String cardholdername,
			String receiverName, String receiverOfficeLocation,
			String depositerName) {
				this.userid = userid;
		TransactionId = transactionId;
		RequestDate = requestDate;
		RequestTime = requestTime;
		ModeofPayment = modeofPayment;
		AmountToCredit = amountToCredit;
		Bankcharges = bankcharges;
		AcceptedAmount = acceptedAmount;
		RecieptNo = recieptNo;
		RecievingBankName = recievingBankName;
		RecievingBranchName = recievingBranchName;
		RecievingBranchCity = recievingBranchCity;
		RecievingBankAccNo = recievingBankAccNo;
		BankTranId = bankTranId;
		RefrenceId = refrenceId;
		DepositBankName = depositBankName;
		DepositLocation = depositLocation;
		DepositDate = depositDate;
		ChequeDDNo = chequeDDNo;
		Chequedate = chequedate;
		Chequebankname = chequebankname;
		ApprovedBy = approvedBy;
		ApprovalDate = approvalDate;
		Status = status;
		Creditdate = creditdate;
		Creditdays = creditdays;
		this.commitmentdate = commitmentdate;
		this.remark = remark;
		this.remarkAdmin = remarkAdmin;
		DepositorAccountNumber = depositorAccountNumber;
		Refereceno = refereceno;
		OrderId = orderId;
		this.cardholdername = cardholdername;
		ReceiverName = receiverName;
		ReceiverOfficeLocation = receiverOfficeLocation;
		DepositerName = depositerName;
	}	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
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
	public String getModeofPayment() {
		return ModeofPayment;
	}
	public void setModeofPayment(String modeofPayment) {
		ModeofPayment = modeofPayment;
	}
	public double getAmountToCredit() {
		return AmountToCredit;
	}
	public void setAmountToCredit(double amountToCredit) {
		AmountToCredit = amountToCredit;
	}
	public double getBankcharges() {
		return Bankcharges;
	}
	public void setBankcharges(double bankcharges) {
		Bankcharges = bankcharges;
	}
	public double getAcceptedAmount() {
		return AcceptedAmount;
	}
	public void setAcceptedAmount(double acceptedAmount) {
		AcceptedAmount = acceptedAmount;
	}
	public String getRecieptNo() {
		return RecieptNo;
	}
	public void setRecieptNo(String recieptNo) {
		RecieptNo = recieptNo;
	}
	public String getRecievingBankName() {
		return RecievingBankName;
	}
	public void setRecievingBankName(String recievingBankName) {
		RecievingBankName = recievingBankName;
	}
	public String getRecievingBranchName() {
		return RecievingBranchName;
	}
	public void setRecievingBranchName(String recievingBranchName) {
		RecievingBranchName = recievingBranchName;
	}
	public String getRecievingBranchCity() {
		return RecievingBranchCity;
	}
	public void setRecievingBranchCity(String recievingBranchCity) {
		RecievingBranchCity = recievingBranchCity;
	}
	public String getRecievingBankAccNo() {
		return RecievingBankAccNo;
	}
	public void setRecievingBankAccNo(String recievingBankAccNo) {
		RecievingBankAccNo = recievingBankAccNo;
	}
	public String getBankTranId() {
		return BankTranId;
	}
	public void setBankTranId(String bankTranId) {
		BankTranId = bankTranId;
	}
	public String getRefrenceId() {
		return RefrenceId;
	}
	public void setRefrenceId(String refrenceId) {
		RefrenceId = refrenceId;
	}
	public String getDepositBankName() {
		return DepositBankName;
	}
	public void setDepositBankName(String depositBankName) {
		DepositBankName = depositBankName;
	}
	public String getDepositLocation() {
		return DepositLocation;
	}
	public void setDepositLocation(String depositLocation) {
		DepositLocation = depositLocation;
	}
	public String getDepositDate() {
		return DepositDate;
	}
	public void setDepositDate(String depositDate) {
		DepositDate = depositDate;
	}
	public String getChequeDDNo() {
		return ChequeDDNo;
	}
	public void setChequeDDNo(String chequeDDNo) {
		ChequeDDNo = chequeDDNo;
	}
	public String getChequedate() {
		return Chequedate;
	}
	public void setChequedate(String chequedate) {
		Chequedate = chequedate;
	}
	public String getChequebankname() {
		return Chequebankname;
	}
	public void setChequebankname(String chequebankname) {
		Chequebankname = chequebankname;
	}
	public String getApprovedBy() {
		return ApprovedBy;
	}
	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}
	public String getApprovalDate() {
		return ApprovalDate;
	}
	public void setApprovalDate(String approvalDate) {
		ApprovalDate = approvalDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCreditdate() {
		return Creditdate;
	}
	public void setCreditdate(String creditdate) {
		Creditdate = creditdate;
	}
	public String getCreditdays() {
		return Creditdays;
	}
	public void setCreditdays(String creditdays) {
		Creditdays = creditdays;
	}
	public String getCommitmentdate() {
		return commitmentdate;
	}
	public void setCommitmentdate(String commitmentdate) {
		this.commitmentdate = commitmentdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemarkAdmin() {
		return remarkAdmin;
	}
	public void setRemarkAdmin(String remarkAdmin) {
		this.remarkAdmin = remarkAdmin;
	}
	public String getDepositorAccountNumber() {
		return DepositorAccountNumber;
	}
	public void setDepositorAccountNumber(String depositorAccountNumber) {
		DepositorAccountNumber = depositorAccountNumber;
	}
	public String getRefereceno() {
		return Refereceno;
	}
	public void setRefereceno(String refereceno) {
		Refereceno = refereceno;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getCardholdername() {
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
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
	public String getDepositerName() {
		return DepositerName;
	}
	public void setDepositerName(String depositerName) {
		DepositerName = depositerName;
	}
	public File getRecieptFile() {
		return RecieptFile;
	}
	public void setRecieptFile(File recieptFile) {
		RecieptFile = recieptFile;
	}
	public String getRecieptFileFileName() {
		return recieptFileFileName;
	}
	
	public void setRecieptFileFileName(String recieptFileFileName) {
		this.recieptFileFileName = recieptFileFileName;
	}
	
	public String getDepositTime() {
		return DepositTime;
	}
	public void setDepositTime(String depositTime) {
		DepositTime = depositTime;
	}

}
