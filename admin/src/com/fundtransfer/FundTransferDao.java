package com.fundtransfer;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.ConvertUtility;
import com.common.HibernateSession;

public class FundTransferDao {
	
	public  ArrayList getFundTransferDetail(){
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
		ArrayList fundtransfer=new ArrayList();
		try{
			sqlQuery="SELECT  a.TranSection_No,a.agent_id ,a.provious_Amount,a.Transfer_amount,a.balance_amount,convert(varchar(20),a.Date_of_Transfer,121) as Date_of_Transfer,a.Transfer_from_service, a.Transfer_to_service,a.Status,a.Updated_Date,a.Updated_User,a.Remark,b.agent_initial  FROM fundtransferdetail a,agent_details b where a.Status='pending' and a.agent_id=b.agent_id order by a.Date_of_transfer asc";
	
			System.out.println("query is==========="+sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			HashMap map;
			Object[] row;
			while(itr.hasNext())
			{
				row = (Object[])itr.next();
				map=new HashMap();
				map.put("TranSection_No",row[0]);
				String AgID=row[12].toString()+row[1].toString();
				map.put("agent_id",AgID);
				map.put("provious_Amount",row[2]);
				map.put("Transfer_amount",row[3]);
				map.put("balance_amount",row[4]);
				//System.out.println("balance amount is why null "+row[4]);
				map.put("Date_of_Transfer",row[5]);
				map.put("Transfer_from_service",row[6]);
			    map.put("Transfer_to_service",row[7]);
			    map.put("Status",row[8]);
			    map.put("Updated_Date",row[9]);
			    map.put("Updated_User",row[10]);
			    map.put("Remark",row[11]);
				
			    fundtransfer.add(map);
			}
			
		}catch(Exception e)
		{
			System.out.println("Exception in FundTransferDao in getFundTransferDetail ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getPortalId while closing connection"+e.toString());
			}
			
		}
		return fundtransfer;
	}
	
