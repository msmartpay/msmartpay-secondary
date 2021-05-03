package com.dthConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import org.apache.log4j.Logger;

import com.db.DBConnection;


final class DthConnectionDao{

	Logger logger=Logger.getLogger(DthConnectionDao.class);
	
    public  ArrayList<String> getProductDescription(String location,String product){

	ArrayList<String> result=null;
	String product_desc="";
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	try{
			
		result=new ArrayList<String>();
		con = DBConnection.getConnection();
			
		String sql="select  product_description from DthConnectionOperator where location=? and product=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,location);
		pstmt.setString(2,product);
		rs=pstmt.executeQuery();
	
	     	while(rs.next()){
	     		product_desc=rs.getString("product_description");
	     		result.add(product_desc);
	     	}
			
		}catch (Exception ex) {
		logger.info("Exception in DthConnectionDao (getProductDescription) ");
		ex.printStackTrace();
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();			
				}catch (Exception e) {
				
					logger.info("Exception in DthConnectionDao (getProductDescription) while closing connection in getProductDescription");
					e.printStackTrace();
				}		
			
			}	
			return result;

		
	}

	public  ArrayList<String> getProductPrice(String location,String product,String ProductDescription){

		ArrayList<String> result1=null;
		String price="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
			
		try{
				
			result1=new ArrayList<String>();
			con = DBConnection.getConnection();
				
			String sql="select  price from DthConnectionOperator where location=? and product=? and product_description=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, location);
			pstmt.setString(2, product);
			pstmt.setString(3, ProductDescription);
			rs=pstmt.executeQuery();
	  
			while(rs.next()){
						price=rs.getString("price");
						result1.add(price);
			}
				
				
				
		}catch (Exception ex) {
				logger.info("Exception in DthConnectionDao (getProductPrice) ");
				ex.printStackTrace();
		}finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();			
				}catch (Exception e) {
				
					logger.info("Exception in DthConnectionDao (getProductPrice) while closing connection in getProductPrice");
					e.printStackTrace();
				}		
				
		}	
		return result1;

			
	}
	
	final String getPrice(String location,String product,String ProductDescription){
		
		String price1="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try{
			con = DBConnection.getConnection();
			String sql="select  price from DthConnectionOperator where location=? and product=? and product_description=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, location);
			pstmt.setString(2, product);
			pstmt.setString(3, ProductDescription);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
					price1=rs.getString("price");
				
			}
			
			
			
		}catch (Exception ex) {
			logger.info("Exception in DthConnectionDao (getPrice) ");
			ex.printStackTrace();
			
		}finally{
			try{
					if(rs!=null)
						rs.close();
					if(con!=null)
						con.close();	
					if(pstmt!=null)
						pstmt.close();
				
				}catch (Exception e) {
					logger.info("Exception in DthConnectionDao (getPrice) while closing connection in getPrice");
					e.printStackTrace();
				}			
			
		}	
			return price1;

		
	}
		
	final HashMap<String,String> getStatusDthConnection(String agentId,String price,String location,String product,String prod_desc){
	 
		String status="NA";
		String balance="NA";
		HashMap <String,String> hm=new HashMap<String,String>();
		Connection con = null;
		CallableStatement cstmt=null;
		
		try{
			con=DBConnection.getConnection();
			cstmt=con.prepareCall("{call Dth_Connection_Validation(?,?,?,?,?,?,?)}");
			cstmt.setString(1,agentId);
			cstmt.setString(2,price);
			cstmt.setString(3,location);	
			cstmt.setString(4,product);
			cstmt.setString(5,prod_desc);
			cstmt.registerOutParameter(6,java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(7,java.sql.Types.VARCHAR);
			cstmt.execute(); 
	     
			status=cstmt.getString(6);
			balance=cstmt.getString(7);
	     
			hm.put("status", status);
			hm.put("balance", balance);
		 
		}catch(Exception ex)
		{
			logger.info("Exception in DthConnectionDao (getStatusDthConnection) ");
			ex.printStackTrace();
		}finally
		{
			try{
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				logger.info("Exception in DthConnectionDao (getStatusDthConnection) while closing connection in getStatusDthConnection");
				e.printStackTrace(); 
			}
		}
		return hm;

	}

	final HashMap<String,String> getKitDetails(String location,String product,String ProductDescription) {

		String kitPrice="";
		String KitActivationPrice="";
		String kitCustPrice="";
		String kitCustActivationPrice="";
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		HashMap <String,String> KitPrice=new HashMap<String,String>();
		try
		{
			con=DBConnection.getConnection();
			String selectprice="SELECT Ag_kit_price,Ag_kit_activation_charge,kit_price,kit_activation_charge FROM DthConnectionOperator where location=? and product=? and product_description=?";
			pstmt=con.prepareStatement(selectprice);
			pstmt.setString(1, location);
			pstmt.setString(2, product);
			pstmt.setString(3, ProductDescription);
		
			rs=pstmt.executeQuery();
			while(rs.next()){
				kitPrice=rs.getString(1);
		        KitActivationPrice=rs.getString(2);
		        kitCustPrice=rs.getString(3);
		        kitCustActivationPrice=rs.getString(4);              	
			} 
		     			
				KitPrice.put("netkitvalue",kitPrice);	
				KitPrice.put("netactivationvalue",KitActivationPrice);	
				KitPrice.put("netkitCustvalue",kitCustPrice);
				KitPrice.put("netActivationCustvalue",kitCustActivationPrice);
				
			}catch(Exception ex)
			{
			
				logger.info("Exception in DthConnectionDao (getKitDetails) ");
				ex.printStackTrace();
		
			}finally
			{
				try
				{
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(con!=null)
						con.close();
			
				}catch(Exception e)
				{
					logger.info("Exception in DthConnectionDao (getKitDetails) while closing connection in getKitDetails");
					e.printStackTrace(); 
				}	
		
			}	
	
			return KitPrice;
	}

	final String createTransactionID(){
    
		String AgentTranID="";
		try
		{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date=new Date();
			String tranID=sdf.format(date);
			int n = 4;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			AgentTranID=tranID+ran;
			
		}catch(Exception e)
			{
				logger.info("Execption in DthConnectionDao method (createTransactionID)");
			}
		return AgentTranID;
	}

	public String insertDetails(String userId,String Loc,String Prod,String Desc,double kitvalue,double activationcharge,double netMargin,String custname,String Addr,String statename,String cityname,String pincd,String mobno,String servicename,String TranId1,String ipAdd,String TranNo1,String TranId2,String TranNo2,double CustKitPrice,double CustActivationPrice){
	
		Connection con=null;
		CallableStatement cstmt=null;
		ResultSet rs=null;
		String queryStatus="";
		try{	
			con=DBConnection.getConnection();
	
			cstmt=con.prepareCall("{call Insert_Dth_Connection_Request(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1,userId);
			cstmt.setString(2,Loc);
			cstmt.setString(3,Prod);
			cstmt.setString(4,Desc);
			cstmt.setDouble(5,kitvalue);
			cstmt.setDouble(6,activationcharge);
			cstmt.setDouble(7,netMargin);
			cstmt.setString(8,custname);
			cstmt.setString(9,Addr);
			cstmt.setString(10,statename);
			cstmt.setString(11,cityname);
			cstmt.setString(12,pincd);
			cstmt.setString(13,mobno);
			cstmt.setString(14,servicename);
			cstmt.setString(15,TranId1);
			cstmt.setString(16,ipAdd);
			cstmt.setString(17,TranNo1);
			cstmt.setString(18,TranId2);
			cstmt.setString(19,TranId1);
			cstmt.setDouble(20,CustKitPrice);
			cstmt.setDouble(21,CustActivationPrice);
			cstmt.registerOutParameter(22, java.sql.Types.VARCHAR);
			
           cstmt.execute();
           queryStatus = cstmt.getString(22);
	
		}catch(Exception ex)
		{
			logger.info("Exception in DthConnectionDao (insertDetails) ");
			ex.printStackTrace();
		}finally
		{
			try
			{
				if(rs!=null)
					rs.close();
		
				if(con!=null)
					con.close();
		
			}
			catch(Exception e)
			{
				logger.info("Exception in DthConnectionDao (insertDetails) while closing connection in insertDetails");
				e.printStackTrace();
			}
	
		}

		return queryStatus;
	}

	

	}
	
	