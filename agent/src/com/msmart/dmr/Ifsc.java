package com.msmart.dmr;

public class Ifsc {

	private String bank_name;
	private String ifsc;
	private String city;
	private String state;
	private String branch;
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "Ifsc [bank_name=" + bank_name + ", ifsc=" + ifsc + ", city=" + city + ", state=" + state + ", branch="
				+ branch + "]";
	}
}
