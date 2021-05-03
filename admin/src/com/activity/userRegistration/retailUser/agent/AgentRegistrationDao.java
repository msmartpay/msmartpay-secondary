package com.activity.userRegistration.retailUser.agent;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

/**
 * 	Updated By : Manoj
 * 	Updated Date : 07-June-2013
 * 	Updated Matter : Code as well as Database Optimiazation
 */
class AgentRegistrationDao
{

	final HashMap<String,String> getDynamicDetails(String distributorId)
	{

		HashMap<String,String> mapdata=new HashMap<String,String>();;
		Session session=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="select Help_Desk_EmailID,Agent_login_url,flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id='"+distributorId+"'))";
			Query query2=session.createSQLQuery(sql);
			List list=query2.list();
			Iterator itr=list.iterator();
			Object [] row;
			
			while(itr.hasNext())
			{
				row=(Object[])itr.next();
				mapdata.put("helpEmailId",row[0].toString());
				mapdata.put("agentLoginUrl",row[1].toString());
				mapdata.put("clientFlag",row[2].toString());
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
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
				System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
				System.out.println(e.toString());
			}
		}
		return mapdata;
	}
	
	final String checkDistributorIdStatus(String distributorId,String userId, String adminUserType)
	{

		Session session=null;         
		String status="invalid";
		String sql="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			if(adminUserType.equalsIgnoreCase("superadmin")||adminUserType.equalsIgnoreCase("activityAdmin")|| adminUserType.equalsIgnoreCase("activityUser"))
			{
				sql="select count(distributor_id) from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+distributorId+"'";
			}else
			{
				sql="select count(distributor_id) from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+distributorId+"' and md_id in (select md_id from md_details where client_id in (select Portal_id from admin_user_details where user_id='"+userId+"'))";
			}
			//System.out.println(sql);
			Query query1=session.createSQLQuery(sql);
			int count=Integer.parseInt(query1.uniqueResult().toString());
			if(count==1)
			{
				status="Valid";
			}else
			{
				status="invalid";
			}
			//System.out.println("status is "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AgentRegistrationDao,checkDistributorIdStatus ");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AgentRegistrationDao,checkDistributorIdStatus ");
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
				System.out.println("Exception in AgentRegistrationDao,checkDistributorIdStatus ");
				System.out.println(e.toString());
			}
		}
		return status; 
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
			Query query1=session.createQuery("select af.emailId from AgentDetailsFormBean af where af.emailId=:emailId").setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
  				Object row = (Object) it.next();
				status="invalid";
			}
  			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
  		{
			status="invalid";
  			System.out.println("Exception in checkUserName");
  			System.out.println(e.toString());
  		}
		catch(Exception e)
  		{
  			status="invalid";	
  			System.out.println("Exception in checkUserName");
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
  				System.out.println("Exception in checkUserName");
  				System.out.println(e.toString());
  			}
  		}
		return status; 
  	 }
       
	final String checkMobileNo(String mobileNo)
	{
		Session session=null;         
		String status="valid";
    	try
    	{
    		session = HibernateSession.getSessionFactory().openSession(); 
    		Query query1=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where af.mobileNo=:mobileNo").setParameter("mobileNo",mobileNo);
    		for(Iterator it=query1.iterate();it.hasNext();)
    		{
    			Object row = (Object) it.next();
				status="invalid";
			}
			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
    	catch(HibernateException e)
    	{
    		status="invalid";
    		System.out.println("Exception in checkUserName");
    		System.out.println(e.toString());
    	}	
    	catch(Exception e)
    	{
    		status="invalid";	
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
				System.out.println(e.toString());
			}
    	}
		return status; 
    }
	
	final HashMap<String,String> agentRegistration(String distributorId,String firstName, String lastName, String dateOfBirth,
			String gender, String companyType, String companyName,String emailId, String mobileNo, String addressLine1,
			String addressLine2, String country, String state, String district,String cityCode,
			String pincode, String panNo, String password,String userId,int apiStatus)
			{
		HashMap map=new HashMap();
		Session session=null;      
        try
        {
        	session = HibernateSession.getSessionFactory().openSession(); 
        	session.beginTransaction();
		 	Connection con=session.connection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Agent_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        	cstmt.setString(1,distributorId);
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
        	cstmt.setString(12,country);
        	cstmt.setString(13,state);
        	cstmt.setString(14,district);
        	cstmt.setString(15,cityCode);
        	cstmt.setString(16,pincode);
        	cstmt.setString(17,panNo);
        	cstmt.setString(18,password);
        	cstmt.setString(19,userId);
        	cstmt.setInt(20,apiStatus);
        	ResultSet rs=cstmt.executeQuery();
		 	session.getTransaction().commit();
		 	
		 	while(rs.next())
		 	{
		 		map.put("clientFlag",rs.getString(2));
		 		map.put("completeAgentId",rs.getString(3));
		 		map.put("distributorEmailId",rs.getString(4));
		 		map.put("MPIN",rs.getString(5));
		 		map.put("distributorID",rs.getString(6));
		 	}
        }
        catch(HibernateException e)
        {
        	if(session.getTransaction()!= null)			 
        		session.getTransaction().rollback();
        	System.out.println("Exception in registerAgent");
        	System.out.println(e.toString());
        }
        catch(SQLException e)
        {
        	if(session.getTransaction()!= null)
        		session.getTransaction().rollback();
        	System.out.println("Exception in registerAgent");
        	System.out.println(e.toString());
        }
        catch(Exception e)
        {
        	session.getTransaction().rollback();
        	System.out.println("Exception in registerAgent");
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
        		System.out.println("Exception in registerAgent");
        		System.out.println(e.toString());
        	}
        }
        return map;
	}
}
