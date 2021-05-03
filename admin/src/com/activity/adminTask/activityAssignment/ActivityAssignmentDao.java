package com.activity.adminTask.activityAssignment;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.formBean.adminUser.ServiceAuthenticationFormBean;

/**
 * 	Updated By : Manoj
 */

public class ActivityAssignmentDao{
	
	public String getPortalUserIdStatus(String portalUserId,String userId){
		
		Session session=null;
		String portalUserClientId="";
		String clientId="";
		String status="invalid";
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			
			Query query=session.createQuery("select cf.portalId from AdminUserFormBean cf where convert(varchar,cf.userId)=:userId").setParameter("userId",portalUserId);
			
			for(Iterator it=query.iterate();it.hasNext();){
				
				Object row = (Object) it.next();
				portalUserClientId=((Integer)row).toString();
			}

			Query query1=session.createQuery("select cf.portalId from AdminUserFormBean cf where convert(varchar,cf.userId)=:userId").setParameter("userId",userId);
			
			for(Iterator it=query1.iterate();it.hasNext();){
				  
				Object row = (Object) it.next();
				clientId=((Integer)row).toString();
			}

			if(portalUserClientId.equals(clientId))
			{
			
				status="valid";
			}

			else
			{
				status="invalid";
			}
		}catch(Exception E)
		{
			status="invalid";
			System.out.println("Exception in getPortalUserIdStatus======");
			System.out.println(E.toString());
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
				System.out.println("Exception in getPortalUserIdStatus while closing connection"+e.toString());
			}
		}
		
		return status;
	}


	public String getPortalUserIdStatus(String portalUserId)
	{
		Session session=null;
		String portalUserClientId="";
		String status="invalid";
		
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			
			Query query=session.createQuery("select cf.portalId from AdminUserFormBean cf where convert(varchar,cf.userId)=:userId").setParameter("userId",portalUserId);
			
			for(Iterator it=query.iterate();it.hasNext();){
				  
		      Object row = (Object) it.next();
			   portalUserClientId=((Integer)row).toString();
			   status="valid";
			}
		}catch(Exception E)
		{
			status="invalid";
			System.out.println("Exception in getPortalUserIdStatus======"+E.toString());
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
				System.out.println("Exception in getPortalUserIdStatus while closing connection"+e.toString());
			}
		}
		return status;
	}
	
	public String updateUserService(String portalUserId, String userReg,String internalUserReg, String corporateUserReg,
			String retailUserReg, String mdsReg, String profileMgt,String selfProfile, String internal_profile_mgt,
			String corporate_profile_mgt, String retail_profile_mgt,
			String adminTask, String categoryAssign, String dbTask,String schedularRun, String tradeBalanceActivity,
			String tbFinanceAdmin, String tbClientAdmin,String transactionReport, String tranChannelreport,
			String tranEgenreport, String managementReport,String mgtChannelReport, String mgtEgenReport,
			String financeReport, String finChannelReport,String finEgenReport, String statement, String channelStatement,
			String egenStatement, String ccModule, String transactionStatus,String notice, String ccInfo, String clientReport,
			String clientTranReport, String cutOffMangt, String cutOffSetup,String cutOffView, String accountMgt, String accountmanagement,
			String commTdsMgt, String refund, String egenControl,String operatorMgt, String clientServiceControl, String vedorMgt,
			String clientOperatorControl, String clientCutOffView,String egenRefund, String egenTranStatus, String marginSetup,
			String marginWL, String marginAG, String marginEGEN,String cCuserSearch, String sKUmgt, String editSKU, String addSKU,
			String opsMgt, String serviceMgt, String tepVendorMgt,String chargeSet, String agentCharge, String aPIcharge,
			String inventoryMgmt,String carBooking,String holidayPkg,String IMController,String setMarkup,String apiAssignment) 
	{
		Session session=null;
		Transaction txn=null;
		String status="invalid";
         
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			
			int _portalUserId=Integer.parseInt(portalUserId);
			txn=session.beginTransaction();

			ServiceAuthenticationFormBean serviceInfo = (ServiceAuthenticationFormBean)session.get(ServiceAuthenticationFormBean.class,_portalUserId);			
			
			if(serviceInfo != null){
				
				// User Registration
				serviceInfo.setUserRegistration(userReg);
				serviceInfo.setInternalUserreg(internalUserReg);
				serviceInfo.setCorporateUserreg(corporateUserReg);
				serviceInfo.setRetailUserreg(retailUserReg);
				serviceInfo.setMDReg(mdsReg);
			 
			 // Admin Task
			
				serviceInfo.setProfileMgt(profileMgt);
				serviceInfo.setSelfProfile(selfProfile);
				serviceInfo.setInternaluserProfileMgt(internal_profile_mgt);
				serviceInfo.setCorporateUserProfileMgt(corporate_profile_mgt);
				serviceInfo.setRetailUserProfileMgt(retail_profile_mgt);
			 
				serviceInfo.setAdminTasks(adminTask);
				serviceInfo.setCategoryAssignment(categoryAssign);
				serviceInfo.setDBTask(dbTask);
				serviceInfo.setSchedulerRun(schedularRun);
			 
				serviceInfo.setTradeBalanceActivity(tradeBalanceActivity);
				serviceInfo.setTBFinance(tbFinanceAdmin);
				serviceInfo.setTBClient(tbClientAdmin);
			 
				serviceInfo.setTransactionReport(transactionReport);
				serviceInfo.setTranChannel(tranChannelreport);
				serviceInfo.setTranEGEN(tranEgenreport);
				serviceInfo.setManagementReport(managementReport);
				serviceInfo.setMgtChannel(mgtChannelReport);
				serviceInfo.setMgtEGEN(mgtEgenReport);
				serviceInfo.setFinanceReport(financeReport);
				serviceInfo.setFinanceChannel(finChannelReport);
				serviceInfo.setFinanceEgen(finEgenReport);
				serviceInfo.setStatements(statement);
				serviceInfo.setStmtChannel(channelStatement);
				serviceInfo.setStmtEGEN(egenStatement);
				serviceInfo.setClientReport(clientReport);
				serviceInfo.setTranclientreport(clientTranReport);
			 
				serviceInfo.setCCModule(ccModule);
				serviceInfo.setNoticeAlert(notice);
				serviceInfo.setCCInfo(ccInfo);
				serviceInfo.setTransactionStatus(transactionStatus);
				serviceInfo.setCCSearchUser(cCuserSearch);
				serviceInfo.setCutOffManagement(cutOffMangt);
				serviceInfo.setCutOffSetup(cutOffSetup);
				serviceInfo.setCutOffView(cutOffView);
				serviceInfo.setAccountMgt(accountMgt);
				serviceInfo.setAccountManagement(accountmanagement);
				serviceInfo.setCommTDSmgt(commTdsMgt);
				serviceInfo.setRefund(refund);
				serviceInfo.setEGENControl(egenControl);
				serviceInfo.setOperatorManagement(operatorMgt);
				serviceInfo.setClientServiceControl(clientServiceControl);
				serviceInfo.setVendorManagement(vedorMgt);
				serviceInfo.setClientOperatorControl(clientOperatorControl);
				serviceInfo.setClientCutOffView(clientCutOffView);
				serviceInfo.setEGENRefund(egenRefund);
				serviceInfo.setEgenTranStatus(egenTranStatus);
				serviceInfo.setMarginSetup(marginSetup);
				serviceInfo.setWLMargin(marginWL);
				serviceInfo.setAgentMargin(marginAG);
				serviceInfo.setEGENClientMargin(marginEGEN);
			 
				serviceInfo.setChargesSet(chargeSet);
				serviceInfo.setAgentCharges(agentCharge);
				serviceInfo.setAPIClientCharges(aPIcharge);
			 
				serviceInfo.setSKUMgt(sKUmgt);
				serviceInfo.setAddSKU(addSKU);
				serviceInfo.setEditSKU(editSKU);
			 
				serviceInfo.setOpsManagement(opsMgt);
				serviceInfo.setServiceManagement(serviceMgt);
				serviceInfo.setTEPvendorManagement(tepVendorMgt);
			 
				session.update(serviceInfo);
				txn.commit();
				status="valid";
			}
		}
		catch(Exception e){
			status="invalid";
			System.out.println("Exception in updatePortalUserActivity "+e.toString());
			try{
				if(txn!=null){
					txn.rollback();			
				}
			}
			catch(HibernateException ex){
				System.out.println(e.toString());
			}
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
				System.out.println("Exception in updatePortalUserActivity while closing connection ");
				System.out.println(e.toString());
			}
		}
		return status;
	}
}
