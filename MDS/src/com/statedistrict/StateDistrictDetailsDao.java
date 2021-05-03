package com.statedistrict;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.db.DBConnection;

public class StateDistrictDetailsDao {



	@SuppressWarnings("unchecked")
	public  HashMap <String,ArrayList<String>> getStateDistrict() {

		Connection con=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		HashMap <String,ArrayList<String>>districtData=new HashMap<String, ArrayList<String>>();
		ArrayList districtList=null;
		String state="";



		try
		{    System.out.println("[[==inside the stateDistrictDAO class==]]");
		con=DBConnection.getConnection();
		stmt=con.createStatement();
		stmt1=con.createStatement();
		String selectstate="select distinct(state) from Tep_State_District_Details order by state";
		rs=stmt.executeQuery(selectstate);

		while(rs.next())
		{

			state=rs.getString(1);
			districtList=new ArrayList();
			rs1=stmt1.executeQuery("select distinct(District) from Tep_State_District_Details where state='"+state+"'order by District ");
			while(rs1.next()){
				districtList.add(rs1.getString(1));
			}
			districtData.put(state, districtList);
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
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception In getStateDistrict for closing connction"+e.toString());
			}

		}
		return districtData;

	}


}
