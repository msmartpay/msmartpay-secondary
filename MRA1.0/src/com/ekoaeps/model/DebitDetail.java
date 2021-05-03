package com.ekoaeps.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DebitDetail {

	private String url;
	@JsonProperty("http_method")
	private String httpMethod;
	@JsonProperty("interaction_type_id")
	private int interactionTypeId;
	@JsonProperty("interaction_label")
	private String interactionLabel;
	@JsonProperty("client_ref_id")
	private String clientRefId;
	@JsonProperty("card_index")
	private String cardIndex;
	@JsonProperty("is_debithook_response")
	private boolean isDebithookResponse;
	@JsonProperty("http_status")
	private int httpStatus;
	@JsonProperty("is_final")
	private boolean isFinal;
	
	@JsonProperty("request_hash_params")
	private List<String> requestHashParams;
	private DebitData data;
	private DebitDetailResponse response;
	
	
	@Override
	public String toString() {
		return "DebitDetail [url=" + url + ", httpMethod=" + httpMethod + ", interactionTypeId=" + interactionTypeId
				+ ", interactionLabel=" + interactionLabel + ", clientRefId=" + clientRefId + ", cardIndex=" + cardIndex
				+ ", isDebithookResponse=" + isDebithookResponse + ", httpStatus=" + httpStatus + ", isFinal=" + isFinal
				+ ", response=" + response + ", requestHashParams=" + requestHashParams + ", data=" + data + "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public int getInteractionTypeId() {
		return interactionTypeId;
	}
	public void setInteractionTypeId(int interactionTypeId) {
		this.interactionTypeId = interactionTypeId;
	}
	public String getInteractionLabel() {
		return interactionLabel;
	}
	public void setInteractionLabel(String interactionLabel) {
		this.interactionLabel = interactionLabel;
	}
	public String getClientRefId() {
		return clientRefId;
	}
	public void setClientRefId(String clientRefId) {
		this.clientRefId = clientRefId;
	}
	public String getCardIndex() {
		return cardIndex;
	}
	public void setCardIndex(String cardIndex) {
		this.cardIndex = cardIndex;
	}
	public boolean isDebithookResponse() {
		return isDebithookResponse;
	}
	public void setDebithookResponse(boolean isDebithookResponse) {
		this.isDebithookResponse = isDebithookResponse;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public List<String> getRequestHashParams() {
		return requestHashParams;
	}
	public void setRequestHashParams(List<String> requestHashParams) {
		this.requestHashParams = requestHashParams;
	}
	public DebitData getData() {
		return data;
	}
	public void setData(DebitData data) {
		this.data = data;
	}
	public DebitDetailResponse getResponse() {
		return response;
	}
	public void setResponse(DebitDetailResponse response) {
		this.response = response;
	}
	
	
	
}
