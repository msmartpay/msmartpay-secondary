/*
  Class Property :  This class (LoginForm) is created to define setter getter method of panel details.

  Created Date   : 03-Dec-2011 at 9:42 PM.
  Created By     : Bharatveer Singh.

  Updated Date   : 03-Dec-2011.
  Update By      : Bharatveer Singh

*/
package com.paneldetails;
import java.io.Serializable;

@SuppressWarnings("serial")
public class PanelDetailForm  implements Serializable {

	private String loginUrl;
	private String innerHeaderImage;
	private String helpEmailId;
	private String helpMobileNo;
	private String titlePage;
	private String copyRight;
	private String poweredBy;
	private String clientId;
	private String panelType;
	private String domainName;
	private String BalanceDesk;
	private String WhitelableCompanyName;
	private String agentLoginUrl;
    private String agentCellEmailId;
	private String param;
	
	private String Help_Desk_MobileNo;
	private String Help_Desk_EmailID;
	private String distributor_Ticker;
	
	
	
	public String getDistributor_Ticker() {
		return distributor_Ticker;
	}
	public void setDistributor_Ticker(String distributor_Ticker) {
		this.distributor_Ticker = distributor_Ticker;
	}
	public String getHelp_Desk_EmailID() {
		return Help_Desk_EmailID;
	}
	public String getHelp_Desk_MobileNo() {
		return Help_Desk_MobileNo;
	}
	public void setHelp_Desk_MobileNo(String help_Desk_MobileNo) {
		Help_Desk_MobileNo = help_Desk_MobileNo;
	}
	public void setHelp_Desk_EmailID(String help_Desk_EmailID) {
		Help_Desk_EmailID = help_Desk_EmailID;
	}
	public String getAgentLoginUrl() {
		return agentLoginUrl;
	}
	public void setAgentLoginUrl(String agentLoginUrl) {
		this.agentLoginUrl = agentLoginUrl;
	}
	public String getAgentCellEmailId() {
		return agentCellEmailId;
	}
	public void setAgentCellEmailId(String agentCellEmailId) {
		this.agentCellEmailId = agentCellEmailId;
	}
	
	
	
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getPanelType() {
		return panelType;
	}
	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getInnerHeaderImage() {
		return innerHeaderImage;
	}
	public void setInnerHeaderImage(String innerHeaderImage) {
		this.innerHeaderImage = innerHeaderImage;
	}
	public String getHelpEmailId() {
		return helpEmailId;
	}
	public void setHelpEmailId(String helpEmailId) {
		this.helpEmailId = helpEmailId;
	}
	public String getHelpMobileNo() {
		return helpMobileNo;
	}
	public void setHelpMobileNo(String helpMobileNo) {
		this.helpMobileNo = helpMobileNo;
	}
	public String getTitlePage() {
		return titlePage;
	}
	public void setTitlePage(String titlePage) {
		this.titlePage = titlePage;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getPoweredBy() {
		return poweredBy;
	}
	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}
	public String getBalanceDesk() {
		return BalanceDesk;
	}
	public void setBalanceDesk(String balanceDesk) {
		BalanceDesk = balanceDesk;
	}
	public String getParam() {
		return param;
	}
	public String getWhitelableCompanyName() {
		return WhitelableCompanyName;
	}
	public void setWhitelableCompanyName(String whitelableCompanyName) {
		WhitelableCompanyName = whitelableCompanyName;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
}
