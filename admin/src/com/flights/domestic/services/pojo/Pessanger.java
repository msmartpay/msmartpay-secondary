package com.flights.domestic.services.pojo;

public class Pessanger {
	
	private int psngrId;
	
	private String psngrTitle;
	
	private String psngrFirstName;
	
	private String psngrLastName;
	
	private String age;
	
	private String DOB;
	
	private String eTicket;
	
	private String type;
	
	private String flightuid;
	
	private String passuid;
	
	private int originId;
	
	private int destId;
	
	private String canclId;
	
	private int canclFlag;
	
	private int canclFlagForDepart;
	
	private int canclFlagForReturn;
	
	private String canclStatus;
	
	private String canclStatusForDepart;
	
	private String canclStatusForReturn;
	
	private int departDifferenceForOne;
	
	private int departDifferenceForRound;
	
	private int returnDifferenceForRound;
	
	private String confirmationId;
	
	private String pnrnumber;
	
	private String psngrTicketNo;
	
	private String psngrFlightUid;
	
	private String psngrPassUid;
	
	private String canclIdForDepart;
	
	private String canclIdForReturn;
	

	public String getPsngrTitle() {
		return psngrTitle;
	}

	public void setPsngrTitle(String psngrTitle) {
		this.psngrTitle = psngrTitle;
	}

	public String getPsngrFirstName() {
		return psngrFirstName;
	}

	public void setPsngrFirstName(String psngrFirstName) {
		this.psngrFirstName = psngrFirstName;
	}

	public String getPsngrLastName() {
		return psngrLastName;
	}

