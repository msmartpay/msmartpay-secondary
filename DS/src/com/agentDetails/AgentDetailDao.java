package com.agentDetails;

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

public class AgentDetailDao {

	public ArrayList<HashMap<String, Object>> AllActiveAgent(String userId, String page, String clientFlag)
	{
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();

		ArrayList<HashMap<String, Object>> ActiveAgnetInfo=new ArrayList<HashMap<String, Object>>();
		int maxPage=0;
		String loginTable=null;
		Session session = HibernateSession.getSessionFactory().openSession();
		Query query =null;
		try{

			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			if(clientFlag.equalsIgnoreCase("0") || clientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}		

			String countQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"' and b.login_status='Activate'";
			Query qry=session.createSQLQuery(countQry);
			maxPage=(Integer)qry.uniqueResult();
			System.out.println("maxPage  ::"+maxPage);
			if(maxPage!=0)
			{
				maxPage=(int) Math.ceil(maxPage/100);
			}

			query=session.createSQLQuery("select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.MPIN,convert(decimal(18,2),(c.totcash-c.usedcash+c.cutoff_amount)) as amount,a.agency_name from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"' and b.login_status='Activate') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row  ");

			log.print("distributor Login query is "+query, logger);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap<String, Object>  mapInfo=new HashMap<String, Object>();
				Object[]	 row=(Object[])itr.next();
				//mapInfo.put("agentCompleteId",agentCompleteId);
				mapInfo.put("S.No.",row[0]);
				mapInfo.put("AgentId",row[1]);
				mapInfo.put("mobileNo",row[2]);
				mapInfo.put("agentEmailId",row[3]);
				mapInfo.put("status",row[4]);
				mapInfo.put("mpin",row[5]);
				mapInfo.put("amount", row[6]);
				mapInfo.put("agentName", row[7]);
				mapInfo.put("MaxPage",maxPage);		
				ActiveAgnetInfo.add(mapInfo);
			}		
		}			
		catch(Exception  ex){		    	
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
		return ActiveAgnetInfo;
	}
	public ArrayList<HashMap<String, Object>> AllDeactivateAgent(String userId, String page, String clientFlag)
	{
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();		 
		ArrayList<HashMap<String, Object>> ActiveAgnetInfo=new ArrayList<HashMap<String, Object>>();	
		int maxPage=0;
		String loginTable=null;
		Session session = HibernateSession.getSessionFactory().openSession();
		Query query =null;
		try{			
			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			if(clientFlag.equalsIgnoreCase("0") ||clientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}		

			String countQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"' and b.login_status in  ('Deactive','Deactivate')";
			Query qry=session.createSQLQuery(countQry);
			maxPage=(Integer)qry.uniqueResult();
			if(maxPage!=0)
			{
				maxPage=(int) Math.ceil(maxPage/100);
			}

			query=session.createSQLQuery("select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.MPIN,convert(decimal(18,2),(c.totcash-c.usedcash+c.cutoff_amount)) as amount,a.agency_name from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"' and b.login_status in  ('Deactive','Deactivate')) as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row  ");

			log.print("distributor Login query is "+query, logger);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap<String, Object>  mapInfo=new HashMap<String, Object>();
				Object[]	 row=(Object[])itr.next();	

				//mapInfo.put("agentCompleteId",agentCompleteId);
				mapInfo.put("S.No.",row[0]);
				mapInfo.put("AgentId",row[1]);
				mapInfo.put("mobileNo",row[2]);
				mapInfo.put("agentEmailId",row[3]);
				mapInfo.put("status",row[4]);
				mapInfo.put("mpin",row[5]);
				mapInfo.put("amount",row[6]);
				mapInfo.put("agentName", row[7]);
				mapInfo.put("MaxPage",maxPage);		

				ActiveAgnetInfo.add(mapInfo);
			}		
		}			
		catch(Exception  ex){			
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
		return ActiveAgnetInfo;
	}

	public ArrayList<HashMap<String, Object>> AllAgent(String userId, String page, String clientFlag)
	{
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();

		ArrayList<HashMap<String, Object>> ActiveAgnetInfo=new ArrayList<HashMap<String, Object>>();	
		int maxPage=0;
		String loginTable=null;
		Session session = HibernateSession.getSessionFactory().openSession();
		Query query =null;
		try{

			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			if(clientFlag.equalsIgnoreCase("0") || clientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}				
			String countQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"'";
			Query qry=session.createSQLQuery(countQry);
			maxPage=(Integer)qry.uniqueResult();
			if(maxPage!=0)
			{
				maxPage=(int) Math.ceil(maxPage/100);
			}
			String sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.MPIN,convert(decimal(18,2),(c.totcash-c.usedcash+c.cutoff_amount)) as amount,a.agency_name from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+userId+"') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row  ";
			//System.out.println(sql);
			query=session.createSQLQuery(sql);

			log.print("distributor Login query is "+query, logger);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap<String, Object>  mapInfo=new HashMap<String, Object>();
				Object[]	 row=(Object[])itr.next();	

				//mapInfo.put("agentCompleteId",agentCompleteId);
				mapInfo.put("S.No.",row[0]);
				mapInfo.put("AgentId",row[1]);
				mapInfo.put("mobileNo",row[2]);
				mapInfo.put("agentEmailId",row[3]);
				mapInfo.put("status",row[4]);
				mapInfo.put("mpin",row[5]);
				mapInfo.put("amount", row[6]);
				mapInfo.put("agentName", row[7]);
				mapInfo.put("MaxPage",maxPage);
				//System.out.println(">>> "+row[7]+" >>> "+row[8]); 

				ActiveAgnetInfo.add(mapInfo);
			}		
		}			
		catch(Exception  ex){

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
		return ActiveAgnetInfo;
	}

	public String dochangeStatusAgent(String[] changeStatusAgentAll,
			String changingStatus, String clientFlag,String userId) {

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String  changeStatusAgent="notupdate";
		String Login_Table="";
		try 
		{			
			tran=session.beginTransaction();
			for(int i=0; i<changeStatusAgentAll.length; i++)
			{
				String Agentid=changeStatusAgentAll[i];				 		
				System.out.println("Agentid :"+Agentid);			 

				if(clientFlag.equalsIgnoreCase("0")||clientFlag.equalsIgnoreCase("2"))
				{					 
					Login_Table="login_details";
				}

				else
				{
					Login_Table="recharge_e_point_login_info";
				}
				String sqlQuery =" update "+Login_Table+" set login_status='"+changingStatus+"' where user_id in (select agent_id from agent_details where distributor_id='"+userId+"' and agent_initial+convert(varchar,agent_id)='"+Agentid+"')";

				Query query = session.createSQLQuery(sqlQuery);					
				int count = query.executeUpdate();					 
				System.out.println("count is  :::"+count);
				if(count>0){				 
					changeStatusAgent="Update";
				}			  
			}
			tran.commit();	
			changeStatusAgent="Update";
		} 		 
		catch (Exception ex) {
			changeStatusAgent="notupdate";
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

	public String getAgentid(String agentCompletetId) {
		Logger logger = Logger.getLogger(AgentDetailDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String id="";		 
		try 
		{
			Query IdStatusQuery=session.createQuery("select ADF.agentId  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar,ADF.agentId)=:ReceiverID").setParameter("ReceiverID",agentCompletetId);
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

}
