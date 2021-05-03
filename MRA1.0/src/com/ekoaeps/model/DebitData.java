package com.ekoaeps.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties
public class DebitData {

	@JsonProperty("customer_id")
	private String customerId;
	@JsonProperty("bank_code")
	private String bankCode;
	private String type;
	@JsonProperty("user_code")
	private String userCode;
	private String amount;
	@JsonProperty("tx_status")
	private String txStatus;
	@JsonProperty("transaction_date")
	private String transactionDate;
	private String reason;
	@JsonProperty("merchant_code")
	private String merchantCode;
	private String tds;
	@JsonProperty("sender_name")
	private String senderName;
	private String tid;
	private String fee;
	private String totalfee;
	@JsonProperty("service_tax")
	private String serviceTax;
	private String balance;
	private String merchantname;
	private String aadhar;
	@JsonProperty("customer_balance")
	private String customerBalance;
	@JsonProperty("transaction_time")
	private String transactionTime;
	private String commission;
	@JsonProperty("bank_ref_num")
	private String bankRefNum;
	private String shop;
	@JsonProperty("shop_address_line1")
	private String shopAddressLine1;
	@JsonProperty("terminal_id")
	private String terminalId;
	@JsonProperty("auth_code")
	private String authCode;
	private String stan;
	private String aadhaar;
	@JsonProperty("config_value")
	private String configValue;
	
	@Override
	public String toString() {
		return "DebitData [customerId=" + customerId + ", bankCode=" + bankCode + ", type=" + type + ", userCode="
				+ userCode + ", amount=" + amount + ", txStatus=" + txStatus + ", transactionDate=" + transactionDate
				+ ", reason=" + reason + ", merchantCode=" + merchantCode + ", tds=" + tds + ", senderName="
				+ senderName + ", tid=" + tid + ", balance=" + balance + ", merchantname=" + merchantname + ", aadhar="
				+ aadhar + ", customerBalance=" + customerBalance + ", transactionTime=" + transactionTime
				+ ", commission=" + commission + ", bankRefNum=" + bankRefNum + "]";
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTxStatus() {
		return txStatus;
	}
	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getTds() {
		return tds;
	}
	public void setTds(String tds) {
		this.tds = tds;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getMerchantname() {
		return merchantname;
	}
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}
	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(String customerBalance) {
		this.customerBalance = customerBalance;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getBankRefNum() {
		return bankRefNum;
	}
	public void setBankRefNum(String bankRefNum) {
		this.bankRefNum = bankRefNum;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getShopAddressLine1() {
		return shopAddressLine1;
	}
	public void setShopAddressLine1(String shopAddressLine1) {
		this.shopAddressLine1 = shopAddressLine1;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(String totalfee) {
		this.totalfee = totalfee;
	}

	public String getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(String serviceTax) {
		this.serviceTax = serviceTax;
	}
}
