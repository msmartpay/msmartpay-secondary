package com.controlPanel.margin.agentShare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AgentShareAction extends ActionSupport implements ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private HttpSession session;
	public String execute () throws Exception
	{
		AgentSharedao daoObj=new AgentSharedao();
		session=request.getSession();
		try
		{
			response=ServletActionContext.getResponse();
			String userId=(String)session.getAttribute("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}			
			String param=request.getParameter("param");
			System.out.println("MdsWlMarginSetupAction param is "+param);
			if(param.equalsIgnoreCase("getAgentMargin"))
			{
				if(session.getAttribute("ServiceList")==null)
					session.setAttribute("ServiceList", daoObj.fetchMarginServiceList());
				return "AgMargin";
			}else if (param.equalsIgnoreCase("viewAgentShare")){

				String searchId="",usertypeId="";
				searchId=request.getParameter("userId");
				String usertype=request.getParameter("usertype");
				if("ByDsId".equalsIgnoreCase(usertype)) {
					usertypeId=daoObj.getAgentIdByDSId(searchId);
					System.out.println("usertypeId : "+usertypeId);
					if(usertypeId==null)
						usertypeId="2001";
				}else if("ByMdId".equalsIgnoreCase(usertype)) {
					usertypeId=daoObj.getAgentIdByMDSId(searchId);
					System.out.println("usertypeId : "+usertypeId);
					if(usertypeId==null)
						usertypeId="2001";
				}else {
					searchId=searchId.toUpperCase().replaceAll("AG", "");
					usertypeId=searchId;
				}
				ArrayList<HashMap<String, Object>> list=null;
				
				String operator=request.getParameter("operator");
				List detils=daoObj.fetchAgentMargin(Long.parseLong(usertypeId),operator);
				HashMap<String, Object> marginDetails=null;
				if(detils!=null && detils.size()>0){
					list=new ArrayList<HashMap<String, Object>>();
					for(int i=0 ;i<detils.size();i++) {
						
						Object[] obj=(Object[])detils.get(i);
						marginDetails=new HashMap<>();
						marginDetails.put("Service", obj[3]);
						marginDetails.put("Operator", obj[4]);
						marginDetails.put("CommissionType", obj[5]);
						marginDetails.put("Commission", obj[6]);
						marginDetails.put("ChargeType", obj[7]);
						marginDetails.put("Charge", obj[8]);
						marginDetails.put("StartRange", obj[9]);
						marginDetails.put("EndRange", obj[10]);
						list.add(marginDetails);
					}
					request.setAttribute("MarginDetails", list);
					request.setAttribute("usertype", usertype);
					request.setAttribute("searchId", searchId);
				}

				return "AgMargin";

			}else if (param.equalsIgnoreCase("UpdateAgentShare"))
			{
				
				String loginType=(String)session.getAttribute("loginType");
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin") || loginType.equalsIgnoreCase("PortalAdmin"))
				{
					
					String usertype=request.getParameter("usertype");
					String[] checkOperator=(String[])request.getParameterValues("checkpartial");
					int totpass=checkOperator.length;
					
					
					for(int i=0;i<totpass;i++)
					{
						String value=checkOperator[i];
						daoObj.updateAgentChargeShareNew(usertype,request.getParameter("searchId"+value)
								,request.getParameter("Operator"+value),
								request.getParameter("ChargeType"+value),
								Double.parseDouble(request.getParameter("Charge"+value)),
								request.getParameter("CommissionType"+value),
								Double.parseDouble(request.getParameter("Commission"+value)),
								
								Double.parseDouble(request.getParameter("StartRange"+value)),
								Double.parseDouble(request.getParameter("EndRange"+value))
								);
						
					}
					//System.out.println("we are into 1 if");

					request.setAttribute("message", "Proceess has been completed.");

					return "AgMargin";
				}
				else
				{
					request.setAttribute("message","You are Not Authorized for this Service");
					return "AgMargin";
				}		
			}
			/*
			if(param.equalsIgnoreCase("getAgentMargin"))
			{
				return "AgMargin";
			}else if (param.equalsIgnoreCase("viewAgentShare")){
				
				String agentId=request.getParameter("agentId");
				String operator=request.getParameter("operator");
				if(!"".equalsIgnoreCase(agentId)){
					List detils=daoObj.fetchAgentMarggin(Long.parseLong(agentId),operator);
					HashMap<String, Object> marginDetails=null;
					if(detils!=null && detils.size()>0){
						Object[] obj=(Object[])detils.get(0);
						marginDetails=new HashMap<>();
						marginDetails.put("MDID", obj[0]);
						marginDetails.put("DSID", obj[1]);
						marginDetails.put("AGENTID", obj[2]);
						marginDetails.put("Service", obj[3]);
						marginDetails.put("Operator", obj[4]);
						marginDetails.put("ChargeMark", obj[5]);
						marginDetails.put("Charge", obj[6]);
						marginDetails.put("CommissionMark", obj[7]);
						marginDetails.put("Commission", obj[8]);
						marginDetails.put("GST", obj[9]);
						marginDetails.put("net", obj[10]);
						marginDetails.put("KycCommissionMark", obj[11]);
						marginDetails.put("KycCommission", obj[12]);
						marginDetails.put("KycChargeMark", obj[13]);
						marginDetails.put("KycCharge", obj[14]);
						System.out.println(marginDetails);
						request.setAttribute("MarginDetails", marginDetails);
					}
					
				}else{
					request.setAttribute("message", "Details not found");
				}
				return "AgMargin";
				
			}else if (param.equalsIgnoreCase("UpdateAgentShare"))
			{
				String loginType=(String)session.get("loginType");
				String result="";
				String usertype=request.getParameter("usertype");
				String EnterUserId=request.getParameter("EnterUserId");
				String operator=request.getParameter("operator");
				String shareType=request.getParameter("shareType");
				
				String shareMark=request.getParameter("shareMark");
				String share=request.getParameter("share");
				
				String kycShareMark=request.getParameter("kycShareMark");
				String KycShare=request.getParameter("KycShare");
				
				if("".equalsIgnoreCase(shareMark))
					shareMark="0";
				if("".equalsIgnoreCase(KycShare))
					KycShare="0";

				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin") || loginType.equalsIgnoreCase("PortalAdmin"))
				{
					//System.out.println("we are into 1 if");

					if("Surcharge".equalsIgnoreCase(shareType)){
						result=daoObj.updateAgentChargeShareNew(usertype,EnterUserId,operator, shareMark, share,kycShareMark,KycShare);
					}else{
						result=daoObj.updateAgentCommShareNew(usertype,EnterUserId,operator, shareMark, share,kycShareMark,KycShare);
					}

					if(result.equalsIgnoreCase("Success")){
						request.setAttribute("message", "Proceess has been completed.");

					}else{
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}
					
					return "AgMargin";
				}
				else
				{
					request.setAttribute("message","You are Not Authorized for this Service");
					return "AgMargin";
				}		
			}*/
			
					
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in MdsWlMarginSetupAction");
			e.printStackTrace();
			return "err";
		}
		return "err";
	}
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
