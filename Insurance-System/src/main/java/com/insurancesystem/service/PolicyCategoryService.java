package com.insurancesystem.service;

import java.util.List;

import com.insurancesystem.entity.Category;
/**
 * This class handles PolicyCategoryService functionality .
 *
 * @author Akshay Khobragade
 * @since 2025-10-20
 */
public interface PolicyCategoryService {
	public Category createCategory(Category category);

	public List<Category> getAllCategories();
}
