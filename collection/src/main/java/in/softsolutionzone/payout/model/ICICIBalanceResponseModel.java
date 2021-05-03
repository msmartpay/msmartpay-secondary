package in.softsolutionzone.payout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICIBalanceResponseModel {

	private String BALANCE;
	private String STATUS;
	private String MESSAGE;
	@Override
	public String toString() {
		return "ICICIBalanceResponseModel [BALANCE=" + BALANCE + ", STATUS=" + STATUS + ", MESSAGE=" + MESSAGE + "]";
	}
	public ICICIBalanceResponseModel(String BALANCE, String STATUS, String MESSAGE) {
		super();
		BALANCE = this.BALANCE;
		STATUS = this.STATUS;
		MESSAGE = this.MESSAGE;
	}
	
	
	
}
