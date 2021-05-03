package com.controlPanel.userRegistrationLimit;

import com.common.HibernateSession;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserRegLimitDao
{

	Session session;
	public UserRegLimitDao()
	{
	}

	public ArrayList getUserMDLimitationdata() throws SQLException {
		ArrayList data;
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		data = new ArrayList();
		con = null;
		psmt = null;
		rs = null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct client_id,md_id,DS_Creation_Limit from MD_DS_Registration_Limit";
			System.out.println("----------s1------------");
			psmt = con.prepareStatement(sql);
			System.out.println("----------s2------------");
			rs = psmt.executeQuery();
			System.out.println("----------s3------------");
			HashMap map;
			for(; rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("ClientId", rs.getString(1));
				map.put("MdId", rs.getString(2));
				map.put("DSLimit", rs.getString(3));
			}

			System.out.println((new StringBuilder("--data-----")).append(data).toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserRegLimitDao getUserMDLimitationdata");
			System.out.println(e.toString());
		}
		finally{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	public ArrayList getUserDSLimitationdata()
			throws SQLException
	{
		ArrayList data;
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		data = new ArrayList();
		con = null;
		psmt = null;
		rs = null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct client_id,md_id,DS_Id,AG_Creation_Limit from DS_AG_Registration_Limit";
			System.out.println("----------s1------------");
			psmt = con.prepareStatement(sql);
			System.out.println("----------s2------------");
			rs = psmt.executeQuery();
			System.out.println("----------s3------------");
			HashMap map;
			for(; rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("ClientId", rs.getString(1));
				map.put("MdId", rs.getString(2));
				map.put("DSId", rs.getString(3));
				map.put("AGRegLimit", rs.getString(4));
			}

			System.out.println((new StringBuilder("--data-----")).append(data).toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserRegLimitDao getUserDSLimitationdata");
			System.out.println(e.toString());
		}
		finally{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	public ArrayList getOneUserMDLimitationdata(String useId)
			throws SQLException
	{
		ArrayList data;
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		data = new ArrayList();
		con = null;
		psmt = null;
		rs = null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct client_id,md_id,DS_Creation_Limit from MD_DS_Registration_Limit where md_id=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, useId);
			HashMap map;
			for(rs = psmt.executeQuery(); rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("ClientId", rs.getString(1));
				map.put("MdId", rs.getString(2));
				map.put("DSLimit", rs.getString(3));
			}

			System.out.println((new StringBuilder("--data-----")).append(data).toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserRegLimitDao getOneUserMDLimitationdata");
			System.out.println(e.toString());
		}
		finally{
			try {
				rs.close();
       		 psmt.close();
       		 con.close();
                session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	public ArrayList getOneUserDSLimitationdata(String useId)
			throws SQLException
	{
		ArrayList data;
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		data = new ArrayList();
		con = null;
		psmt = null;
		rs = null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct client_id,md_id,DS_Id,AG_Creation_Limit from DS_AG_Registration_Limit where DS_Id=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, useId);
			HashMap map;
			for(rs = psmt.executeQuery(); rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("ClientId", rs.getString(1));
				map.put("MdId", rs.getString(2));
				map.put("DSId", rs.getString(3));
				map.put("AGRegLimit", rs.getString(4));
			}

			System.out.println((new StringBuilder("--data-----")).append(data).toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in UserRegLimitDao getOneUserDSLimitationdata");
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
       		 psmt.close();
       		 con.close();
                session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}

	public String updateMDDetails(String clientgetId, String mdgetId, int dsregLimit)
			throws Exception
	{
		String status;
		status = "";
		System.out.println((new StringBuilder("--data-----")).append(clientgetId).append("---").append(mdgetId).append("------").append(dsregLimit).toString());
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			String sql = (new StringBuilder("update MD_DS_Registration_Limit set DS_Creation_Limit='")).append(dsregLimit).append("' where Client_Id='").append(clientgetId).append("' and MD_Id='").append(mdgetId).append("'").toString();
			System.out.println((new StringBuilder("sql--------")).append(sql).toString());
			Query query = session.createSQLQuery(sql);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			status = "Success";
		}
		catch(Exception e)
		{
			status = "fail";
			System.out.println("Exception in UserRegLimitDao-------- getSubService");
			e.printStackTrace();
		}finally{
			try {
                session.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return status;
	}

	public String updateDSDetails(String clientgetId, String mdgetId, String dsgetId, int agregLimit) throws Exception
	{
		String status;
		status = "";
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			String sql = (new StringBuilder("update DS_AG_Registration_Limit set AG_Creation_Limit='")).append(agregLimit).append("' where Client_Id='").append(clientgetId).append("' and MD_Id='").append(mdgetId).append("' and DS_Id='").append(dsgetId).append("'").toString();
			System.out.println(sql);
			Query query = session.createSQLQuery(sql);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			status = "Success";
		}
		catch(Exception e)
		{
			status = "fail";
			System.out.println("Exception in UserRegLimitDao----- getSubService");
			e.printStackTrace();
		}finally{
            session.close();
		}

		return status;
	}
}
