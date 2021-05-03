package com.activity.userRegistration.retailUser.mds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

/**
 * Updated By 		: Manoj
 * Updated Date 	: 10 June 2013
 * Updated Matter	: Optimization of code as well as data base.
 */

public class MdsRegistrationDao
{
	final String getPortalId(String userId)
	{
		Session session=null;
		String portalId="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query=session.createQuery("select cf.portalId from AdminUserFormBean cf where convert(varchar,cf.userId)=:userId").setParameter("userId",userId);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalId=((Integer)row).toString();
			}
			
		}catch(Exception E)
		{
			portalId="invalid";
			System.out.println("Exception in MdsRegistrationDao,getPortalId");
			System.out.println(E.toString());
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
				System.out.println("Exception in MdsRegistrationDao,getPortalId");
				System.out.println(e.toString());
			}
		}
		return portalId;
	}
	
	final HashMap<String,String> getDynamicDetails(String portalId)
	{

		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;
		int _portalId=Integer.parseInt(portalId);
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select d.helpEmailId,d.mdLoginUrl  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				
				map.put("helpEmailId",(String)row[0]);
				map.put("mdLoginUrl",(String)row[1]);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsRegistrationDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsRegistrationDao,getDynamicDetails");
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
				System.out.println("Exception in MdsRegistrationDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}
	
	public static String getRandomString(int length)
	{

		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	final String checkUserName(String emailId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.emailId from MdsDetailsFormBean mf where mf.emailId=:emailId").setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				status="invalid";
			}
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
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
				System.out.println("Exception in MdsRegistrationDao,checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final String checkMobileNo(String MobileNo)
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where mf.mobileNo=:mobileNo").setParameter("mobileNo",MobileNo);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				status="invalid";
			}
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
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
				System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
				System.out.println(e.toString());
			}
		}
		return status; 
	}

	final HashMap<String, String> MDSRegistration(String portalId,String firstName, String lastName, String dateOfBirth,String gender,
			 String companyType, String companyName,String emailId, String mobileNo, String addressLine1,String addressLine2, String state,
			 String district, String country,String cityCode, String pincode, String panNo, String password, String userId, String mdPortalID) 
			 {
		
		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;      
		Connection con=null;
        try
        {
        	session = HibernateSession.getSessionFactory().openSession(); 
        	session.beginTransaction();
        	con=session.connection();
        	CallableStatement cstmt =null;
        	
        	cstmt=con.prepareCall("{call Master_Distributor_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        	cstmt.setString(1,portalId);
        	cstmt.setString(2,firstName);
        	cstmt.setString(3,lastName);
        	cstmt.setString(4,dateOfBirth);
        	cstmt.setString(5,gender);
        	cstmt.setString(6,companyType);
        	cstmt.setString(7,companyName);
        	cstmt.setString(8,emailId);
        	cstmt.setString(9,mobileNo);
        	cstmt.setString(10,addressLine1);
        	cstmt.setString(11,addressLine2);
        	cstmt.setString(12,state);
        	cstmt.setString(13,district);
        	cstmt.setString(14,country);
        	cstmt.setString(15,cityCode);
        	cstmt.setString(16,pincode);
        	cstmt.setString(17,panNo);
        	cstmt.setString(18,password);
        	cstmt.setString(19, userId);
        	cstmt.setString(20, mdPortalID);
        	ResultSet rs=cstmt.executeQuery();
        	session.getTransaction().commit();
        	
        	while(rs.next())
        	{
        		map.put("complteMDId",rs.getString(1));
        		map.put("mdId",rs.getString(2));
        	}
        }
        
        catch(HibernateException e)
        {
        	if(session.getTransaction()!= null)			 
        		session.getTransaction().rollback();
        	System.out.println("Exception in MdsRegistrationDao,registerMds");
        	System.out.println(e.toString());
        	e.printStackTrace();
        }
        catch(SQLException e)
        {
        	if(session.getTransaction()!= null)
        		session.getTransaction().rollback();
        	System.out.println("Exception in MdsRegistrationDao,registerMds");
        	System.out.println(e.toString());
        	e.printStackTrace();
        }
        catch(Exception e)
        {
        	session.getTransaction().rollback();
        	System.out.println("Exception in MdsRegistrationDao,registerMds");
        	System.out.println(e.toString());
        	e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		session.flush();
        		session.close();
        		if(con!=null)
        			con.close();
        	}
        	catch(Exception e)
        	{
        		System.out.println("Exception in MdsRegistrationDao,registerMds");
        		System.out.println(e.toString());
        	}
        }
        return map;
	}

	public String getMDPortalID(String portalId) 
	{
		Session session=null;
		String userID="";
		int _portalId=Integer.parseInt(portalId);
		
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createQuery("select userId from AdminUserFormBean where portalId=:portal_id").setParameter("portal_id", _portalId);
			if(query.uniqueResult()!=null)
				userID=query.uniqueResult().toString();
			else
				userID="";
			System.out.println(userID);
		}
		catch (Exception e)
		{
			System.out.println("Exception in MdsRegistrationDao,getMDPortalID");
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
		return userID;
	}

	public String checkClientID(String clientID, String userId, String userType) 
	{
		Session session=null;         
		String status="invalid";
		int _userId=Integer.parseInt(userId);
		System.out.println(_userId);
		int _clientID=Integer.parseInt(clientID);
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			if(userType.equalsIgnoreCase("PortalAdmin")||userType.equalsIgnoreCase("portalUser")){
				Query query1=session.createQuery("select mf.portalId from AdminUserFormBean mf where mf.User_id=:User_id and mf.portalId=:portalId").setParameter("User_id",_userId).setParameter("portalId", _clientID);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="valid";
				}
			}else
			{
				Query query1=session.createQuery("select mf.clientId from DynamicDetailsFormBean mf where mf.clientId=:clientId").setParameter("clientId", _clientID);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="valid";
				}
			}
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
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
				System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
}
