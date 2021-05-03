package com.controlPanel.manageEGEN.egenOperatorMgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class OperatorControlDao {

	

	public ArrayList<HashMap<String, Object>> getAgentServiceDetails(
			String operator, String serviceType) 
			{
		
		Session session=null;
		ArrayList<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();	
		Query qry=null;
        
		try {
			//System.out.println("serviceType----"+serviceType);
			//System.out.println("operator----"+operator);
			session=HibernateSession1.getSessionFactory().openSession();
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select ASD.operator,ASD.Active,ASD.Vendor from AgentServiceDetailsForm ASD where ASD.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
				//System.out.println("in else");
				qry=session.createQuery("select ASD.operator,ASD.Active,ASD.Vendor from AgentServiceDetailsForm ASD where ASD.SubService=:service and ASD.operator=:operator")
				.setParameter("service",serviceType).setParameter("operator", operator);	
			}
			
		Iterator<?>it=qry.iterate();
		
		while(it.hasNext())
		{
			Object[] row=(Object[])it.next();
		    HashMap<String,Object> map=new HashMap<String,Object>();
		    map.put("operator", row[0]);
		    map.put("Active", row[1]);
		    map.put("Vendor", row[2]);
		    data.add(map);
		}
		} catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,getAgentServiceDetails ");
			   
		}
		finally
		{
			try {
				
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,getAgentServiceDetails ");
			}
		}
		return data;
	}

	public ArrayList<HashMap<String, Object>> getEganServiceDetails(String operator, String serviceType) 
	{
		Session session=null;
		session=null;
		Transaction tss=session.beginTransaction();
		ArrayList<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();	
		Query qry=null;
        
		try 
		{
			session=HibernateSession1.getSessionFactory().openSession();
			//System.out.println("serviceType----"+serviceType);
			//System.out.println("operator----"+operator);
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select EAO.operator,EAO.Active,EAO.Vendor from EganActiveOperatorDetails EAO where EAO.Service=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			// System.out.println("in else");
				qry=session.createQuery("select EAO.operator,EAO.Active,EAO.Vendor from EganActiveOperatorDetails EAO where EAO.Service=:service and EAO.operator=:operator")
				.setParameter("service",serviceType).setParameter("operator", operator);	
			}
			
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object[] row=(Object[])it.next();
			    HashMap<String,Object> map=new HashMap<String,Object>();
			    map.put("operator", row[0]);
			    map.put("Active", row[1]);
			    map.put("Vendor", row[2]);
			    data.add(map);
				
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,getEganServiceDetails ");
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e2) 
			{
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,getEganServiceDetails ");
				
			}
		}
		
		return data;
	}

	public StringBuffer StringCyberDetails(String operator,String serviceType) 
	{
		Session session=null;
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		
		try 
		{
			session=HibernateSession1.getSessionFactory().openSession();
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select COD.operator from CyberPlatOperatorDetailsForm COD where COD.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
				//System.out.println("in else");
				qry=session.createQuery("select COD.operator from CyberPlatOperatorDetailsForm COD where COD.SubService=:service and COD.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
		
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringCyberDetails ");
		}
		finally
		{
			try {
			
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringCyberDetails ");
			}
		}
		return rs;
	}

	public StringBuffer StringEganCyberDetails(String operator,String serviceType) 
	{
		Session session=null;
		
		Transaction tss=null;
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try 
		{
			session=HibernateSession1.getSessionFactory().openSession();
			tss=session.beginTransaction();
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select COD.operator from CyberPlatOperatorDetailsForm COD where COD.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
				//System.out.println("in else");
				qry=session.createQuery("select COD.operator from CyberPlatOperatorDetailsForm COD where COD.SubService=:service and COD.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
		
			}
			tss.commit();
	
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringEganCyberDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringEganCyberDetails ");
			}
		}
	
		return rs;
	}

	public StringBuffer StringEzyPayDetails(String operator, String serviceType) 
	{
		Session session=HibernateSession1.getSessionFactory().openSession();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try 
		{
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select EP.operator from EzyPayOperatorDetailsForm EP where EP.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select EP.operator from EzyPayOperatorDetailsForm EP where EP.SubService=:service and EP.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringRinDetails ");
		}
		finally
		{
			try {
			
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringRinDetails ");
			}
		}
		return rs;
	}

	public StringBuffer StringBillpaymartDetails(String operator, String serviceType) 
	{
		Session session=HibernateSession1.getSessionFactory().openSession();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try 
		{
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select EP.operator from BillpaymartOperatorDetailsForm EP where EP.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select EP.operator from BillpaymartOperatorDetailsForm EP where EP.SubService=:service and EP.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringRinDetails ");
		}
		finally
		{
			try {
			
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringRinDetails ");
			}
		}
		System.out.println("RS : "+rs);
		return rs;
	}
	
	public StringBuffer StringEganEzyPayDetails(String operator, String serviceType) 
	{
		Session session=null;
		session=HibernateSession1.getSessionFactory().openSession();
		
		Transaction tss=session.beginTransaction();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try {
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select EP.operator from EzyPayOperatorDetailsForm EP where EP.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select EP.operator from EzyPayOperatorDetailsForm EP where EP.SubService=:service and EP.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringEganRinDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringEganRinDetails ");
			}
		}
		return rs;
	}
	
	public StringBuffer StringEganBillpaymartDetails(String operator, String serviceType) 
	{
		Session session=null;
		session=HibernateSession1.getSessionFactory().openSession();
		
		Transaction tss=session.beginTransaction();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try {
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select EP.operator from BillpaymartOperatorDetailsForm EP where EP.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select EP.operator from BillpaymartOperatorDetailsForm EP where EP.SubService=:service and EP.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringEganRinDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringEganRinDetails ");
			}
		}
		System.out.println("RS : "+rs);
		return rs;
	}

	public StringBuffer StringRootDetails(String operator, String serviceType) 
	{
		Session session=HibernateSession1.getSessionFactory().openSession();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try
		{
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select RTC.operator from RootComparkOperatorDetailsForm RTC where RTC.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select RTC.operator from RootComparkOperatorDetailsForm RTC where RTC.SubService=:service and RTC.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
		
			}
	
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringGoalDetails ");
		}
		finally
		{
			try {
				
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringGoalDetails ");
			}
		}
		return rs;
	}

	public StringBuffer StringEganRootDetails(String operator, String serviceType) 
	{
		Session session=null;
		session=HibernateSession1.getSessionFactory().openSession();
		
		Transaction tss=session.beginTransaction();
		Query qry=null;
		StringBuffer rs=new StringBuffer();
		try {
			if("All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select RTC.operator from RootComparkOperatorDetailsForm RTC where RTC.SubService=:service")
				.setParameter("service", serviceType);
			}
			else
			{
			//System.out.println("in else");
				qry=session.createQuery("select RTC.operator from RootComparkOperatorDetailsForm RTC where RTC.SubService=:service and RTC.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);	
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				rs.append(row+",");
		
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,StringEganGoalDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,StringEganGoalDetails ");
			}
		}
		return rs;
	}

	public String doUpdateOperatorVendor(String vendorName, String operatorName,String serviceType) 
	{
		Session session=HibernateSession1.getSessionFactory().openSession();
		String UpdateStatus="NotUpdate";
		Transaction tran=null;
		
		Query qry=null;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		System.out.println("operatorName----"+operatorName);
		System.out.println("vendorName----"+vendorName);
		System.out.println("serviceType=---"+serviceType);
		try 
		{
			qry=session.createQuery("update AgentServiceDetailsForm ASD set ASD.Vendor=:vendor,ASD.updatedUser=:updateby,ASD.updateDate=:date where " +
			"  ASD.operator=:operator and ASD.SubService=:service")
			.setParameter("vendor",vendorName).setParameter("updateby","Admin").setParameter("date",sqlDate)
			.setParameter("operator",operatorName).setParameter("service",serviceType);
			tran=session.beginTransaction();
			
			int flag=qry.executeUpdate();
			if(flag>0) 
			{
				UpdateStatus="Update"; 
			}
			else{
				UpdateStatus="NotUpdate";
			}
			tran.commit();
			
		} catch (Exception e) {
			UpdateStatus="notUpdate";
			tran.rollback();
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,doUpdateOperatorVendor ");
		}
		finally
		{
			try {
			
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,doUpdateOperatorVendor ");
			}
		}
		return UpdateStatus;
	}

	public ArrayList<HashMap<String, Object>> getAgentServiceDetailsAjax(String mainService, String serviceType) {
	
		Session session=HibernateSession1.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();	
		Query qry=null;
		
		try {
			System.out.println("mainService---"+mainService);
			System.out.println("serviceType---"+serviceType);
			if("AgentServiceControl".equalsIgnoreCase(mainService))
			{
				//System.out.println("in -AgentServiceControl");
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct TAM.Operator from TepAgentMobileRechargeActiveOperatorDetailsForm TAM," +
							"AgentServiceDetailsForm ASD " +
							"where ASD.operator=TAM.Operator and ASD.Active!=:status and  ((TAM.Service=:service1 and ASD.SubService=:service1)" +
							" or (TAM.Service=:service2 and ASD.SubService=:service2)or (TAM.Service=:service3 and  ASD.SubService=:service3)) " +
					" order by TAM.Operator")
					.setParameter("status","D").setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");		
				}else
				{
					if("billpay".equalsIgnoreCase(serviceType)||"lic".equalsIgnoreCase(serviceType))
					{
						qry=session.createQuery("select distinct TAM.Operator from " +
								"TepAgentMobileRechargeActiveOperatorDetailsForm TAM " +
						" where TAM.Service=:service  order by TAM.Operator")
						.setParameter("service",serviceType);	
					}else 
					{
						qry=session.createQuery("select distinct TAM.Operator from " +
								"TepAgentMobileRechargeActiveOperatorDetailsForm TAM,AgentServiceDetailsForm ASD " +
						" where ASD.operator=TAM.Operator and ASD.Active!=:status and TAM.Service=:service and ASD.SubService=:service order by TAM.Operator")
						.setParameter("status","D").setParameter("service",serviceType);		
					}
				}
			}
			else if("WLServiceControl".equalsIgnoreCase(mainService))
			{
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD,AgentServiceDetailsForm ASD" +
							" where ASD.Active!=:status and WSD.OperatorValue=ASD.operator and ((WSD.SubService=:service1 and ASD.SubService=:service1)" +
							" or (WSD.SubService=:service2 and ASD.SubService=:service2)or (WSD.SubService=:service3 and  ASD.SubService=:service3)) " +
					" order by WSD.OperatorValue")
					.setParameter("status","D").setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");			
				}else
				{
					if("billpay".equalsIgnoreCase(serviceType)||"lic".equalsIgnoreCase(serviceType))
					{
						qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD" +
						" where    WSD.SubService=:service  order by WSD.OperatorValue ")
						.setParameter("service",serviceType);
					}
					else
					{
						qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD,AgentServiceDetailsForm ASD" +
						" where  WSD.OperatorValue=ASD.operator and ASD.Active!=:status and  WSD.SubService=:service and ASD.SubService=:service order by WSD.OperatorValue ")
						.setParameter("status","D").setParameter("service",serviceType).setParameter("service",serviceType);
					}
					//System.out.println("in sle   serviceType"+serviceType);
				}
			}
			else if("EgenServiceControl".equalsIgnoreCase(mainService))
			{
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct API.OperatorName from ApiServiceOperatorDetailForm API where" +
					" API.serviceName=:service1 or API.serviceName=:service2 or API.serviceName=:service3")
					.setParameter("service1","mobile").setParameter("service2","dth").setParameter("service3","datacard");	
				}else
				{
					//System.out.println("in else-------");
					qry=session.createQuery("select distinct API.OperatorName from ApiServiceOperatorDetailForm API where API.serviceName=:service")
					.setParameter("service",serviceType);		
				}
				
			}
			else if("web".equalsIgnoreCase(mainService))
			{
				qry=session.createQuery("select ASD.operator from AgentServiceDetailsForm ASD where ASD.SubService=:service  and ASD.Active!=:status")
				.setParameter("service",serviceType).setParameter("status","D");
			}
			else
			{
				//System.out.println("in else");
				qry=session.createQuery("select RCM.operator from RepCheckMobileCode RCM where RCM.Service=:service ")
				.setParameter("service",serviceType);
			}
			Iterator<?>it=qry.iterate();
			
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("operator",row);
				data.add(map);
		
			}
			System.out.println("Operater Data : "+data);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,getAgentServiceDetailsAjax ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,getAgentServiceDetailsAjax ");
			}
		}
		return data;
	}

	public ArrayList<HashMap<String, Object>> getAgentServiceDetailsAjax2(String mainService, String serviceType) 
	{
	
		Session session=null;
		session=HibernateSession1.getSessionFactory().openSession();
		
		Transaction tss=session.beginTransaction();
		ArrayList<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();	
		Query qry=null;
    
		try {
			System.out.println("serviceType---"+serviceType);
			System.out.println("mainService---"+mainService);
			if("AgentServiceControl".equalsIgnoreCase(mainService))
			{
				//System.out.println("in -AgentServiceControl");
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct TAM.Operator from TepAgentMobileRechargeActiveOperatorDetailsForm TAM," +
							"AgentServiceDetailsForm ASD " +
							"where ASD.operator=TAM.Operator and ASD.Active!=:status and  ((TAM.Service=:service1 and ASD.SubService=:service1)" +
							" or (TAM.Service=:service2 and ASD.SubService=:service2)or (TAM.Service=:service3 and  ASD.SubService=:service3)) " +
					" order by TAM.Operator")
					.setParameter("status","D").setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");		
				}else
				{
					if("billpay".equalsIgnoreCase(serviceType)||"lic".equalsIgnoreCase(serviceType))
					{
						qry=session.createQuery("select distinct TAM.Operator from " +
								"TepAgentMobileRechargeActiveOperatorDetailsForm TAM " +
						" where TAM.Service=:service  order by TAM.Operator")
						.setParameter("service",serviceType);	
					}else
					{
						qry=session.createQuery("select distinct TAM.Operator from " +
								"TepAgentMobileRechargeActiveOperatorDetailsForm TAM,AgentServiceDetailsForm ASD " +
						" where ASD.operator=TAM.Operator and ASD.Active!=:status and TAM.Service=:service and ASD.SubService=:service order by TAM.Operator")
						.setParameter("status","D").setParameter("service",serviceType);		
					}
				}
			}
			else if("WLServiceControl".equalsIgnoreCase(mainService))
			{
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD,AgentServiceDetailsForm ASD" +
							" where ASD.Active!=:status and WSD.OperatorValue=ASD.operator and ((WSD.SubService=:service1 and ASD.SubService=:service1)" +
							" or (WSD.SubService=:service2 and ASD.SubService=:service2)or (WSD.SubService=:service3 and  ASD.SubService=:service3)) " +
					" order by WSD.OperatorValue")
					.setParameter("status","D").setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");			
				}else
				{
				if("billpay".equalsIgnoreCase(serviceType)||"lic".equalsIgnoreCase(serviceType)){
					qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD" +
					" where    WSD.SubService=:service  order by WSD.OperatorValue ")
					.setParameter("service",serviceType);
				}
				else
				{
					qry=session.createQuery("select distinct WSD.OperatorValue from WLClientServiceDetails WSD,AgentServiceDetailsForm ASD" +
					" where  WSD.OperatorValue=ASD.operator and ASD.Active!=:status and  WSD.SubService=:service and ASD.SubService=:service order by WSD.OperatorValue ")
					.setParameter("status","D").setParameter("service",serviceType).setParameter("service",serviceType);
				}
				}
			}
			else if("EgenServiceControl".equalsIgnoreCase(mainService))
			{
				if("AllRechargeService".equalsIgnoreCase(serviceType))
				{
					qry=session.createQuery("select distinct API.OperatorName from ApiServiceOperatorDetailForm API where" +
					" API.serviceName=:service1 or API.serviceName=:service2 or API.serviceName=:service3")
					.setParameter("service1","mobile").setParameter("service2","dth").setParameter("service3","datacard");	
				}else
				{
					//System.out.println("in else-------");
					qry=session.createQuery("select distinct API.OperatorName from ApiServiceOperatorDetailForm API where API.serviceName=:service")
					.setParameter("service",serviceType);		
				}
			}
			else if("web".equalsIgnoreCase(mainService))
			{
				qry=session.createQuery("select distinct EAO.operator from EganActiveOperatorDetails EAO where EAO.Service='"+serviceType+"'");		
			}
			else
			{
				//System.out.println("in else");
				qry=session.createQuery("select RCM.operator from RepCheckMobileCode RCM where RCM.Service=:service ")
				.setParameter("service",serviceType);
			}
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object row=(Object)it.next();
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("operator",row);
				data.add(map);
			}
			//System.out.println("Operater Data : "+data);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,getAgentServiceDetailsAjax2 ");
		}
		finally
		{
			try {
			
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,getAgentServiceDetailsAjax2 ");
			}
		}
		return data;
	}

	public String doUpdateEganOperatorVendor(String vendorName, String operatorName,String serviceType,String userId) {
	
		String UpdateStatus="NotUpdate";
		String vendorCode="";
		Session session=null;
		session=HibernateSession1.getSessionFactory().openSession();
		Transaction tran=session.beginTransaction();
		String sql="";
		Query qry=null;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		System.out.println("operatorName----"+operatorName);

		System.out.println("serviceType=---"+serviceType);
		try 
		{
			if(vendorName.equalsIgnoreCase("CYBERPLAT"))
			{
				sql="select oc.cyberplatCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else if(vendorName.equalsIgnoreCase("Root"))
			{
				sql="select oc.rootCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else if(vendorName.equalsIgnoreCase("EzyPay")||vendorName.equalsIgnoreCase("EasyPay"))
			{
				sql="select oc.ezyPayCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else if(vendorName.equalsIgnoreCase("Billpaymart"))
			{
				sql="select oc.billpaymartCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else if(vendorName.equalsIgnoreCase("TATASKY"))
			{
				sql="select oc.tataskyCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else if(vendorName.equalsIgnoreCase("VIDEOCOND2H"))
			{
				sql="select oc.videoconCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			else
			{
				sql="select oc.operatorCode from OperatorCodeFormBean oc where oc.operatorName=:operatorName and oc.type=:type";
			}
			vendorCode=(String)session.createQuery(sql).setString("operatorName", operatorName).setString("type", serviceType).uniqueResult();
			
			System.out.println("Operator Name : "+operatorName+"  Vendor Code : "+vendorCode+"  Vendor Name : "+vendorName);
			
			
			if(vendorName.equalsIgnoreCase("Root"))
			{
				qry=session.createQuery("update EganActiveOperatorDetails EOD set EOD.rootCode=:vendorCode, EOD.Vendor=:vendor,EOD.updatedUser=:updateby,EOD.updateDate=:date where" +
				"  EOD.operator=:operator and EOD.Service=:service").setParameter("vendorCode",vendorCode)
				.setParameter("vendor",vendorName).setParameter("updateby",userId).setParameter("date",sqlDate)
				.setParameter("operator",operatorName).setParameter("service",serviceType);
			}
			else if(vendorName.equalsIgnoreCase("EzyPay")||vendorName.equalsIgnoreCase("EasyPay"))
			{
				qry=session.createQuery("update EganActiveOperatorDetails EOD set EOD.ezyPayCode=:vendorCode, EOD.Vendor=:vendor,EOD.updatedUser=:updateby,EOD.updateDate=:date where" +
				"  EOD.operator=:operator and EOD.Service=:service").setParameter("vendorCode",vendorCode)
				.setParameter("vendor",vendorName).setParameter("updateby",userId).setParameter("date",sqlDate)
				.setParameter("operator",operatorName).setParameter("service",serviceType);
			}
			else
			{
				qry=session.createQuery("update EganActiveOperatorDetails EOD set EOD.operatorCode=:vendorCode, EOD.Vendor=:vendor,EOD.updatedUser=:updateby,EOD.updateDate=:date where" +
				"  EOD.operator=:operator and EOD.Service=:service").setParameter("vendorCode",vendorCode)
				.setParameter("vendor",vendorName).setParameter("updateby",userId).setParameter("date",sqlDate)
				.setParameter("operator",operatorName).setParameter("service",serviceType);
			}
			
			tran=session.beginTransaction();
			int flag=qry.executeUpdate();
			if(flag>0) 
			{
				UpdateStatus="Update"; 
			}
			else
			{
				UpdateStatus="NotUpdate";
			}
			tran.commit();
		
		} 
		catch (Exception e) 
		{
			UpdateStatus="notUpdate";
	    	tran.rollback();
	    	System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,doUpdateEganOperatorVendor ");
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,doUpdateEganOperatorVendor ");
			}
		}
		return UpdateStatus;
	}

	//----------------------ADD BY NAVEEN-----------------------

	public ArrayList<HashMap<String, Object>> getSMSServiceDetails(String operator, String serviceType) {
		  
		Session session = HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		Query qry = null;

		try {
			//System.out.println("serviceType----" + serviceType);
		   //System.out.println("operator----" + operator);
			if ("All".equalsIgnoreCase(operator)) {
				qry = session.createQuery("select SSD.operatorName, SSD.status, SSD.cyberPlatStatus, SSD.gpStatus, SSD.Vd2hvendor, SSD.TataSky from SMSServiceDetails SSD where SSD.operatorName != 'null' and opStatus != 0 and SSD.serviceType=:service")
				.setParameter("service", serviceType);
			} else {
				//System.out.println("in else");
				qry = session.createQuery("select SSD.operatorName, SSD.status, SSD.cyberPlatStatus, SSD.gpStatus, SSD.Vd2hvendor, SSD.TataSky from SMSServiceDetails SSD where SSD.operatorName != 'null' and opStatus != 0 and SSD.serviceType=:service and SSD.operatorName=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);
			}
			Iterator<?> it = qry.iterate();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				String operators =  row[0].toString();
				String statusRIN = row[1].toString();
				String statusCYBER = row[2].toString();
				String statusGO = row[3].toString();
				String statusVd2h = row[4].toString();
				String statustsky = row[5].toString();
				//System.out.println(" ********** operators " + operators);
				//System.out.println(" ********** statusRIN " + statusRIN);
				//System.out.println(" ********** statusCYBER " + statusCYBER);
				//System.out.println(" ********** statusGO " + statusGO);
				//System.out.println(" ********** statusVd2h " + statusVd2h);
				//System.out.println(" ********** statustsky " + statustsky);
				map.put("operator", operators);
				map.put("statusRIN", statusRIN);
				map.put("statusCYBER", statusCYBER);
			    map.put("statusGO", statusGO);
			    map.put("statusVd2h", statusVd2h);
			    map.put("statustsky", statustsky);
			    data.add(map);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao.getSMSServiceDetails ");
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("Exception in OperatorControlDao,getSMSServiceDetails ");
			}
		}
		return data;
	}

	public StringBuffer cyberSMSDetails(String operator, String serviceType) {
	
		Session session = HibernateSession.getSessionFactory().openSession();
		Query qry = null;
		StringBuffer rs = new StringBuffer();
		try {
			if ("All".equalsIgnoreCase(operator)) {
				qry = session.createQuery("select COD.operator from SMSCyberPlatOperatorDetailsForm COD where COD.SubService=:service")
				.setParameter("service", serviceType);
			} else {
				//System.out.println("in else");
				qry = session.createQuery("select COD.operator from SMSCyberPlatOperatorDetailsForm COD where COD.SubService=:service and COD.operator=:operator")
				.setParameter("service", serviceType).setParameter(
						"operator", operator);
			}
			Iterator<?> it = qry.iterate();
			while (it.hasNext()) {
				Object row = (Object) it.next();
				rs.append(row + ",");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,cyberSMSDetails ");
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,cyberSMSDetails ");
			}
		}
	return rs;
	}

	public StringBuffer rinSMSDetails(String operator, String serviceType) {
	
		Session session = HibernateSession.getSessionFactory().openSession();
		Query qry = null;
		StringBuffer rs = new StringBuffer();
		try {
			if ("All".equalsIgnoreCase(operator)) {
				qry = session.createQuery("select RIN.operator from SMSRinOperatorDetailsForm RIN where RIN.SubService=:service")
				.setParameter("service", serviceType);
			} else {
				//System.out.println("in else");
				qry = session.createQuery("select RIN.operator from SMSRinOperatorDetailsForm RIN where RIN.SubService=:service and RIN.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);
			}

			Iterator<?> it = qry.iterate();
			while (it.hasNext()) {
				Object row = (Object) it.next();
				rs.append(row + ",");
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,rinSMSDetails ");
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,rinSMSDetails ");
			}
		}
		return rs;
	}

	public StringBuffer goSMSDetails(String operator, String serviceType) {
	
		Session session = HibernateSession.getSessionFactory().openSession();
		Query qry = null;
		StringBuffer rs = new StringBuffer();
		try {
			if ("All".equalsIgnoreCase(operator)) {
				qry = session.createQuery("select GOAL.operator from SMSGoalOperatorDetailsForm GOAL where GOAL.SubService=:service")
				.setParameter("service", serviceType);
			} else {
				//System.out.println("in else");
				qry = session.createQuery("select GOAL.operator from SMSGoalOperatorDetailsForm GOAL where GOAL.SubService=:service and GOAL.operator=:operator")
				.setParameter("service", serviceType).setParameter("operator", operator);
			}
			Iterator<?> it = qry.iterate();
			while (it.hasNext()) {
				Object row = (Object) it.next();
				rs.append(row + ",");
			}
		} catch (Exception e) {
		
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,goSMSDetails ");
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,goSMSDetails ");
			}
		}
		
	return rs;
	}

/*						
 * ************************************ SMS END
 * ************************************
 */
 
 /*						
 * ************************************ SMS Update vendors
 * ************************************
 */

	public String updateSMSOperatorVendor(String vendorName,String operatorName, String serviceType) {
	
		Session session = HibernateSession.getSessionFactory().openSession();
		String UpdateStatus = "NotUpdate";
		Transaction tran = null;
		Query qry = null;
		//System.out.println("operatorName----" + operatorName);
		//System.out.println("vendorName----	" + vendorName);
		//System.out.println("serviceType=---	" + serviceType);
		try {
			if("RIN".equalsIgnoreCase(vendorName)){
				qry = session.createQuery("update SMSServiceDetails SSD set SSD.status = 1, SSD.cyberPlatStatus = 0, SSD.gpStatus = 0, SSD.Vd2hvendor = 0, SSD.TataSky = 0 ,SSD.updatedBy=:updateby, SSD.updatedDatetime=getDate() where "
					+ "  SSD.operatorName=:operator and SSD.serviceType=:service")
					.setParameter("updateby", "Admin").setParameter("operator", operatorName).setParameter("service", serviceType);
		}else if("CYBERPLAT".equalsIgnoreCase(vendorName)){
			qry = session.createQuery("update SMSServiceDetails SSD set SSD.status = 0, SSD.cyberPlatStatus = 1, SSD.gpStatus = 0, SSD.Vd2hvendor = 0, SSD.TataSky = 0, SSD.updatedBy=:updateby, SSD.updatedDatetime=getDate() where "
					+ "  SSD.operatorName=:operator and SSD.serviceType=:service")
					.setParameter("updateby", "Admin").setParameter("operator", operatorName).setParameter("service", serviceType);
		}else if ("GOPROCESS".equalsIgnoreCase(vendorName)) {
			qry = session.createQuery("update SMSServiceDetails SSD set SSD.status = 0, SSD.cyberPlatStatus = 0, SSD.gpStatus = 1, SSD.Vd2hvendor = 0, SSD.TataSky = 0,SSD.updatedBy=:updateby, SSD.updatedDatetime=getDate() where "
					+ "  SSD.operatorName=:operator and SSD.serviceType=:service")
					.setParameter("updateby", "Admin").setParameter("operator", operatorName).setParameter("service", serviceType);
		}
		//Added by Manoj to integrate for Tatasky VIDEOCONDTH
		else if ("TATASKY".equalsIgnoreCase(vendorName)) {
			qry = session.createQuery("update SMSServiceDetails SSD set SSD.status = 0, SSD.cyberPlatStatus = 0, SSD.gpStatus = 0, SSD.Vd2hvendor = 0, SSD.TataSky = 1,SSD.updatedBy=:updateby, SSD.updatedDatetime=getDate() where "
					+ "  SSD.operatorName=:operator and SSD.serviceType=:service")
					.setParameter("updateby", "Admin").setParameter("operator", operatorName).setParameter("service", serviceType);
		}else if ("VIDEOCOND2H".equalsIgnoreCase(vendorName)) {
			qry = session.createQuery("update SMSServiceDetails SSD set SSD.status = 0, SSD.cyberPlatStatus = 0, SSD.gpStatus = 0, SSD.Vd2hvendor = 1, SSD.TataSky = 0,SSD.updatedBy=:updateby, SSD.updatedDatetime=getDate() where "
					+ "  SSD.operatorName=:operator and SSD.serviceType=:service")
					.setParameter("updateby", "Admin").setParameter("operator", operatorName).setParameter("service", serviceType);
		}
	
			tran = session.beginTransaction();
			int flag = qry.executeUpdate();
			if (flag > 0) {
				UpdateStatus = "Update";
			} else {
				UpdateStatus = "NotUpdate";
			}
			tran.commit();

		} catch (Exception e) {
			UpdateStatus = "notUpdate";
			tran.rollback();
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlDao,updateSMSOperatorVendor ");
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in OperatorControlDao,updateSMSOperatorVendor ");
			}
		}
		return UpdateStatus;
	}

	public List getSMSServiceDetailsAjax(String serviceType) {
	 
		Session session = HibernateSession.getSessionFactory().openSession();
		List operators = new ArrayList();
		SQLQuery query = session
		.createSQLQuery("select distinct OperatorName from REP_Check_Mobile_Code where OperatorName != 'null' and OP_Status != 0 and services_type ='"
				+ serviceType + "'");
		operators = query.list();
		return operators;
	}
}