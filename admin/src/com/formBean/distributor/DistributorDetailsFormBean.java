/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.distributor;

/**
 *  Updated By 		: Manoj
 *  Updated Date 	: 6-June 2013
 *  Updated Matter  : Code as well as DB optimization
 *  
 */

public class DistributorDetailsFormBean  {
	
	private int distributorId;
	private String  distributorInitial;

	private String param;

		
	private String firstName;	
	private String lastName;
	private String name;
	private String 	mdId;	
	private String companyName;	
	private String addressLine1;	
	private String addressLine2;	
	private String pincode;
	private String city;
	private String District;
	private String state;
	private String country;
	private String emailId;
	private String mobileNo;
	private String dob;	
	private String gender;
 	private String companyType;
	private String panNo;
	private String dateOfJoining;
	private String updateUser;
	private String MPIN;
	private String clientID;
	
	public DistributorDetailsFormBean() {
		
	}
	
	public DistributorDetailsFormBean(int distributorId, String distributorInitial, String mdId, String firstName, String lastName,
			String companyName, String addressLine1, String addressLine2, String pincode, String city,
			String district, String state, String country, String emailId, String mobileNo, String dob, String gender,
			String companyType, String panNo, String dateOfJoining) {
		super();
		this.distributorId = distributorId;
		this.distributorInitial = distributorInitial;
		this.mdId = mdId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.pincode = pincode;
		this.city = city;
		District = district;
		this.state = state;
		this.country = country;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.dob = dob;
		this.gender = gender;
		this.companyType = companyType;
		this.panNo = panNo;
		this.dateOfJoining = dateOfJoining;
	}
	
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getMPIN() {
		return MPIN;
	}
	public void setMPIN(String mPIN) {
		MPIN = mPIN;
	}
	public int getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorInitial() {
		return distributorInitial;
	}
	public void setDistributorInitial(String distributorInitial) {
		this.distributorInitial = distributorInitial;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
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
		return District;
	}
	public void setDistrict(String district) {
		District = district;
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
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public void setMdId(String mdId) {
		this.mdId = mdId;
	}
	public String getMdId() {
		return mdId;
	}
	
}
