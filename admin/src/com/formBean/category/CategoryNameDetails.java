/**
 * Created Date---9/5/2012
 * Created By---Amit Pathak
 * Purpose- The class(CategoryNameDetails)is created for mapping with table Category_Name_Details
 * Updated Date-9/5/2012
 * Updated By--Amit Pathak
 * Update Purpose-
 */

package com.formBean.category;

import java.util.Date;

public class CategoryNameDetails {

	private String categoryName;
	private String updatedUser;
	private Date updatedDate;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		System.out.println("e");
		this.categoryName = categoryName; 
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		System.out.println("eg");
		this.updatedUser = updatedUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		System.out.println("err");
		this.updatedDate = updatedDate;
	}

}