
/*
  Class Property :  This class (CheckDetailsForm) is created for controlling and display active ,non active Transaction details .

  Created Date   : 8 january-2012 at 10.00 AM.
  Created By     :  Amit Pathak.

  Updated Date   :8 january-2012 at 10.00 AM.
  Update By      :  Amit Pathak

*/


package com.TransactionDetails;

public class CheckDetailsForm {

	
	
	private String param;
	private String TransactionNo;
	private String Service;
	private String TransactionStatus;
	//private String ActionOnBalanceAmount;
	private String AgentCompleteId;
	public String getAgentCompleteId() {
		return AgentCompleteId;
	}
	public void setAgentCompleteId(String agentCompleteId) {
		AgentCompleteId = agentCompleteId;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getTransactionNo() {
		return TransactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		TransactionNo = transactionNo;
	}
	public String getService() {
		return Service;
	}
	public void setService(String service) {
		Service = service;
	}
	public String getTransactionStatus() {
		return TransactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		TransactionStatus = transactionStatus;
	}
	/*public String getActionOnBalanceAmount() {
		return ActionOnBalanceAmount;
	}
	public void setActionOnBalanceAmount(String actionOnBalanceAmount) {
		ActionOnBalanceAmount = actionOnBalanceAmount;
	}
	*/
}
