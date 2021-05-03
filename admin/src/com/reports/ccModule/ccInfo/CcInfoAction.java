package com.reports.ccModule.ccInfo;

/**
 *  Created By : Manoj Khan
 *  Created Matter : for updation of Cc Information
 *  Creatde Date : 22/02/2013
 */
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CcInfoAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute() throws Exception{
		Map session= ActionContext.getContext().getSession();
		String param=(String)request.getParameter("param");
		String key="";
		System.out.println("CcInfoAction class and param is :: "+param);
		try
		{
		
			String userId=(String)session.get("userId");
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			if(param.equalsIgnoreCase("ccinfopage"))
			{
				return "ccinfopage";
			}
			else if(param.equalsIgnoreCase("getCCInfo"))
			{
				CcInfoDao daoObj=new CcInfoDao();
				String userType=request.getParameter("userType");
				String searchBy="";
				if(userType.equalsIgnoreCase("mds"))
				{
					System.out.println("MDS");
					searchBy=request.getParameter("searchBy2");
				}
				else
				{
					System.out.println("ELSE");
					searchBy=request.getParameter("searchBy1");
					/*if(searchBy.equalsIgnoreCase("")||searchBy==null)
					{
						searchBy=request.getParameter("searchBy");
					}*/
				} 
				System.out.println(userType);
				System.out.println(searchBy);
				String userID="";
				
				if(searchBy.equalsIgnoreCase("id"))
				{
					userID=request.getParameter("userID");
					//System.out.println(userID);
					String checkStatus=daoObj.checkUser(userType,searchBy,userID);
					if(checkStatus.equalsIgnoreCase("fail"))
					{
						request.setAttribute("message", "Please Check Input Value.");
						key= "ccinfopage";
					}
					else
					{
						ArrayList listData=daoObj.getCCData(userType,searchBy,userID);
						if(listData.size()>0)
						{
							request.setAttribute("listData",listData);
							request.setAttribute("userType",userType);
						}
						else
						{
							request.setAttribute("message", "Data Not Available.");
						}
						if(userType.equalsIgnoreCase("egen"))
						{
							key ="Egenccinfopage";
						}
						else
						{
							key ="ccinfopage";
						}
						
					}
					System.out.println("Return : "+key);
					return key;
				}
				else if(!searchBy.equalsIgnoreCase("id")){
					ArrayList listData=daoObj.getCCData(userType,searchBy,userID);
					request.setAttribute("listData",listData);
					request.setAttribute("userType",userType);
					if(userType.equalsIgnoreCase("egen")){
						//System.out.println("we are in egen returmn");
						return "Egenccinfopage";
					}else{
						return "ccinfopage";
					}
				}
					
			}else if(param.equalsIgnoreCase("editSlab")){
				return "editSlabpage";
			}
			else if(param.equalsIgnoreCase("editSlabInfo")){
				// New Code
				String userType=request.getParameter("userType");
				String searchBy="";
				if(userType.equalsIgnoreCase("mds")){
					searchBy=request.getParameter("searchBy2");
				}else{
					searchBy=request.getParameter("searchBy1");
				} 
				//System.out.println(userType);
				//System.out.println(searchBy);
				String userID="";
				CcInfoDao daoObj=new CcInfoDao();
				if(searchBy.equalsIgnoreCase("id")){
					userID=request.getParameter("userID");
					//System.out.println(userID);
					String checkStatus=daoObj.checkUser(userType,searchBy,userID);
					if(checkStatus.equalsIgnoreCase("fail")){
						request.setAttribute("message", "Please Check Input Value.");
						return "editSlabpage";
					}
					else{
						ArrayList listData=daoObj.getEditSlabData(userType,searchBy,userID);
						if(listData.size()>0){
							request.setAttribute("listData",listData);
							request.setAttribute("userType",userType);
						}
						else{
							request.setAttribute("message", "Data Not Available.");
						}
						if(userType.equalsIgnoreCase("egen")){
							return "editEgenSlabpage";
						}else{
							return "editSlabpage";
						}
					}
				}
				else if(!searchBy.equalsIgnoreCase("id")){
					ArrayList listData=daoObj.getEditSlabData(userType,searchBy,userID);
					request.setAttribute("listData",listData);
					request.setAttribute("userType",userType);
					if(userType.equalsIgnoreCase("egen")){
						//System.out.println("we are in egen returmn");
						return "editEgenSlabpage";
					}else{
						return "editSlabpage";
					}
				}
				
				
				
				
				//Old Code
//				System.out.println("we are into edit slab");
//				String userType=request.getParameter("typeOfuser");
//				System.out.println(userType);
//				
//				ArrayList listData=daoObj.getEditSlabData(userType);
//				if(listData.size()>0){
//					request.setAttribute("listData",listData);
//					request.setAttribute("userType",userType);
//				}
//				else{
//					request.setAttribute("message", "No data found in the system for this user.");
//				}if(userType.equalsIgnoreCase("egen")){
//					return "editEgenSlabpage";
//				}else{
//					return "editSlabpage";
//				}
			}else if(param.equalsIgnoreCase("updateCCInfo")){
				//System.out.println("we are into update button");
				CcInfoDao daoObj=new CcInfoDao();
				String user=request.getParameter("typeOfuser");
				String[] checkOperator=(String[])request.getParameterValues("checkpartial");
				ArrayList<String> list=null;
				ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
				int totpass=checkOperator.length;
				for(int i=0;i<totpass;i++){
					list=new ArrayList<String>();
	 		        
					String value=checkOperator[i];	 	        	 
					String userMinDeposite=request.getParameter("userMinDeposite"+value);
					list.add(userMinDeposite);
					String userMinDepositeRate=request.getParameter("userMinDepositeRate"+value);
					list.add(userMinDepositeRate);	 		        	 		        
					String AVIDBlock=request.getParameter("AVIDBlock"+value);
					list.add(AVIDBlock);
					String userAVIDUndertaking=request.getParameter("AVIDUndertaking"+value);
					list.add(userAVIDUndertaking);
					String VerifyPan=request.getParameter("VerifyPan"+value);
					list.add(VerifyPan);
					String VerifyAgreement=request.getParameter("VerifyAgreement"+value);
					list.add(VerifyAgreement);
					String userID=request.getParameter("userID"+value);
					list.add(userID);
					String remark=request.getParameter("remark"+value);
					list.add(remark);
					totallist.add(list);
	 		        
				}
				ArrayList<String> listopr=null;
				String result="";
				for(int j=0;j<totallist.size();j++){
					listopr=totallist.get(j);
					String userMinDeposite=listopr.get(0);
					String userMinDepositeRate=listopr.get(1);	 	        		 	        	 
					String AVIDBlock=listopr.get(2);
					String userAVIDUndertaking=listopr.get(3);	 	        	
					String VerifyPan=listopr.get(4);
					String VerifyAgreement=listopr.get(5);
					String userID=listopr.get(6);
					String remark=listopr.get(7);
					result=daoObj.updateCCInfo(userMinDeposite,userMinDepositeRate,AVIDBlock,userAVIDUndertaking,VerifyPan,VerifyAgreement,userID,remark,user);
				}
				if(result.equalsIgnoreCase("fail")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}else{
					request.setAttribute("message", "Proceess has been completed.");
				}
				return "ccinfopage";
			}else if(param.equalsIgnoreCase("updateSlabInfo")){
				//System.out.println("we are into updateSlabInfo button");
				CcInfoDao daoObj=new CcInfoDao();
				String user=request.getParameter("typeOfuser");
				String[] checkOperator=(String[])request.getParameterValues("checkpartial");
				ArrayList<String> list=null;
				ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
				int totpass=checkOperator.length;
				for(int i=0;i<totpass;i++){
					list=new ArrayList<String>();
	 		        
					String value=checkOperator[i];	 	        	 
					String userCategory=request.getParameter("userCategory"+value);
					list.add(userCategory);
					String deposite1=request.getParameter("deposite1"+value);
					list.add(deposite1);	 		        	 		        
					String rate1=request.getParameter("rate1"+value);
					list.add(rate1);
					String deposite2=request.getParameter("deposite2"+value);
					list.add(deposite2);
					String rate2=request.getParameter("rate2"+value);
					list.add(rate2);
					String deposite3=request.getParameter("deposite3"+value);
					list.add(deposite3);
					String rate3=request.getParameter("rate3"+value);
					list.add(rate3);
					String userID=request.getParameter("userID"+value);
					list.add(userID);
					totallist.add(list);
	 		        
	 	           }
				ArrayList<String> listopr=null;
				String result="";
				for(int j=0;j<totallist.size();j++){
					listopr=totallist.get(j);
					String userCategory=listopr.get(0);
					String deposite1=listopr.get(1);	 	        		 	        	 
					String rate1=listopr.get(2);
					String deposite2=listopr.get(3);	 	        	
					String rate2=listopr.get(4);
					String deposite3=listopr.get(5);
					String rate3=listopr.get(6);
					String userID=listopr.get(7);
					result=daoObj.updateSlabInfo(userCategory,deposite1,rate1,deposite2,rate2,deposite3,rate3,userID,user);
				}
				if(result.equalsIgnoreCase("fail")){
					request.setAttribute("message", "Process aborted due to Technical Failure.");
				}else{
					request.setAttribute("message", "Proceess has been completed.");
				}
				return "ccinfopage";
			}
		}catch (Exception e) {
			System.out.println("Exception in AdminUserActivationAction");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "ccinfopage";
		}
		return "err";
	}
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
			
	}
	public HttpServletResponse getServletResponse() {
		return response;
			
	}
}
