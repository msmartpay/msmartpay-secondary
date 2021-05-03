package com.activity.userRegistration.corporateUser.whitelableClient;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

/**
 * Updated By :Manoj
 * Updated Date : 1 Jun 2013
 * Updated Matter : Optimization of code as well as data base.
 */

class WhiteLabelRegistrationDao
{
	final ArrayList<String> getCategoryName()
	{
		Session session=null;
    	Query query=null;
    	String sql="";
    	ArrayList<String> list=new ArrayList<String>();
    	
    	try
    	{
    		session = HibernateSession.getSessionFactory().openSession(); 
    		sql="select distinct cat.categoryName from CategoryNameDetails cat";
    		query=session.createQuery(sql);
    		list=(ArrayList<String>)query.list();
    					
    	}catch(Exception e)
    	{
    		System.out.println("Exception in WhiteLabelRegistrationDao,getCategoryName");
    		System.out.println(e.toString());
    	}
    	finally
    	{
    		try
    		{
    			session.flush();
    			session.close();
	
    		}catch(Exception e)	
    		{
    			System.out.println("Exception in WhiteLabelRegistrationDao,getCategoryName");
    			System.out.println(e.toString());
    		}			
    	}
    	return list;
	}

	final HashMap<String,String> whiteLabelClientRegistration(String companyName,String headerHomeFileName, String agentPanelHelpMobileNo1,
			String agentPanelHelpEmailId, String domainName,String agentLoginUrl, String distributorLoginUrl, String busUrl,
			String airUrl, String agentCellEmailId, String agentUserType,String distUserType, String mdLoginUrl, String careUrl,
			String clientType, String clientFlag, String category,String firstName, String lastName, String dateOfBirth,
			String gender, String designation, String officialNumber,String emailId, String addressLine1, String addressLine2,
			String country, String state, String district, String pincode,String password, String city, String registerBy, String panNo) 
			{
		
		Session session=null; 
		String status="invalid";
        HashMap<String,String> map=new HashMap<String,String>();
        
        try
        {
        	session = HibernateSession.getSessionFactory().openSession(); 
        	session.beginTransaction(); 		  
        	Connection con=session.connection(); 		  
        	CallableStatement cstmt =null;
        	
        	cstmt=con.prepareCall("{call White_Label_Client_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        	
        	cstmt.setString(1,companyName);
        	cstmt.setString(2,headerHomeFileName);
        	cstmt.setString(3,agentPanelHelpMobileNo1);
        	cstmt.setString(4,agentPanelHelpEmailId);
        	cstmt.setString(5,domainName);
        	cstmt.setString(6,agentLoginUrl);
        	cstmt.setString(7,distributorLoginUrl);
        	cstmt.setString(8,busUrl);
        	cstmt.setString(9,airUrl);
        	cstmt.setString(10,agentCellEmailId);
        	cstmt.setString(11,agentUserType);
        	cstmt.setString(12,distUserType);
        	cstmt.setString(13,mdLoginUrl);
        	cstmt.setString(14,careUrl);
        	cstmt.setString(15,clientType);
        	cstmt.setString(16,clientFlag);
        	cstmt.setString(17,category);
        	cstmt.setString(18,firstName);
        	cstmt.setString(19,lastName);
        	cstmt.setString(20,dateOfBirth);
        	cstmt.setString(21,gender);
        	cstmt.setString(22,designation);
        	cstmt.setString(23,officialNumber);
        	cstmt.setString(24,emailId);
        	cstmt.setString(25,addressLine1);
        	cstmt.setString(26,addressLine2);
        	cstmt.setString(27,country);
        	cstmt.setString(28,state);
        	cstmt.setString(29,district);
        	cstmt.setString(30,pincode);
        	cstmt.setString(31,password);
        	cstmt.setString(32,city);
        	cstmt.setString(33,registerBy);
        	cstmt.setString(34,panNo);
        	ResultSet rs=cstmt.executeQuery();
        	session.getTransaction().commit(); 	 
       
        	while(rs.next())
        	{
        		map=new HashMap<String,String>();
        		map.put("clientID",rs.getString(1));
        		map.put("domainName",rs.getString(2));
        		map.put("AgentLoginUrl",rs.getString(3));
        		map.put("DSLoginUrl",rs.getString(4));
        		map.put("MDLoginUrl",rs.getString(5));
        		map.put("CompanyName",rs.getString(6));
        		map.put("HelpMailID",rs.getString(7));
        		map.put("UserID",rs.getString(8));
        	} 
        }
        catch(HibernateException e)
        {
        	status="invalid";
        	System.out.println("Exception in WhiteLabelRegistrationDao,whiteLabelClientRegistration");
        	if(session.getTransaction()!= null)			 
        		session.getTransaction().rollback();
        	System.out.println(e.toString());
        }
        catch(SQLException e)
        {
        	status="invalid";
        	System.out.println("Exception in WhiteLabelRegistrationDao,whiteLabelClientRegistration");
        	if(session.getTransaction()!= null)
        		session.getTransaction().rollback();
        	System.out.println(e.toString());
        }
        catch(Exception e)
        {
        	status="invalid";
        	System.out.println("Exception in WhiteLabelRegistrationDao,whiteLabelClientRegistration");
        	session.getTransaction().rollback();
        	System.out.println(e.toString());
        }
        finally
        {
        	try
        	{			
        		session.close();
        	}
        	catch(Exception e)
        	{
        		System.out.println("Exception in WhiteLabelRegistrationDao,whiteLabelClientRegistration");
        		System.out.println(e.toString());
        	}		
        }  
        return map;	
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
			 //System.out.println("status of checkMobileNo : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in WhiteLabelRegistrationDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in WhiteLabelRegistrationDao,checkMobileNo");
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
				System.out.println("Exception in WhiteLabelRegistrationDao,checkMobileNo while closing connection");
				System.out.println(e.toString());
			}
		}
		return status; 
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
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in WhiteLabelRegistrationDao,checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in WhiteLabelRegistrationDao,checkUserName");
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
				System.out.println("Exception in WhiteLabelRegistrationDao,checkUserName");
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
			//System.out.println("status of checkUserEmail is : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in WhiteLabelRegistrationDao,checkUserEmail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in WhiteLabelRegistrationDao,checkUserEmail");
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
				System.out.println("Exception in WhiteLabelRegistrationDao,checkUserEmail while closing connection");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
}
