package com.distributorregistration;
import com.db.*;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpSession;




public class DistributorRegistrationDao {


	public static String chkPanNo(String panNo,String mdId)
	{
		String resultresponse=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();

			stmt = con.createStatement();
			rs=stmt.executeQuery("select PAN_TIN_No from distributor_details where PAN_TIN_No='"+panNo+"' and md_id="+mdId+"");
			if(rs.next())
				resultresponse="Pan Card No Already Exists";
			else
				resultresponse="Valid";
		}catch(Exception E)
		{
			System.out.println("Exception in chkPanNo======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in chkPanNo while closing connection"+e.toString());
			}

		}


		return resultresponse;
	}


	public static String chkEmail(String email)
	{
		String resultresponse=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			rs=stmt.executeQuery("select email_id from distributor_details where email_id='"+email+"'");
			if(rs.next())
				resultresponse="Email ID is Duplicate.";
			else
				resultresponse="Valid";
		}catch(Exception E)
		{
			System.out.println("Exception in chkEmail======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in chkEmail while closing connection"+e.toString());
			}

		}


		return resultresponse;
	}

	public static String getRandomString(int length) {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}




	/*public static HashMap getMasterDistributorServiceDetails(String mdId){
    Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	HashMap information=new HashMap();

	try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String selectQuery="select md_id,air,rail,re_mobile,re_dth,bl_mobile,bl_lic,bk_bus,bk_hotel,bk_tour,reqest_courier,request_pancard,request_share,e_com_product,e_com_service,e_com_leadbased,ps_edu_e_point,ps_citizen_e_point,ps_profit_bazar,live_recharge,AirtelMobOffline,AirtelDthOffline from md_service_details where md_id="+mdId+""; 
			rs=stmt.executeQuery(selectQuery);
			while(rs.next()){
             information.put("air",rs.getString("air")); 
		     information.put("rail",rs.getString("rail")); 
			 information.put("re_mobile",rs.getString("re_mobile")); 
			 information.put("re_dth",rs.getString("re_dth")); 
			 information.put("bl_mobile",rs.getString("bl_mobile")); 
			 information.put("bl_lic",rs.getString("bl_lic")); 
			 information.put("bk_bus",rs.getString("bk_bus")); 
			 information.put("bk_hotel",rs.getString("bk_hotel")); 
			 information.put("bk_tour",rs.getString("bk_tour")); 
			 information.put("reqest_courier",rs.getString("reqest_courier")); 
			 information.put("request_pancard",rs.getString("request_pancard")); 
			 information.put("request_share",rs.getString("request_share")); 
			 information.put("e_com_product",rs.getString("e_com_product")); 
			 information.put("e_com_service",rs.getString("e_com_service"));
			 information.put("e_com_leadbased",rs.getString("e_com_leadbased")); 
			 information.put("ps_edu_e_point",rs.getString("ps_edu_e_point")); 
			 information.put("ps_citizen_e_point",rs.getString("ps_citizen_e_point")); 
			 information.put("ps_profit_bazar",rs.getString("ps_profit_bazar"));
			 information.put("live_recharge",rs.getString("live_recharge"));
			 information.put("AirtelMobOffline",rs.getString("AirtelMobOffline"));
			 information.put("AirtelDthOffline",rs.getString("AirtelDthOffline"));

            }
			}catch(Exception E)
		{
			System.out.println("Exception in getMasterDistributorServiceDetails======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in chkEmail while closing connection"+e.toString());
			}

		}


		return information;

}

	 */
	public static String checkMobile(String mobNum)
	{
		String resultresponse=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			rs=stmt.executeQuery("select mobile_no from distributor_details where mobile_no='"+mobNum+"'");
			if(rs.next())
				resultresponse="Mobile Number Already Exists";
			else
				resultresponse="Valid";
		}catch(Exception E)
		{
			System.out.println("Exception in chkEmail======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in chkEmail while closing connection"+e.toString());
			}

		}


		return resultresponse;
	}
	public static String checkEmail(String email)
	{
		String resultresponse=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			rs=stmt.executeQuery("select email_id from distributor_details where email_id='"+email+"'");

			if(rs.next())
				resultresponse="Email Already Exists";
			else
				resultresponse="Valid";
		}catch(Exception E)
		{
			System.out.println("Exception in chkEmail======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in chkEmail while closing connection"+e.toString());
			}

		}


		return resultresponse;
	}
	/*
   public static String getCity(String cityCode)
	{
		String cityName="";
		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="select city_name from city_details where CITY_CODE="+cityCode+"";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()){
			cityName=rs.getString(1);	

			}
			else{
				cityName="notfound";
                 }

		}catch(Exception E)
		{
			cityName="notfound";
			System.out.println("Exception in getCity======"+E.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in getCity while closing connection"+e.toString());
			}

		}


		return cityName;
	}

	 */




	public static  HashMap getAgentId(Connection con,String city,String state) {
		Statement stmt=null;
		ResultSet rs=null;
		Statement stmt1=null;
		ResultSet rs1=null;
		int id=0;
		String stcode="";
		HashMap IdDetails=new HashMap();

		try
		{ 
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery("select max(agent_id) as aa from agent_details");

			if(rs.next()){
				String ID=rs.getString("aa");
				if(ID!=null)
					id=Integer.parseInt(ID);
				id=id+1;

			}


			System.out.println("Hello Id="+id);

			IdDetails.put("id",id);
			stmt=con.createStatement();
			String sqlUpdate="select code, state_name, capital from agent_state_initial where state_name='"+state+"'";
			System.out.println(">>>>>"+sqlUpdate);
			rs=stmt.executeQuery(sqlUpdate);
			if(rs.next())
			{
				stcode=rs.getString("code").trim();


				IdDetails.put("initial","AG"+stcode+city);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in DistributorRegistrationDao, method getAgentId while opening connection "+e.toString());
		}
		finally
		{
			try
			{
				if(rs !=null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con!= null)
					con.close();
			}
			catch ( Exception e)
			{
				System.out.println("Exception method getAgentId while closing connection "+e.toString());
			}
		}

		return IdDetails;
	}
	public HashMap getDistributorId(Connection con,String city,String state) {
		return new HashMap();
	}

	public static HashMap saveDistributorDetails(Connection con,String firstname,String lastname,String DOB,String gender,String firmtype,
			String firmname,String Address1,String Address2,String officeCountry,String state,String District,String city,String officePinCode,
			String mobile,String PancardNo,String emailId,String idProofFileName,String addressProofFileName,String businessProofFileName,
			String idExtension,String addExtension,String businessExtension,String mdId,String password) {   

		System.out.println("---"+mdId+"--"+firstname+"---"+lastname+"---"+DOB+"-----"+gender+"------"+firmtype+"---"+firmname+"---"+PancardNo+"---"+Address1+"----"+Address2+"----"+state
				+"---"+District+"---"+officeCountry+"---"+city+"---"+officePinCode+"---"+emailId+"---"+mobile+"---"+password+"---"+mdId);

		HashMap<String, String> map=new HashMap<String, String>();  
		CallableStatement cstmt=null;
		ResultSet rs=null;
		Statement stmt=null;           
		// int md_id=Integer.parseInt(mdId);
		int pincode=Integer.parseInt(officePinCode);
		//   System.out.println("mdId is<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>"+md_id);
		System.out.println("pincode is<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>"+pincode);
		try
		{
			con=DBConnection.getConnection();
			//System.out.println("Distributor_Registration");
			cstmt=con.prepareCall("{call Distributor_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			/* cstmt.setString(1,firstname);
	          cstmt.setString(2,lastname);
	          cstmt.setString(3,DOB);
	          cstmt.setString(4,gender);
			  cstmt.setString(5,firmtype);
			  cstmt.setString(6,firmname);
	          cstmt.setString(7,Address1);
			  cstmt.setString(8,Address2);
			  cstmt.setString(9,officeCountry);
	          cstmt.setString(10,state);
	          cstmt.setString(11,District);
	          cstmt.setString(12,city);
	          cstmt.setInt(13,pincode);
	          cstmt.setString(14,mobile);	          			
	          cstmt.setString(15,PancardNo);
	          cstmt.setString(16,emailId);	 
	          cstmt.setString(17,idProofFileName);			 
	          cstmt.setString(18,addressProofFileName);	         
	          cstmt.setString(19,businessProofFileName);
			  cstmt.setString(20,idExtension);
			  cstmt.setString(21,addExtension);
			  cstmt.setString(22,businessExtension);
	          cstmt.setInt(23,md_id);
	          cstmt.setString(24,password);*/	

			cstmt.setString(1,mdId);
			cstmt.setString(2,firstname);
			cstmt.setString(3,lastname);
			cstmt.setString(4,DOB);
			cstmt.setString(5,gender);
			cstmt.setString(6,firmtype);
			cstmt.setString(7,firmname);
			cstmt.setString(8,PancardNo);			  
			cstmt.setString(9,Address1);
			cstmt.setString(10,Address2);			  
			cstmt.setString(11,state);
			cstmt.setString(12,District);
			cstmt.setString(13,officeCountry);
			cstmt.setString(14,city);
			cstmt.setInt(15,pincode);			  
			cstmt.setString(16,emailId);	 
			cstmt.setString(17,mobile);			  
			cstmt.setString(18,password);	        
			cstmt.setString(19,mdId);

			//    cstmt.registerOutParameter(25, java.sql.Types.VARCHAR);

			rs=cstmt.executeQuery();

			while(rs.next()){

				String status = rs.getString(1);

				if(status.equalsIgnoreCase("proceed"))
				{
					// map.put("name",rs.getString(1));
					map.put("complteDistId",rs.getString("DSID"));
					map.put("distId",rs.getString("distributor_id"));
					map.put("mdEmailid",rs.getString("email_id"));

					//	  map.put("mdEmailid",rs.getString(5));
					/*	  map.put("companyName",rs.getString(6));
						  map.put("state",rs.getString(7));
						  map.put("city",rs.getString(8));
					      map.put("dob",rs.getString(9));
						  map.put("address1",rs.getString(10));
						  map.put("address2",rs.getString(11));
						  map.put("mobileNo",rs.getString(12));
						  map.put("userName",rs.getString(13));
						  map.put("password",rs.getString(14));
						  map.put("pincode",rs.getString(15));*/

					//  map.put("status",cstmt.getString(25));
				}
				else
					if(status.equalsIgnoreCase("limit_exceeded"))
					{
						map.put("status", rs.getString(1));
					}
			}




		}
		catch(Exception e){

			System.out.println("exception in saveDistributorDetails============================"+e.toString());

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
			}
			catch(Exception e)
			{

				System.out.println("Exception in saveDistributorDetails while closing connection"+e.toString());

			}

		}



		return map;

	}


}
