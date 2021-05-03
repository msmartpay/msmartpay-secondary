package com.msmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.JSONArray;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class SearchTransactionDao {

	public JSONObject transactionStatus(String agentId,String txnId)
	{
		JSONObject dataJson=null;

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{
			System.out.println("txnId >>>>>>>>>>>>>>>> : "+txnId);

			//sqlQurry = (new StringBuilder("select top 10 tran_id,mobile_number,amount,status from dbo.live_recharge where status in ('Success','Pending','Refunded','Failure') and service in('mobile','dth','datacard') and user_id='")).append(agentId).append("' and mobile_number='"+mobNo+"' order by date_time desc").toString();
			if(txnId!=null && txnId.length()>0){   


				sqlQurry="select Agent_tran_id,tran_id,mobile_operator,mobile_number,date_of_recharge,requestTime,status from live_recharge  where user_id="+Long.parseLong(agentId)+" and tran_id='"+txnId+"' or Agent_tran_id='"+txnId+"'";
			
				con = DBConnection.getConnection();
				pstmt = con.prepareStatement(sqlQurry);
				rst=pstmt.executeQuery();
				while(rst.next())
				{
					dataJson=new JSONObject();
					dataJson.put("Api_txn_id", rst.getString(1));
					dataJson.put("tran_id", rst.getString(2));
					dataJson.put("txn_id", rst.getString(2));
					dataJson.put("mobile_operator", rst.getString(3));
					dataJson.put("mobile_number", rst.getString(4));
					dataJson.put("dot", rst.getString(5).replace("-", "/"));
					dataJson.put("tot", rst.getString(6));
					dataJson.put("status", rst.getString(7));
					dataJson.put("txnStatus", rst.getString(7));
					dataJson.put("response-code", "0");
					dataJson.put("response-message", "Success");
				}
			
			}else{
				dataJson=new JSONObject();
				dataJson.put("response-code", "1");
				dataJson.put("response-message", "Transaction Id Required.");
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
					rst.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch(Exception exc)
			{
				exc.printStackTrace();
			}
		}
		return dataJson;
	}

	public JSONObject searchTransaction(String agentId,String mobNo,String date)
	{
		JSONObject finalJson=new JSONObject();
		JSONObject dataJson=new JSONObject();
		String service=null;
		String act_bal=null;
		String tran_status=null;
		JSONArray data_array=new JSONArray();

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		try
		{
			System.out.println("mobNo >>>>>>>>>>>>>>>> : "+mobNo);

			/*if(mobNo!=null && mobNo.length()>0){   

				sqlQurry="select td.Id_No,td.Transaction_No,td.service,case when lr.mobile_operator is null then '' else lr.mobile_operator end as mobile_operator ,case when lr.mobile_number is null then '' else lr.mobile_number end as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt,convert(varchar,td.Date_of_Transaction,105) as dot,convert(varchar,td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt "+
						",td.remark from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id where td.agent_id="+Long.parseLong(agentId)+" and lr.mobile_number='"+mobNo+"' and td.Date_of_Transaction='"+date+"' order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
			}else{

				sqlQurry="select td.Id_No,td.Transaction_No,td.service,case when lr.mobile_operator is null then '' else lr.mobile_operator end as mobile_operator ,case when lr.mobile_number is null then '' else lr.mobile_number end as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt,convert(varchar,td.Date_of_Transaction,105) as dot,convert(varchar,td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt "+
						",td.remark from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id where td.agent_id="+Long.parseLong(agentId)+" and td.Date_of_Transaction='"+date+"' order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
			}*/
			if(mobNo!=null && mobNo.length()>0){   


				/*sqlQurry="select td.Id_No,td.Transaction_No,td.service,case when lr.mobile_operator is null then '' else lr.mobile_operator end as mobile_operator ,case when lr.mobile_number is null then '' else lr.mobile_number end as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt,convert(varchar,td.Date_of_Transaction,105) as dot,convert(varchar,td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt "+
						",td.remark from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge  and td.agent_id=lr.user_id where td.agent_id="+Long.parseLong(agentId)+" and lr.mobile_number='"+mobNo+"' and td.Date_of_Transaction='"+date+"' order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
			*/
				sqlQurry="select top 100 td.Id_No,td.Transaction_No,td.service,isnull(lr.mobile_operator,'')  as mobile_operator ,isnull(lr.mobile_number,'') as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt"
						+ ",convert(varchar(10),td.Date_of_Transaction,105) as dot,convert(varchar(8),td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt,td.remark "+
						" from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id"+ 
						" where  td.agent_id="+Long.parseLong(agentId)+" and td.Date_of_Transaction='"+date+"' and lr.mobile_number='"+mobNo+"' order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
				
			
			}else{

				/*sqlQurry="select td.Id_No,td.Transaction_No,td.service,case when lr.mobile_operator is null then '' else lr.mobile_operator end as mobile_operator ,case when lr.mobile_number is null then '' else lr.mobile_number end as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt,convert(varchar,td.Date_of_Transaction,105) as dot,convert(varchar,td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt "+
						",td.remark from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge  and td.agent_id=lr.user_id where td.agent_id="+Long.parseLong(agentId)+" and td.Date_of_Transaction='"+date+"' order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
			*/
				sqlQurry="select top 100 td.Id_No,td.Transaction_No,td.service,isnull(lr.mobile_operator,'')  as mobile_operator ,isnull(lr.mobile_number,'') as mobile_number,td.Action_on_bal_amt,td.Tran_status,td.reqamount,(convert(decimal(18,2),td.DeductedAmt)) as DeductedAmt"
						+ ",convert(varchar(10),td.Date_of_Transaction,105) as dot,convert(varchar(8),td.Time_of_Transaction,108) as tot,td.Agent_balAmt_b_Ded,td.Agent_F_balAmt,td.remark "+
						" from dbo.agent_transaction_details td left join dbo.live_recharge lr on td.Transaction_No=lr.tran_id and td.Date_of_Transaction=lr.Date_of_Recharge and td.agent_id=lr.user_id"+ 
						" where  td.Date_of_Transaction='"+date+"' and td.agent_id="+Long.parseLong(agentId)+" order by td.Date_of_Transaction desc,td.Time_of_Transaction desc";
				
			
			}
			con = DBConnection.getConnection();
			pstmt = con.prepareStatement(sqlQurry);
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
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Wallet Service Credit";
				}else if(service.equalsIgnoreCase("Wallet to Wallet") && act_bal.equalsIgnoreCase("Debit"))
				{
					dataJson.put("mobile_number", "");
					dataJson.put("mobile_operator", "");
					service="Wallet Service Debit";
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
					dataJson.put("mobile_operator", rst.getString(4));
					dataJson.put("mobile_number", rst.getString(5));
				}
				dataJson.put("service", service);

				act_bal=rst.getString(6);
				dataJson.put("Action_on_bal_amt", (act_bal.substring(0,1)).toUpperCase()+act_bal.substring(1, act_bal.length()));

				tran_status=rst.getString(7);
				
				if(null!=tran_status && "Pending".equalsIgnoreCase(tran_status))
					tran_status="Processing";
				
				dataJson.put("tran_status", tran_status);
				dataJson.put("net_amout", rst.getString(8));
				dataJson.put("DeductedAmt", rst.getString(9));
				dataJson.put("dot", rst.getString(10).replace("-", "/"));
				dataJson.put("tot", rst.getString(11));
				dataJson.put("Agent_balAmt_b_Ded", rst.getString(12));
				dataJson.put("Agent_F_balAmt", rst.getString(13));
				data_array.put(dataJson);

			}
			if(data_array.length()>0){
				finalJson.put("dataJSon", data_array.toString());
				finalJson.put("response-code", "0");
				finalJson.put("response-message", "Success");
			}else{
				finalJson.put("response-code", "1");
				finalJson.put("response-message", "Data Not Available.");
			}
			

			rst.close();
			pstmt.close();
			con.close();

			return finalJson;

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
		return null;
	}
}
