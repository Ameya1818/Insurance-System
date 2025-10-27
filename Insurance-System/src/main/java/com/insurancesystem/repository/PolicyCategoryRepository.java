package com.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancesystem.entity.Category;
/**
 * This class handles JpaRepository .
 *
 * @author Akshay Khobragade
 * @since 2025-10-20
 */
@Repository
public interface PolicyCategoryRepository extends JpaRepository<Category, Long> {

	public boolean existsByCategoryName(String categoryName);

}
