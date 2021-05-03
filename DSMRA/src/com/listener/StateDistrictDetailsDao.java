package com.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.hibernate.Session;
import com.common.HibernateSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class StateDistrictDetailsDao {

	public  JSONArray getState() {

		Session session = null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String state="";

		JSONArray stateList=new JSONArray();
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			stmt=con.createStatement();
			JSONObject object=new JSONObject();
			object.put("state", "Select State");
			stateList.add(object);
			String selectstate="select distinct(state) from Tep_State_District_Details order by state";
			rs=stmt.executeQuery(selectstate);

			while(rs.next())
			{
				state=(rs.getString(1));
				if(state!=null){
					
					object=new JSONObject();
					object.put("state", state);
					stateList.add(object);
					
				}
			}
			System.out.println("stateList is  :::"+stateList.size());
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception In getState"+e.toString());
		}


		finally
		{
			try
			{
				if(con!=null)
					con.close();
				if(session!=null)
				{
					session.close();

				}

			}catch(Exception e)
			{
				System.out.println("Exception In getState for closing connction"+e.toString());
			}

		}
		return stateList;

	}


	public JSONArray getStateDistrict(String state) {

		Session session = null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		JSONArray districtList=null;

		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			stmt=con.createStatement();
			districtList=new JSONArray();
			JSONObject object=new JSONObject();
			object.put("district", "Select District");
			districtList.add(object);
			rs=stmt.executeQuery("select District from Tep_State_District_Details where state='"+state+"'order by District");
			while(rs.next()){
				
				String district=rs.getString(1);
				if(district!=null){
					object=new JSONObject();
					object.put("district", district);
					districtList.add(object);
				}
				
			}

		}
		catch(Exception e)
		{
			System.out.println("Exception In getStateDistrict"+e.toString());
		}


		finally
		{
			try
			{
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();

			}
			catch(Exception e)
			{
				System.out.println("Exception In getStateDistrict for closing connction"+e.toString());
			}

		}
		return districtList;

	}


}
