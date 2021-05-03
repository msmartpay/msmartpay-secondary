package com.TranSearch;


/**	Created By  	: 	Arshad
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

public final class TransactionSearchDao {
	public final ArrayList<HashMap<String,String>> getTranData(String userID, String connectionNo) {
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			
			sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,convert(decimal(18,2),l.amount) as amount,l.status from live_recharge l,agent_details a where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and l.user_id in (select agent_id from agent_details where distributor_id='"+userID+"') order by l.date_time desc ";
			//sql="select top(20)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.status,l.USessionID from live_recharge l,agent_details a where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and l.user_id in (select agent_id from agent_details where distributor_id='"+userID+"') order by l.date_time desc ";
			System.out.println(sql);
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;
			while(itr.hasNext()){
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("agentID", row[0].toString());
				mapdata.put("connectionNo",row[1].toString());
				mapdata.put("connectionOperator",row[2].toString() );
				mapdata.put("dateOfRecharge",row[3].toString() );
				mapdata.put("timeOfRecharge",row[4].toString() );
				mapdata.put("amount", row[5].toString());
				mapdata.put("status", row[6].toString());
				//mapdata.put("vendorTranID", (String)row[7]);
				listData.add(mapdata);
			}
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchDao method getTranData");
			e.printStackTrace();
		}
		finally{
			try{
				session.flush();
				session.close();
			}catch(Exception ex){
				System.out.println("Exception in TransactionSearchDao method getTranData closing connection");
				ex.printStackTrace();
			}
		}
		return listData;
	}

}
