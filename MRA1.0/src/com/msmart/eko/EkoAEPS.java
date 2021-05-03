package com.msmart.eko;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.msmart.dao.RegistrationDao;
import com.msmart.service.PropertyFile;

import net.sf.json.JSONObject;

public class EkoAEPS {

	Logger log=Logger.getLogger(EkoAEPS.class);

	public JSONObject requestOTP(String mobile) {

		JSONObject respJson=new JSONObject();

		try {

			EkoAPICall ekoAPICall=new EkoAPICall();

			String data="initiator_id="+PropertyFile.initiator_id+"&mobile="+mobile;
			respJson=ekoAPICall.ekoCall(PropertyFile.REQ_OTP, data,"PUT");

			if(respJson!=null)
			{
				int status=respJson.getInt("status");
				int response_type_id=respJson.getInt("response_type_id");
				if(status==0 && response_type_id==1282){
					respJson.put("Status", "0");
				}else if(status==0 && response_type_id==1317){
					respJson.put("Status", "2");
				}else{
					respJson.put("Status", "1");
				}
				
			}else {
				respJson=new JSONObject();
				respJson.put("Status", "1");
				respJson.put("message", "Technical failure");
			}


		} catch (Exception e) {
			e.printStackTrace();
			respJson=new JSONObject();
			respJson.put("Status","1");
			respJson.put("message", "Unable to process your request. Please try later.");
		}
		return respJson;
	}

	public JSONObject verifyMobile(String mobile,String otp){

		JSONObject respJson=new JSONObject();
		try{
			EkoAPICall ekoAPICall=new EkoAPICall();

			String data="initiator_id="+PropertyFile.initiator_id+"&mobile="+mobile+"&otp="+otp;

			log.info("Verify Sender Request : "+PropertyFile.VERIFY_MOBILE+"?"+data);

			respJson = ekoAPICall.ekoCall(PropertyFile.VERIFY_MOBILE, data,"PUT");
			log.info("Verify Sender Response  : "+respJson);
			if (respJson!= null){
				int status=respJson.getInt("status");
				int response_type_id=respJson.getInt("response_type_id");
				if(status==0 && response_type_id==876){
					respJson.put("Status", "0");
				}else if(status==302) {
					respJson.put("Status", "2");
				}else{
					respJson.put("Status", "1");
				}
			}else {
				respJson=new JSONObject();
				respJson.put("Status", "1");
				respJson.put("message", "Technical failure");
			}

		}catch(Exception e){
			e.printStackTrace();

			respJson=new JSONObject();
			respJson.put("Status", "1");
			respJson.put("message", "Unable to process your request. Please try Later");
		}

		return respJson;
	}

	public JSONObject userOnboard(JSONObject reqObject){

		JSONObject respJson = new JSONObject();
		try {
			String agentId=reqObject.getString("AgentID");
			String mobile=reqObject.getString("Mobile");
			String first_name=reqObject.getString("firstname");
			String middle_name=reqObject.getString("m_name");
			String last_name=reqObject.getString("lastname");
			
			RegistrationDao registrationDao=new RegistrationDao();
			HashMap<String, String> profile=registrationDao.getprofile(agentId);
			
			String dob=profile.get("DateOfBirth");
			String email=profile.get("emailID");
			String pan=profile.get("pannumber");
			String shop_name=profile.get("firmname");
			String state=profile.get("state");
			String address=profile.get("address");
			String district=profile.get("district");
			String pincode=profile.get("pincode");

			JSONObject residence_address=new JSONObject();
			residence_address.put("line", address);
			residence_address.put("city", district);
			residence_address.put("state", state);
			residence_address.put("pincode", pincode);
			residence_address.put("area", address);

			String data="initiator_id="+PropertyFile.initiator_id+"&mobile="+mobile+"&pan_number="+pan+"&first_name="+first_name+"&middle_name="+middle_name+"&last_name="+last_name+"&email="+email+
					"&residence_address="+residence_address+"&dob="+dob+"&shop_name="+shop_name;

			EkoAPICall ekoAPICall= new EkoAPICall();
			log.info("User Onboard Sender Request : "+PropertyFile.ONBOARD_USER+"?"+data);
			respJson=ekoAPICall.ekoCall(PropertyFile.ONBOARD_USER, data,"PUT");
			log.info("User Onboard Sender Response : "+respJson);
			if(respJson!=null) {
				int status=respJson.getInt("status");
				int response_type_id=respJson.getInt("response_type_id");
				if(status==0 && (response_type_id==1290 || response_type_id==1307)) {

					String userCode=respJson.getJSONObject("data").getString("user_code");

					EkoDMTDao dao=new EkoDMTDao();
					dao.updateEKOAEPSUserCode(agentId, userCode);
					
					respJson.put("Status", "0");
				}
				else
					respJson.put("Status", "1");

			}else {
				respJson=new JSONObject();
				respJson.put("Status", "1");
				respJson.put("message", "Technical failure");
			}

		} catch (Exception e) {
			e.printStackTrace();

			respJson.put("Status", "1");
			respJson.put("message", "We are unable to process your request. Please try later.");
			return respJson;
		}
		return respJson;
	}
}
