package com.activity.pgtbrequest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.utility.UtilityP;

public class PGTBRequestDao {
	
	private static Logger log = Logger.getLogger(PGTBRequestDao.class);
	
	public ArrayList getpgTBDetails(String tranId,String fromdate,String todate){
		
		Session session=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> mapdata=null;
		try{
			session=HibernateSession.getSessionFactory().openSession(); 
			con=session.connection();
			if(tranId!=null && tranId!=""){
				sql="select agent_id,date,amount,mobile_no,txn_id,txn_status from pg_return_response_details where txn_id='"+tranId+"'";
				
			}else{
				sql="select agent_id,date,amount,mobile_no,txn_id,txn_status from pg_return_response_details where  convert(varchar(10),date,120) between '"+fromdate+"' and '"+todate+"' order by date desc";
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				mapdata=new HashMap<String,String>();
				mapdata.put("agentId",rs.getString(1));
				mapdata.put("date",rs.getString(2));
				mapdata.put("amount",rs.getString(3));
				mapdata.put("mobile",rs.getString(4));
				mapdata.put("tranId",rs.getString(5));
				mapdata.put("tranStatus",rs.getString(6));
				list.add(mapdata);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			try{
			if(rs!=null)
				rs.close();
			if(con!=null)
				con.close();
			if(session!=null)
				session.close();
			}catch (Exception e) {
				log.error("Exception in PGTBRequestDao class method (getpgTBDetails) while closing connection");
				
			}
		}
		
		
		return list;
	}
	public String creditPGTBAmount(String service,String tranId,String amount,String ipAddress,String operator){
		
		Session session=null;
		CallableStatement cstmt=null;
		Connection con=null;
		Transaction txn=null;
		String result="error";
		try{
			session=HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			con=session.connection();
			cstmt=con.prepareCall("{call Mobile_App_AddMoney_Credit_B2C(?,?,?,?,?,?)}");
			cstmt.setString(1,service);
			cstmt.setString(2,tranId);
			cstmt.setDouble(3,Double.parseDouble(amount));
			cstmt.setString(4,"Mannual");
			cstmt.setString(5,ipAddress);
			cstmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			cstmt.execute();
			result=cstmt.getString(6);
			txn.commit();
			
		}catch (Exception ex) {
			ex.printStackTrace();
			} finally {
				try {
					if (cstmt != null)
						cstmt.close();
					if (con != null)
						con.close();
					if(session!=null)
						session.close();
				} catch (Exception e) {
					log.error("Exception in PGTBRequestDao class method (creditPGTBAmount) while closing connection");
							
				}
			}

		return result;
	}
	public String updatePGTBStatus(String tranId,String action){
		
		Session session=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		String result="";
		String sql=null;
		int count=0;
		Transaction txn=null;
		try{
			session=HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			con=session.connection();
			sql="update pg_return_response_details set txn_status=? where txn_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,action);
			pstmt.setString(2,tranId);
			count=pstmt.executeUpdate();
			txn.commit();
			if(count>0)
				result="success";
			else
				result="error";
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			try {
				
				if (con != null)
					con.close();
				if(session!=null)
					session.close();
			} catch (Exception e) {
				log.error("Exception in PGTBRequestDao class method (updatePGTBStatus) while closing connection");
						
			}
		}
		
		
		return result;
	}
	

}
