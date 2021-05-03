package com.activity.adminTask.categoryCreation;

public class CategoryCreationForm {

	private String NewCatName;
	private String existCatName;
	private String service;
	private String categoryName;
	private String status;
	private String validTo;
	private String validFrom;
	private String updateaction;
	
	
	
	public String getValidTo() { 
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getExistCatName() {
		return existCatName;
	}

	public void setExistCatName(String existCatName) {
		this.existCatName = existCatName;
	}

	private String catType;

	public String getCatType() {
		return catType;
	}

	public void setCatType(String catType) {
		this.catType = catType;
	}

	public String getNewCatName() {
		return NewCatName;
	}

	public void setNewCatName(String newCatName) {
		NewCatName = newCatName;
	}

	public String getUpdateaction() {
		return updateaction;
	}

	public void setUpdateaction(String updateaction) {
		this.updateaction = updateaction;
	}
}
