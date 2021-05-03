package com.controlPanel.manageEGEN.egenCom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession1;

/*
 * Created By   : Manoj 
 * Created Date : 3/12/2012
 * Matter       : This class is used for Set Egen Client Commission and status update.
 */
public final class EgenCommissionDao {
	/*
	 *  Local DB OnlineRechAPI_Live_db
	 *  Live  DB OnlineRechAPI_Live_db
	 */
	
	public final String chkCorpAgentId(String corpAgentId){
		
		String status="notexists";
		String sqlQuery="";
		Query qry = null;
		Session session=null;
		try{
			session=HibernateSession1.getSessionFactory().openSession();
			sqlQuery="Select count(Corporate_Agent_Id) from Rech_API_Corporate_Agent_Details where Corporate_Agent_Id='"+corpAgentId+"'";
			//System.out.println(sqlQuery);
	        qry=session.createSQLQuery(sqlQuery);
			int CorpAgId=Integer.parseInt(qry.uniqueResult().toString());
			if(CorpAgId==0){
				status="notexists";
			}
			else{
				status="exists";
			}
		}catch(Exception e){
			status="notexists";
			System.out.println("Exception in class EgenCommissionDao in chkCorpAgentId");
			System.out.println(e.toString());
				
		}
		finally{
			try{
				session.close();
			}
			catch(Exception e){
				System.out.println("Exception in class EgenCommissionDao in chkCorpAgentId");
				System.out.println(e.toString());
			}
		}
		return status; 
	}
	
	public final ArrayList getCorpAgentDetails(String corpAgentId,String service){
		
		ArrayList listdata=new ArrayList();
		String status="notexists";
		String sqlQuery="";
		Query qry = null;
		Session session=null;
	    try{
	    	session=HibernateSession1.getSessionFactory().openSession();
	    	sqlQuery="select Corporate_Agent_id,Operator,Operator_name,service,status,commission,commission_mark from Rech_API_Service_Operator_Details where Corporate_Agent_Id='"+corpAgentId+"' and Service='"+service+"' ";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row;
			HashMap data;
			while (itr.hasNext()){
				row=(Object[])itr.next();
				data= new HashMap();
				
				data.put("corporateAgntId",row[0]);
				data.put("operator",row[1]);
				data.put("operatorName",row[2]);
				data.put("service",row[3]);
				data.put("status",row[4]);
				data.put("comm",row[5]);
				data.put("commMark",row[6]);
			  
			 
				listdata.add(data);
			}
			System.out.println("listdata : "+listdata);
	    }catch(Exception e){
	    	System.out.println("Exception in class EgenCommissionDao in getCorpAgentDetails");
	    	System.out.println(e.toString());
				
	    }
	    finally{
	    	try{
	    		session.flush();
	    		session.close();
	    	}
	    	catch(Exception e){
	    		System.out.println("Exception in class EgenCommissionDao in getCorpAgentDetails");
	    		System.out.println(e.toString());
	    	}
	    }
	    return listdata; 
	}
	
	public final String updateOptrAll(String optrCode,String service,String status,String comm,String commMark,String corpAgentId){
		
		String updStatus="notsuccess";
		String sqlQuery="";
		Query qry = null;
		Session session=null;
		Transaction tr = null;
		
		try{
			session=HibernateSession1.getSessionFactory().openSession();
			tr=session.beginTransaction();
			sqlQuery="update Rech_API_Service_Operator_Details set Status='"+status+"',commission='"+comm+"' , commission_mark='"+commMark+"' where corporate_agent_id='"+corpAgentId+"' and service='"+service+"' and  Operator='"+optrCode+"'";
			//System.out.println(sqlQuery);
	        qry=session.createSQLQuery(sqlQuery);
	        qry.executeUpdate();
	        tr.commit();
	        updStatus="success";
		}catch(Exception e){
				updStatus="notsuccess";
				tr.rollback();
				System.out.println("exception in class EgenCommissionDao in updateOptrAll");
				System.out.println(e.toString());
				
		}
		finally{
			try{
				session.flush();
	    		session.close();
			}
			catch(Exception e){
				System.out.println("exception in class EgenCommissionDao in updateOptrAll");
				System.out.println(e.toString());
			}
		}
		return updStatus; 
	}
	
