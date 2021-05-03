
/**
 Class Property :  This class (AgentDepositDao) is created to processlogic for agent deposit.

  Created Date   : 23-Dec-2011 at 19:55 PM.
  Created By     : Amit Pathak

  Updated Date   : 23-Dec-2011
  Update By      :Amit Pathak

 */


package com.agentDeposit;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agentamount.AgentAmountForm;
import com.agentamount.AgentJournalForm;
import com.agentamount.AgentTransactionDetailsForm;
import com.common.GenerateIdFunction;
import com.common.HibernateSession;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.disttributordetails.DistributorTransactionDetailForm;
import com.dsamount.DistributorAmountForm;

public class AgentDepositDao
{

	public ArrayList<HashMap<String,Object>> getDepsitDetails(String userId) 
	{
		Logger logger=Logger.getLogger(AgentDepositDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>> depositRequestArrayList=new ArrayList<HashMap<String,Object>>();
		String status="pending";
		try
		{


			String sql="select a.agent_id,a.journal_id,convert(varchar(10),a.journal_date,120),a.request_time,a.mode_of_payment,a.ammount_to_credit,a. checque_no ,b.agent_initial,b.agency_name,b.mobile_no,a.Bank_Charges from agent_journal a,agent_details b where a.agent_id=b.agent_id and a.status='pending' and a.distributor_id='"+userId+"' order by a.request_date desc,a.request_time desc";		
			log.print("doupdateAllAGCutoffAmount is "+sql, logger);
			SQLQuery query = session.createSQLQuery(sql);
			List result = query.list();

			for (Iterator it = result.iterator(); it.hasNext();) 
			{

				HashMap<String,Object>  depositRequest=new HashMap<String,Object> ();
				Object[] row=(Object[])it.next();

				depositRequest.put("agentId", row[0]);
				depositRequest.put("JournalId", row[1]);
				depositRequest.put("Requestdate", row[2]);
				depositRequest.put("RequestTime", row[3]);
				depositRequest.put("ModeOfPayment", row[4]);
				depositRequest.put("AmountToCredit", row[5]);
				depositRequest.put("ChequeNumber", row[6]);
				depositRequest.put("agentInitial", row[7]);
				depositRequest.put("agencyName", row[8]);
				depositRequest.put("AgentMobileNumber", row[9]);
				depositRequest.put("Bank_Charges", row[10]);			
				depositRequestArrayList.add(depositRequest);       
			}       
		} 		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {			
			try {
				session.close();
			} 
			catch (Exception e) {				
				e.printStackTrace();
			}
		}	
		return depositRequestArrayList;
	}

	public String getDsBalance(String userId) {
		String amount="0";
		Session session = HibernateSession.getSessionFactory().openSession();

		try{
			Logger logger = Logger.getLogger(AgentDepositDao.class);
			LogWriter log=new LogWriter();

			String sql="select convert(decimal(18,4),TotCash-usedcash) as avil from distributor_amount where distributor_Id='"+userId+"'";		
			log.print("doupdateAllAGCutoffAmount is "+sql, logger);
			SQLQuery query = session.createSQLQuery(sql);

			amount=query.uniqueResult().toString();



		}
		catch(Exception ex){		
			ex.printStackTrace();
		}
		finally {
			session.close();
		}

		return amount;
	}

	public String updateDistributorAccount(String userId,String agentid,String status,double amtOfCharege,
			String transactionNo, double bankCharges, String ipAddress,
			Double transaferAmt,double DistributorFinalBalance,double DisbalPrevious) 
	{	
		Logger logger = Logger.getLogger(AgentDepositDao.class);
		LogWriter log=new LogWriter();		
		double total=0.0;
		double usedcash=0.0;
		double avilableBalance=0.0;
		double cutoffamount=0.0;
		String AgentMobile="";		 
		String amout="";

		String mdId="";
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String stringdate = dateFormat.format(date);
		String CheckUpdate="NotUpdate";
		GenerateIdFunction GenerateIdFunction=new GenerateIdFunction();
		Session session = null;
		Transaction tran=null;
		try 
		{	



			session = HibernateSession.getSessionFactory().openSession();
			tran = session.beginTransaction();
			String transaction_id=GenerateIdFunction.getIdNo();	


			Query getMd=session.createQuery("select DDF.mdId from DistributorDetailForm DDF where DDF.distributorId=:distributorId").setParameter("distributorId",userId);
			log.print("distributor Login query is "+getMd, logger);
			Iterator<?> it=getMd.iterate();
			if(it.hasNext())
			{
				Object row=(Object)it.next();
				mdId=(String)row;
			}  

			Query ClientIdQry=session.createSQLQuery("select Client_id from md_details  where md_id='"+mdId+"'");					
			String ClientId=(String)ClientIdQry.uniqueResult().toString();	 
			System.out.println(ClientIdQry);
			Query ClientFlagQry=session.createSQLQuery("select flag from dbo.white_label_details where client_id='"+ClientId+"' ");
			String ClientFlag =(String) ClientFlagQry.uniqueResult();
			System.out.println(ClientFlagQry);

			DistributorAmountForm DistributorAmountForm = (DistributorAmountForm)session.get(DistributorAmountForm.class,userId);
			if(DistributorAmountForm!=null)
			{        	
				DistributorAmountForm.setUsedCash(DistributorAmountForm.getUsedCash()+amtOfCharege);
				DistributorAmountForm.setLastAmount(amtOfCharege);        	
				session.update(DistributorAmountForm);		
			}	 
			//-----------------------Query to insert--------------
			DistributorTransactionDetailForm DistributorTransactionDetailForm = new DistributorTransactionDetailForm();
			if(DistributorTransactionDetailForm!=null)
			{        	
				DistributorTransactionDetailForm.setDistributorId(userId);
				DistributorTransactionDetailForm.setTransactionNo(transaction_id);
				DistributorTransactionDetailForm.setReferenceId(transactionNo);
				DistributorTransactionDetailForm.setMdID(mdId);
				DistributorTransactionDetailForm.setUserType("distributor");
				DistributorTransactionDetailForm.setDateOfTransaction(sqlDate);
				DistributorTransactionDetailForm.setTimeOfTransaction(sqltime);
				DistributorTransactionDetailForm.setService("transfertoagent");
				DistributorTransactionDetailForm.setBankCharge(bankCharges);
				DistributorTransactionDetailForm.setTransactionAmount(transaferAmt);
				DistributorTransactionDetailForm.setNetTransactionAmount(amtOfCharege);
				DistributorTransactionDetailForm.setActionOnBalanceAmount("Debit");
				DistributorTransactionDetailForm.setPreviousBalanceAmount(DisbalPrevious);
				DistributorTransactionDetailForm.setUpdatedBalanceAmount(DistributorFinalBalance);
				DistributorTransactionDetailForm.setFinalBalanceAmount(DistributorFinalBalance);
				DistributorTransactionDetailForm.setUpdatedDate(sqlDate);
				DistributorTransactionDetailForm.setUpdatedTime(sqltime);
				DistributorTransactionDetailForm.setUpdatedUser("distributor");
				DistributorTransactionDetailForm.setUpdatedIpAddress(ipAddress);
				DistributorTransactionDetailForm.setRemarks("AmountTransferFromDistToAgent");
				DistributorTransactionDetailForm.setTransactionStatus("Success");
				DistributorTransactionDetailForm.setIpAddress(ipAddress);
				session.save(DistributorTransactionDetailForm); 

				//-----------------distributor done -------- now agent account and details----------------------
				System.out.println(".....before agent details....");
				//GenerateIdFunction=new GenerateIdFunction();
				//System.out.println("GenerateIdFunction ::"+GenerateIdFunction);
				SendSmsOnMobile SendSmsOnMobile=new SendSmsOnMobile();

				transaction_id=GenerateIdFunction.getIdNo();
				System.out.println("transaction_id :"+transaction_id);
				Query getAgentAmountDetails=session.createQuery("select AAF.totalcash,AAF.usedcash,AAF.cutoffAmt,ADF.mobileNo,AJF.remarks  from AgentAmountForm AAF,AgentDetailForm ADF,AgentJournalForm AJF where AAF.agentId=ADF.agentId and ADF.agentId=AJF.agentId and AJF.JournalId=:JournalId").setParameter("JournalId", transactionNo);
				log.print("updateAgentAccount is "+getAgentAmountDetails, logger);
				it=getAgentAmountDetails.iterate();
				if(it.hasNext())
				{
					Object[] row=(Object[])it.next();
					total=(Double)row[0];
					usedcash=(Double)row[1];
					cutoffamount=(Double)row[2];
					AgentMobile=(String)row[3];
				}		    
				Double	AgavilableBalance=total-usedcash;

				System.out.println("AgavilableBalance  :"+AgavilableBalance);

				//double mdBlalance = Double.parseDouble(avilableBalance);
				BigDecimal BlanceAmount = new BigDecimal(AgavilableBalance);
				BigDecimal FinalBalance = BlanceAmount.setScale(4, BigDecimal.ROUND_HALF_UP);
				System.out.println("FinalBalance::"+FinalBalance);
				System.out.println("BlanceAmount::"+BlanceAmount);

				avilableBalance=FinalBalance.doubleValue();

				System.out.println("avilableBalance------------------ :"+avilableBalance);
				log.print("avilableBalance is "+avilableBalance, logger);
				System.out.println("agent id :: "+agentid);

				Double AgentCurrentBalance=avilableBalance+cutoffamount;
				System.out.println("AgentCurrentBalance ::"+AgentCurrentBalance);
				log.print("AgentCurrentBalance is "+AgentCurrentBalance, logger);			
				if(status.equalsIgnoreCase("accepted"))
				{		
					System.out.println("status is::"+status);
					double addeddAmount=avilableBalance+amtOfCharege;
					Double AgentUpdateBalance=addeddAmount+cutoffamount;				    
					//---------------------Table agent Amount updated---------------------				
					AgentAmountForm AgentAmountForm = (AgentAmountForm)session.get(AgentAmountForm.class,agentid);
					if(AgentAmountForm!=null)
					{
						double finalTotal=total+amtOfCharege;
						AgentAmountForm.setTotalcash(finalTotal);
						AgentAmountForm.setLastUpdAmt(amtOfCharege);
						session.update(AgentAmountForm); 						
					}			    
					//---------------------Table Agent Journal Update======-----------------					 
					amout = Double.toString(amtOfCharege);			
					AgentJournalForm AgentJournalForm=(AgentJournalForm)session.get(AgentJournalForm.class,transactionNo);
					AgentJournalForm.setStatus(status);
					AgentJournalForm.setApprovalDate(stringdate);
					AgentJournalForm.setAcceptedAmount(amout);
					session.update(AgentJournalForm); 


					//-----------Insert Query----------------------

					AgentTransactionDetailsForm AgentTransactionDetailsForm=new AgentTransactionDetailsForm() ;  

					AgentTransactionDetailsForm.setAgentid(agentid);
					AgentTransactionDetailsForm.setTransactionID(transaction_id);
					AgentTransactionDetailsForm.setTransactionNo(transactionNo);
					AgentTransactionDetailsForm.setUserType("distributor");
					AgentTransactionDetailsForm.setDistributorId(userId);
					AgentTransactionDetailsForm.setService("DisttoAgent");
					AgentTransactionDetailsForm.setAgentBalanceBeforeDeduction(avilableBalance);
					AgentTransactionDetailsForm.setAgentBalanceAfterDeduction(addeddAmount);
					AgentTransactionDetailsForm.setRequestedAmount(transaferAmt);
					AgentTransactionDetailsForm.setServiceCharge2(bankCharges);
					AgentTransactionDetailsForm.setDeductedAmont(amtOfCharege);
					AgentTransactionDetailsForm.setActionOnBalance("Credit");
					AgentTransactionDetailsForm.setTransactionStatus("Success");
					AgentTransactionDetailsForm.setAgentFinalBalance(addeddAmount);
					AgentTransactionDetailsForm.setIpAddress(ipAddress);
					AgentTransactionDetailsForm.setUpdatedIpAddress(ipAddress);
					AgentTransactionDetailsForm.setRemarks("Trade Balance Request");
					AgentTransactionDetailsForm.setServiceCharge1(0.0);						
					AgentTransactionDetailsForm.setDateOfTransaction(sqlDate);
					AgentTransactionDetailsForm.setTimeOfTransaction(sqltime);
					AgentTransactionDetailsForm.setUpdatedDate(sqlDate);
					AgentTransactionDetailsForm.setUpdatedTime(sqltime);
					session.save(AgentTransactionDetailsForm); 			 



					System.out.println("Flag ----------:"+ClientFlag);
					if(ClientFlag.equalsIgnoreCase("1") || ClientFlag.equalsIgnoreCase("2"))
					{					 
						double TruncatedAmount = Math.floor(AgentUpdateBalance);
						DecimalFormat df = new DecimalFormat("#");
						String AmountSend = df.format(TruncatedAmount);

						String SmsTranCode="";
						if( ClientId.equalsIgnoreCase("10003")) {
							SmsTranCode="TXN";
						}
						else{
							SmsTranCode="TXNC";
						}

					}				
					CheckUpdate="Update";			 
				}
				else
				{				
					amout = Double.toString(amtOfCharege);				
					AgentJournalForm AgentJournalForm= (AgentJournalForm)session.get(AgentJournalForm.class,transactionNo);						
					if(AgentJournalForm!=null)
					{
						AgentJournalForm.setStatus(status);
						AgentJournalForm.setApprovalDate(stringdate);
						AgentJournalForm.setAcceptedAmount(amout);
						session.update(AgentJournalForm); 

						CheckUpdate="Update";
					}	
				}
				//-------------end for agent updation--------------------
				CheckUpdate="Update";
			}
			CheckUpdate="Update"; 
			tran.commit();
		}
		catch (Exception e)
		{
			CheckUpdate="NotUpdate";
			e.printStackTrace();
			tran.rollback();
		}
		finally {			
			try {			
				session.close();			
			} 
			catch (Exception e) {
				CheckUpdate="NotUpdate";
				e.printStackTrace();
			}
		}
		return CheckUpdate;	
	}

	public String updateAgentAccount(String userId, String agentid,
			String status, double amtOfCharege, double bankCharges,
			Double transaferAmt, String transactionNo, String ipAddress) {

		Logger logger = Logger.getLogger(AgentDepositDao.class);
		LogWriter log=new LogWriter();
		Session session = HibernateSession.getSessionFactory().openSession();

		String CheckUpdate="Notupdate";
		Transaction tran=null;

		double total=0.0;
		double usedcash=0.0;
		double avilableBalance=0.0;
		double cutoffamount=0.0;

		GenerateIdFunction GenerateIdFunction=new GenerateIdFunction();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String stringdate = dateFormat.format(date);
		String amout="";
		try 
		{			
			String transaction_id=GenerateIdFunction.getIdNo();
			tran= session.beginTransaction();
			Query getAgentAmountDetails=session.createQuery("select AAF.totalcash,AAF.usedcash,AAF.cutoffAmt,ADF.mobileNo,AJF.remarks  from AgentAmountForm AAF,AgentDetailForm ADF,AgentJournalForm AJF where AAF.agentId=ADF.agentId and ADF.agentId=AJF.agentId and AAF.agentId=:agentid").setParameter("agentid", agentid);
			log.print("updateAgentAccount is "+getAgentAmountDetails, logger);
			Iterator<?> it=getAgentAmountDetails.iterate();
			if(it.hasNext())
			{
				Object[] row=(Object[])it.next();
				total=(Double)row[0];
				usedcash=(Double)row[1];
				cutoffamount=(Double)row[2];
			}

			avilableBalance=total-usedcash;
			log.print("avilableBalance is "+avilableBalance, logger);

			Double AgentCurrentBalance=Math.floor(avilableBalance+cutoffamount);
			/*	System.out.println(" AgentCurrentBalance ::"+AgentCurrentBalance);
				System.out.println("amtOfCharege   ::"+amtOfCharege);*/


			log.print("AgentCurrentBalance is "+AgentCurrentBalance, logger);	


			amout = Double.toString(amtOfCharege);	

			AgentJournalForm AgentJournalForm= (AgentJournalForm)session.get(AgentJournalForm.class,transactionNo);

			if(AgentJournalForm!=null)
			{
				AgentJournalForm.setStatus(status);
				AgentJournalForm.setApprovalDate(stringdate);
				AgentJournalForm.setAcceptedAmount(amout);
				session.update(AgentJournalForm);						
				CheckUpdate="Update";
			}


			Query ClientIdQry=session.createSQLQuery("select Client_ID from distributor_details  where distributor_id='"+userId+"'");					
			String ClientId=(String)ClientIdQry.uniqueResult().toString();	 
			System.out.println(ClientIdQry);
			Query ClientFlagQry=session.createSQLQuery("select flag from dbo.white_label_details where client_id='"+ClientId+"' ");
			String ClientFlag =(String) ClientFlagQry.uniqueResult();
			System.out.println("ClientFlag ------------::"+ClientFlag);
			tran.commit();
		}
		catch (Exception e)
		{
			CheckUpdate="NotUpdate";			
			e.printStackTrace(); 
			try
			{
				tran.rollback();
			}
			catch(Exception ex)
			{
				CheckUpdate="NotUpdate";
				ex.printStackTrace(); 
			}			 
		}
		finally {					
			try {
				session.close();


			} 
			catch (Exception e) {
				CheckUpdate="NotUpdate";
				e.printStackTrace();
			}
		}
		return CheckUpdate;
	}

	public HashMap<String,Object> getPopData(String journalId) {
		Logger logger=Logger.getLogger(AgentDepositDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object>  depositRequest=new HashMap<String,Object> ();
		try
		{
			Query depositRequestQuery=session.createQuery("select AJF.agentId,AJF.JournalId,AJF.RequestDate,AJF.ModeOfPayment,ADF.agentInitial,ADF.agencyName,ADF.mobileNo,AJF.BankName,AJF.remarks,ADF.agentName,ADF.agentEmailId,AJF.DepositerName,AJF.DepositorBankName,AJF.DepositDate,AJF.account_no,AJF.AmounToCredit,AJF.ChequeNumber,AJF.Transaction_id from AgentJournalForm AJF,AgentDetailForm ADF where AJF.agentId=ADF.agentId and AJF.JournalId=:JournalId").setParameter("JournalId",journalId) ;
			log.print("distributor Login query is "+depositRequestQuery, logger);
			Iterator<?> it=depositRequestQuery.iterate();
			while(it.hasNext())
			{			
				Object[] row=(Object[])it.next();


				depositRequest.put("agentId", row[0]);
				depositRequest.put("JournalId", row[1]);
				depositRequest.put("Requestdate", row[2]);
				depositRequest.put("ModeOfPayment", row[3]);
				depositRequest.put("agentInitial", row[4]);
				depositRequest.put("agencyName", row[5]);
				depositRequest.put("AgentMobileNumber", row[6]);
				depositRequest.put("BankName", row[7]);
				depositRequest.put("remarks", row[8]);
				depositRequest.put("AgentName", row[9]);
				depositRequest.put("EmailId", row[10]);
				depositRequest.put("DepositerName", row[11]);
				depositRequest.put("DepositorBankName", row[12]);
				depositRequest.put("DepositDate", row[13]);
				depositRequest.put("account_no", row[14]);
				depositRequest.put("AmounToCredit", row[15]);
				depositRequest.put("ChequeNumber", row[16]);
				depositRequest.put("Transaction_id", row[17]);			
			} 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			session.close();
		}	
		return depositRequest;
	}

	public void updateDepositDecline(String userId, String agentid,
			String status, double amtOfCharege, double bankCharges,
			double amount, String transactionNo, String ipAddress) {
		// TODO Auto-generated method stub

	}

	public String getTranStatus(String transactionNo) {
		Logger logger=Logger.getLogger(AgentDepositDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String status="NoUpdate";
		try
		{

			Query depositRequestQuery=session.createQuery("select  AJF.Status from  AgentJournalForm AJF where AJF.JournalId=:tranId").setParameter("tranId",transactionNo) ;
			log.print("distributor Login query  getTranStatus is "+depositRequestQuery, logger);
			Iterator<?> it=depositRequestQuery.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				status=(String)row;
			}	 
		} 	
		catch (Exception e) 
		{
			status="NoUpdate";
			e.printStackTrace();
		}
		finally {		
			try {
				session.close();
			} 				
			catch (Exception e) {
				status="NoUpdate";
				e.printStackTrace();
			}
		}
		return status;
	}
}
