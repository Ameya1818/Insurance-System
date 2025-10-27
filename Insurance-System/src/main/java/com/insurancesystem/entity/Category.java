package com.insurancesystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Design a PolicyCategory table to group policies under
//categories (Health, Vehicle, Term Life, etc.) for better classification.
/**
 * This class handles Category entities.
 *
 * @author Akshay Khobragade
 * @since 2025-10-20
 */
@Entity
@Table(name="policies")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long categoryId;
	@Column(name = "category_name", nullable = false)
	private String categoryName;
	@Column(name = "category_description")
	private String categoryDescription;
	
//	@ManyToOne
//	private Policy policy;
	public Category() {}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public Category(long categoryId, String categoryName, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}
	
	
}
