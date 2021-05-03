package com.mdamountrequest;

import java.util.HashMap;


public class MdAmountRequestBusinessDelegator {
	public HashMap getMdBalance(){
		MdAmountRequestDao mdAmountRequestDao=new MdAmountRequestDao();
		return mdAmountRequestDao.getMdBalance();
	}
	public static HashMap getModeOfPaymentDetails(String payMode,String amount,String RecBankName )
	{
		return MdAmountRequestDao.getModeOfPaymentDetails(payMode,amount,RecBankName);
	}
	public static HashMap getCrPaymentDetails(String payMode,String amount,String creditdaysReq,String reqDate,String creditdateReq)
	{
		return MdAmountRequestDao.getCrPaymentDetails(payMode,amount,creditdaysReq,reqDate,creditdateReq);
	}
	public static String saveNeftDep( String corpAgentId,String TransactionId,String reqDate,String payMode,String amount,String NeftTransferTid,String NeftRefNo,String NeftDepBank,String NeftDepDate,String NeftDepTime,String NeftRecBankName,String accNo,String NeftRecBrName,double charges,double acceptAmt,String neftremarks,String NeftDepAccNo)
	{
		return MdAmountRequestDao.saveNeftDep(corpAgentId,TransactionId,reqDate,payMode,amount,NeftTransferTid,NeftRefNo,NeftDepBank,NeftDepDate,NeftDepTime,NeftRecBankName,accNo,NeftRecBrName,charges,acceptAmt,neftremarks,NeftDepAccNo);
	}
	public static String saveCreditDep( String corpAgentId,String TransactionId,String reqDate,String payMode,String amount,String creditdaysReq,String creditdateReq,String paymentDate,double charges,double acceptAmt,String creditremarks)
	{
		return MdAmountRequestDao.saveCreditDep(corpAgentId,TransactionId,reqDate,payMode,amount,creditdaysReq,creditdateReq,paymentDate,charges,acceptAmt,creditremarks);
	}
}