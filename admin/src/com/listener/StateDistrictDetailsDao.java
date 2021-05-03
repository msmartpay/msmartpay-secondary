package com.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.hibernate.Session;
import com.common.HibernateSession;

public class StateDistrictDetailsDao {
	
	public  HashMap <String,ArrayList<String>> getStateDistrict() 
	{
		Session session = null;
		Connection con=null;
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
	    HashMap <String,ArrayList<String>>districtData=new HashMap<String, ArrayList<String>>();
	    ArrayList districtList=null;
	    String state="";
	    
	    try
		{
	    	session = HibernateSession.getSessionFactory().openSession();
	    	con=session.connection();
	    	stmt=con.createStatement();
	    	stmt1=con.createStatement();
	    	String selectstate="select distinct(state) from Tep_State_District_Details order by state";
	    	rs=stmt.executeQuery(selectstate);
			
	    	while(rs.next())
			{
	    		state=rs.getString(1);
	    		districtList=new ArrayList();
	    		rs1=stmt1.executeQuery("select District from Tep_State_District_Details where state='"+state+"'order by District ");
	    		
	    		while(rs1.next())
	    		{
	    			districtList.add(rs1.getString(1));
	    		}
	    		districtData.put(state, districtList);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				stmt.close();
				rs.close();
				rs1.close();
				con.close();
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		return districtData;
	}
	
}
