package com.report.agentAmountHistory;




import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.LogWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.report.form.reportForm;

public class AgentTransactionHistoryAction extends ActionSupport implements  ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	Logger logger = Logger.getLogger(AgentTransactionHistoryAction.class);
	LogWriter log=new LogWriter();
	private  reportForm reportForm=new reportForm();
	public Object getModel(){return reportForm;}
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=reportForm.getParam();
			AgentTransactionHistoryDao AgentTransactionHistoryDao=new AgentTransactionHistoryDao();
			log.print("param value in AgentAmountHistoryAction is "+param, logger);
			String userId=(String)session.get("distributorId");
			if(userId==null)
			{
				String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";
				request.setAttribute("Loginmessage",message2);
				return "sessionexpired"; 
			}
		  
			String distributorInitial=(String)session.get("distributorInitial");
			String DistributorCompleteId=distributorInitial+userId;
		  
	//-------------------------------------call to get id----------------------	  
			if(param.equals("getAgentId"))
			{
			  
				ArrayList<HashMap<String,Object>> AgentIdCollection=AgentTransactionHistoryDao.getAgentIdCollection(userId);
			  
			 
				if(AgentIdCollection.size()!=0)
				{
					request.setAttribute("AgentIdCollection", AgentIdCollection) ;
					return "AgentAmountHistorySearch";
				}	     
				else
				{		    	 
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No agent exist in the under this distributor Id</td></tr></table></body></html>";
					request.setAttribute("message", message) ;
					return "AgentAmountHistorySearch";	 
				}	     
			}  
		  
		  //------------------------- Amount history----------------------------------
		  
			if(param.equals("AmountHistory"))
			{		  
				int start=0;
				int end=0;
				int pagecount=0;
				int count=0;
				int count1=0;
				int pno=1;
				int check1TH=0;
				int check2TH=9;
				session.put("check1TH",check1TH);
				session.put("check2TH",check2TH);
				session.put("start",start);
				session.put("end",end);
				session.put("pagecount",pagecount);
				session.put("count",count);
				session.put("count1",count1);
				session.put("pno",pno);
				int  modcount=count1;
			  
				String EnterAgentId=reportForm.getEnterAgentId();
				session.put("EnterAgentId", EnterAgentId);
				String reportof=reportForm.getReportof();
				session.put("reportof", reportof);
				String type=reportForm.getType();
				session.put("type", type);
				String fromDt=reportForm.getFromDate();
				session.put("fromDate", fromDt);
				String toDt=reportForm.getToDate();
				session.put("toDate", toDt);			  
				
				String service="";
	//----------------------------------call to get agent verification----------------------------		
			  
				HashMap<String,Object> AgentVerified=AgentTransactionHistoryDao.getAgentVerified(EnterAgentId);
				String  agentid=(String)AgentVerified.get("agentId");
				session.put("agentid",agentid);
				String Status=(String)AgentVerified.get("status");
				if(Status.equalsIgnoreCase("Noexist"))
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">AgentId does not exist</td></tr></table></body></html>";
			    	 
					request.setAttribute("message", message);
					return "AgentAmountHistorySearch";
				}
			 
				if(reportof.equalsIgnoreCase("BUS")  && type.equalsIgnoreCase("book") )
				{
					service="Bus TBooking"; 
				}
				else if(reportof.equalsIgnoreCase("BUS")  && type.equalsIgnoreCase("cancel") )
				{
					service="Bus TCancel"; 
				}
				else if(reportof.equalsIgnoreCase("Rail")  && type.equalsIgnoreCase("book") )
				{
					service="RailTbooking"; 
				}
				else  if(reportof.equalsIgnoreCase("Rail")  && type.equalsIgnoreCase("cancel") )
				{
					service="RailTicketCancel"; 
				}
				else if(reportof.equalsIgnoreCase("Air")  && type.equalsIgnoreCase("book") )
				{
					service="Ticket Booking - Air"; 
				}
				else  if(reportof.equalsIgnoreCase("Air")  && type.equalsIgnoreCase("cancel") )
				{
					service="Ticket Cancellation - Air"; 
				}
				else
				{
					service=reportof; 
				}
			  
				System.out.println("service :"+service);
				System.out.println("EnterAgentId :"+EnterAgentId);
				System.out.println("type :"+type);
				System.out.println("fromDt :"+fromDt);
				System.out.println("toDt :"+toDt);
				System.out.println("Status :"+Status);
			  
			  
				session.put("service",service);			  
				ArrayList<HashMap<String,Object>>AgentTransactionHistory=AgentTransactionHistoryDao.getAgentTransationHistory(toDt,fromDt,userId,service,type,modcount,start,end,EnterAgentId,DistributorCompleteId,agentid,session);
				System.out.println("AgentTransactionHistory :"+AgentTransactionHistory);
				if(AgentTransactionHistory.size()>0)
				{
					System.out.println("inside data black: ");
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData"; 
				}
				else
				{
					System.out.println("no data is present:----");
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData";
				}		     
			}		  
		  
