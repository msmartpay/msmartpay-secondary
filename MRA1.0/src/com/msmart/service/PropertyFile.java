package com.msmart.service;

public interface PropertyFile {
	
	//SSZPL ICICI
	String MSMART_PAYOUT="https://msmartpay.in/collection/payouts/direct";
	
	String IPAY_SMS_ALERT="";
	String IPAY_EMAIL_ALERT="";
	
	public static String AgentAuthId="AgentAuthId";
	public static String AgentAuthPassword="AgentAuthPassword";
	
	public static String Mds_Id="Mds_Id";
	public static String Distributor_Id="Distributor_Id";
	public static String First_Name="First_Name";
	public static String Last_Name="Last_Name";
	public static String Date_Of_Birth="Date_Of_Birth";
	public static String Gender="Gender";
	public static String Company_Type="Company_Type";
	public static String Company_Name="Company_Name";
	public static String Email_Id="Email_Id";
	public static String Mobile_No="Mobile_No";
	public static String Address_1="Address_1";
	public static String Address_2="Address_2";
	public static String Country="Country";
	public static String State="State";
	public static String District="District";
	public static String City="City";
	public static String Pin_Code="Pin_Code";
	public static String Pan_No="Pan_No";
	
	
	public static String Request_Id="Request_Id";
	public static String Agent_Id="Agent_Id";
	public static String User_Name="User_Name";
	public static String Password="Password";
	public static String Action_On_Amount="Action_On_Amount";
	public static String Service="Service";
	public static String Operator="Operator";
	public static String Amount="Amount";
	
	
	//local eko initiator id     //staging.eko.in:25004/ekoapi/v1/customers/
	/*String ekocustomerurl="https://staging.eko.in:25004/ekoapi/v1/customers/";
	String ekotranurl="https://staging.eko.in:25004/ekoapi/v1/transactions/";
	String initiator_id="9910028267";	
	String developer_key="becbbce45f79c6f5109f848acd540567";
	String key="f74c50a1-f705-4634-9cda-30a477df91b7";
	String secret_key="OzNKrKSaQdn5SmRHNHVJmchTKhYAGDp0EpuNdZbItLI=";
	String secret_key_timestamp="1536919178209";
	int seesionTime=10;
	String ekokycurl="https://staging.eko.in:25004/ekoapi/v1/customers/mobile_number:";
	String ekoAccountVerify="https://staging.eko.in:25004/ekoapi/v1/banks/";
	String ekobankDetails="https://staging.eko.in:25004/ekoicici/v1/banks";
	int sessionExpire=101;*/
		
		
	//Live EKO
	/*String ekocustomerurl="https://api.eko.in:25002/ekoicici/v1/customers/";
	String ekotranurl="https://api.eko.in:25002/ekoicici/v1/transactions/";
	String initiator_id="9560108491";	
	String developer_key="08c8758261e11028b41ae913bcc8bfa4";
	String key="b42f8650-a1e6-472c-9d47-17b9afbf9bb4";
	String secret_key="yyhl9/N+Hhe1H7UvWLXr8FfTBX4C3hcPAbIEaOc1P6g=";
	String secret_key_timestamp="1537929110838";
	int seesionTime=10;
	String ekokycurl="https://api.eko.in:25002/ekoicici/v1/customers/mobile_number:";
	String ekoAccountVerify="https://api.eko.in:25002/ekoicici/v1/banks/";
	String ekobankDetails="https://api.eko.co.in:25002/ekoicici/v1/banks";
	String REQ_OTP="https://api.eko.in:25002/ekoicici/v1/user/request/otp";
	String VERIFY_MOBILE="https://api.eko.in:25002/ekoicici/v1/user/verify";
	String ONBOARD_USER="https://api.eko.in:25002/ekoicici/v1/user/onboard";
	String ACTIVATE_USER="https://api.eko.in:25002/ekoicici/v1/user/service/activate";
	int sessionExpire=101;
	String PARTNER_NAME="MSMART";*/
	
