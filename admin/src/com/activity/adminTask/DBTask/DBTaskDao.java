package com.activity.adminTask.DBTask;

import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

final class DBTaskDao {

	public boolean balanceRequestStatus() {
		Session session=null;
		try
		{
			
			session=HibernateSession.getSessionFactory().openSession();
			int count=(Integer)session.createSQLQuery("select count(agent_id) from agent_journal (nolock) where status in ('Pending','Waiting for payment')").uniqueResult();
			
			
			if(count>0){
				return true;
			}else{
				count=0;
				count=(Integer)session.createSQLQuery("select count(distributor_id) from distributor_journal (nolock) where status in ('Pending','Waiting for payment')").uniqueResult();
				if(count>0){
					return true;
				}
			}
			return false;

		}catch (Exception ex) {
			System.out.println("Exception in DBTaskDao class method (updateTicketDetails)");
			ex.printStackTrace();
			return false;
		}
		finally {
			try {
				if (session!=null)
					session.close();
			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao class method (updateTicketDetails) while closing connection");
				e.printStackTrace();
				
			}
		}
	}
	
	public boolean ticketRequestStatus() {
		Session session=null;
		try
		{
			
			session=HibernateSession.getSessionFactory().openSession();
			int count=(Integer)session.createSQLQuery("select count(Ticket_id) from Client_ticket_details where status='Opened'").uniqueResult();
			
			
			if(count>0){
				return true;
			}
			return false;

		}catch (Exception ex) {
			System.out.println("Exception in DBTaskDao class method (updateTicketDetails)");
			ex.printStackTrace();
			return false;
		}
		finally {
			try {
				if (session!=null)
					session.close();
			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao class method (updateTicketDetails) while closing connection");
				e.printStackTrace();
				
			}
		}
	}
	
	public String getLRTPendingTran(String filePath, String fromDate,String toDate) 
	{
		String sql="";
		String status="Norecord";
		Session session = null;
		Query query=null;
		try{
			session=HibernateSession.getSessionFactory().openSession();
			sql="select count(user_id) from live_recharge where date_of_recharge between '"+fromDate+"' and '"+toDate+"' and status='Pending'";
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select user_id,mobile_operator,mobile_number,amount,date_of_recharge,mob_commission,tran_id,service,status	,response_result,response_code,Dist_Comm,RequestTime,ResponseTime,date_time,USessionID,ApiProvider,Val_CheckMno,Val_Payment,Val_Paymentstatus,DT_CheckMno,DT_Payment,DT_Paymentstatus,Circle,Authcode,Agent_tran_id,Cust_MobileNo,suspect_flag,suspect_remerk,suspect_status,suspect_schdular_flag,date_of_suspect from live_recharge where date_of_recharge between '"+fromDate+"' and '"+toDate+"' and status='Pending'";

				//System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("User ID");
				writer.append('\t');
				writer.append("mobile_operator");
				writer.append('\t');
				writer.append("mobile_number");
				writer.append('\t');
				writer.append("amount");
				writer.append('\t');
				writer.append("date_of_recharge");
				writer.append('\t');
				writer.append("mob_commission");
				writer.append('\t');
				writer.append("tran_id");
				writer.append('\t');
				writer.append("service");
				writer.append('\t');
				writer.append("status");
				writer.append('\t');
				writer.append("response_result");
				writer.append('\t');
				writer.append("response_code");
				writer.append('\t');
				writer.append("Dist_Comm");
				writer.append('\t');
				writer.append("RequestTime");
				writer.append('\t');
				writer.append("ResponseTime");
				writer.append('\t');
				writer.append("date_time");
				writer.append('\t');
				writer.append("USessionID");
				writer.append('\t');
				writer.append("ApiProvider");
				writer.append('\t');
				writer.append("Val_CheckMno");
				writer.append('\t');
				writer.append("Val_Payment");
				writer.append('\t');
				writer.append("Val_Paymentstatus");
				writer.append('\t');
				writer.append("DT_CheckMno");
				writer.append('\t');
				writer.append("DT_Payment");
				writer.append('\t');
				writer.append("DT_Paymentstatus");
				writer.append('\t');
				writer.append("Circle");
				writer.append('\t');
				writer.append("Authcode");
				writer.append('\t');
				writer.append("Agent_tran_id");
				writer.append('\t');
				writer.append("Cust_MobileNo");
				writer.append('\t');
				writer.append("suspect_flag");
				writer.append('\t');
				writer.append("suspect_remerk");
				writer.append('\t');
				writer.append("suspect_status");
				writer.append('\t');
				writer.append("suspect_schdular_flag");
				writer.append('\t');
				writer.append("date_of_suspect");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\t');
					writer.append(row[25]==null ? "Null" :row[25].toString());
					writer.append('\t');
					writer.append(row[26]==null ? "Null" :row[26].toString());
					writer.append('\t');
					writer.append(row[27]==null ? "Null" :row[27].toString());
					writer.append('\t');
					writer.append(row[28]==null ? "Null" :row[28].toString());
					writer.append('\t');
					writer.append(row[29]==null ? "Null" :row[29].toString());
					writer.append('\t');
					writer.append(row[30]==null ? "Null" :row[30].toString());
					writer.append('\t');
					writer.append(row[31]==null ? "Null" :row[31].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getLRTPendingTran");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getLRTPendingTran ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	public String getATTPendingTran(String filePath, String fromDate,String toDate) 
	{
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Agent_id) from agent_transaction_details where date_of_transaction between '"+fromDate+"' and '"+toDate+"' and Tran_status='Pending'";
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select Id_No,Transaction_Id,UserType,Agent_id,distributor_id,Transaction_No,Date_of_Transaction,Time_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession,Service_charge1,Service_charge2,Other_charge,extra_commission,DeductedAmt,Action_on_bal_amt,Agent_balAmt_A_Ded,Tran_status,Updated_date,updated_time,Agent_F_balAmt,IpAddress,UpdIpAddress,Bal_amt_b_Refund,refunded_amount,Bal_Amount_A_upd,Remark,Tds_Amount,External_remark from agent_transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Tran_status='Pending'";

				//System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("Id_No");
				writer.append('\t');
				writer.append("Transaction_Id");
				writer.append('\t');
				writer.append("UserType");
				writer.append('\t');
				writer.append("Agent_id");
				writer.append('\t');
				writer.append("distributor_id");
				writer.append('\t');
				writer.append("Transaction_No");
				writer.append('\t');
				writer.append("Date_of_Transaction");
				writer.append('\t');
				writer.append("Time_of_Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Agent_balAmt_b_Ded");
				writer.append('\t');
				writer.append("ReqAmount");
				writer.append('\t');
				writer.append("Commession");
				writer.append('\t');
				writer.append("Service_charge1");
				writer.append('\t');
				writer.append("Service_charge2");
				writer.append('\t');
				writer.append("Other_charge");
				writer.append('\t');
				writer.append("extra_commission");
				writer.append('\t');
				writer.append("DeductedAmt");
				writer.append('\t');
				writer.append("Action_on_bal_amt");
				writer.append('\t');
				writer.append("Agent_balAmt_A_Ded");
				writer.append('\t');
				writer.append("Tran_status");
				writer.append('\t');
				writer.append("Updated_date");
				writer.append('\t');
				writer.append("updated_time");
				writer.append('\t');
				writer.append("Agent_F_balAmt");
				writer.append('\t');
				writer.append("IpAddress");
				writer.append('\t');
				writer.append("UpdIpAddress");
				writer.append('\t');
				writer.append("Bal_amt_b_Refund");
				writer.append('\t');
				writer.append("refunded_amount");
				writer.append('\t');
				writer.append("Bal_Amount_A_upd");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\t');
				writer.append("Tds_Amount");
				writer.append('\t');
				writer.append("External_remark");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\t');
					writer.append(row[25]==null ? "Null" :row[25].toString());
					writer.append('\t');
					writer.append(row[26]==null ? "Null" :row[26].toString());
					writer.append('\t');
					writer.append(row[27]==null ? "Null" :row[27].toString());
					writer.append('\t');
					writer.append(row[28]==null ? "Null" :row[28].toString());
					writer.append('\t');
					writer.append(row[29]==null ? "Null" :row[29].toString());
					writer.append('\t');
					writer.append(row[30]==null ? "Null" :row[30].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getATTPendingTran");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getATTPendingTran ");
				System.out.println(e.toString());
			}
		}
		//System.out.println(status);
		return status;
	}

