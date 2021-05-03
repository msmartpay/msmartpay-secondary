package com.controlPanel.profileMgt.viewHierarchy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

 class ViewHierarchyDao {
	
	final ArrayList<HashMap<String,String>> getMdsHierarchy(int id, String status, String type) {
		 Session session=null;
		 Query query=null;
		 String sql="";
		 Object[] obj=null;
		 ArrayList<HashMap<String,String>> userDetails=new ArrayList<HashMap<String,String>>();
		 try{
			 session = HibernateSession.getSessionFactory().openSession(); 
			 if(type.equalsIgnoreCase("ds")){
				 sql="select top(100)  md.client_id,md.portal_id,wld.Company_name,md.MD_id,md.MD_initial,md.MD_name,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,mld.Login_status," +
				 "mld.Block_status " +
				 " from distributor_details dd,white_label_details wld,MD_Amount ma," +
				 "MD_login_details mld,MD_Details md where mld.User_id=md.MD_id and  md.MD_id=ma.MD_id and dd.md_id=md.MD_id and md.Client_Id=wld.Client_Id " +
				 " and dd.distributor_id="+id;
				 if(!status.equalsIgnoreCase("all"))
				 {
					 sql=sql+" and mld.login_status='"+status+"'order by md.Date_of_joining desc";
				 }
				 else
				 {
					 sql=sql+" order by md.Date_of_joining desc";
				 }
			 }
			 if(type.equalsIgnoreCase("adminUser")){
				 sql="select client_id from white_label_details where client_id in (select portal_id from admin_user_details where user_id='"+id+"')";
				 query=session.createSQLQuery(sql);
				 String portal_id=query.uniqueResult().toString();
				 //System.out.println(portal_id);
				 sql="select top(100) ad.portal_id,ad.user_id,wld.Company_name,md.MD_initial,md.MD_id,md.MD_name,ma.Total_Cash,ma.Used_Cash,ma.Cutoff_Amount,mld.Login_status,mld.Block_status from md_details md,md_login_details mld,md_amount ma,white_label_details wld,admin_user_details ad where mld.user_id=md.md_id and md.md_id=mld.user_id and md.md_id=ma.md_id and md.client_id=wld.client_id and md.client_id in (select portal_id from admin_user_details where ad.USER_ID='"+id+"') and ad.portal_id=wld.client_id ";
				 if(!status.equalsIgnoreCase("all"))
				 {
					 sql=sql+" and mld.login_status='"+status+"'order by md.Date_of_joining desc";
				 }
				 else
				 {
					 sql=sql+" order by md.Date_of_joining desc";
				 }
			 }
			 query=session.createSQLQuery(sql);
			 List list=query.list();
			 Iterator itr=list.iterator();
			 while(itr.hasNext())
			 {
				 HashMap<String,String>  map=new HashMap<String,String>();
				 obj=(Object[])itr.next();
				 
					 map.put("portalId", obj[0].toString());
					 map.put("mdPortalID", obj[1].toString());
					 map.put("clientCompanyName", (String)obj[2]);
					 String md_id=obj[3].toString()+obj[4].toString();
					 map.put("mdId", md_id);
					 map.put("mdsName", (String)obj[5]);
					 double totcash=Double.parseDouble(obj[6].toString());
					 double usedCash=Double.parseDouble(obj[7].toString());
					 double cutoffAmount=Double.parseDouble(obj[8].toString());
					 double mdBalance=totcash-usedCash+cutoffAmount;
					 DecimalFormat f = new DecimalFormat("0.00");
					 map.put("mdsBalance", f.format(mdBalance).toString());
					 map.put("loginStatus", (String)obj[9]);
					 map.put("mdsBlockStatus",(String) obj[10]);
					 userDetails.add(map);	
			 }
		 }catch(Exception E){
			 System.out.println("Exception in ViewHierarchyDao,getMdsHierarchy");	
			 System.out.println(E.toString());
			 return userDetails;
		 }finally{
			 try{
				 session.flush();
				 session.close();
			 }catch(Exception e)	{
				 System.out.println("Exception in ViewHierarchyDao,getMdsHierarchy");
				 System.out.println(e.toString());			
			 }			
		 }
		 return userDetails;	
	 }
	
	public ArrayList<HashMap<String,String>> getdsHierarchy(int id, String status, String type) {
		Session session=null;
		Query query=null;
		String sql="";
		Object[] obj=null;
		ArrayList<HashMap<String,String>> userDetails=new ArrayList<HashMap<String,String>>();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			if(type.equalsIgnoreCase("mds")){
				sql="select top(100)  md.client_id,md.portal_id,md.md_initial+convert(varchar(10),md.md_id) as md_id,dd.distributor_initial,dd.distributor_id,dd.distributor_name," +
				"da.TotCash,da.usedcash,da.cutoff_amount,dld.login_status,dld.Block_status" +
				" from distributor_details dd,distributor_amount da,distributor_login_details dld," +
				"MD_Details md where dld.user_id=dd.distributor_id and " +
				"dd.distributor_id=da.distributor_id and dd.md_id=md.MD_id " +
				"and md.md_id="+id;
				if(!status.equalsIgnoreCase("all"))
				{
					sql=sql+" and dld.login_status='"+status+" 'order by dd.date_of_joining desc ";
				}	
				else
				{
					sql=sql+" order by dd.date_of_joining desc";
				}	
			}
			else if(type.equalsIgnoreCase("agent")){
				sql="select top(100)  dd.client_id,dd.Portal_id,dd.MDS_ID,dd.distributor_initial,dd.distributor_id,dd.distributor_name," +
				"da.TotCash,da.usedcash,da.cutoff_amount,dld.login_status,dld.Block_status" +
				" from distributor_details dd,distributor_amount da,distributor_login_details dld,agent_details ad " +
				"where dld.user_id=dd.distributor_id and " +
				"dd.distributor_id=da.distributor_id  and ad.distributor_id=dd.distributor_id " +
				"and ad.agent_id="+id;
				if(!status.equalsIgnoreCase("all"))
				{
					sql=sql+" and dld.login_status='"+status+" 'order by dd.date_of_joining desc";
				}
				else
				{
					sql=sql+" order by dd.date_of_joining desc";
				}	
			}
			
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap<String,String>  map=new HashMap<String,String>();
				obj=(Object[])itr.next();
				
				map.put("portalId", obj[0].toString());
				map.put("mdPortalID", obj[1].toString());
				map.put("mdId", obj[2].toString());
				String ds_id=obj[3].toString()+obj[4].toString();
				map.put("distributorId", ds_id);
				map.put("distributorName",(String) obj[5]);
				double totcash=Double.parseDouble(obj[6].toString());
				double usedCash=Double.parseDouble(obj[7].toString());
				double cutoffAmount=Double.parseDouble(obj[8].toString());
				double dsBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				map.put("distributorBalance", f.format(dsBalance).toString());
				map.put("loginStatus",(String) obj[9]);
				map.put("distributorBlockStatus",(String) obj[10]);
				userDetails.add(map);	
			}	
			
		}catch(Exception E){
			 System.out.println("Exception in ViewHierarchyDao,getdsHierarchy");
			 System.out.println(E.toString());
		
			 return userDetails;
		 }finally{
			 try{
				 session.flush();
				 session.close();
			 }catch(Exception e)	{
				 System.out.println("Exception in ViewHierarchyDao,getdsHierarchy");
				 System.out.println(e.toString());			
			 }			
		 }
		 return userDetails;	
	}
	public ArrayList<HashMap<String,String>> getAgentHierarchy(int id, String status, String type) {
		Session session=null;
		Query query=null;
		String sql="";
		Object[] obj=null;		
		ArrayList<HashMap<String,String>> userDetails=new ArrayList<HashMap<String,String>>();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			Transaction ts=session.beginTransaction();	
			String query1="select flag from white_label_details where Client_Id in (select Client_Id from MD_Details where MD_id in (select MD_id from distributor_details where distributor_id='"+id+"'))";
			query=session.createSQLQuery(query1);
			String result=query.uniqueResult().toString();
			System.out.println(result);
			if(result.equals("0") || result.equals("2"))
			 {
				sql="select top(100) ad.client_id,ad.portal_id,ad.mds_id,ad.ds_id,ad.agent_initial,ad.agent_id,ad.agent_name,am.TotCash,am.usedcash,am.cutoff_amount,ald.login_status,ald.block_status from agent_details ad,login_details ald,agent_amount am,distributor_details dd where ad.agent_id=ald.user_id and ad.agent_id=am.agent_id and am.agent_id=ald.user_id and ad.distributor_id=dd.distributor_id and ad.distributor_id='"+id+"'";			   
				if(!status.equalsIgnoreCase("all"))
				{
					sql=sql+" and ald.login_status='"+status+"' order by ad.date_of_joining desc";
				}
				else
				{
					sql=sql+" order by ad.date_of_joining desc";
				}
			 }
			else{
				sql="select top(100) ad.client_id,ad.portal_id,ad.mds_id,ad.ds_id,ad.agent_initial,ad.agent_id,ad.agent_name,am.TotCash,am.usedcash,am.cutoff_amount,ald.login_status,ald.block_status from agent_details ad,recharge_e_point_login_info ald,agent_amount am,distributor_details dd  where ad.agent_id=ald.user_id and ad.agent_id=am.agent_id  and  am.agent_id=ald.user_id and ad.distributor_id=dd.distributor_id and ad.distributor_id='"+id+"'";
				if(!status.equalsIgnoreCase("all"))
				{
					sql=sql+" and ald.login_status='"+status+"' order by ad.date_of_joining desc";
				}
				else
				{
					sql=sql+" order by ad.date_of_joining desc";
				}
			 }
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				HashMap<String,String>  map=new HashMap<String,String>();
				obj=(Object[])itr.next();
				System.out.println("we are into this");
				map.put("portalId", obj[0].toString());
				map.put("mdPortalID", obj[1].toString());
				map.put("mdId", (String)obj[2]);
				map.put("distributorId", obj[3].toString());
				String agent_id=obj[4].toString()+obj[5].toString();
				map.put("agentId", agent_id.toString());
				map.put("agentName", (String)obj[6]);
				double totcash=Double.parseDouble(obj[7].toString());
				double usedCash=Double.parseDouble(obj[8].toString());
				double cutoffAmount=Double.parseDouble(obj[9].toString());
				double agBalance=totcash-usedCash+cutoffAmount;
				DecimalFormat f = new DecimalFormat("0.00");
				map.put("agentBalance", f.format(agBalance).toString());
				map.put("loginStatus", (String)obj[10]);
				map.put("agentBlockStatus",(String) obj[11]);
				userDetails.add(map);	
			}	
			
		}catch(Exception E){		
			 System.out.println("Exception in ViewHierarchyDao,getAgentHierarchy");
			 System.out.println(E.toString());
		
			 return userDetails;
		 }finally{
			 try{
				 session.flush();
				 session.close();
			 }catch(Exception e)	{
				 System.out.println("Exception in ViewHierarchyDao,getAgentHierarchy");
				 System.out.println(e.toString());
					
			 }			
		 }
		 //System.out.println(userDetails);
		 return userDetails;
	}

	final ArrayList<HashMap<String, String>> getPortalHierarchy(int id,String status, String type) {
		Session session=null;
		Query query=null;
		Object[] obj=null;		
		ArrayList<HashMap<String,String>> userDetails=new ArrayList<HashMap<String,String>>();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="";
			session = HibernateSession.getSessionFactory().openSession(); 
			
			 sql="select ad.portal_id,ad.user_id,(ad.first_name+ ' '+ ad.last_name) as name,ald.login_status,ald.block_status,(am.Total_cash-am.Used_cash+am.Cutoff_amount) as balance from admin_user_details ad,admin_login_details ald,admin_user_amount_details am,md_details md where ald.user_id=ad.user_id and am.portal_id=ad.user_id and md.portal_id=ad.user_id and md.md_id='"+id+"' and ad.user_id in (select portal_id from md_details where md_id='"+id+"')";
			 //System.out.println(sql);
			 query=session.createSQLQuery(sql);
			 
			 List list=query.list();
			 Iterator itr=list.iterator();
			 while(itr.hasNext())
			 {
				 HashMap<String,String>  map=new HashMap<String,String>();
				 obj=(Object[])itr.next();
					
				 map.put("portalId", obj[0].toString());
				 map.put("userID", obj[1].toString());
				 map.put("name", (String)obj[2]);
				 map.put("loginStatus",(String) obj[3]);
				 map.put("BlockStatus", (String)obj[4]);
				 DecimalFormat f = new DecimalFormat("0.00");
				 map.put("Balance", f.format(obj[5]).toString());
				 userDetails.add(map);	
			 }		
		}catch (Exception e) {
			 System.out.println("Exception in ViewHierarchyDao,getPortalHierarchy");
			 System.out.println(e.toString());
				
			 return userDetails;
		 }finally{
			 try{
				 session.flush();
				 session.close();
			 }catch(Exception e)	{
				 System.out.println("Exception in ViewHierarchyDao,getPortalHierarchy");
				 System.out.println(e.toString());		
			 }			
		 }
		 return userDetails;
	}
}
