package com.controlPanel.userActivation.retailsUser.mds;

/*	updated By   	: Manoj
 * 	updated Matter 	: Keyword change Blocked ,Unblocked and sms send disabled (Add some field to display)
 * 	updatde date    : 16 Oct 2012,12/11/2012
 */

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
import com.common.SendSmsOnMobile;
import com.formBean.mds.MdsLoginInfoFormBean;

 class MdsActivationDao
{
	final ArrayList getPortalIdList()
	{
		Session session=null;         
		ArrayList portalIdList=new ArrayList();
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.clientId from DynamicDetailsFormBean mf order by mf.clientId");
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalIdList.add(row);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getPortalIdList");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getPortalIdList");
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
				System.out.println("Exception in MdsActivationDao,getPortalIdList");
				System.out.println(e.toString());
			}
		}
		return portalIdList; 
	}
	
	final ArrayList getAllMdsDetail(int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId order by mf. mdId";
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getAllMdsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getAllMdsDetail");
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
				System.out.println("Exception in MdsActivationDao,getAllMdsDetail");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsStatusWise(String status,int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mlf.loginStatus=:status order by mf.mdId";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
				
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsBlockStatusWise(String status,int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mlf.blockStatus=:status order by mf.mdId";
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
			
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsBlockStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsMdIdWise(String _mdId,int pageNumber,String portalId)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
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
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and (mf.mdInitial+convert(varchar,mf.mdId))=:mdId and wf.clientId=:portalId order by mf.mdId";
				query1=session.createQuery(qyery).setParameter("mdId",_mdId).setParameter("portalId",_portalId);
			}
			else
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and (mf.mdInitial+convert(varchar,mf.mdId))=:mdId order by mf.mdId";
				query1=session.createQuery(qyery).setParameter("mdId",_mdId);
			}
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
			
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsMdIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsMdIdWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsMdIdWise");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsPortalWise(String portalId,int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId order by mf.mdId";
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 

				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsPortalWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsPortalWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsPortalWise");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsOfPortalIdStatusWise(String portalId,String status,int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and mlf.loginStatus=:status order by mf.mdId";
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
			
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdStatusWise");
				System.out.println(e.toString());	
			}
		}
		return mdsDetailsList; 
	}
	
	final ArrayList getMdsDetailsOfPortalIdBlockStatusWise(String portalId,String status,int pageNumber)
	{
		Session session=null;         
		ArrayList mdsDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf. mdId,mf.companyName,mf.name,mlf.loginStatus,maf.totalBalanceAmount,mf.mobileNo,mf.emailId,mlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,maf.totalCash,maf.usedCash,maf.cutOffAmount from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and mlf.blockStatus=:status order by mf.mdId";
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap mdsMap=new HashMap();
				mdsMap.put("portalId",row[0]);
				mdsMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				mdsMap.put("mdId",mdId);
				mdsMap.put("companyName",row[4]);
				mdsMap.put("mdsName",row[5]);
				mdsMap.put("loginStatus",row[6]);
				
				mdsMap.put("mdsMobileNo",row[8]);
				mdsMap.put("mdsEmailId",row[9]);
				mdsMap.put("mdsBlockStatus",row[10]);
				// Code Added By Manoj for adding some information 
			
				mdsMap.put("domaiName",row[11]);
				mdsMap.put("clientFlag",row[12]);
				mdsMap.put("categoryName",row[13]);
				mdsMap.put("wlBlockStatus",row[14]);
				mdsMap.put("agentCellEmailId",row[15]);
				mdsMap.put("helpMobileNo",row[16]);
				mdsMap.put("WLstatus",row[17]);
				dateofwlreg=dateConvert(row[18]);
				mdsMap.put("wlRegistrationDate",dateofwlreg);
				mdsMap.put("mdPortalID",row[19]);
				double totcash=(Double)row[20];
				double usedCash=(Double)row[21];
				double cutoffAmount=(Double)row[22];
				double mdBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				mdsMap.put("mdsBalance",f.format(mdBalance));
				mdsDetailsList.add(mdsMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdBlockStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getMdsDetailsOfPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return mdsDetailsList; 
	}
	
	final int getNoOfRecordsAllMdsDetails()
	{
		Session session=null;         
		long count=0;
		int check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId";
			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetails");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetails");
				System.out.println(e.toString());
			}
		}
		return check; 
	}
	
	final int getNoOfRecordsAllMdsDetailsStatusWise(String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mlf.loginStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllMdsDetailsBlockStatusWise(String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId  and mlf.blockStatus=:status";
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsBlockStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllMdsDetailsPortalIdWise(String portalId)
	{
		Session session=null;         
		int count=0;
		long check=0;
		//System.out.println("getNoOfRecordsAllMdsDetailsPortalIdWise porta l i d is>>"+portalId);
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mf.clientId=:portalId";
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllMdsDetailsPortalIdStatusWise(String portalId,String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mf.clientId=:portalId and mlf.loginStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise(String portalId,String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId and mf.clientId=:portalId and mlf.blockStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final int getNoOfRecordsAllMdsDetailsMdIdWise(String _mdId,String portalId)
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
				qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId  and (mf.mdInitial+convert(varchar,mf.mdId))=:mdId and mf.clientId=:portalId";
				
				query1=session.createQuery(qyery).setParameter("mdId",_mdId).setParameter("portalId",_portalId);
			}
			else
			{
				qyery="select count(mf.mdId) from MdsDetailsFormBean mf,MdsAmountFormBean maf,DynamicDetailsFormBean wf,MdsLoginInfoFormBean mlf where mlf.userId=mf.mdId and mf.mdId=maf.mdId and mf.clientId=wf.clientId  and (mf.mdInitial+convert(varchar,mf.mdId))=:mdId";
				
				query1=session.createQuery(qyery).setParameter("mdId",_mdId);
			}
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsMdIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsMdIdWise");
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
				System.out.println("Exception in MdsActivationDao,getNoOfRecordsAllMdsDetailsMdIdWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	final void activateMds(String [] mdIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedMdsList=new ArrayList();
		ArrayList deactivatedMdsList=new ArrayList();
		String status="deactive";
		String mdsMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<mdIds.length;i++)
			{
				status="active";
				String  mdId=mdIds[i];
				Query query1=session.createQuery("select mf.mdId from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId") .setParameter("mdId",mdId);
				int _mdId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_mdId=(Integer)row;
				}
				txn=session.beginTransaction();
				MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
				if(loginInfo != null)
				{	
					loginInfo.setLoginStatus("Activate");		 
					session.update(loginInfo);			  
					txn.commit();
					status="active";
					
				}
				activatedMdsList.add(mdId);	
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("active")){
//					Query queryMobileNo=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf. mdId))=:mdId") .setParameter("mdId",mdId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						mdsMobileNo=(String)row;
//			   }
//					String activateMessage="Dear Master Distributor, Your MDS ID "+ mdId+" has been activated.";
//					//sendsms.sendMobileSmsSMSZONE(mdsMobileNo,activateMessage);
//					//sendsms.sendMobileSmsACL(mdsMobileNo,activateMessage);
//					activatedMdsList.add(mdId);			 
//				}
//				else{
//					status="deactive";
//					deactivatedMdsList.add(mdId);
//				}
//				System.out.println("mdsMobileNo======================"+mdsMobileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("activatedMdsList",activatedMdsList);
			appSession.put("deactivatedMdsList",deactivatedMdsList);
//			System.out.println("activatedMdsList======================"+activatedMdsList);
//			System.out.println("deactivatedMdsList is============"+deactivatedMdsList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in MdsActivationDao,activateMds");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in MdsActivationDao,activateMds");
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
				System.out.println("Exception in MdsActivationDao,activateMds ");
				System.out.println(e.toString());
			}
		}
	}
	
	final void deactivateMds(String [] mdIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedMdsList=new ArrayList();
		ArrayList deactivatedMdsList=new ArrayList();
		String status="active";
		String mdsMobileNoileNo="";
		Connection con=null;
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<mdIds.length;i++)
			{
				status="active";
				String mdId=mdIds[i];
				Query query1=session.createQuery("select mf.mdId from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId") .setParameter("mdId",mdId);
				int _mdId=0;
				
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_mdId=(Integer)row;
				}
				
				txn=session.beginTransaction();
				MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
				if(loginInfo != null)
				{	
					loginInfo.setLoginStatus("Deactive");		 
					session.update(loginInfo);
					txn.commit();
					status="deactive";
				}
				
				deactivatedMdsList.add(mdId);
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("deactive")){
//					Query queryMobileNo=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId").setParameter("mdId",mdId);
//		   
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						mdsMobileNoileNo=(String)row;
//					}
//					String activateMessage="Dear Master Distributor, Your MDS ID "+mdId+" has been deactivated.";
//					// sendsms.sendMobileSmsSMSZONE(mdsMobileNoileNo,activateMessage);
//					// sendsms.sendMobileSmsACL(mdsMobileNoileNo,activateMessage);
//					deactivatedMdsList.add(mdId);			 
//				}
//				else{
//					status="active";
//					activatedMdsList.add(mdId);
//				}
//				System.out.println("mdsMobileNo======================"+mdsMobileNoileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("activatedMdsList",activatedMdsList);
			appSession.put("deactivatedMdsList",deactivatedMdsList);
//			System.out.println("activatedMdsList======================"+activatedMdsList);
//			System.out.println("deactivatedMdsList is============"+deactivatedMdsList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in MdsActivationDao,deactivateMds");
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
			System.out.println("Exception in MdsActivationDao,deactivateMds");
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
				System.out.println("Exception in MdsActivationDao,deactivateMds");
				System.out.println(e.toString());
			}
		}
	}
	
	final void partiallyActivateMds(String [] mdIds,String validUpto,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList pactivatedMdsList=new ArrayList();
		ArrayList inactivatedMdsList=new ArrayList();
		String status="inactive";
		String mdsMobileNoileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<mdIds.length;i++)
			{
				status="inactive";
				String mdId=mdIds[i];
				Query query1=session.createQuery("select mf.mdId from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId") .setParameter("mdId",mdId);
				int _mdId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_mdId=(Integer)row;
				}
				
				txn=session.beginTransaction();
				MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
				if(loginInfo != null)
				{	
					loginInfo.setLoginStatus("PartiallyActive");
					loginInfo.setValidUpto(validUpto);			  
					session.update(loginInfo);
					txn.commit();
					status="PA";
				}
				
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("PA"))
				{
					Query queryMobileNo=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId").setParameter("mdId",mdId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();)
					{
						Object row = (Object) it.next();
						mdsMobileNoileNo=(String)row;
					}
					String activateMessage="Dear Master Distributor, Your MDS ID "+mdId+" has been partially activated and it is valid upto"+validUpto+".";
					//	  sendsms.sendMobileSmsSMSZONE(mdsMobileNoileNo,activateMessage);
					//  sendsms.sendMobileSmsACL(mdsMobileNoileNo,activateMessage);
					pactivatedMdsList.add(mdId);			 
				}
				else
				{
					status="inactive";
					inactivatedMdsList.add(mdId);
				}
