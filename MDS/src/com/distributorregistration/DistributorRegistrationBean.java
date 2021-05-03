
package com.distributorregistration;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
 public class DistributorRegistrationBean extends ActionForm 
{
	           private FormFile idFile;
	           private FormFile addFile;
	           private FormFile businessFile;

                String initial;
				String firstName;
				String middleName;
				//String password;
				String lastName;
				String dob;
                String gender;
				String maritalStatus;
				String occupation;
				String officeAddress1;
				String officeAddress2;
				String state;
				String District;
				String officeCountry;
				String city;
				String officePincode;
				String officelandmark;
				String officeMobileNo;
				String officeAlternateNo;
				String officeSTDCode;
				String officePhone;
				String officefax;
				String officeEmailId;
				String officeWebsite;
				String resAddress1;
				String resAddress2;
				String resState;
				String resDistrict;
				String resCity;
				String resCountry;
				String resPincode;
				String reslandmark;
				String resMobileNo;
				String resAlternateNo;
				String resSTDCode;
				String resPhoneNo;
				String resFaxNo;
				String otherOccupation;
				String companyType;
				String otherCompanyType;
			    String companyName;
                String designation;
				String otherDesignation;
		        String panNo;
				String officeCheckValue;
                String residenceCheckValue;
                String authorizedMobileNo;
				String idProof;
				String addressProof;
				String bussinessProof;
			    String fatherName;
				String motherName;
                String spouseInitial;
				String spouseName;
				String category;
				
                
	  public FormFile getIdFile() {
		return idFile;
	}
	public void setIdFile(FormFile idFile) {
		this.idFile = idFile;
	}
	public FormFile getAddFile() {
		return addFile;
	}
	public void setAddFile(FormFile addFile) {
		this.addFile = addFile;
	}
	public FormFile getBusinessFile() {
		return businessFile;
	}
	public void setBusinessFile(FormFile businessFile) {
		this.businessFile = businessFile;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
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
/*	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}*/
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOfficeAddress1() {
		return officeAddress1;
	}
	public void setOfficeAddress1(String officeAddress1) {
		this.officeAddress1 = officeAddress1;
	}
	public String getOfficeAddress2() {
		return officeAddress2;
	}
	public void setOfficeAddress2(String officeAddress2) {
		this.officeAddress2 = officeAddress2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getOfficeCountry() {
		return officeCountry;
	}
	public void setOfficeCountry(String officeCountry) {
		this.officeCountry = officeCountry;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getOfficePincode() {
		return officePincode;
	}
	public void setOfficePincode(String officePincode) {
		this.officePincode = officePincode;
	}
	public String getOfficelandmark() {
		return officelandmark;
	}
	public void setOfficelandmark(String officelandmark) {
		this.officelandmark = officelandmark;
	}
	public String getOfficeMobileNo() {
		return officeMobileNo;
	}
	public void setOfficeMobileNo(String officeMobileNo) {
		this.officeMobileNo = officeMobileNo;
	}
	public String getOfficeAlternateNo() {
		return officeAlternateNo;
	}
	public void setOfficeAlternateNo(String officeAlternateNo) {
		this.officeAlternateNo = officeAlternateNo;
	}
	public String getOfficeSTDCode() {
		return officeSTDCode;
	}
	public void setOfficeSTDCode(String officeSTDCode) {
		this.officeSTDCode = officeSTDCode;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getOfficefax() {
		return officefax;
	}
	public void setOfficefax(String officefax) {
		this.officefax = officefax;
	}
	public String getOfficeEmailId() {
		return officeEmailId;
	}
	public void setOfficeEmailId(String officeEmailId) {
		this.officeEmailId = officeEmailId;
	}
	public String getOfficeWebsite() {
		return officeWebsite;
	}
	public void setOfficeWebsite(String officeWebsite) {
		this.officeWebsite = officeWebsite;
	}
	public String getResAddress1() {
		return resAddress1;
	}
	public void setResAddress1(String resAddress1) {
		this.resAddress1 = resAddress1;
	}
	public String getResAddress2() {
		return resAddress2;
	}
	public void setResAddress2(String resAddress2) {
		this.resAddress2 = resAddress2;
	}
	public String getResState() {
		return resState;
	}
	public void setResState(String resState) {
		this.resState = resState;
	}
	public String getResDistrict() {
		return resDistrict;
	}
	public void setResDistrict(String resDistrict) {
		this.resDistrict = resDistrict;
	}
	public String getResCity() {
		return resCity;
	}
	public void setResCity(String resCity) {
		this.resCity = resCity;
	}
	public String getResCountry() {
		return resCountry;
	}
	public void setResCountry(String resCountry) {
		this.resCountry = resCountry;
	}
	public String getResPincode() {
		return resPincode;
	}
	public void setResPincode(String resPincode) {
		this.resPincode = resPincode;
	}
	public String getReslandmark() {
		return reslandmark;
	}
	public void setReslandmark(String reslandmark) {
		this.reslandmark = reslandmark;
	}
	public String getResMobileNo() {
		return resMobileNo;
	}
	public void setResMobileNo(String resMobileNo) {
		this.resMobileNo = resMobileNo;
	}
	public String getResAlternateNo() {
		return resAlternateNo;
	}
	public void setResAlternateNo(String resAlternateNo) {
		this.resAlternateNo = resAlternateNo;
	}
	public String getResSTDCode() {
		return resSTDCode;
	}
	public void setResSTDCode(String resSTDCode) {
		this.resSTDCode = resSTDCode;
	}
	public String getResPhoneNo() {
		return resPhoneNo;
	}
	public void setResPhoneNo(String resPhoneNo) {
		this.resPhoneNo = resPhoneNo;
	}
	public String getResFaxNo() {
		return resFaxNo;
	}
	public void setResFaxNo(String resFaxNo) {
		this.resFaxNo = resFaxNo;
	}
	public String getOtherOccupation() {
		return otherOccupation;
	}
	public void setOtherOccupation(String otherOccupation) {
		this.otherOccupation = otherOccupation;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getOtherCompanyType() {
		return otherCompanyType;
	}
	public void setOtherCompanyType(String otherCompanyType) {
		this.otherCompanyType = otherCompanyType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getOfficeCheckValue() {
		return officeCheckValue;
	}
	public void setOfficeCheckValue(String officeCheckValue) {
		this.officeCheckValue = officeCheckValue;
	}
	public String getResidenceCheckValue() {
		return residenceCheckValue;
	}
	public void setResidenceCheckValue(String residenceCheckValue) {
		this.residenceCheckValue = residenceCheckValue;
	}
	public String getAuthorizedMobileNo() {
		return authorizedMobileNo;
	}
	public void setAuthorizedMobileNo(String authorizedMobileNo) {
		this.authorizedMobileNo = authorizedMobileNo;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public String getAddressProof() {
		return addressProof;
	}
	public void setAddressProof(String addressProof) {
		this.addressProof = addressProof;
	}
	public String getBussinessProof() {
		return bussinessProof;
	}
	public void setBussinessProof(String bussinessProof) {
		this.bussinessProof = bussinessProof;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getSpouseInitial() {
		return spouseInitial;
	}
	public void setSpouseInitial(String spouseInitial) {
		this.spouseInitial = spouseInitial;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	 
}
