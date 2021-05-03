/*
  Class Property :  This class (CommonDetailDao) is created for selecting distributor panel dynamic information.

  Created Date   : 23-Nov-2011 at 1:16 AM.
  Created By     : Bharatveer singh.

  Updated Date   : 3-Mar-2012
  Update By      : Amit Pathak.

 */
package com.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonDetailDao {
	Logger logger = Logger.getLogger(CommonDetailDao.class);
	LogWriter log = new LogWriter();


	public HashMap getDynamicDetails(String ServerName) {
		Session ses = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		HashMap map = new HashMap();
		String ClientIDForTicer = "";
		try {
			ses = HibernateSession.getSessionFactory().openSession();
			con = ses.connection();
			stmt = con.createStatement();
			String domainName = ServerName.replaceAll("www.", "");

			domainName = "www." + domainName;

			String sql ="select Client_Id,Client_Type,Distributor_user_type,Logo_Image,Company_name,Help_Desk_MobileNo,Help_Desk_EmailID,domain_name,Powered_By,Company_name,Distributor_login_url,MD_Ticker,banner_status from white_label_details where domain_name='"+domainName+"'";
			log.print("select distributor panel details query is : " + sql,
					logger);

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ClientIDForTicer = (String) rs.getString("Client_Id");
				String Distributor_user_type = (String) rs.getString("Distributor_user_type");
				map.put("Client_Id", rs.getString("Client_Id"));
				map.put("Client_Type", rs.getString("Client_Type"));
				map.put("user_type", rs.getString("Distributor_user_type"));
				map.put("distributor_header_home_image", rs.getString("Logo_Image"));
				map.put("distributor_help_mobile_no1", rs.getString("Help_Desk_MobileNo"));
				map.put("distributor_help_email_id1", rs.getString("Help_Desk_EmailID"));
				map.put("distributor_page_title", rs.getString("Company_name"));
				map.put("domain_name", rs.getString("domain_name"));
				map.put("Powered_By", rs.getString("Powered_By"));
				map.put("Distributor_login_url", rs.getString("Distributor_login_url"));
				map.put("company_name", rs.getString("Company_name"));
				map.put("TickerMessage",  rs.getString("MD_Ticker"));
				map.put("bannerStatus", rs.getString("banner_status"));
			}

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("exception in getDynamicDetails");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
				if(ses!=null)
					ses.close();

			} catch (Exception e) {
				System.out
				.println("exception CommonDetailDao for geting getDynamicDetails");
			}
		}
		return map;
	}

	public String getDsbalance(String distributorId) {

		double totalCash = 0;
		double usedCash = 0;
		double cutoffAamount = 0;
		String amount = "0";

		DecimalFormat dff = new DecimalFormat("##.00");
		Session session =null;

		try {
			session = HibernateSession.getSessionFactory().openSession();

			Query query = session
					.createQuery(
							"select dsa.totalCash,dsa.usedCash,dsa.cutoffAamount from DistributorAmountForm dsa where dsa.distributorId=:distributorId")
					.setParameter("distributorId", distributorId);


			Iterator it = query.iterate();
			if (it.hasNext()) {

				Object[] row = (Object[]) it.next();
				totalCash = (Double) row[0];
				usedCash = (Double) row[1];
				cutoffAamount = (Double) row[2];

			}

			amount = dff.format(totalCash - usedCash + cutoffAamount);

			System.out.println("amount--------" + amount);
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return amount;
	}

	public JSONObject getLogoUrlAndCompanyName(String clientId) {

		String baseUrl = "";
		String imageUrl = "";
		String connectingFolderName = "/images/";
		String finalUrl="";
		String companyName="";
		Connection con=null;
		ResultSet rs=null;
		JSONObject responseObject=null;
		Session session=null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			String sql="select Agent_login_url,Logo_Image,Company_Name from white_label_details  where Client_id=?";
			System.out.println(sql);
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, clientId);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				baseUrl = rs.getString(1);
				/*String s=baseUrl.trim().substring(0,7)+"www."+baseUrl.substring(7);
				System.out.println(s);
				baseUrl=s;*/
				imageUrl = rs.getString(2);	
				companyName = rs.getString(3);	
			}

			finalUrl = baseUrl + connectingFolderName+imageUrl;
			responseObject=new JSONObject();
			responseObject.put("LogoUrl",finalUrl);
			responseObject.put("CompanyName", companyName);
			System.out.println("Logo Url--------   " + finalUrl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{

			try {
				if(session!=null)
					session.close();
				if(rs!=null)
					rs.close();
				if(con!=null)
					con.close();

			} 
			catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return responseObject;
	}

	public JSONArray getAllServiceBuisinessDone(String distributor_id) {

		Connection con=null;
		JSONArray distributorBuisinessDetails=null;
		CallableStatement cStmt=null;
		ResultSet rs=null;
		Session session=null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			cStmt=con.prepareCall("{call Distributor_dashboard(?)}");
			cStmt.setLong(1,Long.parseLong(distributor_id.trim()));
			rs =cStmt.executeQuery();
			System.out.println(distributor_id);
			while(rs.next())
			{
				if(null==distributorBuisinessDetails)
					distributorBuisinessDetails=new JSONArray();
				JSONObject obj=new JSONObject();
				obj.put("Trans-Amount", rs.getString(1));
				obj.put("Trans-Count", rs.getString(2));
				obj.put("Trans-Status", rs.getString(3));
				obj.put("Service-Name", rs.getString(4));
				obj.put("Month-Type", rs.getString(5));
				distributorBuisinessDetails.add(obj);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(session!=null)
					session.close();
				if(rs!=null)
					rs.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return distributorBuisinessDetails;
	}


	public int saveLocation(long userId,String data,String type) {
		PreparedStatement pstmt=null;
		Connection con = null;
		int count=0;
		Session session=null;
		Transaction txn=null;
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			txn=session.beginTransaction();
			String sql="insert into location_tracker ( user_id, type, location, datetime) "
					+ "values  (?,?,?,getdate())";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, userId);
			pstmt.setString(2, type);
			pstmt.setString(3, data);

			count=pstmt.executeUpdate();

			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			txn.rollback();

		}finally{
			try {
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return count;
	}
}
