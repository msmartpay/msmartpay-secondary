/*
  Class Property :  This class (LoginDao) is created for fetching data from database.

  Created Date   : 2-Dec-2011 at 9:45 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 2-Dec-2011.
  Update By      : Prachi Sharma.


 */

package com.login.superadmin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;



import com.common.HibernateSession;

	/**
	* Updated By :Arvind
	* Updated Date : 6 Jun 2013
	* Updated Matter : Format of class.
	*/

 public class SuperAdminLoginDao {
	
	public static final String getSuperAdminBalance(String userId) 
	{
		Session session = null;
		String balance="";
		try {
			session = HibernateSession.getSessionFactory().openSession();
			String sql="select convert(decimal(30,2),Total_cash-Used_cash+Cutoff_amount) as balance from SuperAdmin_Amount_Details where Super_admin_id=1";
			balance=session.createSQLQuery(sql).uniqueResult().toString();
		}
		catch (HibernateException e) {
			System.out.println("Exception in SuperAdminLoginDao,getSuperAdminBalance");
			System.out.println(e.toString());
		}
		catch (Exception e) {
			System.out.println("Exception in SuperAdminLoginDao,getSuperAdminBalance");
			System.out.println(e.toString());
		}
		finally {
			try {
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in SuperAdminLoginDao,getSuperAdminBalance");
				System.out.println(e.toString());
			}
		}
		return balance;
	}

	final String getRandomCode() {
		String code = "";
		try {
			int n = 8;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n - 1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			code = String.valueOf(randomNum);
		} catch (Exception e) {
			System.out.println("Exception in SuperAdminLoginDao,getRandomCode");
			e.printStackTrace();}
		return code;
	}

	final HashMap<String,Object> getDynamicDetails() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		Session session = null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			Query query1 = session.createQuery("select d.adminLoginUrl,d.helpMobileNo," +
					"d.helpEmailId,d.LogoImage,d.domainName,d.companyName,d.mdLoginUrl," +
					"d.agentLoginUrl,d.distributorLoginUrl,d.clientFlag,d.powerBy  from " +
					"DynamicDetailsFormBean d where convert(varchar,d.clientId)=:portalId").setParameter("portalId", "10001");
			for (Iterator<?> it = query1.iterate(); it.hasNext();) 
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
				map.put("powerBy", (String) row[10]);
			}
		}catch (HibernateException e) {
			System.out.println("Exception in SuperAdminLoginDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		catch (Exception e) {
			System.out.println("Exception in SuperAdminLoginDao,getDynamicDetails");
			System.out.println(e.toString());
		}
		finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in SuperAdminLoginDao,getDynamicDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}
	final HashMap<String,Object> getUserServiceAuthenticationDetails() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		Session session = null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			Query query1 = session.createQuery("select " +
					"s.activity,s.UserRegistration,s.InternalUserreg,s.CorporateUserreg,s.RetailUserreg,s.MDReg," +
					"s.AdminTasks,s.ActivityAssignment,s.CategoryAssignment,s.DBTask,s.SchedulerRun,"+"s.TradeBalanceActivity,s.TBSuperAdmin,s.TBFinance,s.TBClient," +
					"s.ControlPanel,s.ProfileMgt,s.InternaluserProfileMgt,s.CorporateUserProfileMgt,s.RetailUserProfileMgt,s.SelfProfile,"+
					"s.CutOffManagement,s.CutOffSetup,s.CutOffView," +"s.AccountMgt,s.AccountManagement,s.commTDSmgt,s.Refund," +
					"s.EGENControl,s.OperatorManagement,s.ClientServiceControl,s.VendorManagement,s.ClientOperatorControl,s.ClientCutOffView,s.EGENRefund,s.EgenTranStatus," +
					"s.OpsManagement,s.ServiceManagement,s.TEPvendorManagement," +"s.SKUMgt,s.EditSKU,s.AddSKU," +"s.MarginSetup,s.WLMargin,s.AgentMargin,s.EGENClientMargin," +
					"s.Reports,s.TransactionReport,s.TranChannel,s.TranEGEN," +"s.ManagementReport,s.MgtChannel,s.MgtEGEN," +
					"s.FinanceReport,s.FinanceChannel,s.FinanceEgen," +"s.Statements,s.StmtChannel,s.stmtEGEN,"+"s.CCModule,s.TransactionStatus,s.NoticeAlert," +
					"s.ClientReport,s.Tranclientreport,s.CCInfo,s.CCSearchUser," +"s.ChargesSet,s.AgentCharges,s.APIClientCharges "+
					"from ServiceAuthenticationFormBean s where convert(varchar,s.userId)=:userId"
			)
			.setParameter("userId", "1");
			for (Iterator<?> it = query1.iterate(); it.hasNext();)
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
				
				map.put("InventoryMgmt","Y");
				map.put("InventoryMgmtController","Y");
				map.put("CarBooking","Y");
				map.put("HolidayPkg","Y");
				map.put("SetMarkup","Y");
				map.put("APIAssignment","Y");
				
			}
		}
		catch (HibernateException e) {
			
			System.out.println("Exception in SuperAdminLoginDao,getUserServiceAuthenticationDetails");
			System.out.println(e.toString());
		}
		catch (Exception e) {
			System.out.println("Exception in SuperAdminLoginDao,getUserServiceAuthenticationDetails");
			System.out.println(e.toString());
			}
		finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in SuperAdminLoginDao,getUserServiceAuthenticationDetails");
				System.out.println(e.toString());
			}
		}
		return map;
	}

	final String checkLoginDetails(String userName, String password,Map appSession, String ip) {
		Session session = null;
		String status = "invalid";
		String userId = "";
		String finalStatus = "";
		double totalBalanceAmount = 0.00;
		String userType = "";
		String pass = "";
		try 
		{
        
			System.out.println(">>>>>>>>>>>>   userName : "+userName+"   password : "+password);
			
			session = HibernateSession.getSessionFactory().openSession();
			//Query query = session
			//.createQuery(
				//"select ds.userName,ds.password,ds.userId,ds.userType from SuperAdminLoginInfoFormBean ds where ds.userName=:userName and ds.password=:password")
				//.setParameter("userName", userName).setParameter("password", password);
			
			Query query = session.createSQLQuery("select Super_admin_User_name,Super_admin_password,Super_admin_id,User_type from SuperAdmin_Login_Details where Super_admin_User_name='"+userName+"' and Super_admin_password='"+password+"'");
			
			
			List list=query.list();
			System.out.println("List Size : "+list.size());
			for (Iterator it = list.iterator(); it.hasNext();) 
			{
				Object[] row = (Object[]) it.next();
				pass = (String) row[1];
				userId = ( row[2])+"";
				userType = (String) row[3];
				status = "valid";
			}
			if (password.equals(pass)) 
			{
				appSession.put("userId", userId);
				appSession.put("adminUserType", userType);
				appSession.put("dynamicDetails", new HashMap());
				if (status.equals("valid"))
				{
					Query query1 = session
					.createQuery("select (af.totalCash-af.usedCash+cutOffAmount) from SuperAdminAmountFormBean af where convert(varchar,af.userId)=:userId")
					.setParameter("userId", userId);
					for (Iterator it = query1.iterate(); it.hasNext();) 
					{
						Object row = (Object) it.next();
						totalBalanceAmount = (Double) row;
					}
					appSession.put("loginType","superadmin");
					appSession.put("userName", "Super Admin");
					appSession.put("balance", totalBalanceAmount);
					appSession.put("userId", userId);
					appSession.put("headerName","Super Admin");
					session.beginTransaction();
					Query queryinsert=session.createSQLQuery("insert into Admin_Iptracking (id,user_type,date_of_login,time_of_login,ip) values ('"+userId+"','SuperAdmin',getdate(),getdate(),'"+ip+"')");
					int check=queryinsert.executeUpdate();
					session.getTransaction().commit();
					if(check>1)
					{
						finalStatus="valid";
					}else{
						finalStatus="error";
					}
					finalStatus = "valid";
				}
				else {
					finalStatus = "invalid";
				}
			}
			else 
			{
				finalStatus = "invalid";
			}
		}
		catch (HibernateException ex) 
		{
			System.out.println("Exception in SuperAdminLoginDao,checkLoginDetails");
			finalStatus = "invalid";
			System.out.println(ex.toString());
			
		}
		catch (Exception ex) 
		{
			finalStatus = "invalid";
			System.out.println("Exception in SuperAdminLoginDao,checkLoginDetails" );
			System.out.println(ex.toString());
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			}
			catch (Exception ex) 
			{
				System.out.println("Exception in SuperAdminLoginDao,checkLoginDetails while closing connection");
				System.out.println(ex.toString());
			}
		}
		
		return finalStatus;
	}

	final String getUserMobileNo(String userId) {
		Session session = null;
		String mobileNo = "invalid";
		
		try {
		
			session = HibernateSession.getSessionFactory().openSession();
			Query query1 = session
			.createQuery("select af.officialNumber from AdminUserFormBean af where convert(varchar,af.userId)=:userId")
			.setParameter("userId", userId);
		
			for (Iterator<?> it = query1.iterate(); it.hasNext();) {
				Object row = (Object) it.next();
				mobileNo = (String) row;
			}
		}
		catch (HibernateException e) {
			System.out.println("Exception in SuperAdminLoginDao, getUserMobileNo");
			System.out.println(e.toString());
		}
		catch (Exception e) {
			System.out.println("Exception in SuperAdminLoginDao,getPortalAdminBalance");
			System.out.println(e.toString());
		}
		finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				System.out.println("Exception in SuperAdminLoginDao,getUserMobileNo while closing connection");
				System.out.println(e.toString());
			}
		}
		return mobileNo;
	}
}
