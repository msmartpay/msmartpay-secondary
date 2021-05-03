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

import in.softsolutionzone.provider.call.ProviderCall;
import in.softsolutionzone.provider.services.ChannelPartnerProviderServices;
import in.softsolutionzone.util.ICICIEncryption;
import in.softsolutionzone.util.ProjectConstants;

@Service
public class ChannelPartnerProviderServicesImpl implements ChannelPartnerProviderServices {

	private static final Logger logger = LoggerFactory.getLogger(ChannelPartnerProviderServicesImpl.class);

	@Autowired
	ProviderCall providerCall;

	@Autowired
	ModelMapper mapper;

	@Override
	public JSONObject ecollection(JSONObject request) {
		// TODO Auto-generated method stub

		JSONObject transaction=null;

		try {

			//TODO encryption of request
			logger.info("ecollection : "+request);

			String[] MSMARTPAY_URLS=ProjectConstants.MSMARTPAY_URLS;

			if(MSMARTPAY_URLS!=null && MSMARTPAY_URLS.length>0) {

				for (int i = 0; i < MSMARTPAY_URLS.length; i++) {
					
					String url=MSMARTPAY_URLS[i];
					String method="POST";
					Map<String,String> headerMaps=new HashMap<String, String>();
					headerMaps.put("content-type",MediaType.APPLICATION_JSON);
					headerMaps.put("APIKEY", ProjectConstants.MSMARTPAY_KEY);

					String iciciResponse=providerCall.apiCallWithStringResponse(url, request.toString(), method, headerMaps);
					logger.info("ICICIPayoutResponseModel iciciResponse : "+iciciResponse);
					if(iciciResponse!=null) {

						String decryptedResponse=ICICIEncryption.decrypt(iciciResponse);
						logger.info("ICICIPayoutResponseModel decryptedResponse : "+decryptedResponse);
						transaction=new JSONObject(decryptedResponse);
					}
				}
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


}
