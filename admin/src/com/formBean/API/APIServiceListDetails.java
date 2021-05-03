package com.formBean.API;

public class APIServiceListDetails {
	private int serviceId;
	private int parentId;
	private String serviceLink;
	private String serviceDisplayName;
	private String serviceType; //0|1
	
	public int getServiceId() {
		return serviceId;
	}
	public int getParentId() {
		return parentId;
	}
	public String getServiceLink() {
		return serviceLink;
	}
	public String getServiceDisplayName() {
		return serviceDisplayName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public void setServiceLink(String serviceLink) {
		this.serviceLink = serviceLink;
	}
	public void setServiceDisplayName(String serviceDisplayName) {
		this.serviceDisplayName = serviceDisplayName;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	
}
