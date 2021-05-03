package in.softsolutionzone.provider.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.softsolutionzone.payout.model.ICICIClientRegistrationResponseModel;
import in.softsolutionzone.payout.model.ICICIClientRegistrationStatusResponseModel;
import in.softsolutionzone.payout.model.PayoutRequestModel;
import in.softsolutionzone.provider.call.ProviderCall;
import in.softsolutionzone.provider.services.ICICIProviderServices;
import in.softsolutionzone.util.ICICIEncryption;
import in.softsolutionzone.util.ProjectConstants;

@Service
public class ICICIProviderServicesImpl implements ICICIProviderServices {

	private static final Logger logger = LoggerFactory.getLogger(ICICIProviderServicesImpl.class);
	
	@Autowired
	ProviderCall providerCall;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public JSONObject payouts(PayoutRequestModel payoutRequestModel,String UNIQUEID) {
		// TODO Auto-generated method stub
		
		JSONObject transaction=null;
		
		try {
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("AGGRNAME", ProjectConstants.AGGRNAME);
			iciciRequestJSON.put("DEBITACC", ProjectConstants.DEBITACC);
			iciciRequestJSON.put("CURRENCY", ProjectConstants.CURRENCY);
			
			iciciRequestJSON.put("CREDITACC", payoutRequestModel.getCredit_account());
			iciciRequestJSON.put("IFSC", payoutRequestModel.getIfs_code());
			iciciRequestJSON.put("AMOUNT", payoutRequestModel.getCredit_amount());
			iciciRequestJSON.put("TXNTYPE", payoutRequestModel.getSp_key());
			iciciRequestJSON.put("REMARKS", payoutRequestModel.getRemarks());
			iciciRequestJSON.put("PAYEENAME", payoutRequestModel.getBene_name());
			iciciRequestJSON.put("UNIQUEID", UNIQUEID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			//iciciRequestJSON.put("OTP", "N");
			//iciciRequestJSON.put("WORKFLOW_REQD", "N");
			
			//TODO encryption of request
			logger.info("ICICIPayoutResponseModel iciciRequestJSON : "+iciciRequestJSON);
			
			String url=ProjectConstants.ICICI_PAYOUT_TRANSACTION;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			logger.info("ICICIPayoutResponseModel iciciResponse : "+iciciResponse);
			if(iciciResponse!=null) {
				
				String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
				logger.info("ICICIPayoutResponseModel decryptedResponse : "+decryptedResponse);
				transaction=new JSONObject(decryptedResponse);
			}
				
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		
		return transaction;
	}

	@Override
	public ICICIClientRegistrationResponseModel registration(String UNIQUEID) {
		// TODO Auto-generated method stub
		ICICIClientRegistrationResponseModel clientRegistrationResponseModel=null;
		try {
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("REQUESTFROM", ProjectConstants.REQUESTFROM);
			iciciRequestJSON.put("REQUESTTYPE", ProjectConstants.REQUESTTYPE);
			iciciRequestJSON.put("AGGRNAME", ProjectConstants.AGGRNAME);
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			iciciRequestJSON.put("BANKID", ProjectConstants.BANKID);
			
			//TODO encryption of request
			logger.info("ICICIClientRegistrationResponseModel : "+iciciRequestJSON);
			
			String url=ProjectConstants.ICICI_PAYOUT_REGISTRATION;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			logger.info("ICICIClientRegistrationResponseModel iciciResponse : "+iciciResponse);
			if(iciciResponse!=null) {
				
				String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
				logger.info("ICICIClientRegistrationResponseModel decryptedResponse : "+decryptedResponse);
				
				clientRegistrationResponseModel=mapper.map(decryptedResponse, ICICIClientRegistrationResponseModel.class);
			
			}else{
				
				
				
			}
				
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return clientRegistrationResponseModel;
	}

	@Override
	public ICICIClientRegistrationStatusResponseModel registrationStatus(String UNIQUEID) {
		// TODO Auto-generated method stub
		ICICIClientRegistrationStatusResponseModel clientRegistrationResponseModel=null;
		try {
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("AGGRNAME", ProjectConstants.AGGRNAME);
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			
			//TODO encryption of request
			
			logger.info("ICICIClientRegistrationStatusResponseModel iciciRequestJSON : "+iciciRequestJSON);
			
			String url=ProjectConstants.ICICI_PAYOUT_REGISTRATION_STATUS;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			logger.info("ICICIClientRegistrationStatusResponseModel iciciResponse : "+iciciResponse);
			if(iciciResponse!=null) {
				
				String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
				logger.info("ICICIClientRegistrationStatusResponseModel decryptedResponse : "+decryptedResponse);
				
				clientRegistrationResponseModel=mapper.map(decryptedResponse, ICICIClientRegistrationStatusResponseModel.class);
			
			}else{
				
				
				
			}
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return clientRegistrationResponseModel;
	}
	
	
	@Override
	public JSONObject transactionStatus(String UNIQUEID) {
		// TODO Auto-generated method stub
		JSONObject transactionStatus=null;
		try {
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("UNIQUEID",UNIQUEID);
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			
			//TODO encryption of request
			
			logger.info("transactionStatus iciciRequestJSON : "+iciciRequestJSON);
			
			String url=ProjectConstants.ICICI_TRANSACTION_ENQUIRY;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			logger.info("transactionStatus iciciResponse : "+iciciResponse);
			if(iciciResponse!=null) {
				
				String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
				logger.info("transactionStatus decryptedResponse : "+decryptedResponse);
				transactionStatus=new JSONObject(decryptedResponse);
;			
			}else{
				
				
				
			}
		
		
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return transactionStatus;
	}
	
	@Override
	public JSONObject balanceheck() {
		// TODO Auto-generated method stub
		JSONObject balance=null;
		try {
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			iciciRequestJSON.put("ACCOUNTNO", ProjectConstants.DEBITACC);
			//TODO encryption of request
			
			
			String url=ProjectConstants.ICICI_BALANCE_ENQUIRY;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			if(iciciResponse!=null) {
				
				String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
				logger.info("transactionStatus decryptedResponse : "+decryptedResponse);
				balance=new JSONObject(decryptedResponse);
			
			}
		
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return balance;
	}
	@Override
	public JSONObject getStatement(String fromDate,String toDate) {
		// TODO Auto-generated method stub
		JSONObject statement=null;
		try {
			
			JSONObject iciciRequestJSON=new JSONObject();
			iciciRequestJSON.put("CORPID", ProjectConstants.CORPID);
			iciciRequestJSON.put("USERID", ProjectConstants.USERID);
			iciciRequestJSON.put("AGGRID", ProjectConstants.AGGRID);
			iciciRequestJSON.put("URN", ProjectConstants.URN);
			iciciRequestJSON.put("ACCOUNTNO", ProjectConstants.DEBITACC);
			iciciRequestJSON.put("FROMDATE", fromDate);
			iciciRequestJSON.put("TODATE", toDate);
			//TODO encryption of request
			
			logger.info("getStatement iciciRequestJSON : "+iciciRequestJSON);
			
			String url=ProjectConstants.ICICI_STATEMENT;
			String encrypterRequest=ICICIEncryption.encrypt(iciciRequestJSON.toString().getBytes("UTF-8"));
			String method="POST";
			
			Map<String,String> headerMaps=new HashMap<String, String>();
			//headerMaps.put("accept", "*/*");
			////headerMaps.put("content-length", String.valueOf(encrypterRequest.length()));
			headerMaps.put("content-type",MediaType.TEXT_PLAIN);
			headerMaps.put("APIKEY", ProjectConstants.APIKEY);
			
			String iciciResponse=providerCall.apiCallWithStringResponse(url, encrypterRequest, method, headerMaps);
			if(iciciResponse!=null) {
				statement=new JSONObject(iciciResponse);	
				String encryptedKey=statement.getString("encryptedKey");
				String encryptedData=statement.getString("encryptedData");
				String decryptedData=ICICIEncryption.doubleDecryption(encryptedKey, encryptedData);
				if(decryptedData!=null)
					statement=new JSONObject(decryptedData);
				else
					statement=null;
			}else{
				
			}
		
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("JSONException", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception", e);
		}
		return statement;
	}

}
