package com.distributorregistration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpSession;

import com.distributorregistration.DistributorRegistrationDao;


public class DistributorRegistrationBussinessDelegator {
	public static String chkPanNo(String panNo,String mdId)
{
return DistributorRegistrationDao.chkPanNo(panNo,mdId);
}
public static String getRandomString(int length) {
 return DistributorRegistrationDao.getRandomString(length);


}
/*
public static String getCity(String citycode)
{
return DistributorRegistrationDao.getCity(citycode);
}
*/
/*
public static HashMap getMasterDistributorServiceDetails(String mdId){
return DistributorRegistrationDao.getMasterDistributorServiceDetails(mdId);

}
*/
	

	public  static HashMap getAgentId(Connection con,String city,String state) {
	return DistributorRegistrationDao.getAgentId(con,city,state);
	}

			
	public static HashMap getDistributorId(Connection con,String city, String state) {
				DistributorRegistrationDao adao = new DistributorRegistrationDao();	
		return adao.getDistributorId(con,city,state);
	}
			
			
}
