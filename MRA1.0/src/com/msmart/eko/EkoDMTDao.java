package com.msmart.eko;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.msmart.dbconnection.DBConnection;
import com.msmart.service.PropertyFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EkoDMTDao {

	Logger log=Logger.getLogger(EkoDMTDao.class);

	public String generateBeneId()
	{
		log.info("executing generateBeneId method of DMR_BeneficiaryDao class");
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs=null;
		
		String beneId="";
		try {
			con = DBConnection.getConnection();

			String sql="select isnull(max([so.no]),0)+1 from EKO_Beneficiary_Details";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				
				beneId=String.valueOf(rs.getLong(1));
			}

		} catch (Exception e) {
			log.error("executing addBeneficiary method of DMR_BeneficiaryDao class" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_BeneficiaryDao class method (addBeneficiary) while closing connection"+e.getMessage());
			}
		}
		return beneId;

	}
	
	public String addBeneficiaryNew(String Bene_Name, String Bene_Mobile, String Bene_Bank_Name, String Bene_Bank_IFSC, 
			String Bene_Bank_Acount, String senderId,String bene_id,int channel,int isverified)
	{
		log.info("executing addBeneficiaryNew method of DMR_BeneficiaryDao class");
		CallableStatement cstmt = null;
		Connection con = null;
		String result="";
		try {
			con = DBConnection.getConnection();

			cstmt = con.prepareCall("{call DMR_BENEFICIARY_REGISTRATION_NEW(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, senderId);
			cstmt.setString(2, Bene_Mobile);
			cstmt.setString(3, Bene_Name);
			cstmt.setString(4, Bene_Bank_Name);
			cstmt.setString(5, Bene_Bank_IFSC);
			cstmt.setString(6, Bene_Bank_Acount);
			cstmt.setString(7, bene_id);
			cstmt.setInt(8, channel);
			cstmt.setInt(9,isverified);
			cstmt.registerOutParameter(10, java.sql.Types.VARCHAR);
			cstmt.execute();
			result = cstmt.getString(10);
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
	
	public JSONArray fetchBeneficiary(String customer_no)
	{
		log.info("executing addBeneficiary method of DMR_BeneficiaryDao class");
		PreparedStatement pstmt = null;
		Connection con = null;
		JSONArray beneList=new JSONArray();
		ResultSet rs=null;

		JSONObject bene=null;
		try {
			con = DBConnection.getConnection();

			String sql="select Beneficiary_id,Beneficiary_name,Account_Number,IFSC_code,isnull(Bank_name,'NA'),isnull(Beneficiary_mob_no,'NA'),channel,is_verified from EKO_Beneficiary_Details where Customer_mob_no=? and flag=1 order by Beneficiary_name asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, customer_no);

			rs=pstmt.executeQuery();
			while(rs.next()) {

				bene=new JSONObject();
				bene.put("RecipientId", rs.getString(1));
				bene.put("RecipientIdType", "NA");
				bene.put("BeneName", rs.getString(2));
				bene.put("Account", rs.getString(3));
				bene.put("Ifsc", rs.getString(4));
				bene.put("BankName", rs.getString(5));
				bene.put("BeneMobile", rs.getString(6));
				bene.put("IMPS", "2");
				bene.put("NEFT", "1");

				bene.put("Channel", rs.getString(7));
				bene.put("IsVerified", rs.getString(8));

				beneList.add(bene);
			}

		} catch (Exception e) {
			log.error("executing addBeneficiary method of DMR_BeneficiaryDao class" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				log.error("Exception in DMR_BeneficiaryDao class method (addBeneficiary) while closing connection"+e.getMessage());
			}
		}
		return beneList;

	}
	public HashMap<String,String> getMasterBankStatus(String bank_id) {
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		HashMap<String,String> bank=null;
		try {
			String sql="select bank,imps_enabled,ifsc,is_ifsc_required,short_code from master_bank_details where id="+bank_id;
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				bank=new HashMap<String, String>();
				bank.put("bank", rs.getString(1));
				bank.put("imps_enabled", rs.getString(2));
				bank.put("ifsc", rs.getString(3));
				bank.put("is_ifsc_required", rs.getString(4));
				bank.put("short_code", rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bank;
	}

	public String getEKoOtpRefId(String customerId) {
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String otpRefId=null;
		try {
			String sql="select otp from EKO_DMR_Sender_Details where Customer_id='"+customerId+"'";
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				otpRefId=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return otpRefId;
	}
	public int updateOTP(String customer_id,String otp)

	{
		log.info("Execute setcustomervarifiedindicator method of dmr_customer_dao class");
		int Status = 0;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EKO_DMR_Sender_Details set otp=? where Customer_id=?");
			pst.setString(1, otp);
			pst.setString(2, customer_id);
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
	public String getEKoUserCode(String agentId) {
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String eko_aeps_user_code=null;
		try {
			String sql="select eko_aeps_user_code from agent_details where agent_id="+Long.parseLong(agentId);
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				eko_aeps_user_code=rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return eko_aeps_user_code;
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


	public JSONObject getAllBeneficiary(String mobile, EkoAPICall dmrconsumeapi,
			String initiator_id) {
		log.info("Execute getAllBeneficiary method of dmr_customer_dao class");
		JSONObject finalObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		String url = PropertyFile.ekokycurl + mobile+ "/recipients?initiator_id=" + initiator_id + "";
		JSONObject returnobj = dmrconsumeapi.ekoCall(url,"","GET");
		JSONObject jsonObject = null;
		try {
			if (returnobj != null) {
				int status = (int) returnobj.get("status");
				if (status == 0 && returnobj.containsKey("data") /*&& !returnobj.isNull("data")*/) {
					JSONObject dataobj = (JSONObject) returnobj.get("data");
					JSONArray recipientlist = (JSONArray) dataobj.get("recipient_list");
					String Recipient_id_type = "";
					String account = "";
					String ifsc = "";


					log.info("recipientlist" + recipientlist);
					for (int i = 0; i < recipientlist.size(); i++) {
						jsonObject = new JSONObject();
						JSONObject json_data = recipientlist.getJSONObject(i);
						int recipient_idint = (int) json_data.get("recipient_id");
						String recipient_id = String.valueOf(recipient_idint);
						Recipient_id_type = json_data.optString("recipient_id_type");
						String name = json_data.getString("recipient_name");
						String channel = json_data.getString("channel");
						account = json_data.getString("account");
						ifsc = json_data.getString("ifsc");
						String bank_name = json_data.getString("bank");
						mobile = json_data.getString("recipient_mobile");

						if(mobile==null || mobile.equalsIgnoreCase("null"))
							mobile="NA";

						jsonObject.put("RecipientId", recipient_id);
						jsonObject.put("RecipientIdType", Recipient_id_type==null?"NA":Recipient_id_type);
						jsonObject.put("BeneName", name);
						jsonObject.put("Account", account);
						jsonObject.put("Ifsc", ifsc);
						jsonObject.put("BankName", bank_name);
						jsonObject.put("BeneMobile", mobile);
						jsonObject.put("IMPS", "2");
						jsonObject.put("NEFT", "1");
						jsonObject.put("Channel", channel);
						jsonArray.add(jsonObject);

					}
				} else {
					jsonArray = null;
				}
			} else {
				jsonArray = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in getAllBeneficiary method of dmr_customer_dao class" + e.getMessage());

			return null;
		}
		finalObject.put("recipient_list", jsonArray);
		return finalObject;
	}

	public String validateService(String agentID,String service,String operator,double amt,String senderId,String ben_account) {
		log.info("executing validateService method of DMR_Transaction_DaoEKO class");
		String status="N";
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			log.info(agentID);
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call DMR_Transaction_Validation(?,?,?,?,?,?,?)}");
			cstmt.setString(1,agentID);
			cstmt.setString(2,service);
			cstmt.setString(3,operator);
			cstmt.setDouble(4,amt);
			cstmt.setString(5,senderId);
			cstmt.setString(6,ben_account);
			cstmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(7);

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

	public String updateStatusDMRTranEKO(String sendorMobile,String transactionId,String Status,
			String vendorTranId,String message,String bankRRN,String tx_status)
	{
		//trandao.updateStatusDMRTranEKO(customer_mobile,transactionId, "Pending", "", "EKO",url + str, "no response from eko");

		log.info("executing updateStatusDMRTranEKO method of DMR_Transaction_DaoEKO class");
		String result="error";
		log.info("sendorMobile : "+sendorMobile);
		log.info("transactionId : "+transactionId);
		log.info("Status : "+Status);
		log.info("vendorTranId : "+vendorTranId);
		log.info("message : "+message);
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
			cstmt.setString(5,message);
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
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		try {

			log.info("::::::::::::: ifsc:: "+ifscCode);
			pst = con.prepareStatement("select BankName from DMR_BankCodeList where BankCode=? ");
			pst.setString(1, ifscCode);

			rs = pst.executeQuery();
			if (rs.next()) {
				ifsc=new Ifsc();
				ifsc.setBank_name(rs.getString(2));
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
		Connection con = DBConnection.getConnection();
		ResultSet rs = null;
		try {

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

	public static String getEKOTid(String TranNO)

	{
		String tid = null;
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement("select Reference_id from agent_transaction_details where (Transaction_Id=? OR Reference_id=?)");
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
			cstmt = con.prepareCall("{call Master_Bank_list()}");
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
				arrlist.add(jsonObject);
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
}
