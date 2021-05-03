package in.softsolutionzone.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.softsolutionzone.payout.model.ICICIBalanceResponseModel;
import in.softsolutionzone.payout.model.ICICIClientRegistrationResponseModel;
import in.softsolutionzone.payout.model.ICICIClientRegistrationStatusResponseModel;
import in.softsolutionzone.payout.model.ICICIPayoutResponseModel;
import in.softsolutionzone.payout.model.ICICIStatementResponseModel;
import in.softsolutionzone.payout.model.PayoutRequestModel;
import in.softsolutionzone.payout.model.StatementResponse;
import in.softsolutionzone.provider.services.ICICIProviderServices;
import in.softsolutionzone.services.PayoutServices;
import in.softsolutionzone.util.CustomResponse;
import in.softsolutionzone.util.Util;

@Service
public class PayoutServicesImpl implements PayoutServices {
	
	private static final Logger logger = LoggerFactory.getLogger(PayoutServicesImpl.class);
	
	@Autowired
	ICICIProviderServices iCICIProviderServices;

	@Autowired
	Util util;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public ResponseEntity<?> payouts(PayoutRequestModel payoutRequestModel) {
		// TODO Auto-generated method stub

		ICICIPayoutResponseModel iciciPauoutResponseModel=null;
		
		String transactionStatus="",UTRNUMBER="",URN="",message="";;
		
		String UNIQUEID=util.generate26DigitUniqueTransactionId("MS","2001");
		
		try {
			JSONObject transaction=iCICIProviderServices.payouts(payoutRequestModel,payoutRequestModel.getExternal_ref());
			logger.info("ICICI response : "+transaction);
			if(transaction!=null) {

				transactionStatus=transaction.getString("STATUS");
				URN=transaction.optString("URN");

				if("SUCCESS".equalsIgnoreCase(transactionStatus)) {

					message="Txn Successful";

					UTRNUMBER=transaction.optString("UTRNUMBER");
					transactionStatus="SUCCESS";
				}else if("PENDING".equalsIgnoreCase(transactionStatus)) {

					message="Txn posted successfully. Bank response awaited";
					UTRNUMBER=transaction.optString("UTRNUMBER");
					transactionStatus="INITIATED";
				}else if("Pending For Processing".equalsIgnoreCase(transactionStatus)) {

					message="Txn posted successfully";

					UTRNUMBER=transaction.optString("UTRNUMBER");
					transactionStatus="INITIATED";
				}else if("DUPLICATE".equalsIgnoreCase(transactionStatus) || "FAILURE".equalsIgnoreCase(transactionStatus)) {

					String RESPONSECODE=transaction.optString("RESPONSECODE");
					if("954".equalsIgnoreCase(RESPONSECODE)){
						transactionStatus="INITIATED";
					}else {
						transactionStatus="FAILURE";
					}
				}

			}else {
				message="Transaction Under Process";
				transactionStatus="PENDING";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.error("Exception ", e);
			message="Transaction Under Process";
			transactionStatus="PENDING";
		}
		
		iciciPauoutResponseModel=new ICICIPayoutResponseModel(UNIQUEID, payoutRequestModel.getExternal_ref(), UTRNUMBER, transactionStatus, message);

		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setStatus(0)
				.setPayload(iciciPauoutResponseModel)
				.build(),HttpStatus.OK);	
		
	}

