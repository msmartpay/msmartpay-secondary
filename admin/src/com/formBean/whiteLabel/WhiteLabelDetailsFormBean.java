/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.whiteLabel;
/**
 * Updated By :Manoj
 * Updated Date : 1 Jun 2013
 * Updated Matter : Optimization of code as well as data base.
 */
import java.io.File;

public class WhiteLabelDetailsFormBean  {
	
	private int clientId;
	
    private String clientType;
    private String categoryName;
	private String companyName;
	private String domainName;
	private String agentPanelHelpEmailId;
	private String agentPanelHelpMobileNo1;
	private String agentCellEmailId;
	private String careUrl;
	private File headerHomeImage;
	private String headerHomeImageFileName;
	private String param;
	
	private String firstName;	
	private String lastName;	
	private String dob;	
	private String gender;
	private String designation;
	private String emailId;	
	private String personalNumber;
	private String addressLine1;
 	private String addressLine2;
	private String country;
	private String state;
	private String city;
	private String district;
	private String pincode;
	private String maritalStatus;
	private String employeeCode;
	
	private String otherDesignation;
	private String activityDeskName;
	private String otherDeskName;
	private String officialNumber;
	
	private String alternateNumber;	
	
	private String landmark;
	private String personalEmailId;
	private String dateOfJoining;
	private String authorizedToCutoff;
    private double cutOffLimit;
    private String updateUser;
    private String PanNo;

	    

	
	public String getHeaderHomeImageFileName() {
			return headerHomeImageFileName;
		}
		public void setHeaderHomeImageFileName(String headerHomeImageFileName) {
			this.headerHomeImageFileName = headerHomeImageFileName;
		}

		public File getHeaderHomeImage() {
			return headerHomeImage;
		}
		public void setHeaderHomeImage(File headerHomeImage) {
			this.headerHomeImage = headerHomeImage;
		}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getAgentPanelHelpMobileNo1() {
		return agentPanelHelpMobileNo1;
	}
	public void setAgentPanelHelpMobileNo1(String agentPanelHelpMobileNo1) {
		this.agentPanelHelpMobileNo1 = agentPanelHelpMobileNo1;
	}

	public String getAgentPanelHelpEmailId() {
		return agentPanelHelpEmailId;
	}
	public void setAgentPanelHelpEmailId(String agentPanelHelpEmailId) {
		this.agentPanelHelpEmailId = agentPanelHelpEmailId;
	}

	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getAgentCellEmailId() {
		return agentCellEmailId;
	}
	public void setAgentCellEmailId(String agentCellEmailId) {
		this.agentCellEmailId = agentCellEmailId;
	}

	public String getCareUrl() {
		return careUrl;
	}
	public void setCareUrl(String careUrl) {
		this.careUrl = careUrl;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getParam() {
		return param;
	}
	

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOtherDesignation() {
		return otherDesignation;
	}
	public void setOtherDesignation(String otherDesignation) {
		this.otherDesignation = otherDesignation;
	}
	public String getActivityDeskName() {
		return activityDeskName;
	}
	public void setActivityDeskName(String activityDeskName) {
		this.activityDeskName = activityDeskName;
	}
	public String getOtherDeskName() {
		return otherDeskName;
	}
	public void setOtherDeskName(String otherDeskName) {
		this.otherDeskName = otherDeskName;
	}
	public String getOfficialNumber() {
		return officialNumber;
	}
	public void setOfficialNumber(String officialNumber) {
		this.officialNumber = officialNumber;
	}
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	public String getAlternateNumber() {
		return alternateNumber;
	}
	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPersonalEmailId() {
		return personalEmailId;
	}
	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getAuthorizedToCutoff() {
		return authorizedToCutoff;
	}
	public void setAuthorizedToCutoff(String authorizedToCutoff) {
		this.authorizedToCutoff = authorizedToCutoff;
	}
	public double getCutOffLimit() {
		return cutOffLimit;
	}
	public void setCutOffLimit(double cutOffLimit) {
		this.cutOffLimit = cutOffLimit;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public void setPanNo(String panNo) {
		PanNo = panNo;
	}
	public String getPanNo() {
		return PanNo;
	}
	
}