//---------------------------------------------Call for page number------------------		  
		  
			if(param.equalsIgnoreCase("pageno"))
			{       
			  
				String type=(String)session.get("type");
				String service=(String)session.get("service");
				String toDt=(String) session.get("toDate");
				String fromDt=(String) session.get("fromDate");
				// System.out.println("value of fromDt of================"+fromDt);
				String  EnterAgentId=(String)session.get("EnterAgentId");
				System.out.println("EnterAgentId---"+EnterAgentId);
				String  agentid=(String)session.get("agentid");
				int end=0;
				int start=0;
				int pno=reportForm.getPgno();
				session.put("pno",pno);
				end=pno*100;
				start=end-100;
				int  modcount=0;
			 
			 
				int check1TH=(Integer)session.get("check1TH");
				int check2TH=(Integer)session.get("check2TH");
				int pagecheck=pno-1;
				if(check2TH==pno)
				{		    	
					check1TH=check1TH-2;
					check1TH=check1TH+10;

					check2TH=check2TH-1;
					check2TH=check2TH+10;
				}			
				session.put("check1TH",check1TH);
				session.put("check2TH",check2TH);
			 
				
				ArrayList<HashMap<String,Object>>AgentTransactionHistory=AgentTransactionHistoryDao.getAgentTransationHistory(toDt,fromDt,userId,service,type,modcount,start,end,EnterAgentId,DistributorCompleteId,agentid,session);

				if(AgentTransactionHistory!=null)
				{
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData";
				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData";
				}
			}	  
		  	  
		  
		//-------------------------------------NEXT--------------------------------------------------
			if(param.equalsIgnoreCase("next"))
			{
				String type=(String)session.get("type");
				// System.out.println("value of type of================"+type);
				String service=(String)session.get("service");
				String toDt=(String) session.get("toDate");
				// System.out.println("value of toDt of================"+toDt);
				String fromDt=(String) session.get("fromDate");
				// System.out.println("value of fromDt of================"+fromDt);
				String  start1=(String)request.getParameter("a");
				String end1=(String)request.getParameter("b");
				String  EnterAgentId=(String)session.get("EnterAgentId");
				//System.out.println("EnterAgentId---"+EnterAgentId);
				String  agentid=(String)session.get("agentid");
			  
				int pgno=reportForm.getPno();
				int pno=pgno+1;
				session.put("pno",pno);
				int start=Integer.parseInt( start1);
				int end=Integer.parseInt(end1);
				start=end+1;
				int modcount=reportForm.getCount1();
			  
				int check1TH=(Integer)session.get("check1TH");
				int check2TH=(Integer)session.get("check2TH");
			    
				if(check2TH==pgno)
				{
					check1TH=check1TH-2;
					check1TH=check1TH+10;
				  
					check2TH=check2TH-1;
					check2TH=check2TH+10;
				}     
				
				session.put("check1TH",check1TH);
				session.put("check2TH",check2TH);
				ArrayList<HashMap<String,Object>>AgentTransactionHistory=AgentTransactionHistoryDao.getAgentTransationHistory(toDt,fromDt,userId,service,type,modcount,start,end,EnterAgentId,DistributorCompleteId,agentid,session);
			  
				if(AgentTransactionHistory!=null)
				{
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData"; 
				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData";
				}
			}	  
		  
		  //-----------------------------------PREVIOUS----------------------------------------------  
			  
			if(param.equalsIgnoreCase("previous"))
			{
				
				String type=(String)session.get("type");
				String service=(String)session.get("service");
				String toDt=(String) session.get("toDate");
				String fromDt=(String) session.get("fromDate");
				String  start1=(String)request.getParameter("a");
				String end1=(String)request.getParameter("b");
				String  EnterAgentId=(String)session.get("EnterAgentId");
				String  agentid=(String)session.get("agentid");
				
				int pgno=reportForm.getPno();
				int start=Integer.parseInt( start1);
				int end=Integer.parseInt(end1);
				int pno=pgno-1;
				session.put("pno",pno);
				end=start;
				start=start-100;
				int modcount=reportForm.getCount1();
			  
				int check1TH=(Integer)session.get("check1TH");
				int check2TH=(Integer)session.get("check2TH");
				if(check1TH==pgno)
				{
					check1TH=check1TH-10;
					check2TH=check2TH-10;
				}
			      
				
				session.put("check1TH",check1TH);
				session.put("check2TH",check2TH);
				
				ArrayList<HashMap<String,Object>>AgentTransactionHistory=AgentTransactionHistoryDao.getAgentTransationHistory(toDt,fromDt,userId,service,type,modcount,start,end,EnterAgentId,DistributorCompleteId,agentid,session);

				if(AgentTransactionHistory!=null)
				{
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData"; 
				}
				else
				{
					String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display</td></tr></table></body></html>";
					request.setAttribute("message", message);
					request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
					return "AgentTransactionData";
				}
				
			}
