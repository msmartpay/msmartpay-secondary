/*
  Class Property :  This class (LoginForm) is created to define setter getter method of login request parameter .

  Created Date   : 12-Dec-2011 at 4:41 PM.
  Created By     : Prachi Sharma.

  Updated Date   : 12-Dec-2011 at 4:41 PM.
  Update By      : Prachi Sharma.

*/
package com.formBean.EGEN;

import java.io.File;

@SuppressWarnings("serial")
public class APIClientDetailsFormBean  {//implements Serializable {

	private String param;

	private String corporateAgentId;
	private String corporateAgentInitial;
	private String loginUserName;
	private String password;
	private String confirmPassword;

	private String firstName;	
	private String middleName;	
	private String lastName;
	private String companyName;	
    private String dob;
	private String designation;	
	private String panNo;
	private String officeAddress;	
	private String district;
	private String state;
	private String city;
	private String country;	
	private String landmark;
	private String pincode;
	private String mobileNo;
	private String officePhoneNo;	
	private String officeFaxNo;
	private String emailId;	
	private String website;
 	private String resAddress;
    private String resCity;
 	private String resDistrict;
	private String resCountry;
	private String resState;
	private String resLandmark;
	private String resPincode;
	private String resMobileNo;
	private String resPhoneNo;
	private String resFaxNo;
 	private String authorizedMobileNo;
	private String idProof;
	private String businessProof;
	private String addressProof;
	private File idProofFile;
	private String idProofFileContentType; 
    private String idProofFileFileName;
	private File businessProofFile;	
	private String businessProofFileFileName;	
	private File addressProofFile;	 
    private String addressProofFileFileName;	
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String category;	
	
	private String authorizedIp1;
	private String authorizedIp2;
	
