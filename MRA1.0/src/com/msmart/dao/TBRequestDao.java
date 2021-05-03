package com.msmart.dao;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TBRequestDao {

	public JSONArray collectionBank(long agentID) {
		String sql="";
		JSONArray data=new JSONArray();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select bank_name,bank_account,bank_account_name,bnk_ifsc from wb_collection_bank_detils (nolock) a join agent_details (nolock) b on a.client_id=b.Client_ID and b.agent_id=? and a.status=1";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, agentID);
			rs=pstmt.executeQuery();
			JSONObject temp;
			while(rs.next()){
				temp=new JSONObject(); 
				temp.put("bank_name",rs.getString(1)); 
				temp.put("bank_account",rs.getString(2)); 
				temp.put("bank_account_name",rs.getString(3));
				temp.put("bnk_ifsc",rs.getString(4));  
				data.add(temp);			 
			}

		}catch (Exception ex) {
			ex.printStackTrace();
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
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public JSONArray getDepositDetail(String agentID) {
		String sql="";
		JSONArray data=new JSONArray();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{

			con=DBConnection.getConnection();
			sql="select top(5) request_date,convert(varchar(10),request_time,108) as time,remarks,str(ammount_to_credit,10,2),status,convert(datetime,convert(varchar(10),request_date,120)+' '+convert(varchar(10),request_time,120)) as dattime,isnull(journal_id,'NA') as refId from agent_journal where agent_id=? and mode_of_payment not in ('distributor','Push_Transfer') order by dattime desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, agentID);
			rs=pstmt.executeQuery();
			JSONObject temp;
			while(rs.next()){
				temp=new JSONObject(); 
				temp.put("date",rs.getString(1)); 
				temp.put("time",rs.getString(2)); 
				temp.put("mode",rs.getString(3));
				temp.put("amount",rs.getString(4)!=null?rs.getString(4).trim():rs.getString(4));  
				temp.put("status",rs.getString(5));
				temp.put("refId",rs.getString(7));
				data.add(temp);			 
			}

		}catch (Exception ex) {
			System.out.println("Exception in TransaferRequestDao class method (getDepositDetail)");
			ex.printStackTrace();
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
				System.out.println("Exception in TransaferRequestDao class method (getDepositDetail) while closing connection");
				e.printStackTrace();
			}
		}
		return data;
	}

	public String insertRecordForBalanceReq(String agentID, String transactionId,String depDate, String amount,String cbDepBrTid, 
			String cbRecBankName, String acceptAmt,String cbremarks,String mode) {
		String sql="";
		String status="fail";
		Connection con = null;
		PreparedStatement pstmt=null;
		try
		{

			con=DBConnection.getConnection();
			con.setAutoCommit(false);
			sql="INSERT INTO agent_journal (agent_id,journal_id,journal_date,Request_Date,Request_Time,Mode_of_payment,Ammount_To_Credit,Bank_charges,"
					+ "Accepted_Amount,bank_name,branch_name,branch_city,account_no,transaction_id,Deposit_Location,Deposit_Date,Status,Remarks,distributor_id,Depositor_Name)values" +
					"(?,?,getdate(),getdate(),getdate(),?,?,?,?,?,?,?,?,?,?,?,'Pending',?,(select distributor_id from agent_details where agent_id="+agentID+"),?)";
			//System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, agentID);
			pstmt.setString(2, transactionId);
			pstmt.setString(3, mode);
			pstmt.setString(4, amount);
			pstmt.setString(5, "0");
			pstmt.setString(6, acceptAmt);
			pstmt.setString(7, cbRecBankName);
			pstmt.setString(8, "");
			pstmt.setString(9, "");
			pstmt.setString(10, "");
			pstmt.setString(11, cbDepBrTid);
			pstmt.setString(12, "");
			pstmt.setString(13, depDate);
			pstmt.setString(14, cbremarks);
			pstmt.setString(15, "");
			pstmt.execute();
			con.commit();
			status="Success";
		}catch (Exception ex) {
			status="fail";
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Exception in TransaferRequestDao class method (insertRecordForCashDeposite)");
			ex.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in TransaferRequestDao class method (insertRecordForCashDeposite) while closing connection");
				e.printStackTrace();
			}
		}
		return status;
	}


	public static String createXmlResponseReqDetails(ArrayList<HashMap<String, String>> data_list)
	{
		HashMap<String, String> data_map;
		int size=0;
		try{
			size=data_list.size();
			String xmlString;
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root1=doc.createElement("response-Statement");
			doc.appendChild(root1);
			for(int i=0;i<size;i++){
				data_map=data_list.get(i);
				Element  root= doc.createElement("transaction_detail");
				root1.appendChild(root);	

				Element id = doc.createElement("Date");
				root.appendChild(id);
				Text id_num = doc.createTextNode(data_map.get("date"));
				id.appendChild(id_num);

				Element Time = doc.createElement("Time");
				root.appendChild(Time);
				Text Time_name = doc.createTextNode(data_map.get("time"));
				Time.appendChild(Time_name);

				Element Mode = doc.createElement("Mode");
				root.appendChild(Mode);
				Text Mode_txt = doc.createTextNode(data_map.get("mode"));
				Mode.appendChild(Mode_txt);


				Element Status = doc.createElement("Status");
				root.appendChild(Status);
				Text Status_txt = doc.createTextNode(data_map.get("status"));
				Status.appendChild(Status_txt);

			}

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			xmlString = sw.toString();
			data_list.clear();

			System.out.println("StatementNReportUtil.createXmlResponseStatement() "+xmlString);
			return xmlString;

		}catch(Exception ex)
		{
			System.out.println("Exception "+ex);
			ex.printStackTrace();
		}
		return null;
	}

}
