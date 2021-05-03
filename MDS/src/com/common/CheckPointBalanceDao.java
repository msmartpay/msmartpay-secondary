package com.common;

import java.sql.*;

import javax.servlet.http.HttpSession;

import com.db.DBConnection;

public class CheckPointBalanceDao {
	
	
	
	    public static String getCheckpointBalance(Connection con, String distributorId) {


		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		String updateStatus="notUpdated";
	    
	    double finalBalanceAmount=00.00;
	    double balance=0.00;
	    Double finalBal=0.00;
		
		try
		 {
			  
			  con=DBConnection.getConnection();
			  String sql="select TotCash, usedcash from distributor_amount where distributor_id=?";
			  
			  
			
			  pstmt = con.prepareStatement(sql); 
		      pstmt.setString(1, distributorId); 
			  rs=pstmt.executeQuery();
			
			if(rs.next())
			{	
				
				double totalCash= rs.getDouble("TotCash");
			
				double usedCash= rs.getDouble("usedcash");
				
				 balance = totalCash-usedCash;
			}
				
			System.out.println("balance is==================="+balance);	
		  String sqlQuery="select Final_Bal_amount from  distributor_transaction_details  where id_no=(select MAX(Id_no) from distributor_transaction_details where distributor_id=? )";
				
			    pstmt = con.prepareStatement(sqlQuery); 
			    pstmt.setString(1, distributorId); 
				rs1=pstmt.executeQuery();
				
				if(rs1.next())
				{
					finalBalanceAmount=rs1.getDouble("Final_Bal_amount");
			
				}	
				System.out.println("finalBalanceAmount is==================="+finalBalanceAmount);
				
				double balanceDifference=balance-finalBalanceAmount;
			    double balanceDifferenceCheck=0.00;	
				if (balanceDifference < 0 ){
					balanceDifferenceCheck = balanceDifference * -1;
				}
				
				System.out.println("balanceDifference is==================="+balanceDifference);
				if(balanceDifferenceCheck<0.10 )
				{
			    
			    updateStatus ="valid";
				}
				
				else 
				{

					

				String updateQuery="update distributor_amount set usedcash=usedcash +? where distributor_id=?";
				System.out.println("updatequery is-----  "+updateQuery);
				pstmt=con.prepareStatement(updateQuery);
				pstmt.setDouble(1,balanceDifference);
				pstmt.setString(2,distributorId);
				int flag=pstmt.executeUpdate();
				if(flag>0)
				{
					updateStatus="updated";
				}
				else
				{
					updateStatus="notUpdated";
				}
				
			    
			
				}
			
			
			
		 }catch(Exception e)
				{
			 
				    updateStatus="notUpdated";
                    System.out.println("Exception in CheckPointDao"+e.toString());
				}
			finally
			{
				try
				{
					
					if(rs!=null)
						rs.close();

					if(rs1!=null)
						rs1.close();

					if(pstmt!=null)
						pstmt.close();

					if(con!=null)
						con.close();

				}
				catch(Exception e)
				{
					System.out.println("Exception in CheckPointDao while closing connection"+e.toString());
				}
				
			}
		return updateStatus;
	}
	    
	    
	    public static void main(String a[]){
	    	Connection con=null;
	    	CheckPointBalanceDao obj=new CheckPointBalanceDao();
	    	String status=obj.getCheckpointBalance(con, "40");
	    	
	    	System.out.println("status is========="+status);
	    	
	    	
	    	
	    }
}
