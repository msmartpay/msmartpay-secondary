package com.msmart.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.msmart.service.PropertyFile;
import com.msmart.util.UtilityP;

import net.sf.json.JSONObject;

@Path("ezypay")
public class PlanController {

	
	Logger logger = Logger.getLogger(PlanController.class);

	@Context HttpServletRequest request;

	
	@POST
	@Consumes("application/json")
	@Path("/plan")
	public String  apiCall(JSONObject obj)
	{

		try {
			
			String client=obj.getString("client");
			String agent_id=obj.getString("agent_id");
			
			String data="",url="",baseUrl="";
			
			String type=obj.getString("type");
			String operator=obj.getString("operator");
			String mobile=obj.getString("mobile");
			
			if("mobile".equalsIgnoreCase(type)) {
				String circle=obj.getString("cricle");
				circle=circle.replace(" ", "%20");
				
				if("".equalsIgnoreCase(mobile)) {
					
					if("BSNL".equalsIgnoreCase(operator) || "BSNLRECH".equalsIgnoreCase(operator))
						operator="Bsnl";
					else if("TATADOCOMO".equalsIgnoreCase(operator) || "TATADOCOMOSP".equalsIgnoreCase(operator))
						operator="Tata Docomo";
					else if("Jio Recharge".equalsIgnoreCase(operator))
						operator="Jio";
					else if("BSNL".equalsIgnoreCase(operator) || "BSNLRECH".equalsIgnoreCase(operator))
						operator="Bsnl";
					else if("MTNLV".equalsIgnoreCase(operator) || "MTNLT".equalsIgnoreCase(operator))
						operator="MTNL";
					
					baseUrl="https://www.mplan.in/api/plans.php?apikey="+PropertyFile.PLAN_API_KEY;
					
				}else {
					
					if("BSNL".equalsIgnoreCase(operator) || "BSNLRECH".equalsIgnoreCase(operator))
						operator="Bsnl";
					else if("TATADOCOMO".equalsIgnoreCase(operator) || "TATADOCOMOSP".equalsIgnoreCase(operator))
						operator="Tata Docomo";
					else if("Jio Recharge".equalsIgnoreCase(operator))
						operator="Jio";
					else if("BSNL".equalsIgnoreCase(operator) || "BSNLRECH".equalsIgnoreCase(operator))
						operator="Bsnl";
					else if("MTNLV".equalsIgnoreCase(operator) || "MTNLT".equalsIgnoreCase(operator))
						operator="MTNL";
					
					baseUrl="https://www.mplan.in/api/plans.php?apikey="+PropertyFile.PLAN_API_KEY+"&offer=roffer&tel="+mobile;
					
				}
				operator=URLEncoder.encode(operator, "UTF-8");
				data="&cricle="+circle+"&operator="+operator;
				
			}else if("dth".equalsIgnoreCase(type)) {
				if("".equalsIgnoreCase(mobile)) {
					
					if(operator!=null && operator.contains("Airtel"))
						operator="Airtel dth";
					else if(operator!=null && operator.contains("Dish"))
						operator="Dish TV";
					else if(operator!=null && operator.contains("Tata"))
						operator="Tata Sky";
					else if(operator!=null && operator.contains("Sun"))
						operator="Sun Direct";
					else if(operator!=null && operator.contains("Videocon"))
						operator="Videocon";
					
					baseUrl="https://www.mplan.in/api/dth_plans.php?apikey="+PropertyFile.PLAN_API_KEY;
				}else {
					baseUrl="https://www.mplan.in/api/dth_plans.php?apikey="+PropertyFile.PLAN_API_KEY+"&offer=roffer&tel="+mobile;
				}
				operator=URLEncoder.encode(operator, "UTF-8");
				data="&operator="+operator;
			}else if("dthInfo".equalsIgnoreCase(type)) {
				if(operator!=null && operator.contains("Airtel"))
					operator="Airteldth";
				else if(operator!=null && operator.contains("Dish"))
					operator="Dishtv";
				else if(operator!=null && operator.contains("Tata"))
					operator="TataSky";
				else if(operator!=null && operator.contains("Sun"))
					operator="Sundirect";
				else if(operator!=null && operator.contains("Videocon"))
					operator="Videocon";
				
				baseUrl="https://www.mplan.in/api/Dthinfo.php?apikey="+PropertyFile.PLAN_API_KEY+"&offer=roffer&tel="+mobile;
				
				operator=URLEncoder.encode(operator, "UTF-8");
				data="&operator="+operator;
			}
			
			url=baseUrl+data;
			
			
			String ip=request.getRemoteAddr();
			logger.info("ip >>>>>>>>>> : "+url);
			return UtilityP.plancall(url);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
