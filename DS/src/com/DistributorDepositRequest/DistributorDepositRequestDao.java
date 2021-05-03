package com.DistributorDepositRequest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.PushBalance.PushBalanceDao;
import com.common.HibernateSession;
import com.common.LogWriter;
import com.dsamount.DistributorJournalForm;

public class DistributorDepositRequestDao {

	
	public ArrayList<HashMap<String, Object>> collectionBank(long dsId) {
		Session session=null;
		ArrayList<HashMap<String, Object>> bankList=new ArrayList<HashMap<String, Object>>();
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createSQLQuery("select bank_name,bank_account,bank_account_name,bnk_ifsc from wb_collection_bank_detils (nolock) a join distributor_details (nolock) b on a.client_id=b.Client_ID and b.distributor_id=:distributor_id and a.status=1").setParameter("distributor_id",dsId);
			List li=query.list();
			Iterator<?> it=li.iterator();
			while(it.hasNext())
			{

				Object[] row = (Object[]) it.next();
				HashMap<String,Object> temp=new HashMap<String,Object>(); 
				temp.put("bank_name",row[0]); 
				temp.put("bank_account",row[1]); 
				temp.put("bank_account_name",row[2]);
				temp.put("bnk_ifsc",row[3]);  
				bankList.add(temp);

			}

		} 	 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return bankList;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap getModeOfPaymentDetails(String depositMode, double amount,
			String recBankName) {


		Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap HashMode=new HashMap();
		BigDecimal chargesDouble = new BigDecimal(10);
		String chargeType="";
		double acceptedAmt=0.00;
		double charges = 0.0;
		int limitAmount=(int)amount;
		try 
		{

			String sql="Select  top 1 charges,charges_Type,Account_No,BranchName  from MD_mode_of_payment where Limit_Amount<="+limitAmount+" and mode_of_payment='"+depositMode+"' and Bank_Name='"+recBankName+"' order by Limit_Amount desc";
			log.print("distributorDistributorAmountForm is "+sql, logger);
			SQLQuery query = session.createSQLQuery(sql);
			Object [] it = (Object [])
					query.uniqueResult(); 

			chargesDouble=(BigDecimal )it[0];
			chargeType=(String)it[1];
			String AccountNumber=(String)it[2];
			String BranchName=(String)it[3];

			HashMode.put("AccountNumber",AccountNumber);
			HashMode.put("BranchName", BranchName);
			charges=chargesDouble.doubleValue();
			HashMode.put("charges",charges);


			if(chargeType.equalsIgnoreCase("R"))
			{
				acceptedAmt=amount-charges;
			}
			if(chargeType.equalsIgnoreCase("P"))
			{
				acceptedAmt=(amount * charges)/100;
			}
			HashMode.put("acceptedAmt",acceptedAmt);
		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return HashMode;


	}

	public String saveCashOnDeskDep(String userId,
			String transactionId, String depositMode, Double amountdouble,
			String recieptNumber, String requestTime, String remarks,
			Integer mdId, String depositorName,
			String recieverName, String receiverOfficeLocation,String reqDate)
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String result="";

		try
		{


			tran=session.beginTransaction();

			DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();
			DistributorJournalForm.setMdId(mdId);
			DistributorJournalForm.setDistributorId(userId);
			DistributorJournalForm.setTransactionJournalId(transactionId);
			DistributorJournalForm.setInitial("DISTJ");
			DistributorJournalForm.setModeOfPayment(depositMode);
			DistributorJournalForm.setAmountToCredit(amountdouble);
			DistributorJournalForm.setAcceptedAmount(amountdouble);
			DistributorJournalForm.setReceiptNumber(recieptNumber);
			DistributorJournalForm.setDepositDate(reqDate);
			DistributorJournalForm.setStatus("Pending");
			DistributorJournalForm.setRemarks(remarks);
			DistributorJournalForm.setDepositorName(depositorName);
			DistributorJournalForm.setReceiverName(recieverName);
			DistributorJournalForm.setReceiverOfficeLocation(receiverOfficeLocation);
			DistributorJournalForm.setReceiverOfficeLocation(receiverOfficeLocation);

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();



		} 

		catch (Exception e) 
		{
			result="unsuccess";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();

			} 
			catch (Exception e) {
				result="unsuccess";
				e.printStackTrace();
			}
		}

