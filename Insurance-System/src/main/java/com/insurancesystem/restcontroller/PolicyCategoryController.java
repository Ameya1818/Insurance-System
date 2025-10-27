package com.insurancesystem.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancesystem.entity.Category;
import com.insurancesystem.service.PolicyCategoryService;

@RestController
@RequestMapping(value ="/admin")
public class PolicyCategoryController {

	@Autowired PolicyCategoryService policyCategoryService;
	
	
	@PostMapping("/createPolicy")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category c1 = policyCategoryService.createCategory(category);
    return new ResponseEntity<>(c1, HttpStatus.CREATED);   
}
	
    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = policyCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    // List all categories
//    @GetMapping("/list")
//    public List<Category> listAllCategories() {
//        return policyCategoryService.getAllCategories();
//    }
}
