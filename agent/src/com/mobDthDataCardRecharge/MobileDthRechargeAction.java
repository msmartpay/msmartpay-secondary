package com.mobDthDataCardRecharge;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import com.common.GenerateIdDao;
import com.common.MRAController;
import com.common.PropertyFile;
import com.opensymphony.xwork2.ActionSupport;
import com.utility.MapComparator;

public final class MobileDthRechargeAction extends ActionSupport{

	Logger logger=Logger.getLogger(MobileDthRechargeAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@SuppressWarnings("unchecked")
	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			if(param==null)param="";
			logger.info("TEP ,Class is MobileDthRechargeAction ,Param is "+param);
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			if("getPage".equalsIgnoreCase(param)){


				List<HashMap<String,String>> opertorList=null;
				if(session.getAttribute("opertorList")==null){
					opertorList=MobileDthRechargeDao.fetchAllUtilityOperator();
					if(opertorList!=null && opertorList.size()>0){

						Collections.sort(opertorList, new MapComparator("OperatorName"));

						session.setAttribute("opertorList",opertorList);

					}
				}
				
				return "ReMobile";
			}
			else if(param.equals("mobileDthRech")) 
			{

				PrintWriter out=null;
				JSONObject respObj=new JSONObject();
				try {

					response=ServletActionContext.getResponse();
					out=response.getWriter();


					String op= request.getParameter("OP");
					String mobileNo=request.getParameter("CN");
					String amtrechg=request.getParameter("AMT");
					String servicename=request.getParameter("Service");

					logger.info("OP : "+op+" : CN : "+mobileNo+" : amount : "+amtrechg+" : Service : "+servicename);

					if ("".equalsIgnoreCase(op) || mobileNo.equalsIgnoreCase("") || mobileNo==null ||amtrechg==null || amtrechg.equalsIgnoreCase(""))
					{
						respObj.put("Status", "Failure");
						respObj.put("Description", "Please use Mozilla Firefox or Internet Explorer. Website is having problem on Google Chrome.");
					}
					else if (amtrechg!=null && Double.parseDouble(amtrechg)<5)
					{
						respObj.put("Status", "Failure");
						respObj.put("Description", "Invalid Amount");

					}else{

						String requestId=GenerateIdDao.getIdNo();
						String ip=request.getRemoteHost();

						JSONObject mraRep=MRAController.sendAPIRechargeRequest(op, amtrechg, mobileNo, requestId, ip, servicename, agentID, tranKey);
						if(mraRep!=null) {
							respObj.put("Status", "Success");
							respObj.put("Description",mraRep.get("response-message"));
						}else {
							respObj.put("Status", "Failure");
							respObj.put("Description","Technical failure. Please check account statement");
						}

					}


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", "Technical failure. Please check account statement.");
				}
				
				out.print(respObj);

				return null;

			}else if(param.equals("dthInfo")) 
			{

				PrintWriter out=null;
				JSONObject respObj=new JSONObject();
				try {

					response=ServletActionContext.getResponse();
					out=response.getWriter();


					String operator= request.getParameter("opName");
					String mobile=request.getParameter("mobileNo");

					logger.info("opName : "+operator+" : mobileNo : "+mobile);
					String url="ezypay/plan";
					
					JSONObject obj=new JSONObject();
					obj.put("agent_id", agentID);
					obj.put("client", PropertyFile.PARTNE_NAME);
					obj.put("cricle", "");
					obj.put("type", "dthInfo");
					obj.put("operator", operator);
					obj.put("mobile", mobile);

					respObj=MRAController.webservicecall(obj, url);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", "Technical failure. Please check account statement.");
				}

				out.print(respObj);

				return null;

			}else if(param.equals("viewPlan")) 
			{

				PrintWriter out=null;
				JSONObject respObj=new JSONObject();
				try {

					response=ServletActionContext.getResponse();
					out=response.getWriter();


					String operator= request.getParameter("opName");
					String type= request.getParameter("type");

					logger.info("opName : "+operator);
					String url="ezypay/plan";
					
					JSONObject obj=new JSONObject();
					obj.put("agent_id", agentID);
					obj.put("client", PropertyFile.PARTNE_NAME);
					obj.put("type", type);
					obj.put("operator", operator);
					obj.put("mobile", "");
					if("mobile".equalsIgnoreCase(type))
						obj.put("cricle", request.getParameter("circle"));

					respObj=MRAController.webservicecall(obj, url);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", "Technical failure. Please check account statement.");
				}

				out.print(respObj);

				return null;

			}
			else if(param.equals("viewoffer")) 
			{

				PrintWriter out=null;
				JSONObject respObj=new JSONObject();
				try {

					response=ServletActionContext.getResponse();
					out=response.getWriter();


					String operator= request.getParameter("opName");
					String type= request.getParameter("type");
					String mobile=request.getParameter("mobile");

					logger.info("opName : "+operator+" : mobileNo : "+mobile);
					String url="ezypay/plan";
					
					JSONObject obj=new JSONObject();
					obj.put("agent_id", agentID);
					obj.put("client", PropertyFile.PARTNE_NAME);
					obj.put("type", type);
					obj.put("operator", operator);
					obj.put("mobile", mobile);
					if("mobile".equalsIgnoreCase(type))
						obj.put("cricle", request.getParameter("circle"));
					
					respObj=MRAController.webservicecall(obj, url);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", "Technical failure. Please check account statement.");
				}

				out.print(respObj);

				return null;

			}
		}		
		catch(Exception e){
			e.printStackTrace();	
			request.setAttribute("message", PropertyFile.TECHNICAL_FAILURE);
			
		}	
		return "ERROR";
	}

}