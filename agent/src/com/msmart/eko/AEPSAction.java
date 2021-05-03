package com.msmart.eko;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import net.sf.json.JSONObject;

import com.common.BackButtonDao;
import com.common.GenerateIdDao;
import com.common.MRAController;
import com.common.PropertyFile;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.smartkinda.model.AEPSForm;
import com.utility.HmacSHA256;

public class AEPSAction extends ActionSupport implements ModelDriven<Object>{

	Logger logger=Logger.getLogger(AEPSAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	private AEPSForm apsForm=new AEPSForm();  

	public Object getModel()
	{
		return apsForm;
	} 

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		session=request.getSession(false);
		PrintWriter out=response.getWriter();
		JSONObject resoJson=new JSONObject(); 
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String param=request.getParameter("param");
			logger.info("TEP ,Class is AccountStatementAction ,Param is "+param);
			String agentId=(String)session.getAttribute("agentID");
			if(param.equalsIgnoreCase("reuestOTP")){

				String mobile=request.getParameter("mobile");
				String data="initiator_id="+PropertyFile.Initiator_id+"&mobile="+mobile;
				String respString=MRAController.ekoCall(PropertyFile.REQ_OTP, data,"PUT");

				if(respString!=null) {
					resoJson=JSONObject.fromObject(respString);
				}else {
					resoJson.put("status", "1");
					resoJson.put("message", "Technical failure");
				}
				out.print(resoJson);
				out.close();
				return null;

			}
			else if (param.equalsIgnoreCase("VerifyOTP")) {

				String mobile=request.getParameter("mobile");
				String otp=request.getParameter("otp");

				String data="initiator_id="+PropertyFile.Initiator_id+"&mobile="+mobile+"&otp="+otp;
				String respString=MRAController.ekoCall(PropertyFile.VERIFY_MOBILE, data,"PUT");

				if(respString!=null) {
					resoJson=JSONObject.fromObject(respString);
				}else {
					resoJson.put("status", "1");
					resoJson.put("message", "Technical failure");
				}

				out.print(resoJson);
				out.close();
				return null;
			}else if (param.equalsIgnoreCase("onboard")) {

				String mobile=request.getParameter("mobile");
				String email=request.getParameter("email");
				String pan=request.getParameter("pan");
				String first_name=request.getParameter("f_name");
				String last_name=request.getParameter("l_name");
				String dob=request.getParameter("dob");
				String shop_name=request.getParameter("shop_name");
				String state=request.getParameter("state");
				String address=request.getParameter("address");
				String district=request.getParameter("district");
				String pincode=request.getParameter("pincode");

				JSONObject residence_address=new JSONObject();
				residence_address.put("line", address);
				residence_address.put("city", district);
				residence_address.put("state", state);
				residence_address.put("pincode", pincode);
				residence_address.put("area", address);

				String data="initiator_id="+PropertyFile.Initiator_id+"&mobile="+mobile+"&pan_number="+pan+"&first_name="+first_name+"&last_name="+last_name+"&email="+email+
						"&residence_address="+residence_address+"&dob="+dob+"&shop_name="+shop_name;
				String respString=MRAController.ekoCall(PropertyFile.ONBOARD_USER, data,"PUT");

				if(respString!=null) {
					resoJson=JSONObject.fromObject(respString);
					int status=resoJson.getInt("status");
					int response_type_id=resoJson.getInt("response_type_id");
					if(status==0 && (response_type_id==1290 || response_type_id==1307)) {

						String userCode=resoJson.getJSONObject("data").getString("user_code");

						MoneyTransferDao moneyTransferDao=new MoneyTransferDao();
						moneyTransferDao.updateEKOAEPSUserCode(agentId, userCode);
					}

				}else {
					resoJson.put("status", "1");
					resoJson.put("message", "Technical failure");
				}
				apsForm=new AEPSForm();

				out.print(resoJson);
				out.close();
				return null;
			}
			else{

				request.setAttribute("message", "please contact channel Partner");

			}
			return "home";

		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
			resoJson.put("status", "1");
			resoJson.put("message", "Technical failure");
			out.print(resoJson);
			out.close();
			return null;
		}
	}

	/*public String validateAepsTransaction() {

		try {

			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession(false);
			PrintWriter out=response.getWriter();
			JSONObject resoJson=new JSONObject(); 
			if(session.getAttribute("agentID")==null){

				resoJson.put("status", false);
				resoJson.put("message", "Your Login Session has Expired. Please Login Again.");
			}else {
				MoneyTransferDao dao=new MoneyTransferDao();
				String agentId=(String)session.getAttribute("agentID");
				String userCode=dao.fetchEKOAEPSUserCode(agentId);

				String ip=request.getRemoteAddr();
				String amount=request.getParameter("amount");
				String customer_id=request.getParameter("customer_id");
				String bank_code=request.getParameter("bank_code");
				String user_code=request.getParameter("user_code");
				String type=request.getParameter("type");
				String client_ref_id=request.getParameter("client_ref_id");
				String request_hash_params=request.getParameter("request_hash_params");
				String[] request_hash_paramsArr=request_hash_params.split(",");

				String req=client_ref_id+"|"+amount+"|"+customer_id+"|"+bank_code+"|"+user_code+"|"+type;
				String tranType="";
				if("2".equalsIgnoreCase(type))
					tranType="AEPS-Withdrawal";
				else if("3".equalsIgnoreCase(type)) {
					tranType="AEPS-Enquiry";
					amount="0";
				}
				else
					tranType="";
				if("".equalsIgnoreCase(amount))
					amount="0";

				if(userCode.equalsIgnoreCase(user_code)) {

					String insertStatus=dao.saveAepsLog(user_code, client_ref_id, "validate", req, "GO", ip,tranType,Double.parseDouble(amount));
					if("Y".equalsIgnoreCase(insertStatus)) {
						Date date = new Date();
						String secret_key_timestamp = Long.toString(date.getTime());

						JSONObject eko_gateway_request=new JSONObject();
						eko_gateway_request.put("action", "go");
						eko_gateway_request.put("allow", true);
						eko_gateway_request.put("secret_key", HmacSHA256.activateEKyc(secret_key_timestamp,PropertyFile.KEY));
						eko_gateway_request.put("secret_key_timestamp", secret_key_timestamp);
						eko_gateway_request.put("client_ref_id", client_ref_id);
						String request_hash=secret_key_timestamp;
						for (int i = 0; i < request_hash_paramsArr.length; i++) {
							
							String val=request_hash_paramsArr[i];
							if("customer_id".equalsIgnoreCase(val))
								request_hash=request_hash+customer_id;
							if("amount".equalsIgnoreCase(val) && "2".equalsIgnoreCase(type))
								request_hash=request_hash+amount;
							if("user_code".equalsIgnoreCase(val))
									request_hash=request_hash+user_code;
						}
						String request_hash_key=HmacSHA256.activateEKyc(request_hash,PropertyFile.KEY);
						eko_gateway_request.put("request_hash", request_hash_key);
						JSONObject data=new JSONObject();
						data.put("eko_gateway_request", eko_gateway_request);
						resoJson.put("data", data);
						System.out.println("resoJson :: "+resoJson);

					}else {
						JSONObject eko_gateway_request=new JSONObject();
						eko_gateway_request.put("action", "go");
						eko_gateway_request.put("allow", false);
						eko_gateway_request.put("message", "Technical failure");
						JSONObject data=new JSONObject();
						data.put("eko_gateway_request", eko_gateway_request);
						resoJson.put("data", data);
					}

				}else {
					JSONObject eko_gateway_request=new JSONObject();
					eko_gateway_request.put("action", "go");
					eko_gateway_request.put("allow", false);
					eko_gateway_request.put("message", "Invalid user code");
					JSONObject data=new JSONObject();
					data.put("eko_gateway_request", eko_gateway_request);
					resoJson.put("data", data);
				}

			}

			out.print(resoJson);
			out.close();
			return null;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	public String updateAepsTransaction() {

		try {
			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession(false);
			JSONObject resoJson=new JSONObject(); 
			if(session.getAttribute("agentID")==null){

				resoJson.put("status", false);
				resoJson.put("message", "Your Login Session has Expired. Please Login Again.");
			}else {

				MoneyTransferDao dao=new MoneyTransferDao();
				String agentId=(String)session.getAttribute("agentID");
				String userCode=dao.fetchEKOAEPSUserCode(agentId);
				String ip=request.getRemoteAddr();

				String amount=request.getParameter("amount");
				String sender_name=request.getParameter("sender_name");
				String tid=request.getParameter("tid");
				String bank_ref_num=request.getParameter("bank_ref_num");
				String user_code=request.getParameter("user_code");
				String commission=request.getParameter("commission");
				String client_ref_id=request.getParameter("client_ref_id");
				String tds=request.getParameter("tds");
				String tx_status=request.getParameter("tx_status");

				if("0".equalsIgnoreCase(tx_status)) {

					if("".equalsIgnoreCase(tds))
						tds="0";
					if("".equalsIgnoreCase(commission))
						commission="0";
					if("".equalsIgnoreCase(amount))
						amount="0";

					String tranData=ip+"|"+amount+"|"+sender_name+"|"+tid+"|"+bank_ref_num+"|"+user_code+"|"+client_ref_id;

					if(userCode.equalsIgnoreCase(user_code)) {
						try {
							dao.updateAepsLog(userCode, client_ref_id, tranData);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						String creditStatus=dao.aepsCredit(tid, client_ref_id, bank_ref_num, user_code, Double.parseDouble(commission), Double.parseDouble(tds), Double.parseDouble(amount),  ip);
						logger.info("Status :"+creditStatus);
						System.out.println("Status :"+creditStatus);
					}

				}


			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}*/

	public String aeps(){
		request=ServletActionContext.getRequest();
		session=request.getSession(false);
		try {
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String service_desc="",message="";
			String agentId=(String)session.getAttribute("agentID");
			MoneyTransferDao moneyTransferDao=new MoneyTransferDao();
			int ekoStatus=moneyTransferDao.fetchEkoAEPS(agentId);
			String userCode=moneyTransferDao.fetchEKOAEPSUserCode(agentId);
			if((userCode!=null && !"".equalsIgnoreCase(userCode)) && (ekoStatus==2 || ekoStatus==4)) {
				org.json.JSONObject resp=MRAController.userServiceEnquiry(userCode);
				logger.info("resp : "+resp);
				if(resp!=null) {
					int status=resp.getInt("status");
					if(status==0) {
						org.json.JSONObject data=resp.getJSONObject("data");
						JSONArray service_status_list=data.getJSONArray("service_status_list");
						for(int i=0;i<service_status_list.length();i++) {
							org.json.JSONObject service=service_status_list.getJSONObject(i);
							String service_code=service.getString("service_code");
							if("43".equalsIgnoreCase(service_code) || "1".equalsIgnoreCase(service_code)) {
								int service_status=service.getInt("status");
								service_desc=service.getString("status_desc");
								message=service.getString("comments");
								moneyTransferDao.updateEKOAEPSStatus(agentId, service_status);
								if(service_status==1)
									ekoStatus=1;
								else {
									
								}

								break;
							}
						}
					}else {
						service_desc="Not Available";
						message=resp.getString("comments");
					}
				}

			}

			request.setAttribute("service_desc", service_desc);
			request.setAttribute("message", message);
			session.setAttribute("eko_aeps", ekoStatus);
			return "Activate";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "input";
		}
	}
	
	public String getSecretKey() {

		try {
			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession(false);
			PrintWriter out=response.getWriter();
			JSONObject resoJson=new JSONObject(); 
			if(session.getAttribute("agentID")==null){

				resoJson.put("status", false);
				resoJson.put("message", "Your Login Session has Expired. Please Login Again.");
			}else {

				MoneyTransferDao dao=new MoneyTransferDao();
				String agentId=(String)session.getAttribute("agentID");
				String userCode=dao.fetchEKOAEPSUserCode(agentId);

				Date date = new Date();
				String secret_key_timestamp = Long.toString(date.getTime());
				String secret_key= HmacSHA256.activateEKyc(secret_key_timestamp,PropertyFile.KEY);

				resoJson.put("status", true);
				JSONObject params=new JSONObject();
				params.put("developer_key", PropertyFile.DEVELOPER_KEY);
				params.put("secret_key", secret_key);
				params.put("secret_key_timestamp", secret_key_timestamp);
				params.put("initiator_id", PropertyFile.Initiator_id);
				params.put("user_code", userCode);
				params.put("initiator_logo_url", PropertyFile.PARTNER_LOGO_URL);
				params.put("partner_name", PropertyFile.PARTNE_NAME);
				params.put("language", "en");
				params.put("is_light_header", "true");
				params.put("callback_url", PropertyFile.EKO_AEPS_CALLBACK_URL);
				resoJson.put("params", params);
			}

			out.print(resoJson);
			out.close();
			return null;


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public String activateAeps(){
		request=ServletActionContext.getRequest();
		session=request.getSession(false);
		try {
			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			String message="";
			String agentId=(String)session.getAttribute("agentID");

			JSONObject office_address=new JSONObject();
			office_address.put("line", request.getParameter("shop_address"));
			office_address.put("city", request.getParameter("shop_district"));
			office_address.put("state", request.getParameter("shop_state"));
			office_address.put("pincode", request.getParameter("shop_pincode"));
			office_address.put("area",apsForm.getAddress());

			String service="AEPS Activation",operator="AEPS Activation";

			String ipaddress=request.getRemoteHost();
			String transactionId=GenerateIdDao.getAETranId(agentId); 
			String tranNo=GenerateIdDao.getIdNo();
			double amt=499;

			BackButtonDao dao=new BackButtonDao();
			String insertStatus=dao.accountStatementInsert(transactionId, Long.parseLong(agentId), operator, amt, tranNo, service, ipaddress,"EKO",apsForm.getUserCode());
			if("inserted".equalsIgnoreCase(insertStatus)) {

				String data="service_code=43&initiator_id="+PropertyFile.Initiator_id+"&user_code="+apsForm.getUserCode()+"&devicenumber="+apsForm.getDevicenumber()+"&modelname="+apsForm.getModelName()+"&office_address="+office_address+"&address_as_per_proof="+office_address;
				dao.insertEKOLogs("EKO", transactionId, data, agentId);
				String respString=MRAController.activateAEPS(data, apsForm);
				if(respString!=null) {
					dao.updateEKOLogs(transactionId, respString, agentId);
					JSONObject respJson=JSONObject.fromObject(respString);
					message=respJson.getString("message");
					int status=respJson.getInt("status");
					if(status==0) {

						JSONObject jsondata=respJson.getJSONObject("data");
						String serviceStatus=jsondata.getString("service_status");
						MoneyTransferDao moneyTransferDao=new MoneyTransferDao();
						int updateStatus= moneyTransferDao.updateEKOAEPSStatus(agentId, Integer.parseInt(serviceStatus));;
						
						String dbupdateStatus=dao.accountStatementUpdateStatus(transactionId, Long.parseLong(agentId), "Success");
						logger.info("dbupdateStatus :: "+dbupdateStatus);
						session.setAttribute("eko_aeps", Integer.parseInt(serviceStatus));
						logger.info("AEPS Status : "+updateStatus);
					}else {
						String dbupdateStatus=dao.accountStatementUpdateStatus(transactionId, Long.parseLong(agentId), "Failure");
						logger.info("dbupdateStatus :: "+dbupdateStatus);
						
					}
					
				}else {

					message="Technical failure.";

				}

			}else {

				if("insufficient_balance".equalsIgnoreCase(insertStatus)) 
					message="You do not have sufficient balance to activate this service.";
				else if("commisson_error".equalsIgnoreCase(insertStatus))
					message="Your surcharge is not set properly. Contact to Customer Care";
				else
					message="Process aborted due to technical failure.";


			}
			request.setAttribute("message", message);

			return "Activate";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "input";
		}
	}


}
