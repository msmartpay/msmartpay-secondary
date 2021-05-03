package com.distributor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public class DistributorDetailsBusinessDelegator {
	public static String activateDistributorStatus(String distributorId,String mdId){
		return DistributorDetailsDao.activateDistributorStatus(distributorId,mdId);
	}
	public static String deactivateDistributorStatus(String distributorId,String mdId){
		return DistributorDetailsDao.deactivateDistributorStatus(distributorId,mdId);
	}
	public static String DoAllActivateDistributor(String mdId){
		return DistributorDetailsDao.DoAllActivateDistributor(mdId);
	}
	public static String DoAllDeactivateDistributor(String mdId){
		return DistributorDetailsDao.DoAllDeactivateDistributor(mdId);
	}
	/*public static String getCity(String citycode)
	{
		return DistributorDetailsDao.getCity(citycode);
	}*/
	public static ArrayList getAllDistributor(String mdId) 
	{
		DistributorDetailsDao adao = new DistributorDetailsDao();
		ArrayList distributors=adao.getAllDistributor(mdId);
		return distributors;
	}
	public static ArrayList getAllDistributorActive(String mdId) 
	{
		DistributorDetailsDao adao = new DistributorDetailsDao();
		ArrayList distributors=adao.getAllDistributorActive(mdId);
		return distributors;
	}
	public static ArrayList getAllDistributorDeactive(String mdId) 
	{
		DistributorDetailsDao adao = new DistributorDetailsDao();
		ArrayList distributors=adao.getAllDistributorDeactive(mdId);
		return distributors;
	}
}
