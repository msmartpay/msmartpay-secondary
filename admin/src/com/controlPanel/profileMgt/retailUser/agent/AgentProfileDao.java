package com.controlPanel.profileMgt.retailUser.agent;

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
import java.util.Random;
import java.util.StringTokenizer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.formBean.agent.AgentDetailsFormBean;
import com.formBean.agent.AgentLoginInfoFormBean;
import com.formBean.agent.REPAgentLoginInfoFormBean;

public class AgentProfileDao
{
	final HashMap getDynamicDetails(int agentId)
	{
		HashMap map=new HashMap();
		Session session=null;
		int portalId=0;
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query2=session.createQuery("select df.clientId from DynamicDetailsFormBean df,AdminUserFormBean af,MdsDetailsFormBean mf,DistributorDetailsFormBean ddf,AgentDetailsFormBean lf where lf.distId=ddf.distributorId and ddf.mdId=mf.mdId and mf.clientId=af.portalId and af.portalId=df.clientId and  lf.agentId=:userId").setParameter("userId",agentId);
			for(Iterator it=query2.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalId=(Integer)row;
			}
			
			Query query1=session.createQuery("select d.adminLoginUrl,d.helpMobileNo,d.helpEmailId,d.domainName,d.companyName,d.mdLoginUrl,d.agentLoginUrl,d.distributorLoginUrl,d.clientFlag  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			List list=query1.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				map.put("adminLoginUrl",(String)row[0]);
				map.put("helpMobileNo",(String)row[1]);
				map.put("helpEmailId",(String)row[2]);
				map.put("domainName",(String)row[3]);
				map.put("companyName",(String)row[4]);
				map.put("mdLoginUrl",(String)row[5]);
				map.put("agentLoginUrl",(String)row[6]);
				map.put("distributorLoginUrl",(String)row[7]);
				map.put("clientFlag",(String)row[8]);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentProfileDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentProfileDao,getDynamicDetails");
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
				System.out.println("Exception in AgentProfileDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}
	
	final ArrayList getAgentTurnOver(int agentId)
	{
		ArrayList turnOverList=new ArrayList();
		Session session=null;
		Transaction txn=null;
		Transaction txn1=null;
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			int totalNoOfTranscations=0;
			double totalTransactionAmount=0.00;
			con=session.connection(); 		  
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt.setString(1,"countAgentTurnOverAmount");
			cstmt.setInt(2,agentId);
			ResultSet rs=cstmt.executeQuery();
			
			while(rs.next())
			{
				totalTransactionAmount=rs.getDouble(1);
				totalNoOfTranscations=rs.getInt(2);
			}
			
			txn1=session.beginTransaction();
			CallableStatement cstmt1 =null;
			cstmt1=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt1.setString(1,"agent");
			cstmt1.setInt(2,agentId);
			ResultSet rs1=cstmt1.executeQuery();
			
			while(rs1.next())
			{
        	   HashMap map=new HashMap();
        	   double serviceAmount=rs1.getDouble(1);
        	   String service=rs1.getString(2);
        	   int noOfTransctions=rs1.getInt(3);
        	   double TurnOver=(serviceAmount*100)/totalTransactionAmount;
        	   double transactionTurnOver=(noOfTransctions*100)/totalNoOfTranscations;
        	   DecimalFormat df = new DecimalFormat("#.##");
        	   String serviceTurnOver=df.format(TurnOver);
        	   map.put("serviceTurnOver",serviceTurnOver);
        	   map.put("transactionTurnOver",transactionTurnOver);
        	   map.put("service",service);
        	   turnOverList.add(map);
			}
		}
		catch(HibernateException e)
		{
			if(txn!= null)			 
				txn.rollback();
			if(txn1!= null)			 
				txn1.rollback();
			System.out.println("Exception in AgentProfileDao,getAgentTurnOver");
			System.out.println(e.toString());
		}
		catch(SQLException e)
		{
			if(txn!= null)			 
				txn.rollback();
			if(txn1!= null)			 
				txn1.rollback();
			System.out.println("Exception in AgentProfileDao,getAgentTurnOver");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			if(txn!= null)			 
				txn.rollback();
			if(txn1!= null)			 
				txn1.rollback();
			System.out.println("Exception in AgentProfileDao,getAgentTurnOver");
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
				System.out.println("Exception in AgentProfileDao,getAgentTurnOver");
				System.out.println(e.toString());
			}
		}
		return turnOverList;
	}
	
	public final HashMap getAgentProfileDetails(String agentId)
	{
		Session session=null;  
		HashMap agentprofileDetailsMap=new HashMap();
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			int _agentId=0;
			String clientFlag="";
			String agentLoginUrl="";
			String mpin="";
			
			Query query1=session.createQuery("select af.agentId,df.clientFlag,df.agentLoginUrl,af.MPIN from AgentDetailsFormBean af,DynamicDetailsFormBean df,AgentLoginInfoFormBean alf where (af.agentInitial+convert(varchar,af.agentId))=:agentId and df.agentUserType=alf.userType and af.agentId=alf.userId").setParameter("agentId",agentId);;
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				_agentId=(Integer)row[0];
				clientFlag=(String)row[1];
				agentLoginUrl=(String)row[2];
				mpin=(String)row[3];
			}
			//Query query3=session.createQuery("select atf.dateOfTransaction,atf.requestedAmount from AgentTransactionDetailsFormBean atf where atf.idNo=(select max(atd.idNo) from AgentTransactionDetailsFormBean atd where atd.agentId=:agentId)").setParameter("agentId",_agentId);
			String sql="select top 1 Date_of_Transaction,ReqAmount from agent_transaction_details where Agent_id=convert(varchar(12),"+_agentId+") order by ID_No desc";
			/*Query query3=session.createSQLQuery(sql);
			List list=query3.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				DecimalFormat f = new DecimalFormat("0.00");
				agentprofileDetailsMap.put("lastDateOfTransaction",row[0]);
				agentprofileDetailsMap.put("lastTransactionAmount",f.format(row[1]));
			}*/
			agentprofileDetailsMap.put("lastDateOfTransaction","");
			agentprofileDetailsMap.put("lastTransactionAmount",1000);
			/*sql="select top 1 Date_of_Transaction from agent_transaction_details where Agent_id=convert(varchar(12),"+_agentId+") and Action_on_bal_amt='credit' order by ID_No desc" ;
			//Query query4=session.createQuery("select top 1 Date_of_Transaction from agent_transaction_details where Agent_id=convert(varchar(12),"+_agentId+") and Action_on_bal_amt='credit' order by ID_No desc");
			Query query4=session.createSQLQuery(sql);
			List list=query4.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{
				Object row = (Object) it.next();
				agentprofileDetailsMap.put("lastDateOfTBTaken",row);
			}*/
			agentprofileDetailsMap.put("lastDateOfTBTaken","");
//			System.out.println("_agentId is>>"+_agentId+"clientFlag is>>"+clientFlag+"agentLoginUrl>>>>>"+agentLoginUrl);
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.mobileNo,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount,alf.userName,alf.password,af.MPIN,aaf.lastAmount,alf.APIN,af.kycStatus,af.aadhaarNo from AgentDetailsFormBean af,AgentAmountFormBean aaf,AgentLoginInfoFormBean alf where af.agentId=aaf.agentId and aaf.agentId=alf.userId and alf.userId=:agentId";
				System.out.println("qyery : "+qyery);
			}
			if(clientFlag.equals("1"))
			{
				qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.mobileNo,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount,alf.userName,alf.password,af.MPIN,aaf.lastAmount,alf.APIN from AgentDetailsFormBean af,AgentAmountFormBean aaf,REPAgentLoginInfoFormBean alf where af.agentId=aaf.agentId and aaf.agentId=alf.userId and alf.userId=:agentId";
				System.out.println("qyery : "+qyery);
			}
//			System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query5=session.createQuery(qyery).setParameter("agentId",_agentId);
			List list=query5.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				agentprofileDetailsMap.put("firstName",(row[0]!=null?row[0]:""));
				agentprofileDetailsMap.put("lastName",(row[1]!=null?row[1]:""));
				agentprofileDetailsMap.put("dateOfBirth",(row[2]!=null?row[2]:""));
				agentprofileDetailsMap.put("gender",(row[3]!=null?row[3]:""));
				agentprofileDetailsMap.put("companyType",(row[4]!=null?row[4]:""));			  
				agentprofileDetailsMap.put("companyName",(row[5]!=null?row[5]:""));			  
				agentprofileDetailsMap.put("emailID",(row[6]!=null?row[6]:""));
				agentprofileDetailsMap.put("mobileNo",(row[7]!=null?row[7]:""));
				agentprofileDetailsMap.put("address1",(row[8]!=null?row[8]:""));
				agentprofileDetailsMap.put("address2",(row[9]!=null?row[9]:""));			  
				agentprofileDetailsMap.put("state",(row[10]!=null?row[10]:""));
				agentprofileDetailsMap.put("district",(row[11]!=null?row[11]:""));
				agentprofileDetailsMap.put("city",(row[12]!=null?row[12]:""));
				agentprofileDetailsMap.put("pincode",(row[13]!=null?row[13]:""));			 
				agentprofileDetailsMap.put("panNo",(row[14]!=null?row[14]:""));
				String date=dateConvert(row[15]);
				agentprofileDetailsMap.put("dateOfJoining",date);
				double totCash=(Double)row[16];			  
				double usedCash=(Double)row[17];
				double cutoffAmount=(Double)row[18];
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				agentprofileDetailsMap.put("agentBalance",f.format(balance));
				agentprofileDetailsMap.put("userName",(row[19]!=null?row[19]:""));
				agentprofileDetailsMap.put("password",(row[20]!=null?row[20]:""));		  
				agentprofileDetailsMap.put("agentCuttoffAmount",(row[18]!=null?row[18]:""));
				agentprofileDetailsMap.put("MPIN",(row[21]!=null?row[21]:""));
				agentprofileDetailsMap.put("lastAmount",f.format(row[22]));
				agentprofileDetailsMap.put("APIN",(row[23]!=null?row[23]:""));
				agentprofileDetailsMap.put("kycStatus",(row[24]!=null?row[24]:""));
				agentprofileDetailsMap.put("aadhaarNo",(row[25]!=null?row[25]:""));
				agentprofileDetailsMap.put("agentId",_agentId);
				agentprofileDetailsMap.put("agentIdFlag",clientFlag);
				agentprofileDetailsMap.put("CompleteAgentId",agentId);
			}
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.out.println("Exception in AgentProfileDao,getAgentProfileDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in AgentProfileDao,getAgentProfileDetails");
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
				System.out.println("Exception in AgentProfileDao,getAgentProfileDetails");
				System.out.println(e.toString());
			}
		}
		return agentprofileDetailsMap; 
	}
	
	final String getRandomString(int length)
	{
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		
		for (int i = 0; i < length; i++) 
		{
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}
	
	final String getRandomString1(int length) 
	{
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789";
		for (int i = 0; i < length; i++) 
		{
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}
	
	final String setAgentOTP(String agentId,String clientFlag,String otp)
	{
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
			String optDate=sqlDate.toString();
			String optTime=sqlTime.toString();
			optDate=optDate+optTime;
			int _agentId=Integer.parseInt(agentId);
			txn=session.beginTransaction();
			
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,_agentId);
				if(loginInfo != null)
				{	
					loginInfo.setOtp(otp);
					loginInfo.setOtpDate(optDate);			  
					session.update(loginInfo);
					txn.commit();
					status="updated";
				}
			}
			if(clientFlag.equals("1") )
			{
				REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,_agentId);
				if(reploginInfo != null)
				{	
					reploginInfo.setOtp(otp);
					reploginInfo.setOtpDate(optDate);
					session.update(reploginInfo);
					txn.commit();
					status="updated";
				}
			}
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
			System.out.println("Exception in AgentProfileDao,setAgentOTP");
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
			System.out.println("Exception in AgentProfileDao,setAgentOTP");
			System.out.println(e.toString());
		}
		finally{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AgentProfileDao,setAgentOTP");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetAgentPassword(String agentId,String clientFlag,String password)
	{
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _agentId=Integer.parseInt(agentId);
			txn=session.beginTransaction();
			
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				AgentLoginInfoFormBean loginInfo = (AgentLoginInfoFormBean)session.get(AgentLoginInfoFormBean.class,_agentId);
				if(loginInfo != null)
				{	
					loginInfo.setPassword(password);
					session.update(loginInfo);
					txn.commit();
					status="updated";
				}
			}
			if(clientFlag.equals("1") )
			{
				REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,_agentId);
				if(reploginInfo != null)
				{	
					reploginInfo.setPassword(password);
					session.update(reploginInfo);
					txn.commit();
					status="updated";
				}
			}
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AgentProfileDao,resetAgentPassword");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in AgentProfileDao,resetAgentPassword");
			System.out.println(e.toString());
		}
		finally{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AgentProfileDao,resetAgentPassword");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetAgentMPIN(String agentId,String clientFlag,String mpin)
	{
		Session session=null;				
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _agentId=Integer.parseInt(agentId);
			txn=session.beginTransaction();
				
			AgentDetailsFormBean agentDetails = (AgentDetailsFormBean)session.get(AgentDetailsFormBean.class,_agentId);
			if(agentDetails != null)
			{				
				agentDetails.setMPIN(mpin);			  			  
				session.update(agentDetails);
				txn.commit();
				status="updated";
			}
			
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
			System.out.println("Exception in AgentProfileDao,resetAgentMPIN");
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
			System.out.println("Exception in AgentProfileDao,resetAgentMPIN");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentProfileDao,resetAgentMPIN");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	// Reste APIN
	final String resetAgentAPIN(String agentId,String clientFlag,String apin)
	{
		Session session=null;				
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _agentId=Integer.parseInt(agentId);
			txn=session.beginTransaction();
			String sql="";
			//System.out.println(agentId);
			if(clientFlag.equalsIgnoreCase("0")|| clientFlag.equalsIgnoreCase("2") )
			{
				sql="update login_details set mob_pass='"+apin+"' where user_id='"+agentId+"'";
			}else if(clientFlag.equalsIgnoreCase("1"))
			{
				sql="update recharge_e_point_login_info set mob_pass='"+apin+"' where user_id='"+agentId+"'";
			}
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
			txn.commit();
			status="update";
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			status="notupdated";
			System.out.println("Exception in AgentProfileDao,resetAgentAPIN");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in AgentProfileDao,resetAgentAPIN");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AgentProfileDao,resetAgentAPIN");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String checkAgentIdStatus(String agentId,String userId)
	{
		Session session=null;         
		String status="invalid";
		String clientId="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select mf.clientId from MdsDetailsFormBean mf where mf.mdId=(select df.mdId from DistributorDetailsFormBean df where distributorId=(select df.distributorId from AgentDetailsFormBean df where (df.agentInitialInitial+convert(varchar,df.agentId)=:agentId))").setParameter("agentId",agentId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				clientId=((Integer)row).toString();
			}
			if(clientId.equals(userId)){
				status="valid";
			}
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AgentProfileDao,checkAgentIdStatus");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AgentProfileDao,checkAgentIdStatus");
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
				System.out.println("Exception in AgentProfileDao,checkAgentIdStatus");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final HashMap getAgentProfileDetailsForUpdate(String agentId)
	{
		Session session=null;  
		HashMap agentprofileDetailsMap=new HashMap();
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			qyery="select af.agentId,af.distId,af.firstName,af.middleName,af.lastName,af.companyName,af.addressLine1,"
					+ "af.addressLine2,af.landmark,af.pincode,af.city,af.district,af.state,af.country,af.emailId,"
					+ "af.officePhoneNo,af.officeFaxNo,af.mobileNo,af.alternateMobileNo,af.dob,af.gender,af.maritalStatus,"
					+ "af.occupation,af.companyType,af.designation,af.panNo,af.website,af.residentialAddressLine1,"
					+ "af.residentialAddressLine2,af.resCity,af.resDistrict,af.resCountry,af.resState,af.resPincode,"
					+ "af.resLandmark,af.resMobileNo,af.resAlternateNo,af.resPhoneNo,af.resFaxNo,af.authorizedMobileNo,"
					+ "af.fatherName,af.motherName,af.spouseName,af.category,af.correspondenceAddressValue,af.alternateEmailId,"
					+ "af.initial,af.kycStatus,af.aadhaarNo from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,af.agentId))=:agentId";
			Query query5=session.createQuery(qyery).setParameter("agentId",agentId);
			int distId=0;
			
			for(Iterator it=query5.iterate();it.hasNext();)
			{
				Object row[] = (Object[]) it.next();
				agentprofileDetailsMap.put("agentId",row[0]);
				distId=(Integer)row[1];
				agentprofileDetailsMap.put("agentId",row[2]);
				agentprofileDetailsMap.put("firstName",row[3]);
				agentprofileDetailsMap.put("middleName",row[4]);
				agentprofileDetailsMap.put("lastName",row[5]);
				agentprofileDetailsMap.put("companyName",row[6]);
				agentprofileDetailsMap.put("addressLine1",row[7]);
				agentprofileDetailsMap.put("addressLine2",row[8]);
				agentprofileDetailsMap.put("landmark",row[9]);
				agentprofileDetailsMap.put("pincode",row[10]);
				agentprofileDetailsMap.put("city",row[11]);
				agentprofileDetailsMap.put("district",row[12]);
				agentprofileDetailsMap.put("state",row[13]);
				agentprofileDetailsMap.put("country",row[14]);
				agentprofileDetailsMap.put("emailId",row[15]);
				String officePhoneNo=(String)row[16];
				StringTokenizer str=new StringTokenizer(officePhoneNo,"-");
				agentprofileDetailsMap.put("officePhoneNo",row[16]);
				agentprofileDetailsMap.put("mobileNo",row[17]);
				agentprofileDetailsMap.put("alternateMobileNo",row[18]);
				agentprofileDetailsMap.put("dob",row[19]);
				agentprofileDetailsMap.put("gender",row[20]);
				agentprofileDetailsMap.put("maritalStatus",row[21]);
				agentprofileDetailsMap.put("occupation",row[22]);
				agentprofileDetailsMap.put("companyType",row[23]);
				agentprofileDetailsMap.put("designation",row[24]);
				agentprofileDetailsMap.put("panNo",row[25]);
				agentprofileDetailsMap.put("website",row[26]);
				agentprofileDetailsMap.put("residentialAddressLine1",row[27]);
				agentprofileDetailsMap.put("residentialAddressLine2",row[28]);
				agentprofileDetailsMap.put("resCity",row[29]);
				agentprofileDetailsMap.put("resDistrict",row[30]);
				agentprofileDetailsMap.put("resCountry",row[31]);
				agentprofileDetailsMap.put("resState",row[32]);
				agentprofileDetailsMap.put("resPincode",row[33]);
				agentprofileDetailsMap.put("resLandmark",row[34]);
				agentprofileDetailsMap.put("resMobileNo",row[35]);
				agentprofileDetailsMap.put("resAlternateNo",row[36]);
				agentprofileDetailsMap.put("resPhoneNo",row[37]);
				agentprofileDetailsMap.put("resFaxNo",row[38]);
				agentprofileDetailsMap.put("authorizedMobileNo",row[39]);
				agentprofileDetailsMap.put("fatherName",row[40]);
				agentprofileDetailsMap.put("motherName",row[41]);
				agentprofileDetailsMap.put("spouseName",row[42]);
				agentprofileDetailsMap.put("category",row[43]);
				agentprofileDetailsMap.put("correspondenceAddressValue",row[44]);
				agentprofileDetailsMap.put("alternateEmailId",row[45]);
				agentprofileDetailsMap.put("agentInitial",row[46]);
				agentprofileDetailsMap.put("kycStatus",row[47]);
				agentprofileDetailsMap.put("aadhaarNo",row[48]);
			}
			String qyery1="";
			qyery1="select (df.distributorInitial+convert(varchar,df.distrId)) as distributorId from DistributorDetailsFormBean df where df.distId)=:distributorId";
			Query query6=session.createQuery(qyery1).setParameter("distributorId",distId);
			for(Iterator it=query6.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				agentprofileDetailsMap.put("completeDistributorId",row);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentProfileDao,getAgentProfileDetailsForUpdate");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentProfileDao,getAgentProfileDetailsForUpdate");
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
				System.out.println("Exception in AgentProfileDao,getAgentProfileDetailsForUpdate");
				System.out.println(e.toString());
			}
		}
		return agentprofileDetailsMap; 
	}
	
	final String  updateAgentProfile(String agentId,int distId,String category,String initial,String firstName,String middleName,String lastName,String dateOfBirth,String gender,String maritalStatus,String fatherName,String motherName,String spouseName,String occupation,String companyType,String companyName,String designation,String addressLine1,String addressLine2,String  country,String state,String district,String city,String landmark,String pincode,String alternateMobileNo,String officePhoneNo,String officeFaxNo,String website,String residentialAddressLine1,String residentialAddressLine2,String resCountry,String resState,String resDistrict,String resCity,String resLandmark,String resPincode,String resMobileNo,String resAlternateNo,String resPhoneNo,String resFaxNo,String cAddressValue,String panNo,String emailId,String alternateEmailId,String authorizedMobileNo)
	{
		Session session=null;
		Transaction txn=null;
		String status="invalid";
		int countNo=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			int _agentId=0;
			Query query1=session.createQuery("select af.agentId from AgentDetailsFormBean af where (af.agentInitial+convert(varchar,lf.agentId))=:agentId").setParameter("verificationCode",agentId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				_agentId=(Integer)row;
			}
			txn=session.beginTransaction();
			AgentDetailsFormBean agentDetails = (AgentDetailsFormBean)session.get(AgentDetailsFormBean.class,_agentId);			
			if(agentDetails != null)
			{	
				agentDetails.setDistId(distId);
				session.update(agentDetails);
				txn.commit();
				status="valid";
			}
			else
			{
				status="invalid";
			}
		}catch(Exception e)
		{
			try{
				if(txn!=null)
				{
					txn.rollback();			
				}
			}
			catch(HibernateException ex){
			}
			System.out.println("Exception in AgentProfileDao,updateAgentProfile");
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
				System.out.println("Exception in AgentProfileDao,updateAgentProfile");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String checkUserName(String emailId, String agid)
	{
		Session session=null;         
		String status="valid";
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _agid=Integer.parseInt(agid);
			Query quey=session.createQuery("select af.userName from AgentLoginInfoFormBean af where af.userId=:AgID").setParameter("AgID", _agid);
			String OldemailID=quey.uniqueResult().toString();
			
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select af.userName from AgentLoginInfoFormBean af where af.userName=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
  			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
  		{
			status="invalid";
  			System.out.println("Exception in AgentProfileDao,checkUserName");
  			System.out.println(e.toString());
  		}  			

		catch(Exception e)
  		{
  			status="invalid";	
  			System.out.println("Exception in AgentProfileDao,checkUserName");
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
  				System.out.println("Exception in AgentProfileDao,checkUserName");
  				System.out.println(e.toString());
  			}
  		}
		return status; 
  	 }
       
	final String checkMobileNo(String mobileNo, String agid)
	{
		Session session=null;         
		String status="valid";
    	try
    	{
    		session = HibernateSession.getSessionFactory().openSession(); 
    		int _agid=Integer.parseInt(agid);
			//System.out.println(_agid);
			Query quey=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where af.agentId=:AgID").setParameter("AgID", _agid);
			//System.out.println(quey);
			String OldMobileNo=quey.uniqueResult().toString();
			if(OldMobileNo.equalsIgnoreCase(mobileNo))
			{
				//System.out.println("we are into this");
				status="valid";
			}else
			{
				Query query1=session.createQuery("select af.mobileNo from AgentDetailsFormBean af where af.mobileNo=:mobileNo").setParameter("mobileNo",mobileNo);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AgentProfileDao,checkMobileNo");
			System.out.println(e.toString());
		}	
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AgentProfileDao,checkMobileNo");
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
				System.out.println("Exception in AgentProfileDao,checkMobileNo");
				System.out.println(e.toString());
			}
    	}
		return status; 
    }
	
	public static String dateConvert (Object inDate)
    {
		try 
		{
			DateFormat formatter ; 
			Date date ; 
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = (Date)formatter.parse(inDate.toString());
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			String outDate = formatter.format(date);
			return outDate;

		} catch (ParseException e)
		{
			System.out.println("Exception in Converting Date:");  
			System.out.println(e.toString());
		}  
		return null;
    }
	
	public String updateAGProfile(String aadhaarNo,String kycStatus, String firstName, String lastName,String dob, String gender, String companyType, String companyName,String emailId, String mobileNo,
			 String addressLine1,String addressLine2, String state, String district, String city,String pincode, String panNo, String agId, String clientFlag) 
	{
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try
		{
			//System.out.println("we are into update");
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _agid=Integer.parseInt(agId);
			String AGName=firstName+" "+lastName;
			
			String sql="update agent_Details set agent_name='"+AGName+"',DateOfBirth='"+dob+"',Gender='"+gender+"',agency_name='"+companyName+"',Company_Type='"+companyType+"',address1='"+addressLine1+"',"+
							"address2='"+addressLine2+"',state='"+state+"',district='"+district+"',city='"+city+"',pin_code='"+pincode+"',mobile_no='"+mobileNo+"',Pan_no='"+panNo+"',Agent_first_name='"+firstName+"',Agent_last_name='"+lastName+"'" +
							",email_id='"+emailId+"',AadhaarNo='"+aadhaarNo+"',kyc_status="+Integer.parseInt(kycStatus)+" where agent_id="+_agid;
			
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			
			if(clientFlag.equalsIgnoreCase("0")|| clientFlag.equalsIgnoreCase("2"))
			{
				sql="update login_details set user_name='"+emailId+"' where user_id='"+agId+"'";
			}else if(clientFlag.equalsIgnoreCase("0"))
			{
				sql="update recharge_e_point_login_info set user_name='"+mobileNo+"' where user_id='"+agId+"'";
			}
			query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			ts.commit();
			status="success";
		}
		catch(HibernateException e)
		{
			ts.rollback();
			status="fail";
			System.out.println("Exception in AgentProfileDao,updateAGProfile");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in AgentProfileDao,updateAGProfile");
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
				System.out.println("Exception in AgentProfileDao,updateAGProfile");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	public String checkEmailID(String emailId, String agId) 
	{
		Session session=null;         
		String status="valid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _agid=Integer.parseInt(agId);
			//System.out.println(_agid);
			Query quey=session.createQuery("select af.emailId from AgentDetailsFormBean af where af.agentId=:AgID").setParameter("AgID", _agid);
			//System.out.println(quey);
			String OldemailID=quey.uniqueResult().toString();
			
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select af.emailId from AgentDetailsFormBean af where af.emailId=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
  			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
  		{
			status="invalid";
  			System.out.println("Exception in AgentProfileDao,checkEmailID");
  			System.out.println(e.toString());
  		}
		catch(Exception e)
  		{
  			status="invalid";	
  			System.out.println("Exception in AgentProfileDao,checkEmailID");
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
  				System.out.println("Exception in AgentProfileDao,checkEmailID");
  				System.out.println(e.toString());
  			}
  		}
		return status; 
	}
	public static void main(String[] args) {
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String DateToStr = format.format(curDate);
		String[] datearr=DateToStr.split("\\/");
		
		     System.out.println(datearr[0]);
		     System.out.println(datearr[1]);
		     System.out.println(datearr[2]);

	}
}