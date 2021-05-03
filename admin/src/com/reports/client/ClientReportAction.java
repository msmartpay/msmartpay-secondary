package com.reports.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.common.ConvertUtility;
import com.common.ZipCreator;

public final class ClientReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	  
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();
		ClientReportDao clientReportDao=new ClientReportDao();
	
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=request.getParameter("param");
			System.out.println("ClientReportAction param is "+param);
			if(param.equalsIgnoreCase("ClientReport"))
			{
				return "TranChannelReport";
			}
			else if(param.equals("downloadReport"))
			{
				String portalId="0";
				String reportOf=request.getParameter("reportOf");
				portalId=(String)session.get("adminUserPortalId");
				System.out.println("userType ::::::::::::::"+portalId);
				System.out.println("reportOf ::"+reportOf);
//				String fromDate=request.getParameter("fromDate");
//				String toDate=request.getParameter("toDate");
//				System.out.println(fromDate);
//				System.out.println(toDate);
				if(reportOf.equals("billpayReport"))
				{
					
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BillMobile"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/work/";
					String zipFilePath2="BillMobile"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					String  fileStatus=clientReportDao.getBillPaymentReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}
				else if(reportOf.equals("busTranBook"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BusBooking"+".xls";
					
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="BusBooking"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=clientReportDao.getBusTranBookReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}
				else if(reportOf.equals("busTranCan"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BusCancellation"+".xls";
					
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="BusCancellation"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=clientReportDao.getBusTranCanReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}	
				
				else if(reportOf.equals("licPremiumReport"))
				{
					
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="LIC"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="LIC"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=clientReportDao.getLICPremiumReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
             	
				}
				else if(reportOf.equals("liveRechargeTransactionReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="LiveRecharge"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="LiveRecharge"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					String  fileStatus=clientReportDao.getLiveRechargeReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}
				
				else if(reportOf.equals("panCardReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="pancard"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="pancard"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					     
					String  fileStatus=clientReportDao.getPanCardReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
	             	
				}
				else if(reportOf.equals("suspectReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="SuspectReport"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="SuspectReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
		
					String  fileStatus=clientReportDao.getSuspectDataReport(filePath,reportOf,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}	
				  
				else if(reportOf.equals("airDomesticReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="AirBookingReport"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="AirBookingReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					String fileStatus=clientReportDao.getAirDomesticAccountReport(filePath,portalId);
					//System.out.println(fileStatus);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}
				else if(reportOf.equals("airDomesticCanReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="AirCancellationReport"+".xls";
					String path="/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="AirCancellationReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					String fileStatus=clientReportDao.getAirDomesticCancellationAccountReport(filePath,portalId);
					if(fileStatus.equalsIgnoreCase("NoRecord"))
					{
						
						request.setAttribute("message","There is no transaction in report.");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "clientReportRes";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR"))
					{
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "clientReportRes";
					}else if(fileStatus.equalsIgnoreCase("Success"))
					{
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error"))
				    	{
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "clientReportRes";
				    	}
				    	response.sendRedirect(zipFilePath);	
					}
				}
			}else
			{
				return "Error";
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in AccountStatementAction class");
			System.out.println(e.toString());
			return "Error";
		}
		return null;   
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	public void setSession(Map session)
	{
		session = this.getSession();
	}

	public Map getSession()
	{
		return session;
	}
	
	public void setServletResponse(HttpServletResponse response)
	{
		this.response=response;
	}
}

