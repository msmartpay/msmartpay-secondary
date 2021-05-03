package com.controlPanel.editServices;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;

import com.common.HibernateSession;

public class EditServiceDao
{
	Session session;
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;


	public ArrayList getAgentService()
	{
		ArrayList alt = new ArrayList();
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct(service) from agentservice where service not in ('rail','Bill_pay','DthConnection','Re_Dth','Re_Mobile')";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			String service = "";
			HashMap map;
			for(; rs.next(); alt.add(map))
			{
				service = rs.getString(1);
				if(service.equalsIgnoreCase("bl_Lic"))
					service = "LIC";
				else
					if(service.equalsIgnoreCase("PrepaidCard"))
						service = "PREPAID CARD";
					else
						if(service.equalsIgnoreCase("Live_Billpay"))
							service = "LIVE BILLPAY";
						else
							if(service.equalsIgnoreCase("Live_Recharge"))
								service = "LIVE RECHARGE";
							else
								if(service.equalsIgnoreCase("request_pancard"))
									service = "PANCARD";
				map = new HashMap();
				map.put("services", service);
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception in EditServiceDao,getAgentService");
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
		return alt;
	}

	public String updateAgentService(String serviceOption, String filterOption)
	{
		String status = "";
		if(serviceOption.equalsIgnoreCase("LIC"))
			serviceOption = "bl_Lic";
		else
			if(serviceOption.equalsIgnoreCase("PREPAID CARD"))
				serviceOption = "PrepaidCard";
			else
				if(serviceOption.equalsIgnoreCase("LIVE BILLPAY"))
					serviceOption = "Live_Billpay";
				else
					if(serviceOption.equalsIgnoreCase("LIVE RECHARGE"))
						serviceOption = "Live_Recharge";
					else
						if(serviceOption.equalsIgnoreCase("PANCARD"))
							serviceOption = "request_pancard";
		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = (new StringBuilder("update agentservice set Active='")).append(filterOption).append("' where service='").append(serviceOption).append("'").toString();
			psmt = con.prepareStatement(sql);
			int i = psmt.executeUpdate();
			if(i > 0)
				status = "success";
			else
				status = "failure";
		}
		catch(Exception e)
		{
			status = "failure";
			System.out.println("Exception in EditServiceDao,getAgentService");
			System.out.println(e.toString());
		}
		finally{
			try {
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return status;
	}


}
