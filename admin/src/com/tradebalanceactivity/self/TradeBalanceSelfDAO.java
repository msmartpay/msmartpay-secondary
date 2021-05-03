package com.tradebalanceactivity.self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.common.HibernateSession1;
import com.formBean.adminUser.UserAmountFormBean;

public class TradeBalanceSelfDAO {


	public String saveTBSelfRequest(long id,String transactionId,double amount,String ipAddress,String loginType) {

		Session session = null;
		Transaction ts = null;
		Query query=null;
		String status="";
		try {

			session = HibernateSession.getSessionFactory().openSession();
			ts = session.beginTransaction();

			String hql="exec dbo.uspSaveTBSelfRequest :userId,:amount,:loginType,:transactionId,:ipAddress";
			query=session.createSQLQuery(hql);
			query.setLong("userId",id);
			query.setDouble("amount",amount);
			query.setString("loginType",loginType);
			query.setString("transactionId",transactionId);
			query.setString("ipAddress",ipAddress);

			status=(String)query.uniqueResult();

			ts.commit();


		} catch (Exception E) {
			ts.rollback();
			return status;
		} finally {
			try {
				if (null != session)
					session.close();
			} catch (Exception e) {
			}
		}
		return status;
	}
	/** Method for return the account information of logged in user  */
	public static UserAmountFormBean myAccountInfo(String userid)
	{

		UserAmountFormBean bean=null;
		Session session=null;	
		try{
			int id=Integer.parseInt(userid);
			System.out.println(id);
			session = HibernateSession.getSessionFactory().openSession(); 
			bean=(UserAmountFormBean)session.get(UserAmountFormBean.class,id);

		}catch(Exception E){		
			System.out.println(E.toString());
			E.printStackTrace();
		}
		finally{
			try{
				session.close();
			}catch(Exception e){
				System.out.println(e);				
			}			
		}return bean;			
	}

