package com.utilityService;

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
import com.common.BackButtonDao;
import com.common.GenerateIdDao;
import com.common.PropertyFile;
import com.opensymphony.xwork2.ActionSupport;
import com.ssz.api.SmartKindaAPI;
import com.utility.MapComparator;

public final class UtilityServiceAction extends ActionSupport{

	Logger logger=Logger.getLogger(UtilityServiceAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	UtilityServiceDao daoObj=null;
	@SuppressWarnings("unchecked")
	public String execute()
			throws Exception {

		try
		{
			String agentID="";
			request=ServletActionContext.getRequest();
			session=request.getSession(false);

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			logger.info("TEP ,Class is UtilityServiceAction ,Param is "+param);
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			if(param.equals("UtilityPage")){

				JSONObject obj=new JSONObject();
				List<HashMap<String,String>> opertorList=null;
				//if(session.getAttribute("opertorList")==null){
				opertorList=SmartKindaAPI.fetchOperator(obj,agentID,tranKey);
				if(opertorList!=null && opertorList.size()>0){

					Collections.sort(opertorList, new MapComparator("OperatorName"));

					session.setAttribute("opertorList",opertorList);

				}
				//}


				return "UtilityPage";
			}
			else if(param.equals("viewBillpay"))
			{
				PrintWriter out=null;
				JSONObject respObj=null;
				try {



					response=ServletActionContext.getResponse();
					out=response.getWriter();

					String op= request.getParameter("OP");
					String opName=request.getParameter("OPName");
					String service=request.getParameter("Service");
					String cn=request.getParameter("CN");
					String amount=request.getParameter("AMT");
					String ad1=request.getParameter("AD1");
					String ad2=request.getParameter("AD2");
					String ad4=request.getParameter("AD4");

					String tranId=GenerateIdDao.getAETranId(agentID);

					respObj=SmartKindaAPI.viewBillpayRequest(tranId, op, cn, amount, ad1, ad2, "", ad4,opName,service,agentID,tranKey);
					logger.info("Response :: "+respObj);
					if(respObj==null){
						respObj=new JSONObject();
						respObj.put("Status", "Failure");
						respObj.put("Description", "Unable to process. Please try Later.");
					}else{
						respObj.put("Status", respObj.get("response-code"));
						respObj.put("Description", respObj.get("response-message"));
					}


				} catch (Exception e) {
					// TODO: handle exception
					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", "Unable to process. Please try Later.");
				}

				out.print(respObj);

				return null;
			}
			else if(param.equals("payBillpay"))
			{

				PrintWriter out=null;
				JSONObject respObj=null;
				try {

					String ipaddress=request.getRemoteAddr();

					response=ServletActionContext.getResponse();
					out=response.getWriter();


					String op= request.getParameter("OP");
					String opName=request.getParameter("OPName");
					String cn=request.getParameter("CN");
					String amount=request.getParameter("AMT");
					String ad1=request.getParameter("AD1");
					String ad2=request.getParameter("AD2");
					String ad4=request.getParameter("AD4");
					String service=request.getParameter("Service");

					String reference_id, requestId="";
					if("POSTPAID".equalsIgnoreCase(service) | "LANDLINE".equalsIgnoreCase(service)) {
						requestId=GenerateIdDao.getAETranId(agentID);
						reference_id="";
					}else{
						requestId=request.getParameter("REQUEST_ID");
						reference_id=request.getParameter("reference_id");
					}
					if(requestId.equalsIgnoreCase("")) {
						respObj=new JSONObject();
						respObj.put("Status", "1");
						respObj.put("Description", "Invalid Transation Id");
					}else {

						respObj=SmartKindaAPI.sendAPIBillpayRequest(op, amount, cn, requestId, ad1, ad2, "", ad4, 
								opName, reference_id, service, agentID, tranKey);
						logger.info("Response :: "+respObj);
						if(respObj==null){
							respObj=new JSONObject();
							respObj.put("Status", "Failure");
							respObj.put("Description", "Unable to process. Please try Later.");
						}else{
							respObj.put("Status", respObj.get("response-code"));
							respObj.put("Description", respObj.get("response-message"));
						}

					}

				} catch (Exception e) {
					// TODO: handle exception
					respObj=new JSONObject();
					respObj.put("Status", "Failure");
					respObj.put("Description", PropertyFile.TECHNICAL_FAILURE);
				}

				out.print(respObj);

				return null;

			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			request.setAttribute("message", PropertyFile.TECHNICAL_FAILURE);
			logger.info("UtilityServiceAction.execute()"+e.getMessage());
		}
		return "ERROR";
	}


}