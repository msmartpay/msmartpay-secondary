package com.reports.ccModule.ccInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;


/**
 *  Created By : Manoj Khan
 *  Created Matter : for updation of Cc Information
 *  Creatde Date : 22/02/2013
 */
// Live Db OnlineRechAPI_Live_db
// Test DB OnlineRechAPI_db_2013Feb2
class CcInfoDao {

	final String checkUser(String userType, String searchBy, String userid) 
	{
		System.out.println("In checkUser1");
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        String status="fail";
        System.out.println("In checkUser2");
        try{
        	if(userType.equalsIgnoreCase("adminUser")){
        		sqlQuery="select count(user_id) from admin_user_details where user_id='"+userid+"' ";
        	}else if(userType.equalsIgnoreCase("mds")){
        		sqlQuery="select count(md_id) from md_details where md_initial+convert(varchar(10),md_id)='"+userid+"' ";
        	}else if(userType.equalsIgnoreCase("egen")){
        		sqlQuery="select count(Corporate_Agent_Id) from  Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Details where Corporate_Agent_initial+convert(varchar(10),Corporate_Agent_Id)='"+userid+"' ";
        	}
        	System.out.println(sqlQuery);
        	qry=session.createSQLQuery(sqlQuery);
        	int count=Integer.parseInt(qry.uniqueResult().toString());
        	System.out.println(count);
        	if(count==1){
        		status="success";
        	}else{
        		status="fail";
        	}
        }catch (Exception e) {
        	status="fail";
			System.out.println("Exception in CcInfoDao method is checkUser");
			System.out.println(e.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in CcInfoDao,checkUser");
				System.out.println(e.toString());
			}
		}
		return status;
	}

