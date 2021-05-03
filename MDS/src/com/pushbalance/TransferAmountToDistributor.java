package com.pushbalance;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mobilesms.SmsToMobileDao;

@SuppressWarnings("serial")
public class TransferAmountToDistributor extends Action   {	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {		
		HttpSession session=request.getSession(true);		
		Connection con = null;
		String mdId=(String)session.getAttribute("mdId");
		ServletContext context=null;
		if(mdId==null){			
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}	
		try
		{		
			String param=(String)request.getParameter("param");
			System.out.println("param is=============="+param);
			if(param.equals("transferRequest")){
				session.setAttribute("message","");
				return mapping.findForward("pushBalance");	
			}
			if(param.equals("pushBalance")){				
				String distId=(String)request.getParameter("distId");
				session.setAttribute("completeDistId",distId);
				String id_status=TransferAmountToDistributorBusinessDelegator.checkDistIdStatus(con,mdId,distId,session);
				if(id_status.equalsIgnoreCase("invalid")){
					String message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Please Enter Correct AFC ID.</td></tr></table></body></html>";
					session.setAttribute("message",message);
					return mapping.findForward("pushBalance");	
				}
				else{
					HashMap status=TransferAmountToDistributorBusinessDelegator.getDistributorDetails(distId,mdId);
					session.setAttribute("distInfo",status);
					return mapping.findForward("balanceTransfer");
				}
			}			
			if(param.equals("balanceTransfer")){
				session.setAttribute("message","");
				return mapping.findForward("balanceTransfer");
			}		
			if(param.equals("updateBalance")){
				String ip_address=(String)session.getAttribute("ip_address");
				String distId=(String)session.getAttribute("completeDistId");
				String compdistId=distId;
				String amount=(String)request.getParameter("amount");
				String mode=(String)request.getParameter("mode");
				String remark=(String)request.getParameter("remark");
				double amt=Double.parseDouble(amount);
				// ------- last update on 27/8/2012 // addition of servlet context ----//
				context=servlet.getServletContext();				
				String message="";

				String Tnxstatus=(String)context.getAttribute(mdId);
				if(Tnxstatus==null||!Tnxstatus.equalsIgnoreCase(mdId))
				{
					context.setAttribute(mdId, mdId);					
					String id_status=TransferAmountToDistributorBusinessDelegator.checkDistIdStatus(con,mdId,distId,session);
					String distributorId=(String)session.getAttribute("distributorId");					
					if(id_status.equalsIgnoreCase("invalid")){
						message="Please Enter Correct AFC ID.";
						session.setAttribute("message",message);
						context.removeAttribute(mdId);
						return mapping.findForward("balanceTransfer");		 
					}
					String amt_status=TransferAmountToDistributorBusinessDelegator.getAmountStatus(con,amt,mdId);					
					if(amt_status.equalsIgnoreCase("invalid")){
						message="Insufficient Balance. Please Recharge Your Account.";						
						session.setAttribute("message",message);
						context.removeAttribute(mdId);
						return mapping.findForward("balanceTransfer");
					}
					String final_status=TransferAmountToDistributorBusinessDelegator.transferAmount(con,mdId,distributorId,amt,remark,mode,ip_address,session);
					if(final_status.equalsIgnoreCase("valid")){						
						String distributorMob =(String)session.getAttribute("distributorMob");
						String discurrentBalAmount=(String)session.getAttribute("discurrentBalAmount"); 
						System.out.println("ds mob-----"+distributorMob);
						double msgAmount = Double.parseDouble(discurrentBalAmount);
						double smsAmount = Math.floor(msgAmount);
						DecimalFormat df = new DecimalFormat("#");
						String smsTranAmount=df.format(amt);
						String smsAmountSend = df.format(smsAmount);

						//	String testMessage="Amount of Rs."+smsTranAmount+" has been Transferred in Your AFC ID "+compdistId+" by your MFC. Current Balance: Rs."+smsAmountSend+"";
						String testMessage="Amount of Rs."+amt+" has been transferred in your Distributor ID "+compdistId+" by your Master Distributor.Current BAL: Rs. "+discurrentBalAmount+"";
						String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(distributorMob,testMessage);
						System.out.println("----send sms status----"+smsStatus);
						session.removeAttribute("discurrentBalAmount");
						session.removeAttribute("distributorMob");					
						message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Amount Transferred Successfully to AFC "+distId+"</td></tr></table></body></html>";
						session.setAttribute("message",message);
						context.removeAttribute(mdId);
						return mapping.findForward("balanceTransfer");
					}
					else{
						if(final_status.equalsIgnoreCase("not valid")){
							message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Duplicate Push Transfer is not Allowed within 5 Minutes. Please Try Again.</td></tr></table></body></html>";
							session.setAttribute("message",message);
							context.removeAttribute(mdId);
							return mapping.findForward("balanceTransfer");
						}
						else if(final_status.equalsIgnoreCase("insufficient balance")){
							message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Insufficient Balance.</td></tr></table></body></html>";
							session.setAttribute("message",message);
							context.removeAttribute(mdId);
							return mapping.findForward("balanceTransfer");
						}
						else{

							message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Process Aborted due to Technical Failure.</td></tr></table></body></html>";
							session.setAttribute("message",message);
							context.removeAttribute(mdId);
							return mapping.findForward("balanceTransfer");
						}
					}
				}
				else{
					message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Transaction is Under Process.</td></tr></table></body></html>";
					session.setAttribute("message",message);
					context.removeAttribute(mdId);
					return mapping.findForward("balanceTransfer");
				}
			}
		}
		catch(NumberFormatException e){
			String message="<html><body><table><tr><td><font color=\"red\" size=\"3\">Invalid Amount Request.</td></tr></table></body></html>";
			session.setAttribute("message",message);
			context.removeAttribute(mdId);
			return mapping.findForward("balanceTransfer");	
		}
		catch(Exception e){
			System.out.println("Exception in TransferAmountToDistributor"+e.toString());
			context.removeAttribute(mdId);
			e.printStackTrace();			
		}	
		return null;
	}
}

