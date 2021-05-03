package in.softsolutionzone.payout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatementResponse {

	private String CHEQUENO;
	private String TXNDATE;
	private String REMARKS;
	private String AMOUNT;
	private String BALANCE;
	private String VALUEDATE;
	private String TYPE;
	private String TRANSACTIONID;
	
}
