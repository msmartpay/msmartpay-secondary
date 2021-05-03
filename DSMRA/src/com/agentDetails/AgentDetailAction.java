/*
  Class Property :  This class (AgentDetailAction) is created for controlling and display active ,non active agent details .

  Created Date   : 9-Dec-2011 at 11:55 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 9-Dec-2011.
  Update By      : Bharatveer Singh

 */
package com.agentDetails;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.common.LogWriter;
import com.login.LoginDao;


public class AgentDetailAction {

	Logger logger = Logger.getLogger(AgentDetailAction.class);
	LogWriter log=new LogWriter();
	
	public JSONObject viewAgent(JSONObject requestObject) {

		JSONObject responseJson=new JSONObject();
		String param=requestObject.getString("param");
		try 
		{ 
			AgentDetailDao agentDetailDao=new AgentDetailDao();
		
			String userId=requestObject.getString("distributorId");
			
			String txnkey=requestObject.getString("txnkey");
			if(txnkey!=null){
				
				LoginDao dao=new LoginDao();
				if(dao.varifyTxnKey(Long.parseLong(userId), txnkey)){
					
					if(param.equals("AgentDetails")){

						
						String Status=requestObject.getString("Status");
						
								  
						JSONArray newAgents=agentDetailDao.getAgentDetails(userId,Status);
						if (newAgents!=null && newAgents.length()>0){
							responseJson.put("agentDetails",newAgents.toString());
							responseJson.put("message","Success");
							responseJson.put("status","0");
						}else{
							responseJson.put("message","Agent not available.");
							responseJson.put("status","1");
						}
						
						return responseJson;
					}else if("singleAgentDetail".equalsIgnoreCase(param)){
						String fullagentId=requestObject.getString("agentId");
						
						if(fullagentId!=null){
							
							responseJson=agentDetailDao.getSingleAgentDetails(fullagentId, userId);
							if(responseJson!=null){
								
								responseJson.put("message","Success");
								responseJson.put("status","0");
							}else{
								responseJson=new JSONObject();
								responseJson.put("message","Please send valid agent Id.");
								responseJson.put("status","1");
							}
							
							
						}else{
							responseJson.put("message","Please send valid agent Id.");
							responseJson.put("status","1");
						}
						
						
					}else if("changeStatusAgent".equals(param))
					{				
						
						String agentFullId = requestObject.getString("agentFullId");
						String AgentRequiredStatus=requestObject.getString("checkChangeStatus");  

						String changeStatusAgent=agentDetailDao.dochangeStatusAgent(agentFullId,AgentRequiredStatus,userId);				
						System.out.println("changeStatusAgent :"+changeStatusAgent);				
						if(changeStatusAgent.equalsIgnoreCase("Update"))
						{
							responseJson.put("message","Status updated successfully.");
							responseJson.put("status", "0");
						}
						else
						{				  
							responseJson.put("message","We are unable to update status. Please try later.");
							responseJson.put("status", "1");
							return responseJson; 
						}	 			
					}else if("updateAgent".equals(param))
					{				
						
						String agentFullId = requestObject.getString("agentFullId");
						String dateofbirth =requestObject.getString("dateofbirth");
						String companyType=requestObject.getString("CompanyType");			
						String companyName=requestObject.getString("AgencyName");
						String panNo=requestObject.getString("PanNo");	
						String officeAddress1=requestObject.getString("Address");
						String officeState=requestObject.getString("State");
						String officeDistrict=requestObject.getString("District");
						String officeCity=requestObject.getString("City");					
						String officePincode=requestObject.getString("Pincode");
						

						boolean changeStatusAgent=agentDetailDao.updateAgent(agentFullId,dateofbirth,companyType,companyName,panNo,officeAddress1
								,officeState,officeDistrict,officeCity,officePincode,userId);				
						System.out.println("changeStatusAgent :"+changeStatusAgent);				
						if(changeStatusAgent)
						{
							responseJson.put("message","Details updated successfully.");
							responseJson.put("status", "0");
							return responseJson;
						}
						else
						{				  
							responseJson.put("message","We are unable to update details. Please try later.");
							responseJson.put("status", "1");
							return responseJson; 
						}	 			
					}  
					
					
				}else{
					responseJson.put("message", "Invalid request.");
					responseJson.put("status", "1");
				}
			}else{
				responseJson.put("message", "Invalid request.");
				responseJson.put("status", "1");
			}
			

			  
		}catch(Exception ex){
			ex.printStackTrace();
			String message = "Due to some technical issue we are unable to proceed your request";
			responseJson.put("message", message);
			responseJson.put("status", "1");
			return responseJson;
		}
		return responseJson;    
	}	

}
