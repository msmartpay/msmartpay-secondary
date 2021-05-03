package com.AgentReport;


import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;

public final class RarAgentReportDao {
	public String getATTReport(String userID,String filePath, String Todate) {		
		Session session = null;		
		String Status = "Norecord";
		Query qry = null;
		String sqlQuery=null;
		try {
			session = HibernateSession.getSessionFactory().openSession();

			String Query = "select count(a.Transaction_Id) from dbo.agent_transaction_details a,agent_details b where a.agent_id in (select agent_id from agent_details where distributor_id ='"+userID+"')and a.agent_id=b.agent_id and a.Date_of_Transaction ='"+Todate+"'";
			//			sqlQuery="select count(a.user_id) from bill_mobile a where Date_of_Booking between '2012-12-10' and '2012-12-30' and a.user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(Query);
			Query qry1 = session.createSQLQuery(Query);
			int count=Integer.parseInt(qry1.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				Status ="NoRecord";
			}else if(count>=20000){
				Status="MoreRecord";
			}else if(count>=1 && count<=20000){


				sqlQuery="select b.agent_initial+convert(varchar,b.agent_id) as AGID,a.Transaction_Id,a.UserType,a.Date_of_Transaction,a.Time_of_Transaction,a.Service,a.Agent_balAmt_b_Ded, a.ReqAmount,a.Commession,a.Service_charge1,a.DeductedAmt,a.Action_on_bal_amt,a.Agent_balAmt_A_Ded,a.Tran_status,a.Updated_date,a.updated_time,a.Remark from dbo.agent_transaction_details a,agent_details b where a.agent_id in (select agent_id from agent_details where distributor_id ='"+userID+"')and a.agent_id=b.agent_id and a.Date_of_Transaction ='"+Todate+"' order by a.Date_of_Transaction desc" ;			
				System.out.println("sqlQuery :: "+sqlQuery);
				qry = session.createSQLQuery(sqlQuery);
				//log.print("Query in  class-SuspectTranDao and method getAllTran--"+qry, logger);
				List list =qry.list();
				Iterator itr = list.iterator();
				Object row[];
				Status = "Success";
				FileWriter writer = new FileWriter(filePath);
				writer.append("Agent Id");
				writer.append(';');
				writer.append("Txn Id");
				writer.append(';');
				writer.append("User Type");
				writer.append(';');
				writer.append("Date of Recharge");
				writer.append(';');
				writer.append("Time of Recharge");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Agent Bal. before Deduction");
				writer.append(';');
				writer.append("Requested Amount");
				writer.append(';');
				writer.append("Commission");
				writer.append(';');
				writer.append("Service Charge1");
				writer.append(';');
				writer.append("Deducted Amount");
				writer.append(';');
				writer.append("Action_on_bal_amt");
				writer.append(';');
				writer.append("Agent Bal. After Deduction");
				writer.append(';');
				writer.append("Tran_status");
				writer.append(';');
				writer.append("Updated_date");
				writer.append(';');
				writer.append("updated_time");
				writer.append(';');
				writer.append("Remark");
				writer.append('\n');

				while (itr.hasNext()) {
					row = (Object[])itr.next();            	


					String Remark =(String)row[16];
					if(Remark==null){
						Remark="";
					}            	
					writer.append(row[0].toString());
					writer.append(';');
					writer.append(","+row[1].toString());
					writer.append(';');
					writer.append(row[2].toString());
					writer.append(';');            	
					writer.append(row[3].toString());
					writer.append(';');
					writer.append(row[4].toString());
					writer.append(';');
					writer.append(row[5].toString());
					writer.append(';');
					writer.append(row[6].toString());
					writer.append(';');
					writer.append(row[7].toString());
					writer.append(';');
					writer.append(row[8].toString());            	
					writer.append(';');
					writer.append(row[9].toString());
					writer.append(';');
					writer.append(row[10].toString());
					writer.append(';');
					writer.append(row[11].toString());
					writer.append(';');
					writer.append(row[12].toString());
					writer.append(';');
					writer.append(row[13].toString());
					writer.append(';');
					writer.append(row[14].toString());
					writer.append(';');
					writer.append(row[15].toString());
					writer.append(';');
					writer.append(Remark);       
					writer.append('\n');
				}
				writer.flush();
				writer.close();

			}else{
				Status="ERROR";
			}
		}
		catch (Exception e) {
			Status = "Norecord";
			e.printStackTrace();
		}finally {
			try {

				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  getSuspectDataReport while closing connection"+ e.toString());
			}
		}
		return Status;		
	}

