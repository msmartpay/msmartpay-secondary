package com.disttributordetails;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.agentDetails.AgentDetailDao;
import com.common.HibernateSession;
import com.common.LogWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DistributorProfileDao {

	public HashMap<String, Object> getDSProfile(String userId) {
		 Logger logger = Logger.getLogger(DistributorProfileDao.class);
		 LogWriter log=new LogWriter();		
		 HashMap<String, Object> DistributorProfileMapInfo=new HashMap<String, Object>();
		 Session session = HibernateSession.getSessionFactory().openSession();	 
		 try 
		    {			 
			 Query query=session.createQuery("select DDF.DistributorName,DDF.distributorId,DDF.distributorInitial,DDF.MobileNo,DDF.emailId,DDF.Address,DDF.Address2,DDF.State,DDF.District,DDF.City,DDF.PinCode,DDF.companyName from DistributorDetailForm DDF where DDF.distributorId=:DistributorId").setParameter("DistributorId",userId);
			 log.print("distributor Login query is "+query, logger);
			 Iterator<?> it=query.iterate();

			 if(it.hasNext())
			 {
				 Object[] row = (Object[]) it.next();
				 DistributorProfileMapInfo.put("DistributorName",row[0]);
				 DistributorProfileMapInfo.put("distributorId", row[1]);
				 DistributorProfileMapInfo.put("distributorInitial", row[2]);
				// DistributorProfileMapInfo.put("Designation", row[3]);
				 DistributorProfileMapInfo.put("MobileNo", row[3]);
				 DistributorProfileMapInfo.put("emailId", row[4]);
				 DistributorProfileMapInfo.put("Address", row[5]);
				 DistributorProfileMapInfo.put("Address2", row[6]);
				 DistributorProfileMapInfo.put("State",row[7]);
				 DistributorProfileMapInfo.put("District",row[8]);
				 DistributorProfileMapInfo.put("City", row[9]);
				 DistributorProfileMapInfo.put("PinCode", row[10]);
			//	 DistributorProfileMapInfo.put("AuthorizedMobile", row[12]);
				 DistributorProfileMapInfo.put("companyName",row[11]);				
			 }
			 Query qry1=session.createSQLQuery("select b.md_initial+convert(varchar,b.md_id) as MDID from distributor_details a,md_details b where distributor_id='"+userId+"'and a.md_id=b.md_id");
			 log.print("distributor Login query is "+qry1, logger);
				String  MDID=(qry1.uniqueResult().toString());
				DistributorProfileMapInfo.put("MDID",MDID);
		    }	 
		 catch (Exception ex) {
			 ex.printStackTrace();
		 }
		 finally {
			 try {
				 session.flush();
				 session.close();
			 } 
			 catch (Exception e) {				 
				 e.printStackTrace();
			 }
		 }
		 return DistributorProfileMapInfo;
	}
	public JSONArray getDSJournal(String userId)
	{
		 
		JSONArray ActiveAgnetInfo=new JSONArray();
		Session session = null;
		Query query =null;
		try{
			session = HibernateSession.getSessionFactory().openSession();
			JSONObject temp;
			query=session.createSQLQuery("select top 10 mode_of_payment,ammount_to_credit,request_date,approval_date,Bank_Tran_Id, status,remarks,journalid from distributor_journal where distributor_id='"+userId+"' order by request_date desc,request_time desc")
					.addScalar("mode_of_payment", Hibernate.STRING).addScalar("ammount_to_credit", Hibernate.STRING).addScalar("request_date", Hibernate.STRING).addScalar("approval_date", Hibernate.STRING)
					.addScalar("Bank_Tran_Id", Hibernate.STRING).addScalar("status", Hibernate.STRING).addScalar("remarks", Hibernate.STRING).addScalar("journalid", Hibernate.STRING);
			
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				temp=new JSONObject();
				temp.put("mod",row[0]+""); 
				temp.put("amount",row[1]+""); 
				temp.put("reqDate",row[2]+"");
				temp.put("appDate",row[3]==null?"":row[3]+"");  
				temp.put("refId",row[4]==null?"":row[4]+"");
				temp.put("status",row[5]+"");
				temp.put("remark",row[6]==null?"":row[6]+"");
				temp.put("ReqId",row[7]==null?"":row[7]+"");
					 
				ActiveAgnetInfo.add(temp);				
			}		
		}			
		catch(Exception  ex){		    	
			ex.printStackTrace();
		}
		finally {
			try {
				session.close();
			} 
			catch (Exception e) {				
				e.printStackTrace();
			}
		}		
		return ActiveAgnetInfo;
	}
	
	public HashMap<String, Object> getUpdateData(String distributorId)	
	{
		 Logger logger = Logger.getLogger(DistributorProfileDao.class);
		 LogWriter log=new LogWriter();		
		 HashMap<String, Object> DistributorProfileMapInfo=new HashMap<String, Object>();
		 Session session = HibernateSession.getSessionFactory().openSession();
		 String AvilableBalance="";
		// String OfficePhoneNumber="";
		 String StdCode="";
		 String FaxNo="";
	     double tot=0.0000;
		 double used=0.0000;
		 DecimalFormat dff = new DecimalFormat("##.#### ");
		 
		 try 
		 {
			 Query query=session.createQuery("select DAF.totalCash,DAF.usedCash,DAF.cutoffAamount,DAF.lastAmount," +
					 "DDF.DistributorName from DistributorDetailForm DDF,DistributorAmountForm DAF where " +
			 "DDF.distributorId=DAF.distributorId and  DDF.distributorId=:DistributorId").setParameter("DistributorId",distributorId);
			 log.print("getUpdateData "+query, logger);
			 Iterator<?> it=query.iterate();

			 if(it.hasNext())
			 {
				 Object[] row = (Object[]) it.next();
				 tot=(Double) row[0];
				 log.print("tot "+tot, logger);
				 used=(Double)row[2];
				 log.print("used "+used, logger);
				 AvilableBalance=dff.format(tot-used);
				 log.print("AvilableBalance "+AvilableBalance, logger);	
				 
				 DistributorProfileMapInfo.put("totalCash", row[0]);
				 DistributorProfileMapInfo.put("usedCash", row[1]);
				 DistributorProfileMapInfo.put("cutoffAamount", row[2]);
				 DistributorProfileMapInfo.put("lastAmount",row[3]);			
				 DistributorProfileMapInfo.put("DistributorName",row[4]);
				 DistributorProfileMapInfo.put("AvilableBalance",AvilableBalance);				
			 }
		 } 	 
		 catch (Exception ex) {
			ex.printStackTrace();
		}
		 finally {
			 try {
				 session.flush();
				 session.close();
			 } 
			 catch (Exception e) {						
				 e.printStackTrace();
			 }
		 }
		 return DistributorProfileMapInfo;
	}	
	public String doUpdate(String userId, String address, String address2,String country, String state, String district, String city,String agencyName, String officePinCode) {		
		    @SuppressWarnings("unused")
			Logger logger = Logger.getLogger(DistributorProfileDao.class);
		    @SuppressWarnings("unused")
			LogWriter log=new LogWriter();
		    String updateStatus="notUpdated";
			Session session = HibernateSession.getSessionFactory().openSession();
			Transaction tran=null;			
			try{
				tran=session.beginTransaction();
		//		DistributorDetailForm DistributorDetailForm = (DistributorDetailForm)session.get(DistributorDetailForm.class,userId);
				/*   if(DistributorDetailForm!=null){
					DistributorDetailForm.setAddress(address);
					DistributorDetailForm.setAddress2(address2);
					DistributorDetailForm.setCountry(country);
					DistributorDetailForm.setState(state);
					DistributorDetailForm.setDistrict(district);
					DistributorDetailForm.setCity(city);
					DistributorDetailForm.setCompanyName(agencyName);
					DistributorDetailForm.setMobileNo(officePinCode);								    
					session.update(DistributorDetailForm);
				}*/
				
				String sqlquery="update distributor_details set company_name='"+agencyName+"',address1='"+address+"',address2='"+address2+"',pin_code='"+officePinCode+"',office_district='"+district+"',city='"+city+"',state='"+state+"' where distributor_id='"+userId+"'";				
				Query query = session.createSQLQuery(sqlquery);					
				int result = query.executeUpdate();	
				if(result>0){
					updateStatus="Updated";						
				}				
				tran.commit();
					    
		       }		  
	    catch(Exception  ex){
	    tran.rollback();
	    updateStatus="notUpdated";
	    ex.printStackTrace();
	     }
	    finally {	
		  try {			
			  session.close();
		  } 
		  catch (Exception e) {			  
			  e.printStackTrace();
		  }
	    }		
	    return updateStatus;
	}
