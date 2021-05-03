package com.msmart.eko;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.db.DBConnection;

public class MoneyTransferDao {

	
	Logger log=Logger.getLogger(MoneyTransferDao.class);

	public HashMap<String,Object> getcustomerName(String customer_id)
	{
		log.info("Executing getcustomerName method of dmr_customer_dao class");
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = null;

		HashMap<String,Object> map=null;
		try {

			con = DBConnection.getConnection();
			pst = con.prepareStatement("select Customer_id,name,otp,kyc_indicator,varified,address,convert(varchar(10),reg_datetime,120) from EKO_DMR_Sender_Details where Customer_id=?");
			pst.setString(1, customer_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				map=new HashMap<>();
				map.put("SenderId", rs.getString(1));
				map.put("Name", rs.getString(2));
				map.put("KycStatus", rs.getString(3));
				map.put("VarifiedStatus", rs.getString(4));
				map.put("Address", rs.getString(5));
				map.put("Doj", rs.getString(6));
			}

		} catch (Exception e) {
			log.error("Error in getcustomerName method of dmr_customer_dao class" + e.getMessage());
			return null;
		} finally {

			try {
				
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error in getcustomerName method of dmr_customer_dao class while closing the connection"+e.getMessage());
			}

		}
		return map;

	}

	public int getSenderDetails(String CustomerNo)

	{
		log.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("select kyc_indicator from EKO_DMR_Sender_Details where Customer_id=?");
			pst.setString(1, CustomerNo);
			rs = pst.executeQuery();
			while (rs.next()) {
				Status = rs.getInt(1);
			}

		} catch (Exception e) {
			log.info("Error in getCustomerKycStatus method of dmr_customer_dao class" +e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in getCustomerKycStatus method of dmr_customer_dao class while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public int insertEKOLogs(String vendorName,String tranId,String reqString,String agentId)

	{
		log.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("insert into EGEN_recharge_log_details"
					+ "(vendor_name,transaction_id,Input_XML,transaction_date,agent_id)"
					+ " values(?,?,?,getdate(),?)");
			
			pst.setString(1, vendorName);
			pst.setString(2, tranId);
			pst.setString(3, reqString);
			pst.setString(4, agentId);
			
			int count = pst.executeUpdate();
			
			log.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			log.info("Error in getCustomerKycStatus method of dmr_customer_dao class");
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in getCustomerKycStatus method of dmr_customer_dao class while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public int updateEKOLogs(String tranId,String respString,String agentId)
	{
		log.info("Execute getCustomerKycStatus method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EGEN_recharge_log_details set Output_XML=? where transaction_id=? and agent_id=?");
			pst.setString(1, respString);
			pst.setString(2, tranId);
			pst.setString(3, agentId);
			
			int count = pst.executeUpdate();
			
			log.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			log.info("Error in getCustomerKycStatus method of dmr_customer_dao class");
			e.printStackTrace();
			
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in getCustomerKycStatus method of dmr_customer_dao class while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}

	public String validateService(String agentID,String service) {
		log.info("executing validateService method of DMR_Transaction_DaoEKO class");
		String status="N";
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			log.info(agentID);
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call SKAPI_Client_Service_Validation(?,?,?)}");
			cstmt.setString(1,agentID);
			cstmt.setString(2,service);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(3);
			
		}catch (Exception ex) {
			status="N";
			log.error("Exception in DMR_Transaction_DaoEKO class method (validateService)"+ex.getMessage());
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_Transaction_DaoEKO class method (dmrMoneyTransferRequest) while closing connection");
			}
		}
		return status;
	}
	
