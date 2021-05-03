package com.accountstatement;

import java.sql.Connection;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class AccountStatementBusinessDelegator {  
	public static Vector accountStatementReport(Connection con,String mdId,String toDt,String fromDt,String type,String modcount,int start,int end,HttpSession session){
		return AccountStatementDao.accountStatementReport(con,mdId,toDt,fromDt,type,modcount,start,end,session);
	}

			

	public static Vector getAccStatement(Connection con,String mdId){
		return AccountStatementDao.getAccStatement(con,mdId);
	}

	public static String getAccountStatement(Connection con,String filePath,String mdid,HttpSession session,String Fromdate,String Todate)
	
	{	
		AccountStatementDao dao=new AccountStatementDao();
		String live=dao.getAccountStatement(con,filePath,mdid,session,Fromdate,Todate);
		
		return live ;
		 
	}
	public static HashMap getAdmintoMDDetails(Connection con,String transid)
	{	
		AccountStatementDao dao=new AccountStatementDao();
		HashMap ham=dao.getAdmintoMDDetails(con,transid);
		
		return ham ;		 
	}
	public static HashMap getMDtoDistDetails(Connection con,String transid)
	{
		AccountStatementDao dao=new AccountStatementDao();
		HashMap ham=dao.getMDtoDistDetails(con,transid);
		
		return ham ;		 
	}

	public static HashMap getDetailAccountAdjustment(Connection con,String transid,String userId)
	
	{
		AccountStatementDao dao=new AccountStatementDao();
		HashMap ham=dao.getDetailAccountAdjustment(con,transid,userId);
		
		return ham ;		 
	}
		
}




