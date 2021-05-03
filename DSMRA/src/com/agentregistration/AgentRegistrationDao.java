package com.agentregistration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.LogWriter;
import com.disttributordetails.DistributorProfileDao;

public class AgentRegistrationDao {

	public String chkEmail(String emailId) {
	
		Logger logger = Logger.getLogger(AgentRegistrationDao.class);
		LogWriter log=new LogWriter();
		
		Session session = HibernateSession.getSessionFactory().openSession();
		String emailResponse="valid";	 
		try 
		{
			Query query=session.createQuery("select ADF.AgentUserId from AgentLoginForm ADF where ADF.userName=:email").setParameter("email",emailId);
			log.print("AgentRegistrationDao -chkEmail- query is "+query, logger);
			Iterator<?> it=query.iterate();

			if(it.hasNext())
			{
				Object row = (Object) it.next();
				emailResponse="invalid"; 
			}
		} 		 
		catch (Exception ex) {
			emailResponse="invalid"; 
			ex.printStackTrace();
		}
		finally {
			session.flush();
			session.close();
		}
		return emailResponse;
	}
	public String chkMobile(String athoMobile) {
		Logger logger = Logger.getLogger(AgentRegistrationDao.class);
		LogWriter log=new LogWriter();		
		Session session = HibernateSession.getSessionFactory().openSession();
		String Response="valid";		 
		try 
		{
			Query query=session.createQuery("select ADF.mobileNo from AgentDetailForm ADF where ADF.mobileNo=:mobileNo").setParameter("mobileNo",athoMobile);
			log.print("AgentRegistrationDao -chkEmail- query is "+query, logger);
			Iterator it=query.iterate();
	
			if(it.hasNext())
			{
				Object row = (Object) it.next();
				Response="invalid"; 
			}
		} 	 
		catch (Exception ex) {
			Response="invalid";
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		return Response;
	}

	public String getRandomString(int length) {		
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public HashMap getServiceDetails(String userId, String panNo,
			String emailId, String athoMobile, String idProof,
			String addressProof, String bussinessProof, String password) {
		Logger logger = Logger.getLogger(DistributorProfileDao.class);
		LogWriter log=new LogWriter();		
		HashMap ServiceDetailsMapInfo=new HashMap();
		Session session = HibernateSession.getSessionFactory().openSession();		 
		try 
		{
			Query query=session.createQuery("select DDF.liverechargeMobile,DDF.air,DDF.rail,DDF.rechargeMobile,DDF.rechargeDth,DDF.billMobile,DDF.billLic,DDF.busTicketBooking,DDF.panCardRequest  from DistributorDetailForm DDF where DDF.distributorId=:DistributorId").setParameter("DistributorId",userId);
			log.print("distributor Login query is "+query, logger);
			Iterator it=query.iterate();
			
			if(it.hasNext())
			{
				Object[] row = (Object[]) it.next();
				ServiceDetailsMapInfo.put("liverechargeMobile",row[0]);
				ServiceDetailsMapInfo.put("air", row[1]);
				ServiceDetailsMapInfo.put("rail", row[2]);
				ServiceDetailsMapInfo.put("rechargeMobile", row[3]);
				ServiceDetailsMapInfo.put("rechargeDth", row[4]);
				ServiceDetailsMapInfo.put("billMobile", row[5]);
				ServiceDetailsMapInfo.put("billLic", row[6]);
				ServiceDetailsMapInfo.put("busTicketBooking",row[7]);
				ServiceDetailsMapInfo.put("panCardRequest",row[8]);
				ServiceDetailsMapInfo.put("panNo", panNo);
				ServiceDetailsMapInfo.put("emailId", emailId);
				ServiceDetailsMapInfo.put("athoMobile", athoMobile);
				ServiceDetailsMapInfo.put("idProof",idProof);
				ServiceDetailsMapInfo.put("addressProof",addressProof);
				ServiceDetailsMapInfo.put("bussinessProof",bussinessProof);
				ServiceDetailsMapInfo.put("password",password);				 
			}
		}		 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
		return ServiceDetailsMapInfo;
	}

	public String checkPanNo(String panNo) {		
		Logger logger = Logger.getLogger(AgentRegistrationDao.class);
		LogWriter log=new LogWriter();
		Session session = HibernateSession.getSessionFactory().openSession();
		String status="valid";	
		try 
		{
			Query query=session.createQuery("select ADF.PanNo from AgentDetailForm ADF where ADF.PanNo=:panNo").setParameter("panNo",panNo);
			log.print("AgentRegistrationDao -checkPanNo- query is "+query, logger);
			
			for(Iterator<?> it=query.iterate();it.hasNext();)
			{		
				Object row = (Object) it.next();
				status="invalid";
			}
		} 		 
		catch(HibernateException e)
		{  			
			status="invalid";
			System.out.println("Exception in checkPanNo========"+e.toString());
		}	 
		catch (Exception ex) {
			status="invalid"; 
			ex.printStackTrace();
		}
		finally {
			session.flush();
			session.close();
		}
		return status;
	}

	public HashMap<String,Object> registerAgent(String DSFullId, String firstName,String lastname,
			String dateOfBirth, String gender,
			String companyType, String companyName,
			String panNo, String officeAddress1,
			String officeAddress2, String officeState, String officeDistrict,
			String officeCountry, String officeCity, String officePincode,
			String authoEmailId, String password,
			String athoMobile) {
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		Session session=null;      
		int pincode=Integer.parseInt(officePincode);
		Connection con=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			session.beginTransaction();
			con=session.connection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Agent_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setString(1,DSFullId);
			cstmt.setString(2,firstName);
			cstmt.setString(3,lastname);
			cstmt.setString(4,dateOfBirth);
			cstmt.setString(5,gender);
			cstmt.setString(6,companyType);
			cstmt.setString(7,companyName);
			cstmt.setString(8,authoEmailId);
			cstmt.setString(9,athoMobile);
			cstmt.setString(10,officeAddress1);
			cstmt.setString(11,officeAddress2);
			cstmt.setString(12,officeCountry);
			cstmt.setString(13,officeState);
			cstmt.setString(14,officeDistrict);		
			cstmt.setString(15,officeCity);
			cstmt.setInt(16,pincode);
			cstmt.setString(17,panNo);				
			cstmt.setString(18,password);
			cstmt.setString(19,DSFullId);
			cstmt.setInt(20,0);
			System.out.println("in registerAgent block:" );
			ResultSet rs=cstmt.executeQuery();
			session.getTransaction().commit();
	     
			while(rs.next()){				
				map.put("clientFlag",rs.getString(2));
				map.put("completeAgentId",rs.getString(3));
				//map.put("agentId",rs.getString(3));
				map.put("distributorEmailId",rs.getString(4));
				map.put("MPIN",rs.getString(5));	 
			}			
		}	 
		catch(HibernateException e){
			if(session.getTransaction()!= null)			 
				session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		catch(SQLException e){
			if(session.getTransaction()!= null)
				session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		 catch(Exception e){
			 session.getTransaction().rollback();
			 e.printStackTrace();
			 return null;
		 }
		 finally
		 {
			 try
			 {
				 if(con!=null)
					 con.close();
				 session.flush();
				 session.close();
			 }
			 catch(Exception e)
			 {
				 System.out.println("Exception in registerAgent while closing connection"+e.toString());
			 }		
		 }
		 return map;
	}
	
	@SuppressWarnings("deprecation")
	public String updateAgentCreationCharge(String userId,String trnId,String refrId,String IpAdress,Double dutAmount,String completeAgentId ){
		Logger logger = Logger.getLogger(AgentRegistrationDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		String status="";
		double newAmount=0;
		try{				
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction tr=session.beginTransaction();				
			String mdid="";
			Double total=0.0;
			Double usedcash=0.0;
			Double cutoffamount=0.0;
				
			String sql="select ADF.mdId from DistributorDetailForm ADF where ADF.distributorId="+userId+"";
			System.out.println(sql);
			Query qry = session.createQuery(sql);						
			log.print("DistributorDetailForm query in updateAgentCreationCharge is "+qry, logger);
			mdid=(String)qry.uniqueResult();
			System.out.println("mdid is :::"+mdid);
			System.out.println("dutAmount :"+dutAmount);			
				
			Query qry2=session.createQuery("select AAF.totalCash,AAF.usedCash,AAF.cutoffAamount from DistributorAmountForm AAF where AAF.distributorId=:userId").setParameter("userId", userId);
			log.print("updateAgentAccount is "+qry2, logger);
			Iterator<?> it=qry2.iterate();			
			if(it.hasNext())
			{
				Object[] row=(Object[])it.next();
				total=(Double)row[0];
				usedcash=(Double)row[1];
				cutoffamount=(Double)row[2];
			}		    
			double avilableBalance = total-usedcash;						 
			System.out.println("avilableBalance ::"+avilableBalance);							
			newAmount=avilableBalance-dutAmount;
			System.out.println("newAmount :"+newAmount);		
			
			String sqlquery="update distributor_amount  set usedcash=usedcash+"+dutAmount+",last_amount="+dutAmount+" where distributor_id='"+userId+"'";
				
			Query query = session.createSQLQuery(sqlquery);
				
			int result = query.executeUpdate();			
			System.out.println("before inserting query block:" );
			String sql3="insert into distributor_Transaction_details" +
			"(Transaction_No,Refrence_id,Distributor_id,Md_id,User_Type,Time_of_Transaction,Service,Tran_amount,Commission,Service_charge,Bank_charge,Other_charge,Net_Tran_amount,Action_on_Bal_amount,Previous_Bal_amount,Updated_Bal_amount,Tran_status,Final_Bal_amount,Tran_ip_address,Updated_Date,Updated_time,Updated_User,Updated_ip_adddress,Remarks)" +
			"values('"+trnId+"','"+refrId+"','"+userId+"','"+mdid+"','distributor',getdate(),'AgentCreationCharge','"+dutAmount+"','0','0','0','0','"+dutAmount+"','debit','"+avilableBalance+"','"+newAmount+"','success','"+newAmount+"','"+IpAdress+"',getDate(),getdate(),'distributor','"+IpAdress+"','AgentCreationCharge on "+completeAgentId+"')";
			System.out.println(sql3);
			Query qry1 = session.createSQLQuery(sql3);
			System.out.println(sql3);
			int result1 = qry1.executeUpdate();
			System.out.println("insert query result is :::"+result1);
			tr.commit();
			status="valid";
		}
	    catch(Exception e){
			 session.getTransaction().rollback();
			 System.out.println("Exception in registerAgent"+e.toString());
			 status="invalid";		 
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
				 System.out.println("Exception in registerAgent while closing connection"+e.toString());
			 }		
		 }
		 return status;
	}
	
 public String checkEmail(String emailId){  		 
  		 Session session=null; 
  		 int count=0;
  		 String status="valid"; 		 
  		 try{
  			 session = HibernateSession.getSessionFactory().openSession();   			 
  			 Query query1=session.createSQLQuery("select count(agent_id) from agent_details where email_id='"+emailId+"' "); 		
  			 /*for(Iterator it=query1.iterate();it.hasNext();){  				  
  				 Object row = (Object) it.next();
  				 status="invalid";
  			 }*/
  			 count=(Integer)query1.uniqueResult();  
  			 System.out.println("count is "+count);
  			 if(count>0){
  				 status="invalid";
  			 }else{
  				 status="valid";
  			 }  			
  			 System.out.println("status is is>>>>>>>>>>>>>"+status);
  		 }
  		 catch(HibernateException e)
  		 {  			
  			 status="invalid";
  			 System.out.println("Exception in checkUserName========"+e.toString());
  		 }  		
  		 catch(Exception e)
  		 {  			 
  			 status="invalid";	
  			 System.out.println("Exception in checkUserName========"+e.toString());
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
  				 System.out.println("Exception in checkUserName while closing connection========"+e.toString());
  			 }  			
  		 }
  		 return status; 	 
 }
 public String checkMob(String mob){  		 
		 Session session=null; 
		 int count=0;
		 String status="valid"; 		 
		 try{
			 session = HibernateSession.getSessionFactory().openSession();   			 
			 Query query1=session.createSQLQuery("select count(agent_id) from agent_details where mobile_no='"+mob+"' "); 		
			 /*for(Iterator it=query1.iterate();it.hasNext();){  				  
				 Object row = (Object) it.next();
				 status="invalid";
			 }*/
			 count=(Integer)query1.uniqueResult();  
			 if(count>0){
				 status="invalid";
			 }
			
			 System.out.println("status is is>>>>>>>>>>>>>"+status);
		 }
		 catch(HibernateException e)
		 {  			
			 status="invalid";
			 System.out.println("Exception in checkUserName========"+e.toString());
		 }  		
		 catch(Exception e)
		 {  			 
			 status="invalid";	
			 System.out.println("Exception in checkUserName========"+e.toString());
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
				 System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			 }  			
		 }
		 return status; 	 
}

	public String getMdEmailId(String userId) {
		 Logger logger = Logger.getLogger(AgentRegistrationDao.class);
		 LogWriter log=new LogWriter();
		 Session session = HibernateSession.getSessionFactory().openSession();
		 String email="";		 
		 try 
		    {
			 Query query=session.createQuery("select  MDF.UserName from MdLoginDetailsForm MDF where UseriD in(select mdId from DDF.DistributorDetailForm DDF where DDF.distributorId=:userId)").setParameter("userId",userId);
			 log.print("AgentRegistrationDao -getMdEmailId- query is "+query, logger);
			 Iterator<?> it=query.iterate();

			 if(it.hasNext())
			 {
				 Object row = (Object) it.next();
				 email=(String)row; 
			 }
		    } 	 
		 catch (Exception ex) {
			
			 ex.printStackTrace();
		 }
		 finally {
			 session.flush();
			 session.close();
		 }
		 return email;
	}

	public String getTranId() {
		String transaction_id="";
		try
		{		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
		Date date=new Date();
		String tran_id=sdf.format(date);
		@SuppressWarnings("unused")
		Random rand = new Random();
		int n = 4;
		Random randGen = new Random();
		int startNum = (int) Math.pow(10, n-1);
		int range = (int) (Math.pow(10, n) - startNum);
		int randomNum = randGen.nextInt(range) + startNum;
		String ran = String.valueOf(randomNum);
		transaction_id=tran_id+ran;
		}
		catch(Exception e)
		{
		System.out.println("hello Execption in CheckAvailabilityDao"+e.toString());
		}
	return transaction_id;
}
}
