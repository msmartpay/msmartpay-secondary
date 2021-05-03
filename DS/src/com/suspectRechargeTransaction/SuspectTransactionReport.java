package com.suspectRechargeTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.LogWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;




	public class SuspectTransactionReport extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware 
	{
		 private static final long serialVersionUID = 1L;
		 private HttpServletRequest request;
	    
		 LogWriter log=new LogWriter();
		 suspectReportForm suspectReportForm=new suspectReportForm();		
		 public Object getModel(){
			 return suspectReportForm;
		 }
		 public String execute() throws Exception { 
			 @SuppressWarnings("unchecked")
			 Map<String, String> session = ActionContext.getContext().getSession();
			 System.out.println("we are in this SuspectTransactionReport");
			 try{
				 String Usertype="";
				 String EnterAgentId="";
				 String userId=session.get("distributorId");
				 String distributorInitial=session.get("distributorInitial");
				 String DistributorCompleteId=distributorInitial+userId;
				 System.out.println("ds id :::"+userId);
				 System.out.println("ds distributorInitial :::"+distributorInitial);
				 System.out.println(" DistributorCompleteId :::"+DistributorCompleteId);
			
				 SuspectRechargeTransactionDao suspectRechargeTransactionDao=new SuspectRechargeTransactionDao();
			
				 String param=suspectReportForm.getParam();		
			
				 System.out.println("param is :"+param);
			
				 if(param.equalsIgnoreCase("getReportPage")){
					 System.out.println("we are in getReportPage");
//					 ArrayList AgentDetail=suspectRechargeTransactionDao.getAgentId(userId);
//					 //System.out.println("AgentDetail ::"+AgentDetail);
//					 if(AgentDetail.size()<=0)
//					 {
//						 String  message2="dimly<body><table><tr><td><font color=\"red\" size=\"2\">Their is No agent.</td></tr></table></body></html>";
//						 request.setAttribute("message",message2) ;
//						 return "getPage";
//					 }			     
//					 request.setAttribute("AgentIdCollection", AgentDetail);
					 return "getPage";				    
				 }
			
				 if(param.equals("getSuspectReport"))
				 {
					 Usertype=suspectReportForm.getUsertype();	
					 System.out.println("getUsertype  :"+Usertype);
					 session.put("Usertype", Usertype);
					 if (Usertype.equalsIgnoreCase("SeachbyTransactionID")){
						 
						 String AgentTran_id=request.getParameter("EnterTran_id");
						 session.put("AgentTran_id", AgentTran_id);
						 System.out.println("AgentTran_id ::"+AgentTran_id);
						 
						 ArrayList<?> AgentTransactionHistory=suspectRechargeTransactionDao.getTranRechargeStatement(AgentTran_id);

						 if(AgentTransactionHistory.size()<=0)
						 {
							
							 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">Transaction not found.</td></tr></table></body></html>";
							 request.setAttribute("message", message);
							 return "SuspectReportDetails";					 
						 }
						 request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
						 return "SuspectReportDetails";					
					 }
					 if (Usertype.equalsIgnoreCase("SeachbyAgentID")){
							
						 EnterAgentId=suspectReportForm.getEnterAgentId();
						 session.put("EnterAgentId", EnterAgentId);
						 System.out.println("EnterAgentId::"+EnterAgentId);
					  
						 ArrayList<HashMap<String,Object>>AgentTransactionHistory=suspectRechargeTransactionDao.getAgentRechargeStatement(EnterAgentId,DistributorCompleteId);
						 System.out.println("AgentTransactionHistory ::"+AgentTransactionHistory);
						 if(AgentTransactionHistory.size()<=0)
						 {
							 System.out.println("inside no agent");
							 String  message="<html><body><table><tr><td><font color=\"red\" size=\"2\">No data to  display.</td></tr></table></body></html>";
							 request.setAttribute("message", message);
							 return "SuspectReportDetails";					 
						 }
					
						 request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
						 return "SuspectReportDetails";	
					 }	
//					 if (Usertype.equalsIgnoreCase("ds_id")){
//						 
//						 
//						 
//						 ArrayList AgentTransactionHistory=suspectRechargeTransactionDao.getAllAgentRechargeStatement(DistributorCompleteId);
//
//						 if(AgentTransactionHistory.size()<=0)
//						 {
//							 String  message="No data to  display";
//							 request.setAttribute("message", message);
//							 return "SuspectReportDetails";					 
//						 }
//						 request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
//						 return "SuspectReportDetails";					
//					 }
							     
				 }
  		
				 if(param.equalsIgnoreCase("updateSuspectFlag")){
				
					 if(userId==null){
						 String  message2="<html><body><table><tr><td><font color=\"red\" size=\"2\">Your session has been expire ,relogin to continue</td></tr></table></body></html>";

						 request.setAttribute("Loginmessage",message2);
			  
						 return "sessionexpired"; 
					 }

					 System.out.println("inside updateSuspectFlag ");  			
					 String transid1=(String)request.getParameter("transid");
					
					 System.out.println("transid1 ::::::::::::::::"+transid1);
					 String status=suspectRechargeTransactionDao.updateSuspectStatus(DistributorCompleteId,transid1);
					 if (status.equalsIgnoreCase("updated")){  
						 Usertype=session.get("Usertype");
						 System.out.println("Usertype :"+Usertype);
						 if (Usertype.equalsIgnoreCase("SeachbyAgentID")){
							 
							 EnterAgentId =session.get("EnterAgentId");
							 ArrayList<HashMap<String,Object>>AgentTransactionHistory=suspectRechargeTransactionDao.getAgentRechargeStatement(EnterAgentId,DistributorCompleteId);
							
							 String message = " <html><body><table><tr><td><font color=\"red\" size=\"2\">Your Report is submitted,and we will update you soon</td></tr></table></body></html>";
							 request.setAttribute("message", message);
							 request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
							 return "SuspectReportDetails";					
						 }
						 if (Usertype.equalsIgnoreCase("SeachbyTransactionID")){					
							 String AgentTran_id =session.get("AgentTran_id");
							 System.out.println("AgentTran_id  ::"+AgentTran_id);
							 ArrayList<?> AgentTransactionHistory=suspectRechargeTransactionDao.getTranRechargeStatement(AgentTran_id);
							 String message = " <html><body><table><tr><td><font color=\"red\" size=\"2\">Your Report is submitted,and we will update you soon</td></tr></table></body></html>";
							 request.setAttribute("message", message);			
							 request.setAttribute("AgentTransactionHistory", AgentTransactionHistory);
							 return "SuspectReportDetails";	   							     
						 }
					 }        		
					 return "searchReport";				
				 }			
			
			 }catch(Exception e){			
				 System.out.println("exception in suspect transaction report in md panel");
			 }		
		
			 return null;
		 }

		 public void setServletRequest(HttpServletRequest httpServletRequest) {
			 this.request = httpServletRequest;
		 }
		 public HttpServletRequest getServletRequest() {
				return request;
		 }
		 @Override
		 public void setServletResponse(HttpServletResponse arg0) {
			
		 }
	}