	public String getLRTReport(String userID,String filePath, String date) {

		Session session = null;

		String Status = "Norecord";
		Query qry = null;
		String sqlQuery=null;
		try {

			session = HibernateSession.getSessionFactory().openSession();		

			String Query = "select count(l.Agent_tran_id) from live_recharge l,agent_details a ,distributor_details d,md_details m " +
					"where a.agent_id=l.user_id and a.distributor_id=d.distributor_id and d.md_id=m.md_id and " +
					"l.user_id in (select agent_id from agent_details where distributor_id ='"+userID+"') and " +
					"date_of_recharge='"+date+"' " ;
			//			sqlQuery="select count(a.user_id) from bill_mobile a where Date_of_Booking between '2012-12-10' and '2012-12-30' and a.user_id in (select agent_id from agent_details where distributor_id='"+userId+"')";
			System.out.println(Query);
			Query qry1 = session.createSQLQuery(Query);
			int count=Integer.parseInt(qry1.uniqueResult().toString());
			System.out.println(count);
			if(count<=0 || count==0){
				Status ="NoRecord";
			}else if(count>=20000){
				Status="MoreRecord";
			}else if(count>=1 && count<=20000){

				sqlQuery="select a.agent_initial+convert(varchar(10),l.user_id) as AgentID," +
						"d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID," +
						"m.md_initial+convert(varchar(10),d.md_id) as MDID,l.Agent_tran_id ,l.mobile_number,l.mobile_operator," +
						"l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.service,l.mob_commission," +
						"l.status from live_recharge l,agent_details a ,distributor_details d,md_details m " +
						"where a.agent_id=l.user_id and a.distributor_id=d.distributor_id and d.md_id=m.md_id and " +
						"l.user_id in (select agent_id from agent_details where distributor_id ='"+userID+"') and " +
						"date_of_recharge='"+date+"' order by date_of_recharge desc" ;
				System.out.println("sqlQuery :: "+sqlQuery);
				qry = session.createSQLQuery(sqlQuery);
				//log.print("Query in  class-SuspectTranDao and method getAllTran--"+qry, logger);
				List list =qry.list();
				Iterator itr = list.iterator();
				Object row[];
				Status = "Success";
				FileWriter writer = new FileWriter(filePath);

				writer.append("Agent Id");
				writer.append(';');
				writer.append("Distributor Id");
				writer.append(';');
				writer.append("Master distributor ID");
				writer.append(';');
				writer.append("User Type");
				writer.append(';');
				writer.append("Agent Txn ID");
				writer.append(';');
				writer.append("Connection Number");
				writer.append(';');
				writer.append("Connection Operator");
				writer.append(';');
				writer.append("Date of Recharge");
				writer.append(';');
				writer.append("Time of Recharge");
				writer.append(';');
				writer.append("Transaction Amount");
				writer.append(';');
				writer.append("Service");
				writer.append(';');
				writer.append("Commission");
				writer.append(';');
				writer.append("Status");
				writer.append('\n');

				while (itr.hasNext()) {
					row = (Object[])itr.next();

					writer.append(row[0].toString());
					writer.append(';');
					writer.append(row[1].toString());
					writer.append(';');
					writer.append(row[2].toString());
					writer.append(';');
					writer.append("Agent");
					writer.append(';');
					writer.append(row[3].toString());
					writer.append(';');
					writer.append(row[4].toString());
					writer.append(';');
					writer.append(row[5].toString());
					writer.append(';');
					writer.append(row[6].toString());
					writer.append(';');				
					writer.append(row[7].toString());				
					writer.append(';');
					writer.append(row[8].toString());
					writer.append(';');
					writer.append(row[9].toString());
					writer.append(';');
					writer.append(row[10].toString());
					writer.append(';');
					writer.append(row[11].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}else{
				Status="ERROR";
			}
		}
		catch (Exception e) {
			Status = "Norecord";
			e.printStackTrace();
			System.out.println("Exception in  getSuspectDataReport >>>>>>>>>>>>>>>>>"+ e.toString());
		}finally {
			try {					
				session.close();				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception in  getSuspectDataReport while closing connection"+ e.toString());
			}
		}
		return Status;			
	}
}
