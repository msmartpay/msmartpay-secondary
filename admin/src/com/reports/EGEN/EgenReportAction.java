package com.reports.EGEN;

/*
 * Created By   : Manoj 
 * Created Date : 24 may 2012
 * Matter       : This class is used for Egen report.We are using Admin_Egen_reports stored Procedure in OnlineRechAPI_db
 */

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.common.ZipCreator;

public class EgenReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	@SuppressWarnings("rawtypes")
	private Map session; 
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public String execute() throws Exception
	{
		Map session=ActionContext.getContext().getSession();
		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			
			String param=(String)request.getParameter("param");
			System.out.println("EgenReportAction and param is :: "+param);
			EgenReportDao egenReportDaoObj;
			
			if(param.equalsIgnoreCase("TranEGENReport"))
			{
				return "TranEGENReport";
			}
			else if(param.equalsIgnoreCase("MgtEGENReport"))
			{
				return "MgtEGENReport";
			}
			else if(param.equalsIgnoreCase("FinanceEGENReport"))
			{
				return "FinanceEGENReport";
			}
			else if(param.equalsIgnoreCase("egenReport"))
			{
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String reportOfEgen=request.getParameter("reportofegen");
				String reportFor= "All";
				
				//System.out.println("report for is :"+reportFor);
				//System.out.println("all data from date is :"+ fromDate +" todate : "+toDate +" report for is : "+reportOfEgen);
				
				if(reportOfEgen.equalsIgnoreCase("apiaccountstatement"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/EgenAccountStatement/";
					String filePath2="APIAccountStatement"+".xls";
					//String filePath1 = "E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="APIAccountStatement"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getApiAccountStatement(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("fileStatus","Data not available.");
						return "reportDownload";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("fileStatus","Transaction are more than 100000 which is not allowed for download");
						return "reportDownload";
					}
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("fileStatus","Transaction are more than 100000 which is not allowed for download");
			    		return "reportDownload";
			    	}
			    	
			    	response.sendRedirect("Reportfile/EgenAccountStatement/"+zipFilePath2);
				}
				if(reportOfEgen.equalsIgnoreCase("accadjustreport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/apiAccountAdjustment/";
					String filePath2="APIAccountAdjustment"+".xls";
					//String filePath1 = "E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="APIAccountAdjustment"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getapiAccountAdjustment(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("message","Data not available.");
						return "FinanceEGENReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceEGENReport";
					}
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "FinanceEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/apiAccountAdjustment/"+zipFilePath2);
					
				}
				if(reportOfEgen.equalsIgnoreCase("BillpayReport"))
				{
					
					String filePath1 = request.getRealPath("/")+"Reportfile/EgenBillPay/";
					String filePath2="EgenBillPay"+".xls";
					//String filePath1 = "E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenBillPay"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenBillPayReport(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						
						request.setAttribute("message","Data not available.");
						return "TranEGENReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranEGENReport";
					}
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	if(result.equals("error"))
			    	{
			    		
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "TranEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/EgenBillPay/"+zipFilePath2);
		
				}
				if(reportOfEgen.equalsIgnoreCase("livedepositfirst"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/EgenLiveDeposit/";
					String filePath2="EgenDepositeDetail"+".xls";
					//String filePath1 = "E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenDepositeDetail"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenLiveDeposit(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("message","Data not available.");
						return "FinanceEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceEGENReport";
					}
					
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "FinanceEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/EgenLiveDeposit/"+zipFilePath2);
		
				}
				if(reportOfEgen.equalsIgnoreCase("liverechargefirst"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/EgenLiveRecharge/";
					String filePath2="EgenLiveRecharge"+".xls";
					//String filePath1 = "E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenLiveRecharge"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenLiveRechargeReport(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						
						request.setAttribute("message","Data not available.");
						return "TranEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranEGENReport";
					}
					
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "TranEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/EgenLiveRecharge/"+zipFilePath2);
				}
				if(reportOfEgen.equalsIgnoreCase("B2BDOMFlight"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/B2BDomFlight/";
					//String filePath1 = "E:/Work/";
					String filePath2="EgenLiveDOMFlight"+".xls";
					
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenLiveDOMFlight"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenLiveDOMFlight(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						
						request.setAttribute("message","Data not available.");
						return "TranEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranEGENReport";
					}
					
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "TranEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/B2BDomFlight/"+zipFilePath2);
				}
				if(reportOfEgen.equalsIgnoreCase("B2BHotel"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/Bookinghotel/";
					String filePath2="EgenLiveHotel"+".xls";
					//String filePath1 ="E:/Work/";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenLiveHotel"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenLiveHotel(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						
						request.setAttribute("message","Data not available.");
						return "TranEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranEGENReport";
					}
					
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "TranEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/Bookinghotel/"+zipFilePath2);
				}
				if(reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					//String filePath1 ="E:/Work/";
					String filePath1 = request.getRealPath("/")+"Reportfile/Bus/";
					String filePath2="EgenLiveBus"+".xls";
					
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="EgenLiveBus"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					egenReportDaoObj=new EgenReportDao();
					String  fileStatus=egenReportDaoObj.getEgenLiveHotel(filePath,fromDate,toDate,reportOfEgen,reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						
						request.setAttribute("message","Data not available.");
						return "TranEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranEGENReport";
					}
					
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	
			    	if(result.equals("error"))
			    	{
			    		request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
			    		return "TranEGENReport";
			    	}
			    	
			    	response.sendRedirect("Reportfile/Bus/"+zipFilePath2);
				}
				if(reportOfEgen.equalsIgnoreCase("apiJournal"))
				{
		            
					String filePath1 = request.getRealPath("/")+"Reportfile/ApiJournalReport/";
					String filePath2 = "ApiJournalReport.xls";
					String path = "/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2 = "ApiJournalReport.rar";
					String filePath =path+filePath2;
					String zipFilePath = filePath1+zipFilePath2;
					egenReportDaoObj = new EgenReportDao();
					String fileStatus = egenReportDaoObj.getApiJournalReport(filePath, fromDate, toDate, reportOfEgen, reportFor);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
		          
						request.setAttribute("message", "Data not available.");
						return "FinanceEGENReport";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message", "Transaction are more than 100000 which is not allowed for download");
						return "FinanceEGENReport";
					}
					
					ZipCreator zipObject = new ZipCreator();
					String result = zipObject.createRarFile(filePath, zipFilePath);
					
					if(result.equals("error"))
					{
		         
						request.setAttribute("message", "Transaction are more than 100000 which is not allowed for download");
						return "FinanceEGENReport";
					}
					
					response.sendRedirect("Reportfile/ApiJournalReport/"+zipFilePath2);
		       
				}
				if(reportOfEgen.equalsIgnoreCase("IOReport"))
				{
		            
					String userID = request.getParameter("reportForID");
					//System.out.println("userID :"+userID);
					String filePath1 = request.getRealPath("/")+"Reportfile/IOReport/";
					String filePath2 = "IOReport.xls";
//					String path="E:/Work/";
					String path = "/usr/Trandata/";
					String zipFilePath2 = "IOReport.rar";
					String filePath =path+filePath2;
					String zipFilePath = filePath1+zipFilePath2;
					egenReportDaoObj = new EgenReportDao();
					String fileStatus = egenReportDaoObj.getIODetailReport(filePath, fromDate, toDate,userID);
					
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
		          
						request.setAttribute("fileStatus", "Data not available.");
						return "reportDownload";
					}
					
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("fileStatus", "Transaction are more than 100000 which is not allowed for download");
						return "reportDownload";
					}
					
					ZipCreator zipObject = new ZipCreator();
					String result = zipObject.createRarFile(filePath, zipFilePath);
					
					if(result.equals("error"))
					{
		         
						request.setAttribute("fileStatus", "Transaction are more than 100000 which is not allowed for download");
						return "reportDownload";
					}
					response.sendRedirect("Reportfile/IOReport/"+zipFilePath2);
		       
				}
		        if(reportOfEgen.equalsIgnoreCase("IODetails"))
		        {
		           // System.out.println("we are in IODetails");
		        	egenReportDaoObj = new EgenReportDao();
		        	String userID = request.getParameter("reportForID");
		        	String fromdate = request.getParameter("fromDate");
		        	String todate = request.getParameter("toDate");
		        	//System.out.println("userID :"+userID);
		        	ArrayList  	Data = egenReportDaoObj.getIOReport(fromdate, todate, userID);
		        	
		        	if(Data.size() <= 0)
		        	{
		        		request.setAttribute("message", "There is no trasnaction for this Client ID.");
		        		return "IODetailPage";
		        	}
		            request.setAttribute("data", Data);
		            return "IODetailPage";
		        }
		        if(reportOfEgen.equalsIgnoreCase("ApiCutOff"))
		        {
		        	//System.out.println("we are in ApiCutOff");
		        	egenReportDaoObj = new EgenReportDao();
		        	String userID = request.getParameter("reportForID");
		        	ArrayList apiCutoffDetail;
		        	apiCutoffDetail = egenReportDaoObj.getAPICutoffDetail(userID);
		        	
		        	if(apiCutoffDetail.size()<=0)
		        	{
		        		request.setAttribute("message", "Due to some technical error we can not provide you the information.");
		        		return "APICutoffDetails";
		        	}
		        	
		        	request.setAttribute("data", apiCutoffDetail);
		        	return "APICutoffDetails";
		        }
			}
			else if(param.equalsIgnoreCase("egenReportView"))
			{
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String reportOfEgen=request.getParameter("reportofegen");
				String reportFor= "All";
				
				if(reportOfEgen.equalsIgnoreCase("BillpayReport"))
				{
					egenReportDaoObj = new EgenReportDao();
			        	
					ArrayList  Data = egenReportDaoObj.getBillpayReportView( fromDate, toDate, reportOfEgen, reportFor);
			        	
					if(Data.size() <= 0)
					{
						request.setAttribute("message", "There is no trasnaction.");
						return "TranEGENReport";
					}
					request.setAttribute("showService", "Recharge");
					request.setAttribute("flag", "Y");
					request.setAttribute("RechargeData", Data);
					return "TranEGENReport";
				}
				
				if(reportOfEgen.equalsIgnoreCase("liverechargefirst"))
				{
					egenReportDaoObj = new EgenReportDao();
			        	
					ArrayList  Data = egenReportDaoObj.getliverechargefirstView( fromDate, toDate, reportOfEgen, reportFor);
			        	
					if(Data.size() <= 0)
					{
						request.setAttribute("message", "There is no trasnaction.");
						return "TranEGENReport";
					}
					
					request.setAttribute("showService", "Recharge");
					request.setAttribute("flag", "Y");
					request.setAttribute("RechargeData", Data);
					return "TranEGENReport";
				}
				if(reportOfEgen.equalsIgnoreCase("B2BDOMFlight"))
				{
					egenReportDaoObj = new EgenReportDao();
			        	
					ArrayList  Data = egenReportDaoObj.getliverechargefirstView( fromDate, toDate, reportOfEgen, reportFor);
			        	
					if(Data.size() <= 0)
					{
						request.setAttribute("message", "There is no trasnaction.");
						return "TranEGENReport";
					}
					
					request.setAttribute("showService", "B2BDOMFlight");
					request.setAttribute("flag", "Y");
					request.setAttribute("RechargeData", Data);
					return "TranEGENReport";
				}
				if(reportOfEgen.equalsIgnoreCase("B2BHotel"))
				{
					egenReportDaoObj = new EgenReportDao();
			        	
					ArrayList  Data = egenReportDaoObj.getliverechargefirstView( fromDate, toDate, reportOfEgen, reportFor);
			        	
					if(Data.size() <= 0)
					{
						request.setAttribute("message", "There is no trasnaction.");
						return "TranEGENReport";
					}
					
					request.setAttribute("showService", "B2BHotel");
					request.setAttribute("flag", "Y");
					request.setAttribute("RechargeData", Data);
					return "TranEGENReport";
				}
				if(reportOfEgen.equalsIgnoreCase("B2BBus"))
				{
					egenReportDaoObj = new EgenReportDao();
			        	
					ArrayList  Data = egenReportDaoObj.getliverechargefirstView( fromDate, toDate, reportOfEgen, reportFor);
			        	
					if(Data.size() <= 0)
					{
						request.setAttribute("message", "There is no trasnaction.");
						return "TranEGENReport";
					}
					
					request.setAttribute("showService", "B2BBus");
					request.setAttribute("flag", "Y");
					request.setAttribute("RechargeData", Data);
					return "TranEGENReport";
				}
			}
			
		}catch(Exception e)
		{
			System.out.println("Exception in EgenReportAction class");
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "searchPortalUser";
		}
		return null;   
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map session)
	{
		session = this.getSession();
	}

	@SuppressWarnings("rawtypes")
	public Map getSession()
	{
		return session;
	}
	  
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
	
	public HttpServletResponse getServletResponse() 
	{
		return response;
	}
}
