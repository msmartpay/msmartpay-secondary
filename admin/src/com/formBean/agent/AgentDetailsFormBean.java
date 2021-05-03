/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

 */
package com.formBean.agent;
/**
 * 	Updated By : Manoj
 * 	Updated Date : 07-June-2013
 * 	Updated Matter : Code as well as Database Optimiazation
 */
public class AgentDetailsFormBean  {	

	private String 	param;
	private int 	agentId;
	private String  agentInitial;
	private int 	distId;
	private String distributorId;
	private String firstName;	
	private String lastName;
	private String name;
	private String dateOfJoining;		
	private String companyName;	
	private String addressLine1;	
	private String addressLine2;	
	private String pincode;
	private String city;
	private String district;
	private String state;
	private String country;
	private String emailId;
	private String mobileNo;
	private String dob;	
	private String gender;
	private String companyType;
	private String panNo;
	private String category;
	private String MPIN;
	private String APIN;
	private String clientID;
	private String aadhaarNo;
	private int kycStatus;
	private int apiStatus;

	public AgentDetailsFormBean() {

	}

	public AgentDetailsFormBean(int agentId, String agentInitial, int distId, String firstName,
			String lastName, String dateOfJoining, String companyName, String addressLine1, String addressLine2,
			String pincode, String city, String district, String state, String country, String emailId, String mobileNo,
			String dob, String gender, String companyType, String panNo, String aadhaarNo) {
		super();
		this.agentId = agentId;
		this.agentInitial = agentInitial;
		this.distId = distId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfJoining = dateOfJoining;
		this.companyName = companyName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.dob = dob;
		this.gender = gender;
		this.companyType = companyType;
		this.panNo = panNo;
		this.aadhaarNo = aadhaarNo;
	}

	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getAPIN() {
		return APIN;
	}
	public void setAPIN(String aPIN) {
		APIN = aPIN;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getAgentInitial() {
		return agentInitial;
	}
	public void setAgentInitial(String agentInitial) {
		this.agentInitial = agentInitial;
	}
	public int getDistId() {
		return distId;
	}
	public void setDistId(int distId) {
		this.distId = distId;
	}
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMPIN() {
		return MPIN;
	}
	public void setMPIN(String mPIN) {
		MPIN = mPIN;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public int getKycStatus() {
		return kycStatus;
	}
	public void setKycStatus(int kycStatus) {
		this.kycStatus = kycStatus;
	}

	public int getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(int apiStatus) {
		this.apiStatus = apiStatus;
	}




















}
