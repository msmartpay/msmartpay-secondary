package com.masterdistributor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public class MasterDistributorBusinessDelegator {
public static String updateMasterDistributorProfile(String mdId,String firmname,String Address1,String Address2,String state,String District,String city,String PinCode){
	return MasterDistributorDao.updateMasterDistributorProfile(mdId,firmname,Address1,Address2,state,District,city,PinCode);
	}

public static HashMap getMasterDistributorInformation(String mdId) 
	{
		MasterDistributorDao adao = new MasterDistributorDao();
		HashMap mdInfo=adao.getMasterDistributorInformation(mdId);
		return mdInfo;
	}
	
	public static String getCity(String citycode)
{
return MasterDistributorDao.getCity(citycode);
}
}
