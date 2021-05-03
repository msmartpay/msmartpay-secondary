package com.TranSearch;

/**	Created By  	: 	Arshad
 * 	Created Date 	:	14/01/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.db.DBConnection;

public final class TransactionSearchDao {
	
	public final ArrayList<HashMap<String,String>> getTranData(String userID, String connectionNo) {
		String sql="";
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DBConnection.getConnection();
			sql="select top(10) a.agent_initial+convert(varchar(10),l.user_id) as AgentID,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,convert(decimal(18,2),l.amount) as amount,l.status from live_recharge l join agent_details a on a.agent_id=l.user_id where l.mobile_number=? order by l.date_time desc" ;
			System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, connectionNo);

			rs=pstmt.executeQuery();
			HashMap<String,String> mapdata;
			while(rs.next()){
				mapdata=new HashMap<String,String>();
				mapdata.put("agentID",rs.getString(1));
				//mapdata.put("distributorID",rs.getString(2));
				mapdata.put("connectionNo", rs.getString(2));
				mapdata.put("connectionOperator", rs.getString(3));
				mapdata.put("dateOfRecharge", rs.getString(4));
				mapdata.put("timeOfRecharge", rs.getString(5));
				mapdata.put("amount", rs.getString(6));
				mapdata.put("status", rs.getString(7));
				//mapdata.put("vendorTranID", rs.getString(8));

				list.add(mapdata);
			}
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchDao method getTranData");
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt !=null)
					pstmt.close();
				if(con !=null)
					con.close();
			}catch(Exception ex){
				System.out.println("Exception in TransactionSearchDao method getTranData closing connection");
				ex.printStackTrace();

			}
		}
		return list;
	}

}
