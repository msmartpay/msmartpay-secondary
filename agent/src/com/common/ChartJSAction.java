package com.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChartJSAction{

	Logger logger=Logger.getLogger(ChartJSAction.class);

	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String param;
	private String fromDate;
	private String toDate;

	public String execute()
			throws Exception {

		PrintWriter out=null;

		String agentID="";
		try
		{
			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession();
			PrintWriter printWriter=response.getWriter();

			if(session.getAttribute("agentID")==null)
			{
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			out=response.getWriter();

			logger.info("TEP ,Class is ChartJSAction ,Param is "+param);
			ChartJSDao chartJSDao=new ChartJSDao();
			if("dayAndServiceWise".equalsIgnoreCase(param))
			{
				if(session.getAttribute("dayAndServiceWise")==null) {
					
					JSONObject finalobj=new JSONObject();
					
					JSONArray days=chartJSDao.findLast15Days(agentID);
					if(days.length()>0) {
						
						finalobj.put("labels", days);
						
						JSONArray datasets=new JSONArray();
						
						/*for (int i = 0; i < days.length(); i++) {
							String day=days.getString(i);
							
						}*/
						int g=99;
						int b=132;
						int r=255;
						JSONArray services=chartJSDao.findLast15DaysService(agentID);
						for (int j = 0; j < services.length(); j++) {
							
							JSONObject dataset=new JSONObject();
							
							if("AEPS-Withdrawal".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Withdrawal");
							else if("DMR-E-ACCOUNT".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Verification");
							else if("DMR-E-REMIT".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Remittance");
							else if("liveMobRech".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Mobile");
							else if("liveDthRech".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "DTH");
							else if("liveDCRech".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Datacard");
							else if("liveBillpay".equalsIgnoreCase(services.getString(j)))
								dataset.put("label", "Billpayment");
							else
								dataset.put("label", services.getString(j));
							
							dataset.put("backgroundColor", "rgba("+r+","+g+", "+b+", 0.5)");
							dataset.put("borderColor", "rgb("+r+", "+g+", "+b+")");
							dataset.put("borderWidth", 1);
							
							JSONArray data=new JSONArray();
							for (int i = 0; i < days.length(); i++) {
								data.put(chartJSDao.findLast15DaysBusiness(agentID, services.getString(j), days.getString(i)));
							}
							dataset.put("data", data);
							datasets.put(dataset);
							
							g=g+30;
							b=b+30;
							r=r-30;
						}
						finalobj.put("datasets", datasets);
						session.setAttribute("dayAndServiceWise", finalobj);
						out.print(finalobj);
						out.close();
					}
				}else {
					out.print(session.getAttribute("dayAndServiceWise"));
					out.close();
				}
				return null;
			}else if("todayServiceWise".equalsIgnoreCase(param))
			{
				JSONArray data=chartJSDao.todayServiceWiseBusiness(agentID);
				out.print(data);
				out.close();
				return null;
			}
			else if("todayBusinessOperatorWise".equalsIgnoreCase(param))
			{
				
				fromDate=request.getParameter("fromDate");
				toDate=request.getParameter("toDate");
				
				JSONArray data=chartJSDao.todayBusinessOperatorWise(agentID,fromDate,toDate);
				out.print(data);
				out.close();
				return null;
			}
			else
			{
				request.setAttribute("message","Your request can not be processed due to some technical problem.");
				return "HomePage";
			}

		}catch (Exception ex) {
			logger.info("Exception in BackButtonAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}


	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}



}
