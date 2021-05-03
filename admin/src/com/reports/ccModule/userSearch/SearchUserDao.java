package com.reports.ccModule.userSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

/**
 * Created Date 	:22/02/2013
 * Created By 		:Manoj
 * Created Matter 	: To search user by mobile number and email id
 *
 */

class SearchUserDao 
{
	// Live DB= OnlineRechAPI_Live_db
	// Test DB= OnlineRechAPI_db_2013Feb2

	final ArrayList<HashMap<String,String>> getAdminUserData(String userType, String mobileNo,String emailID, 
			String loginType, String userId, String searchUserId,String name) 
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;

		try
		{
			if(mobileNo !=null && mobileNo.length()>9)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and Mobile_no='"+mobileNo+"'";
				}else
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and Mobile_no='"+mobileNo+"' and ad.Portal_id in (select Portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}else if(emailID!=null && emailID.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.Official_email_id='"+emailID+"'";	
				}else
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.Official_email_id='"+emailID+"' and ad.Portal_id in (select Portal_id from admin_user_details where user_id='"+userId+"')";
				}

			}else if(name!=null && name.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.First_name like '%"+name+"%'";	
				}else
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.First_name like '%"+name+"%' and ad.Portal_id in (select Portal_id from admin_user_details where user_id='"+userId+"')";
				}

			}else{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.user_id='"+searchUserId+"'";	
				}else
				{
					sql="select ad.Portal_id,wld.Company_name,ad.user_id,(ad.First_name+' '+Last_name) as UserName,ld.Login_status,ld.Block_status,ld.User_type from admin_user_details ad,admin_login_details ld,white_label_details wld where ad.user_id=ld.user_id and ad.Portal_id=wld.client_id and ad.user_id='"+searchUserId+"' and ad.Portal_id in (select Portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;

			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("clientID", row[0].toString());
				mapdata.put("companyName",row[1].toString());
				mapdata.put("userID",row[2].toString() );
				mapdata.put("userName",(String)row[3] );
				mapdata.put("loginStatus",row[4].toString() );
				mapdata.put("blockStatus",(String)row[5]==null ? " ":row[5].toString() );
				mapdata.put("userType", row[6].toString());
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SearchUserDao method getAdminUserData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in SearchUserDao method getAdminUserData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}

	final ArrayList<HashMap<String,String>> getMdsUserData(String userType,String mobileNo,String emailID,String loginType,String userId,
			String searchUserId,String name) 
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try
		{
			if(mobileNo !=null && mobileNo.length()>9)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.Mobile_no='"+mobileNo+"' and md.client_id=wld.client_id and md.md_id=mld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.Mobile_no='"+mobileNo+"' and md.client_id=wld.client_id and md.md_id=mld.user_id and md.portal_id='"+userId+"'";	
				}

			}else if(emailID!=null && emailID.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.Email_id='"+emailID+"' and md.client_id=wld.client_id and md.md_id=mld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.Email_id='"+emailID+"' and md.client_id=wld.client_id and md.md_id=mld.user_id and md.portal_id='"+userId+"'";	
				}

			}else if(name!=null && name.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.MD_name like '%"+name+"%' and md.client_id=wld.client_id and md.md_id=mld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.MD_name like '%"+name+"%' and md.client_id=wld.client_id and md.md_id=mld.user_id and md.portal_id='"+userId+"'";	
				}

			}else 
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.md_initial+convert(varchar(10),md_id)='"+searchUserId+"' and md.client_id=wld.client_id and md.md_id=mld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),md.MD_id) as MDID,md.MD_name,md.Company_name,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,white_label_details wld where md.md_initial+convert(varchar(10),md_id)='"+searchUserId+"' and md.client_id=wld.client_id and md.md_id=mld.user_id and md.portal_id='"+userId+"'";	
				}

			}

			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;
			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("clientID", row[0].toString());
				mapdata.put("ClientcompanyName",row[1].toString());
				mapdata.put("userID",(String)row[2] );
				mapdata.put("userName",(String)row[3] );
				mapdata.put("userCompanyName",(String)row[4] );
				mapdata.put("loginStatus",row[5].toString() );
				mapdata.put("blockStatus",(String)row[6]==null ? " " : row[6].toString() );
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SearchUserDao method getMdsUserData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in SearchUserDao method getMdsUserData");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}

	final ArrayList<HashMap<String,String>> getDSUserData(String userType, String mobileNo,String emailID, String loginType, String userId, 
			String searchUserId,String name) 
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;

		try
		{
			if(mobileNo !=null && mobileNo.length()>9)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.mobile_no='"+mobileNo+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.mobile_no='"+mobileNo+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id and dd.portal_ID='"+userId+"'";	
				}

			}else if(emailID!=null && emailID.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.email_id='"+emailID+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id";	
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.email_id='"+emailID+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id and dd.portal_ID='"+userId+"'";
				}

			}else if(name!=null && name.length()>1)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.distributor_name like '%"+name+"%' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id";	
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.distributor_name like '%"+name+"%' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id and dd.portal_ID='"+userId+"'";
				}

			}else 
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.distributor_initial+convert(varchar(10),dd.distributor_id)='"+searchUserId+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id";
				}else
				{
					sql="select md.Client_Id,wld.Company_name,md.MD_initial+convert(varchar(10),dd.MD_id) as MDID,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSID,dd.distributor_name,dd.company_name,dld.login_status,dld.Block_status from distributor_details dd,distributor_login_details dld,md_details md,white_label_details wld where dd.distributor_initial+convert(varchar(10),dd.distributor_id)='"+searchUserId+"' and wld.client_id=md.client_id and md.md_id=dd.md_id and dd.distributor_id=dld.user_id and dd.portal_ID='"+userId+"'";
				}
			}
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;

			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("clientID", row[0].toString());
				mapdata.put("companyName",row[1].toString());
				mapdata.put("MDID",(String)row[2] );
				mapdata.put("userID",(String)row[3] );
				mapdata.put("UserName",row[4].toString() );
				mapdata.put("UserCompanyName",(String)row[5] );
				mapdata.put("loginStatus", row[6].toString());
				mapdata.put("blockStatus", (String)row[7]==null ? " " : row[7].toString());
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SearchUserDao method getDSUserData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in SearchUserDao method getDSUserData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}

	final ArrayList<HashMap<String,String>> getAgentUserData(String userType, String mobileNo,String emailID, String loginType,
			String userId, String searchUserId,String name) 
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;

		try
		{
			System.out.println("we are into getAgentUserData");
			if(mobileNo !=null && mobileNo.length()>9)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where mobile_no='"+mobileNo+"')))";
				}
				else
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where mobile_no='"+mobileNo+"')))";	
				}
			}else if(emailID!=null && emailID.length()>0)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where email_id='"+emailID+"')))";	
				}else
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where email_id='"+emailID+"')))";
				}

			}else if(name!=null && name.length()>0)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_name like '%"+name+"%')))";	
				}else
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_name like '%"+name+"%')))";
				}

			}else
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select flag from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_initial+convert(varchar(10),agent_id)='"+searchUserId+"')))";
				}else
				{
					sql="select flag from white_label_details where client_id in (select portal_id from admin_user_details where user_id='"+userId+"') and client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id in (select distributor_id from agent_details where agent_initial+convert(varchar(10),agent_id)='"+searchUserId+"')))";
				}
			}
			//System.out.println(sql);
			//query=session.createSQLQuery(sql);
			//flag=query.uniqueResult().toString();
			//System.out.println(flag);

			//if(flag.equalsIgnoreCase("0") || flag.equalsIgnoreCase("2"))
			//{
			if(mobileNo !=null && mobileNo.length()>9)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin"))
				{
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
				}else
				{
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";	
				}

			}else if(emailID!=null && emailID.length()>0)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.email_id='"+emailID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
				}else
				{
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.email_id='"+emailID+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";
				}
			}else if(name!=null && name.length()>0)
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.agent_name like '%"+name+"%' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
				}else
				{
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.agent_name like '%"+name+"%' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";
				}
			}else 
			{
				if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.agent_initial+convert(varchar(10),agent_id)='"+searchUserId+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
				}else
				{
					sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,ld.login_status,ld.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,login_details ld,distributor_details dd,md_details md,white_label_details wld where ad.email_id='"+emailID+"' and ad.mobile_no='"+mobileNo+"' and ad.agent_id=ld.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";	
				}

			}
			/*}else 
			{
				if(mobileNo !=null && mobileNo.length()>9)
				{
					if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.mobile_no='"+mobileNo+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
					}else
					{
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.mobile_no='"+mobileNo+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";	
					}

				}else if(emailID!=null && emailID.length()>0)
				{
					if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.email_id='"+emailID+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
					}else
					{
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.email_id='"+emailID+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";	
					}

				}else 
				{
					if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityuser")||loginType.equalsIgnoreCase("superadmin")){
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.agent_initial+convert(varchar(10),agent_id)='"+searchUserId+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id";
					}else
					{
						sql="select ad.agent_initial+convert(varchar(10),ad.agent_id) as AGID,ad.agency_name,ad.agent_name,rd.login_status,rd.Block_status,dd.distributor_initial+convert(varchar(10),ad.distributor_id) as DSID,md.MD_initial+convert(varchar(10),dd.md_id) as MDID,wld.Client_Id,wld.Company_name from agent_details ad,recharge_e_point_login_info rd,distributor_details dd,md_details md,white_label_details wld where ad.agent_initial+convert(varchar(10),agent_id)='"+searchUserId+"' and ad.agent_id=rd.user_id and ad.distributor_id=dd.distributor_id and md.md_id=dd.md_id and md.client_id=wld.client_id and ad.portal_id='"+userId+"'";	
					}

				}
			}*/
			System.out.println(sql);
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;

			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("AGID", row[0].toString());
				mapdata.put("UsercompanyName",row[1].toString());
				mapdata.put("UserName",(String)row[2] );
				mapdata.put("loginStatus",row[3].toString() );
				mapdata.put("blockStatus",(String)row[4]==null ? " " : row[4].toString());
				mapdata.put("DSID",(String)row[5] );
				mapdata.put("MDID", row[6].toString());
				mapdata.put("ClientID", row[7].toString());
				mapdata.put("compnayName", row[8].toString());
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SearchUserDao method getAdminUserData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in SearchUserDao method getAdminUserData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}

	final ArrayList<HashMap<String,String>> getEgenUserData(String userType, String mobileNo,String emailID, String searchUserId)
	{
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;

		try
		{

			if(mobileNo !=null && mobileNo.length()>9)
			{
				sql="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as userName,ad.Agency_Name,ld.Status,ld.Block_status from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Authorized_Mobile_No='"+mobileNo+"'";
			}else if(emailID!=null && emailID.length()>0)
			{
				sql="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as userName,ad.Agency_Name,ld.Status,ld.Block_status from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.EmailID='"+emailID+"'";
			}else 
			{
				sql="select ad.Corporate_Agent_initial+convert(varchar(10),ad.Corporate_Agent_Id) as UserID,(ad.Agent_First_Name+' '+ad.Agent_Middle_Name+' '+ad.Agent_Last_Name) as userName,ad.Agency_Name,ld.Status,ld.Block_status from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Details ad,OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Auth_details ld where ad.Corporate_Agent_Id=ld.Corporate_Agent_Id and ad.Corporate_Agent_Id='"+searchUserId+"'";
			}

			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			HashMap<String,String> mapdata;
			Object [] row;

			while(itr.hasNext())
			{
				mapdata=new HashMap<String,String>();
				row=(Object[])itr.next();
				mapdata.put("userID", row[0].toString());
				mapdata.put("UserName",row[1].toString());
				mapdata.put("companyName",(String)row[2] );
				mapdata.put("loginStatus",row[3].toString());
				mapdata.put("blockStatus",(String)row[4]==null ? " " : row[4].toString() );
				listData.add(mapdata);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SearchUserDao method getEgenUserData");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(session!=null)session.close();
			}catch(Exception ex)
			{
				System.out.println("Exception in SearchUserDao method getEgenUserData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}
}
