package com.agent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import com.db.*;
import com.utility.UtilityP;



public class AgentDetailsDao {

	public static String getClientTyp(String ClientId){
		Statement stmt=null; 
		ResultSet rs=null;
		Connection con=null;
		String client_type=null;	
		try
		{		 
			con=DBConnection.getConnection();
			stmt=con.createStatement();

			String sql1="select Flag from dbo.white_label_details (nolock) where Client_Id ='"+ClientId+"'";

			rs=stmt.executeQuery(sql1);			
			while(rs.next())
			{
				client_type=rs.getString(1);
			}
		}catch(Exception E)
		{
			E.printStackTrace();
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
				e.printStackTrace();
			}

		}
		return client_type;	

	}
	public static ArrayList getAllDistributorId(String mdId){
		ArrayList getDistributor=new ArrayList();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String Client_Flag=null;


		try
		{           
			con=DBConnection.getConnection();			
			stmt1=con.createStatement();

			String sql1="select flag from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";
			System.out.println(sql1);
			rs1=stmt1.executeQuery(sql1);			
			while(rs1.next())
			{
				Client_Flag=rs1.getString(1);
			}              
			System.out.println("client type :"+Client_Flag);
			if(Client_Flag.equalsIgnoreCase("2")||(Client_Flag.equalsIgnoreCase("0")))
			{
				stmt=con.createStatement();
				String sql="";

				sql="select a.distributor_id as did,a.distributor_initial as ini from distributor_details a,distributor_login_details b,distributor_amount c where a.distributor_id=b.user_id and b.user_id=c.distributor_id and a.md_id="+mdId+" order by ini";

				System.out.println(sql);			
				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{             
					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;				
					HashMap information=new HashMap();
					information.put("distributor_id",distributor_id);
					getDistributor.add(information);
				}			
			}
			if(Client_Flag.equalsIgnoreCase("1"))
			{		
				stmt=con.createStatement();
				String sql="";					
				sql="select a.distributor_id as did,a.distributor_initial as ini from distributor_details a ,dbo.distributor_login_details b,distributor_amount c where   a.distributor_id =b.user_id and b.user_id=c.distributor_id and a.md_id="+mdId+" order by ini";	
				System.out.println(sql);			
				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{           	    
					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;
					HashMap information=new HashMap();
					information.put("distributor_id",distributor_id);
					getDistributor.add(information);
				}
			}			
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}
		}
		return getDistributor;
	}

	public static String deactivateAgentStatus(String AgentId,String mdId){
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		String status="";
		try
		{		 
			con=DBConnection.getConnection();
			stmt1=con.createStatement();			
			String sql1="select flag from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";

			rs1=stmt1.executeQuery(sql1);			
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}

			System.out.println("hello client id is "+client_type1);

			stmt = con.createStatement();
			String Agent_ID="";
			String Agent_mobileNumber="";

			String selectQuery="select agent_id,mobile_no from agent_details where (agent_initial+convert(varchar,agent_id))='"+AgentId+"'";
			System.out.println("hello selectQuery  is "+selectQuery);
			System.out.println("AgentId :"+AgentId);
			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{				
				Agent_ID=rs.getString(1);
				Agent_mobileNumber=rs.getString(2);
			}
			System.out.println("after loop");              
			if(client_type1.equalsIgnoreCase("0")||client_type1.equalsIgnoreCase("2"))
			{
				String sql="update login_details set login_status='Deactive' where user_id="+Agent_ID+"";
				System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0){					
					String testMessage="Dear Lead Associate, Your Lead Associate ID "+Agent_ID+" has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);	
					status="update";
				}
				else{
					status="notupdate";
				}			
			}               
			if(client_type1.equalsIgnoreCase("1"))
			{	
				String updateQuery="update  agent_details  set MOB_TXN_STATUS='Deactive' where (agent_initial+convert(varchar,agent_id))='"+AgentId+"'";
				//System.out.println(updateQuery);


				int updateCount=stmt.executeUpdate(updateQuery);

				String sql="update recharge_e_point_login_info set login_status='Deactive' where user_id="+Agent_ID+"";
				//System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0 && updateCount>0){              
					String testMessage="Dear Lead Associate, Your Lead Associate ID "+AgentId+" has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);
					status="update";
				}
				else{
					status="notupdate";
				}
				System.out.println("status of transaction is =========="+status);
			}
		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
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
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}

		}
		return status;	
	}

	@SuppressWarnings("unused")



	public static String  activateAgentStatus(String AgentId,String mdId){

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		CallableStatement cstmt=null;
		String status="";
		String AgentMob="";
		String SmsStatus="";
		String Mpin="";
		String testmessage="";
		try
		{
			con= DBConnection.getConnection();
			stmt=con.createStatement();
			// SP used before was Activate_Agent_FromMD
			cstmt=con.prepareCall("{call MDS_Activate_Agent(?)}");
			cstmt.setString(1,AgentId);

			rs=cstmt.executeQuery();
			if(rs.next()){

				status=rs.getString(1);
				/* AgentMob=rs.getString(2);
			  SmsStatus=rs.getString(3);
			  Mpin=rs.getString(4);*/
			}		

			if(status.equalsIgnoreCase("Active")){
				status="Activate";  
			}
			else{
				status="Notactive";
			}

		}catch(Exception E)
		{
			status="Notactive";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
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
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}

		}
		return status;	


	}
	/*public static String updateDistributorProfile(String mdId,String distributorId,String resAddress1,String resAddress2,String country,String state,String resCity,String pincode,String landmark,String resMobileNo,String resAlternateNo,String resPhoneNo,String resFaxNo){
	    Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String status="";
	try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="update distributor_details set Res_Address1='"+resAddress1+"',Res_Address2='"+resAddress2+"',Res_Country='"+country+"',Res_State='"+state+"',Res_City='"+resCity+"',Res_Pincode='"+pincode+"',Res_Landmark='"+landmark+"',Res_Mobile_No='"+resMobileNo+"',Res_Alternate_Mobile_No='"+resAlternateNo+"',Res_Phone_No='"+resPhoneNo+"',Res_Fax_No='"+resFaxNo+"' where (distributor_initial+convert(varchar,distributor_id))='"+distributorId+"'";
			//System.out.println(sql);
			int a=stmt.executeUpdate(sql);
			if(a>0){
			status="update";
			}
			else{
			status="notupdate";
			}
			System.out.println("value of a is =========="+a);
		}

		catch(Exception E)
		{
		    status="notupdate";
			System.out.println("Exception in updateDistributorProfile======"+E.toString());
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
				System.out.println("Exception in updateDistributorProfile while closing connection"+e.toString());
			}

		}


		return status;

	}
	 */
	/*public static String getCity(String cityCode)
	{
		String cityName="";
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			String sql="select city_name from city_details where CITY_CODE="+cityCode+"";
			//System.out.println(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				cityName=rs.getString("city_name");	
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

	public ArrayList getAllAgent(String mdId) {
		ArrayList getDistributor=new ArrayList();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;


		try
		{

			//System.out.println("test 1");
			con=DBConnection.getConnection();
			//System.out.println("test 2");
			stmt1=con.createStatement();
			//System.out.println("test 3");
			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";
			//System.out.println("test 4"+sql1);
			rs1=stmt1.executeQuery(sql1); 
			//System.out.println("test 5");
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}
			// System.out.println("test 6");
			System.out.println(client_type1);

			//check for TEP
			if(client_type1.equals("TEP"))
			{
				stmt=con.createStatement();
				String sql="";

				sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c,distributor_details d where  c.agent_id=a.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and d.md_id="+mdId+" order by agentcomid ";


				System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					//System.out.println("hello we are inside while loop");
					HashMap information=new HashMap();
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
					information.put("mpin", rs.getString("MPIN"));

					getDistributor.add(information);
				}

			}

			//end of tep

			//check for REP
			if(client_type1.equals("REP"))
			{

				System.out.println("welcome to rep");
				stmt=con.createStatement();
				String sql="";

				sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,recharge_e_point_login_info b,agent_amount c,distributor_details d where  c.agent_id=a.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and d.md_id="+mdId+" order by agentcomid";

				System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					//System.out.println("hello we are inside while loop");
					HashMap information=new HashMap();
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));	
					information.put("mpin", rs.getString("MPIN"));
					getDistributor.add(information);
				}

			}

			//end of REP

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}

	public ArrayList getAllAgentActive(String mdId,String distributorId,String typ, String page,HttpSession session) {
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;

		int maxPage=0;
		String DsId=null;
		try
		{		
			int pageInt=Integer.parseInt(page);
			int start=pageInt*50+1;
			int end=(pageInt+1)*50;
			con=DBConnection.getConnection();			
			stmt1=con.createStatement();			
			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";

			rs1=stmt1.executeQuery(sql1); 

			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}

			System.out.println(client_type1);

			String query="select distributor_id  from distributor_details where (distributor_initial+convert(varchar(20),distributor_id))='"+distributorId+"'";
			System.out.println("query :" +query);		 
			rs=stmt1.executeQuery(query);
			while(rs.next())
			{
				DsId=rs.getString(1);
			}

			if(client_type1.equals("TEP"))
			{
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";

				System.out.println("typ is :"+typ);

				selectQuery="select count(a.agent_id)from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate'";


				System.out.println("selectQuery :" +selectQuery);		 
				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					maxPage=(rs.getInt(1));

					System.out.println("##MAXPAGE="+maxPage);

					if(maxPage!=0)
					{
						maxPage=(int) Math.ceil(maxPage/50);
					}

					System.out.println("Total rows is "+maxPage);
				}

				sql=" select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid, (c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";
				rs=stmt.executeQuery(sql);
				System.out.println(sql);

				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					HashMap<String, String> information=new HashMap<String, String>();

					information.put("SN.o", rs.getString("row"));
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
					information.put("mpin", rs.getString("MPIN"));
					//	System.out.println("hello we are inside while loop and hap value ia "+information);
					getDistributor.add(information);
				}				
			}

			if(client_type1.equals("REP"))
			{
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";

				selectQuery="select count(a.agent_id)from  agent_details a ,recharge_e_point_login_info b where  a.agent_id =b.user_id and  a.distributor_id='"+DsId+"' and b.login_status='Activate'";


				System.out.println("selectQuery :" +selectQuery);						
				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					maxPage=(rs.getInt(1));

					System.out.println("##MAXPAGE="+maxPage);

					if(maxPage!=0)
					{
						maxPage=(int) Math.ceil(maxPage/50);
					}

					System.out.println("Total rows is "+maxPage);
				}

				sql=" select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid, (c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,recharge_e_point_login_info b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";		
				//   sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,recharge_e_point_login_info b,agent_amount c,distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate' order by agentcomid";


				System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{
					/*   
				   String initial=rs.getString("ini");
				   String id=rs.getString("did");
	               String distributor_id=initial+id;*/


					HashMap<String, String> information=new HashMap<String, String>();
					information.put("SN.o", rs.getString("row"));
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributorId);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
					information.put("mpin", rs.getString("MPIN"));
					// System.out.println("hello we are inside while loop and hap value ia "+information);

					getDistributor.add(information);
				}

			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}

		//System.out.println("helloo arry list size is "+getDistributor);
		return getDistributor;
	}


	public static String DoAllActivateAgent(String mdId,String DSId){
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		String status="";
		try
		{
			System.out.println("DSID ::: "+DSId);
			con=DBConnection.getConnection();
			stmt1=con.createStatement();

			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";

			rs1=stmt1.executeQuery(sql1);			
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}

			System.out.println("hello client id is "+client_type1);

			stmt = con.createStatement();
			String DS_ID="";


			String selectQuery="select distributor_id from distributor_details where distributor_initial+convert(varchar,distributor_id)='"+DSId+"'";
			System.out.println("hello selectQuery  is "+selectQuery);
			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{				
				DS_ID=rs.getString(1);
			}
			System.out.println("after loop");              
			if(client_type1.equals("TEP"))
			{
				String sql="update login_details set login_status='Activate' where  user_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0){

					//  String testMessage="Dear Agent, Your Agent ID  has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);	
					status="update";
				}
				else{
					status="notupdate";
				}

			}


			//end of tep
			//check rep deactivate..................

			if(client_type1.equals("REP"))
			{


				String updateQuery="update  agent_details  set MOB_TXN_STATUS='Active',SmsTxn_Status='Y' where agent_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				System.out.println(updateQuery);


				int updateCount=stmt.executeUpdate(updateQuery);

				String sql="update recharge_e_point_login_info set login_status='Activate' where user_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0 && updateCount>0){              
					//String testMessage="Dear Agent, Your Agent ID "+AgentId+" has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);
					status="update";
				}
				else{
					status="notupdate";
				}
				System.out.println("status of transaction is =========="+status);
			}


			//end..................................... 

		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
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
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}

		}
		return status;	


	}
	public static String DoAllDeactivateAgent (String mdId,String DSId){
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		String status="";
		try
		{
			System.out.println("DSID ::: "+DSId);
			con=DBConnection.getConnection();
			stmt1=con.createStatement();

			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";

			rs1=stmt1.executeQuery(sql1);			
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}

			System.out.println("hello client id is "+client_type1);

			stmt = con.createStatement();
			String DS_ID="";
			String Agent_mobileNumber="";

			String selectQuery="select distributor_id from distributor_details where distributor_initial+convert(varchar,distributor_id)='"+DSId+"'";
			System.out.println("hello selectQuery  is "+selectQuery);
			rs=stmt.executeQuery(selectQuery);
			while(rs.next())
			{				
				DS_ID=rs.getString(1);
			}
			System.out.println("after loop");              
			if(client_type1.equals("TEP"))
			{
				String sql="update login_details set login_status='Deactive' where  user_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0){

					String testMessage="Dear Lead Associate, Your Lead Associate ID  has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);	
					status="update";
				}
				else{
					status="notupdate";
				}	
			}
			//end of tep
			//check rep deactivate..................

			if(client_type1.equalsIgnoreCase("REP"))
			{


				String updateQuery="update  agent_details  set MOB_TXN_STATUS='Deactive',SmsTxn_Status='N' where agent_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				//System.out.println(updateQuery);


				int updateCount=stmt.executeUpdate(updateQuery);

				String sql="update recharge_e_point_login_info set login_status='Deactive' where user_id in (select agent_id from agent_details where distributor_id ='"+DS_ID+"' )";
				//System.out.println(sql);
				int a=stmt.executeUpdate(sql);
				if(a>0 && updateCount>0){              
					//String testMessage="Dear Agent, Your Agent ID "+AgentId+" has been deactivated.";
					// String smsStatus=(String)SmsToMobileDao.sendMobileSmsZone(Agent_mobileNumber,testMessage);
					status="update";
				}
				else{
					status="notupdate";
				}
				System.out.println("status of transaction is =========="+status);
			}


			//end..................................... 

		}catch(Exception E)
		{
			status="notupdate";
			System.out.println("Exception in activateDistributorStatus======"+E.toString());
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
				System.out.println("Exception in activateDistributorStatus while closing connection"+e.toString());
			}

		}
		return status;	


	}
	public ArrayList<HashMap<String, String>> getActiveAgents(String distributorId,String page,String ClientFlag) 
	{
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		int maxPage=0;
		String loginTable=null;
		try
		{
			int count=0;
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(ClientFlag.equalsIgnoreCase("0") || ClientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}		
			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			String CountQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate'";
			System.out.println(CountQry);
			stmt=con.createStatement();		
			rs=stmt.executeQuery(CountQry);		
			while(rs.next())
			{
				maxPage=(rs.getInt(1));			
				System.out.println("##MAXPAGE="+maxPage);			
				if(maxPage!=0)
				{
					maxPage=(int) Math.ceil(maxPage/100);
				}			
				System.out.println("Total rows is "+maxPage);
			}	
			sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Activate') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";				

			//	sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,rec.CREATED_DATE,sms_send.TEXT_MESSAGE,sms_send.SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where ag.Agent_MobileNo='"+AgentMobileNo+"'";

			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;

				HashMap information=new HashMap();
				information.put("SN.o", rs.getString("row"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));
				information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
				information.put("mpin", rs.getString("MPIN"));
				information.put("MaxPage", maxPage);
				//	System.out.println("hello we are inside while loop and hap value ia "+information);
				getDistributor.add(information);

			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in getDSDetails OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}
	public ArrayList<HashMap<String, String>> getAllAgents(String distributorId,String page,String ClientFlag) 
	{
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		int maxPage=0;
		String loginTable=null;
		try
		{
			int count=0;
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(ClientFlag.equalsIgnoreCase("0") ||ClientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}		
			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			String CountQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' ";
			System.out.println(CountQry);
			stmt=con.createStatement();		
			rs=stmt.executeQuery(CountQry);		
			while(rs.next())
			{
				maxPage=(rs.getInt(1));			
				System.out.println("##MAXPAGE="+maxPage);			
				if(maxPage!=0)
				{
					maxPage=(int) Math.ceil(maxPage/100);
				}			
				System.out.println("Total rows is "+maxPage);
			}	
			sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";				

			//	sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,rec.CREATED_DATE,sms_send.TEXT_MESSAGE,sms_send.SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where ag.Agent_MobileNo='"+AgentMobileNo+"'";

			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;

				HashMap information=new HashMap();
				information.put("SN.o", rs.getString("row"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));
				information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
				information.put("mpin", rs.getString("MPIN"));
				information.put("MaxPage", maxPage);
				//	System.out.println("hello we are inside while loop and hap value ia "+information);
				getDistributor.add(information);

			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in getDSDetails OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}
	public ArrayList<HashMap<String, String>> getDeactiveAgents(String distributorId,String page,String ClientFlag) 
	{
		ArrayList<HashMap<String, String>> getDistributor=new ArrayList<HashMap<String, String>>();

		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		String sql="";
		int maxPage=0;
		String loginTable=null;
		try
		{
			int count=0;
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			if(ClientFlag.equalsIgnoreCase("0") || ClientFlag.equalsIgnoreCase("2")){
				loginTable="login_details";
			}
			else{
				loginTable="recharge_e_point_login_info";
			}		
			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;

			String CountQry="select count (a.agent_id) from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status in  ('Deactive','Deactivate')";
			System.out.println(CountQry);
			stmt=con.createStatement();		
			rs=stmt.executeQuery(CountQry);		
			while(rs.next())
			{
				maxPage=(rs.getInt(1));			
				System.out.println("##MAXPAGE="+maxPage);			
				if(maxPage!=0)
				{
					maxPage=(int) Math.ceil(maxPage/100);
				}			
				System.out.println("Total rows is "+maxPage);
			}	
			sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,"+loginTable+" b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status in  ('Deactive','Deactivate')) as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";				

			//	sql="select top 20 rec.TEXT_MESSAGE,rec.KEYWORD,rec.CREATED_DATE,sms_send.TEXT_MESSAGE,sms_send.SMS_SEND_DATETIME from SMS_RECEIVED as rec inner join sms_send on sms_send.REF_NO=rec.REF_NO inner join agent_details as ag on ag.agent_mobileno=rec.mobile inner join distributor_details as ds on ds.distributor_id=ag.distributor_id inner join md_details as md on md.md_id=ds.md_id where ag.Agent_MobileNo='"+AgentMobileNo+"'";

			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				String initial=rs.getString("ini");
				String id=rs.getString("did");
				String distributor_id=initial+id;

				HashMap information=new HashMap();
				information.put("SN.o", rs.getString("row"));
				information.put("distributor_id",distributor_id);
				information.put("company_name",rs.getString("cname"));
				information.put("mobile",rs.getString("mobno"));
				information.put("email_id",rs.getString("eid"));
				information.put("login_status",rs.getString("status"));
				information.put("contact_person",rs.getString("contact_person"));
				information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
				information.put("mpin", rs.getString("MPIN"));
				information.put("MaxPage", maxPage);
				//	System.out.println("hello we are inside while loop and hap value ia "+information);
				getDistributor.add(information);

			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in getDSDetails OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}

	public ArrayList getAllAgentDeactive(String mdId,String distributorId,String typ, String page,HttpSession session) {
		ArrayList getDistributor=new ArrayList();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		int count=0;
		int maxPage=0;
		String DsId=null;
		try
		{
			con=DBConnection.getConnection();			
			stmt1=con.createStatement();		

			int pageInt=Integer.parseInt(page);
			int start=pageInt*100+1;
			int end=(pageInt+1)*100;


			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";

			rs1=stmt1.executeQuery(sql1); 			
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}              
			System.out.println(client_type1);

			String query="select distributor_id  from distributor_details where (distributor_initial+convert(varchar(20),distributor_id))='"+distributorId+"'";
			System.out.println("query :" +query);		 
			rs=stmt1.executeQuery(query);
			while(rs.next())
			{
				DsId=rs.getString(1);
			} 

			if(client_type1.equals("TEP"))
			{
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";			


				selectQuery="select count(a.agent_id)from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Deactive'";


				System.out.println("selectQuery :" +selectQuery);

				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					maxPage=(rs.getInt(1));

					System.out.println("##MAXPAGE="+maxPage);

					if(maxPage!=0)
					{
						maxPage=(int) Math.ceil(maxPage/100);
					}

					System.out.println("Total rows is "+maxPage);
				}
				sql=" select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid, (c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Deactive') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";
				rs=stmt.executeQuery(sql);
				System.out.println(sql);

				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					HashMap information=new HashMap();
					information.put("SN.o", rs.getString("row"));
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
					information.put("mpin", rs.getString("MPIN"));
					information.put("MaxPage", maxPage);
					//	System.out.println("hello we are inside while loop and hap value ia "+information);
					getDistributor.add(information);
				}
				//  session.setAttribute("count1",count1);
			}

			if(client_type1.equalsIgnoreCase("REP"))
			{
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";			

				selectQuery="select count(a.agent_id)from  agent_details a ,recharge_e_point_login_info b where  a.agent_id =b.user_id and  a.distributor_id='"+DsId+"' and b.login_status='Deactive'";

				System.out.println("selectQuery :" +selectQuery);				
				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					maxPage=(rs.getInt(1));

					System.out.println("##MAXPAGE="+maxPage);

					if(maxPage!=0)
					{
						maxPage=(int) Math.ceil(maxPage/100);
					}

					System.out.println("Total rows is "+maxPage);
				}

				sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,recharge_e_point_login_info b,agent_amount c,distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' and b.login_status='Deactive' order by agentcomid";


				System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					HashMap information=new HashMap();
					information.put("SN.o", rs.getString("row"));
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
					information.put("mpin", rs.getString("MPIN"));
					information.put("MaxPage", maxPage);
					// System.out.println("hello we are inside while loop and hap value ia "+information);

					getDistributor.add(information);
				}
				//	session.setAttribute("count1",count1);
			}

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}			
		}
		return getDistributor;
	}

	public ArrayList getDistributorAllAgent(String mdId,String distributorId,String typ, String modcount,int start,int end,HttpSession session) {
		ArrayList getDistributor=new ArrayList();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		int count=0;
		int count1=0;
		int pagecount=0;
		String DsId=null;
		try
		{

			//System.out.println("test 1");
			con=DBConnection.getConnection();
			//System.out.println("test 2");
			stmt1=con.createStatement();
			//System.out.println("test 3");
			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";
			//System.out.println("test 4"+sql1);
			rs1=stmt1.executeQuery(sql1); 
			//System.out.println("test 5");
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}
			//System.out.println("test 6");
			System.out.println(client_type1);


			String query="select distributor_id  from distributor_details where (distributor_initial+convert(varchar(20),distributor_id))='"+distributorId+"'";
			System.out.println("query :" +query);		 
			rs=stmt1.executeQuery(query);
			while(rs.next())
			{
				DsId=rs.getString(1);

			}


			if(client_type1.equals("TEP"))
			{   
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";	

				System.out.println("typ is :"+typ);




				if(typ.equalsIgnoreCase("deactive")){
					selectQuery="select count(a.agent_id)from  agent_details a ,login_details b where  a.agent_id =b.user_id and a.distributor_id='"+DsId+"' and b.login_status='Deactive'";
				}
				if(typ.equalsIgnoreCase("active")){
					selectQuery="select count(a.agent_id)from agent_details a ,login_details b where a.agent_id =b.user_id and a.distributor_id='"+DsId+"' and b.login_status='Activate'";
				}
				if(typ.equalsIgnoreCase("viewAgentAll")){
					selectQuery=" select count(a.agent_id)from agent_details a,login_details b where a.agent_id =b.user_id  and a.distributor_id='"+DsId+"'";
				}

				System.out.println("selectQuery :" +selectQuery);	



				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					count=rs.getInt(1);
				}
				session.setAttribute("count",count);
				int modulus=count%100;
				System.out.println("modulus : " +modulus);
				if(modulus==0)
				{
					pagecount=count/100;
					System.out.println("pagecount: "+pagecount);

				}
				else
				{
					int page=count/100;
					pagecount=page+1;
					System.out.println("pagecount: "+pagecount);
				}
				session.setAttribute("pagecount",pagecount);
				System.out.println("count is=================="+count);

				if(modcount.equals("0"))
				{

					count1=Integer.parseInt(modcount);

					count1=count;
				}
				else
				{
					count1=Integer.parseInt(modcount);
				}

				if(count1>0)
				{
					if(count1<100)
					{
						session.setAttribute("start",start);
						end=start+count1;
						session.setAttribute("end",end);


						sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as  balance,a.agent_id as did, a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status, a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c, distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and  (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' ) as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row";		
						rs=stmt.executeQuery(sql);
						System.out.println(sql);

						while(rs.next())
						{

							String initial=rs.getString("ini");
							String id=rs.getString("did");
							String distributor_id=initial+id;

							HashMap information=new HashMap();
							information.put("balance",rs.getString("balance"));
							information.put("distributor_id",distributor_id);
							information.put("company_name",rs.getString("cname"));
							information.put("mobile",rs.getString("mobno"));
							information.put("email_id",rs.getString("eid"));
							information.put("login_status",rs.getString("status"));
							information.put("contact_person",rs.getString("contact_person"));
							information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
							information.put("mpin", rs.getString("MPIN"));

							//	System.out.println("hello we are inside while loop and hap value ia "+information);
							getDistributor.add(information);
						}
						session.setAttribute("count1",count1);
					}
					else
					{
						System.out.println("inside next-- all agents-- else block");
						session.setAttribute("start",start);
						end=start+99;
						System.out.println("end in Dao class is"+end);
						session.setAttribute("end",end);				   
						sql=" select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid, (c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";
						rs=stmt.executeQuery(sql);
						System.out.println(sql);

						while(rs.next())
						{
							//    System.out.println("inside else block of param=next");
							String initial=rs.getString("ini");
							String id=rs.getString("did");
							String distributor_id=initial+id;

							HashMap information=new HashMap();
							information.put("balance",rs.getString("balance"));
							information.put("distributor_id",distributor_id);
							information.put("company_name",rs.getString("cname"));
							information.put("mobile",rs.getString("mobno"));
							information.put("email_id",rs.getString("eid"));
							information.put("login_status",rs.getString("status"));
							information.put("contact_person",rs.getString("contact_person"));
							information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
							information.put("mpin", rs.getString("MPIN"));

							//	System.out.println("hello we are inside while loop and hap value ia "+information);
							getDistributor.add(information);
						}
						System.out.println("end of else in next");
						session.setAttribute("count1",count1);
					}

				}
			}

			//end TEP 
			//check rep Activate............

			if(client_type1.equals("REP"))
			{
				stmt=con.createStatement();
				String sql="";
				String selectQuery="";

				if(typ.equalsIgnoreCase("deactive")){
					selectQuery="select count(a.agent_id)from  agent_details a ,recharge_e_point_login_info b where  a.agent_id =b.user_id and  a.distributor_id='"+DsId+"' and b.login_status='Deactive'";
				}
				if(typ.equalsIgnoreCase("active")){
					selectQuery="select count(a.agent_id)from  agent_details a ,recharge_e_point_login_info b where  a.agent_id =b.user_id and  a.distributor_id='"+DsId+"' and b.login_status='Activate'";
				}
				if(typ.equalsIgnoreCase("viewAgentAll")){
					selectQuery="select count(a.agent_id)from  agent_details a ,recharge_e_point_login_info b where  a.agent_id =b.user_id and  a.distributor_id='"+DsId+"'";
				}
				System.out.println("selectQuery :" +selectQuery);

				rs=stmt.executeQuery(selectQuery);
				while(rs.next())
				{
					count=rs.getInt(1);
				}
				session.setAttribute("count",count);
				int modulus=count%100;
				if(modulus==0)
				{
					pagecount=count/100;
				}
				else
				{
					int page=count/100;
					pagecount=page+1;
				}
				session.setAttribute("pagecount",pagecount);
				System.out.println("page count is=================="+count);

				if(modcount.equals("0"))
				{

					count1=Integer.parseInt(modcount);

					count1=count;
				}
				else
				{
					count1=Integer.parseInt(modcount);
				}

				if(count1>0)
				{
					if(count1<100)
					{
						session.setAttribute("start",start);
						end=start+count1;
						session.setAttribute("end",end);


						sql="select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount)as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,recharge_e_point_login_info b,agent_amount c,distributor_details d where a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"' )  as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row  ";


						System.out.println(sql);

						rs=stmt.executeQuery(sql); 
						while(rs.next())
						{

							String initial=rs.getString("ini");
							String id=rs.getString("did");
							String distributor_id=initial+id;					
							HashMap information=new HashMap();
							information.put("balance",rs.getString("balance"));
							information.put("distributor_id",distributor_id);
							information.put("company_name",rs.getString("cname"));
							information.put("mobile",rs.getString("mobno"));
							information.put("email_id",rs.getString("eid"));
							information.put("login_status",rs.getString("status"));
							information.put("contact_person",rs.getString("contact_person"));
							information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
							information.put("mpin", rs.getString("MPIN"));
							// System.out.println("hello we are inside while loop and hap value ia "+information);

							getDistributor.add(information);
						}
						session.setAttribute("count1",count1);
					}
					else
					{
						session.setAttribute("start",start);
						end=start+99;
						System.out.println("end in Dao class is"+end);
						session.setAttribute("end",end);					   
						sql=" select * from(select ROW_NUMBER() OVER (ORDER BY a.date_of_joining desc)as row,(convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid, (c.totcash-c.usedcash+c.cutoff_amount) as balance,a.agent_id as did,a.agent_initial as ini,a.agency_name as cname, a.mobile_no as mobno,  a.email_id as eid ,b.login_status as status,a.agent_name as contact_person,a.MOB_TXN_STATUS,a.MPIN from agent_details a ,login_details b,agent_amount c,  distributor_details d where  a.agent_id=c.agent_id and a.agent_id =b.user_id and a.distributor_id=d.distributor_id and (d.distributor_initial+convert(varchar(20),d.distributor_id))='"+distributorId+"') as mytable where mytable.row>='"+start+"' AND mytable.row<='"+end+"' order by row ";
						rs=stmt.executeQuery(sql);
						System.out.println(sql);

						while(rs.next())
						{
							String initial=rs.getString("ini");
							String id=rs.getString("did");
							String distributor_id=initial+id;

							//System.out.println("hello we are inside while loop");
							HashMap information=new HashMap();
							information.put("balance",rs.getString("balance"));
							information.put("distributor_id",distributor_id);
							information.put("company_name",rs.getString("cname"));
							information.put("mobile",rs.getString("mobno"));
							information.put("email_id",rs.getString("eid"));
							information.put("login_status",rs.getString("status"));
							information.put("contact_person",rs.getString("contact_person"));
							information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));
							information.put("mpin", rs.getString("MPIN"));
							getDistributor.add(information);
						}
						session.setAttribute("count1",count1);
					}

					//end........................
				}		

			}

		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}
		}
		return getDistributor;
	}


	public ArrayList getAllDistributorDeactive(String mdId) {
		ArrayList getDistributor=new ArrayList();
		Statement stmt=null;
		Statement stmt1=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		Connection con=null;
		String client_type1=null;
		try
		{


			// System.out.println("test 1");
			con=DBConnection.getConnection();
			//System.out.println("test 2");
			stmt1=con.createStatement();
			//System.out.println("test 3");
			String sql1="select Client_Type from dbo.white_label_details where Client_Id in(select Client_Id from dbo.MD_Details where MD_id="+mdId+")";
			//System.out.println("test 4"+sql1);
			rs1=stmt1.executeQuery(sql1); 
			//System.out.println("test 5");
			while(rs1.next())
			{
				client_type1=rs1.getString(1);
			}
			//System.out.println("test 6");
			System.out.println(client_type1);

			//check for TEP
			if(client_type1.equals("TEP"))
			{

				stmt=con.createStatement();
				String sql="";

				sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.distributor_id as did,a.distributor_initial as ini,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person,a.MOB_TXN_STATUS from distributor_details a ,distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdId+" and b.login_status='Deactive' order by agentcomid";


				//System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					//System.out.println("hello we are inside while loop");
					HashMap information=new HashMap();
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));				
					getDistributor.add(information);
				}
			}

			//end TEP

			//check Deactive Rep 
			if(client_type1.equals("REP"))
			{

				stmt=con.createStatement();
				String sql="";

				sql="select (convert(varchar(15),a.agent_initial)+convert(varchar(15),a.agent_id)) as agentcomid,(c.totcash-c.usedcash+c.cutoff_amount) as balance,a.distributor_id as did,a.distributor_initial as ini,a.company_name as cname, a.mobile_no as mobno, a.email_id as eid ,b.login_status as status,a.distributor_name as contact_person,a.MOB_TXN_STATUS from distributor_details a ,dbo.rep_distributor_login_details b,distributor_amount c where  c.distributor_id=a.distributor_id and a.distributor_id =b.user_id and a.md_id="+mdId+" and b.login_status='Deactive' order by agentcomid";


				//System.out.println(sql);

				rs=stmt.executeQuery(sql); 
				while(rs.next())
				{

					String initial=rs.getString("ini");
					String id=rs.getString("did");
					String distributor_id=initial+id;

					//System.out.println("hello we are inside while loop");
					HashMap information=new HashMap();
					information.put("balance",rs.getString("balance"));
					information.put("distributor_id",distributor_id);
					information.put("company_name",rs.getString("cname"));
					information.put("mobile",rs.getString("mobno"));
					information.put("email_id",rs.getString("eid"));
					information.put("login_status",rs.getString("status"));
					information.put("contact_person",rs.getString("contact_person"));
					information.put("mobileStatus",rs.getString("MOB_TXN_STATUS"));



					getDistributor.add(information);
				}
			}
		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return getDistributor;
	}
	public static HashMap getDistributorInformation(String distributorId) 
	{


		HashMap information=new HashMap();
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		try
		{
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			String sql="";
			String resCity="";
			String resPhoneNo="";
			String resfaxNo="";
			sql="select distributor_id,distributor_initial,md_id,distributor_name,company_name,legal_status,address1,address2,address3,landmark,nearest_airport,pin_code,city,state,country,email_id,office_phone1,mobile_no,fax_no,date_of_joining,region,no_of_agent,air,rail,re_mobile,re_dth,bl_mobile,bl_lic,bk_bus,bk_hotel,bk_tour,reqest_courier,request_pancard,request_share,e_com_product,e_com_service,e_com_leadbased,ps_edu_e_point,ps_citizen_e_point,ps_profit_bazar,live_recharge,AirtelMobOffline,AirtelDthOffline,Date_Of_Birth,Gender,Marital_Status,Occupation,Company_Type,Designation,PAN_TIN_No,Website,Res_Address1,Res_Address2,Res_Country,Res_State,Res_District,Res_City,Res_Pincode,Res_Landmark,Res_Mobile_No,Res_Alternate_Mobile_No,Res_Phone_No,Res_Fax_No,Authorized_Mobile_No,Id_Proof,Address_Proof,Business_Firm_Proof,Father_Husband_Name,Mother_Maiden_Name,Spouse_Name,Category_name from distributor_details where (distributor_initial+convert(varchar,distributor_id))='"+distributorId+"'";
			//System.out.println(sql);

			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				information.put("distributor_id",rs.getString("distributor_id"));
				information.put("distributor_initial",rs.getString("distributor_initial"));
				information.put("md_id",rs.getString("md_id"));
				information.put("distributor_name",rs.getString("distributor_name"));
				information.put("company_name",rs.getString("company_name"));
				information.put("legal_status",rs.getString("legal_status"));
				information.put("address1",rs.getString("address1"));
				information.put("address2",rs.getString("address2"));
				information.put("address3",rs.getString("address3"));
				information.put("landmark",rs.getString("landmark"));
				information.put("pin_code",rs.getString("pin_code"));
				information.put("city",rs.getString("city"));
				information.put("state",rs.getString("state"));
				information.put("country",rs.getString("country"));
				information.put("email_id",rs.getString("email_id"));
				information.put("office_phone1",rs.getString("office_phone1"));
				information.put("mobile_no",rs.getString("mobile_no"));
				information.put("fax_no",rs.getString("fax_no"));
				information.put("date_of_joining",rs.getString("date_of_joining"));
				information.put("region",rs.getString("region"));
				information.put("no_of_agent",rs.getString("no_of_agent"));
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
				information.put("Date_Of_Birth",rs.getString("Date_Of_Birth"));
				information.put("Gender",rs.getString("Gender"));
				information.put("Marital_Status",rs.getString("Marital_Status"));
				information.put("Occupation",rs.getString("Occupation"));
				information.put("Company_Type",rs.getString("Company_Type"));
				information.put("Designation",rs.getString("Designation"));
				information.put("PAN_TIN_No",rs.getString("PAN_TIN_No"));
				information.put("Website",rs.getString("Website"));
				information.put("Res_Address1",rs.getString("Res_Address1"));
				information.put("Res_Address2",rs.getString("Res_Address2"));
				information.put("Res_Country",rs.getString("Res_Country"));
				information.put("Res_State",rs.getString("Res_State"));
				information.put("Res_District",rs.getString("Res_District"));
				resCity=rs.getString("Res_City");
				information.put("Res_City",resCity);
				information.put("Res_Pincode",rs.getString("Res_Pincode"));
				information.put("Res_Landmark",rs.getString("Res_Landmark"));
				information.put("Res_Mobile_No",rs.getString("Res_Mobile_No"));
				information.put("Res_Alternate_Mobile_No",rs.getString("Res_Alternate_Mobile_No"));
				resPhoneNo=rs.getString("Res_Phone_No");
				information.put("Res_Phone_No",resPhoneNo);
				resfaxNo=rs.getString("Res_Fax_No");
				information.put("Res_Fax_No",resfaxNo);
				information.put("Authorized_Mobile_No",rs.getString("Authorized_Mobile_No"));
				information.put("Id_Proof",rs.getString("Id_Proof"));
				information.put("Address_Proof",rs.getString("Address_Proof"));
				information.put("Business_Firm_Proof",rs.getString("Business_Firm_Proof"));
				information.put("Father_Husband_Name",rs.getString("Father_Husband_Name"));
				information.put("Mother_Maiden_Name",rs.getString("Mother_Maiden_Name"));
				information.put("Spouse_Name",rs.getString("Spouse_Name"));
				information.put("Category_name",rs.getString("Category_name"));				
			}

			StringTokenizer str=new StringTokenizer(resPhoneNo,"-");
			String stdcode=str.nextToken();
			String phoneNo=str.nextToken();
			StringTokenizer str1=new StringTokenizer(resfaxNo,"-");
			stdcode=str1.nextToken();
			String faxNo=str1.nextToken();
			information.put("stdcode",stdcode);
			information.put("phoneNo",phoneNo);
			information.put("faxNo",faxNo);

			String selectQuery="select city_code from city_details where city_name='"+resCity+"'";
			//System.out.println(selectQuery);
			rs=stmt.executeQuery(selectQuery);
			while(rs.next()){
				information.put("code",rs.getString(1));

			}


		}catch(Exception e)
		{
			System.out.println("Hello Add Member"+e.toString());
			e.printStackTrace();
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
				System.out.println("Exception in Login OF ADMIN"+e.toString());
			}

		}
		return information;
	}


	public static String sendMobileSms(String mobileNo,String message) {

		Statement stmt= null;
		ResultSet rs= null;
		String result="";

		try
		{
			message=message.replaceAll(" ", "%20");
			message=message.replaceAll("&", "and");

			String url="http://www.smszone.in/sendsms.asp?page=SendSmsBulk&username=919212244790&password=9327&number="+mobileNo+"%20&message="+message+"";



			String sendingResponse = UtilityP.post(url, null, "text", "GET", null);

			System.out.println("sendingResponse===in====Controller=========="+sendingResponse);
			if (sendingResponse==null)
			{
				result="unsuccess";
			}
			else{
				result="success";
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{

		}
		return result;


	}



}
