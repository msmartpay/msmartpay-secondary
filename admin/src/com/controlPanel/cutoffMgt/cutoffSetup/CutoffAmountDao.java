package com.controlPanel.cutoffMgt.cutoffSetup;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;
import com.formBean.EGEN.ApiClientAmountFormBean;
import com.formBean.mds.MdsAmountFormBean;


public class CutoffAmountDao 
{

	public String doupdateAllAGCutoffAmount(double cutoffAmount,String userId,String userType,String ipAddress,String CuttOfBy,String EnterUserId,int clientId) 
	{
		  String update="Notdone";
		  Session session=null;
		  Transaction tran=null;
		  int Userid=Integer.parseInt(userId);
		  String sql="";
		  String remark="";
		  try
		  {
			  session = HibernateSession.getSessionFactory().openSession();
			  tran=session.beginTransaction();
			  if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
			  {
				  if(CuttOfBy.equalsIgnoreCase("ByAll"))
				  {
					  sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'";
					  remark="on all agent";
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByClientId"))
				  {
					 sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					 " agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from " +
					 "distributor_details where md_id in (select md_id from md_details where client_id='"+EnterUserId+"')))";
					 remark="on all agent under Client id='"+EnterUserId+"'";  
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByMdId"))
				  {
					 sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					 " agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from " +
					 "distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"')))";
					 remark="on all agent under MD id="+EnterUserId+"";
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByDsId"))
				  {
					  sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					 " agent_id in (select agent_id from agent_details where distributor_id in " +
					 "(select distributor_id from distributor_details where distributor_initial+convert(varchar(14),distributor_id)='"+EnterUserId+"'))";
					  remark="on all agent under DS id='"+EnterUserId+"'";
				  }
				      
			  } 
			  else
			  {
				  if(CuttOfBy.equalsIgnoreCase("ByAll"))
				  {
					 sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					 " agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from " +
					 "distributor_details where md_id in (select md_id from md_details where client_id="+clientId+")))";
					 remark="on all agent";
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByClientId"))
				  {
					  sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
				    	   " agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from " +
				    	   "distributor_details where md_id in (select md_id from md_details where " +
				    	   "client_id="+clientId+" and client_id="+EnterUserId+")))";
					  remark="on all agent under Client id="+EnterUserId+"";  
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByMdId"))
				  {
					  sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					  " agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from " +
					  "distributor_details where md_id in (select md_id from md_details " +
					  "where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"' and client_id="+clientId+")))";
					  remark="on all agent under MD id="+EnterUserId+"";
				  }
				  if(CuttOfBy.equalsIgnoreCase("ByDsId"))
				  {
					  sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					  " agent_id in (select agent_id from agent_details where distributor_id in " +
					  "(select distributor_id from distributor_details where" +
					  " distributor_initial+convert(varchar(14),distributor_id)='"+EnterUserId+"' and md_id in (" +
					  "select md_id from md_details where client_id="+clientId+") ))";
					  remark="on all agent under DS id='"+EnterUserId+"'";
				  }	
			  }
			  SQLQuery query = session.createSQLQuery(sql);
			  int flag=query.executeUpdate();
			  //System.out.println("flag---"+flag);
			  if(flag>0)
			  {
				  CutOffAmountForm CutOffForm=new CutOffAmountForm();
				  CutOffForm.setUserId(Userid);
				  CutOffForm.setCutoffAmount(cutoffAmount);
				  CutOffForm.setUserType("AG");
				  CutOffForm.setIpAddress(ipAddress);
				  CutOffForm.setUpdateduser(userType);
				  CutOffForm.setRemark(remark);		        	   
				  session.save(CutOffForm);
				  update="done";   
			  }
			  tran.commit();
		  } 
		  catch (Exception e)
		  {
			 update="NotUpdate";
			 System.out.println("Exception in CutoffAmountDao,doupdateAllAGCutoffAmount");
			 System.out.println(e.toString());
			 tran.rollback();
		 }
		 finally 
		 {
			 try 
			 {
				 session.flush();
				 session.close();
			 } 
			 catch (Exception e) 
			 {
				 update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doupdateAllAGCutoffAmount");
				 System.out.println(e.toString());
			 }
		 }
		 return update;
	}

	public HashMap<String, Object> getdata(String enterUserId,String cutOffFor) 
	{
		HashMap<String,Object>Data=new HashMap<String,Object>();
		 
		Session session=null;
		double tot=0.0;
		double use=0.0;
		double cut=0.0;
		String cutLimit="";
		String AvilBalance="";
		String TotalBalance="";
		String stringTot="";
		String useString="";
		String cutString="";
		String cutLimitString="";
		Query qry=null;
		DecimalFormat dff = new DecimalFormat("00.00");
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			if("AG".equalsIgnoreCase(cutOffFor)) 
			{
				qry=session.createQuery("select ADF.name,ADF.agentId,AAF.totalCash,AAF.usedCash,AAF.cutOffAmount,AAF.lastAmount from AgentDetailsFormBean ADF,AgentAmountFormBean AAF  where ADF.agentId=AAF.agentId and ADF.agentInitial+convert(varchar,ADF.agentId)=:id ").setParameter("id", enterUserId);
			}
			if("DS".equalsIgnoreCase(cutOffFor))
			{
				qry=session.createQuery("select DDF.name,DDF.distributorId,DAF.totalCash,DAF.usedCash,DAF.cutOffAmount from DistributorDetailsFormBean DDF,DistributorAmountFormBean DAF  where DDF.distributorId=DAF.distributorId and DDF.distributorInitial+convert(varchar,DDF.distributorId)=:id ").setParameter("id", enterUserId);
			}
			if("MD".equalsIgnoreCase(cutOffFor)) 
			{
				qry=session.createQuery("select MDF.name,MDF.mdId,MAF.totalCash,MAF.usedCash,MAF.cutOffAmount,MAF.totalBalanceAmount,MAF.availableBalanceAmount from MdsDetailsFormBean MDF,MdsAmountFormBean MAF  where MDF.mdId=MAF.mdId and MDF.mdInitial+convert(varchar,MDF.mdId)=:id ").setParameter("id", enterUserId);
			}
			Iterator<?>it=qry.iterate();
			{
				Object[] row=(Object[])it.next();
				tot=(Double)row[2];
				use=(Double)row[3];
				cut=(Double)row[4];
				
				if(!"MD".equalsIgnoreCase(cutOffFor))
				{
					AvilBalance=dff.format(tot-use);
					TotalBalance=dff.format(tot-use+cut);
				}
				else
				{ 
					TotalBalance=dff.format((Double)row[5]); 
					AvilBalance=dff.format((Double)row[6]);					
				}
				Data.put("UserName",row[0]);
				Data.put("OnlyUserId",row[1]);
				Data.put("stringTot",dff.format(tot));
				Data.put("useString",dff.format(use));
				Data.put("cutString",dff.format(cut));
				Data.put("AvilBalance",AvilBalance);
				Data.put("TotalBalance",TotalBalance);
			}
		} catch (Exception e)
		{
			System.out.println("Exception in CutoffAmountDao,getdata");
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			}catch (Exception e) 
			{
				System.out.println("Exception in CutoffAmountDao,getdata");
				System.out.println(e.toString());
			}
		}
		return Data;
	}

	public String doupdateAGIDCutoffAmount(double cutoffAmount,String userId,String userType,String ipAddress,int agenID,String RemarkAdmin,int clientId) 
	{
		    String update="Notdone";
		    String sql="";
		   
		    Session session=null;
		    Transaction tran=null;
		    int Userid=Integer.parseInt(userId);
		    try 
		    {
		    	session=HibernateSession.getSessionFactory().openSession();
		    	tran=session.beginTransaction();
		    	if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
		    	{
		    		sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'" +
		    		"where agent_id ="+agenID+""; 
		    		
		    	} 
		    	else
		    	{
		    		sql="update agent_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'" +
					"where agent_id ="+agenID+" and agent_id in (select agent_id from agent_details where distributor_id in " +
					"(select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where client_id='"+clientId+"')))";
				} 
			 SQLQuery query = session.createSQLQuery(sql);
			 int flag=query.executeUpdate();
			 //System.out.println("flag---"+flag);
			 if(flag>0)
			 {
				 CutOffAmountForm CutOffForm=new CutOffAmountForm();
				 CutOffForm.setUserId(Userid);
				 CutOffForm.setCutoffAmount(cutoffAmount);
				 CutOffForm.setUserType("AG");
				 CutOffForm.setIpAddress(ipAddress);
				 CutOffForm.setUpdateduser(userType);
				 CutOffForm.setRemark(RemarkAdmin);
				 session.save(CutOffForm);
				 update="done"; 
			 }
			 tran.commit();
		 }catch (Exception e){
			 update="NotUpdate";
			 System.out.println("Exception in CutoffAmountDao,doupdateAGIDCutoffAmount");
			 System.out.println(e.toString());
			 tran.rollback();
		 }
		 finally 
		 {
			 try 
			 {
				 session.flush();
				 session.close();
			 } 
			 catch (Exception e) {
				 update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doupdateAGIDCutoffAmount");
				 System.out.println(e.toString());
			 }
		 }
		 return update;
	}

	public String doupdateAllDSCutoffAmount(double cutoffAmount, String userId,String userType, String ipAddress, String checkBoxCutOff,
			double secondcutoffAmount,String EnterUserId,String CuttOfBy,int clientId) 
	{
		String update="Notdone";
		String remark="";
		  
		Session session=null;
		Transaction tran=null;
		int Userid=Integer.parseInt(userId);
		String hql="";
		String sql="";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			//System.out.println("beofore updation-------------<>>>>>>>>>>>>>>");
			if("on".equals(checkBoxCutOff))
			{
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{
					hql = "update DistributorDetailsFormBean DDF set DDF.authorizedToCutoff =:AuthCut,DDF.cutOffLimit=:cutoff,DDF.updateUser=:User"; 
				}
				else
				{
					hql = "update DistributorDetailsFormBean DDF set DDF.authorizedToCutoff =:AuthCut,DDF.cutOffLimit=:cutoff,DDF.updateUser=:User where  DDF.mdId in(select MDF.mdId from MdsDetailsFormBean MDF where MDF.clientId=:clientID)"; 

				}
				Query query = session.createQuery(hql);
				query.setString("AuthCut","Y");
				query.setDouble("cutoff",secondcutoffAmount);
				query.setString("User",userType);
				query.setInteger("clientID",Userid);
				int rowCount = query.executeUpdate();
				//System.out.println("Rows affected: " + rowCount);
					 
			}
			if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")){
				if(CuttOfBy.equalsIgnoreCase("ByAll"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'";
					remark="on all Distributor";
				}
				if(CuttOfBy.equalsIgnoreCase("ByClientId"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					" distributor_id in (select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where client_id='"+EnterUserId+"'))";
					remark="on all Distributor under Client id='"+EnterUserId+"'";  
				}
				if(CuttOfBy.equalsIgnoreCase("ByMdId"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					" distributor_id in (select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"'))";
					remark="on all Distributor under MD id="+EnterUserId+"";
				}
			} 
			else
			{
				if(CuttOfBy.equalsIgnoreCase("ByAll"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					" distributor_id in (select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where client_id="+clientId+"))";
					remark="on all Distributor";
				}
				if(CuttOfBy.equalsIgnoreCase("ByClientId"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					" distributor_id in (select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where " +
					"client_id="+clientId+" and client_id="+EnterUserId+"))";
					remark="on all Distributor under Client id="+EnterUserId+"";  
				}
				if(CuttOfBy.equalsIgnoreCase("ByMdId"))
				{
					sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"' where" +
					" distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details " +
					"where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"' and client_id="+clientId+"))";
					remark="on all Distributor under MD id="+EnterUserId+"";
				}
			}
		           
			SQLQuery query = session.createSQLQuery(sql);
			int flag=query.executeUpdate();
			//System.out.println("flag---"+flag);
			if(flag>0)
			{
				CutOffAmountForm CutOffForm=new CutOffAmountForm();
				CutOffForm.setUserId(Userid);
				CutOffForm.setCutoffAmount(cutoffAmount);
				CutOffForm.setUserType("Distributor");
				CutOffForm.setIpAddress(ipAddress);
				CutOffForm.setUpdateduser(userType);
				CutOffForm.setRemark(remark);
				session.save(CutOffForm);
				update="done";   
			}
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			 System.out.println("Exception in CutoffAmountDao,doupdateAllDSCutoffAmount");
			 System.out.println(e.toString());
			 tran.rollback();
		}
		finally 
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) {
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateAllDSCutoffAmount");
				System.out.println(e.toString());
			}
		}
		return update;
	}

	public String doupdateDSIDCutoffAmount(double cutoffAmount, String userId,String userType, String ipAddress, int dSID,
			double secondcutoffAmount,String  checkBoxCutOff,String RemarkAdmin,String RemarkOther,int clientId)
	{
		String update="Notdone";
		String sql="";
			
		Session session=null;
		Transaction tran=null;
		int Userid=Integer.parseInt(userId);
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
			{
				sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'" +
				"where distributor_id ="+dSID+""; 
			} 
			else
			{
				sql="update distributor_amount set usedcash=(usedcash-cutoff_amount)+'"+cutoffAmount+"',cutoff_amount='"+cutoffAmount+"'" +
				"where distributor_id ="+dSID+" and distributor_id in (select distributor_id from " +
				"distributor_details where md_id in (select md_id from md_details where client_id='"+clientId+"'))";
			} 
				
			SQLQuery query = session.createSQLQuery(sql);
			int flag=query.executeUpdate();
			//System.out.println("flag---"+flag);
			if(flag>0) 
			{
				CutOffAmountForm CutOffForm=new CutOffAmountForm();
				CutOffForm.setUserId(Userid);
				CutOffForm.setCutoffAmount(cutoffAmount);
				CutOffForm.setUserType("Distributor");
				CutOffForm.setIpAddress(ipAddress);
				CutOffForm.setUpdateduser(userType);
				CutOffForm.setRemark(RemarkAdmin);
				session.save(CutOffForm);
				update="done";   
			} 
			tran.commit();
		}
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doupdateDSIDCutoffAmount");
			System.out.println(e.toString());
			tran.rollback();
		}
		finally 
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) 
			{
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateDSIDCutoffAmount");
				System.out.println(e.toString());
			}
		}
		return update;
	}

