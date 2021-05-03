package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class StatementNReportDao {

	static final Logger logger = Logger.getLogger(StatementNReportDao.class);

	public JSONObject statementDao(String agent_id)
	{
		JSONObject dataJson=null;
		JSONObject finalStatement=new JSONObject();
		String service=null;
		String act_bal=null;
		String tran_status=null;
		JSONArray array=new JSONArray();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{
			sqlQurry="select top 100 td.Id_No,td.Transaction_No,td.service,isnull(lr.mobile_operator,'')  as mobile_operator ,isnull(lr.mobile_number,'') as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt"
					+ ",convert(varchar(10),td.Date_of_Transaction,105) as dot,convert(varchar(8),td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt,td.remark "+
					" from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id"+ 
					" where  td.agent_id="+Long.parseLong(agent_id)+" order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";

			logger.debug("sql stat querry   "+sqlQurry);			

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			rst=pstmt.executeQuery();
			while(rst.next())
			{

				dataJson=new JSONObject();
				dataJson.put("Id_No", rst.getString(1));
				dataJson.put("tran_id", rst.getString(2).toUpperCase());
				service=rst.getString(3);
				dataJson.put("mobile_operator", rst.getString(4));
				dataJson.put("mobile_number", rst.getString(5));
				act_bal=rst.getString(6);
				if(service.equalsIgnoreCase("liveMobRech"))
				{
					service="Mobile Recharge";
				}
				else if(service.equalsIgnoreCase("liveDTHRech"))
				{
					service="Dth Recharge";
				}
				else if(service.equalsIgnoreCase("liveDCRech"))
				{
					service="DataCard Recharge";
				}
				else if(service.equalsIgnoreCase("liveBillpay"))
				{
					service="Billpay";
				}else if(service.equalsIgnoreCase("RliveMobRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Mobile";
				}
				else if(service.equalsIgnoreCase("RliveDTHRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "Refund Dth");
					service="Refund Dth";
				}
				else if(service.equalsIgnoreCase("RliveDCRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund DataCard";
				}
				else if(service.equalsIgnoreCase("RliveBillpay"))
				{
					dataJson.put("mobile_number","");
					dataJson.put("mobile_operator", "");
					service="Refund Billpay";
				}else if(service.equalsIgnoreCase("Wallet to Wallet") && act_bal.equalsIgnoreCase("Credit"))
				{
					dataJson.put("mobile_number", rst.getString(14)==null?"":rst.getString(14));
					dataJson.put("mobile_operator", "");
					service="Wallet Balance Received ";
				}else if(service.equalsIgnoreCase("Wallet to Wallet") && act_bal.equalsIgnoreCase("Debit"))
				{
					dataJson.put("mobile_number", rst.getString(14)==null?"":rst.getString(14));
					dataJson.put("mobile_operator", "");
					service="Wallet Balance Transfered ";
				}else if(service.equalsIgnoreCase("DMR"))
				{
					service="Money Transfered";
				}else if(service.equalsIgnoreCase("BEDMR"))
				{
					service="Account Verification";
				}else if(service.equalsIgnoreCase("RDMR"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Money Transfer";
				}else if(service.equalsIgnoreCase("RBEDMR"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Account Verification";

				}else if(service.equalsIgnoreCase("Agent TB-Taken"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Balance Received";
				}else if(service.equalsIgnoreCase("Commission"))
				{
					dataJson.put("mobile_number", "Commission Received");
					dataJson.put("mobile_operator", "");
					service="Commission Received";

				}else if(service.equalsIgnoreCase("AG TB-Push"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Balance Received";
				}
				else if(service.equalsIgnoreCase("Account Adjustment"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Account Adjustment";
				}else if(service.equalsIgnoreCase("TDS"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="TDS Deducted";
				}
				else{

				}
				dataJson.put("service", service);



				dataJson.put("Action_on_bal_amt", (act_bal.substring(0,1)).toUpperCase()+act_bal.substring(1, act_bal.length()));

				tran_status=rst.getString(7);
				if(null!=tran_status && "Pending".equalsIgnoreCase(tran_status))
					tran_status="Processing";

				dataJson.put("tran_status",tran_status);
				dataJson.put("net_amout", rst.getString(8));
				dataJson.put("DeductedAmt", rst.getString(9));
				dataJson.put("dot", rst.getString(10).replace("-", "/"));
				dataJson.put("tot", rst.getString(11));
				dataJson.put("Agent_balAmt_b_Ded", rst.getString(12)==null?"0":rst.getString(12));
				dataJson.put("Agent_F_balAmt", rst.getString(13));
				array.put(dataJson);
			}
			if(array!=null && array.length()>0){
				finalStatement.put("Statement", array.toString());
				finalStatement.put("response-code", "0");
				finalStatement.put("response-message", "Success");
			}else{
				finalStatement.put("response-code", "1");
				finalStatement.put("response-message", "Your have not order any thing yet.");
			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc)
			{

			}
		}
		return finalStatement;
	}
	
	public JSONObject statementDao(String agent_id,String from,String to)
	{
		JSONObject dataJson=null;
		JSONObject finalStatement=new JSONObject();
		String service=null;
		String act_bal=null;
		String tran_status=null;
		JSONArray array=new JSONArray();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{


			sqlQurry="select top 100 td.Id_No,td.Transaction_No,td.service,isnull(lr.mobile_operator,'')  as mobile_operator ,isnull(lr.mobile_number,'') as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt"
					+ ",convert(varchar(10),td.Date_of_Transaction,105) as dot,convert(varchar(8),td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt,td.remark "+
					" from dbo.agent_transaction_details (nolock) td left join dbo.live_recharge (nolock) lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id"+ 
					" where  td.agent_id="+Long.parseLong(agent_id)+" and td.Date_of_Transaction between '"+from+"' and '"+to+"' order by td.Id_No desc";

			logger.debug("sql stat querry   "+sqlQurry);			

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			rst=pstmt.executeQuery();
			while(rst.next())
			{

				dataJson=new JSONObject();
				dataJson.put("Id_No", rst.getString(1));
				dataJson.put("tran_id", rst.getString(2).toUpperCase());
				service=rst.getString(3);
				dataJson.put("mobile_operator", rst.getString(4));
				dataJson.put("mobile_number", rst.getString(5));
				act_bal=rst.getString(6);
				if(service.equalsIgnoreCase("liveMobRech"))
				{
					service="Mobile Recharge";
				}
				else if(service.equalsIgnoreCase("liveDTHRech"))
				{
					service="Dth Recharge";
				}
				else if(service.equalsIgnoreCase("liveDCRech"))
				{
					service="DataCard Recharge";
				}
				else if(service.equalsIgnoreCase("liveBillpay"))
				{
					service="Billpay";
				}else if(service.equalsIgnoreCase("RliveMobRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Mobile";
				}
				else if(service.equalsIgnoreCase("RliveDTHRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "Refund Dth");
					service="Refund Dth";
				}
				else if(service.equalsIgnoreCase("RliveDCRech"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund DataCard";
				}
				else if(service.equalsIgnoreCase("RliveBillpay"))
				{
					dataJson.put("mobile_number","");
					dataJson.put("mobile_operator", "");
					service="Refund Billpay";
				}else if(service.equalsIgnoreCase("Wallet to Wallet") && act_bal.equalsIgnoreCase("Credit"))
				{
					dataJson.put("mobile_number", rst.getString(14)==null?"":rst.getString(14));
					dataJson.put("mobile_operator", "");
					service="Wallet Balance Received ";
				}else if(service.equalsIgnoreCase("Wallet to Wallet") && act_bal.equalsIgnoreCase("Debit"))
				{
					dataJson.put("mobile_number", rst.getString(14)==null?"":rst.getString(14));
					dataJson.put("mobile_operator", "");
					service="Wallet Balance Transfered ";
				}else if(service.equalsIgnoreCase("DMR"))
				{
					service="Money Transfered";
				}else if(service.equalsIgnoreCase("BEDMR"))
				{
					service="Account Verification";
				}else if(service.equalsIgnoreCase("RDMR"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Money Transfer";
				}else if(service.equalsIgnoreCase("RBEDMR"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Refund Account Verification";

				}else if(service.equalsIgnoreCase("Agent TB-Taken"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Balance Received";
				}else if(service.equalsIgnoreCase("Commission"))
				{
					dataJson.put("mobile_number", "Commission Received");
					dataJson.put("mobile_operator", "");
					service="Commission Received";

				}else if(service.equalsIgnoreCase("AG TB-Push"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Balance Received";
				}
				else if(service.equalsIgnoreCase("Account Adjustment"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Account Adjustment";
				}else if(service.equalsIgnoreCase("TDS"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="TDS Deducted";
				}
				else{

				}
				dataJson.put("service", service);



				dataJson.put("Action_on_bal_amt", (act_bal.substring(0,1)).toUpperCase()+act_bal.substring(1, act_bal.length()));

				tran_status=rst.getString(7);
				if(null!=tran_status && "Pending".equalsIgnoreCase(tran_status))
					tran_status="Processing";

				dataJson.put("tran_status",tran_status);
				dataJson.put("net_amout", rst.getString(8));
				dataJson.put("DeductedAmt", rst.getString(9));
				dataJson.put("dot", rst.getString(10).replace("-", "/"));
				dataJson.put("tot", rst.getString(11));
				dataJson.put("Agent_balAmt_b_Ded", rst.getString(12)==null?"0":rst.getString(12));
				dataJson.put("Agent_F_balAmt", rst.getString(13));
				array.put(dataJson);
			}
			if(array!=null && array.length()>0){
				finalStatement.put("Statement", array.toString());
				finalStatement.put("response-code", "0");
				finalStatement.put("response-message", "Success");
			}else{
				finalStatement.put("response-code", "1");
				finalStatement.put("response-message", "Your have not order any thing yet.");
			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc)
			{

			}
		}
		return finalStatement;
	}


	public JSONObject orderHistory(String agent_id)
	{
		JSONObject dataJson=null;
		String service=null;
		JSONArray array=new JSONArray();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{


			sqlQurry="select top 20 Order_id,convert(varchar(10),Order_Date,105) as OrderDate,convert(varchar(8),Order_Time,108) as OrderTime,Status,Payment_type,Grand_total,Service_Type,Contact_No from Customer_Order_Details where Agent_id="+Long.parseLong(agent_id)+" order by Order_Date desc,Order_Time desc";

			logger.debug("sql stat querry   "+sqlQurry);			

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			rst=pstmt.executeQuery();
			while(rst.next())
			{

				dataJson=new JSONObject();
				dataJson.put("orderId", rst.getString(1));
				dataJson.put("date", rst.getString(2));
				dataJson.put("time", rst.getString(3));
				dataJson.put("status", rst.getString(4));
				dataJson.put("payment", rst.getString(5));
				dataJson.put("orderAmount", rst.getString(6));
				dataJson.put("mobile", rst.getString(8));

				service=rst.getString(7);
				if(service.equalsIgnoreCase("Food"))
				{
					service="Food Order";
				}
				else if(service.equalsIgnoreCase("Flower"))
				{
					service="Flower Order";
				}
				else if(service.equalsIgnoreCase("Bakery"))
				{
					service="Cake Order";
				}

				dataJson.put("service", service);

				array.put(dataJson);
			}
			JSONObject finalStatement=new JSONObject();

			if(array!=null && array.length()>0){
				finalStatement.put("Statement", array.toString());
				finalStatement.put("response-code", "0");
				finalStatement.put("response-message", "Success");
			}else{
				finalStatement.put("response-code", "1");
				finalStatement.put("response-message", "Your have not order any thing yet.");
			}
			return finalStatement;

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
					rst.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc)
			{

			}
		}
		return null;
	}

	public JSONObject sendrHistory(String senderId)
	{
		JSONObject dataJson=null;
		JSONArray array=new JSONArray();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{
			sqlQurry="select a.Sender_Id,a.Transaction_No,convert(varchar(10),a.Date_of_Transaction,120),convert(varchar(8),a.Time_of_Transaction,120),a.ReqAmount,b.Tran_status,isnull(a.Reference_id,'') as bank_ref,"+
					"isnull(a.Bene_Name,'') as bene_name,isnull(a.Bene_Bank_Name,'') as bene_bank_name,isnull(a.Bene_Bank_IFSC,'') as bene_bank_ifsc, "+
					"isnull(a.Bene_Account,'') as bene_account,isnull(a.TransactionType,'') as transaction_type from DMR_SENDER_TRANSACTION_DETAILS a join agent_transaction_details b on a.Transaction_No=b.Transaction_No where a.Sender_Id='"+senderId+"' order by a.ID_no desc";

			logger.debug("sql stat querry   "+sqlQurry);			

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			rst=pstmt.executeQuery();
			while(rst.next())
			{

				dataJson=new JSONObject();
				dataJson.put("SenderId",rst.getString(1));
				dataJson.put("TranNo",rst.getString(2));
				dataJson.put("Dot", rst.getString(3));
				dataJson.put("Tot", rst.getString(4));
				dataJson.put("Amount", rst.getString(5));
				dataJson.put("Status", rst.getString(6));
				dataJson.put("BankrefId", rst.getString(7));
				dataJson.put("BeneName", rst.getString(8));
				dataJson.put("BeneBankName", rst.getString(9));
				dataJson.put("BeneBankIfsc", rst.getString(10));
				dataJson.put("BeneAccount", rst.getString(11));
				dataJson.put("TransactionType", rst.getString(12));
				array.put(dataJson);
			}

			JSONObject finalStatement=new JSONObject();

			if(array!=null && array.length()>0){
				finalStatement.put("Statement", array.toString());
				finalStatement.put("Status", "0");
				finalStatement.put("message", "Success");
			}else{
				finalStatement.put("Status", "1");
				finalStatement.put("message", "Data not Available");
			}
			return finalStatement;

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
					rst.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc)
			{

			}
		}
		return null;
	}
}