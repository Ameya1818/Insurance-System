package com.insurancesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.Category;
import com.insurancesystem.repository.PolicyCategoryRepository;
import com.insurancesystem.service.PolicyCategoryService;

@Service
public class PolicyCategoryImpl implements PolicyCategoryService {

	@Autowired
	private PolicyCategoryRepository categoryRepository;
	@Override
	public Category createCategory(Category category) {
		if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
			throw new IllegalArgumentException("Category name '" + category.getCategoryName() + "' already exists.");
		}

		return categoryRepository.save(category);

	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll(); // (List all categories)
	}
}
