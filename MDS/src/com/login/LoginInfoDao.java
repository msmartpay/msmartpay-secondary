package com.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.db.DBConnection;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

import com.mobilesms.SmsToMobileDao;

public class LoginInfoDao {

	public static String updateLoginStatus2(Connection con,String vrifyCode,String mdId,HttpSession session) {

		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String status="";
		int countNo=0;		
		try
		{
			System.out.println("================updateLoginStatus2============");
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String updateQuery="select count(*) as countno from md_login_details where  Mob_Verified_code='"+vrifyCode+"' and user_id="+mdId+"";
			System.out.println("varified query is "+updateQuery);
			rs=stmt.executeQuery(updateQuery);
			while (rs.next())
			{
				countNo=rs.getInt(1);
				System.out.println("it is the counter part..... it will have value of one column");			 
			}
			if(countNo>=1) 
			{
				String updateQuery2="update md_login_details set Mob_Verified='Y' where user_id="+mdId+"";
				System.out.println("this will check the database n then retrun to the home page");
				stmt.executeUpdate(updateQuery2);
				status="Verified";
			}
			else{
				status="notVerified";				
			}
			String selectQuery="select md_login_url,md_page_title,md_footer_copyright,MD_header_home_image from white_label_details where client_id=(select client_id from md_details where md_id="+mdId+")";

			rs1=stmt.executeQuery(selectQuery);
			while (rs1.next())
			{		
				session.setAttribute("md_login_url",rs1.getString(1));
				session.setAttribute("md_page_title",rs1.getString(2));
				session.setAttribute("md_footer_copyright",rs1.getString(3));
				session.setAttribute("md_header_home_image",rs1.getString(4));		 
			}			
		}catch(Exception e)
		{
			System.out.println("Exception in updateLoginStatus"+e.toString());
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
				System.out.println("Exception in updateLoginStatus while closing connection "+e.toString());
			}			
		}
		return status;		
	}
	public static String getMdsMobileNo(String mdId){	
		String mobileNo=null;
		Statement stmt=null;
		ResultSet rs=null;		
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();			
			stmt = con.createStatement();
			rs=stmt.executeQuery("select Authorized_Mobile_No from md_details where md_id="+mdId+"");
			if(rs.next()){
				mobileNo=rs.getString(1);			
			}				
		}catch(Exception E)
		{
			System.out.println("Exception in getMdsMobileNo======"+E.toString());
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
				System.out.println("Exception in getMdsMobileNo while closing connection"+e.toString());
			}			
		}		
		return mobileNo;
	}
	public static String generateVerifyCodeForMobileVerification(String mdId,String varificationcode,String MobileNo){

		Statement stmt=null;
		ResultSet rs=null;
		boolean id=false;
		Connection con=null;
		String resultresponse="";

		try
		{ 
			System.out.println("--------------------inside generateVerifyCodeForMobileVerification-----------------"); 
			con=DBConnection.getConnection();			
			stmt = con.createStatement();
			int flag=stmt.executeUpdate("update md_login_details set Mob_Verified_code='"+varificationcode+"' where user_id="+mdId+"");
			if(flag>0){
				String message="Dear Master Distributor, Please verify your Mobile Number "+MobileNo+" in order to access your services & profile update. Your Verification code is "+varificationcode+"";
				String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(MobileNo,message);
				String smsStatusAcl=(String)SmsToMobileDao.sendMobileSmsAcl(MobileNo,message);
				resultresponse="valid";
			}				
			else
				resultresponse="invalid";
		}
		catch(Exception E)
		{
			resultresponse="invalid";
			System.out.println("Exception in generateVerifyCodeForMobileVerification======"+E.toString());
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
				System.out.println("Exception in generateVerifyCodeForMobileVerification while closing connection"+e.toString());
			}			
		}		
		return resultresponse;
	}
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
			rs=stmt.executeQuery("select PAN_TIN_No from md_details where PAN_TIN_No='"+panNo+"' and md_id !="+mdId+"");
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

	/*public static String getCity(String cityCode)
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
	} */


	public static String updateMdsProfileDetails(Connection con,String mdId, String mdName,String dob,String gender,String maritalStatus,String fatherhusbandname,String motherName,String occupation,String companyType,String designation,String officeAddress1,String officeAddress2,String officeState,String officeDistrict, String officeCity, String officeCountry, String officePincode,String officelandmark,String officePhone,String officeWebsite,String panNo,String resAddress1,String resAddress2,String resCountry,String resState,String resDistrict,String resCity,String resPincode,String reslandmark,String resMobileNo,String resAlternateNo ,String resPhoneNo,String corraddval,String companyName)
	{
		Statement stmt=null;
		int flag=0;
		String result="";
		String updateaddress="";
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="UPDATE MD_Details SET MD_name='"+mdName+"',Company_name ='"+companyName+"',Address1 ='"+officeAddress1+"',Address2= '"+officeAddress2+"',Landmark='"+officelandmark+"',Pin_code ='"+officePincode+"',office_district = '"+officeDistrict+"',City='"+officeCity+"',State='"+officeState+"',Country='"+officeCountry+"',Office_phone='"+officePhone+"',Date_of_Birth='"+dob+"', Gender = '"+gender+"',Marital_Status = '"+maritalStatus+"',Occupation ='"+occupation+"', Company_Type ='"+companyType+"' , designation ='"+designation+"', PAN_TIN_No ='"+panNo+"', Website ='"+officeWebsite+"', Res_Address1 = '"+resAddress1+"', Res_Address2 ='"+resAddress2+"', Res_Country ='"+resCountry+"', Res_State ='"+resState+"', Res_District ='"+resDistrict+"', Res_City ='"+resCity+"', Res_Pincode = '"+resPincode+"', Res_Landmark = '"+reslandmark+"', Res_Mobile_No ='"+resMobileNo+"', Res_Alternate_Mobile_No ='"+resAlternateNo+"', Res_Phone_No ='"+resPhoneNo+"', Father_Husband_Name ='"+fatherhusbandname+"', Mother_Maiden_Name ='"+motherName+"', Correspondence_Address_Value = '"+corraddval+"' where md_id='"+mdId+"'";
			String updateQuery2="update  MD_login_details set Profile_Status='Y' where user_id='"+mdId+"'";

			if(corraddval.equalsIgnoreCase("OA")){
				updateaddress="update mds_Correspondence_Address set Corres_Address1='"+officeAddress1+"',Coress_Address2='"+officeAddress2+"',Coress_Country='"+officeCountry+"',Coress_Satate='"+officeState+"',Coress_District='"+officeDistrict+"',Coress_City='"+officeCity+"',Coress_Pincode='"+officePincode+"',Coress_Landmark='"+officelandmark+"',Coress_Phone='"+officePhone+"' where md_id='"+mdId+"'";
			}
			else     
			{
				updateaddress="update mds_Correspondence_Address set Corres_Address1='"+resAddress1+"',Coress_Address2='"+resAddress2+"',Coress_Country='"+resCountry+"',Coress_Satate='"+resState+"',Coress_District='"+resDistrict+"',Coress_City='"+resCity+"',Coress_Pincode='"+resPincode+"',Coress_Landmark='"+reslandmark+"',Coress_Mobile='"+resMobileNo+"',Coress_AtlMobile='"+resAlternateNo+"',Coress_Phone='"+officePhone+"' where md_id='"+mdId+"' ";
			}    
			flag=stmt.executeUpdate(sql);
			stmt.executeUpdate(updateQuery2);
			stmt.executeUpdate(updateaddress);
			if(flag>0)
			{
				result="updated";
			}
			else
			{
				result="notupdated";
			}
		}catch(Exception E)
		{
			result="notupdated";			
			System.out.println("Hello Error in updateMdDetails="+E.toString());
		}
		finally
		{
			try
			{				
				if(stmt!=null)
					stmt.close();
				
				con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in updateMdDetails"+e.toString());
			}
		}
		return result;
	}

	public static HashMap getMdInfo(String mdId){
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		HashMap mapData =new HashMap();
		try
		{		
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String selectQuery="SELECT password,company_name FROM md_login_details,md_details where user_id="+mdId+" and user_id=md_id";
			System.out.println("select query is "+selectQuery);
			rs=stmt.executeQuery(selectQuery);			
			while(rs.next())
			{
				mapData.put("password",rs.getString(1));
				mapData.put("company_name",rs.getString(2));		
			}		
		}catch(Exception e)
		{
			System.out.println("Exception in getMdInfo in class LoginInfoDao"+e.toString());
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
				System.out.println("Exception in getMdInfo in class LoginInfoDao while closing connection"+e.toString());
			}			
		}
		return mapData;
	}
	public static HashMap getMdsProfile(String mdId){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		HashMap mapData =new HashMap();
		String officeCity="";
		String resCity="";
		try
		{			
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String selectQuery="SELECT  MD_name,Company_name,Address1,Address2,Landmark,Pin_code,City,office_district,State,Email_id,Office_phone,Mobile_no,Fax_no,Date_of_Birth,Gender,Marital_Status,Occupation,Company_Type,designation,PAN_TIN_No,Website,Res_Address1,Res_Address2,Res_State,Res_District,Res_City,Res_Pincode,Res_Landmark,Res_Mobile_No,Res_Alternate_Mobile_No,Res_Phone_No,Res_Fax_No,Authorized_Mobile_No,Father_Husband_Name,Mother_Maiden_Name,Correspondence_Address_Value FROM md_details where md_id="+mdId+"";

			rs=stmt.executeQuery(selectQuery);			
			while(rs.next())
			{
				mapData.put("mdName",rs.getString(1));
				mapData.put("companyName",rs.getString(2));
				mapData.put("officeAddress1",rs.getString(3));
				mapData.put("officeAddress2",rs.getString(4));
				mapData.put("landmark",rs.getString(5));
				mapData.put("officePinCode",rs.getString(6));
				officeCity=rs.getString(7);
				mapData.put("city",officeCity);
				mapData.put("officeDistrict",rs.getString(8));
				mapData.put("officeState",rs.getString(9));			
				mapData.put("emailId",rs.getString(10));
				String officePhone=rs.getString(11);
				//System.out.println("officePhone is>>>>>>>>>>>>>>>>>>>>>>"+officePhone);

				if(officePhone==null || officePhone.equalsIgnoreCase("-") || officePhone.equals(""))
				{
					officePhone="00000-00000000";
				}
				StringTokenizer restoken=new StringTokenizer(officePhone,"-");
				String officePhoneNo="";
				String officeSTDCode="";

				while(restoken.hasMoreTokens())
				{
					officeSTDCode=restoken.nextToken();
					officePhoneNo=restoken.nextToken();
				}
				//System.out.println("officeSTDCode is>>>>>>>>>>>>>>>>>>>>>>"+officeSTDCode);
				//System.out.println("officePhoneNo is>>>>>>>>>>>>>>>>>>>>>>"+officePhoneNo);
				mapData.put("officeSTDCode",officeSTDCode);
				mapData.put("officePhoneNo",officePhoneNo);			
				mapData.put("mobileNo",rs.getString(12));			
				String officeFax=rs.getString(13);
				//System.out.println("officeFax is>>>>>>>>>>>>>>>>>>>>>>"+officeFax);

				if(officeFax==null || officeFax.equalsIgnoreCase("-") || officeFax.equals(""))
				{
					officeFax="00000-00000000";
				}
				StringTokenizer restoken1=new StringTokenizer(officeFax,"-");
				String officeFaxNo="";
				String officeFaxCode="";
				while(restoken1.hasMoreTokens())
				{
					officeFaxCode=restoken1.nextToken();
					officeFaxNo=restoken1.nextToken();
				}
				//System.out.println("officeFaxCode is>>>>>>>>>>>>>>>>>>>>>>"+officeFaxCode);
				//System.out.println("officeFaxNo is>>>>>>>>>>>>>>>>>>>>>>"+officeFaxNo);
				mapData.put("officeFaxCode",officeFaxCode);
				mapData.put("officeFaxNo",officeFaxNo);			
				mapData.put("dateOfbirth",rs.getString(14));
				mapData.put("gender",rs.getString(15));
				mapData.put("maritalStatus",rs.getString(16));
				mapData.put("occupation",rs.getString(17));
				mapData.put("companyType",rs.getString(18));
				mapData.put("designation",rs.getString(19));
				mapData.put("panNo",rs.getString(20));
				mapData.put("website",rs.getString(21));
				mapData.put("residentialAddress1",rs.getString(22));
				mapData.put("residentialAddress2",rs.getString(23));			
				mapData.put("residentialState",rs.getString(24));
				mapData.put("residentialDistrict",rs.getString(25));
				resCity=rs.getString(26);
				mapData.put("residentialCity",resCity);
				mapData.put("residentialPinCode",rs.getString(27));
				mapData.put("residentialLandMark",rs.getString(28));
				mapData.put("residentialMobileNo",rs.getString(29));
				mapData.put("residentialAlternateNo",rs.getString(30));
				String resPhone=rs.getString(31);
				//System.out.println("resPhone is>>>>>>>>>>>>>>>>>>>>>>"+resPhone);

				if(resPhone==null || resPhone.equalsIgnoreCase("-") || resPhone.equals(""))
				{
					resPhone="00000-00000000";
				}
				StringTokenizer restoken2=new StringTokenizer(resPhone,"-");
				String resPhoneNo="";
				String resSTDCode="";
				while(restoken2.hasMoreTokens())
				{
					resSTDCode=restoken2.nextToken();
					resPhoneNo=restoken2.nextToken();
				}
				//System.out.println("resSTDCode is>>>>>>>>>>>>>>>>>>>>>>"+resSTDCode);
				//System.out.println("resPhoneNo is>>>>>>>>>>>>>>>>>>>>>>"+resPhoneNo);
				mapData.put("resSTDCode",resSTDCode);
				mapData.put("resPhoneNo",resPhoneNo);
				String resFax=rs.getString(32);
				//System.out.println("resFax is>>>>>>>>>>>>>>>>>>>>>>"+resFax);

				if(resFax==null || resFax.equalsIgnoreCase("-") || resFax.equals(""))
				{
					resFax="00000-00000000";
				}
				StringTokenizer restoken3=new StringTokenizer(resFax,"-");
				String resFaxNo="";
				String resFaxCode="";
				while(restoken3.hasMoreTokens())
				{
					resFaxCode=restoken3.nextToken();
					resFaxNo=restoken3.nextToken();
				}
				// System.out.println("resFaxCode is>>>>>>>>>>>>>>>>>>>>>>"+resFaxCode);
				//System.out.println("resFaxNo is>>>>>>>>>>>>>>>>>>>>>>"+resFaxNo);
				mapData.put("resFaxCode",resFaxCode);
				mapData.put("resFaxNo",resFaxNo);			
				mapData.put("authorizedMobileNo",rs.getString(33));		
				mapData.put("fathersName",rs.getString(34));
				mapData.put("mothersName",rs.getString(35));		
				mapData.put("correspondenceAddValue",rs.getString(36));			
			}					
			selectQuery="select city_code from city_details where city_name='"+officeCity+"'";
			System.out.println(selectQuery);
			rs1=stmt.executeQuery(selectQuery);
			while(rs1.next()){
				mapData.put("officeCityCode",rs1.getString(1));			
			}
			selectQuery="select city_code from city_details where city_name='"+resCity+"'";
			System.out.println(selectQuery);
			rs2=stmt.executeQuery(selectQuery);
			while(rs2.next()){
				mapData.put("resCityCode",rs2.getString(1));			
			}
		}catch(Exception e)
		{
			System.out.println("Exception in chekLoginInfo in class LoginInfoDao"+e.toString());
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(rs1!=null)
					rs1.close();
				if(rs2!=null)
					rs2.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();			
			}
			catch(Exception e)
			{
				System.out.println("Exception in chekLoginInfo in class LoginInfoDao while closing connection"+e.toString());
			}			
		}
		return mapData;
	}

	@SuppressWarnings("unchecked")
	public static HashMap chekLoginInfo(Connection con, String mdId) {		
		Statement stmt=null;
		ResultSet rs=null;
		HashMap mapData =new HashMap();
		try
		{		
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String selectQuery="SELECT password,old_Pass,Verified,Mob_Verified,Profile_Status FROM md_login_details where user_id="+mdId+"";
			System.out.println("select query is........ "+selectQuery);
			rs=stmt.executeQuery(selectQuery);			
			while(rs.next())
			{
				mapData.put("password",rs.getString(1));
				mapData.put("old_Pass",rs.getString(2));
				mapData.put("EmailVerified",rs.getString(3));
				mapData.put("MobVerified",rs.getString(4));
				mapData.put("Profile_Status",rs.getString(5));
				System.out.println("......we r inside LoginInfoDao CLASS and inside the checkloginINFO method...asdfghjkgfdsdfghjk...");
			}			
		}catch(Exception e)
		{
			System.out.println("Exception in chekLoginInfo in class LoginInfoDao"+e.toString());
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
				System.out.println("Exception in chekLoginInfo in class LoginInfoDao while closing connection"+e.toString());
			}		
		}
		return mapData;		
	}
	public static String updatePass(Connection con, String password,String mdId,String mailId,String vrifyCode,String mobno) {		
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String exist="NO";
		try
		{		
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select email_id from md_details where email_id='"+mailId+"'and md_id!="+mdId+"";
			rs1=stmt.executeQuery(sql);
			System.out.println("sql========"+sql);
			while(rs1.next())
			{
				exist="yes";
			}
			if(exist.equalsIgnoreCase("No"))
			{
				System.out.println("password in dao :"+password);
				String update="update md_login_details set user_name='"+mailId+"',password='"+password+"',verification_code='"+vrifyCode+"',pass_status='newpass',Verified='N' where user_id="+mdId+"";
				stmt.executeUpdate(update);		
				System.out.println("update qry :"+update);
				String update2="update md_details set email_id='"+mailId+"' where md_id="+mdId+"";
				stmt.executeUpdate(update2);
			}			
		}catch(Exception e)
		{
			System.out.println("Exception in LoginInfoDao in updatePass"+e.toString());
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
				System.out.println("Exception in LoginInfoDao in updatePass while closing connection"+e.toString());
			}			
		}	
		return exist;	
	}
	public static String updateLoginStatus(Connection con,String vrifyCode,String mdId,HttpSession session) {		
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String status="notVerified";
		String Md_ID=null;
		int countNo=0;		
		try
		{
			con=DBConnection.getConnection();		
			stmt=con.createStatement();

			String Query="select md_id from md_details where md_initial+convert(varchar,md_id) ='"+mdId+"'";
			System.out.println(Query);
			rs=stmt.executeQuery(Query);
			if(rs.next())
			{
				Md_ID=rs.getString(1);			 
			}

			String updateQuery="select count(*) as countno from md_login_details where  verification_code='"+vrifyCode+"' and user_id="+Md_ID+"";
			System.out.println(updateQuery);
			rs=stmt.executeQuery(updateQuery);
			if(rs.next())
			{
				countNo=rs.getInt(1);			 
			}
			if(countNo>=1)
			{
				String updateQuery2="update md_login_details set Verified='Y' where user_id="+Md_ID+"";
				System.out.println("========we r inside dao class and the update query is==========="+updateQuery2);

				stmt.executeUpdate(updateQuery2);
				/*String selectMobileStatus="select Mob_Verified from md_login_details where  user_id="+mdId+"";
			rs=stmt.executeQuery(selectMobileStatus);
			System.out.println("selectMobileStatus :"+selectMobileStatus);
			if(rs.next()){
				status=rs.getString(1);
				System.out.println("========***************************************==========="+status);
			}*/

				status="verified";
			}
			else{
				System.out.println("inside else block of updateLoginStatus");
				status="notVerified";
				System.out.println("========++++++++++this is not verified++++++++==========="+status);		
			}			
		}catch(Exception e)
		{
			status="notVerified";
			System.out.println("Exception in updateLoginStatus"+e.toString());
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
				System.out.println("Exception in updateLoginStatus while closing connection "+e.toString());
			}			
		}
		return status;	
	}
	public static String  getRandomCode() {
		String transid="";
		try
		{   
			System.out.println("------------------inside getRandomCode---------------");

			int n = 8;
			Random randGen = new Random();	    
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);	    
			int randomNum = randGen.nextInt(range) + startNum;
			transid = String.valueOf(randomNum);
			System.out.println("------------------inside transid---------------"+transid);			
		}catch(Exception e)
		{
			System.out.println("Exception in getRandomCode"+e.toString());
		} 	
		return transid;
	}
	public static String changeMdpassword(Connection con,String mdId,String newPass) {	
		Statement stmt=null;
		ResultSet rs=null;		
		String status="notupdated";
		try
		{    
			System.out.println("------------------inside changeMdpassword---------------");
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			System.out.println("newPass in dao class :"+newPass);
			String updateQuery="update md_login_details set password='"+newPass+"' where user_id="+mdId+"";
			System.out.println("updateQuery  is "+updateQuery);
			int a=stmt.executeUpdate(updateQuery);			
			if(a>0)
			{
				status="updated";
			}
			else{
				status="notupdated";			
			}			
		}catch(Exception e)
		{
			status="notupdated";
			System.out.println("Exception in changeMdpassword"+e.toString());
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
				System.out.println("Exception in changeMdpassword while closing connection"+e.toString());
			}			
		}
		return status;		
	}

	public static String getOldvarificationCode(Connection con,String mdId,String mailId,HttpSession session) {
		System.out.println("inside getOldvarificationCode");
		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String varify_code ="";
		String exist="No";
		try
		{  			
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="select email_id from md_details where email_id='"+mailId+"'and md_id!="+mdId+"";
			System.out.println("sql============"+sql+"------"+mailId+"-----"+mdId);
			rs1=stmt.executeQuery(sql);
			while(rs1.next())
			{
				exist="yes";
			}
			System.out.println("exist :"+exist); 
			if(exist.equalsIgnoreCase("No"))
			{
				String update2="update md_details set email_id='"+mailId+"' where md_id="+mdId+"";
				System.out.println("update query is "+update2);
				stmt.executeUpdate(update2);
				String update3="update md_login_details set user_name='"+mailId+"' where user_id="+mdId+"";
				System.out.println("2nd update "+update3);
				stmt.executeUpdate(update3);            	 
				System.out.println("");
				String selectQuery="SELECT verification_code FROM md_login_details  where user_id="+mdId+"";
				System.out.println("select Query is---------   -----"+selectQuery);
				rs=stmt.executeQuery(selectQuery);			
				if(rs.next())
				{
					varify_code=rs.getString(1);
				}
				System.out.println("varify code...."+varify_code);	
			}            
			session.setAttribute("exist",exist);             
		}catch(Exception e)
		{
			System.out.println("Exception in getOldvarificationCode========"+e.toString());
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
				System.out.println("Exception in getOldvarificationCode while closing connection========"+e.toString());
			}			
		}
		return varify_code;		
	}
	public static String updateNewvarificationCode(Connection con,String mdId,HttpSession session,String varificationcode){

		Statement stmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		String Email_id ="";
		String exist="No";
		try
		{    
			System.out.println("------------------inside UpdateNewvarificationCode---------------");
			con=DBConnection.getConnection();
			System.out.println("checking the db conttn");
			stmt=con.createStatement();        
			String update1=("update md_login_details set verification_code='"+varificationcode+"' where user_id="+mdId+"");
			System.out.println(update1);
			stmt.executeUpdate(update1);         
			System.out.println("select Query is :"+update1);
			String selectQuery="select Email_id from md_details where md_id='"+mdId+"'";
			System.out.println(selectQuery);
			System.out.println("Query :"+selectQuery);
			rs=stmt.executeQuery(selectQuery);			
			if(rs.next())
			{
				Email_id=rs.getString(1);
			}	
			session.setAttribute("exist",exist);			
		}catch(Exception e)
		{
			System.out.println("Exception in UpdateNewvarificationCode========"+e.toString());
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
				System.out.println("Exception in getOldvarificationCode while closing connection========"+e.toString());
			}		
		}
		return Email_id;
	}
	public static String getAgentbalance(String agent_id) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String balance ="";
		double balance1=0;
		DecimalFormat df = new DecimalFormat("0.00");
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String balancequery="SELECT totcash-usedcash as balance  FROM agent_amount where agent_id='"+agent_id+"'";

			rs=stmt.executeQuery(balancequery);			
			while(rs.next())
			{
				balance1=rs.getDouble(1);
				balance=df.format(balance1);
			}		
		}
		catch(Exception e)
		{
			System.out.println("Hello Error In checking balance"+e.toString());
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
				System.out.println("Exception in check login status"+e.toString());
			}		
		}
		return balance;	
	}
	public static String getcashBalance(String agent_id) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String Amount ="";
		String cashbackAmount ="0";
		int countno=0;
		DecimalFormat df = new DecimalFormat("0.00");
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String balancequery="select count(*),SUM(used_amount) from dbo.agent_card_details where  agent_id='"+agent_id+"'";

			rs=stmt.executeQuery(balancequery);			
			while(rs.next())
			{
				countno=rs.getInt(1);
				Amount=rs.getString(2);			
			}
			if(countno>=1)
			{
				cashbackAmount=Amount;
			}
			else {
				cashbackAmount="zero";
			}		
		}
		catch(Exception e)
		{
			System.out.println("Hello Error In checking balance"+e.toString());
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
				System.out.println("Exception in check login status"+e.toString());
			}			
		}
		return cashbackAmount;		
	}
	public static void main(String ads[]){
		//System.out.println("i m in main method");
		try{			
			Connection con=null;
			String userid="119900";
			HashMap mapdata=LoginInfoDao.chekLoginInfo(con,userid);
			String passStatus=(String)mapdata.get("passStatus");
			String password=(String)mapdata.get("password");
			String oldpassword=(String)mapdata.get("old_Pass");
			String Verified=(String)mapdata.get("Verified");
			String email_id=(String)mapdata.get("email_id");
			if(Verified==null){            	 
				System.out.print("hello null");
			}	             
			else {            	 
				System.out.print("hello not null");           	 
			}		
		} catch(Exception e){
			System.out.println("Problem in creating connection  "+e);
		}
	}
}
