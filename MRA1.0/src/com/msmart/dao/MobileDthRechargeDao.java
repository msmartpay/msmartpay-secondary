package com.msmart.dao;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.msmart.dbconnection.DBConnection;

public class MobileDthRechargeDao {

	Logger logger=Logger.getLogger(MobileDthRechargeDao.class);
	
	public HashMap<String,String> getRechargeStatus(String agentID, String servicename,String operator, String amtrechg) {
		String status="NA",vendor="NA",mobtype="NA",vendorCode="";
		HashMap<String,String> hm=new HashMap<String,String>();
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call Recharge_Validation(?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, agentID);
			cstmt.setString(2, servicename);
			cstmt.setString(3, operator);
			cstmt.setString(4, amtrechg);
			cstmt.registerOutParameter(5, 12);
			cstmt.registerOutParameter(6, 12);
			cstmt.registerOutParameter(7, 12);
			cstmt.registerOutParameter(8, 12);
			cstmt.execute();
			status = cstmt.getString(5);
			vendor = cstmt.getString(6);
			vendorCode = cstmt.getString(7);
			mobtype=cstmt.getString(8);
			hm.put("status", status);
			hm.put("vendor", vendor);
			hm.put("vendorCode", vendorCode);
			hm.put("operator", mobtype);
			logger.info(hm);
		}catch (Exception ex) {
			logger.info("Exception in MobileDthRechargeDao class method (getRechargeStatus)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in MobileDthRechargeDao class method (getRechargeStatus) while closing connection");
				e.printStackTrace();
			}
		}
		return hm;
	}

	final HashMap<String, String> GetCyberText(String xmlRecords){		 
		HashMap<String,String> hmap=new HashMap<String,String>();

		try {

			DocumentBuilderFactory dbf =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();       
			is.setCharacterStream(new StringReader(xmlRecords));
			Document doc = db.parse(is);
			// logger.info("helloo i get called in getText Method");
			NodeList nodes = doc.getElementsByTagName("Response");		
			int xmlnodesize=nodes.getLength();
			// logger.info("Xml Node  is : "+nodes);
			for (int i = 0; i < xmlnodesize; i++) {
				Element element = (Element) nodes.item(i);				 
				//	 logger.info("we are inside for loop is : " +element);				 	
				NodeList errCode = element.getElementsByTagName("error");
				// logger.info("we are inside for nodlist, errCode is : " +errCode);
				Element line = (Element) errCode.item(0);
				String errCodeVal= getCharacterDataFromElement(line);
				// logger.info("stCodeVal is : "+errCodeVal);
				hmap.put("ERROR",errCodeVal);
				NodeList resCode = element.getElementsByTagName("result");
				line = (Element) resCode.item(0);
				String resCodeVal=getCharacterDataFromElement(line);
				hmap.put("RESULT",resCodeVal);
				NodeList tranId = element.getElementsByTagName("tranId");
				line = (Element) tranId.item(0);
				String tranIdVal=getCharacterDataFromElement(line);
				hmap.put("TRANID",tranIdVal);
			}
		}
		catch (Exception e) {
			logger.info("Exception in MobileDthRechargeDao class method GetCyberText");
			e.printStackTrace();
		}
		return hmap;
	}

	final String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}	

	public final String insertRecordNewQuery(String transaction_id,
			String agentID, String operator, String mobileNo, double amtrechg, String tRANSNO, String servicename, 
			String cleintIpaddress, String apiProvider,
			String custmobileNo) {
		Connection con = null;
		CallableStatement cstmt=null;
		String status="NA";
		try
		{

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Recharge_Insert(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,transaction_id);
			cstmt.setLong(2,Long.parseLong(agentID));
			cstmt.setString(3,operator);
			cstmt.setString(4,mobileNo);
			cstmt.setDouble(5,amtrechg);
			cstmt.setString(6,tRANSNO);
			cstmt.setString(7,servicename);
			cstmt.setString(8,cleintIpaddress);
			cstmt.setString(9,custmobileNo);
			cstmt.setString(10,apiProvider);
			cstmt.registerOutParameter(11, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(11);

			logger.info("Insert Record :: "+status);
		}catch (Exception ex) {
			logger.info("Exception in MobileDthRechargeDao class method (insertRecordNewQuery)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in MobileDthRechargeDao class method (insertRecordNewQuery) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}

	public final String  updateStatusTranEGEN(String rechTranId, String tranStatus, String aGuser_id, String resultCode, 
			String vendorSessionId, String operatorId, String inputXML, String outputXML){
		Connection con = null;
		CallableStatement cstmt=null;
		String result="error";
		if(inputXML==null)
			inputXML="";
		logger.info(" >>  "+"rechTranId : "+rechTranId+"  :   tranStatus : "+tranStatus+"  : aGuser_id : "+aGuser_id+"  :   vendorSessionId : "+vendorSessionId+"   : inputXML : "+inputXML);

		try 
		{
			String apiProvider="";
			con = DBConnection.getConnection();
			cstmt = con.prepareCall("{call update_vendor_result_EGEN(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, rechTranId.trim());
			cstmt.setString(2, tranStatus);
			cstmt.setString(3, aGuser_id);
			cstmt.setString(4, resultCode);
			cstmt.setString(5, operatorId);
			cstmt.setString(6, vendorSessionId+"");
			cstmt.setString(7, inputXML);
			cstmt.setString(8, outputXML);
			cstmt.setString(9, apiProvider);
			cstmt.registerOutParameter(10, 12);
			cstmt.execute();
			result = cstmt.getString(10);
			logger.info("Update Status EGEN :: "+result);
		}catch (Exception ex) {
			logger.info("Exception in MobileDthRechargeDao class method (updateStatusTranGoal)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				logger.info("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return result;
	}

	final String insertOfflineRecordNew(String AGuser_id,String  Operator,double  amt,String  mobileNo,String TranId,String AGTranID,String cleintIpaddress,String servicename) {
		String result="error";
		Connection con = null;
		CallableStatement cstmt=null;
		try
		{
			//logger.info("we are into offline insert record Query");

			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Insert_OffLineRecharge_Data(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,AGuser_id);
			cstmt.setString(2,Operator);
			cstmt.setString(3,mobileNo);
			cstmt.setDouble(4,amt);
			cstmt.setString(5,TranId);
			cstmt.setString(6,AGTranID);
			cstmt.setString(7,servicename);
			cstmt.setString(8,cleintIpaddress);
			cstmt.setString(9,"All Circle");


			cstmt.registerOutParameter(10, java.sql.Types.VARCHAR);
			cstmt.execute();
			result=cstmt.getString(10);
			logger.info(result);

		}
		catch(Exception e)
		{
			result="error";
			logger.info("Hello exception in getReMobile method ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(cstmt !=null)
					cstmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				logger.info("Exception in closing connection getReMobile");
				e.printStackTrace();
			}

		}
		return result;
	}
	
	public HashMap<String, String> getTranDetails(String marsRefId,String OrderNo,String refId) 
	{

		System.out.println("AG ID :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+marsRefId);
		HashMap<String, String> map= null;
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select user_id,tran_id,service,mobile_operator,mobile_number,date_of_recharge,RequestTime,status,Agent_tran_id from live_recharge where ( tran_id='"+refId+"')";
			System.out.println("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{

				map= new HashMap<String, String>();
				map.put("user_id", rst.getString("user_id")+"");
				map.put("tran_id", rst.getString("tran_id")+"");
				map.put("service", rst.getString("service")+"");
				
				map.put("mobile_operator", rst.getString("mobile_operator")+"");
				map.put("mobile_number", rst.getString("mobile_number")+"");
				map.put("dot", rst.getString("date_of_recharge")+"");
				map.put("tot", rst.getString("RequestTime")+"");
				map.put("status", rst.getString("status")+"");
				map.put("Agent_tran_id", rst.getString("Agent_tran_id")+"");

			}
			System.out.println("getTranDetails()>>>>>>>>>>>>>>>>>>>>>>  Map : "+map);

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
			}catch(Exception exc){

			}
		}
		return map;
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
			String sqlQurry="select user_id,tran_id,service from live_recharge where USessionID='"+marsRefId.trim()+"'";
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
		return map;
	}

	public String getVendorIp() 
	{

		logger.info("getVendorIp >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
		try
		{

			con=DBConnection.getConnection();
			String sqlQurry="select IP from Vendor_IP_Details";
			logger.info("sqlQurry : "+sqlQurry);
			pstmt=con.createStatement();
			rst=pstmt.executeQuery(sqlQurry);

			while(rst.next())
			{
				ip=rst.getString("IP");
			}
			logger.info("getVendorIp()>>>>>>>>>>>>>>>>>>>>>>  ip : "+ip);

		}catch(Exception exc)
		{
			exc.printStackTrace();
			logger.info("Exception getVendorIp "+exc);
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

	public String getAPIClientResponseURL(String agentId) 
	{

		logger.info("getAPIClientResponseURL >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+agentId);
		Connection con=null;
		Statement pstmt=null;
		ResultSet rst=null;
		String ip=null;
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
