package com.flights.domestic.services.pojo;

import java.util.List;

public class DomesticAirCancellatioAccountingRecord {
	

	private String partnerRefID;								// FSTable
	private String flight;										// FSTable
	private String sector;										// ABTable
	private String travelDate;									// ABTable

	private double openingbalance;								// ATTable Agent_balAmt_b_Ded
	private double closingBalance;								// AtTable Agent_balAmt_A_Ded
	private double refundedAmount;								// ATTable
	private double tdsInPercentage;								// ATTable
	
	private List<PessangerForReport> pessangers;				// PSTable
	
	private String arzooReferenceID;							// ABTable
	private String journeyType;									// ABTable
	private String agentID;										// ABTable
	
	private int taxes;										// ODTable
	private int serviceTaxes;									// ODTable
	private int partnerCommission;							// ODTable
	private int basicFare;										// ODTable
	private int grossFare;										// ODTable
	
	private String cancellationCharges; 						// ATDTable
	private String cancellationDate;							// ATDTable
	private String cancellationTime;							// ATDTable
	
	
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
	 * @return the taxes
	 */
	public int getTaxes() {
		return taxes;
	}
	/**
	 * @param taxes the taxes to set
	 */
	public void setTaxes(int taxes) {
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
	 * @return the partnerCommission
	 */
	public int getPartnerCommission() {
		return partnerCommission;
	}
	/**
	 * @param partnerCommission the partnerCommission to set
	 */
	public void setPartnerCommission(int partnerCommission) {
		this.partnerCommission = partnerCommission;
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
	 * @return the cancellationCharges
	 */
	public String getCancellationCharges() {
		return cancellationCharges;
	}
	/**
	 * @param cancellationCharges the cancellationCharges to set
	 */
	public void setCancellationCharges(String cancellationCharges) {
		this.cancellationCharges = cancellationCharges;
	}
	/**
	 * @return the cancellationDate
	 */
	public String getCancellationDate() {
		return cancellationDate;
	}
	/**
	 * @param cancellationDate the cancellationDate to set
	 */
	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	/**
	 * @return the cancellationTime
	 */
	public String getCancellationTime() {
		return cancellationTime;
	}
	/**
	 * @param cancellationTime the cancellationTime to set
	 */
	public void setCancellationTime(String cancellationTime) {
		this.cancellationTime = cancellationTime;
	}
	/**
	 * @return the refundedAmount
	 */
	public double getRefundedAmount() {
		return refundedAmount;
	}
	/**
	 * @param refundedAmount the refundedAmount to set
	 */
	public void setRefundedAmount(double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}

}
