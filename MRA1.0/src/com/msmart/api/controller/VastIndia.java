package com.msmart.api.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.msmart.dao.MobileDthRechargeDao;
import com.msmart.service.CommonServices;
import com.msmart.util.UtilityP;

import net.sf.json.JSONObject;

public class VastIndia {

	Logger logger=Logger.getLogger(VastIndia.class);

	public HashMap<String,String> callRecharge(String rech_amount,String mobile_no,String oprCode,String transaction_No,
			String Operator) {
		HashMap<String,String> result=new HashMap<String,String>();
		String recString="",searchResponse="",url="";
		String requestContentType=MediaType.APPLICATION_JSON;
		try {

			recString="Mobile="+mobile_no+"&Amount="+rech_amount+"&rch_id="+transaction_No;

			url = oprCode+recString;

			logger.info("Input request from VastIndia is: "+url);
			CommonServices.insertEKOLogs("VastIndia", transaction_No, url, "");

			searchResponse = UtilityP.httpsPost(url, null, requestContentType, "GET",null);
			logger.info(searchResponse);

			try {
				CommonServices.updateEKOLogs(transaction_No, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(searchResponse!=null && searchResponse.length()>0){
				result=GetRespText(searchResponse);	
			}else{
				result=new HashMap<String,String>();
				result.put("refId", transaction_No);
				result.put("OperatorTxnId", "");
				result.put("Status", "Pending");
			}
			result.put("InputXML", url);
			result.put("ResoponseXML", searchResponse);

		} catch (Exception e) {
			e.printStackTrace();
			result=new HashMap<String,String>();
			result.put("refId", transaction_No);
			result.put("OperatorTxnId", "");
			result.put("Status", "Pending");
			result.put("InputXML", url);
			result.put("ResoponseXML", searchResponse);
			return result;
		}

		return result;
	}
	public	HashMap<String,String> GetAirtelJioTATASkyRespText(String jsonString)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			

			if(null!=jsonString)
			{
				JSONObject obj=JSONObject.fromObject(jsonString);
				hmap.put("chkNode","response");
				hmap.put("ResoponseXML",jsonString);

				String statusVal= obj.getString("STATUS");
				if (statusVal.equalsIgnoreCase("1")) {
					hmap.put("Status", "Success");
				}else if(statusVal.equalsIgnoreCase("3")){
					hmap.put("Status", "failure");
				}else{
					hmap.put("Status", "Pending");
				}

				hmap.put("responseCode",statusVal); 
				hmap.put("refId",obj.optString("MEMBERREQID")); 

				hmap.put("OrderNo",obj.optString("ORDERID"));
				hmap.put("OperatorTxnId",obj.optString("OPTRANSID"));

				hmap.put("Description", obj.optString("MESSAGE"));
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("description", "Technical failure.");
			hmap.put("refId","");
			hmap.put("OperatorTxnId","");
			e.printStackTrace();
		}
		return hmap;
	}

	public	HashMap<String,String> GetIdeaVodaDthRespText(String jsonString)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			

			if(null!=jsonString)
			{
				JSONObject obj=JSONObject.fromObject(jsonString);
				hmap.put("chkNode","response");
				hmap.put("ResoponseXML",jsonString);
				String statusVal= obj.getString("status");
				if (statusVal.equalsIgnoreCase("SUCCESS")) {
					hmap.put("Status", "Success");
				}else if(statusVal.equalsIgnoreCase("Failed")){
					hmap.put("Status", "failure");
				}else{
					hmap.put("Status", "Pending");
				}

				hmap.put("responseCode",statusVal); 
				hmap.put("refId",obj.optString("reqid")); 

				hmap.put("OrderNo",obj.optString("opid"));
				hmap.put("OperatorTxnId",obj.optString("opid"));

				hmap.put("Description", obj.getString("remark"));
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("description", "Technical failure.");
			hmap.put("refId","");
			hmap.put("OperatorTxnId","");
			e.printStackTrace();
		}
		return hmap;
	}

	public	HashMap<String,String> GetRespText(String jsonString)
	{		
		HashMap<String, String> hmap=new HashMap<String,String>(); 
		try 
		{			

			if(null!=jsonString)
			{
				JSONObject obj=JSONObject.fromObject(jsonString);
				hmap.put("chkNode","response");
				hmap.put("ResoponseXML",jsonString);
				String statusVal= obj.getString("Status");
				if (statusVal.equalsIgnoreCase("SUCCESS")) {
					hmap.put("Status", "Success");
				}else if(statusVal.equalsIgnoreCase("Failed")){
					hmap.put("Status", "failure");
				}else{
					hmap.put("Status", "Pending");
				}

				hmap.put("responseCode",statusVal); 
				hmap.put("refId",obj.optString("RID")); 

				hmap.put("OrderNo",obj.optString("Operatorid"));
				hmap.put("OperatorTxnId",obj.optString("Operatorid"));

				hmap.put("Description", obj.optString("remark"));
			}				
		}
		catch (Exception e) {
			hmap.put("Status", "failure");
			hmap.put("description", "Technical failure.");
			hmap.put("refId","");
			hmap.put("OperatorTxnId","");
			e.printStackTrace();
		}
		return hmap;
	}

	public void vastwebindia(HttpServletRequest request) {
		try {
			String txnStatus="",vendor="Venfone";
			String reqid=request.getParameter("rchid");
			String Status=request.getParameter("Status");
			String OperatorTxnId=request.getParameter("operatorid");
			MobileDthRechargeDao daoObj=new MobileDthRechargeDao();
			HashMap<String,String> tranDetils=daoObj.getTranDetails(reqid, reqid, reqid);
			String agentID=tranDetils.get("user_id");
			if(Status.equalsIgnoreCase("Success"))
			{
				txnStatus="Success";
				logger.info(" Updateing start on Ravi Success >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");


			}else if(Status.equalsIgnoreCase("Pending"))
			{
				txnStatus="Pending";
				logger.info(" Updateing start on Ravi Pending >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");


			}else if(Status.equalsIgnoreCase("FAILED")||Status.equalsIgnoreCase("FREQUENT") ||Status.equalsIgnoreCase("REFUND"))
			{
				txnStatus="Failure";
				logger.info(" Updateing start on Ravi "+Status+" >>>>>>>>>>>>>>>>>  ");
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
				logger.info(" Updateing Stop on Ravi Success >>>>>>>>>>>>>>>  ");

			}else 
			{
				//		
				txnStatus="Pending";
				daoObj.updateStatusTranEGEN(reqid,txnStatus,agentID,"",OperatorTxnId,vendor,"","");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