	public final String updateOptrStatus(String optrCode,String service,String status,String corpAgentId){
		  
		String updStatus="notsuccess";
		String sqlQuery="";
		Query qry = null;
		Session session=null;
		Transaction tr;
		tr=session.beginTransaction();
		try{
			session=HibernateSession1.getSessionFactory().openSession();
			sqlQuery="update Rech_API_Service_Operator_Details set Status='"+status+"' where corporate_agent_id='"+corpAgentId+"' and service='"+service+"' and  Operator='"+optrCode+"'";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
	        qry.executeUpdate();
	        tr.commit();
			updStatus="success";
			
		}catch(Exception e){
			updStatus="notsuccess";
			tr.rollback();
			System.out.println("exception in class EgenCommissionDao in updateOptrStatus");
			System.out.println(e.toString());
				
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("exception in class EgenCommissionDao in updateOptrStatus");
				System.out.println(e.toString());
			}
		}
		return updStatus; 
	}
	
	public final String updateOptrComm(String optrCode,String service,String comm,String commMark,String corpAgentId){
		  
		String updStatus="notsuccess";
		String sqlQuery="";
		Query qry = null;
		Session session=HibernateSession1.getSessionFactory().openSession();
		Transaction tr;
		tr=session.beginTransaction();
	   
		try{
			
			sqlQuery="update Rech_API_Service_Operator_Details set commission='"+comm+"' , commission_mark='"+commMark+"' where corporate_agent_id='"+corpAgentId+"' and service='"+service+"' and  Operator='"+optrCode+"'";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			tr.commit();
			updStatus="success";
			
		}catch(Exception e){
			updStatus="notsuccess";
			tr.rollback();
			System.out.println("exception in class EgenCommissionDao in updateOptrComm");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("exception in class EgenCommissionDao in updateOptrComm");
				System.out.println(e.toString());
			}
		}
		return updStatus; 
	}

	public final ArrayList getServices() {
		ArrayList listdata=new ArrayList();
		String status="notexists";
		String sqlQuery="";
		Query qry = null;
		Session session=HibernateSession1.getSessionFactory().openSession();
	    try{
	    	
	    	sqlQuery="select Type,Operator_name,vendor,status from Rech_API_MobDth_Operator_Details order by Type";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row;
			HashMap data;
			while (itr.hasNext()){
				row=(Object[])itr.next();
				data= new HashMap();
				
				data.put("Type",row[0]);
				data.put("operator",row[1]);
				data.put("vendor",row[2]);
				data.put("status",row[3]);
				listdata.add(data);
			}
	    }catch(Exception e){
	    	System.out.println("Exception in class EgenCommissionDao in getServices. ");
	    	System.out.println(e.toString());
				
	    }
	    finally{
	    	try{
	    		session.flush();
	    		session.close();
	    	}
	    	catch(Exception e){
	    		System.out.println("Exception in class EgenCommissionDao in getServices");
	    		System.out.println(e.toString());
	    	}
	    }
	    return listdata; 
	}

	public final String updateServices(String mark, String operator, String service) {
		String updStatus="notsuccess";
		String sqlQuery="";
		Query qry = null;
		Session session=HibernateSession1.getSessionFactory().openSession();
		Transaction tr;
		tr=session.beginTransaction();
	   
		try{
			
			sqlQuery="update Rech_API_MobDth_Operator_Details set Status='"+mark+"' where Type='"+service+"' and operator_name='"+operator+"'";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			tr.commit();
			updStatus="success";
			
		}catch(Exception e){
			updStatus="notsuccess";
			tr.rollback();
			System.out.println("exception in class EgenCommissionDao in updateServices");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("exception in class EgenCommissionDao in updateServices");
				System.out.println(e.toString());
			}
		}
		return updStatus;
		
	}

	public final ArrayList getCorpAgentServDetails(String corpAgentId) {
		ArrayList listdata=new ArrayList();
		String status="notexists";
		String sqlQuery="";
		Query qry = null;
		Session session=HibernateSession1.getSessionFactory().openSession();
	    try{
	    	
	    	sqlQuery="select Corporate_agent_id,Service,Status,remark from Rech_API_User_Service_Details where Corporate_agent_id='"+corpAgentId+"'";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row;
			HashMap data;
			while (itr.hasNext()){
				row=(Object[])itr.next();
				data= new HashMap();
				
				data.put("corporateAgntId",row[0]);
				data.put("service",row[1]);
				data.put("status",row[2]);
				data.put("remark",row[3]);
				listdata.add(data);
			}
			//System.out.println("listdata : "+listdata);
	    }catch(Exception e){
	    	System.out.println("Exception in class EgenCommissionDao in getServices. ");
	    	System.out.println(e.toString());
	    }
	    finally{
	    	try{
	    		session.flush();
	    		session.close();
	    	}
	    	catch(Exception e){
	    		System.out.println("Exception in class EgenCommissionDao in getServices");
	    		System.out.println(e.toString());
	    	}
	    }
	    return listdata; 
	}

	public final String updateAgntServices(String corpAgentId, String mark,String service, String remark) {
		String updStatus="notsuccess";
		String sqlQuery="";
		Query qry = null;
		Session session=HibernateSession1.getSessionFactory().openSession();
		Transaction tr;
		tr=session.beginTransaction();
	   
		try{
			
			sqlQuery="update Rech_API_User_Service_Details set Status='"+mark+"',remark='"+remark+"',Updateed_Date=getDate(),updated_user='Super Admin' where Service='"+service+"' and Corporate_Agent_Id='"+corpAgentId+"'";
			//System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			tr.commit();
			updStatus="success";
			
		}catch(Exception e){
			updStatus="notsuccess";
			tr.rollback();
			System.out.println("exception in class EgenCommissionDao in updateAgntServices");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}
			catch(Exception e){
				System.out.println("exception in class EgenCommissionDao in updateAgntServices");
				System.out.println(e.toString());
			}
		}
		return updStatus;
	}
}
