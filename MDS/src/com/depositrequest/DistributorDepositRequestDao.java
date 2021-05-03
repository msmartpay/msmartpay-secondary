
//------updated by Monika on 13-sep-2012------- 
//------updated topic: counter on transactions for distributor deposit----


package com.depositrequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.common.GenerateIdDao;
import com.db.DBConnection;


public class DistributorDepositRequestDao {

	public static ArrayList<HashMap<String,String>> collectionBank(long mdsId) {
		String sql="";
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select bank_name,bank_account,bank_account_name,bnk_ifsc from wb_collection_bank_detils (nolock) a join MD_Details (nolock) b on a.client_id=b.Client_ID and b.MD_id=? and a.status=1";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, mdsId);
			rs=pstmt.executeQuery();
			HashMap<String,String> temp;
			while(rs.next()){
				temp=new HashMap<String,String>(); 
				temp.put("bank_name",rs.getString(1)); 
				temp.put("bank_account",rs.getString(2)); 
				temp.put("bank_account_name",rs.getString(3));
				temp.put("bnk_ifsc",rs.getString(4));  
				data.add(temp);			 
			}

		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	public static String getMDBalance(String mdId,HttpSession session){

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String balance="";
		try
		{   con=DBConnection.getConnection();
		stmt=con.createStatement();
		String SelectDistrubutorbalance="select convert(decimal(18,4),available_bal_amount) as balance from MD_amount where md_id="+mdId+"";
		System.out.println("query is"+"" +SelectDistrubutorbalance);
		rs=stmt.executeQuery(SelectDistrubutorbalance);
		while (rs.next())
		{
			balance=rs.getString(1);
		}
		session.setAttribute("balance",balance);
		}
		catch(Exception e){
			System.out.println("Exception in getMDBalance"+e.toString());
		}
		finally
		{
			try
			{

				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e)
			{
				System.out.println("Exception in LoginDao class, method getMDBalance during closing connection"+e.toString());
			}

		}


		//select a.TotCash, a.usedcash,cutoff_amount,
		return balance;	
	}

	public static ArrayList<HashMap<String, String>> getMdJournal(String md_id) 
	{
		ArrayList<HashMap<String, String>> getMdJournal=new ArrayList<HashMap<String, String>>();

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";

		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();	

			sql="select top 10 Request_Date,convert(varchar, Request_Time, 8) as time ,Mode_of_payment,convert(decimal(18,2),Amount_To_Credit) as amount,Status from dbo.MD_Journal_Details where md_id='"+md_id+"' order by Deposit_Date desc";				

			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{			
				HashMap information=new HashMap();
				information.put("date",rs.getString("Request_Date"));
				information.put("time",rs.getString("time"));
				information.put("Mode_of_payment",rs.getString("Mode_of_payment"));
				information.put("amount",rs.getString("amount"));
				information.put("Status",rs.getString("Status"));
				getMdJournal.add(information);						
			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getDSDetails OF ADMIN"+e.toString());
			}

		}
		return getMdJournal;
	}
	public static ArrayList getDistributorDepositRequestList(String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		ArrayList al=new ArrayList();
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select (b.distributor_initial+convert(varchar,b.distributor_id)) as distributor_id,b.company_name,a.journal_id,a.journal_date,convert(varchar(10),a.request_date,120) as request_date,convert(varchar(10),a.request_time,108) as request_time,a.mode_of_payment,a.ammount_to_credit,a.checque_no,a.status from distributor_journal a,distributor_details  b where a.status='pending' and  a.distributor_id=b.distributor_id and b.md_id="+mdId+" order by request_date,request_time ";
			System.out.println("sql================================"+sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				HashMap map=new HashMap();
				map.put("distributor_id",rs.getString("distributor_id"));
				map.put("company_name",rs.getString("company_name"));
				map.put("journal_id",rs.getString("journal_id"));
				map.put("request_date",rs.getString("request_date"));
				map.put("request_time",rs.getString("request_time"));
				map.put("mode",rs.getString("mode_of_payment"));
				map.put("ammount_to_credit",rs.getString("ammount_to_credit"));
				map.put("checque_no",rs.getString("checque_no"));
				map.put("status",rs.getString("status"));
				al.add(map);					
			}		    
		}	
		catch(Exception e)
		{
			System.out.println("Exception in DistributorDepositRequestDao,method getDistributorDepositRequestList "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception in DistributorDepositRequestDao,method getDistributorDepositRequestList while closing connections"+e.toString());
			}
		}