	final ArrayList getCCData(String userType, String searchBy, String userID) {
		ArrayList listData =new ArrayList();
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        String status="fail";
        String id=userID.replace("API", "");
        try{
        	if(searchBy.equalsIgnoreCase("all")){
        		//System.out.println("we are into all in dao class");
        		if(userType.equalsIgnoreCase("adminUser")){
        			sqlQuery="select aud.Portal_id,aud.user_id,wld.company_name,(aud.First_name+' '+aud.Last_name) as user_name,ald.Login_status,ald.Block_status,amd.Total_Cash,amd.Used_Cash,amd.Cutoff_Amount,amd.Total_balance_Amount,amd.Available_balance_amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree,wld.company_name,wld.category_Name,wld.flag from admin_user_details aud,admin_login_details ald, Admin_user_amount_details amd,admin_ccinfo_tab ac,white_label_details wld where aud.user_id=ald.user_id and aud.user_id=amd.portal_id and aud.user_id=ac.user_id and aud.portal_id=wld.client_id order by ac.user_id";
        		}else if(userType.equalsIgnoreCase("mds")){
        			sqlQuery="select md.client_id,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.Company_name,md.MD_name,mld.Login_status,mld.Block_status,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount,mc.category,mc.min_deposit,mc.min_rate,mc.AVID_block,mc.AVID_Undertaking,mc.verify_pan,mc.verify_agreement,mc.remark,mc.depositeOne,mc.rateOne,mc.depositeTwo,mc.rateTwo,mc.depositeThree,mc.rateThree,wld.company_name,wld.category_Name,wld.flag from md_details md,md_login_details mld, md_amount ma,md_ccinfo_tab mc,white_label_details wld where md.md_id=mld.user_id and md.md_id=ma.md_id and md.md_id=mc.md_id and wld.client_id=md.client_id order by mc.md_id";
        		}else if(userType.equalsIgnoreCase("egen")){
        			sqlQuery="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,ad.Agency_Name,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as user_name,ld.Status,ld.Block_status,am.Total_Cash,am.Used_Cash,am.Cutoff_Amount,am.Total_Bal_Amount,am.Available_Bal_Amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree from Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Details ad,Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Auth_details ld,Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Amount am,Onelineapi05032013.dbo.api_ccinfo_tab ac where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id=am.Corporate_Agent_Id and ad.Corporate_Agent_Id=ac.user_id order by ac.user_id";
        		}
        	}
        	else{
        		if(userType.equalsIgnoreCase("adminUser")){
            		sqlQuery="select aud.Portal_id,aud.user_id,wld.company_name,(aud.First_name+' '+aud.Last_name) as user_name,ald.Login_status,ald.Block_status,amd.Total_Cash,amd.Used_Cash,amd.Cutoff_Amount,amd.Total_balance_Amount,amd.Available_balance_amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree,wld.company_name,wld.category_Name,wld.flag from admin_user_details aud,admin_login_details ald, Admin_user_amount_details amd,admin_ccinfo_tab ac,white_label_details wld where aud.user_id=ald.user_id and aud.user_id=amd.portal_id and aud.user_id=ac.user_id and aud.portal_id=wld.client_id  and aud.user_id='"+userID+"' order by ac.user_id";
            	}else if(userType.equalsIgnoreCase("mds")){
            		sqlQuery="select md.client_id,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.Company_name,md.MD_name,mld.Login_status,mld.Block_status,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount,mc.category,mc.min_deposit,mc.min_rate,mc.AVID_block,mc.AVID_Undertaking,mc.verify_pan,mc.verify_agreement,mc.remark,mc.depositeOne,mc.rateOne,mc.depositeTwo,mc.rateTwo,mc.depositeThree,mc.rateThree,wld.company_name,wld.category_Name,wld.flag from md_details md,md_login_details mld, md_amount ma,md_ccinfo_tab mc,white_label_details wld where md.md_id=mld.user_id and md.md_id=ma.md_id and md.md_id=mc.md_id and wld.client_id=md.client_id and md.md_initial+convert(varchar(10),md.md_id)='"+userID+"' order by mc.md_id ";
            	}else if(userType.equalsIgnoreCase("egen")){
            		sqlQuery="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,ad.Agency_Name,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as user_name,ld.Status,ld.Block_status,am.Total_Cash,am.Used_Cash,am.Cutoff_Amount,am.Total_Bal_Amount,am.Available_Bal_Amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree from Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Details ad,Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Auth_details ld,Onelineapi05032013.dbo.Rech_API_Corporate_Agent_Amount am,Onelineapi05032013.dbo.api_ccinfo_tab ac where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id=am.Corporate_Agent_Id and ad.Corporate_Agent_Id=ac.user_id and ad.Corporate_Agent_Id='"+id+"' order by ac.user_id";
            	}
        	}
        	System.out.println(sqlQuery);
        	qry=session.createSQLQuery(sqlQuery);
        	List list=qry.list();
        	Iterator itr=list.iterator();
        	Object[] row;
        	HashMap map;
        	while(itr.hasNext()){
        		map=new HashMap();
        		if(userType.equalsIgnoreCase("egen")){
        			row=(Object[])itr.next();
        			map.put("userID", row[0].toString());
        			map.put("companyName", row[1].toString());
        			map.put("nameOfUser", row[2].toString());
        			map.put("loginStatus", row[3].toString());
        			map.put("blockStatus", (String)row[4]);
        			map.put("totalCash", row[5].toString());
        			map.put("usedCash", row[6].toString());
        			map.put("cutOff", row[7].toString());
        			map.put("totalBal", row[8].toString());
        			map.put("availableBal", row[9].toString());
        			map.put("userCategory", row[10].toString());
        			map.put("userMinDeposite", row[11].toString());
        			map.put("userMinRate", row[12].toString());
        			map.put("userAVIDBlock", row[13].toString());
        			map.put("userAVIDUndertaking", row[14].toString());
        			map.put("userVerifyPan", row[15].toString());
        			map.put("userVerifyAgreement", row[16].toString());
        			map.put("userRemark", row[17].toString());
        			map.put("userDepositeOne", row[18].toString());
        			map.put("userRateOne", row[19].toString());
        			map.put("userDepositetwo", row[20].toString());
        			map.put("userRateTwo", row[21].toString());
        			map.put("userDepositeThree", row[22].toString());
        			map.put("userRateThree", row[23].toString());
        			
        			listData.add(map);
        		}
        		if(userType.equalsIgnoreCase("mds") ||userType.equalsIgnoreCase("adminUser")){
        			row=(Object[])itr.next();
        			map.put("clientID", row[0].toString());
        			map.put("userID", row[1].toString());
        			map.put("companyName", row[2].toString());
        			map.put("nameOfUser", (String)row[3]);
        			map.put("loginStatus", row[4].toString());
        			map.put("blockStatus", (String)row[5]);
        			map.put("totalCash", row[6].toString());
        			map.put("usedCash", row[7].toString());
        			map.put("cutOff", row[8].toString());
        			map.put("totalBal", row[9].toString());
        			map.put("availableBal", row[10].toString());
        			map.put("userCategory", row[11].toString());
        			map.put("userMinDeposite", row[12].toString());
        			map.put("userMinRate", row[13].toString());
        			map.put("userAVIDBlock", row[14].toString());
        			map.put("userAVIDUndertaking", row[15].toString());
        			map.put("userVerifyPan", row[16].toString());
        			map.put("userVerifyAgreement", row[17].toString());
        			map.put("userRemark", row[18].toString());
        			map.put("userDepositeOne", row[19].toString());
        			map.put("userRateOne", row[20].toString());
        			map.put("userDepositetwo", row[21].toString());
        			map.put("userRateTwo", row[22].toString());
        			map.put("userDepositeThree", row[23].toString());
        			map.put("userRateThree", row[24].toString());
        			map.put("clientCompanyName", row[25].toString());
        			map.put("clientCategory", row[26].toString());
        			map.put("clientFlag", row[27].toString());
  
        			listData.add(map);
        		}
        		System.out.println("Map : "+map);
        	}
        	
        }catch (Exception e) {
			System.out.println("Exception in CcInfoDao method is getCCData");
			System.out.println(e.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in CcInfoDao,getCCData");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	final ArrayList getEditSlabData(String userType, String searchBy, String userID) {
		
		ArrayList listData =new ArrayList();
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        try{
        	if(searchBy.equalsIgnoreCase("all")){
        		//System.out.println("we are into all in dao class");
        		if(userType.equalsIgnoreCase("adminUser")){
        			sqlQuery="select aud.Portal_id,aud.user_id,wld.company_name,(aud.First_name+' '+aud.Last_name) as user_name,ald.Login_status,ald.Block_status,amd.Total_Cash,amd.Used_Cash,amd.Cutoff_Amount,amd.Total_balance_Amount,amd.Available_balance_amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree,wld.company_name,wld.category_Name,wld.flag from admin_user_details aud,admin_login_details ald, Admin_user_amount_details amd,admin_ccinfo_tab ac,white_label_details wld where aud.user_id=ald.user_id and aud.user_id=amd.portal_id and aud.user_id=ac.user_id and aud.portal_id=wld.client_id order by ac.user_id";
        		}else if(userType.equalsIgnoreCase("mds")){
        			sqlQuery="select md.client_id,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.Company_name,md.MD_name,mld.Login_status,mld.Block_status,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount,mc.category,mc.min_deposit,mc.min_rate,mc.AVID_block,mc.AVID_Undertaking,mc.verify_pan,mc.verify_agreement,mc.remark,mc.depositeOne,mc.rateOne,mc.depositeTwo,mc.rateTwo,mc.depositeThree,mc.rateThree,wld.company_name,wld.category_Name,wld.flag from md_details md,md_login_details mld, md_amount ma,md_ccinfo_tab mc,white_label_details wld where md.md_id=mld.user_id and md.md_id=ma.md_id and md.md_id=mc.md_id and wld.client_id=md.client_id order by mc.md_id";
        		}else if(userType.equalsIgnoreCase("egen")){
        			sqlQuery="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,ad.Agency_Name,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as user_name,ld.Status,ld.Block_status,am.Total_Cash,am.Used_Cash,am.Cutoff_Amount,am.Total_Bal_Amount,am.Available_Bal_Amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount am,OnlineRechAPI_Live_db.dbo.api_ccinfo_tab ac where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id=am.Corporate_Agent_Id and ad.Corporate_Agent_Id=ac.user_id order by ac.user_id";
        		}
        	}
        	else{
        		if(userType.equalsIgnoreCase("adminUser")){
            		sqlQuery="select aud.Portal_id,aud.user_id,wld.company_name,(aud.First_name+' '+aud.Last_name) as user_name,ald.Login_status,ald.Block_status,amd.Total_Cash,amd.Used_Cash,amd.Cutoff_Amount,amd.Total_balance_Amount,amd.Available_balance_amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree,wld.company_name,wld.category_Name,wld.flag from admin_user_details aud,admin_login_details ald, Admin_user_amount_details amd,admin_ccinfo_tab ac,white_label_details wld where aud.user_id=ald.user_id and aud.user_id=amd.portal_id and aud.user_id=ac.user_id and aud.portal_id=wld.client_id and ac.user_id='"+userID+"' order by ac.user_id";
            	}else if(userType.equalsIgnoreCase("mds")){
            		sqlQuery="select md.client_id,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.Company_name,md.MD_name,mld.Login_status,mld.Block_status,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount,mc.category,mc.min_deposit,mc.min_rate,mc.AVID_block,mc.AVID_Undertaking,mc.verify_pan,mc.verify_agreement,mc.remark,mc.depositeOne,mc.rateOne,mc.depositeTwo,mc.rateTwo,mc.depositeThree,mc.rateThree,wld.company_name,wld.category_Name,wld.flag from md_details md,md_login_details mld, md_amount ma,md_ccinfo_tab mc,white_label_details wld where md.md_id=mld.user_id and md.md_id=ma.md_id and md.md_id=mc.md_id and wld.client_id=md.client_id and md.md_initial+convert(varchar(10),md.md_id)='"+userID+"' order by mc.md_id ";
            	}else if(userType.equalsIgnoreCase("egen")){
            		sqlQuery="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,ad.Agency_Name,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as user_name,ld.Status,ld.Block_status,am.Total_Cash,am.Used_Cash,am.Cutoff_Amount,am.Total_Bal_Amount,am.Available_Bal_Amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount am,OnlineRechAPI_Live_db.dbo.api_ccinfo_tab ac where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id=am.Corporate_Agent_Id and ad.Corporate_Agent_Id=ac.user_id and ad.Corporate_Agent_Id='"+userID+"' order by ac.user_id";
            	}
        	}
//        	if(userType.equalsIgnoreCase("adminUser")){
//        		sqlQuery="select aud.Portal_id,aud.user_id,wld.company_name,(aud.Initial+' '+aud.First_name+' '+aud.Middle_name+' '+aud.Last_name) as user_name,ald.Login_status,ald.Block_status,amd.Total_Cash,amd.Used_Cash,amd.Cutoff_Amount,amd.Total_balance_Amount,amd.Available_balance_amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree,wld.company_name,wld.category_Name,wld.flag from admin_user_details aud,admin_login_details ald, Admin_user_amount_details amd,admin_ccinfo_tab ac,white_label_details wld where aud.user_id=ald.user_id and aud.user_id=amd.portal_id and aud.user_id=ac.user_id and aud.portal_id=wld.client_id order by ac.user_id";
//        	}else if(userType.equalsIgnoreCase("mds")){
//        		sqlQuery="select md.client_id,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.Company_name,md.MD_name,mld.Login_status,mld.Block_status,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount,mc.category,mc.min_deposit,mc.min_rate,mc.AVID_block,mc.AVID_Undertaking,mc.verify_pan,mc.verify_agreement,mc.remark,mc.depositeOne,mc.rateOne,mc.depositeTwo,mc.rateTwo,mc.depositeThree,mc.rateThree,wld.company_name,wld.category_Name,wld.flag from md_details md,md_login_details mld, md_amount ma,md_ccinfo_tab mc,white_label_details wld where md.md_id=mld.user_id and md.md_id=ma.md_id and md.md_id=mc.md_id and wld.client_id=md.client_id order by mc.md_id";
//        	}else if(userType.equalsIgnoreCase("egen")){
//        		sqlQuery="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,ad.Agency_Name,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as user_name,ld.Status,ld.Block_status,am.Total_Cash,am.Used_Cash,am.Cutoff_Amount,am.Total_Bal_Amount,am.Available_Bal_Amount,ac.category,ac.min_deposit,ac.min_rate,ac.AVID_block,ac.AVID_Undertaking,ac.verify_pan,ac.verify_agreement,ac.remark,ac.depositeOne,ac.rateOne,ac.depositeTwo,ac.rateTwo,ac.depositeThree,ac.rateThree from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount am,OnlineRechAPI_Live_db.dbo.api_ccinfo_tab ac where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id=am.Corporate_Agent_Id and ad.Corporate_Agent_Id=ac.user_id order by ac.user_id";
//        	}
        	qry=session.createSQLQuery(sqlQuery);
        	List list=qry.list();
        	Iterator itr=list.iterator();
        	Object[] row;
        	HashMap map;
        	while(itr.hasNext()){
        		map=new HashMap();
        		if(userType.equalsIgnoreCase("egen")){
        			row=(Object[])itr.next();
        			map.put("userID", row[0].toString());
        			map.put("companyName", row[1].toString());
        			map.put("nameOfUser", row[2].toString());
        			map.put("loginStatus", row[3].toString());
        			map.put("blockStatus", (String)row[4]);
        			map.put("totalCash", row[5].toString());
        			map.put("usedCash", row[6].toString());
        			map.put("cutOff", row[7].toString());
        			map.put("totalBal", row[8].toString());
        			map.put("availableBal", row[9].toString());
        			map.put("userCategory", row[10].toString());
        			map.put("userMinDeposite", row[11].toString());
        			map.put("userMinRate", row[12].toString());
        			map.put("userAVIDBlock", row[13].toString());
        			map.put("userAVIDUndertaking", row[14].toString());
        			map.put("userVerifyPan", row[15].toString());
        			map.put("userVerifyAgreement", row[16].toString());
        			map.put("userRemark", row[17].toString());
        			map.put("userDepositeOne", row[18].toString());
        			map.put("userRateOne", row[19].toString());
        			map.put("userDepositetwo", row[20].toString());
        			map.put("userRateTwo", row[21].toString());
        			map.put("userDepositeThree", row[22].toString());
        			map.put("userRateThree", row[23].toString());
        			
        			listData.add(map);
        		}
        		if(userType.equalsIgnoreCase("mds") ||userType.equalsIgnoreCase("adminUser")){
        			row=(Object[])itr.next();
        			map.put("clientID", row[0].toString());
        			map.put("userID", row[1].toString());
        			map.put("companyName", row[2].toString());
        			map.put("nameOfUser", (String)row[3]);
        			map.put("loginStatus", row[4].toString());
        			map.put("blockStatus", (String)row[5]);
        			map.put("totalCash", row[6].toString());
        			map.put("usedCash", row[7].toString());
        			map.put("cutOff", row[8].toString());
        			map.put("totalBal", row[9].toString());
        			map.put("availableBal", row[10].toString());
        			map.put("userCategory", row[11].toString());
        			map.put("userMinDeposite", row[12].toString());
        			map.put("userMinRate", row[13].toString());
        			map.put("userAVIDBlock", row[14].toString());
        			map.put("userAVIDUndertaking", row[15].toString());
        			map.put("userVerifyPan", row[16].toString());
        			map.put("userVerifyAgreement", row[17].toString());
        			map.put("userRemark", row[18].toString());
        			map.put("userDepositeOne", row[19].toString());
        			map.put("userRateOne", row[20].toString());
        			map.put("userDepositetwo", row[21].toString());
        			map.put("userRateTwo", row[22].toString());
        			map.put("userDepositeThree", row[23].toString());
        			map.put("userRateThree", row[24].toString());
        			map.put("clientCompanyName", row[25].toString());
        			map.put("clientCategory", row[26].toString());
        			map.put("clientFlag", row[27].toString());
  
        			listData.add(map);
        		}
        	}
        	
        }catch (Exception e) {
			System.out.println("Exception in CcInfoDao method is getEditSlabData");
			System.out.println(e.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in CcInfoDao,getEditSlabData");
				System.out.println(e.toString());
			}
		}
		return listData;
	}

	final String updateCCInfo(String userMinDeposite,
			String userMinDepositeRate, String aVIDBlock,
			String userAVIDUndertaking, String verifyPan,
			String verifyAgreement, String userID, String remark, String user) {
		String status="fail";
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        try{
        	if(user.equalsIgnoreCase("mds")){
        		
        		sqlQuery="update md_ccinfo_tab set min_deposit='"+userMinDeposite+"',min_rate='"+userMinDepositeRate+"',AVID_block='"+aVIDBlock+"',AVID_Undertaking='"+userAVIDUndertaking+"',verify_pan='"+verifyPan+"',verify_agreement='"+verifyAgreement+"',remark='"+remark+"' where md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+userID+"')";
        	}
        	else if(user.equalsIgnoreCase("adminUser")){
        	
        		sqlQuery="update admin_ccinfo_tab set min_deposit='"+userMinDeposite+"',min_rate='"+userMinDepositeRate+"',AVID_block='"+aVIDBlock+"',AVID_Undertaking='"+userAVIDUndertaking+"',verify_pan='"+verifyPan+"',verify_agreement='"+verifyAgreement+"',remark='"+remark+"' where user_id='"+userID+"' ";
            }
        	else if(user.equalsIgnoreCase("egen")){
        		sqlQuery="update OnlineRechAPI_Live_db.dbo.api_ccinfo_tab set min_deposit='"+userMinDeposite+"',min_rate='"+userMinDepositeRate+"',AVID_block='"+aVIDBlock+"',AVID_Undertaking='"+userAVIDUndertaking+"',verify_pan='"+verifyPan+"',verify_agreement='"+verifyAgreement+"',remark='"+remark+"' where  user_id in (select Corporate_Agent_Id from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details where Corporate_Agent_initial+convert(varchar(10),Corporate_Agent_Id)='"+userID+"' )";
        	}
        	//System.out.println(sqlQuery);
        	qry=session.createSQLQuery(sqlQuery);
        	qry.executeUpdate();
        	status="Sucess";
        }catch (Exception e) {
        	status="fail";
			System.out.println("Exception in CcInfoDao method is updateCCInfo");
			System.out.println(e.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in CcInfoDao,updateCCInfo");
				System.out.println(e.toString());
			}
			
		}
		return status;
	}

	final String updateSlabInfo(String userCategory, String deposite1,
			String rate1, String deposite2, String rate2, String deposite3,String rate3,
			String userID, String user) {
		
		String status="fail";
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        try{
        	if(user.equalsIgnoreCase("mds")){
        		
        		sqlQuery="update md_ccinfo_tab set category='"+userCategory+"',depositeOne='"+deposite1+"',rateOne='"+rate1+"',depositeTwo='"+deposite2+"',rateTwo='"+rate2+"',depositeThree='"+deposite3+"',rateThree='"+rate3+"' where md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+userID+"')";
        	}
        	else if(user.equalsIgnoreCase("adminUser")){
        	
        		sqlQuery="update admin_ccinfo_tab set category='"+userCategory+"',depositeOne='"+deposite1+"',rateOne='"+rate1+"',depositeTwo='"+deposite2+"',rateTwo='"+rate2+"',depositeThree='"+deposite3+"',rateThree='"+rate3+"' where user_id='"+userID+"' ";
            }
        	else if(user.equalsIgnoreCase("egen")){
        		sqlQuery="update OnlineRechAPI_Live_db.dbo.api_ccinfo_tab set category='"+userCategory+"',depositeOne='"+deposite1+"',rateOne='"+rate1+"',depositeTwo='"+deposite2+"',rateTwo='"+rate2+"',depositeThree='"+deposite3+"',rateThree='"+rate3+"' where  user_id in (select Corporate_Agent_Id from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details where Corporate_Agent_initial+convert(varchar(10),Corporate_Agent_Id)='"+userID+"') ";
        	}
        	//System.out.println(sqlQuery);
        	qry=session.createSQLQuery(sqlQuery);
        	qry.executeUpdate();
        	status="Sucess";
        }catch (Exception e) {
        	status="fail";
			System.out.println("Exception in CcInfoDao method is updateSlabInfo");
			System.out.println(e.toString());
		}finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in CcInfoDao,updateSlabInfo");
				System.out.println(e.toString());
			}
		}
		return status;
	}

}
