package com.report.agentAmountHistory;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.DistributorDepositRequest.DistributorDepositRequestDao;
import com.common.HibernateSession;
import com.common.LogWriter;

public class AgentTransactionHistoryDao {

	public ArrayList<HashMap<String, Object>> getAgentIdCollection(String userId) {
		 Logger logger = Logger.getLogger(AgentTransactionHistoryDao.class);
		 LogWriter log=new LogWriter();
		 Session session=HibernateSession.getSessionFactory().openSession();
		 ArrayList<HashMap<String, Object>> idArray=new ArrayList<HashMap<String, Object>>();
		 String AgentCompleteId="";
		 String agentid="";
		 String agentinitial="";
		 try 
		 {
			 Query IdQuery=session.createQuery("select ADF.agentId,ADF.agentInitial  from  AgentDetailForm ADF where  ADF.distributorId=:userId").setParameter("userId",userId);
			 log.print("getAgentIdCollection -getIdStatus- query is "+IdQuery, logger);
			 Iterator<?> it=IdQuery.iterate();
			 while(it.hasNext())
			 {
				 HashMap<String,Object> idMap=new HashMap<String,Object>();
				 
				 Object[] row = (Object[]) it.next();
				 agentid=(String)row[0];
				 agentinitial=(String)row[1];
				 AgentCompleteId=agentinitial+agentid;
				 idMap.put("agentCompleteId",AgentCompleteId);
				 
				 idArray.add(idMap);
				 
			 }
			 
		 } 	 
		 catch (Exception ex) 
		 {
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
		 return idArray;
	}

	public ArrayList<HashMap<String, Object>> getAgentTransationHistory(
			String toDt, String fromDt, String userId, String service,
			String type, int modcount, int start, int end, String enterAgentId,String DistributorCompleteId,String agentid,
			Map session) {
		Session session1=null;
		Session session2=null;
		ArrayList<HashMap<String,Object>> AccountTransationHistoryArray=new  ArrayList<HashMap<String,Object>>();
		int count=0;
		int count1=0;
		int pagecount=0;
		CallableStatement CS=null;
		ResultSet rs1=null;
		Connection con=null,con2=null;
		try 
		{
			session1=HibernateSession.getSessionFactory().openSession();
			
			session1.beginTransaction();
			con=session1.connection();
			CS =con.prepareCall("{call count_agent_Transaction_History(?,?,?,?,?)  }");
			CS.setString(1,userId);  
			CS.setString(2,agentid);
			CS.setString(3,service); 
			CS.setString(4,fromDt);
			CS.setString(5,toDt);
			ResultSet rs=CS.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
                
              //  count=(int) longcount;
		 
			session.put("count",count);
			
			session1.getTransaction().commit();
			int modulus=count%100;
			if(modulus==0)
			{
				pagecount=count/100;
			}
			else
			{
				int page=count/100;
				pagecount=page+1;
			}
			session.put("pagecount",pagecount);
			 
			if(modcount==0)
			{
				count1=count;
			}
			else
			{
				count1=modcount;
			}				
			
			if(count1>0)
			{
				if(count1<100)
				{					 
					session.put("start",start);
					end=start+count1;
					session.put("end",end);
					
					session2=HibernateSession.getSessionFactory().openSession();
					session2.beginTransaction();
					con2=session2.connection();
					   
					CS=con2.prepareCall("{call agentTransactionHistory(?,?,?,?,?,?,?)}");
					CS.setString(1,userId);
					CS.setString(2,agentid);
					CS.setString(3,fromDt);
					System.out.println("Start :::::: "+fromDt);
					CS.setString(4,toDt);
					System.out.println("Start :::::: "+toDt);
					CS.setString(5,service);
					CS.setInt(6,start);
					CS.setInt(7,end);
					rs1=CS.executeQuery();
					while(rs1.next())
					{
						HashMap<String,Object> mapinfo=new HashMap<String,Object>();
				         
						mapinfo.put("DistributorCompleteId",rs1.getString(1));
						mapinfo.put("AgentCompleteId",rs1.getString(2));
						mapinfo.put("TransactionId",rs1.getString(3));
						mapinfo.put("DateOfTransaction",rs1.getString(4));
						System.out.println("rs1.getString(4)v   :::"+rs1.getString(4));
						mapinfo.put("Service",rs1.getString(5)); 
						mapinfo.put("TransactionAmount",rs1.getString(7)); 
						mapinfo.put("Commission",rs1.getString(8)); 
						mapinfo.put("NetTransactionAmount",rs1.getString(9)); 
						mapinfo.put("Status",rs1.getString(11)); 
						mapinfo.put("serviceon",rs1.getString(12)); 
						AccountTransationHistoryArray.add(mapinfo);				        
					}
					 
					session.put("count1",count1);
					System.out.println("count1---"+count1);
					session2.getTransaction().commit();
				}
				else
				{						     
					System.out.println("in case else----- "); 
					
					session.put("start",start);
					end=start+99;
					System.out.println("end in Dao class is"+end);
					session2.beginTransaction();
					con2=session2.connection();
					
					session.put("end",end);
					CS=con2.prepareCall("{call agentTransactionHistory(?,?,?,?,?,?,?)}");
					CS.setString(1,userId);
					CS.setString(2,agentid);
					System.out.println("Start :::::: "+fromDt);
					CS.setString(3,fromDt);
					CS.setString(4,toDt);
					System.out.println("Start :::::: "+toDt);
					CS.setString(5,service);
					CS.setInt(6,start);				
					CS.setInt(7,end);
					
					rs1=CS.executeQuery();
					while(rs1.next()){
						HashMap<String,Object> mapinfo=new HashMap<String,Object>();
						
						mapinfo.put("DistributorCompleteId",rs1.getString(1));
						mapinfo.put("AgentCompleteId",rs1.getString(2));
						mapinfo.put("TransactionId",rs1.getString(3));
						mapinfo.put("DateOfTransaction",rs1.getString(4));
						System.out.println("rs1.getString(4)v   :::"+rs1.getString(4));
						mapinfo.put("Service",rs1.getString(5)); 
						mapinfo.put("TransactionAmount",rs1.getString(7)); 
						mapinfo.put("Commission",rs1.getString(8)); 
						mapinfo.put("NetTransactionAmount",rs1.getString(9)); 
						mapinfo.put("Status",rs1.getString(11)); 
						mapinfo.put("serviceon",rs1.getString(12)); 
						AccountTransationHistoryArray.add(mapinfo);
						System.out.println("AccountTransationHistoryArray  "+AccountTransationHistoryArray);      
					}
					
					session.put("count1",count1);
					System.out.println("count1---"+count1);
					session2.getTransaction().commit();
				}				
			}		
		} 	 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		}
		finally {
			
			
			try {
				if(con2!=null)
					con2.close();
				if(con!=null)
					con.close();
				session1.close();
				session2.close();
				
			} 
			catch (Exception e) {
					
				e.printStackTrace();
			}
		}
		return AccountTransationHistoryArray;
	}

