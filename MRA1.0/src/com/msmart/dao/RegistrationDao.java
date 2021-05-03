package com.msmart.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import com.msmart.dbconnection.DBConnection;

import net.sf.json.JSONObject;

public class RegistrationDao {

	public HashMap<String,String> agentRegistration(String distributorId,String firstName, String lastName, String dateOfBirth,
			String gender, String companyType, String companyName,String emailId, String mobileNo, String addressLine1,
			String addressLine2, String country, String state, String district,String cityCode, String pincode, String panNo, String password,String userId, String referral_code)
	{
		HashMap<String,String> map=new HashMap<String,String>();
		Connection con=null;     
		try
		{
			con=DBConnection.getConnection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Agent_Registration_App(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,distributorId);
			cstmt.setString(2,firstName);
			cstmt.setString(3,lastName);
			cstmt.setString(4,dateOfBirth);
			cstmt.setString(5,gender);
			cstmt.setString(6,companyType);
			cstmt.setString(7,companyName);
			cstmt.setString(8,emailId);
			cstmt.setString(9,mobileNo);
			cstmt.setString(10,addressLine1);
			cstmt.setString(11,addressLine2);
			cstmt.setString(12,country);
			cstmt.setString(13,state);
			cstmt.setString(14,district);
			cstmt.setString(15,cityCode);
			cstmt.setString(16,pincode);
			cstmt.setString(17,panNo);
			cstmt.setString(18,password);
			cstmt.setString(19,userId);
			cstmt.setString(20, referral_code);
			ResultSet rs=cstmt.executeQuery();

			while(rs.next())
			{
				if(rs.getString(1).equalsIgnoreCase("proceed")){
					map.put("clientFlag",rs.getString(2));
					map.put("completeAgentId",rs.getString(3));
					map.put("distributorEmailId",rs.getString(4));
					map.put("MPIN",rs.getString(5));
					map.put("distributorID",rs.getString(6));

				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in registerAgent");
			System.out.println(e.getMessage());
			return null;
		}
		finally
		{	
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in registerAgent");
				System.out.println(e.getMessage());
			}
		}
		return map;
	}
	public HashMap<String,String> getMobileappsdetails(String agentId,String passsword){
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		HashMap<String,String>mapdataInfo =new HashMap<String,String>();
		String mobileNo="";
		int countrecord=0;
		String selectdetailsquery="";
		Connection con=null; 
		try { 
			con=DBConnection.getConnection();
			String selectquery="select mobile_no from agent_details where agent_id=?";
			pstmt=con.prepareStatement(selectquery);
			pstmt.setString(1, agentId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				mobileNo=rs.getString("mobile_no");
			}
			//System.out.println("agent mobile number is "+mobileNo);
			String countresultQuery="select count(agent_id) as countnumber from agent_details where mobile_no=?";

			pstmt.close();
			rs.close();

			pstmt=con.prepareStatement(countresultQuery);
			pstmt.setString(1, mobileNo);
			rs=pstmt.executeQuery();
			while(rs.next()){
				countrecord=rs.getInt(1);
			}
			//System.out.println("agent mobile number countrecord is "+countrecord);
			if(countrecord==1){
				if(mobileNo.length()<=9 || Integer.parseInt(mobileNo.substring(0,2))<=69){
					mapdataInfo.put("Status","invalidnumber");
					mapdataInfo.put("mobilenumber",mobileNo);
				}
				else {
					String updateQuery="update login_details set mob_pass=? where user_id=?";
					//System.out.println(updateQuery);
					pstmt.close();
					pstmt=con.prepareStatement(updateQuery);
					pstmt.setString(1, passsword);
					pstmt.setString(2, agentId);
					pstmt.execute();
					selectdetailsquery="select a.email_id,b.mob_pass from agent_details a,login_details b where a.agent_id=b.user_id and a.agent_id=?"; 
					//System.out.println(selectdetailsquery);
					pstmt.close();
					rs.close();
					pstmt=con.prepareStatement(selectdetailsquery);
					pstmt.setString(1, agentId);
					rs=pstmt.executeQuery();
					while(rs.next()){
						mapdataInfo.put("Status","validinfo");
						mapdataInfo.put("mobilenumber",mobileNo);
						mapdataInfo.put("emailId",rs.getString(1));
						mapdataInfo.put("mobpassword",rs.getString(2));
					}
				}
			}
			else {
				mapdataInfo.put("Status","duplicatenumber");
				mapdataInfo.put("mobilenumber",mobileNo);
			}
		}
		catch(Exception ex){
			System.out.println("Exception in MobileappRequestDao (getMobileappsdetails) ");
			ex.printStackTrace();
			mapdataInfo.put("Status","invalidnumber");
			mapdataInfo.put("mobilenumber",mobileNo);
		}
		finally {
			try
			{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in MobileappRequestDao (getMobileappsdetails) while closing connection in getMobileappsdetails");
				e.printStackTrace();
			}
		}
		return mapdataInfo;
	}
	public  String  getPassword() {
		String str="";

		try
		{
			int n = 6;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			str = String.valueOf(randomNum);

		}
		catch(Exception e)
		{
			System.out.println("Exception in MobileappRequestDao (getPassword) ");
			e.printStackTrace();
		} 
		return str;
	}
	public HashMap<String, String> distributorRegistration(String mdId,String firstName, String lastName, String dateOfBirth,
			String gender, String companyType, String companyName,String panNo, String addressLine1, String addressLine2,String state,
			String district, String country, String cityCode,String pincode, String emailId, String mobileNo, String password,String userId) 
	{
		HashMap<String, String> map=new HashMap<String, String>();
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Distributor_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,mdId);
			cstmt.setString(2,firstName);
			cstmt.setString(3,lastName);
			cstmt.setString(4,dateOfBirth);
			cstmt.setString(5,gender);
			cstmt.setString(6,companyType);
			cstmt.setString(7,companyName);
			cstmt.setString(8,panNo);
			cstmt.setString(9,addressLine1);
			cstmt.setString(10,addressLine2);
			cstmt.setString(11,state);
			cstmt.setString(12,district);
			cstmt.setString(13,country);
			cstmt.setString(14,cityCode);
			cstmt.setString(15,pincode);
			cstmt.setString(16,emailId);
			cstmt.setString(17,mobileNo);
			cstmt.setString(18,password);
			cstmt.setString(19,userId);
			ResultSet rs=cstmt.executeQuery();

			while(rs.next())
			{
				if(rs.getString(1).equalsIgnoreCase("proceed")){
					map.put("completeDistributorId",rs.getString(2));
					map.put("distributorId",rs.getString(3));
					map.put("mdEmailId",rs.getString(4));
					map.put("mdID", rs.getString(5));
				}else{
					return null;
				}
			}	
		}
		catch(Exception e)
		{
			System.out.println("Exception in registerDistributor");
			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in registerDistributor");
				System.out.println(e.getMessage());
			}
		}
		return map;
	}

	public HashMap<String, String> MDSRegistration(String portalId,String firstName, String lastName, String dateOfBirth,String gender,
			String companyType, String companyName,String emailId, String mobileNo, String addressLine1,String addressLine2, String state,
			String district, String country,String cityCode, String pincode, String panNo, String password, String userId, String mdPortalID) 
	{

		HashMap<String,String> map=new HashMap<String,String>();
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			CallableStatement cstmt =null;

			cstmt=con.prepareCall("{call Master_Distributor_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,portalId);
			cstmt.setString(2,firstName);
			cstmt.setString(3,lastName);
			cstmt.setString(4,dateOfBirth);
			cstmt.setString(5,gender);
			cstmt.setString(6,companyType);
			cstmt.setString(7,companyName);
			cstmt.setString(8,emailId);
			cstmt.setString(9,mobileNo);
			cstmt.setString(10,addressLine1);
			cstmt.setString(11,addressLine2);
			cstmt.setString(12,state);
			cstmt.setString(13,district);
			cstmt.setString(14,country);
			cstmt.setString(15,cityCode);
			cstmt.setString(16,pincode);
			cstmt.setString(17,panNo);
			cstmt.setString(18,password);
			cstmt.setString(19, userId);
			cstmt.setString(20, mdPortalID);
			ResultSet rs=cstmt.executeQuery();

			while(rs.next())
			{
				map.put("complteMDId",rs.getString(1));
				map.put("mdId",rs.getString(2));
			}
		}


		catch(Exception e)
		{
			System.out.println("Exception in MdsRegistrationDao,registerMds");
			System.out.println(e.getMessage());
			return null;
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in MdsRegistrationDao,registerMds");
				System.out.println(e.getMessage());
			}
		}
		return map;
	}

	public HashMap<String, String> APIRegistration(String firstName, String lastName,String dob, String agencyName, String panNo, String officeAddress,
			String officeState, String officeDistrict, String officeCountry,String officePincode, String officeEmailId, String pass,
			String officeMobileNo, String headerUserName) 
	{
		HashMap<String, String> hm=new HashMap<String, String>();
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			CallableStatement cstmt =null;
			//			System.out.println("we are going to call egen registrtion procedure");
			cstmt=con.prepareCall("{call Egen_Registration(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,firstName);
			cstmt.setString(2,lastName);
			cstmt.setString(3,dob);
			cstmt.setString(4,agencyName);
			cstmt.setString(5,panNo);
			cstmt.setString(6,officeAddress);
			cstmt.setString(7,officeState);
			cstmt.setString(8,officeDistrict);
			cstmt.setString(9,officeCountry);
			cstmt.setString(10,officePincode);
			cstmt.setString(11,officeEmailId);
			cstmt.setString(12,pass);
			cstmt.setString(13,officeMobileNo);
			cstmt.setString(14,headerUserName);
			ResultSet rs=cstmt.executeQuery();
			System.out.println("we are here");
			while(rs.next())
			{
				hm.put("status",rs.getString(1));
				hm.put("corpAgentId",rs.getString(2));
				hm.put("headerUserName",rs.getString(3));
				hm.put("headerPassword",rs.getString(4));
				hm.put("loginId",rs.getString(5));
				hm.put("subAgentId",rs.getString(6));
				hm.put("subAgentPassword",rs.getString(7));
				hm.put("subAgentMobileNo",rs.getString(8));
			}
			//System.out.println("hm in dao class>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+hm);
		}
		catch(Exception e)
		{
			System.out.println("Exception in registerAPIClient");
			System.out.println(e.getMessage());
			return null;
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(Exception e){
				System.out.println("Exception in registerAPIClient");
				System.out.println(e.getMessage());
			}
		}
		return hm;
	}

	public String generatePassword() {
		String password=RandomStringUtils.randomAlphanumeric(10);
		return password;
	}

	public String checkEmail(String emailId){  		 
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		int count=0;
		String status="valid"; 		 
		try{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select count(agent_id) from agent_details where email_id='"+emailId+"'"; 		
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="invalid";
			} 			
			System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(Exception e)
		{  			
			status="invalid";
			e.printStackTrace();
		}  		
		finally
		{
			try
			{
				con.close();  				
			}
			catch(Exception e)
			{
				System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			}  			
		}
		return status; 	 
	}
	public String checkMob(String mob){  		 
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		int count=0;
		String status="valid"; 		 
		try{
			con=DBConnection.getConnection();
			stmt=con.createStatement();  			 
			String sql="select count(agent_id) from agent_details where mobile_no='"+mob+"'"; 	
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);
			}
			if(count>0){
				status="invalid";
			}

			System.out.println("status is is>>>>>>>>>>>>>"+status);
		}
		catch(Exception e)
		{  			 
			status="invalid";	
			e.printStackTrace();
		}  		 
		finally
		{
			try
			{
				con.close();  				
			}
			catch(Exception e)
			{
				System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			}  			
		}
		return status; 	 
	}
	public String getUpdateprofile(String agentID, String firstname, String lastname,
			String gender, String firmname, String email, String mobileno, String address,
			String country, String state, String ditrict, String city, String pincode,
			String pannumber,String DateOfBirth) {
		// TODO Auto-generated method stub


		PreparedStatement pstmt=null;
		Connection con=null; 

		String updateQuery="update agent_details set Agent_first_name=?,Agent_last_name=?,address1=?,pin_code=?,state=?,district=?,city=?,Gender=?,country=?,Pan_no=?,agency_name=?,DateOfBirth=? where agent_id=? and mobile_no=?";
		//System.out.println(updateQuery);
		try {
			con=DBConnection.getConnection();
			pstmt=con.prepareStatement(updateQuery);
			pstmt.setString(1, firstname);
			pstmt.setString(2, lastname);
			pstmt.setString(3, address);
			pstmt.setString(4, pincode);
			pstmt.setString(5, state);
			pstmt.setString(6, ditrict);
			pstmt.setString(7, city);
			pstmt.setString(8, gender);
			pstmt.setString(9, country);
			pstmt.setString(10, pannumber);
			pstmt.setString(11, firmname);
			pstmt.setString(12, DateOfBirth);
			pstmt.setString(13, agentID);
			pstmt.setString(14, mobileno);



			int ustatus=pstmt.executeUpdate();
			if(ustatus==1)
			{
				return "Y";
			}
			else{
				return "N";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		{
			try
			{
				con.close();  				
			}
			catch(Exception e)
			{
				System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			}  			
		}
		return "N";
	}
	public HashMap<String, String> getprofile(String agentID) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		HashMap<String,String>mapdataInfo =null;
		String selectdetailsquery="";
		Connection con=null; 
		try{

			con=DBConnection.getConnection();
			selectdetailsquery="select a.Agent_first_name,a.Agent_last_name,a.address1,a.pin_code,a.state,a.district,a.city,a.Gender,a.email_id,a.mobile_no,a.country,a.Pan_no,a.DateOfBirth,a.kyc_status,agency_name,eko_aeps_user_code from agent_details a where a.agent_id=?"; 
			//System.out.println(selectdetailsquery);

			pstmt=con.prepareStatement(selectdetailsquery);
			pstmt.setString(1, agentID);
			rs=pstmt.executeQuery();
			while(rs.next()){
				mapdataInfo =new HashMap<String,String>();
				mapdataInfo.put("firstname",rs.getString(1));
				mapdataInfo.put("lastname",rs.getString(2));
				mapdataInfo.put("address",rs.getString(3));
				mapdataInfo.put("pincode",rs.getString(4));
				mapdataInfo.put("state",rs.getString(5));
				mapdataInfo.put("district",rs.getString(6));
				mapdataInfo.put("city",rs.getString(7));
				mapdataInfo.put("gender",rs.getString(8));
				mapdataInfo.put("emailID",rs.getString(9));
				mapdataInfo.put("mobile",rs.getString(10));
				mapdataInfo.put("country",rs.getString(11));
				mapdataInfo.put("pannumber",rs.getString(12));
				mapdataInfo.put("adharnumber","");
				mapdataInfo.put("DateOfBirth",rs.getString(13));
				mapdataInfo.put("kycstatus",rs.getString(14));
				mapdataInfo.put("firmname",rs.getString(15));
				mapdataInfo.put("user_code",rs.getString(16));

				return mapdataInfo;


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		{
			try
			{
				con.close();  				
			}
			catch(Exception e)
			{
				System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			}  			
		}
		return mapdataInfo;
	}
	public String getRequestKyc(String agentID, String adharcard, String adharHoldername) {
		PreparedStatement pstmt=null;
		Connection con=null; 
		try {
			String updateQuery="insert into kyc_details(agent_id,Adhar_no,Adharcard_holder,date_of_kyc,kyc_status)values(?,?,?,GETDATE(),?)";


			con=DBConnection.getConnection();

			pstmt=con.prepareStatement(updateQuery);
			pstmt.setString(1, agentID);
			pstmt.setString(2, adharcard);
			pstmt.setString(3, adharHoldername);
			pstmt.setString(4, "Pending");


			int ustatus=pstmt.executeUpdate();
			if(ustatus==1)
			{
				return "Y";
			}
			else{
				return "N";
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try
			{
				con.close();  				
			}
			catch(Exception e)
			{
				System.out.println("Exception in checkUserName while closing connection========"+e.toString());
			}  			
		}
		return "N";
	}
	public JSONObject getKycDetails(String agentID) {


		Connection con=null; 
		JSONObject jsonObject=null;
		Statement stmt=null;
		try {

			con=DBConnection.getConnection();
			stmt=con.createStatement();  			 
			String sql="select kd.Adhar_no,kd.Adharcard_holder,CASE ad.kyc_status when 1 then 'Success' else 'pending' end from kyc_details (nolock) kd join agent_details ad on kd.agent_id=ad.agent_id and ad.agent_id="+Long.parseLong(agentID);; 	
			ResultSet	rs=stmt.executeQuery(sql);
			while (rs.next()) {
				jsonObject=new JSONObject();
				jsonObject.put("adharcardNo", rs.getString(1));
				jsonObject.put("adharHoldername", rs.getString(2));
				jsonObject.put("kycRequeststatus", rs.getString(3));


				return jsonObject;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return jsonObject;
	}
}

