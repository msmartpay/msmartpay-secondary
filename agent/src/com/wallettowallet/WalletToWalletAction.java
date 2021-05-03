package com.wallettowallet;

import java.util.ArrayList;
import java.util.HashMap;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.common.GenerateIdDao;
import com.opensymphony.xwork2.ActionSupport;
import com.utility.SendSmtpMail;

public class WalletToWalletAction extends ActionSupport{

	Logger logger=Logger.getLogger(WalletToWalletAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			final String  agent="AG"+agentID;
			String param=request.getParameter("param");
			if(param.equalsIgnoreCase("getpage")){
				return "getpage";
			}else if(param.equalsIgnoreCase("wbgetpage")){

				WallettoWalletDao walletDao=new WallettoWalletDao();
				ArrayList<HashMap<String,String>> depDetails=walletDao.getDepositDetail(Long.parseLong(agentID));
				if(depDetails!=null && !depDetails.isEmpty())
					request.setAttribute("depDetails", depDetails);
				
				request.setAttribute("bankList", walletDao.collectionBank(Long.parseLong(agentID)));

				return "wbgetpage";
			}else if(param.equalsIgnoreCase("WBBalanceRequest")){

				try {
					WallettoWalletDao walletDao=new WallettoWalletDao();
					final String paymentMode=request.getParameter("paymode");
					final String bankName=request.getParameter("bank");
					final String refId=request.getParameter("referenceId");
					final String amount=request.getParameter("amount");
					final String depositDate=request.getParameter("date");
					if( bankName.length()>0 && amount.length()>0 && depositDate.length()>0  && paymentMode.length()>0){
						if(Double.parseDouble(amount)<500){
							request.setAttribute("message", "Request Amount Should Be Greater Than Rs. 500");
						}else{
							final String  TransactionId=GenerateIdDao.getIdNo();
							
							String status=walletDao.insertRecordForBalanceReq(agentID, TransactionId, depositDate, amount, refId, bankName, amount, "", paymentMode);
							if(status.equalsIgnoreCase("Success")){
								request.setAttribute("message", "Balance Request Submitted Successfully.");
							
								try {

									Thread thread = new Thread(new Runnable() {
										@Override
										public void run() {

											try {
												
												String[] receipient={com.common.PropertyFile.SUPPORT_EMAIL};
												String subject="Wallet balance request from Agent :: "+agent;
												String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\">"
														+ "<table width=\"100%\" >"
														+ "<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
														"<p><tr><td>"+
														"<table width=\"100%\" border=\"1\">"+
														"<tr><td width=\"40%\">Retailer ID</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+agent+"</td></tr>"+
														"<tr><td width=\"40%\">Payment Mode</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+paymentMode+"</td></tr>"+
														"<tr><td width=\"40%\">Request Amount</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+amount+"</td></tr>"+
														"<tr><td width=\"40%\">Deposit Date</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+depositDate+"</td></tr>"+
														"<tr><td width=\"40%\">Receiving Bank Name</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+bankName+"</td></tr>"+
														"<tr><td width=\"40%\">Transaction Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+TransactionId+"</td></tr>"+
														"<tr><td width=\"40%\">Bank Reference Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+refId+"</td></tr>"+
														"</table></td></tr></p>"+
														"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'Thank You,</td></tr>"+
														"<tr><td>&nbsp;</td></tr>"+
														"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team SmartKinda\nHelpDesk No. +91-9711402774 Website : http://partner.smartkinda.com</td></tr><br>"+				   
														"</tr></td></table></BODY></html>";
								            	
												SendSmtpMail.sendSSLMessage(receipient, subject, message, "no-reply@smartkinda.com");
												
											} catch (MessagingException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}

									});
									thread.start();



								} catch (Exception e) {
									e.printStackTrace();
								}
							
							}else
								request.setAttribute("message", "Request Failure. Try Again!");


						}
						
					}else{
						request.setAttribute("message", "Invalid request!");
						return "wbgetpage";
					}
					
					ArrayList<HashMap<String,String>> depDetails=walletDao.getDepositDetail(Long.parseLong(agentID));
					if(depDetails!=null && !depDetails.isEmpty())
						request.setAttribute("depDetails", depDetails);

					return "wbgetpage";
				} catch (Exception e) {
					e.printStackTrace();
					return "wbgetpage";
				}

			}else if(param.equalsIgnoreCase("WtoWTransfer")){

				WallettoWalletDao daoObj=new WallettoWalletDao();

				String toAgentId="",amount="",remark="",serviceName="Wallet to Wallet";
				toAgentId=request.getParameter("toAgentId");
				amount=request.getParameter("amount");
				remark=request.getParameter("remark");
				String ttAgentId=toAgentId.replaceAll("AG", "").replaceAll("ag", "");

				String transactionId=daoObj.getTranId_14(agentID); 			
				String IdNo=daoObj.getTranId_20();
				String ip=request.getRemoteAddr();

				HashMap<String,String> map=daoObj.getAgentDetails(ttAgentId);
				if(map!=null && !map.isEmpty()){
					String status=daoObj.walletToWalletTransfer(IdNo, agentID, ttAgentId, transactionId, Double.parseDouble(amount), serviceName, remark, ip);
					logger.info("MoneyTransferAction.execute() : "+status);
					if(status.equalsIgnoreCase("Success"))
					{
						request.setAttribute("toAgentId",toAgentId);
						request.setAttribute("agentName",map.get("agentName"));
						request.setAttribute("amount",amount);
						request.setAttribute("transactionId",transactionId);
						request.setAttribute("message","Amount Transfered Successfully.");
						return "success";
					}else{
						request.setAttribute("message","Amount Not Transfered Successfully. Please Try Again.");
					}
				}else{
					request.setAttribute("message","Please Enter Valid Agent Id");
				}

				return "getpage";
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message","Process aborted due to technical failure.");
			return "ERROR";
		}
		return agentID;
	}
}
