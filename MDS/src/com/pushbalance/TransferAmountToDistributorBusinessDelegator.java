package com.pushbalance;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





public class TransferAmountToDistributorBusinessDelegator {

	public static HashMap getDistributorDetails(String distId,String mdId){
return TransferAmountToDistributorDao.getDistributorDetails(distId,mdId);
}
public static String getAmountStatus(Connection con,double amount,String mdId){
return TransferAmountToDistributorDao.getAmountStatus(con,amount,mdId);
}
public static String transferAmount(Connection con,String mdId,String distributor_id,double amount,String remark,String mode,String ip_address,HttpSession session ){
return TransferAmountToDistributorDao.transferAmount(con,mdId,distributor_id,amount,remark,mode,ip_address,session);
}
             public static String  checkDistIdStatus(Connection con,String mdId,String dist_id,HttpSession session){
			  return TransferAmountToDistributorDao.checkDistIdStatus(con,mdId,dist_id,session);
}
}
