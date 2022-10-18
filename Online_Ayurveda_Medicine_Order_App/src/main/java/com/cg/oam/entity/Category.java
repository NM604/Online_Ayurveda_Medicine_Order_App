package com.cg.oam.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	String categoryId;
	String categoryName;

	public Category(){}
	/**
	 * @param categoryId
	 * @param categoryName
	 */
	public Category(String categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	/** 
	 * @return String
	 */
	public String getCategoryId() {
		return categoryId;
	}
	
	/** 
	 * @param categoryId
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	/** 
	 * @return String
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/** 
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	

}
