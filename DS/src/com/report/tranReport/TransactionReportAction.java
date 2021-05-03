package com.report.tranReport;

/**
 *  @author Arshad 
 *  Created Date 16/2/2013
 *  Created Matter to provide the report
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zip.ZipCreator;

public class TransactionReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
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
				String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";
				
				request.setAttribute("Loginmessage",message2);
				return "sessionexpired"; 
			}
			if(param.equalsIgnoreCase("getTranReportPage")){
				return "getReportPage"; 
			}
			if(param.equalsIgnoreCase("getReport")){
				
				String reportOf=request.getParameter("reportOf");
				System.out.println("reportOf ::"+reportOf);
				TransactionReportDao daoObj=new TransactionReportDao();
				if(reportOf.equals("billpayReport")){
					
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BillMobile"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/work/";
					String zipFilePath2="BillMobile"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					
					String  fileStatus=daoObj.getBillPaymentReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
				}
				else if(reportOf.equals("busTranBook")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BusBooking"+".xls";
					
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="BusBooking"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=daoObj.getBusTranBookReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
				}
				else if(reportOf.equals("busTranCan")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="BusCancellation"+".xls";
					
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="BusCancellation"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=daoObj.getBusTranCanReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
				}	
				
				else if(reportOf.equals("licPremiumReport")){
					
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="LIC"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="LIC"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					String  fileStatus=daoObj.getLICPremiumReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
             	
				}
				else if(reportOf.equals("liveRechargeTransactionReport")){
					
					
					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="LiveRecharge"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="LiveRecharge"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
	
					
					
					String  fileStatus=daoObj.getLiveRechargeReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
				}
				
				else if(reportOf.equals("panCardReport")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="pancard"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="pancard"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					     
					String  fileStatus=daoObj.getPanCardReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
	             	
				}
				else if(reportOf.equals("suspectReport")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="SuspectReport"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="SuspectReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
		
					
					String  fileStatus=daoObj.getSuspectDataReport(filePath,reportOf,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
	             	
				}	
				  
				else if(reportOf.equals("airDomesticReport")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="AirBookingReport"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="AirBookingReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					String fileStatus=daoObj.getAirDomesticAccountReport(filePath,userId);
					System.out.println(fileStatus);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
				}
				else if(reportOf.equals("airDomesticCanReport")){

					String filePath1 = request.getRealPath("/")+"Reportfile/ClientReport/";
					String filePath2="AirCancellationReport"+".xls";
					String path="C:/usr/Trandata/";
//					String path="E:/Work/";
					String zipFilePath2="AirCancellationReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					
					String fileStatus=daoObj.getAirDomesticCancellationAccountReport(filePath,userId);
					if(fileStatus.equalsIgnoreCase("NoRecord")){
						
						request.setAttribute("message","There is no transaction in report.");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("MoreRecord")){
						
						request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
						return "downloadReport";
					}
					else if(fileStatus.equalsIgnoreCase("ERROR")){
						
						request.setAttribute("message","Due to some technical problem we can not provide you the report.");
						return "downloadReport";
					}else if(fileStatus.equalsIgnoreCase("Success")){
						ZipCreator zipObject=new ZipCreator();
				    	String result=zipObject.createRarFile(filePath, zipFilePath);
				    	if(result.equals("error")){
				    		
				    		request.setAttribute("message","Transaction are more than 20000 which is not allowed for download");
				    		return "downloadReport";
				    	}
				    	response.sendRedirect("Reportfile/ClientReport/"+zipFilePath2);	
					}
					
				}
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
			return "error";
		}
		return "error";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	 public void setServletResponse(HttpServletResponse response) {
			this.response=response;
			
		}
}
