package com.controlPanel.deductionQuery;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

// Referenced classes of package com.controlPanel.deductionQuery:
//            BalanceDeductionQueryDao

public class BalanceDeductionQueryAction extends ActionSupport
    implements ServletRequestAware, ServletResponseAware
{

	private HttpServletRequest request;
    private HttpServletResponse response;
    private Map session;
    private BalanceDeductionQueryDao bdqd;
    public BalanceDeductionQueryAction()
    {
        bdqd = new BalanceDeductionQueryDao();
    }

    public String execute()
        throws Exception
    {
        ArrayList detailList;
        session = ActionContext.getContext().getSession();
        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();
        ArrayList dsList = new ArrayList();
        detailList = new ArrayList();
        String userId = (String)session.get("userId");
        String flag = "N/A";
        if(userId == null)
        {
        	request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
        	return "sessionExpire";
        }
        String param;
        param = request.getParameter("param");
        System.out.println((new StringBuilder("BalanceDeductionQueryAction---------- param is------ ")).append(param).toString());
        if(param.equalsIgnoreCase("getPage"))
        {
	        dsList = bdqd.getDistributorList();
	        request.setAttribute("dsList", dsList);
	        request.setAttribute("message", "");
	        return "getDeductionPage";
        }
        if(param.equalsIgnoreCase("getCredPage"))
        {
	        dsList = bdqd.getDistributorList();
	        request.setAttribute("dsList", dsList);
	        request.setAttribute("message", "");
	        return "getCreditPage";
        }
        if(param.equalsIgnoreCase("searchDetails"))
        {
	        System.out.println("-----inside deduction----------");
	        String agentId = "";
	        String dsId = request.getParameter("DsID");
	        System.out.println((new StringBuilder("dsId----")).append(dsId).toString());
	        String filterOption = request.getParameter("filterOption");
	        if(filterOption.equalsIgnoreCase("idwise"))
	        {
	            agentId = request.getParameter("userid");
	            System.out.println((new StringBuilder("agentId-----")).append(agentId).toString());
	            detailList = bdqd.getDetailedList(dsId, agentId, filterOption);
	        } else
	        if(filterOption.equalsIgnoreCase("all"))
	            detailList = bdqd.getDetailedList(dsId, agentId, filterOption);
	        
	        if(detailList.size() > 0 && detailList != null)
	        {
	        	dsList = bdqd.getDistributorList();
		        request.setAttribute("dsList", dsList);
		        request.setAttribute("detailList", detailList);
		        request.setAttribute("message", "");
		        return "getDeductionPage";
	        }else{
	        	dsList = bdqd.getDistributorList();
		        request.setAttribute("dsList", dsList);
		        request.setAttribute("message", "No Data Found.");
		        return "getDeductionPage";
	        }
	        
        }
        if(param.equalsIgnoreCase("searchDetailsCredit"))
        {
	        System.out.println("-----inside deduction----------");
	        String agentId = "";
	        String dsId = request.getParameter("DsID");
	        System.out.println((new StringBuilder("dsId----")).append(dsId).toString());
	        String filterOption = request.getParameter("filterOption");
	        if(filterOption.equalsIgnoreCase("idwise"))
	        {
	            agentId = request.getParameter("userid");
	            System.out.println((new StringBuilder("agentId-----")).append(agentId).toString());
	            detailList = bdqd.getDetailedList(dsId, agentId, filterOption);
	        } else
	        if(filterOption.equalsIgnoreCase("all"))
	            detailList = bdqd.getDetailedList(dsId, agentId, filterOption);
	        
	        if(detailList.size() > 0 && detailList != null)
	        {
	        	dsList = bdqd.getDistributorList();
		        request.setAttribute("dsList", dsList);
		        request.setAttribute("detailList", detailList);
		        request.setAttribute("message", "");
		        return "getCreditPage";
	        }else{
	        	dsList = bdqd.getDistributorList();
		        request.setAttribute("dsList", dsList);
		        request.setAttribute("message", "No Data Found.");
		        return "getCreditPage";
	        }
	        
        } 
        if(param.equalsIgnoreCase("UpdateDedAmount"))
        {
        	String ipAdr;
	        String checkOperator[];
	        String result;
	        int year;
	        int month;
	        int dayOfMonth;
	        String userType;
       
	        ipAdr = "121.243.151.40";
	        String loginType = (String)session.get("loginType");
	        checkOperator = request.getParameterValues("checkpartial");
	        result = "";
	        Calendar calendar = new GregorianCalendar();
	        year = calendar.get(1);
	        System.out.println((new StringBuilder("yy------")).append(String.valueOf(year)).toString());
	        month = calendar.get(2);
	        System.out.println((new StringBuilder("mm------")).append(String.valueOf(month)).toString());
	        dayOfMonth = calendar.get(5);
	        System.out.println((new StringBuilder("dd------")).append(String.valueOf(dayOfMonth)).toString());
	        month = Integer.parseInt(getMonth(month));
	        System.out.println((new StringBuilder("mmm------")).append(String.valueOf(month)).toString());
	        dayOfMonth = Integer.parseInt(getMonth(dayOfMonth));
	        System.out.println((new StringBuilder("ddd------")).append(String.valueOf(month)).toString());
	        userType = "Agent";
	        if(loginType.equalsIgnoreCase("superadmin"))
	        {
	        	System.out.println("-----A--we are into 1 if------");
	        	if(checkOperator != null)
	            {
			        ArrayList list = null;
			        ArrayList totallist = new ArrayList();
			        int totpass = checkOperator.length;
			        System.out.println((new StringBuilder("total----")).append(totpass).toString());
			        for(int i = 0; i < totpass; i++)
			        {
			            list = new ArrayList();
			            String value = checkOperator[i];
			            String agentId = request.getParameter((new StringBuilder("agID")).append(value).toString());
			            list.add(agentId);
			            String remark = request.getParameter((new StringBuilder("remark")).append(value).toString());
			            list.add(remark);
			            String amount = request.getParameter((new StringBuilder("DeductAmt")).append(value).toString());
			            list.add(amount);
			            totallist.add(list);
			        }
			
			        ArrayList listopr = null;
			        for(int j = 0; j < totallist.size(); j++)
			        {
			            System.out.println("we are going to update ");
			            listopr = (ArrayList)totallist.get(j);
			            String agentId = (String)listopr.get(0);
			            String remark = (String)listopr.get(1);
			            String amount = (String)listopr.get(2);
			            result = bdqd.updateDSDetails(agentId, amount, ipAdr, remark, year, month, dayOfMonth, userType,"DR");
			        }
			
			        if(result.equalsIgnoreCase("valid"))
			        {
			        	dsList = bdqd.getDistributorList();
			        	request.setAttribute("dsList", dsList);
			        	request.setAttribute("message", "Proceess has been completed.");
			        	return "getDeductionPage";
			        	
			        }else{
			        	dsList = bdqd.getDistributorList();
			            request.setAttribute("dsList", dsList);
			            request.setAttribute("message", "Process aborted due to Technical Failure.");
			            return "getDeductionPage";
			        }
	            }else{
	            	request.setAttribute("message", "Please select Check box");
	        		return "getDeductionPage";
	            }
	        }
        }
        if(param.equalsIgnoreCase("UpdateCreditAmount"))
        {
        	String ipAdr;
	        String checkOperator[];
	        String result;
	        int year;
	        int month;
	        int dayOfMonth;
	        String userType;
       
	        ipAdr = "121.243.151.40";
	        String loginType = (String)session.get("loginType");
	        checkOperator = request.getParameterValues("checkpartial");
	        result = "";
	        Calendar calendar = new GregorianCalendar();
	        year = calendar.get(1);
	        System.out.println((new StringBuilder("yy------")).append(String.valueOf(year)).toString());
	        month = calendar.get(2);
	        System.out.println((new StringBuilder("mm------")).append(String.valueOf(month)).toString());
	        dayOfMonth = calendar.get(5);
	        System.out.println((new StringBuilder("dd------")).append(String.valueOf(dayOfMonth)).toString());
	        month = Integer.parseInt(getMonth(month));
	        System.out.println((new StringBuilder("mmm------")).append(String.valueOf(month)).toString());
	        dayOfMonth = Integer.parseInt(getMonth(dayOfMonth));
	        System.out.println((new StringBuilder("ddd------")).append(String.valueOf(month)).toString());
	        userType = "Agent";
	        if(loginType.equalsIgnoreCase("superadmin"))
	        {
	        	System.out.println("-----A--we are into 1 if------");
	        	if(checkOperator != null)
	            {
			        ArrayList list = null;
			        ArrayList totallist = new ArrayList();
			        int totpass = checkOperator.length;
			        System.out.println((new StringBuilder("total----")).append(totpass).toString());
			        for(int i = 0; i < totpass; i++)
			        {
			            list = new ArrayList();
			            String value = checkOperator[i];
			            String agentId = request.getParameter((new StringBuilder("agID")).append(value).toString());
			            list.add(agentId);
			            String remark = request.getParameter((new StringBuilder("remark")).append(value).toString());
			            list.add(remark);
			            String amount = request.getParameter((new StringBuilder("DeductAmt")).append(value).toString());
			            list.add(amount);
			            totallist.add(list);
			        }
			
			        ArrayList listopr = null;
			        for(int j = 0; j < totallist.size(); j++)
			        {
			            System.out.println("we are going to update ");
			            listopr = (ArrayList)totallist.get(j);
			            String agentId = (String)listopr.get(0);
			            String remark = (String)listopr.get(1);
			            String amount = (String)listopr.get(2);
			            result = bdqd.updateDSDetails(agentId, amount, ipAdr, remark, year, month, dayOfMonth, userType,"CR");
			        }
			
			        if(result.equalsIgnoreCase("valid"))
			        {
			        	dsList = bdqd.getDistributorList();
			        	request.setAttribute("dsList", dsList);
			        	request.setAttribute("message", "Proceess has been completed.");
			        	return "getDeductionPage";
			        	
			        }else{
			        	dsList = bdqd.getDistributorList();
			            request.setAttribute("dsList", dsList);
			            request.setAttribute("message", "Process aborted due to Technical Failure.");
			            return "getDeductionPage";
			        }
	            }else{
	            	request.setAttribute("message", "Please select Check box");
	        		return "getDeductionPage";
	            }
	        }
        }
        return "error";
    }
    

    public String getMonth(int month)
    {
        StringBuffer mnt = new StringBuffer();
        if(month == 1)
            mnt.append("01");
        else
        if(month == 2)
            mnt.append("02");
        else
        if(month == 3)
            mnt.append("03");
        else
        if(month == 4)
            mnt.append("04");
        else
        if(month == 5)
            mnt.append("05");
        else
        if(month == 6)
            mnt.append("06");
        else
        if(month == 7)
            mnt.append("07");
        else
        if(month == 8)
            mnt.append("08");
        else
        if(month == 9)
            mnt.append("09");
        else
            mnt.append(String.valueOf(month));
        return mnt.toString();
    }

    public void setServletResponse(HttpServletResponse httpservletresponse)
    {
    	this.response=httpservletresponse;
    }

    public void setServletRequest(HttpServletRequest httpservletrequest)
    {
    	this.request=httpservletrequest;
    }

    
}
