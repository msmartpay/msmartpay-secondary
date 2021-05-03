package com.controlPanel.userActivation.corporateUser.whitelabelClient;

import java.sql.Connection;
import java.text.DateFormat;
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
import com.formBean.dynamicDetails.DynamicDetailsFormBean;

/**
* Updated By :Manoj
* Updated Date : 9 July 2013
* Updated Matter : Optimization of code and DB
*/

 class WhiteLabelClientActivationDao
{
	final int getNoOfRecordsAllWhiteLabelClientDetails(){
		Session session=null;         
		long count=0;
		int check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.clientId) from DynamicDetailsFormBean df";
			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
			//System.out.println("Total no of client is "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetails");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetails");
				System.out.println(e.toString());
			}
		}
		return check; 
	}
	final ArrayList<HashMap<String,String>> getAllWhiteLabelClientDetail(int pageNumber){
		Session session=null;         
		ArrayList<HashMap<String,String>> userDetailsList=new ArrayList<HashMap<String,String>>();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select df.clientId,df.companyName,df.domainName,df.status,df.blockStatus,df.clientFlag,df.registrationDate,df.categoryName,df.helpEmailId,df.helpMobileNo from DynamicDetailsFormBean df order by df.clientId";
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1)+1);
			query1.setMaxResults(pageSize*pageNumber);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row[] = (Object[]) it.next();
				HashMap<String,String> userMap=new HashMap<String,String>();
				userMap.put("clientId",row[0].toString());
				userMap.put("companyName",(String)row[1]);  
				userMap.put("domainName",(String)row[2]);
				userMap.put("status",(String)row[3]);			 
				userMap.put("blockStatus",(String)row[4]);
				userMap.put("clientFlag",(String)row[5]);
				dateofwlreg=dateConvert(row[6]);
				userMap.put("dateOfRegistrtion",dateofwlreg);
				userMap.put("categoryName",(String)row[7]);
				userMap.put("emailID",(String)row[8]);
				userMap.put("mobileNo",(String)row[9]);
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getAllWhiteLabelClientDetail");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getAllWhiteLabelClientDetail");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao,getAllWhiteLabelClientDetails");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	final int getNoOfRecordsAllWhiteLabelClientDetailsStatusWise(String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.clientId) from DynamicDetailsFormBean df where df.status=:status";
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of client is "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetailsStatusWise");
			System.out.println(e.toString());
			}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetailsStatusWise");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllWhiteLabelClientDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final ArrayList<HashMap<String,String>> getWhiteLabelClientDetailsStatusWise(String status,int pageNumber){
		Session session=null;         
		ArrayList<HashMap<String,String>> userDetailsList=new ArrayList<HashMap<String,String>>();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select df.clientId,df.companyName,df.domainName,df.status,df.blockStatus,df.clientFlag,df.registrationDate,df.categoryName,df.helpEmailId,df.helpMobileNo from DynamicDetailsFormBean df where df.status=:status order by df.clientId";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row[] = (Object[]) it.next();
				HashMap<String,String> userMap=new HashMap<String,String>();
				userMap.put("clientId",row[0].toString());
				userMap.put("companyName",(String)row[1]);  
				userMap.put("domainName",(String)row[2]);
				userMap.put("status",(String)row[3]);			 
				userMap.put("blockStatus",(String)row[4]);
				userMap.put("clientFlag",(String)row[5]);
				dateofwlreg=dateConvert(row[6]);
				userMap.put("dateOfRegistrtion",dateofwlreg);
				userMap.put("categoryName",(String)row[7]);
				userMap.put("emailID",(String)row[8]);
				userMap.put("mobileNo",(String)row[9]);
		      userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDaogetWhiteLabelClientDetailsStatusWise");
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
				System.out.println("Exception in getWhiteLabelClientDetailsStatusWise ");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	final int getNoOfRecordsWhiteLabelClientDetailsClientIdWise(String userId){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			String qyery="";
			Query query1=null;
			session = HibernateSession.getSessionFactory().openSession();
			qyery="select count(df.clientId) from DynamicDetailsFormBean df where  convert(varchar,df.clientId)=:userId";
			
			query1=session.createQuery(qyery).setParameter("userId",userId);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of client is "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllAdminUserDetailsMdIdWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final ArrayList<HashMap<String,String>> getWhiteLabelClientDetailsClientIdWise(String userId,int pageNumber){
	   Session session=null;         
	   ArrayList<HashMap<String,String>> userDetailsList=new ArrayList<HashMap<String,String>>();
	   int pageSize=100;
	   String dateofwlreg="";
	   try{
		   session = HibernateSession.getSessionFactory().openSession();
		   String qyery="";
		   Query query1=null;
		   int _userId=Integer.parseInt(userId);
		   qyery="select df.clientId,df.companyName,df.domainName,df.status,df.blockStatus,df.clientFlag,df.registrationDate,df.categoryName,df.helpEmailId,df.helpMobileNo from DynamicDetailsFormBean df where df.clientId=:userId order by df.clientId";
		   
		   query1=session.createQuery(qyery).setParameter("userId",_userId);
		   query1.setFirstResult(pageSize * (pageNumber - 1));
		   query1.setMaxResults(pageSize);
		   for(Iterator it=query1.iterate();it.hasNext();){
			   Object row[] = (Object[]) it.next();
			   HashMap<String,String> userMap=new HashMap<String,String>();
				userMap.put("clientId",row[0].toString());
				userMap.put("companyName",(String)row[1]);  
				userMap.put("domainName",(String)row[2]);
				userMap.put("status",(String)row[3]);			 
				userMap.put("blockStatus",(String)row[4]);
				userMap.put("clientFlag",(String)row[5]);
				dateofwlreg=dateConvert(row[6]);
				userMap.put("dateOfRegistrtion",dateofwlreg);
				userMap.put("categoryName",(String)row[7]);
				userMap.put("emailID",(String)row[8]);
				userMap.put("mobileNo",(String)row[9]);
				userDetailsList.add(userMap);
		   }
	   }
	   catch(HibernateException e)
	   {
		   System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsClientIdWise");
		   System.out.println(e.toString());
			}
	   catch(Exception e)
	   {
		   System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsClientIdWise");
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
			   System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsClientIdWise ");
			   System.out.println(e.toString());
		   }
	   }
	   return userDetailsList; 
	}
	final void activateWhiteLabelClient(String [] userIds,Map appSession){
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedWhiteLabelClientList=new ArrayList();
		ArrayList deactivatedWhiteLabelClientList=new ArrayList();
		String status="deactive";
		String adminUserMobileNo="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++){
				status="active";
				String  userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				DynamicDetailsFormBean loginInfo = (DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,_userId);
				if(loginInfo != null){	
					loginInfo.setStatus("Activate");		 
					session.update(loginInfo);			  
					txn.commit();
					status="active";
				}
				if(status.equalsIgnoreCase("active")){
					activatedWhiteLabelClientList.add(userId);			 
				}
				else{
					status="deactive";
					deactivatedWhiteLabelClientList.add(userId);
				}
			}
			appSession.put("activatedWhiteLabelClient",activatedWhiteLabelClientList);
			appSession.put("deactivatedWhiteLabelClient",deactivatedWhiteLabelClientList);
		}
		catch(HibernateException e){
			try{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,activateWhiteLabelClient");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
				         txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,activateWhiteLabelClient");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
		   }
			catch(Exception e){
				System.out.println("Exception in  WhiteLabelClientActivationDao,activateWhiteLabelClient");
				System.out.println(e.toString());
			}
		}
	}
	final void deactivateWhiteLabelClient(String [] userIds,Map appSession){
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedWhiteLabelClientList=new ArrayList();
		ArrayList deactivatedWhiteLabelClientList=new ArrayList();
		String status="active";
		String adminUserMobileNo="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++){
				status="active";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				DynamicDetailsFormBean loginInfo = (DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,_userId);
				if(loginInfo != null){
					loginInfo.setStatus("Deactive");		 
					session.update(loginInfo);
					txn.commit();
					status="deactive";
				}
				if(status.equalsIgnoreCase("deactive")){
					deactivatedWhiteLabelClientList.add(userId);			 
				}
				else{
					status="active";
					activatedWhiteLabelClientList.add(userId);
				}
			}
			appSession.put("activatedWhiteLabelClient",activatedWhiteLabelClientList);
			appSession.put("deactivatedWhiteLabelClient",deactivatedWhiteLabelClientList);
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,deactivateApiClient");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,deactivateApiClient");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in  WhiteLabelClientActivationDao,deactivateApiClient");
				System.out.println(e.toString());
			}
		}
	}
	final void partiallyActivateWhiteLabelClient(String [] userIds,String validUpto,Map appSession){
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList pactivatedWhiteLabelClientList=new ArrayList();
		ArrayList inactivatedWhiteLabelClientList=new ArrayList();
		String status="active";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++){
				status="inactive";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				DynamicDetailsFormBean loginInfo = (DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,_userId);
				if(loginInfo != null){
					loginInfo.setStatus("PartiallyActive");	
					loginInfo.setValidUpto(validUpto);	
					session.update(loginInfo);
					txn.commit();
					status="partiallyactive";
				}
				if(status.equalsIgnoreCase("partiallyactive")){
					pactivatedWhiteLabelClientList.add(userId);			 
				}
				else{
					status="inactive";
					inactivatedWhiteLabelClientList.add(userId);
				}
			}
			appSession.put("pactivatedWhiteLabelClient",pactivatedWhiteLabelClientList);
			appSession.put("inactivatedWhiteLabelClient",inactivatedWhiteLabelClientList);
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,partiallyActivateWhiteLabelClient");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,partiallyActivateWhiteLabelClient");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in  WhiteLabelClientActivationDao,partiallyActivateWhiteLabelClient");
				System.out.println(e.toString());
			}
		}
	}
	final void blockWhiteLabelClient(String [] userIds,Map appSession){
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockWhiteLabelClientList=new ArrayList();
		ArrayList unblockWhiteLabelClientList=new ArrayList();
		String status="unblock";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++){
				status="unblock";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				DynamicDetailsFormBean loginInfo = (DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,_userId);
				if(loginInfo != null){
					
					//loginInfo.setBlockStatus("blocked");		 
					// Updated By Manoj
					loginInfo.setBlockStatus("Blocked");
					session.update(loginInfo);
					txn.commit();
					status="block";
				}
		  if(status.equalsIgnoreCase("block")){
			  blockWhiteLabelClientList.add(userId);			 
		  }
		  else{
			  status="unblock";
			  unblockWhiteLabelClientList.add(userId);
		  }
			}
			appSession.put("blockedWhiteLabelClient",blockWhiteLabelClientList);
			appSession.put("unblockedWhiteLabelClient",unblockWhiteLabelClientList);
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,blockWhiteLabelClient");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in blockWhiteLabelClient");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in  WhiteLabelClientActivationDao,blockWhiteLabelClient");
				System.out.println(e.toString());
			}
		}
	}
	final void unblockWhiteLabelClient(String [] userIds,Map appSession){
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockWhiteLabelClientList=new ArrayList();
		ArrayList unblockWhiteLabelClientList=new ArrayList();
		String status="block";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++){
				status="block";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				DynamicDetailsFormBean loginInfo = (DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,_userId);
				if(loginInfo != null){
					
					//loginInfo.setBlockStatus("unblocked");		 
					// Updated By Manoj
					loginInfo.setBlockStatus("Unblocked");
					session.update(loginInfo);
					txn.commit();
					status="unblock";
				}
				if(status.equalsIgnoreCase("unblock")){
					unblockWhiteLabelClientList.add(userId);			 
				}
				else{
					status="block";
					blockWhiteLabelClientList.add(userId);
				}
			}
			appSession.put("blockWhiteLabelClientList",blockWhiteLabelClientList);
			appSession.put("unblockWhiteLabelClientList",unblockWhiteLabelClientList);
		}
		catch(HibernateException e){
			try{
				if(txn!=null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,blockWhiteLabelClient");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!=null)			 
					txn.rollback();
				 }
			catch(Exception ex){
			}
			System.out.println("Exception in  WhiteLabelClientActivationDao,blockWhiteLabelClient");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in  WhiteLabelClientActivationDao,blockWhiteLabelClient while closing connection");
				System.out.println(e.toString());
			}
		}
	}
	final int getNoOfRecordsAllWhiteLabelClientDetailsBlockStatusWise(String status){
		Session session=null;         
		int count=0;
		long check=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select count(df.clientId) from DynamicDetailsFormBean df where df.blockStatus=:status";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("Total no of client is "+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllAdminUserDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getNoOfRecordsAllAdminUserDetailsBlockStatusWise");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao, getNoOfRecordsAllAdminUserDetailsBlockStatusWise ");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	final ArrayList<HashMap<String,String>> getWhiteLabelClientDetailsBlockStatusWise(String status,int pageNumber){
		Session session=null;         
		ArrayList<HashMap<String,String>> userDetailsList=new ArrayList<HashMap<String,String>>();
		int pageSize=100;
		String dateofwlreg="";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="select df.clientId,df.companyName,df.domainName,df.status,df.blockStatus,df.clientFlag,df.registrationDate,df.categoryName,df.helpEmailId,df.helpMobileNo from DynamicDetailsFormBean df where df.blockStatus=:status order by df.clientId";
			
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row[] = (Object[]) it.next();
				HashMap<String,String> userMap=new HashMap<String,String>();
				userMap.put("clientId",row[0].toString());
				userMap.put("companyName",(String)row[1]);  
				userMap.put("domainName",(String)row[2]);
				userMap.put("status",(String)row[3]);			 
				userMap.put("blockStatus",(String)row[4]);
				userMap.put("clientFlag",(String)row[5]);
				dateofwlreg=dateConvert(row[6]);
				userMap.put("dateOfRegistrtion",dateofwlreg);
				userMap.put("categoryName",(String)row[7]);
				userMap.put("emailID",(String)row[8]);
				userMap.put("mobileNo",(String)row[9]);
		    	  userDetailsList.add(userMap);	
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsBlockStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsBlockStatusWise");
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
				System.out.println("Exception in  WhiteLabelClientActivationDao,getWhiteLabelClientDetailsBlockStatusWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	public static String dateConvert (Object inDate)
    {
        try {
         DateFormat formatter ; 
         Date date ; 
          formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          date = (Date)formatter.parse(inDate.toString());
          formatter = new SimpleDateFormat("dd-MM-yyyy");
          String outDate = formatter.format(date);
          return outDate;

          } catch (ParseException e)
          {System.out.println("Exception in WhiteLabelClientActivationDao,dataConvert");
          System.out.println(e.toString());}  
          return null;
    }
}

