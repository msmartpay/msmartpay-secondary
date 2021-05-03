package in.softsolutionzone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICICollectionResponseModel {

	@JsonProperty("SuccessANDRejected")
	private String SuccessANDRejected;
	
	@JsonProperty("CODE")
	private String CODE;
	
}
