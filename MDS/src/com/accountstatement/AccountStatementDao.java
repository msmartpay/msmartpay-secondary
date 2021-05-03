package com.accountstatement;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;



import com.db.DBConnection;


public class AccountStatementDao {

	public static Vector getAccStatement(Connection con,String mdId){
		
		Vector<HashMap<String, String>> vector=new Vector<HashMap<String, String>>();
		Statement stmt=null;
		ResultSet rs=null;
		System.out.println("we are inside getAccStatement");
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select top 25 transaction_no,Reference_id,Date_of_Transaction,Time_of_Transaction,Service,Tran_amount,Commission,Service_charge-Bank_charge as charge,Net_Tran_amount,Action_on_Bal_amount,Previous_Bal_amount,Updated_Bal_amount,Tran_status,Final_Bal_amount,Remarks from MD_Transaction_details (nolock) where Md_id='"+mdId+"' order by Date_of_Transaction desc";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);

			while(rs.next()){		  
				HashMap<String, String> hm=new HashMap<String, String>();
				hm.put("transactionNo",rs.getString(1));
				hm.put("referenceNo",rs.getString(2));
				hm.put("tranDate",rs.getString(3));
				hm.put("tranTime",rs.getString(4));
				hm.put("service",rs.getString(5)); 
				hm.put("tranAmount",rs.getString(6)); 
				hm.put("commession",rs.getString(7)); 
				hm.put("charge",rs.getString(8)); 
				hm.put("netTranAmount",rs.getString(9)); 
				hm.put("actionOnBal",rs.getString(10)); 
				hm.put("previousBalance",rs.getString(11));
				hm.put("updatedBalance",rs.getString(12));
				hm.put("status",rs.getString(13));
				hm.put("finalBalance",rs.getString(14));
				hm.put("remark",rs.getString(15));				
				vector.add(hm);		
			}
		}		
		catch(Exception e){
			System.out.println("exception in getAccStatement"+e.toString());
		}
		finally{
			try{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){
				System.out.println("exception in getAccStatement while closing comnnection"+e.toString());
			}
		}
		return vector;
	}
	
	public static Vector accountStatementReport(Connection con,String mdId,String toDt,String fromDt,String type,String modcount,int start,int end,HttpSession session){
		System.out.println("we are inside accountStatementReport from next");
		Vector vector=new Vector();
		Statement stmt=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		int count=0;
		int count1=0;
		int pagecount=0;
		String selectQuery="";
		try
		{
		 
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			System.out.println("from date is :.........."+fromDt);
			System.out.println("to date is :............................."+toDt);
			System.out.println("md id is :......................"+mdId);
			if(type.equalsIgnoreCase("debit")){
				selectQuery="select count(md_id) from md_transaction_details (nolock) where date_of_transaction between '"+fromDt+"' and '"+toDt+"' and action_on_bal_amount='debit' and md_id="+mdId+"";
			}
			if(type.equalsIgnoreCase("credit")){
				selectQuery="select count(md_id) from md_transaction_details (nolock) where date_of_transaction between '"+fromDt+"' and '"+toDt+"' and action_on_bal_amount='credit' and md_id="+mdId+"";
			}
			if(type.equalsIgnoreCase("all")){
				selectQuery="select count(md_id) from md_transaction_details (nolock) where date_of_transaction between '"+fromDt+"' and '"+toDt+"' and action_on_bal_amount!='debit/credit' and md_id="+mdId+"";
			}
			System.out.println(selectQuery);
			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			session.setAttribute("count",count);
			int modulus=count%50;
			if(modulus==0)
			{
				pagecount=count/50;
			}
			else
			{
				int page=count/50;
				pagecount=page+1;
			}
			session.setAttribute("pagecount",pagecount);
			System.out.println("page count is=================="+count);

			if(modcount.equals("0"))
			{
		 
				count1=Integer.parseInt(modcount);
			
				count1=count;
			}
			else
			{
				count1=Integer.parseInt(modcount);
			}
		
			if(count1>0)
			{
				if(count1<50)
				{
					session.setAttribute("start",start);
					end=start+count1;
					session.setAttribute("end",end);
					
					cstmt=con.prepareCall("{call MD_account_statement(?,?,?,?,?,?)}");
					cstmt.setString(1,mdId);
					cstmt.setString(2,fromDt);
					cstmt.setString(3,toDt);
					cstmt.setString(4,type);
					cstmt.setInt(5,start);
					cstmt.setInt(6,end);
					System.out.println("hello we have called stored procedure");
					rs=cstmt.executeQuery();
					while(rs.next()){
						HashMap hm=new HashMap();
		 
						hm.put("transactionNo",rs.getString(1));
						hm.put("referenceNo",rs.getString(2));
						hm.put("tranDate",rs.getString(3));
						hm.put("tranTime",rs.getString(4));
						hm.put("service",rs.getString(5)); 
						hm.put("tranAmount",rs.getString(6)); 
						hm.put("commession",rs.getString(7)); 
						hm.put("charge",rs.getString(8)); 
						hm.put("netTranAmount",rs.getString(9)); 
						hm.put("actionOnBal",rs.getString(10)); 
						hm.put("previousBalance",rs.getString(11));
						hm.put("updatedBalance",rs.getString(12));
						hm.put("status",rs.getString(13));
						hm.put("finalBalance",rs.getString(14));
						hm.put("remark",rs.getString(15));
						vector.add(hm);
					}
					session.setAttribute("count1",count1);		
				} 
				else
				{
					//c=a;

					session.setAttribute("start",start);
					end=start+49;
					System.out.println("end in Dao class is"+end);
					
					session.setAttribute("end",end);
					cstmt=con.prepareCall("{call MD_account_statement(?,?,?,?,?,?)}");
					cstmt.setString(1,mdId);
					cstmt.setString(2,fromDt);
					cstmt.setString(3,toDt);
					
					cstmt.setString(4,type);
					cstmt.setInt(5,start);
					cstmt.setInt(6,end);
					System.out.println("hello we have called stored procedure");
					rs=cstmt.executeQuery();
					while(rs.next()){
						HashMap hm=new HashMap();
						//System.out.println("transaction_no is"+rs.getString("transaction_no"));
						hm.put("transactionNo",rs.getString(1));
						hm.put("referenceNo",rs.getString(2));
						hm.put("tranDate",rs.getString(3));
						hm.put("tranTime",rs.getString(4));
						hm.put("service",rs.getString(5)); 
						hm.put("tranAmount",rs.getString(6)); 
						hm.put("commession",rs.getString(7)); 
						hm.put("charge",rs.getString(8)); 
						hm.put("netTranAmount",rs.getString(9)); 
						hm.put("actionOnBal",rs.getString(10)); 
						hm.put("previousBalance",rs.getString(11));
						hm.put("updatedBalance",rs.getString(12));
						hm.put("status",rs.getString(13));
						hm.put("finalBalance",rs.getString(14));
						hm.put("remark",rs.getString(15));
						vector.add(hm);
					}
					session.setAttribute("count1",count1);
				}
		 
			}

		}//
		catch(Exception e){
			System.out.println("Exception in accountStatementReport========"+e.toString());
		}
		finally{
			try{
				if(rs!=null)
					rs.close();
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();

			}
			catch(Exception e){
				System.out.println("exception in accountStatementReport while closing comnnection"+e.toString());
			}

		}
		return vector;
	}//

	public final String getAccountStatement(Connection con,String filePath,String mdid,HttpSession session,String Fromdate,String Todate) {
			String sqlQuery=null;
			String status="NoRecord";
		
			Statement stmt=null;
			ResultSet rs=null;
			try {
				con = DBConnection.getConnection();
				sqlQuery = "select count(md_id) from md_transaction_details where date_of_transaction between '"+Fromdate+"' and '"+Todate+"' and md_id="+mdid+"";

				System.out.println(sqlQuery);
				stmt=con.createStatement();
				rs=stmt.executeQuery(sqlQuery);
				int count=0;
				while(rs.next()){
					count=rs.getInt(1);
				}
				System.out.println(count);
				if(count<=0 || count==0){
					status="NoRecord";
				}else if(count>=20000){
					status="MoreRecord";
				}else if(count>=1 && count<=20000){
					
					String sql="select Transaction_No,User_Type,Date_of_Transaction,Time_of_Transaction,Service,Tran_amount,Commission,Service_charge,Bank_charge,Other_charge,Net_Tran_amount,Action_on_Bal_amount,Tran_status,Final_Bal_amount,Remarks from MD_Transaction_details (nolock) where Md_id='"+mdid+"' and  Date_of_Transaction>='"+Fromdate+"' and Date_of_Transaction<='"+Todate+"'  order by Date_of_Transaction desc";
					System.out.println(sql);
					stmt = con.createStatement();
					rs=stmt.executeQuery(sql);
					status = "Success";
					FileWriter writer = new FileWriter(filePath);
					
		            writer.append("Transaction_No");
		            writer.append(';');
		            writer.append("User_Type");
		            writer.append(';');
		            writer.append("Date_of_Transaction");
		            writer.append(';');
		            writer.append("Time_of_Transaction");
		            writer.append(';');
		            writer.append("Service");
		            writer.append(';');
		            writer.append("Tran_amount");
		            writer.append(';');
		            /*writer.append("Commission");
		            writer.append(';');*/
		            writer.append("Service_charge");
		            writer.append(';');
		            writer.append("Bank_charge");
		            writer.append(';');
		            writer.append("Other_charge");
		            writer.append(';');
		            writer.append("Net_Tran_amount");
		            writer.append(';');
		            writer.append("Action_on_bal_amt");
		            writer.append(';');
		            writer.append("Tran_status");
		            writer.append(';');
		            writer.append("Final_Bal_amount");
		            writer.append(';');
		            writer.append("Remarks");
		            writer.append('\n');
		            
		            while (rs.next()) {  
		            	
		            	/*//String completeDate=rs.getString(3)+" "+rs.getString(4);
						SimpleDateFormat formatterB = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat sdfSource=new SimpleDateFormat("yyyy-MM-dd");
					   	String Sdate =rs.getString(3);
						System.out.println(Sdate);
						Date date = sdfSource.parse(Sdate);	
						Calendar c = Calendar.getInstance();
						c.setTime(date); // Now use today date.
						c.add(Calendar.DATE, 2); // Adding 2 days
						String dateGet = formatterB.format(c.getTime());
						//System.out.println(dateGet);
*/		            	
					
						
		            	writer.append(","+rs.getString(1));
						writer.append(';');
						writer.append(rs.getString(2));
						writer.append(';');
						writer.append(rs.getString(3));
						writer.append(';');
						writer.append(rs.getString(4));
						writer.append(';');
						writer.append(rs.getString(5));
						writer.append(';');
						writer.append(rs.getString(6));
						writer.append(';');
						/*writer.append(rs.getString(7));
						writer.append(';');*/
						writer.append(rs.getString(8));
						writer.append(';');
						writer.append(rs.getString(9));
						writer.append(';');
						writer.append(rs.getString(10));
						writer.append(';');
						writer.append(rs.getString(11));	
						writer.append(';');
						writer.append(rs.getString(12));
						writer.append(';');
						writer.append(rs.getString(13));
						writer.append(';');
						writer.append(rs.getString(14));
						writer.append(';');
						writer.append(rs.getString(15));
						writer.append('\n');
		            }
		            writer.flush();
		            writer.close();
		            
				}else{
					status="ERROR";
				}
			}
			catch (Exception e) {
				status = "ERROR";
				System.out.println("Exception in  getSuspectDataReport ");
				e.printStackTrace();
			} finally {
				try {
					if(con!=null)
						con.close();
					
				} catch (Exception e) {
					System.out.println("Exception in  getSuspectDataReport while closing connection");
					e.printStackTrace();
				}

			}
			return status;
		}
	
 @SuppressWarnings("unchecked")
 public static HashMap getMDtoDistDetails(Connection con,String transid)
 
 {	
	 Statement stmt=null;
	 ResultSet rs=null;
	 ResultSet rs1=null;
	 ResultSet rs2=null;
	 String transactionNo="";
	 String DSID=null;
	 HashMap hm=new HashMap();	 
	 try
	 {	 
		 con=DBConnection.getConnection();
		 stmt=con.createStatement();
		 String sql="select Reference_id from MD_Transaction_details (nolock) where Transaction_No='"+transid+"'";
		 System.out.println(sql);
		 rs=stmt.executeQuery(sql);
		 while(rs.next())
		 {
			 transactionNo=rs.getString(1);
		 }
		 hm.put("transactionId",transid);
		 hm.put("transactionNo",transactionNo);
		 
		 String selectQuery="select request_date,convert(varchar(10),request_time,108),mode_of_payment,ammount_to_credit,bank_charges,Accepted_Amount,bank_name,checque_no,Status,BC_Remark,bank_tran_id,distributor_id from distributor_journal (nolock) where journal_id='"+transactionNo+"'";
		 System.out.println(selectQuery);
		 rs1=stmt.executeQuery(selectQuery);
		 while(rs1.next())
		 {
			 
			 hm.put("Request_Date",rs1.getDate(1));
			 hm.put("Request_Time",rs1.getString(2));
			 hm.put("Mode_of_payment",rs1.getString(3));
			 hm.put("Amount_To_Credit",rs1.getString(4));
			 hm.put("Bank_charges",rs1.getString(5));
			 hm.put("Accepted_Amount",rs1.getString(6));
			 hm.put("Recieving_Bank_Name",rs1.getString(7));			 
			 hm.put("Cheque_DD_No",rs1.getString(8));			 
			 hm.put("Status",rs1.getString(9));				  
			 hm.put("BC_Remark",rs1.getString(10));
			 hm.put("Bank_Tran_Id",rs1.getString(11));	
			 DSID=rs1.getString(12);
		 }
		 String sqlQry="select DSID,distributor_name from distributor_details (nolock) where distributor_id='"+DSID+"'";
		 System.out.println(sqlQry);
		 rs2=stmt.executeQuery(sqlQry);
		 while(rs2.next())
		 {
			 hm.put("DSID",rs2.getString(1));
			 hm.put("DSName",rs2.getString(2));
		 }
	 }catch(Exception e)
	 {
		 System.out.println("Exception in getMDtoDistDetails"+e.toString());
	 }
	 finally
	 {
		 try
		 {			
			 if(rs!=null)
				 rs.close();
			 if(rs1!=null)
				 rs1.close();
			 if(rs2!=null)
				 rs2.close();
			 if(stmt!=null)
				 stmt.close();
			 if(con!=null)
				 con.close();
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in getTransactionDetail while closing connection"+e.toString());
		 }
			
	 }
	 return hm;
	
 }



 @SuppressWarnings("unchecked")
 public static HashMap getAccountAdjustmentDetails(Connection con,String transid)
 	
 {	
	 Statement stmt=null;
	 ResultSet rs=null;
	 ResultSet rs1=null;
	 String transactionNo="";
	 HashMap hm=new HashMap();
	 
	 try
	 {
		 
		 con=DBConnection.getConnection();
		 stmt=con.createStatement();
		 String sql="select Reference_id from MD_Transaction_details (nolock) where Transaction_No='"+transid+"'";
		 rs=stmt.executeQuery(sql);
		 while(rs.next())
		 {
			 transactionNo=rs.getString(1);
		 }
		 hm.put("transactionId",transid);
		 hm.put("transactionNo",transactionNo);
		 
		 String selectQuery="select request_date,convert(varchar(10),request_time,108),mode_of_payment,ammount_to_credit,bank_charges,Accepted_Amount,bank_name,checque_no,Status,BC_Remark,bank_tran_id from distributor_journal (nolock) where journal_id='"+transactionNo+"'";
		 rs1=stmt.executeQuery(selectQuery);
		 while(rs1.next())
		 {	 
			 hm.put("Request_Date",rs1.getString(1));
			 hm.put("Request_Time",rs1.getString(2));
			 hm.put("Mode_of_payment",rs1.getString(3));
			 hm.put("Amount_To_Credit",rs1.getString(4));
			 hm.put("Bank_charges",rs1.getString(5));
			 hm.put("Accepted_Amount",rs1.getString(6));
			 hm.put("Recieving_Bank_Name",rs1.getString(7));			 
			 hm.put("Cheque_DD_No",rs1.getString(8));					 
			 hm.put("Status",rs1.getString(9));				  
			 hm.put("BC_Remark",rs1.getString(10));
			 hm.put("Bank_Tran_Id",rs1.getString(11));		 
		 }
	 }catch(Exception e)
	 {
		 System.out.println("Exception in getMDtoDistDetails"+e.toString());
	 }
	 finally
	 {
		 try
		 {		
			 if(rs!=null)
				 rs.close();
			 if(rs1!=null)
				 rs1.close();
			 if(stmt!=null)
				 stmt.close();
			 if(con!=null)
				 con.close();
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in getTransactionDetail while closing connection"+e.toString());
		 }
			
	 }
	 return hm;
	
 }

 public static HashMap getDetailAccountAdjustment(Connection con,String transid,String user_id){
     
	 
	 Statement stmt=null;
	 ResultSet rs=null;
	 HashMap hamdata=new HashMap();
	 try{
		 con=DBConnection.getConnection();
		 stmt=con.createStatement();
		 
		
		 String sql1="select a.Transaction_No as tid,convert(varchar(10),a.Date_of_Transaction,120) as dot,a.Service as ser,convert(varchar,a.Time_of_Transaction,114) as tot,convert(decimal(16,4),a.tran_amount,0) as reqamt,a.action_on_bal_amount as action,a.Tran_status as status,a.remarks as remark,a.Previous_Bal_amount,a.Final_Bal_amount,a.Tran_amount from md_transaction_details  (nolock) a where a.md_id='"+user_id+"' and a.Transaction_No='"+transid+"'";
		 System.out.println(sql1);
		 rs=stmt.executeQuery(sql1);
		 while(rs.next()){
			
			 hamdata.put("transactionId",rs.getString("tid"));
			 hamdata.put("dateOfTransaction",rs.getString("dot"));
			 hamdata.put("service",rs.getString("ser"));
			 hamdata.put("timeOfTransaction",rs.getString("tot"));
			 hamdata.put("transactionAmount",rs.getString("reqamt"));
			 hamdata.put("actionOnAmount",rs.getString("action"));
			 hamdata.put("status",rs.getString("status"));
			 hamdata.put("remark",rs.getString("remark"));		
			 hamdata.put("previousBalAmount",rs.getString("Previous_Bal_amount"));
			 hamdata.put("finalBalAmount",rs.getString("Final_Bal_amount"));
			 hamdata.put("tranAmount",rs.getString("Tran_amount"));
		
		 }
	 }
	 catch(Exception e){
		 System.out.println("exception is::::::::::::::getDetailAccountAdjustment"+e);
	 }
	 finally{
		 try{
			 if(rs!=null)
				 rs.close();
			 if(stmt!=null)
				 stmt.close(); 
			 if(con!=null)
				 con.close();
		 }
		 catch(Exception e){
			 System.out.println("exception getDetailAccountAdjustment while closing connection"+e);
		 }
	 }
	 return hamdata;
 }
 public static HashMap getAdmintoMDDetails(Connection con,String transid) 
	{	
	 Statement stmt=null;
	 ResultSet rs=null;
	 ResultSet rs1=null;
	 String transactionNo="";
	 HashMap<String, String> hm=new HashMap<String, String>();	 
	 try
	 {  
		 con=DBConnection.getConnection();
		 stmt=con.createStatement();
		 String sql="select Reference_id from MD_Transaction_details (nolock) where Transaction_No='"+transid+"'";
		 System.out.println(sql);
		 rs=stmt.executeQuery(sql);
		 while(rs.next())
		 {
			 transactionNo=rs.getString(1);
		 }
		 hm.put("transactionNo",transid);
		 
		 String selectQuery="select Transaction_Id,Request_Date,convert(varchar(10),Request_Time,108),Mode_of_payment,Amount_To_Credit,Bank_charges,Accepted_Amount,Recieving_Bank_Name,Recieving_Branch_Name,Recieving_Branch_City,Recieving_Bank_Acc_No,Bank_Tran_Id,Refrence_Id,Deposit_Bank_Name,Deposit_Date,Cheque_DD_No,Cheque_date,Cheque_bank_name,Approval_Date,Status,remark,remark_admin from md_journal_details where Transaction_Id='"+transactionNo+"'";
		 System.out.println(selectQuery);
		 rs1=stmt.executeQuery(selectQuery);
		 while(rs1.next())
		 {
			 hm.put("Transaction_Id",rs1.getString(1));
			 hm.put("Request_Date",rs1.getString(2));
			 hm.put("Request_Time",rs1.getString(3));
			 hm.put("Mode_of_payment",rs1.getString(4));
			 hm.put("Amount_To_Credit",rs1.getString(5));
			 hm.put("Bank_charges",rs1.getString(6));
			 hm.put("Accepted_Amount",rs1.getString(7));
			 hm.put("Recieving_Bank_Name",rs1.getString(8));
			 hm.put("Recieving_Branch_Name",rs1.getString(9));
			 hm.put("Recieving_Branch_City",rs1.getString(10));
			 hm.put("Recieving_Bank_Acc_No",rs1.getString(11));
			 hm.put("Bank_Tran_Id",rs1.getString(12));
			 hm.put("Refrence_Id",rs1.getString(13));
			 hm.put("Deposit_Bank_Name",rs1.getString(14));
			 hm.put("Deposit_Date",rs1.getString(15));
			 hm.put("Cheque_DD_No",rs1.getString(16));
			 hm.put("Cheque_date",rs1.getString(17));
			 hm.put("Cheque_bank_name",rs1.getString(18));
			 hm.put("Approval_Date",rs1.getString(19));
			 hm.put("Status",rs1.getString(20));
			 hm.put("remark",rs1.getString(21));
			 hm.put("remark_admin",rs1.getString(22));
		 }
	 }catch(Exception e)
	 {
		 System.out.println("Exception in getMDtoDistDetails"+e.toString());
	 }
	 finally
	 {
		 try
		 {		 
			 if(rs!=null)
				 rs.close();
			 if(rs1!=null)
				 rs1.close();
			 if(stmt!=null)
				 stmt.close();
			 if(con!=null)
				 con.close();
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in getTransactionDetail while closing connection"+e.toString());
		 }
		 
	 }
	 return hm;
	
	}

}