	@Override
	public ResponseEntity<?> registration() {
		// TODO Auto-generated method stub
		
		String UNIQUEID=util.generate26DigitUniqueTransactionId("SS","2001");
		ICICIClientRegistrationResponseModel clientRegistrationResponseModel=iCICIProviderServices.registration(UNIQUEID);
		
		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setStatus(0)
				.setPayload(clientRegistrationResponseModel)
				.build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> registrationStatus(String UNIQUEID) {
		// TODO Auto-generated method stub
		ICICIClientRegistrationStatusResponseModel clientRegistrationResponseModel=iCICIProviderServices.registrationStatus(UNIQUEID);
		
		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setStatus(0)
				.setPayload(clientRegistrationResponseModel)
				.build(),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> transactionStatus(String UNIQUEID) {
		// TODO Auto-generated method stub
		JSONObject transactionStatus=iCICIProviderServices.transactionStatus(UNIQUEID);
		/*
		 * {
		 * "RESPONSE": "SUCCESS",
		 * "STATUS": "SUCCESS",
		 * "URN": "SR191375691",
		 * "UNIQUEID": "201027212746YIGJ",
		 * "UTRNUMBER": "030121448391"
		 * }
		 */
		String UTRNUMBER=transactionStatus.optString("UTRNUMBER");
		String RESPONSE=transactionStatus.optString("RESPONSE");
		String STATUS=transactionStatus.optString("STATUS");

		ICICIPayoutResponseModel iciciPauoutResponseModel=new ICICIPayoutResponseModel(null, UNIQUEID, UTRNUMBER, STATUS, RESPONSE);

		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setPayload(iciciPauoutResponseModel)
				.setStatus(0)
				.build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBalance() {
		// TODO Auto-generated method stub
		JSONObject balanceStatus=iCICIProviderServices.balanceheck();
		/*
		 * {
		 * "RESPONSE": "SUCCESS",
		 * "AGGR_ID": "OTOE0011",
		 * "CORP_ID": "562782347",
		 * "USER_ID": "MANOJKUM",
		 * "URN":"SR191375691",
		 * "ACCOUNTNO": "628605014393",
		 * "DATE":"06/11/20 05:05:38",
		 * "EFFECTIVEBAL":"1975610.45",
		 * "CURRENCY":"INR"
		 * }
		 */
		String EFFECTIVEBAL=balanceStatus.optString("EFFECTIVEBAL");
		String RESPONSE=balanceStatus.optString("RESPONSE");
		String STATUS=balanceStatus.optString("STATUS");

		ICICIBalanceResponseModel balanceResponseModel=new ICICIBalanceResponseModel(EFFECTIVEBAL, STATUS, RESPONSE);

		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setPayload(balanceResponseModel)
				.setStatus(0)
				.build(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getStatement(String fromDate,String toDate) {
		// TODO Auto-generated method stub
		JSONObject statement=iCICIProviderServices.getStatement(fromDate, toDate);

		ICICIStatementResponseModel statementResponseModel=new ICICIStatementResponseModel();
		if(statement!=null) {
			statementResponseModel.setACCOUNTNO(statement.getString("ACCOUNTNO"));
			statementResponseModel.setRESPONSE(statement.getString("RESPONSE"));
			JSONArray statementArray=statement.optJSONArray("Record");
			List<StatementResponse> statementResponseList=new ArrayList<StatementResponse>();
			if(statementArray!=null && statementArray.length()>0) {
				for (int j = 0; j < statementArray.length(); j++) {
					JSONObject stmt=statementArray.getJSONObject(j);
					StatementResponse statementResponse=new StatementResponse();
					statementResponse.setAMOUNT(stmt.getString("AMOUNT"));
					statementResponse.setBALANCE(stmt.getString("BALANCE"));
					statementResponse.setREMARKS(stmt.getString("REMARKS"));
					statementResponse.setTRANSACTIONID(stmt.getString("TRANSACTIONID"));
					statementResponse.setTXNDATE(stmt.getString("TXNDATE"));
					statementResponse.setTYPE(stmt.getString("TYPE"));
					statementResponse.setVALUEDATE(stmt.getString("VALUEDATE"));
					statementResponse.setCHEQUENO(stmt.getString("CHEQUENO"));
					statementResponseList.add(statementResponse);
				}

			}
			statementResponseModel.setStatements(statementResponseList);
		}

		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setPayload(statementResponseModel)
				.setStatus(0)
				.build(),HttpStatus.OK);
	}
}
