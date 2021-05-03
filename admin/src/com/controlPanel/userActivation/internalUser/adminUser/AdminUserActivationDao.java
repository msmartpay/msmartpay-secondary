package com.controlPanel.userActivation.internalUser.adminUser;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.login.LoginInfoFormBean;

/*	updated By   	: Manoj
 * 	updated Matter 	: Keyword change Blocked ,Unblocked and Email send disabled
 * 	updatde date    : 16 Oct 2012
 */

 class AdminUserActivationDao
 {
	final ArrayList getPortalIdList()
	{
		Session session=null;         
		ArrayList portalIdList=new ArrayList();
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select au.clientId from DynamicDetailsFormBean au order by au.clientId");
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalIdList.add(row);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getPortalIdList");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getPortalIdList");
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
				System.out.println("Exception in AdminUserActivationDao,getPortalIdList");
				System.out.println(e.toString());
			}
		}
		return portalIdList; 
	}
	
	final ArrayList getAllAdminUserDetail(int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAllAdminUserDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAllAdminUserDetail");
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
				System.out.println("Exception in AdminUserActivationDao,getAllAdminUserDetail ");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsStatusWise(String status,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and alf.loginStatus=:status order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsBlockStatusWise(String status,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and alf.blockStatus=:status order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsBlockStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsUserIdWise(String userId,int pageNumber,String portalId)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			Query query1=null;
			if(!portalId.equalsIgnoreCase("all"))
			{
				int _portalId=Integer.parseInt(portalId);
				qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and convert(varchar,au.userId)=:userId and wf.clientId=:portalId order by au.userId";
				//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
				query1=session.createQuery(qyery).setParameter("userId",userId).setParameter("portalId",_portalId);
			}
			else
			{
				qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and convert(varchar,au.userId)=:userId order by au.userId";
				//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
				query1=session.createQuery(qyery).setParameter("userId",userId);
			}
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String user_Id=((Object)row[9]).toString();
				userMap.put("userId",user_Id);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsUserIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsUserIdWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsUserIdWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsPortalWise(String portalId,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and wf.clientId=:portalId order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsPortalWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsPortalWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsPortalWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsOfPortalIdStatusWise(String portalId,String status,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and wf.clientId=:portalId and alf.loginStatus=:status order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	final ArrayList getAdminUserDetailsOfPortalIdBlockStatusWise(String portalId,String status,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.registrationDate,au.userId,au.firstName,au.lastName,alf.loginStatus,alf.blockStatus,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and wf.clientId=:portalId and alf.blockStatus=:status order by au.userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("portalId",row[0]);
				userMap.put("clientCompanyName",row[1]);
				userMap.put("domaiName",row[2]);
				userMap.put("clientFlag",row[3]);
				userMap.put("categoryName",row[4]);
				userMap.put("wlBlockStatus",row[5]);
				userMap.put("agentCellEmailId",row[6]);
				userMap.put("helpMobileNo",row[7]);
				dateofwlreg=dateConvert(row[8]);
				userMap.put("wlRegistrationDate",dateofwlreg);
				String userId=((Object)row[9]).toString();
				userMap.put("userId",userId);
				String name=(String)row[10]+" "+(String)row[11];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[12]);
				userMap.put("userBlockStatus",row[13]);
				double totCash=(Double)row[14];			  
				double usedCash=(Double)row[15];
				double cutOffAmount=(Double)row[16];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(balance));
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdBlockStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getAdminUserDetailsOfPortalIdBlockStatusWise ");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	final int getNoOfRecordsAllAdminUserDetails()
	{
		Session session=null;         
		long count=0;
		int check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
			//System.out.println("Total no of user  "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetails");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetails ");
				System.out.println(e.toString());
			}
		}
		return check; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsStatusWise(String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and alf.loginStatus=:status";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
				//System.out.println("Total no of User "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsBlockStatusWise(String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId  and alf.blockStatus=:status";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of User  "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsBlockStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsPortalIdWise(String portalId)
	{
		Session session=null;         
		int count=0;
		long check=0;
		//System.out.println("getNoOfRecordsAllAdminUserDetailsPortalIdWise porta l i d is>>"+portalId);
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and au.portalId=:portalId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
				//System.out.println("Total no of User "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDaogetNoOfRecordsAllAdminUserDetailsPortalIdWise");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise(String portalId,String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and au.portalId=:portalId and alf.loginStatus=:status";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of User "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise(String portalId,String status)
	{
	     Session session=null;         
	     int count=0;
	     long check=0;
	     try
	     {
	    	 session = HibernateSession.getSessionFactory().openSession();
	    	 int _portalId=Integer.parseInt(portalId);
	    	 String qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId and au.portalId=:portalId and alf.blockStatus=:status";
	    	 //System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
	    	 Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
	    	 for(Iterator it=query1.iterate();it.hasNext();)
	    	 {
	    		 Object row = (Object) it.next();
	    		 check=(Long)row;
	    		 count=(int)check; 
	    	 }
	    	 //System.out.println("Total no of User "+count);
	     }
	     catch(HibernateException e)
	     {
	    	 System.out.println("Exception in AdminUserActivationDaogetNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise");
	    	 System.out.println(e.toString());
	     }
	     catch(Exception e)
	     {
	    	 System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise");
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
	    		 System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsPortalIdBlockStatusWise");
	    		 System.out.println(e.toString());
	    	 }
	     }
	     return count; 
	}
	
	final int getNoOfRecordsAllAdminUserDetailsUserIdWise(String userId,String portalId)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			String qyery="";
			Query query1=null;
			session = HibernateSession.getSessionFactory().openSession();
			if(!portalId.equalsIgnoreCase("all"))
			{
				int _portalId=Integer.parseInt(portalId);
				qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId  and convert(varchar,au.userId)=:userId and au.portalId=:portalId";
				//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
				query1=session.createQuery(qyery).setParameter("userId",userId).setParameter("portalId",_portalId);
			}
			else
			{
				qyery="select count(au.userId) from AdminUserFormBean au,UserAmountFormBean aaf,DynamicDetailsFormBean wf,LoginInfoFormBean alf where alf.userId=au.userId and au.userId=aaf.userId and au.portalId=wf.clientId  and convert(varchar,au.userId)=:userId";
				//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
				query1=session.createQuery(qyery).setParameter("userId",userId);
			}
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of User "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise");
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
				System.out.println("Exception in AdminUserActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise ");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final void activateAdminUser(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedAdminUserList=new ArrayList();
		ArrayList deactivatedAdminUserList=new ArrayList();
		String status="deactive";
		String adminUserMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<userIds.length;i++)
			{
				status="active";
				String  userId=userIds[i];
				Query query1=session.createQuery("select au.userId from AdminUserFormBean au where convert(varchar,au.userId)=:userId") .setParameter("userId",userId);
				int _userId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_userId=(Integer)row;
				}
				txn=session.beginTransaction();
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_userId);
				if(loginInfo != null)
				{	
					loginInfo.setLoginStatus("Activate");		 
					session.update(loginInfo);			  
					txn.commit();
					status="active";
				}
				if(status.equalsIgnoreCase("active"))
				{
					activatedAdminUserList.add(userId);			 
				}
				else
				{
					status="deactive";
					deactivatedAdminUserList.add(userId);
				}
			}
			appSession.put("activatedAdminUserList",activatedAdminUserList);
			appSession.put("deactivatedAdminUserList",deactivatedAdminUserList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,activateAdminUser");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try
			{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,activateAdminUser");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AdminUserActivationDao,activateAdminUser");
				System.out.println(e.toString());
			}
		}
	}
	
	final void deactivateAdminUser(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedAdminUserList=new ArrayList();
		ArrayList deactivatedAdminUserList=new ArrayList();
		String status="active";
		String adminUserMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<userIds.length;i++)
			{
				status="active";
				String userId=userIds[i];
				Query query1=session.createQuery("select au.userId from AdminUserFormBean au where convert(varchar,au.userId)=:userId") .setParameter("userId",userId);
				int _userId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_userId=(Integer)row;
				}
				txn=session.beginTransaction();
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_userId);
				if(loginInfo != null)
				{	
					loginInfo.setLoginStatus("Deactive");		 
					session.update(loginInfo);
					txn.commit();
					status="deactive";
				}		 
				if(status.equalsIgnoreCase("deactive"))
				{
					deactivatedAdminUserList.add(userId);			 
				}
				else
				{
					status="active";
					activatedAdminUserList.add(userId);
				}
			}
			appSession.put("activatedAdminUserList",activatedAdminUserList);
			appSession.put("deactivatedAdminUserList",deactivatedAdminUserList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,deactivateAdminUser");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,deactivateAdminUser");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AdminUserActivationDao,deactivateAdminUser");
				System.out.println(e.toString());
			}
		}
	}
	
	final void blockAdminUser(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedAdminUserList=new ArrayList();
		ArrayList unblockedAdminUserList=new ArrayList();
		String status="";
		String adminUserMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<userIds.length;i++)
			{
				status="";
				String userId=userIds[i];
				Query query1=session.createQuery("select au.userId from AdminUserFormBean au where convert(varchar,au.userId)=:userId").setParameter("userId",userId);
				int _userId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_userId=(Integer)row;
				}
				txn=session.beginTransaction();
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_userId);
				if(loginInfo != null)
				{	
					//loginInfo.setBlockStatus("blocked");
					// Updated By Manoj
					loginInfo.setBlockStatus("Blocked");
					session.update(loginInfo);
					txn.commit();
					status="blocked";
				}
				if(status.equalsIgnoreCase("blocked"))
				{
					
					blockedAdminUserList.add(userId);			 
				}
				else
				{
					status="";
					unblockedAdminUserList.add(userId);
				}
			}
			appSession.put("blockedAdminUserList",blockedAdminUserList);
			appSession.put("unblockedAdminUserList",unblockedAdminUserList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex)
			{
			}
			System.out.println("Exception in AdminUserActivationDao,blockAdminUser");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try{
				if(txn!= null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in AdminUserActivationDao,blockAdminUser");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AdminUserActivationDao,blockAdminUser");
				System.out.println(e.toString());
			}
		}
	}
	
	final void unblockAdminUser(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedAdminUserList=new ArrayList();
		ArrayList unblockedAdminUserList=new ArrayList();
		String status="";
		String adminUserMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<userIds.length;i++)
			{
				status="";
				String userId=userIds[i];
				Query query1=session.createQuery("select au.userId from AdminUserFormBean au where convert(varchar,au.userId)=:userId").setParameter("userId",userId);
				int _userId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_userId=(Integer)row;
				}
				txn=session.beginTransaction();
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_userId);
				if(loginInfo != null)
				{	
					//loginInfo.setBlockStatus("unblocked");
					//Updated By Manoj
					loginInfo.setBlockStatus("Unblocked");
					session.update(loginInfo);	  
					txn.commit();
					status="unblocked";
				}
				if(status.equalsIgnoreCase("unblocked")){
					
					unblockedAdminUserList.add(userId);			 
				}
				else{
					status="";
					blockedAdminUserList.add(userId);
				}
			}
			appSession.put("blockedAdminUserList",blockedAdminUserList);
			appSession.put("unblockedAdminUserList",unblockedAdminUserList);
		}
		catch(HibernateException e){
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,unblockAdminUser");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AdminUserActivationDao,unblockAdminUser");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AdminUserActivationDao,unblockAdminUser");
				System.out.println(e.toString());
			}
		}
	}
	public static String dateConvert (Object inDate)
    {
        try 
        {
        	DateFormat formatter ; 
        	Date date ; 
        	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	date = (Date)formatter.parse((String) inDate);
        	formatter = new SimpleDateFormat("dd-MM-yyyy");
        	String outDate = formatter.format(date);
        	return outDate;

        } catch (ParseException e)
        {
        	System.out.println("Exception in AdminUserActivationDao,dataComvert"); 
        	System.out.println(e.toString());
        }  
        return null;
    }
}