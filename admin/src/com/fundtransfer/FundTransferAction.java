package com.fundtransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.common.GenerateIdFunction;
import com.common.SendSmsOnMobile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*		Created By 		 : Manoj 
 * 		Created Date	 : 23/11/2012
 * 		Created Matter	 : this class use for the transfer of Air to tep and tep to air transfer fund
 */
public class FundTransferAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware{
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private ServletContext context = null;
	private Map session;
	public String execute()throws Exception{
		
		Map session=ActionContext.getContext().getSession();
		FundTransferDao funddaoObj=new FundTransferDao();
		SendSmsOnMobile smszone=new SendSmsOnMobile();
		try{
			
			String userId=(String)session.get("userId");
			if(userId==null){

				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			String loginType=(String)session.get("loginType");
			String param=(String)request.getParameter("param");
			String status1="";
			System.out.println("we are in FundTransferAction param is======================"+param);
    			
			if(param.equals("FundTransferRequest")){
    				
				ArrayList fundData=funddaoObj.getFundTransferDetail();
				if(fundData.size()<=0){
					request.setAttribute("message", "Fund transfer request is not found in Data Base");
					return "FundTransferRequest";
				}
				request.setAttribute("fundData", fundData);
    	        return "FundTransferRequest";
    				
			}
			if(param.equals("updatefundtansfer")){
    				
				int sendbal=0;
				int reqbal=0;
				String status=(String)request.getParameter("status");
				String service=(String)request.getParameter("service1"+status);
				String ip_address=(String)request.getParameter("ip_address"+status);
				
				String agent_id=(String)request.getParameter("agentid"+status);
				
				String reqamount=(String)request.getParameter("amount"+status);
				
				String statusid=(String)request.getParameter("statusid"+status);
				
				String transaction_no=(String)request.getParameter("transaction_no"+status);
				
				String date_of_transfer=(String)request.getParameter("date_of_transfer"+status);
				
				String updated_date=(String)request.getParameter("updated_date"+status);
				
				GenerateIdFunction idobj=new GenerateIdFunction();
				String IdNo=idobj.getIdNo();
				
				String Agid=funddaoObj.getAgentID(agent_id);
				
				String refreshFunction=funddaoObj.getTranStatus(transaction_no,Agid);
				System.out.println("referesh function is "+refreshFunction);					
				
				String Tnxattribute=transaction_no;
			    String Tnxstatus=(String)context.getAttribute(Tnxattribute);		  
			    if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(Tnxattribute))
			    {
			    	 context.setAttribute(Tnxattribute, Tnxattribute);
		             context.setAttribute("loginUserId",userId);	
			    }
				if(refreshFunction.equalsIgnoreCase("success") || refreshFunction.equalsIgnoreCase("failure"))
				{
					
					ArrayList fundData=funddaoObj.getFundTransferDetail();
					if(fundData.size()<=0){
						request.setAttribute("message", "Fund transfer request is not found in Data Base");
					}
    					status1="Refresh functionality is not allowed";
    					request.setAttribute("status",status1);
    					request.setAttribute("fundData",fundData);
    					return "FundTransferRequest";
				}
				if(service.equalsIgnoreCase("air"))
				{
					System.out.println("-----------we are in Air to tep-------------");
					if(statusid.equalsIgnoreCase("success")){
						System.out.println("---------we are in Air to tep Success-----------");
						String prev_amount=(String)request.getParameter("prevamount"+status);
						
						double prevamt=Double.parseDouble(prev_amount);
						double req_amount=Double.parseDouble(reqamount);
						Double bal_amt=prevamt-req_amount;
						String final_amt=bal_amt.toString();

						status1=funddaoObj.updateFundTransferDetail(IdNo,Agid,statusid,transaction_no,prev_amount,reqamount,final_amt,date_of_transfer,updated_date,ip_address,context,userId,loginType);
						if(status1.equalsIgnoreCase("Transaction has been successfully updated"))
						{
							HashMap info=funddaoObj.getDetailsToSend(Agid);
							String mobileNo=(String)info.get("mobileNo");
							String currBalance=(String)info.get("balance");
							sendbal=(int) Math.floor(Float.parseFloat(currBalance));
							reqbal=(int) Math.floor(Float.parseFloat(reqamount));
							String testmessage="TID-"+transaction_no+", your amount of Rs."+reqbal+" has been successfully transferred from Air to TEP .Current BAL: Rs. "+sendbal+" ";
							testmessage=testmessage.replaceAll(" ", "%20"); 
							testmessage= testmessage.replaceAll("&", "and");
							smszone.sendMobileSmsSMSZONE(mobileNo,testmessage); 
						}
    				    	
					}
					if(statusid.equalsIgnoreCase("failure")){
						System.out.println("----------we are failure Air to tep-------------------- ");
						status1=funddaoObj.updateFundTransferDetailStatus(Agid,transaction_no);
						if(status1.equalsIgnoreCase("Transaction has been successfully updated"))
						{
							HashMap<String,String> info=funddaoObj.getDetailsToSend(Agid);
							String mobileNo=(String)info.get("mobileNo");
							String currBalance=(String)info.get("balance");
							sendbal=(int) Math.floor(Float.parseFloat(currBalance));
							reqbal=(int) Math.floor(Float.parseFloat(reqamount));
							String testmessage="TID-"+transaction_no+", your request for fund transfer of amount of Rs."+reqbal+"  from Air to TEP has been declined .Current BAL: Rs. "+sendbal+" ";
							testmessage=testmessage.replaceAll(" ", "%20"); 
							testmessage= testmessage.replaceAll("&", "and");
							smszone.sendMobileSmsSMSZONE(mobileNo,testmessage); 
						}
					}
				}
				else
				{
					if(statusid.equalsIgnoreCase("success")){
						System.out.println("-------------we are in tep to air success-------------------");
						double req_amount=Double.parseDouble(reqamount);
						
						String IdNo2=idobj.getIdNo();
						
						status1=funddaoObj.updateFundTransferTepToAir(Agid,transaction_no,ip_address,context,userId,loginType,req_amount,IdNo2);
						if(status1.equalsIgnoreCase("Transaction has been successfully updated"))
						{
							HashMap info=funddaoObj.getDetailsToSend(Agid);
							String mobileNo=(String)info.get("mobileNo");
							String currBalance=(String)info.get("balance");
							sendbal=(int) Math.floor(Float.parseFloat(currBalance));
							reqbal=(int) Math.floor(Float.parseFloat(reqamount));
							String testmessage="TID-"+transaction_no+", your amount of Rs."+reqbal+" has been successfully transferred from TEP to Air .Current BAL: Rs. "+sendbal+" ";
							testmessage=testmessage.replaceAll(" ", "%20"); 
							testmessage= testmessage.replaceAll("&", "and");
							smszone.sendMobileSmsSMSZONE(mobileNo,testmessage); 
							
						}
					}
					if(statusid.equalsIgnoreCase("failure")){
						System.out.println("---------------we are in tep to air failure--------------");
						String prev_amount=(String)request.getParameter("prevamount"+status);
						double prevamt=Double.parseDouble(prev_amount);
						double req_amount=Double.parseDouble(reqamount);
						Double bal_amt=prevamt-req_amount;
						String final_amt=bal_amt.toString();
						String tno=funddaoObj.getTransection_id(Agid);
						status1=funddaoObj.updateFundTransferTepToAirFailure(IdNo,Agid,transaction_no,tno,prev_amount,reqamount,final_amt,date_of_transfer,updated_date,ip_address,context,userId,loginType);
						if(status1.equalsIgnoreCase("Transaction has been successfully updated"))
						{
							System.out.println("Transaction has been successfully updated block");
							HashMap info=funddaoObj.getDetailsToSend(Agid);
							String mobileNo=(String)info.get("mobileNo");
							String currBalance=(String)info.get("balance");
							sendbal=(int) Math.floor(Float.parseFloat(currBalance));
							reqbal=(int) Math.floor(Float.parseFloat(reqamount));
							String testmessage="TID-"+transaction_no+", your request of amount of Rs."+reqbal+"  from TEP to Air has been declined .Current BAL: Rs. "+sendbal+" ";
							testmessage=testmessage.replaceAll(" ", "%20"); 
							testmessage= testmessage.replaceAll("&", "and");
							smszone.sendMobileSmsSMSZONE(mobileNo,testmessage); 
						}
					}
				}
				context.removeAttribute(Tnxattribute);
				context.removeAttribute("loginUserId");	
				ArrayList fundData=funddaoObj.getFundTransferDetail(); 
				if(fundData.size()<=0){
					request.setAttribute("message", "Fund transfer request is not found in Data Base");
				}
				request.setAttribute("fundData",fundData);
				request.setAttribute("status",status1);
				return "FundTransferRequest";
    				
			}
		}catch (Exception e) {
			System.out.println("Exception in FundTransferAction class ");
			e.printStackTrace();
		}
		return null;
	}
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
		 
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
			
	}
	public void setSession(Map session){
		session = this.getSession();
	}

	public Map getSession(){
		return session;
	}
	public ServletContext getServletContext() {
		return context;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
