/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.mds;

import java.util.Date;

/**
 * Updated By 		: Manoj
 * Updated Date 	: 5 June 2013
 * Updated Matter	: Optimization of code as well as data base.
 */

public class MdsDetailsFormBean  {
	
	private String param;
	private int mdId;
	private String mdInitial;
	
	
	private String firstName;	
	private String lastName;
	private String name;
	private int clientId;	
	private String companyName;	
	private String addressLine1;	
	private String addressLine2;	
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
	private String pincode;
	private Date dateOfJoining;
	private String updateUser;
	private String portalID;
	private String MPIN;


    
	public String getMPIN() {
		return MPIN;
	}
	public void setMPIN(String mPIN) {
		MPIN = mPIN;
	}
	public String getPortalID() {
		return portalID;
	}
	public void setPortalID(String portalID) {
		this.portalID = portalID;
	}
	public int getMdId() {
		return mdId;
	}
	public void setMdId(int mdId) {
		this.mdId = mdId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
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
	
	public void setMdInitial(String mdInitial) {
		this.mdInitial = mdInitial;
	}
	public String getMdInitial() {
		return mdInitial;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	
 
}
