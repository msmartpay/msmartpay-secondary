package com.util;

public interface PropertyFile {

	//LIVE EKO CREDENTIALS
	String ekocustomerurl="https://api.eko.in:25002/ekoicici/v1/customers/";
	String ekotranurl="https://api.eko.in:25002/ekoicici/v1/transactions/";
	int seesionTime=10;
	String ekokycurl="https://api.eko.in:25002/ekoicici/v1/customers/mobile_number:";
	String ekoAccountVerify="https://api.eko.in:25002/ekoicici/v1/banks/";
	String ekobankDetails="https://api.eko.in:25002/ekoicici/v1/banks";
	int sessionExpire=101;
	//AEPS Settlement
	String AEPS_Settlement="https://api.eko.in:25002/ekoicici/v1/agent/";
	String AEPS_Settlement_service_code="39"; 
	
	String initiator_id="9560108491";	
	String developer_key="08c8758261e11028b41ae913bcc8bfa4";
	
	
	String EKO_KEY="b42f8650-a1e6-472c-9d47-17b9afbf9bb4";
	String secret_key="yyhl9/N+Hhe1H7UvWLXr8FfTBX4C3hcPAbIEaOc1P6g=";
	String secret_key_timestamp="1537929110838";
}
