package com.ekoaeps.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class HookResponse {
	
	private boolean allow;
	private String action; 
	
	@JsonProperty("secret_key_timestamp")
	private String secretKeyTimestamp;
	
	@JsonProperty("request_hash")
	private String requestHash;	
	
	@JsonProperty("secret_key")
	private String secretKey;
	@JsonIgnore
	private String message;
	@JsonProperty("client_ref_id")
	private String clientRefId;

	@Override
	public String toString() {
		return "HookResponse [allow=" + allow + ", action=" + action + ", secretKeyTimestamp=" + secretKeyTimestamp
				+ ", requestHash=" + requestHash + ", secretKey=" + secretKey + ", message=" + message + "]";
	}
	public boolean getAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSecretKeyTimestamp() {
		return secretKeyTimestamp;
	}

	public void setSecretKeyTimestamp(String secretKeyTimestamp) {
		this.secretKeyTimestamp = secretKeyTimestamp;
	}

	public String getRequestHash() {
		return requestHash;
	}

	public void setRequestHash(String requestHash) {
		this.requestHash = requestHash;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClientRefId() {
		return clientRefId;
	}

	public void setClientRefId(String clientRefId) {
		this.clientRefId = clientRefId;
	}
}
