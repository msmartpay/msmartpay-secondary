
/**
 *  Created Date---2/5/2012
 *  Created By---Amit Pathak 
 *  Purpose- The class(OperatorControlAction)is created for to redirect recharges opertor's on specific vendor and also consist ajax call method
 *  Updated Date-2/5/2012
 *  Updated By--Amit Pathak 
 *  Update Purpose-
*/
/*
 * 	Updated By      : Manoj
 * 	Updated Date    : 5 sep 2012
 *  Updated Matter  : To integrate two operator Tata Sky And Videocon Dth
 */



package com.controlPanel.manageEGEN.egenOperatorMgt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OperatorControlAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware  
{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	OperatorControlFormBean formBean=new OperatorControlFormBean();
	 
	public Object getModel() 
	{
		return formBean;
	}
	 
	
	OperatorControlDao dao=new OperatorControlDao();
	//--------------------------Block to redirect header link to specific page--------
	 
	public String OperatorControl()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		String type=request.getParameter("param");
		if(type==null)
		{
			request.setAttribute("type", "tep");
		}
		else
		{
			request.setAttribute("type", "egan");
		}
	   
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		return "OperatorControl";
	}
	
	 
	//-------------------------------Block to get opertor control details-------------
	 
	public String getOperatorControlDetail()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		StringBuffer BillpaymartOperatorsDetails=null;
		
		StringBuffer CyberOperatorsDetails=null;
		StringBuffer EzyPayOperatorsDetails=null;
		StringBuffer RootOperatorsDetails=null;
		String BillpaymartOperators="";
		String CyberOperators="";
		String EzyPayOperators="";
		String RootOperators="";
		String type="";
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try {
			type=formBean.getParam();
			if(type==null)
			{
				type="tep";
			}
			String Operator=formBean.getOperator();
		    String serviceType=formBean.getServiceType();
		    ArrayList<HashMap<String,Object>> AgentService=null;
		    //--------------------------------method call to get operator details of CyberPlat--------------------	
		    if(type.equalsIgnoreCase("tep"))
		    {
		    	CyberOperatorsDetails=dao.StringCyberDetails(Operator,serviceType);
		    }
		    if(type.equalsIgnoreCase("egan"))
		    {
		    	CyberOperatorsDetails=dao.StringEganCyberDetails(Operator,serviceType);
		    }
		    //System.out.println("CyberOperatorsDetails0000000000000-----------------------------"+CyberOperatorsDetails.length());
		    if(CyberOperatorsDetails.length()>0)
		    {
		    	CyberOperators=CyberOperatorsDetails.substring(0,CyberOperatorsDetails.lastIndexOf(","));   
		    }
		   
		    //--------------------------------method call to get operator details of Billpaymart--------------------	
		    if(type.equalsIgnoreCase("tep"))
		    {
		    	BillpaymartOperatorsDetails=dao.StringBillpaymartDetails(Operator,serviceType);
		    }
		    if(type.equalsIgnoreCase("egan"))
		    {
		    	BillpaymartOperatorsDetails=dao.StringEganBillpaymartDetails(Operator,serviceType);
		    }
		    if(BillpaymartOperatorsDetails.length()>0)
		    {
		    	BillpaymartOperators=BillpaymartOperatorsDetails.substring(0,BillpaymartOperatorsDetails.lastIndexOf(","));
		    }
		  
		    //--------------------------------method call to get operator details of EzyPay--------------------	
		    if(type.equalsIgnoreCase("tep"))
		    {
		    	EzyPayOperatorsDetails=dao.StringEzyPayDetails(Operator,serviceType);
		    }
		    if(type.equalsIgnoreCase("egan"))
		    {
		    	EzyPayOperatorsDetails=dao.StringEganEzyPayDetails(Operator,serviceType);
		    }
		    if(EzyPayOperatorsDetails.length()>0)
		    {
		    	EzyPayOperators=EzyPayOperatorsDetails.substring(0,EzyPayOperatorsDetails.lastIndexOf(","));
		    }
		    //--------------------------------method call to get operator details of Root--------------------
		    if(type.equalsIgnoreCase("tep"))
		    {
		    	RootOperatorsDetails=dao.StringRootDetails(Operator,serviceType);
		    }
		    if(type.equalsIgnoreCase("egan"))
		    {
		    	
		    	RootOperatorsDetails=dao.StringEganRootDetails(Operator,serviceType);
		    }
		    if(RootOperatorsDetails.length()>0)
		    {
		    	RootOperators=RootOperatorsDetails.substring(0,RootOperatorsDetails.lastIndexOf(","));
		    }
		    
		    //--------------------------------method call to get operator details of Agent service--------------------	
		    if(type.equalsIgnoreCase("tep"))
		    {
		    	AgentService=dao.getAgentServiceDetails(Operator,serviceType);
		    }
		    if(type.equalsIgnoreCase("egan"))
		    {
		    	
		    	AgentService=dao.getEganServiceDetails(Operator,serviceType);
		    }
		    if(AgentService.size()>0)
		    {
		    	request.setAttribute("CyberOperators", CyberOperators);	
		    	request.setAttribute("EzyPayOperators", EzyPayOperators);	
		    	request.setAttribute("RootOperators", RootOperators);
		    	request.setAttribute("BillpaymartOperators", BillpaymartOperators);
		    	request.setAttribute("AgentService", AgentService);
		    	int size=AgentService.size();
		    	request.setAttribute("serviceType", serviceType);
		    	session.setAttribute("agentServiceSize", size);
		    }
		    else
		    {
				 request.setAttribute("message","Data not available.");	
				 return "OperatorControl";
		    }
		  
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlAction ");
		}
		request.setAttribute("type", type);
		return "OperatorControl"; 
	}
	 
	//-----------------------------Update opertor vendor -----------------
	 
	public String updateOperatorControl()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try {
			String operatorName="";
			String VendorName="";
			String updateOperatorVendor="NotUpdated";
			String type=formBean.getParam();
			if(type==null)
			{
				type="tep";
			}
			String serviceType=formBean.getServiceType();
			int size=(Integer)session.getAttribute("agentServiceSize");
			//System.out.println("size of service in updateOperatorControl()>>>>>>>>>>>>>"); 	 
			for(int i=0;i<size;i++)
			{
				//System.out.println("size of service in updateOperatorControl()>>>>>>>>>>>>>"+size); 
				String radioValue=request.getParameter("radioname"+i);
				System.out.println("size of service in updateOperatorControl()>>>>>>>>>>>>>"+radioValue); 
				if(radioValue!=null)
				{
					StringTokenizer st = new StringTokenizer(radioValue,"&"); 
					while(st.hasMoreTokens()) 
					{ 
						operatorName = st.nextToken();
						VendorName = st.nextToken(); 
					}
					 
					//-------------------------------Method call to update vendor operator wise--------------------	
					if((VendorName.equalsIgnoreCase("EzyPay")||VendorName.equalsIgnoreCase("EasyPay"))&& !operatorName.equalsIgnoreCase("AirtelAllCircle") )
					{
						
					}
					else if(VendorName.equalsIgnoreCase("Root") && operatorName.equalsIgnoreCase("BPL"))
					{
						
					}
					else
					{
						if(type.equalsIgnoreCase("tep"))
						  {
						updateOperatorVendor=dao.doUpdateOperatorVendor(VendorName,operatorName,serviceType);
						  }
						  if(type.equalsIgnoreCase("egan"))
						  {
							  updateOperatorVendor=dao.doUpdateEganOperatorVendor(VendorName,operatorName,serviceType,userId);
						  }
					}
					  request.setAttribute("type", type);
					if("Update".equalsIgnoreCase(updateOperatorVendor))
					{
						 request.setAttribute("message","Successfuly Updated");	
					}
					else
					{
						request.setAttribute("message","Not Updated");	
					}
				}
					 
			}
		} catch (Exception e) {
			
			request.setAttribute("message","Process aborted due to Technical Failure.");
			e.printStackTrace();
			System.out.println("Exception in OperatorControlAction ");
		}
		return "OperatorControl";  
	}
	
	 
	//-----------------------------Method call  for ajax-----------------
	public String  getOperator()
	{
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		
		try 
		{
			String type=request.getParameter("param");
			if(type==null)
			{
				type="tep";
			}
			String mainService=formBean.getMainService();
			System.out.println("mainservice---"+mainService);
			String serviceType=formBean.getServiceType();
			System.out.println("mainservice---"+serviceType);
			ArrayList<HashMap<String,Object>> AgentService=null;
			
			System.out.println("type>>>>>>>>>>>>>>>>>>>>>>>>"+type); 
			//String Operator="All";
			PrintWriter out=response.getWriter();
			System.out.println("Type : "+type);
			if(type.equalsIgnoreCase("tep"))
			{
				AgentService=dao.getAgentServiceDetailsAjax(mainService,serviceType);
			}
			if(type.equalsIgnoreCase("egan"))
			{
				AgentService=dao.getAgentServiceDetailsAjax2(mainService,serviceType);
			}
			if(AgentService.size()>0)
			{
				StringBuffer str=new StringBuffer();
				StringBuffer operatorName=new StringBuffer();
				HashMap<String,Object> temp=new HashMap<String,Object>();
				for(int i=0;i<AgentService.size();i++)
				{
					temp=(HashMap<String,Object>)AgentService.get(i);
			
					operatorName.append("<OPTION VALUE=\""+temp.get("operator")+"\">"+temp.get("operator")+"</OPTION>");
		
				}
				//str.append("<select name=\"dddddd\"  style=\"width:300px\" >");
				//str.append("<OPTION VALUE=\"selected\">Select</OPTION>");
				str.append("<OPTION VALUE='All'>All</OPTION>");
				str.append(operatorName);
			
				//str.append("</select>");
				String	resultResponse = str.toString(); 
				//System.out.println("Operators : "+resultResponse);
				out.print(resultResponse);
			}
		} 
		catch (Exception e) 
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlAction ");
		}
		return null;
		
	}
	
	//----------------- Add By Naveen-----------------------
	//-----------------------------Method call  for ajax-----------------
	public String getSMServicesOperator() {
		String serviceType = formBean.getServiceType();
		//System.out.println("	**********	serviceType		:: " + serviceType);
		try {
			PrintWriter out = response.getWriter();
			List<String> AgentService =  dao.getSMSServiceDetailsAjax(serviceType);
			if (AgentService.size() > 0) {
				StringBuffer str = new StringBuffer();
				StringBuffer operatorName = new StringBuffer();
				for(String s : AgentService){
					operatorName.append("<OPTION VALUE=\"" + s + "\">" + s + "</OPTION>");
				}
				str.append("<OPTION VALUE='All'>All</OPTION>");
				str.append(operatorName);
				String resultResponse = str.toString();
				out.print(resultResponse);
			}
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlAction ");
		}
		return null;
	}

 /*
	  * SMS service operators control 
	 */
	public String SMServiceOperatorControl() {
		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		return "SMServiceOperatorControl";
	}
	
	public String getSMSServiceOperatorDetail()
	{
		HttpSession session = request.getSession(true);
		String userId	= (String) session.getAttribute("userId");
		String cyberOperators = "";
		String rinOperators = "";
		String goOperators = "";
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		try {
			String Operator	=	formBean.getOperator();
			String serviceType	=	formBean.getServiceType();
		    
			//--------------------------------method call to get operator details of CyberPlat--------------------	    
		    StringBuffer cyberOperatorsDetails = dao.cyberSMSDetails(Operator,serviceType);
		    //System.out.println("	cyberOperatorsDetails	::	" + cyberOperatorsDetails.length());
		    
		    if(cyberOperatorsDetails.length()>0)
		    {
		    	cyberOperators	=	cyberOperatorsDetails.substring(0,cyberOperatorsDetails.lastIndexOf(","));   
		    }
		   
		  
		    //--------------------------------method call to get operator details of RIN--------------------	
		    StringBuffer rinOperatorsDetails = dao.rinSMSDetails(Operator,serviceType);
		    if(rinOperatorsDetails.length()>0)
		    {
		    	rinOperators = rinOperatorsDetails.substring(0,rinOperatorsDetails.lastIndexOf(","));
		    }
		    //--------------------------------method call to get operator details of Go Process--------------------	    
		    StringBuffer goOperatorsDetails = dao.goSMSDetails(Operator,serviceType);
		    if(goOperatorsDetails.length()>0)
		    {
		    	goOperators	=	goOperatorsDetails.substring(0,goOperatorsDetails.lastIndexOf(","));
		    }
		    
		  //--------------------------------method call to get operator details of Agent service--------------------	   
		    ArrayList<HashMap<String,Object>> serviceSMS = dao.getSMSServiceDetails(Operator,serviceType);
		    if(serviceSMS.size()>0)
		    {
		    	request.setAttribute("SMSCyberOperators", cyberOperators);	
		    	request.setAttribute("SMSRinOperators", rinOperators);	
		    	request.setAttribute("SMSGoalOperators", goOperators);	
		    	request.setAttribute("SMSVendorService", serviceSMS);
		    	int size = serviceSMS.size();
		    	request.setAttribute("SMSserviceType", serviceType);
		    	session.setAttribute("SMSagentServiceSize", size);
		    }
		    else
		    {
		    	request.setAttribute("message","No data found.");	
				 return "SMServiceOperatorControl";
		    }
		  
		    } catch (Exception e) {
			
		    	request.setAttribute("message","Process aborted due to Technical Failure.");
				System.out.println(e.toString());
				System.out.println("Exception in OperatorControlAction ");
		}
			return "SMServiceOperatorControl"; 
	 }
	
	 
	  //--------------------------------------------------------	SMS Services	------------------------------------------
	 
	public String updateSMSServiceOperators()
	{
		HttpSession session = request.getSession(true);
		String userId = (String)session.getAttribute("userId");
		if(userId==null)
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		 
		try {
			String operatorName = "";
			String VendorName = "";
			String serviceType = formBean.getServiceType();
			int size = (Integer)session.getAttribute("SMSagentServiceSize");
			
			for(int i = 0; i < size; i++)
			{
				//System.out.println("	service in updateSMSServiceOperators()	::	" + i); 
				String radioValue = request.getParameter("radioname"+i);
				if(radioValue != null)
				{
					StringTokenizer st = new StringTokenizer(radioValue,"&"); 
					while(st.hasMoreTokens()) { 
						operatorName = st.nextToken();
						//System.out.println("	********************	Operator Name		********************	" + operatorName);
						VendorName = st.nextToken();
						//System.out.println("	********************	Vendor Name			********************	" + VendorName);
					}
					 
					//-------------------------------Method call to update vendor opertor wise--------------------				  
					String updateOperatorVendor = dao.updateSMSOperatorVendor(VendorName,operatorName,serviceType);
					if("Update".equalsIgnoreCase(updateOperatorVendor))
					{
						 request.setAttribute("message","Successfuly Updated.");	
					}
					else
					{
						request.setAttribute("message","Not Updated.");	
					}
				}
			}
		} catch (Exception e) {
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			System.out.println("Exception in OperatorControlAction ");
			
		}
		return "SMServiceOperatorControl";  
	}
	 
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