	public String validateService(String agentID,String service,String operator,double amount,String senderId) {
		log.info("executing dmrMoneyTransferRequest method of DMR_Transaction_DaoEKO class");
		String status="N";
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			log.info(agentID);
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call DMR_Transaction_Validation(?,?,?,?,?,?)}");
			cstmt.setString(1,agentID);
			cstmt.setString(2,service);
			cstmt.setString(3,operator);
			cstmt.setDouble(4,amount);
			cstmt.setString(5,senderId);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(6);
			
		}catch (Exception ex) {
			status="N";
			log.error("Exception in DMR_Transaction_DaoEKO class method (dmrMoneyTransferRequest)"+ex.getMessage());
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_Transaction_DaoEKO class method (dmrMoneyTransferRequest) while closing connection");
			}
		}
		return status;
	}

	final String updateStatusDMRTranEKO(String sendorMobile,String transactionId,String Status,
			String vendorTranId,String venor,String bankRRN)
	{
		//trandao.updateStatusDMRTranEKO(customer_mobile,transactionId, "Pending", "", "EKO",url + str, "no response from eko");
		
		log.info("executing updateStatusDMRTranEKO method of DMR_Transaction_DaoEKO class");
		String result="error";
		log.info("sendorMobile : "+sendorMobile);
		log.info("transactionId : "+transactionId);
		log.info("Status : "+Status);
		log.info("vendorTranId : "+vendorTranId);
		log.info("venor : "+venor);
		log.info("bankRRN : "+bankRRN);
		Connection con = null;
		CallableStatement cstmt=null;
		
		try 
		{
			con=DBConnection.getConnection();

			cstmt=con.prepareCall("{call DMR_UPDATE_VENDOR_RESULT(?,?,?,?,?,?,?)}");
			cstmt.setString(1,sendorMobile);
			cstmt.setString(2,transactionId);
			cstmt.setString(3,Status);
			cstmt.setString(4,vendorTranId);
			cstmt.setString(5,venor);
			cstmt.setString(6,bankRRN);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			cstmt.execute();
			result=cstmt.getString(7);
			log.info("result:::::::::::"+result);
		}catch (Exception ex) {
			ex.printStackTrace();
			log.error("Exception in DMR_Transaction_DaoEKO class method (updateStatusDMRTranEKO)"+ex.getMessage());
		}
		finally 
		{
			try 
			{
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_Transaction_DaoEKO class method (updateStatusDMRTran) while closing connection");
			}
		}
		return result;
	}
	
	public String DMR_TRANSACTION_INSERT(String tranId, String TranNo, 
			String agentID, String service,String operator,
			String SenderId,String Ipaddress,String apiProvider,
			String BeneCode,String transactionRemark,
			String transactionType,String Bene_Name,
			String Bene_Bank_Name,String Bene_Bank_IFSC,
			String Bene_Bank_Acount,double amt) {
		log.info("Execute InsertAccountInfoORrefundDMR method of dmr_customer_dao class");
		String result = "error";
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBConnection.getConnection();

			cstmt = con.prepareCall("{call DMR_TRANSACTION_INSERT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, tranId);
			cstmt.setLong(2, Long.parseLong(agentID));
			cstmt.setString(3, service);
			cstmt.setString(4, operator);
			cstmt.setString(5, SenderId);
			cstmt.setDouble(6, amt);
			cstmt.setString(7, TranNo);
			cstmt.setString(8, Ipaddress);
			cstmt.setString(9, apiProvider);
			cstmt.setString(10, BeneCode);
			cstmt.setString(11, transactionRemark);
			cstmt.setString(12, transactionType);
			cstmt.setString(13, Bene_Name);
			cstmt.setString(14, Bene_Bank_Name);
			cstmt.setString(15, Bene_Bank_IFSC);
			cstmt.setString(16, Bene_Bank_Acount);
			cstmt.registerOutParameter(17, java.sql.Types.VARCHAR);
			cstmt.execute();
			result = cstmt.getString(17);
			log.info("Update Status :: " + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in dmr_customer_dao class method (InsertAccountInfoORrefundDMR) while closing connection"+ e.getMessage());
			}
		}
		return result;
	}

	public String getImpsEnableStatusBankCode(String BankName) {
		log.info("executing getImpsEnableStatusBankCode method of DMR_Transaction_DaoEKO class");
		Connection con = null;
		PreparedStatement pstmt=null;
		String status="";
		ResultSet rs = null;
		try
		{

			log.info("-----------------------------------------"+BankName);
			con=DBConnection.getConnection();
			String	sql="SELECT ShortCode from  Bank_List_IMPS_Availability  where BankName=? and IMPS='Y'";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,BankName);

			rs =pstmt.executeQuery();
			while(rs.next())
			{
				status=rs.getString(1);
			}

		}catch (Exception ex) {
			log.error("Exception in DMR_Transaction_DaoEKO class method (getImpsEnableStatusBankCode)"+ex.getMessage());
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
				log.error("Exception in DMR_Transaction_DaoEKO class method (getImpsEnableStatusBankCode) while closing connection");
			}
		}
		return status;
	}

	public Ifsc getbankDetailByIfsc(String ifscCode)

	{
		log.info("executing getbankDetailByIfsc method of DMR_BeneficiaryDao class");
		Ifsc ifsc=null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			log.info("::::::::::::: ifsc:: "+ifscCode);
			pst = con.prepareStatement("select BRANCH,BANK,IFSC,STATE,CITY from IFSC_Details where IFSC=? ");
			pst.setString(1, ifscCode);

			rs = pst.executeQuery();
			if (rs.next()) {
				ifsc=new Ifsc();
				ifsc.setBranch(rs.getString(1));
				ifsc.setBank_name(rs.getString(2));
				ifsc.setIfsc(rs.getString(3));
				ifsc.setState(rs.getString(4));
				ifsc.setCity(rs.getString(5));
			}

		} catch (Exception e) {
			log.error("Error in getbankDetailByIfsc method of DMR_BeneficiaryDao class" + e.getMessage());
		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_BeneficiaryDao class method (getbankDetailByIfsc) while closing connection"+e.getMessage());
			}
		}
		return ifsc;

	}


	public String getbankDetailByShortCode(String shortCode)

	{
		log.info("executing getbankDetailByShortCode method of DMR_BeneficiaryDao class");
		String ifsc=null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {

			con = DBConnection.getConnection();
			log.info("::::::::::::: ifsc:: "+shortCode);
			pst = con
					.prepareStatement("select ifsc_code from DMR_BankCodeList where BankCode=?");
			pst.setString(1, shortCode);

			rs = pst.executeQuery();
			if (rs.next()) {
				ifsc=rs.getString(1);
			}

		} catch (Exception e) {
			log.error("Error in getbankDetailByShortCode method of DMR_BeneficiaryDao class" + e.getMessage());
		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_BeneficiaryDao class method (getbankDetailByShortCode) while closing connection"+e.getMessage());
			}
		}
		return ifsc;

	}
	
	public String addBeneficiary(String Bene_Name, String Bene_Mobile, String Bene_Bank_Name, String Bene_Bank_IFSC, String Bene_Bank_Acount, String senderId,String Beneficiary_id)
	{
		log.info("executing addBeneficiary method of DMR_BeneficiaryDao class");
		CallableStatement cstmt = null;
		Connection con = null;
		String result="";
		try {
			con = DBConnection.getConnection();

			cstmt = con.prepareCall("{call DMR_BENEFICIARY_REGISTRATION(?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, senderId);
			cstmt.setString(2, Bene_Mobile);
			cstmt.setString(3, Bene_Name);
			cstmt.setString(4, Bene_Bank_Name);
			cstmt.setString(5, Bene_Bank_IFSC);
			cstmt.setString(6, Bene_Bank_Acount);
			cstmt.setString(7, Beneficiary_id);
			cstmt.registerOutParameter(8, java.sql.Types.VARCHAR);
			cstmt.execute();
			result = cstmt.getString(8);
			log.info("Update Status :: " + result);

		} catch (Exception e) {
			log.error("executing addBeneficiary method of DMR_BeneficiaryDao class" + e.getMessage());
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_BeneficiaryDao class method (addBeneficiary) while closing connection"+e.getMessage());
			}
		}
		return result;

	}

	public boolean deleteReceipent(String Beneficiary_id)

	{
		log.info("Execute deleteReceipent method of dmr_customer_dao class");
		boolean b = false;
		PreparedStatement pst = null;
		Connection con = null;
		try {

			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EKO_Beneficiary_Details set flag=0 where Beneficiary_id=?");
			pst.setString(1, Beneficiary_id);

			b = pst.execute();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return b;

	}

	public int setcustomervarifiedindicator(String customer_id)

	{
		log.info("Execute setcustomervarifiedindicator method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EKO_DMR_Sender_Details set varified=1 where Customer_id=?");
			pst.setString(1, customer_id);
			Status = pst.executeUpdate();
		} catch (Exception e) {
			log.error("Error in setcustomervarifiedindicator method of dmr_customer_dao class"
					+ e.getMessage());
			return 0;
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error(
						"Error in setcustomervarifiedindicator method of dmr_customer_dao class while closing the connection"
								+ e.getMessage());
			}

		}
		return Status;

	}

	public String senderRegistration(String senderId,String senderName,String address,String dob,String otp) {
		log.info("ServicesDao.senderRegistration()");
		String status="Unable to Register at this time. Please try later";
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call EKO_DMR_Sender_Registration(?,?,?,?,?,?)}");
			cstmt.setString(1,senderId);
			cstmt.setString(2,senderName);
			cstmt.setString(3,address);
			cstmt.setString(4,dob);
			cstmt.setString(5,otp);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);

			cstmt.execute();
			status=cstmt.getString(6);


		}catch (Exception ex) {
			status="Unable to Register at this time. Please try later";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			log.info("ServicesDao.senderRegistration() Exception");
			ex.printStackTrace();
			return status;
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.info("ServicesDao.senderRegistration() Exception");
			}
		}
		return status;
	}

	public static String getEKOTid(String TranNO,String agentID)

	{
		String tid = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("select Reference_id from agent_transaction_details where agent_id="+Long.parseLong(agentID)+" and (Transaction_Id=? OR Reference_id=?)");
			pst.setString(1, TranNO);
			pst.setString(2, TranNO);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				tid = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}

		}
		return tid;

	}
	
	public static JSONArray getAllBankNameandCodeNEFT()
	{

		ResultSet rs = null;
		Connection con = null;
		CallableStatement cstmt=null;
		JSONArray arrlist = new JSONArray();
		try {

			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call BankListDetails_App(?)}");
			cstmt.setString(1, "IMPS");
			rs=cstmt.executeQuery();
			while (rs.next()) {
				String bname = rs.getString(1);
				String bcode = rs.getString(2);
				JSONObject jsonObject=new JSONObject();
				if(bname!=null)
					bname=StringUtils.capitalise(bname);

				jsonObject.put("bname", bname);
				jsonObject.put("bcode", bcode);
				jsonObject.put("ifscCode", rs.getString(3));
				arrlist.put(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return arrlist;

	}
	
	public int insertEKOKYCRequest(String tranId,String SenderId,long agentId)

	{
		log.info("Execute insertEKOKYCRequest");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("insert into EKO_KYC_REQUEST_DETAILS"
					+ "(agent_id,TransactionId,SenderMobile,RequestDate,KYCStatus)"
					+ " values(?,?,?,getdate(),'Pending')");
			
			pst.setLong(1, agentId);
			pst.setString(2, tranId);
			pst.setString(3, SenderId);
			
			Status = pst.executeUpdate();
			
			log.info("Log Inserted : "+Status);

		} catch (Exception e) {
			
			log.info("Error in insertEKOKYCRequest");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in insertEKOKYCRequest while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public int updateEKOKYCStatus(String tranId,String respString,String agentId)
	{
		log.info("Execute updateEKOKYCStatus");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EKO_KYC_REQUEST_DETAILS set KYCStatus=?,UpdatedDate=getdate() where TransactionId=?");
			pst.setString(1, respString);
			pst.setString(2, tranId);
			pst.setString(3, agentId);
			
			int count = pst.executeUpdate();
			
			log.info("Log Inserted : "+count);

		} catch (Exception e) {
			
			log.info("Error in updateEKOKYCStatus");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateEKOKYCStatus while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}

	public int updateEKOAEPSUserCode(String agentId,String userCode)
	{
		log.info("Execute updateEKOAEPSUserCode");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update AGENT_DETAILS set eko_aeps_user_code=? where agent_id=?");
			pst.setString(1, userCode);
			pst.setLong(2, Long.parseLong(agentId));
			
			Status = pst.executeUpdate();
			
			log.info("Log update : "+Status);

		} catch (Exception e) {
			
			log.info("Error in updateEKOAEPSUserCode");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateEKOKYCStatus while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	
	public String fetchEKOAEPSUserCode(String agentId)
	{
		log.info("Execute updateEKOAEPSUserCode");
		String userCode="";
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("Select eko_aeps_user_code from AGENT_DETAILS where agent_id=?");
			pst.setLong(1, Long.parseLong(agentId));
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				userCode = rs.getString(1);
			
			log.info("Log userCode : "+userCode);

		} catch (Exception e) {
			
			log.info("Error in updateEKOAEPSUserCode");
			e.printStackTrace();
			return null;
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateEKOKYCStatus while closing the connection"+ e.getMessage());
			}

		}
		return userCode;

	}

	public int updateEKOAEPSStatus(String agentId,int status)
	{
		log.info("Execute updateEKOAEPSUserCode");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update AGENT_DETAILS set EKO_AAEPS=? where agent_id=?");
			pst.setInt(1, status);
			pst.setLong(2, Long.parseLong(agentId));
			
			Status = pst.executeUpdate();
			
			log.info("Log update : "+Status);

		} catch (Exception e) {
			
			log.info("Error in updateEKOAEPSUserCode");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateEKOKYCStatus while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	public int fetchEkoAEPS(String agentId)
	{
		log.info("Execute updateEKOAEPSUserCode");
		int ekoStatus=-1;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("Select EKO_AAEPS from AGENT_DETAILS where agent_id=?");
			pst.setLong(1, Long.parseLong(agentId));
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				ekoStatus = rs.getInt(1);
			
			log.info("Log ekoStatus : "+ekoStatus);

		} catch (Exception e) {
			
			log.info("Error in updateEKOAEPSUserCode");
			e.printStackTrace();
			return ekoStatus;
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateEKOKYCStatus while closing the connection"+ e.getMessage());
			}

		}
		return ekoStatus;

	}
	
	public int updateAepsLog(String userCode,String clientRefId,String tranData)
	{
		log.info("Execute updateaepsLog");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update aeps_transaction_log set tran_data=? where user_code=? and client_ref_id=?");
			pst.setString(1, tranData);
			pst.setString(2, userCode);
			pst.setString(3, clientRefId);
			
			Status = pst.executeUpdate();
			
			log.info("Log update : "+Status);

		} catch (Exception e) {
			
			log.info("Error in updateaepsLog");
			e.printStackTrace();
			
		} finally {

			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in updateaepsLog while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	
	public String aepsCredit(String tid,String clientRefId,String bankRefId,String userCode,double commission,double tds,
			double amount,String ip)

	{
		log.info("Execute insertEKOKYCRequest");
		String Status = "Failure";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call aeps_transaction_insert(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, clientRefId);
			cstmt.setString(2, userCode);
			cstmt.setDouble(3, amount);
			cstmt.setDouble(4, commission);
			cstmt.setDouble(5, tds);
			cstmt.setString(6, tid);
			cstmt.setString(7, bankRefId);
			cstmt.setString(8, ip);
			cstmt.registerOutParameter(9, 12);
			cstmt.execute();
			Status=cstmt.getString(9);
			
		} catch (Exception e) {
			
			log.info("Error in insertEKOKYCRequest");
			e.printStackTrace();
			
		} finally {

			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.info("Error in insertEKOKYCRequest while closing the connection"+ e.getMessage());
			}

		}
		return Status;

	}
	public String saveAepsLog(String userCode,String refId,String serviceType,String data,String action,String ip,String type,double amount) {
		PreparedStatement pstmt=null;
		Connection con = null;
		try {


			String sql="insert into aeps_transaction_log ( user_code, client_ref_id, service_type, date_time, raquest_data, action, ip,type,amount) "
					+ "values  (?,?,?,getdate(),?,?,?,?,?)";
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userCode);
			pstmt.setString(2, refId);
			pstmt.setString(3, serviceType);
			pstmt.setString(4, data);
			pstmt.setString(5, action);
			pstmt.setString(6, ip);
			pstmt.setString(7, type);
			pstmt.setDouble(8, amount);
			
			int count=pstmt.executeUpdate();
			if(count>0)
				return "Y";
			else
				return "N";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
