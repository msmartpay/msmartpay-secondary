package com.reports.TEP;

/*
 * Update By      : Manoj
 * Updated Date   :22 may 2012
 * Updated matter : convert all report into CSV and provide RAR file.
 */

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.ZipCreator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;

	public String execute() throws Exception{ 

		Map session=ActionContext.getContext().getSession();

		try
		{
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String param=request.getParameter("param");
			System.out.println("ReportAction param is "+param);
			if(param.equalsIgnoreCase("TranChannelReport")){
				return "TranChannelReport";
			}
			else if(param.equalsIgnoreCase("MgtChannelReport"))
			{
				return "MgtChannelReport";
			}
			else if(param.equalsIgnoreCase("FinanceChannelReport"))
			{
				return "FinanceChannelReport";
			}

			else if(param.equals("downloadReport"))
			{
				String portalId="0";
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String reportOf=request.getParameter("reportOf");
				String loginType=(String)session.get("loginType");
				if(!loginType.equalsIgnoreCase("SuperAdmin")){
					portalId=(String)session.get("adminUserPortalId");
				}
				//System.out.println("loginType------------------------"+loginType);
				//System.out.println("userType ::::::::::::::"+portalId);
				//System.out.println("reportOf ::"+reportOf);


				if(reportOf.equals("accountAdjustmentReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/AccountAdjustment/";
					String filePath2="AccountAdjustment"+".xls";
					String path="/usr/Trandata/";
					//					String path="E:/Work/";
					String zipFilePath2="AccountAdjustment"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getAccountAdjustmentReport(filePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					response.sendRedirect(zipFilePath);
				}
				if(reportOf.equals("agentAmountReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/AgentAmt/";
					String filePath2="AgentAmount"+".xls";
					String path="/usr/Trandata/";
					//					 String path="E:/Work/";
					String zipFilePath2="AgentAmount"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getAgentAmountReport(filePath,fromDate,toDate,reportOf,loginType,portalId);
					//System.out.println("file status is "+fileStatus);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/AgentAmt/"+zipFilePath2);	
				}
				if(reportOf.equals("agentDetailsReport"))
				{
					//correct
					String filePath1 = request.getRealPath("/")+"Reportfile/Agent_details/";
					String filePath2="AgentDetails"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getAgentDetailsReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{
						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/Agent_details/"+filePath2);	
					return null;
				}
				if(reportOf.equals("agentJournalReport"))
				{

					//correct
					String filePath1 = request.getRealPath("/")+"Reportfile/AgentJournal/";
					String filePath2="AgentJournal"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getAgentJournalReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					response.sendRedirect("Reportfile/AgentJournal/"+filePath2);	
					return null;
				}
				if(reportOf.equals("agentRegistrationReport"))
				{

					//correct
					String filePath1 = request.getRealPath("/")+"Reportfile/Agent_Registration_details/";
					String filePath2="AgentRegistration"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getAgentRegistrationReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/Agent_Registration_details/"+filePath2);	
				}
				if(reportOf.equals("billpayReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/BillMobile/";
					String filePath2="BillMobile"+".xls";
					String path="/usr/Trandata/";
					//					 String path="E:/Work/";
					String zipFilePath2="BillMobile"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getBillPaymentReport(filePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/BillMobile/"+zipFilePath2);	
				}
				if(reportOf.equals("busTransactionReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/Bus/";
					String filePath2="Bus"+".xls";
					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="Bus"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getBusTransactionReport(filePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/Bus/"+zipFilePath2);	
				}	
				if(reportOf.equals("cyberplatTransactionReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/cyberplat/";
					String filePath2="cyberplat"+".xls";
					String path="/usr/Trandata/";
					//					 String path="E:/Work/";
					String zipFilePath2="cyberplat"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getCyberplatBalanceReport(filePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/cyberplat/"+zipFilePath2);	
				}
				if(reportOf.equals("distributorAmountReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/DistAmount/";
					String filePath2="DistributorAmount"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getDistributorAmountReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/DistAmount/"+filePath2);	
				}
				if(reportOf.equals("distributorDetailsReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/Dist_details/";
					String filePath2="DistributorDetails"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getDistributorDetailsReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/Dist_details/"+filePath2);	
				}	
				if(reportOf.equals("distributorRegistrationReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/Dist_Registration_details/";
					String filePath2="DistributorRegistration"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getDistributorRegistrationReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/Dist_Registration_details/"+filePath2);	
				}
				if(reportOf.equals("distributorJournalReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/DistributorJournal/";
					String filePath2="Distributor Journal"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getDistributorJournalReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					response.sendRedirect("Reportfile/DistributorJournal/"+filePath2);	
					return null;
				}
				if(reportOf.equals("irctcRegisteredAgentReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/irctc_Reg_agent/";
					String filePath2="IRCTCRegAgent"+".xls";
					String path="/usr/Trandata/";
					//					 String path="E:/Work/";
					String zipFilePath2="IRCTCRegAgent"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getIRCTCRegisteredAgentReport(filePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/irctc_Reg_agent/"+zipFilePath2);	
				}
				if(reportOf.equals("liveDMRTransactionReport"))
				{

					String agentId=request.getParameter("agentId");
					if(null!=agentId){
						agentId=agentId.replaceAll("AG", "").replaceAll("ag", "").replaceAll("Ag", "").replaceAll("aG", "");
					}else
						agentId="all";

					String filePath1 = request.getRealPath("/")+"Reportfile/liveRech/";
					String filePath2="LiveDMR"+".xls";
					//String path="/usr/Trandata/";
					// String path="E:/Work/";
					//String zipFilePath2="LiveDMR"+".rar";
					//String filePath =path+filePath2;
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getLiveDMRReport(response,fromDate,toDate,reportOf,loginType,portalId,agentId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					return null;	
				}
				if(reportOf.equals("liveRechargeTransactionReport")  || "all".equalsIgnoreCase(reportOf)
						 || "liveAepsTransactionReport".equalsIgnoreCase(reportOf))
				{

					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getLiveRechargeReport(response,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 50000 which is not allowed for download");
						return "TranChannelReport";
					}

					return null;
				}
				if(reportOf.equals("mdJournalReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/MdJournal/";
					String filePath2="MdJournal"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getMdJournalReport(filePath2,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					response.sendRedirect("Reportfile/MdJournal/"+filePath2);	
					return null;
				}
				if(reportOf.equals("mdRegistrationReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/MD_Registration_details/";
					String filePath2="MdRegistration"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getMdRegistrationReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/MD_Registration_details/"+filePath2);
					return null;
				}
				if(reportOf.equals("mobileRechargeReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/MobileRech/";
					String filePath2="offlineMobRech"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getOfflineMobileRechargeReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/MobileRech/"+filePath2);	
					return null;
				}
				if(reportOf.equals("dthRechargeReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/MobileDth/";
					String filePath2="OfflineDTHRech"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getOfflineDthRechargeReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/MobileDth/"+filePath2);
					return null;
				}
				if(reportOf.equals("panCardReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/PanCard/";
					String filePath2="pancard"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getPanCardReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/PanCard/"+filePath2);	
					return null;
				}
				if(reportOf.equals("paymentGatewayReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/PaymentGateway/";
					String filePath2="PaymentGateway"+".xls";
					String path="/usr/Trandata/";
					//						 String path="E:/Work/";
					String zipFilePath2="PaymentGateway"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getPaymentGatewayReport(filePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect(zipFilePath);	

				}
				if(reportOf.equals("railTicketCancellationReport"))
				{

					String filePath1 = request.getRealPath("/")+"Reportfile/RailCancel/";
					String filePath2="RailCancellation"+".xls";
					String zipFilePath=filePath1+filePath2;
					ReportDao reportDaoObj=new ReportDao();

					String  fileStatus=reportDaoObj.getRailTicketCancellationReport(zipFilePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					response.sendRedirect("Reportfile/RailCancel/"+filePath2);	
				}
				if(reportOf.equals("railTicketTransactionReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/RailTran/";
					String filePath2="RailTransaction"+".xls";
					String path="/usr/Trandata/";
					//  	String path="E:/Work/";
					String zipFilePath2="RailTransaction"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getRailTicketBookingReport(filePath,fromDate,toDate,reportOf,loginType,portalId);

					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/RailTran/"+zipFilePath2);	
				}
				if(reportOf.equals("sLiveRechargeReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/success_liverecharge/";
					String filePath2="liverecharge"+".xls";
					String path="/usr/Trandata/";
					//							 String path="E:/Work/";
					String zipFilePath2="liverecharge"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getSuccessLiveRechargeReport(filePath,fromDate,toDate,reportOf,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord"))
					{

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect(zipFilePath);	
				}
				if(reportOf.equals("airDomesticReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/AirBookingReport/";
					String filePath2="AirBookingReport"+".xls";
					String path="/usr/Trandata/";
					//					  String path="E:/Work/";
					String zipFilePath2="AirBookingReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String fileStatus=reportDaoObj.getAirDomesticAccountReport(filePath,fromDate,toDate,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/AirBookingReport/"+zipFilePath2);

				}
				if(reportOf.equals("airDomesticCanReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/AirCancelReport/";
					String filePath2="AirCancellationReport"+".xls";
					String path="/usr/Trandata/";
					//						  String path="E:/Work/";
					String zipFilePath2="AirCancellationReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String fileStatus=reportDaoObj.getAirDomesticCancellationAccountReport(filePath,fromDate,toDate,loginType,portalId);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/AirCancelReport/"+zipFilePath2);

				}
				//  download Portal journal report
				if(reportOf.equals("portalJournalReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/PortalJournal/";
					String filePath2="PortalJournalReport"+".xls";
					String path="/usr/Trandata/";
					//					  String path="E:/Work/";
					String zipFilePath2="PortalJournalReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String fileStatus=reportDaoObj.getPortalJournalReport(filePath,fromDate,toDate,loginType,portalId,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "FinanceChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "FinanceChannelReport";
					}

					response.sendRedirect("Reportfile/PortalJournal/"+zipFilePath2);
				}
				//  download Portal account statement report
				if(reportOf.equals("accountStatementofPortal"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/PortalAccounStatement/";
					String filePath2="PortalAccounStatement"+".xls";
					String path="/usr/Trandata/";
					//			  String path="E:/Work/";
					String zipFilePath2="PortalAccounStatement"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String fileStatus=reportDaoObj.getPortalAccountStatReport(filePath,fromDate,toDate,loginType,portalId,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord"))
					{
						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/PortalAccounStatement/"+zipFilePath2);
				}
				// code for suspected report
				if(reportOf.equals("suspectReport"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/suspectReport/";
					String filePath2="SuspectReport"+".xls";

					String path="/usr/Trandata/";
					//	 String path="E:/Work/";
					String zipFilePath2="SuspectReport"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getSuspectDataReport(filePath,fromDate,toDate,portalId,loginType);

					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/suspectReport/"+zipFilePath2);
				}

				// Code for B2B AIR domestic Report
				if(reportOf.equals("B2BDomFlight"))
				{
					String filePath1 = request.getRealPath("/")+"Reportfile/B2BDomFlight/";
					String filePath2="B2BDomesticFlight"+".xls";

					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="B2BDomesticFlight"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getB2BDomesticFlightReport(filePath,fromDate,toDate,portalId,loginType,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}

					response.sendRedirect("Reportfile/B2BDomFlight/"+zipFilePath2);
				}

				if(reportOf.equals("B2BInterFlight")){
					String filePath1 = request.getRealPath("/")+"Reportfile/B2BInterFlight/";
					String filePath2="B2BInternationalFlight"+".xls";

					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="B2BInternationalFlight"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getB2BInternationalFlightReport(filePath,fromDate,toDate,portalId,loginType,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/B2BInterFlight/"+zipFilePath2);
				}
				if(reportOf.equals("bookinghotel")){
					String filePath1 = request.getRealPath("/")+"Reportfile/Bookinghotel/";
					String filePath2="BookingHotal"+".xls";

					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="BookingHotal"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getBookingHotalReport(filePath,fromDate,toDate,portalId,loginType,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/Bookinghotel/"+zipFilePath2);
				}

				if(reportOf.equals("opstransaction")){
					String filePath1 = request.getRealPath("/")+"Reportfile/OpsTransaction/";
					String filePath2="Opstransaction"+".xls";

					String path="/usr/Trandata/";
					//String path="E:/Work/";
					String zipFilePath2="Opstransaction"+".rar";
					String filePath =path+filePath2;
					String zipFilePath=filePath1+zipFilePath2;
					ReportDao reportDaoObj=new ReportDao();
					String  fileStatus=reportDaoObj.getOpsTrancationReport(filePath,fromDate,toDate, loginType,portalId,reportOf);
					if(fileStatus.equalsIgnoreCase("Norecord")){

						request.setAttribute("message","Data not available.");
						return "TranChannelReport";
					}
					if(fileStatus.equalsIgnoreCase("MoreRecord")){

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					ZipCreator zipObject=new ZipCreator();
					String result=zipObject.createRarFile(filePath, zipFilePath);
					if(result.equals("error"))
					{

						request.setAttribute("message","Transaction are more than 100000 which is not allowed for download");
						return "TranChannelReport";
					}
					response.sendRedirect("Reportfile/OpsTransaction/"+zipFilePath2);
				}

			}
			else if("viewReport".equalsIgnoreCase(param))
			{
				String portalId="0";
				String viewFromDate="";
				String viewToDate="";
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				String reportOf=request.getParameter("reportOf");
				String loginType=(String)session.get("loginType");

				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					portalId=(String)session.get("adminUserPortalId");
				}
				/*	if(reportOf.equalsIgnoreCase("busTransactionReport") || reportOf.equalsIgnoreCase("B2BDomFlight")){
					viewFromDate=request.getParameter("ViewFromDate");
					viewToDate =request.getParameter("ViewToDate");
				}
				if(!loginType.equalsIgnoreCase("SuperAdmin")){
					portalId=(String)session.get("adminUserPortalId");
				}*/
				System.out.println(" ew are here : " + reportOf);
				System.out.println("fromDate ::"+fromDate);
				System.out.println(" toDate "+toDate);
				System.out.println("reportOf ::"+reportOf);
				System.out.println("loginType ::"+loginType);
				System.out.println("portalId ::"+portalId);



				if("liveRechargeTransactionReport".equalsIgnoreCase(reportOf)  || "all".equalsIgnoreCase(reportOf)
						 || "liveAepsTransactionReport".equalsIgnoreCase(reportOf))
				{
					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getLiveRechargeReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					if (data.size() <=0)
					{
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}
					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "Recharge");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}else if("liveDMRTransactionReport".equalsIgnoreCase(reportOf))
				{

					String agentId=request.getParameter("agentId");
					if(null!=agentId){
						agentId=agentId.replaceAll("AG", "").replaceAll("ag", "").replaceAll("Ag", "").replaceAll("aG", "");
					}else
						agentId="all";

					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getLiveDMRReportView(fromDate,toDate,reportOf,loginType,portalId,request,agentId);
					if (data!=null && data.size()>0)
					{
						request.setAttribute("RechargeData", data);
						request.setAttribute("showService", "DMR");
						request.setAttribute("flag", "Y");

					}else{
						request.setAttribute("message","Transaction not available.");
					}
					return "TranChannelReport";
				}
				else if("busTransactionReport".equalsIgnoreCase(reportOf)){
					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getBusTransactionReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					if (data.size() <=0)
					{
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}
					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "BusData");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}
				else if("B2BDomFlight".equalsIgnoreCase(reportOf)){
					ReportDao reportDaoObj=new ReportDao();

					ArrayList  data=reportDaoObj.getB2BDomFlightTransactionReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					System.out.println("find tha data size " +data.size());
					if (data.size() <=0)
					{
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}

					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "B2BDomFlight");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}


				else if("bookinghotel".equalsIgnoreCase(reportOf)){
					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getBookingHotalReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					System.out.println("find tha data size " +data.size());
					if (data.size() <=0){
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}

					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "bookinghotel");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}

				else if("opstransaction".equalsIgnoreCase(reportOf)){
					//System.out.println(" inside opstransaction :::");
					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getopstransactionReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					System.out.println("find tha data size " +data.size());
					if (data.size() <=0){
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}

					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "opstransaction");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}

				else if("B2BInterFlight".equalsIgnoreCase(reportOf)){
					ReportDao reportDaoObj=new ReportDao();
					ArrayList  data=reportDaoObj.getB2BinterFlightTransactionReportView(fromDate,toDate,reportOf,loginType,portalId,request);
					if (data.size() <=0){
						request.setAttribute("message","Transaction not available.");
						return "TranChannelReport";
					}

					request.setAttribute("RechargeData", data);
					request.setAttribute("showService", "B2BInterFlight");
					request.setAttribute("flag", "Y");
					return "TranChannelReport";
				}
			}
			else
			{
				return "Error";
			}
		}
		catch(Exception e){
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println("Exception in ReportAction");
			System.out.println(e.toString());
			return "err";
		}
		return "err";   
	}

	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}

	public void setSession(Map session){
		session = this.getSession();
	}

	public Map getSession(){
		return session;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}	
