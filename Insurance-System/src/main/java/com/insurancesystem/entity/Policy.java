package com.insurancesystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * This class handles Policy entities.
 *
 * @author Akshay Khobragade
 * @since 2025-10-20
 */
@Entity
@Table(name="policy")
public class Policy {
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_Id", nullable = false, updatable = false)
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
