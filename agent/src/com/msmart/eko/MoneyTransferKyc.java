package com.msmart.eko;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.login.LoginDao;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smartkinda.model.KYCForm;

import net.sf.json.JSONObject;

public class MoneyTransferKyc extends ActionSupport implements ModelDriven<Object>{

	Logger logger=Logger.getLogger(MoneyTransferKyc.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private KYCForm kycForm=new KYCForm();  
	
	public Object getModel()
	{
		return kycForm;
	} 

	/*public String dmrCustomerKyc(){
		
		String agentID="";
		request=ServletActionContext.getRequest();
		session=request.getSession(false);
		try
		{
			
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			
			String customerMobile=request.getParameter("SenderId");
			

			String transactionId="SK"+GenerateIdDao.dateTime();
			
			request.setAttribute("transactionId", transactionId);
			request.setAttribute("SenderId", customerMobile);
			
			
			
			MoneyTransferDao dao=new MoneyTransferDao();
			String insertStatus=dao.insertEKOKYCRequest(transactionId, customerMobile, Long.parseLong(agentID));
			if("Success".equalsIgnoreCase(insertStatus)){
				
				return "Success";
			}else{
				request.setAttribute("message", insertStatus);
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Technical failure. Please try later.");
		}
		
		return "dmrhome";
	}*/
	
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
				
				request.setAttribute("senderId", customerMobile);
				
				request.setAttribute("senderId", customerMobile);
				return "Success";
				/*MoneyTransferDao dao=new MoneyTransferDao();
				String insertStatus=dao.insertEKOKYCRequest(transactionId, customerMobile, Long.parseLong(agentID));
				if("Success".equalsIgnoreCase(insertStatus)){
					
					return "Success";
				}else{
					request.setAttribute("message", insertStatus);
				}*/
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Technical failure. Please try later.");
		}
		
		return "error";
	}
	
	public String submitEkoCustomerKyc(){
		
		String aadhaar_xml_password="",aadhaarzip="",customerMobile="",transactionId="",message="";
		request=ServletActionContext.getRequest();
		
		try
		{
			aadhaar_xml_password=kycForm.getZipPass();
			customerMobile=kycForm.getSenderId();
			aadhaarzip=kycForm.getAadhaarzip().getAbsolutePath();
			
			File file=kycForm.getAadhaarzip();
			String fileName=kycForm.getAadhaarzipFileName();
			
			
			String kycStatus=DMRApi.instantEkoKyc(aadhaar_xml_password,file, fileName, customerMobile);
			JSONObject kycStatusJson=JSONObject.fromObject(kycStatus);
			if(kycStatus!=null) {
				int status=kycStatusJson.getInt("status");
				if(status==1354)
					message=kycStatusJson.getString("message");
				else if(status==463) 
					message=kycStatusJson.getString("message");
				else if(status==21) 
					message=kycStatusJson.getString("message");
				else
					message=kycStatusJson.getString("message");
			}
			//dao.updateEKOKYCStatus(transactionId, "status", "api_ref_id", "message");
			request.setAttribute("message",message);
			return "Success";

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("message", "Technical failure. Please try later.");
		}
		
		return "error";
	}
	
	
}
