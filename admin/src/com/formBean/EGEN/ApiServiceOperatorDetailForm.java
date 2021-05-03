package com.formBean.EGEN;

import java.util.Date;

public class ApiServiceOperatorDetailForm {

	
	
	private int CorporateAgentId;
	private String OperatorName;
	private String serviceName;
	private double Commission;
	private String CommValidFrom;
	private String CommValidTo;
	private Date UpdateDate;
	private String updatedBy;
	private String ipaddress;
	private String Operator;
	private String InternalMessage;
	private String RemarkMessage;
	private String ActiveStatus;
	
	
	
	
	public String getActiveStatus() {
		return ActiveStatus;
	}
	public void setActiveStatus(String activeStatus) {
		ActiveStatus = activeStatus;
	}
	public String getRemarkMessage() {
		return RemarkMessage;
	}
	public void setRemarkMessage(String remarkMessage) {
		RemarkMessage = remarkMessage;
	}
	public String getInternalMessage() {
		return InternalMessage;
	}
	public void setInternalMessage(String internalMessage) {
		InternalMessage = internalMessage;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	private String CommissionMark;
	
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
	public int getCorporateAgentId() {
		return CorporateAgentId;
	}
	public void setCorporateAgentId(int corporateAgentId) {
		CorporateAgentId = corporateAgentId;
	}
	public String getOperatorName() {
		return OperatorName;
	}
	public void setOperatorName(String operatorName) {
		OperatorName = operatorName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getCommission() {
		return Commission;
	}
	public void setCommission(double commission) {
		Commission = commission;
	}
	public String getCommValidFrom() {
		return CommValidFrom;
	}
	public void setCommValidFrom(String commValidFrom) {
		CommValidFrom = commValidFrom;
	}
	public String getCommValidTo() {
		return CommValidTo;
	}
	public void setCommValidTo(String commValidTo) {
		CommValidTo = commValidTo;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCommissionMark() {
		return CommissionMark;
	}
	public void setCommissionMark(String commissionMark) {
		CommissionMark = commissionMark;
	}
}