//-------------------------------------Download------------------------------------		  
			if(param.equalsIgnoreCase("download"))
			{
				String toDt=(String) session.get("toDate");
				System.out.println("toDt----"+toDt);
				String fromDt=(String) session.get("fromDate");
				System.out.println("fromDt----"+fromDt);
				String service=(String) session.get("service");
				System.out.println("service----"+service);
				String agentid=(String) session.get("agentid");
				System.out.println("agentid----"+agentid);
				String enterAgentId=(String)session.get("EnterAgentId");
				System.out.println("enterAgentId----"+enterAgentId);
				String reportof=(String) session.get("reportof");
				System.out.println("reportof----"+reportof);
			  
				String filePath2="";
				String filePath1 = request.getRealPath("/")+"Reportfile/agentTransactionHistory/";
		 
				filePath2=enterAgentId+".xls"; 
				String filePath =filePath1+filePath2;
				File f = new File(filePath);
				String headerName="";
        //------------------------logic for header name in report--------------------------------
				
				if(reportof.equals("liveMobRech") || reportof.equals("OffLineMobBil"))
				{
					headerName="Mobile Number";
				}
				if(reportof.equals("liveDTHRech"))
				{
					headerName="Mobile Number";
				}
                  
				if(reportof.equals("BUS") || reportof.equals("Rail"))
				{
					headerName="PNR  Number";
				}
				if(reportof.equals("OffLineLicReq"))
				{
					headerName="Policy Number";
				}
                  
				if(reportof.equals("OffLinePanReq") )
				{
					headerName="Through";
				}    
			  
				if(reportof.equalsIgnoreCase("Air") )
				{
					headerName="Reference ID";
				} 
				System.out.println("headerName :"+headerName);
				if (!f.exists())
				{
	 
					String  fileStatus=AgentTransactionHistoryDao.downloadDepositReport(filePath,toDt,fromDt,userId,agentid,service,headerName);
					String filepathdownload="agentTransactionHistory/"+filePath2;
					session.put("fileStatus",filepathdownload);
					return  "successdownload";
				}
				else {
					
					boolean success = f.delete();
					
					if (!success)
					{
						String filepathdownload="agentTransactionHistory/"+filePath2;
						session.put("fileStatus",filepathdownload);
						return "successdownload";
						
					} 
					else 
					{
						String  fileStatus=AgentTransactionHistoryDao.downloadDepositReport(filePath,toDt,fromDt,userId,agentid,service,headerName);
						String filepathdownload="agentTransactionHistory/"+filePath2;
						session.put("fileStatus",filepathdownload);
						return "successdownload";					  
					}
				} 
			}				  
		}	  
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
			return "error";
		}
		return "error";     
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
	}
}