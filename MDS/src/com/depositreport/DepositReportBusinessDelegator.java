
package com.depositreport;

import java.sql.Connection;
import java.util.Vector;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class DepositReportBusinessDelegator {
	
private static DepositReportDao reportDao = new DepositReportDao();
public static String getAgentIdStatus(Connection con,String agentid,String dist_id){
return DepositReportDao.getAgentIdStatus(con,agentid,dist_id);
}

 public static Vector getAgentDepositReport(Connection con,String dist_id,String agentid,String todt,String fromdt)
	{
	return DepositReportDao.getAgentDepositReport(con,dist_id,agentid,todt,fromdt);

	}
public static Vector getAllAgentDepositReport(Connection con,String dist_id,String todt,String fromdt)
	{
	return DepositReportDao.getAllAgentDepositReport(con,dist_id,todt,fromdt);

	}
public static Vector getDistributorDepositReport(Connection con,String dist_id,String todt,String fromdt){
	return DepositReportDao.getDistributorDepositReport(con,dist_id,todt,fromdt);
	}

 
 
}