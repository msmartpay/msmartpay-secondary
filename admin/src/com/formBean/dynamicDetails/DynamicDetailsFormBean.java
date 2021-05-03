/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.dynamicDetails;

import java.util.Date;


public class DynamicDetailsFormBean  {//implements Serializable {
	
	private int clientId;
	private String adminLoginUrl;
	private String helpMobileNo;
	private String helpEmailId;
	private String domainName;
	private String mdLoginUrl;
	private String companyName;
	private String distributorLoginUrl;
	private String agentLoginUrl;
	private String clientFlag;
	private String status;
	private String blockStatus;
	private String validUpto;
    private String marginSetupWLClient;
	private String agentUserType;
    private String distributorUserType;
    private String mdsUserType;
    
    private String ClientUserType;
    private String careUrl;
    private String categoryName;
    private String LogoImage;
    private String powerBy;
    private String registrationDate;
    private String txnNotificationID;
    private String agentTicker;
    private String dsTicker;
    private String mdsTicker;
    private Date tickerUpdatedDate;
    private String tickerUpdatedBy;

    public String getLogoImage() {
		return LogoImage;
	}
	public void setLogoImage(String logoImage) {
		LogoImage = logoImage;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDistributorLoginUrl() {
		return distributorLoginUrl;
	}
	public void setDistributorLoginUrl(String distributorLoginUrl) {
		this.distributorLoginUrl = distributorLoginUrl;
	}
	public String getAgentLoginUrl() {
		return agentLoginUrl;
	}
	public void setAgentLoginUrl(String agentLoginUrl) {
		this.agentLoginUrl = agentLoginUrl;
	}

	public String getMdLoginUrl() {
		return mdLoginUrl;
	}
	public void setMdLoginUrl(String mdLoginUrl) {
		this.mdLoginUrl = mdLoginUrl;
	}

		
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAdminLoginUrl() {
		return adminLoginUrl;
	}
	public void setAdminLoginUrl(String adminLoginUrl) {
		this.adminLoginUrl = adminLoginUrl;
	}
	public String getHelpMobileNo() {
		return helpMobileNo;
	}
	public void setHelpMobileNo(String helpMobileNo) {
		this.helpMobileNo = helpMobileNo;
	}
	public String getHelpEmailId() {
		return helpEmailId;
	}
	public void setHelpEmailId(String helpEmailId) {
		this.helpEmailId = helpEmailId;
	}

	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public void setClientFlag(String clientFlag) {
		this.clientFlag = clientFlag;
	}
	public String getClientFlag() {
		return clientFlag;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}
	public String getBlockStatus() {
		return blockStatus;
	}
	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}
	public String getValidUpto() {
		return validUpto;
	}
	public void setMdsUserType(String mdsUserType) {
		this.mdsUserType = mdsUserType;
	}
	public String getMdsUserType() {
		return mdsUserType;
	}
	public void setDistributorUserType(String distributorUserType) {
		this.distributorUserType = distributorUserType;
	}
	public String getDistributorUserType() {
		return distributorUserType;
	}
	public void setAgentUserType(String agentUserType) {
		this.agentUserType = agentUserType;
	}
	public String getAgentUserType() {
		return agentUserType;
	}

	public String getCareUrl() {
		return careUrl;
	}
	public void setCareUrl(String careUrl) {
		this.careUrl = careUrl;
	}
	public String getClientUserType() {
		return ClientUserType;
	}
	public void setClientUserType(String clientUserType) {
		ClientUserType = clientUserType;
	}
	public String getMarginSetupWLClient() {
		return marginSetupWLClient;
	}
	public void setMarginSetupWLClient(String marginSetupWLClient) {
		this.marginSetupWLClient = marginSetupWLClient;
	}
	public void setPowerBy(String powerBy) {
		this.powerBy = powerBy;
	}
	public String getPowerBy() {
		return powerBy;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public String getTxnNotificationID() {
		return txnNotificationID;
	}
	public void setTxnNotificationID(String txnNotificationID) {
		this.txnNotificationID = txnNotificationID;
	}
	public String getAgentTicker() {
		return agentTicker;
	}
	public void setAgentTicker(String agentTicker) {
		this.agentTicker = agentTicker;
	}
	public String getDsTicker() {
		return dsTicker;
	}
	public void setDsTicker(String dsTicker) {
		this.dsTicker = dsTicker;
	}
	public String getMdsTicker() {
		return mdsTicker;
	}
	public void setMdsTicker(String mdsTicker) {
		this.mdsTicker = mdsTicker;
	}
	
	public Date getTickerUpdatedDate() {
		return tickerUpdatedDate;
	}
	public void setTickerUpdatedDate(Date tickerUpdatedDate) {
		this.tickerUpdatedDate = tickerUpdatedDate;
	}
	public String getTickerUpdatedBy() {
		return tickerUpdatedBy;
	}
	public void setTickerUpdatedBy(String tickerUpdatedBy) {
		this.tickerUpdatedBy = tickerUpdatedBy;
	}
	
	
	
	
	
}