	/** Method for receiving bankCharge according to mode of payment and amount */
	public static double getBankCharges(String paymentMode, double amount,String bankName)
	{

		String sql="";
		Double charge=0.0;
		Session session=null;	
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction ts=session.beginTransaction();
			int limit=0;
			String slimit="";
			if(paymentMode.equals("Cash on Desk"))
			{
				sql="select mop.Limit_Amount from MD_mode_of_payment mop where mop.mode_of_payment='"+paymentMode+"'" +
						"order by Limit_Amount desc";
			}
			else
			{

				sql="select mop.Limit_Amount from MD_mode_of_payment mop where mop.mode_of_payment='"+paymentMode+"'" +
						"and mop.Bank_Name='"+bankName+"' order by Limit_Amount desc";
			}
			Query query=session.createSQLQuery(sql);
			ArrayList list=(ArrayList)query.list();
			ListIterator itr=list.listIterator();
			while(itr.hasNext())
			{
				int limitAmount=Integer.parseInt((String)itr.next());
				if(limitAmount<amount)
				{
					slimit=Integer.toString(limitAmount);
					limit=Integer.parseInt(slimit);
					break;

				}

			}

			if(paymentMode.equals("Cash on Desk")){
				sql="select mop.charges from MD_mode_of_payment mop where mop.mode_of_payment='"+paymentMode+"'" +
						" and mop.Limit_Amount="+limit;
			}
			else{
				sql="select mop.charges from MD_mode_of_payment mop where mop.mode_of_payment='"+paymentMode+"'" +
						" and mop.Bank_Name='"+bankName+"' and mop.Limit_Amount="+limit;
			}

			Query sqlQuery=session.createSQLQuery(sql).addScalar("charges",Hibernate.DOUBLE);
			Object o=sqlQuery.uniqueResult();
			charge=(Double)o;
			ts.commit();
			System.out.println("Successfully persisted");

			if(charge!=null) {
				return charge; 
			} else {
				charge=0.0;
				return charge;
			}			
		}catch(Exception E){		
			System.out.println(E.toString());	
			return charge;
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)
			{
				System.out.println(e);				
			}		
		}
	}
	/** Method for saved the deposit form info in DB */
	public static String MyDepositSave(TradeBalanceFormBean TBFBean)
	{

		Session session=null;	
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction ts=session.beginTransaction();
			session.save(TBFBean);
			ts.commit();
			System.out.println("Successfully persisted");

			return "success";
		}catch(Exception E){		
			System.out.println(E.toString());	
			return "err";
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)
			{
				System.out.println(e);				
			}		
		}		
	}

	/** Method,return the pending list of Trade balance credit request  */
	public  ArrayList pendingList(long portalid,String userType,String requestType)
	{

		String sql=null;
		Session session = null;
		Query query = null;
		ArrayList pendinglist=new ArrayList();
		try{
			if(!requestType.equalsIgnoreCase("APIClient")&& requestType.equalsIgnoreCase("portalUser"))
			{
				if((userType.equalsIgnoreCase("superadmin"))&&(requestType.equalsIgnoreCase("portalUser")))
				{
					sql=" SELECT apugd.ModeofPayment,apugd.TransactionId,apugd.AmountToCredit,apugd.Bankcharges,apugd.userid,apugd.remark," +
							"apugd.Status,aud.officialNumber,apugd.RequestDate,apugd.RequestTime,apugd.RecievingBankName,apugd.RecievingBranchName," +
							"apugd.BankTranId,apugd.ChequeDDNo,apugd.Chequebankname,apugd.AcceptedAmount,wld.clientId,apugd.loginType,aud.emailId,wld.companyName,apugd.DepositDate from" +
							" LoginInfoFormBean ald,AdminUserFormBean aud,DynamicDetailsFormBean wld, TradeBalanceFormBean apugd  where " +
							"ald.userId=aud.userId and aud.portalId=wld.clientId and apugd.userid=ald.userId and apugd.Status='Pending' " +
							"and apugd.loginType in('activityAdmin','portalAdmin') order by apugd.RequestDate desc,apugd.RequestTime desc";
				}			

				if(userType.equalsIgnoreCase("activityAdmin")&& requestType.equalsIgnoreCase("portalUser"))
				{
					sql=" SELECT apugd.ModeofPayment,apugd.TransactionId,apugd.AmountToCredit,apugd.Bankcharges,apugd.userid,apugd.remark," +
							"apugd.Status,aud.officialNumber,apugd.RequestDate,apugd.RequestTime,apugd.RecievingBankName,apugd.RecievingBranchName," +
							"apugd.BankTranId,apugd.ChequeDDNo,apugd.Chequebankname,apugd.AcceptedAmount,wld.clientId,apugd.loginType,aud.emailId,wld.companyName,apugd.DepositDate from " +
							"LoginInfoFormBean ald,AdminUserFormBean aud,DynamicDetailsFormBean wld, TradeBalanceFormBean apugd where " +
							"ald.userId=aud.userId and aud.portalId=wld.clientId and apugd.userid=ald.userId and apugd.Status='Pending' " +
							"and apugd.loginType in('activityUser','portalAdmin') order by apugd.RequestDate desc,apugd.RequestTime desc";	
				}		
				if(userType.equalsIgnoreCase("portalAdmin") && requestType.equalsIgnoreCase("portalUser"))
				{
					sql="SELECT apugd.ModeofPayment,apugd.TransactionId,apugd.AmountToCredit,apugd.Bankcharges,apugd.userid,apugd.remark," +
							"apugd.Status,aud.officialNumber,apugd.RequestDate,apugd.RequestTime,apugd.RecievingBankName,apugd.RecievingBranchName," +
							"apugd.BankTranId,apugd.ChequeDDNo,apugd.Chequebankname,apugd.AcceptedAmount,wld.clientId,apugd.loginType,aud.emailId,wld.companyName,apugd.DepositDate from " +
							"LoginInfoFormBean ald,AdminUserFormBean aud,DynamicDetailsFormBean wld, TradeBalanceFormBean apugd where " +
							"ald.userId=aud.userId and aud.portalId=wld.clientId and apugd.userid=ald.userId and apugd.Status='Pending' " +
							"and apugd.loginType='portalUser' order by apugd.RequestDate desc,apugd.RequestTime desc";	
				}

			}
			else if(requestType.equalsIgnoreCase("APIClient") &&(!userType.equalsIgnoreCase("portalUser")&&!userType.equalsIgnoreCase("superadmin")))
			{
				sql="SELECT api.modeOfPayment,api.JournalId,api.amountToCredit,api.bankCharges,api.aesApiId,api.remarks," +
						"api.Status,Acd.consumerMobileNo,api.requestDate,api.requestTime,api.bankName,api.branchName,api.bankTransactionId," +
						"api.chequeDDN,api.checkBankName,api.acceptedAmount,Acd.emailId,Acd.companyName,api.depositDate from AesAPICustomerDetails Acd, AesAPICustomerJounralDetails api" +
						" where api.aesApiId=Acd.aesApiId and api.Status='Pending' order by api.requestDate desc,api.requestTime desc";			
			}
			else if(requestType.equalsIgnoreCase("MDS")&&(!userType.equalsIgnoreCase("portalUser")&&!userType.equalsIgnoreCase("portalAdmin")))
			{
				sql="SELECT MDJ.modeOfPayment,MDJ.transactionId,MDJ.amountToCredit,MDJ.bankCharges,MDJ.mdId,MDJ.remarks,MDJ.status,MDD.mobileNo," +
						"MDJ.requestDate,MDJ.requestTime,MDJ.bankName,MDJ.branchName,MDJ.bankTransactionId,MDJ.chequeNumber,MDJ.chequeBankName," +
						"MDJ.acceptedAmount,MDD.clientId,MDD.mdInitial,MDD.emailId,MDD.companyName,MDJ.depositDate from MdsDetailsFormBean MDD, MdsJournalFormBean MDJ,DynamicDetailsFormBean wld where  MDJ.mdId=MDD.mdId and " +
						"MDJ.status='Pending' and MDD.clientId=wld.clientId order by MDJ.requestDate desc,MDJ.requestTime desc";			
			}
			else if(requestType.equalsIgnoreCase("MDS")&&(!userType.equalsIgnoreCase("activityUser")&&!userType.equalsIgnoreCase("activityAdmin")))
			{
				sql="SELECT MDJ.modeOfPayment,MDJ.transactionId,MDJ.amountToCredit,MDJ.bankCharges,MDJ.mdId,MDJ.remarks,MDJ.status,MDD.mobileNo," +
						"MDJ.requestDate,MDJ.requestTime,MDJ.bankName,MDJ.branchName,MDJ.bankTransactionId,MDJ.chequeNumber,MDJ.chequeBankName," +
						"MDJ.acceptedAmount,MDD.clientId,MDD.mdInitial,MDD.emailId,MDD.companyName,MDJ.depositDate from MdsDetailsFormBean MDD, MdsJournalFormBean MDJ,DynamicDetailsFormBean wld  where  MDJ.mdId=MDD.mdId and " +
						"MDJ.status='Pending' and MDD.clientId=wld.clientId and MDD.clientId="+portalid+" order by MDJ.requestDate desc,MDJ.requestTime desc";			
			}
			else if(requestType.equalsIgnoreCase("DS") && userType.equalsIgnoreCase("activityAdmin"))
			{
				System.out.println("we are into this of DS");
				sql = "SELECT  DSJ.modeOfPayment,DSJ.journalId,DSJ.amountToCredit,DSJ.bankCharges,DSJ.distributorId,DSJ.remarks,DSJ.status,DSD.mobileNo,DSJ.requestDate," +
						"DSJ.requestTime,DSJ.bankName,DSJ.branchName,DSJ.bankTransactionId,DSJ.chequeNumber,DSJ.chequeBankName,DSJ.acceptedAmount,DSD.clientID,DSD.distributorInitial," +
						"DSD.emailId,DSD.companyName,DSJ.depositDate,DSJ.referenceNo from DistributorJournalFormBean DSJ,DistributorDetailsFormBean DSD where  DSJ.distributorId=DSD.distributorId and " +
						"DSJ.status='Pending' and DSD.distributorId in (select distributorId from DistributorDetailsFormBean) order by DSJ.requestDate desc,DSJ.requestTime desc";
				System.out.println(sql);
			} 
			else if(requestType.equalsIgnoreCase("DS") && (userType.equalsIgnoreCase("PortalAdmin") || userType.equalsIgnoreCase("PortalUser")))
			{
				System.out.println("we are into this of DS");
				sql = "SELECT  DSJ.modeOfPayment,DSJ.journalId,DSJ.amountToCredit,DSJ.bankCharges,DSJ.distributorId,DSJ.remarks,DSJ.status,DSD.mobileNo,DSJ.requestDate," +
						"DSJ.requestTime,DSJ.bankName,DSJ.branchName,DSJ.bankTransactionId,DSJ.chequeNumber,DSJ.chequeBankName,DSJ.acceptedAmount,DSD.clientID,DSD.distributorInitial," +
						"DSD.emailId,DSD.companyName,DSJ.depositDate,DSJ.referenceNo from DistributorJournalFormBean DSJ,DistributorDetailsFormBean DSD where  DSJ.distributorId=DSD.distributorId and " +
						"DSJ.status='Pending' and DSD.distributorId in (select ddb.distributorId from DistributorDetailsFormBean ddb where ddb.clientID="+String.valueOf(portalid)+" ) order by DSJ.requestDate desc,DSJ.requestTime desc";
				System.out.println(sql);
			} 
			else if(requestType.equalsIgnoreCase("Agent") && userType.equalsIgnoreCase("activityAdmin"))
			{
				System.out.println("we are into this of Agent");
				sql = "SELECT AGJ.modeOfPayment,AGJ.journalId,AGJ.amounToCredit,AGJ.bankCharges,AGJ.agentId,AGJ.remarks,AGJ.status,AGD.mobileNo,AGJ.requestDate," +
						"AGJ.requestTime,AGJ.bankName,AGJ.branchName,AGJ.bankTransactionId,AGJ.chequeNumber,AGJ.bankName,AGJ.acceptedAmount,AGD.clientID,AGD.agentInitial," +
						"AGD.emailId,AGD.companyName,AGJ.depositDate,AGJ.referenceNo from AgentDetailsFormBean AGD, AgentJournalFormBean AGJ where  AGJ.agentId=AGD.agentId and AGJ.status='Pending'" +
						"  order by AGJ.requestDate desc,AGJ.requestTime desc";
				System.out.println(sql);
			}
			else if(requestType.equalsIgnoreCase("Agent") && (userType.equalsIgnoreCase("PortalAdmin") || userType.equalsIgnoreCase("PortalUser")))
			{
				System.out.println("we are into this of Agent");
				sql = "SELECT AGJ.modeOfPayment,AGJ.journalId,AGJ.amounToCredit,AGJ.bankCharges,AGJ.agentId,AGJ.remarks,AGJ.status,AGD.mobileNo,"
						+ "AGJ.requestDate," +
						"AGJ.requestTime,AGJ.bankName,AGJ.branchName,AGJ.bankTransactionId,AGJ.chequeNumber,AGJ.bankName,AGJ.acceptedAmount,AGD.clientID,AGD.agentInitial," +
						"AGD.emailId,AGD.companyName,AGJ.depositDate,AGJ.referenceNo from AgentDetailsFormBean AGD, AgentJournalFormBean AGJ where  AGJ.agentId=AGD.agentId and AGJ.status='Pending'" +
						"and AGD.clientID="+String.valueOf(portalid)+"  order by AGJ.requestDate desc,AGJ.requestTime desc";
				System.out.println(sql);
			}
			else
			{
				System.out.println(" we are into else block ");
			}

			if(sql!=null){
				
				session = HibernateSession.getSessionFactory().openSession();

				query=session.createQuery(sql);
				System.out.println(query);
				Iterator itr=query.iterate();
				while(itr.hasNext())
				{		
					HashMap<String,Object> Data=new HashMap<String,Object>();
					Object[] row=(Object[])itr.next();					 
					Data.put("paymentMode", row[0]);
					Data.put("transactionId", row[1]);
					Data.put("amount", row[2]);
					Data.put("charges", row[3]);
					Data.put("userId", row[4]);
					Data.put("remarks", row[5]);
					Data.put("status", row[6]);
					Data.put("mobileNo", row[7]);
					/*java.sql.Timestamp date=(java.sql.Timestamp)row[8];				 
				 java.sql.Timestamp time=(java.sql.Timestamp)row[9];
				 if(date==null||time==null)
				 {
					 Data.put("date","");
					 Data.put("time", ""); 
				 }
				 else
				 {*/
					Data.put("date",row[8]);
					Data.put("time", row[9]);
					/* }*/

					Data.put("BankName", row[10]);
					Data.put("RecievingBranchName", row[11]);
					Data.put("BankTranId", row[12]);
					Data.put("ChequeDDNo", row[13]);
					Data.put("Chequebankname", row[14]);
					Data.put("AcceptedAmount", row[15]);
					if(!requestType.equalsIgnoreCase("MDS")&&!requestType.equalsIgnoreCase("APIClient") &&  !requestType.equalsIgnoreCase("DS") && !requestType.equalsIgnoreCase("Agent"))
					{
						Data.put("portalId", row[16]);
						Data.put("userType", row[17]);
						Data.put("emailId", row[18]);
						Data.put("companyName", row[19]);
						Data.put("depositDate", row[20]);
						Data.put("initial", "");

					} 
					else if(requestType.equalsIgnoreCase("MDS"))
					{
						Data.put("portalId", row[16]);
						Data.put("initial", row[17]);
						Data.put("emailId", row[18]);
						Data.put("companyName", row[19]);
						Data.put("depositDate", row[20]);
						Data.put("userType","MDS");
					} else if(requestType.equalsIgnoreCase("APIClient"))
					{
						Data.put("initial", "API");
						Data.put("emailId", row[16]);
						Data.put("companyName", row[17]);
						Data.put("depositDate", row[18]);
						Data.put("userType","APIClient");
						Data.put("portalId", "0");
					}
					else if(requestType.equalsIgnoreCase("DS"))
					{
						Data.put("portalId", row[16]);
						Data.put("initial", row[17]);
						Data.put("emailId", row[18]);
						Data.put("companyName", row[19]);
						Data.put("depositDate", row[20]);
						Data.put("referenceNo", row[21]);
						Data.put("userType","DS");
					}else if(requestType.equalsIgnoreCase("Agent"))
					{
						Data.put("portalId", row[16]);
						Data.put("userType", row[17]);
						Data.put("emailId", row[18]);
						Data.put("companyName", row[19]);
						Data.put("depositDate", row[20]);
						Data.put("referenceNo", row[21]);
						Data.put("initial", "");

					} 
					pendinglist.add(Data);
				}

			}
			System.out.println("TradeBalanceSelfDAO.pendingList() : "+pendinglist);
			return pendinglist;
		}
		catch(Exception E)
		{		
			//System.out.println("Exception in TradeBalanceSelfDAO");
			E.printStackTrace();
			return pendinglist;
		}
		finally
		{
			try{
				session.close();			
			}catch(Exception e)
			{
				e.printStackTrace();			
			}		
		}		
	}		

	/**DAO method for return user info for trade balance push..*/
	public static HashMap<String, Object> viewRequestingUser(String type, String userId, String loginType, long portalId) {


		Session session = null;
		Query query = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {

			session = HibernateSession.getSessionFactory().openSession();
			String hql="exec dbo.uspViewRequestingUser :type,:userId,:portalId,:loginType";
			query=session.createSQLQuery(hql);
			query.setString("type",type);
			query.setString("userId",userId);
			query.setLong("portalId",portalId);
			query.setString("loginType",loginType);

			Object[] obj = (Object[])query.uniqueResult();


			if("Agent".equalsIgnoreCase(type)){

				map.put("userId", obj[0]);
				map.put("portalId", obj[1]);
				map.put("name", obj[2]);
				map.put("company", obj[3]);
				map.put("mobileNo", obj[4]);
				map.put("status", obj[5]);
				map.put("balance", obj[6]);
				map.put("userType", "Agent");
				map.put("clientType", "");

			}else if ("DS".equalsIgnoreCase(type)){

				map.put("userId", obj[0]);
				map.put("portalId", obj[1]);
				map.put("name", obj[2]);
				map.put("company", obj[3]);
				map.put("mobileNo", obj[4]);
				map.put("status", obj[5]);
				map.put("balance", obj[6]);
				map.put("userType", type);

			}else if ("portalUser".equalsIgnoreCase(type) || "portalAdmin".equalsIgnoreCase(type) 
					|| "activityUser".equalsIgnoreCase(type) || "activityAdmin".equalsIgnoreCase(type)
					|| "MDS".equalsIgnoreCase(type)){

				map.put("balance", obj[0]);
				map.put("status", obj[1]);
				map.put("company", obj[2]);
				map.put("userId", obj[3]);
				map.put("portalId", obj[4]);
				map.put("name", obj[5]);
				map.put("mobileNo", obj[6]);
				map.put("userType", type);
			}else if("APIClient".equalsIgnoreCase(type)){
				map.put("balance", obj[0]);
				map.put("status", obj[1]);
				map.put("company", obj[2]);
				map.put("userId", obj[3]);
				map.put("name", obj[4]);
				map.put("mobileNo", obj[5]);
				map.put("userType", type);

			}


			return map;
		} catch (Exception E) {
			E.printStackTrace();
			return map;
		} finally {
			try {
				if (null != session)
					session.close();
			} catch (Exception e) {
			}
		}		
	}
	/** DAO method for New Trade Balance Push */
	public String AdminPushBalance(long receiverid, long portalId, long loginId, double amount, String name,String intremark, String extremark, 
			String type,String loginType,String txnNumber,String usrtnxid,String tranIp ) {

		String result="Failure";
		Query query=null;
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();

			String hql="exec dbo.uspAdminPushBalance :receiverId,:amount,:type,:loginId,:portalId,:txnNumber,:usrtnxid,:tranIp,:loginType,:intremark,:extremark";
			query=session.createSQLQuery(hql);
			query.setLong("receiverId",receiverid);
			query.setDouble("amount",amount);
			query.setString("type",type);
			query.setLong("loginId",loginId);
			query.setLong("portalId",portalId);
			query.setString("txnNumber",txnNumber);
			query.setString("usrtnxid",usrtnxid);
			query.setString("tranIp",tranIp);
			query.setString("loginType",loginType);
			query.setString("intremark",intremark);
			query.setString("extremark",extremark);

			result=(String) query.uniqueResult();

			txn.commit();

		} catch (Exception e) {
			// TODO: handle exception
			txn.rollback();
			e.printStackTrace();
		}finally{

			if(session!=null)
				session.close();

		}

		return result;

	}

	public ArrayList listOfBankCharges(String mode,String bankName)
	{
		ArrayList chargesList=new ArrayList();
		Query query=null;
		Session session = null;
		String sql="";
		try{

			session = HibernateSession.getSessionFactory().openSession(); 
			if(mode.equalsIgnoreCase("Cash on Desk"))
			{
				sql="select mod.S_No,mod.Limit_Amount,mod.charges,mod.charges_Type from MD_mode_of_payment mod where mode_of_payment='"+mode+"'";
			}
			else
			{
				sql="select mod.S_No,mod.Limit_Amount,mod.charges,mod.charges_Type,mod.BranchName,mod.Account_No from MD_mode_of_payment mod where mode_of_payment='"+mode+"' And mod.Bank_Name='"+bankName+"'";
			}
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				Object[] obj=(Object[])itr.next();
				HashMap map=new HashMap();
				map.put("sno", obj[0]);
				map.put("limit", obj[1]);
				map.put("charge", obj[2]);
				map.put("chargeType", obj[3]);
				if(!mode.equalsIgnoreCase("Cash on Desk"))
				{
					map.put("BranchName", obj[4]);
					map.put("AccountNo", obj[5]);				
				}				
				chargesList.add(map);
			}
			return chargesList;

		}catch(Exception E){		
			System.out.println(E.toString());
			return chargesList;

		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println(e);				
			}			
		}	

	}


	public String updateBankCharges(ArrayList list)
	{		
		Session session = null;
		String sql="";
		Query query=null;
		Transaction ts=null;
		HashMap map=null;
		try{

			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				map=(HashMap)itr.next();
				sql="update MD_mode_of_payment set Limit_Amount="+map.get("limit")+",charges="+map.get("charge")+",charges_Type='"+map.get("chargeType")+"' where S_No="+map.get("sno");
				query=session.createSQLQuery(sql);
				query.executeUpdate();		

			}
			ts.commit();

			return "success";
		}catch(Exception E){		
			System.out.println(E.toString());
			ts.rollback();
			return "err";

		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println(e);				
			}			
		}	

	}

	public String getStatus(String TnxId,String userType)
	{
		String status="",sql="";
		Session session = null;
		Query query=null;
		try{

			session = HibernateSession.getSessionFactory().openSession(); 			
			if(userType.equalsIgnoreCase("APIClient"))
			{
				sql="select Status from aadhaarshilaapi.dbo.AES_API_CUSTOMER_JOUNRAL_DETAILS " +
						"where Journal_Id='"+TnxId+"'";
			}
			if(userType.equalsIgnoreCase("AG"))
			{
				sql="select status from agent_journal where journal_id='"+TnxId+"'";
			}
			if(userType.equalsIgnoreCase("MDS"))
			{
				sql="select Status from MD_Journal_Details where Transaction_Id='"+TnxId+"'";
			}
			if(userType.equalsIgnoreCase("DS"))
			{
				sql="select status from distributor_journal where journal_id='"+TnxId+"'";
			}
			if(!userType.equalsIgnoreCase("APIClient")&&!userType.equalsIgnoreCase("MDS")&&!userType.equalsIgnoreCase("DS") && !userType.equalsIgnoreCase("AG"))
			{
				sql="select Status from admin_portal_user_Journal_Details where Transaction_Id='"+TnxId+"'";
			}

			query=session.createSQLQuery(sql);
			status=(String)query.uniqueResult();

			return status;

		}catch(Exception E){		
			System.out.println(E.toString());			
			return status;

		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println(e);				
			}			
		}	

	}

	public String uspUpdateWBRequest(long receiverid,double amount,long loginId,double serviceCharge,String type,
			String txnNumber,String usrtnxid,String tranIp,String bankName,String paymentMode,String loginType,
			String journalReqId,String updateStatus,String adminRemark,String remark){
		String result="Failure";
		Query query=null;
		Session session = null;
		Transaction txn = null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			String hql="exec dbo.uspUpdateWBRequest :receiverid,:amount,:type,:loginId,:txnNumber,:tranIp,:bankName,:paymentMode,:loginType,:serviceCharge,:journalReqId,:updateStatus,:adminRemark,:remark";
			query=session.createSQLQuery(hql);
			query.setLong("receiverid",receiverid);
			query.setDouble("amount",amount);
			query.setString("type",type);
			query.setLong("loginId",loginId);
			query.setString("txnNumber",txnNumber);
			query.setString("journalReqId",journalReqId);
			query.setString("tranIp",tranIp);
			query.setString("bankName",bankName);
			query.setString("paymentMode",paymentMode);
			query.setString("loginType",loginType);
			query.setDouble("serviceCharge",serviceCharge);
			query.setString("updateStatus",updateStatus);
			query.setString("adminRemark",adminRemark);
			query.setString("remark",remark);

			result=(String) query.uniqueResult();

			txn.commit();

		} catch (Exception e) {
			// TODO: handle exception
			txn.rollback();
			e.printStackTrace();
		}finally{

			if(session!=null)
				session.close();

		}

		return result;

	}
}
