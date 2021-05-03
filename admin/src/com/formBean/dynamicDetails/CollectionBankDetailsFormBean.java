
package com.formBean.dynamicDetails;


public class CollectionBankDetailsFormBean  {
	
	private int slNo;
	private long clientId;
	private String bankName;
	private String bankAccount;
	private String bankIFSC;
	private String bankAccountName;
	private int status;
	
	
	@Override
	public String toString() {
		return "CollectionBankDetailsFormBean [slNo=" + slNo + ", clientId=" + clientId + ", bankName=" + bankName
				+ ", bankAccount=" + bankAccount + ", bankIFSC=" + bankIFSC + ", bankAccountName=" + bankAccountName
				+ ", status=" + status + "]";
	}
	
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankIFSC() {
		return bankIFSC;
	}
	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
