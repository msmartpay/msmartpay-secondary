package in.softsolutionzone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICICICollectionRequestModel {

	@JsonProperty("requestId")
	private String requestId;
	
	@JsonProperty("service")
	private String service;
	
	@JsonProperty("encryptedKey")
	private String encryptedKey;
	
	@JsonProperty("iv")
	private String iv;
	@JsonProperty("encryptedData")
	private String encryptedData;
	@Override
	public String toString() {
		return "ICICICollectionRequestModel [requestId=" + requestId + ", service=" + service + ", encryptedKey="
				+ encryptedKey + ", iv=" + iv + ", encryptedData=" + encryptedData + "]";
	}
	
	
	
}
