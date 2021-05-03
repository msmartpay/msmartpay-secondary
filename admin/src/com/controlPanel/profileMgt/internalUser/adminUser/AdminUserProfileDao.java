package com.controlPanel.profileMgt.internalUser.adminUser;

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
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.login.LoginInfoFormBean;

public class AdminUserProfileDao{
	
	final HashMap getDynamicDetails(int adminUserId){
		HashMap map=new HashMap();
		Session session=null;
		int portalId=0;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query2=session.createQuery("select df.clientId from DynamicDetailsFormBean df,AdminUserFormBean af where af.portalId=df.clientId and  af.userId=:userId").setParameter("userId",adminUserId);
			for(Iterator it=query2.iterate();it.hasNext();){
				Object row = (Object) it.next();
				portalId=(Integer)row;
			}
			Query query1=session.createQuery("select d.helpEmailId  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			//System.out.println(query1);
			for(Iterator it=query1.iterate();it.hasNext();){
				String row = (String) it.next();
				map.put("helpEmailId",row);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AdminUserProfileDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminUserProfileDao,getDynamicDetails");
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
				System.out.println("Exception in AdminUserProfileDao,getDynamicDetails" );
				System.out.println(e.toString());
			}
		}
		return map;
	}
	
	public  ArrayList getAdminUserTurnOver(int adminUserId){
		ArrayList turnOverList=new ArrayList();
		Session session=null;  
		Transaction txn=null;
		Transaction txn1=null;
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			int totalNoOfTranscations=0;
			double totalTransactionAmount=0.00;
			con=session.connection(); 		  
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt.setString(1,"countAdminUserTurnOverAmount");
			cstmt.setInt(2,adminUserId);
			ResultSet rs=cstmt.executeQuery();
			txn.commit();	     
			while(rs.next()){
				totalTransactionAmount=rs.getDouble(1);
				totalNoOfTranscations=rs.getInt(2);
			}
			//System.out.println("totalTransactionAmount>>>>>>>>>>>"+totalTransactionAmount);
			//System.out.println("totalNoOfTranscations>>>>>>>>>>>"+totalNoOfTranscations);
			txn1=session.beginTransaction();
		  
			CallableStatement cstmt1 =null;
			cstmt1=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt1.setString(1,"adminUser");
			cstmt1.setInt(2,adminUserId);
			ResultSet rs1=cstmt1.executeQuery();
			txn1.commit();	   	     
	  
			while(rs1.next()){

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
		catch(Exception e){
			try{
				if(txn!=null){
					txn.rollback();
				}
			}catch(Exception ex){}
            try{
				if(txn1!=null){
					txn1.rollback();
				}
            }catch(Exception ex){}
            System.out.println("Exception in AdminUserProfileDao,getAdminUserTurnOver");
			System.out.println(e.toString());
		}
		finally{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AdminUserProfileDao,getAdminUserTurnOver");
				System.out.println(e.toString());
			}
		}
		return turnOverList;
	}

