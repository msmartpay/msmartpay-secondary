package com.ekoaeps.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class DebitDetailResponse {

	private int status;
	@JsonProperty("response_status_id")
	private int responseStatusId;
	@JsonProperty("response_type_id")
	private int responseTypeId;
	private String message;
	private DebitData data;
	
	@Override
	public String toString() {
		return "DebitDetailResponse [status=" + status + ", responseStatusId=" + responseStatusId + ", responseTypeId="
				+ responseTypeId + ", message=" + message + ", data=" + data + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getResponseStatusId() {
		return responseStatusId;
	}
	public void setResponseStatusId(int responseStatusId) {
		this.responseStatusId = responseStatusId;
	}
	public int getResponseTypeId() {
		return responseTypeId;
	}
	public void setResponseTypeId(int responseTypeId) {
		this.responseTypeId = responseTypeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public DebitData getData() {
		return data;
	}
	public void setData(DebitData data) {
		this.data = data;
	}
	
}
