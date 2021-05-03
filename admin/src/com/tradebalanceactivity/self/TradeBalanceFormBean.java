package com.tradebalanceactivity.self;

import java.util.Date;
/**
 *Created Date=31/Mar/2012,
 *
 * Created BY=Vibhor Kumar ,
 * 
 * Purpose=Action Class,
 * 
 * Save Deposit Information of Portal User 
 * 
 * Updated Date="", Updated By=""
 */
public class TradeBalanceFormBean {
	 
	private int userid;
	private String TransactionId;
	private Date RequestDate;
	private Date RequestTime;
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
	private Date DepositDate;
	private String ChequeDDNo;
	private Date Chequedate;
	private String Chequebankname;
	private String ApprovedBy;
	private Date ApprovalDate;
	private String Status;
	private Date Creditdate;
	private String Creditdays;
	private Date commitmentdate;
	private String remark;
	private String remarkAdmin;
	private String DepositorAccountNumber;
	private String Refereceno;
	private String OrderId;
	private String cardholdername;
	private String ReceiverName;
	private String ReceiverOfficeLocation;		
	private String DepositerName;
	private String RecieptFileLocation;
	private String DepositTime;
	private int portalId;
	private String loginType;
	public TradeBalanceFormBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TradeBalanceFormBean(int userid, String transactionId,
			Date requestDate, Date requestTime, String modeofPayment,
			double amountToCredit, double bankcharges, double acceptedAmount,
			String recieptNo, String recievingBankName,
			String recievingBranchName, String recievingBranchCity,
			String recievingBankAccNo, String bankTranId, String refrenceId,
			String depositBankName, String depositLocation, Date depositDate,
			String chequeDDNo, Date chequedate, String chequebankname,
			String approvedBy, Date approvalDate, String status,
			Date creditdate, String creditdays, Date commitmentdate,
			String remark, String remarkAdmin, String depositorAccountNumber,
			String refereceno, String orderId, String cardholdername) {
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
	public Date getRequestDate() {
		return RequestDate;
	}
	public void setRequestDate(Date requestDate) {
			RequestDate = requestDate;
		
	}
	public Date getRequestTime() {
		return RequestTime;
	}
	public void setRequestTime(Date requestTime) {
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
	public Date getDepositDate() {
		return DepositDate;
	}
	public void setDepositDate(Date depositDate) {
			DepositDate = depositDate;
		
	}
	public String getChequeDDNo() {
		return ChequeDDNo;
	}
	public void setChequeDDNo(String chequeDDNo) {
			ChequeDDNo = chequeDDNo;
		
	}
	public Date getChequedate() {
		return Chequedate;
	}
	public void setChequedate(Date chequedate) {
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
	public Date getApprovalDate() {
		return ApprovalDate;
	}
	public void setApprovalDate(Date approvalDate) {
			ApprovalDate = approvalDate;
		
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
			Status = status;
		
	}
	public Date getCreditdate() {
		return Creditdate;
	}
	public void setCreditdate(Date creditdate) {
				Creditdate = creditdate;
		
	}
	public String getCreditdays() {
		return Creditdays;
	}
	public void setCreditdays(String creditdays) {
				Creditdays = creditdays;
		
	}
	public Date getCommitmentdate() {
		return commitmentdate;
	}
	public void setCommitmentdate(Date commitmentdate) {
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
	
	}public String getDepositerName() {
		return DepositerName;
	}
	public void setDepositerName(String depositerName) {
			DepositerName = depositerName;
		
	}		
	
	public String getRecieptFileLocation() {
		return RecieptFileLocation;
	}
	public void setRecieptFileLocation(String recieptFileLocation) {
		RecieptFileLocation = recieptFileLocation;
	}
	public String getDepositTime() {
		return DepositTime;
	}
	public void setDepositTime(String depositTime) {
		DepositTime = depositTime;
	}
	public int getPortalId() {
		return portalId;
	}
	public void setPortalId(int portalId) {
		this.portalId = portalId;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}	
	
	
	
	
	

}
