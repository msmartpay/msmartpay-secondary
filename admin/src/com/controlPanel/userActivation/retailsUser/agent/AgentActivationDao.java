package com.controlPanel.userActivation.retailsUser.agent;

/* Updated By     : Manoj
 * Updated Matter : keyword change Blocked and Unblocked, stop SMS on block,unblock
 * updated Date   : 16 Oct 2012
 */


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.formBean.agent.AgentDetailsFormBean;
import com.formBean.agent.AgentLoginInfoFormBean;
import com.formBean.agent.REPAgentLoginInfoFormBean;

class AgentActivationDao
{

	public List<AgentDetailsFormBean> downloadAgent(String loginType,String portalId) {

		Session session=null;         
		String hql="";
		List<AgentDetailsFormBean> agentList=null;
		try{
			Query query1=null;
			session = HibernateSession.getSessionFactory().openSession();
			if(!loginType.equalsIgnoreCase("SuperAdmin"))
			{
				
				hql="select new AgentDetailsFormBean( a.agentId,  a.agentInitial,  a.distId,  a.firstName," + 
						"a.lastName,  a.dateOfJoining,  a.companyName,  a.addressLine1,  a.addressLine2," + 
						"a.pincode,  a.city,  a.district,  a.state,  a.country,  a.emailId,  a.mobileNo," + 
						"a.dob,  a.gender,  a.companyType,  a.panNo,  a.aadhaarNo) from AgentDetailsFormBean a where a.clientID=:clientID";
				query1=session.createQuery(hql).setParameter("clientID",portalId);
				
			}else {
				hql="select new AgentDetailsFormBean( a.agentId,  a.agentInitial,  a.distId,  a.firstName," + 
						"a.lastName,  a.dateOfJoining,  a.companyName,  a.addressLine1,  a.addressLine2," + 
						"a.pincode,  a.city,  a.district,  a.state,  a.country,  a.emailId,  a.mobileNo," + 
						"a.dob,  a.gender,  a.companyType,  a.panNo,  a.aadhaarNo) from AgentDetailsFormBean a ";
				query1=session.createQuery(hql);
				
			}
			agentList=query1.list();
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
		return agentList; 
	
	}

	final String getPortalFlag(String portalId){
		Session session=null;         
		String flag="N";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			Query query1=session.createQuery("select df.clientFlag from DynamicDetailsFormBean df,AdminUserFormBean au where au.portalId=df.clientId and au.portalId=:portalId").setParameter("portalId",_portalId);;
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				flag=(String)row;
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getPortalFlag");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getPortalFlag");
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
				System.out.println("Exception in AgentActivationDao,getPortalFlag");
				System.out.println(e.toString());
			}
		}
		return flag; 
	}

	final ArrayList getPortalIdList()
	{
		Session session=null;         
		ArrayList portalIdList=new ArrayList();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select df.clientId,df.companyName from DynamicDetailsFormBean df order by df.clientId");
			for(Iterator it=query1.iterate();it.hasNext();){
				Object[] row = (Object[]) it.next();		     
				HashMap portalIdMap=new HashMap();
				portalIdMap.put("portalId", row[0]);
				portalIdMap.put("companyName", row[1]); 
				portalIdList.add(portalIdMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getPortalIdList");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getPortalIdList");
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
				System.out.println("Exception in AgentActivationDao,getPortalIdList");
				System.out.println(e.toString());
			}
		}
		return portalIdList; 
	}
	//change in Query By Manoj Add ,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus
	// Change By Manoj and date is 22/11/2012

	final ArrayList getAllAgentsDetail(int pageNumber,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1")){
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId order by af.dateOfJoining desc";
			}
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsStatusWise(String status,int pageNumber,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2")){
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.loginStatus=:status order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1") )
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.loginStatus=:status order by af.dateOfJoining desc";
			}

			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsBlockStatusWise(String status,int pageNumber,String clientFlag){
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.blockStatus=:status order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1")){
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.blockStatus=:status order by af.dateOfJoining desc";
			}

			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsAgentIdWise(String _agentId,int pageNumber,String portalId,String clientFlag){
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String qyery="";
		Query query1=null;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			if(!portalId.equalsIgnoreCase("all"))
			{
				int _portalId=Integer.parseInt(portalId);
				if(clientFlag.equals("0") || clientFlag.equals("2")){
					qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and (af.agentInitial+convert(varchar,af.agentId))=:agentId and wf.clientId=:portalId order by af.dateOfJoining desc";
				}
				if(clientFlag.equals("1")){
					qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and (af.agentInitial+convert(varchar,af.agentId))=:agentId and wf.clientId=:portalId order by af.dateOfJoining desc";
				}
				//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
				query1=session.createQuery(qyery).setParameter("agentId",_agentId).setParameter("portalId",_portalId);
			}
			else{
				if(clientFlag.equals("0") || clientFlag.equals("2")){
					qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and (af.agentInitial+convert(varchar,af.agentId))=:agentId order by af.dateOfJoining desc";
				}
				if(clientFlag.equals("1")){
					qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and (af.agentInitial+convert(varchar,af.agentId))=:agentId order by af.dateOfJoining desc";
				}

				query1=session.createQuery(qyery).setParameter("agentId",_agentId);
			}
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsPortalWise(String portalId,int pageNumber,String clientFlag){
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			int _portalId=Integer.parseInt(portalId);
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId order by af.dateOfJoining desc";
			}
			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsOfPortalIdStatusWise(String portalId,String status,int pageNumber,String clientFlag){
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.loginStatus=:status order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.loginStatus=:status order by af.dateOfJoining desc";
			}

			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdStatusWise");
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
				System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final ArrayList getAgentDetailsOfPortalIdBlockStatusWise(String portalId,String status,int pageNumber,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _portalId=Integer.parseInt(portalId);
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.blockStatus=:status order by af.dateOfJoining desc";
			}
			if(clientFlag.equals("1")){
				qyery="select wf.clientId,wf.companyName,mf.mdInitial,mf.mdId,df.distributorInitial,df.distributorId,af.agentInitial,af.agentId,af.companyName,af.name,alf.loginStatus,aaf.totalCash,aaf.usedCash,af.mobileNo,af.emailId,alf.blockStatus,wf.domainName,wf.clientFlag,wf.categoryName,wf.blockStatus,wf.helpEmailId,wf.helpMobileNo,wf.status,aaf.cutOffAmount,wf.registrationDate,mf.portalID from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.blockStatus=:status order by af.dateOfJoining desc";
			}

			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);;
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap agentMap=new HashMap();
				agentMap.put("portalId",row[0]);
				agentMap.put("clientCompanyName",row[1]);
				String mdInitial=(String)row[2];			  
				String mdId=((Object)row[3]).toString();
				mdId=mdInitial+mdId;
				agentMap.put("mdId",mdId);
				String distributorInitial=(String)row[4];
				String distributorId=((Object)row[5]).toString();
				distributorId=distributorInitial+distributorId;
				agentMap.put("distributorId",distributorId);
				String agentInitial=(String)row[6];
				String agentId=((Object)row[7]).toString();
				agentId=agentInitial+agentId;
				agentMap.put("agentId",agentId);
				agentMap.put("companyName",row[8]);
				agentMap.put("agentName",row[9]);
				agentMap.put("loginStatus",row[10]);
				double totCash=(Double)row[11];			  
				double usedCash=(Double)row[12];
				double cutOffAmount=(Double)row[23];
				double balance=totCash-usedCash+cutOffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentMap.put("agentBalance",f.format(balance));
				agentMap.put("agentMobileNo",row[13]);
				agentMap.put("agentEmailId",row[14]);
				agentMap.put("agentBlockStatus",row[15]);
				// Code Added By Manoj Date : 22/11/2012
				agentMap.put("domaiName",row[16]);
				agentMap.put("clientFlag",row[17]);
				agentMap.put("categoryName",row[18]);
				agentMap.put("wlBlockStatus",row[19]);
				agentMap.put("agentCellEmailId",row[20]);
				agentMap.put("helpMobileNo",row[21]);
				agentMap.put("WLstatus",row[22]);
				dateofwlreg=dateConvert(row[24]);
				agentMap.put("wlRegistrationDate",dateofwlreg);
				agentMap.put("mdPortalID",row[25]);
				agentDetailsList.add(agentMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdBlockStatusWise");
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
				System.out.println("Exception in AgentActivationDao,getAgentDetailsOfPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return agentDetailsList; 
	}
	final int getNoOfRecordsAllAgentDetails(String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		long count=0;
		int check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId";
			}
			if(clientFlag.equals("1")){
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId";
			}

			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
			if(check>2000)
			{
				check=2000;
			}else{
				check=check;
			}
			//System.out.println("Total No of Agent is :"+check);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return check; 
	}
	final int getNoOfRecordsAllAgentDetailsStatusWise(String status,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.loginStatus=:status";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.loginStatus=:status";
			}

			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000){
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllAgentDetailsBlockStatusWise(String status,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.blockStatus=:status";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and alf.blockStatus=:status";
			}

			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000){
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getAgentDetailsBlockStatusWise");
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
				System.out.println("Exception in AgentActivationDao,getAgentDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllAgentDetailsPortalIdWise(String portalId,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId";
			}

			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000)
			{
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetails");
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
				System.out.println("Exception in AgentActivationDao,getAllAgentsDetail");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllAgentDetailsPortalIdStatusWise(String portalId,String status,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.loginStatus=:status";
			}
			if(clientFlag.equals("1"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.loginStatus=:status";
			}

			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000){
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdStatusWise");
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
				System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final int getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise(String portalId,String status,String clientFlag)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			int _portalId=Integer.parseInt(portalId);
			String qyery="";
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.blockStatus=:status";
			}
			if(clientFlag.equals("0"))
			{
				qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId and wf.clientId=:portalId and alf.blockStatus=:status";
			}

			Query query1=session.createQuery(qyery).setParameter("portalId",_portalId).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000){
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+check);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise");
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
				System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsPortalIdBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}

	final int getNoOfRecordsAllAgentDetailsAgentIdWise(String _agentId,String portalId,String clientFlag)
	{
		Session session=null;         
		ArrayList agentDetailsList=new ArrayList();
		int count=0;
		long check=0;
		try{
			String qyery="";
			Query query1=null;
			session = HibernateSession.getSessionFactory().openSession();
			if(!portalId.equalsIgnoreCase("all"))
			{
				int _portalId=Integer.parseInt(portalId);
				if(clientFlag.equals("0") || clientFlag.equals("2"))
				{
					qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (af.agentInitial+convert(varchar,af.agentId))=:agentId and wf.clientId=:portalId";
				}
				if(clientFlag.equals("1"))
				{
					qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (af.agentInitial+convert(varchar,af.agentId))=:agentId and wf.clientId=:portalId";
				}

				query1=session.createQuery(qyery).setParameter("agentId",_agentId).setParameter("portalId",_portalId);
			}
			else{
				if(clientFlag.equals("0") || clientFlag.equals("2"))
				{
					qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,AgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (af.agentInitial+convert(varchar,af.agentId))=:agentId";
				}
				if(clientFlag.equals("1"))
				{
					qyery="select count(af.agentId) from AgentDetailsFormBean af,DistributorDetailsFormBean df,MdsDetailsFormBean mf,AgentAmountFormBean aaf,DynamicDetailsFormBean wf,REPAgentLoginInfoFormBean alf where alf.userId=af.agentId and af.agentId=aaf.agentId and af.distId=df.distributorId and df.mdId=mf.mdId and mf.clientId=wf.clientId  and (af.agentInitial+convert(varchar,af.agentId))=:agentId";
				}

				query1=session.createQuery(qyery).setParameter("agentId",_agentId);
			}
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			if(count>2000){
				count=2000;
			}else{
				count=count;
			}
			//System.out.println("Total No of Agent is :"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsAgentIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsAgentIdWise");
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
				System.out.println("Exception in AgentActivationDao,getNoOfRecordsAllAgentDetailsAgentIdWise ");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final void activateAgents(String [] agentIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		ArrayList activatedAgentsList=new ArrayList();
		ArrayList deactivatedAgentList=new ArrayList();
		String status="";
		String agentMobileNo="";
		String SmsStatus="";
		String MPIN="";
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<agentIds.length;i++)
			{
				session.beginTransaction();
				String agentId=agentIds[i];

				CallableStatement cs = con.prepareCall("{ call Activate_Agent_FromMD(?) }");
				cs.setString(1,agentId);
				ResultSet rs= cs.executeQuery();
				session.getTransaction().commit();
				while(rs.next())
				{
					status=rs.getString(1);
					agentMobileNo=rs.getString(2);
					SmsStatus=rs.getString(3);
					MPIN=rs.getString(4);
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("Active")){
					String activateMessage="Dear Agent, Your Agent ID "+agentId+" has been activated.";
					//sendsms.sendMobileSmsSMSZONE(agentMobileNo,activateMessage);
					//sendsms.sendMobileSmsACL(agentMobileNo,activateMessage);
					activatedAgentsList.add(agentId);
					status="Activate";
				}
				else{
					status="Notactive";
					deactivatedAgentList.add(agentId);
				}
				if(SmsStatus.equalsIgnoreCase("Active"))
				{
					String mpinMessage="Congrats! Your mobile no. "+agentMobileNo+" is successfully activated for transactions using SMS. Your MPIN (Mobile PIN) is "+MPIN+" ";
					//sendsms.sendMobileSmsSMSZONE(agentMobileNo,mpinMessage);
					//sendsms.sendMobileSmsACL(agentMobileNo,mpinMessage);
				}
			}
			appSession.put("activatedAgentsList",activatedAgentsList);
			appSession.put("deactivatedAgentList",deactivatedAgentList);
		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,activateAgents");
			System.out.println(e.toString());
		}
		catch(SQLException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,activateAgents");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,activateAgents");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentActivationDao,activateAgents ");
				System.out.println(e.toString());
			}
		}
	}
	final void deactivateAgents(String [] agentIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedAgentsList=new ArrayList();
		ArrayList deactivatedAgentList=new ArrayList();
		String status="active";
		String agentMobileNo="";
		String loginType=(String)appSession.get("loginType");

		try{	
			session = HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			for(int i=0;i<agentIds.length;i++)
			{
				status="active";
				String agentId=agentIds[i];
				Query query1=session.createQuery("select af.agentId from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
				int agentid=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					agentid=(Integer)row;
				}
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin"))
				{
					String sql="select wld.flag from agent_details ad,distributor_details dd,MD_Details md," +
							"white_label_details wld where ad.distributor_id=dd.distributor_id and dd.md_id=md.MD_id " +
							"and md.Client_Id=wld.Client_Id and ad.agent_id="+agentid+"";
					Query aquery=session.createSQLQuery(sql);
					clientFlag=(String)aquery.uniqueResult();
				}
				if(clientFlag.equals("0")|| clientFlag.equals("2"))
				{
					AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,agentid);
					if(loginInfo != null){	
						loginInfo.setLoginStatus("Deactive");		 
						session.update(loginInfo);
						txn.commit();
						status="deactive";
					}
				}
				if(clientFlag.equals("1") )
				{
					REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,agentid);
					if(reploginInfo != null)
					{	
						reploginInfo.setLoginStatus("Deactive");		 
						session.update(reploginInfo);	          
						status="deactive";
					}
					AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,agentid);
					if(loginInfo != null)
					{	
						loginInfo.setLoginStatus("Deactive");		 
						session.update(loginInfo);
						status="deactive";			  
					}
					txn.commit();
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("deactive"))
				{
					Query queryMobileNo=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();){
						Object row = (Object) it.next();
						agentMobileNo=(String)row;
					}
					String activateMessage="Dear Agent, Your Agent ID "+agentId+" has been deactivated.";
					//sendsms.sendMobileSmsSMSZONE(agentMobileNo,activateMessage);
					//sendsms.sendMobileSmsACL(agentMobileNo,activateMessage);
					deactivatedAgentList.add(agentId);			 
				}
				else{
					status="active";
					activatedAgentsList.add(agentId);
				}

			}
			appSession.put("activatedAgentsList",activatedAgentsList);
			appSession.put("deactivatedAgentList",deactivatedAgentList);
		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,deactivateAgents");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in AgentActivationDao,deactivateAgents");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentActivationDao,deactivateAgents");
				System.out.println(e.toString());
			}
		}
	}
	final void partiallyActivateAgents(String [] agentIds,String clientFlag,String validUpto,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList pactivatedAgentsList=new ArrayList();
		ArrayList inactivatedAgentList=new ArrayList();
		String status="inactive";
		String agentMobileNo="";
		Connection con=null;
		String loginType=(String)appSession.get("loginType");
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<agentIds.length;i++)
			{
				status="inactive";
				String agentId=agentIds[i];
				Query query1=session.createQuery("select af.agentId from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
				int agentid=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					agentid=(Integer)row;
				}
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin"))
				{
					String sql="select wld.flag from agent_details ad,distributor_details dd,MD_Details md," +
							"white_label_details wld where ad.distributor_id=dd.distributor_id and dd.md_id=md.MD_id " +
							"and md.Client_Id=wld.Client_Id and ad.agent_id="+agentid+"";
					Query aquery=session.createSQLQuery(sql);
					clientFlag=(String)aquery.uniqueResult();
				}
				txn=session.beginTransaction();
				if(clientFlag.equals("0")|| clientFlag.equals("2")){
					AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,agentid);
					if(loginInfo != null)
					{	
						loginInfo.setLoginStatus("PartiallyActive");
						loginInfo.setValidUpto(validUpto);
						session.update(loginInfo);
						txn.commit();
						status="PA";
					}
				}
				if(clientFlag.equals("1"))
				{
					REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,agentid);
					if(reploginInfo != null)
					{	
						reploginInfo.setLoginStatus("PartiallyActive");
						reploginInfo.setValidUpto(validUpto);
						session.update(reploginInfo);
						txn.commit();
						status="PA";
					}
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("PA"))
				{
					Query queryMobileNo=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();)
					{
						Object row = (Object) it.next();
						agentMobileNo=(String)row;
					}
					String activateMessage="Dear Agent, Your Agent ID "+agentId+" has been partially activated and it is valid upto"+validUpto+".";
					// sendsms.sendMobileSmsSMSZONE(agentMobileNo,activateMessage);
					// sendsms.sendMobileSmsACL(agentMobileNo,activateMessage);
					pactivatedAgentsList.add(agentId);			 
				}
				else{
					status="inactive";
					inactivatedAgentList.add(agentId);
				}

			}
			appSession.put("pactivatedAgentsList",pactivatedAgentsList);
			appSession.put("inactivatedAgentList",inactivatedAgentList);

		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,partiallyActivateAgents");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,partiallyActivateAgents");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentActivationDao,partiallyActivateAgents");
				System.out.println(e.toString());
			}
		}
	}
	final void blockAgents(String [] agentIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedAgentsList=new ArrayList();
		ArrayList unblockedAgentList=new ArrayList();
		String status="";
		String agentMobileNo="";
		Connection con=null;
		String loginType=(String)appSession.get("loginType");
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<agentIds.length;i++)
			{
				status="";
				String agentId=agentIds[i];
				Query query1=session.createQuery("select af.agentId from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
				int agentid=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					agentid=(Integer)row;
				}
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin"))
				{
					String sql="select wld.flag from agent_details ad,distributor_details dd,MD_Details md," +
							"white_label_details wld where ad.distributor_id=dd.distributor_id and dd.md_id=md.MD_id " +
							"and md.Client_Id=wld.Client_Id and ad.agent_id="+agentid+"";
					Query aquery=session.createSQLQuery(sql);
					clientFlag=(String)aquery.uniqueResult();
				}
				txn=session.beginTransaction();
				if(clientFlag.equals("0") || clientFlag.equals("2"))
				{
					AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,agentid);
					if(loginInfo != null){	
						//loginInfo.setBlockStatus("blocked");
						// updated By Manoj 
						loginInfo.setBlockStatus("Blocked");
						session.update(loginInfo);
						txn.commit();
						status="blocked";
					}
				}
				if(clientFlag.equals("1"))
				{
					REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,agentid);
					if(reploginInfo != null){	
						//reploginInfo.setBlockStatus("blocked");
						// Updated By Manoj
						reploginInfo.setBlockStatus("Blocked");
						session.update(reploginInfo);
						txn.commit();
						status="blocked";
					}
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("blocked"))
				{
					Query queryMobileNo=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();)
					{
						Object row = (Object) it.next();
						agentMobileNo=(String)row;
					}
					String activateMessage="Dear Agent, Your Agent ID "+agentId+" has been blocked";
					//  sendsms.sendMobileSmsSMSZONE(agentMobileNo,activateMessage);
					//  sendsms.sendMobileSmsACL(agentMobileNo,activateMessage);
					blockedAgentsList.add(agentId);			 
				}
				else{
					status="";
					unblockedAgentList.add(agentId);
				}

			}
			appSession.put("blockedAgentsList",blockedAgentsList);
			appSession.put("unblockedAgentList",unblockedAgentList);

		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in blockAgents");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,blockAgents");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentActivationDao,blockAgents ");
				System.out.println(e.toString());
			}
		}
	}
	final void unblockAgents(String [] agentIds,String clientFlag,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedAgentsList=new ArrayList();
		ArrayList unblockedAgentsList=new ArrayList();
		String status="";
		String agentMobileNo="";
		Connection con=null;
		String loginType=(String)appSession.get("loginType");
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<agentIds.length;i++)
			{
				status="";
				String agentId=agentIds[i];
				Query query1=session.createQuery("select af.agentId from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
				int agentid=0;
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					agentid=(Integer)row;
				}
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin"))
				{
					String sql="select wld.flag from agent_details ad,distributor_details dd,MD_Details md," +
							"white_label_details wld where ad.distributor_id=dd.distributor_id and dd.md_id=md.MD_id " +
							"and md.Client_Id=wld.Client_Id and ad.agent_id="+agentid+"";
					Query aquery=session.createSQLQuery(sql);
					clientFlag=(String)aquery.uniqueResult();
				}
				txn=session.beginTransaction();
				if(clientFlag.equals("0") || clientFlag.equals("2"))
				{
					AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,agentid);
					if(loginInfo != null)
					{	
						//loginInfo.setBlockStatus("unblocked");
						//Updated By Manoj 
						loginInfo.setBlockStatus("Unblocked");
						session.update(loginInfo);
						txn.commit();
						status="unblocked";
					}
				}
				if(clientFlag.equals("1"))
				{
					REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,agentid);
					if(reploginInfo != null)
					{	
						//reploginInfo.setBlockStatus("unblocked");
						// updated By Manoj
						reploginInfo.setBlockStatus("Unblocked");
						session.update(reploginInfo);
						txn.commit();
						status="unblocked";
					}
				}
				SendSmsOnMobile sendsms=new SendSmsOnMobile();
				if(status.equalsIgnoreCase("unblocked"))
				{
					Query queryMobileNo=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId") .setParameter("agentId",agentId);
					for(Iterator it=queryMobileNo.iterate();it.hasNext();)
					{
						Object row = (Object) it.next();
						agentMobileNo=(String)row;
					}
					String activateMessage="Dear Agent, Your Agent ID "+agentId+" has been unblocked";
					//  sendsms.sendMobileSmsSMSZONE(agentMobileNo,activateMessage);
					//  sendsms.sendMobileSmsACL(agentMobileNo,activateMessage);
					unblockedAgentsList.add(agentId);			 
				}
				else{
					status="";
					blockedAgentsList.add(agentId);
				}

			}
			appSession.put("blockedAgentsList",blockedAgentsList);
			appSession.put("unblockedAgentList",unblockedAgentsList);

		}
		catch(HibernateException e){
			try{
				if(session.getTransaction()!= null)			 
					session.getTransaction().rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in unblockAgents");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(session.getTransaction()!= null)
					session.getTransaction().rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AgentActivationDao,unblockAgents");
			System.out.println(e.toString());
		}
		finally{
			try{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentActivationDao,unblockAgents");
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
			System.out.println("Exception in AgentActivationDao,dataConvert ");
			System.out.println(e.toString());
		}  
		return null;
	}
	public static void main(String[] args) {
		Session session=HibernateSession.getSessionFactory().openSession();
		List<AgentDetailsFormBean> agent=(List<AgentDetailsFormBean>)session.getNamedQuery("agentDetailsQuery").setInteger(0, 2622).list();
		for (AgentDetailsFormBean user : agent){
			//System.out.println(user.getAgentId());
			//System.out.println(user.getFirstName());
		}
	}
}