package com.AgentReport;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zip.ZipCreator;

public class RarAgentReport  extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response = null;
	String userId=null;
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=(String)request.getParameter("param");
			System.out.println("class is TransactionReportAction and param is "+param);
			userId=(String)session.get("distributorId");
			if(userId==null){
				String  message2="Your Login session has Expired. Please Login to Continue.";
				
				request.setAttribute("Loginmessage",message2);
				return "sessionexpired"; 
			}
			if(param.equalsIgnoreCase("getReportPage")){
				return "getReportPage";
			}else if(param.equalsIgnoreCase("download")){
				
				String searchBy=request.getParameter("searchBy");
				if(searchBy.equalsIgnoreCase("DownloadATT")){
					
					String date=request.getParameter("date");
					
					RarAgentReportDao daoObj=new RarAgentReportDao();			  
				//	String filePath1 = request.getRealPath("/")+"Reportfile/AccountAdjustment/";
					String filePath1 = request.getRealPath("/")+"Reportfile/agentTransactionHistory/";
					String filePath2="AgentAccountStatment"+".xls";
				//	String path="Z:/Trandata/";	
				//	String path="E:/Work/";
					String path="/usr/Trandata/";
					String zipFilePath2="AgentAccountStatment"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					String fileStatus=daoObj.getATTReport(userId,filePath,date);
					
					if(fileStatus.equalsIgnoreCase("NoRecord")){					
						request.setAttribute("message","Data is Not Available.");
						return "getReportPage";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){					
						request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
						return "getReportPage";
					}
					if(fileStatus.equalsIgnoreCase("ERROR")){					
						request.setAttribute("message","Process aborted due to Technical Failure.");
						return "getReportPage";
					}
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	if(result.equals("error")){		    		
			    		request.setAttribute("message","Process aborted due to Technical Failure.");
			    		return "getReportPage";
			    	}	    
					response.sendRedirect("Reportfile/agentTransactionHistory/"+zipFilePath2);	
				}else if(searchBy.equalsIgnoreCase("DownloadLRT")){

					
					String date=request.getParameter("date");
					
					
					RarAgentReportDao daoObj=new RarAgentReportDao();			  
					String filePath1 = request.getRealPath("/")+"Reportfile/agentTransactionHistory/";
					String filePath2="RechargeTransaction"+".xls";
//					String path="Z:/Trandata/";	
				//	String path="E:/Work/";
					String path="/usr/Trandata/";
					String zipFilePath2="RechargeTransaction"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					String  fileStatus=daoObj.getLRTReport(userId,filePath,date);			
					
					if(fileStatus.equalsIgnoreCase("Norecord")){					
						request.setAttribute("message","Data is Not Available.");
						return "getReportPage";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){					
						request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
						return "getReportPage";
					}
					if(fileStatus.equalsIgnoreCase("ERROR")){					
						request.setAttribute("message","Process aborted due to Technical Failure.");
						return "getReportPage";
					}
					ZipCreator zipObject=new ZipCreator();
			    	String result=zipObject.createRarFile(filePath, zipFilePath);
			    	if(result.equals("error")){		    		
			    		request.setAttribute("message","Process aborted due to Technical Failure.");
			    		return "getReportPage";
			    	}	    
					response.sendRedirect("Reportfile/agentTransactionHistory/"+zipFilePath2);
				
				}else{
					request.setAttribute("message","Report Not Available..");
		    		return "getReportPage";
				}
					
			}else{
				request.setAttribute("message","Transaction Size is More Than Allowed Limit.");
	    		return "getReportPage";		
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			return "getReportPage";
		}
		return "getReportPage";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	 public void setServletResponse(HttpServletResponse response) {
			this.response=response;
			
		}
}
