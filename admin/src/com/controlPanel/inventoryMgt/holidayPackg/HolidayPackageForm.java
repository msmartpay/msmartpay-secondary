package com.controlPanel.inventoryMgt.holidayPackg;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;


/* 
 * Created By : Manoj Kumar
 * Created Date : 25/11/2013
 * Purpose : To store Car details
 */
public class HolidayPackageForm 
{
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	
	
	public File getUserImage() {
		return userImage;
	}
	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}
	public String getUserImageContentType() {
		return userImageContentType;
	}
	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}
	public String getUserImageFileName() {
		return userImageFileName;
	}
	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}


	private String datetime;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	private int sNo;
	
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	private int pkgId;
	
	public int getPkgId() {
		return pkgId;
	}
	public void setPkgId(int pkgId) {
		this.pkgId = pkgId;
	}


	private BigInteger userId;
	private String packageType;
	private String DepartCity;
	private String ArrivalCity;
	private String travelBy;
	private String packagePrice;
	private String packagePriceIncludes;
	private String travelType;
	private String hotelBudgetFrom;
	private String hotelBudgetTo;
	
	private String hotelBudget;
	
	public String getHotelBudget() {
		return hotelBudget;
	}
	public void setHotelBudget(String hotelBudget) {
		this.hotelBudget = hotelBudget;
	}

	private String duration;
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}


	private String days;
	private String nights;
	private String HotelName;
	private String star2;
	private String mealPref;
	private String sightSeen;
	private String mealDishes;
	private String placeToView;
	private String placeDescription;
	private String packageTitle;
	private String inclusionHotel;
	private String inclusionTransport;
	private String inclusionOthers;
	private String packageInitierary;
	private String specialReqt;
	private String otherInst;
	private String termCond;
	private String cancelPolicy;
	
	
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getDepartCity() {
		return DepartCity;
	}
	public void setDepartCity(String departCity) {
		DepartCity = departCity;
	}
	public String getArrivalCity() {
		return ArrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		ArrivalCity = arrivalCity;
	}
	public String getTravelBy() {
		return travelBy;
	}
	public void setTravelBy(String travelBy) {
		this.travelBy = travelBy;
	}
	public String getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(String packagePrice) {
		this.packagePrice = packagePrice;
	}
	public String getPackagePriceIncludes() {
		return packagePriceIncludes;
	}
	public void setPackagePriceIncludes(String packagePriceIncludes) {
		this.packagePriceIncludes = packagePriceIncludes;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getHotelBudgetFrom() {
		return hotelBudgetFrom;
	}
	public void setHotelBudgetFrom(String hotelBudgetFrom) {
		this.hotelBudgetFrom = hotelBudgetFrom;
	}
	public String getHotelBudgetTo() {
		return hotelBudgetTo;
	}
	public void setHotelBudgetTo(String hotelBudgetTo) {
		this.hotelBudgetTo = hotelBudgetTo;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getNights() {
		return nights;
	}
	public void setNights(String nights) {
		this.nights = nights;
	}
	public String getHotelName() {
		return HotelName;
	}
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	public String getStar2() {
		return star2;
	}
	public void setStar2(String star2) {
		this.star2 = star2;
	}
	public String getMealPref() {
		return mealPref;
	}
	public void setMealPref(String mealPref) {
		this.mealPref = mealPref;
	}
	public String getSightSeen() {
		return sightSeen;
	}
	public void setSightSeen(String sightSeen) {
		this.sightSeen = sightSeen;
	}
	public String getMealDishes() {
		return mealDishes;
	}
	public void setMealDishes(String mealDishes) {
		this.mealDishes = mealDishes;
	}
	public String getPlaceToView() {
		return placeToView;
	}
	public void setPlaceToView(String placeToView) {
		this.placeToView = placeToView;
	}
	public String getPlaceDescription() {
		return placeDescription;
	}
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
	public String getPackageTitle() {
		return packageTitle;
	}
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	public String getInclusionHotel() {
		return inclusionHotel;
	}
	public void setInclusionHotel(String inclusionHotel) {
		this.inclusionHotel = inclusionHotel;
	}
	public String getInclusionTransport() {
		return inclusionTransport;
	}
	public void setInclusionTransport(String inclusionTransport) {
		this.inclusionTransport = inclusionTransport;
	}
	public String getInclusionOthers() {
		return inclusionOthers;
	}
	public void setInclusionOthers(String inclusionOthers) {
		this.inclusionOthers = inclusionOthers;
	}
	public String getPackageInitierary() {
		return packageInitierary;
	}
	public void setPackageInitierary(String packageInitierary) {
		this.packageInitierary = packageInitierary;
	}
	public String getSpecialReqt() {
		return specialReqt;
	}
	public void setSpecialReqt(String specialReqt) {
		this.specialReqt = specialReqt;
	}
	public String getOtherInst() {
		return otherInst;
	}
	public void setOtherInst(String otherInst) {
		this.otherInst = otherInst;
	}
	public String getTermCond() {
		return termCond;
	}
	public void setTermCond(String termCond) {
		this.termCond = termCond;
	}
	public String getCancelPolicy() {
		return cancelPolicy;
	}
	public void setCancelPolicy(String cancelPolicy) {
		this.cancelPolicy = cancelPolicy;
	}


	
	
}
