package com.mdamountrequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.db.DBConnection;

public class MdAmountRequestDao {

	public HashMap getMdBalance(){
		Connection con=null;	
		Statement stmt=null;
		ResultSet rs=null;
		HashMap hv=new HashMap();
		try
		{
			System.out.println("list :");
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select Trade_Balance_Limit from trade_Balance_Limit_Setup where user_type='mds'";

			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(sql);
				hv.put("TradeBalanceLimit",rs.getString("Trade_Balance_Limit")); 
				//hv.put("TradeBalanceMaxLimit",rs.getString("Trade_Balance_Max_Limit"));
				System.out.println("TradeBalanceLimit" +rs.getString("Trade_Balance_Limit"));
				//System.out.println("TradeBalanceMaxLimit"+rs.getString("Trade_Balance_Max_Limit"));

			}
		}catch(Exception e)
		{
			System.out.println("exception in getModeOfPay in getMdBalance "+e.toString());
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
				System.out.println("Exception in getMdBalance in MdAmountRequestDao while closing connection "+e.toString());
			}

		}
		return hv;

	}	

	public static HashMap getModeOfPaymentDetails(String payMode,String amount,String RecBankName )	{
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		HashMap hm=new HashMap();
		double charges=0.00;
		String chargeType="";
		double acceptedAmt=0.00;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="Select  top 1 charges,charges_Type,Account_No,BranchName  from MD_mode_of_payment where Limit_Amount<="+amount+" and mode_of_payment='"+payMode+"' and Bank_Name='"+RecBankName+"' order by Limit_Amount desc";
			System.out.println("select query for bank charges is"+sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				charges=rs.getDouble("charges");
				chargeType=rs.getString("charges_Type");
				String accNo=rs.getString("Account_No");
				String BranchName=rs.getString("BranchName");
				hm.put("charges",charges);
				hm.put("accNo",accNo);
				hm.put("RecBrName",BranchName);

			}
			double amt=Double.parseDouble(amount);
			if(chargeType.equalsIgnoreCase("R"))
			{
				acceptedAmt=amt-charges;
			}
			if(chargeType.equalsIgnoreCase("P"))
			{
				acceptedAmt=(amt * charges)/100;
			}
			hm.put("acceptedAmt",acceptedAmt);
		}catch(Exception e)
		{
			System.out.println("error in getModeOfPaymentDetails in MdAmountRequestDao "+e.toString());
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
				System.out.println("Exception in getModeOfPaymentDetails OF MdAmountRequestDao"+e.toString());
			}

		}
		return hm;
	}
	public static HashMap getCrPaymentDetails(String payMode,String amount,String creditdaysReq,String reqDate,String creditdateReq)	{
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		HashMap hm=new HashMap();
		double charges=0.00;
		String chargeType="";
		double acceptedAmt=0.00;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="Select  top 1 charges,charges_Type,credit_req_days,payment_commitment_days  from MD_mode_of_payment where Limit_Amount<="+amount+" and mode_of_payment='"+payMode+"' and credit_req_days='"+creditdaysReq+"'  order by Limit_Amount desc";
			System.out.println("select query for bank charges is"+sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{

				charges=rs.getDouble("charges");
				chargeType=rs.getString("charges_Type");
				int daysrequested=rs.getInt("payment_commitment_days");

				System.out.println("days requested are"+daysrequested);
				hm.put("charges",charges);


				DateFormat formatter ;
				Calendar cal = Calendar.getInstance();
				Date date ; 
				formatter = new SimpleDateFormat("yyyy-MM-dd");
				date = (Date)formatter.parse(creditdateReq);
				cal.setTime(date);
				cal.add(Calendar.DATE,daysrequested);
				Date newdate=cal.getTime();


				String paymentDate = formatter.format(newdate);
				System.out.println("commitment date is"+paymentDate);

				hm.put("paymentDate",paymentDate);


			}
			double amt=Double.parseDouble(amount);
			if(chargeType.equalsIgnoreCase("R"))
			{
				acceptedAmt=amt-charges;
			}
			if(chargeType.equalsIgnoreCase("P"))
			{
				acceptedAmt=(amt * charges)/100;
			}
			hm.put("acceptedAmt",acceptedAmt);
		}catch(Exception e)
		{
			System.out.println("error in getCrPaymentDetails in MdAmountRequestDao "+e.toString());
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
				System.out.println("Exception in getCrPaymentDetails OF MdAmountRequestDao"+e.toString());
			}

		}
		return hm;
	}
	public static String saveNeftDep(String corpAgentId,String TransactionId,String reqDate,String payMode,String amount,String NeftTransferTid,String NeftRefNo,String NeftDepBank,String NeftDepDate,String NeftDepTime,String NeftRecBankName,String accNo,String NeftRecBrName,double charges,double acceptAmt,String neftremarks,String NeftDepAccNo)
	{
		Statement stmt=null;
		Connection con=null;
		String result="";
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="INSERT INTO MD_Journal_Details(md_id,Transaction_Id,Request_Date,Request_Time,Mode_of_payment,Amount_To_Credit,Bank_charges,Accepted_Amount,Recieving_Bank_Name,Recieving_Branch_Name,Recieving_Bank_Acc_No,Bank_Tran_Id,Refrence_Id,Deposit_Bank_Name,Deposit_Date,Status,Remark,Depositor_Account_Number)values ('"+corpAgentId+"','"+TransactionId+"','"+sqlDate+"','"+sqlTime+"','"+payMode+"','"+amount+"','"+charges+"','"+acceptAmt+"','"+NeftRecBankName+"','"+NeftRecBrName+"','"+accNo+"','"+NeftTransferTid+"','"+NeftRefNo+"' ,'"+NeftDepBank+"','"+NeftDepDate+" "+NeftDepTime+"','Pending','"+neftremarks+"','"+NeftDepAccNo+"')";
			System.out.println("insert query for Neft deposit"+sql);
			int flag=stmt.executeUpdate(sql);
			if(flag>0)
			{
				result="success";
			}
			else
			{
				result="unsuccess";
			}

		}catch(Exception e)
		{
			result="unsuccess";
			System.out.println("error in saveNeftDep in MdAmountRequestDao "+e.toString());
		}
		finally
		{
			try
			{

				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in saveNeftDep OF MdAmountRequestDao"+e.toString());
			}

		}
		return result;
	}
	public static String saveCreditDep(String corpAgentId,String TransactionId,String reqDate,String payMode,String amount,String creditdaysReq,String creditdateReq,String paymentDate,double charges,double acceptAmt,String creditremarks)	{
		Statement stmt=null;
		Connection con=null;
		String result="";
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="INSERT INTO MD_Journal_Details(md_id,Transaction_Id,Request_Date,Request_Time,Mode_of_payment,Amount_To_Credit,Bank_charges,Accepted_Amount,Status,Credit_date,Credit_days,commitment_date,Remark)values ('"+corpAgentId+"','"+TransactionId+"','"+sqlDate+"','"+sqlTime+"','"+payMode+"','"+amount+"','"+charges+"','"+acceptAmt+"','Pending','"+creditdateReq+"','"+creditdaysReq+"','"+paymentDate+"','"+creditremarks+"')";
			System.out.println("insert query for Credit deposit"+sql);
			int flag=stmt.executeUpdate(sql);
			if(flag>0)
			{
				result="success";
			}
			else
			{
				result="unsuccess";
			}

		}catch(Exception e)
		{
			result="unsuccess";
			System.out.println("error in saveCreditDep in MdAmountRequestDao "+e.toString());
		}
		finally
		{
			try
			{

				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in saveCreditDep OF MdAmountRequestDao"+e.toString());
			}

		}
		return result;
	}


	public ArrayList getdepositDetail(String agent_id,HttpSession session) {

		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ArrayList depositDetail=new ArrayList();

		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			rs1=stmt.executeQuery("select agent_id, TotCash, usedcash , cutoff_amount, last_amount from agent_amount where agent_id='"+agent_id+"' and agent_type='A'");
			if(rs1.next())
			{	
				double TotCash= rs1.getDouble("TotCash");
				double usedcash= rs1.getDouble("usedcash");
				double cutoff_amount= rs1.getDouble("cutoff_amount");
				double last_amount= rs1.getDouble("last_amount");
				double balance = TotCash-usedcash;
				double percentUsed=0.0;
				if(balance!=0.0)
					percentUsed = (balance*100)/TotCash;
				DecimalFormat df=new DecimalFormat("0");

				session.setAttribute("percentUsed",df.format(percentUsed));
				session.setAttribute("TotCash",df.format(TotCash));
				session.setAttribute("usedcash",df.format(usedcash));
				session.setAttribute("balance",df.format(balance));
				session.setAttribute("cutoff_amount",df.format(cutoff_amount));
				session.setAttribute("last_amount",df.format(last_amount));							


			}
			System.out.println("check......1");
			String sqlcount="SELECT count(*) FROM agent_journal where agent_id='"+agent_id+"'";
			//System.out.println("check......2");
			rs2=stmt.executeQuery(sqlcount);
			//System.out.println("check......3");
			//System.out.println("............."+sqlcount);
			//System.out.println("check......4");
			int a=0;
			int b=0;
			int c=0;
			String sqlr="";
			//System.out.println("?????????????????"+a);
			while(rs2.next())
			{
				a=rs2.getInt(1);
				//System.out.println("value of a is"+a);

			}
			if(a==1||a==2||a==3||a==4||a==5||a==6||a==7||a==8||a==9||a==10)
			{
				b=0;
				//addend

				// String sql="select ammount_to_credit,remarks,DATE_FORMAT(request_date,'%d-%m-%Y'),request_time,status,mode_of_payment,journalid,initial from distributor_journal where distributor_id='"+agent_id+"'";
				sqlr="select top "+a+"  ammount_to_credit,journal_id,request_time,convert(varchar(10),request_date,111),status,mode_of_payment,remarks from agent_journal where agent_id='"+agent_id+"'";
				System.out.println("select form agent_journal is"+""+sqlr);	
			}//add2
			if(a>10)
			{
				b=a-10;
				c=b+10;
				//System.out.println("value of b is"+b);
				sqlr="select ammount_to_credit,journal_id,request_time,convert(varchar,request_date,111)as date,status,mode_of_payment,remarks  from"+
						"( SELECT  ROW_NUMBER() OVER (ORDER BY request_date ASC)as row, ammount_to_credit,journal_id,request_time,request_date,status,mode_of_payment,remarks "+
						"from agent_journal where agent_id='"+agent_id+"')as mytable where  mytable.row>="+b+" AND mytable.row<="+c+"";

				System.out.println("select form agent_journal is"+""+sqlr);	
			}
			//add2

			rs=stmt.executeQuery(sqlr);
			while(rs.next()){
				HashMap temp=new HashMap();
				temp.put("amount",rs.getString(1)); 
				temp.put("journalid",rs.getString(2)); 
				temp.put("time",rs.getString(3)); 
				temp.put("date",rs.getString(4)); 
				temp.put("status",rs.getString(5));
				temp.put("mode",rs.getString(6));
				temp.put("remark",rs.getString(7));
				depositDetail.add(temp);			 
			}





		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return depositDetail;
	}



	public String savedeposit(String user_id,String distributor_id,String amount,String modeofpay,String depositdate,String cashBankName,String cashBranch,String cashCity,String ddBank,String ddBranch,String ddCity,String ddNumber,String ddDate,String ebank,String ebranch,String eaccno,String etranid,String rbank,String rbranch,String Rcity,String Raccno,String Rtranid,String recieptno,String remark) {
		String address="";
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		Statement stmt=null;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());


		String  TransactionId="";
		String prefix="";

		Date now = new Date();
		long logntranId = now.getTime();

		int length = user_id.length();


		if(length<=4)
		{
			prefix="100"+user_id;
		}
		if(length>=6)
		{
			prefix="1"+user_id;
		}
		String suffix = new String();

		suffix=String.valueOf(logntranId);

		TransactionId=prefix+suffix;
		Connection con=null;


		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(modeofpay.equalsIgnoreCase("cash deposit"))
			{

				String sql="insert into agent_journal (agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,branch_city,deposit_date,remarks,status,distributor_id) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+cashBankName+"','"+cashBranch+"','"+cashCity+"','"+depositdate+"','"+remark+"','pending','"+distributor_id+"')";
				// System.out.println("depositr query is for cash is  >>>>>>>>>>>>"+sql);
				stmt.executeUpdate(sql);

			}

			else if(modeofpay.equalsIgnoreCase("cheque deposit"))
			{

				String sql="insert into agent_journal (agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,branch_city,cheque_dd_date,checque_no,remarks,status,distributor_id) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+ddBank+"','"+ddBranch+"','"+ddCity+"','"+ddDate+"','"+ddNumber+"','"+remark+"','Pending','"+distributor_id+"')";
				//System.out.println("depositr query is for dd and cheque is  >>>>>>>>>>>>"+sql);

				stmt.executeUpdate(sql);


			}
			else if(modeofpay.equalsIgnoreCase("E-Transfer"))
			{

				String sql="insert into agent_journal (agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,account_no,transaction_id,remarks,status,distributor_id,deposit_date) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+ebank+"','"+ebranch+"','"+eaccno+"','"+etranid+"','"+remark+"','Pending','"+distributor_id+"','"+depositdate+"')";

				// System.out.println("depositr query is for e Tran is  >>>>>>>>>>>>"+sql);
				stmt.executeUpdate(sql);
			}
			else if(modeofpay.equalsIgnoreCase("RTGS/NEFT"))
			{

				String sql="insert into agent_journal (agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,account_no,transaction_id,remarks,status,distributor_id,deposit_date) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+rbank+"','"+rbranch+"','"+Raccno+"','"+Rtranid+"','"+remark+"','Pending','"+distributor_id+"','"+depositdate+"')";

				// System.out.println("depositr query is for RTGS/NEFT is  >>>>>>>>>>>>"+sql);
				stmt.executeUpdate(sql);
			}
			else 
			{

				String sql="insert into agent_journal (agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,transaction_id,remarks,status,distributor_id,deposit_date) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+recieptno+"','"+remark+"','Pending','"+distributor_id+"','"+depositdate+"')";

				// System.out.println("depositr query is for cash on desk is  >>>>>>>>>>>>"+sql);
				stmt.executeUpdate(sql);
			}





		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{

				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return address;
	}	

	public String getAgencyName(String agent_id) {
		String agency_name="";
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select agency_name from agent_details where agent_id='"+agent_id+"'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()){

				agency_name=rs.getString(1); 
				System.out.println("agency_name="+agency_name);
			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return agency_name;
	}



	/*                                counter details                                      */

	public ArrayList getCounterdepositDetail(String agent_id,HttpSession session) {

		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ArrayList depositDetail=new ArrayList();

		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			rs1=stmt.executeQuery("select sub_agent_id, TotCash, usedcash , cutoff_amount, last_amount from sub_agent_amount where sub_agent_id='"+agent_id+"' and agent_type='A'");
			if(rs1.next())
			{	
				double TotCash= rs1.getDouble("TotCash");
				double usedcash= rs1.getDouble("usedcash");
				double cutoff_amount= rs1.getDouble("cutoff_amount");
				double last_amount= rs1.getDouble("last_amount");
				double balance = TotCash-usedcash;
				double percentUsed=0.0;
				if(balance!=0.0)
					percentUsed = (balance*100)/TotCash;
				DecimalFormat df=new DecimalFormat("0");

				session.setAttribute("percentUsed",df.format(percentUsed));
				session.setAttribute("TotCash",df.format(TotCash));
				session.setAttribute("usedcash",df.format(usedcash));
				session.setAttribute("balance",df.format(balance));
				session.setAttribute("cutoff_amount",df.format(cutoff_amount));
				session.setAttribute("last_amount",df.format(last_amount));							


			}


			String sql="select ammount_to_credit,remarks,convert(varchar(10),request_date,120),request_time,status,mode_of_payment from counter_journal where sub_agent_id='"+agent_id+"'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				HashMap temp=new HashMap();
				temp.put("amount",rs.getString(1)); 
				temp.put("remark",rs.getString(2)); 
				temp.put("date",rs.getString(3)); 
				temp.put("time",rs.getString(4)); 
				temp.put("status",rs.getString(5));
				temp.put("mode",rs.getString(6));
				depositDetail.add(temp);			 
			}


		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return depositDetail;
	}



	public String savecounterdeposit(String user_id,String agentid,String TransactionId,String amount,String modeofpay,String depositdate,String cashBankName,String cashBranch,String cashCity,String ddBank,String ddBranch,String ddCity,String ddNumber,String ddDate,String creditBank,String cardType,String cardNumber,String cardfrommon,String cardfromyear,String cardHolder,String cardApproved,String remark) {
		String address="";
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		Statement stmt=null;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());

		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(modeofpay.equals("cash deposit"))
			{

				String sql="insert into counter_journal (sub_agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,branch_city,deposit_date,remarks,status,agent_id) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+cashBankName+"','"+cashBranch+"','"+cashCity+"','"+depositdate+"','"+remark+"','pending','"+agentid+"')";

				stmt.executeUpdate(sql);

			}

			else if(modeofpay.equals("cheque deposit"))
			{

				String sql="insert into counter_journal (sub_agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,branch_name,branch_city,cheque_dd_date,checque_no,remarks,status,agent_id) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+ddBank+"','"+ddBranch+"','"+ddCity+"','"+ddDate+"','"+ddNumber+"','"+remark+"','Pending','"+agentid+"')";
				stmt.executeUpdate(sql);


			}
			else
			{

				String sql="insert into counter_journal (sub_agent_id,journal_id,journal_date,request_date,request_time,mode_of_payment,ammount_to_credit,bank_name,cardtype,creditcard_no,approved_by,remarks,status,agent_id) values ('"+user_id+"','"+TransactionId+"','"+sqlDate+"','"+sqlDate+"','"+sqlTime+"','"+modeofpay+"','"+amount+"','"+creditBank+"','"+cardType+"','"+cardNumber+"','"+cardApproved+"','"+remark+"','Pending','"+agentid+"')";

				stmt.executeUpdate(sql);
			}





		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{

				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return address;
	}	


	public Vector getcounterReqDetail(String agent_id) {

		Statement stmt=null;
		ResultSet rs=null;
		Vector vectordata=new Vector();

		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();


			String sql="select Sub_agent_LoginId,Sub_Agent_Mob_No,journal_id,ammount_to_credit,remarks,DATE_FORMAT(request_date,'%d-%d-%Y') as requestDate,request_time,a.status,mode_of_payment,checque_no from counter_journal a,sub_agent_counter_details b where  a.status='pending' and a.sub_agent_id=b.sub_agent_id and a.agent_id='"+agent_id+"'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())

			{
				HashMap temp=new HashMap();
				temp.put("counterId",rs.getString(1)); 
				temp.put("mobileNo",rs.getString(2));
				temp.put("journalId",rs.getString(3)); 
				temp.put("amount",rs.getString(4)); 
				temp.put("remark",rs.getString(5)); 
				temp.put("date",rs.getString(6)); 
				temp.put("time",rs.getString(7)); 
				temp.put("status",rs.getString(8));
				temp.put("mode",rs.getString(9));
				temp.put("checqueNo",rs.getString(10));
				vectordata.add(temp);			 
			}


		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return vectordata;
	}

	public String updateCounterAccount1(String journalId,String user_id) {

		Statement stmt=null;
		ResultSet rs=null;
		String status="";
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			stmt.executeUpdate("update counter_journal set status='declined', approval_date='"+sqlDate+"' where journal_id='"+journalId+"'");
			status="success";

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return status;
	}


	public String updateCounterAccount2(String journalId,double transferamount,double dbnkcharge,String user_id,double damount,String cleintIpaddress,HttpSession session) {

		Statement stmt=null;
		ResultSet rs=null;
		String status="";
		double agentBalamount=0;
		DecimalFormat dff = new DecimalFormat("##.##");
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
		Connection con=null;


		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			String sql="SELECT totcash-usedCash FROM agent_amount where agent_id=(SELECT agent_id FROM counter_journal where journal_id='"+journalId+"')";
			System.out.println("sql query is "+sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())

			{
				agentBalamount=rs.getDouble(1);



			}
			if(agentBalamount>transferamount)
			{


				double agentBalamountNew=agentBalamount-transferamount;

				String agbalAmt=dff.format(agentBalamount);
				String agbalAmtNew=dff.format(agentBalamountNew);
				String counterTransAmt=dff.format(transferamount);
				String bnckCharges=dff.format(dbnkcharge);





				stmt.executeUpdate("update agent_amount set usedCash=usedCash+"+counterTransAmt+",last_amount='"+transferamount+"' where agent_id=(SELECT agent_id FROM counter_journal where journal_id='"+journalId+"')");
				stmt.executeUpdate("update sub_agent_amount set totcash=totcash+"+transferamount+",last_amount='"+transferamount+"' where sub_agent_id=(SELECT sub_agent_id FROM counter_journal where journal_id='"+journalId+"')");
				stmt.executeUpdate("update counter_journal set status='accepted',accepted_amount='"+counterTransAmt+"',approval_date='"+sqlDate+"' where journal_id='"+journalId+"'");
				stmt.executeUpdate("insert into  agent_Transaction_Details(UserType,Agent_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession,DeductedAmt, Agent_balAmt_A_Ded,Tran_status,Updated_date, updated_time,Agent_F_balAmt,IpAddress,Remark) values('Agent',"+user_id+",'"+journalId+"','"+sqlDate+"','CounterDeposit',"+agbalAmt+",'"+damount+"',"+bnckCharges+","+counterTransAmt+","+agbalAmtNew+",'success','"+sqlDate+"','"+sqlTime+"',"+agbalAmtNew+",'"+cleintIpaddress+"','')");

				rs=stmt.executeQuery("SELECT totcash-usedCash as balance  FROM agent_amount where agent_id=(SELECT agent_id FROM counter_journal where journal_id='"+journalId+"')");
				while(rs.next())

				{       
					session.setAttribute("balance",rs.getString(1));
				}
				status="success";
			}
			else {
				status="unsuccess";


			}


		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return status;
	}



	public String getTranstatus(String JournalId) {

		Statement stmt=null;
		ResultSet rs=null;
		String status="";	
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();


			rs=stmt.executeQuery("SELECT status FROM counter_journal where journal_id='"+JournalId+"'");
			while(rs.next())

			{
				status=rs.getString(1);
			}


		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return status;
	}

}