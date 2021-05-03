package in.softsolutionzone.payout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayoutRequestModel {

	private String sp_key;
	private String external_ref;
	private String credit_account;
	private String credit_rmn;
	private String ifs_code;
	private String bene_name;
	private String credit_amount;
	private String upi_mode;
	private String vpa;
	private String latitude;
	private String longitude;
	private String endpoint_ip;
	private String alert_mobile;
	private String alert_email;
	private String remarks;
	
	/*
	 * This API is used to transfer funds from InstantPay wallet to provided Bank Account Details and for beneficiary account verification.

	You can check the status of the transaction from Status check API which is available at Others API>>Status Check.
	
	Request Parameters Description
	
	token: API token is a unique identifier of an application requesting access to your service. Your service would generate an API token for the application to use when requesting your service from https://www.instantpay.in/developers/api/credentials
	
	sp_key: Service Key (Mandatory)
	
	DPN- For IMPS
	BPN- For NEFT
	CPN- For RTGS
	PPN- For UPI
	RPN- Amazon Pay
	QPN- Paytm
	credit_account: Account number of beneficiary (Mandatory but optional when sp_key=PPN)
	
	credit_rmn: Mobile number linked with Amazon and paytm wallet (Mandatory when sp_key=RPN,QPN)
	
	ifs_code: IFSC code of beneficiary (Mandatory and optional when sp_key=PPN)
	
	bene_name: Name of the beneficiary (Mandatory)
	
	credit_amount Amount to be transferred (Mandatory)
	
	external_ref: Your transaction ID (Agent ID) (Mandatory)
	
	endpoint: End Customer IP Address (Mandatory)
	
	latitude: End Customer Latitude (Mandatory)
	
	longitude: End Customer Longitiude (Mandatory)
	
	alert_mobile: In this 10 digit Mobile Number on which alert would be sent when beneficiary receives the funds.Multiple numbers can be sent for alert by using comma separated list (Optional)
	
	alert_email: Email ID on which alert would be sent when beneficiary receives the funds. Multiple emails can be sent for alert by using comma separated list (Optional)
	
	vpa: Enter your UPI ID (Mandatory in case when sp_key=PPN)
	
	upi_mode: It will be VPA. (Mandatory in case when sp_key=PPN)
	
	remarks: Payment remarks for beneficiary and it should be 0 to 20 character long alphabets only (Mandatory)
	
	otp_auth: If 0 then OTP is not required and if 1 OTP is required
	
	otp: In this pass OTP which you will generate from Generate OTP API
	
	Note:
	
	To validate VPA, sp_key=PPN, amount=0
	To validate Beneficiary account, sp_key=DPN, amount=1

	 */
	
	
	
}