	public String getLRTPendingTranAPI(String filePath, String fromDate,String toDate) {

		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Corporate_Agent_Id) from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_RechMobileDth_Details where convert(varchar(10),Date_of_Recharge,120) between '"+fromDate+"' and '"+toDate+"' and Status='Pending'";
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select ID_No,Corporate_Agent_Id,Transaction_id,Refrence_Id,Operator_txn_Id,Vendor_txn_Id,MobileDth_Operator,MobileDth_Circle,MobileDth_No,Date_of_Recharge,Recharge_Amount,Commission,Service,Status,Response_Code,Response_Result,Request_Time,Response_Time,API_Privider,API_Provider_Comm,Sub_Agent_Mobile_No from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_RechMobileDth_Details where convert(varchar(10),Date_of_Recharge,120) between '"+fromDate+"' and '"+toDate+"' and Status='Pending'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("ID_No");
				writer.append('\t');
				writer.append("Corporate_Agent_Id");
				writer.append('\t');
				writer.append("Transaction_id");
				writer.append('\t');
				writer.append("Refrence_Id");
				writer.append('\t');
				writer.append("Operator_txn_Id");
				writer.append('\t');
				writer.append("Vendor_txn_Id");
				writer.append('\t');
				writer.append("MobileDth_Operator");
				writer.append('\t');
				writer.append("MobileDth_Circle");
				writer.append('\t');
				writer.append("MobileDth_No");
				writer.append('\t');
				writer.append("Date_of_Recharge");
				writer.append('\t');
				writer.append("Recharge_Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Status");
				writer.append('\t');
				writer.append("Response_Code");
				writer.append('\t');
				writer.append("Response_Result");
				writer.append('\t');
				writer.append("Request_Time");
				writer.append('\t');
				writer.append("Response_Time");
				writer.append('\t');
				writer.append("API_Privider");
				writer.append('\t');
				writer.append("API_Provider_Comm");
				writer.append('\t');
				writer.append("Sub_Agent_Mobile_No");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getLRTPendingTranAPI");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getLRTPendingTranAPI ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	public String getATTPendingTranAPI(String filePath, String fromDate,String toDate) {

		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Corporate_Agent_Id) from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Tran_Status='Pending'";
			//			System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select ID_No,Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,Date_of_Transaction,Time_of_Transaction,Service,Tran_Amount,Commission,Service_Charges,Bank_Charges,Other_Charges,Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_Date,Updated_Time,Updated_User,Updated_IP,Remark from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Tran_Status='Pending'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("ID_No");
				writer.append('\t');
				writer.append("Corporate_Transaction_Id");
				writer.append('\t');
				writer.append("Refrence_Id");
				writer.append('\t');
				writer.append("Corporate_Agent_Id");
				writer.append('\t');
				writer.append("Sub_Agent_Id");
				writer.append('\t');
				writer.append("Date_of_Transaction");
				writer.append('\t');
				writer.append("Time_of_Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Tran_Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service_Charges");
				writer.append('\t');
				writer.append("Bank_Charges");
				writer.append('\t');
				writer.append("Other_Charges");
				writer.append('\t');
				writer.append("Net_Tran_Amt");
				writer.append('\t');
				writer.append("Action_On_Bal_Amt");
				writer.append('\t');
				writer.append("Previous_Bal_Amt");
				writer.append('\t');
				writer.append("Updated_Bal_Amt");
				writer.append('\t');
				writer.append("Tran_Status");
				writer.append('\t');
				writer.append("Final_Bal_Amt");
				writer.append('\t');
				writer.append("Tran_IP");
				writer.append('\t');
				writer.append("Updated_Date");
				writer.append('\t');
				writer.append("Updated_Time");
				writer.append('\t');
				writer.append("Updated_User");
				writer.append('\t');
				writer.append("Updated_IP");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getATTPendingTranAPI");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getATTPendingTranAPI ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	final String getRemarkLRTTran(String filePath, String fromDate,String toDate, String remark) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(user_id) from live_recharge where date_of_recharge between '"+fromDate+"' and '"+toDate+"' and ApiProvider like '%"+remark+"%'";
			//			System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select user_id,mobile_operator,mobile_number,amount,date_of_recharge,mob_commission,tran_id,service,status	,response_result,response_code,Dist_Comm,RequestTime,ResponseTime,date_time,USessionID,ApiProvider,Val_CheckMno,Val_Payment,Val_Paymentstatus,DT_CheckMno,DT_Payment,DT_Paymentstatus,Circle,Authcode,Agent_tran_id,Cust_MobileNo,suspect_flag,suspect_remerk,suspect_status,suspect_schdular_flag,date_of_suspect from live_recharge where date_of_recharge between '"+fromDate+"' and '"+toDate+"' and ApiProvider like '%"+remark+"%'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("User ID");
				writer.append('\t');
				writer.append("mobile_operator");
				writer.append('\t');
				writer.append("mobile_number");
				writer.append('\t');
				writer.append("amount");
				writer.append('\t');
				writer.append("date_of_recharge");
				writer.append('\t');
				writer.append("mob_commission");
				writer.append('\t');
				writer.append("tran_id");
				writer.append('\t');
				writer.append("service");
				writer.append('\t');
				writer.append("status");
				writer.append('\t');
				writer.append("response_result");
				writer.append('\t');
				writer.append("response_code");
				writer.append('\t');
				writer.append("Dist_Comm");
				writer.append('\t');
				writer.append("RequestTime");
				writer.append('\t');
				writer.append("ResponseTime");
				writer.append('\t');
				writer.append("date_time");
				writer.append('\t');
				writer.append("USessionID");
				writer.append('\t');
				writer.append("ApiProvider");
				writer.append('\t');
				writer.append("Val_CheckMno");
				writer.append('\t');
				writer.append("Val_Payment");
				writer.append('\t');
				writer.append("Val_Paymentstatus");
				writer.append('\t');
				writer.append("DT_CheckMno");
				writer.append('\t');
				writer.append("DT_Payment");
				writer.append('\t');
				writer.append("DT_Paymentstatus");
				writer.append('\t');
				writer.append("Circle");
				writer.append('\t');
				writer.append("Authcode");
				writer.append('\t');
				writer.append("Agent_tran_id");
				writer.append('\t');
				writer.append("Cust_MobileNo");
				writer.append('\t');
				writer.append("suspect_flag");
				writer.append('\t');
				writer.append("suspect_remerk");
				writer.append('\t');
				writer.append("suspect_status");
				writer.append('\t');
				writer.append("suspect_schdular_flag");
				writer.append('\t');
				writer.append("date_of_suspect");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\t');
					writer.append(row[25]==null ? "Null" :row[25].toString());
					writer.append('\t');
					writer.append(row[26]==null ? "Null" :row[26].toString());
					writer.append('\t');
					writer.append(row[27]==null ? "Null" :row[27].toString());
					writer.append('\t');
					writer.append(row[28]==null ? "Null" :row[28].toString());
					writer.append('\t');
					writer.append(row[29]==null ? "Null" :row[29].toString());
					writer.append('\t');
					writer.append(row[30]==null ? "Null" :row[30].toString());
					writer.append('\t');
					writer.append(row[31]==null ? "Null" :row[31].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getLRTPendingTran");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getLRTPendingTran ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	final String getRemarkATTTran(String filePath, String fromDate,String toDate,String remark) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Agent_id) from agent_transaction_details where date_of_transaction between '"+fromDate+"' and '"+toDate+"' and Remark like '%"+remark+"%'";
			//			System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select Transaction_Id,UserType,Agent_id,distributor_id,Transaction_No,Date_of_Transaction,Time_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession,Service_charge1,Service_charge2,Other_charge,extra_commission,DeductedAmt,Action_on_bal_amt,Agent_balAmt_A_Ded,Tran_status,Updated_date,updated_time,Agent_F_balAmt,IpAddress,UpdIpAddress,Bal_amt_b_Refund,refunded_amount,Bal_Amount_A_upd,Remark,Tds_Amount,External_remark from agent_transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Remark like '%"+remark+"%'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("Id_No");
				writer.append('\t');
				writer.append("Transaction_Id");
				writer.append('\t');
				writer.append("UserType");
				writer.append('\t');
				writer.append("Agent_id");
				writer.append('\t');
				writer.append("distributor_id");
				writer.append('\t');
				writer.append("Transaction_No");
				writer.append('\t');
				writer.append("Date_of_Transaction");
				writer.append('\t');
				writer.append("Time_of_Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Agent_balAmt_b_Ded");
				writer.append('\t');
				writer.append("ReqAmount");
				writer.append('\t');
				writer.append("Commession");
				writer.append('\t');
				writer.append("Service_charge1");
				writer.append('\t');
				writer.append("Service_charge2");
				writer.append('\t');
				writer.append("Other_charge");
				writer.append('\t');
				writer.append("extra_commission");
				writer.append('\t');
				writer.append("DeductedAmt");
				writer.append('\t');
				writer.append("Action_on_bal_amt");
				writer.append('\t');
				writer.append("Agent_balAmt_A_Ded");
				writer.append('\t');
				writer.append("Tran_status");
				writer.append('\t');
				writer.append("Updated_date");
				writer.append('\t');
				writer.append("updated_time");
				writer.append('\t');
				writer.append("Agent_F_balAmt");
				writer.append('\t');
				writer.append("IpAddress");
				writer.append('\t');
				writer.append("UpdIpAddress");
				writer.append('\t');
				writer.append("Bal_amt_b_Refund");
				writer.append('\t');
				writer.append("refunded_amount");
				writer.append('\t');
				writer.append("Bal_Amount_A_upd");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\t');
				writer.append("Tds_Amount");
				writer.append('\t');
				writer.append("External_remark");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\t');
					writer.append(row[25]==null ? "Null" :row[25].toString());
					writer.append('\t');
					writer.append(row[26]==null ? "Null" :row[26].toString());
					writer.append('\t');
					writer.append(row[27]==null ? "Null" :row[27].toString());
					writer.append('\t');
					writer.append(row[28]==null ? "Null" :row[28].toString());
					writer.append('\t');
					writer.append(row[29]==null ? "Null" :row[29].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getATTPendingTran");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getATTPendingTran while closing connection");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	final String getRemarkLRTTranAPI(String filePath, String fromDate,String toDate,String remark) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Corporate_Agent_Id) from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_RechMobileDth_Details where convert(varchar(10),Date_of_Recharge,120) between '"+fromDate+"' and '"+toDate+"' and API_Privider like '%"+remark+"%'";
			//			System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select ID_No,Corporate_Agent_Id,Transaction_id,Refrence_Id,Operator_txn_Id,Vendor_txn_Id,MobileDth_Operator,MobileDth_Circle,MobileDth_No,Date_of_Recharge,Recharge_Amount,Commission,Service,Status,Response_Code,Response_Result,Request_Time,Response_Time,API_Privider,API_Provider_Comm,Sub_Agent_Mobile_No from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_RechMobileDth_Details where convert(varchar(10),Date_of_Recharge,120) between '"+fromDate+"' and '"+toDate+"' and API_Privider like '%"+remark+"%'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("ID_No");
				writer.append('\t');
				writer.append("Corporate_Agent_Id");
				writer.append('\t');
				writer.append("Transaction_id");
				writer.append('\t');
				writer.append("Refrence_Id");
				writer.append('\t');
				writer.append("Operator_txn_Id");
				writer.append('\t');
				writer.append("Vendor_txn_Id");
				writer.append('\t');
				writer.append("MobileDth_Operator");
				writer.append('\t');
				writer.append("MobileDth_Circle");
				writer.append('\t');
				writer.append("MobileDth_No");
				writer.append('\t');
				writer.append("Date_of_Recharge");
				writer.append('\t');
				writer.append("Recharge_Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Status");
				writer.append('\t');
				writer.append("Response_Code");
				writer.append('\t');
				writer.append("Response_Result");
				writer.append('\t');
				writer.append("Request_Time");
				writer.append('\t');
				writer.append("Response_Time");
				writer.append('\t');
				writer.append("API_Privider");
				writer.append('\t');
				writer.append("API_Provider_Comm");
				writer.append('\t');
				writer.append("Sub_Agent_Mobile_No");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getLRTPendingTranAPI");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getLRTPendingTranAPI ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	final String getRemarkATTTranAPI(String filePath, String fromDate,String toDate,String remark) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			sql="select count(Corporate_Agent_Id) from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Remark like '%"+remark+"%'";
			//			System.out.println(sql);
			query=session.createSQLQuery(sql);
			int record=Integer.parseInt(query.uniqueResult().toString());
			//			System.out.println(record);
			if(record<=0){
				status="Norecord";
			}else if(record>=25000){
				status="MoreRecord";
			}else if (record>0 && record<25000){

				sql="select ID_No,Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,Date_of_Transaction,Time_of_Transaction,Service,Tran_Amount,Commission,Service_Charges,Bank_Charges,Other_Charges,Net_Tran_Amt,Action_On_Bal_Amt,Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_Date,Updated_Time,Updated_User,Updated_IP,Remark from OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details where Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and Remark like '%"+remark+"%'";
				//				System.out.println(sql);
				query=session.createSQLQuery(sql);
				List list=query.list();
				Iterator itr=list.iterator();
				FileWriter writer = new FileWriter(filePath);
				writer.append("ID_No");
				writer.append('\t');
				writer.append("Corporate_Transaction_Id");
				writer.append('\t');
				writer.append("Refrence_Id");
				writer.append('\t');
				writer.append("Corporate_Agent_Id");
				writer.append('\t');
				writer.append("Sub_Agent_Id");
				writer.append('\t');
				writer.append("Date_of_Transaction");
				writer.append('\t');
				writer.append("Time_of_Transaction");
				writer.append('\t');
				writer.append("Service");
				writer.append('\t');
				writer.append("Tran_Amount");
				writer.append('\t');
				writer.append("Commission");
				writer.append('\t');
				writer.append("Service_Charges");
				writer.append('\t');
				writer.append("Bank_Charges");
				writer.append('\t');
				writer.append("Other_Charges");
				writer.append('\t');
				writer.append("Net_Tran_Amt");
				writer.append('\t');
				writer.append("Action_On_Bal_Amt");
				writer.append('\t');
				writer.append("Previous_Bal_Amt");
				writer.append('\t');
				writer.append("Updated_Bal_Amt");
				writer.append('\t');
				writer.append("Tran_Status");
				writer.append('\t');
				writer.append("Final_Bal_Amt");
				writer.append('\t');
				writer.append("Tran_IP");
				writer.append('\t');
				writer.append("Updated_Date");
				writer.append('\t');
				writer.append("Updated_Time");
				writer.append('\t');
				writer.append("Updated_User");
				writer.append('\t');
				writer.append("Updated_IP");
				writer.append('\t');
				writer.append("Remark");
				writer.append('\n');
				status="Success";
				while(itr.hasNext()){

					Object[] row=(Object[])itr.next();
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\t');
					writer.append(row[7]==null ? "Null" :row[7].toString());
					writer.append('\t');
					writer.append(row[8]==null ? "Null" :row[8].toString());
					writer.append('\t');
					writer.append(row[9]==null ? "Null" :row[9].toString());
					writer.append('\t');
					writer.append(row[10]==null ? "Null" :row[10].toString());
					writer.append('\t');
					writer.append(row[11]==null ? "Null" :row[11].toString());
					writer.append('\t');
					writer.append(row[12]==null ? "Null" :row[12].toString());
					writer.append('\t');
					writer.append(row[13]==null ? "Null" :row[13].toString());
					writer.append('\t');
					writer.append(row[14]==null ? "Null" :row[14].toString());
					writer.append('\t');
					writer.append(row[15]==null ? "Null" :row[15].toString());
					writer.append('\t');
					writer.append(row[16]==null ? "Null" :row[16].toString());
					writer.append('\t');
					writer.append(row[17]==null ? "Null" :row[17].toString());
					writer.append('\t');
					writer.append(row[18]==null ? "Null" :row[18].toString());
					writer.append('\t');
					writer.append(row[19]==null ? "Null" :row[19].toString());
					writer.append('\t');
					writer.append(row[20]==null ? "Null" :row[20].toString());
					writer.append('\t');
					writer.append(row[21]==null ? "Null" :row[21].toString());
					writer.append('\t');
					writer.append(row[22]==null ? "Null" :row[22].toString());
					writer.append('\t');
					writer.append(row[23]==null ? "Null" :row[23].toString());
					writer.append('\t');
					writer.append(row[24]==null ? "Null" :row[24].toString());
					writer.append('\n');
				}
				writer.flush();
				writer.close();
			}
		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getATTPendingTranAPI");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getATTPendingTranAPI ");
				System.out.println(e.toString());
			}

		}
		//System.out.println(status);
		return status;
	}

	public String executeCreditQuery(String dbType, String typeOfUser,String userID, String amount, String remark, String ipAdd) {
		Session session = null;
		Transaction txn = null;
		String status="ERROR";
		try {

			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			Connection con = session.connection();

			CallableStatement cstmt = null;
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH); // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			if(dbType.equalsIgnoreCase("tep")){
				if(typeOfUser.equalsIgnoreCase("agent")){
					cstmt = con.prepareCall("{call CommissionServiceAG(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("ds")){
					cstmt = con.prepareCall("{call CommissionService(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("mds")){
					cstmt = con.prepareCall("{call CommissionServiceMD(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("admin")){
					cstmt = con.prepareCall("{call CommissionServicePORTAL(?,?,?,?,?,?,?,?)}");
				}
			}else if(dbType.equalsIgnoreCase("api")){
				cstmt = con.prepareCall("{call OnlineRechAPI_Live_db.dbo.CommissionServiceAPI(?,?,?,?,?,?,?)}");
			}
			if(dbType.equalsIgnoreCase("tep")){
				if(typeOfUser.equalsIgnoreCase("agent")||typeOfUser.equalsIgnoreCase("ds")|| typeOfUser.equalsIgnoreCase("mds")){
					cstmt.setString(1, amount);
					cstmt.setString(2, remark);
					cstmt.setString(3, remark);
					cstmt.setString(4, userID);
					cstmt.setString(5, String.valueOf(year));
					cstmt.setString(6, String.valueOf(month));
					cstmt.setString(7, String.valueOf(day));
					cstmt.setString(8, ipAdd);
					cstmt.setString(9, typeOfUser);
				}else if(typeOfUser.equalsIgnoreCase("admin")){
					cstmt.setString(1, amount);
					cstmt.setString(2, remark);
					cstmt.setString(3, userID);
					cstmt.setString(4, String.valueOf(year));
					cstmt.setString(5, String.valueOf(month));
					cstmt.setString(6, String.valueOf(day));
					cstmt.setString(7, ipAdd);
					cstmt.setString(8, typeOfUser);
				}
			}else if(dbType.equalsIgnoreCase("api")){ 

				cstmt.setString(1, amount);
				cstmt.setString(2, remark);
				cstmt.setString(3, userID);
				cstmt.setString(4, String.valueOf(year));
				cstmt.setString(5, String.valueOf(month));
				cstmt.setString(6, String.valueOf(day));
				cstmt.setString(7, ipAdd);
			}
			//System.out.println("we have called procedure");
			ResultSet rs = cstmt.executeQuery();

			while(rs.next()){
				status=rs.getString(1);
			}
			//System.out.println(status);
			txn.commit();
		} catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in DBTaskDao method is executeCreditQuery");
			System.out.println(e.toString());
			if (txn != null) {
				try {
					txn.rollback();
				} catch (Exception ex) {
					System.out.println(e.toString());
				}

			}
		} finally {
			try {
				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao method is executeCreditQuery");
				System.out.println(e.toString());
			}

		}
		//System.out.println("Status is "+status);
		return status;
	}

	public String executeDebitQuery(String dbType, String typeOfUser,String userID, String amount, String remark, String ipAdd) {
		Session session = null;
		Transaction txn = null;
		String status="ERROR";
		try {

			session = HibernateSession.getSessionFactory().openSession();

			txn = session.beginTransaction();

			Connection con = session.connection();

			CallableStatement cstmt = null;
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH); // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			if(dbType.equalsIgnoreCase("tep")){
				if(typeOfUser.equalsIgnoreCase("agent")){
					cstmt = con.prepareCall("{call SafeCustodyAgent(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("ds")){
					cstmt = con.prepareCall("{call SafeCustodyDS(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("mds")){
					cstmt = con.prepareCall("{call SafeCustodyMD(?,?,?,?,?,?,?,?,?)}");
				}else if(typeOfUser.equalsIgnoreCase("admin")){
					cstmt = con.prepareCall("{call SafeCustodyPORTAL(?,?,?,?,?,?,?,?)}");
				}
			}else if(dbType.equalsIgnoreCase("api")){
				cstmt = con.prepareCall("{call OnlineRechAPI_Live_db.dbo.SafeCustodyAPI(?,?,?,?,?,?,?)}");
			}

			if(dbType.equalsIgnoreCase("tep")){
				if(typeOfUser.equalsIgnoreCase("agent")||typeOfUser.equalsIgnoreCase("ds")|| typeOfUser.equalsIgnoreCase("mds")){
					cstmt.setString(1, amount);
					cstmt.setString(2, remark);
					cstmt.setString(3, remark);
					cstmt.setString(4, userID);
					cstmt.setString(5, String.valueOf(year));
					cstmt.setString(6, String.valueOf(month));
					cstmt.setString(7, String.valueOf(day));
					cstmt.setString(8, ipAdd);
					cstmt.setString(9, typeOfUser);
				}else if(typeOfUser.equalsIgnoreCase("admin")){
					cstmt.setString(1, amount);
					cstmt.setString(2, remark);
					cstmt.setString(3, userID);
					cstmt.setString(4, String.valueOf(year));
					cstmt.setString(5, String.valueOf(month));
					cstmt.setString(6, String.valueOf(day));
					cstmt.setString(7, ipAdd);
					cstmt.setString(8, typeOfUser);
				}
			}
			else if(dbType.equalsIgnoreCase("api")){ 

				cstmt.setString(1, amount);
				cstmt.setString(2, remark);
				cstmt.setString(3, userID);
				cstmt.setString(4, String.valueOf(year));
				cstmt.setString(5, String.valueOf(month));
				cstmt.setString(6, String.valueOf(day));
				cstmt.setString(7, ipAdd);
			}
			//System.out.println("we have called procedure");
			ResultSet rs = cstmt.executeQuery();

			while(rs.next()){
				status=rs.getString(1);
			}
			//System.out.println(status);
			txn.commit();
		} catch (Exception e) {
			status = "ERROR";
			System.out.println("Exception in DBTaskDao method is executeDebitQuery");
			System.out.println(e.toString());
			if (txn != null) {
				try {
					txn.rollback();
				} catch (Exception ex) {
					System.out.println("Exception in DBTaskDao method is executeDebitQuery");
					System.out.println(ex.toString());
				}

			}
		} finally {
			try {
				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao method is executeDebitQuery");
				System.out.println(e.toString());
			}

		}
		//System.out.println("Status is "+status);
		return status;
	}

	public String getBalanceOfUser(String dbType, String parentUser,
			String parentID, String child, String filePath) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			if(dbType.equalsIgnoreCase("tep")){
				if(parentUser.equalsIgnoreCase("superadmin")){
					if(child.equalsIgnoreCase("admin")){
						sql="select Portal_id,Total_cash,Used_cash,Total_commision,Cutoff_amount,Total_balance_Amount,Available_balance_amount from Admin_user_amount_details";
					}
					else if(child.equalsIgnoreCase("mds")){
						sql="select md.md_initial+convert(varchar(10),ma.MD_id) as MDID,ma.Total_Cash,ma.Used_Cash,ma.Total_Commision,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount from dbo.MD_Amount ma,md_details md where ma.MD_id=md.Md_id ";
					}else if(child.equalsIgnoreCase("ds")){
						sql="select dd.distributor_initial+convert(varchar(10),da.distributor_id) as DSID,da.TotCash,da.usedcash,da.commision_Block,da.cutoff_amount,(da.TotCash-da.usedcash+da.cutoff_amount) as tot,(da.TotCash-da.usedcash) as avail from distributor_amount da,distributor_details dd where da.distributor_id=dd.distributor_id ";
					}else if(child.equalsIgnoreCase("agent")){
						sql="select md.MD_initial+convert(varchar(10),md.MD_id) as MDSId,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSId,ad.agent_initial+convert(varchar(10),aa.agent_id) as AGID,aa.TotCash,aa.usedcash,aa.commision,aa.cutoff_amount,(aa.TotCash-aa.usedcash+aa.cutoff_amount) as tot,(aa.TotCash-aa.usedcash) as avail from agent_amount aa,agent_details ad,distributor_details dd,MD_Details md  where aa.agent_id=ad.agent_id and dd.distributor_id=ad.distributor_id and dd.MD_id=md.MD_id";
					}else{
						status="wrongInput";
					}
				}
				else if(parentUser.equalsIgnoreCase("admin")){
					if(child.equalsIgnoreCase("mds")){
						sql="select md.md_initial+convert(varchar(10),ma.MD_id) as MDID,ma.Total_Cash,ma.Used_Cash,ma.Total_Commision,ma.Cutoff_Amount,ma.Total_Bal_Amount,ma.Available_Bal_Amount from dbo.MD_Amount ma,md_details md where ma.md_id=md.md_id and ma.md_id in (select md_id from md_details where client_id in (select portal_id from admin_user_details where user_id='"+parentID+"')) ";
					}
					else if(child.equalsIgnoreCase("ds")){
						sql="select dd.distributor_initial+convert(varchar(10),da.distributor_id) as DSID,da.TotCash,da.usedcash,da.commision_Block,da.cutoff_amount,(da.TotCash-da.usedcash+da.cutoff_amount) as tot,(da.TotCash-da.usedcash) as avail from distributor_amount da,distributor_details dd where da.distributor_id=dd.distributor_id and da.distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where client_id in (select portal_id from admin_user_details where user_id='"+parentID+"')) )";
					}else if(child.equalsIgnoreCase("agent")){
						sql="select md.MD_initial+convert(varchar(10),md.MD_id) as MDSId,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSId,ad.agent_initial+convert(varchar(10),aa.agent_id) as AGID,aa.TotCash,aa.usedcash,aa.commision,aa.cutoff_amount,(aa.TotCash-aa.usedcash+aa.cutoff_amount) as tot,(aa.TotCash-aa.usedcash) as avail from agent_amount aa,agent_details ad,distributor_details dd,MD_Details md  where aa.agent_id=ad.agent_id and dd.distributor_id=ad.distributor_id and dd.MD_id=md.MD_id and ad.agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where client_id in (select portal_id from admin_user_details where user_id='"+parentID+"'))))";
					}else{
						status="wrongInput";
					}
				}else if(parentUser.equalsIgnoreCase("mds")){
					if(child.equalsIgnoreCase("ds")){
						sql="select dd.distributor_initial+convert(varchar(10),da.distributor_id) as DSID,da.TotCash,da.usedcash,da.commision_Block,da.cutoff_amount,(da.TotCash-da.usedcash+da.cutoff_amount) as tot,(da.TotCash-da.usedcash) as avail from distributor_amount da,distributor_details dd where da.distributor_id=dd.distributor_id and da.distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+parentID+"') )";
					}else if(child.equalsIgnoreCase("agent")){
						sql="select md.MD_initial+convert(varchar(10),md.MD_id) as MDSId,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSId,ad.agent_initial+convert(varchar(10),aa.agent_id) as AGID,aa.TotCash,aa.usedcash,aa.commision,aa.cutoff_amount,(aa.TotCash-aa.usedcash+aa.cutoff_amount) as tot,(aa.TotCash-aa.usedcash) as avail from agent_amount aa,agent_details ad,distributor_details dd,MD_Details md  where aa.agent_id=ad.agent_id and dd.distributor_id=ad.distributor_id and dd.MD_id=md.MD_id and ad.agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+parentID+"')))";
					}else{
						status="wrongInput";
					}
				}else if(parentUser.equalsIgnoreCase("ds")){
					if(child.equalsIgnoreCase("agent")){
						sql="select md.MD_initial+convert(varchar(10),md.MD_id) as MDSId,dd.distributor_initial+convert(varchar(10),dd.distributor_id) as DSId,ad.agent_initial+convert(varchar(10),aa.agent_id) as AGID,aa.TotCash,aa.usedcash,aa.commision,aa.cutoff_amount,(aa.TotCash-aa.usedcash+aa.cutoff_amount) as tot,(aa.TotCash-aa.usedcash) as avail from agent_amount aa,agent_details ad,distributor_details dd,MD_Details md  where aa.agent_id=ad.agent_id and dd.distributor_id=ad.distributor_id and dd.MD_id=md.MD_id and ad.agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+parentID+"'))";
					}else{
						status="wrongInput";
					}
				}else{
					status="wrongInput";
				}
			}else if(dbType.equalsIgnoreCase("api")){
				sql="select Corporate_Agent_Id,Total_Cash,Used_Cash,Total_Commission,CutOff_Amount,Total_Bal_Amount,Available_Bal_Amount from OnlineRechAPI_Live_db.dbo.Rech_API_Corporate_Agent_Amount ";
			}

			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			FileWriter writer = new FileWriter(filePath);
			if(dbType.equalsIgnoreCase("tep")){
				if(child.equalsIgnoreCase("admin")){
					writer.append("User ID");
					writer.append('\t');
					writer.append("Total Cash");
					writer.append('\t');
					writer.append("Used Cash");
					writer.append('\t');
					writer.append("Total Commission");
					writer.append('\t');
					writer.append("Cutoff Amount");
					writer.append('\t');
					writer.append("Total Balance");
					writer.append('\t');
					writer.append("Available Amount");
					writer.append('\n');
				}
				else if(child.equalsIgnoreCase("mds")){
					writer.append("MD ID");
					writer.append('\t');
					writer.append("Total Cash");
					writer.append('\t');
					writer.append("Used Cash");
					writer.append('\t');
					writer.append("Total Commission");
					writer.append('\t');
					writer.append("Cutoff Amount");
					writer.append('\t');
					writer.append("Total Balance");
					writer.append('\t');
					writer.append("Available Amount");
					writer.append('\n');
				}
				else if(child.equalsIgnoreCase("ds")){
					writer.append("Distributor ID");
					writer.append('\t');
					writer.append("Total Cash");
					writer.append('\t');
					writer.append("Used Cash");
					writer.append('\t');
					writer.append("Commission");
					writer.append('\t');
					writer.append("Cutoff Amount");
					writer.append('\t');
					writer.append("Total Balance");
					writer.append('\t');
					writer.append("Available Amount");
					writer.append('\n');
				}
				else if(child.equalsIgnoreCase("agent")){
					writer.append("MDS ID");
					writer.append('\t');
					writer.append("DS ID");
					writer.append('\t');
					writer.append("Agent ID");
					writer.append('\t');
					writer.append("Total Cash");
					writer.append('\t');
					writer.append("Used Cash");
					writer.append('\t');
					writer.append("Commission");
					writer.append('\t');
					writer.append("Cutoff Amount");
					writer.append('\t');
					writer.append("Total Balance");
					writer.append('\t');
					writer.append("Available Amount");
					writer.append('\n');
				}
			}else if(dbType.equalsIgnoreCase("api")){
				writer.append("Corporate ID");
				writer.append('\t');
				writer.append("Total Cash");
				writer.append('\t');
				writer.append("Used Cash");
				writer.append('\t');
				writer.append("Total Commission");
				writer.append('\t');
				writer.append("Cutoff Amount");
				writer.append('\t');
				writer.append("Total Balance");
				writer.append('\t');
				writer.append("Available Amount");
				writer.append('\n');
			}
			status="Success";
			while(itr.hasNext()){

				Object[] row=(Object[])itr.next();
				if(dbType.equalsIgnoreCase("tep")){
					if(child.equalsIgnoreCase("admin")){
						writer.append(row[0]==null ? "Null" :row[0].toString());
						writer.append('\t');
						writer.append(row[1]==null ? "Null" :row[1].toString());
						writer.append('\t');
						writer.append(row[2]==null ? "Null" :row[2].toString());
						writer.append('\t');
						writer.append(row[3]==null ? "Null" :row[3].toString());
						writer.append('\t');
						writer.append(row[4]==null ? "Null" :row[4].toString());
						writer.append('\t');
						writer.append(row[5]==null ? "Null" :row[5].toString());
						writer.append('\t');
						writer.append(row[6]==null ? "Null" :row[6].toString());
						writer.append('\n');
					}else if(child.equalsIgnoreCase("mds")){
						writer.append(row[0]==null ? "Null" :row[0].toString());
						writer.append('\t');
						writer.append(row[1]==null ? "Null" :row[1].toString());
						writer.append('\t');
						writer.append(row[2]==null ? "Null" :row[2].toString());
						writer.append('\t');
						writer.append(row[3]==null ? "Null" :row[3].toString());
						writer.append('\t');
						writer.append(row[4]==null ? "Null" :row[4].toString());
						writer.append('\t');
						writer.append(row[5]==null ? "Null" :row[5].toString());
						writer.append('\t');
						writer.append(row[6]==null ? "Null" :row[6].toString());
						writer.append('\n');
					}else if(child.equalsIgnoreCase("ds")){
						writer.append(row[0]==null ? "Null" :row[0].toString());
						writer.append('\t');
						writer.append(row[1]==null ? "Null" :row[1].toString());
						writer.append('\t');
						writer.append(row[2]==null ? "Null" :row[2].toString());
						writer.append('\t');
						writer.append(row[3]==null ? "Null" :row[3].toString());
						writer.append('\t');
						writer.append(row[4]==null ? "Null" :row[4].toString());
						writer.append('\t');
						writer.append(row[5]==null ? "Null" :row[5].toString());
						writer.append('\t');
						writer.append(row[6]==null ? "Null" :row[6].toString());
						writer.append('\n');
					}else if(child.equalsIgnoreCase("agent")){
						writer.append(row[0]==null ? "Null" :row[0].toString());
						writer.append('\t');
						writer.append(row[1]==null ? "Null" :row[1].toString());
						writer.append('\t');
						writer.append(row[2]==null ? "Null" :row[2].toString());
						writer.append('\t');
						writer.append(row[3]==null ? "Null" :row[3].toString());
						writer.append('\t');
						writer.append(row[4]==null ? "Null" :row[4].toString());
						writer.append('\t');
						writer.append(row[5]==null ? "Null" :row[5].toString());
						writer.append('\t');
						writer.append(row[6]==null ? "Null" :row[6].toString());
						writer.append('\t');
						writer.append(row[5]==null ? "Null" :row[7].toString());
						writer.append('\t');
						writer.append(row[6]==null ? "Null" :row[8].toString());
						writer.append('\n');
					}
				}else if(dbType.equalsIgnoreCase("api")){
					writer.append(row[0]==null ? "Null" :row[0].toString());
					writer.append('\t');
					writer.append(row[1]==null ? "Null" :row[1].toString());
					writer.append('\t');
					writer.append(row[2]==null ? "Null" :row[2].toString());
					writer.append('\t');
					writer.append(row[3]==null ? "Null" :row[3].toString());
					writer.append('\t');
					writer.append(row[4]==null ? "Null" :row[4].toString());
					writer.append('\t');
					writer.append(row[5]==null ? "Null" :row[5].toString());
					writer.append('\t');
					writer.append(row[6]==null ? "Null" :row[6].toString());
					writer.append('\n');
				}
			}
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getATTPendingTranAPI");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getATTPendingTranAPI ");
				System.out.println(e.toString());
			}
		}
		//System.out.println(status);
		return status;
	}

	final String getStatusOfoldLoginID(String dbType, String user, String userType,
			String oldLoginID) {
		String sql="";
		String status="InCorrect";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			if(dbType.equalsIgnoreCase("tep")){
				if(user.equalsIgnoreCase("mds")){
					sql="select count(User_name) from md_login_details where user_name='"+oldLoginID+"'";
				}else if(user.equalsIgnoreCase("ds")){
					sql="select count(User_name) from distributor_login_details where user_name='"+oldLoginID+"'";
				}else if(user.equalsIgnoreCase("agent")){
					if(userType.equalsIgnoreCase("tepUser")){
						sql="select count(User_name) from login_details where user_name='"+oldLoginID+"'";
					}else if(userType.equalsIgnoreCase("repUser")){
						sql="select count(User_name) from recharge_e_point_login_info where user_name='"+oldLoginID+"'";
					}
				}
			}else if(dbType.equalsIgnoreCase("api")){
				sql="";
			}
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			int count=Integer.parseInt(query.uniqueResult().toString());
			if(count==1){
				status="Correct";
			}else{
				status="InCorrect";
			}

		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getStatusOfoldLoginID");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getStatusOfoldLoginID ");
				System.out.println(e.toString());
			}
		}
		return status;
	}

	public String checkNewloginID(String dbType, String user, String userType,String newLoginID) {
		String sql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Query query=null;
		try{
			if(dbType.equalsIgnoreCase("tep")){
				if(user.equalsIgnoreCase("mds")){
					sql="select count(User_name) from md_login_details where user_name='"+newLoginID+"'";
				}else if(user.equalsIgnoreCase("ds")){
					sql="select count(User_name) from distributor_login_details where user_name='"+newLoginID+"'";
				}else if(user.equalsIgnoreCase("agent")){
					if(userType.equalsIgnoreCase("tepUser")){
						sql="select count(User_name) from login_details where user_name='"+newLoginID+"'";
					}else if(userType.equalsIgnoreCase("repUser")){
						sql="select count(User_name) from recharge_e_point_login_info where user_name='"+newLoginID+"'";
					}
				}
			}else if(dbType.equalsIgnoreCase("api")){
				sql="";
			}
			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			int count=Integer.parseInt(query.uniqueResult().toString());
			if(count==1){
				status="Correct";
			}else{
				status="InCorrect";
			}

		}catch (Exception e) {
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getStatusOfoldLoginID");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getStatusOfoldLoginID ");
				System.out.println(e.toString());
			}
		}
		return status;
	}

	public String updateloginID(String dbType, String user, String userType,String newLoginID, String oldLoginID) {
		String sql="";
		String updateSql="";
		String status="Norecord";
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran;
		Query query=null;
		tran=session.beginTransaction();
		try{
			if(dbType.equalsIgnoreCase("tep")){
				if(user.equalsIgnoreCase("mds")){
					sql="update md_login_details set user_name='"+newLoginID+"' where user_name='"+oldLoginID+"'";
					updateSql="update md_details set email_id='"+newLoginID+"' where email_id='"+oldLoginID+"'";
				}else if(user.equalsIgnoreCase("ds")){
					sql="update distributor_login_details set user_name='"+newLoginID+"' where user_name='"+oldLoginID+"'";
					updateSql="update distributor_details set email_id='"+newLoginID+"' where email_id='"+oldLoginID+"'";
				}else if(user.equalsIgnoreCase("agent")){
					if(userType.equalsIgnoreCase("tepUser")){
						sql="update login_details set user_name='"+newLoginID+"' where user_name='"+oldLoginID+"'";
						updateSql="update agent_details set email_id='"+newLoginID+"' where email_id='"+oldLoginID+"'";
					}else if(userType.equalsIgnoreCase("repUser")){
						sql="update recharge_e_point_login_info set user_name='"+newLoginID+"' where user_name='"+oldLoginID+"'";
						//updateSql="update agent_details set email_id='"+newLoginID+"' where email_id='"+oldLoginID+"'";
					}
				}
			}else if(dbType.equalsIgnoreCase("api")){
				sql="";
				updateSql="";
			}

			//System.out.println(sql);
			query=session.createSQLQuery(sql);
			query.executeUpdate();
			query=session.createSQLQuery(updateSql);
			query.executeUpdate();
			tran.commit();

		}catch (Exception e) {
			tran.rollback();
			status="ERROR";
			System.out.println("Exception in DBTaskDao method is getStatusOfoldLoginID");
			System.out.println(e.toString());
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				System.out.println("Exception in  DBTaskDao method is getStatusOfoldLoginID ");
				System.out.println(e.toString());
			}
		}
		return status;
	}

	public List<String> getMobileAndMailId(String activityFor,String fetchType) {
		String sql="";
		Session session=null;
		Query query=null;
		List<String> list=null;
		try{
			session=HibernateSession.getSessionFactory().openSession();
			if(fetchType.equalsIgnoreCase("bulkmail")){
				if(activityFor.equalsIgnoreCase("agent")){
					sql="select email_id from agent_details";
				}
				else if(activityFor.equalsIgnoreCase("ds")){
					sql="select email_id from distributor_details";
				}else if(activityFor.equalsIgnoreCase("wl")){
					sql="select Help_Desk_EmailID from white_label_details";
				}
			}else if(fetchType.equalsIgnoreCase("bulksms")){
				if(activityFor.equalsIgnoreCase("agent")){
					sql="select mobile_no from agent_details";
				}
				else if(activityFor.equalsIgnoreCase("ds")){
					sql="select mobile_no from distributor_details";
				}else if(activityFor.equalsIgnoreCase("wl")){
					sql="select Help_Desk_MobileNo from white_label_details";
				}
			}
			if(sql.length()>0){
				query=session.createSQLQuery(sql);
				list=query.list();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {

				session.flush();
				session.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	final ArrayList<HashMap<String,String>> getTicketDetails() {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Session session=null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			sql="select Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Opened_Date=convert(date,getdate())"
					+" order by Opened_Date desc,Opened_Time desc";
			System.out.println("SQL : "+sql);
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				
				temp.put("Ticket_id",rs.getString(1)); 
				temp.put("Txn_id",rs.getString(2)); 
				temp.put("Opened_Date",rs.getString(3));
				temp.put("Opened_Time",rs.getString(4)); 
				temp.put("Closed_Date",rs.getString(5)==null?"NA":rs.getString(5)); 
				temp.put("Closed_Time",rs.getString(6)==null?"NA":rs.getString(6));
				temp.put("Status",rs.getString(7));
				temp.put("Query_Message",rs.getString(8)==null?"NA":rs.getString(8));
				temp.put("Solution_Message",rs.getString(9)==null?"NA":rs.getString(9));
				data.add(temp);			 
				
			}
			//System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			System.out.println("Exception in DBTaskDao class method (getTicketDetails)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (con!=null)
					con.close();
				if (session!=null)
					session.close();
			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao class method (getTicketDetails) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	final ArrayList<HashMap<String,String>> getTicketDetails(String fromdate,String toDate,String ticketId) {
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		String sql="";
		Session session=null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			if(ticketId!=null && ticketId.length()>0){
				sql="select Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Ticket_Id='"+ticketId+"' and Opened_Date between '"+fromdate+"' and '"+toDate+"' "
						+" order by Opened_Date desc,Opened_Time desc";
			}else{
				sql="select Ticket_id,Txn_id,Opened_Date,Opened_Time,Closed_Date,Closed_Time,Status,Query_Message,Solution_Message from Client_Ticket_Details where Opened_Date between '"+fromdate+"' and '"+toDate+"' "
						+" order by Opened_Date desc,Opened_Time desc";
			}
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			
			System.out.println("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				
				temp.put("Ticket_id",rs.getString(1)); 
				temp.put("Txn_id",rs.getString(2)); 
				temp.put("Opened_Date",rs.getString(3));
				temp.put("Opened_Time",rs.getString(4)); 
				temp.put("Closed_Date",rs.getString(5)==null?"NA":rs.getString(5)); 
				temp.put("Closed_Time",rs.getString(6)==null?"NA":rs.getString(6));
				temp.put("Status",rs.getString(7));
				temp.put("Query_Message",rs.getString(8)==null?"NA":rs.getString(8));
				temp.put("Solution_Message",rs.getString(9)==null?"NA":rs.getString(9));
				data.add(temp);			 
				
			}
			//System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			System.out.println("Exception in DBTaskDao class method (getTicketDetails)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (con!=null)
					con.close();
				if (session!=null)
					session.close();
			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao class method (getTicketDetails) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	final boolean updateTicketDetails(String ticketId,String solution) {
		String sql="";
		Session session=null;
		Connection con = null;
		PreparedStatement pstmt=null;
		Transaction txn=null;
		try
		{
			
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			con=session.connection();
			sql ="update Client_Ticket_Details set Closed_Date=convert(varchar(10),getdate(),120),Closed_Time=getdate(),Solution_Message='"+solution+"', Status='Closed' where Ticket_id='"+ticketId+"'" ;
			System.out.println("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			int count=pstmt.executeUpdate();
			txn.commit();
			if(count>0)
				return true;
			else
				return false;
			//System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			System.out.println("Exception in DBTaskDao class method (updateTicketDetails)");
			ex.printStackTrace();
			return false;
		}
		finally {
			try {
				if (con!=null)
					con.close();
				if (session!=null)
					session.close();
			} catch (Exception e) {
				System.out.println("Exception in DBTaskDao class method (updateTicketDetails) while closing connection");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public ArrayList<HashMap<String,String>> getPayOutDetails(String UserType,long userId) {
		String sql="";
		Session session=null;
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> temp=null;
		try
		{
			sql="SELECT Client_Id, User_Type, Service_Type, Commission, Comm_mark FROM Sattelment_Commission_Details where User_Type='"+UserType+"' and Client_id="+userId;
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			
			//System.out.println("SQL : "+sql);
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				
				temp.put("Client_Id",rs.getString(1)); 
				temp.put("User_Type",rs.getString(2)); 
				temp.put("Service_Type",rs.getString(3));
				temp.put("Commission",rs.getString(4)); 
				temp.put("Comm_mark",rs.getString(5)); 
				data.add(temp);
				
			}
			////System.out.println("---------------------AccountStatementDao--------------"+data+"\n");

		}catch (Exception ex) {
			//System.out.println("Exception in DBTaskDao class method (getTicketDetails)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (con!=null)
					if(con!=null)con.close();;
				if (session!=null)
					session.close();
			} catch (Exception e) {
				//System.out.println("Exception in DBTaskDao class method (getTicketDetails) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public boolean updatePayOutCommission(HashMap<String,String> map,String mdId,String userTypeMain) {
		String sql="";
		Session session=null;
		Transaction txn=null;
		try
		{
			
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			
			String clientId=map.get("clientId");
			
			
			if("all".equalsIgnoreCase(clientId) ){
				
				if(("DS".equalsIgnoreCase(userTypeMain) && "all".equalsIgnoreCase(mdId)) ){
				
					sql ="update Sattelment_Commission_Details set Commission="+Double.parseDouble(map.get("comm"))+", Comm_mark='"+map.get("CommMark")+"' where User_Type='"+userTypeMain+"' and Service_Type='"+map.get("serviceType")+"'" ;
				
				}else if(("DS".equalsIgnoreCase(userTypeMain) && !"all".equalsIgnoreCase(mdId)) ){
					//Update all ds of a particular MD
					sql ="update Sattelment_Commission_Details set Commission="+Double.parseDouble(map.get("comm"))+", Comm_mark='"+map.get("CommMark")+"' where User_Type='"+userTypeMain+"' and Service_Type='"+map.get("serviceType")+"' and Client_Id in (select distributor_id from distributor_details where md_id ="+Long.parseLong(mdId)+")" ;
				
				}else {
					
						sql ="update Sattelment_Commission_Details set Commission="+Double.parseDouble(map.get("comm"))+", Comm_mark='"+map.get("CommMark")+"' where User_Type='"+userTypeMain+"' and Service_Type='"+map.get("serviceType")+"'" ;
				}

			}else{
				
				sql ="update Sattelment_Commission_Details set Commission="+Double.parseDouble(map.get("comm"))+", Comm_mark='"+map.get("CommMark")+"' where Client_Id="+Long.parseLong(map.get("clientId"))+" and User_Type='"+map.get("userType")+"' and Service_Type='"+map.get("serviceType")+"'" ;
			}
			Query qry=session.createSQLQuery(sql);
			int count=qry.executeUpdate();
			
			txn.commit();
			
			if(count>0)
				return true;
			else 
				return false;
			
			
		}catch (Exception ex) {
			//System.out.println("Exception in DBTaskDao class method (updateTicketDetails)");
			ex.printStackTrace();
			return false;
		}
		finally {
			try {
				if (session!=null)
					session.close();
			} catch (Exception e) {
				//System.out.println("Exception in DBTaskDao class method (updateTicketDetails) while closing connection");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public String runDsPayOut(String clientId,String UserType,String date,String idType,String parentMDSId) {
		Session session = null;
		Transaction txn = null;
		String status="ERROR";
		Connection con=null;
		CallableStatement cstmt = null;
		try {

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			txn = session.beginTransaction();

			if("DS".equalsIgnoreCase(UserType)){
				cstmt = con.prepareCall("{call sp_ds_autocommission(?,?,?,?)}");
				cstmt.setString(1, date);
				cstmt.setLong(2, Long.parseLong(clientId));
				cstmt.setString(3, idType);
				cstmt.setString(4, parentMDSId);
			}else{
				cstmt = con.prepareCall("{call sp_mds_autocommission(?,?,?)}");
				cstmt.setString(1, date);
				cstmt.setLong(2, Long.parseLong(clientId));
				cstmt.setString(3, idType);
			}
			
			cstmt.executeQuery();
			
			txn.commit();
			
			status="Process Successfull";
			
		} catch (Exception e) {
			status = "ERROR";
			//System.out.println("Exception in DBTaskDao method is executeDebitQuery");
			//System.out.println(e.toString());
			if (txn != null) {
				try {
					txn.rollback();
				} catch (Exception ex) {
					e.printStackTrace();
				}

			}
		} finally {
			try {
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return status;
	}

}
