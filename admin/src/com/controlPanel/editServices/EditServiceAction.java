package com.controlPanel.editServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

// Referenced classes of package com.controlPanel.editServices:
//            EditServiceDao

public class EditServiceAction extends ActionSupport
implements ServletRequestAware, ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	EditServiceDao daoObj;

	public EditServiceAction()
	{
		daoObj = new EditServiceDao();
	}

	public String execute()        throws Exception
	{
		Map session = ActionContext.getContext().getSession();
		String userId = (String)session.get("userId");
		if(userId == null)
		{
			request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String param;
		param = request.getParameter("param");
		ArrayList service = new ArrayList();
		System.out.println((new StringBuilder("EditServiceAction Class and Param is----------- ")).append(param).toString());
		if(param.equals("getPage"))
		{
			service = daoObj.getAgentService();
			request.setAttribute("service", service);
			return "getServicesPage";
		}
		if(param.equalsIgnoreCase("saveDetails"))
		{
			try{
				String message = "";
				String serviceOption = request.getParameter("serviceOption");
				String filterOption = request.getParameter("filterOption");
				String status = daoObj.updateAgentService(serviceOption, filterOption);
				if(status.equalsIgnoreCase("success"))
				{
					service = daoObj.getAgentService();
					request.setAttribute("service", service);
					message = "Process completed successfully.";
					request.setAttribute("message", message);
				} else
					if(status.equalsIgnoreCase("failure"))
					{
						service = daoObj.getAgentService();
						request.setAttribute("service", service);
						message = "Process aborted due to Technical Failure.";
						request.setAttribute("message", message);
					}
			}
			catch(Exception e)
			{
				System.out.println("Exception in EditServiceAction");
				request.setAttribute("message", "Process aborted due to Technical Failure.");
				System.out.println(e.toString());
				return "err";
			}


			return "getServicesPage";
		}
		return "err";
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public void setSession(Map session)
	{
		session = getSession();
	}

	public Map getSession()
	{
		return session;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}


}
