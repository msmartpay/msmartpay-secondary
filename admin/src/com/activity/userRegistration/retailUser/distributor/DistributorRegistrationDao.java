package com.activity.userRegistration.retailUser.distributor;
/**
 *  Updated By 		: Manoj
 *  Updated Date 	: 6-June 2013
 *  Updated Matter  : Code as well as DB optimization
 *  
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

/**
 *  Updated By 		: Manoj
 *  Updated Date 	: 6-June 2013
 *  Updated Matter  : Code as well as DB optimization
 *  
 *  
 */

 class DistributorRegistrationDao
{
	final HashMap<String,String> getDynamicDetails(int mdId)
	{
		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;
		int portalId=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="select client_id from MD_Details where MD_id="+mdId+"";
			Query query2=session.createSQLQuery(sql);
			String id=query2.uniqueResult().toString();
			portalId=Integer.parseInt(id);
			Query query1=session.createQuery("select d.helpEmailId,d.distributorLoginUrl  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				map.put("helpEmailId",(String)row[0]);
				map.put("distributorLoginUrl",(String)row[1]);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorRegistrationDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorRegistrationDao,getDynamicDetails");
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
				System.out.println("Exception in DistributorRegistrationDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}

	final String getRandomString(int length) 
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
	
	final String checkUserName(String emailId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select df.emailId from DistributorDetailsFormBean df where df.emailId=:emailId").setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				status="invalid";
			}
		//	System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in DistributorRegistrationDao, checkUserName");
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
			Query query1=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where df.mobileNo=:mobileNo").setParameter("mobileNo",mobileNo);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				status="invalid";
			}
		//	System.out.println("status is is>>>>>>>>>>>>>"+status);
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
 
	final HashMap<String, String> distributorRegistration(String mdId,String firstName, String lastName, String dateOfBirth,
			String gender, String companyType, String companyName,String panNo, String addressLine1, String addressLine2,String state,
			 String district, String country, String cityCode,String pincode, String emailId, String mobileNo, String password,String userId) 
			 {
		HashMap map=new HashMap();
		Session session=null; 
		Transaction txn=null;
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			Connection con=session.connection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Distributor_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,mdId);
			cstmt.setString(2,firstName);
			cstmt.setString(3,lastName);
			cstmt.setString(4,dateOfBirth);
			cstmt.setString(5,gender);
			cstmt.setString(6,companyType);
			cstmt.setString(7,companyName);
			cstmt.setString(8,panNo);
			cstmt.setString(9,addressLine1);
			cstmt.setString(10,addressLine2);
			cstmt.setString(11,state);
			cstmt.setString(12,district);
			cstmt.setString(13,country);
			cstmt.setString(14,cityCode);
			cstmt.setString(15,pincode);
			cstmt.setString(16,emailId);
			cstmt.setString(17,mobileNo);
			cstmt.setString(18,password);
			cstmt.setString(19,userId);
			ResultSet rs=cstmt.executeQuery();
			txn.commit();
			
			while(rs.next())
			{
				String status = rs.getString(1);
				if(status.equalsIgnoreCase("proceed"))
                {
					map.put("completeDistributorId", rs.getString("DSID"));
                    map.put("distributorId", rs.getString("distributor_id"));
                    map.put("mdEmailId", rs.getString("email_id"));
                    map.put("mdID", rs.getString("md_id"));
                    map.put("status", status);

                } else if(status.equalsIgnoreCase("limit_exceeded")){
                        map.put("status", rs.getString(1));
                }
			}	
		}
		catch(Exception e)
		{
			if(txn!=null)
			{
				try
				{
					txn.rollback();
				}catch(Exception ex){}
			}
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
				System.out.println("Exception in registerDistributor");
				System.out.println(e.toString());
			}
		}
		return map;
	}

	final String getMDIDStatus(String mdId, String userId, String userType)
	{
		Session session=null;
		String sql="";
		String status="Invalid";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			
			if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")||userType.equalsIgnoreCase("activityUser"))
			{
				sql="select count(md_id) from md_details where md_initial+convert(varchar(10),md_id)='"+mdId+"'";
			}else
			{
				sql="select count(md_id) from md_details where md_initial+convert(varchar(10),md_id)='"+mdId+"'and client_id in (select Portal_id from admin_user_details where user_id='"+userId+"')";
			}
				Query query=session.createSQLQuery(sql);
				int count=Integer.parseInt(query.uniqueResult().toString());
				if(count==1)
				{
					status="valid";
				}else
				{
					status="Invalid";
				}
				//System.out.println("status is :"+status);
				
		}catch(Exception e)
		{
			status="Invalid";
			System.out.println("Exception in DistributorRegistrationDao,getMDIDStatus");
			System.out.println(e.toString());
			
		}finally
		{
			session.flush();
			session.close();
		}
		return status;
	}

	public String checkMdsId(String mdsID, String user_Id, String userType) 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