	public  HashMap getAdminUserProfileDetails(String adminUserId){
	 
		Session session=null;  
		HashMap adminUserProfileDetailsMap=new HashMap();
		try{

			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			int _adminUserId=Integer.parseInt(adminUserId);
			String clientFlag="";
			String mdsLoginUrl="";
			String mpin="";

			String Sql=" select top 1 Date_of_Transaction,Net_Tran_amount from Portal_User_Transaction_details where Portal_id="+_adminUserId+" order by Id_No desc";

			Query query3=session.createSQLQuery(Sql);
		 
			List list=query3.list();
			for(Iterator it=list.iterator();it.hasNext();){
				Object row[] = (Object[]) it.next();
				adminUserProfileDetailsMap.put("lastDateOfTransaction",row[0]);
				adminUserProfileDetailsMap.put("lastTransactionAmount",row[1]);
			}
			Query query4=session.createQuery("select ajf.approvalDate from AdminUserJournalFormBean ajf where ajf.idNo=(select max(afd.idNo) from AdminUserJournalFormBean afd where afd.userId=:adminUserId)").setParameter("adminUserId",_adminUserId);
			
			for(Iterator it=query4.iterate();it.hasNext();){
				Object row = (Object) it.next();
				adminUserProfileDetailsMap.put("lastDateOfTBTaken",row == null ? "" : dateConvert(row));
			}
			
			qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.officialNumber,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,af.userId,alf.userName,alf.password,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount,aaf.lastUpdateAmount from AdminUserFormBean af,UserAmountFormBean aaf,LoginInfoFormBean alf where af.userId=aaf.userId and aaf.userId=alf.userId and alf.userId=:adminUserId";
			
			Query query5=session.createQuery(qyery).setParameter("adminUserId",_adminUserId);
			
			for(Iterator it=query5.iterate();it.hasNext();){

				Object row[] = (Object[]) it.next();
				
				adminUserProfileDetailsMap.put("firstName",row[0]);
				adminUserProfileDetailsMap.put("lastName",row[1]);
				adminUserProfileDetailsMap.put("dateOfBirth",row[2]);
				adminUserProfileDetailsMap.put("gender",row[3]);
				adminUserProfileDetailsMap.put("companyType",row[4]);			  
				adminUserProfileDetailsMap.put("companyName",row[5]);			  
				adminUserProfileDetailsMap.put("emailID",row[6]);
				adminUserProfileDetailsMap.put("mobileNo",row[7]);
				adminUserProfileDetailsMap.put("address1",row[8]);
				adminUserProfileDetailsMap.put("address2",row[9]);			  
				adminUserProfileDetailsMap.put("state",row[10]);
				adminUserProfileDetailsMap.put("district",row[11]);
				adminUserProfileDetailsMap.put("city",row[12]);
				adminUserProfileDetailsMap.put("pincode",row[13]);			 
				adminUserProfileDetailsMap.put("panNo",row[14]);
				String date=dateConvert(row[15]);
				adminUserProfileDetailsMap.put("dateOfJoining",date);
				adminUserProfileDetailsMap.put("adminUserId",row[16]);
				
				adminUserProfileDetailsMap.put("userName",row[17]);
				adminUserProfileDetailsMap.put("password",row[18]);
				DecimalFormat f = new DecimalFormat("0.00");
				double totcash=(Double)row[19];
				double usedCash=(Double)row[20];
				double cutoffAmount=(Double)row[21];
				double mdBalance=totcash-usedCash+cutoffAmount;
				adminUserProfileDetailsMap.put("totalBalance",f.format(mdBalance));
				adminUserProfileDetailsMap.put("cutoffAmount",f.format(row[21]));
				adminUserProfileDetailsMap.put("lastAmount",f.format(row[22]));
			
			}
		}
		catch(HibernateException e)
		{
			adminUserProfileDetailsMap.put("error", "error");
			System.out.println("Exception in AdminUserProfileDao,getAdminUserProfileDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			adminUserProfileDetailsMap.put("error", "error");
			System.out.println("Exception in AdminUserProfileDao,getAdminUserProfileDetails");
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
				System.out.println("Exception in AdminUserProfileDao,getAdminUserProfileDetails");
				System.out.println(e.toString());
			}
		}
		return adminUserProfileDetailsMap; 
	}

	public  String getRandomString(int length) {

		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
    
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	public  String setAdminUserOTP(String adminUserId,String otp){
		
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try{
			session = HibernateSession.getSessionFactory().openSession(); 

			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
			String optDate=sqlDate.toString();
			String optTime=sqlTime.toString();
			optDate=optDate+" "+optTime;
			//System.out.println("optDate date is>>>>>>>>>>>>>>>>."+optDate);
			int _adminUserId=Integer.parseInt(adminUserId);
			txn=session.beginTransaction();
			LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_adminUserId);

			if(loginInfo != null){
				//System.out.println(adminUserId+otp);
				loginInfo.setOtp(otp);
				loginInfo.setOtpDate(optDate);			  
				session.update(loginInfo);
				txn.commit();
				status="updated";
			}
		}catch(HibernateException e){
			try{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex){
			}
			System.out.println("Exception in AdminUserProfileDao,setAdminUserOTP");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in AdminUserProfileDao,setAdminUserOTP");
			System.out.println(e.toString());
		 }
		finally{
			try{
			   session.flush();
			   session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AdminUserProfileDao,setAdminUserOTP");
				System.out.println(e.toString());
			}
		}
		return status;
	}