	public String getTranStatus(String transaction_no,String agent_id){
		String status="";
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
		try
		{
			
			sqlQuery="select Status from fundtransferdetail where agent_id="+agent_id+" and TranSection_No='"+transaction_no+"'";
			System.out.println("select query is=========="+sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			status=qry.uniqueResult().toString();
			
		}catch(Exception e)
		{
			System.out.println("Exception in FundTransferDao in getTranStatus ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao in getTranStatus while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public String updateFundTransferDetailStatus(String agent_id,String transaction_no){
		
		String status="Unsuccessful Transaction, Please Check Amount Detail";
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        Transaction tran=null;
		try
		{
			tran=session.beginTransaction();
			sqlQuery="update fundtransferdetail set Status='Failure',updated_date=getdate() where agent_id="+agent_id+" and TranSection_No='"+transaction_no+"'";
			System.out.println(sqlQuery);
			
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			tran.commit();
			status="Transaction has been successfully updated";
			
		}catch(Exception e)
		{
			tran.rollback();
			status="unsuccessful transaction, please check amount detail";
			System.out.println("Exception in FundTransferDao in updateFundTransferDetailStatus ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao in updateFundTransferDetailStatus while closing connection");
				e.printStackTrace();
			}
			
		}
		return status;	

	}
	
	public String updateFundTransferDetail(String IdNo,String agent_id,String statusid,String transaction_no,String prev_amount,String reqamount,String final_amt,String date_of_transfer,String updated_date,String ip_address, ServletContext context,String loginUserId,String loginUserType){
        java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
        
		double balAmount=0.00;
		double currentBal=0.00;
		double usedcash=0.00;
		String distId="";
		double distComm=0.00;
		double agentComm=0.00;
		
		String status="Unsuccessful Transaction, Please Check Amount Detail";
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        Transaction tran=null;
        
        CallableStatement cstmt = null;
        Connection con=null;
		try
		{
			tran=session.beginTransaction();
			int userId=Integer.parseInt((String)context.getAttribute("loginUserId"));
			String Tnxattribute=(String)context.getAttribute(transaction_no);
			String fundTranStatus=getTranStatus(transaction_no, agent_id);
			
			if(userId==Integer.parseInt(loginUserId) && Tnxattribute.equalsIgnoreCase(transaction_no))
			{
			if(fundTranStatus.equalsIgnoreCase("pending"))
			{			
			sqlQuery="select a.TotCash,a.usedcash,b.distributor_id from agent_amount a , agent_details b where a.agent_id='"+agent_id+"' and a.agent_id=b.agent_id";
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row; 
			
			double req_amount=Double.parseDouble(reqamount);
			System.out.println("req_amount is-------"+req_amount);
			while(itr.hasNext()){
				row = (Object[])itr.next();
				double TotCash=Double.parseDouble(row[0].toString());	
				usedcash=Double.parseDouble(row[1].toString());	
				distId=row[2].toString();
				balAmount=TotCash-usedcash;
				
			
			}
			
			String adminAmountQuery="";
			
			 con = session.connection();
			cstmt=con.prepareCall("{call agent_commission_value(?,?,?)}");
			cstmt.setString(1,agent_id);
			cstmt.setString(2,"air");
			cstmt.setDouble(3,req_amount);
			ResultSet rs=cstmt.executeQuery();
			while(rs.next())
			{
				agentComm=rs.getDouble(1);
			}
			System.out.println("agentComm is" +agentComm)	;
            double deductAmt=req_amount-agentComm;
			currentBal=balAmount+deductAmt;	
			double prevamt=Double.parseDouble(prev_amount);
			
			Double bal_amt=prevamt-req_amount;
           
			String updateQuery="update agent_amount set usedcash=usedcash-"+deductAmt+",last_amount="+deductAmt+" where agent_id="+agent_id+"";
            System.out.println(updateQuery);
            qry=session.createSQLQuery(updateQuery);
            qry.executeUpdate();
        	String updateAdminQuery="";
        	BigDecimal ret = null;
        	if(loginUserType.equalsIgnoreCase("activityAdmin"))
            {
            	adminAmountQuery="select (Total_cash-Used_cash+Cutoff_amount) as totalCash from Admin_user_amount_details where Portal_id="+loginUserId+"";
            }
            if(loginUserType.equalsIgnoreCase("superadmin"))
            {
            	adminAmountQuery="select (Total_cash-Used_cash+Cutoff_amount) as totalCash from SuperAdmin_Amount_Details";
            }
            
            System.out.println(adminAmountQuery);
            qry=session.createSQLQuery(adminAmountQuery);
            double adminTotCash=0.0;
            adminTotCash=Double.parseDouble(qry.uniqueResult().toString());
            System.out.println(adminTotCash);
            double finalAdminAmount=adminTotCash-deductAmt;
            
            System.out.println("admin balance is "+adminTotCash);
            System.out.println("finalAdminAmount is "+finalAdminAmount);
        	
            if(loginUserType.equalsIgnoreCase("activityAdmin"))
            {
            	updateAdminQuery="update dbo.Admin_user_amount_details set Used_cash=Used_cash+"+deductAmt+",Total_balance_Amount=Total_balance_Amount-"+deductAmt+",Available_balance_amount=Available_balance_amount-"+deductAmt+",Last_updated_amount="+deductAmt+" where Portal_id="+loginUserId+"";
            }
            if(loginUserType.equalsIgnoreCase("superadmin"))
            {
            	updateAdminQuery="update SuperAdmin_Amount_Details set Used_cash=Used_cash+"+deductAmt+",Total_balance_Amount=Total_balance_Amount-"+deductAmt+",Available_balance_amount=Available_balance_amount-"+deductAmt+",Last_updated_amount="+deductAmt+" where Super_admin_id="+loginUserId+"";
            }
            
            System.out.println(updateAdminQuery);
            qry=session.createSQLQuery(updateAdminQuery);
            qry.executeUpdate();
			
			String updateQuery1="update fundtransferdetail set Status='Success',updated_date=getdate(),provious_Amount="+prevamt+",balance_amount="+bal_amt+" where agent_id="+agent_id+" and TranSection_No='"+transaction_no+"'";
			System.out.println(updateQuery1);
			qry=session.createSQLQuery(updateQuery1);
			qry.executeUpdate();
			
			String insertQuery="insert into agent_transaction_details(Transaction_Id,UserType,Agent_id,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Service_charge2,DeductedAmt,Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date,Updated_Time,Agent_F_balAmt,IpAddress,UpdIpAddress,Remark) values('"+IdNo+"','admin','"+agent_id+"','"+distId+"','"+transaction_no+"',getdate(),'TransferAir2Tep',convert(decimal(18,4),"+balAmount+"),convert(decimal(18,4),"+reqamount+"),'"+agentComm+"',convert(decimal(18,4),"+deductAmt+"),convert(decimal(18,4),"+currentBal+"),'credit','Success',getdate(),'"+sqltime+"',convert(decimal(18,4),"+currentBal+"),'"+ip_address+"','"+ip_address+"','Transfer from AIR To TEP')";

	        System.out.println(insertQuery);
	        qry=session.createSQLQuery(insertQuery);
	        qry.executeUpdate();
	        
			String adminTranId=ConvertUtility.transactionId();
		    String sql="insert into Portal_User_Transaction_details(Transaction_No,Reference_id,Portal_id,User_Type,Date_of_Transaction,Time_of_Transaction" +
			",Service,Tran_amount,Bank_charge,Net_Tran_amount,Action_on_Bal_amount,Previous_Bal_amount,Updated_Bal_amount," +
			"Tran_status,Final_Bal_amount,Tran_ip_address,Updated_Date,Updated_time,Updated_User,Updated_ip_adddress,Remarks,transfer_to) " +
			"values('"+adminTranId+"','"+IdNo+"',"+loginUserId+",'"+loginUserType+"',GETDATE(),GETDATE()," +
			"'Air To Tep',"+deductAmt+",0.0,"+deductAmt+",'debit',"+adminTotCash+","+finalAdminAmount+"," +
					"'Success',"+finalAdminAmount+",'"+ip_address+"',GETDATE(),convert(varchar,getdate(),108),'"+loginUserId+"','"+ip_address+"','Air to Tep','Agent-"+agent_id+"')";
			Query adminInserQuery=session.createSQLQuery(sql);
			System.out.println(sql);
			adminInserQuery.executeUpdate();
			
			
		    status="Transaction has been successfully updated";
			System.out.println("we are in updateFundTransferDetail and status is --"+status);
			tran.commit();
			}else{
				status="This Transaction alredy proced";
			}
			}
			else{
				status="This Transaction is under process, Please wait.";
			}
		
		}catch(Exception e)
		{
			tran.rollback();
			System.out.println("Exception in FundTransferDao in updateFundTransferDetail ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao in updateFundTransferDetail while closing connection");
				e.printStackTrace();
			}
			
		}
		return status;	
	}
	
	public HashMap getDetailsToSend(String agentId) {
		
		HashMap hm=new HashMap();
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
		try
		{
			sqlQuery="select a.TotCash-a.usedCash+a.cutoff_Amount as balance, b.mobile_no from agent_amount a, agent_details b where a.agent_id='"+agentId+"' and a.agent_id=b.agent_id ";
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row;
			while(itr.hasNext())
			{	
				row=(Object[])itr.next();
				hm.put("balance",row[0].toString()); 
				hm.put("mobileNo",row[1].toString()); 
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getDetailsToSend ");
			e.printStackTrace();
		}
		finally{
			try
			{
				session.flush();
				session.close();
			}
            catch(Exception e){
				  System.out.println("Exception in getDetailsToSend while closing connection");
				  e.printStackTrace();
			  }
		}
		return hm;
	}
	
	public String updateFundTransferTepToAirFailure(String IdNo,String agent_id,String transaction_no,String tno,String prev_amount,String reqamount,String final_amt,String date_of_transfer,String updated_date,String ip_address, ServletContext context, String loginUserId, String loginType){
        
		//String amount="";
		double currentBal=0.00;
		double usedcash=0.00;
		//double usecashamt=0.00;
		double balAmount=0.00;
		double deductAmt=0.00;
		String distId="";
		String status="Unsuccessful Transaction, Please Check Amount Detail";
		java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
		Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        Transaction tran=null;
        System.out.println("we are in updateFundTransferTepToAirFailure method");
		try
		{
			
			tran=session.beginTransaction();
			int userId=Integer.parseInt((String)context.getAttribute("loginUserId"));
			String Tnxattribute=(String)context.getAttribute(transaction_no);
			String fundTranStatus=getTranStatus(transaction_no, agent_id);
			
			if(userId==Integer.parseInt(loginUserId) && Tnxattribute.equalsIgnoreCase(transaction_no))
			{
			if(fundTranStatus.equalsIgnoreCase("pending"))
			{
			sqlQuery="update fundtransferdetail set Status='Failure',updated_date=getdate() where agent_id="+agent_id+" and TranSection_No='"+transaction_no+"'";
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			sqlQuery="update agent_transaction_details set tran_status='Refunded',updated_date=getdate(),Updated_Time='"+sqltime+"',UpdIpAddress='"+ip_address+"',Remark='TransferAir2TepBack"+tno+"' where agent_id="+agent_id+" and Transaction_No='"+transaction_no+"'";
			
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			
			sqlQuery="select a.TotCash,a.usedcash,b.distributor_id,c.DeductedAmt from agent_amount a, agent_details b,agent_transaction_details c where a.agent_id='"+agent_id+"' and a.agent_id=b.agent_id and c.Transaction_No='"+transaction_no+"'";
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			
			Object[] row;
			while(itr.hasNext()){
				row=(Object[])itr.next();
				double TotCash=Double.parseDouble(row[0].toString());	
				usedcash=Double.parseDouble(row[1].toString());
				distId=row[2].toString();
				deductAmt=Double.parseDouble(row[3].toString());
				balAmount=TotCash-usedcash;
				currentBal=balAmount+deductAmt;
			}
			sqlQuery="update agent_amount set usedcash=usedcash-'"+deductAmt+"',last_amount='"+deductAmt+"' where agent_id="+agent_id+"";
			System.out.println(sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			
			String insertQuery="insert into fundtransferdetail (TranSection_No,agent_id,provious_Amount,Transfer_amount,balance_amount,Date_of_Transfer,Transfer_from_service,Transfer_to_service,Status,Updated_Date,Updated_user,Remark) values ('"+tno+"','"+agent_id+"','"+balAmount+"','"+deductAmt+"','"+currentBal+"',getdate(),'Tep','AirBack','Success',getdate(),'admin','Transfer Back From TEP to AIR')";
			System.out.println(insertQuery);
			qry=session.createSQLQuery(insertQuery);
			qry.executeUpdate();
			
			String insertQuery1="insert into agent_transaction_details(Transaction_Id,UserType,Agent_id,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession,DeductedAmt,Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date,Updated_time,Agent_F_balAmt,IpAddress,UpdIpAddress,Remark) values('"+IdNo+"','admin','"+agent_id+"','"+distId+"','"+tno+"',getdate(),'TransferAir2TepBack',convert(decimal(18,4),"+balAmount+"),convert(decimal(18,4),"+deductAmt+"),'0',convert(decimal(18,4),"+deductAmt+"),convert(decimal(18,4),"+currentBal+"),'credit','Success',getdate(),'"+sqltime+"',convert(decimal(18,4),"+currentBal+"),'"+ip_address+"','"+ip_address+"','refund of'+'"+transaction_no+"')";
			System.out.println(insertQuery1);
			qry=session.createSQLQuery(insertQuery1);
			qry.executeUpdate();
			tran.commit();
			status="Transaction has been successfully updated";
			
			System.out.println("----------status is -------"+status);
			}else{
				status="This Transaction alredy proced";
			}
			}
			else{
				status="This Transaction is under process, Please wait.";
			}
			
		}catch(Exception e)
		{
			tran.rollback();
			System.out.println("Exception in FundTransferDao in updateFundTransferTepToAirFailure ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao in updateFundTransferTepToAirFailure while closing connection");
			}
			
		}
		return status;	
	}
	
	public String  getTransection_id(String user_id) {
		
		String Transection_id="";
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
		String  transectiondate=sqlDate+" "+sqltime;
		Query qry = null;
        String sqlQuery=null;
        
        Session session = HibernateSession.getSessionFactory().openSession();
		try
		{
			sqlQuery="insert into Refund_transection_id(date,agent_id)values('"+transectiondate+"','"+user_id+"')";
			System.out.println("Insert  query is for transaction id <<<<<<<<<<<<<<>>>>>>>>>>>>> : "+ sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			qry.executeUpdate();
			
			sqlQuery="SELECT max(tr_id) as transectionid FROM Refund_transection_id where agent_id='"+user_id+"'";
			
			
			System.out.println("select Transection   query is  : "+ sqlQuery);
			List list=qry.list();
			Iterator itr =list.iterator();
			Object[] row;
			if(itr.hasNext())
			{
				row=(Object[])itr.next();
				int idNo=Integer.parseInt((String) row[0]);
				if(idNo<10)
				{
					Transection_id="FTA"+user_id+"0000000"+idNo;
						 
				}
				if(idNo<100 && idNo>9)
				{
					Transection_id="FTA"+user_id+"000000"+idNo;
						 
				}
				if(idNo<1000 && idNo>99)
				{
					Transection_id="FTA"+user_id+"00000"+idNo;
						 
				}
				if(idNo<10000 && idNo>999)
				{
					Transection_id="FTA"+user_id+"0000"+idNo;
						 
				}
				if(idNo<100000 && idNo>9999)
				{
					Transection_id="FTA"+user_id+"000"+idNo;
						 
				}
				if(idNo<1000000 && idNo>99999)
				{
					Transection_id="FTA"+user_id+"00"+idNo;
						 
				}
				if(idNo<10000000 && idNo>999999)
				{
					Transection_id="FTA"+user_id+"0"+idNo;
						 
				}
				if(idNo>=10000000)
				{
					Transection_id="FTA"+user_id+idNo;
						 
				}


			}
		}catch(Exception e)
		{
			System.out.println("Exception in FundTransferDao method is getTransection_id");
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao method is getTransection_id while closing connection");
				e.printStackTrace();
			}
			
		}
		return Transection_id;
	}
	
	public String  updateFundTransferTepToAir(String agent_id,String transaction_no,String ip_address, ServletContext context, String loginUserId,String loginUserType, double req_amount, String IdNo){
		String distId="";
		String service="";
		double distComm=0.0;
		double amount=0.0;
		String status="Unsuccessful Transaction, Please Check Amount Detail";
		java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
		Query qry = null;
        String sqlQuery=null;
        Transaction tran=null;
        Session session = HibernateSession.getSessionFactory().openSession();
		try
		{
			tran=session.beginTransaction();
			tran=session.beginTransaction();
			int userId=Integer.parseInt((String)context.getAttribute("loginUserId"));
			String Tnxattribute=(String)context.getAttribute(transaction_no);
			String fundTranStatus=getTranStatus(transaction_no, agent_id);
			
			if(userId==Integer.parseInt(loginUserId) && Tnxattribute.equalsIgnoreCase(transaction_no))
			{
			if(fundTranStatus.equalsIgnoreCase("pending"))
			{	
			sqlQuery="select a.distributor_id ,b.service,b.ReqAmount from agent_details a,agent_transaction_details b where a.agent_id='"+agent_id+"' and b.Transaction_No='"+transaction_no+"'";
			System.out.println("Query is "+sqlQuery);
			qry=session.createSQLQuery(sqlQuery);
			List list=qry.list();
			Iterator itr=list.iterator();
			Object[] row;
			while(itr.hasNext())
			{
				row=(Object[])itr.next();
				distId=row[0].toString();
				service=(String) row[1].toString();
				amount=Double.parseDouble(row[2].toString());

			}
           String updateQuery="update fundtransferdetail set Status='Success',updated_date=getdate() where agent_id="+agent_id+" and TranSection_No='"+transaction_no+"'";
           System.out.println(updateQuery);
           qry=session.createSQLQuery(updateQuery);
           qry.executeUpdate();
           
           String updateQuery1="update agent_transaction_details set distributor_id='"+distId+"',Action_on_bal_amt='Debit',tran_status='Success',updated_date=getdate(),Updated_Time='"+sqltime+"',UpdIpAddress='"+ip_address+"',Remark='Transferteptoairsuccess with"+transaction_no+"' where agent_id="+agent_id+" and Transaction_No='"+transaction_no+"'";
           System.out.println(updateQuery1);
           qry=session.createSQLQuery(updateQuery1);
           qry.executeUpdate();
           
           //Code Added By Manoj For credit amount for superadmin and portal admin
           String adminAmountQuery="";
           String updateAdminQuery="";
       		
           if(loginUserType.equalsIgnoreCase("activityAdmin"))
           {
        	   adminAmountQuery="select (Total_cash-Used_cash+CutOff_Amount) as totalCash from Admin_user_amount_details where Portal_id="+loginUserId+"";
           }
           if(loginUserType.equalsIgnoreCase("superadmin"))
           {
        	   adminAmountQuery="select (Total_cash-Used_cash+CutOff_Amount) as totalCash from SuperAdmin_Amount_Details";
           }
           
           System.out.println(adminAmountQuery);
           qry=session.createSQLQuery(adminAmountQuery);
           double adminTotCash=0.0;
			adminTotCash=Double.parseDouble(qry.uniqueResult().toString());
			System.out.println(adminTotCash);
			double finalAdminAmount=adminTotCash+req_amount;
			
           System.out.println("adminTotCash is "+adminTotCash);
           System.out.println("finalAdminAmount is "+finalAdminAmount);
           
       	
           if(loginUserType.equalsIgnoreCase("activityAdmin"))
           {
        	   updateAdminQuery="update dbo.Admin_user_amount_details set Used_cash=Used_cash-"+req_amount+",Total_balance_Amount=Total_balance_Amount+"+req_amount+",Available_balance_amount=Available_balance_amount+"+req_amount+",Last_updated_amount="+req_amount+" where Portal_id="+loginUserId+"";
           }
           if(loginUserType.equalsIgnoreCase("superadmin"))
           {
        	   updateAdminQuery="update SuperAdmin_Amount_Details set Used_cash=Used_cash-"+req_amount+",Total_balance_Amount=Total_balance_Amount+"+req_amount+",Available_balance_amount=Available_balance_amount+"+req_amount+",Last_updated_amount="+req_amount+" where Super_admin_id="+loginUserId+"";
           }
           
           System.out.println(updateAdminQuery);
           qry=session.createSQLQuery(updateAdminQuery);
           qry.executeUpdate();
           
           
           String adminTranId=ConvertUtility.transactionId();
		    String sql="insert into Portal_User_Transaction_details(Transaction_No,Reference_id,Portal_id,User_Type,Date_of_Transaction,Time_of_Transaction" +
			",Service,Tran_amount,Bank_charge,Net_Tran_amount,Action_on_Bal_amount,Previous_Bal_amount,Updated_Bal_amount," +
			"Tran_status,Final_Bal_amount,Tran_ip_address,Updated_Date,Updated_time,Updated_User,Updated_ip_adddress,Remarks,transfer_to) " +
			"values('"+adminTranId+"','"+IdNo+"',"+loginUserId+",'"+loginUserType+"',GETDATE(),GETDATE()," +
			"'Tep To Air',"+req_amount+",0.0,"+req_amount+",'credit',"+adminTotCash+","+finalAdminAmount+"," +
					"'success',"+finalAdminAmount+",'"+ip_address+"',GETDATE(),convert(varchar,getdate(),108),'"+loginUserId+"','"+ip_address+"','Tep to Air','Agent-"+agent_id+"')";
			Query adminInserQuery=session.createSQLQuery(sql);
			System.out.println(sql);
			adminInserQuery.executeUpdate();
           
           status="Transaction has been successfully updated";
           
           tran.commit();
			}else{
				status="This Transaction alredy proced";
			}
			}
			else{
				status="This Transaction is under process, Please wait.";
			}
		}catch(Exception e)
		{
			tran.rollback();
			System.out.println("Exception in FundTransferDao in updateFundTransferTepToAir ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			}
			
			catch(Exception e)
			{
				System.out.println("Exception in FundTransferDao in updateFundTransferTepToAir while closing connection");
				e.printStackTrace();
			}
			
		}
		return status;	
	}

	public String getAgentID(String agent_id) {
		String sqlQuery=null;
        Transaction tran=null;
        Session session = HibernateSession.getSessionFactory().openSession();
        Query qry = null;
        String user_id="";
        try{
        	sqlQuery="select agent_id from agent_details where agent_initial+convert(varchar(10),agent_id)='"+agent_id+"'";
        	qry=session.createSQLQuery(sqlQuery);
        	user_id=qry.uniqueResult().toString();
        	
        }catch(Exception e){
        	System.out.println("Exception in FundTransferDao method is getAgentID");
        }
		return user_id;
	}
}
