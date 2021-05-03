package com.controlPanel.margin.wlMdShare;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WLMDShareAction  extends ActionSupport implements ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	public String execute () throws Exception
	{
		WLMDShareDao daoObj=new WLMDShareDao();
		session=ActionContext.getContext().getSession();
		try{
			PrintWriter out = null;
			response=ServletActionContext.getResponse();
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}			
			String param=request.getParameter("param");
			System.out.println("MdsWlMarginSetupAction param is "+param);
			if(param.equalsIgnoreCase("getwlMdsMarginPage")){
				return "wlMdsPage";
			}else if(param.equalsIgnoreCase("getwlMdsMarginPageNew")){
				return "wlMdsPageNew";
			}
			else if(param.equalsIgnoreCase("getWLMDDetails"))
			{
				String ClientID=request.getParameter("EnterUserId");
				String MDSid=request.getParameter("MDSid");
				String serviceType=request.getParameter("serviceType");
				String shareType=request.getParameter("shareType");

				request.setAttribute("shareType",shareType);


				String Category=daoObj.getCategory(ClientID);
				ArrayList list=new ArrayList();
				list=daoObj.getWlMDdata(ClientID, MDSid, serviceType,shareType);	
				System.out.println("map Size::"+list.size());
				if(list.size()<=0){
					request.setAttribute("message","No Data Is Available.");
				}
				request.setAttribute("getDetails", list);
				request.setAttribute("MDSid", MDSid);
				request.setAttribute("ClientID", ClientID);
				request.setAttribute("Category", Category);
				request.setAttribute("flag", "Y");
				request.setAttribute("shareType", shareType);
				return "wlMdsPage";
			}
			else if(param.equalsIgnoreCase("downloadWLMDDetails"))
			{
				String ClientID=request.getParameter("EnterUserId");
				String MDSid=request.getParameter("MDSid");
				String serviceType=request.getParameter("serviceType");
				String shareType=request.getParameter("shareType");

				request.setAttribute("shareType",shareType);

				String Category=daoObj.getCategory(ClientID);
				ArrayList list=new ArrayList();
				list=daoObj.getWlMDdata(ClientID, MDSid, serviceType,shareType);	
				if(list.size()<=0){
					request.setAttribute("message","No Data Is Available.");
					return "wlMdsPage";
				}
				
				response.setContentType("application/vnd.ms-excel");
				String reportName = "Margin Sheet.xls";
				response.setHeader("Content-disposition", "attachment; filename=" + reportName);
				ArrayList<String> rows = new ArrayList<String>();
				
				rows.add("MD ID");
				rows.add("\t");
				rows.add("Main Service");
				rows.add("\t");
				rows.add("Sub Service");
				rows.add("\t");
				rows.add("SKU Name");
				rows.add("\t");
				rows.add("Margin");
				rows.add("\n");
				
				for (int i = 0; i < list.size(); i++) {
					
					HashMap map=(HashMap) list.get(i);
					
					rows.add(map.get("MD_ID")+"");
					rows.add("\t");
					rows.add(map.get("Main_Service")+"");
					rows.add("\t");
					rows.add(map.get("Sub_Service")+"");
					rows.add("\t");
					rows.add(map.get("SKU_Name")+"");
					rows.add("\t");
					rows.add(map.get("MDSMargin")+"");
					rows.add("\n");
					
				}
				
				Iterator<String> iter = rows.iterator();
				while (iter.hasNext()) {
					String outputString = (String) iter.next();
					response.getOutputStream().print(outputString);
				}
				response.getOutputStream().flush();
				response.getOutputStream().close();
				
				return null;
				
			}
			else if (param.equalsIgnoreCase("UpdateShare"))
			{
				String loginType=(String)session.get("loginType");
				System.out.println("loginType "+loginType);
				String[] checkOperator=(String[])request.getParameterValues("checkpartial");
				System.out.println("check Partial is "+checkOperator.length);
				//SKUManagementDao daoObj=new SKUManagementDao();
				String result="";
				String shareType=request.getParameter("shareType");
				String Displaycategory=request.getParameter("Displaycategory");
				String ClientID=request.getParameter("DisplayClientID");
				String DisplayMDID=request.getParameter("DisplayMDID");
				System.out.println("Displaycategory 	:"+Displaycategory);
				System.out.println("DisplayMDID 	:"+DisplayMDID);
				System.out.println("ClientID 	:"+ClientID);
				if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("PortalAdmin"))
				{
					//System.out.println("we are into 1 if");

					ArrayList<String> list=null;
					ArrayList<ArrayList<String>> totallist=new ArrayList<ArrayList<String>>();;
					int Share=100;
					int totpass=checkOperator.length;
					for(int i=0;i<totpass;i++)
					{
						list=new ArrayList<String>();
						//System.out.println("we are u=into to get value");
						String value=checkOperator[i];
						String mainService =request.getParameter("mser"+value);
						list.add(mainService);
						String subService =request.getParameter("ser"+value);
						list.add(subService);
						/*String category =request.getParameter("cat"+value);
						list.add(category);*/
						String SKUname =request.getParameter("sku"+value);
						list.add(SKUname);					
						String md_id =request.getParameter("md_id"+value);
						list.add(md_id);
						String cat =request.getParameter("cat"+value);
						list.add(cat);
						String clientShare =request.getParameter("clientShare"+value);
						list.add(clientShare);
						String mdShare=request.getParameter("MD_Share"+value);
						list.add(mdShare);
						System.out.println("Now all data is "+list);
						totallist.add(list);
					}
					ArrayList<String> listopr=null;
					for(int j=0;j<totallist.size();j++)
					{
						//	System.out.println("we are going to update ");
						listopr=totallist.get(j);
						//	System.out.println(" loop is "+listopr);
						String mainService=listopr.get(0);
						String subService=listopr.get(1);		 	         	        	 
						String SKUname=listopr.get(2);	 	        	
						String md_id=listopr.get(3);
						String cat=listopr.get(4);
						String clientShare=listopr.get(5);
						String mdShare=listopr.get(6);

						System.out.println("mainService :"+mainService);
						System.out.println("subService :"+subService);		 	        	
						System.out.println("SKUname :"+SKUname);		 	        
						System.out.println("md_id :"+md_id);
						System.out.println("cat :"+cat);
						System.out.println("clientShare :"+clientShare);
						System.out.println("mdShare :"+mdShare);

						if(DisplayMDID.equalsIgnoreCase("All"))
						{
							result=daoObj.UpdateShareAllMD(mainService,subService,SKUname,clientShare,mdShare,ClientID,cat,shareType);
						}
						else
						{
							result=daoObj.UpdateShare(mainService,subService,SKUname,clientShare,mdShare,ClientID,md_id,cat,shareType);
						}	 	        
					}					
					if(result.equalsIgnoreCase("Success")){
						request.setAttribute("message", "Proceess has been completed.");

					}else{
						request.setAttribute("message", "Process aborted due to Technical Failure.");
					}				
					return "wlMdsPage";
				}
				else
				{
					request.setAttribute("message","You are Not Authorized for this Service");
					return "wlMdsPage";
				}		
			}

			else if(param.equalsIgnoreCase("getMdid"))
			{
				String loginType=(String)session.get("loginType");
				System.out.println("loginType1 "+loginType);
				String OutPut="";
				try 
				{
					out = response.getWriter();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}				
				String MDInitial="<option value='select'>----Select----</option>";	

				String enterUserID=request.getParameter("Id");
				System.out.println(enterUserID);
				ArrayList ListOfMdid=daoObj.getMdid(enterUserID,userId,loginType);
				StringBuffer MDIdetail=new StringBuffer();				
				if(ListOfMdid.size()<=0)
				{
					OutPut="invalid";
					MDIdetail.append("<option value='select'>----- Select -----</option>");
				}
				else
				{
					//MDIdetail.append("<option value='All'>ALL</option>");
				}
				for(int i=0;i<ListOfMdid.size();i++)
				{
					String MDId=(String)ListOfMdid.get(i);

					MDIdetail.append("<option value=\""+MDId+"\">"+MDId+"</option>");
				}
				String MDend="</select>";
				System.out.println("Output : "+OutPut);
				OutPut=MDIdetail+MDend;
				out.print(OutPut);				
				return null;
			}			
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
	public void setSession(Map session){
		session = this.getSession();
	}
	public Map getSession(){
		return session;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
}
