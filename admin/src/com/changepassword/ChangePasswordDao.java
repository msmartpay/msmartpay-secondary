package com.changepassword;

import java.util.HashMap;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.formBean.superAdmin.SuperAdminLoginInfoFormBean;
import com.login.LoginInfoFormBean;

 class ChangePasswordDao
{

	final HashMap<String,Object> getLoginDetails(String userId,String adminUserType)
	{
		Session session=null;
		String loginUserName="invalid";
		String password="";
		HashMap<String,Object> mapdata=new HashMap<String,Object>();
		Query query=null;
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int userid=Integer.parseInt(userId);
			if("superadmin".equalsIgnoreCase(adminUserType))
			{
				query=session.createQuery("select lf.userName,lf.password from SuperAdminLoginInfoFormBean lf where lf.userId=:userId")
				.setParameter("userId",userid);	
			}
			else
			{
				query=session.createQuery("select lf.userName,lf.password from LoginInfoFormBean lf where lf.userId=:userId")
				.setParameter("userId",userid);	
			}
			for(Iterator<?> it=query.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				loginUserName=(String)row[0];
				password=(String)row[1];
			}
			
			mapdata.put("loginUserName",loginUserName);
			mapdata.put("password",password);
		}catch(Exception E)
		{
			loginUserName="invalid";
			System.out.println("Exception in ChangePasswordDao , getLoginDetails () method");
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
				System.out.println("Exception in ChangePasswordDao , getLoginDetails () method");
				System.out.println(e.toString());
			}
		}
		return mapdata;
	}

	final String updatePassword(String userId,String newPassword,String adminUserType)
	{
		Session session=null;
		Transaction txn=null;
		String status="failure";
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			int userid=Integer.parseInt(userId);
			if("superAdmin".equalsIgnoreCase(adminUserType))
			{
				SuperAdminLoginInfoFormBean loginInfo = (SuperAdminLoginInfoFormBean)session.get(SuperAdminLoginInfoFormBean.class,userid);			
				if(loginInfo != null)
				{	
					loginInfo.setPassword(newPassword);
					session.update(loginInfo);
					txn.commit();
					status="success";
				}
				else
				{
					status="failure";
				}	
			}
			else
			{
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);			
				if(loginInfo != null)
				{	
					loginInfo.setPassword(newPassword);
					session.update(loginInfo);
					txn.commit();
					status="success";
				}
				else{
					status="failure";
				}	
			}
		}
		catch(Exception e)
		{
			status="failure";
			try
			{
				if(txn!=null)
				{
					txn.rollback();			
				}
			}
			catch(HibernateException ex)
			{
			}
			System.out.println("Exception in ChangePasswordDao , updatePassword () method");
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
				System.out.println("Exception in ChangePasswordDao , updatePassword () method");
				System.out.println(e.toString());
			}
		}
		return status;
	}
}
