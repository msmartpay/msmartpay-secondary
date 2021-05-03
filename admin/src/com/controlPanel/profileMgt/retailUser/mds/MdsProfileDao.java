package com.controlPanel.profileMgt.retailUser.mds;

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
import com.formBean.mds.MdsDetailsFormBean;
import com.formBean.mds.MdsLoginInfoFormBean;

/**
* Updated By :Arvind
* Updated Date : 11 Jun 2013
* Updated Matter : Format of class.
*/

 class MdsProfileDao
{
	final HashMap getDynamicDetails(int mdId){
		HashMap map=new HashMap();
		Session session=null;
		int portalId=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query2=session.createQuery("select df.clientId from DynamicDetailsFormBean df,AdminUserFormBean af,MdsDetailsFormBean mf where mf.clientId=af.portalId and af.portalId=df.clientId and  mf.mdId=:mdId").setParameter("mdId",mdId);
			for(Iterator it=query2.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalId=(Integer)row;
			}
			
			Query query1=session.createQuery("select d.adminLoginUrl,d.helpMobileNo,d.helpEmailId,d.domainName,d.companyName,d.mdLoginUrl,d.clientFlag  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				map.put("adminLoginUrl",(String)row[0]);
				map.put("helpMobileNo",(String)row[1]);
				map.put("helpEmailId",(String)row[2]);
				map.put("domainName",(String)row[3]);
				map.put("companyName",(String)row[4]);
				map.put("mdLoginUrl",(String)row[5]);
				map.put("clientFlag",(String)row[6]);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsProfileDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsProfileDao,getDynamicDetails");
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
				System.out.println("Exception in MdsProfileDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}
	final ArrayList getMdsTurnOver(int mdId)
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
			cstmt.setString(1,"countMdsTurnOverAmount");
			cstmt.setInt(2,mdId);
			ResultSet rs=cstmt.executeQuery();
			txn.commit();	
			
			while(rs.next())
			{
				totalTransactionAmount=rs.getDouble(1);
				totalNoOfTranscations=rs.getInt(2);
			}
			
			txn1=session.beginTransaction();
			Connection con1=session.connection(); 		  
			CallableStatement cstmt1 =null;
			cstmt1=con.prepareCall("{call Admin_userTurnOver(?,?)}");
			cstmt1.setString(1,"mds");
			cstmt1.setInt(2,mdId);
			ResultSet rs1=cstmt1.executeQuery();
			txn1.commit();	   	     
			
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
		catch(Exception e)
		{
			try
			{
				if(txn!=null){
					txn.rollback();
				}
			} catch(Exception ex){}
			try
			{
				if(txn1!=null){
					txn1.rollback();
				}
				
			}catch(Exception ex)
			{
				
			}
			System.out.println("Exception in MdsProfileDao,getMdsTurnOver");
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
				System.out.println("Exception in MdsProfileDao,getMdsTurnOver");
				System.out.println(e.toString());
			}
		}
		return turnOverList;
	}
	
	final HashMap getMdsProfileDetails(String mdId)
	{
		Session session=null;  
		HashMap mdsProfileDetailsMap=new HashMap();		 
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			int _mdId=0;
			String clientFlag="";
			String mdsLoginUrl="";
			String mpin="";
			
			Query query1=session.createQuery("select af.mdId,df.clientFlag,df.mdLoginUrl,af.MPIN from MdsDetailsFormBean af,DynamicDetailsFormBean df where (af.mdInitial+convert(varchar,af.mdId))=:mdId and df.clientId=af.clientId ").setParameter("mdId",mdId);;
			for(Iterator it=query1.iterate();it.hasNext();)
			{				  
				Object row[] = (Object[]) it.next();
				_mdId=(Integer)row[0];
				clientFlag=(String)row[1];
				mdsLoginUrl=(String)row[2];
				mpin=(String)row[3];  
			}
			
			String sql=" select top 1 Date_of_Transaction,Net_Tran_amount from MD_Transaction_details where Md_id="+_mdId+" order by Id_No desc";
			Query query3=session.createSQLQuery(sql);
			List list=query3.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{				  
				Object row[] = (Object[]) it.next();
				mdsProfileDetailsMap.put("lastDateOfTransaction",row[0]);
				mdsProfileDetailsMap.put("lastTransactionAmount",row[1]);  
			}
			Query query4=session.createSQLQuery("select top 5 Date_of_Transaction from MD_Transaction_details where Md_id="+_mdId+" and Action_on_Bal_amount='credit' order by Id_No desc");
			list=query4.list();
			
			for(Iterator it=list.iterator();it.hasNext();)
			{				  
				Object row = (Object) it.next();
				mdsProfileDetailsMap.put("lastDateOfTBTaken",row);	   
			}
			qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.mobileNo,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,af.mdId,af.MPIN,alf.userName,alf.password,aaf.totalBalanceAmount,aaf.cutOffAmount,aaf.lastAmount,aaf.updatedDate,aaf.totalCash,aaf.usedCash from MdsDetailsFormBean af,MdsAmountFormBean aaf,MdsLoginInfoFormBean alf where af.mdId=aaf.mdId and aaf.mdId=alf.userId and alf.userId=:mdId";
			Query query5=session.createQuery(qyery).setParameter("mdId",_mdId);   
			for(Iterator it=query5.iterate();it.hasNext();)
			{
								  
				Object row[] = (Object[]) it.next();			  
				String firstName=(String)row[0];
				String lastName=(String)row[2];
				mdsProfileDetailsMap.put("firstName",row[0]);
				mdsProfileDetailsMap.put("lastName",row[1]);
				mdsProfileDetailsMap.put("dateOfBirth",row[2]);
				mdsProfileDetailsMap.put("gender",row[3]);
				mdsProfileDetailsMap.put("companyType",row[4]);			  
				mdsProfileDetailsMap.put("companyName",row[5]);			  
				mdsProfileDetailsMap.put("emailID",row[6]);
				mdsProfileDetailsMap.put("mobileNo",row[7]);
				mdsProfileDetailsMap.put("address1",row[8]);
				mdsProfileDetailsMap.put("address2",row[9]);			  
				mdsProfileDetailsMap.put("state",row[10]);
				mdsProfileDetailsMap.put("district",row[11]);
				mdsProfileDetailsMap.put("city",row[12]);
				mdsProfileDetailsMap.put("pincode",row[13]);			 
				mdsProfileDetailsMap.put("panNo",row[14]);
				String date=dateConvert(row[15]);
				mdsProfileDetailsMap.put("dateOfJoining",date);
				mdsProfileDetailsMap.put("mdId",row[16]);
				mdsProfileDetailsMap.put("MPIN",row[17]);
				mdsProfileDetailsMap.put("userName",row[18]);
				mdsProfileDetailsMap.put("password",row[19]);
				DecimalFormat f = new DecimalFormat("0.00");
				double totcash=(Double)row[24];
				double usedCash=(Double)row[25];
				double cutoffAmount=(Double)row[21];
				double mdBalance=totcash-usedCash+cutoffAmount;
				mdsProfileDetailsMap.put("totalBalance",f.format(mdBalance));
				mdsProfileDetailsMap.put("cutoffAmount",f.format(row[21]));
				mdsProfileDetailsMap.put("lastAmount",f.format(row[22]));
				date=dateConvert(row[23]);
				mdsProfileDetailsMap.put("updatedDate",date);
				mdsProfileDetailsMap.put("completeMDID",mdId);
				mdsProfileDetailsMap.put("mdIdFlag",clientFlag);
				
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in MdsProfileDao,getMdsProfileDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in MdsProfileDao,getMdsProfileDetails");
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
				System.out.println("Exception in MdsProfileDao,getMdsProfileDetails");
				System.out.println(e.toString());
			}
		}
		return mdsProfileDetailsMap; 
	}
	