	//TEST API 2.0
	/*String ekocustomerurl="https://staging.eko.in:25004/ekoapi/v2/customers/";
	String ekotranurl="https://staging.eko.in:25004/ekoapi/v2/transactions/";
	String initiator_id="9910028267";	
	String developer_key="becbbce45f79c6f5109f848acd540567";
	String ekokycurl="https://staging.eko.in:25004/ekoapi/v2/customers/mobile_number:";
	String ekoAccountVerify="https://staging.eko.in:25004/ekoapi/v2/banks/";
	String ekobankDetails="https://staging.eko.in:25004/ekoapi/v2/banks";
	int sessionExpire=101;
	int seesionTime=10;
	
	String key = "f74c50a1-f705-4634-9cda-30a477df91b7";
	String secret_key="O7TmVc2MNiVAa0Max1/vYbliZAMir+4hd9JglCaNX/o=";
	String secret_key_timestamp="1550901488398";
	
	String REQ_OTP="https://api.eko.in:25002/ekoicici/v1/user/request/otp";
	String VERIFY_MOBILE="https://api.eko.in:25002/ekoicici/v1/user/verify";
	String ONBOARD_USER="https://api.eko.in:25002/ekoicici/v1/user/onboard";
	String ACTIVATE_USER="https://api.eko.in:25002/ekoicici/v1/user/service/activate";
	String KYC_URL = "https://gateway.eko.in/v1/ekoekyc";
	String PARTNER_NAME="MSMART";*/
	
	
	//Live EKO 2.0
	String ekocustomerurl="https://api.eko.in:25002/ekoicici/v2/customers/";
	String ekotranurl="https://api.eko.in:25002/ekoicici/v2/transactions/";
	String initiator_id="9560108491";	
	String developer_key="08c8758261e11028b41ae913bcc8bfa4";
	int seesionTime=10;
	String ekokycurl="https://api.eko.in:25002/ekoicici/v2/customers/mobile_number:";
	String ekoAccountVerify="https://api.eko.in:25002/ekoicici/v2/banks/";
	String ekobankDetails="https://api.eko.co.in:25002/ekoicici/v2/banks";
	int sessionExpire=101;
	
	String REQ_OTP="https://api.eko.in:25002/ekoicici/v1/user/request/otp";
	String VERIFY_MOBILE="https://api.eko.in:25002/ekoicici/v1/user/verify";
	String ONBOARD_USER="https://api.eko.in:25002/ekoicici/v1/user/onboard";
	String ACTIVATE_USER="https://api.eko.in:25002/ekoicici/v1/user/service/activate";
	String KYC_URL = "https://gateway.eko.in/v1/ekoekyc";
	String PARTNER_NAME="MSMART";
	
	String key="b42f8650-a1e6-472c-9d47-17b9afbf9bb4";
	String secret_key="yyhl9/N+Hhe1H7UvWLXr8FfTBX4C3hcPAbIEaOc1P6g=";
	String secret_key_timestamp="1537929110838";

	
	//public static final String DB_IP="103.25.128.64:2499";
	//public static final String DB_IP="10.10.33.238:1433";
	//public static final String DB_USERNAME="msmart";
	//public static final String DB_PASSWORD="!@123lancer";
	
	public static final String DB_IP="13.235.120.132:1433;";
	public static final String DB_USERNAME="arpit";
	public static final String DB_PASSWORD="!@123lancer";
	
	public static final String DB_NAME="b2b_msmart;sendStringParametersAsUnicode=false";
	
	public static final String SUPPORT_EMAIL="mukeshkjindal72@gmail.com";
	
	String TECHNICAL_FAILURE="Transaction Failure. Please check account statement.";
	
	String RechargeBillpay_URL="https://softsolutionzone.in/SKAPI1.3/resources/SKIPDMR/";
	String RechargeBillpay_AgentAuthId="4077";
	String RechargeBillpay_AgentAuthPassword="18090600354500507835";
	
	String Outlet_ID="3669";
	
	public static String SmartKindaURL="https://softsolutionzone.in/SKAPI1.3/resources/SKDMR/";
	String SSZ_DMR_API_AUTH_KEY="4000";
	String SSZ_DMR_API_AUTH_PASSWORD="18082403243909835765";
	
	String SMS_AUTH_KEY="266017AkYEblb75c7ea8ff";
	String SMS_SENDER_ID="MSMART";
	
	
	String CYBERHUB_TOKEN="03b40bd2d89845faa6197a5765a99461";
	
	String PLAN_API_KEY="445ae1bae0b4cf439e5fdb6ff87de402";
	
	String VENUS_USERID="Arpit100004";
	String VENUS_AUTH_KEY="23456547d0679ca88db6464eac60da96345513964";
	String VENUS_URL="http://dmr.venusrecharge.com/venus/dmt/";
	
	String VENUS_RECHARGE_URL="http://api.msmartpay.in/";
	String VENUS_RECHARGE_USERID="10001";
	String VENUS_RECHARGE_PASSWORD="Test@123";
	
}
