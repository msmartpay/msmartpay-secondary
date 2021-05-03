package com.common;

public interface PropertyFile {

	
	public static final String SUPPORT_EMAIL="care@msmartpay.in";
	
	String TECHNICAL_FAILURE="Transaction Failure. Please check account statement.";
	
	//String RechargeBillpay_URL="https://msmartpay.in/MRA1.0/resources/";
	String Outlet_ID="3669";

	String MRA_URL="https://msmartpay.in/MRA1.0/resources/";
	String MRA_VENUS_URL=MRA_URL+"SKDMR/";
	
	String AEPS_CHARGE="0";;
	
	//Testing
	/*String KEY = "f74c50a1-f705-4634-9cda-30a477df91b7";
	String Initiator_id = "9962981729";//9962981729
	String KYC_URL = "https://gateway.eko.in/v1/ekoekyc";
	String INSTANTKYC_URL="https://staging.eko.in:25004/ekoapi/v1/customers/mobile_number:";
	String DEVELOPER_KEY = "becbbce45f79c6f5109f848acd540567";
	String secret_key="zzXQ/vUshAV32utYyzmNLfwOuGKZbu5H6Fh4S7I3nos=";
	String secret_key_timestamp="1550561806065";
	String PARTNER_LOGO_URL="http://agent.msmartpay.in/agent/images/ARPIT%20ENTERPRISES/M%20smart%20Pay.PNG";
	String PARTNE_NAME="Arpit Enterprises";
	String REQ_OTP="https://staging.eko.in:25004/ekoapi/v1/user/request/otp";
	String VERIFY_MOBILE="https://staging.eko.in:25004/ekoapi/v1/user/verify";
	String ONBOARD_USER="https://staging.eko.in:25004/ekoapi/v1/user/onboard";
	String ACTIVATE_USER="https://staging.eko.in:25004/ekoapi/v1/user/service/activate";
	String EKO_AEPS_LANDING_URL="https://stagegateway.eko.in/v2/aeps";
	String USER_ENQUIRY="https://staging.eko.in:25004/ekoapi/v1/user/services/user_code";;*/
	
	
	//Live
	String KEY = "b42f8650-a1e6-472c-9d47-17b9afbf9bb4";
	String Initiator_id = "9560108491";
	String KYC_URL = "https://gateway.eko.in/v1/ekoekyc";
	String INSTANTKYC_URL="https://api.eko.in:25002/ekoicici/v1/customers/mobile_number:";
	String DEVELOPER_KEY = "08c8758261e11028b41ae913bcc8bfa4";
	String PARTNER_NAME="Arpit Enterprises";
	String secret_key="yyhl9/N+Hhe1H7UvWLXr8FfTBX4C3hcPAbIEaOc1P6g=";
	String secret_key_timestamp="1537929110838";
	
	String PARTNER_LOGO_URL="https://msmartpay.in/agent/images/ARPIT%20ENTERPRISES/M%20smart%20Pay.PNG";
	String PARTNE_NAME="Arpit Enterprises";
	String REQ_OTP="https://api.eko.in:25002/ekoicici/v1/user/request/otp";
	String VERIFY_MOBILE="https://api.eko.in:25002/ekoicici/v1/user/verify";
	String ONBOARD_USER="https://api.eko.in:25002/ekoicici/v1/user/onboard";
	String ACTIVATE_USER="https://api.eko.in:25002/ekoicici/v1/user/service/activate";
	String EKO_AEPS_LANDING_URL="https://gateway.eko.in/v2/aeps";
	String USER_ENQUIRY="https://api.eko.in:25002/ekoicici/v1/user/services/user_code";
	String EKO_AEPS_CALLBACK_URL="https://msmartpay.in/MRA1.0/resources/AEPS/hook";
	
	String SMS_AUTH_KEY="266017AkYEblb75c7ea8ff";
	String SMS_SENDER_ID="MSMART";
	
}
