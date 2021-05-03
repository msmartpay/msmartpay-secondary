package com.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.hibernate.Session;
import com.common.HibernateSession;
public class StateDistrictDetailsDao {



	@SuppressWarnings("unchecked")
	public  HashMap <String,ArrayList<String>> getStateDistrict() {

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
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			stmt=con.createStatement();
			stmt1=con.createStatement();
			String selectstate="select distinct(state) from Tep_State_District_Details order by state";
			rs=stmt.executeQuery(selectstate);

			while(rs.next())
			{

				state=rs.getString(1);
				//	System.out.println(state);
				districtList=new ArrayList();
				rs1=stmt1.executeQuery("select District from Tep_State_District_Details where state='"+state+"'order by District ");
				while(rs1.next()){
					// System.out.println(rs1.getString(1));
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
				if(con!=null)
					con.close();
				if(session!=null)
				{
					session.close();

				}

			}
			catch(Exception e)
			{
				System.out.println("Exception In getStateDistrict for closing connction"+e.toString());
			}

		}
		return districtData;

	}


}