	private String createdUser;
	private String dateOfJoining;
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getCorporateAgentInitial() {
		return corporateAgentInitial;
	}
	public void setCorporateAgentInitial(String corporateAgentInitial) {
		this.corporateAgentInitial = corporateAgentInitial;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		System.out.println("lastName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+lastName);
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
		System.out.println("companyName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+companyName);
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
		System.out.println("dob>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dob);
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
		System.out.println("designation>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+designation);
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
		System.out.println("panNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+panNo);
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
		System.out.println("officeAddress>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+officeAddress);
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
		System.out.println("district>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+district);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
		System.out.println("state>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+state);
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
		System.out.println("country>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+country);
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
		System.out.println("landmark>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+landmark);
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
		System.out.println("pincode>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+pincode);
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
		System.out.println("mobileNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+mobileNo);
	}
	public String getOfficePhoneNo() {
		return officePhoneNo;
		
	}
	public void setOfficePhoneNo(String officePhoneNo) {
		this.officePhoneNo = officePhoneNo;
		System.out.println("officePhoneNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+officePhoneNo);
	}
	public String getOfficeFaxNo() {
		return officeFaxNo;
	}
	public void setOfficeFaxNo(String officeFaxNo) {
		this.officeFaxNo = officeFaxNo;
		System.out.println("officeFaxNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+officeFaxNo);
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
		System.out.println("emailId>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+emailId);
	}
	public String getWebsite() {
		return website;

	}
	public void setWebsite(String website) {
		this.website = website;
		System.out.println("website>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+website);
	}
	public String getResAddress() {
		return resAddress;
	}
	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
		System.out.println("resAddress>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resAddress);
	}
	public String getResCity() {
		return resCity;
	}
	public void setResCity(String resCity) {
		this.resCity = resCity;
		System.out.println("resCity>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resCity);
	}
	public String getResDistrict() {
		return resDistrict;
	}
	public void setResDistrict(String resDistrict) {
		this.resDistrict = resDistrict;
		System.out.println("resDistrict>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resDistrict);
	}
	public String getResCountry() {
		return resCountry;
	}
	public void setResCountry(String resCountry) {
		this.resCountry = resCountry;
		System.out.println("resCountry>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resCountry);
	}
	public String getResState() {
		return resState;
	}
	public void setResState(String resState) {
		this.resState = resState;
		System.out.println("resState>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resState);
	}
	public String getResLandmark() {
		return resLandmark;
	}
	public void setResLandmark(String resLandmark) {
		this.resLandmark = resLandmark;
		System.out.println("resLandmark>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resLandmark);
	}
	public String getResMobileNo() {
		return resMobileNo;
	}
	public void setResMobileNo(String resMobileNo) {
		this.resMobileNo = resMobileNo;
		System.out.println("resMobileNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resMobileNo);
	}
	public String getResPhoneNo() {
		return resPhoneNo;
	}
	public void setResPhoneNo(String resPhoneNo) {
		this.resPhoneNo = resPhoneNo;
		System.out.println("resPhoneNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resPhoneNo);
	}
	public String getResFaxNo() {
		return resFaxNo;
	}
	public void setResFaxNo(String resFaxNo) {
		this.resFaxNo = resFaxNo;
		System.out.println("resFaxNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resFaxNo);
	}
	public String getAuthorizedMobileNo() {
		return authorizedMobileNo;
	}
	public void setAuthorizedMobileNo(String authorizedMobileNo) {
		this.authorizedMobileNo = authorizedMobileNo;
		System.out.println("authorizedMobileNo>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+authorizedMobileNo);
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
		System.out.println("idProof>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+idProof);
	}
	public String getBusinessProof() {
		return businessProof;
	}
	public void setBusinessProof(String businessProof) {
		this.businessProof = businessProof;
		System.out.println("businessProof>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+businessProof);
	}
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
		System.out.println("addressProof>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+addressProof);
	}
	public File getIdProofFile() {
		return idProofFile;
	}
	public void setIdProofFile(File idProofFile) {
		this.idProofFile = idProofFile;
		System.out.println("idProofFile>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+idProofFile);
	}
	public String getIdProofFileContentType() {
		return idProofFileContentType;
	}
	public void setIdProofFileContentType(String idProofFileContentType) {
		this.idProofFileContentType = idProofFileContentType;
		System.out.println("idProofFileContentType>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+idProofFileContentType);
	}
	public String getIdProofFileFileName() {
		return idProofFileFileName;
	}
	public void setIdProofFileFileName(String idProofFileFileName) {
		this.idProofFileFileName = idProofFileFileName;
		System.out.println("idProofFileFileName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+idProofFileFileName);
	}
	public File getBusinessProofFile() {
		return businessProofFile;
	}
	public void setBusinessProofFile(File businessProofFile) {
		this.businessProofFile = businessProofFile;
		System.out.println("businessProofFile>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+businessProofFile);
	}
	public String getBusinessProofFileFileName() {
		return businessProofFileFileName;
	}
	public void setBusinessProofFileFileName(String businessProofFileFileName) {
		this.businessProofFileFileName = businessProofFileFileName;
		System.out.println("businessProofFileFileName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+businessProofFileFileName);

	}
	public File getAddressProofFile() {
		return addressProofFile;
	}
	public void setAddressProofFile(File addressProofFile) {
		this.addressProofFile = addressProofFile;
		System.out.println("addressProofFile>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+addressProofFile);

	}
	public String getAddressProofFileFileName() {
		return addressProofFileFileName;
	}
	public void setAddressProofFileFileName(String addressProofFileFileName) {
		this.addressProofFileFileName = addressProofFileFileName;
		System.out.println("addressProofFileFileName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+addressProofFileFileName);
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
		System.out.println("fatherName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+fatherName);
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
		System.out.println("motherName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+motherName);
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
		System.out.println("spouseName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+spouseName);
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
		System.out.println("category>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+category);
	}
	public String getResPincode() {
		return resPincode;
	}
	public void setResPincode(String resPincode) {
		this.resPincode = resPincode;
		System.out.println("resPincode>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+resPincode);
	}
	public void setCorporateAgentId(String corporateAgentId) {
		this.corporateAgentId = corporateAgentId;
		System.out.println("corporateAgentId>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+corporateAgentId);
	}
	public String getCorporateAgentId() {
		return corporateAgentId;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
		System.out.println("createdUser>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+createdUser);
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
System.out.println("loginUserName>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+loginUserName);
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setPassword(String password) {
		this.password = password;
System.out.println("password>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+password);
	}
	public String getPassword() {
		return password;
	}
	public void setAuthorizedIp2(String authorizedIp2) {
		this.authorizedIp2 = authorizedIp2;
		System.out.println("authorizedIp2>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+authorizedIp2);
	}
	public String getAuthorizedIp2() {
		return authorizedIp2;
	}
	public void setAuthorizedIp1(String authorizedIp1) {
		this.authorizedIp1 = authorizedIp1;
		System.out.println("authorizedIp1>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+authorizedIp1);
	}
	public String getAuthorizedIp1() {
		return authorizedIp1;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		System.out.println("confirmPassword>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+confirmPassword);
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setCity(String city) {
		this.city = city;
System.out.println("city>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+city);
	}
	public String getCity() {
		return city;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	
	

	
}
