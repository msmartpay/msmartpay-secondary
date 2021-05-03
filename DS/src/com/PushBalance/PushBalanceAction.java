
/*
 Class Property :  This class (PushBalanceAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 3 Dec-2012 at 10.00 AM.
  Created By     : Monika nethani

  Updated Date   : 3 Dec-2012
  Update By      :Monika Nethani



 */

package com.PushBalance;

import java.text.DecimalFormat;
import java.util.Date;
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
import com.common.SendSmsOnMobile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@SuppressWarnings("serial")
public class PushBalanceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware 
{
	private HttpServletRequest request;
	private ServletContext context = null;
	Logger logger = Logger.getLogger(PushBalanceAction.class);
	LogWriter log=new LogWriter();
	String userId=null;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			String param=(String)request.getParameter("param");
			log.print("param value in PushBalanceAction is "+param, logger);
			String message="";
			String FinalTransafer=null;
			userId=(String)session.get("distributorId");
			if(userId==null){
				message="Your Login session has Expired. Please Login to Continue.";				
				request.setAttribute("Loginmessage",message);
				return "sessionexpired"; 
			}
			PushBalanceDao PushBalanceDao = new PushBalanceDao();
			if(param.equals("pushBalancePage"))
			{
				return "pushBalance";
			}
			if(param.equalsIgnoreCase("getAgentInfo")){				
				String AgID=(String)request.getParameter("id");
				System.out.println("AgID ::"+AgID);
				session.put("AgID", AgID);
				if(AgID.contains("AG") || AgID.contains("ag") || AgID.contains("Ag")){
					System.out.println("PushBalanceAction.execute() Valid");
				}else{
					AgID="AG"+AgID;
				}
				String checkAg=PushBalanceDao.checkAgentId(AgID,userId);				
				if(checkAg.equalsIgnoreCase("ivalid")){
					message="Please Enter Correct Agent ID.";
					request.setAttribute("message",message);
					return "pushBalance";
				}else{
					HashMap agData=new HashMap();
					agData = PushBalanceDao.getAgentData(AgID);				
					if(agData==null){
						message="Please Enter Correct Agent ID.";
						request.setAttribute("message",message);
						return "pushBalance";
					}else{
						session.put("agData",agData);			
						return "refresPage";
					}
				}


			}
			if(param.equalsIgnoreCase("pushBalance"))			  
			{		
				if(userId==null){
					message="Your Login session has Expired. Please Login to Continue.";				
					request.setAttribute("Loginmessage",message);
					return "sessionexpired"; 
				}
				String IpAdress=(String)request.getParameter("IpAdress");
				String ReceiverID=(String)session.get("AgID");
				System.out.println("ReceiverID::: in pushbalance block"+ReceiverID);
				String TransferAmount=(String)request.getParameter("TransaferAmount");
				System.out.println("TransferAmount :"+TransferAmount);
				String PaymentRemark=(String)request.getParameter("PaymentRemark");				
				System.out.println("PaymentRemark is :::::::::::::::::::::::::::::::::::::::"+PaymentRemark);
				double DoubleTransferAmount=Double.parseDouble(TransferAmount);
				DecimalFormat dff = new DecimalFormat("##.#### ");				
				//------------------------Method to check weather user exist in system or not-----
				HashMap<String,Object> IdStatus=PushBalanceDao.getIdStatus(ReceiverID,userId);				  
				String agentIdOnly="";
				String agentmobileNmber="";				
				if(IdStatus.size()==0)
				{
					message="Please Enter Correct Agent ID.";
					request.setAttribute("message",message); 
					// return "pushBalancefromAction";
					return "refresPage";
				}
				else
				{
					agentIdOnly=(String)IdStatus.get("ReceiverOnlyId");
					String		agentmobileNmberIs=(String)IdStatus.get("ReceiverMobileNo");
					agentmobileNmber=agentmobileNmberIs.trim();					
				}
				//------------------Method to compare the available balance with transfer amount of distributor-----

				String BalanceStatus = PushBalanceDao.getBalanceStatus(DoubleTransferAmount,ReceiverID,userId);
				if("FundInSufficent".equalsIgnoreCase(BalanceStatus))
				{
					message="Insufficient Balance. Please Recharge Your Account.";
					request.setAttribute("message",message); 
					//return "pushBalancefromAction";
					return "refresPage";
				}				  
				//---------------------------Logic to generate Transaction ID----------------------------------
				String  TransactionId="";
				String prefix="";				
				Date now = new Date();
				long logntranId = now.getTime();
				int length = agentIdOnly.length();				
				if(length<=4)
				{
					prefix="100"+agentIdOnly;
				}
				if(length>=6)
				{
					prefix="1"+agentIdOnly;
				}
				String suffix = new String();
				suffix=String.valueOf(logntranId);
				TransactionId=prefix+suffix;				  
				//----------------Final Method  to transfer amount to Agent\DistributorID-------------------------
				String agentUpdatedAmount2 = "";
				String Tnxstatus=(String)context.getAttribute(userId);
				System.out.println("TransactionId   :::::::::"+TransactionId);
				if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(userId))
				{
					context.setAttribute(userId, userId);					
					FinalTransafer=PushBalanceDao.doFinalTransfer(agentIdOnly,userId,DoubleTransferAmount,PaymentRemark,IpAdress,TransactionId);					
					//---------------Method to get Update balance of agent-----------------------------------					
					double agentUpdatedAmount=PushBalanceDao.getAgentUpdatedAmount(agentIdOnly);
					agentUpdatedAmount2 = dff.format(agentUpdatedAmount);
					if(FinalTransafer.equalsIgnoreCase("valid"))
					{
						double msgAmount = Double.parseDouble(agentUpdatedAmount2);
						double smsAmount = Math.floor(msgAmount);
						DecimalFormat df = new DecimalFormat("#");
						System.out.println("TransferAmount :"+TransferAmount);
						//String smsTranAmount=df.format(TransferAmount);
						String smsAmountSend = df.format(smsAmount);						
						System.out.println("in side-------------------------------valid");						 
						//		String testmessage="Dear Agent Rs. "+TransferAmount+" has been credited to your "+ReceiverID+" account. Your account balance is Rs."+smsAmountSend+"";

						HashMap<?, ?> ClientData=(HashMap<?, ?>) PushBalanceDao.getFlag(ReceiverID,userId);
						String ClientId=(String) ClientData.get("clientID");
						String Flag=(String)ClientData.get("flag");

						System.out.println("Flag ::::::::::::::::::: PUSH is : "+Flag);
						if(Flag.equalsIgnoreCase("1")||Flag.equalsIgnoreCase("2")){ 				
							String testmessage="Rs. "+TransferAmount+" has been credited to your ID: "+ReceiverID+" (Bal: Rs. "+smsAmountSend+").";
							SendSmsOnMobile.sendMobileSmsSMSZONE(agentmobileNmber,testmessage);
						}                        
						message="Rs. "+TransferAmount+" Has Been Successfully Transferred to "+ReceiverID+" & Updated Balance For This User is Rs."+smsAmountSend+"";

						request.setAttribute("message",message);
						//return "pushBalancefromAction" ;
						context.removeAttribute(userId);
						return "refresPage";
					}
					else{
						if(FinalTransafer.equalsIgnoreCase("not valid")){
							message="Duplicate Push Transfer is not Allowed within 5 Minutes. Please Try Again.";
							request.setAttribute("message",message);
							context.removeAttribute(userId);
							// return "pushBalancefromAction";
							return "refresPage";	
						}
						else if(FinalTransafer.equalsIgnoreCase("insufficient balance")){
							message="Insufficient Balance.";
							request.setAttribute("message",message);
							context.removeAttribute(userId);
							// return "pushBalancefromAction";
							return "refresPage";	
						}
						else{
							message="Process aborted due to Technical Failure.";
							request.setAttribute("message",message);
							context.removeAttribute(userId);
							// return "pushBalancefromAction";
							return "refresPage";
						}
					}
				}					  
				else{

					message="Transaction is Under Process.";
					request.setAttribute("message",message);
					context.removeAttribute(userId);
					// return "pushBalancefromAction";
					return "refresPage";
				}			
			} 
		}	  
		catch(Exception ex){
			ex.printStackTrace();
			context.removeAttribute(userId);
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
	public void setServletContext(ServletContext context) {
		this.context = context;
	}	 
}
