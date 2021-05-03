package com.controlPanel.accountMgt.commissionTDS;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;
 /*
 * Created Date   : 23 Sep 2012 Sunday 12:00 AM
 * Created Matter : This class used as Form Bean for Commission and TDS Adjustment
 */

public class CommTDSAdjDao 
{
	
	public  HashMap<String,Object> getAccountAgentData(String clientFlag,String requestUserId) 
	{
		String Status="NotFound";
		HashMap<String,Object> DataMap=new HashMap<String,Object>();
		Session session=null;
		
		Query query1 = null;
		int agentid =0;
		String AvilableBalance="";
		double tot=0.0;
		double used=0.00;
		double cuttoff=0.00;
		DecimalFormat dff = new DecimalFormat("##.#### ");
		
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			 Query query=session.createQuery("select ADF.agentId,ADF.companyName,ADF.mobileNo,ADF.name,AAF.totalCash,AAF.usedCash,AAF.cutOffAmount " +
	       		" from AgentDetailsFormBean ADF,AgentAmountFormBean AAF where ADF.agentId=AAF.agentId and  " +
	       		" ADF.agentInitial+convert(varchar,ADF.agentId)=:id").setParameter("id",requestUserId);
	         Iterator<?> it=query.iterate();
			
			 while(it.hasNext())
			 {
				 Object[] row = (Object[]) it.next();
				 agentid=(Integer)row[0];
				 tot=(Double) row[4];
				 used=(Double)row[5];
				 cuttoff=(Double)row[6];
				 AvilableBalance=dff.format(tot-used+cuttoff);
				 DataMap.put("userId",row[0]);
				 DataMap.put("agencyName",row[1]);
				 DataMap.put("mobileNo",row[2]);
				 DataMap.put("Name",row[3]);
				 DataMap.put("AvilableBalance",AvilableBalance);
				 DataMap.put("requestUserId",requestUserId);
			 }
			 
			 if(clientFlag.equalsIgnoreCase("0") ||clientFlag.equalsIgnoreCase("2") )
			 {
				query1=session.createQuery("select ALIFB.loginStatus from AgentLoginInfoFormBean ALIFB where ALIFB.userId=:Agentid")
				.setParameter("Agentid",agentid);
			 }
			 if(clientFlag.equalsIgnoreCase("1"))
			 {
				 query1=session.createQuery("select RALIFB.loginStatus from REPAgentLoginInfoFormBean RALIFB where RALIFB.userId=:Agentid").setParameter("Agentid",agentid);
			 }
			 Iterator<?> it1=query1.iterate();
			 
			 while(it1.hasNext()) 
			 {
				 Object row = (Object) it1.next();
				 Status=(String)row;
			 }
			 DataMap.put("Status",Status);
		}
		catch (Exception ex) 
		{
			System.out.println("Exception in CommTDSAdjDao,getAccountAgentData");
			System.out.println(ex.toString());
		}
		finally 
		{
			session.flush();
			session.close();
		}
		return DataMap;
	}
	
	public  HashMap<String, Object> getAccountPortalData(String requestUserId)
	{
		 HashMap<String,Object> DataMap=new HashMap<String,Object>();
		 Session session=HibernateSession.getSessionFactory().openSession();
		
		 Query query = null;
		 String initial="";
		 String firstName="";
		 String middleName="";
		 String lastName="";
		 String Name="";
		 int UserId=Integer.parseInt(requestUserId);
		 
		 try
		 {
			 query=session.createQuery("select DDF.companyName,ADUF.firstName,ADUF.lastName," +
					 "ADUF.officialNumber, UAFB.availableBalanceAmount,LIFB.loginStatus,ADUF.userId " +
					 "from DynamicDetailsFormBean DDF,AdminUserFormBean ADUF,UserAmountFormBean UAFB,LoginInfoFormBean LIFB " +
			 "where DDF.clientId=ADUF.portalId and  ADUF.userId=UAFB.userId and UAFB.userId=LIFB.userId and ADUF.userId=:id")
			 .setParameter("id",UserId);
	
			 Iterator<?> it=query.iterate();
	      
			 while(it.hasNext())
			 {
				 Object[] row = (Object[]) it.next();
				 
				 firstName=(String) row[1];
				 
				 lastName=(String) row[2];
				 Name=firstName+" "+lastName;
				 DataMap.put("agencyName",row[0]);
				 DataMap.put("mobileNo",row[3]);
				 DataMap.put("Name",Name);
				 DataMap.put("Status",row[5]);
				 DataMap.put("AvilableBalance",row[4]);
				 DataMap.put("userId",row[6]);
			 }
		 } catch (Exception ex)
		 {
			 System.out.println("Exception in CommTDSAdjDao,getAccountPortalData");
			 System.out.println(ex.toString());
		 }
		 finally
		 {
			 session.flush();
			 session.close();
		 }
		return DataMap;
	}
	
	public HashMap<String, Object> getAccountAPIData(String requestUserId) 
	{
		 HashMap<String,Object> DataMap=new HashMap<String,Object>();
		 Session session=null;
		 
		 Query query = null;
		 String initial="";
		 String firstName="";
		 String middleName="";
		 String lastName="";
		 String Name="";
		 String AvilableBalance="";
		 double tot=0.0;
		 double used=0.00;
		 double cuttoff=0.00;
		 DecimalFormat dff = new DecimalFormat("##.#### ");
	
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			query=session.createQuery("select ADF.firstName,ADF.middleName,ADF.lastName,ADF.companyName,ADF.mobileNo," +
					"AADF.loginStatus,AAF.totalCash,AAF.usedCash,AAF.cutOffAmount,ADF.corporateAgentId from APIClientDetailsFormBean ADF," +
					"APIClientAuthenticationDetailsFormBean AADF,ApiClientAmountFormBean AAF where " +
					"ADF.corporateAgentId=AADF.corporateAgentId and AADF.corporateAgentId=AAF.corporateAgentId and " +
			"ADF.corporateAgentInitial+convert(varchar,ADF.corporateAgentId)=:id").setParameter("id",requestUserId);
	
			Iterator<?> it=query.iterate();
	     
			while(it.hasNext())
			{
				Object[] row = (Object[]) it.next();
			    firstName=(String) row[0];
			    middleName=(String) row[1];
			    lastName=(String) row[2];
			    
			    if(middleName.equalsIgnoreCase("NA"))
			    {
			    	middleName="";
			    }
			    
			    Name=initial+firstName+middleName+lastName;
			    tot=(Double) row[6];
			    used=(Double)row[7];
			    cuttoff=(Double)row[8];
			    AvilableBalance=dff.format(tot-used+cuttoff);
			    DataMap.put("agencyName",row[3]);
			    DataMap.put("mobileNo",row[4]);
			    DataMap.put("Name",Name);
			    DataMap.put("Status",row[5]);
			    DataMap.put("AvilableBalance",AvilableBalance);
			    DataMap.put("userId",row[9]);
			}
		}catch (Exception ex) 
		{
			System.out.println("Exception in CommTDSAdjDao,getAccountAPIData");
			System.out.println(ex.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return DataMap;
	}
	
	public  HashMap<String, Object> getAccountData(String userType,String requestUserId) 
	{
		HashMap<String,Object> DataMap=new HashMap<String,Object>();
		Session session=null;
		
		Query query = null;
		String AvilableBalance="";
		double tot=0.0;
		double used=0.00;
		double cuttoff=0.00;
		DecimalFormat dff = new DecimalFormat("##.#### ");
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			if(userType.equalsIgnoreCase("Distributor"))
			{
				query=session.createQuery("select DDF.companyName,DDF.mobileNo,DDF.name,DAF.totalCash,DAF.usedCash,DAF.cutOffAmount," +
						"DLI.loginStatus,DDF.distributorId  from DistributorDetailsFormBean DDF,DistributorAmountFormBean DAF," +
						"DistributorLoginInfoFormBean DLI where DDF.distributorId=DAF.distributorId and DAF.distributorId=DLI.userId " +
				"and  DDF.distributorInitial+convert(varchar,DDF.distributorId)=:id").setParameter("id",requestUserId);
		    }
	
			if(userType.equalsIgnoreCase("Master Distributor"))
			{
		        query=session.createQuery("select MDF.companyName,MDF.mobileNo,MDF.name,MAF.totalCash,MAF.usedCash,MAF.cutOffAmount," +
		        		"MLI.loginStatus,MDF.mdId  from MdsDetailsFormBean MDF,MdsAmountFormBean MAF,MdsLoginInfoFormBean MLI " +
		        "where MDF.mdId=MAF.mdId and MAF.mdId=MLI.userId and  MDF.mdInitial+convert(varchar,MDF.mdId)=:id")
		        .setParameter("id",requestUserId);
	     	}
			Iterator<?> it=query.iterate();
			
			while(it.hasNext()) 
			{
				Object[] row = (Object[]) it.next();
				tot=(Double) row[3];
				used=(Double)row[4];
				cuttoff=(Double)row[5];
				AvilableBalance=dff.format(tot-used+cuttoff);
				DataMap.put("agencyName",row[0]);
				DataMap.put("mobileNo",row[1]);
				DataMap.put("Name",row[2]);
				DataMap.put("Status",row[6]);
				DataMap.put("AvilableBalance",AvilableBalance);
				DataMap.put("userId",row[7]);
			}
		}
		catch (Exception ex) 
		{
			System.out.println("Exception in CommTDSAdjDao,getAccountData");
			System.out.println(ex.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return DataMap;
	}	
	
	public  String updateAgentCommAmount(String iDNo, String requestUserId,double amount,String loginType, String loginUserid, String actionType,
			String internalRemark, String externalRemark, int userOnlyId,String ipAddress,String UserType) 
	{
		Session session=null;
		   
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		   
		SimpleDateFormat y=new SimpleDateFormat("yyyy");
		String year=y.format(sqlDate);
		SimpleDateFormat m=new SimpleDateFormat("MM");
		String month=m.format(sqlDate);		
		SimpleDateFormat d=new SimpleDateFormat("dd");
		String day=d.format(sqlDate);
		String status="invalid";
		CallableStatement CS=null;
		ResultSet rs=null;
		Connection con=null;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			session.beginTransaction();
			con=session.connection();
			CS=con.prepareCall("{call  Admin_Commision_Management(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			CS.setString(1,iDNo);
			CS.setDouble(2,amount);
			CS.setString(3, internalRemark);
			CS.setString(4, externalRemark);
			CS.setInt(5, userOnlyId);
			CS.setString(6, year);
			CS.setString(7, month);
			CS.setString(8, day);		     
			CS.setString(9,actionType);
			CS.setString(10,ipAddress);
			CS.setString(11, UserType);
			CS.setString(12, requestUserId);
		     
			CS.setString(13, loginUserid);
			CS.setString(14, loginType);
			rs=CS.executeQuery();
			session.getTransaction().commit();
			while(rs.next())
			{
				status=rs.getString(1);
			}
				// System.out.println("status-----"+status);
			}catch (Exception ex) 
			{
				System.out.println("Exception in CommTDSAdjDao,updateAgentCommAmount");
				System.out.println(ex.toString());
				status="invalid";
				
			}
			finally 
			{
				try {
					
					if(rs!=null)rs.close();
					if(CS!=null)CS.close();
					if(con!=null)con.close();
					if(session!=null)session.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			return status;
	}
	
	public String updateAgentTDSAmount(String iDNo,String requestUserId,double amount,String loginType,String loginUserid,String actionType,String internalRemark,
			String externalRemark,int userOnlyId,String ipAddress,String UserType)
	{
		Session session=null;
		   
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		   
		SimpleDateFormat y=new SimpleDateFormat("yyyy");
		String year=y.format(sqlDate);
		SimpleDateFormat m=new SimpleDateFormat("MM");
		String month=m.format(sqlDate);
		SimpleDateFormat d=new SimpleDateFormat("dd");
		String day=d.format(sqlDate);
		String status="invalid";
		CallableStatement CS=null;
		ResultSet rs=null;
		Connection con=null;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			session.beginTransaction();
			con=session.connection();
			CS=con.prepareCall("{call  Admin_TDS_Management(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				
			String test=" '12013017144401712631',1100,'test by vibhor','Test by vibhor','63','2012','09','11'," +
			"'credit','192.168.4.182','Distributor','DSUP2863',1,'superAdmin'";
			
			CS.setString(1,iDNo);
			CS.setDouble(2,amount);
			CS.setString(3, internalRemark);
			CS.setString(4, externalRemark);
			CS.setInt(5, userOnlyId);
			CS.setString(6, year);
			CS.setString(7, month);
			CS.setString(8, day);		     
			CS.setString(9,actionType);
			CS.setString(10,ipAddress);
			CS.setString(11, UserType);
			CS.setString(12, requestUserId);		     
			CS.setString(13, loginUserid);
			CS.setString(14, loginType);
			rs=CS.executeQuery();
			session.getTransaction().commit();
			
			while(rs.next())
			{
				status=rs.getString(1);
			}
				// System.out.println("status-----"+status);
		}catch (Exception ex) 
		{
			System.out.println("Exception in CommTDSAdjDao,updateAgentTDSAmount");
			System.out.println(ex.toString());
			status="invalid";
			session.getTransaction().rollback();
		}
		finally
		{
			try {
				if(rs!=null)rs.close();
				if(CS!=null)CS.close();
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public String CheckUserAmount(double amount,String actionType, int userOnlyId, String userType)
	{
		Session session=null;
		String status="invalid";
		String sql="";
		Connection con=null;
	    try 
		{
	    	session=HibernateSession.getSessionFactory().openSession();
			session.beginTransaction();
			con=session.connection();
			
			if(userType.equalsIgnoreCase("Agent"))
			{
				sql="select (TotCash-usedcash) as balance from agent_amount where agent_id='"+userOnlyId+"'";
			}else if(userType.equalsIgnoreCase("Distributor"))
			{
				sql="select (TotCash-usedcash) from distributor_amount where distributor_id='"+userOnlyId+"'";
			}else if(userType.equalsIgnoreCase("Master Distributor"))
			{
				sql="select (Total_Cash-Used_Cash) from md_amount where md_id='"+userOnlyId+"'";
			}else if(userType.equalsIgnoreCase("API/EGEN Partner"))
			{
				sql="select (Total_Cash-Used_Cash) as balance from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount where Corporate_Agent_Id='"+userOnlyId+"'";
			}else if(userType.equalsIgnoreCase("Portal"))
			{
				sql="select (Total_cash-Used_cash) as balance from admin_user_amount_details where Portal_id='"+userOnlyId+"'";
			}
			
			System.out.println(sql);
			double availBal=Double.parseDouble(session.createSQLQuery(sql).uniqueResult().toString());
			System.out.println("availBal is "+availBal);
			if(amount>availBal)
			{
				status="invalid";
			}
			else
			{
				status="valid";
			}
			
			//System.out.println("status-----"+status);
		}catch (Exception ex) 
		{
			System.out.println("Exception in AccountAdjustmentDao");
			System.out.println(ex.toString());
			status="invalid";
		}
		finally
		{

			try {
				if(con!=null)con.close();
				if(session!=null)session.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return status;
	}
}
