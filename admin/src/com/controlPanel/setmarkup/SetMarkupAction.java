package com.controlPanel.setmarkup;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SetMarkupAction extends ActionSupport implements ModelDriven
{
	private SetMarkupForm setMarkupForm=new SetMarkupForm();
	private SetMarkupDao setMarkupDao=null;
	private HttpServletRequest request=null;
	private HttpSession session=null;
	
	public Object getModel() 
	{
		return setMarkupForm;
	}
	
	public String execute() 
	{
		String key="";
		
		
		request=ServletActionContext.getRequest();
		session=request.getSession();
		
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String param=(String)request.getParameter("param");
		BigInteger clientUserId=new BigInteger(userId);
		if(param.equalsIgnoreCase("getMarkup"))
		{
			/*setMarkupDao=SetMarkupDao.getInstance();
			ArrayList<String> list =setMarkupDao.getClientId( userId);
			System.out.println("SetMarkupAction.execute() : "+list);
			request.setAttribute("ClientList", list);*/
			return "SetMarkupPage";
		}
		if(param.equalsIgnoreCase("getMarkupDetails"))
		{
			
			String loginType=(String)session.getAttribute("loginType");
			String initial="all";
			String clientId="0";
			double clientMarkup=0;
			double mdsMarkup=0;
			double dsMarkup=0;
			double agentMarkup=0;
			String service=(String)request.getParameter("selectService");
			String panelOption=(String)request.getParameter("panelOption");
			
			setMarkupDao=SetMarkupDao.getInstance();
			
			String status="";
			
			if(panelOption.equalsIgnoreCase("all"))
			{
				//System.out.println("For All CLIENT MDS DS and AGENT : "+panelOption);
				clientMarkup=Double.parseDouble((String)request.getParameter("clientMarkup"));
				mdsMarkup=Double.parseDouble((String)request.getParameter("mdsMarkup"));
				dsMarkup=Double.parseDouble((String)request.getParameter("dsMarkup"));
				agentMarkup=Double.parseDouble((String)request.getParameter("agentMarkup"));
				
			}
			else
			{
				boolean validSatus=true,queryStatus=true;
				setMarkupForm.setSetByUserId(clientUserId);
				setMarkupForm.setService(service);
				if(panelOption.equalsIgnoreCase("client"))
				{
					initial="CL";
					clientId=(String)request.getParameter("id");
					if(!loginType.equalsIgnoreCase("SuperAdmin")&& !userId.equalsIgnoreCase("5108"))
					{
						System.out.println("Not a super admin and 5108");
						setMarkupForm.setUserId(clientUserId);
						queryStatus=false;
						validSatus=setMarkupDao.getMDStatus(userId, clientId);
					}
					if(validSatus)
					{
						
						setMarkupForm.setMdId(clientId);
						setMarkupForm=setMarkupDao.getMarkupDetailForIndividual(setMarkupForm,initial,service,queryStatus);
						if(setMarkupForm.getClientInitial()==null)
						{
							request.setAttribute("message", "Enter a valid Client ID.");
							request.setAttribute("flag", "N");
						}
						else
						{
							request.setAttribute("panelOption", "client");
							request.setAttribute("flag", "Y");
							request.setAttribute("MarkupDetails", setMarkupForm);
						}
						
					}
					else{
						request.setAttribute("message", "Please check input value");
					}
					
				}
					
				return "SetMarkupPage";
				
			}
			
			
			return "success";
		}
		if(param.equalsIgnoreCase("saveMarkupDetails"))
		{
			String loginType=(String)session.getAttribute("loginType");
			boolean validSatus=true,queryStatus=true;
			String status="failure";
			SetMarkupDao setMarkupDao=SetMarkupDao.getInstance();
			if(!loginType.equalsIgnoreCase("SuperAdmin") && !userId.equalsIgnoreCase("5108"))
			{
				setMarkupForm.setUserId(clientUserId);
				System.out.println("Not a super admin and 5108");
				queryStatus=false;
				validSatus=setMarkupDao.getMDStatus(userId, setMarkupForm.getMdId());
			}
			
			if(validSatus)
			{
				setMarkupForm.setSetByUserId(clientUserId);
				if(setMarkupForm.getClientInitial().equalsIgnoreCase("CL"))
				{
					setMarkupForm.setDsId("all");
					setMarkupForm.setAgentId("all");
					status=setMarkupDao.saveMarkupDetailForindividual(setMarkupForm,queryStatus);
					
				}
				
				if(status.equalsIgnoreCase("success"))
				{
					request.setAttribute("message", "Process Completed.");
					
				}
				else
				{
					request.setAttribute("message", "Process abort due to technical failure.");
					
				}
			}else{
				request.setAttribute("message", "Please check input value");
			}
			return "success";
		}
		
		return key;
	}
	
}
