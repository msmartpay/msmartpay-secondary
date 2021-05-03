package com.controlPanel.profileMgt.internalUser.EGEN;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;
import com.formBean.EGEN.APIClientAuthenticationDetailsFormBean;
public class ApiClientProfileDao
{

	public static ArrayList getApiClientTurnOver(int apiClientId)
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
			cstmt.setString(1,"countApiClientTurnOverAmount");
			cstmt.setInt(2,apiClientId);     
			ResultSet rs=cstmt.executeQuery();
			txn.commit();	     
			while(rs.next())
			{
				totalTransactionAmount=rs.getDouble(1);
				totalNoOfTranscations=rs.getInt(2);
			}
			//System.out.println("totalTransactionAmount>>>>>>>>>>>"+totalTransactionAmount);
			// System.out.println("totalNoOfTranscations>>>>>>>>>>>"+totalNoOfTranscations);
			txn1=session.beginTransaction();
			Connection con1=session.connection(); 		  
			CallableStatement cstmt1 =null;
			cstmt1=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt1.setString(1,"apiClient");
			cstmt1.setInt(2,apiClientId);
			ResultSet rs1=cstmt1.executeQuery();
			txn1.commit();	   	     
			while(rs1.next())
			{
				HashMap map=new HashMap();
				double serviceAmount=rs1.getDouble(1);
				String service=rs1.getString(2);
				int noOfTransctions=rs1.getInt(3);
				//System.out.println("serviceAmount>>>>>>>>>>>"+serviceAmount);
				//System.out.println("service>>>>>>>>>>>"+service);
				//System.out.println("noOfTransctions>>>>>>>>>>>"+noOfTransctions);

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
		catch(Exception e)
		{
		 
			try
			{				
				if(txn!=null){
					txn.rollback();
				}
			} catch(Exception ex){
				System.out.println("Exception in getApiClientTurnOver");
				System.out.println(e.toString());
			}
            
			
			try{
				if(txn1!=null){
					txn1.rollback();
				}
			}catch(Exception ex){}
			System.out.println("Exception in getApiClientTurnOver");
			e.printStackTrace();
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
				System.out.println("Exception in getApiClientTurnOver");
				System.out.println(e.toString());
			}
		}
		return turnOverList;
	}

	public static HashMap getApiClientProfileDetails(String apiClientId){
		Session session=null;  
		HashMap apiClientProfileDetailsMap=new HashMap();
		try{
			session = HibernateSession1.getSessionFactory().openSession();
			System.out.println("session = HibernateSession1.getSessionFactory().openSession();");
			String qyery="";
			long _apiClientId=Long.parseLong(apiClientId);
			Query query3=session.createQuery("select atf.dateOfTransaction,atf.requestedAmount from ApiClientTransactionDetailsFormBean atf where atf.idNo=(select max(atd.idNo) from ApiClientTransactionDetailsFormBean atd where atd.corporateAgentId=:corporateAgentId)").setLong("corporateAgentId",_apiClientId);
			for(Iterator it=query3.iterate();it.hasNext();){
				Object row[] = (Object[]) it.next();
				int id=Integer.parseInt(apiClientId);
				DecimalFormat f = new DecimalFormat("0.00");
				apiClientProfileDetailsMap.put("lastDateOfTransaction",dateConvert(row[0]));
				apiClientProfileDetailsMap.put("lastTransactionAmount",f.format(row[1]));
				apiClientProfileDetailsMap.put("id",id);
			}
			Query query4=session.createQuery("select ajf.approvalDate from ApiClientJournalFormBean ajf where ajf.idNo=(select max(afd.idNo) from ApiClientJournalFormBean afd where afd.corporateAgentId=:corporateAgentId)").setLong("corporateAgentId",_apiClientId);
			for(Iterator it=query4.iterate();it.hasNext();){
				Object row = (Object) it.next();
				apiClientProfileDetailsMap.put("lastDateOfTBTaken",dateConvert(row));
			}

//		   qyery="select af.firstName,af.lastName,af.dob,af.designation,af.companyName,af.emailId,af.mobileNo,af.officeAddress,af.state,af.district,af.pincode,af.panNo,aaf.totalBalanceAmount,af.dateOfJoining,aaf.cutOffAmount from APIClientDetailsFormBean af,ApiClientAmountFormBean aaf,APIClientAuthenticationDetailsFormBean alf where af.corporateAgentId=aaf.corporateAgentId and aaf.corporateAgentId=alf.corporateAgentId and alf.corporateAgentId=:corporateAgentId";
		   qyery="select af.firstName,af.lastName,af.dob,af.designation,af.companyName,af.emailId,af.mobileNo,af.officeAddress,af.state,af.district,af.pincode,aaf.totalBalanceAmount,af.dateOfJoining,aaf.cutOffAmount from APIClientDetailsFormBean af,ApiClientAmountFormBean aaf where af.corporateAgentId=aaf.corporateAgentId and af.corporateAgentId=:corporateAgentId";
		   //System.out.println("----------this is details query "+qyery);
		   //System.out.println("apiClientId is "+apiClientId);
		   Query query5=session.createQuery(qyery).setLong("corporateAgentId",_apiClientId);
		   //System.out.println("--------final Query is "+query5);
		   for(Iterator it=query5.iterate();it.hasNext();){
			   //System.out.println("inside iterartor");
			   Object row[] = (Object[]) it.next();
			   apiClientProfileDetailsMap.put("firstname",row[0]);
			   apiClientProfileDetailsMap.put("lastName",row[1]);
			   apiClientProfileDetailsMap.put("dob",row[2]);
			   apiClientProfileDetailsMap.put("designation",row[3]);
			   apiClientProfileDetailsMap.put("companyName",row[4]);
			   apiClientProfileDetailsMap.put("apiClientEmailId",row[5]);
			   apiClientProfileDetailsMap.put("apiClientMobileNo",row[6]);
			   apiClientProfileDetailsMap.put("officeAddress",row[7]);		
			   apiClientProfileDetailsMap.put("state",row[8]);
			   apiClientProfileDetailsMap.put("district",row[9]);
			   apiClientProfileDetailsMap.put("pincode",row[10]);
			   DecimalFormat f = new DecimalFormat("0.00");
			   apiClientProfileDetailsMap.put("apiClientBalance",f.format(row[11]));
			   apiClientProfileDetailsMap.put("dateOfJoining",row[12]);	
			   apiClientProfileDetailsMap.put("apiClientCuttoffAmount",row[13]);
			   apiClientProfileDetailsMap.put("completeApiClientId",apiClientId);
		   }
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in getApiClientProfileDetails");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in getApiClientProfileDetails");
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
				System.out.println("Exception in getApiClientProfileDetails");
				System.out.println(e.toString());
			}
		}
		return apiClientProfileDetailsMap; 
	}

	public static String getRandomString(int length) {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	public static String setApiClientOTP(String apiClientId,String otp){
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try{
			session = HibernateSession1.getSessionFactory().openSession(); 
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
			String optDate=sqlDate.toString();
			String optTime=sqlTime.toString();
			optDate=optDate+" "+optTime;
			//System.out.println("optDate date is>>>>>>>>>>>>>>>>."+optDate);
			Connection con=session.connection();
			int _apiClientId=Integer.parseInt(apiClientId);
			txn=session.beginTransaction();
			APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_apiClientId);
			if(loginInfo != null){
				loginInfo.setOtp(otp);
				loginInfo.setOtpDate(optDate);			  
				session.update(loginInfo);
				txn.commit();
				status="updated";
			}
		}
		catch(HibernateException e){
			try{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in setApiClientOTP");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in setApiClientOTP");
			e.printStackTrace();
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in setApiClientOTP");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	public static String resetApiClientPassword(String apiClientId,String password){
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try{
			session = HibernateSession1.getSessionFactory().openSession(); 
			Connection con=session.connection();
			int _apiClientId=Integer.parseInt(apiClientId);
			txn=session.beginTransaction();
			APIClientAuthenticationDetailsFormBean loginInfo = (APIClientAuthenticationDetailsFormBean)session.get(APIClientAuthenticationDetailsFormBean.class,_apiClientId);
			if(loginInfo != null){	
				loginInfo.setAgentLoginPassword(password);
				session.update(loginInfo);
				txn.commit();
				status="updated";
			}
		}
		catch(HibernateException e){
			try{
				if(txn!= null)
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in resetApiClientPassword");
			e.printStackTrace();
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in resetApiClientPassword");
			e.printStackTrace();
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in resetApiClientPassword");
				System.out.println(e.toString());
			}
		}
		return status;
	}

	public static String dateConvert (Object inDate){
		try {
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

	public String updateEGENProfile(String firstName, String lastName, String dob,
			String companyType, String companyName, String emailId,
			String mobileNo, String addressLine1, String state, String district,
			String pincode, String apiClientId) {
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try{
			//System.out.println("we are into update");
			session = HibernateSession1.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _apiClientId=Integer.parseInt(apiClientId);
			String sql="update dbo.Rech_API_Corporate_Agent_Details set Agent_First_Name='"+firstName+"',Agent_Last_Name='"+lastName+"',Agency_Name='"+companyName+"',Date_of_Birth='"+dob+"',Designation='"+companyType+"',Office_Address='"+addressLine1+"',Office_District='"+district+"',Office_PinCode='"+pincode+"',Office_State='"+state+"',EmailID='"+emailId+"',Office_Mobile='"+mobileNo+"' where Corporate_Agent_Id='"+_apiClientId+"'";
		
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			ts.commit();
			status="success";
		}
		catch(HibernateException e)
		{
			ts.rollback();
			status="fail";
			System.out.println("Exception in AgentProfileDao,checkUserName");
			e.printStackTrace();
			
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in AgentProfileDao,checkUserName");
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
				System.out.println("Exception in AgentProfileDao,checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}

	public String checkMobileNo(String mobileNo, String apiClientId) {
		Session session=null;         
		String status="valid";
		try{
			
			session = HibernateSession1.getSessionFactory().openSession(); 
			long _apiClientId=Long.parseLong(apiClientId);
		System.out.println(_apiClientId);
		Query quey=session.createQuery("select af.mobileNo from APIClientDetailsFormBean af where af.corporateAgentId=:corporateAgentId").setLong("corporateAgentId", _apiClientId);
		//System.out.println(quey);
		String OldMobileNo=quey.uniqueResult().toString();
		if(OldMobileNo.equalsIgnoreCase(mobileNo)){
			status="valid";
		}else{
			Query query1=session.createQuery("select af.mobileNo from APIClientDetailsFormBean af where af.mobileNo=:mobileNo").setParameter("mobileNo",mobileNo);
			for(Iterator it=query1.iterate();it.hasNext();){
				Object row = (Object) it.next();
				status="invalid";
			}
		}
		//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in checkUserName");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}

	public String checkEmailID(String emailId, String apiClientId) {
		Session session=null;         
		String status="valid";
		try{
			session = HibernateSession1.getSessionFactory().openSession(); 
			long _apiClientId=Long.parseLong(apiClientId);
			System.out.println(_apiClientId);
			Query quey=session.createQuery("select af.emailId from APIClientDetailsFormBean af where af.corporateAgentId=:corporateAgentId").setLong("corporateAgentId", _apiClientId);
			//System.out.println(quey);
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId)){
				status="valid";
			}else{
				Query query1=session.createQuery("select af.emailId from APIClientDetailsFormBean af where af.emailId=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in checkUserName");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
}