	public void setPsngrLastName(String psngrLastName) {
		this.psngrLastName = psngrLastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	/**
	 * @return the eTicket
	 */
	public String geteTicket() {
		return eTicket;
	}

	/**
	 * @param eTicket the eTicket to set
	 */
	public void seteTicket(String eTicket) {
		this.eTicket = eTicket;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the psngrId
	 */
	public int getPsngrId() {
		return psngrId;
	}

	/**
	 * @param psngrId the psngrId to set
	 */
	public void setPsngrId(int psngrId) {
		this.psngrId = psngrId;
	}

	/**
	 * @return the flightuid
	 */
	public String getFlightuid() {
		return flightuid;
	}

	/**
	 * @param flightuid the flightuid to set
	 */
	public void setFlightuid(String flightuid) {
		this.flightuid = flightuid;
	}

	/**
	 * @return the passuid
	 */
	public String getPassuid() {
		return passuid;
	}

	/**
	 * @param passuid the passuid to set
	 */
	public void setPassuid(String passuid) {
		this.passuid = passuid;
	}

	/**
	 * @return the originId
	 */
	public int getOriginId() {
		return originId;
	}

	/**
	 * @param originId the originId to set
	 */
	public void setOriginId(int originId) {
		this.originId = originId;
	}

	/**
	 * @return the destId
	 */
	public int getDestId() {
		return destId;
	}

	/**
	 * @param destId the destId to set
	 */
	public void setDestId(int destId) {
		this.destId = destId;
	}

	/**
	 * @return the canclFlag
	 */
	public int getCanclFlag() {
		return canclFlag;
	}

	/**
	 * @param canclFlag the canclFlag to set
	 */
	public void setCanclFlag(int canclFlag) {
		this.canclFlag = canclFlag;
	}

	/**
	 * @return the canclFlagForDepart
	 */
	public int getCanclFlagForDepart() {
		return canclFlagForDepart;
	}

	/**
	 * @param canclFlagForDepart the canclFlagForDepart to set
	 */
	public void setCanclFlagForDepart(int canclFlagForDepart) {
		this.canclFlagForDepart = canclFlagForDepart;
	}

	/**
	 * @return the canclFlagForReturn
	 */
	public int getCanclFlagForReturn() {
		return canclFlagForReturn;
	}

	/**
	 * @param canclFlagForReturn the canclFlagForReturn to set
	 */
	public void setCanclFlagForReturn(int canclFlagForReturn) {
		this.canclFlagForReturn = canclFlagForReturn;
	}

	/**
	 * @return the canclStatus
	 */
	public String getCanclStatus() {
		return canclStatus;
	}

	/**
	 * @param canclStatus the canclStatus to set
	 */
	public void setCanclStatus(String canclStatus) {
		this.canclStatus = canclStatus;
	}

	/**
	 * @return the canclStatusForDepart
	 */
	public String getCanclStatusForDepart() {
		return canclStatusForDepart;
	}

	/**
	 * @param canclStatusForDepart the canclStatusForDepart to set
	 */
	public void setCanclStatusForDepart(String canclStatusForDepart) {
		this.canclStatusForDepart = canclStatusForDepart;
	}

	/**
	 * @return the canclStatusForReturn
	 */
	public String getCanclStatusForReturn() {
		return canclStatusForReturn;
	}

	/**
	 * @param canclStatusForReturn the canclStatusForReturn to set
	 */
	public void setCanclStatusForReturn(String canclStatusForReturn) {
		this.canclStatusForReturn = canclStatusForReturn;
	}

	/**
	 * @return the departDifferenceForOne
	 */
	public int getDepartDifferenceForOne() {
		return departDifferenceForOne;
	}

	/**
	 * @param departDifferenceForOne the departDifferenceForOne to set
	 */
	public void setDepartDifferenceForOne(int departDifferenceForOne) {
		this.departDifferenceForOne = departDifferenceForOne;
	}

	/**
	 * @return the departDifferenceForRound
	 */
	public int getDepartDifferenceForRound() {
		return departDifferenceForRound;
	}

	/**
	 * @param departDifferenceForRound the departDifferenceForRound to set
	 */
	public void setDepartDifferenceForRound(int departDifferenceForRound) {
		this.departDifferenceForRound = departDifferenceForRound;
	}

	/**
	 * @return the returnDifferenceForRound
	 */
	public int getReturnDifferenceForRound() {
		return returnDifferenceForRound;
	}

	/**
	 * @param returnDifferenceForRound the returnDifferenceForRound to set
	 */
	public void setReturnDifferenceForRound(int returnDifferenceForRound) {
		this.returnDifferenceForRound = returnDifferenceForRound;
	}

	/**
	 * @return the confirmationId
	 */
	public String getConfirmationId() {
		return confirmationId;
	}

	/**
	 * @param confirmationId the confirmationId to set
	 */
	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}

	/**
	 * @return the pnrnumber
	 */
	public String getPnrnumber() {
		return pnrnumber;
	}

	/**
	 * @param pnrnumber the pnrnumber to set
	 */
	public void setPnrnumber(String pnrnumber) {
		this.pnrnumber = pnrnumber;
	}

	/**
	 * @return the psngrTicketNo
	 */
	public String getPsngrTicketNo() {
		return psngrTicketNo;
	}

	/**
	 * @param psngrTicketNo the psngrTicketNo to set
	 */
	public void setPsngrTicketNo(String psngrTicketNo) {
		this.psngrTicketNo = psngrTicketNo;
	}

	/**
	 * @return the psngrFlightUid
	 */
	public String getPsngrFlightUid() {
		return psngrFlightUid;
	}

	/**
	 * @param psngrFlightUid the psngrFlightUid to set
	 */
	public void setPsngrFlightUid(String psngrFlightUid) {
		this.psngrFlightUid = psngrFlightUid;
	}

	/**
	 * @return the psngrPassUid
	 */
	public String getPsngrPassUid() {
		return psngrPassUid;
	}

	/**
	 * @param psngrPassUid the psngrPassUid to set
	 */
	public void setPsngrPassUid(String psngrPassUid) {
		this.psngrPassUid = psngrPassUid;
	}

	/**
	 * @return the canclId
	 */
	public String getCanclId() {
		return canclId;
	}

	/**
	 * @param canclId the canclId to set
	 */
	public void setCanclId(String canclId) {
		this.canclId = canclId;
	}

	/**
	 * @return the canclIdForDepart
	 */
	public String getCanclIdForDepart() {
		return canclIdForDepart;
	}

	/**
	 * @param canclIdForDepart the canclIdForDepart to set
	 */
	public void setCanclIdForDepart(String canclIdForDepart) {
		this.canclIdForDepart = canclIdForDepart;
	}

	/**
	 * @return the canclIdForReturn
	 */
	public String getCanclIdForReturn() {
		return canclIdForReturn;
	}

	/**
	 * @param canclIdForReturn the canclIdForReturn to set
	 */
	public void setCanclIdForReturn(String canclIdForReturn) {
		this.canclIdForReturn = canclIdForReturn;
	}
}
