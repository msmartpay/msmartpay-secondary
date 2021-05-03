package com.report.accountStmt;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public final class AccountStatementAction extends ActionSupport{

	Logger logger=Logger.getLogger(AccountStatementAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute()
	throws Exception {
		
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession();
		
		String agentID="";
		try
		{
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			logger.info("TEP ,Class is AccountStatementAction ,Param is "+param);

			if(param.equalsIgnoreCase("accountStatementPage")){
				
				
				AccountStatementDao daoObj=new AccountStatementDao();
				ArrayList<HashMap<String,String>> listData=daoObj.getAccountStmtOfAgentOnClick(agentID);
				int size=listData.size();
				logger.info("Size of Data is : "+size);
				if(size==0){
					request.setAttribute("message", "There is No Data for Requested Date.");
				}
				request.setAttribute("AccountStmtData", listData);
				return "AccounStmtPage";
			}else if(param.equalsIgnoreCase("SenderAccountStatementPage")){
				return "SenderAccounStmtPage";
			}
			else if(param.equalsIgnoreCase("SenderAccountStatement")){
				
				String senderId=request.getParameter("senderId");
				AccountStatementDao daoObj=new AccountStatementDao();
				ArrayList<HashMap<String,String>> listData=daoObj.getSenderAccountStmtOfAgentOnClick(agentID,senderId);

				if(listData!=null && listData.size()>=0){
					request.setAttribute("AccountStmtData", listData);
					
				}else{
					request.setAttribute("message", "There is No Data for Requested Sender.");
				}
				
				return "SenderAccounStmtPage";
			}else if(param.equalsIgnoreCase("RDmrAccountStatementDetails")){
				
				String tranNo=request.getParameter("tranNo");
				String transactionId=request.getParameter("AgenttranNo");
				
				AccountStatementDao daoObj=new AccountStatementDao();
				HashMap<String,String> listData=daoObj.getDMRAccountStmtDetails(agentID,tranNo,transactionId);

				if(listData!=null){
					request.setAttribute("AccountStmtData", listData);
					
				}else{
					request.setAttribute("message", "There is No Data for Requested Sender.");
				}
				
				return "RDmrAccountStatementDetails";
			}
			else if(param.equalsIgnoreCase("getAccountStmt")){
				
				String fromdate=request.getParameter("fromdate");
				String todate=request.getParameter("todate");
				AccountStatementDao daoObj=new AccountStatementDao();
				ArrayList<HashMap<String,String>> listData=daoObj.getAccountStmtOfAgent(agentID,fromdate,todate);
				int size=listData.size();
				logger.info("Size of Data is : "+size);
				if(size==0){
					request.setAttribute("message", "There is No Data in Requested Date.");
				}
				request.setAttribute("AccountStmtData", listData);
				return "AccounStmtPage";
			}
			else if(param.equalsIgnoreCase("AccountStmtInfo")){
				
				String tranNo=request.getParameter("tranNo");
				String service=request.getParameter("service");
				String AgenttranNo=request.getParameter("AgenttranNo");
				//logger.info(AgenttranNo);
				AccountStatementDao daoObj=new AccountStatementDao();
				HashMap<String,String> AccountDetailsMap=new HashMap<String,String> ();
				String url="";
				if(service.equalsIgnoreCase("Recharge-MOB")||service.equalsIgnoreCase("Recharge-DTH")||service.equalsIgnoreCase("Recharge-Data Card")||service.equalsIgnoreCase("Bill-Pay")){
					
					AccountDetailsMap=daoObj.getAccountWIndowInfoForRecharge(tranNo);
					url="AccounStmtWindow";
				}
				else if(service.equalsIgnoreCase("Refund-MOB")||service.equalsIgnoreCase("Refund-DTH")||service.equalsIgnoreCase("Refund-Data Card")|| service.equalsIgnoreCase("Refund-Bill-Pay")){
					
					AccountDetailsMap=daoObj.getAccountWIndowInfoForAll(tranNo);
					url="RefundWindow";
				}
				else if(service.equalsIgnoreCase("SMS Charge") || service.equalsIgnoreCase("Account Adjustment") || service.equalsIgnoreCase("DS - Agent") || service.equalsIgnoreCase("AG TB-Push")){
					
					AccountDetailsMap=daoObj.getAccountWIndowInfoForAll(tranNo);
					url="RefundWindow";
				}else if(service.equalsIgnoreCase("Recharge-MOB-Ops")){
					AccountDetailsMap=daoObj.getAccountWIndowInfoForOffline(AgenttranNo,tranNo);
					url="AccounStmtWindow";
				}else if(service.equalsIgnoreCase("Test-Merit")){
					AccountDetailsMap=daoObj.getAccountWIndowInfoForOps(AgenttranNo,tranNo);
					url="AccounStmtWindowOps";
				}
				else {
					AccountDetailsMap=daoObj.getAccountWIndowInfoForAll(tranNo);
					url="RefundWindow";
				}
				
				
				AccountDetailsMap.put("service1", service);
				request.setAttribute("AccountDetailsMap", AccountDetailsMap);
				return url;
			}
			else if(param.equalsIgnoreCase("downloadReportPage")){
				
				return "downloadReportPage";
			}
			else if(param.equalsIgnoreCase("downloadReport")){

				String reportOf=request.getParameter("reportOf");
				String type=request.getParameter("type");

				if("DownloadAccountStatement".equalsIgnoreCase(reportOf))
				{
					
					if(type.equalsIgnoreCase("current")){
						String filePath1 = request.getServletContext().getRealPath("/")+"Reportfile/AccounStmt/";
						String filePath2="AccountStmt"+".xls";
						//String filePathDownload="AccounStmt"+filePath2;
						//String path="/usr/Trandata/";
						//String path="F:/";
						//String zipFilePath2="AccountStmt"+agentID +".rar";
						String filePath =filePath1+filePath2;
						//String zipFilePath=filePath1+zipFilePath2;
						AccountStatementDao daoObj=new AccountStatementDao();
						String  fileStatus=daoObj.getAgentAccountStmtReport(filePath,agentID);

						if(fileStatus.equalsIgnoreCase("Norecord")){

							request.setAttribute("message","There is No Data for Requested Date.");
							return "downloadReportPage";
						}else if(fileStatus.equalsIgnoreCase("error")){
							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}
						
						//FileUtils.copyFile(new File(filePath), new File("Reportfile/AccounStmt/"+filePath2));
						
						/*ZipCreator zipObject=new ZipCreator();
						String result=zipObject.createRarFile(filePath, zipFilePath);
						if(result.equals("error")){

							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}*/
						response.sendRedirect("Reportfile/AccounStmt/"+filePath2 );
						return "downloadReportPage";
					}
					else if(type.equalsIgnoreCase("bydaterange")){
						String fromDate=request.getParameter("fromDate");
						String toDate=request.getParameter("toDate");
						logger.info("AccountStatementAction.execute()"+fromDate+" "+toDate);
						String filePath1 = request.getServletContext().getRealPath("/")+"Reportfile/AccounStmt/";
						String filePath2="AccountStmt"+".xls";
						//String filePathDownload="AccounStmt"+filePath2;
						//String path="/usr/Trandata/";
						//String path="E:/Work/";
						//String zipFilePath2="AccountStmt"+agentID +".rar";
						String filePath =filePath1+filePath2;
						//String zipFilePath=filePath1+zipFilePath2;
						AccountStatementDao daoObj=new AccountStatementDao();
						String  fileStatus=daoObj.getAgentAccountStmtReport(filePath,agentID,fromDate,toDate);

						if(fileStatus.equalsIgnoreCase("Norecord")){

							request.setAttribute("message","There is No Data for Requested Date.");
							return "downloadReportPage";
						}else if(fileStatus.equalsIgnoreCase("error")){
							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}
						/*ZipCreator zipObject=new ZipCreator();
						String result=zipObject.createRarFile(filePath, zipFilePath);
						if(result.equals("error")){

							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}*/
						response.sendRedirect("Reportfile/AccounStmt/"+filePath2 );
						return "downloadReportPage";
					}
					
				}else{
					
					if(type.equalsIgnoreCase("current")){

						String senderId=request.getParameter("senderId");
						AccountStatementDao daoObj=new AccountStatementDao();

						String filePath1 = request.getServletContext().getRealPath("/")+"Reportfile/AccounStmt/";
						String filePath2="SenderAccountStmt"+".xls";
						//String filePathDownload="AccounStmt"+filePath2;
						//String path="/usr/Trandata/";
						//String path="E:/Work/";
						//String zipFilePath2="SenderAccountStmt"+agentID +".rar";
						String filePath =filePath1+filePath2;
						//String zipFilePath=filePath1+zipFilePath2;

						String  fileStatus=daoObj.getSenderAccountStmtReport(filePath,senderId);

						if(fileStatus.equalsIgnoreCase("Norecord")){

							request.setAttribute("message","There is No Data for Requested Date.");
							return "downloadReportPage";
						}else if(fileStatus.equalsIgnoreCase("error")){
							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}
						/*ZipCreator zipObject=new ZipCreator();
						String result=zipObject.createRarFile(filePath, zipFilePath);
						if(result.equals("error")){

							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}*/
						response.sendRedirect("Reportfile/AccounStmt/"+filePath2 );


						return "downloadReportPage";
					}else if(type.equalsIgnoreCase("bydaterange")){

						String senderId=request.getParameter("senderId");
						String fromDate=request.getParameter("fromDate");
						String toDate=request.getParameter("toDate");
						AccountStatementDao daoObj=new AccountStatementDao();

						String filePath1 = request.getServletContext().getRealPath("/")+"Reportfile/AccounStmt/";
						String filePath2="SenderAccountStmt"+".xls";
						//String filePathDownload="AccounStmt"+filePath2;
						//String path="/usr/Trandata/";
						//String path="E:/Work/";
						//String zipFilePath2="SenderAccountStmt"+agentID +".rar";
						String filePath =filePath1+filePath2;
						//String zipFilePath=filePath1+zipFilePath2;

						String  fileStatus=daoObj.getSenderAccountStmtReport(filePath,senderId,fromDate,toDate);

						if(fileStatus.equalsIgnoreCase("Norecord")){

							request.setAttribute("message","There is No Data for Requested Date.");
							return "downloadReportPage";
						}else if(fileStatus.equalsIgnoreCase("error")){
							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}
						/*ZipCreator zipObject=new ZipCreator();
						String result=zipObject.createRarFile(filePath, zipFilePath);
						if(result.equals("error")){

							request.setAttribute("message","We are facing technical issues. Please try later.");
							return "downloadReportPage";
						}*/
						response.sendRedirect("Reportfile/AccounStmt/"+filePath2 );


						return "downloadReportPage";
					}
				}
				
			}
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
	return "ERROR";
	}
}
