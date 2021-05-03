package com.controlPanel.manageEGEN.egenService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class EgenServiceControlDao {

	public ArrayList<HashMap<String, Object>> getUpdateEgenServiceDetails(
			String enteruserId, String operator, String subservice,String service) {
		
		Session session=HibernateSession1.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>>data=new ArrayList<HashMap<String,Object>>();	
		Query qry=null;
		String checkStatus="";
		String messageStatus="";
		String InternalRemark="";
		String ExternalRemark="";
	    
		System.out.println("operator : "+operator+" subservice : "+subservice+" service : "+service);
		try 
		{
			if(("AllRechargeService".equalsIgnoreCase(subservice) && "All".equalsIgnoreCase(enteruserId)&& "All".equalsIgnoreCase(operator)) /*||*/
			/*("AllRechargeService".equalsIgnoreCase(subservice) && "All".equalsIgnoreCase(operator))*/)
			{
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator from ApiServiceOperatorDetailForm APISD " +
				" where APISD.serviceName=:service1 or APISD.serviceName=:service2 or APISD.serviceName=:service3")
				.setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");
				
			}
			else if("AllRechargeService".equalsIgnoreCase(subservice) && "All".equalsIgnoreCase(enteruserId) ||
					(!"AllRechargeService".equalsIgnoreCase(subservice) && "All".equalsIgnoreCase(enteruserId)&& !"All".equalsIgnoreCase(operator)))
			{
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator from ApiServiceOperatorDetailForm APISD " +
				" where APISD.OperatorName=:opName")
				.setParameter("opName",operator);	
       
			}
			else if("All".equalsIgnoreCase(enteruserId)&& "All".equalsIgnoreCase(operator))
			{
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator from ApiServiceOperatorDetailForm APISD " +
				" where APISD.serviceName=:service")
				.setParameter("service",subservice);		
       
			}
			else if(!"AllRechargeService".equalsIgnoreCase(subservice) && !"All".equalsIgnoreCase(enteruserId)&& "All".equalsIgnoreCase(operator))
			{
				int id=Integer.parseInt(enteruserId);
				messageStatus="check";
				checkStatus="getStatus";
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator,APISD.InternalMessage,APISD.RemarkMessage,APISD.ActiveStatus from ApiServiceOperatorDetailForm APISD " +
				" where APISD.CorporateAgentId=:userid and APISD.serviceName=:service")
				.setParameter("userid",id)
				.setParameter("service", subservice);	
			}
			else if(("AllRechargeService".equalsIgnoreCase(subservice) && !"All".equalsIgnoreCase(enteruserId)&& "All".equalsIgnoreCase(operator)))
			{
				int id=Integer.parseInt(enteruserId);
				messageStatus="check";
				checkStatus="getStatus";
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator,APISD.InternalMessage,APISD.RemarkMessage,APISD.ActiveStatus from ApiServiceOperatorDetailForm APISD " +
				" where APISD.CorporateAgentId=:userid  and  (APISD.serviceName=:service1 or APISD.serviceName=:service2 or APISD.serviceName=:service3)")
				.setParameter("userid",id).setParameter("service1", "mobile").setParameter("service2", "dth").setParameter("service3", "datacard");
	               
			}
			else
			{
				checkStatus="getStatus";
				messageStatus="check";
				int id=Integer.parseInt(enteruserId);
				qry=session.createQuery("select distinct APISD.serviceName,APISD.OperatorName,APISD.Operator,APISD.InternalMessage,APISD.RemarkMessage,APISD.ActiveStatus from ApiServiceOperatorDetailForm APISD " +
				" where APISD.serviceName=:service and APISD.CorporateAgentId=:userid and APISD.OperatorName=:opName")
				.setParameter("service",subservice).setParameter("userid",id).setParameter("opName",operator);
			}
			System.out.println("Query : "+qry);
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object[] row=(Object[])it.next();
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("service", row[0]);
				map.put("opName", row[1]);
			    map.put("opValue", row[2]);
			    if("check".equalsIgnoreCase(messageStatus))
			    {
			    	InternalRemark=(String)row[3];
			    	ExternalRemark=(String)row[4];
			    	if(InternalRemark==null || InternalRemark.equalsIgnoreCase("null") || InternalRemark.equals("")|| InternalRemark=="null"  )
			    	{
			    		InternalRemark="";	
			    	}
			   
			    	if(ExternalRemark==null || ExternalRemark.equalsIgnoreCase("null") || ExternalRemark.equals("")|| ExternalRemark=="null")
			    	{
			    		ExternalRemark="";	
			    	}	
			    }
			    else
			    {
			    	InternalRemark="";
			    	ExternalRemark="";
			    }
		    
			    map.put("InternalRemark",InternalRemark);
			    map.put("ExternalRemark",ExternalRemark);
			    if("getStatus".equalsIgnoreCase(checkStatus)){
			    	String Status=(String)row[5];
			    	if("Y".equalsIgnoreCase(Status)){
			    		Status="Active"; 
			    	}
			    	else{
			    		Status="Block";  
			    	}
			    	map.put("Status",Status); 
			    	//System.out.println("checkStatus is : "+checkStatus);
			    	map.put("checkStatus",checkStatus); 
			    }
			    data.add(map);
			}
			System.out.println("Data : "+data);
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in EgenServiceControlDao,getUpdateEgenServiceDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println(e2.toString());
				System.out.println("Exception in EgenServiceControlDao,getUpdateEgenServiceDetails ");
			}
		}
		return data;
	}

	public String doupdate(String internalRemark, String enternalresonse,
			String operatorName, String updateBy, String actionTake,String subservice,String userid,String ipAddress) {
		
		Session session=HibernateSession1.getSessionFactory().openSession();
		String UpdateStatus="NotUpdate";
	    Transaction tran=null;
	    Query qry=null;
	    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
	    try {
			if("All".equalsIgnoreCase(userid)){
				qry=session.createQuery("update ApiServiceOperatorDetailForm APISD set APISD.ActiveStatus=:status,APISD.updatedBy=:updateby, " +
						" APISD.UpdateDate=:date,APISD.RemarkMessage=:response,APISD.InternalMessage=:internalMessage,APISD.ipaddress=:ip where " +
				" APISD.Operator=:operator and APISD.serviceName=:service")
				.setParameter("status",actionTake).setParameter("updateby",updateBy).setParameter("date",sqlDate).setParameter("response",enternalresonse)
				.setParameter("internalMessage",internalRemark).setParameter("ip",ipAddress).setParameter("operator",operatorName).setParameter("service",subservice);
			}else{
				int id=Integer.parseInt(userid);
				qry=session.createQuery("update ApiServiceOperatorDetailForm APISD set APISD.ActiveStatus=:status,APISD.updatedBy=:updateby, " +
						" APISD.UpdateDate=:date,APISD.RemarkMessage=:response,APISD.InternalMessage=:internalMessage,APISD.ipaddress=:ip where " +
				" APISD.Operator=:operator and APISD.serviceName=:service and  APISD.CorporateAgentId=:userid")
				.setParameter("status",actionTake).setParameter("updateby",updateBy).setParameter("date",sqlDate).setParameter("response",enternalresonse)
				.setParameter("internalMessage",internalRemark).setParameter("ip",ipAddress).setParameter("operator",operatorName)
				.setParameter("service",subservice).setParameter("userid",id);
			}
			
			tran=session.beginTransaction();
			int flag=qry.executeUpdate();
			if(flag>0) {
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
		    	System.out.println("Exception in EgenServiceControlDao,doupdate ");
			  
	    }
	    finally
		{
	    	try {
				session.flush();
				session.close();
	    	} catch (Exception e2) {
	    		System.out.println(e2.toString());
				System.out.println("Exception in EgenServiceControlDao,doupdate ");
	    	}
		}
	    return UpdateStatus;
	}

	public ArrayList<HashMap<String, Object>> getclientDetails() {
		
		Session session=HibernateSession1.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();	
        Query qry=null;
		try {
		qry=session.createQuery("select distinct APID.corporateAgentId,APID.companyName from APIClientDetailsFormBean APID");
		Iterator<?>it=qry.iterate();
		while(it.hasNext())
		{
			Object[] row=(Object[])it.next();
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put("corporateAgentId",row[0]);
			map.put("companyName",row[1]);
			data.add(map);
		}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Exception in EgenServiceControlDao,getclientDetails ");
		}
		finally
		{
			try {
				session.flush();
				session.close();
			   } catch (Exception e2) {
				   System.out.println(e2.toString());
				   System.out.println("Exception in EgenServiceControlDao,getclientDetails ");
			   }
		}
		return data;
	}
}
