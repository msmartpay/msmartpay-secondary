package in.softsolutionzone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICICollectionModel {

	@JsonProperty("CustomerCode")
	private String customerCode;
	
	@JsonProperty("VirtualACCode")
	private String virtualAcCode;
	
	@JsonProperty("MODE")
	private String mode;
	
	@JsonProperty("UTR")
	private String utr;
	
	@JsonProperty("SENDER_REMARK")
	private String senderRemark;
	
	@JsonProperty("CustomerAccountNo")
	private String customerAccountNo;
	
	@JsonProperty("AMT")
	private String amount;
	
	@JsonProperty("PayeeName")
	private String payeeName;
	
	@JsonProperty("PayeeAccountNumber")
	private String payeeAccountNumber;
	
	@JsonProperty("PayeeBankIFSC")
	private String payeeBankIFSC;

	@JsonProperty("PayeePaymentDate")
	private String payeePaymentDate;
	
	@JsonProperty("BankInternalTransactionNumber")
	private String bankInternalTransactionNumber;
	
}
