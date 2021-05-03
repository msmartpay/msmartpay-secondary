package com.controlPanel.deductionQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class BalanceDeductionQueryDao
{

	Session session;
	public BalanceDeductionQueryDao()
	{
	}

	public ArrayList getDistributorList()
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
			System.out.println("----------Inside BalanceDeduction Query Dao class------------");
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distributor_initial+convert(varchar(20),distributor_id) as dsId from distributor_details";
			psmt = con.prepareStatement(sql);
			HashMap map;
			for(rs = psmt.executeQuery(); rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("dsId", rs.getString(1));
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception in BalanceDeductionQueryDao getDistributorList()");
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

	public ArrayList getDetailedList(String DsID, String agentId, String filterOption)
			throws SQLException
	{
		ArrayList data;
		Connection con;
		PreparedStatement psmt;
		ResultSet rs;
		String sql;
		data = new ArrayList();
		con = null;
		psmt = null;
		rs = null;
		sql = "";
		try
		{
			System.out.println("----------Inside BalanceDeduction Query Dao class------------");
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			if(filterOption.equalsIgnoreCase("idwise"))
				sql = "select Client_ID,DS_ID,agent_initial+convert(varchar(20),agent_id) as Agent_ID from agent_details where DS_ID=? and agent_initial+convert(varchar(10),agent_id)=?";
			else
				if(filterOption.equalsIgnoreCase("all"))
					sql = "select Client_ID,DS_ID,agent_initial+convert(varchar(20),agent_id) as Agent_ID from agent_details where DS_ID=?";
			psmt = con.prepareStatement(sql);
			if(filterOption.equalsIgnoreCase("idwise"))
			{
				psmt.setString(1, DsID);
				psmt.setString(2, agentId);
			} else
				if(filterOption.equalsIgnoreCase("all"))
					psmt.setString(1, DsID);
			HashMap map;
			for(rs = psmt.executeQuery(); rs.next(); data.add(map))
			{
				map = new HashMap();
				map.put("client_id", rs.getString(1));
				map.put("ds_id", rs.getString(2));
				map.put("agent_id", rs.getString(3));
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception in BalanceDeductionQueryDao getDetailedList()");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return data;
	}

	public String updateDSDetails(String agentId, String amount, String ipAdr, String remark, int year, int month, int dayOfMonth, 
			String userType,String trType)
	{
		String status = "";
		Connection con = null;
		CallableStatement csmt = null;
		ResultSet rs = null;
		Transaction txn=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			txn=session.beginTransaction();
			csmt = con.prepareCall("{call SafeCustodyAgent(?,?,?,?,?,?,?,?,?,?)}");
			csmt.setString(1, amount);
			csmt.setString(2, remark);
			csmt.setString(3, remark);
			csmt.setString(4, agentId);
			csmt.setString(5, String.valueOf(year));
			csmt.setString(6, String.valueOf(month));
			csmt.setString(7, String.valueOf(dayOfMonth));
			csmt.setString(8, ipAdr);
			csmt.setString(9, userType);
			csmt.setString(10,trType);

			for(rs = csmt.executeQuery(); 
					rs.next(); 
					System.out.println((new StringBuilder("status----")).append(status).toString()))
				status = rs.getString(1);
			txn.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println((new StringBuilder("---exception in balance deduction------dao-----")).append(e).toString());
		}
		finally{
			try {
				rs.close();
				con.close();
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return status;
	}
}
