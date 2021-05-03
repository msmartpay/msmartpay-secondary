

/*
 Class Property :  This class (AgentDepositAction) is created to get request data of agent deposit and process it accordingly.

  Created Date   : 23-Dec-2011 at 19:55 PM.
  Created By     : Amit Pathak

  Updated Date   : 13-march-2012
  Update By      :Amit Pathak

*/

package com.agentDeposit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.common.LogWriter;
import com.dsamount.DistributorJournalForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AgentDepositAction extends ActionSupport implements ModelDriven<Object> ,
ServletRequestAware,ServletResponseAware,ServletContextAware
{
	private ServletContext context = null;
	 private static final long serialVersionUID = 1L;
	 private HttpServletRequest request;
     Logger logger = Logger.getLogger(AgentDepositAction.class);
	 LogWriter log=new LogWriter();
	 private  DistributorJournalForm DistributorJournal=new DistributorJournalForm();
	 public Object getModel(){return DistributorJournal;}
	  @SuppressWarnings("unchecked")
	  
	  
	  public String execute() throws Exception { 
		  try 
		  { 
			  Map session = ActionContext.getContext().getSession();
			  String param=DistributorJournal.getParam();
			  AgentDepositDao AgentDepositDao=new AgentDepositDao();
			  log.print("param value in AgentDepositAction is "+param,logger);
		
			  String userId=(String)session.get("distributorId");
			  if(userId==null){
				  String  message2="Your Login session has Expired. Please Login to Continue.";
				  
				  request.setAttribute("Loginmessage",message2);
				  return "sessionexpired"; 
			  }
			  if(param.equals("agentDepositRequest"))
			  {		  
				  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
				  if(DepositDetails.size()!=0)
				  {
					  request.setAttribute("DepositDetails",DepositDetails);
					  return "DepositRequest";
				  }
				  else
				  {
					 // String  message="Data is Not Available.";
					  request.setAttribute("message", "");
					  return "DepositRequest";			  
				  }		  
			  }			  
			  //--------------------------------Block to update agent deposit------------------------------------
			  if("UpdateDepositRequest".equalsIgnoreCase(param))
			  {
				  String Tnxstatus=(String)context.getAttribute(userId);
				  if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(userId))
				  {
					  context.setAttribute(userId, userId); 
					  
					  String TransactionNo=DistributorJournal.getTransactionJournalId();
					  double BankCharges=DistributorJournal.getBankCharges();
					  String Status=DistributorJournal.getStatus();
					  String agentid=DistributorJournal.getAgentid();
					  String ipAddress=DistributorJournal.getIpAddress();
					  double Amount=DistributorJournal.getAcceptedAmount();
					  double amtOfCharege=0.0;			
			  
					  //-------------------method to get Distributor balance------------------		  

					  String DisBalance=AgentDepositDao.getDsBalance(userId);
					  //----------------Method to update 	Agent Deposit request in case of selected status is ACCEPTED ---------- 
			
					  if(Status.equalsIgnoreCase("Accepted"))
					  {				
						  String getStatus=AgentDepositDao.getTranStatus(TransactionNo);					  
						  if(getStatus.equalsIgnoreCase("Pending"))
						  {					
							  Double halfOftransferAmt=Amount/2;				 
							  amtOfCharege=Amount-BankCharges;						  
							  double Disbal=(Double.parseDouble(DisBalance));
							  double DistributorFinalBalance=(Double.parseDouble(DisBalance)-amtOfCharege);
							  if(BankCharges>halfOftransferAmt)
							  {
								  String messageDist="Bank Charges cannot be Greater than 50% of Total Amount.";
								  request.setAttribute("message",messageDist);
								  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								  request.setAttribute("DepositDetails",DepositDetails);
								  context.removeAttribute(userId);
								  return "DepositRequest";
							  }

							  if(BankCharges<0)
							  {
								  String messageDist="Bank Charges cannot be Less than Zero.";
								  request.setAttribute("message",messageDist);
								  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								  request.setAttribute("DepositDetails",DepositDetails);
								  context.removeAttribute(userId);
								  return "DepositRequest";
							  }
							  if(Amount >Disbal)
							  {   						 
								  String messageDistt="Insufficient Balance. Please Recharge Your Account.";
						
								  request.setAttribute("message",messageDistt);
								  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								  request.setAttribute("DepositDetails",DepositDetails);
								  context.removeAttribute(userId);
								  return "DepositRequest";						    
							  }	
					
							  String distributorTableUpdate= AgentDepositDao.updateDistributorAccount(userId,agentid,Status,amtOfCharege,TransactionNo,BankCharges,ipAddress,Amount,DistributorFinalBalance,Disbal);
				
							  if(distributorTableUpdate.equalsIgnoreCase("NotUpdate"))
							  {
								  String messageDistt="Process aborted due to Technical Failure.";
								  request.setAttribute("message",messageDistt);
								  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								  request.setAttribute("DepositDetails",DepositDetails);
								  context.removeAttribute(userId);
								  return "DepositRequest";	
							  }				 
							  /*	 String agentTableUpdate=AgentDepositDao.updateAgentAccount(userId,agentid,Status,amtOfCharege,BankCharges,Amount,TransactionNo,ipAddress);
							 if(agentTableUpdate.equalsIgnoreCase("NotUpdate"))
							 {
								 String messageDistt="<html><body><table><tr><td><font color=\"red\" size=\"2\">Transaction not updated,Please try again </td></tr></table></body></html>";
								 request.setAttribute("message",messageDistt);
								 ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								 request.setAttribute("DepositDetails",DepositDetails);
								 return "DepositRequest";	
							 } */				     
							  String messageDistt="Balance Transferred Successfully.";
							  request.setAttribute("message",messageDistt);
							  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							  request.setAttribute("DepositDetails",DepositDetails);
							  context.removeAttribute(userId);
							  return "DepositRequest"; 
						  }				 
						  else
						  {
							  String messageDist="Command Disallowed.";
							  request.setAttribute("message",messageDist);
							  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							  request.setAttribute("DepositDetails",DepositDetails);
							  context.removeAttribute(userId);
							  return "DepositRequest";
						  }
					  }			  
					  //----------------Method to update 	Agent Deposit request in case of selected status is DECLINED ---------- 
					  if(Status.equalsIgnoreCase("Declined"))
					  {				  
						  String getStatus=AgentDepositDao.getTranStatus(TransactionNo);
					  
						  if(getStatus.equalsIgnoreCase("Pending"))
						  {
							  String agentTableUpdate=AgentDepositDao.updateAgentAccount(userId,agentid,Status,amtOfCharege,BankCharges,Amount,TransactionNo,ipAddress);
							  if(agentTableUpdate.equalsIgnoreCase("NotUpdate"))
							  {
								  String messageDistt="Process Aborted due to Technical Failure.";
								  request.setAttribute("message",messageDistt);
								  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
								  request.setAttribute("DepositDetails",DepositDetails);
								  context.removeAttribute(userId);
								  return "DepositRequest";	
							  }
							  String  message="Process has been Completed Successfully.";
						  
							  request.setAttribute("message",message);
							  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							  request.setAttribute("DepositDetails",DepositDetails);
							  context.removeAttribute(userId);
							  return "DepositRequest"; 
						  }
						  else
						  {
							  String messageDist="Command Disallowed.";
							  request.setAttribute("message",messageDist);
							  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
							  request.setAttribute("DepositDetails",DepositDetails);
							  context.removeAttribute(userId);
							  return "DepositRequest";		 
						  }
					  }
					  //----------------Method to update 	Agent Deposit request in case of selected status is PENDING ---------- 
					  if(Status.equalsIgnoreCase("Pending"))
					  {
						  String  messageDist="Please Accept or Decline the Deposit Request.";
					  
						  request.setAttribute("message",messageDist);
						  ArrayList<HashMap<String,Object>> DepositDetails=AgentDepositDao.getDepsitDetails(userId);
						  request.setAttribute("DepositDetails",DepositDetails);
						  context.removeAttribute(userId);
						  return "DepositRequest";
					  }	
				  }
				  else{
					  String message = "Transaction is Under Process.";
					  request.setAttribute("message",message);
					  return "DepositRequest";
			  }	  
			  }	  
			  //--------------------------------------FOR POP UP-----------------------------------		  
			  if(param.equalsIgnoreCase("transactionPopUp"))
			  {
				  String JournalId=DistributorJournal.getTransactionJournalId();
				  HashMap<String,Object> depositRequest=AgentDepositDao.getPopData(JournalId);
				  if(depositRequest.size()!=0)
				  {
					  request.setAttribute("depositRequest", depositRequest);	
					  return "AgentDepositPopUp";
				  }
				  else
				  {	
					  String  message="Data is Not Available.";					
					  request.setAttribute("message",message);
					  return "AgentDepositPopUp";		
				  }		  
			  }	  
		  }	  
		  catch(Exception ex){
			  ex.printStackTrace();
			  request.setAttribute("message", "Process aborted due to Technical Failure.");
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
	  
	  public ServletContext getServletContext() {
		  return context;
	  }
	  
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	

	 
}
