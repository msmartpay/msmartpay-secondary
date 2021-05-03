package com.formBean.API;

import java.sql.Date;

public class APIClientResponseUrlDetails {

	private long apiId;
	private String clientResponseUrl;
	private Date updateDate;
	public long getApiId() {
		return apiId;
	}
	public void setApiId(long apiId) {
		this.apiId = apiId;
	}
	public String getClientResponseUrl() {
		return clientResponseUrl;
	}
	public void setClientResponseUrl(String clientResponseUrl) {
		this.clientResponseUrl = clientResponseUrl;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
