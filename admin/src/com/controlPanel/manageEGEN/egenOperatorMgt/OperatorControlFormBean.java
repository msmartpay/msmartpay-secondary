package com.controlPanel.manageEGEN.egenOperatorMgt;

public class OperatorControlFormBean 
{
    private String param;
	private String serviceType;
	private String Operator;
    private String mainService;
    
    public String getParam() 
    {
    	return param; 
    }
   
    public void setParam(String param) 
    {
    	this.param = param;
    }
	public String getMainService() 
	{
		return mainService;
	}
	public void setMainService(String mainService) 
	{
		this.mainService = mainService;
	}
	public String getServiceType() 
	{
		return serviceType;
	}
	public void setServiceType(String serviceType) 
	{
		this.serviceType = serviceType;
	}
	public String getOperator() 
	{
		return Operator;
	}
	public void setOperator(String operator) 
	{
		Operator = operator;
	}
}