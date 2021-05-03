package com.activity.userRegistration.internalUser.EGEN;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class APIClientRegistrationDao
{

	public static String getCityName(String cityCode)
	{
		Session session=null;
		String cityName="invalid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query=session.createQuery("select cf.cityName from CityDetailsFormBean cf where cf.cityCode=:cityCode").setParameter("cityCode",cityCode);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				cityName=(String)row;
			}
		}catch(Exception E)
		{
			cityName="invalid";
			System.out.println("Exception in getCityName");
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
				System.out.println("Exception in getCity");
				System.out.println(e.toString());
			}
		}
		return cityName;
	}
	
	public static String checkIP(String IP)
	{
		String resultresponse="valid";
		Session session=null;
		try
		{
			//System.out.println("IP is>>>>>>>>>>>>>>>>>>>>>>>>>>>"+IP);
			session = HibernateSession.getSessionFactory().openSession();
			Query query=session.createQuery("select af.corporateAgentId from APIClientAuthenticationDetailsFormBean af where af.authorizedIP1=:IP1 OR af.authorizedIP2=:IP2").setParameter("IP1",IP).setParameter("IP2",IP);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				resultresponse="invalid";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
				System.out.println("Exception in checkIP ");
				System.out.println(e.toString());
			}
		}
		return  resultresponse;
	}
	
	public static String checkEmail(String emailId)
	{
		String resultresponse="valid";
		Session session=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			Query query=session.createQuery("select af.emailId from AesAPICustomerDetails af  where af.emailId=:emailId").setParameter("emailId",emailId);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				resultresponse="invalid";
			}
		}
		catch(Exception E)
		{
			resultresponse="invalid";
			E.printStackTrace();
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
				System.out.println("Exception in checkEmail");
				System.out.println(e.toString());
			}
		}
		return  resultresponse;
	}
	
	public static String checkUserName(String userName)
	{
		String resultresponse="valid";
		Session session=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			Query query=session.createQuery("select af.customerAuthId from AesAPICustomerAuthDetails af  where af.customerAuthId=:customerAuthId").setParameter("customerAuthId",userName);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				resultresponse="invalid";
			}
		}
		catch(Exception E)
		{
			resultresponse="invalid";
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
				System.out.println(e.toString());
			}
		}
		return  resultresponse;
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
	
	public static void main(String[] args)
	{
		System.out.println(APIClientRegistrationDao.getRandomString(8));
	}
	
	public static HashMap APIRegistration(String firstName, String lastName,String dob, String agencyName, String panNo, String officeAddress,
			String officeState, String officeDistrict, String officeCountry,String officePincode, String officeEmailId, String pass,
			String officeMobileNo, String headerUserName) 
	{
		Session session=null; 
		String status="invalid";
		HashMap hm=new HashMap();
		Transaction txn=null;
		try
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date date = sdf1.parse(dob);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			
			session = HibernateSession.getSessionFactory().openSession(); 
			session.beginTransaction();
			//Connection con=session.connection();
			CallableStatement cstmt =null;
//			System.out.println("we are going to call egen registrtion procedure");
			cstmt=session.connection().prepareCall("{call API_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,firstName);
			cstmt.setString(2,lastName);
			cstmt.setDate(3,sqlStartDate);
			cstmt.setString(4,agencyName);
			cstmt.setString(5,panNo);
			cstmt.setString(6,officeAddress);
			cstmt.setString(7,officeState);
			cstmt.setString(8,officeDistrict);
			cstmt.setString(9,officeCountry);
			cstmt.setString(10,officePincode);
			cstmt.setString(11,officeEmailId);
			cstmt.setString(12,pass);
			cstmt.setString(13,officeMobileNo);
			cstmt.setString(14,headerUserName);
			ResultSet rs=cstmt.executeQuery();
			session.getTransaction().commit();
//			System.out.println("we are here");
			while(rs.next())
			{
				hm.put("status",rs.getString(1));
				hm.put("aesApiId",rs.getString(2));
				hm.put("regMobileNo",rs.getString(3));
				hm.put("loginUserName",rs.getString(4));
				hm.put("loginPassword",rs.getString(5));
				hm.put("customerAuthId",rs.getString(6));
				hm.put("customerAuthPass",rs.getString(7));
				
			}
			//System.out.println("hm in dao class>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+hm);
		}
		catch(HibernateException e)
		{
			status="invalid";
			if(session.getTransaction()!= null)
				session.getTransaction().rollback();
			System.out.println("Exception in registerAPIClient");
			System.out.println(e.toString());
		}
		catch(SQLException e)
		{
			status="invalid";
			if(session.getTransaction()!= null)
				session.getTransaction().rollback();
			System.out.println("Exception in registerAPIClient");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";
			session.getTransaction().rollback();
			System.out.println("Exception in registerAPIClient");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in registerAPIClient");
				System.out.println(e.toString());
			}
		}
		return hm;
	}
	
	public static String checkMobileNo(String mobileNo)
	{
		String resultresponse="valid";
		Session session=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			Query query=session.createQuery("select af.regMobileNo from AesAPICustomerDetails af  where af.regMobileNo=:regMobileNo").setParameter("regMobileNo",mobileNo);
			for(Iterator it=query.iterate();it.hasNext();){
				Object row = (Object) it.next();
				resultresponse="invalid";
			}
		}
		catch(Exception E)
		{
			resultresponse="invalid";
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
				System.out.println(e.toString());
			}
		}
		return  resultresponse;
	}
}
