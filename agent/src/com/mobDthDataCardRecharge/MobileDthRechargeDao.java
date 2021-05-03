package com.mobDthDataCardRecharge;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.db.DBConnection;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MobileDthRechargeDao {

	Logger logger=Logger.getLogger(MobileDthRechargeDao.class);

	
	public final String  updateStatusTranEGEN(String rechTranId, String tranStatus, String agentId, 
			String resultCode, String vendorSessionId, String checkVendor, String inputXML, String outputXML){
		Connection con = null;
		CallableStatement cstmt=null;
		String result="error";
		if(inputXML==null)
			inputXML="";
		System.out.println(" >>  "+"rechTranId : "+rechTranId+"  :   tranStatus : "+tranStatus+"  : agentId : "+agentId+"  :   vendorSessionId : "+vendorSessionId+"   : inputXML : "+inputXML);

		try 
		{
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call update_vendor_result_EGEN(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, rechTranId.trim());
			cstmt.setString(2, tranStatus);
			cstmt.setString(3, agentId);
			cstmt.setString(4, resultCode);
			cstmt.setString(5, vendorSessionId.trim());
			cstmt.setString(6, checkVendor);
			cstmt.setString(7, inputXML);
			cstmt.setString(8, outputXML);
			cstmt.setString(9, checkVendor);
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

	public String getAPIClientResponseURL(String agentId) 
	{

		logger.info("getAPIClientResponseURL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+agentId);
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		if(agentId!=null){
			try
			{

				con=DBConnection.getConnection();
				String sqlQurry="select Response_Url from API_Client_Response_URL where Agent_id="+Long.parseLong(agentId);
				logger.info("sqlQurry : "+sqlQurry);
				pstmt=con.createStatement();
				rst=pstmt.executeQuery(sqlQurry);

				while(rst.next())
				{
					ip=rst.getString("Response_Url");
				}
				logger.info("getVendorIp()>>>>>>>>>>>>>>>>>>>>>>  ip : "+ip);

			}catch(Exception exc)
			{
				exc.printStackTrace();
				logger.info("Exception getVendorIp "+exc);
				return null;
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
				}catch(Exception exc){

				}
			}

		}else{
			return null;
		}
		return ip;
	}
	public void saveAPIResponse(String tranId,String apiProvider,String response) 
	{

		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="insert into Recharge_API_Response_Details(Tran_id, API_Provider, Response, Date, Time) values "
					+ "(?,?,?,getdate(),getdate())";
			logger.info("sqlQurry : "+sqlQurry);
			pstmt=con.prepareStatement(sqlQurry);
			pstmt.setString(1, tranId);
			pstmt.setString(2, apiProvider);
			pstmt.setString(3,response);
			int count=pstmt.executeUpdate();

			System.out.println("MobileDthRechargeDao.saveAPIResponse() save response status >>>>>>>>>>>>>>>> "+count);

		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){

			}
		}
	}
	public static String getVendorIp() 
	{

		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select IP from Vendor_IP_Details";
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{
				ip=rst.getString("IP");
			}

		}catch(Exception exc)
		{
			exc.printStackTrace();
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
			}catch(Exception exc){

			}
		}
		return ip;
	}

	public HashMap<String, Object> getTranDetails(String marsRefId) 
	{

		logger.info("AG ID :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+marsRefId);
		HashMap<String, Object> map= null;
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select user_id,tran_id,service from live_recharge where tran_id='"+marsRefId.trim()+"'";
			logger.info("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{

				map= new HashMap<String, Object>();
				map.put("user_id", rst.getString("user_id"));
				map.put("tran_id", rst.getString("tran_id"));
				map.put("service", rst.getString("service"));

			}
			logger.info("getTranDetails()>>>>>>>>>>>>>>>>>>>>>>  Map : "+map);

		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception occurs in LiVE MRA App "+exc);
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
			}catch(Exception exc){

			}
		}
		return map;
	}

	public static ArrayList<HashMap<String,String>> fetchAllUtilityOperator() {
		ArrayList<HashMap<String,String>> opList=new ArrayList<>();
		HashMap<String,String> object=null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con=DBConnection.getConnection();
			psmt = con.prepareStatement("select distinct a.Service,a.Operator_Name,a.Display_Name,a.Operator_Code,a.BillFetch from Agent_Recharge_Billpay_Operator_Details a join newagentservice b on a.Main_Service=b.service and a.Sub_Service=b.Sub_Service and a.Operator_Name=b.operator and b.Active='Y' and b.MD_id=4001 and a.SK_Code is not null order by Service asc");
			rs = psmt.executeQuery();

			while(rs.next()){
				object=new HashMap<>();
				object.put("Service", rs.getString(1));
				object.put("OperatorName", rs.getString(2));
				object.put("DisplayName", rs.getString(3));
				object.put("OpCode", rs.getString(4));
				object.put("BillFetch", rs.getString(5));

				opList.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(rs!=null)
					rs.close();
				if(psmt!=null)
					psmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return opList;
	}
	public void sendResponseToAPIClient(String callBackUrl) {

		try {


			Client client = Client.create();
			WebResource webResource = client.resource(callBackUrl);
			ClientResponse response1 = webResource.type("text/html").get(ClientResponse.class);

			String output = response1.getEntity(String.class);
			if (response1.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response1.getStatus());

			}else{
				System.out.println("MobileDthRechargeDao.sendResponseToAPIClient() Status Sent Successfully");
			}

			System.out.println("Client Callbak response >>>>>>>>>>>>>>>>>>>>  "+output);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
