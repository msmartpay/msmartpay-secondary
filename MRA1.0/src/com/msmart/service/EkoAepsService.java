package com.msmart.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.ekoaeps.model.HookRequestModel;
import com.ekoaeps.model.HookResponse;
import com.msmart.dao.EkoAepsDao;
import com.msmart.util.HmacSHA256;

public class EkoAepsService {
	
	Logger log=Logger.getLogger(EkoAepsService.class);

	public ResponseBuilder hook(HookRequestModel hookRequestModel ,String ip) {

		HookResponse hookResponse=new HookResponse();
		EkoAepsDao dao=new EkoAepsDao();
		String action=hookRequestModel.getAction();
		if("debit-hook".equalsIgnoreCase(action)) {
			String amount = hookRequestModel.getDetail().getData().getAmount();
			String customer_id = hookRequestModel.getDetail().getData().getCustomerId();
			//String bank_code = hookRequestModel.getDetail().getData().getBankCode();
			String user_code = hookRequestModel.getDetail().getData().getUserCode();
			String type = hookRequestModel.getDetail().getData().getType();
			String client_ref_id = hookRequestModel.getDetail().getClientRefId();
			List<String> request_hash_params = hookRequestModel.getDetail().getRequestHashParams();
			
			String req=hookRequestModel.toString();
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
			
			
			if(user_code!=null && !"".equalsIgnoreCase(user_code)) {

				String insertStatus=dao.saveAepsLog(user_code, client_ref_id, "validate", req, "GO", ip,tranType,Double.parseDouble(amount));
				if("Y".equalsIgnoreCase(insertStatus)) {
					Date date = new Date();
					String secret_key_timestamp = Long.toString(date.getTime());

					hookResponse.setAction("go");
					hookResponse.setAllow(true);
					hookResponse.setSecretKey(HmacSHA256.generateSecretKey(secret_key_timestamp,PropertyFile.key));
					hookResponse.setSecretKeyTimestamp(secret_key_timestamp);
					hookResponse.setClientRefId(client_ref_id);
					String request_hash=secret_key_timestamp;
					for (int i = 0; i < request_hash_params.size(); i++) {
						
						String val=request_hash_params.get(i);
						if("customer_id".equalsIgnoreCase(val))
							request_hash=request_hash+customer_id;
						if("amount".equalsIgnoreCase(val) && "2".equalsIgnoreCase(type))
							request_hash=request_hash+amount;
						if("user_code".equalsIgnoreCase(val))
								request_hash=request_hash+user_code;
					}
					String request_hash_key=HmacSHA256.generateSecretKey(request_hash,PropertyFile.key);
					
					hookResponse.setRequestHash(request_hash_key);
					log.info("HookResponse "+action+" : "+hookResponse.toString());
				}else {
					hookResponse.setAction("go");
					hookResponse.setAllow(false);
					hookResponse.setMessage("Technical failure");
				}

			}else {
				
				hookResponse.setAction("go");
				hookResponse.setAllow(false);
				hookResponse.setMessage("Invalid user code");
			}
			System.out.println("hookResponse "+hookResponse.toString());
			return Response.ok(hookResponse);
		}else if("eko-response".equalsIgnoreCase(action)){
			
			String client_ref_id=hookRequestModel.getDetail().getClientRefId();
			String tx_status=hookRequestModel.getDetail().getResponse().getData().getTxStatus();
			String user_code=hookRequestModel.getDetail().getResponse().getData().getUserCode();
			if("0".equalsIgnoreCase(tx_status)) {

				String amount=hookRequestModel.getDetail().getResponse().getData().getAmount();
				//String sender_name=hookRequestModel.getDetail().getResponse().getData().getSender_name();
				String tid=hookRequestModel.getDetail().getResponse().getData().getTid();
				String bank_ref_num=hookRequestModel.getDetail().getResponse().getData().getBankRefNum();
				
				String commission=hookRequestModel.getDetail().getResponse().getData().getCommission();
				
				String tds=hookRequestModel.getDetail().getResponse().getData().getTds();
				
				if("".equalsIgnoreCase(tds))
					tds="0";
				if("".equalsIgnoreCase(commission))
					commission="0";
				if("".equalsIgnoreCase(amount))
					amount="0";

				if(user_code!=null && !"".equalsIgnoreCase(user_code)) {
					
					try {
						dao.updateAepsLog(user_code, client_ref_id, hookRequestModel.toString());
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					String creditStatus=dao.aepsCredit(tid, client_ref_id, bank_ref_num, user_code, Double.parseDouble(commission), Double.parseDouble(tds), Double.parseDouble(amount),  ip,"Success");
					log.info("AepsCredit Status :"+creditStatus);
				}

			}else {
				try {
					dao.updateAepsLog(user_code, client_ref_id, hookRequestModel.toString());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			hookResponse.setMessage("ok");
			System.out.println("hookResponse "+hookResponse.toString());
			return Response.ok(hookResponse);
		}else {
			return Response.serverError();
		}
	}

	public ResponseBuilder setHeader(ResponseBuilder builder) {
		if(builder!=null)
		{
			builder.header("Access-Control-Allow-Headers", "Content-Type");
			builder.header("Access-Control-Allow-Methods", "POST,OPTIONS");
			builder.header("Access-Control-Allow-Origin", "https://stagegateway.eko.in");
		}
		return builder;
	}
}
