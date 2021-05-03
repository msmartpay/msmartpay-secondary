package com.depositrequest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;




import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.agent.AgentDetailsBusinessDelegator;
import com.mobilesms.SmsToMobileDao;

public class DistributorDepositRequestAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			 ActionForm form,
			 HttpServletRequest request,
			 HttpServletResponse response)
throws Exception {
		
		
		HttpSession session=request.getSession(true);
		String mdId=(String)session.getAttribute("mdId");
		System.out.println("mdId is==============================="+mdId);
		
		
		if(mdId==null){
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}
	
		Connection con=null;
		String Dist_id="";
		try
		{		
			String param=(String)request.getParameter("param");
			System.out.println("param is>>>>>>>>>>>>>>>>>>>>>>>>>>"+param);			
			if("depositRequest".equals(param))
			{
				ArrayList requestlist=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
				request.setAttribute("requestlist",requestlist);
			
				
				return mapping.findForward("depositRequest");
			}			
			if("distribotorDetails".equals(param))
			{
				String distributorId=(String)request.getParameter("did");
				String tranId=(String)request.getParameter("tranId");
				String date=(String)request.getParameter("date");
				String time=(String)request.getParameter("time");
				HashMap distributordetails=DistributorDepositRequestBusinessDelegator.getDistributorDetails(distributorId,tranId,date,time);
				request.setAttribute("distributordetails",distributordetails);
			
				return mapping.findForward("distribotorDetails");
			}
			if("updatedeposit".equals(param))
			{       
				
				ServletContext context=servlet.getServletContext();
				System.out.println(context);			  
				String Tnxstatus=(String)context.getAttribute(mdId);
				if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(mdId))
				{
					context.setAttribute(mdId, mdId);			
				
					String tranId=(String)request.getParameter("tranId");				
					String balance=DistributorDepositRequestBusinessDelegator.getMDBalance(mdId,session);				
					String clientIp=(String)session.getAttribute("clientIp");			
					String bankCh="0";
					String status=(String)request.getParameter("status");				
					String bankChh=(String)request.getParameter("bankcharge"+status);				
					String bankChargeRemark=(String)request.getParameter("bankchargeremark"+status);				
					String distributor_id=(String)request.getParameter("distributorId");
					String date=(String)request.getParameter("date");
					String time=(String)request.getParameter("time");				
					String statusselected=(String)request.getParameter("statusid"+status);				
					String amount=(String)request.getParameter("amount");               
					String AgntAmt="";
					Double bankcharges=0.00; 
					if(statusselected.equalsIgnoreCase("Accepted"))
					{			
						String statuss=DistributorDepositRequestBusinessDelegator.chkTranId(con,tranId,session);					
						if(statuss.equals("Invalid"))
						{
							String message="Command Disallowed.";
							request.setAttribute("message",message);
							ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
							request.setAttribute("requestlist", al);
							context.removeAttribute(mdId);
							return mapping.findForward("depositRequest");
						}
						if(statuss.equals("valid"))
						{
							if (bankChh==null || bankChh.equals("") )
							{
								bankCh="0";
							}
							else {
								bankCh=bankChh;
							}
							bankcharges=Double.parseDouble(bankCh);						
							Double TransaferAmt=Double.parseDouble(amount);
							Double halfOftransferAmt=TransaferAmt/2;
							double amtOfCharege=TransaferAmt-bankcharges;				
							double bal=(Double.parseDouble(balance));
							double amt=(Double.parseDouble(amount));				 
							String amtMd=String.valueOf(amtOfCharege);
							if(bankcharges>halfOftransferAmt)
							{
								String messageDist="Bank Charges cannot be Greater than 50% of Total Amount.";
								request.setAttribute("message",messageDist);
								ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);							
								request.setAttribute("requestlist", al);
								context.removeAttribute(mdId);
								return mapping.findForward("depositRequest");
							}
							if(bankcharges<0)
							{
								String messageDist="Bank Charges cannot be Less than Zero.";
								request.setAttribute("message",messageDist);
								ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
								request.setAttribute("requestlist", al);
								context.removeAttribute(mdId);
								return mapping.findForward("depositRequest");
							}
							if(amtOfCharege>bal)
							{   
								//System.out.println("hello bharatveer@comparkgroup.com in balence");
								String messageDistt="Insufficient Balance. Please Recharge Your Account.";					
								request.setAttribute("message",messageDistt);
								ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
								request.setAttribute("requestlist", al);
								context.removeAttribute(mdId);
								return mapping.findForward("depositRequest");			
							} 
							System.out.println("-------------------------------------------------------");
							System.out.println(" distributor_id :"+distributor_id);
							System.out.println(" statusselected :"+statusselected);
							System.out.println(" amtMd :"+amtMd);
							System.out.println(" bankcharges :"+bankcharges);
							System.out.println(" TransaferAmt :"+TransaferAmt);
							System.out.println(" bankChargeRemark :"+bankChargeRemark);
							System.out.println(" status :"+status);
							System.out.println("-------------------------------------------------------");
							
							String distributorMobileNO=(String)DistributorDepositRequestBusinessDelegator.updateMasterDistributorAmount(con,session,mdId,distributor_id,date,time,status,statusselected,amtMd,tranId,bankcharges,clientIp,TransaferAmt,bankChargeRemark);				
							System.out.println("ds mobile no-----"+distributorMobileNO);
							//String distributorMobileNO=(String)DistributorDepositRequestBusinessDelegator.updateDistributorAmount(con,distributor_id,mdId,date,time,statusselected,amtMd,bankcharges,amount,tranId,clientIp,session,bankChargeRemark);
							String discurrentBalAmount=(String)session.getAttribute("discurrentBalAmount");
							//String testMessage="TID-"+tranId+", your Amount Request of Rs."+amount+" has been Transferred in your Area Facility Center ID "+distributor_id+" .Current BAL: Rs. "+discurrentBalAmount+"";
							String testMessage="TID-"+tranId+", your Amount Request of Rs."+amount+" has been Transferred in your has been Transferred in your Distributor ID "+distributor_id+" .Current BAL: Rs. "+discurrentBalAmount+"";
							
							String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(distributorMobileNO,testMessage);
							
							System.out.println("smsStatus-----"+smsStatus);
							System.out.println("-------------------------------------------------------");
							session.removeAttribute("discurrentBalAmount");						
							ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
							request.setAttribute("requestlist", al);
							context.removeAttribute(mdId);
							String message="Balance Transferred Successfully.";
							request.setAttribute("message", message);				
							return mapping.findForward("depositRequest");					
						}
					}
					if(statusselected.equalsIgnoreCase("Declined"))
					{				
						String distributorMobileNO=(String)DistributorDepositRequestBusinessDelegator.updateDistributorAmount(con,distributor_id,mdId,date,time,statusselected,AgntAmt,bankcharges,amount,tranId,clientIp,session,bankChargeRemark);
						String discurrentBalAmount=(String)session.getAttribute("discurrentBalAmount");
						
						
						//String testMessage="TID-"+tranId+", Your Amount Request of Rs."+amount+"has been Decline by your Master Facility Center. Current BAL: Rs. "+discurrentBalAmount+"";
						String testMessage="TID-"+tranId+", Your Amount Request of Rs."+amount+"has been Decline by your Master Distributor. Current BAL: Rs. "+discurrentBalAmount+"";

						//System.out.println("hello text Message is  "+testMessage);
					//	String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(distributorMobileNO,testMessage);
						session.removeAttribute("discurrentBalAmount");

						ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
						request.setAttribute("requestlist", al);
						context.removeAttribute(mdId);
					
						String message="Request Declined Successfully.";
						request.setAttribute("message", message);				
						return mapping.findForward("depositRequest");
					}
					if(statusselected.equalsIgnoreCase("pending"))
					{
						String messageDist="Please Select Status as Accepted and Declined.";
						request.setAttribute("message",messageDist);
						ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
						request.setAttribute("depositrequest", al);
						context.removeAttribute(mdId);
						String message="Please Select Status.";
						request.setAttribute("message", message);				
						return mapping.findForward("depositRequest");
					}
				}
				else{
					String message = "Transaction Under Process.";
					session.setAttribute("message",message);
					return mapping.findForward("depositRequest");
					
				}
			}
		}
		catch(NumberFormatException e){

			ArrayList al=DistributorDepositRequestBusinessDelegator.getDistributorDepositRequestList(mdId);
			request.setAttribute("requestlist", al);
			String message="Request Amount is not Numeric. Please Decline the Request.";
		    request.setAttribute("message",message);
            return mapping.findForward("depositRequest");		
		}
		catch(Exception e){
		System.out.println("Exception in AdminDepAgentAction"+e.toString());
		e.printStackTrace();	
	}
	
return null;
}
}

