package com.formBean.EGEN;

public class ApiServiceMasterCommDetailForm 
{
	private int CorporateAgentId;
	private String OperatorName;
	private String serviceName;
	private double Commission;
	private String CommValidFrom;
	private String CommValidTo;
	private String UpdateDate;
	private String updatedBy;
	private String CommissionMark;
	
	public String getCommissionMark() 
	{
		return CommissionMark;
	}
	public void setCommissionMark(String commissionMark) {
		
		CommissionMark = commissionMark;
	}
	public int getCorporateAgentId() {
		return CorporateAgentId;
	}
	public void setCorporateAgentId(int corporateAgentId) 
	{
		CorporateAgentId = corporateAgentId;
	}
	public String getOperatorName() 
	{
		return OperatorName;
	}
	public void setOperatorName(String operatorName) 
	{
		OperatorName = operatorName;
	}
	public String getServiceName() 
	{
		return serviceName;
	}
	public void setServiceName(String serviceName) 
	{
		this.serviceName = serviceName;
	}
	public double getCommission() 
	{
		return Commission;
	}
	public void setCommission(double commission) 
	{
		Commission = commission;
	}
	public String getCommValidFrom() {
		return CommValidFrom;
	}
	public void setCommValidFrom(String commValidFrom) 
	{
		CommValidFrom = commValidFrom;
	}
	public String getCommValidTo() 
	{
		return CommValidTo;
	}
	public void setCommValidTo(String commValidTo) 
	{
		CommValidTo = commValidTo;
	}
	
	public String getUpdateDate() 
	{
		return UpdateDate;
	}
	public void setUpdateDate(String updateDate) 
	{
		UpdateDate = updateDate;
	}
	public String getUpdatedBy() 
	{
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) 
	{
		this.updatedBy = updatedBy;
	}
}
