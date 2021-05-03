package com.formBean.adminUser;

import java.util.Date;

/**
 *Created Date=11/App/2012
 *Created BY=Vibhor Kumar
 *Purpose=update the transaction after accepted the TBRequest
 *Updated Date=""
 *Updated By=""
 */
public class PortalUserTransactionFormBean {
	
private String TransactionNo;
private String Referenceid;
private int Portalid;
private String UserType;
private Date DateofTransaction;
private Date TimeofTransaction;
private String Service;
private double Tranamount;
private double Commission;
private double Servicecharge;
private double Bankcharge;
private double Othercharge;
private double NetTranamount;
private String ActiononBalamount;
private double PreviousBalamount;
private double UpdatedBalamount;
private String Transtatus;
private double FinalBalamount;
private String Tranipaddress;
private Date UpdatedDate;
private Date Updatedtime;
private String UpdatedUser;
private String Updatedipadddress;
private String Remarks;
private String transferTo;
public PortalUserTransactionFormBean() {
	super();
	// TODO Auto-generated constructor stub
}
public PortalUserTransactionFormBean(String transactionNo, String referenceId,
		int portalid, String userType, Date dateofTransaction,
		Date timeofTransaction, String service, double tranamount,
		double commission, double servicecharge, double bankcharge,
		double othercharge, double netTranamount, String actiononBalamount,
		double previousBalamount, double updatedBalamount, String transtatus,
		double finalBalamount, String tranipaddress, Date updatedDate,
		Date updatedtime, String updatedUser, String updatedipadddress,
		String remarks) {
	super();
	TransactionNo = transactionNo;
	Referenceid = referenceId;
	Portalid = portalid;
	UserType = userType;
	DateofTransaction = dateofTransaction;
	TimeofTransaction = timeofTransaction;
	Service = service;
	Tranamount = tranamount;
	Commission = commission;
	Servicecharge = servicecharge;
	Bankcharge = bankcharge;
	Othercharge = othercharge;
	NetTranamount = netTranamount;
	ActiononBalamount = actiononBalamount;
	PreviousBalamount = previousBalamount;
	UpdatedBalamount = updatedBalamount;
	Transtatus = transtatus;
	FinalBalamount = finalBalamount;
	Tranipaddress = tranipaddress;
	UpdatedDate = updatedDate;
	Updatedtime = updatedtime;
	UpdatedUser = updatedUser;
	Updatedipadddress = updatedipadddress;
	Remarks = remarks;
}
public String getTransactionNo() {
	return TransactionNo;
}
public void setTransactionNo(String transactionNo) {
	TransactionNo = transactionNo;
}
public String getReferenceid() {
	return Referenceid;
}
public void setReferenceid(String referenceId) {
	Referenceid = referenceId;
}
public int getPortalid() {
	return Portalid;
}
public void setPortalid(int portalid) {
	Portalid = portalid;
}
public String getUserType() {
	return UserType;
}
public void setUserType(String userType) {
	UserType = userType;
}
public Date getDateofTransaction() {
	return DateofTransaction;
}
public void setDateofTransaction(Date dateofTransaction) {
	DateofTransaction = dateofTransaction;
}
public Date getTimeofTransaction() {
	return TimeofTransaction;
}
public void setTimeofTransaction(Date timeofTransaction) {
	TimeofTransaction = timeofTransaction;
}
public String getService() {
	return Service;
}
public void setService(String service) {
	Service = service;
}
public double getTranamount() {
	return Tranamount;
}
public void setTranamount(double tranamount) {
	Tranamount = tranamount;
}
public double getCommission() {
	return Commission;
}
public void setCommission(double commission) {
	Commission = commission;
}
public double getServicecharge() {
	return Servicecharge;
}
public void setServicecharge(double servicecharge) {
	Servicecharge = servicecharge;
}
public double getBankcharge() {
	return Bankcharge;
}
public void setBankcharge(double bankcharge) {
	Bankcharge = bankcharge;
}
public double getOthercharge() {
	return Othercharge;
}
public void setOthercharge(double othercharge) {
	Othercharge = othercharge;
}
public double getNetTranamount() {
	return NetTranamount;
}
public void setNetTranamount(double netTranamount) {
	NetTranamount = netTranamount;
}
public String getActiononBalamount() {
	return ActiononBalamount;
}
public void setActiononBalamount(String actiononBalamount) {
	ActiononBalamount = actiononBalamount;
}
public double getPreviousBalamount() {
	return PreviousBalamount;
}
public void setPreviousBalamount(double previousBalamount) {
	PreviousBalamount = previousBalamount;
}
public double getUpdatedBalamount() {
	return UpdatedBalamount;
}
public void setUpdatedBalamount(double updatedBalamount) {
	UpdatedBalamount = updatedBalamount;
}
public String getTranstatus() {
	return Transtatus;
}
public void setTranstatus(String transtatus) {
	Transtatus = transtatus;
}
public double getFinalBalamount() {
	return FinalBalamount;
}
public void setFinalBalamount(double finalBalamount) {
	FinalBalamount = finalBalamount;
}
public String getTranipaddress() {
	return Tranipaddress;
}
public void setTranipaddress(String tranipaddress) {
	Tranipaddress = tranipaddress;
}
public Date getUpdatedDate() {
	return UpdatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	UpdatedDate = updatedDate;
}
public Date getUpdatedtime() {
	return Updatedtime;
}
public void setUpdatedtime(Date updatedtime) {
	Updatedtime = updatedtime;
}
public String getUpdatedUser() {
	return UpdatedUser;
}
public void setUpdatedUser(String updatedUser) {
	UpdatedUser = updatedUser;
}
public String getUpdatedipadddress() {
	return Updatedipadddress;
}
public void setUpdatedipadddress(String updatedipadddress) {
	Updatedipadddress = updatedipadddress;
}
public String getRemarks() {
	return Remarks;
}
public void setRemarks(String remarks) {
	Remarks = remarks;
}
public String getTransferTo() {
	return transferTo;
}
public void setTransferTo(String transferTo) {
	this.transferTo = transferTo;
}



}
