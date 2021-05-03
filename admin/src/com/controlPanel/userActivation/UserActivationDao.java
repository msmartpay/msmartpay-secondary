package com.controlPanel.userActivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

public class UserActivationDao {
	
	public ArrayList getPortalIdList()
	{
		Session session=null;         
		ArrayList portalIdList=new ArrayList();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select df.clientId,df.companyName from DynamicDetailsFormBean df order by df.clientId desc");
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();		     
				HashMap portalIdMap=new HashMap();
				portalIdMap.put("portalId", row[0]);
				portalIdMap.put("companyName", row[1]); 
				portalIdList.add(portalIdMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getPortalIdList");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getPortalIdList");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getPortalIdList");
				System.out.println(e.toString());
			}
		}
		return portalIdList; 
	}
}