	public  String resetAdminUserPassword(String adminUserId,String password){
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try{
			
			session = HibernateSession.getSessionFactory().openSession(); 
			int _adminUserId=Integer.parseInt(adminUserId);
			txn=session.beginTransaction();
			LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,_adminUserId);
			
			if(loginInfo != null){	
			  
				loginInfo.setPassword(password);
			  			  
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
				System.out.println("Exception in AdminUserProfileDao,resetAdminUserPassword");
				System.out.println(ex.toString());
			}
			System.out.println("Exception in AdminUserProfileDao,resetAdminUserPassword");
			System.out.println(e.toString());
		 }
		catch(Exception e){
			try{
                   if(txn!= null)
			         txn.rollback();
			 }
			 catch(Exception ex){
				 System.out.println("Exception in AdminUserProfileDao,resetAdminUserPassword");
				 System.out.println(e.toString());
			 }
			 System.out.println("Exception in AdminUserProfileDao,resetAdminUserPassword");
			 System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in AdminUserProfileDao,resetAdminUserPassword");
				System.out.println(e.toString());
			}
		}
	
		return status;
}
	//method Added By Manoj
	public String checkEmailID(String emailId, String adminID) {
		Session session=null;         
		String status="valid";
		try{
			int _adminid=Integer.parseInt(adminID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.emailId from AdminUserFormBean mf where mf.userId=:adminID").setParameter("adminID", _adminid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId)){
				
				status="valid";
			}else{
				Query query1=session.createQuery("select mf.emailId from AdminUserFormBean mf where mf.emailId=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
				status="invalid";
				System.out.println("Exception in AdminUserProfileDao,checkEmailID");
				System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserProfileDao,checkEmailID");
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
				System.out.println("Exception in AdminUserProfileDao,checkEmailID");
				System.out.println(e.toString());
			}
		}
		return status; 
	}

	final String checkUserName(String emailId, String adminID){
		Session session=null;         
		String status="valid";
		try{
			int _adminid=Integer.parseInt(adminID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.userName from LoginInfoFormBean mf where mf.userId=:adminID").setParameter("adminID", _adminid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId)){
				
				status="valid";
			}else{
				Query query1=session.createQuery("select mf.userName from LoginInfoFormBean mf where mf.userName=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
				status="invalid";
				System.out.println("Exception in AdminUserProfileDao,checkUserName");
				System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserProfileDao,checkUserName");
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
				System.out.println("Exception in AdminUserProfileDao,checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	final String checkMobileNo(String MobileNo, String adminID){
		Session session=null;         
		String status="valid";
		try{
			int _adminID=Integer.parseInt(adminID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.officialNumber from AdminUserFormBean mf where mf.userId=:adminID").setParameter("adminID", _adminID);
			
			String OldMobileNo=quey.uniqueResult().toString();
			if(OldMobileNo.equalsIgnoreCase(MobileNo)){
				
				status="valid";
			}else{
				Query query1=session.createQuery("select mf.officialNumber from AdminUserFormBean mf where mf.officialNumber=:mobileNo").setParameter("mobileNo",MobileNo);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkMobileNo : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in AdminUserProfileDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in AdminUserProfileDao,checkMobileNo");
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
				System.out.println("Exception in AdminUserProfileDao,checkMobileNo");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	public String updatePortalUserProfile(String firstName, String lastName,
			String dob, String gender, String companyType, String companyName,
			String emailId, String mobileNo, String addressLine1,
			String addressLine2, String state, String district, String city,
			String pincode, String panNo, String adminID) {
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try{
			
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _adminID=Integer.parseInt(adminID);
			String sql="update admin_user_details set Date_of_birth='"+dob+"',Gender='"+gender+"',CompanyName='"+companyName+"',CompanyType='"+companyType+"',Address_line1='"+addressLine1+"',"+
							"Address_line2='"+addressLine2+"',State='"+state+"',District='"+district+"',City='"+city+"',Pincode='"+pincode+"',Mobile_no='"+mobileNo+"',PAN_No='"+panNo+"',First_name='"+firstName+"',Last_name='"+lastName+"'" +
							",Official_email_id='"+emailId+"' where user_id="+_adminID;
			//System.out.println(sql);
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			
			sql="update admin_login_details set user_name='"+emailId+"' where user_id="+_adminID+" ";
			Query query2=session.createSQLQuery(sql);
			query2.executeUpdate();
			ts.commit();
			status="success";
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
			ts.rollback();
			status="fail";
			System.out.println("Exception in AdminUserProfileDao,updatePortalUserProfile");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in AdminUserProfileDao,updatePortalUserProfile");
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
				System.out.println("Exception in AdminUserProfileDao,updatePortalUserProfile");
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
}