package com.ekoaeps.model;

public class HookRequestModel {

	private String param1;
	private String param2;
	private String action;
	private DebitDetail detail;
	
	@Override
	public String toString() {
		return "HookRequestModel [param1=" + param1 + ", param2=" + param2 + ", action=" + action + ", detail=" + detail
				+ "]";
	}
	
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public DebitDetail getDetail() {
		return detail;
	}
	public void setDetail(DebitDetail detail) {
		this.detail = detail;
	}
}	
