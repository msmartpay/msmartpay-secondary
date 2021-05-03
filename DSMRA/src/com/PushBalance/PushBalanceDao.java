package com.PushBalance;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.LogWriter;

public class PushBalanceDao {

	public HashMap<String, Object> getIdStatus(String receiverID,String userId) {
		
		Logger logger = Logger.getLogger(PushBalanceDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		HashMap<String,Object> idStatus=new HashMap<String,Object>();
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query IdStatusQuery=session.createQuery("select ADF.agentId,ADF.mobileNo  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar,ADF.agentId)=:ReceiverID and ADF.distributorId=:dsid").setParameter("ReceiverID",receiverID).setParameter("dsid", userId);
			log.print("PushBalanceDao -getIdStatus- query is "+IdStatusQuery, logger);
			Iterator<?> it=IdStatusQuery.iterate();
			if(it.hasNext())
			{
				Object[] row = (Object[]) it.next();
				idStatus.put("ReceiverOnlyId",row[0]);
				idStatus.put("ReceiverMobileNo",row[1]);
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
		return idStatus;
	}
	
	public String checkAgentId(String AgID,String userId) {
		 Logger logger = Logger.getLogger(PushBalanceDao.class);
		 LogWriter log=new LogWriter();
		 Session session=HibernateSession.getSessionFactory().openSession();
		 String	 Agent_id="";
		 try 
		    {			 
			 Query BalanceStatusQuery=session.createSQLQuery("select count(agent_id) from agent_details where distributor_id='"+userId+"' and agent_initial+convert(varchar,agent_id)='"+AgID+"'");
			 log.print("PushBalanceDao -getIdStatus- query is "+BalanceStatusQuery, logger);
			 int agentId=(Integer)BalanceStatusQuery.uniqueResult();
			 System.out.println("agentId :"+agentId);
			 if (agentId<1){
				 Agent_id="ivalid";
			 }else{
				 Agent_id="valid";
			 }
					
			
		    } 		 
		 catch (Exception ex) 
		 {
			Agent_id="ivalid";
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
		 return Agent_id;
	}
	
	public HashMap getFlag(String AgID,String DSid) {
		 Logger logger = Logger.getLogger(PushBalanceDao.class);
		 LogWriter log=new LogWriter();
		 Session session=HibernateSession.getSessionFactory().openSession();
		 HashMap data=new HashMap();
		 try 
		    {			 
			
				String userType="select md_id from distributor_details where distributor_id='"+DSid+"' ";
				Query SqlQry=session.createSQLQuery(userType);
				int	md_id=Integer.parseInt(SqlQry.uniqueResult().toString());
				
				String query="select client_id from md_details where md_id='"+md_id+"' ";
				Query SqlQryis=session.createSQLQuery(query);
				int	IntclientID=Integer.parseInt(SqlQryis.uniqueResult().toString());
				String clientID=""+IntclientID;
				Query BalanceStatusQuery=session.createSQLQuery("select flag from dbo.white_label_details where client_id='"+clientID+"'");
				log.print("PushBalanceDao -getIdStatus- query is "+BalanceStatusQuery, logger);
				String flag=(String)BalanceStatusQuery.uniqueResult();				
				
				
				System.out.println("agentId :"+flag);
				data.put("clientID", clientID);
				data.put("flag", flag);
			
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
		 return data ;
	}
	
	
	
	public HashMap getAgentData(String AgID){
		Logger logger = Logger.getLogger(PushBalanceDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String, Object> hm=new HashMap<String, Object>();
		try 
		{
			String ClientType="";
			String tableName="";
			Object[] obj=null;
			Transaction ts=session.beginTransaction();
			
			
			String userType="select a.Client_Type from login_details a,agent_details b where a.user_id=b.agent_id and b.agent_initial+convert(varchar,b.agent_id)='"+AgID+"'";
			Query SqlQry=session.createSQLQuery(userType);
			ClientType=(String)SqlQry.uniqueResult();
			System.out.println("client_type:"+ClientType);

			if(ClientType.equalsIgnoreCase("REP")){
				tableName="recharge_e_point_login_info";
			}else{
				tableName="login_details";
			}
			 
			 String sql="select aa.Cutoff_Amount+TotCash-usedcash as Total_Bal_Amount,ld.login_status as loginStatus,ad.email_id as email,ad.mobile_no as mobNum,ad.agent_initial+convert(varchar,ad.agent_id) as agId,ld.flag as flag,ad.agency_name as agency_name from dbo.agent_details ad,dbo.agent_amount aa,"+tableName+" ld where ad.agent_id=aa.agent_id and aa.agent_id=ld.user_id and ad.agent_initial+convert(varchar,ad.agent_id)='"+AgID+"'";
			 System.out.println("sql ::"+sql);
			 Query query=session.createSQLQuery(sql);
			 {
				 System.out.println("inside query..for rep or tep .. ");
				 obj=(Object[])query.uniqueResult();				
				 hm.put("Total_Bal_Amount", obj[0]);
				 hm.put("loginStatus", obj[1]);
				 hm.put("email", obj[2]);
				 hm.put("mobNum", obj[3]);
				 hm.put("agId", obj[4]);
				 hm.put("flag", obj[5]);
				 hm.put("agency_name", obj[6]);
			 }	
			 ts.commit();
		    }catch (Exception e) {
				// TODO: handle exception
		    	e.printStackTrace();
		    	return null;
		    } finally {
				 try {
					 session.flush();
					 session.close();
				 } 
				 catch (Exception e) {
					 
					 e.printStackTrace();
				 }
			 }
		 
		return hm;		
	}
	
	public String getBalanceStatus(double doubleTransferAmount,String userId) {
		 Logger logger = Logger.getLogger(PushBalanceDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;;
		 String balanceStatus="";
		 double total=0.0;
		 double used=0.0;
		 double avilable=0.0;
		 try 
		    {		
			 session=HibernateSession.getSessionFactory().openSession();
			 Query BalanceStatusQuery=session.createQuery("select DAF.totalCash,DAF.usedCash  from  DistributorAmountForm DAF where DAF.distributorId=:userid").setParameter("userid",userId);
			 log.print("PushBalanceDao -getIdStatus- query is "+BalanceStatusQuery, logger);
			 Iterator<?> it=BalanceStatusQuery.iterate();
			 if(it.hasNext())
			 {
				 Object[] row = (Object[]) it.next();
				 total=(Double)row[0];
				 used=(Double)row[1];
			 }			
			 avilable=total-used;
			 if(avilable<doubleTransferAmount)
			 {
				 balanceStatus="FundInSufficent";
			 }
		    } 		 
		 catch (Exception ex) 
		 {
			 balanceStatus="FundInSufficent";
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
		 return balanceStatus;
	}

	public String doFinalTransfer(String agentIdOnly, String userId,
			double doubleTransferAmount, String paymentRemark, String ipAdress, String transactionId) {
			Session session=null;
			String balanceStatus="";
			Connection con=null;
			try
			{
				session=HibernateSession.getSessionFactory().openSession();
				session.beginTransaction();
				con=session.connection();
				CallableStatement q =con.prepareCall("{ call New_amount_transfer_from_distributor_to_agent(?,?,?,?,?,?,?) }");
				q.setString(1,userId); // first parameter, index starts with 0
				q.setString(2,agentIdOnly); // second parameter
				q.setDouble(3,doubleTransferAmount);
				q.setString(4,"CASH");
				q.setString(5,paymentRemark);
				q.setString(6,ipAdress);
				q.setString(7,transactionId);
				// q.registerOutParameter(8, java.sql.Types.LONGVARCHAR);

				ResultSet rs=q.executeQuery();
				while(rs.next()){					
					balanceStatus=(String)rs.getString(1);
				}
				session.getTransaction().commit();
			}
			catch (Exception ex)
			{
				balanceStatus="FundInSufficent";
				ex.printStackTrace();
			}
			finally {
				try {
					if(con!=null)
						con.close();
					if(session!=null)
						session.close();
					
				}
				catch (Exception e) {

					e.printStackTrace();
				}
			}
			return balanceStatus;
	}
	
	public double getAgentUpdatedAmount(String agentIdOnly) {
		Logger logger = Logger.getLogger(PushBalanceDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		double total=0.0;
		double used=0.0;
		double avilable=0.0;
		try 
		{		
			Query BalanceStatusQuery=session.createQuery("select AAF.totalcash,AAF.usedcash  from  AgentAmountForm AAF where agentId=:userid").setParameter("userid",agentIdOnly);
			log.print("PushBalanceDao -getAgentUpdatedAmount- query is "+BalanceStatusQuery, logger);
			Iterator<?> it=BalanceStatusQuery.iterate();
			if(it.hasNext())
			{
				Object[] row = (Object[]) it.next();
				total=(Double)row[0];
				used=(Double)row[1];
			}		
			avilable=total-used;		
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
		return avilable;
	}

}
