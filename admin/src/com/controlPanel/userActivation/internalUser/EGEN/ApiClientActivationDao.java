package com.controlPanel.userActivation.internalUser.EGEN;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

import com.formBean.EGEN.APIClientAuthenticationDetailsFormBean;

public class ApiClientActivationDao
{
	public static int getNoOfRecordsAllApiClientDetails()
	{
		Session session=null;         
		long count=0;
		int check=0;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String qyery="select count(af.corporateAgentId) from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId";
			//System.out.println("qyery is :"+qyery);
			Query query1=session.createQuery(qyery);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				count=(Long)row;
				check=(int)count; 
			}
			//System.out.println("count>>>>>>>>>>>>>>>>>>>>>>>>>>>"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetails");
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
				System.out.println("Exception in getNoOfRecordsAllApiClientDetails ");
				System.out.println(e.toString());
			}
		}
		return check; 
	}

	public static ArrayList getAllApiClientDetail(int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String qyery="select af.corporateAgentId,af.companyName,af.firstName,af.middleName,af.lastName,alf.status,aaf.totalBalanceAmount,af.mobileNo,af.emailId,alf.blockStatus,alf.authorizedIP1,alf.authorizedIP2 from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId order by af.dateOfJoining desc";
			System.out.println("qyery is : "+qyery);
			Query query1=session.createQuery(qyery);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("corporateAgentId",row[0]);
				userMap.put("companyName",row[1]);
				String middleName=(String)row[3];
				
				if(middleName.equalsIgnoreCase("NA"))
				{
					middleName="";
				}
				String name=(String)row[2]+" "+middleName+" "+(String)row[4];
				userMap.put("userName",name);
				userMap.put("loginStatus",row[5]);
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(row[6]));
				userMap.put("userMobileNo",row[7]);
				userMap.put("userEmailId",row[8]);
				userMap.put("blockStatus",row[9]);
				userMap.put("ip1",row[10]);
				userMap.put("ip2",row[11]);
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getAllApiClientDetail");
			System.out.println(e.toString());
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAllApiClientDetail");
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
				System.out.println("Exception in getAllApiClientDetail ");
				System.out.println(e.toString());}
		}
		return userDetailsList; 
	}

	public static int getNoOfRecordsAllApiClientDetailsStatusWise(String status)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String qyery="select count(af.corporateAgentId) from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId and alf.status=:status";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("count>>>>>>>>>>>>>>>>>>>>>>>>>>>"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetailsStatusWise");
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
				System.out.println("Exception in getNoOfRecordsAllApiClientDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	public static ArrayList getApiClientDetailsStatusWise(String status,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			String qyery="select af.corporateAgentId,af.companyName,af.firstName,af.middleName,af.lastName,alf.status,aaf.totalBalanceAmount,af.mobileNo,af.emailId,alf.blockStatus,alf.authorizedIP1,alf.authorizedIP2 from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId and alf.status=:status order by af.dateOfJoining desc";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query1=session.createQuery(qyery).setParameter("status",status);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("corporateAgentId",row[0]);
				userMap.put("companyName",row[1]);		  
				String middleName=(String)row[3];
				
				if(middleName.equalsIgnoreCase("NA"))
				{
					middleName="";
				}
				
				String name=(String)row[2]+" "+middleName+" "+(String)row[4]; 		 
				userMap.put("userName",name);
				userMap.put("loginStatus",row[5]);			 
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(row[6]));
				userMap.put("userMobileNo",row[7]);
				userMap.put("userEmailId",row[8]);
				userMap.put("blockStatus",row[9]);
				userMap.put("ip1",row[10]);
				userMap.put("ip2",row[11]);
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getAdminUserDetailsStatusWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAdminUserDetailsStatusWise");
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
				System.out.println("Exception in getAdminUserDetailsStatusWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}
	
	public static int getNoOfRecordsApiClientDetailsUserIdWise(String userId)
	{
		Session session=null;         
		int count=0;
		long check=0;
		try
		{
			String qyery="";
			Query query1=null;
			session = HibernateSession1.getSessionFactory().openSession();
			qyery="select count(af.corporateAgentId) from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId and convert(varchar,af.corporateAgentId)=:userId";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			query1=session.createQuery(qyery).setParameter("userId",userId);
			
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("count>>>>>>>>>>>>>>>>>>>>>>>>>>>"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getNoOfRecordsAllAdminUserDetailsMdIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNoOfRecordsAllAdminUserDetailsMdIdWise");
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
				System.out.println("Exception in getNoOfRecordsAllAdminUserDetailsMdIdWise ");
				System.out.println(e.toString());
			}
		}
		return count; 
	}

	public static ArrayList getApiClientDetailsUserIdWise(String userId,int pageNumber)
	{
		Session session=null;         
		ArrayList userDetailsList=new ArrayList();
		int pageSize=100;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession();
			String qyery="";
			Query query1=null;
			qyery="select af.corporateAgentId,af.companyName,af.firstName,af.middleName,af.lastName,alf.status,aaf.totalBalanceAmount,af.mobileNo,af.emailId,alf.blockStatus,alf.authorizedIP1,alf.authorizedIP2 from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId and convert(varchar,af.corporateAgentId)=:userId order by af.dateOfJoining desc";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			query1=session.createQuery(qyery).setParameter("userId",userId);
			query1.setFirstResult(pageSize * (pageNumber - 1));
			query1.setMaxResults(pageSize);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				HashMap userMap=new HashMap();
				userMap.put("corporateAgentId",row[0]);
				userMap.put("companyName",row[1]);		  
				String middleName=(String)row[3];
				
				if(middleName.equalsIgnoreCase("NA"))
				{
					middleName="";
				}
				
				String name=(String)row[2]+" "+middleName+" "+(String)row[4];		 
				userMap.put("userName",name);
				userMap.put("loginStatus",row[5]);			 
				DecimalFormat f = new DecimalFormat("0.00");
				userMap.put("userBalance",f.format(row[6]));
				userMap.put("userMobileNo",row[7]);
				userMap.put("userEmailId",row[8]);
				userMap.put("blockStatus",row[9]);
				userMap.put("ip1",row[10]);
				userMap.put("ip2",row[11]);
				userDetailsList.add(userMap);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getApiClientDetailsUserIdWise");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getApiClientDetailsUserIdWise");
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
				System.out.println("Exception in getApiClientDetailsUserIdWise");
				System.out.println(e.toString());
			}
		}
		return userDetailsList; 
	}

	public static int getNoOfRecordsAllApiClientDetailsUsingAdvanceSearchOption(String panNo,String dateOfBirth,String pincode,String firstName,String lastName,String alternateMobileNo,String state,String city,String district,String fatherName,String motherName)
	{
		Session session=null;         
		int count=0;
		long check=0;
		String name=firstName+" "+lastName;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession();
			String qyery="";
			Query query1=null; 
			qyery="select count(af.corporateAgentId) from APIClientDetailsFormBean af,APIClientAuthenticationDetailsFormBean alf,ApiClientAmountFormBean aaf where alf.corporateAgentId=af.corporateAgentId and af.corporateAgentId=aaf.corporateAgentId and (af.panNo=:panNo or af.dob=:dateOfBirth or af.pincode=:pincode or af.name like :name or af.resMobileNo=:alternateMobileNo or af.state=:state or af.city=:city or af.district=:District or af.fatherName=:fatherName or af.motherName=:motherName)";
			query1=session.createQuery(qyery).setParameter("panNo",panNo).setParameter("dateOfBirth",dateOfBirth).setParameter("pincode",pincode).setParameter("name","%"+name+"%").setParameter("alternateMobileNo",alternateMobileNo).setParameter("state",state).setParameter("city",city).setParameter("District",district).setParameter("fatherName",fatherName).setParameter("motherName",motherName);
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				check=(Long)row;
				count=(int)check; 
			}
			//System.out.println("count>>>>>>>>>>>>>>>>>>>>>>>>>>>"+count);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetailsUsingAdvanceSearchOption");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in getNoOfRecordsAllApiClientDetailsUsingAdvanceSearchOption");
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
				System.out.println("Exception in getNoOfRecordsAllApiClientDetailsUsingAdvanceSearchOption");
				System.out.println(e.toString());
			}
		}
		return count; 
	}
	
	public static void activateApiClient(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedApiClientList=new ArrayList();
		ArrayList deactivatedApiClientList=new ArrayList();
		String status="deactive";
		String adminUserMobileNo="";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++)
			{
				status="active";
				String  userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_userId);
				if(loginInfo != null)
				{	
					loginInfo.setStatus("Activate");		 
					session.update(loginInfo);			  
					txn.commit();
					status="active";
				}
				if(status.equalsIgnoreCase("active"))
				{
					activatedApiClientList.add(userId);			 
				}
				else
				{
					status="deactive";
					deactivatedApiClientList.add(userId);
				}
			}
			appSession.put("activatedApiClientList",activatedApiClientList);
			appSession.put("deactivatedApiClientList",deactivatedApiClientList);
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
			System.out.println("Exception in activateApiClient");
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
			System.out.println("Exception in activateApiClient");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in activateApiClient");
				System.out.println(e.toString());
			}
		}
	}
	
	public static void deactivateApiClient(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList activatedApiClientList=new ArrayList();
		ArrayList deactivatedApiClientList=new ArrayList();
		String status="active";
		String adminUserMobileNo="";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++)
			{
				status="active";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_userId);
				if(loginInfo != null)
				{
					//System.out.println("inside update");
					loginInfo.setStatus("Deactive");		 
					session.update(loginInfo);
					txn.commit();
					//System.out.println("after update");
					status="deactive";
				}
				if(status.equalsIgnoreCase("deactive"))
				{
					deactivatedApiClientList.add(userId);			 
				}
				else{
					status="active";
					activatedApiClientList.add(userId);
				}
			}
			appSession.put("activatedApiClientList",activatedApiClientList);
			appSession.put("deactivatedApiClientList",deactivatedApiClientList);
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
			System.out.println("Exception in deactivateApiClient");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in deactivateApiClient");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in deactivateApiClient");
				System.out.println(e.toString());
			}
		}
	}
	
	public static void blockApiClient(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedApiClientList=new ArrayList();
		ArrayList unblockedApiClientList=new ArrayList();
		String status="unblock";
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			for(int i=0;i<userIds.length;i++)
			{
				status="unblock";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_userId);
				if(loginInfo != null)
				{
					//System.out.println("inside update");
					//loginInfo.setBlockStatus("blocked");
					// Updatde By Manoj
					loginInfo.setBlockStatus("Blocked");
					session.update(loginInfo);
					txn.commit();
					//System.out.println("after update");
					status="blocked";
				}
				if(status.equalsIgnoreCase("blocked"))
				{
					blockedApiClientList.add(userId);			 
				}
				else
				{
					status="unblock";
					unblockedApiClientList.add(userId);
				}
			}
			appSession.put("blockedApiClientList",blockedApiClientList);
			appSession.put("unblockedApiClientList",unblockedApiClientList);
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
			System.out.println("Exception in blockApiClient");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in blockApiClient");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in blockApiClient");
				System.out.println(e.toString());
			}
		}
	}
	
	public static void unblockApiClient(String [] userIds,Map appSession)
	{
		HashMap map=new HashMap();
		Session session=null;
		Transaction txn=null;
		ArrayList blockedApiClientList=new ArrayList();
		ArrayList unblockedApiClientList=new ArrayList();
		String status="block";
		Connection con=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			con=session.connection();
			for(int i=0;i<userIds.length;i++)
			{
				status="block";
				String userId=userIds[i];
				int _userId=Integer.parseInt(userId);
				txn=session.beginTransaction();
				APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_userId);
				if(loginInfo != null)
				{
					//System.out.println("inside update");
					//loginInfo.setBlockStatus("unblocked");
					// updated By Manoj
					loginInfo.setBlockStatus("Unblocked");
					session.update(loginInfo);
					txn.commit();
					//System.out.println("after update");
					status="unblocked";
				}
				if(status.equalsIgnoreCase("unblocked"))
				{
					unblockedApiClientList.add(userId);			 
				}
				else{
					status="block";
					blockedApiClientList.add(userId);
				}
			}
			appSession.put("blockedApiClientList",blockedApiClientList);
			appSession.put("unblockedApiClientList",unblockedApiClientList);
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
			System.out.println("Exception in unblockApiClient");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try{
				if(txn!=null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in unblockApiClient");
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
				System.out.println("Exception in unblockApiClient");
				System.out.println(e.toString());
			}
		}
	}
	
	public HashMap  getApiIp(String id)
	{
		Session session=null;
		Transaction ts=null;
		String sql="";
		Query query1=null;
		HashMap map=new HashMap();
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			sql="select alf.status,alf.authorizedIP1,alf.authorizedIP2 from APIClientAuthenticationDetailsFormBean alf where convert(varchar,alf.corporateAgentId)=:userId ";
			//System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+sql);
			query1=session.createQuery(sql).setParameter("userId",id);
			List list=query1.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				//System.out.println("we are in loop "+row[0].toString());
				map.put("status",row[0].toString());
				map.put("IP1",row[1].toString());
				map.put("IP2",row[2].toString());
			}
		}catch(Exception E)
		{		
			System.out.println("Exception in getApiIp");
			System.out.println(E.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in getApiIp");
				System.out.println(e.toString());			
			}			
		}
		return map;
	}
	
	public String  updateApiIp(String id,String ip1,String ip2,String status)
	{
		Session session=null;
		Transaction ts=null;
		String sql="";
		Query query=null;
		try
		{
			session = HibernateSession1.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			sql="update OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details set Authorized_IP1='"+ip1+"',Authorized_IP2='"+ip2+"',Status='"+status+"' where convert(varchar,Corporate_Agent_Id)='"+id+"' ";
			//live server Query
			//sql="update OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details set Authorized_IP1='"+ip1+"',Authorized_IP2='"+ip2+"',Status='"+status+"' where convert(varchar,Corporate_Agent_Id)='"+id+"' ";
			query=session.createSQLQuery(sql);
			int result=query.executeUpdate();
			ts.commit();
			//System.out.println("Successfully updated");
			return "success";
							
		}catch(Exception E)
		{		
			System.out.println("Exception in updateApiIp");
			System.out.println(E.toString());
			ts.rollback();
			return "err";
			
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}catch(Exception e)
			{
				System.out.println("Exception in updateApiIp");
				System.out.println(e.toString());		
			}			
		}
	 }
}