		return al;

	}

	public static HashMap getDistributorDetails(String distributorId,String tranId,String date,String time){

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		HashMap map=new HashMap();
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select c.login_status,a.Bank_Tran_Id,b.distributor_name as name,b.company_name,a.bank_name,a.cardholdername,a.ammount_to_credit,a.journalid, (b.distributor_initial+convert(varchar,b.distributor_id)) as dist_id,b.email_id,b.mobile_no,a.bank_name,a.request_date,convert(varchar(10),a.request_time,108) as request_time,a.mode_of_payment,a.remarks as remark,a.Referece_no as tranId,a.checque_no as chequeNo,a.depositor_name,a.Depositor_Account_Number,a.deposit_date from distributor_journal a,distributor_details  b,distributor_login_details c where a.distributor_id=b.distributor_id and b.distributor_id=c.user_id and (b.distributor_initial+convert(varchar,b.distributor_id))='"+distributorId+"' and request_time='"+time+"' and request_date='"+date+"' and journal_id='"+tranId+"'";
			System.out.println("sql================================"+sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				map.put("login_status",rs.getString("login_status"));
				map.put("Bank_Tran_Id",rs.getString("Bank_Tran_Id"));
				map.put("name",rs.getString("name"));
				map.put("company_name",rs.getString("company_name"));
				map.put("bank_name",rs.getString("bank_name"));				
				map.put("cardholdername",rs.getString("cardholdername"));
				map.put("ammount_to_credit",rs.getString("ammount_to_credit"));					
				map.put("journalid",rs.getString("journalid"));
				map.put("dist_id",rs.getString("dist_id"));
				map.put("email_id",rs.getString("email_id"));
				map.put("mobile_no",rs.getString("mobile_no"));
				map.put("bank_name",rs.getString("bank_name"));
				map.put("request_date",rs.getString("request_date"));
				map.put("request_time",rs.getString("request_time"));
				map.put("mode",rs.getString("mode_of_payment"));
				map.put("remark",rs.getString("remark"));
				map.put("tranId",rs.getString("tranId"));
				map.put("chequeNo",rs.getString("chequeNo"));
				map.put("depositorName",rs.getString("depositor_name"));
				map.put("depositorAccNo",rs.getString("Depositor_Account_Number"));					
				map.put("deposit_date",rs.getString("deposit_date"));
			}
			map.put("tranNo",tranId);		
		}
		catch(Exception e)
		{
			System.out.println("Exception in DistributorDepositRequestDao,method getDistributorDetails "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();		
			}
			catch(Exception e)
			{
				System.out.println("Exception in DistributorDepositRequestDao,method getDistributorDetails while closing connections"+e.toString());
			}
		}		
		return map;
	}


	public String updateDistributorAmount(Connection con, String distId,String mdId, String date,
			String time, String status, String distamt,double bankcharges,String amount,String tranId ,String clientIp,HttpSession session,String bankChargeRemark) {
		Statement stmt=null;		
		ResultSet rs=null;		
		String id="";		
		String query="";		

		String distributorMobileNo="";
		try
		{
			con=DBConnection.getConnection();	   
			stmt=con.createStatement();			


			String selectQuery="select distributor_id,mobile_No from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distId+"'";

			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{
				id=rs.getString(1);
				distributorMobileNo=rs.getString(2);       
			}

			query="update distributor_journal set status='"+status+"',approval_date=convert(varchar(10),getdate(),111) where journal_id='"+tranId+"'";
			System.out.println(query);			

			stmt.executeUpdate(query);

			String balanceQuery="select (TotCash-usedcash+cutoff_amount)as balance from distributor_amount where distributor_id='"+id+"'";
			String discurrentBalAmount="0";
			rs=stmt.executeQuery(balanceQuery);

			while(rs.next()){
				discurrentBalAmount=(String)rs.getString(1);			
			}
			session.setAttribute("discurrentBalAmount",discurrentBalAmount);	
		}      
		catch(Exception e)
		{	
			System.out.println("Exception in DistributorDepositRequestDao,method updateDistributorAmount while updating deposits list"+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();				
			}
			catch(Exception e)
			{
				System.out.println("Exception in DistributorDepositRequestDao,method updateDistributorAmount while closing connections"+e.toString());
			}
		}
		return distributorMobileNo;

	}

	public String updateMasterDistributorAmount(Connection con,HttpSession session, String mdId,String distId ,String date,
			String time,  String status,String statusselected,String amount,String tranId,double bankcharges,String ip_address,double TransaferAmt,String bankChargeRemark) throws SQLException {
		Statement stmt=null;		
		ResultSet rs1=null;
		ResultSet rs=null;
		String distributorMobileNo="";
		try
		{
			con=DBConnection.getConnection();        		
			con.setAutoCommit(false);
			String query="";
			String md_id="";
			double bal=0.00;
			double total=0.00;
			Double amt=Double.parseDouble(amount);
			double net_amt=amt-bankcharges;

			double agnAmt=0.00;
			double added=0.00;        		
			String id="";
			String exists="";        	
			String query2="";
			String tid="";
			stmt=con.createStatement();

			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			String tran_id=sdf.format(new Date());
			Random rand = new Random();
			int n = 4;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			String transaction_id=tran_id+ran;               
			String sql="select b.Total_Cash,total_bal_amount as balance from  "+
					"MD_amount b where b.md_id="+mdId+"";
			System.out.println("query:===================="+sql);

			rs=stmt.executeQuery(sql);            
			while(rs.next())
			{				
				total = rs.getDouble("total_cash");
				bal=rs.getDouble("balance");
			}        		
			Double distAmt=Double.parseDouble(amount);
			Double finalamt=bal-amt;


			System.out.println(" distAmt :"+distAmt);
			System.out.println(" finalamt :"+finalamt);

			query="update MD_amount  set used_cash=used_cash+"+amt+",Available_Bal_Amount=Available_Bal_Amount-"+amt+",Total_Bal_Amount=Total_Bal_Amount-"+amt+",last_updated_amount="+amount+",updated_date=getdate() where md_id="+mdId+"";
			System.out.println("query:=====update query for md amount==============="+query);
			stmt.executeUpdate(query);
			String insertQuery="insert into md_transaction_details (transaction_no,Reference_id,md_id,user_type,Date_of_Transaction,Time_of_Transaction,Service,Tran_amount,Bank_charge,Net_Tran_amount,Action_on_Bal_amount,previous_Bal_amount,Updated_Bal_amount,Tran_status,Final_Bal_amount,Tran_ip_address,Updated_date,updated_time,Updated_User,Updated_ip_adddress,Remarks) values ('"+transaction_id+"','"+tranId+"',"+mdId+",'md',getdate(),convert(varchar,getdate(),108),'DS TB-Transfer',"+TransaferAmt+","+bankcharges+","+amt+",'Debit',"+bal+","+finalamt+",'Success',"+finalamt+",'"+ip_address+"',getdate(),convert(varchar,getdate(),108),'distributor','"+ip_address+"','AmountTransferFromMDtodist')";			
			System.out.println("query:==========md trancation =========="+insertQuery);
			stmt.executeUpdate(insertQuery);       		

			//--------end for md updation------now------ updating Distributor details and amount----------------//


			transaction_id=GenerateIdDao.getIdNo();
			String selectQuery="select distributor_id,mobile_No from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distId+"'";

			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{
				id=rs.getString(1);
				distributorMobileNo=rs.getString(2);       
			}


			if(statusselected.equalsIgnoreCase("Accepted")){
				/*sql="select a.distributor_id,b.TotCash,(b.TotCash-b.usedcash)as balance from  "+
    					"distributor_amount b,distributor_details a where a.distributor_id=b.distributor_id and (a.distributor_initial+convert(varchar,b.distributor_id))='"+distId+"'";*/

				sql="select a.distributor_id,convert(decimal(18,4),b.TotCash) as TotCash,convert(decimal(18,4),(b.TotCash-b.usedcash))as balance from distributor_amount b,distributor_details a where a.distributor_id=b.distributor_id and (a.distributor_initial+convert(varchar,b.distributor_id))='"+distId+"'";

				System.out.println("query is=====get total amount query========="+sql);
				rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					id=rs.getString(1);
					total=rs.getDouble(2);
					bal=rs.getDouble(3);
				}
				Double agntAmt=Double.parseDouble(amount);

				added=bal+agntAmt;

				System.out.println("agnet   agntAmt   "+agntAmt);
				System.out.println("agnet amount added   "+added);
				System.out.println("distributor_amount    " +amount);

				String query3="insert into distributor_transaction_details (transaction_no,Refrence_id,distributor_id,md_id,user_type,Date_of_Transaction,Time_of_Transaction,Service,Tran_amount,Bank_charge,Net_Tran_amount,Action_on_Bal_amount,previous_Bal_amount,Updated_Bal_amount,Tran_status,Final_Bal_amount,Tran_ip_address,Updated_date,updated_time,Updated_User,Updated_ip_adddress,Remarks) values ('"+transaction_id+"','"+tranId+"',"+id+",'"+mdId+"','distributor',getdate(),convert(varchar,getdate(),108),'mdtodist',"+amount+","+bankcharges+","+amount+",'Credit',"+bal+","+added+",'Success',"+added+",'"+ip_address+"',getdate(),convert(varchar,getdate(),108),'distributor','"+ip_address+"','AmountTransferFromMDTodist')";
				System.out.println("query3 is======>into distributor transaction table>======="+query3);
				stmt.execute(query3);

				query="update distributor_journal set status='"+statusselected+"',approval_date=convert(varchar(10),getdate(),111),Accepted_amount="+amount+",BC_Remark='"+bankChargeRemark+"',bank_charges="+bankcharges+" where journal_id='"+tranId+"'";

				total=total+agntAmt;
				System.out.println("distrbutor journal table  :"+query);

				query2="update distributor_amount set TotCash=TotCash+"+amount+",last_amount="+amount+" where distributor_id="+id+"";
				System.out.println("updating distributor amount "+query2);
				stmt.executeUpdate(query2);
			}
			else
			{
				query="update distributor_journal set status='"+statusselected+"',approval_date=convert(varchar(10),getdate(),111) where journal_id='"+tranId+"'";
				System.out.println(query);			
			}
			stmt.executeUpdate(query);

			String balanceQuery="select (TotCash-usedcash+cutoff_amount)as balance from distributor_amount where distributor_id='"+id+"'";
			String discurrentBalAmount="0";
			rs=stmt.executeQuery(balanceQuery);

			while(rs.next()){
				discurrentBalAmount=(String)rs.getString(1);			
			}
			session.setAttribute("discurrentBalAmount",discurrentBalAmount);	
			con.commit();
		}        
		catch(Exception e)
		{
			System.out.println("Exception in DistributorDepositRequestDao,method updateDeposit1 while updating deposits list"+e.toString());
			e.printStackTrace();
			con.rollback();
		}
		finally
		{
			try{
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();			
			}
			catch(Exception e)
			{
				System.out.println("Exception in DistributorDepositRequestDao,method updateDeposit1 while closing connections"+e.toString());
			}
		}

		return distributorMobileNo;
	}
	public String chkTranId(Connection con, String tranId,HttpSession session) {
		Statement stmt=null;	

		ResultSet rs=null;
		String status="";
		try
		{
			con=DBConnection.getConnection();
			String query="";


			stmt=con.createStatement();

			query="Select Refrence_id from distributor_transaction_details where Refrence_id='"+tranId+"'";
			System.out.println("query:===================="+query);
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				status="Invalid";
				session.setAttribute("status",status);
			}
			else
				status="valid";
			session.setAttribute("status",status);		
		}
		catch(Exception e)
		{
			System.out.println("Exception in AdminDepAgentDao,method chkTranId "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();				
				if(con!=null)
					con.close();       			
			}
			catch(Exception e)
			{
				System.out.println("Exception in AdminDepAgentDao,method chkTranId while closing connections"+e.toString());
			}
		}
		return status;
	}
}