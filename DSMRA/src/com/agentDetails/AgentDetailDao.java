package com.agentDetails;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import net.sf.json.JSONObject;

import com.common.HibernateSession;
import com.common.LogWriter;

public class AgentDetailDao {

	public JSONArray getAgentDetails(String userId,String status)
	{
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();

		JSONArray array=new JSONArray();
		Session session = HibernateSession.getSessionFactory().openSession();
		Query query =null;
		try{
			
			if("Activate".equals(status) || "Deactive".equals(status))
				query=session.createSQLQuery("select convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id) as agentcomid,a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,(c.totcash-c.usedcash+c.cutoff_amount) as amount,a.agency_name from agent_details a ,login_Details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and d.distributor_id="+Long.parseLong(userId)+" and b.login_status='"+status+"'");
			else
				query=session.createSQLQuery("select convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id) as agentcomid,a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,(c.totcash-c.usedcash+c.cutoff_amount) as amount,a.agency_name from agent_details a ,login_Details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and d.distributor_id="+Long.parseLong(userId));
			log.print("distributor Login query is "+query, logger);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				JSONObject  mapInfo=new JSONObject();
				Object[]	 row=(Object[])itr.next();
				mapInfo.put("AgentId",row[0]);
				mapInfo.put("mobileNo",row[1]);
				mapInfo.put("agentEmailId",row[2]);
				mapInfo.put("status",row[3]);
				mapInfo.put("amount", row[4]);
				mapInfo.put("agencyName", row[5]);
				array.put(mapInfo);
				// log.print("distributor ActiveAgnetInfo "+ActiveAgnetInfo, logger);
			}		
		}			
		catch(Exception  ex){		    	
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
		return array;
	}

	public String dochangeStatusAgent(String agentFullId,
			String changingStatus,String userId) {
		Session session = null;
		Transaction tran=null;
		String  changeStatusAgent="notupdated";
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();

			String sqlQuery ="update login_details set login_status='"+changingStatus+"' where user_id in (select agent_id from agent_details where distributor_id="+Long.parseLong(userId)+" and aid='"+agentFullId+"')";

			System.out.println(sqlQuery);

			Query query = session.createSQLQuery(sqlQuery);					
			int count = query.executeUpdate();					 
			System.out.println("count is  :::"+count);
			if(count>0){				 
				changeStatusAgent="Update";
			}			  
			tran.commit();	
		} 		 
		catch (Exception ex) {
			changeStatusAgent="notupdated";
			ex.printStackTrace();
			tran.rollback();
		}
		finally {
			try {
				session.close();
			} 
			catch (Exception e) {				 
				e.printStackTrace();
			}
		}
		return changeStatusAgent;
	}

	public JSONObject getSingleAgentDetails(String agentCompletetId,String dsId) {
		JSONObject  mapInfo=null;
		Session session=null;;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query IdStatusQuery=
					session.createSQLQuery("select agentdetai0_.agent_id as col_0_0_, agentdetai0_.agency_name as col_1_0_, agentdetai0_.agent_name as col_2_0_, agentdetai0_.mobile_no as col_3_0_, agentdetai0_.email_id as col_4_0_, agentdetai0_.distributor_id as col_5_0_, agentdetai0_.DateOfBirth as col_6_0_, agentdetai0_.Gender as col_7_0_, agentdetai0_.Pan_no as col_8_0_, agentdetai0_.address1 as col_9_0_, agentdetai0_.address2 as col_10_0_,(amt.TotCash-amt.usedcash+amt.cutoff_amount) as amount,agentdetai0_.Company_Type,agentdetai0_.state,agentdetai0_.district,agentdetai0_.city,agentdetai0_.pin_code from agent_details agentdetai0_ ,agent_Amount amt where agentdetai0_.agent_initial+convert(Varchar(15), agentdetai0_.agent_id)=:ReceiverID and agentdetai0_.distributor_id=:dsId and agentdetai0_.agent_id=amt.agent_id").setParameter("ReceiverID",agentCompletetId).setParameter("dsId", Long.parseLong(dsId));

			List list=IdStatusQuery.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				mapInfo=new JSONObject();
				Object[] row=(Object[])itr.next();
				mapInfo.put("AgentId",agentCompletetId);
				mapInfo.put("agencyName",row[1]);
				mapInfo.put("agentName",row[2]);
				mapInfo.put("mobileNo",row[3]);
				mapInfo.put("emailId",row[4]);
				mapInfo.put("dsId", row[5]+"");
				mapInfo.put("dob", row[6]);
				mapInfo.put("gender", row[7]);
				mapInfo.put("panNo", row[8]);
				mapInfo.put("address", row[9]);
				mapInfo.put("address2", row[10]);
				mapInfo.put("amount", row[11]);
				
				mapInfo.put("firmType", row[12]);
				mapInfo.put("state", row[13]);
				mapInfo.put("district", row[14]);
				mapInfo.put("city", row[15]);
				mapInfo.put("pinCode", row[16]);
			}			 
		} 		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		return mapInfo;
	}

	public String getAgentid(String agentCompletetId) {
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String id="";		 
		try 
		{
			Query IdStatusQuery=session.createQuery("select ADF.agentId  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar(15),ADF.agentId)=:ReceiverID").setParameter("ReceiverID",agentCompletetId);
			log.print("getAgentid- query is "+IdStatusQuery, logger);
			Iterator<?> it=IdStatusQuery.iterate();
			if(it.hasNext())
			{
				Object row = (Object) it.next();
				id=(String)row;				
			}			 
		} 		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		return id;
	}

	public boolean updateAgent(String agentFullId,String dateofbirth,String companyType,String companyName,String panNo,String officeAddress1
		,String officeState,String officeDistrict,String officeCity,String officePincode,String userId) {
		Session session=null;
		Transaction txn=null;
		Connection con=null;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			con=session.connection();
			String sql="update agent_details set company_Type='"+companyType+"',agency_name='"+companyName+"',pan_no='"+panNo+"',address1='"+officeAddress1+"',state='"+officeState+"',district='"+officeDistrict+"',city='"+officeCity+"',pin_code='"+officePincode+"',DateOfBirth='"+dateofbirth+"' where agent_initial+convert(varchar(10),agent_id)='"+agentFullId+"' and distributor_id="+Long.parseLong(userId);
			System.out.println(sql);
			int count =con.createStatement().executeUpdate(sql);
			txn.commit();
			if(count>0)
				return true;
			else 
				return false;

		} 		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return false;
		}
		finally {
			try {
				session.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
