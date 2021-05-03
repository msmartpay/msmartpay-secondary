package com.activity.adminTask.tradeBalanceLimitSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

/**
 *Created Date=18/May/2012,
 * Created BY=Vibhor Kumar , 
 * Updated Date="" ,Updated By=""
 */

public class TradeBalanceLimitSetupDAO 
{
	public ArrayList limitSetup()
	 {
		Session session=null;
		Query query=null;
		String sql="";
		Object[] obj=null;
		ArrayList limitList=new ArrayList();
		try	{
			session = HibernateSession.getSessionFactory().openSession(); 
			sql="select Id,User_Type,Trade_Balance_Limit from trade_Balance_Limit_Setup";
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap  map=new HashMap();
				obj=(Object[])itr.next();
				map.put("id",obj[0]);
				map.put("UserType",obj[1]);
				map.put("limit",obj[2]);
				limitList.add(map);
			}
			return limitList;				
		}catch(Exception E){
			System.out.println("Exception in TradeBalanceLimitSetupDAO,limitSetup");
			System.out.println(E.toString());
			return limitList;
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println(e);	
				System.out.println("Exception in TradeBalanceLimitSetupDAO,limitSetup");
			}			
		}
	 }
	 
	public String updateLimitSetup(double limit,String userType,int id)
	{
		Session session=null;
		Query query=null;
		String sql="";		
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction ts=session.beginTransaction();
				
			sql="update trade_Balance_Limit_Setup set Trade_Balance_Limit="+limit+",Updated_user='"+id+"',Updated_Date=GETDATE() where User_Type='"+userType+"'";
			query=session.createSQLQuery(sql) ;
			query.executeUpdate();
			ts.commit();
			return "success";			
		}catch(Exception E){	
			System.out.println("Exception in TradeBalanceLimitSetupDAO,updateLimitSetup");
			System.out.println(E.toString());
			return "err";
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in TradeBalanceLimitSetupDAO,updateLimitSetup");
				System.out.println(e);				
			}			
		}
	}
}	