		return result;
	}



	public String saveChequeDep(String userId, String transactionId,
			String depositMode, Double amount, String chequeNumber,
			String chequeBankName, String chequeDDDate, String depositLocation,
			String bankName, String accountNumber, String branchName,
			Double charges, Double acceptAmt, String remarks, Integer mdId,
			String requestTime, String reqDate,String DepositorName) {

		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String result="";

		try
		{
			tran=session.beginTransaction();

			DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();
			DistributorJournalForm.setMdId(mdId);
			DistributorJournalForm.setDistributorId(userId);
			DistributorJournalForm.setTransactionJournalId(transactionId);
			DistributorJournalForm.setInitial("DISTJ");
			DistributorJournalForm.setModeOfPayment("cheque deposit");
			DistributorJournalForm.setAmountToCredit(amount);
			DistributorJournalForm.setBankCharges(charges);
			DistributorJournalForm.setAcceptedAmount(acceptAmt);
			DistributorJournalForm.setBankName(bankName);
			DistributorJournalForm.setBranchName(branchName);
			DistributorJournalForm.setAccountNumber(accountNumber);
			DistributorJournalForm.setDepositLocation(depositLocation);
			DistributorJournalForm.setDepositDate(reqDate);
			DistributorJournalForm.setChequeNumber(chequeNumber);
			DistributorJournalForm.setChequeDDDate(chequeDDDate);
			DistributorJournalForm.setChequeBankName(chequeBankName);
			DistributorJournalForm.setStatus("Pending");
			DistributorJournalForm.setRemarks(remarks);
			DistributorJournalForm.setDepositorName(DepositorName);

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();		
		} 	
		catch (Exception e) 
		{
			result="unsuccess";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();				
			} 
			catch (Exception e) {
				result="unsuccess";
				e.printStackTrace();
			}
		}

		return result;
	}

	public String saveETransferDep(String userId, String transactionId,
			Double amount, String bankTransactionId, String referenceNo,
			String depositorBankName, String bankName, String accountNumber,
			String branchName, Double charges, Double acceptAmt,
			String remarks, Integer mdId,
			String depositorName, String requestDate,String DepositorAccountNumber) {
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String result="";

		try
		{
			tran=session.beginTransaction();

			DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();

			DistributorJournalForm.setMdId(mdId);
			DistributorJournalForm.setDistributorId(userId);
			DistributorJournalForm.setTransactionJournalId(transactionId);
			DistributorJournalForm.setInitial("DISTJ");
			DistributorJournalForm.setModeOfPayment("E-Transfer");
			DistributorJournalForm.setAmountToCredit(amount);
			DistributorJournalForm.setBankCharges(charges);
			DistributorJournalForm.setAcceptedAmount(acceptAmt);
			DistributorJournalForm.setBankName(bankName);
			DistributorJournalForm.setBranchName(branchName);
			DistributorJournalForm.setAccountNumber(accountNumber);
			DistributorJournalForm.setBankTransactionId(bankTransactionId);
			DistributorJournalForm.setReferenceNo(referenceNo);
			DistributorJournalForm.setDepositorBankName(depositorBankName);
			DistributorJournalForm.setDepositDate(requestDate);
			DistributorJournalForm.setStatus("Pending");
			DistributorJournalForm.setRemarks(remarks);
			DistributorJournalForm.setDepositorAccountNumber(DepositorAccountNumber);
			DistributorJournalForm.setDepositorName(depositorName);

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();		
		} 

		catch (Exception e) 
		{
			result="unsuccess";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();					
			} 
			catch (Exception e) {
				result="unsuccess";
				e.printStackTrace();
			}
		}

		return result;
	}

	public String saveNeftDep(String userId, String transactionId,
			double amount, String bankTransactionId, String referenceNo,
			String depositorBankName, String bankName, String accountNumber,
			String branchName, Double charges, Double acceptAmt,
			String remarks, String depositorAccountNumber, Integer mdId,
			String depositorName, String requestDate,String mode) {
		Session session=null;
		Transaction tran=null;
		String result="";

		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();

			DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();

			DistributorJournalForm.setMdId(mdId);
			DistributorJournalForm.setDistributorId(userId);
			DistributorJournalForm.setTransactionJournalId(transactionId);
			DistributorJournalForm.setInitial("DISTJ");
			DistributorJournalForm.setModeOfPayment(mode);
			DistributorJournalForm.setAmountToCredit(amount);
			DistributorJournalForm.setBankCharges(charges);
			DistributorJournalForm.setAcceptedAmount(acceptAmt);
			DistributorJournalForm.setBankName(bankName);
			DistributorJournalForm.setBranchName(branchName);
			DistributorJournalForm.setAccountNumber(accountNumber);
			DistributorJournalForm.setReferenceNo(bankTransactionId);
			DistributorJournalForm.setBankTransactionId(bankTransactionId);
			DistributorJournalForm.setDepositorBankName(depositorBankName);
			DistributorJournalForm.setDepositDate(requestDate);
			DistributorJournalForm.setStatus("Pending");
			DistributorJournalForm.setRemarks(remarks);
			DistributorJournalForm.setDepositorAccountNumber(depositorAccountNumber);
			DistributorJournalForm.setDepositorName(depositorName);

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();		
		} 

		catch (Exception e) 
		{
			result="unsuccess";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();				
			} 
			catch (Exception e) {
				result="unsuccess";
				e.printStackTrace();
			}
		}

		return result;
	}


	public String saveCashInBankDep(String userId, String transactionId,
			String reqDate, String depositMode, double amount,
			String depositLocation, String bankTransactionId, String bankName,
			String accountNumber, String branchName, Double charges,
			Double acceptAmt, String requestTime, String remarks, Integer mdId,
			String depositorName) {
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String result="";

		try
		{
			tran=session.beginTransaction();

			DistributorJournalForm DistributorJournalForm=new DistributorJournalForm();

			DistributorJournalForm.setMdId(mdId);
			DistributorJournalForm.setDistributorId(userId);
			DistributorJournalForm.setTransactionJournalId(transactionId);
			DistributorJournalForm.setInitial("DISTJ");
			DistributorJournalForm.setModeOfPayment("cash deposit");
			DistributorJournalForm.setAmountToCredit(amount);
			DistributorJournalForm.setBankCharges(charges);
			DistributorJournalForm.setAcceptedAmount(acceptAmt);
			DistributorJournalForm.setBankName(bankName);
			DistributorJournalForm.setBranchName(branchName);
			DistributorJournalForm.setBranchCity(depositLocation);
			DistributorJournalForm.setAccountNumber(accountNumber);
			DistributorJournalForm.setBankTransactionId(bankTransactionId);
			DistributorJournalForm.setDepositDate(reqDate);
			DistributorJournalForm.setDepositLocation(depositLocation);
			DistributorJournalForm.setStatus("Pending");
			DistributorJournalForm.setRemarks(remarks);
			DistributorJournalForm.setDepositorName(depositorName);

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();
		} 		
		catch (Exception e) 
		{
			result="unsuccess";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();				
			} 
			catch (Exception e) {
				result="unsuccess";
				e.printStackTrace();
			}
		}

		return result;
	}



	public String getTranId() {

		Logger logger = Logger.getLogger(PushBalanceDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String tranId="";
		int numId = 0;
		int id=0;
		try 
		{
			Query IdStatusQuery=session.createQuery("select max(DJF.JournalId) from DistributorJournalForm as DJF");
			log.print("PushBalanceDao -getIdStatus- query is "+IdStatusQuery, logger);
			Iterator<?> it=IdStatusQuery.iterate();
			if(it.hasNext())
			{
				Object  row = (Object) it.next();
				numId=(Integer)row;
				id=numId+1;

			}
			tranId=((Integer)id).toString();
		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			try {
				session.flush();
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return tranId;
	}

	@SuppressWarnings("unchecked")
	public HashMap getCrPaymentDetails(String depositModefirst,
			double amountfirst, String creditdaysReq, String paymentDatefirst,
			String creditdateReq) {
		Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap HashMode=new HashMap();
		BigDecimal chargesDouble = new BigDecimal(10);
		String chargeType="";
		double acceptedAmt=0.00;
		double charges = 0.0;

		int limitAmount=(int)amountfirst;
		try 
		{
			String sql="Select  top 1 charges,charges_Type,payment_commitment_days  from MD_mode_of_payment where Limit_Amount<='"+limitAmount+"' and mode_of_payment='"+depositModefirst+"' and credit_req_days='"+creditdaysReq+"'  order by Limit_Amount desc";
			log.print("distributorDistributorAmountForm is "+sql, logger);
			SQLQuery query = session.createSQLQuery(sql);
			Object [] it = (Object [])
					query.uniqueResult(); 

			chargesDouble=(BigDecimal)it[0];
			chargeType=(String)it[1];
			charges=chargesDouble.doubleValue();
			HashMode.put("charges",charges);
			int daysrequested=(Integer)it[2];

			DateFormat formatter ;
			Calendar cal = Calendar.getInstance();
			Date date ; 
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date)formatter.parse(creditdateReq);
			cal.setTime(date);
			cal.add(Calendar.DATE,daysrequested);
			Date newdate=cal.getTime();


			String paymentDate = formatter.format(newdate);

			HashMode.put("paymentDate",paymentDate);


			if(chargeType.equalsIgnoreCase("R"))
			{
				acceptedAmt=amountfirst-charges;
			}
			if(chargeType.equalsIgnoreCase("P"))
			{
				acceptedAmt=(amountfirst * charges)/100;
			}
			HashMode.put("acceptedAmt",acceptedAmt);
		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return HashMode;
	}

	public HashMap getMDSMailId(String usrId) {
		HashMap map=new HashMap();
		Session session=null;

		try 

		{

			session=HibernateSession.getSessionFactory().openSession();
			String sql="select dd.email_id,md.Email_id from distributor_details dd join MD_Details md on md.MD_id=dd.md_id where dd.distributor_id="+Long.parseLong(usrId);

			SQLQuery query = session.createSQLQuery(sql);
			List list=query.list();
			Iterator it=list.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				map.put("MDEmail", row[0].toString());
				map.put("DSEmail", row[1].toString());
			}
			

		}	
		catch (Exception ex) 
		{
			ex.printStackTrace();
			map=new HashMap();
			return map;

		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}

		return map;
	}

}
