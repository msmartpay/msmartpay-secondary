package com.disttributordetails;


	import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.common.LogWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
   
    @SuppressWarnings("serial")
    public class DistributorProfileAction extends ActionSupport implements ServletRequestAware  {
			 
    	private HttpServletRequest request;
    	//private HttpServletResponse response;
    	Logger logger = Logger.getLogger(DistributorProfileAction.class);
    	LogWriter log=new LogWriter();			
    	@SuppressWarnings("unchecked")
    	public String execute() throws Exception { 
    		try 
    		{ 
    			Map session = ActionContext.getContext().getSession();
    			String param=(String)request.getParameter("param");
    			log.print("param value in DistributorProfileAction is "+param, logger);
    			
    			String userId=(String)session.get("distributorId");
    			if(userId==null){
    				String  message2="Your Login session has Expired. Please Login to Continue.";
    				request.setAttribute("Loginmessage",message2);
    				return "sessionexpired"; 
    			}				  
    			DistributorProfileDao DistributorProfileDao=new DistributorProfileDao();
    			if(param.equals("DisProfile"))					  
    			{
    				HashMap<String,Object> DistributorProfile=DistributorProfileDao.getDSProfile(userId);
    				if(DistributorProfile.size()!=0)
    				{
    					request.setAttribute("DistributorProfile",DistributorProfile);
    					return "profile";				  
    				}
    				else
    				{
    					String  message="Data is Not Available.";
    					request.setAttribute("message",message);
    					return "profile"; 
    				}					  
    			}			  
				  //---------Method to get required data for distributor and displayed on profile update panel
				  
    			if("UpdateDisProfile".equalsIgnoreCase(param))
    			{
    				String DistributorId=(String)request.getParameter("DistributorId");
    				HashMap<String,Object> UpdateProfile=DistributorProfileDao.getUpdateData(DistributorId);					 
    				if(UpdateProfile.size()!=0)
    				{
    					session.put("UpdateProfile",UpdateProfile);
    					return "upadteprofile";
    				}
    				else
    				{
    					String  message="Data is Not Available.";
    					request.setAttribute("message",message);
    					return "upadteprofile"; 
    				}
    			}				  
    			if("DoUpdateDisProfile".equalsIgnoreCase(param))
    			{		
    				String agencyName=(String)request.getParameter("agencyName");
    				String Address=(String)request.getParameter("Address1");
    				String Address2=(String)request.getParameter("Address2");
    				String country=(String)request.getParameter("country");
    				String state=(String)request.getParameter("state");
    				String District=(String)request.getParameter("District");
    				String city=(String)request.getParameter("city");     			 
    				String officePinCode=(String)request.getParameter("officePinCode");    			   
    				
    				String doUpdate=DistributorProfileDao.doUpdate(userId,Address,Address2,country,state,District,city,agencyName,officePinCode);
    				if("Updated".equalsIgnoreCase(doUpdate))
    				{
    					HashMap<String,Object> DistributorProfile=DistributorProfileDao.getDSProfile(userId);
    					if(DistributorProfile.size()!=0)
    					{							  
    						String  message="Process has been completed Successfully.";
    						request.setAttribute("message",message);
    						request.setAttribute("DistributorProfile",DistributorProfile);
    						return "profile";				  
    					}
    					else
    					{
    						String  message="Data is Not Available.";
    						request.setAttribute("message",message);
    						return "profile"; 
    					}					   
    				}
    				else
    				{	
    					String  message="Process aborted due to Technical Failure.";
    					request.setAttribute("message", message);
    					return "profile";
    				}			    
    			}			  
    		}			  
    		catch(Exception ex){
    			ex.printStackTrace();
    			request.setAttribute("message", "Due to some technical issue we are unable to proceed your request");
    			return "error";
    		}
    		return "error";		      
    	}			
    	@Override
    	public void setServletRequest(HttpServletRequest httpServletRequest) {
    		this.request = httpServletRequest;
    	}

			
    	/*public void setServletResponse(HttpServletResponse httpServletResponse) {
				 this.response =httpServletResponse;
				
			}*/
			
			
			 
    }
		 