	public String doupdateAllMDCutoffAmount(double cutoffAmount, String userId,String userType, String ipAddress, String checkBoxCutOff,double secondcutoffAmount)
	{
		String update="Notdone";
		  
		Session session=null;
		Transaction tran=null;
		int Userid=Integer.parseInt(userId);
		String sql="";
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			String hql = "update MdsDetailsFormBean MDF set MDF.authorizedToCutoff =:AuthCut,MDF.cutOffLimit=:cutoff,MDF.updateUser=:User"; 
			Query query = session.createQuery(hql);
			query.setString("AuthCut","Y");
			query.setDouble("cutoff",secondcutoffAmount);
			query.setString("User",userType);
			query.executeUpdate();
			
			if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")) 
			{
				sql="update MD_Amount set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_Bal_Amount=Total_Bal_Amount-'"+cutoffAmount+"'";
			} else
			{
				sql="update MD_Amount set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_Bal_Amount=Total_Bal_Amount-'"+cutoffAmount+"' where md_id in(select md_id from md_details where client_id='"+userId+"')";
			}
			SQLQuery query2 = session.createSQLQuery(sql);
			int flag=query2.executeUpdate();
			if(flag>0) 
			{
				CutOffAmountForm CutOffForm=new CutOffAmountForm();
				CutOffForm.setUserId(Userid);
				CutOffForm.setCutoffAmount(cutoffAmount);
				CutOffForm.setUserType("MD");
				CutOffForm.setIpAddress(ipAddress);
				CutOffForm.setUpdateduser(userType);
				CutOffForm.setRemark("AllMD");
				session.save(CutOffForm);
				update="done";   
			}
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doupdateAllMDCutoffAmount");
			System.out.println(e.toString());
			tran.rollback();
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) 
			{
				update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doupdateAllMDCutoffAmount");
				 System.out.println(e.toString());
			}
		}
		return update;
	}

	public String getIdStatus(String cutOffFor, String enterUserId,String userId, String userType,int clientId)
	{
		String idStatus="NotExist";
		Session session=null;
		String qry="";
		int id=0;
		int userIdInt=Integer.parseInt(userId);
		//System.out.println("we are inside md view check");
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
			{
				if("AG".equalsIgnoreCase(cutOffFor)) 
				{			 
					qry="select agent_id from agent_details where agent_initial+convert(varchar(14),agent_id)='"+enterUserId+"'";
				}			 
		  
				if("DS".equalsIgnoreCase(cutOffFor))
				{
					qry="select distributor_id from " +
					"distributor_details where distributor_initial+convert(varchar(14),distributor_id)='"+enterUserId+"'";
				}   
		   
				if("MD".equalsIgnoreCase(cutOffFor))
				{
					System.out.println("we are in md_block of super admin");
					qry="select md_id from md_details where md_initial+convert(varchar(14),md_id)='"+enterUserId+"'";			  
				}
			}
		   
			if(userType.equalsIgnoreCase("portalUser")||userType.equalsIgnoreCase("portalAdmin"))
			{
				if("AG".equalsIgnoreCase(cutOffFor)) 
				{			 
					qry="select agent_id from agent_details where distributor_id in (select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where client_id="+clientId+")) and agent_initial+convert(varchar(14),agent_id)='"+enterUserId+"'";
				}			 
				  
				if("DS".equalsIgnoreCase(cutOffFor))
				{
					qry="select distributor_id from " +
					"distributor_details where md_id in (select md_id from md_details where client_id="+clientId+") and distributor_initial+convert(varchar(14),distributor_id)='"+enterUserId+"'";
				}   
				   
				if("MD".equalsIgnoreCase(cutOffFor))
				{
					qry="select md_id from md_details where client_id="+clientId+" and md_initial+convert(varchar(14),md_id)='"+enterUserId+"'";			  
				}
			}
			
			Query sqlquery=session.createSQLQuery(qry);
			List list=sqlquery.list();
			Iterator itr=list.iterator();
			if(itr.hasNext())
			{
				idStatus="Exist";  
			}else
			{
				idStatus="NotExist";
			}
		   
		} catch (Exception e)
		{
			idStatus="NotExist";
			System.out.println("Exception in CutoffAmountDao,getIdStatus");
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			} 
			catch (Exception e)
			{
				idStatus="NotExist";
				System.out.println("Exception in CutoffAmountDao,getIdStatus");
				System.out.println(e.toString());
			}
		}
		return idStatus;
	}
	
	public String getStatusByType(String CuttOfBy, String EnterUserId,String userType,int clientId,String cutOffFor) 
	{
		String idStatus="NotExist";
		String sql="";
		Session session=null;
		String qry=""; 
		try 
		{		
			session=HibernateSession.getSessionFactory().openSession();
			if("AG".equalsIgnoreCase(cutOffFor))
			{
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select agent_id from agent_details";
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select agent_id from agent_details where distributor_id in (select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where client_id='"+EnterUserId+"'))";				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByMdId"))
					{
						sql="select agent_id from agent_details where distributor_id in (select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"'))";
				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByDsId"))
					{
						sql="select agent_id from agent_details where distributor_id in " +
						"(select distributor_id from distributor_details where distributor_initial+convert(varchar(14),distributor_id)='"+EnterUserId+"')";
					}
				} 
				else
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select agent_id from agent_details where distributor_id in (select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where client_id="+clientId+"))";
						
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select agent_id from agent_details where distributor_id in (select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where " +
						"client_id="+clientId+" and client_id="+EnterUserId+"))";
				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByMdId"))
					{
						sql="select agent_id from agent_details where distributor_id in (select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details " +
						"where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"' and client_id="+clientId+"))";
				    	  
					}
					if(CuttOfBy.equalsIgnoreCase("ByDsId"))
					{
						sql="select agent_id from agent_details where distributor_id in " +
						"(select distributor_id from distributor_details where" +
						" distributor_initial+convert(varchar(14),distributor_id)='"+EnterUserId+"' and md_id in (" +
						"select md_id from md_details where client_id="+clientId+"))";
				    	  
					}	
				}
			}
			if("DS".equalsIgnoreCase(cutOffFor))
			{
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select distributor_id from distributor_details";
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where client_id='"+EnterUserId+"')";				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByMdId"))
					{
						sql="select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"')";
					}				      
				} 
				else
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where client_id="+clientId+")";
				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details where " +
						"client_id="+clientId+" and client_id="+EnterUserId+")";
				    	   
					}
					if(CuttOfBy.equalsIgnoreCase("ByMdId"))
					{
						sql="select distributor_id from " +
						"distributor_details where md_id in (select md_id from md_details " +
						"where md_initial+convert(varchar(14),md_id)='"+EnterUserId+"' and client_id="+clientId+")";
					}				      	
				}
			}
			if("MD".equalsIgnoreCase(cutOffFor)) 
			{
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select md_id from md_details";
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select md_id from md_details where client_id='"+EnterUserId+"'";				    	   
					}				      		      
				} 
				else
				{
					if(CuttOfBy.equalsIgnoreCase("ByAll"))
					{
						sql="select md_id from md_details where client_id="+clientId+"";
					}
					if(CuttOfBy.equalsIgnoreCase("ByClientId"))
					{
						sql="select md_id from md_details where " +
						"client_id="+clientId+" and client_id="+EnterUserId+"";
					}				      				      	
				}
			}
			Query sqlquery=session.createSQLQuery(sql);
			List list=sqlquery.list();
			Iterator itr=list.iterator();
			if(itr.hasNext())
			{
				idStatus="Exist";  
			}else
			{
				idStatus="NotExist";
			}
			
		} catch (Exception e)
		{
			idStatus="NotExist";
			System.out.println("Exception in CutoffAmountDao,getStatusByType");
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) 
			{
				idStatus="NotExist";
				System.out.println("Exception in CutoffAmountDao,getStatusByType");
				System.out.println(e.toString());
			}
		}
		return idStatus;
	}

	public String doupdateMDIDCutoffAmount(double cutoffAmount, String userId,String userType, String ipAddress, int mdID,
			double secondcutoffAmount, String checkBoxCutOff,String remarkAdmin, String remarkOther)
	{
		String update="Notdone";
		    
		Session session=null;
		Transaction tran=null;
		int Userid=Integer.parseInt(userId);
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			MdsAmountFormBean MDForm = (MdsAmountFormBean)session.get(MdsAmountFormBean.class,mdID);
			if(MDForm!=null)
			{
				MDForm.setUsedCash(MDForm.getUsedCash()-MDForm.getCutOffAmount()+cutoffAmount);
				MDForm.setAvailableBalanceAmount(MDForm.getTotalBalanceAmount()-cutoffAmount);
				MDForm.setCutOffAmount(cutoffAmount);
				MDForm.setLastAmount(cutoffAmount);
				session.update(MDForm);
			}   	   
			CutOffAmountForm CutOffForm=new CutOffAmountForm();
			CutOffForm.setUserId(Userid);
			CutOffForm.setCutoffAmount(cutoffAmount);
			CutOffForm.setUserType("MD");
			CutOffForm.setIpAddress(ipAddress);
			CutOffForm.setUpdateduser(userType);
			CutOffForm.setRemark(remarkAdmin);
			session.save(CutOffForm);
			update="done";   
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			 System.out.println("Exception in CutoffAmountDao,doupdateMDIDCutoffAmount");
			 System.out.println(e.toString());
			 tran.rollback();
		}
		finally 
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e)
			{
				update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doupdateMDIDCutoffAmount");
				 System.out.println(e.toString());
			}
		}
	       return update;
	}

	public String getcheckClientIdStatus(String enterUserId) 
	{
		String data="notfound";
		
		Session session=null;
		Query qry=null;
	      
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			int id=Integer.parseInt(enterUserId); 
			qry=session.createQuery("select clientId from  DynamicDetailsFormBean  DDF  where DDF.clientId=:id ").setParameter("id",id);
			Iterator<?>it=qry.iterate();
			if(it.hasNext())
			{
				data="found";
			}
		}catch (Exception e)
		{
			data="notfound";
			 System.out.println("Exception in CutoffAmountDao,getcheckClientIdStatus");
			 System.out.println(e.toString());
		}
		finally 
		{
			try 
			{
				session.flush();
				session.close();
			}catch (Exception e) 
			{
				System.out.println("Exception in CutoffAmountDao,getcheckClientIdStatus");
				System.out.println(e.toString());
			}
		}
		return data;
	}

	public String doUpdateCutOffByClientId(double cutoffAmount, String userId,String userType,String ipAddress,String checkBoxCutOff,double secondcutoffAmount,int clientID)
	{
		String update="Notdone";
		Session session=null;
		Transaction tran=null;
		String sql="";
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			String hql = "update MdsDetailsFormBean MDF set MDF.authorizedToCutoff =:AuthCut,MDF.cutOffLimit=:cutoff,MDF.updateUser=:User where MDF.clientId=:id"; 
			Query query = session.createQuery(hql);
			query.setString("AuthCut","Y");
			query.setDouble("cutoff",secondcutoffAmount);
			query.setString("User",userType);
			query.setInteger("id",clientID);
			query.executeUpdate();
			sql="update MD_Amount set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_Bal_Amount=Total_Bal_Amount-'"+cutoffAmount+"' where md_id in(select md_id from md_details where client_id='"+clientID+"')";
			SQLQuery query2 = session.createSQLQuery(sql);
			int flag=query2.executeUpdate();
			
			if(flag>0) 
			{
				CutOffAmountForm CutOffForm=new CutOffAmountForm();
				CutOffForm.setUserId(clientID);
				CutOffForm.setCutoffAmount(cutoffAmount);
				CutOffForm.setUserType("MD");
				CutOffForm.setIpAddress(ipAddress);
				CutOffForm.setUpdateduser(userType);
				CutOffForm.setRemark("AllMDOnClient");
				session.save(CutOffForm);
				update="done";   
			}
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doUpdateCutOffByClientId");
			System.out.println(e.toString());
			tran.rollback();
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) 
			{
				update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doUpdateCutOffByClientId");
				 System.out.println(e.toString());
			}
		}
		return update;
	}

	public HashMap<String, Object> getdataSecond(String enterUserId,String cutOffFor) 
	{
		HashMap<String,Object>Data=new HashMap<String,Object>();
		Session session=null;
		String AvilBalance="";
		String TotalBalance="";
		String stringTot="";
		String useString="";
		String cutString="";
		String middleName="";
		String name="";
		String cutLimitString="";
		Query qry=null;
		DecimalFormat dff = new DecimalFormat("00.00");
		
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			if("Portal".equalsIgnoreCase(cutOffFor))
			{ 
				int id=Integer.parseInt(enterUserId);
				qry=session.createQuery("select ADF.firstName,ADF.lastName,AAF.userId,AAF.totalCash,AAF.usedCash,AAF.totalBalanceAmount,AAF.availableBalanceAmount,AAF.cutOffAmount from  AdminUserFormBean ADF,UserAmountFormBean AAF where ADF.userId=AAF.userId and AAF.userId=:id").setParameter("id",id);
			}
			if("API".equalsIgnoreCase(cutOffFor))
			{
				qry=session.createQuery("select ADF.firstName,ADF.lastName,AAF.corporateAgentId,AAF.totalCash,AAF.usedCash,AAF.totalBalanceAmount,AAF.availableBalanceAmount,AAF.cutOffAmount from  APIClientDetailsFormBean ADF,ApiClientAmountFormBean AAF where  ADF.corporateAgentId=convert(varchar,AAF.corporateAgentId)and ADF.corporateAgentInitial+convert(varchar,AAF.corporateAgentId)=:id").setParameter("id",enterUserId);
			}
			System.out.println("Query : "+qry);
			System.out.println("enterUserId : "+enterUserId);
			
			Iterator<?>it=qry.iterate();
			while(it.hasNext())
			{
				Object[] row=(Object[])it.next();
				
				name=(String)row[0]+" "+(String)row[1];
				stringTot=dff.format((Double)row[3]);
				useString=dff.format((Double)row[4]);
				TotalBalance=dff.format((Double)row[5]);
				AvilBalance=dff.format((Double)row[6]);
				cutString=dff.format((Double)row[7]);
				Data.put("UserName",name);
				Data.put("OnlyUserId",row[2]);
				Data.put("stringTot",stringTot);
				Data.put("useString",useString);
				Data.put("cutString",cutString);
				Data.put("AvilBalance",AvilBalance);
				Data.put("TotalBalance",TotalBalance);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in CutoffAmountDao,getdataSecond");
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			} 
			catch (Exception e)
			{
				System.out.println("Exception in CutoffAmountDao,getdataSecond");
				e.printStackTrace();
			}
		}
		return Data;
	}

	public String doupdateAPIIDCutoffAmount(double cutoffAmount,String userId,String userType,String ipAddress,int apiID,String remarkAdmin,String remarkOther) 
	{
		String update="Notdone";
		
		Session session1=null;
		Query qry=null;
		Transaction tran=null;
		try
		{
			session1=HibernateSession.getSessionFactory().openSession();
			tran=session1.beginTransaction();
			//----------------------------Table update Rech_API_Corporate_Agent_Amount----------------------		
			ApiClientAmountFormBean ApiClientAmount = (ApiClientAmountFormBean)session1.get(ApiClientAmountFormBean.class,apiID);
			if(ApiClientAmount!=null)
			{
				ApiClientAmount.setUsedCash(ApiClientAmount.getUsedCash()-ApiClientAmount.getCutOffAmount()+cutoffAmount);
				ApiClientAmount.setAvailableBalanceAmount(ApiClientAmount.getTotalBalanceAmount()-cutoffAmount);
				ApiClientAmount.setCutOffAmount(cutoffAmount);
				ApiClientAmount.setLastAmount(cutoffAmount);
				ApiClientAmount.setCorporateAgentId(apiID);
				session1.update(ApiClientAmount);
			}
			update="done"; 
			tran.commit();
			
			
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doupdateAPIIDCutoffAmount");
        	System.out.println(e.toString());
			tran.rollback();
		}
		finally 
		{
			try
			{
				session1.flush();
				session1.close();
			} 
			catch (Exception e)
			{
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateAPIIDCutoffAmount");
	        	System.out.println(e.toString());
			}
		}
		Session session=HibernateSession.getSessionFactory().openSession();
		try
		{
			tran=null;
			update="Notdone";
			
			tran=session.beginTransaction();
			CutOffAmountForm CutOffForm=new CutOffAmountForm();
			CutOffForm.setUserId(apiID);
			CutOffForm.setCutoffAmount(cutoffAmount);
			CutOffForm.setUserType("API");
			CutOffForm.setIpAddress(ipAddress);
			CutOffForm.setUpdateduser(userType);
			CutOffForm.setRemark(remarkAdmin);
			session.save(CutOffForm);
		
			update="done";   
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doupdateAPIIDCutoffAmount");
        	System.out.println(e.toString());
			tran.rollback();
		}
		finally 
		{
			try
			{
				session.flush();
				session.close();
			} 
			catch (Exception e)
			{
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateAPIIDCutoffAmount");
	        	System.out.println(e.toString());
			}
		}
		return update;
	}

	public String doupdateAllAPICutoffAmount(double cutoffAmount,String userId,String userType,String ipAddress)
	{
		String update="Notdone";
		 
		Session session1=null;
		Query qry=null;
		Transaction tran=null;
		int Userid=Integer.parseInt(userId);
		String sql="";
		try
		{ 
			session1=HibernateSession1.getSessionFactory().openSession();
			tran=session1.beginTransaction();
			sql="update Rech_API_Corporate_Agent_Amount set Used_Cash=(Used_Cash-CutOff_Amount)+'"+cutoffAmount+"',CutOff_Amount='"+cutoffAmount+"',Available_Bal_Amount=Total_Bal_Amount-'"+cutoffAmount+"'";
			System.out.println("sql : "+sql);
			SQLQuery query2 = session1.createSQLQuery(sql);
			int flag=query2.executeUpdate();
		    if(flag>0) 
		    	update="done";   
			
			tran.commit();
		} 
		catch (Exception e)
		{
			update="NotUpdate";
			System.out.println("Exception in CutoffAmountDao,doupdateAllAPICutoffAmount");
			System.out.println(e.toString());
			tran.rollback();
		}
		finally
		{
			try
			{
				session1.flush();
				session1.close();
			} 
			catch (Exception e)
			{
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateAllAPICutoffAmount");
				System.out.println(e.toString());
			}
		}
		//System.out.println("update : "+update);
		if(update.equalsIgnoreCase("done"))
		{
			tran=null;
			update="NotUpdate";
			Session session=HibernateSession.getSessionFactory().openSession();
			try
			{ 
				tran=session.beginTransaction();
				
				CutOffAmountForm CutOffForm=new CutOffAmountForm();
				CutOffForm.setUserId(Userid);
				CutOffForm.setCutoffAmount(cutoffAmount);
				CutOffForm.setUserType("API");
				CutOffForm.setIpAddress(ipAddress);
				CutOffForm.setUpdateduser(userType);
				CutOffForm.setRemark("AllAPI");
				session.save(CutOffForm);
				
				update="done";   
				
				tran.commit();
			} 
			catch (Exception e)
			{
				update="NotUpdate";
				System.out.println("Exception in CutoffAmountDao,doupdateAllAPICutoffAmount");
				System.out.println(e.toString());
				tran.rollback();
			}
			finally
			{
				try
				{
					session.flush();
					session.close();
				} 
				catch (Exception e)
				{
					update="NotUpdate";
					System.out.println("Exception in CutoffAmountDao,doupdateAllAPICutoffAmount");
					System.out.println(e.toString());
				}
			}
		}
		return update;
	}
	
	public String doupdatePortalIDCutoffAmount(double cutoffAmount,String userId, String userType, String ipAddress, int portalID,
			double secondcutoffAmount, String checkBoxCutOff, String remarkAdmin) 
	{
		  String update="Notdone";
		  
		  Session session=null;
		  Transaction tran=null;
		  int Userid=Integer.parseInt(userId);
		  String sql="";
		  try
		  {
			  session=HibernateSession.getSessionFactory().openSession();
			  tran=session.beginTransaction();
//			  String hql = "update AdminUserFormBean MDF set MDF.authorizedToCutoff =:AuthCut,MDF.cutOffLimit=:cutoff,MDF.updateUser=:User"; 
//			  Query query = session.createQuery(hql);
//			  query.setString("AuthCut","Y");
//			  query.setDouble("cutoff",secondcutoffAmount);
//			  query.setString("User",userType);
//			  query.executeUpdate();
			  if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")) 
			  {
				  sql="update Admin_user_amount_details set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_balance_amount=Total_balance_Amount-'"+cutoffAmount+"' where portal_id='"+portalID+"'";
			  } else
			  {
				  sql="update Admin_user_amount_details set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_balance_amount=Total_balance_Amount-'"+cutoffAmount+"' where Portal_id='"+userId+"')";
			  }
			  SQLQuery query2 = session.createSQLQuery(sql);
			  int flag=query2.executeUpdate();
			  if(flag>0) 
			  {
				  CutOffAmountForm CutOffForm=new CutOffAmountForm();
				  CutOffForm.setUserId(Userid);
				  CutOffForm.setCutoffAmount(cutoffAmount);
				  CutOffForm.setUserType("Portal");
				  CutOffForm.setIpAddress(ipAddress);
				  CutOffForm.setUpdateduser(userType);
				  CutOffForm.setRemark("AllPortal");
				  session.save(CutOffForm);
				  update="done";   
			  }
			  tran.commit();
		  } 
		  catch (Exception e)
		  {
			  update="NotUpdate";
			  System.out.println("Exception in CutoffAmountDao,doupdatePortalIDCutoffAmount");
			  System.out.println(e.toString());
			  tran.rollback();
		  }
		  finally 
		  {
			  try
			  {
				  session.flush();
				  session.close();
			  } 
			  catch (Exception e) {
				  update="NotUpdate";
				  System.out.println("Exception in CutoffAmountDao,doupdatePortalIDCutoffAmount");
				  System.out.println(e.toString());
			  }
		  }
		  return update;
	}

	public String doupdateAllPortalCutoffAmount(double cutoffAmount,String userId, String userType, String ipAddress,
			String checkBoxCutOff, double secondcutoffAmount) 
	{
		 String update="Notdone";
		 Session session=null;
		 Transaction tran=null;
		 int Userid=Integer.parseInt(userId);
		 String sql="";
		 try
		 {
			 session=HibernateSession.getSessionFactory().openSession();
			 tran=session.beginTransaction();
			 String hql = "update AdminUserFormBean MDF set MDF.authorizedToCutoff =:AuthCut,MDF.cutOffLimit=:cutoff,MDF.updateUser=:User"; 
			 Query query = session.createQuery(hql);
			 query.setString("AuthCut","Y");
			 query.setDouble("cutoff",secondcutoffAmount);
			 query.setString("User",userType);
			 query.executeUpdate();
			 if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin")) 
			 {
				 sql="update Admin_user_amount_details set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_balance_amount=Total_balance_Amount-'"+cutoffAmount+"'";
			 } else
			 {
				 sql="update Admin_user_amount_details set used_cash=(used_cash-Cutoff_Amount)+'"+cutoffAmount+"',Cutoff_Amount='"+cutoffAmount+"',Available_balance_amount=Total_balance_Amount-'"+cutoffAmount+"' where Portal_id='"+userId+"')";
			 }
			 SQLQuery query2 = session.createSQLQuery(sql);
			 int flag=query2.executeUpdate();
			 if(flag>0)
			 {
				 CutOffAmountForm CutOffForm=new CutOffAmountForm();
				 CutOffForm.setUserId(Userid);
				 CutOffForm.setCutoffAmount(cutoffAmount);
				 CutOffForm.setUserType("Portal");
				 CutOffForm.setIpAddress(ipAddress);
				 CutOffForm.setUpdateduser(userType);
				 CutOffForm.setRemark("AllPortal");
				 session.save(CutOffForm);
				 update="done";   
			 }
			 tran.commit();
		 } 
		 catch (Exception e)
		 {
			 update="NotUpdate";
			 System.out.println("Exception in CutoffAmountDao,doupdateAllPortalCutoffAmount");
			 System.out.println(e.toString());
			 tran.rollback();
		 }
		 finally
		 {
			 try
			 {
				 session.flush();
				 session.close();
			 } 
			 catch (Exception e)
			 {
				 update="NotUpdate";
				 System.out.println("Exception in CutoffAmountDao,doupdateAllPortalCutoffAmount");
				 System.out.println(e.toString());
			 }
		 }
		 return update;
	}
}