/*	public String getCity(String citycode) {
		Logger logger = Logger.getLogger(DistributorProfileDao.class);
	    LogWriter log=new LogWriter();
	    String CityName="notfound";
	    Session session = HibernateSession.getSessionFactory().openSession();    
	    try 
	    {
		 Query query=session.createQuery("select CNF.CityName from CityNameForm CNF where CNF.CityCode=:citycode").setParameter("citycode",citycode);
		 log.print("getCity name  query is "+query, logger);
		 Iterator<?> it=query.iterate();

		 if(it.hasNext())
		 {
			 System.out.println("------------------------------");
			 Object row = (Object) it.next();
			 CityName=(String)row;
			
		 }
		 else
		 {
			 CityName="notfound";
		 }

	  } 
	 
	 
	 
	 
	 catch (Exception ex) {
		 CityName="notfound";
		ex.printStackTrace();
	}
	 finally {
		 try {
				session.flush();
				session.close();
			     } 
			   catch (Exception e) {
				
				e.printStackTrace();
			     }
		       }
	 return CityName;
	}*/

	/* Created By : Arshad khan
	 * This method fetch the minimum and maximum amount to deposit so no user can request less than or greater than this fetched amount.
	 * 
	 */
	public HashMap<String, String> getMaxMinLimit(String userId) {
		 Logger logger = Logger.getLogger(DistributorProfileDao.class);
		 LogWriter log=new LogWriter();
		
		 HashMap<String, String> information=new HashMap<String, String>();
		 Session session = HibernateSession.getSessionFactory().openSession();
		 Query qry=null;
		 try{
			 
			 qry=session.createSQLQuery("select Trade_Balance_Limit,Trade_Balance_Max_Limit from trade_Balance_Limit_Setup where user_type='distributor'");
			 log.print("Query in  class-DistributorProfileDao and method getMaxMinLimit--"+qry,logger);
			 List list=qry.list();
			 
			 Iterator itr=list.iterator();
			 while(itr.hasNext())
			 {
				 Object[] row=(Object[])itr.next();
				 
				 information.put("minAmount",row[0].toString());
				 information.put("maxAmount",row[1].toString());				
			 }
		 }catch(Exception e){
			 System.out.println("Exception in getMaxMinLimit"+e.toString());
		 }
		 finally {
			 try {
					session.flush();
					session.close();
			 } 
			 catch (Exception ex) {
				 
				 ex.printStackTrace();
			 }
		 }
		return information;
	}	
}