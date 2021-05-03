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
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class CommonDetailDao {
	Logger logger = Logger.getLogger(CommonDetailDao.class);
	LogWriter log = new LogWriter();


	public HashMap getDynamicDetails(String ServerName) {
		Session ses = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		HashMap map = new HashMap();
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
		Session session = HibernateSession.getSessionFactory().openSession();

		try {

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
	public static HashMap<String,String> getDynamicMailerDetails(String userId)
	{

		HashMap<String,String> mapdata=new HashMap<String,String>();;
		Session session=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="select Mailer_id,Mailer_password,SMS_id,SMS_password from white_label_details where Client_Id=(select client_id from distributor_details where distributor_id="+userId+")";
			Query query2=session.createSQLQuery(sql);
			List list=query2.list();
			Iterator itr=list.iterator();
			Object [] row;

			while(itr.hasNext())
			{
				row=(Object[])itr.next();
				mapdata.put("Mailer_id",row[0].toString());
				mapdata.put("Mailer_password",row[1].toString());
				mapdata.put("SMS_id",row[2].toString());
				mapdata.put("SMS_password",row[3].toString());
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
				System.out.println(e.toString());
			}
		}
		return mapdata;
	}

	/***********************************
	 * 	GET BUSINESS DETAILS
	 * *************************************************/
	public static ArrayList<HashMap<String,String>> getBusinessDetails(String userId)
	{

		//		HashMap<String,String> currentMonth=new HashMap<String,String>();
		//		HashMap<String,String> previousMonth=new HashMap<String,String>();
		ArrayList<HashMap<String,String>> businessDetails=null;//=new HashMap<String,HashMap<String,String>>();
		Session session=null;
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="{call Distributor_dashboard(?)}";
			con=session.connection();
			cstmt=con.prepareCall(sql);
			cstmt.setInt(1, Integer.parseInt(userId.trim()));
			rs=cstmt.executeQuery();

			while(rs.next())
			{
				if(businessDetails==null)
					businessDetails=new ArrayList<>();
				HashMap<String,String> business=new HashMap<String,String>();

				business.put("Trans-Amount", rs.getString(1));
				business.put("Trans-Count", rs.getString(2));
				business.put("Trans-Status", rs.getString(3));
				business.put("Service-Name", rs.getString(4));
				business.put("Month-Type", rs.getString(5));
				businessDetails.add(business);
			}


		}
		catch(HibernateException e)
		{
			System.out.println("Exception in CommonDetailDao,ViewBusiness ");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
				System.out.println(e.toString());
			}
		}
		return businessDetails;
	}
}
