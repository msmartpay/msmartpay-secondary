package com.mdamountrequest;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.common.SendMailAttachmentCashReciept;
import com.depositrequest.DistributorDepositRequestBusinessDelegator;

public class MdAmountRequestAction extends Action {

	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {


		HttpSession session=request.getSession(true);
		String mdId=(String)session.getAttribute("mdId");
		String completeId=(String)session.getAttribute("completeId");
		String balanceDeskId=(String)session.getAttribute("Help_balance_desk");
		//System.out.println("balanceDeskId is==============================="+balanceDeskId);
		//System.out.println("mdId is==============================="+mdId);
		//System.out.println("completeId is==============================="+completeId);
		if(mdId==null){
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}
		String Dist_id="";
		try
		{

			String param=(String)request.getParameter("param");
			System.out.println("param is>>>>>>>>>>>>>>>>>>>>>>>>>>"+param);

			if("amountRequest".equals(param))
			{
				MdAmountRequestBusinessDelegator mdAmountRequestBusinessDelegator = new MdAmountRequestBusinessDelegator();
				
				HashMap hm=new HashMap();
				hm=mdAmountRequestBusinessDelegator.getMdBalance();
				ArrayList getMdJournal=DistributorDepositRequestBusinessDelegator.getMdJournal(mdId);
				ArrayList<HashMap<String,String>> collectionBanks=DistributorDepositRequestBusinessDelegator.collectionBank(Long.parseLong(mdId));
				request.setAttribute("collectionBanks",collectionBanks);
				request.setAttribute("getMdJournal",getMdJournal);
				session.setAttribute("hm", hm);

				return mapping.findForward("amountRequest");
			}

			if(param.equalsIgnoreCase("saveDeposit"))
			{			
				MdAmountRequestBusinessDelegator mdAmountRequestBusinessDelegator = new MdAmountRequestBusinessDelegator();
				HashMap hm=new HashMap();
				hm=mdAmountRequestBusinessDelegator.getMdBalance();
				ArrayList<HashMap<String,String>> collectionBanks=DistributorDepositRequestBusinessDelegator.collectionBank(Long.parseLong(mdId));
				request.setAttribute("collectionBanks",collectionBanks);
				session.setAttribute("hm", hm);

				String payMode=request.getParameter("payMode");
				String amount=request.getParameter("amount");
				String reqDate=request.getParameter("cashDate");
				String neftremarks=request.getParameter("cdremark");
				String NeftRecBankName=request.getParameter("cbRecBankName");
				String TransactionId="";
				StringBuffer prefix=new StringBuffer("");;
				String result="";

				Date now = new Date();
				long logntranId = now.getTime();
				System.out.println("logntranId :"+logntranId );
				int length = mdId.length();
				if(length==5)
				{
					prefix.append("10"+mdId);
					System.out.println("prefix length<=4 :"+prefix );
				}
				String suffix = new String();

				suffix=String.valueOf(logntranId);
				System.out.println("suffix length>=6 :"+suffix );

				TransactionId=prefix.toString()+suffix;
				String NeftDepAccNo="";
				String NeftTransferTid="";
				String NeftRefNo="";
				String NeftDepBank="";
				String NeftDepDate="";
				String NeftDepTime="";
				String accNo="";
				String NeftRecBrName="";

				Double charges=0.0;
				Double acceptAmt=Double.parseDouble(amount);
				
				System.out.println("TransactionId :"+TransactionId );
				result=MdAmountRequestBusinessDelegator.saveNeftDep(mdId,TransactionId,reqDate,payMode,amount,NeftTransferTid,NeftRefNo,NeftDepBank,NeftDepDate,NeftDepTime,NeftRecBankName,accNo,NeftRecBrName,charges,acceptAmt,neftremarks,NeftDepAccNo);

				if(result.equalsIgnoreCase("success"))
				{
					if(payMode.equalsIgnoreCase("Cash in Bank")){ 
						System.out.println("inside normal block=====");
						result="<html><body><table><tr><td style=\"font-family: arial,Trebuchet MS; font-size: 12pt;color:red;\">Please send Scanned Copy of Bank Receipt to Finance Desk and/or Your Area Facility Center By Email.</td></tr></table></body></html>";
					}else{
						result="<html><body><table><tr><td style=\"font-family: Trebuchet MS; font-size: 12pt;color:red;\">Your Deposit Request has been Submitted Successfully.</td></tr></table></body></html>";
					}
				}

				if(result.equalsIgnoreCase("unsuccess"))
				{
					result="<html><body><table><tr><td style=\"font-family: Trebuchet MS; font-size: 12pt;color:red;\">Process aborted due to Technical Failure.</td></tr></table></body></html>";
				}

				hm=mdAmountRequestBusinessDelegator.getMdBalance();
				ArrayList getMdJournal=DistributorDepositRequestBusinessDelegator.getMdJournal(mdId);
				request.setAttribute("collectionBanks",collectionBanks);
				request.setAttribute("getMdJournal",getMdJournal);
				session.setAttribute("hm", hm);
				
				return mapping.findForward("amountRequest");

			}			

		}catch(Exception e){
			System.out.println("Exception in AdminDepAgentAction"+e.toString());
			e.printStackTrace();

		}

		return null;
	}
}

