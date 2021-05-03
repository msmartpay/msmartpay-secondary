package com.formBean.API;

public class APIVendorDetails {

	private int slNo;
	private String vendorName;
	private String url;
	private String status;
	
	public APIVendorDetails() {
		super();
	}
	
	public APIVendorDetails(int slNo, String vendorName, String url, String status) {
		super();
		this.slNo = slNo;
		this.vendorName = vendorName;
		this.url = url;
		this.status = status;
	}
	
	

	public int getSlNo() {
		return slNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getUrl() {
		return url;
	}
	public String getStatus() {
		return status;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
