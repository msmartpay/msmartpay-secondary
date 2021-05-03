package com.pendingTransaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.dbConnection.DBConnection;

public class UpdateRechPendingTransactionDao {

	static final Logger logger = Logger.getLogger(UpdateRechPendingTransactionDao.class);
	
	public int updateAepsLogStatus(String clientRefId,String status)
	{
		int updateStatus = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update aeps_transaction_log set status=? where client_ref_id=?");
			pst.setString(1, status);
			pst.setString(2, clientRefId);
			
			updateStatus = pst.executeUpdate();
			

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return updateStatus;

	}
	public String aepsCredit(String tid,String clientRefId,String bankRefId,String userCode,
			double commission,double tds,double amount,String ip,String status)

	{
		String Status = "Failure";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call aeps_transaction_insert(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, clientRefId);
			cstmt.setString(2, userCode);
			cstmt.setDouble(3, amount);
			cstmt.setDouble(4, commission);
			cstmt.setDouble(5, tds);
			cstmt.setString(6, tid);
			cstmt.setString(7, bankRefId);
			cstmt.setString(8, ip);
			cstmt.setString(9, status);
			cstmt.registerOutParameter(10, 12);
			cstmt.execute();
			Status=cstmt.getString(10);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {

			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return Status;

	}

	public ArrayList<HashMap<String,String>> fetchEkoAEPSTransactions() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			
			con=DBConnection.getConnection();
			String sql="select user_code,client_ref_id from aeps_transaction_log where type='AEPS-Withdrawal' and status='Pending' and convert(varchar(10),date_time,120) >'2020-05-13' ";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				
				map=new HashMap<String, String>();
				map.put("user_code", rs.getString(1));
				map.put("client_ref_id", rs.getString(2));

				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("UpdateRechPendingTransactionDao.updateSmartRechPendingTransaction()");
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<HashMap<String,String>> updateMARSRechPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id,service,Usessionid,apiprovider from live_recharge where date_time<= dateadd(minute, -3, getdate()) and status='Pending' and  Usessionid is not null and apiprovider not like '%MARS1%' and apiprovider not like '%Smart%' and apiprovider not like '%WINUS%' and apiprovider!=''";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));
				map.put("Usessionid", rs.getString(4));
				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<HashMap<String,String>> getHermesPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id+'H'+Agent_tran_id,service,Usessionid,apiprovider,status from live_recharge where date_time<= dateadd(minute, -5, getdate()) and status='Pending' and Usessionid is not null and (apiprovider like '%HERMES-APP-Bill%' or apiprovider like '%WINUS-TEP-WEB%' or apiprovider like '%HERMES-APP%' or ApiProvider like '%HERMES-WEB-Bill%') and apiprovider!=''";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));
				map.put("Usessionid", rs.getString(4));
				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<HashMap<String,String>> updateSmartRechPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id,service,apiprovider from live_recharge where date_time<= dateadd(minute, -15, getdate()) and status='Pending' and apiprovider like '%Smart%'";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));

				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("UpdateRechPendingTransactionDao.updateSmartRechPendingTransaction()");
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<HashMap<String,String>> updateWinusPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id,service,apiprovider,Usessionid from live_recharge where date_time<= dateadd(minute, -1, getdate()) and status='Pending' and (apiprovider like '%WINUS%' or apiprovider like '%Venus%') and service not in ('Billpay')";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));
				map.put("apiprovider", rs.getString(4));
				map.put("Usessionid", rs.getString(5));

				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("UpdateRechPendingTransactionDao.updateSmartRechPendingTransaction()");
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public ArrayList<HashMap<String,String>> updateRBLPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id,service,apiprovider,Usessionid from live_recharge where date_time<= dateadd(minute, -60, getdate()) and status='Pending' and (apiprovider like '%RBL%')";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));
				map.put("apiprovider", rs.getString(4));
				map.put("Usessionid", rs.getString(5));

				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("UpdateRechPendingTransactionDao.updateSmartRechPendingTransaction()");
				e.printStackTrace();
			}
		}
		return list;
	}


	public ArrayList<HashMap<String,String>> updateMARS1RechPendingTransaction() {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select user_id,tran_id,service,Usessionid,apiprovider from live_recharge where date_time<= dateadd(minute, -15, getdate()) and status='pending' and  Usessionid is not null and apiprovider like '%MARS1%' and apiprovider!='' order by date_of_recharge desc";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				map=new HashMap<String, String>();
				map.put("user_id", rs.getString(1));
				map.put("tran_id", rs.getString(2));
				map.put("service", rs.getString(3));
				map.put("Usessionid", rs.getString(4));
				list.add(map);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return list;
	}

	public String  updateStatusTranEGEN(String rechTranId, String tranStatus, String aGuser_id, String resultCode, String vendorSessionId, String checkVendor, String inputXML, String outputXML){
		CallableStatement cstmt=null;
		String result="error";
		if(inputXML==null)
			inputXML="";
		System.out.println(" >>  "+"rechTranId : "+rechTranId+"  :   tranStatus : "+tranStatus+"  : aGuser_id : "+aGuser_id+"  :   vendorSessionId : "+vendorSessionId+"   : inputXML : "+inputXML);
		Connection con=null;
		try 
		{
			String apiProvider="";
			if(checkVendor.equalsIgnoreCase("SELF"))
			{
				apiProvider="SK-Bill";
			}
			else if(checkVendor.equalsIgnoreCase("MARS"))
			{
				apiProvider="MARS-WEB";
			}
			else if(checkVendor.equalsIgnoreCase("MARS1"))
			{
				apiProvider="MARS1-WEB";
			}
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call update_vendor_result_EGEN_Mannual(?,?,?,?,?,?,?,?,?,?)}");
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
	
	public String updateTransaction(String SenderId,String RechTranId,String status,long agentId
			,String ERROR,String vendorSessionId,String requestXML,String searchResponse,String updateRemark,String serviceName) {

		String updateStatus="";
		Connection con=null;
		CallableStatement cstmt=null;
		try {

			String checkvendor="RBL";

			/*@SenderId varchar(10),@RechTranId varchar(25),@status varchar(15),@user_id bigint,@ERROR varchar(15),
			 * @vendorSessionId varchar(25), @check_vendor varchar(35),@requestXML varchar(500),@service_name  varchar(25),
			 * @searchResponse varchar(500),@remark varchar(30),@out_status varchar(15) output*/
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call SMART_UPDATE_VENDOR_RESULT(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, SenderId);
			cstmt.setString(2,RechTranId);
			cstmt.setString(3, status);
			cstmt.setLong(4,agentId);
			cstmt.setString(5, ERROR);
			cstmt.setString(6, vendorSessionId);
			cstmt.setString(7,checkvendor);
			cstmt.setString(8, serviceName);
			cstmt.setString(9,requestXML);
			cstmt.setString(10,searchResponse);
			cstmt.setString(11, updateRemark);
			cstmt.registerOutParameter(12, java.sql.Types.VARCHAR);
			cstmt.execute();
			updateStatus=cstmt.getString(12);

		} catch (Exception e) {
			logger.info("SmartAPIDao.registerBeneficiaryDetail()");
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}


		return updateStatus;

	}
}