	final static String getRandomString(int length) 
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
	
	final String setMdsOTP(String mdId,String clientFlag,String otp){
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
			optDate=optDate+" "+optTime;
			
			int _mdId=Integer.parseInt(mdId);
			txn=session.beginTransaction();
			MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
			if(loginInfo != null)
			{
				
				loginInfo.setOtp(otp);
				loginInfo.setOtpDate(optDate);			  
				session.update(loginInfo);
				txn.commit();
				status="updated";
			}
		}
		catch(HibernateException e){
			try
			{
				if(txn!= null)			 
					txn.rollback();
			}catch(Exception ex)
			{
			}
			System.out.println("Exception in MdsProfileDao,setMdsOTP");
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
			System.out.println("Exception in MdsProfileDao,setMdsOTP");
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
				System.out.println("Exception in MdsProfileDao,setMdsOTP");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetMdsPassword(String mdId,String clientFlag,String password)
	{
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _mdId=Integer.parseInt(mdId);
			txn=session.beginTransaction();
			MdsLoginInfoFormBean loginInfo = (MdsLoginInfoFormBean)session.get(MdsLoginInfoFormBean.class,_mdId);
			if(loginInfo != null)
			{	
				loginInfo.setPassword(password);
				session.update(loginInfo);
				txn.commit();
				status="updated";
			}
		}
		catch(HibernateException e){
			try
			{
				if(txn!= null)
					txn.rollback();
			}catch(Exception ex)
			{
			}
			System.out.println("Exception in MdsProfileDao,resetMdsPassword");
			System.out.println(e.toString());
		}
		catch(Exception e){
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex)
			{
			}
			System.out.println("Exception in MdsProfileDao,resetMdsPassword");
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
				System.out.println("Exception in MdsProfileDao,resetMdsPassword");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetMdsMPIN(String mdId,String clientFlag,String mpin)
	{
		Session session=null;				
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _mdId=Integer.parseInt(mdId);
			txn=session.beginTransaction();
			
			if(clientFlag.equals("1") || clientFlag.equals("2"))
			{		
				MdsDetailsFormBean mdsDetails = (MdsDetailsFormBean)session.get(MdsDetailsFormBean.class,_mdId);
				if(mdsDetails != null)
				{				
					mdsDetails.setMPIN(mpin);			  			  
					session.update(mdsDetails);
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
			System.out.println("Exception in MdsProfileDao,resetMdsMPIN");
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
			System.out.println("Exception in MdsProfileDao,resetMdsMPIN");
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
				System.out.println("Exception in MdsProfileDao,resetMdsMPIN");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	// method Added By Manoj
	final String checkUserName(String emailId, String mdID)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _mdid=Integer.parseInt(mdID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.userName from MdsLoginInfoFormBean mf where mf.userId=:mdID").setParameter("mdID", _mdid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				
				status="valid";
			}else
			{
				Query query1=session.createQuery("select mf.userName from MdsLoginInfoFormBean mf where mf.userName=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
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
				System.out.println("Exception in MdsRegistrationDao,checkUserName");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final String checkMobileNo(String MobileNo, String mdID)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _mdid=Integer.parseInt(mdID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where mf.mdId=:mdID").setParameter("mdID", _mdid);
			
			String OldMobileNo=quey.uniqueResult().toString();
			if(OldMobileNo.equalsIgnoreCase(MobileNo))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select mf.mobileNo from MdsDetailsFormBean mf where mf.mobileNo=:mobileNo").setParameter("mobileNo",MobileNo);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkMobileNo : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
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
				System.out.println("Exception in MdsRegistrationDao,checkMobileNo");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	public String updateMdProfile(String firstName, String lastName,String dob, String gender, String companyType, String companyName,String emailId, String mobileNo, String addressLine1,
			String addressLine2, String state, String district, String city,String pincode, String panNo, String mdID)
	{
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _mdid=Integer.parseInt(mdID);
			String mdName=firstName+" "+lastName;	
			
			String sql="update MD_Details set MD_name='"+mdName+"',Date_of_Birth='"+dob+"',Gender='"+gender+"',Company_name='"+companyName+"',Company_Type='"+companyType+"',Address1='"+addressLine1+"',"+
							"Address2='"+addressLine2+"',State='"+state+"',office_district='"+district+"',City='"+city+"',Pin_code='"+pincode+"',Mobile_no='"+mobileNo+"',PAN_TIN_No='"+panNo+"',First_name='"+firstName+"',Last_name='"+lastName+"'" +
							",Email_id='"+emailId+"' where MD_id="+_mdid;
			
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			sql="update md_login_details set user_name='"+emailId+"' where user_id="+_mdid+" ";
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
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
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
				System.out.println("Exception in MdsRegistrationDao,checkUserName");
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
	
	public String checkEmailID(String emailId, String mdID)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _mdid=Integer.parseInt(mdID);
			
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select mf.emailId from MdsDetailsFormBean mf where mf.mdId=:mdID").setParameter("mdID", _mdid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select mf.emailId from MdsDetailsFormBean mf where mf.emailId=:emailId").setParameter("emailId",emailId);
				for(Iterator it=query1.iterate();it.hasNext();)
				{
					Object row = (Object) it.next();
					status="invalid";
				}
			}
			//System.out.println("status of checkUserName : "+status);
		}
		catch(HibernateException e)
		{
			status="invalid";
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in MdsRegistrationDao,checkUserName");
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
				System.out.println("Exception in MdsRegistrationDao,checkUserName");
				System.out.println(e.toString());
			}
		}
		return status;
	}
}
