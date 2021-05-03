package com.agent;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public class AgentDetailsBusinessDelegator {

	public static ArrayList getAllDistributorId(String mdId){
	return AgentDetailsDao.getAllDistributorId(mdId);
	}
	public static String activateAgentStatus(String agent_id,String mdId){
		return AgentDetailsDao.activateAgentStatus(agent_id,mdId);
	}
		public static String deactivateAgentStatus(String agent_id,String mdId){
		 return AgentDetailsDao.deactivateAgentStatus(agent_id,mdId);
	}
		public static String DoAllActivateAgent(String MdId,String DsID){
			return AgentDetailsDao.DoAllActivateAgent(MdId,DsID);
		}
		public static String DoAllDeactivateAgent(String MdId,String DsId){
			return AgentDetailsDao.DoAllDeactivateAgent(MdId,DsId);
		}
	/*public static String updateDistributorProfile(String mdId,String distributorId,String resAddress1,String resAddress2,String country,String state,String resCity,String pincode,String landmark,String resMobileNo,String resAlternateNo,String resPhoneNo,String resFaxNo){
	return AgentDetailsDao.updateDistributorProfile(mdId,distributorId,resAddress1,resAddress2,country,state,resCity,pincode,landmark,resMobileNo,resAlternateNo,resPhoneNo,resFaxNo);
	}
	*/
/*	public static String getCity(String citycode)
{
return AgentDetailsDao.getCity(citycode);
}*/

	public static ArrayList getAllAgent(String mdId) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		ArrayList distributors=adao.getAllAgent(mdId);
		return distributors;
	}
		public static ArrayList getAllAgentActive(String mdId,String distributorId,String typ,String page,HttpSession session ) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		ArrayList distributors=adao.getAllAgentActive(mdId,distributorId,typ,page,session);
		return distributors;
	}
	public static ArrayList getDistributorAllAgent(String mdId,String distributorId,String typ, String modcount,int start,int end,HttpSession session ) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		ArrayList distributors=adao.getDistributorAllAgent(mdId,distributorId,typ,modcount,start,end,session);
		return distributors;
	}
	public static ArrayList getAllAgentDeactive(String mdId,String distributorId,String typ, String page,HttpSession session) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		ArrayList distributors=adao.getAllAgentDeactive(mdId,distributorId,typ,page,session);
		return distributors;
	}
public static ArrayList getAllDistributorDeactive(String mdId) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		ArrayList distributors=adao.getAllDistributorDeactive(mdId);
		return distributors;
	}
		public static HashMap getDistributorInformation(String distributorId) 
	{
		AgentDetailsDao adao = new AgentDetailsDao();
		HashMap distributors=adao.getDistributorInformation(distributorId);
		return distributors;
	}
		public static ArrayList getActiveAgents(String distributorId,String page,String ClientFlag) 
		{
			AgentDetailsDao adao = new AgentDetailsDao();
			ArrayList distributors=adao.getActiveAgents(distributorId,page,ClientFlag);
			return distributors;
		}
		public static ArrayList getDeactiveAgents(String distributorId,String page,String ClientFlag) 
		{
			AgentDetailsDao adao = new AgentDetailsDao();
			ArrayList distributors=adao.getDeactiveAgents(distributorId,page,ClientFlag);
			return distributors;
		}
		public static ArrayList getAllAgents(String distributorId,String page,String ClientFlag) 
		{
			AgentDetailsDao adao = new AgentDetailsDao();
			ArrayList distributors=adao.getAllAgents(distributorId,page,ClientFlag);
			return distributors;
		}
}
