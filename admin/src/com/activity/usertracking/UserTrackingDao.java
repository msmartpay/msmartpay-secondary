package com.activity.usertracking;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

public class UserTrackingDao 
{
	public List<UserTracking> getTrackingDetails(String userType,Long userId,String fromDate,String toDate)
	{
		Session session=null;
		Query qry=null;
		List<UserTracking> list=null;
		try
		{	
			session = HibernateSession.getSessionFactory().openSession();
			String sql="select a from UserTracking a where a.type='"+userType+"' and a.userId="+userId +" and a.dateTime between '"+fromDate+"' and '"+toDate+"' order by a.id desc";
			System.out.println("SQL"+sql);
			qry=session.createQuery(sql);
			list=qry.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(session!=null)
				{
					session.flush();
					session.close();
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
