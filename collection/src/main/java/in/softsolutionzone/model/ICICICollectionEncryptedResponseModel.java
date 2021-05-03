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
public class ICICICollectionEncryptedResponseModel {

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
	
}
