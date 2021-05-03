package in.softsolutionzone.payout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICIPayoutResponseModel {

	private String ORDERID;
	private String UNIQUEID;
	private String UTRNUMBER;
	private String STATUS;
	private String MESSAGE;
	
	
	@Override
	public String toString() {
		return "ICICIPayoutResponseModel [ORDERID=" + ORDERID + ", UNIQUEID=" + UNIQUEID + ", UTRNUMBER=" + UTRNUMBER
				+ ", STATUS=" + STATUS + ", MESSAGE=" + MESSAGE + "]";
	}
	public ICICIPayoutResponseModel(String oRDERID, String uNIQUEID, String uTRNUMBER, String sTATUS, String mESSAGE) {
		super();
		ORDERID = oRDERID;
		UNIQUEID = uNIQUEID;
		UTRNUMBER = uTRNUMBER;
		STATUS = sTATUS;
		MESSAGE = mESSAGE;
	}

	
	
}
