package com.msmart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;

import com.msmart.dbconnection.DBConnection;


public class MobileAppRechargeDao {

	static final Logger logger = Logger.getLogger(MobileAppRechargeDao.class);
	public HashMap<String, String> getServiceStatus(String agentid,String agent_initial,String operator,String service,String mdId)
	{
		long md_id=-1L;
		if(mdId!=null)
			md_id=Long.parseLong(mdId);

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String service_Status=null,vendor=null;
		HashMap<String, String> resMap=new HashMap<String, String>();
		try
		{

			con=DBConnection.getConnection();
			sqlQurry="select Active ,Vendor from dbo.newagentservice where operator=? and Sub_Service=? and MD_id="+md_id;
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1, operator);
			pstmt.setString(2,service);
			System.out.println("Operator "+operator+"  service  "+service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				service_Status=rst.getString(1);
				vendor=rst.getString(2);
			}
			logger.debug("Status  "+service_Status);
			if(service_Status==null)
			{
				resMap.put("Status","N");
				resMap.put("msg", "Undefind requested serviec!");
			}
			else if(service_Status.equalsIgnoreCase("Y"))
			{
				sqlQurry="select recharge from dbo.agent_details where agent_initial=? and convert(varchar,agent_id)=?";
				pstmt.close();
				rst.close();
				pstmt=con.prepareStatement(sqlQurry);
				pstmt.setString(1, agent_initial);
				pstmt.setString(2, agentid);
				rst=pstmt.executeQuery();
				while(rst.next())
				{
					service_Status=rst.getString(1);
				}
				if(service_Status.equalsIgnoreCase("Y"))
				{
					resMap.put("Status","Y");
					resMap.put("msg", "success");
					resMap.put("vendor", vendor);
				}
				else
				{
					resMap.put("Status","N");
					resMap.put("msg", "Your Recharge Service is Blocked!");
				}
			}
			else 
			{
				resMap.put("Status","N");
				resMap.put("msg", "Requested service has been deactivated!");
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
			}catch(Exception exc){}
		}
		return resMap;
	}

	public String updateMarsRefPending(String status,String transaction_No,String marsarefId)
	{
		String sts="failure";
		Connection con=null;
		PreparedStatement pstmt=null;
		String sqlQurry=null;
		ResultSet rst=null;
		try
		{
			sqlQurry="update dbo.live_recharge set USessionID=? where tran_id=?";
			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,marsarefId);
			pstmt.setString(2,transaction_No);
			pstmt.executeUpdate();
			con.commit();
			sts="success";
		}catch(Exception exc)
		{
			try {
				if(con!=null)
					con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(" Execption in LIVE MRA APP ");				
				e.printStackTrace();
			}
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
			}catch(Exception exc){}
		}
		return sts;
	}

	public HashMap<String, String> getAPIDetails(String agentID) 
	{
		String[] arr=agentID.split("-");
		String ag_id=arr[0];
		System.out.println("AG ID :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+ag_id);
		HashMap<String, String> map= null;
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rst=null;
		try
		{
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call getAPIdetails_for_mobileapp(?,?,?,?,?,?,?)}");
			cstmt.setString(1,ag_id);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			rst=cstmt.executeQuery();
			while(rst.next())
			{
				if(rst.getString(7).equalsIgnoreCase("Y"))
				{
					map= new HashMap<String, String>();
					map.put("APICORPAGTXNAUTHID", rst.getString(2));
					map.put("APICORPAGTXNPWD", rst.getString(3));
					map.put("APICORPSAGTXNAUTHID", rst.getString(4));
					map.put("APICORPSAGTXNAUTHPWD", rst.getString(5));
					map.put("APICORPSAGREGMOBNO", rst.getString(6));
					map.put("Status", rst.getString(7));
				}

			}
			System.out.println("MobileAppRechargeDao.getAPIDetails()>>>>>>>>>>>>>>>>>>>>>>  Central : "+map);
			rst.close();
			cstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(cstmt!=null)
				{
					cstmt.close();
					cstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc){

			}
		}
		return map;
	}

	public String getAI_DI_MI_Details(String agent_Id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String agent_detail_id=null;
		try
		{
			sqlQurry="select a.agent_initial+convert(varchar,a.agent_id) as AG,b.distributor_initial+convert(varchar,b.distributor_id) as DS,c.md_initial+convert(varchar,c.md_id) as MD,c.md_id as MdId from agent_details a,distributor_details b,md_details c where a.distributor_id=b.distributor_id and b.md_id=c.md_id and a.agent_id=?";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,agent_Id);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				agent_detail_id=rst.getString(1)+"-"+rst.getString(2)+"-"+rst.getString(3)+","+rst.getString(4);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs In Live MRA "+exc);
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
		return agent_detail_id;
	}

	public String getAgentBalance(String agent_Id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String balance=null;
		DecimalFormat myFormatter = new DecimalFormat("##.##");
		try
		{
			sqlQurry="select (TotCash -usedcash) as balance from dbo.agent_amount where agent_id=?";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,agent_Id);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				balance=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
			if(balance!=null)
				balance=myFormatter.format(Double.parseDouble(balance));
			else 
				balance="00.00";
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
				exc.printStackTrace();
				System.out.println("inside Live MRA App ");
			}
		}
		return balance;
	}

	public  String getAgentCommission_Agent(String agent_Id,String rechargeamount,String operator,String service)
	{
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rst=null;
		String commission=null; 
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call getCommision(?,?,?,?)}");
			cstmt.setString(1,agent_Id);
			cstmt.setString(2, service);
			cstmt.setString(3, operator);
			cstmt.setString(4, rechargeamount);
			rst=cstmt.executeQuery();
			while(rst.next())
			{
				commission=rst.getString(1);
			}
			logger.info("commission   "+commission);
			rst.close();
			cstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
		}
		finally
		{
			try{
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
				if(cstmt!=null)
				{
					cstmt.close();
					cstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc){

			}
		}
		return commission;
	}



	public String getOperator_Name(String operator_code)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select Operator_Name from dbo.Agent_MobDth_Recharge_operator_details where Operator_CodeEgen=?";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operator_code);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}

	public String getRechargeOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select Operator_CyberPlat from dbo.Agent_MobDth_Recharge_operator_details where Operator_Name=? and Service=?";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}

	public String getAESOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select AES_Operator_Code from Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}
	public String getWINUSOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select WINUS from Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}
	public String getHERMESOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select Hermes_Operator_Code from Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}
	public String getEMONEYOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select EMONEY_Operator_Code from Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}
	public String getSmartOperatosCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select Smart_Rech from Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}

	public String getMarsOperCode(String operatorName,String service)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String operator_Name=null; 
		try
		{
			sqlQurry="select Operator_Code_Mars from dbo.Agent_New_Vendor_Operator_Details where Operator_Name=? and Service=?";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1,operatorName);
			pstmt.setString(2,service);
			rst=pstmt.executeQuery();
			while(rst.next())
			{
				operator_Name=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception exc)
		{
			exc.printStackTrace();
			return null;
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
			}catch(Exception exc){}
		}
		return operator_Name;
	}


	public String getClientActiveService(String agent_id,String agent_dst_id,String Sub_Service,String Operator_value)
	{
		logger.info("getClientActiveService()>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		Connection con=null;
		CallableStatement cstmt=null;
		String service_status=null;
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Mobile_App_Agent_Active_Service(?,?,?,?,?)}");
			cstmt.setString(1,agent_id);
			cstmt.setString(2,agent_dst_id);
			cstmt.setString(3,Sub_Service);
			cstmt.setString(4,Operator_value);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.execute();
			service_status=cstmt.getString(5);
			cstmt.close();
			con.close();
			System.out.println("service_status  "+service_status);
			logger.info("getClientActiveService()<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
			return service_status;
		}
		catch(Exception ex)
		{
			System.out.println(" Execption in LIVE MRA ");
			ex.printStackTrace();
			return service_status="N";

		}
		finally
		{
			try{
				if(cstmt!=null)
				{
					cstmt.close();
					cstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc){
				System.out.println(" Execption in LIVE MRA ");
				exc.printStackTrace();
			}
		}		
	}


	/*public String updateTransactionStatusFail(String user_id,String deduct_amt,String status,String res_code,String res_result,String transaction_No,String ip_add,String response_code,String date_of_tran,String transaction_id)
		{
			logger.debug("updateTransactionStatusFail()>>>>>>>>>>>>>>>>>>>>>>>>>>>>> date_of_tran "+date_of_tran);
			Connection con=null;
			PreparedStatement pstmt=null;
			String sqlQurry=null;
			ResultSet rst=null;
			String response_msg=null;
			try
			{
				sqlQurry="update dbo.live_recharge set status=?,response_code=?,response_result=?,ResponseTime=convert(varchar(10),GETDATE(),108) where tran_id=? and date_of_recharge=?";
				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(sqlQurry);
				pstmt.setString(1,status);
				pstmt.setString(2,res_code);
				pstmt.setString(3,res_result);
				pstmt.setString(4,transaction_No);
				pstmt.setString(5,date_of_tran);
				pstmt.executeUpdate();
			//	con.commit();
				sqlQurry="update dbo.agent_transaction_details set Action_on_bal_amt='debit/credit',Tran_status='Failure',UpdIpAddress=?,Agent_F_balAmt=Agent_balAmt_b_Ded,Remark='Refunded' where Transaction_No=? and Date_of_Transaction=?";
				logger.debug("sqlQurry  "+sqlQurry);
				pstmt=con.prepareStatement(sqlQurry);
				pstmt.setString(1,ip_add);
				pstmt.setString(2,transaction_No);
				pstmt.setString(3,date_of_tran);
				pstmt.executeUpdate();
			//	con.commit();
				sqlQurry="update agent_amount set usedcash=usedcash-"+deduct_amt+" where agent_id="+user_id;
				pstmt=con.prepareStatement(sqlQurry);
				pstmt.executeUpdate();
				con.commit();
				sqlQurry="select display_msg from dbo.MobileApp_Response where response_code='"+response_code+"'";
				pstmt=con.prepareStatement(sqlQurry);
				rst=pstmt.executeQuery();
				while(rst.next())
				{
					response_msg=rst.getString(1);
				}		
				pstmt.close();
				con.close();
				}catch(Exception exc)
				{ 
					try {
						if(con!=null)
						con.rollback();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(" Execption in LIVE MRA APP ");
						e.printStackTrace();
					}
					exc.printStackTrace();
					System.out.println("Exception occurs LIVE MRA APP "+exc);
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
					}catch(Exception exc){
						System.out.println(" Execption in LIVE MRA APP ");
						exc.printStackTrace();
					}
				}
				logger.debug("updateTransactionStatusFail()<<<<<<<<<<<<<<<<<<<<<<<");
				return response_msg;
		}*/

	/*public HashMap<String,String> updateTransactionStatusSuccessNPending(String user_id,String deduct_amt,String status,String res_code,String res_result,String transaction_No,String ip_add,String response_code,String date_of_tran,String transaction_id)
		{
			HashMap<String,String> result_map=new HashMap<String, String>();
			Connection con=null;
			PreparedStatement pstmt=null;
			String sqlQurry=null;
			ResultSet rst=null;
			String response_msg=null;
			String sqlBalance=null;
			String balance=null;
			try
			{
				sqlQurry="update dbo.live_recharge set status=?,response_code=?,response_result=?,ResponseTime=convert(varchar(10),GETDATE(),108) where tran_id=? and date_of_recharge=?";

				con=DBConnection.getConnection();
				con.setAutoCommit(false);
				pstmt=con.prepareStatement(sqlQurry);
				pstmt.setString(1,status);
				pstmt.setString(2,res_code);
				pstmt.setString(3,res_result);
//				pstmt.setString(4,"EGEN_API");
				pstmt.setString(4,transaction_No);
				pstmt.setString(5,date_of_tran);
				pstmt.executeUpdate();
			//	con.commit();
				if(!status.equalsIgnoreCase("Pending"))
				{
					sqlQurry="update dbo.agent_transaction_details set Tran_status=?,UpdIpAddress=?, Remark=? where Transaction_No=? and Date_of_Transaction=?";
					logger.debug("sqlQurry  "+sqlQurry);
					pstmt=con.prepareStatement(sqlQurry);
					pstmt.setString(1,status);
					pstmt.setString(2,ip_add);
					pstmt.setString(3,status);
					pstmt.setString(4,transaction_No);
					pstmt.setString(5,date_of_tran);
					pstmt.executeUpdate();
				//	con.commit();
				}
				con.commit();
				sqlQurry="select display_msg from dbo.MobileApp_Response where response_code='"+response_code+"'";
				pstmt=con.prepareStatement(sqlQurry);
				rst=pstmt.executeQuery();
				while(rst.next())
				{
					response_msg=rst.getString(1);
				}

				sqlBalance="select TotCash-usedcash as balance from dbo.agent_amount where agent_id='"+user_id+"'";
				pstmt=con.prepareStatement(sqlBalance);
				rst=pstmt.executeQuery();
				while(rst.next())
				{
					balance=rst.getString(1);
				}

				rst.close();
				pstmt.close();
				con.close();
				}catch(Exception exc)
				{
					try {
						if(con!=null)
						con.rollback();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(" Execption in LIVE MRA APP ");				
						e.printStackTrace();
					}
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
					}catch(Exception exc){}
				}
				result_map.put("balance", balance);
				result_map.put("response_msg", response_msg);
				return result_map;
		}*/

	/*public HashMap<String, String> updateTxnStatus(String user_id,String transaction_id,String transaction_No,String status,String deduct_amt,String res_code,String res_result,String ip_add,String date_of_tran)
		{

			HashMap<String, String> hashMap=new HashMap<String, String>();
			Connection con=null;
			CallableStatement cstmt=null;
			String response_msg=null;
			String balance=null;
			try
			{
				con=DBConnection.getConnection();
				cstmt=con.prepareCall("{call dbo.Mobile_App_Transaction_Update(?,?,?,?,?,?,?,?,?,?)}");
				cstmt.setString(1, user_id);
				cstmt.setString(2, transaction_id);
				cstmt.setString(3, transaction_No);
				cstmt.setString(4, status);
				cstmt.setString(5, res_code);
				cstmt.setString(6, res_result);
				cstmt.setString(7, ip_add);
				cstmt.setString(8, date_of_tran);
				cstmt.registerOutParameter(9, java.sql.Types.VARCHAR);
				cstmt.registerOutParameter(10, java.sql.Types.VARCHAR);
				cstmt.execute();

				balance=cstmt.getString(9);
				response_msg=cstmt.getString(10);

				hashMap.put("balance", balance);
				hashMap.put("response_msg", response_msg);

			}catch(Exception exc)
			{
				exc.printStackTrace();
			}
			finally
			{
				try{
					if(cstmt!=null)
					{
						cstmt.close();
						cstmt=null;
					}
					if(con!=null)
					{
						con.close();
						con=null;
					}
				}catch(Exception exc){}
			}
			System.out.println("MRA Update status  : "+hashMap);
			return hashMap;
		}*/

	public final String  updateStatusTranEGEN(String rechTranId, String tranStatus, String aGuser_id, String resultCode, String vendorSessionId, String checkVendor, String inputXML, String outputXML){
		Connection con = null;
		CallableStatement cstmt=null;
		String result="error";
		if(inputXML==null)
			inputXML="";
		System.out.println(" >>  "+"rechTranId : "+rechTranId+"  :   tranStatus : "+tranStatus+"  : aGuser_id : "+aGuser_id+"  :   vendorSessionId : "+vendorSessionId+"   : inputXML : "+inputXML);

		try 
		{
			String apiProvider="";
			if(checkVendor.equalsIgnoreCase("SmartRecharge"))
			{
				apiProvider="SmartRecharge-APP";
			}
			else if(checkVendor.equalsIgnoreCase("SELF"))
			{
				apiProvider="SK-Bill";
			}
			else if(checkVendor.equalsIgnoreCase("MARS"))
			{
				apiProvider="MARS-APP";
			}else if(checkVendor.equalsIgnoreCase("MARS1"))
			{
				apiProvider="MARS1-APP";
			}
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call update_vendor_result_EGEN(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, rechTranId.trim());
			cstmt.setString(2, tranStatus);
			cstmt.setString(3, aGuser_id);
			cstmt.setString(4, resultCode);
			cstmt.setString(5, vendorSessionId.trim());
			cstmt.setString(6, checkVendor);
			cstmt.setString(7, inputXML);
			cstmt.setString(8, outputXML);
			cstmt.setString(9, apiProvider);
			cstmt.registerOutParameter(10, 12);
			cstmt.execute();
			result = cstmt.getString(10);
			System.out.println("Update Status EGEN :: "+result);
		}catch (Exception ex) {
			System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranGoal)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return result;
	}

	public String  insertRecord(String user_id,String mobile_operator,String mobile_number,String amount,String mob_commission,String service,String Dist_Comm,String tran_no,String area_circle,String trand_id,String ip_add,String deduct_amt,String agent_dist_id,String request_type)
	{
		logger.debug("insertRecord() called>>>>>>>>>>>>>>>>>>>"); 
		Connection con=null;
		CallableStatement cstmt=null;
		String outResult=null;
		String tran_date=null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		tran_date=sdf.format(date);

		try
		{
			logger.info("trand_id "+trand_id+" tran_no "+tran_no+" user_id "+user_id+" mobile_operator "+mobile_operator+" mobile_number "+mobile_number+" amount "+amount+" mob_commission "+mob_commission+" service "+service+" Dist_Comm "+Dist_Comm+"  area_circle  "+area_circle+" request_type "+request_type+" agent_dist_id "+agent_dist_id+" deduct_amt "+deduct_amt+" ip_add "+ip_add);

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Mobile_App_Transaction_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,trand_id);
			cstmt.setString(2,tran_no);
			cstmt.setString(3,user_id);
			cstmt.setString(4,mobile_operator);
			cstmt.setString(5,mobile_number);
			cstmt.setString(6,amount);
			cstmt.setString(7,mob_commission);
			cstmt.setString(8,service);
			cstmt.setString(9,Dist_Comm);
			cstmt.setString(10,area_circle);
			cstmt.setString(11,request_type);
			cstmt.setString(12,agent_dist_id);
			cstmt.setString(13,deduct_amt);
			cstmt.setString(14,ip_add);
			cstmt.setString(15,tran_date);
			cstmt.registerOutParameter(16, java.sql.Types.VARCHAR);
			cstmt.execute();
			outResult=cstmt.getString(16);
			System.out.println("outResult "+outResult);
			cstmt.close();

			logger.debug("insertRecord() success<<<<<<<<<<<<<<<<<<<<<<");
			return outResult;
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception occurs "+exc.getMessage());
			return "not inserted";
		}
		finally
		{
			try{
				if(cstmt!=null)
				{
					cstmt.close();
					cstmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
			}catch(Exception exc){}
		}
	}

	public String  getAgentBalanceDao(String agent_id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		String amount=null;
		try
		{
			sqlQurry="select (totcash-usedcash) as amount from agent_amount where agent_id="+Long.parseLong(agent_id);

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			rst=pstmt.executeQuery();
			if(rst.next())
			{
				amount=rst.getString(1);
			}
			rst.close();
			pstmt.close();
			con.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
					con=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
			}catch(Exception ex){

			}
		}
		return amount;
	}


	public boolean checkLastTransaction(String user_id,String mob_no,String operator,String amount)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String sqlQurry=null;
		int time;

		try
		{
			sqlQurry="SELECT top 1 DATEDIFF(hour,date_time, getdate()) as timediff from live_recharge where user_id=? and mobile_number=? and mobile_operator=? and amount=?  and status in ('success','pending') order by date_time desc";

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1, user_id);
			pstmt.setString(2, mob_no);
			pstmt.setString(3, operator);
			pstmt.setString(4, amount);
			rst=pstmt.executeQuery();
			if(rst.next())
			{
				time=Integer.parseInt(rst.getString(1));
				if(time<1)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
					con=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
				if(rst!=null)
				{
					rst.close();
					rst=null;
				}
			}catch(Exception ex){}
		}
	}

	public String txn_key_Validation(String txn_key,String agent_id)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		String txn_key_DB=null;
		try
		{

			con=DBConnection.getConnection();
			pstmt=con.prepareStatement("select Tran_key from dbo.agent_details where agent_id=?");
			pstmt.setLong(1,Long.parseLong(agent_id));
			rst=pstmt.executeQuery();
			if(rst.next())
			{
				txn_key_DB=rst.getString(1);
			}

			if(txn_key_DB.equals(txn_key))
			{
				return "Y";
			}
			else
			{
				return "N";
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			System.out.println(" inside MRA Live Application  ");
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
				}
				if(pstmt!=null)
				{
					pstmt.close();
				}
				if(rst!=null)
				{
					rst.close();
				}
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println(" inside MRA Live Application  ");
			}
		}
		return "N";
	}	public void recieved_Request(String transaction_id,String Request_Url,String Request_IP)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("insert into dbo.Mobile_App_Send_Received(Transaction_id,Request_Url,Request_IP) values(?,?,?)");
			pstmt.setString(1, transaction_id);
			pstmt.setString(2, Request_Url);
			pstmt.setString(3, Request_IP);
			pstmt.executeUpdate();
			con.commit();
		}catch(SQLException ex)
		{
			try {
				if(con!=null)
					con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				System.out.println(" inside MRA Live Application  ");
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
					con=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println(" inside MRA Live Application..  ");
			}
		}
	}
	public void send_Response(String transaction_id,String Response_Send)
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("update dbo.Mobile_App_Send_Received set Response_Send=? where Transaction_id=?");
			pstmt.setString(1, Response_Send);
			pstmt.setString(2, transaction_id);
			pstmt.executeUpdate();
			pstmt.close();
			con.commit();
			con.close();
		}catch(SQLException ex)
		{
			try {
				if(con!=null)
					con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				System.out.println(" inside MRA Live Application  ");
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
		finally
		{
			try{
				if(con!=null)
				{
					con.close();
					con=null;
				}
				if(pstmt!=null)
				{
					pstmt.close();
					pstmt=null;
				}
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println(" inside MRA Live Application  ");
			}
		}
	}

	public static String getVendorIp() 
	{

		System.out.println("getVendorIp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select IP from Vendor_IP_Details";
			System.out.println("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{
				ip=rst.getString("IP");
			}
			System.out.println("getVendorIp()>>>>>>>>>>>>>>>>>>>>>>  ip : "+ip);

		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("Exception getVendorIp "+exc);
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
			}catch(Exception exc){

			}
		}
		return ip;
	}

}
