package com.controlPanel.management.ServiceManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ServiceManagementAction extends ActionSupport 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute()
	{
		System.out.println("helo");
		request= ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session = request.getSession(true);                                                                                                                    
		
		String key="failure";
		
		String userType=(String)session.getAttribute("loginType");
		
		if(userType==null)
		{
			System.out.println("in action :");
			request.setAttribute("message","Session has been expired,Please relogin to continue.");
			return "sessionExpire";
		}		        
		ServiceManagementDao dao=new ServiceManagementDao();	
		String param=request.getParameter("param");
		System.out.println("in action :" +param);
		if(param.equalsIgnoreCase("SMPage"))
		{
			return "SMPage";
		}
		if(param.equalsIgnoreCase("DMRService"))
		{
			
			request.setAttribute("Vendor", dao.fetchVendor("DMR"));
			return "DMR";
		}
		if(param.equalsIgnoreCase("DMRServiceUpdate"))
		{
			String vendor =request.getParameter("vendor");
			try {
				
				int count=dao.updateVendor("DMR", vendor);
				if(count>0)
					request.setAttribute("message", "Vendor updated to "+vendor);
				else
					request.setAttribute("message", "Update process Failure.");
				
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("message", "Update process Failure.");
			}
			return "DMR";
		}
		if(param.equalsIgnoreCase("SMSService"))
		{
			
			request.setAttribute("Vendor", dao.fetchVendor("SMS"));
			return "SMS";
		}
		if(param.equalsIgnoreCase("SMSServiceUpdate"))
		{
			String vendor =request.getParameter("vendor");
			try {
				
				int count=dao.updateVendor("SMS", vendor);
				if(count>0)
					request.setAttribute("message", "Vendor updated to "+vendor);
				else
					request.setAttribute("message", "Update process Failure.");
				
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("message", "Update process Failure.");
			}
			return "SMS";
		}
		if(param.equalsIgnoreCase("getDetails"))
		{
			//System.out.println("in getDetails");
			
			String Operator="";
			String service="";
			ArrayList getDetails=null;
			
			String mdId=request.getParameter("mdId");
			long mdLongId=-1L;
			if(mdId.equalsIgnoreCase("ALL"))
				mdLongId=4001;
			else
				mdLongId=Long.parseLong(mdId);
			
			String vartical=request.getParameter("vartical");
			service=request.getParameter("webservice");
			//System.out.println("webservice : "+service);
			
			if((service==null)||(service.equalsIgnoreCase("select")))
			{
				service=request.getParameter("smsservice");
				//System.out.println("smsservice : "+service);
			}
			
			if(vartical.equalsIgnoreCase("sms"))
			{
				Operator=request.getParameter("utility_operatorlist");
				
				if((Operator==null)||(Operator.equalsIgnoreCase("select")))
				{
					Operator=request.getParameter("operator");
				}
			}
			
			System.out.println("vertical: "+vartical);
			System.out.println("Operator: "+Operator);
			System.out.println("selectService: "+service);
			
			
			
			getDetails=dao.getserviceManagementDetails(service,vartical,Operator,mdLongId);
			
			if(getDetails.size()>0)
			{
				//System.out.println(getDetails);
				if(mdId.equalsIgnoreCase("ALL"))
					request.setAttribute("MDId", "ALL");
				else
					request.setAttribute("MDId", mdId);
				request.setAttribute("getDetails", getDetails);
				request.setAttribute("vertical",vartical);
				request.setAttribute("service",service);
				//System.out.println("vertical"+request.getAttribute("vertical"));
				//System.out.println("service"+request.getAttribute("service"));
				request.setAttribute("flag", "Y");
			}
			else
			{
				request.setAttribute("message","Data not available.");	
			}
			key="success";
		}
		else if(param.equalsIgnoreCase("updateDetails"))
		{
			ArrayList update=new ArrayList();
			//String count=(String)request.getParameter("count");
			
			String mdId=request.getParameter("mdId");
			long mdLongId=-1L;
			if(mdId.equalsIgnoreCase("ALL"))
				mdLongId=-1;
			else
				mdLongId=Long.parseLong(mdId);
			String updateService=(String)request.getParameter("updateService");
			System.out.println("updateService ; "+updateService);
			HashMap hm=null;
			if(updateService.equalsIgnoreCase("web"))
			{
				String[]updateCheckpartail=request.getParameterValues("checkpartial");
				
				for(int i=0; i<updateCheckpartail.length; i++)
				{
					String tokenNumber=updateCheckpartail[i];
					int Idnumber=Integer.parseInt(tokenNumber);
					
					hm=new HashMap();
					
					hm.put("sub_service", (String)request.getParameter("sub_service"+Idnumber));
					hm.put("sku_name", (String)request.getParameter("sku_name"+Idnumber));
					hm.put("vendor_status", (String)request.getParameter("myoption1"+Idnumber));
					hm.put("vendor", (String)request.getParameter("vendor"+Idnumber));
					update.add(hm);
					System.out.println("Map : "+hm);
				}
			}
			if(updateService.equalsIgnoreCase("sms"))
			{
				String[]updateCheckpartail=request.getParameterValues("checkpartial");
				
				for(int i=0; i<updateCheckpartail.length; i++)
				{
					String opCode=null;
					String tokenNumber=null;
					String partialDetails=updateCheckpartail[i];
					StringTokenizer st = new StringTokenizer(partialDetails,"&"); 
					while(st.hasMoreTokens()) 
					{ 
						opCode = st.nextToken();
						tokenNumber = st.nextToken();
					} 
					int Idnumber=Integer.parseInt(tokenNumber);
					
					hm=new HashMap();
					
					hm.put("Op_Code", opCode);
					hm.put("vendor_status", (String)request.getParameter("myoption1"+Idnumber));
					hm.put("vendor", (String)request.getParameter("vendor"+Idnumber));
					update.add(hm);
					System.out.println("Map"+Idnumber+" : "+hm);
				}
			}
			//System.out.println("ArrayList : "+update);
			
			//key="success";
			
			ServiceManagementDao  managementDao=new ServiceManagementDao();
			String result=managementDao.updateServiceManagement(update,updateService,mdLongId);
			if(result.equalsIgnoreCase("success"))
			{
				request.setAttribute("message", "Update process successful.");
				key="success";
			}
			
			else
			{
				request.setAttribute("message", "Update process failure.");
				key="failure";
			}
		}else if(param.equalsIgnoreCase("vendorIp"))
		{
			return "vendorIP";
		}else if(param.equalsIgnoreCase("vendorIpUpdate")){
			
			try
			{
				String vendorIP=request.getParameter("vendorIp");
				String status=dao.addVendorIP(vendorIP);
				if(status.equalsIgnoreCase("success"))
					request.setAttribute("message", "Update process successful.");
				else
					request.setAttribute("message", "Update process Failure.");
				
				key="vendorIP";
					
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "Update process Failure.");
				key="vendorIP";
			}
		}
		
		return key;
	}
			
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
