package com.msmart.eko;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.common.GenerateIdDao;
import com.login.LoginDao;
import com.opensymphony.xwork2.ActionSupport;
public class MoneyTransferAction extends ActionSupport{

	Logger logger=Logger.getLogger(MoneyTransferAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String param=request.getParameter("param");
			logger.info("TEP ,Class is AccountStatementAction ,Param is "+param);

			if(param.equalsIgnoreCase("Money Trasfer")){

				request.removeAttribute("message");

				return "home";

			}
			else if (param.equalsIgnoreCase("Sender Report")) {


				return "home";
			}
			else{

				request.setAttribute("message", "please contact channel Partner");

			}
			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	public String findSender()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			DMRApi dmrApi=new DMRApi();
			String senderId=request.getParameter("senderId");

			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");
			
			HashMap<String, Object> findSenderMap=dmrApi.getFindSender(senderId,tranKey,agentID);

			if(findSenderMap!=null)
			{
				String Status=findSenderMap.get("Status")+"";
				String message=findSenderMap.get("message")+"";
				if("0".equalsIgnoreCase(Status)){
					request.setAttribute("findSenderlist", findSenderMap);
				}else if("2".equalsIgnoreCase(Status)){

					HashMap<String,Object> senderDetails= (HashMap<String,Object>)findSenderMap.get("sdMap");

					request.setAttribute("verifyStatus", "Y");
					request.setAttribute("SenderId", senderDetails.get("SenderId"));
					request.setAttribute("SenderName", senderDetails.get("Name"));
					request.setAttribute("Pincode", "");
					request.setAttribute("message", message);

					return "register";
				}else if("3".equalsIgnoreCase(Status)){

					request.setAttribute("SenderId", senderId);
					request.setAttribute("message", message);
					return "register";
				}else if("5".equalsIgnoreCase(Status)){

					request.setAttribute("message", "Activate Money Transfer service");
					session.setAttribute("eko_aeps", 0);
					return "Activate";
				}

				request.setAttribute("message", message);


			}
			else {

				request.setAttribute("message", "Technical Failure");
			}
			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
	
	public String registerSenderpage()throws Exception
	{
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");



			return "home";



		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	public String registerSender()throws Exception
	{

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");

			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			String SenderId = request.getParameter("SenderId");
			String SenderName =request.getParameter("SenderName");
			String dob = request.getParameter("dob");
			String Pincode = request.getParameter("Pincode");

			DMRApi api=new DMRApi();
			HashMap<String, Object> getSenderregistration=api.getRegisterSender(SenderId, SenderName, Pincode, dob,tranKey,agentID);
			if(getSenderregistration!=null)
			{

				String statusCode=getSenderregistration.get("Status")+"";
				if("0".equalsIgnoreCase(statusCode)){

					request.setAttribute("verifyStatus", "Y");
					
				}else {
					request.setAttribute("message", getSenderregistration.get("message"));
				}

				request.setAttribute("SenderId", SenderId);
				request.setAttribute("SenderName", SenderName);
				request.setAttribute("Pincode", Pincode);
				request.setAttribute("dob", dob);
				return "home";

			}
			else{

			}
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}

	public String VerifySender()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");

			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");
			
			String SenderId = request.getParameter("SenderId");
			String SenderName =request.getParameter("SenderName");
			String Pincode = request.getParameter("Pincode");
			String otp = request.getParameter("otp");

			DMRApi api=new DMRApi();
			JSONObject respJson=api.getverifySender(SenderId, SenderName, "", Pincode,otp,tranKey,agentID);
			if(respJson!=null)
			{

				String statusCode=respJson.getString("Status");
				if("0".equalsIgnoreCase(statusCode)){

					HashMap<String, Object> findSenderMap=api.getFindSender(SenderId,tranKey,agentID);

					if(findSenderMap!=null)
					{
						String Status=findSenderMap.get("Status")+"";

						if("0".equalsIgnoreCase(Status)){
							request.setAttribute("findSenderlist", findSenderMap);
						}else if("2".equalsIgnoreCase(Status)){

							request.setAttribute("findSenderlist", findSenderMap);
							request.setAttribute("SenderId", SenderId);

							return "register";
						}else if("3".equalsIgnoreCase(Status)){
							request.setAttribute("SenderId", SenderId);
							return "register";
						}

					}

				}else if("2".equalsIgnoreCase(statusCode)){
					request.setAttribute("message", respJson.getString("message"));
					return "invalid";
				}else{
					request.setAttribute("message", respJson.getString("message"));
					return "home";
				}
				request.setAttribute("message", respJson.getString("message"));

			}else{
				request.setAttribute("message", "Technical failure.");
			}

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
			request.setAttribute("message", "Technical failure.");
		}
		return "home";
	}

