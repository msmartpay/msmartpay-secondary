package com.flights.domestic.services.pojo;

import java.util.List;

	public class DomesticAirAccountingRecord 
	{

		private String bookingDate;									// FSTable
		private String bookingTime;									// FSTable
		private String partnerRefID;								// FSTable
		private String flight;										// FSTable
		private String sector;										// FSTable
		private String bookingClass;								// FSTable
		private String travelDate;									// FSTable
		private String adultFare;									// FSTable
		private String childFare;									// FSTable
		private String infantFare;									// FSTable
		private String adultTaxBreakup;								// FSTable
		private String ChildTaxBreakup;								// FSTable
		private String infantTaxBreakup;							// FSTable

		private double openingbalance;								// ATTable Agent_balAmt_b_Ded
		private double closingBalance;								// AtTable Agent_balAmt_A_Ded
		private double netAmount;									// ATTable
		private double tdsInPercentage;								// ATTable
		private double tdsInValue;									// ATTable
		private double transactionFeeReversed;							// ATTable
	
		private List<PessangerForReport> pessangers;					// PSTable
	
		private String distributorID;								// LDTable
		private String masterDistributorID;							// LDTable
	
		private String arzooReferenceID;							// ABTable
		private String journeyType;									// ABTable
		private String agentID;										// ABTable
		private String bookingStatus;								// ABTable
	
		private String journey;										// ODTable
		private int adultPax;										// ODTable
		private int childPax;										// ODTable
		private int infantPax;										// ODTable
		private String taxes;										// ODTable
		private int serviceTaxes;									// ODTable
		private String serviceCharge;								// ODTable
		private String partnerCommission;							// ODTable
		private int printableMRP;									// ODTable
		private int agentCommission;								// ODTable
		private int basicFare;										// ODTable
		private int grossFare;										// ODTable
		private int transactionFees;								// ODTable
		private double commInBasicInPercentage;						// ODTable
		private double commInGrossInPercentage;						// ODTable
		private double commInYQInPercentage;						// ODTable
		private double fixAgentComm;								// ODTable
		private double commInBasicInValue;							// ODTable
		private double commInGrossInValue;							// ODTable
		private double commInYQInValue;								// ODTable
		private int totalAgentMarkup;								// ODTable	
	
	

		private String paymentGatewayCharges;						// No
		private String DSCommissionInRps;							// No
		private String DSCommission;								// No
	
		/**
		 * @return the bookingDate
		 */
		public String getBookingDate() {
			return bookingDate;
		}
		/**
		 * @param bookingDate the bookingDate to set
		 */
		public void setBookingDate(String bookingDate) {
			this.bookingDate = bookingDate;
		}
		/**
		 * @return the bookingTime
		 */
		public String getBookingTime() {
			return bookingTime;
		}
		/**
		 * @param bookingTime the bookingTime to set
		 */
		public void setBookingTime(String bookingTime) {
			this.bookingTime = bookingTime;
		}
		/**
		 * @return the agentID
		 */
		public String getAgentID() {
			return agentID;
		}
		/**
		 * @param agentID the agentID to set
		 */
		public void setAgentID(String agentID) {
			this.agentID = agentID;
		}
		/**
		 * @return the distributorID
		 */
		public String getDistributorID() {
			return distributorID;
		}
		/**
		 * @param distributorID the distributorID to set
		 */
		public void setDistributorID(String distributorID) {
			this.distributorID = distributorID;
		}
		/**
		 * @return the masterDistributorID
		 */
		public String getMasterDistributorID() {
			return masterDistributorID;
		}
		/**
		 * @param masterDistributorID the masterDistributorID to set
		 */
		public void setMasterDistributorID(String masterDistributorID) {
			this.masterDistributorID = masterDistributorID;
		}
		/**
		 * @return the partnerRefID
		 */
		public String getPartnerRefID() {
			return partnerRefID;
		}
		/**
		 * @param partnerRefID the partnerRefID to set
		 */
		public void setPartnerRefID(String partnerRefID) {
			this.partnerRefID = partnerRefID;
		}
		/**
		 * @return the arzooReferenceID
		 */
		public String getArzooReferenceID() {
			return arzooReferenceID;
		}
		/**
		 * @param arzooReferenceID the arzooReferenceID to set
		 */
		public void setArzooReferenceID(String arzooReferenceID) {
			this.arzooReferenceID = arzooReferenceID;
		}
		/**
		 * @return the journeyType
		 */
		public String getJourneyType() {
			return journeyType;
		}
		/**
		 * @param journeyType the journeyType to set
		 */
		public void setJourneyType(String journeyType) {
			this.journeyType = journeyType;
		}

		/**
		 * @return the sector
		 */
		public String getSector() {
			return sector;
		}
		/**
		 * @param sector the sector to set
		 */
		public void setSector(String sector) {
			this.sector = sector;
		}
		/**
		 * @return the bookingClass
		 */
		public String getBookingClass() {
			return bookingClass;
		}
		/**
		 * @param bookingClass the bookingClass to set
		 */
		public void setBookingClass(String bookingClass) {
			this.bookingClass = bookingClass;
		}
		/**
		 * @return the travelDate
		 */
		public String getTravelDate() {
			return travelDate;
		}
		/**
		 * @param travelDate the travelDate to set
		 */
		public void setTravelDate(String travelDate) {
			this.travelDate = travelDate;
		}
		/**
		 * @return the bookingStatus
		 */
		public String getBookingStatus() {
			return bookingStatus;
		}
		/**
		 * @param bookingStatus the bookingStatus to set
		 */
		public void setBookingStatus(String bookingStatus) {
			this.bookingStatus = bookingStatus;
		}
		/**
		 * @return the adultPax
		 */
		public int getAdultPax() {
			return adultPax;
		}
		/**
		 * @param adultPax the adultPax to set
		 */
		public void setAdultPax(int adultPax) {
			this.adultPax = adultPax;
		}
		/**
		 * @return the childPax
		 */
		public int getChildPax() {
			return childPax;
		}
		/**
		 * @param childPax the childPax to set
		 */
		public void setChildPax(int childPax) {
			this.childPax = childPax;
		}
		/**
		 * @return the infantPax
		 */
		public int getInfantPax() {
			return infantPax;
		}
		/**
		 * @param infantPax the infantPax to set
		 */
		public void setInfantPax(int infantPax) {
			this.infantPax = infantPax;
		}
		/**
		 * @return the adultFare
		 */
		public String getAdultFare() {
			return adultFare;
		}
		/**
		 * @param adultFare the adultFare to set
		 */
		public void setAdultFare(String adultFare) {
			this.adultFare = adultFare;
		}
		/**
		 * @return the childFare
		 */
		public String getChildFare() {
			return childFare;
		}
		/**
		 * @param childFare the childFare to set
		 */
		public void setChildFare(String childFare) {
			this.childFare = childFare;
		}
		/**
		 * @return the infantFare
		 */
		public String getInfantFare() {
			return infantFare;
		}
		/**
		 * @param infantFare the infantFare to set
		 */
		public void setInfantFare(String infantFare) {
			this.infantFare = infantFare;
		}
		/**
		 * @return the basicFare
		 */
		public int getBasicFare() {
			return basicFare;
		}
		/**
		 * @param basicFare the basicFare to set
		 */
		public void setBasicFare(int basicFare) {
			this.basicFare = basicFare;
		}
		/**
		 * @return the adultTaxBreakup
		 */
		public String getAdultTaxBreakup() {
			return adultTaxBreakup;
		}
		/**
		 * @param adultTaxBreakup the adultTaxBreakup to set
		 */
		public void setAdultTaxBreakup(String adultTaxBreakup) {
			this.adultTaxBreakup = adultTaxBreakup;
		}
		/**
		 * @return the childTaxBreakup
		 */
		public String getChildTaxBreakup() {
			return ChildTaxBreakup;
		}
		/**
		 * @param childTaxBreakup the childTaxBreakup to set
		 */
		public void setChildTaxBreakup(String childTaxBreakup) {
			ChildTaxBreakup = childTaxBreakup;
		}
		/**
		 * @return the infantTaxBreakup
		 */
		public String getInfantTaxBreakup() {
			return infantTaxBreakup;
		}
		/**
		 * @param infantTaxBreakup the infantTaxBreakup to set
		 */
		public void setInfantTaxBreakup(String infantTaxBreakup) {
			this.infantTaxBreakup = infantTaxBreakup;
		}
		/**
		 * @return the taxes
		 */
		public String getTaxes() {
			return taxes;
		}
		/**
		 * @param taxes the taxes to set
		 */
		public void setTaxes(String taxes) {
			this.taxes = taxes;
		}
		/**
		 * @return the serviceTaxes
		 */
		public int getServiceTaxes() {
			return serviceTaxes;
		}
		/**
		 * @param serviceTaxes the serviceTaxes to set
		 */
		public void setServiceTaxes(int serviceTaxes) {
			this.serviceTaxes = serviceTaxes;
		}
		/**
		 * @return the transactionFees
		 */
		public int getTransactionFees() {
			return transactionFees;
		}
		/**
		 * @param transactionFees the transactionFees to set
		 */
		public void setTransactionFees(int transactionFees) {
			this.transactionFees = transactionFees;
		}
		/**
		 * @return the printableMRP
		 */
		public int getPrintableMRP() {
			return printableMRP;
		}
		/**
		 * @param printableMRP the printableMRP to set
		 */
		public void setPrintableMRP(int printableMRP) {
			this.printableMRP = printableMRP;
		}
		/**
		 * @return the transactionFeeReversed
		 */
		public double getTransactionFeeReversed() {
			return transactionFeeReversed;
		}
		/**
		 * @param transactionFeeReversed the transactionFeeReversed to set
		 */
		public void setTransactionFeeReversed(double transactionFeeReversed) {
			this.transactionFeeReversed = transactionFeeReversed;
		}
		/**
		 * @return the partnerCommission
		 */
		public String getPartnerCommission() {
			return partnerCommission;
		}
		/**
		 * @param partnerCommission the partnerCommission to set
		 */
		public void setPartnerCommission(String partnerCommission) {
			this.partnerCommission = partnerCommission;
		}
		/**
		 * @return the commInBasicInPercentage
		 */
		public double getCommInBasicInPercentage() {
			return commInBasicInPercentage;
		}
		/**
		 * @param commInBasicInPercentage the commInBasicInPercentage to set
		 */
		public void setCommInBasicInPercentage(double commInBasicInPercentage) {
			this.commInBasicInPercentage = commInBasicInPercentage;
		}
		/**
		 * @return the commInBasicInValue
		 */
		public double getCommInBasicInValue() {
			return commInBasicInValue;
		}
		/**
		 * @param commInBasicInValue the commInBasicInValue to set
		 */
		public void setCommInBasicInValue(double commInBasicInValue) {
			this.commInBasicInValue = commInBasicInValue;
		}
		/**
		 * @return the commInYQInPercentage
		 */
		public double getCommInYQInPercentage() {
			return commInYQInPercentage;
		}
		/**
		 * @param commInYQInPercentage the commInYQInPercentage to set
		 */
		public void setCommInYQInPercentage(double commInYQInPercentage) {
			this.commInYQInPercentage = commInYQInPercentage;
		}
		/**
		 * @return the commInYQInValue
		 */
		public double getCommInYQInValue() {
			return commInYQInValue;
		}
		/**
		 * @param commInYQInValue the commInYQInValue to set
		 */
		public void setCommInYQInValue(double commInYQInValue) {
			this.commInYQInValue = commInYQInValue;
		}
		/**
		 * @return the grossFare
		 */
		public int getGrossFare() {
			return grossFare;
		}
		/**
		 * @param grossFare the grossFare to set
		 */
		public void setGrossFare(int grossFare) {
			this.grossFare = grossFare;
		}
		/**
		 * @return the commInGrossInPercentage
		 */
		public double getCommInGrossInPercentage() {
			return commInGrossInPercentage;
		}
		/**
		 * @param commInGrossInPercentage the commInGrossInPercentage to set
		 */
		public void setCommInGrossInPercentage(double commInGrossInPercentage) {
			this.commInGrossInPercentage = commInGrossInPercentage;
		}
		/**
		 * @return the commInGrossInValue
		 */
		public double getCommInGrossInValue() {
			return commInGrossInValue;
		}
		/**
		 * @param commInGrossInValue the commInGrossInValue to set
		 */
		public void setCommInGrossInValue(double commInGrossInValue) {
			this.commInGrossInValue = commInGrossInValue;
		}
		/**
		 * @return the fixAgentComm
		 */
		public double getFixAgentComm() {
			return fixAgentComm;
		}
		/**
		 * @param fixAgentComm the fixAgentComm to set
		 */
		public void setFixAgentComm(double fixAgentComm) {
			this.fixAgentComm = fixAgentComm;
		}
		/**
		 * @return the agentCommission
		 */
		public int getAgentCommission() {
			return agentCommission;
		}
		/**
		 * @param agentCommission the agentCommission to set
		 */
		public void setAgentCommission(int agentCommission) {
			this.agentCommission = agentCommission;
		}
	
		/**
		 * @return the netAmount
		 */
		public double getNetAmount() {
			return netAmount;
		}
		/**
		 * @param netAmount the netAmount to set
		 */
		public void setNetAmount(double netAmount) {
			this.netAmount = netAmount;
		}
		/**
		 * @return the tdsInPercentage
		 */
		public double getTdsInPercentage() {
			return tdsInPercentage;
		}
		/**
		 * @param tdsInPercentage the tdsInPercentage to set
		 */
		public void setTdsInPercentage(double tdsInPercentage) {
			this.tdsInPercentage = tdsInPercentage;
		}
		/**
		 * @return the tdsInValue
		 */
		public double getTdsInValue() {
			return tdsInValue;
		}
		/**
		 * @param tdsInValue the tdsInValue to set
		 */
		public void setTdsInValue(double tdsInValue) {
			this.tdsInValue = tdsInValue;
		}
		/**
		 * @return the paymentGatewayCharges
		 */
		public String getPaymentGatewayCharges() {
			return paymentGatewayCharges;
		}
		/**
		 * @param paymentGatewayCharges the paymentGatewayCharges to set
		 */
		public void setPaymentGatewayCharges(String paymentGatewayCharges) {
				this.paymentGatewayCharges = paymentGatewayCharges;
		}
		/**
		 * @return the totalAgentMarkup
		 */
		public int getTotalAgentMarkup() {
			return totalAgentMarkup;
		}
		/**
		 * @param totalAgentMarkup the totalAgentMarkup to set
		 */
		public void setTotalAgentMarkup(int totalAgentMarkup) {
			this.totalAgentMarkup = totalAgentMarkup;
		}
		/**
		 * @return the dSCommissionInRps
		 */
		public String getDSCommissionInRps() {
			return DSCommissionInRps;
		}
		/**
		 * @param dSCommissionInRps the dSCommissionInRps to set
		 */
		public void setDSCommissionInRps(String dSCommissionInRps) {
			DSCommissionInRps = dSCommissionInRps;
		}
		/**
		 * @return the dSCommission
		 */
		public String getDSCommission() {
			return DSCommission;
		}
		/**
		 * @param dSCommission the dSCommission to set
		 */
		public void setDSCommission(String dSCommission) {
			DSCommission = dSCommission;
		}
		/**
		 * @return the openingbalance
		 */
		public double getOpeningbalance() {
			return openingbalance;
		}
		/**
		 * @param openingbalance the openingbalance to set
		 */
		public void setOpeningbalance(double openingbalance) {
			this.openingbalance = openingbalance;
		}
		/**
		 * @return the closingBalance
		 */
		public double getClosingBalance() {
			return closingBalance;
		}
		/**
		 * @param closingBalance the closingBalance to set
		 */
		public void setClosingBalance(double closingBalance) {
			this.closingBalance = closingBalance;
		}
		/**
		 * @return the journey
		 */
		public String getJourney() {
			return journey;
		}
		/**
		 * @param journey the journey to set
		 */
		public void setJourney(String journey) {
			this.journey = journey;
		}
		/**
		 * @return the flight
		 */
		public String getFlight() {
			return flight;
		}
		/**
		 * @param flight the flight to set
		 */
		public void setFlight(String flight) {
			this.flight = flight;
		}
		/**
		 * @return the serviceCharge
		 */
		public String getServiceCharge() {
			return serviceCharge;
		}
		/**
		 * @param serviceCharge the serviceCharge to set
		 */
		public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
		}
		/**
		 * @return the pessangers
		 */
		public List<PessangerForReport> getPessangers() {
			return pessangers;
		}
		/**
		 * @param pessangers the pessangers to set
		 */
		public void setPessangers(List<PessangerForReport> pessangers) {
			this.pessangers = pessangers;
		}
	}
