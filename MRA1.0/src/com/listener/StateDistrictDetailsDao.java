package com.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.hibernate.Session;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONArray;
public class StateDistrictDetailsDao {

	public  ArrayList<String> getState() {

		Session session = null;
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String state="";

		ArrayList<String> stateList=new ArrayList<>();
		try
		{
			
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String selectstate="select distinct(state) from Tep_State_District_Details order by state";
			rs=stmt.executeQuery(selectstate);

			while(rs.next())
			{
				state=rs.getString(1);
				stateList.add( state);
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
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			districtList=new JSONArray();
			rs=stmt.executeQuery("select District from Tep_State_District_Details where state='"+state+"'order by District");
			while(rs.next()){
				districtList.add(rs.getString(1));
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
