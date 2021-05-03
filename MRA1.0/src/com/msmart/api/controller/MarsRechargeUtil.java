package com.msmart.api.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.msmart.dao.MobileAppRechargeDao;

public class MarsRechargeUtil {
	
	HashMap<String,String> result =null;
	MobileAppRechargeDao mrd=new MobileAppRechargeDao();
	public  HashMap<String,String> sndMarsRequest(String agentId, String transaction_No,String operator,String mobile_no,String rech_amount,String request_type)throws ParserConfigurationException, SAXException, IOException 
	{
		result =new HashMap<String,String>();
		MarsController mar=new MarsController();
		result=mar.sndMarsRequest( mobile_no, rech_amount, transaction_No, operator); 
		String status="";
		String ser_tran_res_code=result.get("res_code");
		String marsRefId=result.get("marsRefId");
		if(marsRefId!=null)
			marsRefId=marsRefId.trim();
		
		if(ser_tran_res_code.equalsIgnoreCase("00")){
			status="Pending";
			ser_tran_res_code="01";

		}
		else if(ser_tran_res_code.equalsIgnoreCase("01")){
			status="Pending";
			ser_tran_res_code="01";
		}
		else if(ser_tran_res_code.equalsIgnoreCase("02")){
			status="Failure";
			ser_tran_res_code="02";
		}
		else{
			status="Pending";
			ser_tran_res_code="01";
			
		}
		String update="";
		System.out.println("MRA Update  started >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		MobileAppRechargeDao mad=new MobileAppRechargeDao();
		update=mad.updateMarsRefPending(status, transaction_No, marsRefId);
		String balance=mad.getAgentBalance(agentId);
		result.put("balance", balance);
		System.out.println("MRA Update  End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+update);
		
		return result;
	}

	public  HashMap<String,String> sndRechargeRequest(String agentId,String transaction_No,String operator,String mobile_no,String rech_amount,String request_type,String rechargeType)throws ParserConfigurationException, SAXException, IOException 
	{
		result =new HashMap<String,String>();
		RechargeController mar=new RechargeController();
		result=mar.sndRechargeRequest( mobile_no, rech_amount, transaction_No, operator,rechargeType); 
		String status="";
		String ser_tran_res_code=result.get("res_code");
		String marsRefId=result.get("rechargeRefId");
		if(marsRefId!=null)
			marsRefId=marsRefId.trim();
		
		if(ser_tran_res_code.equalsIgnoreCase("00")){
			status="Pending";
			ser_tran_res_code="01";

		}
		else if(ser_tran_res_code.equalsIgnoreCase("01")){
			status="Pending";
			ser_tran_res_code="01";
		}
		else if(ser_tran_res_code.equalsIgnoreCase("02")){
			status="Failure";
			ser_tran_res_code="02";
		}
		else{
			status="Pending";
			ser_tran_res_code="01";
			
		}
		String update="";
		System.out.println("MRA Update  started >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		MobileAppRechargeDao mad=new MobileAppRechargeDao();
		update=mad.updateMarsRefPending(status, transaction_No, marsRefId);
		String balance=mad.getAgentBalance(agentId);
		result.put("balance", balance);
		System.out.println("MRA Update  End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+update);
	
		
		return result;
	}
	
}
