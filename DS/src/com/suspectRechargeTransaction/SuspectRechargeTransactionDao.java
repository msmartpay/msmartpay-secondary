package com.suspectRechargeTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.LogWriter;
import com.report.agentAmountHistory.AgentTransactionHistoryDao;


public class SuspectRechargeTransactionDao  {
	
//	Session session=HibernateSession.getSessionFactory().openSession();

	
	public ArrayList<HashMap<String, String>>  getAgentId(String dsid) {
		 Logger logger = Logger.getLogger(AgentTransactionHistoryDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;
		 ArrayList<HashMap<String, String>> idArray=new ArrayList<HashMap<String, String>>();
		 
		 try {
			 session=HibernateSession.getSessionFactory().openSession();
			 Query IdQuery=session.createQuery("select ADF.agentId,ADF.agentInitial  from  AgentDetailForm ADF where  ADF.distributorId=:userId").setParameter("userId",dsid);
			 log.print("getAgentIdCollection -getIdStatus- query is "+IdQuery, logger);
			 Iterator<?> it=IdQuery.iterate();
			 while(it.hasNext())
			 {
				 HashMap<String, String> idMap=new HashMap<String, String>();
			 
				 Object[] row = (Object[]) it.next();
				 String agentid = (String)row[0];
				 String agentinitial = (String)row[1];
				 String AgentCompleteId = agentinitial+agentid;
				 idMap.put("agentCompleteId",AgentCompleteId);
			 
				 idArray.add(idMap);
			 
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
		 return idArray;
	}
	
	public ArrayList<HashMap<String, Object>> getTranRechargeStatement(String tranID) {
		Session session=null;
		  
		ArrayList<HashMap<String, Object>> getAgentRechargeStatement=new  ArrayList<HashMap<String, Object>>();
		  
		try 
		{	
			session=HibernateSession.getSessionFactory().openSession();
			Query qry = null;
			String sql=null;
			sql="select c.agent_initial+convert(varchar,c.agent_id) as AGID,d.distributor_initial+convert(varchar,d.distributor_id) as DSID," +
					"a.Transaction_Id as Transaction_Id,b.mobile_operator as mobile_operator,b.mobile_number as mobile_number,b.amount as amount," +
					"b.date_of_recharge as date_of_recharge,b.service as service,b.status as status,b.USessionID as USessionID," +
					"b.date_of_suspect as date_of_suspect,b.suspect_remerk as suspect_remerk,b.suspect_flag as suspect_flag " +
					"from dbo.agent_transaction_details a,live_recharge b,agent_details c,distributor_details d where b.user_id=c.agent_id and" +
					" a.agent_id=b.user_id and a.Transaction_No=b.tran_id and b.user_id=c.agent_id and c.distributor_id=d.distributor_id and" +
					" b.date_of_recharge>=DATEADD(DAY, DATEDIFF(day, 0, getdate()), -2)and b.date_of_recharge<=DATEADD(DAY, DATEDIFF(day, 0,getdate()), 0)" +
					" and a.Transaction_Id ='"+tranID+"'  order by b.date_of_recharge";
			 
			System.out.println(sql);
			 
			qry = session.createSQLQuery(sql);
			List<?> list =qry.list();
			Iterator<?> itr = list.iterator();
			 
			Object row[];
			HashMap<String, Object> mapinfo;
			while(itr.hasNext())
			{
				mapinfo=new HashMap<String, Object>();
				row = (Object[])itr.next();

				mapinfo.put("AgentCompleteId",row[0]);
				mapinfo.put("DistributorCompleteId",row[1]);				 
				mapinfo.put("TransactionId",row[2]);
				mapinfo.put("mobile_operator",row[3]);
				mapinfo.put("mobile_number",row[4]);
				mapinfo.put("TransactionAmount",row[5]);
				mapinfo.put("DateOfTransaction",row[6]);
				mapinfo.put("Service",row[7]);
				mapinfo.put("Status",row[8]);
				mapinfo.put("USessionID",row[9]);
				mapinfo.put("date_of_suspect",row[10]);
				mapinfo.put("suspect_remerk",row[11]);
				mapinfo.put("suspect_flag",row[12]);				
			 
				getAgentRechargeStatement.add(mapinfo);
				
			}			 
		} 		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		}
		finally {
			
			
			try {
				session.close();
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		return getAgentRechargeStatement;
	}

	
	public ArrayList<HashMap<String, Object>> getAllAgentRechargeStatement(String dsid) {
		   Session session=null;
		  
		   ArrayList<HashMap<String, Object>> getAgentRechargeStatement=new  ArrayList<HashMap<String, Object>>();
		  
		 try 
		    {	
			 session=HibernateSession.getSessionFactory().openSession();
			 Query qry = null;
			 String sql=null;
			 sql="select c.agent_initial+convert(varchar,c.agent_id) as AGID,d.distributor_initial+convert(varchar,d.distributor_id) as DSID," +
				"a.Transaction_Id as Transaction_Id,b.mobile_operator as mobile_operator,b.mobile_number as mobile_number,b.amount as amount," +
				"b.date_of_recharge as date_of_recharge,b.service as service,b.status as status,b.USessionID as USessionID," +
				"b.date_of_suspect as date_of_suspect,b.suspect_remerk as suspect_remerk,b.suspect_flag as suspect_flag from " +
				"dbo.agent_transaction_details a,live_recharge b,agent_details c,distributor_details d where b.user_id in " +
				"(select agent_id from agent_details where distributor_id in(select distributor_id from distributor_details where " +
				"distributor_initial+convert(varchar,distributor_id)='"+dsid+"')) and a.agent_id=b.user_id and a.Transaction_No=b.tran_id and" +
				" b.user_id=c.agent_id and c.distributor_id=d.distributor_id and b.date_of_recharge>=DATEADD(DAY, DATEDIFF(day, 0, getdate()), -2)" +
				"and b.date_of_recharge<=DATEADD(DAY, DATEDIFF(day, 0,getdate()), 0) order by b.date_of_recharge";
			 
			 System.out.println(sql);
			 
			 qry = session.createSQLQuery(sql);
			 List<?> list =qry.list();
			 Iterator<?> itr = list.iterator();
			 
			 Object row[];
			 HashMap<String, Object> mapinfo;
			 while(itr.hasNext())
			 {
				 mapinfo=new HashMap<String, Object>();
				 row = (Object[])itr.next();

				 mapinfo.put("AgentCompleteId",row[0]);
				 mapinfo.put("DistributorCompleteId",row[1]);				 
				 mapinfo.put("TransactionId",row[2]);
				 mapinfo.put("mobile_operator",row[3]);
				 mapinfo.put("mobile_number",row[4]);
				 mapinfo.put("TransactionAmount",row[5]);
				 mapinfo.put("DateOfTransaction",row[6]);
				 mapinfo.put("Service",row[7]);
				 mapinfo.put("Status",row[8]);
				 mapinfo.put("USessionID",row[9]);
				 mapinfo.put("date_of_suspect",row[10]);
				 mapinfo.put("suspect_remerk",row[11]);
				 mapinfo.put("suspect_flag",row[12]);				
			 
				 getAgentRechargeStatement.add(mapinfo);
				
			 }			 
			} 		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		   }
		 finally {
				
			 
			 try {
				 session.close();
			 } 
			 catch (Exception e) {
					
				 e.printStackTrace();
			 }
		 }
		return getAgentRechargeStatement;
	}

	public ArrayList<HashMap<String, Object>> getAgentRechargeStatement(String agid,String dsid){
		Session session=null;
		  
		ArrayList<HashMap<String,Object>> getAgentRechargeStatement=new  ArrayList<HashMap<String,Object>>();
		  
		try 
		{	
			session=HibernateSession.getSessionFactory().openSession();
			
			 String	sql="select c.agent_initial+convert(varchar,c.agent_id) as AGID,d.distributor_initial+convert(varchar,d.distributor_id) as DSID," +
			"a.Transaction_Id as Transaction_Id,b.mobile_operator as mobile_operator,b.mobile_number as mobile_number,b.amount as amount," +
			"b.date_of_recharge as date_of_recharge,b.service as service,b.status as status,b.USessionID as USessionID," +
			"b.date_of_suspect as date_of_suspect,b.suspect_remerk as suspect_remerk,b.suspect_flag as suspect_flag from " +
			"dbo.agent_transaction_details a,live_recharge b,agent_details c,distributor_details d where b.user_id in " +
			"(select agent_id from agent_details where distributor_id in(select distributor_id from distributor_details where " +
			"distributor_initial+convert(varchar,distributor_id)='"+dsid+"')) and a.agent_id=b.user_id and a.Transaction_No=b.tran_id and" +
			" b.user_id=c.agent_id and c.distributor_id=d.distributor_id and b.date_of_recharge>=DATEADD(DAY, DATEDIFF(day, 0, getdate()), -2)" +
			"and b.date_of_recharge<=DATEADD(DAY, DATEDIFF(day, 0,getdate()), 0) and c.agent_initial+convert(varchar,c.agent_id)='"+agid+"' " +
			" order by b.date_of_recharge";
			
			System.out.println(sql);
			 
			Query qry = session.createSQLQuery(sql);
			List<?> list =qry.list();
			Iterator<?> itr = list.iterator();
			System.out.println("we have done with query");
			Object row[];
			HashMap<String, Object> mapinfo;
			while(itr.hasNext())
			{
				mapinfo=new HashMap<String, Object>();
				row = (Object[])itr.next();

				mapinfo.put("AgentCompleteId",row[0]);
				mapinfo.put("DistributorCompleteId",row[1]);				 
				mapinfo.put("TransactionId",row[2]);
				mapinfo.put("mobile_operator",row[3]);
				mapinfo.put("mobile_number",row[4]);
				mapinfo.put("TransactionAmount",row[5]);
				mapinfo.put("DateOfTransaction",row[6]);
				mapinfo.put("Service",row[7]);
				mapinfo.put("Status",row[8]);
				mapinfo.put("USessionID",row[9]);
				mapinfo.put("date_of_suspect",row[10]);
				mapinfo.put("suspect_remerk",row[11]);
				mapinfo.put("suspect_flag",row[12]);				
			 
				getAgentRechargeStatement.add(mapinfo);
			}		 
		} 		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		}
		finally {		
			try {
				session.close();
			} 
			catch (Exception e) {					
				e.printStackTrace();
			}
		}
		return getAgentRechargeStatement;
	}
	
	
	public String updateSuspectStatus(String dsid,String transid) {
	
		String status="Notupdated";
		Session session=null;
		
		try{
			
			session=HibernateSession.getSessionFactory().openSession();
			Transaction tr=session.beginTransaction();
			
			String sql="update live_recharge set suspect_flag='1',date_of_suspect=getdate(),suspect_remerk='Reported by "+dsid+"'  where tran_id in (select Transaction_No from  agent_transaction_details where Transaction_Id='"+transid+"')";
			System.out.println(sql);
			Query query = session.createSQLQuery(sql);
			
			int result = query.executeUpdate();
			
			if(result>0){
				System.out.println("we are into this "+result);
				status="updated";				
			}
			tr.commit();
		}		
		catch(Exception e){
			session.getTransaction().rollback();
			status="Notupdated";
			System.out.println(e);
			System.out.println("updating suspect flag");
		}
		finally{
			try{
				session.close();				 
			}
			catch(Exception e){
				System.out.println("exception in class agentTranDao in mobileRechargeReport while closing connection"+e);
			}
		}  	
		System.out.println("status is "+status);
		return status;
	}
}
