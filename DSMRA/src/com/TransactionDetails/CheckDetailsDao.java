package com.TransactionDetails;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.PushBalance.PushBalanceDao;
import com.common.HibernateSession;
import com.common.LogWriter;

public class CheckDetailsDao {

	public static HashMap<String,Object> getPopUpDetails(String Service,String transactionStatus,
			String transactionNo,String userId) {

		 Logger logger = Logger.getLogger(CheckDetailsDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;
		 HashMap<String,Object> MapTran=new HashMap<String,Object>();
		 Connection con=null;
		 try 
		    {
			 session=HibernateSession.getSessionFactory().openSession();
			    con=session.connection();
			    CallableStatement CS =con.prepareCall("{ call Distibutor_Transaction_Pop_Up(?,?,?)  }");
			    CS.setString(1,userId); 
		        CS.setString(2,Service); 
		        CS.setString(3,transactionNo);

		        ResultSet rs=CS.executeQuery();
		        while(rs.next())
		        {
		        	
		        	if(Service.equalsIgnoreCase("transfertoagent"))
		        	{
		        		MapTran.put("transactionId",rs.getString("tid"));
			        	MapTran.put("dateOfTransaction",rs.getString("dot"));
			        	MapTran.put("timeOfTransaction",rs.getString("tot"));
			        	MapTran.put("service",rs.getString("ser"));
			        	MapTran.put("transactionAmount",rs.getString("reqamt"));
			        	MapTran.put("commisssion",rs.getBigDecimal("comm"));
			        	MapTran.put("deductedAmount",rs.getString("dedamt"));
			        	MapTran.put("status",rs.getString("status"));
			        	MapTran.put("remark",rs.getString("remark"));
			        	MapTran.put("bankTransactionId",rs.getString("bank_tran_id"));
			        	MapTran.put("bankName",rs.getString("bank_name"));
			    		MapTran.put("modeOfPayment",rs.getString("mode"));
			        	MapTran.put("bankCharges",rs.getString("bank_charge"));
			        	MapTran.put("requestDate",rs.getString("request_date"));	
			        	MapTran.put("userId",rs.getString("userId"));	
			        
		        	}
		        	if(Service.equalsIgnoreCase("accountadjustment"))
		        	{
		            MapTran.put("transactionId",rs.getString("tid"));
		        	MapTran.put("dateOfTransaction",rs.getString("dot"));
		        	MapTran.put("service",rs.getString("ser"));
		        	MapTran.put("timeOfTransaction",rs.getString("tot"));
		        	MapTran.put("transactionAmount",rs.getString("reqamt"));
		        	MapTran.put("actionOnAmount",rs.getString("action"));
		        	MapTran.put("status",rs.getString("status"));
		        	MapTran.put("remark",rs.getString("remark"));		        	
		        	MapTran.put("userId",rs.getString("userId"));
		        	}
		         	
		         	if(Service.equalsIgnoreCase("Account Adjustment-DS"))
		        	{
		            MapTran.put("transactionId",rs.getString("tid"));
		        	MapTran.put("dateOfTransaction",rs.getString("dot"));
		        	MapTran.put("service",rs.getString("ser"));
		        	MapTran.put("timeOfTransaction",rs.getString("tot"));
		        	MapTran.put("transactionAmount",rs.getString("reqamt"));
		        	MapTran.put("actionOnAmount",rs.getString("action"));
		        	MapTran.put("status",rs.getString("status"));
		        	MapTran.put("remark",rs.getString("remark"));		        	
		        	MapTran.put("userId",rs.getString("userId"));
		         	}

		        	
		        	if(Service.equalsIgnoreCase("AgentCreationCharge"))
		        	{
		            MapTran.put("transactionId",rs.getString("tid"));
		        	MapTran.put("dateOfTransaction",rs.getString("dot"));
		        	MapTran.put("service",rs.getString("ser"));
		        	MapTran.put("timeOfTransaction",rs.getString("tot"));
		        	MapTran.put("transactionAmount",rs.getString("reqamt"));
		        	MapTran.put("actionOnAmount",rs.getString("action"));
		        	MapTran.put("status",rs.getString("status"));
		        	MapTran.put("remark",rs.getString("remark"));
		        	MapTran.put("userId",rs.getString("userId"));
		         	}

		        	if((Service.equalsIgnoreCase("transfertodist")) || (Service.equalsIgnoreCase("mdtodist")))
		        	{
		        		MapTran.put("transactionId",rs.getString("tid"));
			        	MapTran.put("dateOfTransaction",rs.getString("dot"));
			        	MapTran.put("timeOfTransaction",rs.getString("tot"));
			        	MapTran.put("service",rs.getString("ser"));
			        	MapTran.put("transactionAmount",rs.getString("reqamt"));
			        	MapTran.put("commisssion",rs.getBigDecimal("comm"));
			        	MapTran.put("deductedAmount",rs.getString("dedamt"));
			        	MapTran.put("status",rs.getString("status"));
			        	MapTran.put("remark",rs.getString("remark"));
			        	MapTran.put("bankTransactionId",rs.getString("bank_tran_id"));
			        	MapTran.put("bankName",rs.getString("bank_name"));
			    		MapTran.put("modeOfPayment",rs.getString("mode"));
			        	MapTran.put("bankCharges",rs.getString("bank_charge"));
			        	MapTran.put("requestDate",rs.getString("request_date"));	
			        	MapTran.put("userId",rs.getString("userId"));	
			        
		        	}
		        	
		        	if(Service.equalsIgnoreCase("AdmintoAgent") || Service.equalsIgnoreCase("Cash Back"))
		        	{
		        		MapTran.put("transactionId",rs.getString("tid"));
		        		MapTran.put("dateOfTransaction",rs.getString("dot"));
		        		MapTran.put("timeOfTransaction",rs.getString("tot"));
		        		MapTran.put("transactionAmount",rs.getString("reqamt"));
		        		MapTran.put("commisssion",rs.getBigDecimal("comm"));
		        		MapTran.put("deductedAmount",rs.getString("dedamt"));
		        		MapTran.put("status",rs.getString("status"));
		        		MapTran.put("remark",rs.getString("remark"));
		        		MapTran.put("Service",rs.getString("Service"));
		        	}
		        	
		        	if(Service.equalsIgnoreCase("RliveMobRechRefunded"))
		        	{
		        		MapTran.put("ecomm",rs.getString("ecomm"));
		        		MapTran.put("total",rs.getString("total"));
		        		MapTran.put("transactionNo",rs.getString("tid"));
		        		MapTran.put("dateOfTransaction",rs.getString("dot"));
		        		MapTran.put("Service",rs.getString("ser"));
		        		MapTran.put("dou",rs.getString("dou"));
		        		MapTran.put("amtbefded",rs.getString("amtbefded"));
		        		MapTran.put("transactionAmount",rs.getString("reqamt"));
		        		MapTran.put("commisssion",rs.getBigDecimal("comm"));
		        		MapTran.put("deductedAmount",rs.getString("dedamt"));
		        		MapTran.put("amtaftded",rs.getString("amtaftded"));
		        		MapTran.put("status",rs.getString("status"));
		        		MapTran.put("transactionId",rs.getString("transid"));
		        	}
		        	if(Service.equalsIgnoreCase("liveDTHRech") )
		        	{
		        		
		        		MapTran.put("mobno",rs.getString("mobno"));
		        		MapTran.put("op",rs.getString("op"));
		        		MapTran.put("provider",rs.getString("provider"));
		        		MapTran.put("tid",rs.getString("tid"));
		        		MapTran.put("dot",rs.getString("dot"));
		        		MapTran.put("tot",rs.getString("tot"));
		        		MapTran.put("ser",rs.getString("ser"));
		        		MapTran.put("reqamt",rs.getString("reqamt"));
		        		MapTran.put("comm",rs.getBigDecimal("comm"));
		        		MapTran.put("dedamt",rs.getString("dedamt"));
		        		MapTran.put("status",rs.getString("status"));
		        		MapTran.put("remark",rs.getString("remark"));
		        		
		        	}
		        	
		        	
		        	if(Service.equalsIgnoreCase("liveDTHRefund") )
		        	{
		        		
		        		MapTran.put("mobno",rs.getString("mobno"));
		        		MapTran.put("op",rs.getString("op"));
		        		MapTran.put("provider",rs.getString("provider"));
		        		MapTran.put("tid",rs.getString("tid"));
		        		MapTran.put("dot",rs.getString("dot"));
		        		MapTran.put("tot",rs.getString("tot"));
		        		MapTran.put("ser",rs.getString("ser"));
		        		MapTran.put("reqamt",rs.getString("reqamt"));
		        		MapTran.put("comm",rs.getBigDecimal("comm"));
		        		MapTran.put("dedamt",rs.getString("dedamt"));
		        		MapTran.put("status",rs.getString("status"));
		        		MapTran.put("remark",rs.getString("remark"));	
		        		
		        	}
		        	
		        	
		        }

		      //  session.getTransaction().commit();

			
			 
			} 
		 
		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
		   }
		 finally {

			 try {
				 session.flush();
				 session.close();
				 con.close();
				} 
				   catch (Exception e) {
					
					e.printStackTrace();
				     }
			     }
		return MapTran;
	}

	public static HashMap<String,Object> getDetailTransferToDistributor(
			String transactionStatus, String transactionNo, String userId) {
		return null;
	}

	

	public static String getAgentid(String agentCompleteId) {
		 Logger logger = Logger.getLogger(PushBalanceDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;

		 String id="";
		 
		 try 
		    {
			 session=HibernateSession.getSessionFactory().openSession();
			 Query IdStatusQuery=session.createQuery("select ADF.agentId  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar,ADF.agentId)=:ReceiverID").setParameter("ReceiverID",agentCompleteId);
             log.print("getAgentid- query is "+IdStatusQuery, logger);
			 Iterator<?> it=IdStatusQuery.iterate();
			 if(it.hasNext())
			 {
				 Object row = (Object) it.next();
				 id=(String)row;
				
			}
			
		} 
		 
		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
		   }
		 finally {
				session.close();
			     }
		return id;
	}

}
