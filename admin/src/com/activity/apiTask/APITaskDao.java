package com.activity.apiTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class APITaskDao 
{
	public String addClient(String clientName,String dbName,String serverIp)
	{
		String key="fail";
		Session session=null;
		Transaction tx=null;
		Query qry=null;
		Statement stmt=null;
		try
		{	
			session = HibernateSession1.getSessionFactory().openSession();
			tx=session.beginTransaction();
			String sql="INSERT INTO Client_Details values('"+clientName+"','"+dbName+"','"+serverIp+"',getdate())";
			System.out.println("SQL"+sql);
			qry=session.createSQLQuery(sql);
			qry.executeUpdate();
			tx.commit();
			key="success";
		}
		catch(Exception e)
		{
			key="err";
			tx.commit();
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
		return key;
	}
	public ArrayList getDistinctClientName()
	{
		ArrayList clientList=new ArrayList();
		String key="fail";
		Session session=null;
		Query qry=null;
		Statement stmt=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String sql="select distinct(Client_Name) from Client_Details";
			qry=session.createSQLQuery(sql);
			List list=qry.list();
			Iterator it=list.iterator();
			while(it.hasNext())
			{
				Object obj=(Object)it.next();
				clientList.add(obj);
			}
			
		}
		catch(Exception e)
		{
			key="err";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(session!=null)
					session.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return clientList;
	}
	public HashMap getClientDBName(String clientName)
	{
		HashMap map=new HashMap();
		String key="fail";
		Session session=null;
		Query qry=null;
		Statement stmt=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String sql="select DB_Name,Server_IP from Client_Details where Client_Name='"+clientName+"'";
			System.out.println("SQL : "+sql);
			qry=session.createSQLQuery(sql);
			List list=qry.list();
			Iterator it=list.iterator();
			while(it.hasNext())
			{
				Object[] obj=(Object[])it.next();
				map.put("dbName", obj[0].toString());
				map.put("serverIP", obj[1].toString());
			}
			
		}
		catch(Exception e)
		{
			key="err";
			e.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public String getClientStatus(String clientName)
	{
		String key="N";
		Session session=null;
		Query qry=null;
		Statement stmt=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String sql="select Client_Name from Client_Details where Client_Name='"+clientName+"'";
			System.out.println("SQL : "+sql);
			qry=session.createSQLQuery(sql);
			if(qry.uniqueResult()==null)
				key="Y";
			else
			{
				String name=(String)qry.uniqueResult();
				System.out.println("Name : "+name);
				if(clientName.equalsIgnoreCase(name))
					key="N";
				else
					key="Y";
						
			}
			System.out.println("Key : "+key);
		}
		catch(Exception e)
		{
			key="err";
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
		return key;
	}
	
	public HashMap getAPIDetails(String apiID,HashMap map)
	{
		
		String key="fail";
		Session session=null;
		Query qry=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String sql="select a.Agent_Header_Id,a.Agent_Header_Password,b.Sub_Agent_Id,b.Sub_Agent_Password,b.Sub_Agent_Mobile_No from dbo.Rech_API_Corporate_Agent_Auth_details a,dbo.Rech_API_Corporate_Sub_Agent_Details b where a.Corporate_Agent_Id=b.Corporate_Agent_Id and a.Corporate_Agent_Id="+apiID;
			System.out.println("SQL : "+sql);
			qry=session.createSQLQuery(sql);
			List list=qry.list();
			Iterator it=list.iterator();
			while(it.hasNext())
			{
				Object[] obj=(Object[])it.next();
				map.put("AgentHeaderId", obj[0]);
				map.put("AgentHeaderPassword", obj[1]);
				map.put("SubAgentId", obj[2]);
				map.put("SubAgentPassword", obj[3]);
				map.put("SubAgentMobileNo", obj[4]);
				map.put("apiId", apiID);
				
			}
		}
		catch(Exception e)
		{
			map.put("err", "err");
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
		return map;
	}
	
}
