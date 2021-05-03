package com.activity.admin.moveEntity;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



public class MoveEntityAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static Logger log = Logger.getLogger(MoveEntityAction.class);

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	private MoveEnityDao mventydao=new MoveEnityDao();

	public String execute() throws Exception{
		
		
		session=ActionContext.getContext().getSession();
		try
		{
			response=ServletActionContext.getResponse();
			
			if(session.get("userId")==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}	
			String userId=(String)session.get("userId");
			
			String val=request.getParameter("param");
			if(val.equals("moveentity"))
			{
				return "moveentityjsp";
			}else if(val.equals("moveentityds"))
			{
				return "moveentityjspds";
			}else if(val.equals("submitentity"))
			{   
				String dsid=request.getParameter("distributorId");
				String Agentid=request.getParameter("agentId");
				//System.out.println(Agentid);
				String status="failure";
				//System.out.println("distributor id"+dsid);
				if(Agentid.contains(","))
				{

					String ids[]=Agentid.split(",");
					//System.out.println("----"+ids.length);
					for(int i=0;i<ids.length;i++)
					{

						//System.out.println("agent id="+ids[i]);
						//System.out.println("move entity");
						status=mventydao.movedistributoragent(dsid,ids[i].trim(),userId);
					}

				}
				else
				{
					//System.out.println("in else");

					status=mventydao.movedistributoragent(dsid,Agentid,userId);
				}
				if(status.equals("success"))
					status="Agent Ids Move Successfully";
				else if(status.equals("failure"))
					status="Technical Failure";
				
				request.setAttribute("message", status);

				return "moveentityjsp";

			}else if(val.equals("submitentityds"))
			{   
				String mdsId=request.getParameter("mdsId");
				String dsId=request.getParameter("dsId");
				//System.out.println(Agentid);
				String status="failure";
				//System.out.println("distributor id"+dsid);
				if(dsId.contains(","))
				{

					String ids[]=dsId.split(",");
					//System.out.println("----"+ids.length);
					for(int i=0;i<ids.length;i++)
					{

						//System.out.println("agent id="+ids[i]);
						//System.out.println("move entity");
						status=mventydao.moveMdsToDs(mdsId,ids[i].trim());
					}

				}
				else
				{
					//System.out.println("in else");

					status=mventydao.moveMdsToDs(mdsId,dsId);
				}
				if(status.equals("success"))
					status="DS Ids Move Successfully";
				else
					status="Technical Failure";
				request.setAttribute("statusmsg", status);

				return "moveentityjspds";
			}
			return "moveentityjsp";
			
			
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in MdsWlMarginSetupAction");
			e.printStackTrace();
			return "err";
		}
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request){
		this.request=request;

	}
}
