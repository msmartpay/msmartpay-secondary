package com.controlPanel.userActivation.retailsUser.distributor;

/*	updated By       : Manoj
 *	updated matter   : Keyword change Blocked, Unblocked and Sms send disable 
 * 	updated Date     : 16 oct 2012
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
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.SendSmsOnMobile;
import com.formBean.distributor.DistributorDetailsFormBean;
import com.formBean.distributor.DistributorLoginInfoFormBean;

/**
* Updated By :Arvind
* Updated Date : 10 Jun 2013
* Updated Matter : Format of class.
*/

 class DistributorActivationDao
{
	 public List<DistributorDetailsFormBean> downloadDS(String loginType,String portalId) {

			Session session=null;         
			String hql="";
			List<DistributorDetailsFormBean> dsList=null;
			try{
				Query query1=null;
				session = HibernateSession.getSessionFactory().openSession();
				if(!loginType.equalsIgnoreCase("SuperAdmin"))
				{
					
					hql="select new DistributorDetailsFormBean( a.distributorId, a.distributorInitial, a.mdId, a.firstName, a.lastName," + 
							"a.companyName, a.addressLine1, a.addressLine2, a.pincode, a.city," + 
							"a.district, a.state, a.country, a.emailId, a.mobileNo, a.dob, a.gender," + 
							"a.companyType, a.panNo, a.dateOfJoining) from DistributorDetailsFormBean a where a.clientID=:clientID";
					query1=session.createQuery(hql).setParameter("clientID",portalId);
					
				}else {
					hql="select new DistributorDetailsFormBean( a.distributorId, a.distributorInitial, a.mdId, a.firstName, a.lastName," + 
							"a.companyName, a.addressLine1, a.addressLine2, a.pincode, a.city," + 
							"a.district, a.state, a.country, a.emailId, a.mobileNo, a.dob, a.gender," + 
							"a.companyType, a.panNo, a.dateOfJoining) from DistributorDetailsFormBean a ";
					query1=session.createQuery(hql);
					
				}
				dsList=query1.list();
				
			}
			catch(HibernateException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
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
					System.out.println("Exception in AgentActivationDao,getPortalFlag");
					System.out.println(e.toString());
				}
			}
			return dsList; 
		
		}
	final ArrayList getPortalIdList(){
		Session session=null;         
		ArrayList portalIdList=new ArrayList();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select df.clientId from DynamicDetailsFormBean df order by df.clientId");
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				portalIdList.add(row);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getPortalIdList");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getPortalIdList");
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
				System.out.println("Exception in DistributorActivationDao,getPortalIdList");
				System.out.println(e.toString());
			}
		}
		return portalIdList; 
	}
	
	final ArrayList getAllDistributorsDetail(int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsStatusWise(String status,int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and dlf.loginStatus=:status order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsBlockStatusWise(String status,int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and dlf.blockStatus=:status order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsDistributorIdWise(String _distributorId,int pageNumber,String portalId){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			Query query1=null;
			if(!portalId.equalsIgnoreCase("all"))
			{
				int _portalId=Integer.parseInt(portalId);
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId and wf.clientId=:portalId order by df.dateOfJoining desc";
				
				query1=session.createQuery(qyery).setParameter("distributorId",_distributorId).setParameter("portalId",_portalId);
			}
			else{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId  order by df.dateOfJoining desc";
				
				query1=session.createQuery(qyery).setParameter("distributorId",_distributorId);
			}
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsPortalWise(String portalId,int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsOfPortalIdStatusWise(String portalId,String status,int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and dlf.loginStatus=:status order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdStatusWise");
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
				System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final ArrayList getDistributorDetailsOfPortalIdBlockStatusWise(String portalId,String status,int pageNumber){
		Session session=null;         
		ArrayList distributorDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,df.companyName,df.name,dlf.loginStatus,daf.totalCash,daf.usedCash,df.mobileNo,df.emailId,dlf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,wf.registrationDate,mf.portalID,daf.cutOffAmount from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and dlf.blockStatus=:status order by df.dateOfJoining desc";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap distributorMap=new HashMap();
				distributorMap.put("portalId",row[0]);
				distributorMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				distributorMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				distributorMap.put("distributorId",distributorId);			 
				distributorMap.put("companyName",row[6]);
				distributorMap.put("distributorName",row[7]);
				distributorMap.put("loginStatus",row[8]);
				double totCash=(Double)row[9];			  
				double usedCash=(Double)row[10];
				double cutoffAmount=(Double)row[23];
				
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				distributorMap.put("distributorBalance",f.format(balance));
				distributorMap.put("distributorMobileNo",row[11]);
				distributorMap.put("distributorEmailId",row[12]);
				distributorMap.put("distributorBlockStatus",row[13]);
				//Code Added By Manoj Date is 22/11/2012
				distributorMap.put("domaiName",row[14]);
				distributorMap.put("clientFlag",row[15]);
				distributorMap.put("categoryName",row[16]);
				distributorMap.put("wlBlockStatus",row[17]);
				distributorMap.put("agentCellEmailId",row[18]);
				distributorMap.put("helpMobileNo",row[19]);
				distributorMap.put("WLstatus",row[20]);
				dateofwlreg=dateConvert(row[21]);
				distributorMap.put("wlRegistrationDate",dateofwlreg);
				distributorMap.put("mdPortalID",row[22]);
				distributorDetailsList.add(distributorMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdBlockStatusWise");
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
				System.out.println("Exception in DistributorActivationDao,getDistributorDetailsOfPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return distributorDetailsList; 
	}
	final int getNoOfRecordsAllDistributorDetails(){
		Session session=null;         
		long count=0;
		int check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId";
			
			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
			if(check>1000){
				check=1000;
			}else{
				check=check;
			}
			//System.out.println("Total No of DS is :"+check);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		
		return check; 
	}
	final int getNoOfRecordsAllDistributorDetailsStatusWise(String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and dlf.loginStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllDistributorDetailsBlockStatusWise(String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId  and dlf.blockStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getDistributorDetailsBlockStatusWise");
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
				System.out.println("Exception in DistributorActivationDao,getDistributorDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllDistributorDetailsPortalIdWise(String portalId){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetails");
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
				System.out.println("Exception in DistributorActivationDao,getAllDistributorsDetail");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllDistributorDetailsPortalIdStatusWise(String portalId,String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and dlf.loginStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdStatusWise");
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
				System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise(String portalId,String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and dlf.blockStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise");
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
				System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllDistributorDetailsDistributorIdWise(String _distributorId,String portalId){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			Query query1=null;
			if(!portalId.equalsIgnoreCase("all")){
				int _portalId=Integer.parseInt(portalId);
				qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId and wf.clientId=:portalId";
				
				query1=session.createQuery(qyery).setParameter("distributorId",_distributorId).setParameter("portalId",_portalId);
			}
			else{
				qyery="select count(df.distributorId) from DistributorDetailsFormBean df,MdsDetailsFormBean mf,DistributorAmountFormBean daf,DynamicDetailsFormBean wf,DistributorLoginInfoFormBean dlf where dlf.userId=df.distributorId and df.distributorId=daf.distributorId  and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId";
				
				query1=session.createQuery(qyery).setParameter("distributorId",_distributorId);
			}
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>1000){
				count=1000;
			}else{
				count=count;
			}
			//System.out.println("Total No of DS is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsdistributorIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsdistributorIdWise");
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
				System.out.println("Exception in DistributorActivationDao,getNoOfRecordsAllDistributorDetailsdistributorIdWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final void activateDistributors(String [] distributorIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedDistributorsList=new ArrayList();
		ArrayList deactivatedDistributorList=new ArrayList();
		String status="deactive";
		String distributorMobileNo="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<distributorIds.length;i++)
			{
				status="active";
				String distributorId=distributorIds[i];
				Query query1=session.createQuery("select df.distributorId from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
				int _distributorId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_distributorId=(Integer)row;
				}
				txn=session.beginTransaction();
				
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
				if(loginInfo != null){	
					loginInfo.setLoginStatus("Activate");		 
					session.update(loginInfo);
					txn.commit();
					status="active";
				}
				activatedDistributorsList.add(_distributorId);
			}
			
			appSession.put("activatedDistributorsList",activatedDistributorsList);
			appSession.put("deactivatedDistributorList",deactivatedDistributorList);
//			System.out.println("activatedDistributorsList======================"+activatedDistributorsList);
//			System.out.println("deactivatedDistributorList is============"+deactivatedDistributorList);
		}
		catch(HibernateException e){
			try{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,activateDistributors");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,activateDistributors");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorActivationDao,activateDistributors");
				System.out.println(e.toString());
			}
		}
	}
	final void deactivateDistributors(String [] distributorIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedDistributorsList=new ArrayList();
		ArrayList deactivatedDistributorList=new ArrayList();
		String status="active";
		String DistributorMobileNo="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<distributorIds.length;i++)
			{
				status="active";
				String distributorId=distributorIds[i];
				Query query1=session.createQuery("select df.distributorId from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
				int _distributorId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_distributorId=(Integer)row;
				}
				txn=session.beginTransaction();
				
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
				if(loginInfo != null){	
					loginInfo.setLoginStatus("Deactive");		 
					session.update(loginInfo);
					txn.commit();
					status="deactive";
				}
				deactivatedDistributorList.add(distributorId);	
//				if(clientFlag.equals("1") || clientFlag.equals("2")){
//					DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
//					if(loginInfo != null){	
//						loginInfo.setLoginStatus("Deactive");		 
//						session.update(loginInfo);
//						txn.commit();
//						status="deactive";
//					}
//				}
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("deactive")){
//					Query queryMobileNo=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						DistributorMobileNo=(String)row;
//					}
//					String activateMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been deactivated.";
//					// sendsms.sendMobileSmsSMSZONE(DistributorMobileNo,activateMessage);
//					// sendsms.sendMobileSmsACL(DistributorMobileNo,activateMessage);
//					deactivatedDistributorList.add(distributorId);			 
//				}
//				else{
//					status="active";
//					activatedDistributorsList.add(distributorId);
//				}
//				System.out.println("DistributorMob======================"+DistributorMobileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("activatedDistributorsList",activatedDistributorsList);
			appSession.put("deactivatedDistributorList",deactivatedDistributorList);
//			System.out.println("activatedDistributorsList======================"+activatedDistributorsList);
//			System.out.println("deactivatedDistributorList is============"+deactivatedDistributorList);
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,deactivateDistributors");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,deactivateDistributors");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorActivationDao,deactivateDistributors");
				System.out.println(e.toString());
			}
		}
	 }
	final void partiallyActivateDistributors(String [] distributorIds,String clientFlag,String validUpto,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList pactivatedDistributorsList=new ArrayList();
		ArrayList inactivatedDistributorList=new ArrayList();
		String status="inactive";
		String DistributorMobileNo="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<distributorIds.length;i++)
			{
				status="inactive";
				String distributorId=distributorIds[i];
				Query query1=session.createQuery("select df.distributorId from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
				int _distributorId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_distributorId=(Integer)row;
				}
				txn=session.beginTransaction();
				if(clientFlag.equals("0"))
				{
					DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
					if(loginInfo != null){	
						loginInfo.setLoginStatus("PartiallyActive");
						loginInfo.setValidUpto(validUpto);
						session.update(loginInfo);
						txn.commit();
						status="PA";
					}
				}
				if(clientFlag.equals("1") || clientFlag.equals("2"))
				{
					DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
					if(loginInfo != null)
					{	
						loginInfo.setLoginStatus("PartiallyActive");
						loginInfo.setValidUpto(validUpto);
						session.update(loginInfo);
						txn.commit();
						status="PA";
					}
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("PA")){
					Query queryMobileNo=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();)
					{
						Object row = (Object) it.next();
						DistributorMobileNo=(String)row;
					}
					String activateMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been partially activated and it is valid upto"+validUpto+".";
					//  sendsms.sendMobileSmsSMSZONE(DistributorMobileNo,activateMessage);
					//  sendsms.sendMobileSmsACL(DistributorMobileNo,activateMessage);
					pactivatedDistributorsList.add(distributorId);			 
				}
				else{
					status="inactive";
					inactivatedDistributorList.add(distributorId);
				}
				
			}
			appSession.put("pactivatedDistributorsList",pactivatedDistributorsList);
			appSession.put("inactivatedDistributorList",inactivatedDistributorList);
			
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,partiallyActivateDistributors");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,partiallyActivateDistributors");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorActivationDao,partiallyActivateDistributors");
				System.out.println(e.toString());
			}
		}
	}
	final void blockDistributors(String [] distributorIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedDistributorsList=new ArrayList();
		ArrayList unblockedDistributorList=new ArrayList();
		String status="";
		String distributorMobileNo="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<distributorIds.length;i++)
			{
				status="";
				String distributorId=distributorIds[i];
				Query query1=session.createQuery("select df.distributorId from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId").setParameter("distributorId",distributorId);
				int _distributorId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_distributorId=(Integer)row;
				}
				txn=session.beginTransaction();
				
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
				if(loginInfo != null){	
					//loginInfo.setBlockStatus("blocked");
					//Updated By Manoj
					loginInfo.setBlockStatus("Blocked");
					session.update(loginInfo);
					txn.commit();
					status="blocked";
				}
				blockedDistributorsList.add(distributorId);	
				
//				if(clientFlag.equals("1") || clientFlag.equals("2")){
//					DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
//					if(loginInfo != null){	
//						//loginInfo.setBlockStatus("blocked");
//						//Upadted By Manoj
//						loginInfo.setBlockStatus("Blocked");
//						session.update(loginInfo);
//						txn.commit();
//						status="blocked";
//					}
//				}
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("blocked")){
//					Query queryMobileNo=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						distributorMobileNo=(String)row;
//					}
//					System.out.println("distributorMobileNo"+distributorMobileNo);
//					String activateMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been blocked";
//					// sendsms.sendMobileSmsSMSZONE(distributorMobileNo,activateMessage);
//					// sendsms.sendMobileSmsACL(distributorMobileNo,activateMessage);
//					blockedDistributorsList.add(distributorId);			 
//				}
//				else{
//					status="";
//					unblockedDistributorList.add(distributorId);
//				}
//				System.out.println("distributorMobileNo======================"+distributorMobileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("blockedDistributorsList",blockedDistributorsList);
			appSession.put("unblockedDistributorList",unblockedDistributorList);
//			System.out.println("blockedDistributorsList======================"+blockedDistributorsList);
//			System.out.println("unblockedDistributorList is============"+unblockedDistributorList);
		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,blockDistributors");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,blockDistributors");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorActivationDao,blockDistributors");
				System.out.println(e.toString());
			}
		}
	}
	final void unblockDistributors(String [] distributorIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedDistributorsList=new ArrayList();
		ArrayList unblockedDistributorsList=new ArrayList();
		String status="";
		String distributorMobileNo="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<distributorIds.length;i++)
			{
				status="";
				String distributorId=distributorIds[i];
				Query query1=session.createQuery("select df.distributorId from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId").setParameter("distributorId",distributorId);
				int _distributorId=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					_distributorId=(Integer)row;
				}
				txn=session.beginTransaction();
				
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
				if(loginInfo != null){	
					//loginInfo.setBlockStatus("unblocked");
					//updated By Manoj
					loginInfo.setBlockStatus("Unblocked");
					session.update(loginInfo);
					txn.commit();
					status="unblocked";
				}
				unblockedDistributorsList.add(distributorId);	
//				if(clientFlag.equals("1") || clientFlag.equals("2")){
//					DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
//					if(loginInfo != null){	
//						//loginInfo.setBlockStatus("unblocked");
//						//updated By Manoj
//						loginInfo.setBlockStatus("Unblocked");
//						session.update(loginInfo);
//						txn.commit();
//						status="unblocked";
//					}
//				}
//				SendSmsOnMobile sendsms=new SendSmsOnMobile();
//				if(status.equalsIgnoreCase("unblocked")){
//					Query queryMobileNo=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where (df.distributorInitial+convert(varchar,df.distributorId))=:distributorId") .setParameter("distributorId",distributorId);
//					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
//						Object row = (Object) it.next();
//						System.out.println("inside >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//						distributorMobileNo=(String)row;
//					}
//					System.out.println("inside >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+distributorMobileNo);
//					String activateMessage="Dear Distributor, Your Distributor ID "+distributorId+" has been unblocked";
//					//	  sendsms.sendMobileSmsSMSZONE(distributorMobileNo,activateMessage);
//					//  sendsms.sendMobileSmsACL(distributorMobileNo,activateMessage);
//					unblockedDistributorsList.add(distributorId);			 
//				}
//				else{
//					status="";
//					blockedDistributorsList.add(distributorId);
//				}
//				System.out.println("DistributorMob======================"+distributorMobileNo);
//				System.out.println("status is============"+status);
			}
			appSession.put("blockedDistributorsList",blockedDistributorsList);
			appSession.put("unblockedDistributorList",unblockedDistributorsList);
//			System.out.println("blockedDistributorsList======================"+blockedDistributorsList);
//			System.out.println("unblockedDistributorList is============"+unblockedDistributorsList);
		}
		catch(HibernateException e){
			try{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,unblockDistributors");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
        	         if(txn!= null)
        	        	 txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorActivationDao,unblockDistributors");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorActivationDao,unblockDistributors");
				System.out.println(e.toString());
			}
		}
	}
	
	public static String dateConvert (Object inDate)
	{
		try {
			DateFormat formatter ; 
        	Date date ; 
        	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	date = (Date)formatter.parse((String) inDate);
        	formatter = new SimpleDateFormat("dd-MM-yyyy");
        	String outDate = formatter.format(date);
        	return outDate;
        	
        } catch (ParseException e)
        {
        	System.out.println("Exception inDistributorActivationDao dataCovert");
        	System.out.println(e.toString()); }  
        return null;
    }
}