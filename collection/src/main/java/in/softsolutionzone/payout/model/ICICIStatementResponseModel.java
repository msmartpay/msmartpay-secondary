package in.softsolutionzone.payout.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICIStatementResponseModel {

	private String ACCOUNTNO;
	private String RESPONSE;
	private List<StatementResponse> statements;
	
}