	public String SenderHistory ()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			agentID=(String)session.getAttribute("agentID");
			String SenderId = request.getParameter("senderId");
			DMRApi api=new DMRApi();
			ArrayList<HashMap<String, Object>> responseList=api.getSenderHistory(SenderId,tranKey,agentID);
			if(responseList!=null)
			{
				request.setAttribute("response", responseList);

				return "home";
			}
			else{


			}
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
			return "ERROR";

		}
		return agentID;
	}

	public String getVerificationAvailabilityStatus(){

		PrintWriter out=null;
		String respMsg="";
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			agentID=(String)session.getAttribute("agentID");

			out=response.getWriter();

			String senderId=request.getParameter("SenderId");
			String BankCode=request.getParameter("BankCode");

			DMRApi dmrApi=new DMRApi();

			JSONObject respObj=dmrApi.getBankDetails(senderId, BankCode,tranKey,agentID);

			if(respObj!=null)
			{
				String status=respObj.getString("Status");
				if("0".equalsIgnoreCase(status)){
					String ifsc_status=respObj.getString("ifsc_status");
					String isverificationavailable=respObj.getString("isverificationavailable");
					if("1".equalsIgnoreCase(isverificationavailable) && "4".equalsIgnoreCase(ifsc_status))
						respMsg="IFSCRequired";
					else if("1".equalsIgnoreCase(isverificationavailable) && !"4".equalsIgnoreCase(ifsc_status))
						respMsg="Available";
					else
						respMsg="Bank not available for Account Verification.";
				}else{
					respMsg="Bank status not Available.";
				}
			}else{

			}

		} catch (Exception e) {
			e.printStackTrace();
			respMsg="Bank status not Available.";
		}
		out.print(respMsg);

		return null;
	}

	public String getVerificationIFSCStatus(){

		PrintWriter out=null;
		String respMsg="";
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			agentID=(String)session.getAttribute("agentID");
			
			out=response.getWriter();

			String senderId=request.getParameter("SenderId");
			String BankCode=request.getParameter("BankCode");

			DMRApi dmrApi=new DMRApi();

			JSONObject respObj=dmrApi.getBankDetails(senderId, BankCode,tranKey,agentID);

			if(respObj!=null)
			{
				String status=respObj.getString("Status");
				if("0".equalsIgnoreCase(status)){
					String ifsc_status=respObj.getString("ifsc_status");
					if("4".equalsIgnoreCase(ifsc_status))
						respMsg="IFSCRequired";
					else
						respMsg="Available";
				}else{
					respMsg="Available";
				}
			}else{

			}

		} catch (Exception e) {
			e.printStackTrace();
			respMsg="Bank status not Available.";
		}
		out.print(respMsg);

		return null;
	}


	public String regSenderResendOTP(){


		PrintWriter out=null;
		String respMsg="";
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			HashMap<String,String> AgentDetailData=(HashMap<String,String>)session.getAttribute("AgentDetailData");
			String tranKey=AgentDetailData.get("TxnKey");

			agentID=(String)session.getAttribute("agentID");
			
			out=response.getWriter();

			String senderId=request.getParameter("SenderId");

			DMRApi dmrApi=new DMRApi();

			JSONObject respObj=dmrApi.registerRessendOTP(senderId,tranKey,agentID);

			if(respObj!=null)
			{
				respMsg=respObj.getString("message");
			}else
				respMsg="Unable to resend OTP";


		} catch (Exception e) {
			e.printStackTrace();
			respMsg="Bank status not Available.";
		}
		out.print(respMsg);

		return null;

	}

	public String instantEkoCustomerKyc(){
		
		String agentID="",TxnKey="",customerMobile="";
		request=ServletActionContext.getRequest();
		try
		{
			
			agentID=request.getParameter("agentId");
			TxnKey=request.getParameter("TxnKey");
			customerMobile=request.getParameter("SenderId");
			
			LoginDao loginDao=new LoginDao();
			boolean loginStatus=loginDao.validateAgent(agentID, TxnKey);

			if(loginStatus) {
				String transactionId="SK"+GenerateIdDao.dateTime();
				
				request.setAttribute("transactionId", transactionId);
				request.setAttribute("SenderId", customerMobile);
				
				return "Success";
				
				/*MoneyTransferDao dao=new MoneyTransferDao();
				String insertStatus=dao.insertEKOKYCRequest(transactionId, customerMobile, Long.parseLong(agentID));
				if("Success".equalsIgnoreCase(insertStatus)){
					
					return "Success";
				}else{
					request.setAttribute("message", insertStatus);
				}*/
			}else {
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Technical failure. Please try later.");
		}
		
		return "error";
	}
	
	public String submitEkoCustomerKyc(){
		
		String aadhaar_xml_password="",aadhaarzip="",customerMobile="",transactionId="";
		request=ServletActionContext.getRequest();
		try
		{
			MoneyTransferDao dao=new MoneyTransferDao();
			aadhaar_xml_password=request.getParameter("aadhaar_xml_password");
			aadhaarzip=request.getParameter("aadhaarzip");
			transactionId=request.getParameter("transactionId");
			
			
			//dao.updateEKOKYCStatus(transactionId, "status", "api_ref_id", "message");
			
			return "Success";

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Technical failure. Please try later.");
		}
		
		return "error";
	}

}
