package com.msmart.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.msmart.dbconnection.DBConnection;
import com.msmart.eko.EkoDMTDao;
import com.msmart.ekonew.EkoAPICall;
import com.msmart.service.PropertyFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Job {


	public static ArrayList<String> getcustomerName(String senderId)
	{
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = null;

		ArrayList<String> list=new ArrayList<>();
		try {

			con = DBConnection.getConnection();
			pst = con.prepareStatement("select Customer_id from EKO_DMR_Sender_Details where sync_flag=0 and Customer_id='"+senderId+"'");
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
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
		return list;

	}

	public static void updateCustomerSyncStatus(String sender)
	{
		PreparedStatement pst = null;
		Connection con = null;

		try {

			con = DBConnection.getConnection();
			pst = con.prepareStatement("update EKO_DMR_Sender_Details set sync_flag=1 where Customer_id='"+sender+"'");
			int i=pst.executeUpdate();
			System.out.println("Update sync status : "+i);

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

	}

	

	public static void insertBene(JSONArray beneList,String senderId)
	{

		EkoDMTDao ekoDMTDao=new EkoDMTDao();
		for (int i = 0; i < beneList.size(); i++) {

			JSONObject object=beneList.getJSONObject(i);
			
			String addbeneficiarystatus=ekoDMTDao.addBeneficiaryNew(
					object.getString("recipient_name")
					, senderId, object.getString("bank")
					, object.getString("ifsc")
					, object.getString("account"), senderId
					, object.getString("recipient_id")
					, object.getInt("channel")
					, object.getInt("is_verified"));


			System.err.println(" Bene merge status : "+addbeneficiarystatus);
		}



	}

	/*
	// Register beneficiary on PayTM
	public static void regBenePayTM() {

		JSONObject jsonobj=null;
		try {

			ArrayList<HashMap<String,String>> beneList=fetchAllBeneficiary();


			JSONObject fsJson=new JSONObject();
			fsJson.put("Key", "af199094e81487af7d963f7d908b80bc5f16ae64");
			fsJson.put("AgentID", "2002");


			for (int i = 0; i < beneList.size(); i++) {

				HashMap<String,String> map=beneList.get(i);
				String account=map.get("Account_Number");

				fsJson.put("SenderId", map.get("Customer_mob_no"));
				fsJson.put("BankAccount", account);
				fsJson.put("BeneName", map.get("Beneficiary_name"));
				fsJson.put("IFSC", map.get("IFSC_code"));
				fsJson.put("BeneMobile", "9711402774");
				fsJson.put("BankCode", "");
				fsJson.put("varify", "N");

				MRAController MRAController=new MRAController();

				jsonobj =MRAController.callApi("http://partner.smartkinda.com/B2CMRA1.0/resources/SKDMRR/FindSender", fsJson);


				//logger.info("DMRApi.getFindSender()"+jsonobj.toString());
				boolean bstatus=true;

				String status=jsonobj.getString("Status");
				if("0".equalsIgnoreCase(status)){

					JSONArray BeneListArray=jsonobj.getJSONArray("BeneList");

					for (int j = 0; j < BeneListArray.length(); j++) {

						JSONObject object=BeneListArray.getJSONObject(j);
						if(object.getString("Account").equalsIgnoreCase(account)) {
							System.err.println(" Bene duplicate : "+account);
							bstatus=false;
						}
					}

				}
				if(bstatus) {
					jsonobj =MRAController.callApi("http://partner.smartkinda.com/B2CMRA1.0/resources/SKDMRR/AddBeneAfterVerify", fsJson);

					System.out.println("Bene Add Status : "+i+" : "+jsonobj);

					String Status=jsonobj.getString("Status");
					if("0".equalsIgnoreCase(Status)) {
						updateBeneSyncStatus(map.get("Customer_mob_no"));
					}
				}else {
					updateBeneSyncStatus(map.get("Customer_mob_no"));
				}




			}



		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	 */
	//update bene syn status
	public static ArrayList<String> updateBeneSyncStatus(String sender)
	{
		PreparedStatement pst = null;
		Connection con = null;

		ArrayList<String> list=new ArrayList<>();
		try {

			con = DBConnection.getConnection();
			pst = con.prepareStatement("update PayTM_Beneficiary_Details set eko_sync_flag=1 where Customer_mob_no='"+sender+"'");
			int i=pst.executeUpdate();
			System.out.println("Update sync status : "+i);

		} catch (Exception e) {
			return null;
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
		return list;

	}

	public static void mergeBene(String senderId) {

		JSONObject jsonobj=null;
		try {


			System.out.println("Merging started for SenderId  : "+senderId);

			String url = "https://api.eko.in:25002/ekoicici/v1/customers/mobile_number:" + senderId+ "/recipients?initiator_id=" + PropertyFile.initiator_id ;

			EkoAPICall apiCall=new EkoAPICall();
			jsonobj = apiCall.ekoCall(url,"","GET");

			if(jsonobj!=null) {
				System.out.println("Response"+jsonobj);

				if(jsonobj.has("status")) {
					int status=jsonobj.getInt("status");
					int response_type_id=jsonobj.getInt("response_type_id");
					if(status==0 && response_type_id!=22) {

						JSONObject data=jsonobj.getJSONObject("data");

						JSONArray BeneListArray=data.getJSONArray("recipient_list");
						if(BeneListArray!=null && BeneListArray.size()>0) {
							//System.out.println("BeneListArray : "+BeneListArray);
							insertBene(BeneListArray, senderId);
						}

					}
				}
				updateCustomerSyncStatus(senderId);

			}




		

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
