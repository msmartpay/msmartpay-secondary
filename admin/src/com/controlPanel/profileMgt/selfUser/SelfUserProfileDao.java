package com.controlPanel.profileMgt.selfUser;

import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;


 class SelfUserProfileDao {
	
	final HashMap<String,String> showSelfProfile(int id)
	{
		Session session=null;
		Query query=null;
		String sql="";
		Object[] obj=null;
		HashMap<String,String>  map=new HashMap<String,String>();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction ts=session.beginTransaction();
			
			sql="select aud.First_name,aud.Last_name,aud.Date_of_birth,aud.Gender,aud.CompanyType,aud.CompanyName,aud.Mobile_no,aud.Official_email_id,aud.Address_line1,aud.Address_line2,aud.State,aud.District,aud.City,aud.Pincode,aud.PAN_No from Admin_User_details aud where aud.User_id="+id;
				
//			sql="select aud.First_name,aud.Last_name,aud.Date_of_birth,aud.Gender,aud.CompanyType,aud.CompanyName,aud.Mobile_no,aud.Official_email_id,aud.Address_line1,aud.Address_line2,aud.State,aud.District,aud.City,aud.Pincode,aud.PAN_No from Admin_User_details aud where aud.User_id="+id;
			
			query=session.createSQLQuery(sql);
			obj=(Object[])query.uniqueResult();				
			map.put("fName", (String)obj[0]);
			map.put("lName", (String)obj[1]);
			System.out.println("date in class------------------   "+obj[2].toString());
			map.put("dob", (String)obj[2].toString());
			map.put("gender",(String) obj[3]);
			map.put("companyType",(String) obj[4]);
			map.put("companyName",(String) obj[5]);
			map.put("mobileNo",(String) obj[6]);
			map.put("emailId",(String) obj[7]);
			map.put("address1",(String) obj[8]);
			map.put("address2",(String) obj[9]);
			map.put("state",(String) obj[10]);
			map.put("district",(String) obj[11]);
			map.put("city",(String) obj[12]);
			map.put("pincode",(String) obj[13]);
			map.put("panNo",(String) obj[14]);
			
			return map;				
		}catch(Exception E){
			System.out.println("Exception in SelfUserProfileDao, showSelfProfile");
			System.out.println(E.toString());
			return map;
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in SelfUserProfileDao,showSelfProfile");
				System.out.println(e.toString());		
			}			
		}
	}
	final String checkUserEmail(String emailId, String userId){
		Session session=null;         
		String status="valid";
		try{

			session = HibernateSession.getSessionFactory().openSession(); 
			int _userId=Integer.parseInt(userId);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.emailId from AdminUserFormBean mf where mf.userId=:userId").setParameter("userId", _userId);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId)){
				status="valid";
			}else{
				Query query1=session.createQuery("select mf.emailId from AdminUserFormBean mf where mf.emailId=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserEmail is : "+status);
		 }

		catch(HibernateException e)
		{
			
			status="invalid";
			System.out.println("Exception in SelfUserProfileDao,checkUserEmail");
			System.out.println(e.toString());	
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in SelfUserProfileDao,checkUserEmail");
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
				System.out.println("Exception in SelfUserProfileDao,checkUserEmail");
				System.out.println(e.toString());	
			}
		}
		return status; 
	}
	final String checkMobileNo(String officialNumber, String userId) {
		 Session session=null;         
		 String status="valid";
		 try{

			 session = HibernateSession.getSessionFactory().openSession(); 
			 int _userId=Integer.parseInt(userId);
				
				session = HibernateSession.getSessionFactory().openSession(); 
				Query quey=session.createQuery("select mf.officialNumber from AdminUserFormBean mf where mf.userId=:userId").setParameter("userId", _userId);
				String OldeMobileNo=quey.uniqueResult().toString();
				if(OldeMobileNo.equalsIgnoreCase(officialNumber)){
					
					status="valid";
				}else{
					Query query1=session.createQuery("select mf.officialNumber from AdminUserFormBean mf where mf.officialNumber=:officialNumber").setParameter("officialNumber",officialNumber);
					for(Iterator it=query1.iterate();it.hasNext();){
						Object row = (Object) it.next();
						status="invalid";
					}
				}
				//System.out.println("status of checkMobileNo : "+status);
		 }
		 catch(HibernateException e)
		 {
			 status="invalid";
			 System.out.println("Exception in SelfUserProfileDao,checkMobileNo");
			 System.out.println(e.toString());	
		 }
		 catch(Exception e)
		 {
			 status="invalid";	
			 System.out.println("Exception in SelfUserProfileDao,checkMobileNo");
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
				 System.out.println("Exception in SelfUserProfileDao,checkMobileNo");
				 System.out.println(e.toString());	
			 }
		 }
		 return status; 
	}
	
	final String checkUserName(String emailId, String userId){
		Session session=null;         
		String status="valid";
		try{

			session = HibernateSession.getSessionFactory().openSession(); 
			int _userId=Integer.parseInt(userId);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.userName from LoginInfoFormBean mf where mf.userId=:userId").setParameter("userId", _userId);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId)){
				status="valid";
			}else{
				Query query1=session.createQuery("select mf.userName from LoginInfoFormBean mf where mf.userName=:userName").setParameter("userName",emailId);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserEmail is : "+status);
		 }

		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in SelfUserProfileDao,checkUserName");
			System.out.println(e.toString());	
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in SelfUserProfileDao,checkUserName");
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
				System.out.println("Exception in SelfUserProfileDao,checkUserName");
				System.out.println(e.toString());	
			}
		}
		return status; 
	}
	public String updateSelfProfile(String firstName, String lastName,
			String dob, String gender, String companyType, String companyName,
			String emailId, String mobileNo, String addressLine1,
			String addressLine2, String state, String district, String city,
			String pincode, String panNo, String userId, String type) {
		// TODO Auto-generated method stub
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try{
			
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _userId=Integer.parseInt(userId);
			String sql="update admin_user_details set Date_of_birth='"+dob+"',Gender='"+gender+"',CompanyName='"+companyName+"',CompanyType='"+companyType+"',Address_line1='"+addressLine1+"',"+
							"Address_line2='"+addressLine2+"',State='"+state+"',District='"+district+"',City='"+city+"',Pincode='"+pincode+"',Mobile_no='"+mobileNo+"',PAN_No='"+panNo+"',First_name='"+firstName+"',Last_name='"+lastName+"'" +
							",Official_email_id='"+emailId+"' where user_id="+_userId;
			
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			if(!"superAdmin".equalsIgnoreCase(type)){
				sql="update admin_login_details set user_name='"+emailId+"' where user_id="+_userId+" ";
				Query query2=session.createSQLQuery(sql);
				query2.executeUpdate();
			}
			ts.commit();
			status="success";
			//System.out.println("status of updateSelfProfile : "+status);
		}
		catch(HibernateException e)
		{
			ts.rollback();
			status="fail";
			System.out.println("Exception in SelfUserProfileDao,updateSelfProfile");
			System.out.println(e.toString());	
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in SelfUserProfileDao,updateSelfProfile");
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
				System.out.println("Exception in SelfUserProfileDao,updateSelfProfile");
				System.out.println(e.toString());	
			}
		}
		return status; 
	}
}
