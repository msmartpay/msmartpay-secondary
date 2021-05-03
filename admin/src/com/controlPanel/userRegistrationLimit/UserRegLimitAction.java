package com.controlPanel.userRegistrationLimit;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

// Referenced classes of package com.controlPanel.userRegistrationLimit:
//            UserRegLimitDao

public class UserRegLimitAction extends ActionSupport implements ServletRequestAware, ServletResponseAware
{

	private HttpServletRequest request;
    private HttpServletResponse response;
    private Map session;
    private UserRegLimitDao urld;
    public UserRegLimitAction()
    {
        urld = new UserRegLimitDao();
    }

    public String execute()
        throws Exception
    {
        session = ActionContext.getContext().getSession();
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        String flag;
        String userId = (String)session.get("userId");
        flag = "N/A";
        if(userId == null)
        {
	        request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
	        return "sessionExpire";
        }
        String param;
        ArrayList userDetails;
        param = request.getParameter("param");
        System.out.println((new StringBuilder("UserRegLimitAction---------- param is------ ")).append(param).toString());
        userDetails = new ArrayList();
        if("LimitUser".equalsIgnoreCase(param))
        {
	        request.setAttribute("flag", flag);
	        return "regLimitPage";
        }
        String userType;
        String searchBy;
        if("searchDetails".equalsIgnoreCase(param))
        {
        	System.out.println((new StringBuilder("Param---- : ")).append(param).toString());
        	userType = request.getParameter("userOption");
        	searchBy = "N/A";
        	String useId = "N/A";
        	if(userType.equalsIgnoreCase("mds"))
            {
        		searchBy = request.getParameter("filterOption");
        		flag = "md";
        		if(searchBy.equalsIgnoreCase("all"))
        		{
        			userDetails = urld.getUserMDLimitationdata();
        			if(userDetails.size() > 0 && userDetails != null)
        			{	
        				request.setAttribute("userDetails", userDetails);
            			request.setAttribute("flag", flag);
            			return "regLimitPage";
        				
        			}else{
        				request.setAttribute("message", "No data available.");
        				request.setAttribute("flag", flag);
        				return "regLimitPage";
        			}
        			
        		}
		        if(searchBy.equalsIgnoreCase("idwise"))
		        {
		        	useId = request.getParameter("userid");
		        	userDetails = urld.getOneUserMDLimitationdata(useId);
		        	if(userDetails.size() > 0 && userDetails != null)
		        	{
		        		request.setAttribute("userDetails", userDetails);
			        	request.setAttribute("flag", flag);
			        	return "regLimitPage";
		        		
		        	}else{
		        		request.setAttribute("message", "No data available.");
		        		request.setAttribute("flag", flag);
		        		return "regLimitPage";
		        	}
		        	
		        }
            }
        	if(userType.equalsIgnoreCase("ds"))
            {
        		searchBy = request.getParameter("filterOption");
        		flag = "ds";
        		if(searchBy.equalsIgnoreCase("all"))
        		{
        			userDetails = urld.getUserDSLimitationdata();
        			if(userDetails.size() > 0 && userDetails != null)
        			{
        				request.setAttribute("userDetails", userDetails);
            			request.setAttribute("flag", flag);
            			return "regLimitPage";
        			}else{

        				request.setAttribute("message", "No data available.");
        				request.setAttribute("flag", flag);
        				return "regLimitPage";
        			}
        			
        		}
        		if(searchBy.equalsIgnoreCase("idwise"))
        		{
        			useId = request.getParameter("userid");
        			userDetails = urld.getOneUserDSLimitationdata(useId);
        			if(userDetails.size() > 0 && userDetails != null)
        			{
        				request.setAttribute("userDetails", userDetails);
            			request.setAttribute("flag", flag);
            			return "regLimitPage";
        			}else{

        				request.setAttribute("message", "No data available.");
        				request.setAttribute("flag", flag);
        				return "regLimitPage";
        			}
        			
        		}
            }
        }
        String checkOperator[];
        String result;
        if("UpdateRegLimit".equalsIgnoreCase(param))
        {
        	String loginType = (String)session.get("loginType");
        	System.out.println((new StringBuilder("loginType ")).append(loginType).toString());
        	checkOperator = request.getParameterValues("checkpartial");
        	System.out.println((new StringBuilder("check Partial is ")).append(checkOperator).toString());
        	if(checkOperator == null)
    		{
    			request.setAttribute("message", "Please select Check box");
    			return "regLimitPage";
    		}
        	result = "";
        	String clientId = request.getParameter("clientId");
        	String mdId = request.getParameter("MdsId");
        	System.out.println((new StringBuilder("clientId--------- \t:")).append(clientId).toString());
        	System.out.println((new StringBuilder("mdId------------- \t:")).append(mdId).toString());
        	
	        ArrayList totallist;
	        int totpass;
	        ArrayList list = null;
	        totallist = new ArrayList();
	        totpass = checkOperator.length;
	        userType = session.get("flag").toString();
	        System.out.println((new StringBuilder("-----usertype---in----action------")).append(userType).toString());
	        if(userType.equalsIgnoreCase("MD"))
            {
	        	for(int i = 0; i < totpass; i++)
	        	{
	        		list = new ArrayList();
	        		System.out.println("we are u=into to get value");
	        		String value = checkOperator[i];
	        		String clientgetId = request.getParameter((new StringBuilder("client")).append(value).toString());
	        		list.add(clientgetId);
	        		String mdgetId = request.getParameter((new StringBuilder("Mds")).append(value).toString());
	        		list.add(mdgetId);
	        		String dsregLimit = request.getParameter((new StringBuilder("DSLimit")).append(value).toString());
	        		list.add(dsregLimit);
	        		totallist.add(list);
	        	}
            
	        	ArrayList listopr = null;
	        	for(int j = 0; j < totallist.size(); j++)
	        	{
		            System.out.println("we are going to update ");
		            listopr = (ArrayList)totallist.get(j);
		            String clientgetId = (String)listopr.get(0);
		            String mdgetId = (String)listopr.get(1);
		            int dsregLimit = Integer.parseInt((String)listopr.get(2));
		            System.out.println((new StringBuilder("clientgetId------ :")).append(clientgetId).toString());
		            System.out.println((new StringBuilder("mdgetId------- :")).append(mdgetId).toString());
		            System.out.println((new StringBuilder("dsregLimit------- :")).append(dsregLimit).toString());
		            result = urld.updateMDDetails(clientgetId, mdgetId, dsregLimit);
		            System.out.println((new StringBuilder("-result--------")).append(result).toString());
	        	}
            
	        	if(result.equalsIgnoreCase("Success"))
	        	{
	        		request.setAttribute("message", "Proceess has been completed.");
	        		request.setAttribute("flag", "MD");
	        		return "regLimitPage";
	        	}else{
	        		request.setAttribute("message", "Process aborted due to Technical Failure.");
	        		request.setAttribute("flag", "MD");
	        		return "regLimitPage";
	        	}
            }
	        if(userType.equalsIgnoreCase("DS"))
            {
		        for(int i = 0; i < totpass; i++)
		        {
		            list = new ArrayList();
		            System.out.println("we are u=into to get value");
		            String value = checkOperator[i];
		            String clientgetId = request.getParameter((new StringBuilder("client")).append(value).toString());
		            list.add(clientgetId);
		            String mdgetId = request.getParameter((new StringBuilder("Mds")).append(value).toString());
		            list.add(mdgetId);
		            String dsgetId = request.getParameter((new StringBuilder("Ds")).append(value).toString());
		            list.add(dsgetId);
		            String agregLimit = request.getParameter((new StringBuilder("AGLimit")).append(value).toString());
		            list.add(agregLimit);
		            totallist.add(list);
		        }

		        ArrayList listopr = null;
		        for(int j = 0; j < totallist.size(); j++)
		        {
		            System.out.println("we are going to update ");
		            listopr = (ArrayList)totallist.get(j);
		            String clientgetId = (String)listopr.get(0);
		            String mdgetId = (String)listopr.get(1);
		            String dsgetId = (String)listopr.get(2);
		            int agregLimit = Integer.parseInt((String)listopr.get(3));
		            System.out.println((new StringBuilder("clientgetId------ :")).append(clientgetId).toString());
		            System.out.println((new StringBuilder("mdgetId------- :")).append(mdgetId).toString());
		            System.out.println((new StringBuilder("dsgetId------- :")).append(dsgetId).toString());
		            System.out.println((new StringBuilder("dsregLimit------- :")).append(agregLimit).toString());
		            result = urld.updateDSDetails(clientgetId, mdgetId, dsgetId, agregLimit);
		        }

		        if(result.equalsIgnoreCase("Success"))
		        {
			        request.setAttribute("message", "Proceess has been completed.");
			        request.setAttribute("flag", "DS");
			        return "regLimitPage";
		        }else{
			        request.setAttribute("message", "Process aborted due to Technical Failure.");
			        request.setAttribute("flag", "DS");
			        return "regLimitPage";
		        }
            }
	        return "regLimitPage";
        }   
        return "error";
    }

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}

   
}
