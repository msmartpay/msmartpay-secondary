package com.depositrequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;


public class DistributorDepositRequestBusinessDelegator {
	public static String getMDBalance(String mdId,HttpSession session){
		return DistributorDepositRequestDao.getMDBalance(mdId,session);
	}

	public static ArrayList getDistributorDepositRequestList(String mdId){

		return DistributorDepositRequestDao.getDistributorDepositRequestList(mdId);
	}
	
	public static ArrayList<HashMap<String,String>> collectionBank(long mdId){

		return DistributorDepositRequestDao.collectionBank(mdId);
	}

	public static ArrayList getMdJournal(String mdId){

		return DistributorDepositRequestDao.getMdJournal(mdId);
	}

	public static HashMap getDistributorDetails(String distributorId,String tranId,String date,String time){
		return DistributorDepositRequestDao.getDistributorDetails(distributorId,tranId,date,time);
	}


	public static String updateDistributorAmount(Connection con, String Dist_id,String agentid, String date,
			String time, String status,String AgntAmt,double bankcharges,String amount,String tranId ,String clientIp,HttpSession session,String bankChargeRemark) {

		DistributorDepositRequestDao adao = new DistributorDepositRequestDao();
		return adao.updateDistributorAmount(con,Dist_id,agentid,date,time,status,AgntAmt,bankcharges,amount,tranId,clientIp,session, bankChargeRemark);
	}

	public static String updateMasterDistributorAmount(Connection con, HttpSession session,String agentid,String Dist_id,
			String date, String time,  String status,String statusselected,String amount,String tranId,double bankcharges,String clientIp,double TransaferAmt,String bankChargeRemark) throws SQLException {		
		DistributorDepositRequestDao adao = new DistributorDepositRequestDao();
		return adao.updateMasterDistributorAmount(con,session,agentid,Dist_id,date,time,status,statusselected,amount,tranId,bankcharges,clientIp,TransaferAmt,bankChargeRemark);
	}



	/*public static ArrayList getDepoistRequestDetails(Connection con,String Dist_id) {

		DistributorDepositRequestDao adao = new DistributorDepositRequestDao();
		return adao.getDepoistRequestDetails(con,Dist_id);
	}*/

	/*	public static HashMap getAgentProfile(Connection con, String agen_id) 
	{
		DistributorDepositRequestDao adao = new DistributorDepositRequestDao();
		HashMap information=adao.getAgentProfile(con,agen_id);
		return information;
	}*/
	public static String chkTranId(Connection con,String tranId,HttpSession session)
	{
		DistributorDepositRequestDao adao=new DistributorDepositRequestDao();
		String status=adao.chkTranId(con,tranId,session);
		return status;
	}

}
