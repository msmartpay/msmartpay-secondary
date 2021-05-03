package com.reports.ccModule.transactionStatus;

/**	Created By  	: 	Manoj
 * 	Created Date 	:	14/01/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

class TransactionSearchDao 
{
	final ArrayList<HashMap<String,String>> getTranData(String userID, String connectionNo, String loginType, String portalId)
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try
		{
			if(loginType.equalsIgnoreCase("superadmin")|| loginType.equalsIgnoreCase("activityadmin")|| loginType.equalsIgnoreCase("activityuser"))
			{
				sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,str(l.amount,5,2),l.status,l.USessionID,l.tran_id,l.ApiProvider from live_recharge l,agent_details a where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' order by l.date_time desc";
				//sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID,m.md_initial+convert(varchar(10),d.md_id) as MDID,m.client_id,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.status,l.USessionID from live_recharge l,agent_details a,distributor_details d,md_details m where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and a.distributor_id=d.distributor_id and d.md_id=m.md_id order by l.date_time desc";
			}else
			{
				sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,str(l.amount,5,2),l.status,l.USessionID,l.tran_id,l.ApiProvider from live_recharge l,agent_details a where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and l.user_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where client_id in (select portal_id from admin_user_details where user_id='"+userID+"')))) order by l.date_time desc";
				//sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID,m.md_initial+convert(varchar(10),d.md_id) as MDID,m.client_id,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.status,l.USessionID from live_recharge l,agent_details a,distributor_details d,md_details m where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and a.distributor_id=d.distributor_id and d.md_id=m.md_id and l.user_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where client_id in (select portal_id from admin_user_details where user_id='"+portalId+"'))))";
			}
			
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;
			
			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("agentID", row[0].toString());
				mapdata.put("connectionNo",row[1].toString());
				mapdata.put("connectionOperator",row[2].toString() );
				mapdata.put("dateOfRecharge",row[3].toString() );
				mapdata.put("timeOfRecharge",row[4].toString() );
				mapdata.put("amount", row[5].toString());
				mapdata.put("status", row[6].toString());
				mapdata.put("USessionID", row[7]==null?"NA":row[7]+"");
				mapdata.put("TranId", row[8]==null?"NA":row[8]+"");
				mapdata.put("ApiProvider", row[9]==null?"NA":row[9]+"");
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in TransactionSearchDao method getTranData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in TransactionSearchDao method getTranData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}
}
