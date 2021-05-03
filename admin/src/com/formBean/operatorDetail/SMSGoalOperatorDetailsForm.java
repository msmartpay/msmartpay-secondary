package com.formBean.operatorDetail;

import java.util.Date;

public class SMSGoalOperatorDetailsForm {
	private String SubService;
	private String operator;
	private String Vendor;
	private String updatedUser;
	private Date updateDate;

	/**
	 * @return the subService
	 */
	public String getSubService() {
		return SubService;
	}

	/**
	 * @param subService
	 *            the subService to set
	 */
	public void setSubService(String subService) {
		SubService = subService;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return Vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		Vendor = vendor;
	}

	/**
	 * @return the updatedUser
	 */
	public String getUpdatedUser() {
		return updatedUser;
	}

	/**
	 * @param updatedUser
	 *            the updatedUser to set
	 */
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
