/**
 Class Property :  This class (DistributorDepositRequestAction) is created to get request data of transaction details and process it accordingly.

  Created Date   : 28 dec-2011 at 10.00 AM.
  Created By     : Amit Pathak

  Updated Date   : 27 april-2012
  Update By      : Amit Pathak
  Purpose        : Remove error in cash in bank block(there was no return for if block)

 */

package com.DistributorDepositRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.common.GenerateIdFunction;
import com.common.LogWriter;
import com.disttributordetails.DistributorProfileDao;
import com.dsamount.DistributorJournalForm;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.common.SendSmtpMail;

public class DistributorDepositRequestAction extends ActionSupport implements ModelDriven<Object>, ServletRequestAware,ServletResponseAware 
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpSession ses=null;
	private  DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();
	public Object getModel(){return DistributorJournalForm;}

	Logger logger = Logger.getLogger(DistributorDepositRequestAction.class);
	LogWriter log=new LogWriter();
	DistributorProfileDao DistributorProfileDao=new DistributorProfileDao();
	@SuppressWarnings({ "unchecked", "unused" })
	public String execute() throws Exception { 
		try 
		{ 
			ses=request.getSession();
			Map session = ActionContext.getContext().getSession();
			String param=DistributorJournalForm.getParam();

			log.print("param value in DistributorDepositRequestAction is "+param, logger);
			DistributorDepositRequestDao DistributorDepositRequestDao=new DistributorDepositRequestDao();

			String userId=(String)session.get("distributorId");
			if(userId==null){
				String  message2="Your Login session has Expired. Please Login to Continue.";
				request.setAttribute("Loginmessage",message2);
				return "sessionexpired"; 
			}
			String depositModefirst=DistributorJournalForm.getDepositMode();
			double amountfirst=DistributorJournalForm.getAmountToCredit();
			String paymentDatefirst=DistributorJournalForm.getPaymentdate();
			if("SlipDownload".equalsIgnoreCase(param)) {
				return "SlipDownload";  
			}
			if(param.equalsIgnoreCase("MyDeposit"))  {
				HashMap<String,Object> UpdateProfile=DistributorProfileDao.getUpdateData(userId);            
				// HashMap<String,String> maxAmountData=DistributorProfileDao.getMaxMinLimit(userId);
				String message="";
				if(UpdateProfile == null){
					message="Data is Not Available.";
				}
				request.setAttribute("message", message);
				ArrayList<HashMap<String,String>> getDSJournalData=DistributorProfileDao.getDSJournal(userId);
				request.setAttribute("depDetails",getDSJournalData);  
				
				request.setAttribute("bankList",DistributorDepositRequestDao.collectionBank(Long.parseLong(userId))); 

				session.put("UpdateProfile",UpdateProfile);
				return "MyDeposit"; 
			}				  

			//<<<<<<<<--------------------------------To save the deposit request of distributor------------------------->>>>>>>>>>>>>>>
			if(param.equalsIgnoreCase("saveDeposit"))
			{
				Integer mdId=(Integer)session.get("mdID");
				final String  TransactionId=DistributorDepositRequestDao.getTranId();

				final String paymentMode=request.getParameter("paymode");
				final String DepositorBankName=request.getParameter("bank");
				final String BankTransactionId=request.getParameter("referenceId");
				final String amount=request.getParameter("amount");
				final String RequestDate=request.getParameter("date");

				String mailFrom=null;
				String mailTo=null;
				
				//-------------------------Generate  transaction ID-------------------------------------------		   
				String result="";

				//-------------------------------Deposit case block of Cash on Desk-----------------------------
				String subject="Balance Transfer Request";
				String DepositorName="";
				String payMode="";
				String BankName="";
				String AccountNumber="";
				String BranchName="";
				Double charges=0.0;
				Double acceptAmt=0.0;
				payMode="NEFT/RTGS";
				String DepositorAccountNumber="";
				BankName="";
				AccountNumber="";
				BranchName="";
				charges=0.0;
				acceptAmt=Double.parseDouble(amount);
				String remarks="";
				GenerateIdFunction Idobj =new GenerateIdFunction();
				String ReferenceNo=Idobj.getIdNo();

				if( DepositorBankName.length()>0 && amount.length()>0 && RequestDate.length()>0  && paymentMode.length()>0){
					if(Double.parseDouble(amount)<1000){
						request.setAttribute("message", "Request Amount Should Be Greater Than Rs. 1000");
					}else{

						result=DistributorDepositRequestDao.saveNeftDep(userId,TransactionId,acceptAmt,BankTransactionId,
								ReferenceNo,DepositorBankName,BankName,AccountNumber,BranchName,charges,acceptAmt,remarks
								,DepositorAccountNumber,mdId,DepositorName,RequestDate,paymentMode);

						if("Success".equalsIgnoreCase(result)){
							
							request.setAttribute("message", "Balance Request Submitted Successfully.");
						
							try {
								
								final String DSID=ses.getAttribute("distributorInitial")+""+ses.getAttribute("distributorId");

								Thread thread = new Thread(new Runnable() {
									@Override
									public void run() {

										try {
											
											
											String[] receipient={com.common.PropertyFile.supportMail};
											String subject="Wallet balance request from DS :: "+DSID;
											String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\">"
													+ "<table width=\"100%\" >"
													+ "<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;Team,</td></tr>"+
													"<p><tr><td>"+
													"<table width=\"100%\" border=\"1\">"+
													"<tr><td width=\"40%\">DS ID</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+DSID+"</td></tr>"+
													"<tr><td width=\"40%\">Payment Mode</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+paymentMode+"</td></tr>"+
													"<tr><td width=\"40%\">Request Amount</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+amount+"</td></tr>"+
													"<tr><td width=\"40%\">Deposit Date</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+RequestDate+"</td></tr>"+
													"<tr><td width=\"40%\">Receiving Bank Name</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+DepositorBankName+"</td></tr>"+
													"<tr><td width=\"40%\">Transaction Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+TransactionId+"</td></tr>"+
													"<tr><td width=\"40%\">Bank Reference Id</td><td width=\"6%\"><div align=\"center\"><strong>:</strong></div></td><td width=\"54%\">"+BankTransactionId+"</td></tr>"+
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
					return "MyDeposit";
				}
				
				ArrayList<HashMap<String,String>> getDSJournalData=DistributorProfileDao.getDSJournal(userId);
				request.setAttribute("depDetails",getDSJournalData);   
				return "MyDeposit";

			}
		}
		catch(Exception ex)
		{
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

}