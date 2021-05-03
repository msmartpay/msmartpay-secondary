package com.controlPanel.setmarkup;

import java.math.BigInteger;
import java.util.Date;

public class SetMarkupForm 
{
	private int sNo;
	private BigInteger userId;
	private BigInteger setByUserId;
	private String service;
	private String clientInitial;
	private String clientId;
	private String mdId;
	private String dsId;
	private String agentId;
	
	private double marketLimit;
	private double clientMarkup;
	private double mdsMarkup;
	private double dsMarkup;
	private double agentMarkup;
	
	public SetMarkupForm()
	{
		
	}
	
	public SetMarkupForm(
						BigInteger setByUserId,
						String service,
						String clientInitial,
						double marketLimit,
						double clientMarkup,
						double mdsMarkup,
						double dsMarkup,
						double agentMarkup) 
	{
		
		this.setByUserId=setByUserId;
		this.service=service;
		this.clientInitial=clientInitial;
		this.marketLimit=marketLimit;
		this.clientMarkup=clientMarkup;
		this.mdsMarkup=mdsMarkup;
		this.dsMarkup=dsMarkup;
		this.agentMarkup=agentMarkup;
	}
	
	public void setSetByUserId(BigInteger setByUserId) {
		this.setByUserId = setByUserId;
	}
	public BigInteger getSetByUserId() {
		return setByUserId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public void setDsId(String dsId) {
		this.dsId = dsId;
	}
	public void setMdId(String mdId) {
		this.mdId = mdId;
	}
	public String getAgentId() {
		return agentId;
	}
	public String getDsId() {
		return dsId;
	}
	public String getMdId() {
		return mdId;
	}
	public void setAgentMarkup(double agentMarkup) {
		this.agentMarkup = agentMarkup;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public void setClientInitial(String clientInitial) {
		this.clientInitial = clientInitial;
	}
	public void setClientMarkup(double clientMarkup) {
		this.clientMarkup = clientMarkup;
	}
	public void setDsMarkup(double dsMarkup) {
		this.dsMarkup = dsMarkup;
	}
	public void setMarketLimit(double marketLimit) {
		this.marketLimit = marketLimit;
	}
	public void setMdsMarkup(double mdsMarkup) {
		this.mdsMarkup = mdsMarkup;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	
	public double getAgentMarkup() {
		return agentMarkup;
	}
	public String getClientId() {
		return clientId;
	}
	public String getClientInitial() {
		return clientInitial;
	}
	public double getClientMarkup() {
		return clientMarkup;
	}
	public double getDsMarkup() {
		return dsMarkup;
	}
	public double getMarketLimit() {
		return marketLimit;
	}
	public double getMdsMarkup() {
		return mdsMarkup;
	}
	public String getService() {
		return service;
	}
	public int getsNo() {
		return sNo;
	}
	public BigInteger getUserId() {
		return userId;
	}
}
