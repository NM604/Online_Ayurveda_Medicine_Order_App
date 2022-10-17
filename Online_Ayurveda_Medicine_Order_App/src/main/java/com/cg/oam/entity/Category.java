package com.cg.oam.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
	String categoryId;
	String categoryName;
	public Category(){}
	public Category(String categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
