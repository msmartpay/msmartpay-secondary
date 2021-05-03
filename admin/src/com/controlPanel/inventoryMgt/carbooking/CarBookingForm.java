package com.controlPanel.inventoryMgt.carbooking;

/* 
 * Created By : Manoj Kumar
 * Created Date : 25/11/2013
 * Purpose : To store Car details
 */

import java.io.File;
import java.math.BigInteger;
import java.util.Date;

public class CarBookingForm 
{
	
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	
	private int sNo;
	private BigInteger userId;
	private String carBooking;
	
	private String BookingType;
	private String SubBookingType;
	
	private String segment;
	private String originCity;
	private String destinationCity;
	private String daysavail;
	private String carName;
	private String vendorName;
	private double TripPrice;
	private int seatingCapacity;
	private String airCondition;
	private String stereo;
	private String ChargePerKm;
	private String AdvancePayment;
	private String SpecialOffers;
	private String termCondition;
	private String canPolicy;
	private int bookingId;
	
	private String fuelType;
	private String transmissionType;
	private String NofBaggage;
	
	
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
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getCarBooking() {
		return carBooking;
	}
	public void setCarBooking(String carBooking) {
		this.carBooking = carBooking;
	}
	public String getBookingType() {
		return BookingType;
	}
	public void setBookingType(String bookingType) {
		BookingType = bookingType;
	}
	public String getSubBookingType() {
		return SubBookingType;
	}
	public void setSubBookingType(String subBookingType) {
		SubBookingType = subBookingType;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public String getDaysavail() {
		return daysavail;
	}
	public void setDaysavail(String daysavail) {
		this.daysavail = daysavail;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public double getTripPrice() {
		return TripPrice;
	}
	public void setTripPrice(double tripPrice) {
		TripPrice = tripPrice;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getAirCondition() {
		return airCondition;
	}
	public void setAirCondition(String airCondition) {
		this.airCondition = airCondition;
	}
	public String getStereo() {
		return stereo;
	}
	public void setStereo(String stereo) {
		this.stereo = stereo;
	}
	public String getChargePerKm() {
		return ChargePerKm;
	}
	public void setChargePerKm(String chargePerKm) {
		ChargePerKm = chargePerKm;
	}
	public String getAdvancePayment() {
		return AdvancePayment;
	}
	public void setAdvancePayment(String advancePayment) {
		AdvancePayment = advancePayment;
	}
	public String getSpecialOffers() {
		return SpecialOffers;
	}
	public void setSpecialOffers(String specialOffers) {
		SpecialOffers = specialOffers;
	}
	public String getTermCondition() {
		return termCondition;
	}
	public void setTermCondition(String termCondition) {
		this.termCondition = termCondition;
	}
	public String getCanPolicy() {
		return canPolicy;
	}
	public void setCanPolicy(String canPolicy) {
		this.canPolicy = canPolicy;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getTransmissionType() {
		return transmissionType;
	}
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}
	public String getNofBaggage() {
		return NofBaggage;
	}
	public void setNofBaggage(String nofBaggage) {
		NofBaggage = nofBaggage;
	}
	
	
}
