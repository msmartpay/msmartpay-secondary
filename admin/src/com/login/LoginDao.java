
package com.login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.SendSmsOnMobile;
import com.formBean.adminUser.AdminUserFormBean;


public class LoginDao
{

	final HashMap getDynamicDetails(String userId)
	{
		HashMap map=new HashMap();
		Session session=null;
		int portalId=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			int _userId=Integer.parseInt(userId);

			Query query2=session.createQuery("select lf.portalId from AdminUserFormBean lf where  lf.userId=:userId").setParameter("userId",_userId);

			for(Iterator it=query2.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				portalId=(Integer)row;
			}

			Query query1=session.createQuery("select d.adminLoginUrl,d.helpMobileNo,d.helpEmailId,d.LogoImage,d.domainName,d.companyName,d.mdLoginUrl,d.agentLoginUrl,d.distributorLoginUrl,d.clientFlag,d.ClientUserType,d.powerBy  from DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId",portalId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();
				map.put("adminLoginUrl", (String) row[0]);
				map.put("helpMobileNo", (String) row[1]);
				map.put("helpEmailId", (String) row[2]);
				map.put("mdHeaderHomeImage", (String) row[3]);
				map.put("domainName", (String) row[4]);
				map.put("companyName", (String) row[5]);
				map.put("mdLoginUrl", (String) row[6]);
				map.put("agentLoginUrl", (String) row[7]);
				map.put("distributorLoginUrl", (String) row[8]);
				map.put("clientFlag", (String) row[9]);
				map.put("ClientUserType",(String)row[10]);
				map.put("powerBy", (String) row[11]);
				map.put("clientID", portalId);
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in LoginDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoginDao, getDynamicDetails");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao, getPortalAdminBalance" );
				e.printStackTrace();
			}
		}
		return map;
	}

	final public HashMap getUserServiceAuthenticationDetails(String userId)
	{
		HashMap map=new HashMap();
		Session session=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			Query query1=session.createQuery("select " +

					"s.activity,s.UserRegistration,s.InternalUserreg,s.CorporateUserreg,s.RetailUserreg,s.MDReg," +
					"s.AdminTasks,s.ActivityAssignment,s.CategoryAssignment,s.DBTask,s.SchedulerRun,"+
					"s.TradeBalanceActivity,s.TBSuperAdmin,s.TBFinance,s.TBClient," +
					"s.ControlPanel,s.ProfileMgt,s.InternaluserProfileMgt,s.CorporateUserProfileMgt,s.RetailUserProfileMgt,s.SelfProfile,"+
					"s.CutOffManagement,s.CutOffSetup,s.CutOffView," +
					"s.AccountMgt,s.AccountManagement,s.commTDSmgt,s.Refund," +
					"s.EGENControl,s.OperatorManagement,s.ClientServiceControl,s.VendorManagement,s.ClientOperatorControl,s.ClientCutOffView,s.EGENRefund,s.EgenTranStatus," +
					"s.OpsManagement,s.ServiceManagement,s.TEPvendorManagement," +
					"s.SKUMgt,s.EditSKU,s.AddSKU," +
					"s.MarginSetup,s.WLMargin,s.AgentMargin,s.EGENClientMargin," +
					"s.Reports,s.TransactionReport,s.TranChannel,s.TranEGEN," +
					"s.ManagementReport,s.MgtChannel,s.MgtEGEN," +
					"s.FinanceReport,s.FinanceChannel,s.FinanceEgen," +
					"s.Statements,s.StmtChannel,s.stmtEGEN,"+
					"s.CCModule,s.TransactionStatus,s.NoticeAlert," +
					"s.ClientReport,s.Tranclientreport,s.CCInfo,s.CCSearchUser," +
					"s.ChargesSet,s.AgentCharges,s.APIClientCharges "+
					"from ServiceAuthenticationFormBean s where convert(varchar,s.userId)=:userId")
					.setParameter("userId",userId);

			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();

				map.put("activity", (String) row[0]);
				map.put("UserRegistration", (String) row[1]);
				map.put("InternalUserreg", (String) row[2]);
				map.put("CorporateUserreg", (String) row[3]);
				map.put("RetailUserreg", (String) row[4]);
				map.put("MDReg", (String) row[5]);

				map.put("AdminTasks", (String) row[6]);
				map.put("ActivityAssignment", (String) row[7]);
				map.put("CategoryAssignment", (String) row[8]);
				map.put("DBTask", (String) row[9]);
				map.put("SchedulerRun", (String) row[10]);

				map.put("TradeBalanceActivity", (String) row[11]);
				map.put("TBSuperAdmin", (String) row[12]);
				map.put("TBFinance", (String) row[13]);
				map.put("TBClient", (String) row[14]);

				map.put("ControlPanel", (String) row[15]);
				map.put("ProfileManagement", (String) row[16]);
				map.put("InternaluserProfileMgt", (String) row[17]);
				map.put("CorporateUserProfileMgt", (String) row[18]);
				map.put("RetailUserProfileMgt", (String) row[19]);
				map.put("SelfProfile", (String) row[20]);

				map.put("CutOffManagement", (String) row[21]);
				map.put("CutOffSetup", (String) row[22]);
				map.put("CutOffView", (String) row[23]);

				map.put("AccountMgt", (String) row[24]);
				map.put("AccountManagement", (String) row[25]);
				map.put("commTDSmgt", (String) row[26]);
				map.put("Refund", (String) row[27]);

				map.put("EGENControl", (String) row[28]);
				map.put("OperatorManagement", (String) row[29]);
				map.put("ClientServiceControl", (String) row[30]);
				map.put("VendorManagement", (String) row[31]);
				map.put("ClientOperatorControl", (String) row[32]);
				map.put("ClientCutOffView", (String) row[33]);
				map.put("EGENRefund", (String) row[34]);
				map.put("EgenTranStatus", (String) row[35]);

				map.put("OpsManagement", (String) row[36]);
				map.put("ServiceManagement", (String) row[37]);
				map.put("TEPvendorManagement", (String) row[38]);

				map.put("SKUMgt", (String) row[39]);
				map.put("EditSKU", (String) row[40]);
				map.put("AddSKU", (String) row[41]);

				map.put("marginSetup", (String) row[42]);
				map.put("WLMargin", (String) row[43]);
				map.put("AgentMargin", (String) row[44]);
				map.put("EGENClientMargin", (String) row[45]);

				map.put("Reports", (String) row[46]);
				map.put("TransactionReport", (String) row[47]);
				map.put("TranChannel", (String) row[48]);
				map.put("TranEGEN",(String)row[49]);
				map.put("ManagementReport",(String)row[50]);
				map.put("MgtChannel",(String)row[51]);
				map.put("MgtEGEN",(String)row[52]);
				map.put("FinanceReport",(String)row[53]);
				map.put("FinanceChannel",(String)row[54]);
				map.put("FinanceEgen",(String)row[55]);
				map.put("Statements",(String)row[56]);
				map.put("StmtChannel",(String)row[57]);
				map.put("stmtEGEN",(String)row[58]);
				map.put("CCModule",(String)row[59]);
				map.put("TransactionStatus",(String)row[60]);
				map.put("NoticeAlert",(String)row[61]);
				map.put("ClientReport",(String)row[62]);
				map.put("Tranclientreport",(String)row[63]);
				map.put("CCInfo",(String)row[64]);
				map.put("CCSearchUser",(String)row[65]); 

				map.put("ChargesSet",(String)row[66]);
				map.put("AgentCharges",(String)row[67]);
				map.put("APIClientCharges",(String)row[68]);
			}
			System.out.println("Service MAP : "+map);
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in LoginDao,getUserServiceAuthenticationDetails");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoginDao,getUserServiceAuthenticationDetails");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao,getUserServiceAuthenticationDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}

	final String updateLoginStatus(String verificationCode,String userId)
	{
		Session session=null;
		Transaction txn=null;
		String status="notVerified";
		int countNo=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			Query query1=session.createQuery("select lf.userId from LoginInfoFormBean lf where convert(varchar,lf.userId)=:userId and lf.verificationCode=:verificationCode").setParameter("verificationCode",verificationCode).setParameter("userId",userId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				countNo=1;
			}

			if(countNo>=1) 
			{
				txn=session.beginTransaction();
				int userid=Integer.parseInt(userId);
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);			
				if(loginInfo != null)
				{	
					loginInfo.setVerifiedStatus("Y");
					loginInfo.setPasswordStatus("newpass");		 
					session.update(loginInfo);
					txn.commit();
					status="Verified";
				}
			}
			else
			{
				status="notVerified";
			}
		}catch(Exception e)
		{
			try{
				if(txn!=null){
					txn.rollback();			
				}
			}
			catch(HibernateException ex)
			{
			}
			System.out.println("Exception in LoginDao,updateLoginStatus");
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
				System.out.println("Exception in LoginDao,updateLoginStatus ");
				e.printStackTrace();
			}
		}
		return status;
	}

	final HashMap getOldvarificationCode( String userId,String emailId)
	{
		String code ="";
		String exist="Yes";
		Session session=null;
		Session session2=null;
		Transaction txn=null;
		Transaction   txn1=null;
		HashMap map=new HashMap();
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			session2 = HibernateSession.getSessionFactory().openSession();
			System.out.println("inside method"+session);
			Query query1=session.createQuery("select lf.emailId from AdminUserFormBean lf where convert(varchar,lf.userId)!=:userId and lf.emailId=:emailId").setParameter("userId",userId).setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				//emailId=(String)row;
				exist="YES";
			}
			if(exist.equalsIgnoreCase("No"))
			{
				txn1=session.beginTransaction();
				int userid=Integer.parseInt(userId);	 
				/* LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);
			  System.out.println("loginInfo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+loginInfo);
				if(loginInfo != null){
			     loginInfo.setUserName(emailId);			
			     session.update(loginInfo);		
			     txn1.commit();
			  }*/
				txn=session2.beginTransaction();
				AdminUserFormBean login = (AdminUserFormBean)session2.get(AdminUserFormBean.class,userid);	
				if(login !=null)
				{
					login.setEmailId(emailId);
					session2.update(login);		
					txn.commit();
				}
			}
			Query query2=session.createQuery("select lf.verificationCode from LoginInfoFormBean lf where convert(varchar,lf.userId)=:userId").setParameter("userId",userId);
			for(Iterator it=query2.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				code=(String)row;
				exist="No";
			}
			map.put("code",code);
			map.put("exist",exist);		
		}
		catch(HibernateException e)
		{
			map.put("exist","No");	
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
			}catch(Exception ex){}
			System.out.println("Exception in LoginDao,getOldvarificationCode");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			map.put("exist","No");	
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
			System.out.println("Exception in LoginDao, getOldvarificationCode");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.close();
				session2.close();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				System.out.println("Exception in LoginDao,getOldvarificationCode ");
				e.printStackTrace();
			}
		}
		return map;
	}

	final String changePasswordOnLogin(String password,String userId,String emailId,String verifyCode)
	{
		Session session=null;
		Session session2=null;
		Transaction txn=null;
		Transaction   txn1=null;
		String exist="NO";
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			session2 = HibernateSession.getSessionFactory().openSession();
			System.out.println("inside method"+session);

			Query query1=session.createQuery("select lf.emailId from AdminUserFormBean lf where convert(varchar,lf.userId)!=:userId and lf.emailId=:emailId").setParameter("userId",userId).setParameter("emailId",emailId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				//emailId=(String)row;
				exist="YES";
			}
			System.out.println("exist is>>>>>>>>>>>>>"+exist);
			if(exist.equalsIgnoreCase("NO"))
			{                
				int userid=Integer.parseInt(userId);   
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);
				if(loginInfo!=null)
				{
					txn1=session.beginTransaction();
					loginInfo.setPassword(password);
					loginInfo.setVerificationCode(verifyCode);
					loginInfo.setPasswordStatus("newpass");
					loginInfo.setVerifiedStatus("N");
					session.update(loginInfo);
					txn1.commit();
				}
				AdminUserFormBean login = (AdminUserFormBean)session2.get(AdminUserFormBean.class,userid);
				System.out.println("login>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+login);
				if(login!=null)
				{
					txn=session2.beginTransaction();
					login.setEmailId(emailId);
					session2.update(login);
					txn.commit();
				}
			}
		}
		catch(HibernateException e)
		{
			try
			{
				if(txn!=null)
				{
					txn.rollback();
				}
			}catch(HibernateException ex)
			{
			}
			try
			{
				if(txn1!=null)
				{
					txn1.rollback();
				}
			}
			catch(HibernateException ex){
			}
			System.out.println("Exception in LoginDao,changePasswordOnLogin");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			try
			{
				if(txn!=null)
				{
					txn.rollback();
				}
			}catch(HibernateException ex)
			{
			}
			try
			{
				if(txn1!=null)
				{
					txn1.rollback();
				}
			}
			catch(HibernateException ex)
			{
			}
			System.out.println("Exception in LoginDao, changePasswordOnLogin");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
				session2.flush();
				session2.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao, changePasswordOnLogin ");
				e.printStackTrace();
			}
		}
		return exist;	
	}

	public static final String getPortalAdminBalance(String userId)
	{
		Session session=null;
		String availableBalanceAmount="";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="select convert(decimal(30,2),Total_cash-Used_cash+Cutoff_amount) as balance from dbo.Admin_user_amount_details where Portal_id="+Long.parseLong(userId);
			availableBalanceAmount=session.createSQLQuery(sql).uniqueResult().toString();

		}
		catch(HibernateException e)
		{
			System.out.println("Exception in LoginDao,getPortalAdminBalance========");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in LoginDao,getPortalAdminBalance========");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao,getPortalAdminBalance ");
				e.printStackTrace();
			}
		}
		return availableBalanceAmount; 
	}

	final String  getRandomCode() 
	{
		String code="";
		try
		{   
			int n = 8;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			code = String.valueOf(randomNum);
		}catch(Exception e)
		{
			System.out.println("Exception in LoginDao, getRandomCode");
			e.printStackTrace();
		}
		return code;
	}

	@SuppressWarnings("unchecked")
	final String checkLoginDetails(String userName,String password,Map appSession, String ip)
	{
		Session session =null;
		String status="invalid";
		String userId="";
		String loginStatus="";
		String finalStatus="";
		String firstName="";
		String lastName="";
		String name="";
		String blockStatus="";
		double availableBalanceAmount=0.00;
		String userType="";
		String portalId="";	
		String blockStatusClient="";
		String clientId="";
		String CompanyName="";
		try
		{			
			session = HibernateSession.getSessionFactory().openSession();				
			Query query=session.createQuery("select ds.userName,ds.password,ds.userId,ds.loginStatus,ds.userType,ds.blockStatus from LoginInfoFormBean ds where ds.userName=:userName and ds.password=:password").setParameter("userName",userName).setParameter("password",password);
			for(Iterator it=query.iterate();it.hasNext();)
			{				  
				Object[] row = (Object[]) it.next();		           
				userId=((Integer)row[2]).toString();
				loginStatus=(String)row[3];
				userType=(String)row[4];
				blockStatus=(String)row[5];
				status="valid";
			}
			appSession.put("userId",userId);
			appSession.put("adminUserType",userType);
			if(blockStatus==null)
			{
				blockStatus="";
			}
			if(status.equalsIgnoreCase("valid")){
				if(loginStatus.equalsIgnoreCase("ni"))
				{
					finalStatus="Deactive";
					return  finalStatus;
				}
				else{
					if(blockStatus.equalsIgnoreCase("blocked"))
					{
						finalStatus="Blocked";
						return finalStatus;
					}else
					{
						int _userID=Integer.parseInt(userId);
						String sql="select d.companyName,d.blockStatus,d.clientId from DynamicDetailsFormBean d where d.clientId =some(select a.portalId from AdminUserFormBean a where a.userId=:user_id)";
						List list=session.createQuery(sql).setParameter("user_id", _userID).list();
						Iterator itr=list.iterator();
						while(itr.hasNext())
						{
							Object[] Obj=(Object[])itr.next();
							CompanyName=(String)Obj[0];
							blockStatusClient=(String)Obj[1];
							clientId=""+(Integer)Obj[2];
						}
						if(blockStatusClient==null)
						{
							blockStatusClient="";
						}
						if(status.equals("valid")&& loginStatus.equalsIgnoreCase("Activate") && !blockStatus.equalsIgnoreCase("blocked"))
						{
							Query query1=session.createQuery("select lf.firstName,lf.lastName,(af.totalCash-af.usedCash+af.cutOffAmount),lf.portalId from AdminUserFormBean lf,UserAmountFormBean af where convert(varchar,lf.userId)=:userId and lf.userId=af.userId").setParameter("userId",userId);
							for(Iterator it=query1.iterate();it.hasNext();)
							{				  
								Object[] row = (Object[]) it.next();		             
								firstName=(String)row[0];							
								lastName=(String)row[1];
								availableBalanceAmount=(Double)row[2];
								portalId=((Integer)row[3]).toString();	
							}				 
							name= firstName+" "+lastName;
							appSession.put("userName",name);
							appSession.put("balance",availableBalanceAmount);
							appSession.put("userId",userId);
							appSession.put("adminUserPortalId",portalId);
							appSession.put("headerName",CompanyName);
							appSession.put("clientId", clientId);
							System.out.println("clientId : "+clientId);
							session.beginTransaction();
							Query queryinsert=session.createSQLQuery("insert into Admin_Iptracking (id,user_type,date_of_login,time_of_login,ip) values ('"+userId+"','"+userType+"',getdate(),getdate(),'"+ip+"')");
							int check=queryinsert.executeUpdate();
							session.getTransaction().commit();
							if(check>1)
							{
								finalStatus="valid";
							}else
							{
								finalStatus="error";
							}
							Integer id=0;
							appSession.put("loginType", userType);

							finalStatus="valid";		
							return finalStatus;				
						}
						if(!userType.equalsIgnoreCase("activityAdmin")){
							if(blockStatusClient.equalsIgnoreCase("Blocked")){
								finalStatus="Blocked";
								return finalStatus;
							}
						}

					}
				}
			}else if(status.equalsIgnoreCase("invalid"))
			{
				finalStatus="invalid";
				return finalStatus;
			}
		}catch(HibernateException  ex)
		{
			session.getTransaction().rollback();
			finalStatus="error";   
			ex.printStackTrace();
		}		

		catch(Exception  ex)
		{

			finalStatus="error";
			ex.printStackTrace();
		}				
		finally 
		{
			try
			{
				if(session!=null)
					session.close();
			}catch(Exception ex){
				System.out.println("Exception in LoginDao,checkLoginDetails while closing connection");
				System.out.println(ex.toString());
			}
		}		
		return finalStatus;
	}

	final HashMap chekLoginInfo(String userId) 
	{
		HashMap mapData =new HashMap();
		Session session =null;		
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			Query query=session.createQuery("select ld.passwordStatus,ld.password,ld.oldPassword,ld.verifiedStatus,lud.emailId,ld.mobileVerifiedStatus from LoginInfoFormBean ld,AdminUserFormBean lud where ld.userId=lud.userId and convert(varchar,ld.userId)=:userId").setParameter("userId",userId);
			for(Iterator it=query.iterate();it.hasNext();)
			{
				Object[] row = (Object[]) it.next();	
				mapData.put("passwordStatus",(String)row[0]);
				mapData.put("password",(String)row[1]);
				mapData.put("oldPassword",(String)row[2]);
				mapData.put("verifiedStatus",(String)row[3]);
				mapData.put("emailId",(String)row[4]);
				mapData.put("mobileVerifiedStatus",(String)row[5]);		           
			}			
		}		
		catch(HibernateException  ex)
		{
			ex.printStackTrace();				
			System.out.println("Exception in LoginDao,chekLoginInfo");
		}			
		catch(Exception  ex){
			ex.printStackTrace();				  
			System.out.println("Exception in LoginDao,chekLoginInfo");
		}			
		finally 
		{
			try
			{
				if(session!=null)session.close();
			}				
			catch(Exception ex){
				System.out.println("Exception in LoginDao,chekLoginInfo");
				ex.printStackTrace();
			}
		}
		return mapData;		
	}
	final String generateVerifyCodeForMobileVerification(String userId,String verificationCode,String mobileNo)
	{
		Session session =null;
		Transaction   txn1=null;
		String response="";
		try
		{			
			session = HibernateSession.getSessionFactory().openSession();			
			int userid=Integer.parseInt(userId);			
			LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);			
			int flag=0;			  
			if(loginInfo!=null)
			{
				txn1=session.beginTransaction();
				loginInfo.setMobileVerificationCode(verificationCode);	  
				session.update(loginInfo);
				txn1.commit();
				flag=1;
			}		   
			SendSmsOnMobile sendsms=new SendSmsOnMobile();
			if(flag>0)
			{
				String message="Dear Admin, Please verify your Mobile Number "+mobileNo+" in order to access your services & profile update. Your Verification code is "+verificationCode+"";
				sendsms.sendMobileSmsSMSZONE(mobileNo,message);			
				response="valid";		
			}				
			else
			{
				response="invalid";
			}
		}
		catch(Exception E)
		{
			try
			{
				if(txn1!=null)
				{
					txn1.rollback();
				}
			}
			catch(Exception e){			   
			}
			response="invalid";
			System.out.println("Exception in LoginDao,generateVerifyCodeForMobileVerification");
			E.printStackTrace();
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao, generateVerifyCodeForMobileVerification ");
				e.printStackTrace();
			}			
		}		
		return response;	
	}

	final String getUserMobileNo(String userId)
	{		 
		Session session=null;         
		String mobileNo="invalid";
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			Query query1=session.createQuery("select af.officialNumber from AdminUserFormBean af where convert(varchar,af.userId)=:userId").setParameter("userId",userId);		 
			for(Iterator it=query1.iterate();it.hasNext();)
			{				  
				Object row = (Object) it.next();
				mobileNo=(String)row;
			}			
		}
		catch(HibernateException e)
		{			
			System.out.println("Exception in LoginDao getUserMobileNo");
			e.printStackTrace();
		}		
		catch(Exception e)
		{


			System.out.println("Exception inLoginDao, getPortalAdminBalance");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao,getUserMobileNo ");
				e.printStackTrace();
			}
		}
		return mobileNo; 
	}

	final String updateFinalLoginStatus(String mobVerificationCode,String userId)
	{
		Session session=null;
		Transaction txn=null;
		String status="notVerified";
		int countNo=0;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();	  
			Query query1=session.createQuery("select lf.userId from LoginInfoFormBean lf where convert(varchar,lf.userId)=:userId and lf.mobileVerificationCode=:mobVerificationCode").setParameter("mobVerificationCode",mobVerificationCode).setParameter("userId",userId);
			for(Iterator it=query1.iterate();it.hasNext();)
			{
				Object row = (Object) it.next();
				countNo=1;
			}  
			if(countNo>=1) 
			{
				txn=session.beginTransaction();
				int userid=Integer.parseInt(userId);
				LoginInfoFormBean loginInfo = (LoginInfoFormBean)session.get(LoginInfoFormBean.class,userid);
				if(loginInfo != null)
				{	
					loginInfo.setMobileVerifiedStatus("Y");	
					session.update(loginInfo);
					txn.commit();
					status="verified";			
				}
			}				
			else{
				status="notVerified";			
			}	
		}catch(Exception e)
		{		
			try{				
				if(txn!=null)
				{
					txn.rollback();
				}
			}catch(Exception ex)
			{
			}
			status="notVerified";
			System.out.println("Exception in LoginDao,updateFinalLoginStatus");
			e.printStackTrace();
		}		
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in LoginDao,updateFinalLoginStatus  ");
				e.printStackTrace();
			}
		}
		return status;
	}

	final String  saveVerificationCode(String vcode,String userId,String emailId) 
	{
		Session session=null;
		Transaction ts=null;
		String sql="";
		Query query=null;
		try
		{
			int id=Integer.parseInt(userId);
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			sql="update admin_login_details set Verification_code='"+vcode+"' where User_id="+id+"";
			query=session.createSQLQuery(sql);
			int result=query.executeUpdate();
			ts.commit();
			return "success";

		}catch(Exception E)
		{		
			System.out.println(E.toString());
			ts.rollback();
			return "err";
		}finally
		{
			try
			{
				session.close();
			}catch(Exception e)	
			{
				System.out.println(e);				
			}			
		}
	}

	/*
	 * Dash board Information 
	 * 
	*/
	public HashMap<String,Object> dashboard(long portalId) {
		
		Session session = null;
		Connection con=null;
		CallableStatement cstmt = null;
		HashMap<String,Object> map=new HashMap<String,Object>();
		
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			cstmt = con.prepareCall("{call Admin_Dashbord(?)}");
			cstmt.setLong(1, portalId);

			ResultSet rs = cstmt.executeQuery();
			if(rs.next()){
				map.put("agentBal", rs.getDouble(1));
				map.put("dsBal", rs.getDouble(2));
				map.put("mdsBal", rs.getDouble(3));
				map.put("adminBal", rs.getDouble(4));
				
				map.put("totalActiveAgent", rs.getLong(5));
				map.put("totalSuccessTran", rs.getDouble(6));
				map.put("totalFailedTran", rs.getDouble(7));
				map.put("pendingCount", rs.getLong(8));
				map.put("successCount", rs.getLong(9));
				map.put("failedCount", rs.getLong(10));
				map.put("totalBal", rs.getLong(11));
				
				map.put("totalRecharge", rs.getLong(12));
				map.put("totalBill", rs.getLong(13));
				map.put("totalDMT", rs.getLong(14));
				map.put("totalAeps", rs.getLong(15));
				
			}
			System.out.println("dashboard : "+map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try 
			{
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();

			} catch (Exception e)
			{
				System.out.println("Exception in ClientReportDao,getBillPaymentReport");
				System.out.println(e.toString());
			}
		}
		return map;
	}
}
