package com.controlPanel.profileMgt.retailUser.distributor;

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
import com.formBean.agent.REPAgentLoginInfoFormBean;
import com.formBean.distributor.DistributorDetailsFormBean;
import com.formBean.distributor.DistributorLoginInfoFormBean;

 class DistributorProfileDao
 {
	 final HashMap getDynamicDetails(int distributorId)
	 {
		 HashMap map=new HashMap();
		 Session session=null;
		 int portalId=0;
		 try
		 {
			 session = HibernateSession.getSessionFactory().openSession(); 
			 Query query2=session.createQuery("select df.clientId from DynamicDetailsFormBean df,AdminUserFormBean af,MdsDetailsFormBean mf,DistributorDetailsFormBean ddf where ddf.mdId=mf.mdId and mf.clientId=af.portalId and af.portalId=df.clientId and  ddf.distributorId=:distributorId").setParameter("distributorId",distributorId);
			 for(Iterator it=query2.iterate();it.hasNext();)
			 {
				 Object row = (Object) it.next();
				 portalId=(Integer)row;
			 }
			 
			 Query query1=session.createQuery("select d.adminLoginUrl,d.helpMobileNo,d.helpEmailId,d.domainName,d.companyName,d.mdLoginUrl,d.agentLoginUrl,d.distributorLoginUrl,d.clientFlag  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			 for(Iterator it=query1.iterate();it.hasNext();)
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
			 System.out.println("Exception in DistributorProfileDao,getDynamicDetails");
			 System.out.println(e.toString());
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in DistributorProfileDao,getDynamicDetails");
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
				 System.out.println("Exception in DistributorProfileDao,getDynamicDetails");
				 System.out.println(e.toString());
			 }
		 }
		 return map;
	 }
	 
	 final ArrayList getDistributorTurnOver(int distributorId)
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
			 cstmt.setString(1,"countDistributorTurnOverAmount");
			 cstmt.setInt(2,distributorId);
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
			 cstmt1.setString(1,"distributor");
			 cstmt1.setInt(2,distributorId);
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
				 if(txn!=null)
				 {
					 txn.rollback();
				 }
			 } catch(Exception ex){}
			 try
			 {
				 if(txn1!=null)
				 {
					 txn1.rollback();
				 }
				 
			 }
			 catch(Exception ex){}
			 System.out.println("Exception in DistributorProfileDao,getDistributorTurnOver");
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
				 System.out.println("Exception in DistributorProfileDao,getDistributorTurnOver");
				 System.out.println(e.toString());
			 }
		 }
		 return turnOverList;
	 }
	 
	final HashMap getDistributorProfileDetails(String distributorId)
	{
		Session session=null;  
		HashMap distributorProfileDetailsMap=new HashMap();	 
		String updatedDate="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String qyery="";
			int _distributorId=0;
			String clientFlag="";
			String distributorLoginUrl="";
			String mpin="";
			
			Query query1=session.createQuery("select af.distributorId,df.clientFlag,df.distributorLoginUrl,af.MPIN from DistributorDetailsFormBean af,DynamicDetailsFormBean df,DistributorLoginInfoFormBean alf where (af.distributorInitial+convert(varchar,af.distributorId))=:distributorId and df.distributorUserType=alf.userType and af.distributorId=alf.userId").setParameter("distributorId",distributorId);;
			for(Iterator it=query1.iterate();it.hasNext();)
			{				  
				Object row[] = (Object[]) it.next();
				_distributorId=(Integer)row[0];
				clientFlag=(String)row[1];
				distributorLoginUrl=(String)row[2];
				mpin=(String)row[3];
			}
			
			//Query query3=session.createQuery("select atf.dateOfTransaction,atf.requestedAmount from AgentTransactionDetailsFormBean atf where atf.idNo=(select max(atd.idNo) from AgentTransactionDetailsFormBean atd where atd.distributorId=:distributorId)").setParameter("distributorId",_distributorId);
			Query Query3=session.createSQLQuery("select top 1 Date_of_Transaction,Net_Tran_amount from distributor_Transaction_details where Distributor_id="+_distributorId+" order by ID_No desc");
			
			List list=Query3.list();
			for(Iterator it=list.iterator();it.hasNext();){
				Object row[] = (Object[]) it.next();
				distributorProfileDetailsMap.put("lastDateOfTransaction",row[0]);
				distributorProfileDetailsMap.put("lastTransactionAmount",row[1]);
			}
			String sql="select top 1 Date_of_Transaction from distributor_Transaction_details where Distributor_id="+_distributorId+" and Action_on_Bal_amount='credit' order by ID_No desc";  
			//Query query4=session.createQuery("select ajf.approvalDate from DistributorJournalFormBean ajf where ajf.journalId=(select max(afd.journalId) from DistributorJournalFormBean afd where distributorId=:distributorId)").setParameter("distributorId",_distributorId);
			
			Query query4=session.createSQLQuery(sql);
			list=query4.list();
			for(Iterator it=list.iterator();it.hasNext();)
			{
				Object row = (Object) it.next();
				distributorProfileDetailsMap.put("lastDateOfTBTaken",row);
			}
//			qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.mobileNo,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,af.mdId,af.MPIN";
			qyery="select af.firstName,af.lastName,af.dob,af.gender,af.companyType,af.companyName,af.emailId,af.mobileNo,af.addressLine1,af.addressLine2,af.state,af.district,af.city,af.pincode,af.panNo,af.dateOfJoining,af.MPIN,aaf.totalCash,aaf.usedCash,aaf.cutOffAmount,alf.userName,alf.password,aaf.lastAmount from DistributorDetailsFormBean af,DistributorAmountFormBean aaf,DistributorLoginInfoFormBean alf where af.distributorId=aaf.distributorId and aaf.distributorId=alf.userId and alf.userId=:distributorId";
			System.out.println("qyery is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+qyery);
			Query query5=session.createQuery(qyery).setParameter("distributorId",_distributorId);
			for(Iterator it=query5.iterate();it.hasNext();)
			{
				System.out.println("inside iterator>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				Object row[] = (Object[]) it.next();
				String firstName=(String)row[0];
				String lastName=(String)row[2];
				
				distributorProfileDetailsMap.put("firstName",row[0]==null ? " ": row[0]);
				distributorProfileDetailsMap.put("lastName",row[1]==null ? " ": row[1]);
				distributorProfileDetailsMap.put("dateOfBirth",row[2]==null ? " ": row[2]);
				distributorProfileDetailsMap.put("gender",row[3]==null ? " ": row[3]);
				distributorProfileDetailsMap.put("companyType",row[4]==null ? " ": row[4]);			  
				distributorProfileDetailsMap.put("companyName",row[5]==null ? " ": row[5]);			  
				distributorProfileDetailsMap.put("emailID",row[6]==null ? " ": row[6]);
				distributorProfileDetailsMap.put("mobileNo",row[7]==null ? " ": row[7]);
				distributorProfileDetailsMap.put("address1",row[8]==null ? " ": row[8]);
				distributorProfileDetailsMap.put("address2",row[9]==null ? " ": row[9]);			  
				distributorProfileDetailsMap.put("state",row[10]==null ? " ": row[10]);
				distributorProfileDetailsMap.put("district",row[11]==null ? " ": row[11]);
				distributorProfileDetailsMap.put("city",row[12]==null ? " ": row[12]);
				distributorProfileDetailsMap.put("pincode",row[13]==null ? " ": row[13]);			 
				distributorProfileDetailsMap.put("panNo",row[14]==null ? " ": row[14]);
				String date=dateConvert(row[15]);
				distributorProfileDetailsMap.put("dateOfJoining",date);
				distributorProfileDetailsMap.put("MPIN",row[16]);
				double totCash=(Double)row[17];			  
				double usedCash=(Double)row[18];
				double cutoffAmount=(Double)row[19];
				
				double balance=totCash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				distributorProfileDetailsMap.put("distributorBalance",f.format(balance));
				distributorProfileDetailsMap.put("userName",row[20]==null ? " ": row[20]);
				distributorProfileDetailsMap.put("password",row[21]==null ? " ": row[21]);	
				distributorProfileDetailsMap.put("lastAmount",row[22]==null ? " ": row[22]);
				
				distributorProfileDetailsMap.put("distributorCuttoffAmount",row[19]==null ? " ": row[19]);
				distributorProfileDetailsMap.put("distributorId",_distributorId);
				distributorProfileDetailsMap.put("completeDsID",distributorId);
				distributorProfileDetailsMap.put("distributorIdFlag",clientFlag);	
			}
		}catch(HibernateException e)
		{		
			System.out.println("Exception in DistributorProfileDao,getDistributorProfileDetails");
			System.out.println(e.toString());
		}catch(Exception e)
		{
			System.out.println("Exception in DistributorProfileDao,getDistributorProfileDetails");
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
				System.out.println("Exception in DistributorProfileDao,getDistributorProfileDetails");
				System.out.println(e.toString());
			}
		}
		return distributorProfileDetailsMap;  
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
	
	final String setDistributorOTP(String distributorId,String clientFlag,String otp)
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
			
			int _distributorId=Integer.parseInt(distributorId);
			txn=session.beginTransaction();
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
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
				REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,_distributorId);
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
			}catch(Exception ex){
			}
			System.out.println("Exception in DistributorProfileDao,setDistributorOTP");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorProfileDao,setDistributorOTP");
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
				System.out.println("Exception in DistributorProfileDao,setDistributorOTP");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetDistributorPassword(String distributorId,String clientFlag,String password)
	{
		Session session=null;
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _distributorId=Integer.parseInt(distributorId);
			txn=session.beginTransaction();
			if(clientFlag.equals("0") || clientFlag.equals("2"))
			{
				DistributorLoginInfoFormBean loginInfo = (DistributorLoginInfoFormBean)session.get(DistributorLoginInfoFormBean.class,_distributorId);
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
				REPAgentLoginInfoFormBean reploginInfo = (REPAgentLoginInfoFormBean)session.get(REPAgentLoginInfoFormBean.class,_distributorId);
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
			}catch(Exception ex)
			{
			}
			System.out.println("Exception in DistributorProfileDao,resetAgentPassword");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorProfileDao,resetAgentPassword");
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
				System.out.println("Exception in DistributorProfileDao,resetAgentPassword");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String resetDistributorMPIN(String distributorId,String clientFlag,String mpin)
	{
		Session session=null;				
		Transaction txn=null;
		String status="notupdated";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _distributorId=Integer.parseInt(distributorId);
			txn=session.beginTransaction();
			if(clientFlag.equals("1") || clientFlag.equals("2"))
			{		
				DistributorDetailsFormBean agentDetails = (DistributorDetailsFormBean)session.get(DistributorDetailsFormBean.class,_distributorId);
				if(agentDetails != null)
				{				
					agentDetails.setMPIN(mpin);			  			  
					session.update(agentDetails);
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
			System.out.println("Exception in DistributorProfileDao,resetAgentMPIN");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			try
			{
				if(txn!= null)
					txn.rollback();
			}
			catch(Exception ex){
			}
			System.out.println("Exception in DistributorProfileDao,resetAgentMPIN");
			System.out.println(e.toString());
		}
		finally{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in DistributorProfileDao,resetAgentMPIN");
				System.out.println(e.toString());
			}
		}
		return status;
	}
	
	final String checkUserName(String emailId, String dsId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _dsid=Integer.parseInt(dsId);
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select df.userName from DistributorLoginInfoFormBean df where df.userId=:DSID").setParameter("DSID", _dsid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select df.userName from DistributorLoginInfoFormBean df where df.userName=:emailId").setParameter("emailId",emailId);
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
			System.out.println("Exception in DistributorRegistrationDao, checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName ");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	final String checkMobileNo(String mobileNo, String dsId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _dsid=Integer.parseInt(dsId);
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where df.distributorId=:DSID").setParameter("DSID", _dsid);
			
			String OldMobileNo=quey.uniqueResult().toString();
			if(OldMobileNo.equalsIgnoreCase(mobileNo))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select df.mobileNo from DistributorDetailsFormBean df where df.mobileNo=:mobileNo").setParameter("mobileNo",mobileNo);
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
			System.out.println("Exception in checkUserName");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in checkUserName");
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
				System.out.println("Exception in checkUserName");
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
	
	public String updateDSProfile(String firstName, String lastName,String dob, String gender, String companyType, String companyName,
			String emailId, String mobileNo, String addressLine1,String addressLine2, String state, String district, String city,String pincode, String panNo, String dsId) 
	{
		Session session=null;         
		String status="success";
		Transaction ts=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int _dsid=Integer.parseInt(dsId);
			String dsName=firstName+" "+lastName;
			
			String sql="update distributor_Details set distributor_name='"+dsName+"',Date_Of_Birth='"+dob+"',Gender='"+gender+"',company_name='"+companyName+"',Company_Type='"+companyType+"',address1='"+addressLine1+"',"+
							"address2='"+addressLine2+"',state='"+state+"',office_district='"+district+"',city='"+city+"',pin_code='"+pincode+"',mobile_no='"+mobileNo+"',PAN_TIN_No='"+panNo+"',first_name='"+firstName+"',last_name='"+lastName+"'" +
							",email_id='"+emailId+"' where distributor_id="+_dsid;
			
			Query query1=session.createSQLQuery(sql);
			query1.executeUpdate();
			sql="update distributor_login_details set user_name='"+emailId+"' where user_id="+_dsid+" ";
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
			System.out.println("Exception in MdsRegistrationDao,updateDSProfile");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			ts.rollback();
			status="fail";	
			System.out.println("Exception in MdsRegistrationDao,updateDSProfile");
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
				System.out.println("Exception in MdsRegistrationDao,updateDSProfile");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	public String checkEmailID(String emailId, String dsId)
	{
		Session session=null;         
		String status="valid";
		try
		{
			int _dsid=Integer.parseInt(dsId);
			session = HibernateSession.getSessionFactory().openSession(); 
			Query quey=session.createQuery("select df.emailId from DistributorDetailsFormBean df where df.distributorId=:DSID").setParameter("DSID", _dsid);
			
			String OldemailID=quey.uniqueResult().toString();
			if(OldemailID.equalsIgnoreCase(emailId))
			{
				status="valid";
			}else
			{
				Query query1=session.createQuery("select df.emailId from DistributorDetailsFormBean df where df.emailId=:emailId").setParameter("emailId",emailId);
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
			System.out.println("Exception in DistributorRegistrationDao, checkEmailID");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			status="invalid";	
			System.out.println("Exception in DistributorRegistrationDao, checkEmailID");
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
				System.out.println("Exception in DistributorRegistrationDao, checkEmailID");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
}