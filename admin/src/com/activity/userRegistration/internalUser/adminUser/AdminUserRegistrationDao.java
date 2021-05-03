package com.activity.userRegistration.internalUser.adminUser;
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
 *  Updated By : Manoj
 *  Updated Date : 01 June 2013
 *  Updated Matter : Code optimization and data base optimization
 */
class AdminUserRegistrationDao
{
	final HashMap<String,String> getDynamicDetails(String portalId)
	{
		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;
		int _portalId=Integer.parseInt(portalId);
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select d.adminLoginUrl,d.helpEmailId  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				map.put("adminLoginUrl",(String)row[0]);
				map.put("helpEmailId",(String)row[1]);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserRegistrationDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserRegistrationDao,getDynamicDetails");
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
				System.out.println("Exception in AdminUserRegistrationDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}
	
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
				portalId=row.toString();
			}
		}catch(Exception E)
		{
			portalId="invalid";
			System.out.println("Exception in AdminUserRegistrationDao,getPortalId");
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
				System.out.println("Exception in AdminUserRegistrationDao,getPortalId");
				System.out.println(e.toString());
			}
		}
		System.out.println("portalId : "+portalId);
		return portalId;
	}
	
	public static String getRandomString(int length) 
	{
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
    
		for (int i = 0; i < length; i++)
		{
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}
	
	final String checkUserName(String loginUserName)
	{
		Session session=null;         
 		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
 			Query query1=session.createQuery("select lf.userName from LoginInfoFormBean lf where lf.userName=:loginUserName").setParameter("loginUserName",loginUserName);
 			for(Iterator it=query1.iterate();it.hasNext();)
 			{
				  
 				Object row = (Object) it.next();
 				status="invalid";
 			}
 			//System.out.println("status of checkUserName is :: "+status);
 		}
 		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AdminUserRegistrationDao,checkUserName");
			System.out.println(e.toString());
		}
 		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserRegistrationDao,checkUserName");
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
				System.out.println("Exception in AdminUserRegistrationDao,checkUserName");
				System.out.println(e.toString());
			}
		}
 		return status; 
	}
	
	final String checkUserEmail(String emailId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.emailId from AdminUserFormBean mf where mf.emailId=:emailId").setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				status="invalid";
			}
		//	System.out.println("status of checkUserEmail is :: "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AdminUserRegistrationDao,checkUserEmail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserRegistrationDao,checkUserEmail");
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
				System.out.println("Exception in AdminUserRegistrationDao,checkUserEmail");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final String checkMobileNo(String officialNumber)
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.officialNumber from AdminUserFormBean mf where mf.officialNumber=:officialNumber").setParameter("officialNumber",officialNumber);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				  
				Object row = (Object) it.next();
				status="invalid";
			}
			//System.out.println("status of checkMobileNo is :: "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AdminUserRegistrationDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserRegistrationDao,checkMobileNo");
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
				System.out.println("Exception in AdminUserRegistrationDao,checkMobileNo");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final HashMap<String, String> AdminUserRegistration(String portalId,
			String firstName, String lastName, String dateOfBirth,
			String gender, String companyType, String companyName,
			String officialNumber, String emailId, String addressLine1,
			String addressLine2, String country, String state, String district,
			String cityCode, String pincode, String password,
			String activityUserType, String panNo, String userId) 
			{
		
		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			session.beginTransaction();
			Connection con=session.connection();
			CallableStatement cs = con.prepareCall("{ call Activity_Admin_User_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			cs.setString(1,portalId);
			cs.setString(2,firstName);
			cs.setString(3,lastName);
			cs.setString(4,dateOfBirth);
			cs.setString(5,gender);
			cs.setString(6,companyType);
			cs.setString(7,companyName);
			cs.setString(8,officialNumber);
			cs.setString(9,emailId);
			cs.setString(10,addressLine1);
			cs.setString(11,addressLine2);
			cs.setString(12,country);
			cs.setString(13,state);
			cs.setString(14,district);
			cs.setString(15,cityCode);
			cs.setString(16,pincode);
			cs.setString(17,password);
			cs.setString(18,activityUserType);
			cs.setString(19,panNo);
			cs.setString(20,userId);
			ResultSet rs= cs.executeQuery();
			session.getTransaction().commit();
			
			while(rs.next())
			{
				map.put("UserID",rs.getString(1));
				map.put("EmailID",rs.getString(2));
			}
		}
		catch(HibernateException e){
			System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
			System.out.println(e.toString());
			try{
			 
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex)
			{
				System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
				System.out.println(ex.toString());
			 }
		}
		catch(SQLException e)
		{
			System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
			System.out.println(e.toString());
			try
			{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex)
			{
				System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
				System.out.println(ex.toString());
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
			System.out.println(e.toString());
			try
			{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex)
			{
				System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
				System.out.println(ex.toString());
			}
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
				System.out.println("Exception in AdminUserRegistrationDao,AdminUserRegistration");
				System.out.println(e.toString());
			}
		}
		return map;
	}
}