//				System.out.println("mdsMobileNo======================"+mdsMobileNoileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("pactivatedMdsList",pactivatedMdsList);
			appSession.put("inactivatedMdsList",inactivatedMdsList);
//			System.out.println("activatedMdsList======================"+pactivatedMdsList);
//			System.out.println("deactivatedMdsList is============"+inactivatedMdsList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex)
			{
			}
			System.out.println("Exception in MdsActivationDao,partiallyActivateMds");
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
			 System.out.println("Exception in MdsActivationDao,partiallyActivateMds");
			 System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in MdsActivationDao,partiallyActivateMds");
				System.out.println(e.toString());
			}
		}
	}
	
	final void blockMds(String [] mdIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedMdsList=new ArrayList();
		ArrayList unblockedMdsList=new ArrayList();
		String status="";
		String mdsMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<mdIds.length;i++)
			{
				status="";
				String mdId=mdIds[i];
				Query query1=session.createQuery("select mf.mdId from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId").setParameter("mdId",mdId);
				int _mdId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_mdId=(Integer)row;
				}
				txn=session.beginTransaction();
				MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
				if(loginInfo != null)
				{	
					//  loginInfo.setBlockStatus("blocked");
					//Updated By Manoj
					loginInfo.setBlockStatus("Blocked");
					session.update(loginInfo);
					txn.commit();
					status="blocked";
				}
				blockedMdsList.add(mdId);
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("blocked")){
//					Query queryMobileNo=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId").setParameter("mdId",mdId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						mdsMobileNo=(String)row;
//					}
//					System.out.println("mdsMobileNo"+mdsMobileNo);
//					String activateMessage="Dear Master Distributor, Your MDS ID "+mdId+" has been blocked";
//					//	  sendsms.sendMobileSmsSMSZONE(mdsMobileNo,activateMessage);
//					//	  sendsms.sendMobileSmsACL(mdsMobileNo,activateMessage);
//					blockedMdsList.add(mdId);			 
//				}
//				else{
//					status="";
//					unblockedMdsList.add(mdId);
//				}
//				System.out.println("mdsMobileNo======================"+mdsMobileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("blockedMdsList",blockedMdsList);
			appSession.put("unblockedMdsList",unblockedMdsList);
//			System.out.println("blockedMdsList======================"+blockedMdsList);
//			System.out.println("unblockedMdsList is============"+unblockedMdsList);
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
			System.out.println("Exception in MdsActivationDao,blockMds");
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
			System.out.println("Exception in MdsActivationDao,blockMds");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in MdsActivationDao,blockMds");
				System.out.println(e.toString());
			}
		}
	}
	
	final void unblockMds(String [] mdIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedMdsList=new ArrayList();
		ArrayList unblockedMdsList=new ArrayList();
		String status="";
		String mdsMobileNo="";
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			 con=session.connection();
			for(int i=0;i<mdIds.length;i++)
			{
				status="";
				String mdId=mdIds[i];
				Query query1=session.createQuery("select mf.mdId from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId").setParameter("mdId",mdId);
				int _mdId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_mdId=(Integer)row;
				}
				txn=session.beginTransaction();
				MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
				if(loginInfo != null)
				{	
					//loginInfo.setBlockStatus("unblocked");
					// Updated By Manoj
					loginInfo.setBlockStatus("Unblocked");
					session.update(loginInfo);	  
					txn.commit();
					status="unblocked";
				}
				unblockedMdsList.add(mdId);		
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("unblocked")){
//					Query queryMobileNo=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where (mf.mdInitial+convert(varchar,mf.mdId))=:mdId") .setParameter("mdId",mdId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						System.out.println("inside >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//						mdsMobileNo=(String)row;
//					}
//					System.out.println("inside >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+mdsMobileNo);
//					String activateMessage="Dear Master Distributor, Your MDS ID "+mdId+" has been unblocked";
//					// sendsms.sendMobileSmsSMSZONE(mdsMobileNo,activateMessage);
//					// sendsms.sendMobileSmsACL(mdsMobileNo,activateMessage);
//					unblockedMdsList.add(mdId);			 
//				}
//				else{
//					status="";
//					blockedMdsList.add(mdId);
//				}
			}
			appSession.put("blockedMdsList",blockedMdsList);
			appSession.put("unblockedMdsList",unblockedMdsList);
//			System.out.println("blockedMdsList======================"+blockedMdsList);
//			System.out.println("unblockedMdsList is============"+unblockedMdsList);
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in MdsActivationDao,unblockMds");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in MdsActivationDao,MdsActivationDao,unblockMds");
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
				System.out.println("Exception in MdsActivationDao,unblockMds");
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
        	date = (Date)formatter.parse(inDate.toString());
        	formatter = new SimpleDateFormat("dd-MM-yyyy");
        	String outDate = formatter.format(date);
        	return outDate;

        } catch (ParseException e)
        {
        	System.out.println("Exception in MdsActivationDao");
        	System.out.println(e.toString());
        }  
        return null;
    }
}