	public HashMap<String, Object> getAgentVerified(String enterAgentId) {
		Logger logger = Logger.getLogger(AgentTransactionHistoryDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object> idStatus=new HashMap<String,Object>();
		try 
		{		 
			Query IdStatusQuery=session.createQuery("select ADF.agentId  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar,ADF.agentId)=:ReceiverID").setParameter("ReceiverID",enterAgentId);
			log.print("AgentTransactionHistoryDao -getIdStatus- query is "+IdStatusQuery, logger);
			Iterator<?> it=IdStatusQuery.iterate();
			if(it.hasNext())
			{
				Object row = (Object) it.next();
				idStatus.put("agentId",row);
				idStatus.put("status","exist");				 
			}
			else
			{
				idStatus.put("status","Noexist"); 
			}			 
		} 		 
		catch (Exception ex) 
		{
			idStatus.put("status","Noexist");
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
		return idStatus;
	}

	public String downloadDepositReport(String filePath, String toDt,
			String fromDt, String userId, String agentid, String service,String headerName) {
		Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String filePath1=""; 
		Query query=null;
		CallableStatement CS=null;
		ResultSet rs1=null;
		Connection con=null;
		try 
		{
			session.beginTransaction();
			con=session.connection();
			CS =con.prepareCall("{call agent_Transaction_History_download_report(?,?,?,?,?) }");
			CS.setString(1,userId);  
			CS.setString(2,agentid);
			CS.setString(3,service); 
			CS.setString(4,fromDt);
			CS.setString(5,toDt);
			ResultSet rs=CS.executeQuery();
			
			log.print("downloadDepositReport is "+query, logger);
			        
			
			WritableWorkbook workbook;
			int i=0;
			workbook = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);
		  			
			
			sheet.addCell(new Label(0,i,"Distributor Id"));
			sheet.addCell(new Label(1,i,"Agent Id"));
			sheet.addCell(new Label(2,i,"Transaction Id"));
			sheet.addCell(new Label(3,i,"DateOfTransaction"));
			sheet.addCell(new Label(4,i,"Service"));
			sheet.addCell(new Label(5,i,"Requested Amount"));
			sheet.addCell(new Label(6,i,"Commission"));
			sheet.addCell(new Label(7,i,"Transaction Amount"));
			sheet.addCell(new Label(8,i,"Status"));
			sheet.addCell(new Label(9,i,headerName));
		  				
		  			
			i = 1;
			if(i<20000)
			{
				while(rs.next()){ 
		  			  
					sheet.addCell(new Label(0,i,(String)rs.getString(1)));
					sheet.addCell(new Label(1,i,(String)rs.getString(2)));
					sheet.addCell(new Label(2,i,(String)rs.getString(3)));
					sheet.addCell(new Label(3,i,(String)rs.getString(4)));
					sheet.addCell(new Label(4,i,(String)rs.getString(5)));
					sheet.addCell(new Label(5,i,(String)rs.getString(7)));
					sheet.addCell(new Label(6,i,(String)rs.getString(8)));
					sheet.addCell(new Label(7,i,(String)rs.getString(9)));
					sheet.addCell(new Label(8,i,(String)rs.getString(11)));
					sheet.addCell(new Label(9,i,(String)rs.getString(12))); 			 
		  		
		  			
					i++;
				}
			}
			filePath1=filePath;
			workbook.write();
			workbook.close();
                       
			session.getTransaction().commit();			 
		} 		 
		 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				if(con!=null)
					con.close();
				session.close();
				
			} 
			catch (Exception e) {
					
				e.printStackTrace();
			}
		}
		return filePath1;
	}
}
