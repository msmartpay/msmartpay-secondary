package in.softsolutionzone.services.impl;

import javax.servlet.ServletContext;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.softsolutionzone.model.ICICICollectionEncryptedResponseModel;
import in.softsolutionzone.model.ICICICollectionRequestModel;
import in.softsolutionzone.provider.services.ChannelPartnerProviderServices;
import in.softsolutionzone.services.ECollectionServices;
import in.softsolutionzone.util.ICICIEncryption;

@Service
public class ECollectionServicesImpl implements ECollectionServices{
	private static final Logger logger = LoggerFactory.getLogger(ECollectionServicesImpl.class);
	
	@Autowired
	ChannelPartnerProviderServices channelPartnerProviderServices;

	@Override
	public ICICICollectionEncryptedResponseModel ecollectionRequest(ICICICollectionRequestModel request,ServletContext context) {
		// TODO Auto-generated method stub
		ICICICollectionEncryptedResponseModel responseModel=null;
		try {

			String encryptedKey=request.getEncryptedKey();
			String encryptedData=request.getEncryptedData();
			String decryptedData=ICICIEncryption.doubleDecryption(encryptedKey, encryptedData);
			logger.debug("Service msg : "+decryptedData);
			JSONObject data=new JSONObject();
			if(decryptedData!=null) {

				JSONObject req=new JSONObject(decryptedData);
				if(context.getAttribute(req.getString("UTR"))==null) {
					context.setAttribute(req.getString("UTR"), req.getString("UTR"));

					data.put("SuccessANDRejected", "Successful Transaction");
					data.put("CODE", "11");
					
					try {
						  Thread thread = new Thread(){
						    public void run(){
						    	channelPartnerProviderServices.ecollection(req);
						    }
						  };

						  thread.start();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else {

					data.put("SuccessANDRejected", "Duplicate UTR");
					data.put("CODE", "06");
				}
			}else {
				data.put("SuccessANDRejected", "Technical failure");
				data.put("CODE", "1");
				logger.error("Exception", data);
			}
			logger.debug("Response : "+data);
			responseModel=ICICIEncryption.encryptDataByAES(data.toString());
			responseModel.setService(request.getService());
			responseModel.setRequestId(request.getRequestId());

			return responseModel;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception", e);
			JSONObject data=new JSONObject();
			data.put("SuccessANDRejected", "Technical failure");
			data.put("CODE", "1");
			logger.debug("Response : "+data);
			responseModel=ICICIEncryption.encryptDataByAES(data.toString());
			responseModel.setService(request.getService());
			responseModel.setRequestId(request.getRequestId());
			return responseModel;
		}

	}

}
